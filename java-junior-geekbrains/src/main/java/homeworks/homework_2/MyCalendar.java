package homeworks.homework_2;

import homeworks.homework_2.lib.RandomDate;

import java.util.Date;

public class MyCalendar {
    public MyCalendar() {
    }

    public MyCalendar(Date firstDate) {
        this.firstDate = firstDate;
    }

    @RandomDate // рандомная дата от 12 декабря 2012 по 01 января 2025 г.
    private Date firstDate;

    public Date getFirstDate() {
        return firstDate;
    }

    @Override
    public String toString() {
        return "MyCalendar{" +
                "firstDate=" + firstDate +
                '}';
    }

}
