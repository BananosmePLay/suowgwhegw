package neo;

import net.minecraft.util.ResourceLocation;

public class wQ extends ww<Mb> {
   private static final ResourceLocation SHEARED_SHEEP_TEXTURES = new ResourceLocation("textures/entity/sheep/sheep.png");

   public wQ(wC p_i47195_1_) {
      super(p_i47195_1_, new oy(), 0.7F);
      this.addLayer(new vy(this));
   }

   protected ResourceLocation getEntityTexture(Mb entity) {
      return SHEARED_SHEEP_TEXTURES;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((Mb)var1);
   }
}
