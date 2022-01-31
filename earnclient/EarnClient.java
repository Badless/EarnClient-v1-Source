package earnclient;

import net.minecraft.client.*;
import earnclient.event.bus.*;
import earnclient.mod.*;
import earnclient.hud.mod.*;
import earnclient.ui.settings.*;
import earnclient.anticheat.*;
import earnclient.utils.config.*;
import earnclient.http.gsonobjs.*;
import earnclient.utils.font.*;
import earnclient.http.*;
import earnclient.cosmetics.*;
import earnclient.event.impl.*;
import earnclient.ui.clickgui.*;
import net.minecraft.client.gui.*;
import earnclient.ui.gui.*;
import earnclient.event.*;

public class EarnClient
{
    public String NAME;
    public String VERSION;
    public String AUTHOR;
    public String NAMEVER;
    public static EarnClient INSTANCE;
    public Minecraft mc;
    public EventBus eventBus;
    public EventManager eventManager;
    public ModManager modManager;
    public HudManager hudManager;
    public SettingsManager settingsManager;
    public CheatEngineBlocker cheatEngineBlocker;
    public static Config config;
    public static Settings settings;
    private DiscordRP discordRP;
    private volatile boolean isBanned;
    public boolean isWhitelisted;
    public boolean isUpdated;
    private ObjGlobalSettings globalSettings;
    
    static {
        EarnClient.INSTANCE = new EarnClient();
    }
    
    public EarnClient() {
        this.NAME = "EarnClient";
        this.VERSION = "2.0.0";
        this.AUTHOR = "BadlessTV#6561";
        this.NAMEVER = String.valueOf(this.NAME) + " " + this.VERSION;
        this.mc = Minecraft.getMinecraft();
        this.eventBus = new EventBus();
        this.discordRP = new DiscordRP();
    }
    
    public void startup() {
        this.eventManager = new EventManager();
        EarnClient.config = new Config();
        EarnClient.settings = new Settings();
        EarnClient.config.loadModConfig();
        EarnClient.settings.loadModConfig();
        this.modManager = new ModManager();
        this.hudManager = new HudManager();
        this.settingsManager = new SettingsManager();
        this.cheatEngineBlocker = new CheatEngineBlocker();
        this.discordRP.start();
        System.out.println("Starting " + this.NAMEVER + " by " + this.AUTHOR);
        FontUtil.bootstrap();
        EventManager.register(this);
        HTTPFunctions.sendHWIDMap();
        this.isBanned = HTTPFunctions.isBanned();
        this.isWhitelisted = HTTPFunctions.isWhitelisted();
        this.isUpdated = HTTPFunctions.isUpdated();
        this.globalSettings = HTTPFunctions.downloadGlobalSettings();
        CosmeticController.downloadUserCosmetics();
    }
    
    public void shutdown() {
        System.out.println("Shutting down " + this.NAMEVER);
        EarnClient.config.saveModConfig();
        EarnClient.settings.saveModConfig();
        this.discordRP.shutdown();
        EventManager.unregister(this);
    }
    
    @EventTarget
    public void onTick(final ClientTick event) {
        if (this.mc.gameSettings.keyBindSprint.isPressed()) {
            this.modManager.toggleSprint.toggle();
        }
        if (this.mc.gameSettings.CLICK_GUI.isPressed()) {
            this.mc.displayGuiScreen(new ClickGUI());
        }
        if (this.isBanned && !(Minecraft.getMinecraft().currentScreen instanceof GuiHWIDBanned)) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiHWIDBanned("--reason--"));
        }
        if (this.globalSettings.isWhitelisted() && !this.isWhitelisted && !(Minecraft.getMinecraft().currentScreen instanceof GuiWhitelisted)) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiWhitelisted());
        }
        if (this.globalSettings.isUpdated() && !this.isUpdated && !(Minecraft.getMinecraft().currentScreen instanceof GuiHWIDBanned)) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiUpdated());
        }
    }
    
    public EventBus getEventBus() {
        return this.eventBus;
    }
    
    public DiscordRP getDiscordRP() {
        return this.discordRP;
    }
}
