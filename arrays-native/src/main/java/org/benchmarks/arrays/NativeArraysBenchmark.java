package org.benchmarks.arrays;

import org.benchmarks.arrays.bytes.ByteArray;
import org.benchmarks.arrays.bytes.Java9ByteArray;
import org.benchmarks.arrays.bytes.RegularByteArray;
import org.benchmarks.arrays.bytes.UnsafeByteArray;
import org.benchmarks.arrays.generic.GenericArray;
import org.benchmarks.arrays.generic.Java9GenericArray;
import org.benchmarks.arrays.generic.RegularGenericArray;
import org.benchmarks.arrays.generic.UnsafeGenericArray;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@SuppressWarnings("unused")
public class NativeArraysBenchmark {

    private static final int BYTE_ARRAY_SIZE = 1024 * 1024 * 1024; // 1G elements
    private static final long BYTE_ARRAY_SUM = 68719476490L;

    @State(Scope.Benchmark)
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 1)
    @Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(value = 1)
    public static class RegularNativeByteArrayBenchmark {

        private static ByteArray byteArray1 = new RegularByteArray(BYTE_ARRAY_SIZE);
        private static ByteArray byteArray2 = new RegularByteArray(BYTE_ARRAY_SIZE);

        @Setup
        public void setUpRegularByteArrays() throws Exception {
            for (int i = 0; i < BYTE_ARRAY_SIZE; ++i) {
                byteArray1.set(i, (byte) (i % 127 + 1));  //No byte will equal to 0
                byteArray2.set(i, (byte) (i % 127 + 1));  //No byte will equal to 0
            }
        }

        @Benchmark
        public void equalsTestRegularByteArrays() throws Exception {
            if (!byteArray1.equals(byteArray2)) throw new Exception();
        }

        @Benchmark
        public void getTestRegularByteArray() throws Exception {
            long sum = 0;
            for (int i = 0; i < BYTE_ARRAY_SIZE; i++)
                sum += byteArray1.get(i);
            if (sum != BYTE_ARRAY_SUM) throw new Exception();
        }

        @TearDown
        public void tearDownRegularByteArrays() throws Exception {
            byteArray1 = null;
            byteArray2 = null;
            System.gc();
        }
    }

    @State(Scope.Benchmark)
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 1)
    @Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(value = 1)
    public static class UnsafeNativeByteArrayBenchmark {

        private static ByteArray byteArray1 = new UnsafeByteArray(BYTE_ARRAY_SIZE);
        private static ByteArray byteArray2 = new UnsafeByteArray(BYTE_ARRAY_SIZE);

        @Setup
        public void setUpUnsafeByteArrays() throws Exception {
            for (int i = 0; i < BYTE_ARRAY_SIZE; ++i) {
                byteArray1.set(i, (byte) (i % 127 + 1));  //No byte will equal to 0
                byteArray2.set(i, (byte) (i % 127 + 1));  //No byte will equal to 0
            }
        }

        @Benchmark
        public void equalsTestUnsafeByteArrays() throws Exception {
            if (!byteArray1.equals(byteArray2)) throw new Exception();
        }

        @Benchmark
        public void getTestUnsafeByteArray() throws Exception {
            long sum = 0;
            for (int i = 0; i < BYTE_ARRAY_SIZE; i++)
                sum += byteArray1.get(i);
            if (sum != BYTE_ARRAY_SUM) throw new Exception();
        }

        @TearDown
        public void tearDownUnsafeByteArrays() throws Exception {
            byteArray1 = null;
            byteArray2 = null;
            System.gc();
        }

    }

    @State(Scope.Benchmark)
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 1)
    @Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(value = 1)
    public static class Java9NativeByteArrayBenchmark {

        private static ByteArray byteArray1 = new Java9ByteArray(BYTE_ARRAY_SIZE);
        private static ByteArray byteArray2 = new Java9ByteArray(BYTE_ARRAY_SIZE);

        @Setup
        public void setUpJava9ByteArrays() throws Exception {
            for (int i = 0; i < BYTE_ARRAY_SIZE; ++i) {
                byteArray1.set(i, (byte) (i % 127 + 1));  //No byte will equal to 0
                byteArray2.set(i, (byte) (i % 127 + 1));  //No byte will equal to 0
            }
        }

        @Benchmark
        public void equalsTestJava9ByteArrays() throws Exception {
            if (!byteArray1.equals(byteArray2)) throw new Exception();
        }

        @Benchmark
        public void getTestJava9ByteArray() throws Exception {
            long sum = 0;
            for (int i = 0; i < BYTE_ARRAY_SIZE; i++)
                sum += byteArray1.get(i);
            if (sum != BYTE_ARRAY_SUM) throw new Exception();
        }

        @TearDown
        public void tearDownJava9ByteArrays() throws Exception {
            byteArray1 = null;
            byteArray2 = null;
            System.gc();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @State(Scope.Benchmark)
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 1)
    @Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(value = 1)
    public static class RegularByteGenericArrayBenchmark {

        private static GenericArray genericArray1 = new RegularGenericArray(byte.class, BYTE_ARRAY_SIZE);
        private static GenericArray genericArray2 = new RegularGenericArray(byte.class, BYTE_ARRAY_SIZE);

        @Setup
        public void setUpRegularGenericArrays() throws Exception {
            for (int i = 0; i < BYTE_ARRAY_SIZE; ++i) {
                genericArray1.set(i, (byte) (i % 127 + 1));  //No byte will equal to 0
                genericArray2.set(i, (byte) (i % 127 + 1));  //No byte will equal to 0
            }
        }

        @Benchmark
        public void equalsTestRegularGenericArrays() throws Exception {
            if (!genericArray1.equals(genericArray2)) throw new Exception();
        }

        @Benchmark
        public void getTestRegularGenericArray() throws Exception {
            long sum = 0;
            for (int i = 0; i < BYTE_ARRAY_SIZE; i++)
                sum += (byte) genericArray1.get(i);
            if (sum != BYTE_ARRAY_SUM) throw new Exception();
        }

        @TearDown
        public void tearDownRegularGenericArrays() throws Exception {
            genericArray1 = null;
            genericArray2 = null;
            System.gc();
        }
    }

    @State(Scope.Benchmark)
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 1)
    @Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(value = 1)
    public static class UnsafeByteGenericArrayBenchmark {

        private static GenericArray genericArray1 = new UnsafeGenericArray(byte.class, BYTE_ARRAY_SIZE);
        private static GenericArray genericArray2 = new UnsafeGenericArray(byte.class, BYTE_ARRAY_SIZE);

        @Setup
        public void setUpUnsafeGenericArrays() throws Exception {
            for (int i = 0; i < BYTE_ARRAY_SIZE; ++i) {
                genericArray1.set(i, (byte) (i % 127 + 1));  //No byte will equal to 0
                genericArray2.set(i, (byte) (i % 127 + 1));  //No byte will equal to 0
            }
        }

        @Benchmark
        public void equalsTestUnsafeGenericArrays() throws Exception {
            if (!genericArray1.equals(genericArray2)) throw new Exception();
        }

        @Benchmark
        public void getTestUnsafeGenericArray() throws Exception {
            long sum = 0;
            for (int i = 0; i < BYTE_ARRAY_SIZE; i++)
                sum += (byte) genericArray1.get(i);
            if (sum != BYTE_ARRAY_SUM) throw new Exception();
        }

        @TearDown
        public void tearDownUnsafeGenericArrays() throws Exception {
            genericArray1 = null;
            genericArray2 = null;
            System.gc();
        }
    }

    @State(Scope.Benchmark)
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 1)
    @Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(value = 1)
    public static class Java9ByteGenericArrayBenchmark {

        private static GenericArray genericArray1 = new Java9GenericArray(byte.class, BYTE_ARRAY_SIZE);
        private static GenericArray genericArray2 = new Java9GenericArray(byte.class, BYTE_ARRAY_SIZE);

        @Setup
        public void setUpJava9GenericArrays() throws Exception {
            for (int i = 0; i < BYTE_ARRAY_SIZE; ++i) {
                genericArray1.set(i, (byte) (i % 127 + 1));  //No byte will equal to 0
                genericArray2.set(i, (byte) (i % 127 + 1));  //No byte will equal to 0
            }
        }

        @Benchmark
        public void equalsTestJava9GenericArrays() throws Exception {
            if (!genericArray1.equals(genericArray2)) throw new Exception();
        }

        @Benchmark
        public void getTestJava9GenericArray() throws Exception {
            long sum = 0;
            for (int i = 0; i < BYTE_ARRAY_SIZE; i++)
                sum += (byte) genericArray1.get(i);
            if (sum != BYTE_ARRAY_SUM) throw new Exception();
        }

        @TearDown
        public void tearDownJava9GenericArrays() throws Exception {
            genericArray1 = null;
            genericArray2 = null;
            System.gc();
        }
    }
}