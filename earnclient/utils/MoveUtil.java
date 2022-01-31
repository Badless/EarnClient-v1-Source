package earnclient.utils;

import net.minecraft.client.*;
import net.minecraft.util.*;
import net.minecraft.client.entity.*;
import earnclient.event.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;

public class MoveUtil
{
    private static Minecraft mc;
    
    static {
        MoveUtil.mc = Minecraft.getMinecraft();
    }
    
    public static void setJumpSpeed(final float multiplier) {
        if (MoveUtil.mc.thePlayer.isSprinting()) {
            final float f = MoveUtil.mc.thePlayer.rotationYaw * 0.017453292f;
            final float speed = 0.2f * multiplier;
            final EntityPlayerSP thePlayer = MoveUtil.mc.thePlayer;
            thePlayer.motionX -= MathHelper.sin(f) * speed;
            final EntityPlayerSP thePlayer2 = MoveUtil.mc.thePlayer;
            thePlayer2.motionZ += MathHelper.cos(f) * speed;
        }
        MoveUtil.mc.thePlayer.isAirBorne = true;
    }
    
    public static void setMoveSpeed(final MotionEvent event, final double speed) {
        double forward = MoveUtil.mc.thePlayer.movementInput.moveForward;
        double strafe = MoveUtil.mc.thePlayer.movementInput.moveStrafe;
        float yaw = MoveUtil.mc.thePlayer.rotationYaw;
        if (forward == 0.0 && strafe == 0.0) {
            event.setX(0.0);
            event.setZ(0.0);
        }
        else {
            if (forward != 0.0) {
                if (strafe > 0.0) {
                    yaw += ((forward > 0.0) ? -45 : 45);
                }
                else if (strafe < 0.0) {
                    yaw += ((forward > 0.0) ? 45 : -45);
                }
                strafe = 0.0;
                if (forward > 0.0) {
                    forward = 1.0;
                }
                else if (forward < 0.0) {
                    forward = -1.0;
                }
            }
            event.setX(forward * speed * -Math.sin(Math.toRadians(yaw)) + strafe * speed * Math.cos(Math.toRadians(yaw)));
            event.setZ(forward * speed * Math.cos(Math.toRadians(yaw)) - strafe * speed * -Math.sin(Math.toRadians(yaw)));
        }
    }
    
    public static void TP(final MotionEvent event, final double speed, final double y) {
        float yaw = MoveUtil.mc.thePlayer.rotationYaw;
        final float forward = MoveUtil.mc.thePlayer.moveForward;
        final float strafe = MoveUtil.mc.thePlayer.moveStrafing;
        yaw += ((forward < 0.0f) ? 180 : 0);
        if (strafe < 0.0f) {
            yaw += ((forward < 0.0f) ? -45 : ((forward == 0.0f) ? 90 : 45));
        }
        if (strafe > 0.0f) {
            yaw -= ((forward < 0.0f) ? -45 : ((forward == 0.0f) ? 90 : 45));
        }
        final float direction = yaw * 0.017453292f;
        final double posX = MoveUtil.mc.thePlayer.posX;
        final double posY = MoveUtil.mc.thePlayer.posY;
        final double posZ = MoveUtil.mc.thePlayer.posZ;
        final double raycastFirstX = -Math.sin(direction);
        final double raycastFirstZ = Math.cos(direction);
        final double raycastFinalX = raycastFirstX * speed;
        final double raycastFinalZ = raycastFirstZ * speed;
        MoveUtil.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(posX + raycastFinalX, posY + y, posZ + raycastFinalZ, MoveUtil.mc.thePlayer.onGround));
        MoveUtil.mc.thePlayer.setPosition(posX + raycastFinalX, posY + y, posZ + raycastFinalZ);
    }
    
    public static float getDirection() {
        float yaw = MoveUtil.mc.thePlayer.rotationYaw;
        final float forward = MoveUtil.mc.thePlayer.moveForward;
        final float strafe = MoveUtil.mc.thePlayer.moveStrafing;
        yaw += ((forward < 0.0f) ? 180 : 0);
        if (strafe < 0.0f) {
            yaw += ((forward < 0.0f) ? -45 : ((forward == 0.0f) ? 90 : 45));
        }
        if (strafe > 0.0f) {
            yaw -= ((forward < 0.0f) ? -45 : ((forward == 0.0f) ? 90 : 45));
        }
        return yaw * 0.017453292f;
    }
    
    public static double square(final double in) {
        return in * in;
    }
    
    public static double getSpeed() {
        return Math.hypot(MoveUtil.mc.thePlayer.motionX, MoveUtil.mc.thePlayer.motionZ);
    }
    
    public static void setSpeed(final double speed) {
        MoveUtil.mc.thePlayer.motionX = -MathHelper.sin(getDirection()) * speed;
        MoveUtil.mc.thePlayer.motionZ = MathHelper.cos(getDirection()) * speed;
    }
}
