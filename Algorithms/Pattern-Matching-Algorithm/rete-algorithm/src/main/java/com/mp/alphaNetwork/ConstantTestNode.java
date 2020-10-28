package com.mp.alphaNetwork;

import com.mp.constants.WMEFieldType;
import lombok.Data;

import java.util.List;

@Data
public class ConstantTestNode {

    private WMEFieldType testedField;

    private String equalField;

    private AlphaMemory alphaMemory;

    private List<ConstantTestNode> children;

    public ConstantTestNode(WMEFieldType testedField, String equalField,
                            AlphaMemory alphaMemory) {
        this.testedField = testedField;
        this.equalField = equalField;
        this.alphaMemory = alphaMemory;
    }

    @Override
    public String toString() {
        return this.testedField + " --- " + this.equalField;
    }

    public static ConstantTestNode getDummyTopNode() {
        return new ConstantTestNode(WMEFieldType.NONE, "-42", null);
    }

}
