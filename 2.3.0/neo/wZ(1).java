package neo;

import net.minecraft.util.ResourceLocation;

public class wZ<T extends Ig> extends vI<T> {
   protected final OL item;
   private final yK itemRenderer;

   public wZ(wC renderManagerIn, OL itemIn, yK itemRendererIn) {
      super(renderManagerIn);
      this.item = itemIn;
      this.itemRenderer = itemRendererIn;
   }

   public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
      yh.pushMatrix();
      yh.translate((float)x, (float)y, (float)z);
      yh.enableRescaleNormal();
      yh.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
      yh.rotate((float)(this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
      yh.rotate(180.0F, 0.0F, 1.0F, 0.0F);
      this.bindTexture(zj.LOCATION_BLOCKS_TEXTURE);
      if (this.renderOutlines) {
         yh.enableColorMaterial();
         yh.enableOutlineMode(this.getTeamColor(entity));
      }

      this.itemRenderer.renderItem(this.getStackToRender(entity), sf.GROUND);
      if (this.renderOutlines) {
         yh.disableOutlineMode();
         yh.disableColorMaterial();
      }

      yh.disableRescaleNormal();
      yh.popMatrix();
      super.doRender(entity, x, y, z, entityYaw, partialTicks);
   }

   public Qy getStackToRender(T entityIn) {
      return new Qy(this.item);
   }

   protected ResourceLocation getEntityTexture(Ig entity) {
      return zj.LOCATION_BLOCKS_TEXTURE;
   }
}
