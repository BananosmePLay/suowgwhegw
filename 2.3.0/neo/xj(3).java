package neo;

import net.minecraft.util.ResourceLocation;

public class xj extends ww<Mq> {
   private static final ResourceLocation VILLAGER_TEXTURES = new ResourceLocation("textures/entity/villager/villager.png");
   private static final ResourceLocation FARMER_VILLAGER_TEXTURES = new ResourceLocation("textures/entity/villager/farmer.png");
   private static final ResourceLocation LIBRARIAN_VILLAGER_TEXTURES = new ResourceLocation("textures/entity/villager/librarian.png");
   private static final ResourceLocation PRIEST_VILLAGER_TEXTURES = new ResourceLocation("textures/entity/villager/priest.png");
   private static final ResourceLocation SMITH_VILLAGER_TEXTURES = new ResourceLocation("textures/entity/villager/smith.png");
   private static final ResourceLocation BUTCHER_VILLAGER_TEXTURES = new ResourceLocation("textures/entity/villager/butcher.png");

   public xj(wC renderManagerIn) {
      super(renderManagerIn, new oL(0.0F), 0.5F);
      this.addLayer(new vi(this.getMainModel().villagerHead));
   }

   public oL getMainModel() {
      return (oL)super.getMainModel();
   }

   protected ResourceLocation getEntityTexture(Mq entity) {
      switch (entity.getProfession()) {
         case 0:
            return FARMER_VILLAGER_TEXTURES;
         case 1:
            return LIBRARIAN_VILLAGER_TEXTURES;
         case 2:
            return PRIEST_VILLAGER_TEXTURES;
         case 3:
            return SMITH_VILLAGER_TEXTURES;
         case 4:
            return BUTCHER_VILLAGER_TEXTURES;
         case 5:
         default:
            return VILLAGER_TEXTURES;
      }
   }

   protected void preRenderCallback(Mq entitylivingbaseIn, float partialTickTime) {
      float f = 0.9375F;
      if (entitylivingbaseIn.getGrowingAge() < 0) {
         f = (float)((double)f * 0.5);
         this.shadowSize = 0.25F;
      } else {
         this.shadowSize = 0.5F;
      }

      yh.scale(f, f, f);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void preRenderCallback(Iw var1, float var2) {
      this.preRenderCallback((Mq)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public nH getMainModel() {
      return this.getMainModel();
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((Mq)var1);
   }
}
