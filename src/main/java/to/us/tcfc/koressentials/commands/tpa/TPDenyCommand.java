package to.us.tcfc.koressentials.commands.tpa;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPDenyCommand extends _iTPACommand {
    public TPDenyCommand(TPAManager tpaManager) {
        super(tpaManager);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player p)) {
            sender.sendMessage("You must be a player to use this command!");
            return true;
        }

        if(args.length != 0){
            p.sendMessage("Usage: /tpdeny");
            return true;
        }

        if(!tpaManager.containsTpaRequest(p.getUniqueId())){
            p.sendMessage(ChatColor.RED + "You have no pending teleport requests!");
            return true;
        }

        return tpDenyCommand(p);
    }

    private boolean tpDenyCommand(Player p) {
        Player r = tpaManager.getRequester(p.getUniqueId());
        tpaManager.deleteTpaRequest(p.getUniqueId());

        p.sendMessage(ChatColor.RED + "You have denied the teleport request from " + ChatColor.AQUA + r.getName() + ChatColor.RED + ".");
        r.sendMessage(ChatColor.AQUA + p.getName() + ChatColor.RED + " has denied your teleport request.");
        return true;
    }
}
