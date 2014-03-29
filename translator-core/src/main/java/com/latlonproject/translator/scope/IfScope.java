package com.latlonproject.translator.scope;

import com.google.common.collect.Lists;
import com.sun.source.tree.Tree;
import java.util.List;

public class IfScope extends LineEntity {
    private String condition;
    private String trueBranch;
    private String falseBranch;
    private boolean isTerminal;

    private BlockScope blockScope;
    private List<Scope> entities = Lists.newLinkedList();

    public IfScope() {
        kind = Tree.Kind.IF;
    }

    public void setCondition(final String condition) {
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }

    public String getTrueBranch() {
        return trueBranch;
    }

    public void setTrueBranch(final String trueBranch) {
        this.trueBranch = trueBranch;
    }

    public String getFalseBranch() {
        return falseBranch;
    }

    public void setFalseBranch(final String falseBranch) {
        this.falseBranch = falseBranch;
    }

    public BlockScope getBlockScope() {
        return blockScope;
    }

    public void setBlockScope(final BlockScope blockScope) {
        this.blockScope = blockScope;
    }

    public List<Scope> getEntities() {
        return entities;
    }

    public void setEntities(final List<Scope> entities) {
        this.entities = entities;
    }

    public boolean isTerminal() {
        return isTerminal;
    }

    public void setTerminal(final boolean isTerminal) {
        this.isTerminal = isTerminal;
    }
}
