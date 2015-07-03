/**
 * 
 */
package com.blogspot.tanakanbb.process;

/**
 * @author nobutnk
 *
 */
public abstract class AbstractShutdownHook implements ShutdownHook {

    /* (non-Javadoc)
     * @see com.blogspot.tanakanbb.sqlloader.ShutdownHook#execute()
     */
    public void execute(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                release();
            }
        });
        doExecute(args);

    }

    /**
     * リソースの解放処理
     */
    public abstract void release();
    
    /**
     * メイン処理
     */
    public abstract void doExecute(String[] args);
}
