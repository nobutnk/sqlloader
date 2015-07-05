/**
 * 
 */
package com.blogspot.tanakanbb.sqlloader.handler;

import java.sql.ResultSetMetaData;

import com.blogspot.tanakanbb.sqlloader.exception.SqlLoaderException;

/**
 * @author nobutnk
 *
 */
public interface HeaderHandler {
    /**
     * メタデータからヘッダを出力します
     * @param rsMetaData メタデータ
     * @param separator 区切り文字
     * @throws SqlLoaderException ヘッダ出力失敗時
     */
    public void output(ResultSetMetaData rsMetaData, String separator) throws SqlLoaderException;

}
