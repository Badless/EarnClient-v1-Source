package earnclient.event.impl.render;

import earnclient.event.*;
import net.minecraft.client.gui.*;

public class Render2DEvent extends Event2
{
    private float partialTicks;
    private ScaledResolution scaledResolution;
    
    public Render2DEvent(final float partialTicks, final ScaledResolution scaledResolution) {
        this.partialTicks = partialTicks;
        this.scaledResolution = scaledResolution;
    }
    
    public float getPartialTicks() {
        return this.partialTicks;
    }
    
    public ScaledResolution getScaledResolution() {
        return this.scaledResolution;
    }
}
