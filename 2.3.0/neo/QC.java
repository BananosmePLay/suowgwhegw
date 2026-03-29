package neo;

import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;

public class QC extends OL {
   public QC() {
      this.setMaxStackSize(1);
   }

   public ActionResult<Qy> onItemRightClick(bij worldIn, ME playerIn, EnumHand handIn) {
      Qy itemstack = playerIn.getHeldItem(handIn);
      playerIn.openBook(itemstack, handIn);
      playerIn.addStat(XV.getObjectUseStats(this));
      return new ActionResult(EnumActionResult.SUCCESS, itemstack);
   }

   public static boolean isNBTValid(QQ nbt) {
      if (nbt == null) {
         return false;
      } else if (!nbt.hasKey("pages", 9)) {
         return false;
      } else {
         QW nbttaglist = nbt.getTagList("pages", 8);

         for(int i = 0; i < nbttaglist.tagCount(); ++i) {
            String s = nbttaglist.getStringTagAt(i);
            if (s.length() > 32767) {
               return false;
            }
         }

         return true;
      }
   }
}
