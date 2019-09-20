package test;

import exception.PropertyNotFoundException;
import exception.RecordStatusException;
import exception.RoomNumberException;
import model.*;
import model.utilities.DateTime;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TestMethod {
    public TestMethod() throws RoomNumberException {
    }

    @Test
    public void test1() {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(2019, 7, 9);
        int k = DateTime.getDayOfWeek(date);
        System.out.println(date);
        System.out.println(k);
    }

    @Test
    public void test2() {

        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        for (int i = 0; i < 7; i++) {
            c.add(Calendar.DAY_OF_YEAR, 1);
            System.out.println("[" + c.getTime() + "] " + DateTime.getDayOfWeek(c.getTime()));
        }
    }

    @Test
    public void test3() {
        Date date = new Date();
        Date date1 = DateTime.addDay(date, -11);
        System.out.println(date);
        System.out.println(date1);
        System.out.println(date);
    }

    @Test
    public void test4() {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(new Date(1991, 6, 19));
        Calendar c2 = Calendar.getInstance();
        c2.setTime(new Date(1993, 6, 12));

        int k1 = c1.get(Calendar.DAY_OF_YEAR);
        int k2 = c2.get(Calendar.DAY_OF_YEAR);
        System.out.println("K1:" + k1 + ";K2:" + k2);
        System.out.println(k1 - k2);
    }

    @Test
    public void test5() {
//        Date d1 = new Date(1991, 6, 19);
//        Date d2 = new Date(1993, 6, 16);
//        System.out.println(DateTime.diffDaysOfDates(d1, d2));
//        long k = DateTime.diffDaysOfDates(d2,d1);
//        int s = (int) (k +1);
//        System.out.println(s);
//
//        System.out.println(DateTime.daysBetweenDates(d1,d2));
//        System.out.println(DateTime.daysBetweenDates(d2,d1));
//        System.out.println(DateTime.daysBetweenDates(new Date(),new Date()));
    }

    @Test
    public void test6(){
        List<String> testList1 = new ArrayList<>();
        List<String> testList2 = new ArrayList<>();
        String a = "233";
        String b = "fda";
        testList1.add(a);
        testList1.add(b);

        testList2.add(0,a);
        testList2.add(0,b);

        System.out.println("list1:");
        print(testList1);
        System.out.println("list2:");
        print(testList2);

    }

    @Test
    public void test7() throws RecordStatusException {
        Date start = new Date();
        Date end = DateTime.addDay(start,0);
        Date act = DateTime.addDay(start,11);
        Record record = new Record("fdaf","fdafafa",start,end);
        System.out.println(record.showInfo());
        System.out.println();
        record.finish(act,143,156);
        System.out.println(record.showInfo());
    }

    Property a1 = new Apartment(33,"HA","Mel",2);
    Property a2 = new Apartment(32,"HAfd","Mel",2);
    Property a3 = new Apartment(53,"HfaA","Mel",2);
    Property a4 = new Apartment(21,"HA11","Mel",2);
    Property b1 = new PreSuite(233,"fdfHA","Mel",new Date());
    Property b2 = new PreSuite(553,"eqeHA","Mel",new Date());
    Property b3 = new PreSuite(113,"Hr3A","Mel",new Date());
    Property b4 = new PreSuite(352,"HAgdaf","Mel",new Date());
    RentalSys rentalSys = new RentalSys();

    @Test
    public void test8() {


        rentalSys.addNewP(a1);
        rentalSys.addNewP(a2);
        rentalSys.addNewP(a3);
        rentalSys.addNewP(a4);
        rentalSys.addNewP(b1);
        rentalSys.addNewP(b2);
        rentalSys.addNewP(b3);
        rentalSys.addNewP(b4);

        String id = "A_33HfAMEL";

        Property p;
        try {
            p = rentalSys.findProperty(id);
            System.out.println(p.showInfo());
        } catch (PropertyNotFoundException e) {
            System.out.println("Not found");
        }

    }

    @Test
    void test9() {

    }

    private void print(List<String> list){
        for (String s:list){
            System.out.println(s);
        }
    }
}
