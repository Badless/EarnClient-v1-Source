package earnclient.event.cancelable;

import earnclient.event.*;

public class CancelableEvent extends Event2
{
    private boolean canceled;
    
    public boolean isCanceled() {
        return this.canceled;
    }
    
    public void setCanceled(final boolean canceled) {
        this.canceled = canceled;
    }
}
