package com.latlonproject.translator.converter;

import com.latlonproject.translator.scope.ExpressionEntity;
import com.latlonproject.translator.scope.IfScope;
import com.latlonproject.translator.scope.Scope;
import java.util.Iterator;

public class IfConverter {

    private final IfScope ifScope;

    public IfConverter(final IfScope ifScope) {
        this.ifScope = ifScope;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(String.format("\t\tIf %s, then...\n", ifScope.getCondition()));
        for(Iterator<Scope> iterator =  ifScope.getEntities().iterator(); iterator.hasNext(); ) {
            // expect expression or return here
            Scope scope = iterator.next();
            if(iterator.hasNext() && ifScope.isTerminal()) {
               iterator.next();
            }
            ExpressionEntity entity = (ExpressionEntity) scope;
            builder.append("\t\t\t");
            builder.append(entity.getExpression());
            builder.append("\n");
        }
        if(ifScope.isTerminal()) {
            builder.append("\t\tIf all else fails, then...\n");

            if(ifScope.getEntities().size()>0){
                builder.append(String.format("\t\t\t%s\n", ((ExpressionEntity) ifScope.getEntities().get(
                    ifScope.getEntities().size() - 1)).getExpression()));
            }

        } else {
            builder.append("\t\tIf that isn't a true statement, then...\n");
        }

        return builder.toString();
    }

}
