package earnclient.ui.settings;

import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import earnclient.*;

public class SettingMod
{
    public Minecraft mc;
    public FontRenderer fr;
    public String name;
    public boolean enabled;
    public int x;
    public int y;
    
    public SettingMod(final String name, final int x, final int y) {
        this.mc = Minecraft.getMinecraft();
        this.fr = this.mc.fontRendererObj;
        this.enabled = true;
        this.name = name;
        try {
            this.setEnabled((boolean)EarnClient.settings.settings.get(String.valueOf(name.toLowerCase()) + " enabled"));
        }
        catch (NullPointerException e) {
            this.enabled = false;
        }
    }
    
    public int getWidth() {
        return 50;
    }
    
    public int getHeight() {
        return 50;
    }
    
    public void draw() {
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
