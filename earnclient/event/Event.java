package earnclient.event;

import earnclient.*;
import java.lang.reflect.*;
import java.util.*;

public abstract class Event
{
    private boolean cancelled;
    
    public Event call() {
        this.cancelled = false;
        call(this);
        return this;
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
    
    private static final void call(final Event event) {
        final EventManager eventManager = EarnClient.INSTANCE.eventManager;
        final ArrayHelper<Data> dataList = EventManager.get(event.getClass());
        if (dataList != null) {
            for (final Data data : dataList) {
                try {
                    data.target.invoke(data.source, event);
                }
                catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                catch (InvocationTargetException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }
    
    public enum State
    {
        PRE("PRE", 0, "PRE", 0), 
        POST("POST", 1, "POST", 1);
        
        private State(final String s, final int n, final String string, final int number) {
        }
    }
}
