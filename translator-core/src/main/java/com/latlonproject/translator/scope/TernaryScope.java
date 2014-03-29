package com.latlonproject.translator.scope;

import com.sun.source.tree.Tree;

public class TernaryScope extends LineEntity {
    private String condition;
    private String trueBranch;
    private String falseBranch;

    public TernaryScope() {
        kind = Tree.Kind.CONDITIONAL_EXPRESSION;
    }

    public void setCondition(final String condition) {
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }

    public void setTrueBranch(final String trueBranch) {
        this.trueBranch = trueBranch;
    }

    public String getTrueBranch() {
        return trueBranch;
    }

    public void setFalseBranch(final String falseBranch) {
        this.falseBranch = falseBranch;
    }

    public String getFalseBranch() {
        return falseBranch;
    }
}
