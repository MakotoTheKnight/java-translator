package com.latlonproject.translator.converter;

import com.latlonproject.translator.scope.ClassScope;
import com.latlonproject.translator.scope.MethodScope;
import com.latlonproject.translator.scope.ModifierScope;
import com.latlonproject.translator.scope.VariableScope;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class ClassScopeConverter {

    private final ClassScope classScope;

    public ClassScopeConverter(final ClassScope classScope) {
        this.classScope = classScope;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(String.format("A "));
        if(classScope.getClassModifiers() != null) {
            ModifierScope modifierScope = classScope.getClassModifiers();
            StringBuilder innerBuilder = new StringBuilder();
            for(String s : modifierScope.getModifiers()) {
                innerBuilder.append(s);
                innerBuilder.append(", ");
            }
            builder.append(innerBuilder.toString().substring(0, innerBuilder.length() - 2));
        }
        builder.append(String.format(" class named \"%s\"", classScope.getName()));
        if(!classScope.getGenericTypes().isEmpty()) {
            builder.append(String.format(", with "));
            final StringBuilder aggregate = new StringBuilder();
            for(Map.Entry<String, List<String>> generic : classScope.getGenericTypes().entrySet()) {
                aggregate.append(String.format("type parameter %s", generic.getKey()));
                if(!generic.getValue().isEmpty()) {
                    aggregate.append(String.format(", with type bounds of %s", generic.getValue()));
                }
                aggregate.append(", ");
            }
            builder.append(aggregate.toString().substring(0, aggregate.length() - 2));
        }
        if(StringUtils.isNotEmpty(classScope.getExtendClass())) {
            builder.append(String.format(", which is a child class of \"%s\"", classScope.getExtendClass()));
        }
        if(!classScope.getImplementsClause().isEmpty()) {
            builder.append(String.format(", which implements the %s", classScope.getImplementsClause()));
            builder.append(classScope.getImplementsClause().size() > 1 ? " interfaces" : " interface");
        }
        if(!classScope.getFields().isEmpty()) {
            builder.append(", defined with the following fields:\n");
            for(VariableScope variable : classScope.getFields()) {
                builder.append(String.format("\tA %s %s called \"%s\"", variable.prettyPrintModifiers(), variable.getType(), variable.getName()));
                builder.append("\n");
            }
        }
        for(MethodScope scope : classScope.getMethods()) {
            builder.append("\n\t" + new MethodScopeConverter(scope).toString());
        }
        return builder.toString();
    }

}
