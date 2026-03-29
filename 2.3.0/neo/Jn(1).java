package neo;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.IDataFixer;
import net.minecraft.util.datafix.IDataWalker;
import net.minecraft.util.math.BlockPos;

public class Jn extends Jc {
   private final Yb mobSpawnerLogic = new Yb() {
      public void broadcastEvent(int id) {
         Jn.this.world.setEntityState(Jn.this, (byte)id);
      }

      public bij getSpawnerWorld() {
         return Jn.this.world;
      }

      public BlockPos getSpawnerPosition() {
         return new BlockPos(Jn.this);
      }
   };

   public Jn(bij worldIn) {
      super(worldIn);
   }

   public Jn(bij worldIn, double x, double y, double z) {
      super(worldIn, x, y, z);
   }

   public static void registerFixesMinecartMobSpawner(DataFixer fixer) {
      registerFixesMinecart(fixer, Jn.class);
      fixer.registerWalker(FixTypes.ENTITY, new IDataWalker() {
         public QQ process(IDataFixer fixer, QQ compound, int versionIn) {
            String s = compound.getString("id");
            if (Ir.getKey(Jn.class).equals(new ResourceLocation(s))) {
               compound.setString("id", Yg.getKey(YG.class).toString());
               fixer.process(FixTypes.BLOCK_ENTITY, compound, versionIn);
               compound.setString("id", s);
            }

            return compound;
         }
      });
   }

   public Jb getType() {
      return Jb.SPAWNER;
   }

   public in getDefaultDisplayTile() {
      return Nk.MOB_SPAWNER.getDefaultState();
   }

   protected void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      this.mobSpawnerLogic.readFromNBT(compound);
   }

   protected void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      this.mobSpawnerLogic.writeToNBT(compound);
   }

   public void handleStatusUpdate(byte id) {
      this.mobSpawnerLogic.setDelayToMin(id);
   }

   public void onUpdate() {
      super.onUpdate();
      this.mobSpawnerLogic.updateSpawner();
   }
}
