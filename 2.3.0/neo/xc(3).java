package neo;

import net.minecraft.util.ResourceLocation;

public class xc<T extends KW> extends ww<T> {
   private static final ResourceLocation SPIDER_TEXTURES = new ResourceLocation("textures/entity/spider/spider.png");

   public xc(wC renderManagerIn) {
      super(renderManagerIn, new oI(), 1.0F);
      this.addLayer(new vB(this));
   }

   protected float getDeathMaxRotation(T entityLivingBaseIn) {
      return 180.0F;
   }

   protected ResourceLocation getEntityTexture(T entity) {
      return SPIDER_TEXTURES;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected float getDeathMaxRotation(Iw var1) {
      return this.getDeathMaxRotation((KW)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((KW)var1);
   }
}
