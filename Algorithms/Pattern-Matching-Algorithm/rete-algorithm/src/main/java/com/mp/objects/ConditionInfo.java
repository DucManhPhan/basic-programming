package com.mp.objects;

import com.mp.constants.WMEFieldType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConditionInfo {

    private WMEFieldType fieldArg2;

    private int condNumberOfArg2;

}
