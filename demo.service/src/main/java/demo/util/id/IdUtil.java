package demo.util.id;

import java.util.UUID;

/**
 * Created by zhengjianhui on 16/10/7.
 */
public class IdUtil {

    private static CreateLongId idGenerator = new SnowflakeIdGenerator(1);

    public static synchronized long getId() {
        return idGenerator.nextId();
    }

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }
}
