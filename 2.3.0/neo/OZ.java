package neo;

import java.util.List;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

public class OZ extends OL {
   private final IQ type;

   public OZ(IQ typeIn) {
      this.type = typeIn;
      this.maxStackSize = 1;
      this.setCreativeTab(EN.TRANSPORTATION);
      this.setTranslationKey("boat." + typeIn.getName());
   }

   public ActionResult<Qy> onItemRightClick(bij worldIn, ME playerIn, EnumHand handIn) {
      Qy itemstack = playerIn.getHeldItem(handIn);
      float f = 1.0F;
      float f1 = playerIn.prevRotationPitch + (playerIn.rotationPitch - playerIn.prevRotationPitch) * 1.0F;
      float f2 = playerIn.prevRotationYaw + (playerIn.rotationYaw - playerIn.prevRotationYaw) * 1.0F;
      double d0 = playerIn.prevPosX + (playerIn.posX - playerIn.prevPosX) * 1.0;
      double d1 = playerIn.prevPosY + (playerIn.posY - playerIn.prevPosY) * 1.0 + (double)playerIn.getEyeHeight();
      double d2 = playerIn.prevPosZ + (playerIn.posZ - playerIn.prevPosZ) * 1.0;
      Vec3d vec3d = new Vec3d(d0, d1, d2);
      float f3 = MathHelper.cos(-f2 * 0.017453292F - 3.1415927F);
      float f4 = MathHelper.sin(-f2 * 0.017453292F - 3.1415927F);
      float f5 = -MathHelper.cos(-f1 * 0.017453292F);
      float f6 = MathHelper.sin(-f1 * 0.017453292F);
      float f7 = f4 * f5;
      float f8 = f3 * f5;
      double d3 = 5.0;
      Vec3d vec3d1 = vec3d.add((double)f7 * 5.0, (double)f6 * 5.0, (double)f8 * 5.0);
      RayTraceResult raytraceresult = worldIn.rayTraceBlocks(vec3d, vec3d1, true);
      if (raytraceresult == null) {
         return new ActionResult(EnumActionResult.PASS, itemstack);
      } else {
         Vec3d vec3d2 = playerIn.getLook(1.0F);
         boolean flag = false;
         List<Ig> list = worldIn.getEntitiesWithinAABBExcludingEntity(playerIn, playerIn.getEntityBoundingBox().expand(vec3d2.x * 5.0, vec3d2.y * 5.0, vec3d2.z * 5.0).grow(1.0));

         for(int i = 0; i < list.size(); ++i) {
            Ig entity = (Ig)list.get(i);
            if (entity.canBeCollidedWith()) {
               AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().grow((double)entity.getCollisionBorderSize());
               if (axisalignedbb.contains(vec3d)) {
                  flag = true;
               }
            }
         }

         if (flag) {
            return new ActionResult(EnumActionResult.PASS, itemstack);
         } else if (raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK) {
            return new ActionResult(EnumActionResult.PASS, itemstack);
         } else {
            co block = worldIn.getBlockState(raytraceresult.getBlockPos()).getBlock();
            boolean flag1 = block == Nk.WATER || block == Nk.FLOWING_WATER;
            IR entityboat = new IR(worldIn, raytraceresult.hitVec.x, flag1 ? raytraceresult.hitVec.y - 0.12 : raytraceresult.hitVec.y, raytraceresult.hitVec.z);
            entityboat.setBoatType(this.type);
            entityboat.rotationYaw = playerIn.rotationYaw;
            if (!worldIn.getCollisionBoxes(entityboat, entityboat.getEntityBoundingBox().grow(-0.1)).isEmpty()) {
               return new ActionResult(EnumActionResult.FAIL, itemstack);
            } else {
               if (!worldIn.isRemote) {
                  worldIn.spawnEntity(entityboat);
               }

               if (!playerIn.capabilities.isCreativeMode) {
                  itemstack.shrink(1);
               }

               playerIn.addStat(XV.getObjectUseStats(this));
               return new ActionResult(EnumActionResult.SUCCESS, itemstack);
            }
         }
      }
   }
}
