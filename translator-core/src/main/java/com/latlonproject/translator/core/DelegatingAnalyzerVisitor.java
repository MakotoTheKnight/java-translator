package com.latlonproject.translator.core;

import com.latlonproject.translator.scope.BlockScope;
import com.latlonproject.translator.scope.BreakEntity;
import com.latlonproject.translator.scope.CaseScope;
import com.latlonproject.translator.scope.CatchScope;
import com.latlonproject.translator.scope.ClassScope;
import com.latlonproject.translator.scope.ContinueEntity;
import com.latlonproject.translator.scope.DoWhileScope;
import com.latlonproject.translator.scope.EmptyEntity;
import com.latlonproject.translator.scope.EnhancedForScope;
import com.latlonproject.translator.scope.ExpressionEntity;
import com.latlonproject.translator.scope.ForScope;
import com.latlonproject.translator.scope.IfScope;
import com.latlonproject.translator.scope.MethodScope;
import com.latlonproject.translator.scope.ModifierScope;
import com.latlonproject.translator.scope.ReturnEntity;
import com.latlonproject.translator.scope.Scope;
import com.latlonproject.translator.scope.SwitchScope;
import com.latlonproject.translator.scope.TernaryScope;
import com.latlonproject.translator.scope.TryScope;
import com.latlonproject.translator.scope.VariableScope;
import com.latlonproject.translator.scope.WhileScope;
import com.google.common.collect.Lists;
import com.sun.source.tree.BlockTree;
import com.sun.source.tree.BreakTree;
import com.sun.source.tree.CaseTree;
import com.sun.source.tree.CatchTree;
import com.sun.source.tree.ClassTree;
import com.sun.source.tree.ConditionalExpressionTree;
import com.sun.source.tree.ContinueTree;
import com.sun.source.tree.DoWhileLoopTree;
import com.sun.source.tree.EmptyStatementTree;
import com.sun.source.tree.EnhancedForLoopTree;
import com.sun.source.tree.ExpressionStatementTree;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.ForLoopTree;
import com.sun.source.tree.IfTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.ModifiersTree;
import com.sun.source.tree.ReturnTree;
import com.sun.source.tree.StatementTree;
import com.sun.source.tree.SwitchTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.TryTree;
import com.sun.source.tree.TypeParameterTree;
import com.sun.source.tree.VariableTree;
import com.sun.source.tree.WhileLoopTree;
import com.sun.source.util.TreePathScanner;
import com.sun.source.util.Trees;
import com.sun.tools.javac.tree.JCTree;
import java.util.ArrayList;
import java.util.List;
import javax.lang.model.element.Modifier;

import static com.latlonproject.translator.core.Constants.CONSTRUCTOR_NAME;

public class DelegatingAnalyzerVisitor extends TreePathScanner<Object, Trees> {
    private List<Scope> classEntityList = new ArrayList<>();

    @Override
    public Object visitClass(final ClassTree node, final Trees trees) {
        final ClassScope classScope = new ClassScope();
        classScope.setName(node.getSimpleName().toString());
        classScope.setExtendClass(null != node.getExtendsClause() ? node.getExtendsClause().toString() : null);
        for(Tree tree : node.getImplementsClause()) {
            classScope.addImplement(tree.toString());
        }
        for(TypeParameterTree expr : node.getTypeParameters()) {
            final String genericType = expr.getName().toString();
            final List<String> bounds = Lists.newLinkedList();
            for(Tree tree : expr.getBounds()) {
                bounds.add(tree.toString());
            }
            classScope.addTypesAndBounds(genericType, bounds);
        }
        classEntityList.add(classScope);
        return super.visitClass(node, trees);
    }

