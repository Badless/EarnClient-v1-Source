package earnclient.cosmetics;

import net.minecraft.client.model.*;
import net.minecraft.client.renderer.entity.*;

public class CosmeticModelBase extends ModelBase
{
    protected final ModelBiped playerModel;
    
    public CosmeticModelBase(final RenderPlayer player) {
        this.playerModel = player.getMainModel();
    }
}
