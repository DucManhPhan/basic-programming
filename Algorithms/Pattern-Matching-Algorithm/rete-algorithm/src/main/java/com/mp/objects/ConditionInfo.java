package com.mp.objects;

import com.mp.constants.WMEFieldType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConditionInfo {

    private WMEFieldType fieldArg2;

    private int condNumberOfArg2;

}
