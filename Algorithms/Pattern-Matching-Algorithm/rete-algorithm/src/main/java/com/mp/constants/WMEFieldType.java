package com.mp.constants;

public enum WMEFieldType {

    NONE,

    IDENTITY,

    ATTRIBUTE,

    VALUE

    ;

    public static WMEFieldType valueOf(int idx) {
        return WMEFieldType.values()[idx];
    }

}
