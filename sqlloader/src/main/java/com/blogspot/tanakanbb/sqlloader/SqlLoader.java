/**
 * 
 */
package com.blogspot.tanakanbb.sqlloader;

import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;

import com.blogspot.tanakanbb.sqlloader.exception.SqlLoaderException;

/**
 * @author nobutnk
 *
 */
public class SqlLoader {

    public void execute(Properties properties, Connection connection) {

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        for (String name : properties.stringPropertyNames()) {
            String sql = properties.getProperty(name);
            try {
                pstmt = connection.prepareStatement(sql);
                rs = pstmt.executeQuery();
                ResultSetMetaData  rsMetaData = rs.getMetaData();
                int columnCount = rsMetaData.getColumnCount();
                
                while (rs.next()) {
                    for (int i = 0; i < columnCount; i++) {
                        int columnType = rsMetaData.getColumnType(i);
                        String columnName = rsMetaData.getColumnName(i);
                        String columnValue = getValue(columnName, columnType, rs);
                    }
                }
                
            } catch (SQLException e) {
                throw new SqlLoaderException(e);
            }
            
        }

    }
    
    public String getValue(String columnName, int type, ResultSet rs) throws SQLException {
        String result = null;
        switch (type) {
        case java.sql.Types.CHAR:
        case java.sql.Types.VARCHAR:
            result = rs.getString(columnName);
            break;
        case java.sql.Types.CLOB:
            Clob clob = rs.getClob(columnName);
            if (clob != null) {
                result = clob.getSubString(0, (int) clob.length());
            }
            break;
        case java.sql.Types.INTEGER:
            
        case java.sql.Types.BIGINT:
        case java.sql.Types.NUMERIC:
            BigDecimal decimal = rs.getBigDecimal(columnName);
            if (decimal != null) {
                result = decimal.toPlainString();
            }
            break;
        case java.sql.Types.DATE:
            Date date = rs.getDate(columnName);
            if (date != null) {
                result = date.toString();
            }
        case java.sql.Types.TIME:
        case java.sql.Types.TIMESTAMP:
        case java.sql.Types.TIMESTAMP_WITH_TIMEZONE:
            
        }
        
        return result;
    }
}
