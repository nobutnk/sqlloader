package com.blogspot.tanakanbb.sqlloader.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.blogspot.tanakanbb.sqlloader.SqlLoaderTest;
import com.blogspot.tanakanbb.sqlloader.connection.ConnectionHandlerTest;

@RunWith(Suite.class)
@SuiteClasses( {
    ClassLoaderUtilTest.class,
    SqlLoaderTest.class,
    ConnectionHandlerTest.class
    
})
public class AllTests {

    private static org.h2.tools.Server server;
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String JDBC_URL = "jdbc:h2:mem:test;MODE=MYSQL;DB_CLOSE_DELAY=-1";
    
    @BeforeClass
    public static void beforeClass() throws Exception {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                shutdownServer();
            }
        }));

        server = org.h2.tools.Server.createTcpServer(
                new String[] { "-baseDir", "db\\h2", "-tcpPort", "9092" }).start();
        
        // create table
        Class.forName(JDBC_DRIVER);
        Connection connection = DriverManager.getConnection(JDBC_URL);
        
        Statement st = connection.createStatement();
        st.execute("create table test ("
                + "id int primary key,"
                + "name varchar(30),"
                + "nickname char(20),"
                + "moddate timestamp,"
                + "regdate timestamp)");
        st.execute("insert into test values (1, 'テスト　則夫', 'ノリ蔵', current_timestamp, current_timestamp)");
        st.close();
    }

    @AfterClass
    public static void afterClass() {
        shutdownServer();
    }
    
    private static void shutdownServer() {
        if (server != null) {
            server.shutdown();
            server = null;
        }
    }

}
