package earnclient.event.bus;

import java.util.*;
import java.util.function.*;
import java.lang.annotation.*;
import java.util.concurrent.*;
import java.lang.reflect.*;

public class EventBus
{
    private Map<Class<?>, List<Listener>> registry;
    private Map<Class<?>, List<Listener>> cache;
    private Comparator<Listener> sorter;
    
    public EventBus() {
        this.registry = new HashMap<Class<?>, List<Listener>>();
        this.cache = new HashMap<Class<?>, List<Listener>>();
        this.sorter = Comparator.comparingInt(listener -> listener.getHandler().value());
    }
    
    public void bind(final Object parent) {
        if (this.cache.containsKey(parent.getClass())) {
            this.cache.get(parent.getClass()).forEach(this::bind);
        }
        else {
            Method[] declaredMethods;
            for (int length = (declaredMethods = parent.getClass().getDeclaredMethods()).length, i = 0; i < length; ++i) {
                final Method method = declaredMethods[i];
                if (method.isAnnotationPresent(Handler.class) && method.getParameterCount() == 1) {
                    if (!method.isAccessible()) {
                        method.setAccessible(true);
                    }
                    final Listener listener = new Listener(parent, method);
                    this.bind(listener);
                    this.cache.computeIfAbsent(parent.getClass(), v -> new CopyOnWriteArrayList()).add(listener);
                }
            }
        }
    }
    
    public void bind(final Listener listener) {
        final List<Listener> list = this.registry.computeIfAbsent(listener.getEventClass(), v -> new CopyOnWriteArrayList());
        list.add(listener);
        list.sort(this.sorter);
    }
    
    public void unbind(final Object parent) {
        if (this.cache.containsKey(parent.getClass())) {
            this.cache.get(parent.getClass()).forEach(this::unbind);
        }
    }
    
    public void unbind(final Listener listener) {
        this.registry.values().forEach(list -> list.remove(listener));
    }
    
    public <E> E dispatch(final E event) {
        final List<Listener> list = this.registry.get(event.getClass());
        if (list != null) {
            list.forEach(listener -> listener.run(event));
        }
        return event;
    }
}
