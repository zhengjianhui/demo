package demo;

import demo.mj.T;

/**
 * Created by zhengjianhui on 16/11/9.
 */
public class AAA {

    public static void main(String[] args) {
        T a = new T();
        a.setName("123");
        System.out.println(a);

        aa(a);

        System.out.println(a);


    }

    private static void aa(T t) {
        t.setName("321");
    }
}
