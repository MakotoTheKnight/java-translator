package com.latlonproject.translator.converter;

import com.latlonproject.translator.scope.VariableEntity;

public class VariableConverter {
    private final VariableEntity variableEntity;

    public VariableConverter(final VariableEntity variableEntity) {
        this.variableEntity = variableEntity;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        if(variableEntity.getInitializer() == null) {
            // Declaring
            builder.append(String.format("\t\tDeclare a variable %s with type %s.\n", variableEntity.getName(),
                                         variableEntity.getType()));
        } else if(variableEntity.getType() == null) {
            // Instantiating
            builder.append(String.format("\t\tInstantiate the variable %s with the value %s.\n", variableEntity.getName(),
                                         variableEntity.getInitializer()));
        } else {
            // Both
            builder.append(String.format("\t\tDeclare and instantiate a variable %s with type %s, with the value \"%s\".\n",
                                         variableEntity.getName(), variableEntity.getType(),
                                         variableEntity.getInitializer()));
        }

        return builder.toString();
    }
}
