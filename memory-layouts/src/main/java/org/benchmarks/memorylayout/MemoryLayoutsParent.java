package org.benchmarks.memorylayout;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 100, timeUnit = TimeUnit.MILLISECONDS)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 1)
public abstract class MemoryLayoutsParent {

    static final int NUM_RECORDS = 25 * 1000* 1000;  // 25 000 000 items

    interface MemoryLayout {
        long getTradeId();
        void setTradeId(final long tradeId);
        long getClientId();
        void setClientId(final long clientId);
        int getVenueCode();
        void setVenueCode(final int venueCode);
        int getInstrumentCode();
        void setInstrumentCode(final int instrumentCode);
        long getPrice();
        void setPrice(final long price);
        long getQuantity();
        void setQuantity(final long quantity);
        char getSide();
        void setSide(final char side);
    }

    abstract MemoryLayout create(int index);

    abstract MemoryLayout get(int index);

    abstract void set(int index, MemoryLayout layout);

    void init() {
        final byte[] londonStockExchange = {'X', 'L', 'O', 'N'};
        final int venueCode = pack(londonStockExchange);

        final byte[] billiton = {'B', 'H', 'P'};
        final int instrumentCode = pack(billiton);

        for (int i = 0; i < NUM_RECORDS; i++) {
            MemoryLayout trade = create(i);

            trade.setTradeId(i);
            trade.setClientId(1);
            trade.setVenueCode(venueCode);
            trade.setInstrumentCode(instrumentCode);
            trade.setPrice(i);
            trade.setQuantity(i);
            trade.setSide((i & 1) == 0 ? 'B' : 'S');

            set(i, trade);
        }
    }

    void process() {
        long buyCost = 0;
        long sellCost = 0;

        for (int i = 0; i < NUM_RECORDS; i++) {
            final MemoryLayout trade = get(i);

            if (trade.getSide() == 'B') {
                buyCost += (trade.getPrice() * trade.getQuantity());
            } else {
                sellCost += (trade.getPrice() * trade.getQuantity());
            }
        }
        System.out.println("buyCost = " + buyCost + " sellCost = " + sellCost);
    }

    void destroy() { System.gc(); }

    private int pack(final byte[] value) {
        int result = 0;
        switch (value.length) {
            case 4:
                result |= (value[3]);
            case 3:
                result |= ((int) value[2] << 8);
            case 2:
                result |= ((int) value[1] << 16);
            case 1:
                result |= ((int) value[0] << 24);
                break;

            default:
                throw new IllegalArgumentException("Invalid array size");
        }

        return result;
    }
}
