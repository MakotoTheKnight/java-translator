package com.latlonproject.translator.scope;

import com.sun.source.tree.Tree;

public class VariableScope extends ModifierScope {

    private String name;

    private String type;

    private String initializer;

    public VariableScope() {
        kind = Tree.Kind.VARIABLE;
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

    public String prettyPrintModifiers() {
        final StringBuilder builder = new StringBuilder();

        for(String s : getModifiers()) {
            builder.append(s);
            builder.append(", ");
        }
        if(!getModifiers().contains("private") && !getModifiers().contains("public") && !getModifiers().contains("protected")) {
            builder.append("package-private");
            builder.append(", ");
        }
        return builder.toString().substring(0, builder.length() - 2);

    }
}
