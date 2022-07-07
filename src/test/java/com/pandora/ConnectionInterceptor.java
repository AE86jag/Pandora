package com.pandora;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Objects;
import java.util.Properties;

@Component
@ConditionalOnProperty(name = "isRollback", havingValue = "true")
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class ConnectionInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Connection connection = CurrentConnectionHolder.getConnection();
        if (Objects.nonNull(connection)) {

            Method method = invocation.getMethod();
            Object target = invocation.getTarget();
            return method.invoke(target, connection, 50000);
        } else {
            return invocation.proceed();
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
