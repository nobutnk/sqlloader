/**
 * 
 */
package com.blogspot.tanakanbb.sqlloader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;

import com.blogspot.tanakanbb.sqlloader.exception.SqlLoaderException;
import com.blogspot.tanakanbb.sqlloader.handler.DataHandler;
import com.blogspot.tanakanbb.sqlloader.handler.HeaderHandler;
import com.blogspot.tanakanbb.sqlloader.util.ClassLoaderUtil;

/**
 * @author nobutnk
 *
 */
public class SqlLoader {
    /**
     * プロパティファイル
     */
    private Properties systemSettings = new Properties();

    /**
     * ヘッダ出力有無
     */
    private boolean outputHeader = true;

    /**
     * 区切り文字
     */
    private String separator = ",";

    /**
     * 囲み文字
     */
    private String quote = "";

    /**
     * ヘッダ出力Handler
     */
    private HeaderHandler headerHandler = null;

    /**
     * データ出力Handler
     */
    private DataHandler dataHandler = null;

    public SqlLoader(String systemSettingPath) {
        // 初期設定読み込み
        try {
            systemSettings.load(new FileInputStream(systemSettingPath));
        } catch (FileNotFoundException e) {
            // TODO エラーコード、メッセージ
            throw new SqlLoaderException(e);
        } catch (IOException e) {
            // TODO エラーコード、メッセージ
            throw new SqlLoaderException(e);
        }
        outputHeader = "true".equalsIgnoreCase(systemSettings.getProperty(
                "sqlloader.outputHeader", "true"));
        separator = systemSettings.getProperty("sqlloader.separator", ",");
        quote = systemSettings.getProperty("sqlloader.quote", "");
        headerHandler = ClassLoaderUtil.loadHandler(systemSettings
                .getProperty("sqlloader.headerHandler"));
        dataHandler = ClassLoaderUtil.loadHandler(systemSettings
                .getProperty("sqlloader.dataHandler"));

    }

    /**
     * メイン処理 <br>
     * <ul>
     * <li>ヘッダ出力有無</li>
     * <li>区切り文字</li>
     * <li>囲み文字</li>
     * <li></li>
     * <li></li>
     * <li></li>
     * <li></li>
     * </ul>
     * 
     * @param properties SQLが書いてあるプロパティファイル
     * @param connection DBコネクション
     */
    public void execute(Properties properties, Connection connection) {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        for (String name : properties.stringPropertyNames()) {
            String sql = properties.getProperty(name);
            try {
                pstmt = connection.prepareStatement(sql);
                rs = pstmt.executeQuery();
                ResultSetMetaData rsMetaData = rs.getMetaData();

                // output header.
                if (outputHeader) {
                    headerHandler.output(rsMetaData, separator);
                }

                // output data
                dataHandler.output(rsMetaData, rs, separator, quote);

            } catch (SQLException e) {
                throw new SqlLoaderException(e);
            } finally {
                try {
                    if (pstmt != null && !pstmt.isClosed()) {
                        pstmt.close();
                    }
                    if (rs != null && !rs.isClosed()) {
                        rs.close();
                    }
                    if (connection != null && !connection.isClosed()) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    
                }
            }

        }

    }
}
