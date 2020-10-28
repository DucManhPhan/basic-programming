package com.mp.constants;

import lombok.Data;

@Data
public class Field {

    private FieldType fieldType;

    private String name;

    public static Field var(String name) {
        Field field = new Field();
        field.fieldType = FieldType.VAR;
        field.name = name;

        return field;
    }

    public static Field constant(String name) {
        Field field = new Field();
        field.fieldType = FieldType.CONST;
        field.name = name;

        return field;
    }

}
