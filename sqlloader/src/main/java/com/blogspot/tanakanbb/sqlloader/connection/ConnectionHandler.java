/**
 * 
 */
package com.blogspot.tanakanbb.sqlloader.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.blogspot.tanakanbb.sqlloader.exception.SqlLoaderException;

/**
 * コネクション管理
 * @author nobutnk
 *
 */
public final class ConnectionHandler {
    /**
     * for Singleton
     * @author nobutnk
     *
     */
    private static final class ConnectionHandlerHolder {
        private static final ConnectionHandler instance = new ConnectionHandler();
    }

    private final String driverName;
    private final String url;
    private final String userName;
    private final String userPassword;
    
    private Connection connection;
    
    /**
     * コンストラクタ。
     * コンストラクタ内でConnectionを取得します。
     * 取得できなかった場合は、例外をスローします。
     * 
     * @throws SqlLoaderException コネクション取得に失敗した場合
     */
    private ConnectionHandler() {
        ResourceBundle resource = ResourceBundle.getBundle("sqlloader");
        driverName = resource.getString("jdbc.drive");
        url = resource.getString("jdbc.url");
        userName = resource.getString("jdbc.userName");
        userPassword = resource.getString("jdbc.userPassword");

        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, userName, userPassword);
            connection.setAutoCommit(false);
        } catch (ClassNotFoundException e) {
            throw new SqlLoaderException(e);
        } catch (SQLException e) {
            throw new SqlLoaderException(e);
        }
    }
    
    public static ConnectionHandler getInstance() {
        return ConnectionHandlerHolder.instance;
    }
    
    public Connection getConnection() {
        return connection;
    }
    
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new SqlLoaderException(e);
            }
        }
    }
    
}
