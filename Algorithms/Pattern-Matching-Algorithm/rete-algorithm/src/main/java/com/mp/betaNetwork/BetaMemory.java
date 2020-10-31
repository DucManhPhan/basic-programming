package com.mp.betaNetwork;

import com.mp.alphaNetwork.WME;
import com.mp.node.ReteNode;
import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Data
public class BetaMemory extends ReteNode {

    private LinkedList<Token> tokens;

    public BetaMemory() {
        this.tokens = new LinkedList<>();
    }

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
        if (Objects.nonNull(token)) {
            System.out.println(token.toString());
        } else {
            System.out.println("token = null");
        }

        System.out.println(wme.toString());

        Token newToken = new Token(wme, token);
        this.tokens.addFirst(newToken);

        for (ReteNode child : this.children) {
            child.leftActivation(newToken);
        }
    }
}
