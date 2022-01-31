package earnclient.event.impl.game;

import earnclient.event.cancelable.*;
import net.minecraft.network.*;

public class PacketEvent extends CancelableEvent
{
    private boolean sending;
    private Packet packet;
    
    public PacketEvent(final Packet packet, final boolean sending) {
        this.packet = packet;
        this.sending = sending;
    }
    
    public void setPacket(final Packet packet) {
        this.packet = packet;
    }
    
    public Packet getPacket() {
        return this.packet;
    }
    
    public boolean isSending() {
        return this.sending;
    }
}
