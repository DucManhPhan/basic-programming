package com.mp.node;

import com.mp.alphaNetwork.WME;
import com.mp.betaNetwork.Token;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DummyTopNode extends ReteNode {

    @Override
    public void rightActivation(WME wme) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void leftActivation(Token token) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void leftActivation(Token token, WME wme) {
        throw new UnsupportedOperationException();
    }
}
