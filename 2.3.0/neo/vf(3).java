package neo;

import net.minecraft.inventory.EntityEquipmentSlot;

public class vf extends vc<nM> {
   public vf(wy<?> rendererIn) {
      super(rendererIn);
   }

   protected void initArmor() {
      this.modelLeggings = new nM(0.5F);
      this.modelArmor = new nM(1.0F);
   }

   protected void setModelSlotVisible(nM p_188359_1_, EntityEquipmentSlot slotIn) {
      this.setModelVisible(p_188359_1_);
      switch (slotIn) {
         case HEAD:
            p_188359_1_.bipedHead.showModel = true;
            p_188359_1_.bipedHeadwear.showModel = true;
            break;
         case CHEST:
            p_188359_1_.bipedBody.showModel = true;
            p_188359_1_.bipedRightArm.showModel = true;
            p_188359_1_.bipedLeftArm.showModel = true;
            break;
         case LEGS:
            p_188359_1_.bipedBody.showModel = true;
            p_188359_1_.bipedRightLeg.showModel = true;
            p_188359_1_.bipedLeftLeg.showModel = true;
            break;
         case FEET:
            p_188359_1_.bipedRightLeg.showModel = true;
            p_188359_1_.bipedLeftLeg.showModel = true;
      }

   }

   protected void setModelVisible(nM model) {
      model.setVisible(false);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void setModelSlotVisible(nH var1, EntityEquipmentSlot var2) {
      this.setModelSlotVisible((nM)var1, var2);
   }
}
