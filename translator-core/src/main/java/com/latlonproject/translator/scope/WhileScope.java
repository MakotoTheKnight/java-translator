package com.latlonproject.translator.scope;

import com.latlonproject.translator.scope.enumeration.LoopOrder;
import com.latlonproject.translator.scope.holders.BodyScope;
import com.latlonproject.translator.scope.holders.LoopConstructHolder;
import com.sun.source.tree.Tree;

public class WhileScope extends LoopConstructHolder {

    private final LoopOrder loopOrder = LoopOrder.PRE_LOOP_CHECK;

    private BodyScope body;

    public WhileScope() {
        kind = Tree.Kind.WHILE_LOOP;
        body = new BodyScope();
    }

    public LoopOrder getLoopOrder() {
        return loopOrder;
    }

    public BodyScope getBody() {
        return body;
    }

    public void setBody(BodyScope body) {
        this.body = body;
    }
}
