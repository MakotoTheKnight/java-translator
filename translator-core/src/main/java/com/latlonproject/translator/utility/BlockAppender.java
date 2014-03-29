package com.latlonproject.translator.utility;

import com.latlonproject.translator.converter.IfConverter;
import com.latlonproject.translator.converter.ReturnConverter;
import com.latlonproject.translator.converter.TryConverter;
import com.latlonproject.translator.converter.VariableConverter;
import com.latlonproject.translator.scope.ExpressionEntity;
import com.latlonproject.translator.scope.IfScope;
import com.latlonproject.translator.scope.LineEntity;
import com.latlonproject.translator.scope.ReturnEntity;
import com.latlonproject.translator.scope.TryScope;
import com.latlonproject.translator.scope.VariableEntity;

import java.util.List;

public class BlockAppender {


    public void appendLineEntity(StringBuilder builder, List<LineEntity> LineEntities){

        builder.append("\n\t\t -- in scope --\n");
        for(LineEntity entity : LineEntities) {
            switch(entity.getKind()) {
                case RETURN:
                    builder.append(new ReturnConverter((ReturnEntity)entity).toString());
                    break;
                case EXPRESSION_STATEMENT:
                    final ExpressionEntity expressionEntity = (ExpressionEntity) entity;
                    switch(expressionEntity.getExpressionKind()) {
                        case METHOD_INVOCATION:
                            builder.append(
                                    String.format("\t\tA call is made to %s.\n", expressionEntity.getExpression()));
                            break;
                    }
                    break;
                case VARIABLE:
                    builder.append(new VariableConverter(((VariableEntity) entity)).toString());
                    break;
                case IF:
                    builder.append(new IfConverter((IfScope) entity).toString());
                    break;
                case TRY:
                    builder.append(new TryConverter((TryScope) entity).toString());
                    break;
                case ASSIGNMENT:
                    break;
                case FOR_LOOP:
                    break;
                case WHILE_LOOP:
                    break;
                default:
                    break;
            }
        }


    }







}
