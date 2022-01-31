package earnclient.hud.mod.impl;

import earnclient.hud.mod.*;
import org.lwjgl.opengl.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.*;
import net.minecraft.client.settings.*;

public class Keystrokes extends HudMod
{
    private KeystrokesMode mode;
    
    public Keystrokes() {
        super("Keystrokes", 100, 50);
        this.mode = KeystrokesMode.WASD_JUMP_MOUSE;
    }
    
    @Override
    public int getWidth() {
        return 58;
    }
    
    @Override
    public int getHeight() {
        return 72;
    }
    
    @Override
    public void draw() {
        GL11.glPushMatrix();
        Key[] keys;
        for (int length = (keys = this.mode.getKeys()).length, i = 0; i < length; ++i) {
            final Key key = keys[i];
            final int textWidth = this.fr.getStringWidth(key.getName());
            Gui.drawRect(this.getX() + key.getX(), this.getY() + key.getY(), this.getX() + key.getX() + key.getWidth(), this.getY() + key.getY() + key.getHeight(), key.isDown() ? new Color(255, 255, 255, 102).getRGB() : new Color(0, 0, 0, 120).getRGB());
            this.fr.drawStringWithShadow(key.getName(), (float)(this.getX() + key.getX() + key.getWidth() / 2 - textWidth / 2), (float)(this.getY() + key.getY() + key.getHeight() / 2 - 4), key.isDown() ? new Color(0, 0, 0, 255).getRGB() : -1);
        }
        GL11.glPopMatrix();
        super.draw();
    }
    
    @Override
    public void renderDummy(final int mouseX, final int mouseY) {
        GL11.glPushMatrix();
        Key[] keys;
        for (int length = (keys = this.mode.getKeys()).length, i = 0; i < length; ++i) {
            final Key key = keys[i];
            final int textWidth = this.fr.getStringWidth(key.getName());
            Gui.drawRect(this.getX() + key.getX(), this.getY() + key.getY(), this.getX() + key.getX() + key.getWidth(), this.getY() + key.getY() + key.getHeight(), key.isDown() ? new Color(255, 255, 255, 102).getRGB() : new Color(0, 0, 0, 120).getRGB());
            this.fr.drawStringWithShadow(key.getName(), (float)(this.getX() + key.getX() + key.getWidth() / 2 - textWidth / 2), (float)(this.getY() + key.getY() + key.getHeight() / 2 - 4), key.isDown() ? new Color(0, 0, 0, 255).getRGB() : -1);
        }
        GL11.glPopMatrix();
        super.renderDummy(mouseX, mouseY);
    }
    
    public enum KeystrokesMode
    {
        WASD("WASD", 0, new Key[] { Key.W, Key.A, Key.S, Key.D }), 
        WASD_MOUSE("WASD_MOUSE", 1, new Key[] { Key.W, Key.A, Key.S, Key.D, Key.LMB, Key.RMB }), 
        WASD_JUMP("WASD_JUMP", 2, new Key[] { Key.W, Key.A, Key.S, Key.D, Key.Jump1 }), 
        WASD_JUMP_MOUSE("WASD_JUMP_MOUSE", 3, new Key[] { Key.W, Key.A, Key.S, Key.D, Key.LMB, Key.RMB, Key.Jump2 });
        
        private final Key[] keys;
        private int width;
        private int height;
        
        private KeystrokesMode(final String s, final int n, final Key... keysIn) {
            this.keys = keysIn;
            Key[] keys;
            for (int length = (keys = this.keys).length, i = 0; i < length; ++i) {
                final Key key = keys[i];
                this.width = Math.max(this.width, key.getX() + key.getWidth());
                this.height = Math.max(this.height, key.getY() + key.getHeight());
            }
        }
        
        public int getHeight() {
            return this.height;
        }
        
        public int getWidth() {
            return this.width;
        }
        
        public Key[] getKeys() {
            return this.keys;
        }
    }
    
    public static class Key
    {
        public static Minecraft mc;
        private static final Key W;
        private static final Key A;
        private static final Key S;
        private static final Key D;
        private static final Key LMB;
        private static final Key RMB;
        private static final Key Jump1;
        private static final Key Jump2;
        private final String name;
        private final KeyBinding keyBind;
        private final int x;
        private final int y;
        private final int w;
        private final int h;
        
        static {
            Key.mc = Minecraft.getMinecraft();
            W = new Key("W", Key.mc.gameSettings.keyBindForward, 21, 1, 18, 18);
            A = new Key("A", Key.mc.gameSettings.keyBindLeft, 1, 21, 18, 18);
            S = new Key("S", Key.mc.gameSettings.keyBindBack, 21, 21, 18, 18);
            D = new Key("D", Key.mc.gameSettings.keyBindRight, 41, 21, 18, 18);
            LMB = new Key("LMB", Key.mc.gameSettings.keyBindAttack, 1, 41, 28, 18);
            RMB = new Key("RMB", Key.mc.gameSettings.keyBindUseItem, 31, 41, 28, 18);
            Jump1 = new Key("----", Key.mc.gameSettings.keyBindJump, 1, 41, 58, 18);
            Jump2 = new Key("----", Key.mc.gameSettings.keyBindJump, 1, 61, 58, 18);
        }
        
        public Key(final String name, final KeyBinding keyBind, final int x, final int y, final int w, final int h) {
            this.name = name;
            this.keyBind = keyBind;
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
        }
        
        public boolean isDown() {
            return this.keyBind.isKeyDown();
        }
        
        public int getHeight() {
            return this.h;
        }
        
        public int getWidth() {
            return this.w;
        }
        
        public String getName() {
            return this.name;
        }
        
        public int getX() {
            return this.x;
        }
        
        public int getY() {
            return this.y;
        }
    }
}
