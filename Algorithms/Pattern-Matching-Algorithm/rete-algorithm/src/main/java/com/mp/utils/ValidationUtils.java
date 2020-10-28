package com.mp.utils;

import com.mp.alphaNetwork.AlphaMemory;
import com.mp.node.JoinNode;
import com.mp.node.ReteNode;
import com.mp.node.TestAtJoinNode;

import java.util.List;
import java.util.Objects;

public class ValidationUtils {

    public static boolean isSameJoinNode(ReteNode reteNode, AlphaMemory alphaMemory,
                                 List<TestAtJoinNode> testAtJoinNodes) {

        if (Objects.isNull(reteNode) || !(reteNode instanceof JoinNode)) {
            return false;
        }

        JoinNode joinNode = (JoinNode) reteNode;
        if (joinNode.getAlphaMemory() == alphaMemory
                && joinNode.getTestAtJoinNodes() == testAtJoinNodes) {
            return true;
        }

        return false;
    }

}
