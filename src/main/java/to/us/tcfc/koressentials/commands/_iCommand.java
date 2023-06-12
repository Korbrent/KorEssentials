package to.us.tcfc.koressentials.commands;

import org.bukkit.command.CommandExecutor;
import to.us.tcfc.koressentials.Subscriber;

public abstract class _iCommand implements CommandExecutor {
    protected Subscriber subscriber = null;

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }
}
