package com.latlonproject.translator.scope;

import com.sun.source.tree.Tree;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class VariableEntity extends LineEntity {

    private String name;

    private String type;

    private String initializer;

    private Set<String> modifiers = new HashSet<>();

    private List<String> annotationModifiers  = new LinkedList<>();

    public VariableEntity() {
        kind = Tree.Kind.VARIABLE;
    }

    public VariableEntity(final VariableScope variableScope) {
        this();
        this.name = variableScope.getName();
        this.type = variableScope.getType();
        this.initializer = variableScope.getInitializer();
        this.modifiers = variableScope.getModifiers();
        this.annotationModifiers = variableScope.getAnnotationModifiers();
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getInitializer() {
        return initializer;
    }

    public void setInitializer(final String initializer) {
        this.initializer = initializer;
    }


}
