package demo.data.datasource.typeHandler;

import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * mybatis自定义类型处理器扫描类
 *
 * MetadataReader 的基本类型过滤器
 *
 * 
 */
public class TypeHandlerScanner {
    private static Logger logger = LoggerFactory.getLogger(TypeHandlerScanner.class);

    private static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";

    private final List<TypeFilter> includeFilters = new LinkedList<TypeFilter>();

    private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();

    private MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(this.resourcePatternResolver);

    public TypeHandlerScanner() {
        registerDefaultFilters();
    }

    /**
     * 初始化需要扫描的的类型
     * 扫描指定的类型，如果没有，则不会执行后面的方法，如果有则执行后面的方法
     *
     */
    private void registerDefaultFilters() {
        // newAnnotationTypeFilter(MappedTypes.class)  表示只提取被 MappedType 注解的类
        this.includeFilters.add(new AnnotationTypeFilter(MappedTypes.class));
    }

    /**
     * 第一步
     * @param packagePattern
     * @return
     */
    public TypeHandler<?>[] getTypeHandlers(String packagePattern) {
        List<String> classes = null;
        try {
            classes = getTypeHandlerClasses(packagePattern);
        } catch (IOException e) {
            logger.error("扫描mybatis类型转换器发生错误", e);
            return new TypeHandler<?>[0];
        }

        List<TypeHandler<?>> typeHandlers = getTypeHandlers(classes);

        return typeHandlers.toArray(new TypeHandler<?>[typeHandlers.size()]);
    }

    /**
     * 第二步
     * @param packagePattern
     * @return
     * @throws IOException
     */
    private List<String> getTypeHandlerClasses(String packagePattern) throws IOException {
        String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + ClassUtils.convertClassNameToResourcePath(packagePattern) + "/"
                + DEFAULT_RESOURCE_PATTERN;

        Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);

        List<String> classes = new ArrayList<>(resources.length);
        for (Resource resource : resources) {
            MetadataReader metadataReader = this.metadataReaderFactory.getMetadataReader(resource);
            if (isMappedTypesClass(metadataReader)) {
                classes.add(metadataReader.getClassMetadata().getClassName());
            }
        }

        return classes;
    }

    /**
     * 第三步
     * @param metadataReader
     * @return
     * @throws IOException
     */
    private boolean isMappedTypesClass(MetadataReader metadataReader) throws IOException {
        for (TypeFilter tf : this.includeFilters) {
            if (tf.match(metadataReader, this.metadataReaderFactory)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 第四步
     * @param classes
     * @return
     */
    private List<TypeHandler<?>> getTypeHandlers(List<String> classes) {
        List<TypeHandler<?>> typeHandlers = new ArrayList<>(classes.size());
        for (String typeHandlerClass : classes) {
            TypeHandler<?> handler = getInstance(typeHandlerClass);
            if (handler != null) {
                typeHandlers.add(handler);
            }
        }

        return typeHandlers;
    }

    /**
     * 第五步
     * @param typeHandlerClass
     * @return
     */
    @SuppressWarnings("unchecked")
    public TypeHandler<?> getInstance(String typeHandlerClass) {
        Class<TypeHandler<?>> dialectClass = null;
        try {
            dialectClass = (Class<TypeHandler<?>>) Class.forName(typeHandlerClass);
        } catch (ClassNotFoundException e) {
            logger.error("指定的类型转换器不存在", e);
            return null;
        }

        return BeanUtils.instantiate(dialectClass);
    }



}
