package earnclient.cosmetics;

import earnclient.http.gsonobjs.*;
import net.minecraft.client.entity.*;
import earnclient.http.*;

public class CosmeticController
{
    private static ObjUserCosmetics[] userCosmetics;
    
    public static boolean shouldRenderTopHat(final AbstractClientPlayer player) {
        final ObjUserCosmetics uc = getCosmetics(player);
        return uc != null && uc.getHat().isEnabled();
    }
    
    public static float[] getTopHatColor(final AbstractClientPlayer player) {
        final ObjUserCosmetics uc = getCosmetics(player);
        if (uc == null) {
            return new float[] { 0.0f, 0.0f, 0.0f };
        }
        return uc.getHat().getColor();
    }
    
    private static ObjUserCosmetics getCosmetics(final AbstractClientPlayer player) {
        ObjUserCosmetics[] userCosmetics;
        for (int length = (userCosmetics = CosmeticController.userCosmetics).length, i = 0; i < length; ++i) {
            final ObjUserCosmetics uc = userCosmetics[i];
            if (player.getGameProfile().getId().equals(uc.getUuid())) {
                return uc;
            }
        }
        return null;
    }
    
    public static void downloadUserCosmetics() {
        CosmeticController.userCosmetics = HTTPFunctions.downloadUserCosmetics();
    }
}
