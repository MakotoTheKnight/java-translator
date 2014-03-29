package com.latlonproject.translator.scope;

import com.sun.source.tree.Tree;

public class ReturnEntity extends ExpressionEntity {

    public ReturnEntity() {
        kind = Tree.Kind.RETURN;
    }

}
