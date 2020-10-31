package com.mp.alphaNetwork;

import com.mp.node.ReteNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlphaMemory {

    private List<WME> wmes;

    // activation with JoinNode
    private List<ReteNode> successors;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.wmes.stream().forEach(wme -> {
            sb.append(wme.toString() + "\n");
        });

        return sb.toString();
    }
}
