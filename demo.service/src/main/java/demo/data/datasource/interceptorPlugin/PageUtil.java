package demo.data.datasource.interceptorPlugin;


import demo.data.datasource.interceptorPlugin.page.PageSize;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by zhengjianhui on 16/10/5.
 *
 * 辅助mybatis 分页类
 *
 *
 */
public class PageUtil {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PageUtil.class);

    private static final Pattern ORDER_BY = Pattern.compile(".*order\\s+by\\s+.*", Pattern.CASE_INSENSITIVE);

    /**
     * 根据指定对象的 class 从参数中获取该对象
     * @param params
     * @param type
     * @param <T>
     * @return
     */
    public static <T>T findName(Object params, Class<T> type) {

        if (params == null)
            return null;

        // 第一个对象的Class 等于 第二个对象的Class 则直接返回该对象
        // isAssignableFrom java.lang 包判断两个对象是否属于一个Class
        if(type.isAssignableFrom(params.getClass())) {
            return (T) params;
        }

        // params 实现了 mybatis 的 ParamMap 说明有多个参数
        if(params instanceof MapperMethod.ParamMap) {

            MapperMethod.ParamMap<Object> paramMap = (MapperMethod.ParamMap<Object>) params;

            // 循环map 的entry 对象 比较所有参数 是否有实现 分页对象的参数存在
            for (Map.Entry<String, Object> param : paramMap.entrySet()) {

                Object paramValue = param.getValue();

                if(paramValue != null && type.isAssignableFrom(paramValue.getClass())) {
                    return (T) paramValue;
                }
            }

        }

        return null;
    }


    /**
     * 根据sql 获取查询记录数
     * @param sql
     * @param mappedStatement
     * @param boundSql
     * @return
     */
    public static Integer getCountBySql(String sql, MappedStatement mappedStatement, BoundSql boundSql) {

        // 获取改造后的sql
        String countSql = transformSql(sql);

        Connection connection = null;
        PreparedStatement countStmt = null;
        ResultSet rs = null;
        // 总记录数
        int totalCount = 0;

        try {
            // 从MappedStatement 对象中获取jdbc 连接
            connection = mappedStatement.getConfiguration().getEnvironment().getDataSource().getConnection();

            // 准备发送语句
            countStmt = connection.prepareStatement(countSql);
            BoundSql countBoundSql = createNewBoundSql(mappedStatement, boundSql, countSql);
            setParameters(countStmt, mappedStatement, countBoundSql, boundSql.getParameterObject());

            rs = countStmt.executeQuery();

            if (rs.next()) {
                totalCount = rs.getInt(1);
            }


        } catch (SQLException e) {

            logger.error("查询总数异常");

        } finally {

            try {
                countStmt.close();
                rs.close();
                connection.close();
            } catch (SQLException e) {
                logger.error("关闭异常");
            }

        }

        return totalCount;
    }


    /**
     * 改造sql 为通过语句查询 结果条数
     * 通过sql 语句来统计记录数
     * select count(*) from (sql 具体的sql 例如 select * from xxxx) tmp_count
     * @param sql
     * @return
     */
    private static String transformSql(String sql) {
        StringBuilder sqlBuilder = new StringBuilder();

        return sqlBuilder.append("select count(*) from (").
                append(sql).append(") tmp_count").toString();
    }


    /**
     * 对SQL参数(?)设值
     * @param ps           jdbc发送对象
     * @param mappedStatement  mybatis 与数据库交互对象
     * @param boundSql         封装sql 和 sql 相关
     * @param parameterObject  BoundSql 封装的查询参数
     * @throws SQLException
     */
    private static void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql,
                                      Object parameterObject) throws SQLException {
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
        parameterHandler.setParameters(ps);
    }

    /**
     * 动态拼接sql 达到分页目的
     * @param sql
     * @param page
     * @return
     */
    public static String appendSql(String sql, Pageable page) {
        StringBuilder sb = new StringBuilder(sql);

        PageSize pageSize = new PageSize(page);

        sb.append(" ").append("limit").append(" ").append(pageSize.getOffset()).append(",").append(" " + pageSize.getLimit());

        if(page.getSort() !=null) {
            sb.append(" ").append("order by").append(" ").append("index").append(" ").append(page.getSort());
        }

        return sb.toString();
    }


    /**
     * 获取排序后的sql
     */
    public static String applySorting(String sql, Sort sort) {
        if (null == sort || !sort.iterator().hasNext()) {
            return sql;
        }

        StringBuilder builder = new StringBuilder(sql);

        if (!ORDER_BY.matcher(sql).matches()) {
            builder.append(" order by ");
        } else {
            builder.append(", ");
        }

        for (Sort.Order order : sort) {
            builder.append(getOrderClause(order)).append(", ");
        }

        builder.delete(builder.length() - 2, builder.length());

        return builder.toString();
    }

    private static String getOrderClause(Sort.Order order) {
        String property = order.getProperty();
        String wrapped = order.isIgnoreCase() ? String.format("lower(%s)", property) : property;

        return String.format("%s %s", wrapped, toSqlDirection(order));
    }

    private static String toSqlDirection(Sort.Order order) {
        return order.getDirection().name().toLowerCase(Locale.US);
    }


    /**
     * createNewBoundSql            返回新的 BoundSql 对象
     * createNewMappedStatement     返回新的 MappedStatement 对象
     *
     * @param ms
     * @param boundSql
     * @param sql
     * @return
     */
    public static MappedStatement createMappedStatement(MappedStatement ms, BoundSql boundSql, String sql) {
        BoundSql newBoundSql = createNewBoundSql(ms, boundSql, sql);

        return createNewMappedStatement(ms, new BoundSqlSqlSource(newBoundSql));
    }

    /**
     * 实现SqlSource 类 作用为将 BoundSql 放入其中（参考设计模式）
     *
     *  MappedStatement.Builder(ms.getConfiguration(),ms.getId(),newSqlSource,ms.getSqlCommandType());
     *  MappedStatement.Builder 的构造中需要传递 SqlSource
     */
    private static class BoundSqlSqlSource implements SqlSource {
        BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        @Override
        public BoundSql getBoundSql(Object o) {
            return boundSql;
        }
    }

    /**
     * 构建新的BoundSql
     *
     * public BoundSql(Configuration configuration, String sql, List<ParameterMapping> parameterMappings, Object parameterObject)
     * BoundSql 的构造函数
     *
     * has Additional Parameter 额外参数赋值
     *
     * ParameterMapping  保存从sql 中解析出的参数（没有实际值）
     * 例如 userId = #{userId}  翻译为sql时为 userId = ?
     * 那么ParameterMapping 会保存 userId
     *
     */
    public static BoundSql createNewBoundSql(MappedStatement ms, BoundSql boundSql, String sql) {
        BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql, boundSql.getParameterMappings(), boundSql.getParameterObject());

        for (ParameterMapping mapping : boundSql.getParameterMappings()) {

            String prop = mapping.getProperty();
            if (boundSql.hasAdditionalParameter(prop)) {
                newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
            }
        }

        return newBoundSql;
    }


    /**
     *
     * MappedStatement.Builder    Builder 为内部类 初始化该类
     * builder.build()            build() 方法返回初始化的MappedStatement 对象用于与数据库交互
     *
     * SqlSource 为Sql 源接口 保存xml 中映射的内容
     *
     *
     * @param ms
     * @param newSqlSource
     * @return
     */
    //see: MapperBuilderAssistant
    private static MappedStatement createNewMappedStatement(MappedStatement ms,SqlSource newSqlSource) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(),ms.getId(),newSqlSource,ms.getSqlCommandType());

        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if(ms.getKeyProperties() != null && ms.getKeyProperties().length !=0){
            StringBuffer keyProperties = new StringBuffer();
            for(String keyProperty : ms.getKeyProperties()){
                keyProperties.append(keyProperty).append(",");
            }
            keyProperties.delete(keyProperties.length()-1, keyProperties.length());
            builder.keyProperty(keyProperties.toString());
        }

        //setStatementTimeout()
        builder.timeout(ms.getTimeout());

        //setStatementResultMap()
        builder.parameterMap(ms.getParameterMap());

        //setStatementResultMap()
        builder.resultMaps(ms.getResultMaps());
        builder.resultSetType(ms.getResultSetType());

        //setStatementCache()
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());

        return builder.build();
    }


}
