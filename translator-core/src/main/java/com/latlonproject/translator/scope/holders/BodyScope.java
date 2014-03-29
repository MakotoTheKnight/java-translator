package com.latlonproject.translator.scope.holders;

import com.latlonproject.translator.scope.ScopeBase;

import java.util.List;

public class BodyScope {

    List<ScopeBase> statements;

    public List<ScopeBase> getStatements() {
        return statements;
    }

    public void setStatements(List<ScopeBase> statements) {
        this.statements = statements;
    }
}
