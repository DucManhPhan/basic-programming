package com.mp.alphaNetwork;

import com.mp.constants.WMEFieldType;
import lombok.*;

import java.util.stream.Stream;

@Getter
@Setter
public class WME {

    private String[] fields = new String[3];

    public WME(String id, String attribute, String value) {
        this.fields[WMEFieldType.IDENTITY.ordinal() - 1] = id;
        this.fields[WMEFieldType.ATTRIBUTE.ordinal() -1] = attribute;
        this.fields[WMEFieldType.VALUE.ordinal() - 1] = value;
    }

    public String getField(WMEFieldType type) {
        return this.fields[type.ordinal() - 1];
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
