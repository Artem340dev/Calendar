package calendar.main;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;

public class Cal extends Utils implements CommandExecutor {
    public SpigotPlugin plugin;

    public Cal(SpigotPlugin plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            LocalDate date = LocalDate.now();
            plugin.time.put(p, date);
            String monthValue = null;
            int month = date.getMonthValue();
            int today = date.getDayOfMonth();
            int slot = 0;
            LocalDate newDate = date.minusDays(today-1);
            Notification notify = null;
            if (plugin.getConfig().contains("notifications."+p.getName())) {
                notify = new Notification(p, plugin.getConfig().getInt("notifications."+p.getName()+".year"), plugin.getConfig().getInt("notifications."+p.getName()+".month"), plugin.getConfig().getInt("notifications."+p.getName()+".day"), plugin.getConfig().getString("notifications."+p.getName()+".name"));
            }
            Inventory calendar = Bukkit.createInventory(null, 54, "Календарь: " + date.getYear());
            for (Months months : Months.values()) {
                if (months.month == month) {
                    monthValue = months.name;
                }
            }
            for (Week week : Week.values()) {
                calendar.setItem(week.day, item("&f"+week.name, null, Material.STAINED_GLASS_PANE, (short) 0));
            }
            while (newDate.getMonthValue() == month) {
                if (newDate.getDayOfWeek().getValue() == 1 && newDate.getDayOfMonth() > 1) slot++;
                if (getPeriod(date, newDate).equals("Сегодня")) {
                    calendar.setItem(newDate.getDayOfWeek().getValue()+(9*slot)+9, item("&a"+newDate.getDayOfMonth()+" "+monthValue, Arrays.asList("", ("&7"+getPeriod(date, newDate)).replace('&', '\u00a7')), Material.WOOL, (short) 5));
                } else {
                    if (notify != null) {
                        if (newDate.equals(notify.getDate())) {
                            calendar.setItem(newDate.getDayOfWeek().getValue()+(9*slot)+9, item("&c"+newDate.getDayOfMonth()+" "+monthValue, Arrays.asList("", ("&7"+getPeriod(date, newDate)).replace('&', '\u00a7'), ("&7"+notify.getName()).replace('&', '\u00a7')), Material.WOOL, (short) 14));
                        } else calendar.setItem(newDate.getDayOfWeek().getValue()+(9*slot)+9, item("&f"+newDate.getDayOfMonth()+" "+monthValue, Arrays.asList("", ("&7"+getPeriod(date, newDate)).replace('&', '\u00a7')), Material.WOOL, (short) 0));
                    } else calendar.setItem(newDate.getDayOfWeek().getValue()+(9*slot)+9, item("&f"+newDate.getDayOfMonth()+" "+monthValue, Arrays.asList("", ("&7"+getPeriod(date, newDate)).replace('&', '\u00a7')), Material.WOOL, (short) 0));
                }
                newDate = newDate.plusDays(1);
            }
            calendar.setItem(0, item("&cПредыдущий месяц", null, Material.STAINED_GLASS_PANE, (short) 14));
            calendar.setItem(8, item("&aСледующий месяц", null, Material.STAINED_GLASS_PANE, (short) 5));
            p.openInventory(calendar);
            return true;
        } else {
            return false;
        }
    }
}