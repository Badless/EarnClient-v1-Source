package earnclient.ui.settings;

import earnclient.ui.settings.impl.*;
import java.util.*;

public class SettingsManager
{
    public ArrayList<SettingMod> settingMods;
    public static ChatBackground chatBackground;
    public static FPSBackground fpsBackground;
    
    public SettingsManager() {
        (this.settingMods = new ArrayList<SettingMod>()).add(SettingsManager.chatBackground = new ChatBackground());
        this.settingMods.add(SettingsManager.fpsBackground = new FPSBackground());
    }
    
    public void renderMods() {
        for (final SettingMod s : this.settingMods) {
            if (s.isEnabled()) {
                s.draw();
            }
        }
    }
}
