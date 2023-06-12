package to.us.tcfc.koressentials;

import to.us.tcfc.koressentials.commands.back.BackCommand;
import to.us.tcfc.koressentials.commands.home.*;
import to.us.tcfc.koressentials.commands.tpa.*;
import to.us.tcfc.koressentials.listeners.DeathListener;

public class KorEssentials extends Subscriber {
    @Override
    public void onEnable() {
        getLogger().info("Starting up Korsentials right now...");

        if(!getDataFolder().mkdirs())
            getLogger().info("KorEssentials: Creating directories may have failed...");// Creates the data folder if it doesn't exist
        this.saveDefaultConfig(); // Saves default config if it doesn't exist

        this.tpaManager = new TPAManager(this);
        this.getCommand("tpa").setExecutor(new TPACommand(tpaManager));
        this.getCommand("tpahere").setExecutor(new TPAHereCommand(tpaManager));
        this.getCommand("tpaccept").setExecutor(new TPAcceptCommand(tpaManager));
        this.getCommand("tpdeny").setExecutor(new TPDenyCommand(tpaManager));

        this.backCommand = new BackCommand();
        this.backCommand.setSubscriber(this);
        this.getCommand("back").setExecutor(this.backCommand);
        
        this.homeManager = new HomeManager(this);
        this.getCommand("bed").setExecutor(new BedCommand(homeManager));
        this.getCommand("delhome").setExecutor(new DelhomeCommand(homeManager));
        this.getCommand("home").setExecutor(new HomeCommand(homeManager));
        this.getCommand("homes").setExecutor(new HomesCommand(homeManager));
        this.getCommand("sethome").setExecutor(new SethomeCommand(homeManager));

        this.getServer().getPluginManager().registerEvents(new DeathListener(this), this);

        this.saveConfig();

    }

    @Override
    public void onDisable() {
        getLogger().info("Shutting down KorEssentials...");

    }

    @Override
    public TPAManager getTPACommand() {
        return this.tpaManager;
    }

    @Override
    public BackCommand getBackCommand() {
        return this.backCommand;
    }
}
