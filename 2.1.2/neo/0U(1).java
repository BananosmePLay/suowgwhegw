package neo;

import net.minecraft.entity.EntityLivingBase;

public class 0U extends 0n {
   public String renderedName;
   public final EntityLivingBase entity;

   private static void A82QgMqcdK(0U var0, String var1) {
      var0.renderedName = var1;
   }

   private static EntityLivingBase _L5C4TpyzU/* $FF was: 7L5C4TpyzU*/(0U var0) {
      return var0.entity;
   }

   public _U/* $FF was: 0U*/(EntityLivingBase entity, String renderedName) {
      this.entity = entity;
      this.renderedName = renderedName;
   }

   public EntityLivingBase getEntity() {
      return 7L5C4TpyzU(this);
   }

   private static String wIzLwEaoIt(0U var0) {
      return var0.renderedName;
   }

   public void setRenderedName(String renderedName) {
      A82QgMqcdK(this, renderedName);
   }

   public String getRenderedName() {
      return wIzLwEaoIt(this);
   }
}
