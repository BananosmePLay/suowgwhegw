package neo;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class vR extends vI<IR> {
   private static final ResourceLocation[] BOAT_TEXTURES = new ResourceLocation[]{new ResourceLocation("textures/entity/boat/boat_oak.png"), new ResourceLocation("textures/entity/boat/boat_spruce.png"), new ResourceLocation("textures/entity/boat/boat_birch.png"), new ResourceLocation("textures/entity/boat/boat_jungle.png"), new ResourceLocation("textures/entity/boat/boat_acacia.png"), new ResourceLocation("textures/entity/boat/boat_darkoak.png")};
   protected nH modelBoat = new nO();

   public vR(wC renderManagerIn) {
      super(renderManagerIn);
      this.shadowSize = 0.5F;
   }

   public void doRender(IR entity, double x, double y, double z, float entityYaw, float partialTicks) {
      yh.pushMatrix();
      this.setupTranslation(x, y, z);
      this.setupRotation(entity, entityYaw, partialTicks);
      this.bindEntityTexture(entity);
      if (this.renderOutlines) {
         yh.enableColorMaterial();
         yh.enableOutlineMode(this.getTeamColor(entity));
      }

      this.modelBoat.render(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
      if (this.renderOutlines) {
         yh.disableOutlineMode();
         yh.disableColorMaterial();
      }

      yh.popMatrix();
      super.doRender(entity, x, y, z, entityYaw, partialTicks);
   }

   public void setupRotation(IR entityIn, float entityYaw, float partialTicks) {
      yh.rotate(180.0F - entityYaw, 0.0F, 1.0F, 0.0F);
      float f = (float)entityIn.getTimeSinceHit() - partialTicks;
      float f1 = entityIn.getDamageTaken() - partialTicks;
      if (f1 < 0.0F) {
         f1 = 0.0F;
      }

      if (f > 0.0F) {
         yh.rotate(MathHelper.sin(f) * f * f1 / 10.0F * (float)entityIn.getForwardDirection(), 1.0F, 0.0F, 0.0F);
      }

      yh.scale(-1.0F, -1.0F, 1.0F);
   }

   public void setupTranslation(double x, double y, double z) {
      yh.translate((float)x, (float)y + 0.375F, (float)z);
   }

   protected ResourceLocation getEntityTexture(IR entity) {
      return BOAT_TEXTURES[entity.getBoatType().ordinal()];
   }

   public boolean isMultipass() {
      return true;
   }

   public void renderMultipass(IR entityIn, double x, double y, double z, float entityYaw, float partialTicks) {
      yh.pushMatrix();
      this.setupTranslation(x, y, z);
      this.setupRotation(entityIn, entityYaw, partialTicks);
      this.bindEntityTexture(entityIn);
      ((nD)this.modelBoat).renderMultipass(entityIn, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
      yh.popMatrix();
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void renderMultipass(Ig var1, double var2, double var4, double var6, float var8, float var9) {
      this.renderMultipass((IR)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((IR)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Ig var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((IR)var1, var2, var4, var6, var8, var9);
   }
}
