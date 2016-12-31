package demo.dao.mybatis.interceptorPlugin;

import demo.dao.mybatis.interceptorPlugin.page.PageRequest;
import demo.dao.mybatis.interceptorPlugin.page.PageResult;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


/**
 * Created by zhengjianhui on 16/10/4.
 * 核心思路
 *
 * mybatis 的动态代理中 Invocation 中的三个参数
 *
 *     private Object target; private Method method;   private Object[] args;
 *
 *  private Object[] args; 存放了所有需要的参数
 *
 *  通过 private Object[] args; 获取 mybatis 的三个参数
 *
 *  MappedStatement         对象 负责使用jdbc 对象与数据库交互
 *  MapperMethod$ParamMap   对象 存放了入惨参数的 map 集合
 *  RowBounds               mybatis 自带的物理分页  （分页的关键）
 *
 *
 *  MapperMethod$ParamMap   获取自定的分页对象 如果对象存在则执行分页
 *
 *  MappedStatement         获取MappedStatement 对象
 *  BoundSql                   从MappedStatement 获取BoundSql对象并从其中获取Sql 利用分页对象 对Sql进行改造
 *
 *  将改造后的sql 放入 BoundSql 对象中 并将 BoundSql 放入SqlSource 中
 *
 *  初始化新的MappedStatement 对象 以SqlSource 入参
 *
 *  替换原来的 MappedStatement 对象
 *
 *  初始化RowBounds 对象 并让分页参数 offset = 0 limit = Integer.MAX
 *
 *  执行jdk 的动态代理 invocation.proceed();
 *
 *
 *  MapperStatement，直接翻译就是映射声明，是对映射信息的封装对象，用来存储记录要映射的sql语句的id、sql语句、传参等等
 *  这个类有个比较重要的方法就是getBoundSql(Object parameterObject)，也就是获取绑定的sql，如果要开发一个分页的插件，要利用反射机制，对这个BoundSql进行修改。
 *
 */
@Intercepts({
        @Signature(
                type = Executor.class,                       // 拦截的类（在什么之前执行拦截器）
                method = "query",                            // 拦截的方法
                                                             // 拦截的参数  进入拦截的对象里查看拦截的方法有哪些参数设置拦截参数
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
                )
})
public class IntercrptorPage implements Interceptor {

    private static int MAPPED_STATEMENT_INDEX = 0;
    private static int PARAMETER_INDEX = 1;
    private static int ROWBOUNDS_INDEX = 2;


    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        // 获取Invocation 的参数
        final Object[] queryArgs = invocation.getArgs();
        final Object parameter = queryArgs[PARAMETER_INDEX];

        // 获取page对象 利用反射
        PageRequest page = PageUtil.findName(parameter, PageRequest.class);
        // 分页对象为空直接执行下一个代理
        if(page == null)
            return invocation.proceed();

        // 获取statement 对象
        final MappedStatement ms = (MappedStatement)queryArgs[MAPPED_STATEMENT_INDEX];
        // 获取 保存sql的对象 详情 查看MappedStatement类getBoundSql 方法
        final BoundSql boundSql = ms.getBoundSql(parameter);

        String sql = boundSql.getSql();

        // 在 sql 被改造前 获取 总分页数
        Integer total = PageUtil.getCountBySql(sql, ms, boundSql);

        sql = PageUtil.appendSql(sql, page);


        // RowBounds.NO_ROW_OFFSET,RowBounds.NO_ROW_LIMIT  利用mybatis的RowBounds 分页每次必须充值未初始值
        queryArgs[ROWBOUNDS_INDEX] = new RowBounds(RowBounds.NO_ROW_OFFSET,RowBounds.NO_ROW_LIMIT);
        // 替换原有的 MappedStatement 对象
        queryArgs[MAPPED_STATEMENT_INDEX] = PageUtil.createMappedStatement(ms, boundSql, sql);


        Object obj = invocation.proceed();

        PageResult pageRequest = new PageResult((List<Object>) obj, page.getPageSize(), total);

        // MyBatis 需要返回一个List对象，这里只是满足MyBatis而作的临时包装
        // 实际封装的实体 由 xml 定义这里的泛型指定为未知类型，通过mybatis 传入实际实体来转换
        List<PageResult<?>> reList = new ArrayList(1);
        reList.add(pageRequest);

        return reList;
    }


    @Override
    public Object plugin(Object target) {
        // 通过Plugin的wrap(...)方法来实现代理类的生成操作
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
