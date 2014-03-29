package com.latlonproject.translator.converter;

import com.latlonproject.translator.scope.ReturnEntity;

public class ReturnConverter {

    private final ReturnEntity returnEntity;

    public ReturnConverter(final ReturnEntity returnEntity) {
        this.returnEntity = returnEntity;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("\t\t\tReturn the value \"%s\", which is a(n) %s.", returnEntity.getExpression(), returnEntity.getExpressionKind()));
        builder.append("\n");
        return builder.toString();
    }
}
