package com.latlonproject.translator.scope;

import com.sun.source.tree.Tree;

public class ContinueEntity extends LabeledEntity {

    public ContinueEntity() {
        kind = Tree.Kind.CONTINUE;
    }
}
