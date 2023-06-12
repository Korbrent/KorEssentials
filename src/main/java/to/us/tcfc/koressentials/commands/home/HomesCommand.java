package to.us.tcfc.koressentials.commands.home;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

import java.util.List;

public class HomesCommand extends _iHomeCommand{
    public HomesCommand(HomeManager homeManager) {
        super(homeManager);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player p)) {
            sender.sendMessage("You must be a player to use this command!");
            return true;
        }

        if(args.length != 0){
            p.sendMessage("Usage: /homes");
            return true;
        }
        return homesCommand(p);
    }

    private boolean homesCommand(Player p){
        List<String> homes = homeManager.getHomes(p);
        if(homes.isEmpty()){
            p.sendMessage(ChatColor.RED + "You don't have any homes!");
            return true;
        }
        String msg = ChatColor.YELLOW + "Your homes: ";
        for(String home : homes) {
            msg += ChatColor.GOLD + home + ChatColor.YELLOW + ", ";
        }
        msg = msg.substring(0, msg.length() - 2);
        msg += ChatColor.YELLOW + ".";
        p.sendMessage(msg);
        return true;
    }
}
