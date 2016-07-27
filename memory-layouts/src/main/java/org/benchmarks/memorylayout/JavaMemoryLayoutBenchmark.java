package org.benchmarks.memorylayout;

import org.openjdk.jmh.annotations.Benchmark;

public class JavaMemoryLayoutBenchmark extends MemoryLayoutsParent {

    private static MemoryLayout[] trades;

    @Benchmark
    public void testJavaMemoryLayout() {
        trades = new JavaMemoryTrade[NUM_RECORDS];

        init();
        process();
        destroy();
    }

    @Override
    MemoryLayout create(@SuppressWarnings("unused") int index) {
        return new JavaMemoryLayoutBenchmark.JavaMemoryTrade();
    }

    @Override
    MemoryLayout get(int index) {
        return trades[index];
    }

    @Override
    void set(int index, MemoryLayout trade) {
        trades[index] = trade;
    }

    @Override
    void destroy() {
        super.destroy();
    }

    private static class JavaMemoryTrade implements MemoryLayoutsParent.MemoryLayout {
        private long tradeId;
        private long clientId;
        private int venueCode;
        private int instrumentCode;
        private long price;
        private long quantity;
        private char side;

        @Override
        public long getTradeId() {
            return tradeId;
        }

        @Override
        public void setTradeId(final long tradeId) {
            this.tradeId = tradeId;
        }

        @Override
        public long getClientId() {
            return clientId;
        }

        @Override
        public void setClientId(final long clientId) {
            this.clientId = clientId;
        }

        @Override
        public int getVenueCode() {
            return venueCode;
        }

        @Override
        public void setVenueCode(final int venueCode) {
            this.venueCode = venueCode;
        }

        @Override
        public int getInstrumentCode() {
            return instrumentCode;
        }

        @Override
        public void setInstrumentCode(final int instrumentCode) {
            this.instrumentCode = instrumentCode;
        }

        @Override
        public long getPrice() {
            return price;
        }

        @Override
        public void setPrice(final long price) {
            this.price = price;
        }

        @Override
        public long getQuantity() {
            return quantity;
        }

        @Override
        public void setQuantity(final long quantity) {
            this.quantity = quantity;
        }

        @Override
        public char getSide() {
            return side;
        }

        @Override
        public void setSide(final char side) {
            this.side = side;
        }
    }
}
