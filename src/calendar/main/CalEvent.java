package calendar.main;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.time.LocalDate;

public class CalEvent implements CommandExecutor {
    private SpigotPlugin plugin;

    public CalEvent(SpigotPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length > 3 && !plugin.getConfig().contains("notifications."+p.getName())) {
                int day = Integer.parseInt(args[0]);
                int month = Integer.parseInt(args[1]);
                int year = Integer.parseInt(args[2]);
                if (LocalDate.of(day, month, year).isAfter(LocalDate.now())) {
                    String s = new String();
                    for (int i = 3; i < args.length; i++) {
                        s += args[i] + " ";
                    }
                    plugin.getConfig().set("notifications."+p.getName()+".year", year);
                    plugin.getConfig().set("notifications."+p.getName()+".month", month);
                    plugin.getConfig().set("notifications."+p.getName()+".day", day);
                    plugin.getConfig().set("notifications."+p.getName()+".name", s);
                    plugin.saveConfig();
                    p.sendMessage(ChatColor.GREEN + "Успешно!");
                    return true;
                } else return false;
            } else return false;
        } else return false;
    }
}