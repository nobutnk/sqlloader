/**
 * 
 */
package com.blogspot.tanakanbb.sqlloader.util;

import com.blogspot.tanakanbb.sqlloader.exception.SqlLoaderException;

/**
 * @author nobutnk
 *
 */
public class ClassLoaderUtil {
    /**
     * デフォルトコンストラクタ。使用しない
     */
    private ClassLoaderUtil() {
        // 使用しない
    }

    /**
     * 各種ハンドラ生成のためのメソッド
     * @param fqcn
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T loadHandler(String fqcn) {
        try {
            Class<?> clazz = Class.forName(fqcn);
            Object obj = clazz.newInstance();
            return (T) obj;
        } catch (ClassNotFoundException e) {
            throw new SqlLoaderException(e);
        } catch (InstantiationException e) {
            throw new SqlLoaderException(e);
        } catch (IllegalAccessException e) {
            throw new SqlLoaderException(e);
        }
    }
}
