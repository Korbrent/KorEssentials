package to.us.tcfc.koressentials.commands.home;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SethomeCommand extends _iHomeCommand {
    public SethomeCommand(HomeManager homeManager) {
        super(homeManager);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player p)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }

        if(args.length == 0){
            return setHomeCommand(p, "home");
        } else if(args.length != 1){
            p.sendMessage("Usage: /sethome <name>");
            return true;
        }
        return setHomeCommand(p, args[0]);
    }

    private boolean setHomeCommand(Player p, String name) {
        if(homeManager.isHome(p, name)){
            p.sendMessage(ChatColor.RED + "You already have a home with that name!");
            return true;
        }

        if(homeManager.getHomeCount(p) >= homeManager.getMaxHomes()){
            p.sendMessage(ChatColor.RED + "You have reached the maximum number of homes!");
            return true;
        }

        Location loc = p.getLocation();
        if(homeManager.saveHome(p, name)){
            p.sendMessage(ChatColor.YELLOW + "Home " + ChatColor.GOLD + name + ChatColor.YELLOW + " set!");
            return true;
        }
        p.sendMessage(ChatColor.RED + "There was an error setting your home!");
        return false;
    }
}
