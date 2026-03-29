package neo;

import java.util.UUID;
import net.minecraft.util.math.BlockPos;

public class bny implements bmR {
   private Ig entity;

   public bny() {
   }

   public int getId() {
      UUID uuid = this.entity.getUniqueID();
      long i = uuid.getLeastSignificantBits();
      int j = (int)(i & 2147483647L);
      return j;
   }

   public BlockPos getSpawnPosition() {
      return this.entity.getDataManager().spawnPosition;
   }

   public Zi getSpawnBiome() {
      return this.entity.getDataManager().spawnBiome;
   }

   public String getName() {
      return this.entity.hasCustomName() ? this.entity.getCustomNameTag() : null;
   }

   public int getHealth() {
      if (!(this.entity instanceof Iu)) {
         return 0;
      } else {
         Iu entityliving = (Iu)this.entity;
         return (int)entityliving.getHealth();
      }
   }

   public int getMaxHealth() {
      if (!(this.entity instanceof Iu)) {
         return 0;
      } else {
         Iu entityliving = (Iu)this.entity;
         return (int)entityliving.getMaxHealth();
      }
   }

   public Ig getEntity() {
      return this.entity;
   }

   public void setEntity(Ig entity) {
      this.entity = entity;
   }
}
