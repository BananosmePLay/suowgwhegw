package neo;

import net.minecraft.util.math.BlockPos;

public class bnB implements bmR {
   private Yg tileEntity;

   public bnB() {
   }

   public int getId() {
      return XH.getRandom(this.tileEntity.getPos(), this.tileEntity.getBlockMetadata());
   }

   public BlockPos getSpawnPosition() {
      return this.tileEntity.getPos();
   }

   public String getName() {
      String s = bqT.getTileEntityName(this.tileEntity);
      return s;
   }

   public Zi getSpawnBiome() {
      return this.tileEntity.getWorld().getBiome(this.tileEntity.getPos());
   }

   public int getHealth() {
      return -1;
   }

   public int getMaxHealth() {
      return -1;
   }

   public Yg getTileEntity() {
      return this.tileEntity;
   }

   public void setTileEntity(Yg tileEntity) {
      this.tileEntity = tileEntity;
   }
}
