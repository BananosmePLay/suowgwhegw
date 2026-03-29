package neo;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PO extends OL {
   private static final Logger LOGGER = LogManager.getLogger();

   public PO() {
      this.setMaxStackSize(1);
   }

   public ActionResult<Qy> onItemRightClick(bij worldIn, ME playerIn, EnumHand handIn) {
      Qy itemstack = playerIn.getHeldItem(handIn);
      QQ nbttagcompound = itemstack.getTagCompound();
      if (!playerIn.capabilities.isCreativeMode) {
         playerIn.setHeldItem(handIn, Qy.EMPTY);
      }

      if (nbttagcompound != null && nbttagcompound.hasKey("Recipes", 9)) {
         if (!worldIn.isRemote) {
            QW nbttaglist = nbttagcompound.getTagList("Recipes", 8);
            List<NT> list = Lists.newArrayList();

            for(int i = 0; i < nbttaglist.tagCount(); ++i) {
               String s = nbttaglist.getStringTagAt(i);
               NT irecipe = NP.getRecipe(new ResourceLocation(s));
               if (irecipe == null) {
                  LOGGER.error("Invalid recipe: " + s);
                  return new ActionResult(EnumActionResult.FAIL, itemstack);
               }

               list.add(irecipe);
            }

            playerIn.unlockRecipes((List)list);
            playerIn.addStat(XV.getObjectUseStats(this));
         }

         return new ActionResult(EnumActionResult.SUCCESS, itemstack);
      } else {
         LOGGER.error("Tag not valid: " + nbttagcompound);
         return new ActionResult(EnumActionResult.FAIL, itemstack);
      }
   }
}
