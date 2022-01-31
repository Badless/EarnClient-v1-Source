package earnclient.ui.clickgui.comp;

import earnclient.hud.mod.*;
import net.minecraft.client.*;
import earnclient.utils.font.*;
import java.awt.*;
import earnclient.utils.*;
import net.minecraft.client.gui.*;

public class ModButton
{
    public int x;
    public int y;
    public int w;
    public int h;
    public HudMod m;
    public Minecraft mc;
    
    public ModButton(final int x, final int y, final int w, final int h, final HudMod m) {
        this.mc = Minecraft.getMinecraft();
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.m = m;
    }
    
    public void draw() {
        final ScaledResolution sr = new ScaledResolution(this.mc);
        FontUtil.normal.drawString(this.m.name, this.x, (float)(this.y - 20), -1);
        RoundedUtils.drawSmoothRoundedRect((float)(this.x - 5), (float)(this.y - 25), (float)(this.x + 80), (float)(this.y + 25), 5.0f, new Color(24, 24, 24, 80).getRGB());
        Gui.drawRect(this.x, this.y, this.x + this.w, this.y + this.h, new Color(0, 0, 0, 0).getRGB());
        RoundedUtils.drawSmoothRoundedRect((float)(this.x + 15), (float)this.y, (float)(this.x + this.w + 16), (float)(this.y + this.h), (float)this.h, new Color(64, 64, 64, 255).getRGB());
        RoundedUtils.drawRoundedOutline((float)(this.x + 15), (float)this.y, (float)(this.x + this.w + 16), (float)(this.y + this.h), (float)this.h, 2.0f, new Color(32, 32, 32, 255).getRGB());
        if (this.m.isEnabled()) {
            FontUtil.normal.drawString("enabled!", this.x + 18, (float)(this.y + 2), new Color(0, 255, 0, 255).getRGB());
        }
        else {
            FontUtil.normal.drawString("disabled!", this.x + 17, (float)(this.y + 2), new Color(255, 0, 0, 255).getRGB());
        }
    }
    
    public void onClick(final int mouseX, final int mouseY, final int button) {
        if (mouseX >= this.x && mouseX <= this.x + this.w && mouseY >= this.y && mouseY <= this.y + this.h) {
            if (this.m.isEnabled()) {
                this.m.setEnabled(false);
                System.out.println(String.valueOf(this.m.name) + " | disabled!");
            }
            else {
                this.m.setEnabled(true);
                System.out.println(String.valueOf(this.m.name) + " | enabled!");
            }
        }
    }
}
