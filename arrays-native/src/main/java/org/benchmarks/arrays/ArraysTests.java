package org.benchmarks.arrays;

import org.benchmarks.arrays.bytes.ByteArray;
import org.benchmarks.arrays.bytes.Java9ByteArray;
import org.benchmarks.arrays.bytes.RegularByteArray;
import org.benchmarks.arrays.bytes.UnsafeByteArray;

public class ArraysTests {

    public static void main(String[] args) throws Throwable {
        int len = 1024 * 1024 * 32;
        long startTime;
        long endTime;

        System.out.println("Initializing data...");
        System.out.println("------------------------------------------");
        System.out.println("arrays.bytes.RegularByteArray initialization...");
        startTime = System.nanoTime();
        ByteArray regularByteArray1 = new RegularByteArray(len);
        ByteArray regularByteArray2 = new RegularByteArray(len);
        for (int i = 0; i < len; ++i) {
            regularByteArray1.set(i, (byte) (i % 128 + 1));  //No byte will equal to 0
            regularByteArray2.set(i, (byte) (i % 128 + 1));  //No byte will equal to 0
        }
        endTime = System.nanoTime();
        System.out.println("Initialization time : " + ((endTime - startTime) / 1000) + " us.");

        System.out.println("arrays.bytes.UnsafeByteArray initialization...");
        startTime = System.nanoTime();
        ByteArray unsafeByteArray1 = new UnsafeByteArray(len);
        ByteArray unsafeByteArray2 = new UnsafeByteArray(len);
        for (int i = 0; i < len; ++i) {
            unsafeByteArray1.set(i, (byte) (i % 128 + 1));  //No long will equal to 0
            unsafeByteArray2.set(i, (byte) (i % 128 + 1));  //No long will equal to 0
        }
        endTime = System.nanoTime();
        System.out.println("Initialization time : " + ((endTime - startTime) / 1000) + " us.");

        System.out.println("arrays.bytes.Java9ByteArray initialization...");
        startTime = System.nanoTime();
        ByteArray java9ByteArray1 = new Java9ByteArray(len);
        ByteArray java9ByteArray2 = new Java9ByteArray(len);
        for (int i = 0; i < len; ++i) {
            java9ByteArray1.set(i, (byte) (i % 128 + 1));  //No long will equal to 0
            java9ByteArray2.set(i, (byte) (i % 128 + 1));  //No long will equal to 0
        }
        endTime = System.nanoTime();
        System.out.println("Initialization time : " + ((endTime - startTime) / 1000) + " us.");
        System.out.println("------------------------------------------");

        // if you want to set one byte to 0,uncomment the below statement
        // regularByteArray1.set(1234567, (byte) 0);
        // unsafeByteArray1.set(1234567, (byte) 0);
        // java9ByteArray1.set(1234567, (byte) 0);

        /////////////////////////////////////////////////////////////////////////////////////////////////////

        System.out.println("arrays.bytes.RegularByteArray equals...");
        startTime = System.nanoTime();
        boolean regularByteEquals = regularByteArray1.equals(regularByteArray2);
        endTime = System.nanoTime();
        System.out.println("Result: " + regularByteEquals + ", time : " + ((endTime - startTime) / 1000) + " us.");

        System.out.println("arrays.bytes.UnsafeByteArray equals...");
        startTime = System.nanoTime();
        boolean unsafeByteEquals = unsafeByteArray1.equals(unsafeByteArray2);
        endTime = System.nanoTime();
        System.out.println("Result: " + unsafeByteEquals + ", time : " + ((endTime - startTime) / 1000) + " us.");

        System.out.println("arrays.bytes.Java9ByteArray equals...");
        startTime = System.nanoTime();
        boolean java9ByteEquals = java9ByteArray1.equals(java9ByteArray2);
        endTime = System.nanoTime();
        System.out.println("Result: " + java9ByteEquals + ", time : " + ((endTime - startTime) / 1000) + " us.");
        System.out.println("------------------------------------------");
        /////////////////////////////////////////////////////////////////////////////////////////////////////

        System.out.println("arrays.bytes.RegularByteArray get...");
        startTime = System.nanoTime();
        for (int i = 0; i < len; i++) regularByteArray1.get(i);
        endTime = System.nanoTime();
        System.out.println("Time : " + ((endTime - startTime) / 1000) + " us.");

        System.out.println("arrays.bytes.UnsafeByteArray get...");
        startTime = System.nanoTime();
        for (int i = 0; i < len; i++) unsafeByteArray1.get(i);
        endTime = System.nanoTime();
        System.out.println("Time : " + ((endTime - startTime) / 1000) + " us.");

        System.out.println("arrays.bytes.Java9ByteArray get...");
        startTime = System.nanoTime();
        for (int i = 0; i < len; i++) java9ByteArray1.get(i);
        endTime = System.nanoTime();
        System.out.println("Time : " + ((endTime - startTime) / 1000) + " us.");

        /////////////////////////////////////////////////////////////////////////////////////////////////////

        System.out.println("arrays.longs.RegularByteArray get...");
        startTime = System.nanoTime();
        for (int i = 0; i < len; i++) regularByteArray1.get(i);
        endTime = System.nanoTime();
        System.out.println("Time : " + ((endTime - startTime) / 1000) + " us.");

        System.out.println("arrays.longs.UnsafeByteArray get...");
        startTime = System.nanoTime();
        for (int i = 0; i < len; i++) unsafeByteArray1.get(i);
        endTime = System.nanoTime();
        System.out.println("Time : " + ((endTime - startTime) / 1000) + " us.");

        System.out.println("arrays.longs.Java9ByteArray get...");
        startTime = System.nanoTime();
        for (int i = 0; i < len; i++) java9ByteArray1.get(i);
        endTime = System.nanoTime();
        System.out.println("Time : " + ((endTime - startTime) / 1000) + " us.");
    }
}
