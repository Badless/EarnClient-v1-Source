package earnclient.event.impl.player;

import earnclient.event.cancelable.*;

public class SlowdownEvent extends CancelableEvent
{
    private Type type;
    
    public SlowdownEvent(final Type type) {
        this.type = type;
    }
    
    public Type getType() {
        return this.type;
    }
    
    public enum Type
    {
        Item("Item", 0), 
        Sprinting("Sprinting", 1), 
        SoulSand("SoulSand", 2), 
        Water("Water", 3);
        
        private Type(final String s, final int n) {
        }
    }
}
