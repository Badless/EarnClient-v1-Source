package earnclient.utils.font;

import java.awt.*;
import net.minecraft.client.*;
import net.minecraft.util.*;
import java.io.*;
import java.util.*;

public class FontUtil
{
    public static volatile int completed;
    public static MinecraftFontRenderer normal;
    private static Font normal_;
    
    private static Font getFont(final Map<String, Font> locationMap, final String location, final int size) {
        Font font = null;
        try {
            if (locationMap.containsKey(location)) {
                font = locationMap.get(location).deriveFont(0, (float)size);
            }
            else {
                final InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("earnclient/font/" + location)).getInputStream();
                font = Font.createFont(0, is);
                locationMap.put(location, font);
                font = font.deriveFont(0, (float)size);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, 10);
        }
        return font;
    }
    
    public static boolean hasLoaded() {
        return FontUtil.completed >= 3;
    }
    
    public static void bootstrap() {
        final HashMap<String, Font> locationMap;
        new Thread(() -> {
            locationMap = new HashMap<String, Font>();
            FontUtil.normal_ = getFont(locationMap, "segoe.ttf", 19);
            ++FontUtil.completed;
            return;
        }).start();
        final HashMap<String, Font> locationMap2;
        new Thread(() -> {
            locationMap2 = new HashMap<String, Font>();
            ++FontUtil.completed;
            return;
        }).start();
        final HashMap<String, Font> locationMap3;
        new Thread(() -> {
            locationMap3 = new HashMap<String, Font>();
            ++FontUtil.completed;
            return;
        }).start();
        while (!hasLoaded()) {
            try {
                Thread.sleep(5L);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        FontUtil.normal = new MinecraftFontRenderer(FontUtil.normal_, true, true);
    }
}
