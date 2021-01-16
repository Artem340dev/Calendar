package calendar.main;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class SpigotPlugin extends JavaPlugin {
    public Map<Player, LocalDate> time = new HashMap<>();

    public void onEnable() {
        getCommand("cal").setExecutor(new Cal(this));
        getCommand("calevent").setExecutor(new CalEvent(this));
        Bukkit.getPluginManager().registerEvents(new Events(this), this);
        File config = new File("resources/config.yml");
        if (!config.exists()) {
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        }
    }
}