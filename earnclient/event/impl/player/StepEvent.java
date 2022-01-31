package earnclient.event.impl.player;

import earnclient.event.cancelable.*;
import net.minecraft.entity.*;

public class StepEvent extends CancelableEvent
{
    private Entity entity;
    private float height;
    private boolean pre;
    
    public StepEvent(final Entity entity, final boolean pre) {
        this.entity = entity;
        this.height = entity.stepHeight;
        this.pre = pre;
    }
    
    public StepEvent(final Entity entity) {
        this.entity = entity;
        this.height = entity.stepHeight;
    }
    
    public Entity getEntity() {
        return this.entity;
    }
    
    public float getHeight() {
        return this.height;
    }
    
    public void setHeight(final float height) {
        this.height = height;
    }
    
    public boolean isPre() {
        return this.pre;
    }
}