    @Override
    public Object visitMethod(final MethodTree node, final Trees trees) {
        final MethodScope methodScope = new MethodScope();
        if(CONSTRUCTOR_NAME.equals(node.getName().toString())) {
            methodScope.setConstructor(true);
        }
        methodScope.setName(node.getName().toString());
        methodScope.setReturnType(null != node.getReturnType() ? node.getReturnType().toString() : null);
        if(!node.getParameters().isEmpty()) {
            for(VariableTree vars : node.getParameters()) {
                methodScope.establishVariableMapping(vars.getName().toString(), vars.getType().toString());
            }
        }
        for(ExpressionTree expr : node.getThrows()) {
            methodScope.addThrowable(expr.toString());
        }
        for(TypeParameterTree types : node.getTypeParameters()) {
            final String genericType = types.getName().toString();
            final List<String> bounds = Lists.newLinkedList();
            for(Tree tree : types.getBounds()) {
                bounds.add(tree.toString());
            }
            methodScope.addTypesAndBounds(genericType, bounds);
        }

        classEntityList.add(methodScope);
        return super.visitMethod(node, trees);
    }

    @Override
    public Object visitVariable(final VariableTree node, final Trees trees) {
        final VariableScope variableScope = new VariableScope();
        variableScope.setInitializer(null != node.getInitializer() ? node.getInitializer().toString() : null);
        variableScope.setName(node.getName().toString());
        variableScope.setType(node.getType().toString());
        final ModifiersTree variableModifiers = node.getModifiers();
        for(Tree tree : variableModifiers.getAnnotations()) {
            variableScope.setAnnotation(tree.toString());
        }
        for(Modifier modifier: variableModifiers.getFlags()) {
            variableScope.setModifier(modifier.toString());
        }
        classEntityList.add(variableScope);

        return super.visitVariable(node, trees);
    }

    @Override
    public Object visitEmptyStatement(final EmptyStatementTree node, final Trees trees) {
        classEntityList.add(new EmptyEntity());
        return super.visitEmptyStatement(node, trees);
    }

    @Override
    public Object visitBlock(final BlockTree node, final Trees trees) {
        final BlockScope blockScope = new BlockScope();
        blockScope.setStatic(node.isStatic());
        classEntityList.add(blockScope);
        return super.visitBlock(node, trees);
    }

    @Override
    public Object visitDoWhileLoop(final DoWhileLoopTree node, final Trees trees) {
        final DoWhileScope doWhileScope = new DoWhileScope();
        doWhileScope.setCondition(node.getCondition().toString());
        doWhileScope.setStatementCount(((JCTree.JCBlock) ((JCTree.JCDoWhileLoop)node).getStatement()).getStatements().size());
        classEntityList.add(doWhileScope);
        return super.visitDoWhileLoop(node, trees);
    }

    @Override
    public Object visitWhileLoop(final WhileLoopTree node, final Trees trees) {
        final WhileScope whileScope = new WhileScope();
        whileScope.setCondition(node.getCondition().toString());
        /* UGLY HACK - FIXME */
        whileScope.setStatementCount(((JCTree.JCBlock) ((JCTree.JCWhileLoop)node).getStatement()).getStatements().size());
        classEntityList.add(whileScope);
        return super.visitWhileLoop(node, trees);
    }

    @Override
    public Object visitForLoop(final ForLoopTree node, final Trees trees) {
        final ForScope forScope = new ForScope();
        forScope.setCondition(node.getCondition().toString());
        for(StatementTree stmt : node.getInitializer()) {
           forScope.addInitializer(stmt.toString());
        }
        for(StatementTree stmt : node.getUpdate()) {
            forScope.addIncrementor(stmt.toString());
        }
        forScope.setStatementCount(((JCTree.JCBlock) ((JCTree.JCForLoop)node).getStatement()).getStatements().size());
        classEntityList.add(forScope);
        return super.visitForLoop(node, trees);
    }

    @Override
    public Object visitEnhancedForLoop(final EnhancedForLoopTree node, final Trees trees) {
        final EnhancedForScope enhancedForScope = new EnhancedForScope();
        enhancedForScope.setExpression(node.getExpression().toString());
        enhancedForScope.setVariable(node.getVariable().toString());
        enhancedForScope.setStatementCount(((JCTree.JCBlock) ((JCTree.JCEnhancedForLoop)node).getStatement()).getStatements().size());
        classEntityList.add(enhancedForScope);
        return super.visitEnhancedForLoop(node, trees);
    }

    @Override
    public Object visitSwitch(final SwitchTree node, final Trees trees) {
        final SwitchScope switchScope = new SwitchScope();
        switchScope.setCaseCount(node.getCases().size());
        classEntityList.add(switchScope);
        return super.visitSwitch(node, trees);
    }

