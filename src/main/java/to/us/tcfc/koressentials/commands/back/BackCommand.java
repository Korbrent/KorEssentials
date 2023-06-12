package to.us.tcfc.koressentials.commands.back;

import org.apache.commons.collections4.map.PassiveExpiringMap;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import to.us.tcfc.koressentials.commands._iCommand;

import java.util.Map;
import java.util.UUID;

public class BackCommand extends _iCommand {
    private final Map<UUID, Location> backLocations = new PassiveExpiringMap<>(300000);

    private boolean backCommand(Player p) {
        if (backLocations.containsKey(p.getUniqueId())) {
            p.teleport(backLocations.get(p.getUniqueId()));
            p.sendMessage(ChatColor.YELLOW + "Teleported to your last location.");
            backLocations.remove(p.getUniqueId());
        } else {
            p.sendMessage(ChatColor.RED + "You have no last location to teleport to!");
        }
        return true;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("You must be a player to use this command!");
            return true;
        }

        if (args.length != 0) {
            p.sendMessage("Usage: /back");
            return true;
        }

        return backCommand(p);
    }

    public void setBackLocation(UUID uuid, Location location) {
        backLocations.put(uuid, location);
    }
}
