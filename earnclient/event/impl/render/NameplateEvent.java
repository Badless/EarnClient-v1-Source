package earnclient.event.impl.render;

import earnclient.event.cancelable.*;
import net.minecraft.entity.*;

public class NameplateEvent extends CancelableEvent
{
    private Entity entity;
    
    public NameplateEvent(final Entity entity) {
        this.entity = entity;
    }
    
    public Entity getEntity() {
        return this.entity;
    }
}
