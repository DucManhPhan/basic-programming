package com.mp;

import com.mp.alphaNetwork.AlphaMemory;
import com.mp.alphaNetwork.ConstantTestNode;
import com.mp.alphaNetwork.WME;
import com.mp.betaNetwork.BetaMemory;
import com.mp.betaNetwork.Token;
import com.mp.constants.Condition;
import com.mp.constants.Field;
import com.mp.constants.FieldType;
import com.mp.constants.WMEFieldType;
import com.mp.node.*;
import com.mp.objects.ConditionInfo;
import com.mp.utils.ValidationUtils;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
public class ReteNetwork {

    private ConstantTestNode alphaTop;

    private ReteNode betaTop;

    private List<AlphaMemory> alphaMemories;

    private List<BetaMemory> betaMemories;

    private List<ConstantTestNode> constantTestNodes;

    private List<JoinNode> joinNodes;

    private List<ProductionNode> productionNodes;

    private List<WME> wmes;

    public ReteNetwork() {
        this.alphaTop = ConstantTestNode.getDummyTopNode();
        this.betaTop = new DummyTopNode();

        this.alphaMemories = new ArrayList<>();
        this.betaMemories = new ArrayList<>();
        this.constantTestNodes = new ArrayList<>();
        this.joinNodes = new ArrayList<>();
        this.productionNodes = new ArrayList<>();
        this.wmes = new ArrayList<>();
    }

    public void alphaMemoryActivation(AlphaMemory alphaMemory, WME wme) {
        Objects.requireNonNull(alphaMemory);

        alphaMemory.getWmes().add(wme);
        for (ReteNode child : alphaMemory.getSuccessors()) {
            child.rightActivation(wme);
        }
    }

    public boolean constTestNodeActivation(ConstantTestNode testNode, WME wme) {
        Objects.requireNonNull(testNode);

        if (!testNode.getTestedField().equals(WMEFieldType.NONE)) {
            if (!wme.getField(testNode.getTestedField()).equals(testNode.getEqualField())) {
                return false;
            }
        }

        if (Objects.nonNull(testNode.getAlphaMemory())) {
            this.alphaMemoryActivation(testNode.getAlphaMemory(), wme);
        }

        for (ConstantTestNode child : testNode.getChildren()) {
            this.constTestNodeActivation(child, wme);
        }

        return true;
    }

    public void addWME(WME wme) {
        this.wmes.add(wme);
        this.constTestNodeActivation(this.alphaTop, wme);
    }

    public void updateNewNodeWithMatchesFromAbove(ReteNode newNode) {
        Objects.requireNonNull(newNode);
        ReteNode parent = newNode.getParent();

        if (parent instanceof BetaMemory) {
            BetaMemory betaMemory = (BetaMemory) parent;
            for (Token token : betaMemory.getTokens()) {
                newNode.leftActivation(token);
            }
        } else if (parent instanceof JoinNode) {
            JoinNode joinNode = (JoinNode) parent;
            List<ReteNode> children = joinNode.getChildren();
            joinNode.setChildren(Arrays.asList(newNode));

            for (WME wme : joinNode.getAlphaMemory().getWmes()) {
                joinNode.rightActivation(wme);
            }

            joinNode.setChildren(children);
        } else {
            System.out.println("Do not update new node");
        }
    }

    public BetaMemory buildOrShareBetaMemory(ReteNode parent) {
        for (ReteNode child : parent.getChildren()) {
            if (child instanceof BetaMemory) {
                return (BetaMemory) child;
            }
        }

        BetaMemory newBetaMemory = new BetaMemory();
        this.betaMemories.add(newBetaMemory);
        newBetaMemory.setParent(parent);
        parent.getChildren().add(newBetaMemory);
        this.updateNewNodeWithMatchesFromAbove(newBetaMemory);

        return newBetaMemory;
    }

    public JoinNode buildOrShareJoinNode(ReteNode parent, AlphaMemory alphaMemory,
                                         List<TestAtJoinNode> testAtJoinNodes) {
        Objects.requireNonNull(parent);
        Objects.requireNonNull(alphaMemory);
        Objects.requireNonNull(testAtJoinNodes);

        // reuse join node
        for (ReteNode child : parent.getChildren()) {
            if (ValidationUtils.isSameJoinNode(child, alphaMemory, testAtJoinNodes)) {
                return (JoinNode) child;
            }
        }

        // create new join node
        JoinNode newJoinNode = new JoinNode();
        this.joinNodes.add(newJoinNode);
        newJoinNode.setParent(parent);
        parent.getChildren().add(newJoinNode);

        newJoinNode.setTestAtJoinNodes(testAtJoinNodes);
        newJoinNode.setAlphaMemory(alphaMemory);

        alphaMemory.getSuccessors().add(newJoinNode);
        return newJoinNode;
    }

