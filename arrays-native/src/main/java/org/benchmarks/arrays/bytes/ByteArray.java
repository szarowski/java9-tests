package org.benchmarks.arrays.bytes;

public class ByteArray {

    protected byte[] array;

    public ByteArray(int length) {
        array = new byte[length];
    }

    public byte get(int index) {
        return array[index];
    }

    public ByteArray set(int index, byte value) {
        array[index] = value;
        return this;
    }
}
