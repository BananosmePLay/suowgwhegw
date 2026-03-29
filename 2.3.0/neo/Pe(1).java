package neo;

import javax.annotation.Nullable;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;

public class Pe extends OL {
   private final co containedBlock;

   public Pe(co containedBlockIn) {
      this.maxStackSize = 1;
      this.containedBlock = containedBlockIn;
      this.setCreativeTab(EN.MISC);
   }

   public ActionResult<Qy> onItemRightClick(bij worldIn, ME playerIn, EnumHand handIn) {
      boolean flag = this.containedBlock == Nk.AIR;
      Qy itemstack = playerIn.getHeldItem(handIn);
      RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, flag);
      if (raytraceresult == null) {
         return new ActionResult(EnumActionResult.PASS, itemstack);
      } else if (raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK) {
         return new ActionResult(EnumActionResult.PASS, itemstack);
      } else {
         BlockPos blockpos = raytraceresult.getBlockPos();
         if (!worldIn.isBlockModifiable(playerIn, blockpos)) {
            return new ActionResult(EnumActionResult.FAIL, itemstack);
         } else if (flag) {
            if (!playerIn.canPlayerEdit(blockpos.offset(raytraceresult.sideHit), raytraceresult.sideHit, itemstack)) {
               return new ActionResult(EnumActionResult.FAIL, itemstack);
            } else {
               in iblockstate = worldIn.getBlockState(blockpos);
               hM material = iblockstate.getMaterial();
               if (material == hM.WATER && (Integer)iblockstate.getValue(eB.LEVEL) == 0) {
                  worldIn.setBlockState(blockpos, Nk.AIR.getDefaultState(), 11);
                  playerIn.addStat(XV.getObjectUseStats(this));
                  playerIn.playSound(NO.ITEM_BUCKET_FILL, 1.0F, 1.0F);
                  return new ActionResult(EnumActionResult.SUCCESS, this.fillBucket(itemstack, playerIn, NK.WATER_BUCKET));
               } else if (material == hM.LAVA && (Integer)iblockstate.getValue(eB.LEVEL) == 0) {
                  playerIn.playSound(NO.ITEM_BUCKET_FILL_LAVA, 1.0F, 1.0F);
                  worldIn.setBlockState(blockpos, Nk.AIR.getDefaultState(), 11);
                  playerIn.addStat(XV.getObjectUseStats(this));
                  return new ActionResult(EnumActionResult.SUCCESS, this.fillBucket(itemstack, playerIn, NK.LAVA_BUCKET));
               } else {
                  return new ActionResult(EnumActionResult.FAIL, itemstack);
               }
            }
         } else {
            boolean flag1 = worldIn.getBlockState(blockpos).getBlock().isReplaceable(worldIn, blockpos);
            BlockPos blockpos1 = flag1 && raytraceresult.sideHit == EnumFacing.UP ? blockpos : blockpos.offset(raytraceresult.sideHit);
            if (!playerIn.canPlayerEdit(blockpos1, raytraceresult.sideHit, itemstack)) {
               return new ActionResult(EnumActionResult.FAIL, itemstack);
            } else if (this.tryPlaceContainedLiquid(playerIn, worldIn, blockpos1)) {
               if (playerIn instanceof MG) {
                  bY.PLACED_BLOCK.trigger((MG)playerIn, blockpos1, itemstack);
               }

               playerIn.addStat(XV.getObjectUseStats(this));
               return !playerIn.capabilities.isCreativeMode ? new ActionResult(EnumActionResult.SUCCESS, new Qy(NK.BUCKET)) : new ActionResult(EnumActionResult.SUCCESS, itemstack);
            } else {
               return new ActionResult(EnumActionResult.FAIL, itemstack);
            }
         }
      }
   }

   private Qy fillBucket(Qy emptyBuckets, ME player, OL fullBucket) {
      if (player.capabilities.isCreativeMode) {
         return emptyBuckets;
      } else {
         emptyBuckets.shrink(1);
         if (emptyBuckets.isEmpty()) {
            return new Qy(fullBucket);
         } else {
            if (!player.inventory.addItemStackToInventory(new Qy(fullBucket))) {
               player.dropItem(new Qy(fullBucket), false);
            }

            return emptyBuckets;
         }
      }
   }

   public boolean tryPlaceContainedLiquid(@Nullable ME player, bij worldIn, BlockPos posIn) {
      if (this.containedBlock == Nk.AIR) {
         return false;
      } else {
         in iblockstate = worldIn.getBlockState(posIn);
         hM material = iblockstate.getMaterial();
         boolean flag = !material.isSolid();
         boolean flag1 = iblockstate.getBlock().isReplaceable(worldIn, posIn);
         if (!worldIn.isAirBlock(posIn) && !flag && !flag1) {
            return false;
         } else {
            if (worldIn.provider.doesWaterVaporize() && this.containedBlock == Nk.FLOWING_WATER) {
               int l = posIn.getX();
               int i = posIn.getY();
               int j = posIn.getZ();
               worldIn.playSound(player, posIn, NO.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);

               for(int k = 0; k < 8; ++k) {
                  worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, (double)l + Math.random(), (double)i + Math.random(), (double)j + Math.random(), 0.0, 0.0, 0.0);
               }
            } else {
               if (!worldIn.isRemote && (flag || flag1) && !material.isLiquid()) {
                  worldIn.destroyBlock(posIn, true);
               }

               SoundEvent soundevent = this.containedBlock == Nk.FLOWING_LAVA ? NO.ITEM_BUCKET_EMPTY_LAVA : NO.ITEM_BUCKET_EMPTY;
               worldIn.playSound(player, posIn, soundevent, SoundCategory.BLOCKS, 1.0F, 1.0F);
               worldIn.setBlockState(posIn, this.containedBlock.getDefaultState(), 11);
            }

            return true;
         }
      }
   }
}
