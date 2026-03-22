package neo;

import net.minecraft.entity.Entity;

public class 0z implements 0p {
   public boolean canceled;
   public Entity entity;

   public boolean isCanceled() {
      return GMmLQJy4dY(this);
   }

   private static Entity GZanyoxtSg(0z var0) {
      return var0.entity;
   }

   private static void TQ4JCY1O7E(0z var0, boolean var1) {
      var0.canceled = var1;
   }

   public void setCanceled() {
      TQ4JCY1O7E(this, (boolean)(30126 ^ -8745 ^ 10702 ^ -32330));
   }

   public Entity getEntity() {
      return GZanyoxtSg(this);
   }

   private static boolean GMmLQJy4dY(0z var0) {
      return var0.canceled;
   }

   public _z/* $FF was: 0z*/(Entity entity) {
      this.entity = entity;
   }
}
