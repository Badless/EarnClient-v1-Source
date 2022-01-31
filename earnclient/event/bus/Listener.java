package earnclient.event.bus;

import java.lang.reflect.*;

public class Listener
{
    private Object parent;
    private Method method;
    private Class<?> eventClass;
    private Handler handler;
    
    public Listener(final Object parent, final Method method) {
        this.parent = parent;
        this.method = method;
        this.eventClass = method.getParameterTypes()[0];
        this.handler = method.getAnnotation(Handler.class);
    }
    
    public Object getParent() {
        return this.parent;
    }
    
    public Method getMethod() {
        return this.method;
    }
    
    public Class<?> getEventClass() {
        return this.eventClass;
    }
    
    public Handler getHandler() {
        return this.handler;
    }
    
    public <E> void run(final E event) {
        try {
            this.method.invoke(this.parent, event);
        }
        catch (IllegalAccessException | InvocationTargetException ex2) {
            final ReflectiveOperationException ex;
            final ReflectiveOperationException e = ex;
            e.printStackTrace();
        }
    }
}
