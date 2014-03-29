package com.latlonproject.translator.scope;

import com.latlonproject.translator.scope.holders.GenericTypeHolder;
import com.sun.source.tree.Tree;
import java.util.LinkedList;
import java.util.List;

public class ClassScope extends GenericTypeHolder implements NamedScope {

    private String extendClass;

    private List<String> implementsClause = new LinkedList<>();

    private ModifierScope classModifiers;

    private String name;

    private List<MethodScope> methods;

    private List<VariableScope> fields = new LinkedList<>();

    public ClassScope() {
        kind = Tree.Kind.CLASS;
    }

    public String getExtendClass() {
        return extendClass;
    }

    public void setExtendClass(final String extendClass) {
        this.extendClass = extendClass;
    }

    public List<String> getImplementsClause() {
        return implementsClause;
    }

    public void setImplementsClause(final List<String> implementsClause) {
        this.implementsClause = implementsClause;
    }

    public void addImplement(final String implement) {
        implementsClause.add(implement);
    }

    public ModifierScope getClassModifiers() {
        return classModifiers;
    }

    public void setClassModifiers(final ModifierScope classModifiers) {
        this.classModifiers = classModifiers;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(final String name) {
        this.name = name;
    }

    public List<MethodScope> getMethods() {
        return methods;
    }

    public void setMethods(final List<MethodScope> methods) {
        this.methods = methods;
    }

    public List<VariableScope> getFields() {
        return fields;
    }

    public void setFields(final List<VariableScope> fields) {
        this.fields = fields;
    }

    public void addField(final VariableScope field) {
        fields.add(field);
    }
}
