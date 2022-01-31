package earnclient.ui;

import net.minecraft.util.*;
import earnclient.utils.font.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.gui.*;

public class MainMenu extends GuiScreen
{
    @Override
    public void initGui() {
    }
    
    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        final ScaledResolution sr = new ScaledResolution(this.mc);
        this.mc.getTextureManager().bindTexture(new ResourceLocation("earnclient/mainmenu.png"));
        Gui.drawModalRectWithCustomSizedTexture(0.0f, 0.0f, 0.0f, 0.0f, this.width, this.height, (float)this.width, (float)this.height);
        this.drawGradientRect(0, this.height - 100, this.width, this.height, 0, -16777216);
        final String[] buttons = { "Singleplayer", "   Multiplayer", "Settings", "Language", "Quit" };
        int count = 0;
        String[] array;
        for (int length = (array = buttons).length, i = 0; i < length; ++i) {
            final String name = array[i];
            FontUtil.normal.drawCenteredString(name, this.width / buttons.length * count + this.width / buttons.length / 2.0f + 10.0f, (float)(this.height - 20), -1);
            ++count;
        }
        GlStateManager.pushMatrix();
        GlStateManager.translate(this.width / 2.0f, this.height / 2.0f, 0.0f);
        GlStateManager.scale(3.0f, 3.0f, 1.0f);
        GlStateManager.translate(-(this.width / 2.0f), -(this.height / 2.0f - this.mc.fontRendererObj.FONT_HEIGHT / 2.0f), 0.0f);
        this.drawCenteredString(this.mc.fontRendererObj, "EarnClient", this.width / 2.0f, this.height / 2.3f - this.mc.fontRendererObj.FONT_HEIGHT / 2.0f, -1);
        GlStateManager.popMatrix();
    }
    
    public void mouseClicked(final int mouseX, final int mouxeY, final int button) {
        final String[] buttons = { "Singleplayer", "Multiplayer", "Settings", "Language", "Quit" };
        int count = 0;
        String[] array;
        for (int length = (array = buttons).length, i = 0; i < length; ++i) {
            final String name = array[i];
            final float x = this.width / buttons.length * count + this.width / buttons.length / 2.0f + 0.0f - this.mc.fontRendererObj.getStringWidth(name) / 2.0f;
            final float y = (float)(this.height - 20);
            if (mouseX >= x && mouxeY >= y && mouseX < x + this.mc.fontRendererObj.getStringWidth(name) && mouxeY < y + this.mc.fontRendererObj.FONT_HEIGHT) {
                final String s;
                switch (s = name) {
                    case "Multiplayer": {
                        this.mc.displayGuiScreen(new GuiMultiplayer(this));
                        break;
                    }
                    case "Language": {
                        this.mc.displayGuiScreen(new GuiLanguage(this, this.mc.gameSettings, this.mc.getLanguageManager()));
                        break;
                    }
                    case "Singleplayer": {
                        this.mc.displayGuiScreen(new GuiSelectWorld(this));
                        break;
                    }
                    case "Quit": {
                        this.mc.shutdown();
                        break;
                    }
                    case "Settings": {
                        this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
                        break;
                    }
                    default:
                        break;
                }
            }
            ++count;
        }
    }
    
    @Override
    public void onGuiClosed() {
    }
}
