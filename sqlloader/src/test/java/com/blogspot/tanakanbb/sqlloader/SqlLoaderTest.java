/**
 * 
 */
package com.blogspot.tanakanbb.sqlloader;

import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.Test;

import com.blogspot.tanakanbb.sqlloader.connection.ConnectionHandler;

/**
 * @author nobutnk
 *
 */
public class SqlLoaderTest {

    /**
     * Test method for {@link com.blogspot.tanakanbb.sqlloader.SqlLoader#SqlLoader(java.lang.String)}.
     */
    @Test
    public void testSqlLoader() {
        String systemSettingPath = "src/test/resources/systemSettings.properties";
        SqlLoader target = new SqlLoader(systemSettingPath);
        assertNotNull(target);
    }

    /**
     * Test method for {@link com.blogspot.tanakanbb.sqlloader.SqlLoader#execute(java.util.Properties, java.sql.Connection)}.
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    @Test
    public void testExecute() throws FileNotFoundException, IOException {
        String sqlfilePath = "src/test/resources/testsql.properties";
        Properties properties = new Properties();
        properties.load(new FileInputStream(sqlfilePath));
        
        String systemSettingPath = "src/test/resources/systemSettings.properties";
        SqlLoader target = new SqlLoader(systemSettingPath);
        ConnectionHandler connectionHandler = ConnectionHandler.getInstance();
        target.execute(properties, connectionHandler.getConnection());
        
    }

}
