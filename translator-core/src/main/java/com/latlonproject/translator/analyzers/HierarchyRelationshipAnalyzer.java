package com.latlonproject.translator.analyzers;

import com.google.common.collect.Sets;
import java.util.Set;
import javax.lang.model.element.TypeElement;

public class HierarchyRelationshipAnalyzer {

    public void determineClassRelationship(final TypeElement typeElement) {
        final Set<String> classInheritanceChain = Sets.newLinkedHashSet();
        classInheritanceChain.add(typeElement.getClass().getSimpleName());
        while(!typeElement.getSuperclass().getClass().equals(Object.class)) {
            classInheritanceChain.add(typeElement.getSuperclass().getClass().getSimpleName());
        }
    }
}
