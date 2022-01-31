package earnclient.utils.config;

import earnclient.*;
import earnclient.hud.mod.*;
import java.io.*;
import java.util.*;

public class Config
{
    public File configFolder;
    public File modsFolder;
    public Configuration config;
    public Configuration configToSave;
    
    public Config() {
        this.configFolder = new File("EarnClient");
        this.modsFolder = new File("EarnClient/Mods");
        this.configToSave = ConfigurationAPI.newConfiguration(new File("EarnClient/Mods/ModConfig.json"));
    }
    
    public void saveModConfig() {
        if (!this.configFolder.exists()) {
            this.configFolder.mkdir();
        }
        if (!this.modsFolder.exists()) {
            this.modsFolder.mkdir();
        }
        System.out.println("Saving mod Config");
        for (final HudMod m : EarnClient.INSTANCE.hudManager.hudMods) {
            this.configToSave.set(String.valueOf(m.name.toLowerCase()) + " x", m.getX());
            this.configToSave.set(String.valueOf(m.name.toLowerCase()) + " y", m.getY());
            this.configToSave.set(String.valueOf(m.name.toLowerCase()) + " enabled", m.isEnabled());
        }
        try {
            this.configToSave.save();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void loadModConfig() {
        try {
            this.config = ConfigurationAPI.loadExistingConfiguration(new File("EarnClient/Mods/ModConfig.json"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
