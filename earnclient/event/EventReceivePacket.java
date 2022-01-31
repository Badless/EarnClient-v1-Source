package earnclient.event;

import net.minecraft.network.*;

public class EventReceivePacket extends Event2
{
    public Packet packet;
    private boolean outgoing;
    
    public EventReceivePacket(final Packet packet) {
        this.packet = packet;
    }
    
    public Packet getPacket() {
        return this.packet;
    }
    
    public void setPacket(final Packet packet) {
        this.packet = packet;
    }
    
    public boolean isOutgoing() {
        return this.outgoing;
    }
    
    public boolean isIncoming() {
        return !this.outgoing;
    }
}
