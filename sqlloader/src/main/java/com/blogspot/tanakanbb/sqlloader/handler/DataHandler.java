/**
 * 
 */
package com.blogspot.tanakanbb.sqlloader.handler;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import com.blogspot.tanakanbb.sqlloader.exception.SqlLoaderException;

/**
 * @author nobutnk
 *
 */
public interface DataHandler {

    /**
     * SQL実行結果を出力します。ヘッダ以外
     * 
     * @param rsMetaData
     *            メタデータ
     * @param rs
     *            SQL実行結果
     * @param separator
     *            区切り文字
     * @param quote
     *            囲み文字
     * @throws SqlLoaderException
     *             ヘッダ出力失敗時
     */
    public void output(ResultSetMetaData rsMetaData, ResultSet rs,
            String separator, String quote) throws SqlLoaderException;
}
