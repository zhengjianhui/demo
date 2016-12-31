package demo.util.id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 参照twitter的snowflake改写的ID生成器实现
 */
class SnowflakeIdGenerator implements CreateLongId {
    private static final Logger logger = LoggerFactory.getLogger(SnowflakeIdGenerator.class);

    private final long workerId;
    private final static long twepoch = 1361753741828L;
    private long sequence = 0L;
    private final static long workerIdBits = 4L;
    public final static long maxWorkerId = -1L ^ -1L << workerIdBits;
    private final static long sequenceBits = 10L;

    private final static long workerIdShift = sequenceBits;
    private final static long timestampLeftShift = sequenceBits + workerIdBits;
    public final static long sequenceMask = -1L ^ -1L << sequenceBits;

    private long lastTimestamp = -1L;

    @Override
    public long nextId() {
        long timestamp = this.timeGen();

        if (this.lastTimestamp == timestamp) {
            this.sequence = (this.sequence + 1) & sequenceMask;
            if (this.sequence == 0) {
                timestamp = this.tilNextMillis(this.lastTimestamp);
            }
        } else {
            this.sequence = 0;
        }

        if (timestamp < this.lastTimestamp) {
            try {
                throw new Exception(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", this.lastTimestamp - timestamp));
            } catch (Exception e) {
                logger.error("生成id发生异常", e);
            }
        }

        this.lastTimestamp = timestamp;
        long nextId = ((timestamp - twepoch << timestampLeftShift)) | (this.workerId << workerIdShift) | (this.sequence);

        // System.out.println(nextId);
        // System.out.println(timestamp - twepoch << timestampLeftShift);
        // System.out.println(this.workerId << workerIdShift);
        // System.out.println(this.sequence);
        //

        return nextId;
    }

    @SuppressWarnings("static-access")
    public SnowflakeIdGenerator(final long workerId) {
        super();
        if (workerId > this.maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", this.maxWorkerId));
        }
        this.workerId = workerId;
    }

    // public static void main(String[] args) {
    // SnowflakeIdGenerator a = new SnowflakeIdGenerator(1);
    // a.nextId();
    // }

    private long tilNextMillis(final long lastTimestamp) {
        long timestamp = this.timeGen();

        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }

        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }
}
