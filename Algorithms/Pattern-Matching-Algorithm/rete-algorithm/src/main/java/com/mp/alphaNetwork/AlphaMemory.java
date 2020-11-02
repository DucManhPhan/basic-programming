package com.mp.alphaNetwork;

import com.mp.node.ReteNode;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AlphaMemory {

    private List<WME> wmes;

    // activation with JoinNode
    private List<ReteNode> successors;

    public AlphaMemory() {
        this.wmes = new ArrayList<>();
        this.successors = new ArrayList<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.wmes.stream().forEach(wme -> {
            sb.append(wme.toString() + "\n");
        });

        return sb.toString();
    }
}
