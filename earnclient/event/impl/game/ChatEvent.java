package earnclient.event.impl.game;

import earnclient.event.cancelable.*;

public class ChatEvent extends CancelableEvent
{
    private String msg;
    
    public ChatEvent(final String msg) {
        this.msg = msg;
    }
    
    public String getMsg() {
        return this.msg;
    }
}
