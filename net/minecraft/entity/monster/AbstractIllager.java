package net.minecraft.entity.monster;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

public abstract class AbstractIllager extends EntityMob {
   protected static final DataParameter<Byte> AGGRESSIVE;

   public AbstractIllager(World p_i47509_1_) {
      super(p_i47509_1_);
   }

   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(AGGRESSIVE, (byte)0);
   }

   protected boolean isAggressive(int mask) {
      int i = (Byte)this.dataManager.get(AGGRESSIVE);
      return (i & mask) != 0;
   }

   protected void setAggressive(int mask, boolean value) {
      int i = (Byte)this.dataManager.get(AGGRESSIVE);
      if (value) {
         i |= mask;
      } else {
         i &= ~mask;
      }

      this.dataManager.set(AGGRESSIVE, (byte)(i & 255));
   }

   public EnumCreatureAttribute getCreatureAttribute() {
      return EnumCreatureAttribute.ILLAGER;
   }

   public IllagerArmPose getArmPose() {
      return AbstractIllager.IllagerArmPose.CROSSED;
   }

   static {
      AGGRESSIVE = EntityDataManager.createKey(AbstractIllager.class, DataSerializers.BYTE);
   }

   public static enum IllagerArmPose {
      CROSSED,
      ATTACKING,
      SPELLCASTING,
      BOW_AND_ARROW;

      private IllagerArmPose() {
      }
   }
}
