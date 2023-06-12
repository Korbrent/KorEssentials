package to.us.tcfc.koressentials.commands.home;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BedCommand extends _iHomeCommand {
    public BedCommand(HomeManager homeManager) {
        super(homeManager);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player p)) {
            sender.sendMessage("You must be a player to use this command!");
            return true;
        }

        if(args.length != 0){
            p.sendMessage("Usage: /bed");
            return true;
        }
        return bedCommand(p);
    }

    private boolean bedCommand(Player p){
        Location bed = p.getBedSpawnLocation();
        if(bed == null){
            p.sendMessage(ChatColor.RED + "You do not have a bed spawn location.");
            return true;
        }
        subscriber.getBackCommand().setBackLocation(p.getUniqueId(), p.getLocation());
        p.teleport(bed);
        p.sendMessage("Teleported to bed!");
        return true;
    }
}
