package com.latlonproject.translator.converter;

import com.latlonproject.translator.scope.CatchScope;
import com.latlonproject.translator.scope.TryScope;
import com.latlonproject.translator.utility.BlockAppender;

public class TryConverter {


    private final TryScope tryScope;

    public TryConverter(final TryScope tryScope){
        this.tryScope = tryScope;
    }

    @Override
    public String toString(){

        final StringBuilder builder = new StringBuilder();

        // TRY BLOCK

        BlockAppender blockAppender = new BlockAppender();
        builder.append("\t\tAttempt to run the following code:") ;
        blockAppender.appendLineEntity(builder, tryScope.getBlockScope().getScopedLineEntities());

        // CATCH STATEMENT
        for(CatchScope catchScope : tryScope.getCatches()) {

            builder.append(String.format(
                    "\t\tIf the above try failed, catch the exception as a type of %s, and keep the stack trace in the variable %s.\n",
                    catchScope.getVariableScope().getType(), catchScope.getVariableScope().getName()

            ));

            // CATCH BLOCK
            blockAppender.appendLineEntity(builder, catchScope.getBlockScope().getScopedLineEntities());
        }

        return builder.toString();
    }








}
