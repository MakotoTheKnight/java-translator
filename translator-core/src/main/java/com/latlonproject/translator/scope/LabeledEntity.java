package com.latlonproject.translator.scope;

public abstract class LabeledEntity extends LineEntity {

    private String labelName;

    private boolean hasLabel;

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(final String labelName) {
        this.labelName = labelName;
    }

    public boolean isHasLabel() {
        return hasLabel;
    }

    public void setHasLabel(final boolean hasLabel) {
        this.hasLabel = hasLabel;
    }
}
