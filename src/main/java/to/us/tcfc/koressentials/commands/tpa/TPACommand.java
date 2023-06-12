package to.us.tcfc.koressentials.commands.tpa;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPACommand extends _iTPACommand {
    public TPACommand(TPAManager tpaManager) {
        super(tpaManager);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player player)) {
            sender.sendMessage("You must be a player to use this command!");
            return true;
        }

        if (args.length != 1) {
            player.sendMessage("Usage: /tpa <player>");
            return true;
        }

        return tpaCommand(player, args[0], false);
    }

    protected boolean tpaCommand(Player requester, String targetName, boolean isHere) {
        Player target = requester.getServer().getPlayer(targetName);

        if (target == null) {
            requester.sendMessage(ChatColor.RED + "Player " + targetName + " not found!");
            return true;
        }
        if (target == requester) {
            requester.sendMessage(ChatColor.RED + "You cannot teleport to yourself!");
            return true;
        }
        if (tpaManager.containsTpaRequest(target.getUniqueId())) {
            requester.sendMessage(ChatColor.RED + "That player already has a pending teleport request!");
            return true;
        }

        requester.sendMessage(ChatColor.YELLOW + "Request sent to " +
                ChatColor.AQUA + target.getName() + ChatColor.YELLOW + ", they have 10 seconds to accept.");

        String msg = ChatColor.AQUA + requester.getName() + ChatColor.YELLOW + " has requested ";
        if (!isHere) {
            msg += "to teleport to you.";
        } else {
            msg += "that you teleport to them.";
        }

        if(!tpaManager.addTpaRequest(target.getUniqueId(), requester.getUniqueId(), isHere)){
            requester.sendMessage(ChatColor.RED + "Failed to add request! Please message an admin about this bug.");
            return false;
        }

        msg += "This request will expire in 10 seconds.\n" +
                "Type " + ChatColor.GREEN + "/tpaccept" + ChatColor.YELLOW + " to accept or "
                + ChatColor.RED + "/tpdeny" + ChatColor.YELLOW + " to deny.";
        target.sendMessage(msg);

        return true;
    }
}
