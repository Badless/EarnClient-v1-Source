package earnclient.ui.gui;

import java.util.*;
import java.util.Timer;

import net.minecraft.util.*;
import net.minecraft.event.*;
import java.io.*;
import net.minecraft.client.gui.*;

public class GuiUpdated extends GuiScreen
{
    private IChatComponent[] message;
    private int messageLengthTimesFontHeight;
    private int secondsLeft;
    private static Timer timer;
    private GuiButton refreshButton;
    
    static {
        GuiUpdated.timer = new Timer();
    }
    
    public GuiUpdated() {
        this.secondsLeft = 0;
        this.message = new IChatComponent[] { new ChatComponentText("Hi,"), new ChatComponentText(""), new ChatComponentText("Thank you for using EarnClient!"), new ChatComponentText(""), new ChatComponentText("Currently the client is updated!"), new ChatComponentText(""), new ChatComponentText("You can download latest version on discord server!"), new ChatComponentText(""), new ChatComponentText("https://earnclient.tk/discord").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GOLD).setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://earnclient.tk/discord")).setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("Click to open link!").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN))))), new ChatComponentText("") };
    }
    
    @Override
    protected void keyTyped(final char typedChar, final int keyCode) throws IOException {
    }
    
    @Override
    public void initGui() {
        this.buttonList.clear();
        this.messageLengthTimesFontHeight = this.message.length * this.fontRendererObj.FONT_HEIGHT;
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height - 25, 200, 20, EnumChatFormatting.RED + "Close"));
    }
    
    @Override
    protected void actionPerformed(final GuiButton button) throws IOException {
        if (button.id == 0) {
            this.mc.shutdown();
        }
    }
    
    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.drawDefaultBackground();
        int i = this.height / 2 - this.messageLengthTimesFontHeight / 2;
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
        int i = this.height / 2 - this.messageLengthTimesFontHeight / 2;
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
}
