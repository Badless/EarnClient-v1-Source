package earnclient.ui.gui;

import net.minecraft.util.*;
import net.minecraft.event.*;
import java.io.*;
import earnclient.http.*;
import java.util.*;
import java.util.Timer;

import earnclient.*;
import net.minecraft.client.gui.*;

public class GuiWhitelisted extends GuiScreen
{
    private IChatComponent[] message;
    private int messageLengthTimesFontHeight;
    private int secondsLeft;
    private static Timer timer;
    private GuiButton refreshButton;
    
    static {
        GuiWhitelisted.timer = new Timer();
    }
    
    public GuiWhitelisted() {
        this.secondsLeft = 0;
        this.message = new IChatComponent[] { new ChatComponentText("Hi,"), new ChatComponentText(""), new ChatComponentText("Thank you for using EarnClient!"), new ChatComponentText(""), new ChatComponentText("Currently the client is whitelisted for Beta Testing."), new ChatComponentText(""), new ChatComponentText("If you are asked for your HWID, it is displayed below:"), new ChatComponentText(""), new ChatComponentText(HWID.getHWID()).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GOLD).setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("Click to copy your HWID!").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN)))).setChatClickEvent(new ClickEvent(ClickEvent.Action.COPY_CLIPBOARD, HWID.getHWID()))), new ChatComponentText("") };
    }
    
    @Override
    protected void keyTyped(final char typedChar, final int keyCode) throws IOException {
    }
    
    @Override
    public void initGui() {
        this.buttonList.clear();
        this.messageLengthTimesFontHeight = this.message.length * this.fontRendererObj.FONT_HEIGHT;
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height - 25, 98, 20, EnumChatFormatting.RED + "Close"));
        this.buttonList.add(this.refreshButton = new GuiButton(1, this.width / 2 + 2, this.height - 25, 98, 20, "Refresh"));
    }
    
    @Override
    protected void actionPerformed(final GuiButton button) throws IOException {
        if (button.id == 0) {
            this.mc.shutdown();
        }
        if (button.id == 1 && this.secondsLeft == 0) {
            final boolean isWhitelisted = HTTPFunctions.isWhitelisted();
            if (!isWhitelisted) {
                this.secondsLeft = 6;
                GuiWhitelisted.timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        final GuiWhitelisted this$0 = GuiWhitelisted.this;
                        GuiWhitelisted.access$1(this$0, this$0.secondsLeft - 1);
                        if (GuiWhitelisted.this.secondsLeft == 0) {
                            GuiWhitelisted.this.refreshButton.displayString = "Refresh Again";
                            GuiWhitelisted.this.refreshButton.enabled = true;
                            this.cancel();
                            return;
                        }
                        GuiWhitelisted.this.refreshButton.enabled = false;
                        GuiWhitelisted.this.refreshButton.displayString = new StringBuilder().append(EnumChatFormatting.AQUA).append(GuiWhitelisted.this.secondsLeft).append("...").toString();
                    }
                }, 0L, 1000L);
            }
            else {
                EarnClient.INSTANCE.isWhitelisted = true;
                this.mc.displayGuiScreen(null);
            }
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
    
    static /* synthetic */ void access$1(final GuiWhitelisted guiWhitelisted, final int secondsLeft) {
        guiWhitelisted.secondsLeft = secondsLeft;
    }
}
