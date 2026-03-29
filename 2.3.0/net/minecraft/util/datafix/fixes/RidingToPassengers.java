package net.minecraft.util.datafix.fixes;

import neo.QQ;
import neo.QW;
import net.minecraft.util.datafix.IFixableData;

public class RidingToPassengers implements IFixableData {
   public RidingToPassengers() {
   }

   public int getFixVersion() {
      return 135;
   }

   public QQ fixTagCompound(QQ compound) {
      while(compound.hasKey("Riding", 10)) {
         QQ nbttagcompound = this.extractVehicle(compound);
         this.addPassengerToVehicle(compound, nbttagcompound);
         compound = nbttagcompound;
      }

      return compound;
   }

   protected void addPassengerToVehicle(QQ p_188219_1_, QQ p_188219_2_) {
      QW nbttaglist = new QW();
      nbttaglist.appendTag(p_188219_1_);
      p_188219_2_.setTag("Passengers", nbttaglist);
   }

   protected QQ extractVehicle(QQ p_188220_1_) {
      QQ nbttagcompound = p_188220_1_.getCompoundTag("Riding");
      p_188220_1_.removeTag("Riding");
      return nbttagcompound;
   }
}
