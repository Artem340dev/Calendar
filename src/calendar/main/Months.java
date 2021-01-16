package calendar.main;

public enum Months {
    JANUARY(1, "Января"), FEBRUARY(2, "Февраля"), MARCH(3, "Марта"), APRIL(4, "Апреля"), MAY(5, "Мая"), JUNE(6, "Июня"), JULY(7, "Июля"), AUGUST(8, "Августа"), SEPTEMBER(9, "Сентября"), OCTOBER(10, "Октября"), NOVEMBER(11, "Ноября"), DECEMBER(12, "Декабря");

    public int month;
    public String name;
    Months(int month, String name) {
        this.month = month;
        this.name = name;
    }
}
