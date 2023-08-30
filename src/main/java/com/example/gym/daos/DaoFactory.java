package com.example.gym.daos;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoFactory {
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private MysqlDataSource dataSource;

    public Connection getConnection() {
        if(dataSource == null){
            initDataSource();
        }
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void initDataSource(){
        dataSource = new MysqlDataSource();
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);
    }
}
