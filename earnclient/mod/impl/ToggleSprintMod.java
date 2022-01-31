package earnclient.mod.impl;

import earnclient.mod.*;
import earnclient.event.impl.*;
import earnclient.hud.mod.*;
import net.minecraft.potion.*;
import earnclient.event.*;

public class ToggleSprintMod extends Mod
{
    private boolean sprintingToggled;
    
    public ToggleSprintMod() {
        super("ToggleSprint", "Toggle Sprint Mod!", Category.MISC);
        this.sprintingToggled = false;
    }
    
    @EventTarget
    public void onUpdate(final EventUpdate event) {
        final float f = 0.8f;
        if (HudManager.toggleSprint.isEnabled() && !this.mc.thePlayer.movementInput.sneak && (this.mc.thePlayer.getFoodStats().getFoodLevel() > 6.0f || this.mc.thePlayer.capabilities.allowFlying) && !this.mc.thePlayer.isPotionActive(Potion.blindness) && this.mc.thePlayer.movementInput.moveForward >= f && !this.mc.thePlayer.isSprinting() && !this.mc.thePlayer.isUsingItem() && !this.mc.thePlayer.isCollidedHorizontally) {
            this.mc.thePlayer.setSprinting(true);
        }
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        this.mc.thePlayer.setSprinting(false);
    }
}
