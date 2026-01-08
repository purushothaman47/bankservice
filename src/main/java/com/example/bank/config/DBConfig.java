
package com.example.bank.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class DBConfig {
    private static final Logger log =
            LoggerFactory.getLogger(DBConfig.class);
    private static final HikariDataSource ds;
    static {

        try {
            log.info("Initializing database connectionPool");
            Properties prop = new Properties();

            prop.load(DBConfig.class.getClassLoader().getResourceAsStream("db.properties"));
            HikariConfig c = new HikariConfig();

            c.setJdbcUrl(prop.getProperty("db.url"));

            c.setUsername(prop.getProperty("db.username"));

            c.setPassword(prop.getProperty("db.password"));
            c.setDriverClassName("com.mysql.cj.jdbc.Driver");
            c.setMaximumPoolSize(Integer.parseInt(prop.getProperty("db.pool.size")));
            ds = new HikariDataSource(c);

            log.info("Database connection pool initialized successfully");

        } catch (Exception e) {
            log.error("Database initialization failed");
            throw new RuntimeException(e);
        }
    }
    public static HikariDataSource get()
    {
        return ds;
    }
}
