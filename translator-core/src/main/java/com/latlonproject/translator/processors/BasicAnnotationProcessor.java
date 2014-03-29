package com.latlonproject.translator.processors;

import com.latlonproject.translator.core.DelegatingAnalyzerVisitor;
import com.sun.source.util.TreePath;
import com.sun.source.util.Trees;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;

import static javax.lang.model.SourceVersion.RELEASE_7;

@SupportedSourceVersion(RELEASE_7)
@SupportedAnnotationTypes("*")
public class BasicAnnotationProcessor extends AbstractProcessor {

    private DelegatingAnalyzerVisitor visitor;
    private Trees trees;

    public BasicAnnotationProcessor(final DelegatingAnalyzerVisitor visitor) {
        this.visitor = visitor;
    }

    @Override
    public synchronized void init(final ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        trees = Trees.instance(processingEnv);
    }

    @Override
    public boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        for(Element element : roundEnv.getRootElements()) {
            TreePath treePath = trees.getPath(element);
            // TODO:  Analysis hooks go here
            visitor.scan(treePath, trees);
        }
        return true;
    }
}
