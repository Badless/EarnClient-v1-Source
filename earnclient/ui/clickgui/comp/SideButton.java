package earnclient.ui.clickgui.comp;

import net.minecraft.util.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.gui.*;

public class SideButton extends GuiButton
{
    ResourceLocation drawImage;
    int boxR;
    int boxG;
    int boxB;
    int boxA;
    
    public SideButton(final int buttonId, final int x, final int y, final int widthIn, final int heightIn, final String buttonText, final ResourceLocation image) {
        super(buttonId, x, y, widthIn, heightIn, buttonText);
        this.boxR = 255;
        this.boxG = 255;
        this.boxB = 255;
        this.boxA = 255;
        this.drawImage = image;
    }
    
    @Override
    public void drawButton(final Minecraft mc, final int mouseX, final int mouseY) {
        this.mouseDragged(mc, mouseX, mouseY);
        if (!this.enabled) {
            this.boxA = 255;
        }
        else if (this.hovered) {
            this.boxA = 15;
        }
        final ScaledResolution sr = new ScaledResolution(mc);
        GlStateManager.color((float)this.boxR, (float)this.boxG, (float)this.boxG, (float)this.boxA);
        mc.getTextureManager().bindTexture(this.drawImage);
        Gui.drawModalRectWithCustomSizedTexture((float)this.xPosition, (float)this.yPosition, 0.0f, 0.0f, this.width, this.height, (float)this.width, (float)this.height);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.hovered = (mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height);
        GlStateManager.color(255.0f, 255.0f, 255.0f, 60.0f);
    }
}
