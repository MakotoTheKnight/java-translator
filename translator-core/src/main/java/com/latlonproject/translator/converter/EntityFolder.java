package com.latlonproject.translator.converter;

import com.latlonproject.translator.scope.BlockScope;
import com.latlonproject.translator.scope.CatchScope;
import com.latlonproject.translator.scope.ClassScope;
import com.latlonproject.translator.scope.IfScope;
import com.latlonproject.translator.scope.LineEntity;
import com.latlonproject.translator.scope.MethodScope;
import com.latlonproject.translator.scope.ModifierScope;
import com.latlonproject.translator.scope.Scope;
import com.latlonproject.translator.scope.TryScope;
import com.latlonproject.translator.scope.VariableEntity;
import com.latlonproject.translator.scope.VariableScope;
import com.google.common.collect.Lists;
import com.sun.source.tree.Tree;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class EntityFolder {
    public static List<ClassScope> fold(final List<? extends Scope> entityList) {
        // First, find all of the anchor ClassScope entities.
        // Let the presentation layer deal with peeling a single class off and reading it.

        final List<ClassScope> foldedClassHolder = Lists.newLinkedList();
        int[] bounds = findSublistIndices(0, entityList, Tree.Kind.CLASS);
        while(bounds[0] < bounds[1]) {
            final List<? extends Scope> partition = entityList.subList(bounds[0], bounds[1]);
            final List<MethodScope> methods = Lists.newLinkedList();
            ClassScope classScope = (ClassScope) partition.remove(0);
            classScope.setClassModifiers(((ModifierScope) partition.remove(0)));

            for(Iterator<? extends Scope> iterator = partition.iterator(); iterator.hasNext(); ) {
                Scope entity = iterator.next();
                if(entity.getKind().equals(Tree.Kind.VARIABLE)) {
                    VariableScope variableScope = (VariableScope) entity;
                    classScope.addField(variableScope);
                    iterator.remove();
                } else if(entity.getKind() == Tree.Kind.MODIFIERS) {
                    iterator.remove();
                } else if(entity.getKind() != Tree.Kind.METHOD) {
                    throw new IllegalStateException("I don't belong here...");
                } else {
                    // Find the entity, collapse it into the appropriate location.
                    // Remove the entity from the list afterwards.
                    final List<Scope> methodBlock = Lists.newLinkedList();
                    int ceil = findSublistIndices(0, partition, Tree.Kind.METHOD)[1];
                    iterator.remove();
                    // Offset by 1 due to earlier remove call
                    for(int i = 0; i < ceil - 1; i++) {
                        methodBlock.add(iterator.next());
                        iterator.remove();
                    }
                    MethodScope methodScope = (MethodScope) entity;
                    methodScope.setMethodModifiers((ModifierScope) methodBlock.remove(0));
                    for(int i = 0; i < methodScope.getParamMap().size(); i++) {
                        // remove the variable and modifiers from the method explicitly here...
                        methodBlock.remove(0);
                        methodBlock.remove(0);
                    }
                    BlockScope blockScope = collapseMethodEntities((BlockScope) methodBlock.remove(0), methodBlock);
                    methodScope.addBlockScope(blockScope);
                    methods.add(methodScope);
                }
            }
            classScope.setMethods(methods);
            foldedClassHolder.add(classScope);
            bounds = findSublistIndices(0, entityList, Tree.Kind.CLASS);
        }

        return foldedClassHolder;
    }

    private static BlockScope collapseMethodEntities(final BlockScope blockScope, final List<Scope> methodBlock) {
        for(ListIterator<Scope> iterator = methodBlock.listIterator(); iterator.hasNext(); ) {
            Scope scope = iterator.next();
            if(!Tree.Kind.MODIFIERS.equals(scope.getKind())) {
                if(Tree.Kind.VARIABLE.equals(scope.getKind())) {
                    scope = new VariableEntity((VariableScope) scope);
                } else if(scope.getKind().equals(Tree.Kind.IF)) {
                    IfScope ifScope = (IfScope) scope;
                    // Fold elements that aren't block elements.
                    List<Scope> subElements = Lists.newArrayList();
                    Scope nextEntity = iterator.next();
                    if(nextEntity.getKind().equals(Tree.Kind.BLOCK)) {
                        iterator.remove();
                    }
                    while(iterator.hasNext()) {
                        nextEntity = iterator.next();
                        if(nextEntity.getKind().equals(Tree.Kind.IF)) {
                            iterator.previous();
                            break;
                        }
                        iterator.remove();
                        if(nextEntity.getKind() != Tree.Kind.BLOCK) {
                            // known terminal
                            subElements.add(nextEntity);
                        }
                    }
                    ifScope.setEntities(subElements);
                } else if(scope.getKind().equals(Tree.Kind.TRY)) {
                    // Fold elements of try into stack...
                    List<Scope> tryList = new LinkedList<>();
                    List<Scope> catchList = new LinkedList<>();
                    tryList.add(scope);
                    scope = iterator.next();
                    while(!scope.getKind().equals(Tree.Kind.CATCH)) {
                        tryList.add(scope);
                        iterator.remove();
                        scope = iterator.next();
                    }
                    int statementCount = 0;
                    int expectedStatementSize = ((CatchScope)scope).getExpectedStatementSize();

                    while(statementCount < expectedStatementSize) {
                        catchList.add(scope);
                        iterator.remove();
                        if(scope.getKind().equals(Tree.Kind.EXPRESSION_STATEMENT)) {
                            statementCount++;
                        } else {
                            scope = iterator.next();
                        }
                    }
                    // Fold try...

                    TryScope tryScope = (TryScope) tryList.remove(0);
                    // sanitize modifiers
                    for(Iterator<Scope> iter = tryList.iterator(); iter.hasNext(); ) {
                        Scope next = iter.next();
                        if(next.getKind().equals(Tree.Kind.MODIFIERS)) {
                            iter.remove();
                        }
                    }
                    BlockScope tryBlock = (BlockScope) tryList.remove(0);
                    for(Scope s : tryList) {
                        if(s.getKind().equals(Tree.Kind.VARIABLE)) {
                            VariableEntity varEnt = new VariableEntity((VariableScope)s);
                            tryBlock.addLineEntity(varEnt);
                        } else {
                            tryBlock.addLineEntity((LineEntity) s);
                        }
                    }
                    tryScope.setBlockScope(tryBlock);

                    // Fold catch....
                    CatchScope catchScope = (CatchScope) catchList.remove(0);
                    catchScope.setVariableScope((VariableScope)catchList.remove(0));
                    // remove modifiers
                    catchList.remove(0);
                    BlockScope catchBlock = (BlockScope) catchList.remove(0);
                    for(Scope s : catchList) {
                        catchBlock.addLineEntity((LineEntity)s);
                    }
                    catchScope.setBlockScope(catchBlock);
                    tryScope.addCatch(catchScope);
                    scope = tryScope;
                }
                blockScope.addLineEntity((LineEntity) scope);
            }
        }

        return blockScope;
    }


    private static int[] findSublistIndices(final int start, final List<? extends Scope> entityList,
                                            final Tree.Kind kind) {
        boolean hasAnchor = false;
        int newEnd = 0;
        for(ListIterator<? extends Scope> iterator = entityList.listIterator(); iterator.hasNext(); ) {
            Scope next = iterator.next();
            if(next.getKind().equals(kind) && !hasAnchor) {
                hasAnchor = true;
            } else if(next.getKind().equals(kind) && hasAnchor) {
                newEnd = iterator.previousIndex();
                break;
            } else if(!iterator.hasNext()) {
                newEnd = iterator.nextIndex();
                break;
            }
        }
        return new int[]{ start, newEnd };
    }
}
