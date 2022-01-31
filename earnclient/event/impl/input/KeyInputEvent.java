package earnclient.event.impl.input;

import earnclient.event.*;

public class KeyInputEvent extends Event2
{
    private int key;
    
    public KeyInputEvent(final int key) {
        this.key = key;
    }
    
    public int getKey() {
        return this.key;
    }
}
