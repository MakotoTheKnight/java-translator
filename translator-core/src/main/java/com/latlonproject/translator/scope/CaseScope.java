package com.latlonproject.translator.scope;

import com.latlonproject.translator.scope.holders.BodyScope;
import com.sun.source.tree.Tree;

public class CaseScope extends LineEntity {

    private boolean isDefaultStatement;

    private String value;

    private int statementCount;

    private BodyScope body;


    public CaseScope() {
        kind = Tree.Kind.CASE;
        body = new BodyScope();
    }

    public boolean isDefaultStatement() {
        return isDefaultStatement;
    }

    public void setDefaultStatement(final boolean isDefaultStatement) {
        this.isDefaultStatement = isDefaultStatement;
    }

    public int getStatementCount() {
        return statementCount;
    }

    public void setStatementCount(final int statementCount) {
        this.statementCount = statementCount;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public BodyScope getBody() {
        return body;
    }

    public void setBody(BodyScope body) {
        this.body = body;
    }
}
