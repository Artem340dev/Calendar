package calendar.main;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {
    public String getPeriod(LocalDate today, LocalDate l2) {
        int days = Period.between(today, l2).getDays();
        if (days == 0 && l2.getMonthValue() == LocalDate.now().getMonthValue()) {
            return "Сегодня";
        }
        if (l2.isAfter(today)) {
            return "Через " + Math.abs(days) + " дней";
        } else if (l2.isBefore(today)) {
            return "Было " + Math.abs(days) + " дней назад";
        }
        return null;
    }

    public ItemStack item(String name, List<String> lore, Material mat, short data) {
        ItemStack i = new ItemStack(mat, 1, data);
        ItemMeta meta = i.getItemMeta();
        meta.setDisplayName(name.replace('&', '\u00a7'));
        meta.setLore(lore);
        i.setItemMeta(meta);
        return i;
    }
}