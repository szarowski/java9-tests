package org.benchmarks.memorylayout;

import org.openjdk.jmh.annotations.Benchmark;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class DirectMemoryLayoutBenchmark extends MemoryLayoutsParent {

    private static Unsafe unsafe;

    private static void prepareUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static DirectMemoryTrade flyweight;
    private static long address;

    @Benchmark
    public void testDirectMemoryLayout() {
        prepareUnsafe();
        flyweight = new DirectMemoryTrade();
        final long requiredHeap = NUM_RECORDS * DirectMemoryTrade.getObjectSize();
        address = unsafe.allocateMemory(requiredHeap);

        init();
        process();
        destroy();
    }

    @Override
    MemoryLayout create(int index) {
        return get(index);
    }

    @Override
    MemoryLayout get(int index) {
        long offset = address + (index * DirectMemoryTrade.getObjectSize());
        flyweight.setObjectOffset(offset);
        return flyweight;
    }

    @Override
    void set(int index, MemoryLayout trade) {
    }

    @Override
    void destroy() {
        unsafe.freeMemory(address);
        super.destroy();
    }

    private static class DirectMemoryTrade implements MemoryLayout {
        private static long offset = 0;

        private static final long tradeIdOffset = offset += 0;
        private static final long clientIdOffset = offset += 8;
        private static final long venueCodeOffset = offset += 8;
        private static final long instrumentCodeOffset = offset += 4;
        private static final long priceOffset = offset += 4;
        private static final long quantityOffset = offset += 8;
        private static final long sideOffset = offset += 8;

        private static final long objectSize = offset += 2;

        private long objectOffset;

        static long getObjectSize() {
            return objectSize;
        }

        void setObjectOffset(final long objectOffset) {
            this.objectOffset = objectOffset;
        }

        @Override
        public long getTradeId() {
            return unsafe.getLong(objectOffset + tradeIdOffset);
        }

        @Override
        public void setTradeId(final long tradeId) {
            unsafe.putLong(objectOffset + tradeIdOffset, tradeId);
        }

        @Override
        public long getClientId() {
            return unsafe.getLong(objectOffset + clientIdOffset);
        }

        @Override
        public void setClientId(final long clientId) {
            unsafe.putLong(objectOffset + clientIdOffset, clientId);
        }

        @Override
        public int getVenueCode() {
            return unsafe.getInt(objectOffset + venueCodeOffset);
        }

        @Override
        public void setVenueCode(final int venueCode) {
            unsafe.putInt(objectOffset + venueCodeOffset, venueCode);
        }

        @Override
        public int getInstrumentCode() {
            return unsafe.getInt(objectOffset + instrumentCodeOffset);
        }

        @Override
        public void setInstrumentCode(final int instrumentCode) {
            unsafe.putInt(objectOffset + instrumentCodeOffset, instrumentCode);
        }

        @Override
        public long getPrice() {
            return unsafe.getLong(objectOffset + priceOffset);
        }

        @Override
        public void setPrice(final long price) {
            unsafe.putLong(objectOffset + priceOffset, price);
        }

        @Override
        public long getQuantity() {
            return unsafe.getLong(objectOffset + quantityOffset);
        }

        @Override
        public void setQuantity(final long quantity) {
            unsafe.putLong(objectOffset + quantityOffset, quantity);
        }

        @Override
        public char getSide() {
            return unsafe.getChar(objectOffset + sideOffset);
        }

        @Override
        public void setSide(final char side) {
            unsafe.putChar(objectOffset + sideOffset, side);
        }
    }
}