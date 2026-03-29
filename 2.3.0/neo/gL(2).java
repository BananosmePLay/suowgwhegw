package neo;

import net.minecraft.util.math.BlockPos;

public class gL implements ET {
   private final bij world;
   private final BlockPos pos;

   public gL(bij worldIn, BlockPos posIn) {
      this.world = worldIn;
      this.pos = posIn;
   }

   public bij getWorld() {
      return this.world;
   }

   public double getX() {
      return (double)this.pos.getX() + 0.5;
   }

   public double getY() {
      return (double)this.pos.getY() + 0.5;
   }

   public double getZ() {
      return (double)this.pos.getZ() + 0.5;
   }

   public BlockPos getBlockPos() {
      return this.pos;
   }

   public in getBlockState() {
      return this.world.getBlockState(this.pos);
   }

   public <T extends Yg> T getBlockTileEntity() {
      return this.world.getTileEntity(this.pos);
   }
}
