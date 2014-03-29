package com.latlonproject.translator.scope;

import com.latlonproject.translator.scope.holders.BodyScope;
import com.latlonproject.translator.scope.holders.LoopConstructHolder;
import com.sun.source.tree.Tree;

public class EnhancedForScope extends LoopConstructHolder {

    private String expression;
    private String variable;
    private BodyScope body;

    public EnhancedForScope() {
        kind = Tree.Kind.ENHANCED_FOR_LOOP;
        body = new BodyScope();
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(final String expression) {
        this.expression = expression;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(final String variable) {
        this.variable = variable;
    }

    public BodyScope getBody() {
        return body;
    }

    public void setBody(BodyScope body) {
        this.body = body;
    }
}
