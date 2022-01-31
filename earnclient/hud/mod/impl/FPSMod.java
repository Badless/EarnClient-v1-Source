package earnclient.hud.mod.impl;

import earnclient.ui.settings.*;
import earnclient.hud.mod.*;
import net.minecraft.client.*;
import java.awt.*;
import net.minecraft.client.gui.*;

public class FPSMod extends HudMod
{
    public FPSMod() {
        super("FPS", 50, 100);
    }
    
    @Override
    public void draw() {
        if (SettingsManager.fpsBackground.isEnabled() && HudManager.fps.isEnabled()) {
            if (Minecraft.getDebugFPS() >= 48) {
                Gui.drawRect(this.getX() - 2, this.getY() - 2, this.getX() + this.getWidth(), this.getY() + this.getHeight(), new Color(0, 0, 0, 150).getRGB());
                this.fr.drawStringWithShadow("§8[§6FPS§7: §a" + Minecraft.getDebugFPS() + "§8]", (float)this.getX(), (float)this.getY(), -1);
            }
            else {
                Gui.drawRect(this.getX() - 2, this.getY() - 2, this.getX() + this.getWidth(), this.getY() + this.getHeight(), new Color(0, 0, 0, 150).getRGB());
                this.fr.drawStringWithShadow("§8[§6FPS§7: §c" + Minecraft.getDebugFPS() + "§8]", (float)this.getX(), (float)this.getY(), -1);
            }
        }
        else if (Minecraft.getDebugFPS() >= 48) {
            this.fr.drawStringWithShadow("§8[§6FPS§7: §a" + Minecraft.getDebugFPS() + "§8]", (float)this.getX(), (float)this.getY(), -1);
        }
        else {
            this.fr.drawStringWithShadow("§8[§6FPS§7: §c" + Minecraft.getDebugFPS() + "§8]", (float)this.getX(), (float)this.getY(), -1);
        }
        super.draw();
    }
    
    @Override
    public void renderDummy(final int mouseX, final int mouseY) {
        this.fr.drawStringWithShadow("§8[§6FPS§7: §a69§8]", (float)this.getX(), (float)this.getY(), -1);
        super.renderDummy(mouseX, mouseY);
    }
    
    @Override
    public int getWidth() {
        return this.fr.getStringWidth("§8[§6FPS§7: §a" + Minecraft.getDebugFPS() + "§8]");
    }
    
    @Override
    public int getHeight() {
        return this.fr.FONT_HEIGHT;
    }
}
