package com.latlonproject.translator.converter;

import com.latlonproject.translator.scope.BlockScope;
import com.latlonproject.translator.scope.MethodScope;
import com.latlonproject.translator.utility.BlockAppender;
import java.util.Map;
import java.util.Set;

public class MethodScopeConverter {
    private final MethodScope methodScope;

    public MethodScopeConverter(final MethodScope methodScope) {
        this.methodScope = methodScope;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("A ");
        if(!methodScope.getMethodModifiers().getModifiers().isEmpty()) {
            Set<String> modifiers = methodScope.getMethodModifiers().getModifiers();
            StringBuilder innerBuilder = new StringBuilder();
            if(!modifiers.contains("private") && !modifiers.contains("public") && !modifiers.contains("protected")) {
                innerBuilder.append("package-private ");
                innerBuilder.append(", ");
            } else {
                for(String str : modifiers) {
                    innerBuilder.append(str);
                    innerBuilder.append(", ");
                }
            }
            builder.append(innerBuilder.toString().substring(0, innerBuilder.length() - 2));
        } if(methodScope.isConstructor()) {
            builder.append(" constructor");
        } else {
            builder.append(String.format(" method named \"%s\"", methodScope.getName()));
        }
        if(!methodScope.getParamMap().isEmpty()) {
            builder.append(", with parameters:\n");
            for(Map.Entry<String, String> parameters : methodScope.getParamMap().entrySet()) {
                builder.append(String.format("\t\t%s %s\n", parameters.getValue(), parameters.getKey()));
            }
        }
        if(!methodScope.getBlockStatements().isEmpty()) {

            BlockAppender blockAppender = new BlockAppender();
            for(BlockScope scope : methodScope.getBlockStatements()) {

                // Append All Line Entities
                blockAppender.appendLineEntity(builder, scope.getScopedLineEntities());


           }
        }

        return builder.toString();
    }
}
