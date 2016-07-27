package org.benchmarks.arrays.bytes;

public class RegularByteArray extends ByteArray {

    public RegularByteArray(int length) {
        super(length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof RegularByteArray)) return false;

        RegularByteArray that = (RegularByteArray) o;

        int length = this.array.length;
        if (that.array.length != length) return false;

        for (int i = 0; i < length; i++)
            if (this.array[i] != that.array[i])
                return false;

        return true;
    }
}
