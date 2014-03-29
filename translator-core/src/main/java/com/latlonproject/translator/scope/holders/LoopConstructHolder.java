package com.latlonproject.translator.scope.holders;

import com.latlonproject.translator.scope.BlockScope;
import com.latlonproject.translator.scope.LineEntity;

public abstract class LoopConstructHolder extends LineEntity {

    protected String condition;

    private BlockScope blockScope;
    private int statementCount;

    public String getCondition() {
        return condition;
    }

    public void setCondition(final String condition) {
        this.condition = condition;
    }

    public BlockScope getBlockScope() {
        return blockScope;
    }

    public void setBlockScope(final BlockScope blockScope) {
        this.blockScope = blockScope;
    }

    public void setStatementCount(final int statementCount) {
        this.statementCount = statementCount;
    }

    public int getStatementCount() {
        return statementCount;
    }
}
