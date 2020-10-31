package com.mp.node;

import com.mp.constants.WMEFieldType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
public class TestAtJoinNode {

    private WMEFieldType fieldArg1;

    private WMEFieldType fieldArg2;

    private int condNumberOfArg2;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestAtJoinNode that = (TestAtJoinNode) o;
        return condNumberOfArg2 == that.condNumberOfArg2 &&
                fieldArg1 == that.fieldArg1 &&
                fieldArg2 == that.fieldArg2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldArg1, fieldArg2, condNumberOfArg2);
    }
}
