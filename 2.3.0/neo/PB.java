package neo;

import com.google.common.collect.Lists;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;

public class PB extends OL {
   public PB() {
   }

   public EnumActionResult onItemUse(ME player, bij worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (!worldIn.isRemote) {
         Qy itemstack = player.getHeldItem(hand);
         IX entityfireworkrocket = new IX(worldIn, (double)((float)pos.getX() + hitX), (double)((float)pos.getY() + hitY), (double)((float)pos.getZ() + hitZ), itemstack);
         worldIn.spawnEntity(entityfireworkrocket);
         if (!player.capabilities.isCreativeMode) {
            itemstack.shrink(1);
         }
      }

      return EnumActionResult.SUCCESS;
   }

   public ActionResult<Qy> onItemRightClick(bij worldIn, ME playerIn, EnumHand handIn) {
      if (playerIn.isElytraFlying()) {
         Qy itemstack = playerIn.getHeldItem(handIn);
         if (!worldIn.isRemote) {
            IX entityfireworkrocket = new IX(worldIn, itemstack, playerIn);
            worldIn.spawnEntity(entityfireworkrocket);
            if (!playerIn.capabilities.isCreativeMode) {
               itemstack.shrink(1);
            }
         }

         return new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
      } else {
         return new ActionResult(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
      }
   }

   public void addInformation(Qy stack, @Nullable bij worldIn, List<String> tooltip, BJ flagIn) {
      QQ nbttagcompound = stack.getSubCompound("Fireworks");
      if (nbttagcompound != null) {
         if (nbttagcompound.hasKey("Flight", 99)) {
            tooltip.add(I18n.translateToLocal("item.fireworks.flight") + " " + nbttagcompound.getByte("Flight"));
         }

         QW nbttaglist = nbttagcompound.getTagList("Explosions", 10);
         if (!nbttaglist.isEmpty()) {
            for(int i = 0; i < nbttaglist.tagCount(); ++i) {
               QQ nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
               List<String> list = Lists.newArrayList();
               PC.addExplosionInfo(nbttagcompound1, list);
               if (!list.isEmpty()) {
                  for(int j = 1; j < list.size(); ++j) {
                     list.set(j, "  " + (String)list.get(j));
                  }

                  tooltip.addAll(list);
               }
            }
         }
      }

   }
}
