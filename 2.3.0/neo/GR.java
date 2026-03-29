package neo;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.AxisAlignedBB;

public class GR<T extends Iw> extends Hf {
   protected final Class<T> targetClass;
   private final int targetChance;
   protected final GQ sorter;
   protected final Predicate<? super T> targetEntitySelector;
   protected T targetEntity;

   public GR(Ik creature, Class<T> classTarget, boolean checkSight) {
      this(creature, classTarget, checkSight, false);
   }

   public GR(Ik creature, Class<T> classTarget, boolean checkSight, boolean onlyNearby) {
      this(creature, classTarget, 10, checkSight, onlyNearby, (Predicate)null);
   }

   public GR(Ik creature, Class<T> classTarget, int chance, boolean checkSight, boolean onlyNearby, @Nullable final Predicate<? super T> targetSelector) {
      super(creature, checkSight, onlyNearby);
      this.targetClass = classTarget;
      this.targetChance = chance;
      this.sorter = new GQ(creature);
      this.setMutexBits(1);
      this.targetEntitySelector = new Predicate<T>() {
         public boolean apply(@Nullable T p_apply_1_) {
            if (p_apply_1_ == null) {
               return false;
            } else if (targetSelector != null && !targetSelector.apply(p_apply_1_)) {
               return false;
            } else {
               return !EntitySelectors.NOT_SPECTATING.apply(p_apply_1_) ? false : GR.this.isSuitableTarget(p_apply_1_, false);
            }
         }

         // $FF: synthetic method
         // $FF: bridge method
         public boolean apply(@Nullable Object var1) {
            return this.apply((Iw)var1);
         }
      };
   }

   public boolean shouldExecute() {
      if (this.targetChance > 0 && this.taskOwner.getRNG().nextInt(this.targetChance) != 0) {
         return false;
      } else if (this.targetClass != ME.class && this.targetClass != MG.class) {
         List<T> list = this.taskOwner.world.getEntitiesWithinAABB(this.targetClass, this.getTargetableArea(this.getTargetDistance()), this.targetEntitySelector);
         if (list.isEmpty()) {
            return false;
         } else {
            Collections.sort(list, this.sorter);
            this.targetEntity = (Iw)list.get(0);
            return true;
         }
      } else {
         this.targetEntity = this.taskOwner.world.getNearestAttackablePlayer(this.taskOwner.posX, this.taskOwner.posY + (double)this.taskOwner.getEyeHeight(), this.taskOwner.posZ, this.getTargetDistance(), this.getTargetDistance(), new Function<ME, Double>() {
            @Nullable
            public Double apply(@Nullable ME p_apply_1_) {
               Qy itemstack = p_apply_1_.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
               if (itemstack.getItem() == NK.SKULL) {
                  int i = itemstack.getItemDamage();
                  boolean flag = GR.this.taskOwner instanceof KH && i == 0;
                  boolean flag1 = GR.this.taskOwner instanceof Lk && i == 2;
                  boolean flag2 = GR.this.taskOwner instanceof JB && i == 4;
                  if (flag || flag1 || flag2) {
                     return 0.5;
                  }
               }

               return 1.0;
            }

            // $FF: synthetic method
            // $FF: bridge method
            @Nullable
            public Object apply(@Nullable Object var1) {
               return this.apply((ME)var1);
            }
         }, this.targetEntitySelector);
         return this.targetEntity != null;
      }
   }

   protected AxisAlignedBB getTargetableArea(double targetDistance) {
      return this.taskOwner.getEntityBoundingBox().grow(targetDistance, 4.0, targetDistance);
   }

   public void startExecuting() {
      this.taskOwner.setAttackTarget(this.targetEntity);
      super.startExecuting();
   }
}
