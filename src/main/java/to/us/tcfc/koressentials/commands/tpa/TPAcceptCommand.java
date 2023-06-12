package to.us.tcfc.koressentials.commands.tpa;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import to.us.tcfc.koressentials.commands.back.BackCommand;

public class TPAcceptCommand extends _iTPACommand {
    public TPAcceptCommand(TPAManager tpaManager) {
        super(tpaManager);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player p)) {
            sender.sendMessage("You must be a player to use this command!");
            return true;
        }

        if(args.length != 0){
            p.sendMessage("Usage: /tpaccept");
            return true;
        }

        if(!tpaManager.containsTpaRequest(p.getUniqueId())){
            p.sendMessage(ChatColor.RED + "You have no pending teleport requests!");
            return true;
        }

        Player requester = tpaManager.getRequester(p.getUniqueId());

        return tpaAcceptCommand(p, requester);
    }

    public boolean tpaAcceptCommand(Player p, Player requester){
        BackCommand backCommand = subscriber.getBackCommand();

        Player destination = p;
        Player teleportee = requester;
        if(tpaManager.isTpaHereRequest(p.getUniqueId(), requester.getUniqueId())){
            teleportee = p;
            destination = requester;
        }
        backCommand.setBackLocation(teleportee.getUniqueId(), teleportee.getLocation());
        p.sendMessage(ChatColor.GREEN + "You have accepted the teleport request from " +
                ChatColor.AQUA + teleportee.getName() + ChatColor.GREEN + ".");

        requester.sendMessage(ChatColor.AQUA + p.getName() +
                ChatColor.GREEN + " has accepted your teleport request.");

        teleportee.sendMessage(ChatColor.GREEN + "Teleporting you to " +
                ChatColor.AQUA + destination.getName() + ChatColor.GREEN + "...");
        destination.sendMessage(ChatColor.GREEN + "Teleporting " +
                ChatColor.AQUA + teleportee.getName() + ChatColor.GREEN + " to you...");

        tpaManager.deleteTpaRequest(p.getUniqueId());
        return teleportee.teleport(destination.getLocation());
    }
}
