package to.us.tcfc.koressentials.commands.tpa;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPAHereCommand extends TPACommand {
    public TPAHereCommand(TPAManager tpaManager) {
        super(tpaManager);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player player)) {
            sender.sendMessage("You must be a player to use this command!");
            return true;
        }

        if (args.length != 1) {
            player.sendMessage("Usage: /tpahere <player>");
            return true;
        }

        return tpaCommand(player, args[0], true);
    }
}
