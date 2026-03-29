package neo;

import net.minecraft.entity.Entity;

public class 0J extends 0n implements 0p {
   public boolean canceled;
   public Entity entity;
   public 0I type;

   public void setCanceled() {
      D70Q6yCHIl(this, (boolean)(7328 ^ -25223 ^ 1969 ^ -31127));
   }

   public _J/* $FF was: 0J*/(Entity entity, 0I type) {
      this.entity = entity;
      this.type = type;
   }

   private static Entity mRDvpjJk0X(0J var0) {
      return var0.entity;
   }

   private static void D70Q6yCHIl(0J var0, boolean var1) {
      var0.canceled = var1;
   }

   public boolean isCanceled() {
      return lFKyQCFGFQ(this);
   }

   public 0I getType() {
      return aLcgbVOF4z(this);
   }

   public void setEntity(Entity entity) {
      LmGLAjeFlg(this, entity);
   }

   public Entity getEntity() {
      return mRDvpjJk0X(this);
   }

   private static boolean lFKyQCFGFQ(0J var0) {
      return var0.canceled;
   }

   private static void LmGLAjeFlg(0J var0, Entity var1) {
      var0.entity = var1;
   }

   private static 0I aLcgbVOF4z(0J var0) {
      return var0.type;
   }
}
