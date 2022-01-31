package earnclient.ui.clickgui;

import earnclient.*;
import earnclient.hud.mod.*;
import net.minecraft.util.*;
import earnclient.ui.clickgui.comp.*;
import java.awt.*;
import earnclient.utils.*;
import java.util.*;
import java.io.*;
import net.minecraft.client.gui.*;
import earnclient.hud.*;
import earnclient.ui.settings.impl.gui.*;

public class ClickGUI extends GuiScreen
{
    ArrayList<ModButton> modButtons;
    
    public ClickGUI() {
        this.modButtons = new ArrayList<ModButton>();
    }
    
    @Override
    public void initGui() {
        final ScaledResolution sr = new ScaledResolution(this.mc);
        super.initGui();
        final ArrayList<ModButton> modButtons = this.modButtons;
        final int x = sr.getScaledWidth() / 2 - 160;
        final int y = sr.getScaledHeight() / 2 - 85;
        final int w = 42;
        final int h = this.mc.fontRendererObj.FONT_HEIGHT + 5;
        final HudManager hudManager = EarnClient.INSTANCE.hudManager;
        modButtons.add(new ModButton(x, y, w, h, HudManager.toggleSprint));
        final ArrayList<ModButton> modButtons2 = this.modButtons;
        final int x2 = sr.getScaledWidth() / 2 - 65;
        final int y2 = sr.getScaledHeight() / 2 - 85;
        final int w2 = 42;
        final int h2 = this.mc.fontRendererObj.FONT_HEIGHT + 5;
        final HudManager hudManager2 = EarnClient.INSTANCE.hudManager;
        modButtons2.add(new ModButton(x2, y2, w2, h2, HudManager.fps));
        final HudManager hudManager3 = EarnClient.INSTANCE.hudManager;
        if (HudManager.fps.isEnabled()) {
            this.buttonList.add(new SettingsButton(3, sr.getScaledWidth() / 2 - 10, sr.getScaledHeight() / 2 - 105, 20, 20, "Settings", new ResourceLocation("earnclient/settings.png")));
        }
        final ArrayList<ModButton> modButtons3 = this.modButtons;
        final int x3 = sr.getScaledWidth() / 2 + 30;
        final int y3 = sr.getScaledHeight() / 2 - 85;
        final int w3 = 42;
        final int h3 = this.mc.fontRendererObj.FONT_HEIGHT + 5;
        final HudManager hudManager4 = EarnClient.INSTANCE.hudManager;
        modButtons3.add(new ModButton(x3, y3, w3, h3, HudManager.animations));
        final ArrayList<ModButton> modButtons4 = this.modButtons;
        final int x4 = sr.getScaledWidth() / 2 + 30;
        final int y4 = sr.getScaledHeight() / 2 - 25;
        final int w4 = 42;
        final int h4 = this.mc.fontRendererObj.FONT_HEIGHT + 5;
        final HudManager hudManager5 = EarnClient.INSTANCE.hudManager;
        modButtons4.add(new ModButton(x4, y4, w4, h4, HudManager.smallItems));
        final ArrayList<ModButton> modButtons5 = this.modButtons;
        final int x5 = sr.getScaledWidth() / 2 - 160;
        final int y5 = sr.getScaledHeight() / 2 - 25;
        final int w5 = 42;
        final int h5 = this.mc.fontRendererObj.FONT_HEIGHT + 5;
        final HudManager hudManager6 = EarnClient.INSTANCE.hudManager;
        modButtons5.add(new ModButton(x5, y5, w5, h5, HudManager.fullBright));
        final ArrayList<ModButton> modButtons6 = this.modButtons;
        final int x6 = sr.getScaledWidth() / 2 - 65;
        final int y6 = sr.getScaledHeight() / 2 - 25;
        final int w6 = 42;
        final int h6 = this.mc.fontRendererObj.FONT_HEIGHT + 5;
        final HudManager hudManager7 = EarnClient.INSTANCE.hudManager;
        modButtons6.add(new ModButton(x6, y6, w6, h6, HudManager.keystrokes));
        final HudManager hudManager8 = EarnClient.INSTANCE.hudManager;
        if (HudManager.chat.isEnabled()) {
            this.buttonList.add(new SettingsButton(2, sr.getScaledWidth() / 2 - 105, sr.getScaledHeight() / 2 + 15, 20, 20, "Settings", new ResourceLocation("earnclient/settings.png")));
        }
        final ArrayList<ModButton> modButtons7 = this.modButtons;
        final int x7 = sr.getScaledWidth() / 2 - 160;
        final int y7 = sr.getScaledHeight() / 2 + 35;
        final int w7 = 42;
        final int h7 = this.mc.fontRendererObj.FONT_HEIGHT + 5;
        final HudManager hudManager9 = EarnClient.INSTANCE.hudManager;
        modButtons7.add(new ModButton(x7, y7, w7, h7, HudManager.chat));
    }
    
    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        final ScaledResolution sr = new ScaledResolution(this.mc);
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.buttonList.add(new SideButton(1, sr.getScaledWidth() / 2 + 140, sr.getScaledHeight() / 2 - 110, 30, 30, "Hud Positioning", new ResourceLocation("earnclient/hud-edit.png")));
        RoundedUtils.drawSmoothRoundedRect((float)(sr.getScaledWidth() / 2 - 190), (float)(sr.getScaledHeight() / 2 - 118), (float)(sr.getScaledWidth() / 2 + 140), (float)(sr.getScaledHeight() / 2 + 118), 50.0f, new Color(32, 32, 32, 180).getRGB());
        RoundedUtils.drawRoundedOutline((float)(sr.getScaledWidth() / 2 - 190), (float)(sr.getScaledHeight() / 2 - 118), (float)(sr.getScaledWidth() / 2 + 140), (float)(sr.getScaledHeight() / 2 + 118), 50.0f, 5.0f, new Color(32, 32, 32, 255).getRGB());
        for (final ModButton m : this.modButtons) {
            m.draw();
        }
    }
    
    @Override
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        for (final ModButton m : this.modButtons) {
            m.onClick(mouseX, mouseY, mouseButton);
        }
    }
    
    @Override
    protected void actionPerformed(final GuiButton button) throws IOException {
        super.actionPerformed(button);
        switch (button.id) {
            case 1: {
                this.mc.displayGuiScreen(new HUDConfigScreen());
                break;
            }
            case 2: {
                this.mc.displayGuiScreen(new ChatSettingGUI());
                break;
            }
            case 3: {
                this.mc.displayGuiScreen(new FPSSettingGUI());
                break;
            }
        }
    }
}
