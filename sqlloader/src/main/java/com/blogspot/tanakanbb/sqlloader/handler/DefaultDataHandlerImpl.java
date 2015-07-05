/**
 * 
 */
package com.blogspot.tanakanbb.sqlloader.handler;

import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

import com.blogspot.tanakanbb.sqlloader.exception.SqlLoaderException;

/**
 * @author nobutnk
 *
 */
public class DefaultDataHandlerImpl implements DataHandler {

    /**
     * @throws SqlLoaderException
     *             ヘッダ出力失敗時
     */
    public void output(ResultSetMetaData rsMetaData, ResultSet rs,
            String separator, String quote) throws SqlLoaderException {

        int columnCount;
        try {
            columnCount = rsMetaData.getColumnCount();
            // output data.
            while (rs.next()) {
                StringBuilder data = new StringBuilder();
                for (int i = 0; i < columnCount; i++) {
                    int columnType = rsMetaData.getColumnType(i);
                    String columnName = rsMetaData.getColumnName(i);
                    String columnValue = getValue(columnName, columnType, rs);
                    data.append(quoteString(columnValue, quote));
                    if (i < columnCount - 1) {
                        data.append(separator);
                    }
                }
                System.out.println(data.toString());
            }
        } catch (SQLException e) {
            throw new SqlLoaderException(e);
        }

    }

    public String getValue(String columnName, int type, ResultSet rs)
            throws SQLException {
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
            long longValue = rs.getLong(columnName);
            result = Long.toString(longValue);
        case java.sql.Types.NUMERIC:
        case java.sql.Types.DECIMAL:
            BigDecimal decimal = rs.getBigDecimal(columnName);
            if (decimal != null) {
                result = decimal.toPlainString();
            }
            break;
        case java.sql.Types.REAL:
        case java.sql.Types.FLOAT:
            Float floatValue = rs.getFloat(columnName);
            if (floatValue != null) {
                result = floatValue.toString();
            }
            break;
        case java.sql.Types.DOUBLE:
            Double doubleValue = rs.getDouble(columnName);
            if (doubleValue != null) {
                result = doubleValue.toString();
            }
            break;
        case java.sql.Types.DATE:
            Date date = rs.getDate(columnName);
            if (date != null) {
                result = date.toString();
            }
        case java.sql.Types.TIME:
            Time time = rs.getTime(columnName);
            if (time != null) {
                result = time.toString();
            }
            break;
        case java.sql.Types.TIMESTAMP:
        case java.sql.Types.TIMESTAMP_WITH_TIMEZONE:
            Timestamp timestamp = rs.getTimestamp(columnName);
            if (timestamp != null) {
                result = timestamp.toString();
            }
            break;
        case java.sql.Types.BIT:
            boolean booleanValue = rs.getBoolean(columnName);
            result = Boolean.toString(booleanValue);
        }

        return result;
    }
    
    /**
     * 囲み文字で囲んで返却します。nullの場合は囲みません
     * @param value
     * @param quote
     * @return
     */
    public String quoteString(String value, String quote) {
        if (value == null) {
            return "null";
        }
        StringBuilder result = new StringBuilder();
        result.append(quote);
        result.append(value);
        result.append(quote);
        
        return result.toString();
    }

}
