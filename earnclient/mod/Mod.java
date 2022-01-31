package earnclient.mod;

import net.minecraft.client.*;
import earnclient.*;
import earnclient.event.*;

public class Mod
{
    public Minecraft mc;
    public String name;
    public String description;
    public boolean enabled;
    public Category category;
    
    public Mod(final String name, final String description, final Category category) {
        this.mc = Minecraft.getMinecraft();
        this.name = name;
        this.description = description;
        this.category = category;
        this.enabled = true;
    }
    
    public void onEnable() {
        final EventManager eventManager = EarnClient.INSTANCE.eventManager;
        EventManager.register(this);
    }
    
    public void onDisable() {
        final EventManager eventManager = EarnClient.INSTANCE.eventManager;
        EventManager.unregister(this);
    }
    
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
        if (enabled) {
            this.onEnable();
        }
        else {
            this.onDisable();
        }
    }
    
    public void toggle() {
        this.setEnabled(!this.enabled);
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public Category getCategory() {
        return this.category;
    }
}
