package com.latlonproject.translator.scope;

import com.latlonproject.translator.scope.holders.BodyScope;
import com.latlonproject.translator.scope.holders.LoopConstructHolder;
import com.sun.source.tree.Tree;
import java.util.LinkedList;
import java.util.List;

public class ForScope extends LoopConstructHolder {

    private List<String> initializers = new LinkedList<>();

    private List<String> incrementers = new LinkedList<>();

    private BodyScope body;

    public ForScope() {
        kind = Tree.Kind.FOR_LOOP;
        body = new BodyScope();
    }

    public List<String> getInitializers() {
        return initializers;
    }

    public void setInitializers(final List<String> initializers) {
        this.initializers = initializers;
    }

    public List<String> getIncrementers() {
        return incrementers;
    }

    public void setIncrementers(final List<String> incrementers) {
        this.incrementers = incrementers;
    }

    public void addInitializer(final String initializer) {
        initializers.add(initializer);
    }

    public void addIncrementor(final String incrementer) {
        incrementers.add(incrementer);
    }

    public BodyScope getBody() {
        return body;
    }

    public void setBody(BodyScope body) {
        this.body = body;
    }
}
