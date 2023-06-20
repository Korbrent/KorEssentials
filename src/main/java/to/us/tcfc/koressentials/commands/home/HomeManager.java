package to.us.tcfc.koressentials.commands.home;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import to.us.tcfc.koressentials.Subscriber;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HomeManager {
    FileConfiguration config;
    private int maxHomes;
    Subscriber subscriber;
    File configFile;

    public HomeManager(Subscriber subscriber) {
        this.subscriber = subscriber;
        configFile = new File(subscriber.getDataFolder(), "homes.yml");
        makeConfig();
        maxHomes = subscriber.getConfig().getInt("max_homes");
        this.saveConfig();
    }

    void makeConfig() {
        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            subscriber.saveResource("homes.yml", false);
        }
        this.config = new YamlConfiguration();
        try {
            this.config.load(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected boolean saveHome(Player p, String homeName) {
        String path = p.getUniqueId() + "." + homeName;
        Location loc = p.getLocation();
        Location view = p.getEyeLocation();
        config.set(path + ".world", loc.getWorld().getName());
        config.set(path + ".x", loc.getX());
        config.set(path + ".y", loc.getY());
        config.set(path + ".z", loc.getZ());
        config.set(path + ".pitch", view.getPitch());
        config.set(path + ".yaw", view.getYaw());
        return this.saveConfig();
    }

    protected List<String> getHomes(Player p) {
        String path = p.getUniqueId().toString();
        if(!config.contains(path)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(config.getConfigurationSection(path).getKeys(false));
    }

    protected int getHomeCount(Player p) {
        return this.getHomes(p).size();
    }

    protected int getMaxHomes() {
        return this.maxHomes;
    }

    protected Location loadHome(Player p, String homeName) {
        String path = p.getUniqueId() + "." + homeName;
        World world = subscriber.getServer().getWorld(config.getString(path + ".world"));
        double x = config.getDouble(path + ".x");
        double y = config.getDouble(path + ".y");
        double z = config.getDouble(path + ".z");
        double yaw = config.getDouble(path + ".yaw");
        double pitch = config.getDouble(path + ".pitch");

        return new Location(world, x, y, z, (float) yaw, (float) pitch);
    }

    private boolean saveConfig() {
        try {
            config.save(configFile);
            config.load(configFile);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected boolean isHome(Player p, String homeName) {
        String path = p.getUniqueId() + "." + homeName;
        return config.contains(path);
    }

    protected boolean deleteHome(Player p, String homeName) {
        String path = p.getUniqueId() + "." + homeName;
        config.set(path, null);
        return this.saveConfig();
    }
}
