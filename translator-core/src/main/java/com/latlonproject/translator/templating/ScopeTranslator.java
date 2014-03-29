package com.latlonproject.translator.templating;

import com.latlonproject.translator.converter.ClassScopeConverter;
import com.latlonproject.translator.scope.ClassScope;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ScopeTranslator {
    public static void translateOutput(final List<ClassScope> scopeEntities)
        throws IOException {
        for(ClassScope scope : scopeEntities) {
            FileWriter printWriter = new FileWriter(scope.getName());
            ClassScopeConverter converter = new ClassScopeConverter(scope);
            printWriter.write(converter.toString());
            printWriter.flush();

        }
    }


}
