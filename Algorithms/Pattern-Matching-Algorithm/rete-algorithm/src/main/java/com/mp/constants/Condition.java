package com.mp.constants;

import lombok.Getter;

@Getter
public class Condition {

    private Field[] attributes = new Field[3];

    public Condition(Field identity, Field attribute, Field value) {
        this.attributes[0] = identity;
        this.attributes[1] = attribute;
        this.attributes[2] = value;
    }

}
