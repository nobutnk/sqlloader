/**
 * 
 */
package com.blogspot.tanakanbb.sqlloader.handler;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.blogspot.tanakanbb.sqlloader.exception.SqlLoaderException;

/**
 * 標準出力に出力するHeaderHandler
 * @author nobutnk
 *
 */
public class DefaultHeaderHandlerImpl implements HeaderHandler {

    /**
     * カラム名をseparator区切りで、標準出力に出力します
     * @param rsMetaData メタデータ
     * @param separator 区切り文字
     * @see HeaderHandler
     * @throws SqlLoaderException ヘッダ出力失敗時
     */
    public void output(ResultSetMetaData rsMetaData, String separator) throws SqlLoaderException {
        StringBuilder header = new StringBuilder();
        int columnCount;
        try {
            columnCount = rsMetaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = rsMetaData.getColumnName(i);
                header.append(columnName);
                if (i < columnCount) {
                    header.append(separator);
                }
            }
            System.out.println(header.toString());
        } catch (SQLException e) {
            throw new SqlLoaderException(e);
        }
        
    }

}
