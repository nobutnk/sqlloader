/**
 * 
 */
package com.blogspot.tanakanbb.sqlloader.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.blogspot.tanakanbb.sqlloader.exception.SqlLoaderException;
import com.blogspot.tanakanbb.sqlloader.handler.DataHandler;
import com.blogspot.tanakanbb.sqlloader.handler.HeaderHandler;

/**
 * @author nobutnk
 *
 */
public class ClassLoaderUtilTest {

    /**
     * Test method for
     * {@link com.blogspot.tanakanbb.sqlloader.util.ClassLoaderUtil#loadHandler(java.lang.String)}
     * .
     */
    @Test
    public void testLoadHandler_HeaderHandler() {
        String fqcn = "com.blogspot.tanakanbb.sqlloader.handler.DefaultHeaderHandlerImpl";
        HeaderHandler actual = ClassLoaderUtil.loadHandler(fqcn);
        
        assertNotNull(actual);
        assertTrue(actual instanceof HeaderHandler);
    }
    
    /**
     * Test method for
     * {@link com.blogspot.tanakanbb.sqlloader.util.ClassLoaderUtil#loadHandler(java.lang.String)}
     * .
     */
    @Test
    public void testLoadHandler_DataHandler() {
        String fqcn = "com.blogspot.tanakanbb.sqlloader.handler.DefaultDataHandlerImpl";
        DataHandler actual = ClassLoaderUtil.loadHandler(fqcn);
        
        assertNotNull(actual);
        assertTrue(actual instanceof DataHandler);
    }
    
    /**
     * Test method for
     * {@link com.blogspot.tanakanbb.sqlloader.util.ClassLoaderUtil#loadHandler(java.lang.String)}
     * .
     */
    @Test
    public void testLoadHandler_HeaderHandlerError() {
        String fqcn = "com.blogspot.tanakanbb.sqlloader.handler.DefaultHeaderHandlerImpl";
        
        try {
            DataHandler actual = ClassLoaderUtil.loadHandler(fqcn);
            fail(actual + "ClassCastExceptionが出るはず");
        } catch (ClassCastException e) {
        }
    }

}
