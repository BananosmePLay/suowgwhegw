package neo;

import net.minecraft.inventory.EntityEquipmentSlot;

public class Ha extends Gi {
   private final Ik entity;

   public Ha(Ik creature) {
      this.entity = creature;
   }

   public boolean shouldExecute() {
      return this.entity.world.isDaytime() && this.entity.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty();
   }

   public void startExecuting() {
      ((VO)this.entity.getNavigator()).setAvoidSun(true);
   }

   public void resetTask() {
      ((VO)this.entity.getNavigator()).setAvoidSun(false);
   }
}
