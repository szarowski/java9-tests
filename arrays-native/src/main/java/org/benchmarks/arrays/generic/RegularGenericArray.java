package  org.benchmarks.arrays.generic;

public class RegularGenericArray extends GenericArray {


    public <T> RegularGenericArray(Class<T> type, int capacity) {
        super(type, capacity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof RegularGenericArray)) return false;

        RegularGenericArray that = (RegularGenericArray) o;

        int length = this.array.length;
        if (that.array.length != length) return false;
        for (int i = 0; i < length; i++)
            if (this.getLong(i) != that.getLong(i))
                return false;
        return true;
    }
}
