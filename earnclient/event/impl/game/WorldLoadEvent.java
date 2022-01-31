package earnclient.event.impl.game;

import earnclient.event.*;
import net.minecraft.client.multiplayer.*;

public class WorldLoadEvent extends Event2
{
    private WorldClient worldClient;
    
    public WorldLoadEvent(final WorldClient worldClient) {
        this.worldClient = worldClient;
    }
    
    public WorldClient getWorldClient() {
        return this.worldClient;
    }
}
