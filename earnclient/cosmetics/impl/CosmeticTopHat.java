package earnclient.cosmetics.impl;

import net.minecraft.util.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.entity.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import net.minecraft.entity.*;
import earnclient.cosmetics.*;
import net.minecraft.client.model.*;

public class CosmeticTopHat extends CosmeticBase
{
    private final ModelTopHat modelTopHat;
    private static final ResourceLocation TEXTURE;
    
    static {
        TEXTURE = new ResourceLocation("earnclient/cosmetics/hat.png");
    }
    
    public CosmeticTopHat(final RenderPlayer renderPlayer) {
        super(renderPlayer);
        this.modelTopHat = new ModelTopHat(renderPlayer);
    }
    
    @Override
    public void render(final AbstractClientPlayer player, final float limbSwing, final float limbSwingAmount, final float partialTicks, final float ageInTicks, final float headYaw, final float headPitch, final float scale) {
        if (CosmeticController.shouldRenderTopHat(player)) {
            GlStateManager.pushMatrix();
            this.playerRenderer.bindTexture(CosmeticTopHat.TEXTURE);
            if (player.isSneaking()) {
                GL11.glTranslated(0.0, 0.225, 0.0);
            }
            final float[] color = CosmeticController.getTopHatColor(player);
            GL11.glColor3f(color[0], color[1], color[2]);
            this.modelTopHat.render(player, limbSwing, limbSwingAmount, ageInTicks, headYaw, headPitch, scale);
            GL11.glColor3f(1.0f, 1.0f, 1.0f);
            GL11.glPopMatrix();
        }
    }
    
    private class ModelTopHat extends CosmeticModelBase
    {
        private ModelRenderer rim;
        private ModelRenderer pointy;
        
        public ModelTopHat(final RenderPlayer player) {
            super(player);
            (this.rim = new ModelRenderer(this.playerModel, 0, 0)).addBox(-5.5f, -9.0f, -5.5f, 11, 2, 11);
            (this.pointy = new ModelRenderer(this.playerModel, 0, 13)).addBox(-3.5f, -17.0f, -3.5f, 7, 8, 7);
        }
        
        @Override
        public void render(final Entity entityIn, final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float headYaw, final float headPitch, final float scale) {
            this.rim.rotateAngleX = this.playerModel.bipedHead.rotateAngleX;
            this.rim.rotateAngleY = this.playerModel.bipedHead.rotateAngleY;
            this.rim.rotationPointX = 0.0f;
            this.rim.rotationPointY = 0.0f;
            this.rim.render(scale);
            this.pointy.rotateAngleX = this.playerModel.bipedHead.rotateAngleX;
            this.pointy.rotateAngleY = this.playerModel.bipedHead.rotateAngleY;
            this.pointy.rotationPointX = 0.0f;
            this.pointy.rotationPointY = 0.0f;
            this.pointy.render(scale);
        }
    }
}
