package earnclient.hud.mod;

import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import earnclient.hud.*;
import earnclient.*;
import java.awt.*;

public class HudMod
{
    public Minecraft mc;
    public FontRenderer fr;
    public String name;
    public boolean enabled;
    public DraggableComponent drag;
    public int x;
    public int y;
    
    public HudMod(final String name, final int x, final int y) {
        this.mc = Minecraft.getMinecraft();
        this.fr = this.mc.fontRendererObj;
        this.enabled = true;
        this.name = name;
        try {
            this.x = (int)EarnClient.config.config.get(String.valueOf(name.toLowerCase()) + " x");
            this.y = (int)EarnClient.config.config.get(String.valueOf(name.toLowerCase()) + " y");
            this.setEnabled((boolean)EarnClient.config.config.get(String.valueOf(name.toLowerCase()) + " enabled"));
        }
        catch (NullPointerException e) {
            this.x = x;
            this.y = y;
            this.enabled = false;
        }
        this.drag = new DraggableComponent(this.x, this.y, this.getWidth(), this.getHeight(), new Color(0, 0, 0, 0).getRGB());
    }
    
    public int getWidth() {
        return 50;
    }
    
    public int getHeight() {
        return 50;
    }
    
    public void draw() {
    }
    
    public void renderDummy(final int mouseX, final int mouseY) {
        this.drag.draw(mouseX, mouseY);
    }
    
    public int getX() {
        return this.drag.getxPosition();
    }
    
    public int getY() {
        return this.drag.getyPosition();
    }
    
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }
    
    public void toggle() {
        this.setEnabled(!this.enabled);
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
}
