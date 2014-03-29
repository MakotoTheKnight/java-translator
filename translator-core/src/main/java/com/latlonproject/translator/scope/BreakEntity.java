package com.latlonproject.translator.scope;

import com.sun.source.tree.Tree;

public class BreakEntity extends LabeledEntity {

    public BreakEntity() {
        kind = Tree.Kind.BREAK;
    }
}