    @Override
    public Object visitCase(final CaseTree node, final Trees trees) {
        final CaseScope caseScope = new CaseScope();
        caseScope.setDefaultStatement(null == node.getExpression());
        caseScope.setStatementCount(node.getStatements().size());
        classEntityList.add(caseScope);
        return super.visitCase(node, trees);
    }

    @Override
    public Object visitTry(final TryTree node, final Trees trees) {
        final TryScope tryScope = new TryScope();
        tryScope.setHasCatch(null != node.getCatches());
        tryScope.setHasFinallyBlock(null != node.getFinallyBlock());
        tryScope.setHasResources(!node.getResources().isEmpty());
        classEntityList.add(tryScope);
        return super.visitTry(node, trees);
    }

    @Override
    public Object visitCatch(final CatchTree node, final Trees trees) {
        final CatchScope catchScope = new CatchScope();
        catchScope.setExpectedStatementSize(node.getBlock().getStatements().size());
        classEntityList.add(catchScope);
        return super.visitCatch(node, trees);
    }

    @Override
    public Object visitConditionalExpression(final ConditionalExpressionTree node, final Trees trees) {
        final TernaryScope ternaryScope = new TernaryScope();
        ternaryScope.setCondition(node.getCondition().toString());
        ternaryScope.setTrueBranch(node.getTrueExpression().toString());
        ternaryScope.setFalseBranch(node.getFalseExpression().toString());
        classEntityList.add(ternaryScope);
        return super.visitConditionalExpression(node, trees);
    }

    @Override
    public Object visitIf(final IfTree node, final Trees trees) {
        final IfScope ifScope = new IfScope();
        ifScope.setCondition(node.getCondition().toString());
        ifScope.setTrueBranch(node.getThenStatement().toString());
        ifScope.setFalseBranch(null != node.getElseStatement() ? node.getElseStatement().toString() : null);
        ifScope.setTerminal(node.getElseStatement() == null || node.getElseStatement().getKind() == Tree.Kind.BLOCK);
        classEntityList.add(ifScope);
        return super.visitIf(node, trees);
    }

    @Override
    public Object visitExpressionStatement(final ExpressionStatementTree node, final Trees trees) {
        final ExpressionEntity expressionEntity = new ExpressionEntity();
        expressionEntity.setExpressionKind(node.getExpression().getKind());
        expressionEntity.setExpression(node.getExpression().toString());
        classEntityList.add(expressionEntity);
        return super.visitExpressionStatement(node, trees);
    }

    @Override
    public Object visitBreak(final BreakTree node, final Trees trees) {
        final BreakEntity breakEntity = new BreakEntity();
        breakEntity.setHasLabel(node.getLabel() != null);
        breakEntity.setLabelName(node.getLabel() != null ? node.getLabel().toString() : null);
        return super.visitBreak(node, trees);
    }

    @Override
    public Object visitContinue(final ContinueTree node, final Trees trees) {
        final ContinueEntity continueEntity = new ContinueEntity();
        continueEntity.setHasLabel(null != node.getLabel());
        continueEntity.setLabelName(null != node.getLabel() ? node.getLabel().toString() : null);
        classEntityList.add(continueEntity);
        return super.visitContinue(node, trees);
    }

    @Override
    public Object visitReturn(final ReturnTree node, final Trees trees) {
        final ReturnEntity returnEntity = new ReturnEntity();
        returnEntity.setExpression(node.getExpression().toString());
        returnEntity.setExpressionKind(node.getExpression().getKind());
        classEntityList.add(returnEntity);
        return super.visitReturn(node, trees);
    }

    @Override
    public Object visitModifiers(final ModifiersTree node, final Trees trees) {
        final ModifierScope modifierScope = new ModifierScope();
        for(Tree tree : node.getAnnotations()) {
            modifierScope.setAnnotation(tree.toString());
        }
        for(Modifier modifier: node.getFlags()) {
            modifierScope.setModifier(modifier.toString());
        }
        classEntityList.add(modifierScope);
        return super.visitModifiers(node, trees);
    }

    public List<Scope> getClassEntityList() {
        return classEntityList;
    }
}
