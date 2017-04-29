package demo.test;

import org.joda.time.DateTime;

/**
 * Created by zhengjianhui on 17/3/7.
 */
public class DateTest {

    private static final DateTime VALIDATION_DATE = new DateTime("1900-01-01");

    public static void main(String[] args) {
        DateTime d1 = new DateTime("1915-03-03");
        boolean flag = d1.isBefore(VALIDATION_DATE);
        System.out.println(flag ? "d1在d2之前" : "d1在d2之后");

    }
}
