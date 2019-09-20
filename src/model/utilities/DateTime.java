package model.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateTime {


    private final static SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss a", Locale.ENGLISH);
    private final static SimpleDateFormat sf2 = new SimpleDateFormat("ddMMyyyy", Locale.ENGLISH);
    private final static SimpleDateFormat sf3 = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    private final static SimpleDateFormat weekSF = new SimpleDateFormat("EEEE", Locale.ENGLISH);

    public static String simpleDate(Date date){
        return sf2.format(date);
    }

    public static String simpleDateWithSlash(Date date){
        return sf3.format(date);
    }
    public static int getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static String getStringDayOfWeek(Date date) {
        return weekSF.format(date).toUpperCase();
    }

    public static Date addDay(Date target, int day) {
        Calendar c = Calendar.getInstance();
        c.setTime(target);
        c.add(Calendar.DAY_OF_YEAR, day);
        return c.getTime();
    }

    public static Date toDate(String string) throws ParseException {
        return sf3.parse(string);
    }

    public static long diffDaysOfDates(Date start, Date end) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd", Locale.ENGLISH);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
        LocalDate date1 = LocalDate.parse(simpleDateFormat.format(start), formatter);
        LocalDate date2 = LocalDate.parse(simpleDateFormat.format(end), formatter);
        return ChronoUnit.DAYS.between(date1, date2);
    }

    public static int diffDaysBetweenDates(Date start, Date end) {
        return (int) ChronoUnit.DAYS.between(start.toInstant(), end.toInstant());
    }

    public static int daysBetweenDates(Date start, Date end) {
        int k = 1;
        int sum = 0;
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        if (start.before(end)) {
            c1.setTime(start);
            c2.setTime(end);
        } else if (start.after(end)){
            c1.setTime(end);
            c2.setTime(start);
            k = -1;
        }else {
            return 0;
        }
        while(c1.before(c2)){
            c1.add(Calendar.DAY_OF_YEAR,1);
            sum++;
        }
        return k*sum;
    }


    private static int getDayOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    public static String getStringDate(Date date){
        return sf.format(date);
    }


}
