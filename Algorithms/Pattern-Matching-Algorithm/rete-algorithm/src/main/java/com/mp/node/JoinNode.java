package com.mp.node;

import com.mp.alphaNetwork.AlphaMemory;
import com.mp.alphaNetwork.WME;
import com.mp.betaNetwork.BetaMemory;
import com.mp.betaNetwork.Token;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class JoinNode extends ReteNode {

    private AlphaMemory alphaMemory;

    private List<TestAtJoinNode> testAtJoinNodes;

    public JoinNode() {
        super();
        this.alphaMemory = null;
        this.testAtJoinNodes = new ArrayList<>();
    }

    @Override
    public void rightActivation(WME wme) {
        if (this.parent instanceof BetaMemory) {
            BetaMemory betaParent = (BetaMemory) (this.parent);
            for (Token token : betaParent.getTokens()) {
                if (!this.performJoinTests(token, wme)) {
                    continue;
                }

                for (ReteNode child : this.children) {
                    child.leftActivation(token, wme);
                }
            }
        } else if (this.parent instanceof DummyTopNode) {
            DummyTopNode dummyTopNode = (DummyTopNode) this.parent;
            for (ReteNode child : this.children) {
                child.leftActivation(null, wme);
            }
        } else {
            System.out.println("Do not satisfy the parent for join node");
        }
    }

    @Override
    public void leftActivation(Token token) {
        for (WME wme : this.alphaMemory.getWmes()) {
            if (!this.performJoinTests(token, wme)) {
                continue;
            }

            for (ReteNode child : this.children) {
                child.leftActivation(token, wme);
            }
        }
    }

    @Override
    public void leftActivation(Token token, WME wme) {
        throw new UnsupportedOperationException();
    }

    public boolean performJoinTests(Token token, WME wme) {
        for (TestAtJoinNode test : this.testAtJoinNodes) {
            String arg1 = wme.getField(test.getFieldArg1());
            WME tmpWme = token.index(test.getCondNumberOfArg2());
            String arg2 = tmpWme.getField(test.getFieldArg2());

            if (!arg1.equals(arg2)) {
                return false;
            }
        }

        return true;
    }
}
