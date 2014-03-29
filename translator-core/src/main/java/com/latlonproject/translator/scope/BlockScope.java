package com.latlonproject.translator.scope;

import com.sun.source.tree.Tree;
import java.util.LinkedList;
import java.util.List;

public class BlockScope extends LineEntity {

    private boolean isStatic;

    private List<LineEntity> scopedLineEntities = new LinkedList<>();

    public BlockScope() {
        kind = Tree.Kind.BLOCK;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(final boolean isStatic) {
        this.isStatic = isStatic;
    }

    public void addLineEntity(final LineEntity lineEntity) {
        scopedLineEntities.add(lineEntity);
    }

    public List<LineEntity> getScopedLineEntities() {
        return scopedLineEntities;
    }
}
