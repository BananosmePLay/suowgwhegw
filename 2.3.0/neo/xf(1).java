package neo;

import net.minecraft.util.ResourceLocation;

public class xf extends vN<Ne> {
   public static final ResourceLocation RES_ARROW = new ResourceLocation("textures/entity/projectiles/arrow.png");
   public static final ResourceLocation RES_TIPPED_ARROW = new ResourceLocation("textures/entity/projectiles/tipped_arrow.png");

   public xf(wC manager) {
      super(manager);
   }

   protected ResourceLocation getEntityTexture(Ne entity) {
      return entity.getColor() > 0 ? RES_TIPPED_ARROW : RES_ARROW;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((Ne)var1);
   }
}
