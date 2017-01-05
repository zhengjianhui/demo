package demo.data;

import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.util.StringUtils;

import demo.config.SystemConfig;
import redis.clients.jedis.JedisPoolConfig;

public class ConfigJedisConnectionFactory extends JedisConnectionFactory {

    private static final String DATASOURCE_PREFIX = "redis";
    private static final String DEFAULT_POOL_PROPERTIES_KEY_PREFIX = DATASOURCE_PREFIX + ".default" + ".pool";
    private static final String DEFAULT_CONNECTION_PROPERTIES_KEY_PREFIX = DATASOURCE_PREFIX + ".default";

    private String dataSourceName;

    public ConfigJedisConnectionFactory(String dataSourceName) {
        this.dataSourceName = dataSourceName;

        String currentDatasourcePrefix = DATASOURCE_PREFIX + "." + this.dataSourceName;

        String dbIndex = SystemConfig.getString(currentDatasourcePrefix + ".dbIndex");
        if (StringUtils.isEmpty(dbIndex)) {
            throw new IllegalArgumentException("dbIndex must not null");
        }

        setDatabase(Integer.parseInt(dbIndex));
        setPoolConfig(getJedisPoolConfig());

        int timeout = SystemConfig.getInt(currentDatasourcePrefix + ".timeout", SystemConfig.getInt(DEFAULT_CONNECTION_PROPERTIES_KEY_PREFIX + ".timeout"));
        setTimeout(timeout);
    }

    /**
     * 利用 Spring BeanWrapper  获取jedisPollConfig 的实例
     * @return
     */
    private JedisPoolConfig getJedisPoolConfig() {
        // 获取jedis 的类属性
        BeanWrapper pool = new BeanWrapperImpl(JedisPoolConfig.class);

        // 读取配置文件 将属性对应值注入
        setPoolProperty(pool);

        // 获取jedis 的实例
        return (JedisPoolConfig) pool.getWrappedInstance();
    }

    /**
     * 获取配置文件中的值 注入BeanWrapper 中
     * @param dataSource
     */
    private void setPoolProperty(BeanWrapper dataSource) {
        String currentDatasourcePoolPrefix = DATASOURCE_PREFIX + "." + dataSourceName + ".pool";

        Set<String> keys = SystemConfig.subset(DEFAULT_POOL_PROPERTIES_KEY_PREFIX).getKeys();
        keys.addAll(SystemConfig.subset(currentDatasourcePoolPrefix).getKeys());

        for (String key : keys) {
            dataSource.setPropertyValue(key,
                    SystemConfig.getString(currentDatasourcePoolPrefix + "." + key, SystemConfig.getString(DEFAULT_POOL_PROPERTIES_KEY_PREFIX + "." + key)));
        }
    }
}
