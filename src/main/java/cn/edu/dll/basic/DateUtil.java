package cn.edu.dll.basic;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static Date parseDate(String timeString, String format) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = dateFormat.parse(timeString);
        return date;
    }

    public static Long parseTimestamp(String timeString, String format) throws ParseException {
        return parseDate(timeString, format).getTime();
    }

    public static String getFormatDate(Date date, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        String result = dateFormat.format(date.getTime());
        return result;
    }


    public static void main(String[] args) throws ParseException {
//        String timeString = "2008-02-02 13:39:08";
        String dateFormat = "YYYY-MM-dd HH:mm:ss";
//        Long result = DateUtil.parseTimestamp(timeString, dateFormat);
        Date date = new Date(1198993148000L);
        String result = DateUtil.getFormatDate(date, dateFormat);
        System.out.println(result);
    }

}
