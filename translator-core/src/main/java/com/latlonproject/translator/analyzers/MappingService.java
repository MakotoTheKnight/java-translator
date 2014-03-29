package com.latlonproject.translator.analyzers;

import com.latlonproject.translator.scope.ClassScope;
import com.latlonproject.translator.scope.DoWhileScope;
import com.latlonproject.translator.scope.EmptyEntity;
import com.latlonproject.translator.scope.EnhancedForScope;
import com.latlonproject.translator.scope.ExpressionEntity;
import com.latlonproject.translator.scope.ForScope;
import com.latlonproject.translator.scope.IfScope;
import com.latlonproject.translator.scope.Scope;
import com.latlonproject.translator.scope.ScopeBase;
import com.latlonproject.translator.scope.SwitchScope;
import com.latlonproject.translator.scope.TryScope;
import com.latlonproject.translator.scope.WhileScope;
import com.sun.source.tree.StatementTree;
import java.util.ArrayList;
import java.util.List;

public class MappingService {
    public List<ClassScope> classFolder(List<Scope> blockStatements) {

        final List<ScopeBase> statements = new ArrayList<>();

        for(Scope statement : blockStatements) {
            statements.add(buildStatement(statement));
        }

        return null;
    }

    private ClassScope buildStatement(Scope scope) {

        ScopeBase builtStatement = null;

        switch(scope.getKind()) {

            case BLOCK:
                break;

            case BREAK:
                break;

            case CASE:
                break;

            case CATCH:
                break;

            case CLASS:
                break;

            case CONTINUE:
                break;

            case METHOD:
                break;

            case MODIFIERS:
                break;

            case RETURN:
                break;

            case CONDITIONAL_EXPRESSION:
                break;

            case VARIABLE:
                break;

            case DO_WHILE_LOOP:
                builtStatement = buildDoWhileStatement(null);
                break;

            case EMPTY_STATEMENT:

                builtStatement = buildEmptyStatement(null);
                break;

            case ENHANCED_FOR_LOOP:

                builtStatement = buildEnhancedForLoopStatement(null);
                break;

            case EXPRESSION_STATEMENT:

                builtStatement = buildExpressionStatement(null);
                break;

            case FOR_LOOP:

                builtStatement = buildForLoopStatement(null);
                break;

            case IF:

                builtStatement = buildIfStatement(null);
                break;

            case SWITCH:

                builtStatement = buildSwitchStatement(null);
                break;

            case TRY:

                builtStatement = buildTryStatement(null);
                break;

            case WHILE_LOOP:

                builtStatement = buildWhileLoopStatement(null);
                break;

            default:
                // no-op
        }

        return null;
    }

    private DoWhileScope buildDoWhileStatement(DoWhileScope doWhileScope) {
        return null;
    }

    private EmptyEntity buildEmptyStatement(StatementTree statement) {

        EmptyEntity emptyStatement = new EmptyEntity();

        return emptyStatement;
    }

    private EnhancedForScope buildEnhancedForLoopStatement(StatementTree statement) {

        return null;
    }

    private ExpressionEntity buildExpressionStatement(StatementTree statement) {
        return null;
    }

    private ForScope buildForLoopStatement(StatementTree statement) {
        return null;
    }

    private IfScope buildIfStatement(StatementTree statement) {
        return null;
    }

    private SwitchScope buildSwitchStatement(StatementTree statement) {

        return null;
    }

    private TryScope buildTryStatement(StatementTree statement) {

        return null;
    }

    private WhileScope buildWhileLoopStatement(StatementTree statement) {

        return null;
    }
}
