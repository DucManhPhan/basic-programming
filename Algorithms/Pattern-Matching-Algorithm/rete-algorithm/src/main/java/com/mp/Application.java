package com.mp;

import com.mp.alphaNetwork.WME;
import com.mp.constants.Condition;
import com.mp.constants.Field;
import com.mp.node.ProductionNode;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        ReteNetwork network = new ReteNetwork();

        // define production
        List<Condition> conds = new ArrayList<Condition>() {{
            add(new Condition(Field.var("x"), Field.constant("on"), Field.var("y")));
            add(new Condition(Field.var("y"), Field.constant("left-on"), Field.var("z")));
        }};

        network.addProduction(conds, "production1");

        // define wmes
        network.addWME(new WME("B1", "on", "B2"));
        network.addWME(new WME("B1", "on", "B3"));
        network.addWME(new WME("B2", "left-on", "B3"));

        List<ProductionNode> productionNodes = network.getProductionNodes();
        productionNodes.stream().forEach(productionNode -> {
            System.out.println(productionNode.toString());
        });
    }
}
