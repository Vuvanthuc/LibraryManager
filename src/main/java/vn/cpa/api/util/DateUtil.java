package vn.cpa.api.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil {
    public static final String INIT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String INIT_DATE_FORMAT_2 ="dd-MM-yyyy HH:mm:ss";
    public static final String FROM_DATE_FORMAT = "dd/MM/yyyy 00:00:00";
    public static final String TO_DATE_FORMAT = "dd/MM/yyyy 23:59:59";
    public static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
    public static final String HHMMSS_DDMMYYYY = "HH:mm:ss dd/MM/yyyy";
    public static final String DATE_FORMAT_UPLOAD = "ddMMyyyyHHmmssSSS";
    public static final String DDMMYYYY = "dd/MM/yyyy";
    public static final String DAY_DD_MONTH_MM_YEAR_YYYY = "'Ngày' dd 'tháng' MM 'năm' yyyy";
    public static final String YYYYMMDD_FOLDER = "yyyyMMdd";
    public static final String MMDDYYYYHHMMSS = "MM-dd-yyyy-HH-mm-ss"; //08-16-2020-23-59-59
    public static final String DATE_FORMAT_MINUTE = "dd/MM/yyyy HH:mm:00";
    public static final String DATE_FORMAT_YEAR = "yyyy-MM-dd";
    public static final SimpleDateFormat cmdateFormat = new SimpleDateFormat(DATE_FORMAT);
    public static final String DATE_FOR_TEST_FORMAT = "HH:mm, dd/MM/yyyy";
    public static final String FROM_DATE_FORMAT_SQL = "yyyy-MM-dd 00:00:00";
    public static final String TO_DATE_FORMAT_SQL = "yyyy-MM-dd 23:59:59";
    public static final String regexTime = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$";
    public static final String regexDate = "(^(((0[1-9]|1[0-9]|2[0-8])[\\/](0[1-9]|1[012]))|((29|30|31)[\\/](0[13578]|1[02]))|((29|30)[\\/](0[4,6,9]|11)))[\\/](19|[2-9][0-9])\\d\\d$)|(^29[\\/]02[\\/](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)";

    public static Date formatToDate(Date toDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(TO_DATE_FORMAT);
        String strDate = dateFormat.format(toDate);
        try {
            return cmdateFormat.parse(strDate);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String formatToPattern(Date date, String pattern) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            return dateFormat.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date formatFromDate(Date toDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(FROM_DATE_FORMAT);
        String strDate = dateFormat.format(toDate);
        try {
            return cmdateFormat.parse(strDate);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date formatDate(Date date, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        String strDate = dateFormat.format(date);
        try {
            return cmdateFormat.parse(strDate);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date formatDatePattern(String date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date parseDatePattern(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String strDate = sdf.format(date);
        try {
            return cmdateFormat.parse(strDate);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Timestamp convertToTimestamp(String dateTime) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date parsedDate = dateFormat.parse(dateTime);
            return new Timestamp(parsedDate.getTime());
        } catch (Exception e) {
            return null;
        }
    }

    public static Timestamp convertToTimestampFormat(String dateTime, String format) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            Date parsedDate = dateFormat.parse(dateTime);
            return new Timestamp(parsedDate.getTime());
        } catch (Exception e) {
            return null;
        }
    }

    public synchronized static String getCurrentDateStr() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_UPLOAD);
        return dateFormat.format(new Date());
    }

    public static String getTodayFolder() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(YYYYMMDD_FOLDER);
        return dateFormat.format(new Date());
    }

    public static int daysBetween(Date fromDate,Date toDate){
        LocalDateTime fDate = convertToLocalDateTimeViaInstant(fromDate);
        LocalDateTime tDate = convertToLocalDateTimeViaInstant(toDate);
        return (int) ChronoUnit.DAYS.between(fDate,tDate);
    }

    public static int weeksBetween(Date fromDate,Date toDate){
        LocalDateTime fDate = convertToLocalDateTimeViaInstant(fromDate);
        LocalDateTime tDate = convertToLocalDateTimeViaInstant(toDate);
        return (int) ChronoUnit.WEEKS.between(fDate,tDate);
    }

    public static int monthsBetween(Date fromDate, Date toDate){
        LocalDateTime fDate = convertToLocalDateTimeViaInstant(fromDate);
        LocalDateTime tDate = convertToLocalDateTimeViaInstant(toDate);
        int j = 1;
        for(int i = 0 ; i < ChronoUnit.MONTHS.between(fDate,tDate); i++){
            if(fDate.getDayOfMonth() > tDate.plusMonths(i).getDayOfMonth()){
                j--;
            } else {
                j++;
            }
        }
        return j;
    }

    public static String setMinute(Date date, int minus){
        SimpleDateFormat dateFormat = new SimpleDateFormat(MMDDYYYYHHMMSS);
        Date temp = new Date();
        temp.setTime(date.getTime() + minus * 60 * 1000);
        return dateFormat.format(temp);
    }

    public static Date plusDay(Date date, int day) {
        return plusHour(date, day * 24);
    }

    public static Date minusDay(Date date, int day) {
        return minusHour(date, day * 24);
    }

    public static Date plusHour(Date date, int hour) {
        return plusMinute(date, hour * 60);
    }

    public static Date minusHour(Date date, int hour) {
        return minusMinute(date, hour * 60);
    }

    public static Date plusMinute(Date date, int minus) {
        return plusSecond(date, minus * 60);
    }

    public static Date minusMinute(Date date, int minus) {
        return minusSecond(date, minus * 60);
    }

    public static Date plusSecond(Date date, long second) {
        try {
            Date temp = new Date();
            temp.setTime(date.getTime() + second * 1000);
            return temp;
        } catch (Exception e) {
            return null;
        }
    }

    public static Date minusSecond(Date date, long second) {
        try {
            Date temp = new Date();
            temp.setTime(date.getTime() - second * 1000);
            return temp;
        } catch (Exception e) {
            return null;
        }
    }

    public static String buildStringPattern(Date time, String pattern) {
        try {
            return new SimpleDateFormat(pattern).format(time);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date buildTimePattern(Date time, String pattern) {
        try {
            return new SimpleDateFormat(DATE_FORMAT).parse(new SimpleDateFormat(pattern).format(time));
        } catch (Exception e) {
            return null;
        }
    }

    public static Duration between(Date fromDate, Date toDate) {
        LocalDateTime fDate = convertToLocalDateTimeViaInstant(fromDate);
        LocalDateTime tDate = convertToLocalDateTimeViaInstant(toDate);
        return Duration.between(fDate, tDate);
    }

    public static int secondBetween(Date fromDate,Date toDate){
        LocalDateTime fDate = convertToLocalDateTimeViaInstant(fromDate);
        LocalDateTime tDate = convertToLocalDateTimeViaInstant(toDate);
        return (int) ChronoUnit.SECONDS.between(fDate,tDate);
    }

    public static Long betweenDate(Date fromDate, Date toDate) {
        LocalDate fDate = toLocalDate(fromDate);
        LocalDate tDate = toLocalDate(toDate);
        Duration diff = Duration.between(fDate.atStartOfDay(), tDate.atStartOfDay());
        return diff.toDays();
    }

    public static LocalDate toLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static long[] countdown(Date fromDate, Date toDate) {
        long diff = (toDate.getTime() - fromDate.getTime());
        long days = TimeUnit.MILLISECONDS.toDays(diff);
        long hours = TimeUnit.MILLISECONDS.toHours(diff) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(diff));
        long minutes = TimeUnit.MILLISECONDS.toMinutes(diff) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(diff));
        long seconds = TimeUnit.MILLISECONDS.toSeconds(diff) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(diff));
        long milliseconds = TimeUnit.MILLISECONDS.toMillis(diff) - TimeUnit.MINUTES.toMillis(TimeUnit.MILLISECONDS.toSeconds(diff));

        return new long[]{Math.max(days, 0), Math.max(hours, 0), Math.max(minutes, 0), Math.max(seconds, 0), Math.max(milliseconds, 0)};
    }

    public static Date convertLocalDateToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDateTime convertDateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static Date convertToDateViaInstant(LocalDateTime dateToConvert) {
        return Date
                .from(dateToConvert.atZone(ZoneId.systemDefault())
                        .toInstant());
    }

    public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static boolean validateTimeRegex(String time){
        return time.matches(regexTime);
    }

    public static boolean validateDateRegex(String date){
        return date.matches(regexDate);
    }

    public static Timestamp plusTimestamp(Timestamp timestamp, Integer minutes) {
        return new Timestamp(timestamp.getTime() + minutes * 1000 * 60);
    }
}
