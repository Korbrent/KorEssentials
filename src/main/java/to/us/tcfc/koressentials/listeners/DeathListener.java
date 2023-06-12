package to.us.tcfc.koressentials.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import to.us.tcfc.koressentials.Subscriber;

public class DeathListener implements Listener {
    Subscriber subscriber;

    public DeathListener (Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player p = event.getEntity();
        subscriber.getBackCommand().setBackLocation(p.getUniqueId(), p.getLocation());
    }
}
