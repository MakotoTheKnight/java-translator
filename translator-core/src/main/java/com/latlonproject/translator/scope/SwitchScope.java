package com.latlonproject.translator.scope;

import com.sun.source.tree.Tree;

import java.util.List;

public class SwitchScope extends LineEntity {

    public int caseCount;

    private List<CaseScope> cases;

    public SwitchScope() {
        kind = Tree.Kind.SWITCH;
    }

    public int getCaseCount() {
        return caseCount;
    }

    public void setCaseCount(final int caseCount) {
        this.caseCount = caseCount;
    }

    public List<CaseScope> getCases() {
        return cases;
    }

    public void setCases(List<CaseScope> cases) {
        this.cases = cases;
    }
}
