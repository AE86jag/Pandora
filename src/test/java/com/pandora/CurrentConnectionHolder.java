package com.pandora;

import org.springframework.jdbc.datasource.ConnectionHolder;

import java.sql.Connection;

/**
 * 保存一个全局的数据库链接
 */
public class CurrentConnectionHolder {

    private static Connection connection = null;

    public static Connection getConnection() {
        return connection;
    }

    public static void setConnection(Connection connection) {
        CurrentConnectionHolder.connection = connection;
    }

    public static void clear() {
        CurrentConnectionHolder.connection = null;
    }

}
