package earnclient.utils.config;

import earnclient.*;
import earnclient.ui.settings.*;
import java.io.*;
import java.util.*;

public class Settings
{
    public File settingFolder;
    public File settingsFolder;
    public Configuration settings;
    public Configuration settingsToSave;
    
    public Settings() {
        this.settingFolder = new File("EarnClient");
        this.settingsFolder = new File("EarnClient/Settings");
        this.settingsToSave = ConfigurationAPI.newConfiguration(new File("EarnClient/Settings/Settings.json"));
    }
    
    public void saveModConfig() {
        if (!this.settingFolder.exists()) {
            this.settingFolder.mkdir();
        }
        if (!this.settingsFolder.exists()) {
            this.settingsFolder.mkdir();
        }
        System.out.println("Saving mod Config");
        for (final SettingMod s : EarnClient.INSTANCE.settingsManager.settingMods) {
            this.settingsToSave.set(String.valueOf(s.name.toLowerCase()) + " enabled", s.isEnabled());
        }
        try {
            this.settingsToSave.save();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void loadModConfig() {
        try {
            this.settings = ConfigurationAPI.loadExistingConfiguration(new File("EarnClient/Settings/Settings.json"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
