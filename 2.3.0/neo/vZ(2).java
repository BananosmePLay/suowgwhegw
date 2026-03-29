package neo;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class vZ extends vI<IS> {
   private static final ResourceLocation ENDER_CRYSTAL_TEXTURES = new ResourceLocation("textures/entity/endercrystal/endercrystal.png");
   private final nH modelEnderCrystal = new nY(0.0F, true);
   private final nH modelEnderCrystalNoBase = new nY(0.0F, false);

   public vZ(wC renderManagerIn) {
      super(renderManagerIn);
      this.shadowSize = 0.5F;
   }

   public void doRender(IS entity, double x, double y, double z, float entityYaw, float partialTicks) {
      float f = (float)entity.innerRotation + partialTicks;
      yh.pushMatrix();
      yh.translate((float)x, (float)y, (float)z);
      this.bindTexture(ENDER_CRYSTAL_TEXTURES);
      float f1 = MathHelper.sin(f * 0.2F) / 2.0F + 0.5F;
      f1 += f1 * f1;
      if (this.renderOutlines) {
         yh.enableColorMaterial();
         yh.enableOutlineMode(this.getTeamColor(entity));
      }

      if (entity.shouldShowBottom()) {
         this.modelEnderCrystal.render(entity, 0.0F, f * 3.0F, f1 * 0.2F, 0.0F, 0.0F, 0.0625F);
      } else {
         this.modelEnderCrystalNoBase.render(entity, 0.0F, f * 3.0F, f1 * 0.2F, 0.0F, 0.0F, 0.0625F);
      }

      if (this.renderOutlines) {
         yh.disableOutlineMode();
         yh.disableColorMaterial();
      }

      yh.popMatrix();
      BlockPos blockpos = entity.getBeamTarget();
      if (blockpos != null) {
         this.bindTexture(vW.ENDERCRYSTAL_BEAM_TEXTURES);
         float f2 = (float)blockpos.getX() + 0.5F;
         float f3 = (float)blockpos.getY() + 0.5F;
         float f4 = (float)blockpos.getZ() + 0.5F;
         double d0 = (double)f2 - entity.posX;
         double d1 = (double)f3 - entity.posY;
         double d2 = (double)f4 - entity.posZ;
         vW.renderCrystalBeams(x + d0, y - 0.3 + (double)(f1 * 0.4F) + d1, z + d2, partialTicks, (double)f2, (double)f3, (double)f4, entity.innerRotation, entity.posX, entity.posY, entity.posZ);
      }

      super.doRender(entity, x, y, z, entityYaw, partialTicks);
   }

   protected ResourceLocation getEntityTexture(IS entity) {
      return ENDER_CRYSTAL_TEXTURES;
   }

   public boolean shouldRender(IS livingEntity, uO camera, double camX, double camY, double camZ) {
      return super.shouldRender(livingEntity, camera, camX, camY, camZ) || livingEntity.getBeamTarget() != null;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((IS)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Ig var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((IS)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public boolean shouldRender(Ig var1, uO var2, double var3, double var5, double var7) {
      return this.shouldRender((IS)var1, var2, var3, var5, var7);
   }
}