    public ConditionInfo lookupEarlierConditionsWithField(List<Condition> earlierConds, String name) {
        int condNumberOfArg2 = earlierConds.size() - 1;

        for (int i = earlierConds.size() - 1; i >= 0; --i) {
            for (WMEFieldType type : WMEFieldType.values()) {
                if (type.equals(WMEFieldType.NONE)) {
                    continue;
                }

                Condition cond = earlierConds.get(i);
                Field currentField = cond.getAttributes()[type.ordinal() - 1];
                if (!currentField.getFieldType().equals(FieldType.VAR)) {
                    continue;
                }

                if (currentField.getName().equals(name)) {
                    return new ConditionInfo(type, condNumberOfArg2);
                }
            }

            condNumberOfArg2--;
        }

        return new ConditionInfo(WMEFieldType.NONE, -1);
    }

    public List<TestAtJoinNode> getJoinTestNodesFromCondition(Condition condition, List<Condition> earlierConds) {
        List<TestAtJoinNode> results = new ArrayList<>();
        Field[] fields = condition.getAttributes();

        for (int i = 1; i < 4; ++i) {
            if (fields[i - 1].getFieldType().equals(FieldType.VAR)) {
                continue;
            }

            String name = fields[i - 1].getName();
            ConditionInfo infoCondition = lookupEarlierConditionsWithField(earlierConds, name);

            // create TestAtJoinNode object
            TestAtJoinNode testNode = new TestAtJoinNode();
            testNode.setFieldArg1(WMEFieldType.valueOf(i));
            testNode.setCondNumberOfArg2(infoCondition.getCondNumberOfArg2());
            testNode.setFieldArg2(infoCondition.getFieldArg2());

            results.add(testNode);
        }


        return results;
    }

    public ConstantTestNode buildOrShareConstantTestNode(ConstantTestNode parent, WMEFieldType wmeFieldType, String sym) {
        for (ConstantTestNode child : parent.getChildren()) {
            if (child.getTestedField().equals(wmeFieldType)
                    && child.getEqualField().equals(sym)) {
                return child;
            }
        }

        // build a new node
        ConstantTestNode newConstTestNode = new ConstantTestNode(wmeFieldType, sym, null);
        this.constantTestNodes.add(newConstTestNode);
        parent.getChildren().add(newConstTestNode);

        return newConstTestNode;
    }

    public boolean wmePassConstantTestNodes(WME wme, Condition cond) {
        Field[] fields = cond.getAttributes();

        for (int i = 0; i < 3; ++i) {
            if (!fields[i].getFieldType().equals(FieldType.CONST)) {
                continue;
            }

            if (!fields[i].getName().equals(wme.getFields()[i])) {
                return false;
            }
        }

        return true;
    }

    public AlphaMemory buildOrShareAlphaMemory(Condition condition) {
        ConstantTestNode constTestNode = this.alphaTop;
        Field[] fields = condition.getAttributes();

        for (int i = 1; i < 4; ++i) {
            if (!fields[i - 1].getFieldType().equals(FieldType.CONST)) {
                continue;
            }

            String sym = fields[i - 1].getName();
            constTestNode = this.buildOrShareConstantTestNode(constTestNode, WMEFieldType.valueOf(i), sym);
        }

        if (Objects.nonNull(constTestNode.getAlphaMemory())) {
            return constTestNode.getAlphaMemory();
        }

        constTestNode.setAlphaMemory(new AlphaMemory());
        this.alphaMemories.add(constTestNode.getAlphaMemory());

        for (WME wme : this.wmes) {
            if (this.wmePassConstantTestNodes(wme, condition)) {
                this.alphaMemoryActivation(constTestNode.getAlphaMemory(), wme);
            }
        }

        return constTestNode.getAlphaMemory();
    }

    public ProductionNode addProduction(List<Condition> conditions, String rhs) {
        ReteNode currentNode = this.betaTop;
        List<Condition> earlierConds = new ArrayList<>();

        List<TestAtJoinNode> testAtJoinNodes = this.getJoinTestNodesFromCondition(conditions.get(0), earlierConds);
        AlphaMemory alphaMemory = this.buildOrShareAlphaMemory(conditions.get(0));
        currentNode = this.buildOrShareJoinNode(currentNode, alphaMemory, testAtJoinNodes);
        earlierConds.add(conditions.get(0));

        for (int i = 1; i < conditions.size(); ++i) {
            currentNode = this.buildOrShareBetaMemory(currentNode);
            testAtJoinNodes = this.getJoinTestNodesFromCondition(conditions.get(i), earlierConds);
            alphaMemory = this.buildOrShareAlphaMemory(conditions.get(1));
            currentNode = this.buildOrShareJoinNode(currentNode, alphaMemory, testAtJoinNodes);

            earlierConds.add(conditions.get(i));
        }

        ProductionNode productionNode = new ProductionNode();
        this.productionNodes.add(productionNode);
        productionNode.setParent(currentNode);
        productionNode.setRhs(rhs);
        currentNode.getChildren().add(productionNode);

        this.updateNewNodeWithMatchesFromAbove(productionNode);
        return productionNode;
    }

    public void addProductioNode(ProductionNode node) {
        this.productionNodes.add(node);
    }

}
