package neo;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;

public class Qm extends OL {
   public Qm() {
      this.maxStackSize = 1;
      this.setCreativeTab(EN.COMBAT);
      this.setMaxDamage(336);
      this.addPropertyOverride(new ResourceLocation("blocking"), new Oo() {
         public float apply(Qy stack, @Nullable bij worldIn, @Nullable Iw entityIn) {
            return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
         }
      });
      dk.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, OR.DISPENSER_BEHAVIOR);
   }

   public String getItemStackDisplayName(Qy stack) {
      if (stack.getSubCompound("BlockEntityTag") != null) {
         Om enumdyecolor = Yh.getColor(stack);
         return I18n.translateToLocal("item.shield." + enumdyecolor.getTranslationKey() + ".name");
      } else {
         return I18n.translateToLocal("item.shield.name");
      }
   }

   public void addInformation(Qy stack, @Nullable bij worldIn, List<String> tooltip, BJ flagIn) {
      OV.appendHoverTextFromTileEntityTag(stack, tooltip);
   }

   public Ol getItemUseAction(Qy stack) {
      return Ol.BLOCK;
   }

   public int getMaxItemUseDuration(Qy stack) {
      return 72000;
   }

   public ActionResult<Qy> onItemRightClick(bij worldIn, ME playerIn, EnumHand handIn) {
      Qy itemstack = playerIn.getHeldItem(handIn);
      playerIn.setActiveHand(handIn);
      return new ActionResult(EnumActionResult.SUCCESS, itemstack);
   }

   public boolean getIsRepairable(Qy toRepair, Qy repair) {
      return repair.getItem() == OL.getItemFromBlock(Nk.PLANKS) ? true : super.getIsRepairable(toRepair, repair);
   }
}
