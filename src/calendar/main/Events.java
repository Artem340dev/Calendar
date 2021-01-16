package calendar.main;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.logging.Logger;

public class Events extends Utils implements Listener {
    private SpigotPlugin plugin;

    public Events(SpigotPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Inventory c = e.getInventory();
        if (c.getName().contains("Календарь")) {
            e.setCancelled(true);
            if (e.getSlot() == 8) {
                Player p = (Player) e.getWhoClicked();
                plugin.getLogger().info(String.valueOf(plugin.time.get(p)));
                LocalDate date = plugin.time.get(p);
                date = date.plusMonths(1);
                int month = date.getMonthValue();
                int today = date.getDayOfMonth();
                int slot = 0;
                String monthValue = null;
                LocalDate newDate = date.minusDays(today-1);
                Notification notify = null;
                if (plugin.getConfig().contains("notifications."+p.getName())) {
                    notify = new Notification(p, plugin.getConfig().getInt("notifications."+p.getName()+".year"), plugin.getConfig().getInt("notifications."+p.getName()+".month"), plugin.getConfig().getInt("notifications."+p.getName()+".day"), plugin.getConfig().getString("notifications."+p.getName()+".name"));
                }
                plugin.time.replace(p, date);
                Inventory calendar = Bukkit.createInventory(null, 54, "Календарь: " + newDate.getYear());
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
                    if (getPeriod(newDate, LocalDate.now()).equals("Сегодня")) {
                        calendar.setItem(newDate.getDayOfWeek().getValue()+(9*slot)+9, item("&a"+newDate.getDayOfMonth()+" "+monthValue, Arrays.asList("", ("&7"+getPeriod(LocalDate.now(), newDate)).replace('&', '\u00a7')), Material.WOOL, (short) 5));
                    } else {
                        if (notify != null) {
                            if (newDate.equals(notify.getDate())) {
                                calendar.setItem(newDate.getDayOfWeek().getValue()+(9*slot)+9, item("&c"+newDate.getDayOfMonth()+" "+monthValue, Arrays.asList("", ("&7"+getPeriod(LocalDate.now(), newDate)).replace('&', '\u00a7'), ("&7"+notify.getName()).replace('&', '\u00a7')), Material.WOOL, (short) 14));
                            } else calendar.setItem(newDate.getDayOfWeek().getValue()+(9*slot)+9, item("&f"+newDate.getDayOfMonth()+" "+monthValue, Arrays.asList("", ("&7"+getPeriod(LocalDate.now(), newDate)).replace('&', '\u00a7')), Material.WOOL, (short) 0));
                        } else calendar.setItem(newDate.getDayOfWeek().getValue()+(9*slot)+9, item("&f"+newDate.getDayOfMonth()+" "+monthValue, Arrays.asList("", ("&7"+getPeriod(LocalDate.now(), newDate)).replace('&', '\u00a7')), Material.WOOL, (short) 0));
                    }
                    newDate = newDate.plusDays(1);
                }
                calendar.setItem(0, item("&cПредыдущий месяц", null, Material.STAINED_GLASS_PANE, (short) 14));
                calendar.setItem(8, item("&aСледующий месяц", null, Material.STAINED_GLASS_PANE, (short) 5));
                p.closeInventory();
                p.openInventory(calendar);
            } else if (e.getSlot() == 0) {
                Player p = (Player) e.getWhoClicked();
                plugin.getLogger().info(String.valueOf(plugin.time.get(p)));
                LocalDate date = plugin.time.get(p);
                date = date.minusMonths(1);
                int month = date.getMonthValue();
                int today = date.getDayOfMonth();
                int slot = 0;
                String monthValue = null;
                LocalDate newDate = date.minusDays(today-1);
                Notification notify = null;
                if (plugin.getConfig().contains("notifications."+p.getName())) {
                    notify = new Notification(p, plugin.getConfig().getInt("notifications."+p.getName()+".year"), plugin.getConfig().getInt("notifications."+p.getName()+".month"), plugin.getConfig().getInt("notifications."+p.getName()+".day"), plugin.getConfig().getString("notifications."+p.getName()+".name"));
                }
                plugin.time.replace(p, date);
                Inventory calendar = Bukkit.createInventory(null, 54, "Календарь: " + newDate.getYear());
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
                    if (getPeriod(newDate, LocalDate.now()).equals("Сегодня")) {
                        calendar.setItem(newDate.getDayOfWeek().getValue()+(9*slot)+9, item("&a"+newDate.getDayOfMonth()+" "+monthValue, Arrays.asList("", ("&7"+getPeriod(LocalDate.now(), newDate)).replace('&', '\u00a7')), Material.WOOL, (short) 5));
                    } else {
                        if (notify != null) {
                            if (newDate.equals(notify.getDate())) {
                                calendar.setItem(newDate.getDayOfWeek().getValue()+(9*slot)+9, item("&c"+newDate.getDayOfMonth()+" "+monthValue, Arrays.asList("", ("&7"+getPeriod(LocalDate.now(), newDate)).replace('&', '\u00a7'), ("&7"+notify.getName()).replace('&', '\u00a7')), Material.WOOL, (short) 14));
                            } else calendar.setItem(newDate.getDayOfWeek().getValue()+(9*slot)+9, item("&f"+newDate.getDayOfMonth()+" "+monthValue, Arrays.asList("", ("&7"+getPeriod(LocalDate.now(), newDate)).replace('&', '\u00a7')), Material.WOOL, (short) 0));
                        } else calendar.setItem(newDate.getDayOfWeek().getValue()+(9*slot)+9, item("&f"+newDate.getDayOfMonth()+" "+monthValue, Arrays.asList("", ("&7"+getPeriod(LocalDate.now(), newDate)).replace('&', '\u00a7')), Material.WOOL, (short) 0));
                    }
                    newDate = newDate.plusDays(1);
                }
                calendar.setItem(0, item("&cПредыдущий месяц", null, Material.STAINED_GLASS_PANE, (short) 14));
                calendar.setItem(8, item("&aСледующий месяц", null, Material.STAINED_GLASS_PANE, (short) 5));
                p.closeInventory();
                p.openInventory(calendar);
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (plugin.getConfig().contains("notifications."+e.getPlayer().getName())) {
            int year = plugin.getConfig().getInt("notifications."+e.getPlayer().getName()+".year");
            int month = plugin.getConfig().getInt("notifications."+e.getPlayer().getName()+".month");
            int day = plugin.getConfig().getInt("notifications."+e.getPlayer().getName()+".day");
            String name = plugin.getConfig().getString("notifications."+e.getPlayer().getName()+".name");
            Notification notify = new Notification(e.getPlayer(), year, month, day, name);
            if (notify.getDate().equals(LocalDate.now().plusDays(1))) {
                e.getPlayer().sendTitle("&7[&6Напоминания&7]".replace('&', '\u00a7'), ("&aЗавтра состоится событие " + name).replace('&', '\u00a7'));
            } else if (notify.getDate().equals(LocalDate.now())) {
                e.getPlayer().sendTitle("&7[&6Напоминания&7]".replace('&', '\u00a7'), ("&aСегодня состоится событие " + name).replace('&', '\u00a7'));
            } else if (notify.getDate().equals(LocalDate.now().minusDays(1))) {
                plugin.getConfig().set("notifications."+e.getPlayer().getName(), null);
                plugin.saveConfig();
            }
        }
    }
}