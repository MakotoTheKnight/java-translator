package com.latlonproject.translator.scope;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static com.sun.source.tree.Tree.Kind;

public class ModifierScope implements Scope {

    protected Set<String> modifiers = new HashSet<>();
    protected List<String> annotationModifiers  = new LinkedList<>();
    protected Kind kind;

    public ModifierScope() {
        kind = Kind.MODIFIERS;
    }

    @Override
    public Kind getKind() {
        return kind;
    }

    public Set<String> getModifiers() {
        return modifiers;
    }

    public void setModifiers(final Set<String> modifiers) {
        this.modifiers = modifiers;
    }

    public void setModifier(final String modifier) {
        modifiers.add(modifier);
    }

    public List<String> getAnnotationModifiers() {
        return annotationModifiers;
    }

    public void setAnnotationModifiers(final List<String> annotationModifiers) {
        this.annotationModifiers = annotationModifiers;
    }

    public void setAnnotation(final String annotation) {
        annotationModifiers.add(annotation);
    }
}
