package neo;

import java.util.Iterator;
import java.util.List;

public class Gz extends Gi {
   Ly childAnimal;
   Ly parentAnimal;
   double moveSpeed;
   private int delayCounter;

   public Gz(Ly animal, double speed) {
      this.childAnimal = animal;
      this.moveSpeed = speed;
   }

   public boolean shouldExecute() {
      if (this.childAnimal.getGrowingAge() >= 0) {
         return false;
      } else {
         List<Ly> list = this.childAnimal.world.getEntitiesWithinAABB(this.childAnimal.getClass(), this.childAnimal.getEntityBoundingBox().grow(8.0, 4.0, 8.0));
         Ly entityanimal = null;
         double d0 = Double.MAX_VALUE;
         Iterator var5 = list.iterator();

         while(var5.hasNext()) {
            Ly entityanimal1 = (Ly)var5.next();
            if (entityanimal1.getGrowingAge() >= 0) {
               double d1 = this.childAnimal.getDistanceSq(entityanimal1);
               if (d1 <= d0) {
                  d0 = d1;
                  entityanimal = entityanimal1;
               }
            }
         }

         if (entityanimal == null) {
            return false;
         } else if (d0 < 9.0) {
            return false;
         } else {
            this.parentAnimal = entityanimal;
            return true;
         }
      }
   }

   public boolean shouldContinueExecuting() {
      if (this.childAnimal.getGrowingAge() >= 0) {
         return false;
      } else if (!this.parentAnimal.isEntityAlive()) {
         return false;
      } else {
         double d0 = this.childAnimal.getDistanceSq(this.parentAnimal);
         return d0 >= 9.0 && d0 <= 256.0;
      }
   }

   public void startExecuting() {
      this.delayCounter = 0;
   }

   public void resetTask() {
      this.parentAnimal = null;
   }

   public void updateTask() {
      if (--this.delayCounter <= 0) {
         this.delayCounter = 10;
         this.childAnimal.getNavigator().tryMoveToEntityLiving(this.parentAnimal, this.moveSpeed);
      }

   }
}
