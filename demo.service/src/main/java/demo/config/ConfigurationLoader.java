package demo.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.StringUtils;

public abstract class ConfigurationLoader {
    private static final Logger logger = LoggerFactory.getLogger(ConfigurationLoader.class);

    private static final String ENV_NAME = "env";

    private static String PARAM_ROOT;

    /**
     * 根据properties 中的值 初始化对应环境配置文件的路径
     */
    static {
        init();
    }


    /**
     * 根据properties 中的值 初始化对应环境配置文件的路径
     */
    private static void init() {
        String paramRoot = System.getProperty(ENV_NAME);
        if (StringUtils.isEmpty(paramRoot)) {
            ResourceLoader loader = new DefaultResourceLoader();
            Resource paramPropertiesResource = loader.getResource("classpath:/vars/param.properties");
            if (!paramPropertiesResource.exists()) {
                throw new ConfigurationException("参数配置文件:" + paramPropertiesResource + "不存在");
            }

            Properties paramProperties = null;
            try {
                paramProperties = PropertiesLoaderUtils.loadProperties(paramPropertiesResource);
            } catch (IOException e) {
                throw new ConfigurationException("加载" + paramPropertiesResource + "发生IO异常", e);
            }

            paramRoot = paramProperties.getProperty("param.root");
            if (StringUtils.isEmpty(paramRoot)) {
                throw new ConfigurationException("参数配置文件:" + paramPropertiesResource + "中没有配置param.root");
            }
        }

        PARAM_ROOT = "classpath:/vars/" + paramRoot;

    }

    public static Resource getConfigFile(String fileName) {
        ResourceLoader loader = new DefaultResourceLoader();

        return loader.getResource(PARAM_ROOT + "/files/" + fileName);
    }

    public static List<String> getMessageFilePathList() {
        ResourcePatternResolver loader = new PathMatchingResourcePatternResolver();
        Resource[] resources = null;
        try {
            resources = loader.getResources(PARAM_ROOT + "/i18n/" + "*.properties");
        } catch (IOException e) {
            throw new ConfigurationException("获取message文件列表失败", e);
        }

        List<String> filePathList = new ArrayList<String>(resources.length);
        for (Resource messageFile : resources) {
            String uri = null;
            try {
                uri = messageFile.getURI().toString();
            } catch (IOException e) {
                throw new ConfigurationException("获取message文件URI失败", e);
            }

            filePathList.add(trimFileExtension(uri));
        }

        return filePathList;
    }

    private static String trimFileExtension(String filePath) {
        return filePath.substring(0, filePath.lastIndexOf("."));
    }


    /**
     * 根据对应路径拿到指定的所有properties 文件
     *
     * @return 解析成key value 的Properties 对象
     * @throws IOException
     */
    public static Properties getConfigProperties() throws IOException {
        ResourcePatternResolver loader = new PathMatchingResourcePatternResolver();
        Resource[] resources = loader.getResources(PARAM_ROOT + "/*.properties");

        Properties props = new Properties();
        loadProperties(resources, props);

        return props;
    }

    /**
     *
     * 将
     *
     * @param resources     properties 文件资源数组
     * @param props         Properties 对象
     * @throws IOException
     *
     */
    private static void loadProperties(Resource[] resources, Properties props) throws IOException {
        for (Resource resource : resources) {
            logger.info("Loading properties file from {}", resource);
            try {
                PropertiesLoaderUtils.fillProperties(props, resource);
            } catch (IOException e) {
                logger.warn("Could not load properties from {}: " + e.getMessage(), resource);
                throw e;
            }
        }
    }
}
