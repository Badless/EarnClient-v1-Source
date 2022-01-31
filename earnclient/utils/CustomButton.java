package earnclient.utils;

import net.minecraft.util.*;
import java.awt.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.audio.*;

public class CustomButton extends Gui
{
    protected int width;
    protected int height;
    protected int textureWidth;
    protected int textureHeight;
    protected int xPosition;
    protected int yPosition;
    public String function;
    protected ResourceLocation oldImageLocation;
    protected ResourceLocation imageLocation;
    protected ResourceLocation hoverLocation;
    protected String displayString;
    private boolean isImage;
    int hoverColor;
    int bkColor;
    int normalColor;
    int hoverBkColor;
    private boolean hovered;
    
    public CustomButton(final String buttonFunction, final int x, final int y, final int widthIn, final int heightIn, final int textureWidthIn, final int textureHeightIn, final String location, final String hoverLocation) {
        this.displayString = "";
        this.function = buttonFunction;
        this.xPosition = x;
        this.yPosition = y;
        this.width = widthIn;
        this.height = heightIn;
        this.textureWidth = textureWidthIn;
        this.textureHeight = textureHeightIn;
        this.imageLocation = new ResourceLocation(location);
        this.oldImageLocation = new ResourceLocation(location);
        this.hoverLocation = new ResourceLocation(hoverLocation);
        this.isImage = true;
    }
    
    public CustomButton(final String buttonFunction, final int x, final int y, final int widthIn, final int heightIn, final String buttonString, final Color hoverColor, final Color bkColor, final Color normalColor, final Color hoverBkColor) {
        this.oldImageLocation = null;
        this.imageLocation = null;
        this.hoverLocation = null;
        this.function = buttonFunction;
        this.xPosition = x;
        this.yPosition = y;
        this.width = widthIn;
        this.height = heightIn;
        this.displayString = buttonString;
        this.hoverColor = hoverColor.getRGB();
        this.bkColor = bkColor.getRGB();
        this.normalColor = normalColor.getRGB();
        this.hoverBkColor = hoverBkColor.getRGB();
        this.isImage = false;
    }
    
    public void drawButton(final Minecraft mc, final int mouseX, final int mouseY) {
        final FontRenderer fontrenderer = mc.fontRendererObj;
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.hovered = (mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height);
        final int i = this.getHoverState(this.hovered);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.blendFunc(770, 771);
        if (!this.isImage) {
            int p = this.bkColor;
            if (this.hovered) {
                p = this.hoverBkColor;
            }
            RoundedUtils.drawRoundedRect((float)this.xPosition, (float)this.yPosition, this.xPosition + (float)this.width, this.yPosition + (float)this.height, 3.0f, p);
            this.mouseDragged(mc, mouseX, mouseY);
            int j = this.normalColor;
            if (this.hovered) {
                j = this.hoverColor;
            }
            this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2 + 0.7f, (float)(this.yPosition + (this.height - 8) / 2), j);
        }
        else if (this.isImage) {
            this.mouseDragged(mc, mouseX, mouseY);
            this.imageLocation = this.oldImageLocation;
            if (this.hovered) {
                this.imageLocation = this.hoverLocation;
            }
            mc.getTextureManager().bindTexture(this.imageLocation);
            Gui.drawModalRectWithCustomSizedTexture((float)this.xPosition, (float)this.yPosition, 0.0f, 0.0f, this.width, this.height, (float)this.textureWidth, (float)this.textureHeight);
        }
    }
    
    protected void mouseDragged(final Minecraft mc, final int mouseX, final int mouseY) {
    }
    
    public void mouseReleased(final int mouseX, final int mouseY) {
    }
    
    public boolean mousePressed(final Minecraft mc, final int mouseX, final int mouseY) {
        return mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
    }
    
    public boolean isMouseOver() {
        return this.hovered;
    }
    
    public void drawButtonForegroundLayer(final int mouseX, final int mouseY) {
    }
    
    public void playPressSound(final SoundHandler soundHandlerIn) {
        soundHandlerIn.playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0f));
    }
    
    public int getButtonWidth() {
        return this.width;
    }
    
    public void setWidth(final int width) {
        this.width = width;
    }
    
    protected int getHoverState(final boolean mouseOver) {
        int i = 0;
        if (mouseOver) {
            i = 1;
        }
        return i;
    }
}
