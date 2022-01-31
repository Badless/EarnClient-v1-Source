package earnclient.utils;

import net.minecraft.client.*;
import net.minecraft.client.renderer.culling.*;
import java.nio.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.gui.*;
import org.lwjgl.*;
import org.lwjgl.util.glu.*;
import org.lwjgl.opengl.*;
import java.awt.*;
import net.minecraft.util.*;
import javax.vecmath.*;
import net.minecraft.entity.*;

public class RenderUtil
{
    private static final Minecraft mc;
    private static final Frustum frustrum;
    private static final IntBuffer viewport;
    private static final FloatBuffer modelview;
    private static final FloatBuffer projection;
    
    static {
        mc = Minecraft.getMinecraft();
        frustrum = new Frustum();
        viewport = GLAllocation.createDirectIntBuffer(16);
        modelview = GLAllocation.createDirectFloatBuffer(16);
        projection = GLAllocation.createDirectFloatBuffer(16);
    }
    
    public static double interpolate(final double current, final double old, final double scale) {
        return old + (current - old) * scale;
    }
    
    public static void renderTag(final String name, final double pX, final double pY, final double pZ, final int color) {
        float scale = (float)(RenderUtil.mc.thePlayer.getDistance(pX + RenderUtil.mc.getRenderManager().getRenderPosX(), pY + RenderUtil.mc.getRenderManager().getRenderPosY(), pZ + RenderUtil.mc.getRenderManager().getRenderPosZ()) / 8.0);
        if (scale < 1.6f) {
            scale = 1.6f;
        }
        scale /= 50.0f;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)pX, (float)pY + 1.4f, (float)pZ);
        GL11.glNormal3f(0.0f, 1.0f, 0.0f);
        GL11.glRotatef(-RenderUtil.mc.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(RenderUtil.mc.getRenderManager().playerViewX, 1.0f, 0.0f, 0.0f);
        GL11.glScalef(-scale, -scale, scale);
        GL11.glDisable(2896);
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        final int width = RenderUtil.mc.fontRendererObj.getStringWidth(name) / 2;
        GL11.glPushMatrix();
        GL11.glPopMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        Gui.drawRect(-width - 1, -(RenderUtil.mc.fontRendererObj.FONT_HEIGHT + 8), -width - 1 + 2 + width * 2, -(RenderUtil.mc.fontRendererObj.FONT_HEIGHT - 1), 0);
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        RenderUtil.mc.fontRendererObj.drawStringWithShadow(name, (float)(-width), (float)(-(RenderUtil.mc.fontRendererObj.FONT_HEIGHT + 7)), color);
        GL11.glScalef(1.0f, 1.0f, 1.0f);
        GlStateManager.enableTexture2D();
        GL11.glDisable(3042);
        GL11.glEnable(2896);
        GL11.glEnable(2929);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    public static void drawTexturedModalRect(final int x, final int y, final int u, final int v, final int width, final int height, final float zLevel) {
        final float var7 = 0.00390625f;
        final float var8 = 0.00390625f;
        final Tessellator tessellator = Tessellator.getInstance();
        final WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldRenderer.pos(x, y + height, zLevel).tex(u * 0.00390625f, (v + height) * 0.00390625f).endVertex();
        worldRenderer.pos(x + width, y + height, zLevel).tex((u + width) * 0.00390625f, (v + height) * 0.00390625f).endVertex();
        worldRenderer.pos(x + width, y, zLevel).tex((u + width) * 0.00390625f, v * 0.00390625f).endVertex();
        worldRenderer.pos(x, y, zLevel).tex(u * 0.00390625f, v * 0.00390625f).endVertex();
        tessellator.draw();
    }
    
    public static ScaledResolution getResolution() {
        return new ScaledResolution(RenderUtil.mc);
    }
    
    public static Vec3 to2D(final double x, final double y, final double z) {
        final FloatBuffer screenCoords = BufferUtils.createFloatBuffer(3);
        final IntBuffer viewport = BufferUtils.createIntBuffer(16);
        final FloatBuffer modelView = BufferUtils.createFloatBuffer(16);
        final FloatBuffer projection = BufferUtils.createFloatBuffer(16);
        GL11.glGetFloat(2982, modelView);
        GL11.glGetFloat(2983, projection);
        GL11.glGetInteger(2978, viewport);
        final boolean result = GLU.gluProject((float)x, (float)y, (float)z, modelView, projection, viewport, screenCoords);
        if (result) {
            return new Vec3(screenCoords.get(0), Display.getHeight() - screenCoords.get(1), screenCoords.get(2));
        }
        return null;
    }
    
    public static void drawArrow(float x, float y, final boolean up, final int hexColor) {
        GL11.glPushMatrix();
        GL11.glScaled(1.3, 1.3, 1.3);
        x /= (float)1.3;
        y /= (float)1.3;
        GL11.glEnable(2848);
        GL11.glDisable(3553);
        hexColor(hexColor);
        GL11.glLineWidth(2.0f);
        GL11.glBegin(1);
        GL11.glVertex2d((double)x, (double)(y + (up ? 4 : 0)));
        GL11.glVertex2d((double)(x + 3.0f), (double)(y + (up ? 0 : 4)));
        GL11.glEnd();
        GL11.glBegin(1);
        GL11.glVertex2d((double)(x + 3.0f), (double)(y + (up ? 0 : 4)));
        GL11.glVertex2d((double)(x + 6.0f), (double)(y + (up ? 4 : 0)));
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(2848);
        GL11.glPopMatrix();
    }
    
    public static void drawTracerPointer(final float x, final float y, final float size, final float widthDiv, final float heightDiv, final int color) {
        final boolean blend = GL11.glIsEnabled(3042);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        hexColor(color);
        GL11.glBegin(7);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)(x - size / widthDiv), (double)(y + size));
        GL11.glVertex2d((double)x, (double)(y + size / heightDiv));
        GL11.glVertex2d((double)(x + size / widthDiv), (double)(y + size));
        GL11.glVertex2d((double)x, (double)y);
        GL11.glEnd();
        GL11.glColor4f(0.0f, 0.0f, 0.0f, 0.8f);
        GL11.glBegin(2);
        GL11.glVertex2d((double)x, (double)y);
        GL11.glVertex2d((double)(x - size / widthDiv), (double)(y + size));
        GL11.glVertex2d((double)x, (double)(y + size / heightDiv));
        GL11.glVertex2d((double)(x + size / widthDiv), (double)(y + size));
        GL11.glVertex2d((double)x, (double)y);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        if (!blend) {
            GL11.glDisable(3042);
        }
        GL11.glDisable(2848);
    }
    
    public static void hexColor(final int hexColor) {
        final float red = (hexColor >> 16 & 0xFF) / 255.0f;
        final float green = (hexColor >> 8 & 0xFF) / 255.0f;
        final float blue = (hexColor & 0xFF) / 255.0f;
        final float alpha = (hexColor >> 24 & 0xFF) / 255.0f;
        GL11.glColor4f(red, green, blue, alpha);
    }
    
    public static void drawEntityESP(final double x, final double y, final double z, final double height, final double width, final Color color, final boolean outline) {
        GL11.glPushMatrix();
        GLUtil.setGLCap(3042, true);
        GLUtil.setGLCap(3553, false);
        GLUtil.setGLCap(2896, false);
        GLUtil.setGLCap(2929, false);
        GL11.glDepthMask(false);
        GL11.glLineWidth(1.8f);
        GL11.glBlendFunc(770, 771);
        GLUtil.setGLCap(2848, true);
        GL11.glDepthMask(true);
        BB(new AxisAlignedBB(x - width + 0.25, y + 0.1, z - width + 0.25, x + width - 0.25, y + height + 0.25, z + width - 0.25), new Color(color.getRed(), color.getGreen(), color.getBlue(), 120).getRGB());
        if (outline) {
            OutlinedBB(new AxisAlignedBB(x - width + 0.25, y + 0.1, z - width + 0.25, x + width - 0.25, y + height + 0.25, z + width - 0.25), 1.0f, color.getRGB());
        }
        GLUtil.revertAllCaps();
        GL11.glPopMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    public static int getRainbow(final int speed, final int offset, final float s) {
        float hue = (float)((System.currentTimeMillis() + offset) % speed);
        hue /= speed;
        return Color.getHSBColor(hue, s, 1.0f).getRGB();
    }
    
    public static float[] getRGBAs(final int rgb) {
        return new float[] { (rgb >> 16 & 0xFF) / 255.0f, (rgb >> 8 & 0xFF) / 255.0f, (rgb & 0xFF) / 255.0f, (rgb >> 24 & 0xFF) / 255.0f };
    }
    
    public static void drawImage(final ResourceLocation image, final float x, final float y, final int width, final int height) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        Minecraft.getMinecraft().getTextureManager().bindTexture(image);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0f, 0.0f, width, height, (float)width, (float)height);
    }
    
    public static void drawCircle(final float x, final float y, final float r, final int c) {
        final float f = (c >> 24 & 0xFF) / 255.0f;
        final float f2 = (c >> 16 & 0xFF) / 255.0f;
        final float f3 = (c >> 8 & 0xFF) / 255.0f;
        final float f4 = (c & 0xFF) / 255.0f;
        GL11.glPushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GL11.glEnable(2848);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glBegin(6);
        for (int i = 0; i <= 360; ++i) {
            final double x2 = Math.sin(i * 3.141592653589793 / 180.0) * (r / 2.0f);
            final double y2 = Math.cos(i * 3.141592653589793 / 180.0) * (r / 2.0f);
            GL11.glVertex2d(x + r / 2.0f + x2, y + r / 2.0f + y2);
        }
        GL11.glEnd();
        GL11.glBegin(2);
        for (int i = 0; i <= 360; ++i) {
            final double x2 = Math.sin(i * 3.141592653589793 / 180.0) * (r / 2.0f);
            final double y2 = Math.cos(i * 3.141592653589793 / 180.0) * (r / 2.0f);
            GL11.glVertex2d(x + r / 2.0f + x2, y + r / 2.0f + y2);
        }
        GL11.glEnd();
        GL11.glDisable(2848);
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GL11.glPopMatrix();
    }
    
    public static void drawUnfilledCircle(final float x, final float y, final float r, final int c) {
        final float f = (c >> 24 & 0xFF) / 255.0f;
        final float f2 = (c >> 16 & 0xFF) / 255.0f;
        final float f3 = (c >> 8 & 0xFF) / 255.0f;
        final float f4 = (c & 0xFF) / 255.0f;
        GL11.glPushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glLineWidth(1.0f);
        GL11.glBegin(2);
        for (int i = 0; i <= 360; ++i) {
            final double x2 = Math.sin(i * 3.141592653589793 / 180.0) * (r / 2.0f);
            final double y2 = Math.cos(i * 3.141592653589793 / 180.0) * (r / 2.0f);
            GL11.glVertex2d(x + r / 2.0f + x2, y + r / 2.0f + y2);
        }
        GL11.glEnd();
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GL11.glPopMatrix();
    }
    
    public static void OutlinedBB(final AxisAlignedBB bb, final float width, final int color) {
        enable3D();
        GL11.glLineWidth(width);
        color(color);
        drawOutlinedBoundingBox(bb);
        disable3D();
    }
    
    public static void BB(final AxisAlignedBB bb, final int color) {
        enable3D();
        color(color);
        drawBoundingBox(bb);
        disable3D();
    }
    
    public static void enable3D() {
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
    }
    
    public static void disable3D() {
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
    }
    
    public static void color(final int color) {
        GL11.glColor4f((color >> 16 & 0xFF) / 255.0f, (color >> 8 & 0xFF) / 255.0f, (color & 0xFF) / 255.0f, (color >> 24 & 0xFF) / 255.0f);
    }
    
    public static void drawOutlinedBoundingBox(final AxisAlignedBB aa) {
        final Tessellator tessellator = Tessellator.getInstance();
        final WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        worldRenderer.begin(3, DefaultVertexFormats.POSITION);
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(3, DefaultVertexFormats.POSITION);
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(1, DefaultVertexFormats.POSITION);
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
        tessellator.draw();
    }
    
    public static void drawBoundingBox(final AxisAlignedBB aa) {
        final Tessellator tessellator = Tessellator.getInstance();
        final WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
        tessellator.draw();
    }
    
    public static Vector3d project(final double x, final double y, final double z) {
        final FloatBuffer vector = GLAllocation.createDirectFloatBuffer(4);
        GL11.glGetFloat(2982, RenderUtil.modelview);
        GL11.glGetFloat(2983, RenderUtil.projection);
        GL11.glGetInteger(2978, RenderUtil.viewport);
        if (GLU.gluProject((float)x, (float)y, (float)z, RenderUtil.modelview, RenderUtil.projection, RenderUtil.viewport, vector)) {
            return new Vector3d(vector.get(0) / getResolution().getScaleFactor(), (Display.getHeight() - vector.get(1)) / getResolution().getScaleFactor(), vector.get(2));
        }
        return null;
    }
    
    public static void drawCheckMark(final float x, final float y, final int width, final int color) {
        final float f = (color >> 24 & 0xFF) / 255.0f;
        final float f2 = (color >> 16 & 0xFF) / 255.0f;
        final float f3 = (color >> 8 & 0xFF) / 255.0f;
        final float f4 = (color & 0xFF) / 255.0f;
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glLineWidth(1.5f);
        GL11.glBegin(3);
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glVertex2d(x + width - 6.5, (double)(y + 3.0f));
        GL11.glVertex2d(x + width - 11.5, (double)(y + 10.0f));
        GL11.glVertex2d(x + width - 13.5, (double)(y + 8.0f));
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }
    
    public static boolean isInViewFrustrum(final Entity entity) {
        return isInViewFrustrum(entity.getEntityBoundingBox()) || entity.ignoreFrustumCheck;
    }
    
    public static boolean isInViewFrustrum(final AxisAlignedBB bb) {
        final Entity current = Minecraft.getMinecraft().getRenderViewEntity();
        RenderUtil.frustrum.setPosition(current.posX, current.posY, current.posZ);
        return RenderUtil.frustrum.isBoundingBoxInFrustum(bb);
    }
    
    public static void prepareScissorBox(final ScaledResolution sr, final float x, final float y, final float width, final float height) {
        final float x2 = x + width;
        final float y2 = y + height;
        final int factor = sr.getScaleFactor();
        GL11.glScissor((int)(x * factor), (int)((sr.getScaledHeight() - y2) * factor), (int)((x2 - x) * factor), (int)((y2 - y) * factor));
    }
    
    public static void drawBorderedRect(final double x, final double y, final double width, final double height, final double lineSize, final int borderColor, final int color) {
        Gui.drawRect(x, y, x + width, y + height, color);
        Gui.drawRect(x, y, x + width, y + lineSize, borderColor);
        Gui.drawRect(x, y, x + lineSize, y + height, borderColor);
        Gui.drawRect(x + width, y, x + width - lineSize, y + height, borderColor);
        Gui.drawRect(x, y + height, x + width, y + height - lineSize, borderColor);
    }
    
    public static void drawCornerRect(final double x, final double y, final double width, final double height, final double thickness, final int hex, final boolean border, final double borderwidth) {
        final double w = width / 4.0;
        final double h = height / 4.0;
        drawRect(x, y, w + (border ? borderwidth : 0.0), thickness, hex);
        drawRect(x + width - (w + (border ? borderwidth : 0.0)), y, w, thickness, hex);
        drawRect(x, y + height - thickness, w + (border ? borderwidth : 0.0), thickness, hex);
        drawRect(x + width - (w + (border ? borderwidth : 0.0)), y + height - thickness, w, thickness, hex);
        drawRect(x, y, thickness, h + (border ? borderwidth : 0.0), hex);
        drawRect(x + width - thickness, y, thickness, h + (border ? borderwidth : 0.0), hex);
        drawRect(x, y + height - (h + (border ? borderwidth : 0.0)), thickness, h, hex);
        drawRect(x + width - thickness, y + height - (h + (border ? borderwidth : 0.0)), thickness, h, hex);
    }
    
    public static void drawBordered(final double x, final double y, final double x2, final double y2, final double thickness, final int inside, final int outline) {
        double fix = 0.0;
        if (thickness < 1.0) {
            fix = 1.0;
        }
        drawRect2(x + thickness, y + thickness, x2 - thickness, y2 - thickness, inside);
        drawRect2(x, y + 1.0 - fix, x + thickness, y2, outline);
        drawRect2(x, y, x2 - 1.0 + fix, y + thickness, outline);
        drawRect2(x2 - thickness, y, x2, y2 - 1.0 + fix, outline);
        drawRect2(x + 1.0 - fix, y2 - thickness, x2, y2, outline);
    }
    
    public static void drawBar(final float x, final float y, final float width, final float height, final float max, final float value, final int color) {
        final float f = (color >> 24 & 0xFF) / 255.0f;
        final float f2 = (color >> 16 & 0xFF) / 255.0f;
        final float f3 = (color >> 8 & 0xFF) / 255.0f;
        final float f4 = (color & 0xFF) / 255.0f;
        final float inc = height / max;
        GL11.glColor4f(f2, f3, f4, f);
        drawBorderedRect(x, y, width, height, 0.5, -16777216, 0);
        float incY = y + height - inc;
        for (int i = 0; i < value; ++i) {
            drawBorderedRect(x + 0.25f, incY, width - 0.5f, inc, 0.25, -16777216, color);
            incY -= inc;
        }
    }
    
    public static void drawRect(final double x, final double y, final double width, final double height, final int color) {
        final float f = (color >> 24 & 0xFF) / 255.0f;
        final float f2 = (color >> 16 & 0xFF) / 255.0f;
        final float f3 = (color >> 8 & 0xFF) / 255.0f;
        final float f4 = (color & 0xFF) / 255.0f;
        GL11.glColor4f(f2, f3, f4, f);
        Gui.drawRect(x, y, x + width, y + height, color);
    }
    
    public static void drawRect2(final double x, final double y, final double x2, final double y2, final int color) {
        final float f = (color >> 24 & 0xFF) / 255.0f;
        final float f2 = (color >> 16 & 0xFF) / 255.0f;
        final float f3 = (color >> 8 & 0xFF) / 255.0f;
        final float f4 = (color & 0xFF) / 255.0f;
        GL11.glColor4f(f2, f3, f4, f);
        Gui.drawRect(x, y, x2, y2, color);
    }
    
    public static void drawBorderedRoundedRect(final float x, final float y, final float width, final float height, final float radius, final float linewidth, final int insideC, final int borderC) {
        drawRoundedRect(x, y, width, height, radius, borderC);
        drawOutlinedRoundedRect(x, y, width, height, radius, linewidth, insideC);
    }
    
    public static void drawRoundedRectWithShadow(final double x, final double y, final double width, final double height, final double radius, final int color) {
        drawRoundedRect(x + 2.0, y + 1.0, width, height + 1.0, radius, new Color(0).getRGB());
        drawRoundedRect(x, y, width, height, radius, color);
    }
    
    public static void drawOutlinedRoundedRect(double x, double y, final double width, final double height, final double radius, final float linewidth, final int color) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        double x2 = x + width;
        double y2 = y + height;
        final float f = (color >> 24 & 0xFF) / 255.0f;
        final float f2 = (color >> 16 & 0xFF) / 255.0f;
        final float f3 = (color >> 8 & 0xFF) / 255.0f;
        final float f4 = (color & 0xFF) / 255.0f;
        GL11.glPushAttrib(0);
        GL11.glScaled(0.5, 0.5, 0.5);
        x *= 2.0;
        y *= 2.0;
        x2 *= 2.0;
        y2 *= 2.0;
        GL11.glLineWidth(linewidth);
        GL11.glDisable(3553);
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glEnable(2848);
        GL11.glBegin(2);
        for (int i = 0; i <= 90; i += 3) {
            GL11.glVertex2d(x + radius + Math.sin(i * 3.141592653589793 / 180.0) * (radius * -1.0), y + radius + Math.cos(i * 3.141592653589793 / 180.0) * (radius * -1.0));
        }
        for (int i = 90; i <= 180; i += 3) {
            GL11.glVertex2d(x + radius + Math.sin(i * 3.141592653589793 / 180.0) * (radius * -1.0), y2 - radius + Math.cos(i * 3.141592653589793 / 180.0) * (radius * -1.0));
        }
        for (int i = 0; i <= 90; i += 3) {
            GL11.glVertex2d(x2 - radius + Math.sin(i * 3.141592653589793 / 180.0) * radius, y2 - radius + Math.cos(i * 3.141592653589793 / 180.0) * radius);
        }
        for (int i = 90; i <= 180; i += 3) {
            GL11.glVertex2d(x2 - radius + Math.sin(i * 3.141592653589793 / 180.0) * radius, y + radius + Math.cos(i * 3.141592653589793 / 180.0) * radius);
        }
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glScaled(2.0, 2.0, 2.0);
        GL11.glPopAttrib();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static void drawRoundedRect(double x, double y, final double width, final double height, final double radius, final int color) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        double x2 = x + width;
        double y2 = y + height;
        final float f = (color >> 24 & 0xFF) / 255.0f;
        final float f2 = (color >> 16 & 0xFF) / 255.0f;
        final float f3 = (color >> 8 & 0xFF) / 255.0f;
        final float f4 = (color & 0xFF) / 255.0f;
        GL11.glPushAttrib(0);
        GL11.glScaled(0.5, 0.5, 0.5);
        x *= 2.0;
        y *= 2.0;
        x2 *= 2.0;
        y2 *= 2.0;
        GL11.glDisable(3553);
        GL11.glColor4f(f2, f3, f4, f);
        GL11.glEnable(2848);
        GL11.glBegin(9);
        for (int i = 0; i <= 90; i += 3) {
            GL11.glVertex2d(x + radius + Math.sin(i * 3.141592653589793 / 180.0) * (radius * -1.0), y + radius + Math.cos(i * 3.141592653589793 / 180.0) * (radius * -1.0));
        }
        for (int i = 90; i <= 180; i += 3) {
            GL11.glVertex2d(x + radius + Math.sin(i * 3.141592653589793 / 180.0) * (radius * -1.0), y2 - radius + Math.cos(i * 3.141592653589793 / 180.0) * (radius * -1.0));
        }
        for (int i = 0; i <= 90; i += 3) {
            GL11.glVertex2d(x2 - radius + Math.sin(i * 3.141592653589793 / 180.0) * radius, y2 - radius + Math.cos(i * 3.141592653589793 / 180.0) * radius);
        }
        for (int i = 90; i <= 180; i += 3) {
            GL11.glVertex2d(x2 - radius + Math.sin(i * 3.141592653589793 / 180.0) * radius, y + radius + Math.cos(i * 3.141592653589793 / 180.0) * radius);
        }
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glScaled(2.0, 2.0, 2.0);
        GL11.glPopAttrib();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
}
