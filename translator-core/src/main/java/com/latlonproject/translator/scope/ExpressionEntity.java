package com.latlonproject.translator.scope;

import static com.sun.source.tree.Tree.Kind;

public class ExpressionEntity extends LineEntity {

    private Kind expressionKind;

    private String expression;

    public ExpressionEntity() {
        kind = Kind.EXPRESSION_STATEMENT;
    }

    public Kind getExpressionKind() {
        return expressionKind;
    }

    public void setExpressionKind(final Kind expressionKind) {
        this.expressionKind = expressionKind;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(final String expression) {
        this.expression = expression;
    }
}
