package earnclient.utils;

import net.minecraft.client.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.util.*;

public class Printer
{
    public static void print(final String message) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentTranslation(ChatFormatting.GRAY + "[" + ChatFormatting.DARK_RED + "EarnClient" + ChatFormatting.GRAY + "]" + ChatFormatting.WHITE + " > " + message, new Object[0]));
    }
}
