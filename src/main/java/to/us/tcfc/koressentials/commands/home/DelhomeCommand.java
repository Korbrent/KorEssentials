package to.us.tcfc.koressentials.commands.home;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelhomeCommand extends _iHomeCommand {

    public DelhomeCommand(HomeManager homeManager) {
        super(homeManager);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player p)) {
            sender.sendMessage("You must be a player to use this command!");
            return true;
        }

        if(args.length != 1){
            p.sendMessage("Usage: /delhome <name>");
            return true;
        }

        return delHomeCommand(p, args[0]);
    }

    private boolean delHomeCommand(Player p, String name) {
        if(!homeManager.isHome(p, name)){
            p.sendMessage(ChatColor.RED + "You do not have a home with that name!");
            return true;
        }

        if(homeManager.deleteHome(p, name)){
            p.sendMessage(ChatColor.YELLOW + "Home " + ChatColor.GOLD + name + ChatColor.YELLOW + " deleted!");
            return true;
        }
        p.sendMessage(ChatColor.RED + "There was an error deleting your home!");
        return false;
    }
}
