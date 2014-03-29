package com.latlonproject.translator.scope;

import com.latlonproject.translator.scope.holders.GenericTypeHolder;
import com.sun.source.tree.Tree;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MethodScope extends GenericTypeHolder implements NamedScope {
    private String returnType;

    private Map<String, String> paramMap = new LinkedHashMap<>();

    private boolean isConstructor = false;

    private List<String> throwables = new LinkedList<>();

    private List<ScopeBase> methodStatements = new LinkedList<>();

    private List<BlockScope> blockStatements = new LinkedList<>();

    private String name;

    private ModifierScope methodModifiers;

    public MethodScope() {
        kind = Tree.Kind.METHOD;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(final String returnType) {
        this.returnType = returnType;
    }

    public Map<String, String> getParamMap() {
        return paramMap;
    }

    public void setParamMap(final Map<String, String> paramMap) {
        this.paramMap = paramMap;
    }

    public boolean isConstructor() {
        return isConstructor;
    }

    public void setConstructor(final boolean isConstructor) {
        this.isConstructor = isConstructor;
    }

    public void establishVariableMapping(final String key, final String value) {
        paramMap.put(key, value);
    }

    public List<String> getThrowables() {
        return throwables;
    }

    public void setThrowables(final List<String> throwables) {
        this.throwables = throwables;
    }

    public void addThrowable(final String throwable) {
        throwables.add(throwable);
    }

    public List<ScopeBase> getMethodStatements() {
        return methodStatements;
    }

    public void setMethodStatements(final List<ScopeBase> methodStatements) {
        this.methodStatements = methodStatements;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(final String name) {
        this.name = name;
    }

    public ModifierScope getMethodModifiers() {
        return methodModifiers;
    }

    public void setMethodModifiers(final ModifierScope methodModifiers) {
        this.methodModifiers = methodModifiers;
    }

    public void addBlockScope(final BlockScope blockScope) {
        blockStatements.add(blockScope);
    }

    public List<BlockScope> getBlockStatements() {
        return blockStatements;
    }
}
