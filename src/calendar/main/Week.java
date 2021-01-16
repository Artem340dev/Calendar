package calendar.main;

public enum Week {
    MONDAY(1, "Понедельник"), TUESDAY(2, "Вторник"), WEDNESDAY(3, "Среда"), THURSDAY(4, "Четверг"), FRIDAY(5, "Пятница"), SATURDAY(6, "Суббота"), SUNDAY(7, "Воскресенье");

    public int day;
    public String name;
    Week(int day, String name) {
        this.day = day;
        this.name = name;
    }
}