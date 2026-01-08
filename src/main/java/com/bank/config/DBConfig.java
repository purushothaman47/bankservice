package com.bank.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

public class DBConfig {

    private static final HikariDataSource dataSource;

    static {
        try {
            Properties props = new Properties();

            InputStream is = DBConfig.class
                    .getClassLoader()
                    .getResourceAsStream("db.properties");

            if (is == null) {
                throw new RuntimeException("db.properties file NOT FOUND in classpath");
            }

            props.load(is);

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(props.getProperty("db.url"));
            config.setUsername(props.getProperty("db.username"));
            config.setPassword(props.getProperty("db.password"));
            config.setDriverClassName(props.getProperty("db.driver"));

            dataSource = new HikariDataSource(config);

        } catch (Exception e) {
            throw new RuntimeException("DB Initialization Failed", e);
        }
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}
