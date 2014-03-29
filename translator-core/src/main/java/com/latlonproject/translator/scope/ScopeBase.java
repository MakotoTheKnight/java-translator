package com.latlonproject.translator.scope;

import static com.sun.source.tree.Tree.Kind;

public abstract class ScopeBase implements Scope {
    protected Kind kind;

    public Kind getKind() {
        return kind;
    }

}
