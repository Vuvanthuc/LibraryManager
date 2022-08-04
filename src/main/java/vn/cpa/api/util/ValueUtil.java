package vn.cpa.api.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class ValueUtil {

    private ValueUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String getStringByObject(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public static Date getDateByObject(Object obj) {
        if (obj == null) {
            return null;
        }
        return (Date) obj;
    }

    public static Integer getIntByBigDecimal(BigDecimal bigDecimal){
        if(bigDecimal==null){
            return null;
        }
        return bigDecimal.intValue();
    }


    public static Timestamp getTimestampByDate(Date date){
        return date == null ? null : new Timestamp(date.getTime());
    }


    public static Timestamp getTimestampByObject(Object obj) {
        if (obj == null) {
            return null;
        }
        return (Timestamp) obj;
    }

    public static Long getLongByObject(Object obj) {
        if (obj == null || obj.toString().isEmpty()) {
            return null;
        }
        return Long.valueOf(obj.toString());
    }

    public static Integer getIntegerByObject(Object obj) {
        if (obj == null || obj.toString().isEmpty()) {
            return null;
        }
        return Integer.valueOf(obj.toString());
    }

    public static Boolean getBooleanByObject(Object obj) {
        return (obj != null && "1,true".contains(obj.toString()));
    }

    public static Double getDoubleByObject(Object obj){
        if(obj == null || obj.toString().isEmpty()){
            return null;
        }
        return Double.valueOf(obj.toString());
    }

    public static Float getFloatByObject(Object obj){
        if(obj == null || obj.toString().isEmpty()){
            return null;
        }
        return Float.valueOf(obj.toString());
    }


}
