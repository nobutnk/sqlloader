/**
 * 
 */
package com.blogspot.tanakanbb.sqlloader.exception;

/**
 * @author nobutnk
 *
 */
public class SqlLoaderException extends RuntimeException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4937052861032410516L;
    
    public SqlLoaderException(Throwable t) {
        super(t);
    }

    public SqlLoaderException(String message) {
        super(message);
    }
    
    public SqlLoaderException(String message, Throwable t) {
        super(message, t);
    }
}
