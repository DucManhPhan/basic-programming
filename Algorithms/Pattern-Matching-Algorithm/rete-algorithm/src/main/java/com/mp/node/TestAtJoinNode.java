package com.mp.node;

import com.mp.constants.WMEFieldType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TestAtJoinNode {

    private WMEFieldType fieldArg1;

    private WMEFieldType fieldArg2;

    private int condNumberOfArg2;

}
