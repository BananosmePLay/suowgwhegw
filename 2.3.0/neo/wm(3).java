package neo;

import net.minecraft.util.ResourceLocation;

public class wm extends ww<JX> {
   private static final ResourceLocation ZOMBIE_TEXTURES = new ResourceLocation("textures/entity/zombie/zombie.png");
   private final float scale;

   public wm(wC p_i47206_1_, float scaleIn) {
      super(p_i47206_1_, new oP(), 0.5F * scaleIn);
      this.scale = scaleIn;
      this.addLayer(new vr(this));
      this.addLayer(new vf(this) {
         protected void initArmor() {
            this.modelLeggings = new oP(0.5F, true);
            this.modelArmor = new oP(1.0F, true);
         }
      });
   }

   public void transformHeldFull3DItemLayer() {
      yh.translate(0.0F, 0.1875F, 0.0F);
   }

   protected void preRenderCallback(JX entitylivingbaseIn, float partialTickTime) {
      yh.scale(this.scale, this.scale, this.scale);
   }

   protected ResourceLocation getEntityTexture(JX entity) {
      return ZOMBIE_TEXTURES;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void preRenderCallback(Iw var1, float var2) {
      this.preRenderCallback((JX)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((JX)var1);
   }
}
