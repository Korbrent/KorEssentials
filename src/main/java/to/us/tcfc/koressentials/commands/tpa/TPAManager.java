package to.us.tcfc.koressentials.commands.tpa;

import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections4.map.PassiveExpiringMap;
import to.us.tcfc.koressentials.Subscriber;

public class TPAManager {
    private final Map<UUID, UUID> tpaRequests = new PassiveExpiringMap<>(10000); // <Player, Requester>
    private final Map<UUID, UUID> tpaHereRequests = new PassiveExpiringMap<>(10000); // <Requester, Player>
    Subscriber subscriber;

    public TPAManager(Subscriber subscriber){
        this.subscriber = subscriber;
    }

    protected boolean isTpaHereRequest(UUID player, UUID requester) {
        try {
            return tpaHereRequests.get(requester).equals(player);
        } catch (NullPointerException e) {
            return false;
        }
    }

    protected boolean containsTpaRequest(UUID uuid){
        try{
            return tpaRequests.containsKey(uuid);
        } catch (NullPointerException e){
            return false;
        }
    }

    protected boolean addTpaRequest(UUID player, UUID requester, boolean isTpaHereRequest){
        if(containsTpaRequest(player))
            return false;

        tpaRequests.put(player, requester);
        if(isTpaHereRequest)
            tpaHereRequests.put(requester, player);

        StringBuilder message = new StringBuilder("TPA Requests: \n");
        for(UUID key: tpaRequests.keySet()){
            message.append("Requested: ").append(key).append(" Requester: ").append(tpaRequests.get(key)).append("\n");
        }
        message.append("TPA Here Requests: \n");
        for(UUID key: tpaHereRequests.keySet()){
            message.append("Requested: ").append(tpaHereRequests.get(key)).append(" Requester: ").append(key).append("\n");
        }
        subscriber.getLogger().info(message.toString());
        return true;
    }

    protected Player getRequester(UUID uuid){
        return subscriber.getServer().getPlayer(tpaRequests.get(uuid));
    }

    protected void deleteTpaRequest(UUID p){
        UUID r = tpaRequests.get(p);
        tpaRequests.remove(p);

        if(isTpaHereRequest(p, r))
            tpaHereRequests.remove(r);
    }
}
