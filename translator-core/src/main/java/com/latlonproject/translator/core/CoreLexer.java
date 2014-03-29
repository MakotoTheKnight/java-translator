package com.latlonproject.translator.core;

import com.latlonproject.translator.converter.EntityFolder;
import com.latlonproject.translator.processors.BasicAnnotationProcessor;
import com.latlonproject.translator.scope.ClassScope;
import com.latlonproject.translator.templating.ScopeTranslator;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Scanner;

import static javax.tools.JavaCompiler.CompilationTask;

public class CoreLexer {
    public static void main(String[] args) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        // Read in the sample files here
        File firstSample;
        File secondSample;
        File thirdSample;
     //   File fourthSample;

        try {
            firstSample = new File(compiler.getClass().getResource("/com/stackoverflow/sample/GradeAverager.java").toURI());
            secondSample = new File(compiler.getClass().getResource("/com/stackoverflow/sample/IF_Parser.java").toURI());
            thirdSample = new File(compiler.getClass().getResource("/com/stackoverflow/sample/Number.java").toURI());
            Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(firstSample);
//                                                                                                 secondSample,
//                                                                                                 thirdSample);
            CompilationTask compilationTask = compiler.getTask(null, fileManager, null, null, null, compilationUnits);
            DelegatingAnalyzerVisitor visitor = new DelegatingAnalyzerVisitor();
            compilationTask.setProcessors(Arrays.asList(new BasicAnnotationProcessor(visitor)));
            compilationTask.call();
            List<ClassScope> collapsedScopes = EntityFolder.fold(visitor.getClassEntityList());
            try {
                ScopeTranslator.translateOutput(collapsedScopes);
            } catch(FileNotFoundException e) {
                System.out.println("That's all folks!");
                System.exit(1337);
            } catch(IOException e) {
                e.printStackTrace();
            }
        } catch(URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
