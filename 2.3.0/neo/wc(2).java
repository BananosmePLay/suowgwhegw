package neo;

import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;

public class wc extends vI<Ig> {
   public wc(wC renderManagerIn) {
      super(renderManagerIn);
   }

   public void doRender(Ig entity, double x, double y, double z, float entityYaw, float partialTicks) {
      yh.pushMatrix();
      renderOffsetAABB(entity.getEntityBoundingBox(), x - entity.lastTickPosX, y - entity.lastTickPosY, z - entity.lastTickPosZ);
      yh.popMatrix();
      super.doRender(entity, x, y, z, entityYaw, partialTicks);
   }

   @Nullable
   protected ResourceLocation getEntityTexture(Ig entity) {
      return null;
   }
}
