package neo;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;

public class vk implements vw<Iw> {
   private static final ResourceLocation TEXTURE_ELYTRA = new ResourceLocation("textures/entity/elytra.png");
   protected final wy<?> renderPlayer;
   private final nX modelElytra = new nX();

   public vk(wy<?> p_i47185_1_) {
      this.renderPlayer = p_i47185_1_;
   }

   public void doRenderLayer(Iw entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      Qy itemstack = entitylivingbaseIn.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
      if (itemstack.getItem() == NK.ELYTRA) {
         yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         yh.enableBlend();
         yh.blendFunc(ya.ONE, xR.ZERO);
         if (entitylivingbaseIn instanceof jf) {
            jf abstractclientplayer = (jf)entitylivingbaseIn;
            if (abstractclientplayer.isPlayerInfoSet() && abstractclientplayer.getLocationElytra() != null) {
               this.renderPlayer.bindTexture(abstractclientplayer.getLocationElytra());
            } else if (abstractclientplayer.hasElytraCape() && abstractclientplayer.hasPlayerInfo() && abstractclientplayer.getLocationCape() != null && abstractclientplayer.isWearing(MH.CAPE)) {
               this.renderPlayer.bindTexture(abstractclientplayer.getLocationCape());
            } else {
               ResourceLocation resourcelocation1 = TEXTURE_ELYTRA;
               if (XH.isCustomItems()) {
                  resourcelocation1 = bjG.getCustomElytraTexture(itemstack, resourcelocation1);
               }

               this.renderPlayer.bindTexture(resourcelocation1);
            }
         } else {
            ResourceLocation resourcelocation = TEXTURE_ELYTRA;
            if (XH.isCustomItems()) {
               resourcelocation = bjG.getCustomElytraTexture(itemstack, resourcelocation);
            }

            this.renderPlayer.bindTexture(resourcelocation);
         }

         yh.pushMatrix();
         yh.translate(0.0F, 0.0F, 0.125F);
         this.modelElytra.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entitylivingbaseIn);
         this.modelElytra.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
         if (itemstack.isItemEnchanted()) {
            vc.renderEnchantedGlint(this.renderPlayer, entitylivingbaseIn, this.modelElytra, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
         }

         yh.disableBlend();
         yh.popMatrix();
      }

   }

   public boolean shouldCombineTextures() {
      return false;
   }
}
