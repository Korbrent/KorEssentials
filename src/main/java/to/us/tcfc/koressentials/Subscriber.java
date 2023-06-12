package to.us.tcfc.koressentials;

import org.bukkit.plugin.java.JavaPlugin;
import to.us.tcfc.koressentials.commands.back.BackCommand;
import to.us.tcfc.koressentials.commands.home.HomeManager;
import to.us.tcfc.koressentials.commands.tpa.TPAManager;

public abstract class Subscriber extends JavaPlugin {
    protected TPAManager tpaManager;
    protected BackCommand backCommand;
    protected HomeManager homeManager;

    abstract public TPAManager getTPACommand();
    abstract public BackCommand getBackCommand();
}
