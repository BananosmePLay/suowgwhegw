package neo;

import net.minecraft.util.ResourceLocation;

public class wu extends vI<Ip> {
   private static final ResourceLocation LEASH_KNOT_TEXTURES = new ResourceLocation("textures/entity/lead_knot.png");
   private final oj leashKnotModel = new oj();

   public wu(wC renderManagerIn) {
      super(renderManagerIn);
   }

   public void doRender(Ip entity, double x, double y, double z, float entityYaw, float partialTicks) {
      yh.pushMatrix();
      yh.disableCull();
      yh.translate((float)x, (float)y, (float)z);
      float f = 0.0625F;
      yh.enableRescaleNormal();
      yh.scale(-1.0F, -1.0F, 1.0F);
      yh.enableAlpha();
      this.bindEntityTexture(entity);
      if (this.renderOutlines) {
         yh.enableColorMaterial();
         yh.enableOutlineMode(this.getTeamColor(entity));
      }

      this.leashKnotModel.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
      if (this.renderOutlines) {
         yh.disableOutlineMode();
         yh.disableColorMaterial();
      }

      yh.popMatrix();
      super.doRender(entity, x, y, z, entityYaw, partialTicks);
   }

   protected ResourceLocation getEntityTexture(Ip entity) {
      return LEASH_KNOT_TEXTURES;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((Ip)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Ig var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((Ip)var1, var2, var4, var6, var8, var9);
   }
}
