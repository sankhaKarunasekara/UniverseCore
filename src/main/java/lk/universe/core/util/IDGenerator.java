package lk.universe.core.util;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class IDGenerator {

    public static long generateURLDataId(long timestamp){

        Calendar calender = GregorianCalendar.getInstance();
        Random random = new Random();

        int value = random.nextInt(1000);

        calender.setTimeInMillis(timestamp);

        int year = calender.get(Calendar.YEAR);
        int month = calender.get(Calendar.MONTH);
        int date = calender.get(Calendar.DATE);
        int hour = calender.get(Calendar.HOUR);
        int minite = calender.get(Calendar.MINUTE);
        int millisec = calender.get(Calendar.MILLISECOND);

        return 0;
    }
}
