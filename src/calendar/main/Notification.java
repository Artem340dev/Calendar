package calendar.main;

import org.bukkit.entity.Player;

import java.time.LocalDate;

public class Notification {
    private Player p;
    private LocalDate date;
    private String name;

    public Notification(Player p, int year, int month, int day, String name) {
        this.p = p;
        this.date = LocalDate.of(day, month, year);
        this.name = name;
    }

    public Player getPlayer() {
        return p;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return name;
    }
}