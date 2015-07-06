/**
 * 
 */
package com.blogspot.tanakanbb.sqlloader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.blogspot.tanakanbb.process.AbstractShutdownHook;
import com.blogspot.tanakanbb.process.ShutdownHook;
import com.blogspot.tanakanbb.sqlloader.connection.ConnectionHandler;

/**
 * @author nobutnk
 *
 */
public class SqlLoaderMain {

    /**
     * @param args
     *  SQLファイルパス
     */
    public static void main(String[] args) {
        
        ShutdownHook target = new AbstractShutdownHook() {
            private ConnectionHandler connectionHandler = null;

            /**
             * DBコネクションを解放して終了します。
             * @see com.blogspot.tanakanbb.process.AbstractShutdownHook#release()
             */
            @Override
            public void release() {
                if (connectionHandler != null) {
                    connectionHandler.closeConnection();
                }
            }

            /**
             * SQL実行して、その結果を標準出力に出力する。
             * @see com.blogspot.tanakanbb.process.AbstractShutdownHook#doExecute()
             */
            @Override
            public void doExecute(String[] args) {
                connectionHandler = ConnectionHandler.getInstance();
                
                Properties properties = new Properties();
                for (String filePath : args) {
                    try {
                        properties.load(new FileInputStream(filePath));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        System.exit(1);
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.exit(1);
                    } finally {
                        connectionHandler.closeConnection();
                    }
                }
            }
        };
        
        target.execute(args);
    }

}
