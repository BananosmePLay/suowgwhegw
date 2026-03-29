package neo;

import net.minecraft.util.ResourceLocation;

public class xi extends vP<Lc> {
   private static final ResourceLocation VEX_TEXTURE = new ResourceLocation("textures/entity/illager/vex.png");
   private static final ResourceLocation VEX_CHARGING_TEXTURE = new ResourceLocation("textures/entity/illager/vex_charging.png");
   private int modelVersion;

   public xi(wC renderManagerIn) {
      super(renderManagerIn, new oK(), 0.3F);
      this.modelVersion = ((oK)this.mainModel).getModelVersion();
   }

   protected ResourceLocation getEntityTexture(Lc entity) {
      return entity.isCharging() ? VEX_CHARGING_TEXTURE : VEX_TEXTURE;
   }

   public void doRender(Lc entity, double x, double y, double z, float entityYaw, float partialTicks) {
      int i = ((oK)this.mainModel).getModelVersion();
      if (i != this.modelVersion) {
         this.mainModel = new oK();
         this.modelVersion = i;
      }

      super.doRender(entity, x, y, z, entityYaw, partialTicks);
   }

   protected void preRenderCallback(Lc entitylivingbaseIn, float partialTickTime) {
      yh.scale(0.4F, 0.4F, 0.4F);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Iu var1) {
      return this.getEntityTexture((Lc)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Iu var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((Lc)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void preRenderCallback(Iw var1, float var2) {
      this.preRenderCallback((Lc)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Iw var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((Lc)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((Lc)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Ig var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((Lc)var1, var2, var4, var6, var8, var9);
   }
}
