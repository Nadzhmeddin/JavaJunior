package seminars.second_seminar.annotation;

import seminars.second_seminar.annotation.lib.Random;

public class Person {
    //    @Random("s")
//    private int age3;

    @Random// рандомное число в диапазоне 0 - 100
    protected int age1;

    @Random
    protected int age2;
}
