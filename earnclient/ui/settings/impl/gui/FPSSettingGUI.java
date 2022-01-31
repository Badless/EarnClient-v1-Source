package earnclient.ui.settings.impl.gui;

import earnclient.*;
import earnclient.hud.mod.*;
import earnclient.ui.settings.*;
import earnclient.hud.mod.impl.*;
import net.minecraft.util.*;
import earnclient.ui.clickgui.comp.*;
import java.awt.*;
import earnclient.utils.*;
import java.util.*;
import java.io.*;
import net.minecraft.client.gui.*;
import earnclient.hud.*;
import earnclient.ui.clickgui.*;

public class FPSSettingGUI extends GuiScreen
{
    ArrayList<SettingButton> settingButtons;
    
    public FPSSettingGUI() {
        this.settingButtons = new ArrayList<SettingButton>();
    }
    
    @Override
    public void initGui() {
        final ScaledResolution sr = new ScaledResolution(this.mc);
        super.initGui();
        final ArrayList<SettingButton> settingButtons = this.settingButtons;
        final int x = sr.getScaledWidth() / 2 - 160;
        final int y = sr.getScaledHeight() / 2 - 85;
        final int w = 42;
        final int h = this.mc.fontRendererObj.FONT_HEIGHT + 5;
        final HudManager hudManager = EarnClient.INSTANCE.hudManager;
        final FPSMod fps = HudManager.fps;
        final SettingsManager settingsManager = EarnClient.INSTANCE.settingsManager;
        settingButtons.add(new SettingButton(x, y, w, h, fps, SettingsManager.fpsBackground));
    }
    
    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        final ScaledResolution sr = new ScaledResolution(this.mc);
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.buttonList.add(new SideButton(1, sr.getScaledWidth() / 2 + 140, sr.getScaledHeight() / 2 - 110, 30, 30, "Hud Positioning", new ResourceLocation("earnclient/hud-edit.png")));
        this.buttonList.add(new SideButton(2, sr.getScaledWidth() / 2 + 140, sr.getScaledHeight() / 2 - 75, 30, 30, "Settings", new ResourceLocation("earnclient/earnclient.png")));
        RoundedUtils.drawSmoothRoundedRect((float)(sr.getScaledWidth() / 2 - 190), (float)(sr.getScaledHeight() / 2 - 118), (float)(sr.getScaledWidth() / 2 + 140), (float)(sr.getScaledHeight() / 2 + 118), 50.0f, new Color(32, 32, 32, 180).getRGB());
        RoundedUtils.drawRoundedOutline((float)(sr.getScaledWidth() / 2 - 190), (float)(sr.getScaledHeight() / 2 - 118), (float)(sr.getScaledWidth() / 2 + 140), (float)(sr.getScaledHeight() / 2 + 118), 50.0f, 5.0f, new Color(32, 32, 32, 255).getRGB());
        for (final SettingButton s : this.settingButtons) {
            s.draw();
        }
    }
    
    @Override
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        for (final SettingButton s : this.settingButtons) {
            s.onClick(mouseX, mouseY, mouseButton);
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
                this.mc.displayGuiScreen(new ClickGUI());
                break;
            }
        }
    }
}
