package earnclient.event.impl.player;

import earnclient.event.cancelable.*;

public class PushEvent extends CancelableEvent
{
    private boolean pre;
    
    public PushEvent(final boolean pre) {
        this.pre = pre;
    }
    
    public boolean isPre() {
        return this.pre;
    }
}
