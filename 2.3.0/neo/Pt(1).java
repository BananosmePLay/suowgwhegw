package neo;

import javax.annotation.Nullable;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;

public class Pt extends OL {
   public Pt() {
      this.maxStackSize = 1;
      this.setMaxDamage(432);
      this.setCreativeTab(EN.TRANSPORTATION);
      this.addPropertyOverride(new ResourceLocation("broken"), new Oo() {
         public float apply(Qy stack, @Nullable bij worldIn, @Nullable Iw entityIn) {
            return Pt.isUsable(stack) ? 0.0F : 1.0F;
         }
      });
      dk.DISPENSE_BEHAVIOR_REGISTRY.putObject(this, OR.DISPENSER_BEHAVIOR);
   }

   public static boolean isUsable(Qy stack) {
      return stack.getItemDamage() < stack.getMaxDamage() - 1;
   }

   public boolean getIsRepairable(Qy toRepair, Qy repair) {
      return repair.getItem() == NK.LEATHER;
   }

   public ActionResult<Qy> onItemRightClick(bij worldIn, ME playerIn, EnumHand handIn) {
      Qy itemstack = playerIn.getHeldItem(handIn);
      EntityEquipmentSlot entityequipmentslot = Iu.getSlotForItemStack(itemstack);
      Qy itemstack1 = playerIn.getItemStackFromSlot(entityequipmentslot);
      if (itemstack1.isEmpty()) {
         playerIn.setItemStackToSlot(entityequipmentslot, itemstack.copy());
         itemstack.setCount(0);
         return new ActionResult(EnumActionResult.SUCCESS, itemstack);
      } else {
         return new ActionResult(EnumActionResult.FAIL, itemstack);
      }
   }
}
