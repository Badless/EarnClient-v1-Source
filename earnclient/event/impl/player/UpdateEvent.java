package earnclient.event.impl.player;

import earnclient.event.cancelable.*;
import net.minecraft.client.*;

public class UpdateEvent extends CancelableEvent
{
    private boolean onGround;
    private float yaw;
    private float pitch;
    private double y;
    private boolean pre;
    
    public UpdateEvent(final float yaw, final float pitch, final double y, final boolean onGround, final boolean pre) {
        this.yaw = yaw;
        this.pitch = pitch;
        this.y = y;
        this.onGround = onGround;
        this.pre = pre;
    }
    
    public UpdateEvent(final float yaw, final float pitch, final double y, final boolean onGround) {
        this.yaw = yaw;
        this.pitch = pitch;
        this.y = y;
        this.onGround = onGround;
    }
    
    public float getYaw() {
        return this.yaw;
    }
    
    public float getPitch() {
        return this.pitch;
    }
    
    public double getY() {
        return this.y;
    }
    
    public boolean isOnGround() {
        return this.onGround;
    }
    
    public void setYaw(final float yaw) {
        Minecraft.getMinecraft().thePlayer.renderYawOffset = yaw;
        Minecraft.getMinecraft().thePlayer.rotationYawHead = yaw;
        this.yaw = yaw;
    }
    
    public void setPitch(final float pitch) {
        this.pitch = pitch;
    }
    
    public void setRotations(final float[] rotations) {
        this.setYaw(rotations[0]);
        this.setPitch(rotations[1]);
    }
    
    public void setY(final double y) {
        this.y = y;
    }
    
    public void setOnGround(final boolean onGround) {
        this.onGround = onGround;
    }
    
    public boolean isPre() {
        return this.pre;
    }
}
