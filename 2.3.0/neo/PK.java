package neo;

import com.google.common.base.Predicate;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;

public class PK extends OL {
   public PK() {
      this.setCreativeTab(EN.BREWING);
   }

   public ActionResult<Qy> onItemRightClick(bij worldIn, ME playerIn, EnumHand handIn) {
      List<Ii> list = worldIn.getEntitiesWithinAABB(Ii.class, playerIn.getEntityBoundingBox().grow(2.0), new Predicate<Ii>() {
         public boolean apply(@Nullable Ii p_apply_1_) {
            return p_apply_1_ != null && p_apply_1_.isEntityAlive() && p_apply_1_.getOwner() instanceof HS;
         }

         // $FF: synthetic method
         // $FF: bridge method
         public boolean apply(@Nullable Object var1) {
            return this.apply((Ii)var1);
         }
      });
      Qy itemstack = playerIn.getHeldItem(handIn);
      if (!list.isEmpty()) {
         Ii entityareaeffectcloud = (Ii)list.get(0);
         entityareaeffectcloud.setRadius(entityareaeffectcloud.getRadius() - 0.5F);
         worldIn.playSound((ME)null, playerIn.posX, playerIn.posY, playerIn.posZ, NO.ITEM_BOTTLE_FILL_DRAGONBREATH, SoundCategory.NEUTRAL, 1.0F, 1.0F);
         return new ActionResult(EnumActionResult.SUCCESS, this.turnBottleIntoItem(itemstack, playerIn, new Qy(NK.DRAGON_BREATH)));
      } else {
         RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, true);
         if (raytraceresult == null) {
            return new ActionResult(EnumActionResult.PASS, itemstack);
         } else {
            if (raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK) {
               BlockPos blockpos = raytraceresult.getBlockPos();
               if (!worldIn.isBlockModifiable(playerIn, blockpos) || !playerIn.canPlayerEdit(blockpos.offset(raytraceresult.sideHit), raytraceresult.sideHit, itemstack)) {
                  return new ActionResult(EnumActionResult.PASS, itemstack);
               }

               if (worldIn.getBlockState(blockpos).getMaterial() == hM.WATER) {
                  worldIn.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, NO.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                  return new ActionResult(EnumActionResult.SUCCESS, this.turnBottleIntoItem(itemstack, playerIn, Wg.addPotionToItemStack(new Qy(NK.POTIONITEM), NN.WATER)));
               }
            }

            return new ActionResult(EnumActionResult.PASS, itemstack);
         }
      }
   }

   protected Qy turnBottleIntoItem(Qy p_185061_1_, ME player, Qy stack) {
      p_185061_1_.shrink(1);
      player.addStat(XV.getObjectUseStats(this));
      if (p_185061_1_.isEmpty()) {
         return stack;
      } else {
         if (!player.inventory.addItemStackToInventory(stack)) {
            player.dropItem(stack, false);
         }

         return p_185061_1_;
      }
   }
}
