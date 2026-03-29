package net.minecraft.util;

import neo.QQ;

public class WeightedSpawnerEntity extends WeightedRandom.Item {
   private final QQ nbt;

   public WeightedSpawnerEntity() {
      super(1);
      this.nbt = new QQ();
      this.nbt.setString("id", "minecraft:pig");
   }

   public WeightedSpawnerEntity(QQ nbtIn) {
      this(nbtIn.hasKey("Weight", 99) ? nbtIn.getInteger("Weight") : 1, nbtIn.getCompoundTag("Entity"));
   }

   public WeightedSpawnerEntity(int itemWeightIn, QQ nbtIn) {
      super(itemWeightIn);
      this.nbt = nbtIn;
   }

   public QQ toCompoundTag() {
      QQ nbttagcompound = new QQ();
      if (!this.nbt.hasKey("id", 8)) {
         this.nbt.setString("id", "minecraft:pig");
      } else if (!this.nbt.getString("id").contains(":")) {
         this.nbt.setString("id", (new ResourceLocation(this.nbt.getString("id"))).toString());
      }

      nbttagcompound.setTag("Entity", this.nbt);
      nbttagcompound.setInteger("Weight", this.itemWeight);
      return nbttagcompound;
   }

   public QQ getNbt() {
      return this.nbt;
   }
}
