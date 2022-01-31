package earnclient.cosmetics;

import net.minecraft.client.renderer.entity.layers.*;
import net.minecraft.client.entity.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.entity.*;

public abstract class CosmeticBase implements LayerRenderer<AbstractClientPlayer>
{
    protected final RenderPlayer playerRenderer;
    
    public CosmeticBase(final RenderPlayer playerRenderer) {
        this.playerRenderer = playerRenderer;
    }
    
    @Override
    public void doRenderLayer(final AbstractClientPlayer player, final float limbSwing, final float limbSwingAmount, final float partialTicks, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scale) {
        if (player.hasPlayerInfo() && !player.isInvisible()) {
            this.render(player, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
        }
    }
    
    public abstract void render(final AbstractClientPlayer p0, final float p1, final float p2, final float p3, final float p4, final float p5, final float p6, final float p7);
    
    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
