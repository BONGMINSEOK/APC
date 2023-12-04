package com.kdn.apc.repository;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.util.Properties;

public class AppSqlSessionFactory {
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession session;
    public SqlSession getSqlSession(Properties prop) {

        // Create a PooledDataSource instance
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setDriver(prop.getProperty("driver"));
        dataSource.setUrl(prop.getProperty("url"));
        dataSource.setUsername(prop.getProperty("user"));
        dataSource.setPassword(prop.getProperty("pwd"));

        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(DataMapper.class);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        session = sqlSessionFactory.openSession();
        return session;
    }
}