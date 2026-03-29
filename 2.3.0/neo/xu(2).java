package neo;

import net.minecraft.util.ResourceLocation;

public class xu extends vP<Ll> {
   private static final ResourceLocation ZOMBIE_VILLAGER_TEXTURES = new ResourceLocation("textures/entity/zombie_villager/zombie_villager.png");
   private static final ResourceLocation ZOMBIE_VILLAGER_FARMER_LOCATION = new ResourceLocation("textures/entity/zombie_villager/zombie_farmer.png");
   private static final ResourceLocation ZOMBIE_VILLAGER_LIBRARIAN_LOC = new ResourceLocation("textures/entity/zombie_villager/zombie_librarian.png");
   private static final ResourceLocation ZOMBIE_VILLAGER_PRIEST_LOCATION = new ResourceLocation("textures/entity/zombie_villager/zombie_priest.png");
   private static final ResourceLocation ZOMBIE_VILLAGER_SMITH_LOCATION = new ResourceLocation("textures/entity/zombie_villager/zombie_smith.png");
   private static final ResourceLocation ZOMBIE_VILLAGER_BUTCHER_LOCATION = new ResourceLocation("textures/entity/zombie_villager/zombie_butcher.png");

   public xu(wC p_i47186_1_) {
      super(p_i47186_1_, new oQ(), 0.5F);
      this.addLayer(new vD(this));
   }

   protected ResourceLocation getEntityTexture(Ll entity) {
      switch (entity.getProfession()) {
         case 0:
            return ZOMBIE_VILLAGER_FARMER_LOCATION;
         case 1:
            return ZOMBIE_VILLAGER_LIBRARIAN_LOC;
         case 2:
            return ZOMBIE_VILLAGER_PRIEST_LOCATION;
         case 3:
            return ZOMBIE_VILLAGER_SMITH_LOCATION;
         case 4:
            return ZOMBIE_VILLAGER_BUTCHER_LOCATION;
         case 5:
         default:
            return ZOMBIE_VILLAGER_TEXTURES;
      }
   }

   protected void applyRotations(Ll entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      if (entityLiving.isConverting()) {
         rotationYaw += (float)(Math.cos((double)entityLiving.ticksExisted * 3.25) * Math.PI * 0.25);
      }

      super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Iu var1) {
      return this.getEntityTexture((Ll)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void applyRotations(Iw var1, float var2, float var3, float var4) {
      this.applyRotations((Ll)var1, var2, var3, var4);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((Ll)var1);
   }
}
