package cn.lhzs.data.interceptor;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

/**
 * Created by ZHX on 2018/12/21.
 */
@Intercepts(value = {
        @Signature(type = Executor.class,
                method = "update",
                args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class,
                        CacheKey.class, BoundSql.class}),
        @Signature(type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class SqlInterceptor implements Interceptor {

    private static final Logger logger = LoggerFactory.getLogger(SqlInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();
        Object result = null;
        if (target instanceof Executor) {
            long start = System.currentTimeMillis();
            Method method = invocation.getMethod();
            result = invocation.proceed();
            long end = System.currentTimeMillis();
            final Object[] args = invocation.getArgs();

            MappedStatement ms = (MappedStatement) args[0];
            String commandName = ms.getSqlCommandType().name();
            String name = method.getName();
            if (commandName.startsWith("INSERT")) {
                name += "=新增";
            } else if (commandName.startsWith("UPDATE")) {
                name += "=修改";
            } else if (commandName.startsWith("DELETE")) {
                name += "=删除";
            } else if (commandName.startsWith("SELECT")) {
                name += "=查询";
            }
            String message = "[SqlInterceptor] execute [" + name + "] cost [" + (end - start) + "] ms";
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(message);
            stringBuffer.append("\n");

            Object parameterObject = args[1];
            BoundSql boundSql = ms.getBoundSql(parameterObject);
            String sql = boundSql.getSql();
            List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
            String parameterObjects = JSON.toJSONString(boundSql.getParameterObject());


            String id = ms.getId();
            stringBuffer.append("id=" + id);
            stringBuffer.append("\r\n");

            stringBuffer.append("sql=" + sql);
            stringBuffer.append("\n");

            stringBuffer.append("parameterMappings=" + parameterMappings);
            stringBuffer.append("\n");

            stringBuffer.append("parameterObjects=" + parameterObjects);
            stringBuffer.append("\n");
            // stringBuffer.append("result="+result);
            if (result != null) {
                if (result instanceof List) {
                    stringBuffer.append("result=" + ((List) result).size());
                } else if (result instanceof Collection) {
                    stringBuffer.append("result=" + ((Collection) result).size());
                } else {
                    stringBuffer.append("result=" + 1);
                }
            } else {
                stringBuffer.append("result=NULL");
            }
            stringBuffer.append("\n");
            logger.error(stringBuffer.toString());
            //数组可能为空
            // ParameterMapping mapping = boundSql.getParameterMappings().get(0);
            // Configuration configuration = ms.getConfiguration();
            //  DynamicContext context = new DynamicContext(configuration, parameterObject);
            //   String originSql = context.getSql();
            //  System.out.println("@@@@originSql:"+originSql);
        }
        return result;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
