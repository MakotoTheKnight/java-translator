package com.latlonproject.translator.scope;

import com.latlonproject.translator.scope.holders.BodyScope;
import com.google.common.collect.Lists;
import com.sun.source.tree.Tree;
import java.util.List;

public class  TryScope extends LineEntity {

    boolean hasResources;

    boolean hasFinallyBlock;

    boolean hasCatch;

    private BodyScope body;

    private BlockScope blockScope;

    private List<CatchScope> catches = Lists.newLinkedList();

    public TryScope() {
        kind = Tree.Kind.TRY;
        body = new BodyScope();
    }

    public boolean isHasResources() {
        return hasResources;
    }

    public void setHasResources(final boolean hasResources) {
        this.hasResources = hasResources;
    }

    public boolean isHasFinallyBlock() {
        return hasFinallyBlock;
    }

    public void setHasFinallyBlock(final boolean hasFinallyBlock) {
        this.hasFinallyBlock = hasFinallyBlock;
    }

    public boolean isHasCatch() {
        return hasCatch;
    }

    public void setHasCatch(final boolean hasCatch) {
        this.hasCatch = hasCatch;
    }

    public List<CatchScope> getCatches() {
        return catches;
    }

    public void setCatches(List<CatchScope> catches) {
        this.catches = catches;
    }

    public BodyScope getBody() {
        return body;
    }

    public void setBody(BodyScope body) {
        this.body = body;
    }

    public void addCatch(final CatchScope catchScope) {
        catches.add(catchScope);
    }

    public BlockScope getBlockScope() {
        return blockScope;
    }

    public void setBlockScope(final BlockScope blockScope) {
        this.blockScope = blockScope;
    }
}
