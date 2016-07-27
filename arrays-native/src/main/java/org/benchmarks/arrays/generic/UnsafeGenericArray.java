package  org.benchmarks.arrays.generic;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeGenericArray extends GenericArray {

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

    public <T> UnsafeGenericArray(Class<T> type, int capacity) {
        super(type, capacity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof UnsafeGenericArray)) return false;

        UnsafeGenericArray that = (UnsafeGenericArray) o;

        int length = this.array.length;
        if (length != that.array.length) return false;
        int baseOffset = unsafe.arrayBaseOffset(long[].class);
        long offset = 0;
        for (int i = 0; i < length; offset = baseOffset + (i++ << 3))
            if (0L != (unsafe.getLong(this.array, offset) ^ unsafe.getLong(that.array, offset))) return false;
        return true;
    }
}
