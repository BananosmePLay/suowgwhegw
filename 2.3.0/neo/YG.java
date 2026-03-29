package neo;

import javax.annotation.Nullable;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.WeightedSpawnerEntity;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.IDataFixer;
import net.minecraft.util.datafix.IDataWalker;
import net.minecraft.util.math.BlockPos;

public class YG extends Yg implements ITickable {
   private final Yb spawnerLogic = new Yb() {
      public void broadcastEvent(int id) {
         YG.this.world.addBlockEvent(YG.this.pos, Nk.MOB_SPAWNER, id, 0);
      }

      public bij getSpawnerWorld() {
         return YG.this.world;
      }

      public BlockPos getSpawnerPosition() {
         return YG.this.pos;
      }

      public void setNextSpawnData(WeightedSpawnerEntity p_184993_1_) {
         super.setNextSpawnData(p_184993_1_);
         if (this.getSpawnerWorld() != null) {
            in iblockstate = this.getSpawnerWorld().getBlockState(this.getSpawnerPosition());
            this.getSpawnerWorld().notifyBlockUpdate(YG.this.pos, iblockstate, iblockstate, 4);
         }

      }
   };

   public YG() {
   }

   public static void registerFixesMobSpawner(DataFixer fixer) {
      fixer.registerWalker(FixTypes.BLOCK_ENTITY, new IDataWalker() {
         public QQ process(IDataFixer fixer, QQ compound, int versionIn) {
            if (Yg.getKey(YG.class).equals(new ResourceLocation(compound.getString("id")))) {
               if (compound.hasKey("SpawnPotentials", 9)) {
                  QW nbttaglist = compound.getTagList("SpawnPotentials", 10);

                  for(int i = 0; i < nbttaglist.tagCount(); ++i) {
                     QQ nbttagcompound = nbttaglist.getCompoundTagAt(i);
                     nbttagcompound.setTag("Entity", fixer.process(FixTypes.ENTITY, nbttagcompound.getCompoundTag("Entity"), versionIn));
                  }
               }

               compound.setTag("SpawnData", fixer.process(FixTypes.ENTITY, compound.getCompoundTag("SpawnData"), versionIn));
            }

            return compound;
         }
      });
   }

   public void readFromNBT(QQ compound) {
      super.readFromNBT(compound);
      this.spawnerLogic.readFromNBT(compound);
   }

   public QQ writeToNBT(QQ compound) {
      super.writeToNBT(compound);
      this.spawnerLogic.writeToNBT(compound);
      return compound;
   }

   public void update() {
      this.spawnerLogic.updateSpawner();
   }

   @Nullable
   public Vg getUpdatePacket() {
      return new Vg(this.pos, 1, this.getUpdateTag());
   }

   public QQ getUpdateTag() {
      QQ nbttagcompound = this.writeToNBT(new QQ());
      nbttagcompound.removeTag("SpawnPotentials");
      return nbttagcompound;
   }

   public boolean receiveClientEvent(int id, int type) {
      return this.spawnerLogic.setDelayToMin(id) ? true : super.receiveClientEvent(id, type);
   }

   public boolean onlyOpsCanSetNbt() {
      return true;
   }

   public Yb getSpawnerBaseLogic() {
      return this.spawnerLogic;
   }
}
