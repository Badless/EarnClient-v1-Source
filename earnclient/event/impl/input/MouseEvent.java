package earnclient.event.impl.input;

import earnclient.event.*;

public class MouseEvent extends Event2
{
    private int button;
    
    public MouseEvent(final int button) {
        this.button = button;
    }
    
    public int getButton() {
        return this.button;
    }
    
    public void setButton(final int button) {
        this.button = button;
    }
}
