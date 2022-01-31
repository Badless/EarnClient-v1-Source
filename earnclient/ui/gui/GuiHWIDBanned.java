package earnclient.ui.gui;

import net.minecraft.util.*;
import net.minecraft.event.*;
import earnclient.http.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.gui.*;
import java.io.*;

public class GuiHWIDBanned extends GuiScreen
{
    private IChatComponent[] message;
    private int messageLengthTimesFontHeight;
    
    public GuiHWIDBanned(final String reason) {
        this.message = new IChatComponent[] { new ChatComponentText(new StringBuilder().append(EnumChatFormatting.GOLD).append(EnumChatFormatting.BOLD).append("You have been HWID banned from EarnClient!").toString()), new ChatComponentText(""), new ChatComponentText("You have been banned for: "), new ChatComponentText(""), new ChatComponentText(EnumChatFormatting.AQUA + "reason"), new ChatComponentText(""), new ChatComponentText("You can appeal your ban at " + EnumChatFormatting.YELLOW + "https://earnclient.tk/").setChatStyle(new ChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://earnclient.tk/")).setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("Click to open link!").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN))))), new ChatComponentText(""), new ChatComponentText("Your HWID is: " + EnumChatFormatting.RED + HWID.getHWID()).setChatStyle(new ChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("Click to copy your HWID!").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN)))).setChatClickEvent(new ClickEvent(ClickEvent.Action.COPY_CLIPBOARD, HWID.getHWID()))), new ChatComponentText("") };
    }
    
    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height - 30, "Close"));
        this.messageLengthTimesFontHeight = this.message.length * this.fontRendererObj.FONT_HEIGHT;
    }
    
    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.drawGradientRect(0, 0, this.width, this.height, -12574688, -11530224);
        GL11.glPushMatrix();
        GL11.glTranslated((double)(this.width / 2 - 98), (double)(this.height / 2 - 60), 0.0);
        GL11.glScaled(0.4, 0.4, 0.0);
        GL11.glPopMatrix();
        int i = 50 - this.messageLengthTimesFontHeight / 2;
        IChatComponent[] message;
        for (int length = (message = this.message).length, j = 0; j < length; ++j) {
            final IChatComponent s = message[j];
            this.drawCenteredString(this.fontRendererObj, s.getFormattedText(), (float)(this.width / 2), (float)i, 16777215);
            i += this.fontRendererObj.FONT_HEIGHT;
        }
        this.handleComponentHover(this.findChatComponent(mouseX, mouseY), mouseX, mouseY);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    private IChatComponent findChatComponentLine(final int mouseY) {
        int i = 50 - this.messageLengthTimesFontHeight / 2;
        IChatComponent[] message;
        for (int length = (message = this.message).length, j = 0; j < length; ++j) {
            final IChatComponent s = message[j];
            final int yTop = i;
            final int yBottom = i + this.fontRendererObj.FONT_HEIGHT;
            if (mouseY >= yTop && mouseY < yBottom) {
                return s;
            }
            i += this.fontRendererObj.FONT_HEIGHT;
        }
        return null;
    }
    
    private IChatComponent findChatComponent(final int mouseX, final int mouseY) {
        final IChatComponent s = this.findChatComponentLine(mouseY);
        if (s == null || !(s instanceof ChatComponentText)) {
            return null;
        }
        final int stringWidth = this.mc.fontRendererObj.getStringWidth(GuiUtilRenderComponents.func_178909_a(((ChatComponentText)s).getChatComponentText_TextValue(), false));
        final int xLeft = this.width / 2 - stringWidth / 2;
        final int xRight = this.width / 2 + stringWidth / 2;
        if (mouseX >= xLeft && mouseX < xRight) {
            return s;
        }
        return null;
    }
    
    @Override
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        if (mouseButton == 0) {
            final IChatComponent ichatcomponent = this.findChatComponent(mouseX, mouseY);
            if (this.handleComponentClick(ichatcomponent)) {
                return;
            }
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
    
    @Override
    protected void keyTyped(final char typedChar, final int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
    }
    
    @Override
    protected void actionPerformed(final GuiButton button) throws IOException {
        this.mc.shutdown();
    }
    
    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
    }
}
