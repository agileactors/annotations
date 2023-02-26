package com.agileactors.annotation.processor;

import com.google.auto.service.AutoService;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

@AutoService(Processor.class)
public class MyOverrideNotifierAnnotationProcessor extends AbstractProcessor {


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Set.of(CompilerAnnotation.class.getName(), Override.class.getName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_11;
    }

    @Override
    public boolean process(Set<? extends TypeElement> supportedAnnotation, RoundEnvironment roundEnv) {
        for (TypeElement annotation : supportedAnnotation) {
            Set<? extends Element> elementsAnnotatedWith = roundEnv.getElementsAnnotatedWith(annotation);
            for (Element annotatedElementFunction : elementsAnnotatedWith) {
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.WARNING,
                        String.format("Method %s is annotated with @%s",
                                annotatedElementFunction.getSimpleName(), annotation.getSimpleName()));
            }
        }
        return true;
    }
}
