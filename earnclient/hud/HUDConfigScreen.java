package earnclient.hud;

import net.minecraft.client.gui.*;
import earnclient.*;
import earnclient.hud.mod.*;
import java.util.*;

public class HUDConfigScreen extends GuiScreen
{
    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.drawDefaultBackground();
        for (final HudMod m : EarnClient.INSTANCE.hudManager.hudMods) {
            if (m.isEnabled()) {
                m.renderDummy(mouseX, mouseY);
            }
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
