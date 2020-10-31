package com.mp.node;

import com.mp.alphaNetwork.WME;
import com.mp.betaNetwork.Token;
import lombok.Data;

import java.util.List;

@Data
public abstract class ReteNode {

//    private String nodeType;    // beta memory, join node, production node, ...

    protected ReteNode parent;

    protected List<ReteNode> children;

    public ReteNode() {
        this.parent = null;
    }

    public abstract void rightActivation(WME wme);

    public abstract void leftActivation(Token token);

    public abstract void leftActivation(Token token, WME wme);

}
