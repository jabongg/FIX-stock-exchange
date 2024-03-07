package com.staywell.java;

public class SnowflakeGenerator {
    private static final long EPOCH = 1609459200000L; // January 1, 2021 (UTC)
    private static final int SEQUENCE_BITS = 13;
    private static final int WORKER_ID_BITS = 10;

    private static final long MAX_SEQUENCE = (1L << SEQUENCE_BITS) - 1;
    private static final long MAX_WORKER_ID = (1L << WORKER_ID_BITS) - 1;

    private long workerId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    public SnowflakeGenerator(long workerId) {
        if (workerId < 0 || workerId > MAX_WORKER_ID) {
            throw new IllegalArgumentException("Worker ID must be between 0 and " + MAX_WORKER_ID);
        }
        this.workerId = workerId;
    }

    public synchronized long nextId() {
        long timestamp = System.currentTimeMillis();

        if (timestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards. Refusing to generate id for " + (lastTimestamp - timestamp) + " milliseconds");
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - EPOCH) << (SEQUENCE_BITS + WORKER_ID_BITS))
                | (workerId << SEQUENCE_BITS)
                | sequence;
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp = System.currentTimeMillis();
        while (timestamp <= lastTimestamp) {
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    public static void main(String[] args) {
        SnowflakeGenerator generator = new SnowflakeGenerator(1); // Example worker ID
        System.out.println(generator.nextId());
    }
}
