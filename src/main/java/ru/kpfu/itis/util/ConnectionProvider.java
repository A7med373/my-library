package ru.kpfu.itis.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionProvider {
    private static ConnectionProvider _instance;

    Properties property = new Properties();

    {
        try {
            ClassLoader classloader = getClass().getClassLoader();
            InputStream is = classloader.getResourceAsStream("application.properties");
            property.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ConnectionProvider getInstance() throws DbException {
        if (_instance == null) {
            _instance = new ConnectionProvider();
        }
        return _instance;
    }

    final String DRIVER = property.getProperty("db.driver.classname");
    final String HOST = property.getProperty("db.url");;
    final String USER = property.getProperty("db.username");;
    final String PASS = property.getProperty("db.password");;

    private Connection connection;

    private ConnectionProvider() throws DbException {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(HOST, USER, PASS);
        } catch (SQLException | ClassNotFoundException e){
            throw new DbException("Can't connect to DB", e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
