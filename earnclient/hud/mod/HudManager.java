package earnclient.hud.mod;

import earnclient.hud.mod.impl.*;
import java.util.*;

public class HudManager
{
    public ArrayList<HudMod> hudMods;
    public static ToggleSprintMod toggleSprint;
    public static FPSMod fps;
    public static Animations animations;
    public static SmallItems smallItems;
    public static FullBright fullBright;
    public static Keystrokes keystrokes;
    public static Chat chat;
    
    public HudManager() {
        (this.hudMods = new ArrayList<HudMod>()).add(HudManager.toggleSprint = new ToggleSprintMod());
        this.hudMods.add(HudManager.fps = new FPSMod());
        this.hudMods.add(HudManager.animations = new Animations());
        this.hudMods.add(HudManager.smallItems = new SmallItems());
        this.hudMods.add(HudManager.fullBright = new FullBright());
        this.hudMods.add(HudManager.keystrokes = new Keystrokes());
        this.hudMods.add(HudManager.chat = new Chat());
    }
    
    public void renderMods() {
        for (final HudMod m : this.hudMods) {
            if (m.isEnabled()) {
                m.draw();
            }
        }
    }
}
