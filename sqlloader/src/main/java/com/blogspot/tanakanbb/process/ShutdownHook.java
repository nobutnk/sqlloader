/**
 * 
 */
package com.blogspot.tanakanbb.process;

/**
 * プロセス停止時に呼ばれる
 * @author nobutnk
 *
 */
public interface ShutdownHook {
    /**
     * メイン処理
     * @param args 起動パラメータ
     */
    void execute(String[] args);

}
