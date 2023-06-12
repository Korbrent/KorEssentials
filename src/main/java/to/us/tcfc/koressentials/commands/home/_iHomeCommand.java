package to.us.tcfc.koressentials.commands.home;

import to.us.tcfc.koressentials.commands._iCommand;

abstract class _iHomeCommand extends _iCommand {
    protected HomeManager homeManager;

    _iHomeCommand(HomeManager homeManager) {
        super();
        this.homeManager = homeManager;
        this.setSubscriber(homeManager.subscriber);
    }

    public void setHomeManager(HomeManager homeManager) {
        this.homeManager = homeManager;
    }
}
