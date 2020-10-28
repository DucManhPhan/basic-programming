package com.mp.betaNetwork;

import com.mp.alphaNetwork.WME;
import lombok.Data;

import java.util.Objects;

@Data
public class Token {

    private Token parent;

    private int chainedTokenIdx;

    private WME wme;

    public Token(WME wme, Token parent) {
        this.wme = wme;
        this.parent = parent;
        this.chainedTokenIdx = Objects.isNull(this.parent) ? 0
                                    : this.parent.getChainedTokenIdx() + 1;
    }

    public WME index(int idx) {
        if (idx < 0 || idx >= this.chainedTokenIdx) {
            throw new UnsupportedOperationException();
        }

        if (idx == this.chainedTokenIdx) {
            return this.wme;
        }

        return this.parent.index(idx);
    }

}
