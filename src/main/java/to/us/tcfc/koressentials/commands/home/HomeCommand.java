package to.us.tcfc.koressentials.commands.home;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommand extends _iHomeCommand {
    public HomeCommand(HomeManager homeManager) {
        super(homeManager);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player p)) {
            sender.sendMessage("You must be a player to use this command!");
            return true;
        }

        if(args.length == 0){
            return homeCommand(p, "home");
        } else if(args.length != 1){
            p.sendMessage("Usage: /home <name>");
            return true;
        }
        return homeCommand(p, args[0]);
    }

    private boolean homeCommand(Player p, String name){
        if(!homeManager.isHome(p, name)){
            String msg = ChatColor.RED + "Home " +
                    ChatColor.YELLOW + name +
                    ChatColor.RED + " does not exist.";
            p.sendMessage(msg);
            return true;
        }

        Location loc = homeManager.loadHome(p, name);
        if(loc == null){
            p.sendMessage("Coordinates to home not found or failed to load, please notify an admin.");
            return false;
        }

        subscriber.getBackCommand().setBackLocation(p.getUniqueId(), p.getLocation());
        p.teleport(loc);
        p.sendMessage("Teleported to home " + name + ".");
        return true;
    }
}
