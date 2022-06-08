package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils extends LoggerUtils{

    public static void wait(int second){
        try {
            Thread.sleep(1000L * second);
        } catch (InterruptedException e) {
            log.warn("InterruptedException in Thread.sleep()! Message: " + e.getMessage());
        }
    }

    public static Date getCurrentDateTime(){
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    public static Date getDateTime(long milliSeconds){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return calendar.getTime();
    }

    public static String getFormattedDateTime(Date date, String pattern){
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public static String getFormattedCurrentDateTime(String pattern){
        Date date = getCurrentDateTime();
        return getFormattedDateTime(date, pattern);
    }

    public static String getDateTimeStamp(){
        return getFormattedCurrentDateTime("yyMMddHHmmss");
    }
}
