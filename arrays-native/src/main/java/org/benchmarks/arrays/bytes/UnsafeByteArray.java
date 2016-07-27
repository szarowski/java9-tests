package org.benchmarks.arrays.bytes;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeByteArray extends ByteArray {

    private static Unsafe unsafe;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public UnsafeByteArray(int length) {
        super(length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof UnsafeByteArray)) return false;

        UnsafeByteArray that = (UnsafeByteArray) o;

        int length = this.array.length;
        if (length != that.array.length) return false;
        int baseOffset = unsafe.arrayBaseOffset(long[].class);
        int numLongs = length >> 3;
        long offset = 0;
        int i;
        for (i = 0; i < numLongs; offset = baseOffset + (i++ << 3))
            if (0L != (unsafe.getLong(this.array, offset) ^ unsafe.getLong(that.array, offset)))
                return false;
        int remaining = (length % 8);
        if (remaining == 0) return true;
        offset = baseOffset + (i << 3);
        for (; 0 <= remaining; --remaining)
            if (0L != (unsafe.getByte(this, ++offset) ^ unsafe.getByte(that, offset)))
                return false;
        return true;
    }
}
