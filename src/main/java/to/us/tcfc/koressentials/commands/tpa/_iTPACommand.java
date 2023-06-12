package to.us.tcfc.koressentials.commands.tpa;

import to.us.tcfc.koressentials.commands._iCommand;

abstract class _iTPACommand extends _iCommand {
    protected TPAManager tpaManager;

    _iTPACommand(TPAManager tpaManager) {
        super();
        this.tpaManager = tpaManager;
        this.setSubscriber(tpaManager.subscriber);
    }
}
