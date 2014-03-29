package com.latlonproject.translator.scope;

import static com.sun.source.tree.Tree.Kind.EMPTY_STATEMENT;

public class EmptyEntity extends ScopeBase {
    public EmptyEntity() {
        kind = EMPTY_STATEMENT;
    }

    public boolean isExists() {
        return true;
    }
}
