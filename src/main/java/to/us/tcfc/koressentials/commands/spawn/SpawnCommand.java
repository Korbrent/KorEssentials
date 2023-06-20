package to.us.tcfc.koressentials.commands.spawn;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import to.us.tcfc.koressentials.commands._iCommand;

public class SpawnCommand extends _iCommand {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 0) {
            sender.sendMessage("Usage: /spawn");
            return true;
        }

        if(!(sender instanceof Player p)) {
            sender.sendMessage("You must be a player to use this command!");
            return true;
        }

        return spawnCommand(p);
    }

    private boolean spawnCommand(Player p){
        Location spawnLocation = getSpawnLocation();
        if(spawnLocation != null) {
            subscriber.getBackCommand().setBackLocation(p.getUniqueId(), p.getLocation());
            return p.teleport(spawnLocation);
        } else {
            p.sendMessage(ChatColor.RED + "Spawn location not found, please notify an admin!");
            return false;
        }
    }

    private Location getSpawnLocation() {
        String worldName = subscriber.getConfig().getString("worldName");

        if(worldName != null) {
            World world = subscriber.getServer().getWorld(worldName);
            if(world != null) {
                return world.getSpawnLocation();
            }
        }
        return null;
    }
}
