package com.mp.node;

import com.mp.alphaNetwork.WME;
import com.mp.betaNetwork.Token;
import lombok.Data;

import java.util.List;

@Data
public class ProductionNode extends ReteNode {

    private List<Token> tokens;

    private String rhs;

    @Override
    public void rightActivation(WME wme) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void leftActivation(Token token) {
        System.out.println("" + token.toString() + " --- " + this.rhs);
    }

    @Override
    public void leftActivation(Token token, WME wme) {
        Token newToken = new Token(wme, token);
        this.tokens.add(newToken);

        System.out.println("" + newToken.toString() + " --- " + this.rhs);
    }
}
