package org.benchmarks.arrays.generic;

import java.nio.ByteOrder;
import java.util.Arrays;

public class GenericArray {

    protected static boolean bigEndian = ByteOrder.BIG_ENDIAN.equals(ByteOrder.nativeOrder());

    private static final String BYTE = "byte";
    private static final String SHORT = "short";
    private static final String INT = "int";
    private static final String FLOAT = "float";
    private static final String LONG = "long";
    private static final String DOUBLE = "double";

    protected final String typeName;

    protected long[] array;

    public <T> GenericArray(Class<T> type, int capacity) {
        this.typeName = type.getSimpleName();
        switch (typeName) {
            case BYTE:
                array = new long[(capacity >> 3) + capacity % 8];
                break;
            case SHORT:
                array = new long[(capacity >> 2) + capacity % 4];
                break;
            case INT:
                array = new long[(capacity >> 1) + capacity % 2];
                break;
            case FLOAT:
                array = new long[(capacity >> 1) + capacity % 2];
                break;
            case LONG:
                array = new long[capacity];
                break;
            case DOUBLE:
                array = new long[capacity];
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    public Number get(int index) {
        switch (typeName) {
            case BYTE:
                return getByte(index);
            case SHORT:
                return getShort(index);
            case INT:
                return getInt(index);
            case FLOAT:
                return getFloat(index);
            case LONG:
                return getLong(index);
            case DOUBLE:
                return getDouble(index);
        }
        return null;
    }

    public byte getByte(int index) {
        return (byte) getInternal(index, 3, 8, 3);
    }

    public short getShort(int index) {
        return (short) getInternal(index, 2, 4, 4);
    }

    public int getInt(int index) {
        return (int) getInternal(index, 1, 2, 5);
    }

    public float getFloat(int index) {
        return (float) getInternal(index, 1, 2, 5);
    }

    public long getLong(int index) {
        return array[index];
    }

    public double getDouble(int index) {
        return (double) array[index];
    }

    private long getInternal(int index, int div, int modulo, int type) {
        int i = index >> div;
        if (i < 0 || i > array.length) throw new ArrayIndexOutOfBoundsException();
        int shift = (++index % modulo) << type;
        long mask = ((0xFFFFFFFFL >> (div << 3)) >>> shift) | ((0xFFFFFFFFL >> (div << 3)) << (Long.SIZE - shift));
        return bigEndian ? (array[i] & mask) << (shift - (1 << type)) : (array[i] & mask) >>> (Long.SIZE - shift);
    }

    public GenericArray set(int index, byte value) {
        setInternal(index, value, 3, 8, 3);
        return this;
    }

    public GenericArray set(int index, short value) {
        setInternal(index, value, 2, 4, 4);
        return this;
    }

    public GenericArray set(int index, int value) {
        setInternal(index, value, 1, 2, 5);
        return this;
    }

    public GenericArray set(int index, float value) {
        setInternal(index, (long) value, 1, 2, 5);
        return this;
    }

    public GenericArray set(int index, long value) {
        array[index] = value;
        return this;
    }

    public GenericArray set(int index, double value) {
        array[index] = (long) value;
        return this;
    }

    private void setInternal(int index, long value, int div, int modulo, int type) {
        int i = index >> div;
        if (i < 0 || i > array.length) throw new ArrayIndexOutOfBoundsException();
        int shift = (++index % modulo) << type;
        long mask = 0xFFFFFFFFL >> (div << 3);
        mask = (mask >>> shift) | (mask << (Long.SIZE - shift));
        value = bigEndian ? (value >>> (shift - (1 << type))) & mask : (value << (Long.SIZE - shift)) & mask;
        array[i] = array[i] & ~mask | value;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }
}
