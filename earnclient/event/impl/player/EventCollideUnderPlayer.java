package earnclient.event.impl.player;

import earnclient.event.*;
import net.minecraft.block.*;
import java.util.*;
import net.minecraft.util.*;

public class EventCollideUnderPlayer extends Event2
{
    private Block block;
    private BlockPos.MutableBlockPos blockPos;
    private List<AxisAlignedBB> list;
    
    public EventCollideUnderPlayer(final BlockPos.MutableBlockPos pos, final Block block, final List<AxisAlignedBB> bList) {
        this.block = block;
        this.blockPos = pos;
        this.list = bList;
    }
    
    public Block getBlock() {
        return this.block;
    }
    
    public BlockPos.MutableBlockPos getBlockPos() {
        return this.blockPos;
    }
    
    public List<AxisAlignedBB> getList() {
        return this.list;
    }
    
    public void setBlock(final Block block) {
        this.block = block;
    }
    
    public void setBlockPos(final BlockPos.MutableBlockPos blockPos) {
        this.blockPos = blockPos;
    }
    
    public void setList(final List<AxisAlignedBB> boundingBox) {
        this.list = boundingBox;
    }
}
