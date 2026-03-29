package neo;

import net.minecraft.util.ResourceLocation;

public class xd extends ww<Mf> {
   private static final ResourceLocation SQUID_TEXTURES = new ResourceLocation("textures/entity/squid.png");

   public xd(wC p_i47192_1_) {
      super(p_i47192_1_, new oJ(), 0.7F);
   }

   protected ResourceLocation getEntityTexture(Mf entity) {
      return SQUID_TEXTURES;
   }

   protected void applyRotations(Mf entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      float f = entityLiving.prevSquidPitch + (entityLiving.squidPitch - entityLiving.prevSquidPitch) * partialTicks;
      float f1 = entityLiving.prevSquidYaw + (entityLiving.squidYaw - entityLiving.prevSquidYaw) * partialTicks;
      yh.translate(0.0F, 0.5F, 0.0F);
      yh.rotate(180.0F - rotationYaw, 0.0F, 1.0F, 0.0F);
      yh.rotate(f, 1.0F, 0.0F, 0.0F);
      yh.rotate(f1, 0.0F, 1.0F, 0.0F);
      yh.translate(0.0F, -1.2F, 0.0F);
   }

   protected float handleRotationFloat(Mf livingBase, float partialTicks) {
      return livingBase.lastTentacleAngle + (livingBase.tentacleAngle - livingBase.lastTentacleAngle) * partialTicks;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected float handleRotationFloat(Iw var1, float var2) {
      return this.handleRotationFloat((Mf)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void applyRotations(Iw var1, float var2, float var3, float var4) {
      this.applyRotations((Mf)var1, var2, var3, var4);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((Mf)var1);
   }
}
