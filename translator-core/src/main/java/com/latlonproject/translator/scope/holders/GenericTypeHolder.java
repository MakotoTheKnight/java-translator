package com.latlonproject.translator.scope.holders;

import com.latlonproject.translator.scope.ScopeBase;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class GenericTypeHolder extends ScopeBase {

    protected Map<String, List<String>> genericTypes = new LinkedHashMap<>();

    public Map<String, List<String>> getGenericTypes() {
        return genericTypes;
    }

    public void setGenericTypes(final Map<String, List<String>> genericTypes) {
        this.genericTypes = genericTypes;
    }

    public void addTypesAndBounds(final String genericType, final List<String> bounds) {
        genericTypes.put(genericType, bounds);
    }
}
