package  org.benchmarks.arrays.generic;

import java.util.Arrays;

public class Java9GenericArray extends GenericArray {

    public <T> Java9GenericArray(Class<T> type, int capacity) {
        super(type, capacity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Java9GenericArray)) return false;

        Java9GenericArray that = (Java9GenericArray) o;

        return Arrays.equals(array, that.array);
    }
}
