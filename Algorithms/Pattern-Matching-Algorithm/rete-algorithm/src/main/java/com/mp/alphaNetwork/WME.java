package com.mp.alphaNetwork;

import com.mp.constants.WMEFieldType;
import lombok.*;

import java.util.stream.Stream;

@Getter
@Setter
public class WME {

    String[] fields;

    public WME() {
        this.fields = new String[3];
    }

    public WME(String id, String attribute, String value) {
        this();
        this.fields[WMEFieldType.IDENTITY.ordinal()] = id;
        this.fields[WMEFieldType.ATTRIBUTE.ordinal()] = attribute;
        this.fields[WMEFieldType.VALUE.ordinal()] = value;
    }

    public String getField(WMEFieldType type) {
        return this.fields[type.ordinal()];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Stream.of(this.fields).forEach(field -> {
            sb.append(" --- " + field);
        });

        return sb.toString();
    }
}
