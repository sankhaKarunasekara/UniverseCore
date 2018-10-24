package lk.universe.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtil {

    private static Calendar calender  = GregorianCalendar.getInstance();

    public static Date getTime(long mills){

        calender.setTimeInMillis(mills);
        return calender.getTime();
    }

    public static Date getTimeFromString(String dateString) throws ParseException {
        SimpleDateFormat dateTimeParser = new SimpleDateFormat("MMM dd, yyyy H:mm:ss.SSS", Locale.ENGLISH);
        return dateTimeParser.parse(dateString);
    }

}
