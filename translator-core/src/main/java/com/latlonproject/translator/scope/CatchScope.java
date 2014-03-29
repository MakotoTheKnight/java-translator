package com.latlonproject.translator.scope;

import com.latlonproject.translator.scope.holders.BodyScope;
import com.sun.source.tree.Tree;

public class CatchScope extends LineEntity {

    private String catching;

    private VariableScope variableScope;

    private BodyScope body;

    private BlockScope blockScope;

    private int expectedStatementSize;


    public CatchScope() {
        kind = Tree.Kind.CATCH;
        body = new BodyScope();
    }

    public String getCatching() {
        return catching;
    }

    public void setCatching(String catching) {
        this.catching = catching;
    }

    public BodyScope getBody() {
        return body;
    }

    public void setBody(BodyScope body) {
        this.body = body;
    }

    public VariableScope getVariableScope() {
        return variableScope;
    }

    public void setVariableScope(final VariableScope variableScope) {
        this.variableScope = variableScope;
    }

    public int getExpectedStatementSize() {
        return expectedStatementSize;
    }

    public void setExpectedStatementSize(final int expectedStatementSize) {
        this.expectedStatementSize = expectedStatementSize;
    }

    public BlockScope getBlockScope() {
        return blockScope;
    }

    public void setBlockScope(final BlockScope blockScope) {
        this.blockScope = blockScope;
    }
}
