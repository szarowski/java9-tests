package org.benchmarks.arrays.bytes;

import java.util.Arrays;

public class Java9ByteArray extends ByteArray {

    public Java9ByteArray(int length) {
        super(length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Java9ByteArray)) return false;

        Java9ByteArray that = (Java9ByteArray) o;

        return Arrays.equals(array, that.array);
    }
}
