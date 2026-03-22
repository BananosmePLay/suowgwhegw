package net.minecraft.item;

import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLeashKnot;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemLead extends Item {
   public ItemLead() {
      this.setCreativeTab(CreativeTabs.TOOLS);
   }

   public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      Block block = worldIn.getBlockState(pos).getBlock();
      if (!(block instanceof BlockFence)) {
         return EnumActionResult.PASS;
      } else {
         if (!worldIn.isRemote) {
            attachToFence(player, worldIn, pos);
         }

         return EnumActionResult.SUCCESS;
      }
   }

   public static boolean attachToFence(EntityPlayer player, World worldIn, BlockPos fence) {
      EntityLeashKnot entityleashknot = EntityLeashKnot.getKnotForPosition(worldIn, fence);
      boolean flag = false;
      double d0 = 7.0;
      int i = fence.getX();
      int j = fence.getY();
      int k = fence.getZ();
      Iterator var10 = worldIn.getEntitiesWithinAABB(EntityLiving.class, new AxisAlignedBB((double)i - 7.0, (double)j - 7.0, (double)k - 7.0, (double)i + 7.0, (double)j + 7.0, (double)k + 7.0)).iterator();

      while(var10.hasNext()) {
         EntityLiving entityliving = (EntityLiving)var10.next();
         if (entityliving.getLeashed() && entityliving.getLeashHolder() == player) {
            if (entityleashknot == null) {
               entityleashknot = EntityLeashKnot.createKnot(worldIn, fence);
            }

            entityliving.setLeashHolder(entityleashknot, true);
            flag = true;
         }
      }

      return flag;
   }
}
