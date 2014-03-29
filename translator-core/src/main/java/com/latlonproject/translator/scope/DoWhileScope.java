package com.latlonproject.translator.scope;

import com.latlonproject.translator.scope.enumeration.LoopOrder;
import com.latlonproject.translator.scope.holders.BodyScope;
import com.sun.source.tree.Tree;

public class DoWhileScope extends WhileScope {


    private final LoopOrder loopOrder = LoopOrder.POST_LOOP_CHECK;

    private BodyScope body;

    public DoWhileScope() {
        kind = Tree.Kind.DO_WHILE_LOOP;
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
