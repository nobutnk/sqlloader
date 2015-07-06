/**
 * 
 */
package com.blogspot.tanakanbb.sqlloader.connection;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

/**
 * @author nobutnk
 *
 */
public class ConnectionHandlerTest {

    /**
     * Test method for {@link com.blogspot.tanakanbb.sqlloader.connection.ConnectionHandler#getInstance()}.
     */
    @Test
    public void testGetInstance() {
        ConnectionHandler handler = ConnectionHandler.getInstance();
        assertNotNull(handler);
    }

    /**
     * Test method for {@link com.blogspot.tanakanbb.sqlloader.connection.ConnectionHandler#getConnection()}.
     */
    @Test
    public void testGetConnection() {
        Connection connection = ConnectionHandler.getInstance().getConnection();
        assertNotNull(connection);
    }

    /**
     * Test method for {@link com.blogspot.tanakanbb.sqlloader.connection.ConnectionHandler#closeConnection()}.
     * @throws SQLException 
     */
    @Test
    public void testCloseConnection() throws SQLException {
        Connection connection = ConnectionHandler.getInstance().getConnection();
        ConnectionHandler.getInstance().closeConnection();
        assertTrue(connection.isClosed());
    }

}
