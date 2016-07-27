package org.benchmarks.memorylayout;

import org.openjdk.jmh.annotations.Benchmark;

import java.nio.ByteBuffer;

public class ByteBufferMemoryLayoutBenchmark extends MemoryLayoutsParent {

    private static ByteBuffeMemoryTrade flyweight = new ByteBuffeMemoryTrade();

    private static ByteBuffer bb;

    @Benchmark
    public void testByteBufferMemoryLayout() {
        bb = ByteBuffer.allocateDirect(NUM_RECORDS * ByteBuffeMemoryTrade.getObjectSize());

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
        bb.position(index * ByteBuffeMemoryTrade.getObjectSize());
        flyweight.setObjectOffset(bb.position());
        return flyweight;
    }

    @Override
    void set(int index, MemoryLayout trade) {
    }

    @Override
    void destroy() {
        bb.clear();
        super.destroy();
    }

    private static class ByteBuffeMemoryTrade implements MemoryLayout {
        private static int offset = 0;

        private static final int tradeIdOffset = offset += 0;
        private static final int clientIdOffset = offset += 8;
        private static final int venueCodeOffset = offset += 8;
        private static final int instrumentCodeOffset = offset += 4;
        private static final int priceOffset = offset += 4;
        private static final int quantityOffset = offset += 8;
        private static final int sideOffset = offset += 8;

        private static final int objectSize = offset += 2;

        private int objectOffset;

        static int getObjectSize() {
            return objectSize;
        }

        void setObjectOffset(final int objectOffset) {
            this.objectOffset = objectOffset;
        }

        @Override
        public long getTradeId() {
            return bb.getLong(objectOffset + tradeIdOffset);
        }

        @Override
        public void setTradeId(final long tradeId) {
            bb.putLong(objectOffset + tradeIdOffset, tradeId);
        }

        @Override
        public long getClientId() {
            return bb.getLong(objectOffset + clientIdOffset);
        }

        @Override
        public void setClientId(final long clientId) {
            bb.putLong(objectOffset + clientIdOffset, clientId);
        }

        @Override
        public int getVenueCode() {
            return bb.getInt(objectOffset + venueCodeOffset);
        }

        @Override
        public void setVenueCode(final int venueCode) {
            bb.putInt(objectOffset + venueCodeOffset, venueCode);
        }

        @Override
        public int getInstrumentCode() {
            return bb.getInt(objectOffset + instrumentCodeOffset);
        }

        @Override
        public void setInstrumentCode(final int instrumentCode) {
            bb.putInt(objectOffset + instrumentCodeOffset, instrumentCode);
        }

        @Override
        public long getPrice() {
            return bb.getLong(objectOffset + priceOffset);
        }

        @Override
        public void setPrice(final long price) {
            bb.putLong(objectOffset + priceOffset, price);
        }

        @Override
        public long getQuantity() {
            return bb.getLong(objectOffset + quantityOffset);
        }

        @Override
        public void setQuantity(final long quantity) {
            bb.putLong(objectOffset + quantityOffset, quantity);
        }

        @Override
        public char getSide() {
            return bb.getChar(objectOffset + sideOffset);
        }

        @Override
        public void setSide(final char side) {
            bb.putChar(objectOffset + sideOffset, side);
        }
    }
}
