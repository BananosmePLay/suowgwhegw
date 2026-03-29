package neo;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.translation.I18n;

public class PX extends OL {
   public PX() {
      this.setCreativeTab(EN.MISC);
   }

   public String getItemStackDisplayName(Qy stack) {
      String s = ("" + I18n.translateToLocal(this.getTranslationKey() + ".name")).trim();
      String s1 = Ir.getTranslationName(getNamedIdFrom(stack));
      if (s1 != null) {
         s = s + " " + I18n.translateToLocal("entity." + s1 + ".name");
      }

      return s;
   }

   public EnumActionResult onItemUse(ME player, bij worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      Qy itemstack = player.getHeldItem(hand);
      if (worldIn.isRemote) {
         return EnumActionResult.SUCCESS;
      } else if (!player.canPlayerEdit(pos.offset(facing), facing, itemstack)) {
         return EnumActionResult.FAIL;
      } else {
         in iblockstate = worldIn.getBlockState(pos);
         co block = iblockstate.getBlock();
         if (block == Nk.MOB_SPAWNER) {
            Yg tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof YG) {
               Yb mobspawnerbaselogic = ((YG)tileentity).getSpawnerBaseLogic();
               mobspawnerbaselogic.setEntityId(getNamedIdFrom(itemstack));
               tileentity.markDirty();
               worldIn.notifyBlockUpdate(pos, iblockstate, iblockstate, 3);
               if (!player.capabilities.isCreativeMode) {
                  itemstack.shrink(1);
               }

               return EnumActionResult.SUCCESS;
            }
         }

         BlockPos blockpos = pos.offset(facing);
         double d0 = this.getYOffset(worldIn, blockpos);
         Ig entity = spawnCreature(worldIn, getNamedIdFrom(itemstack), (double)blockpos.getX() + 0.5, (double)blockpos.getY() + d0, (double)blockpos.getZ() + 0.5);
         if (entity != null) {
            if (entity instanceof Iw && itemstack.hasDisplayName()) {
               entity.setCustomNameTag(itemstack.getDisplayName());
            }

            applyItemEntityDataToEntity(worldIn, player, itemstack, entity);
            if (!player.capabilities.isCreativeMode) {
               itemstack.shrink(1);
            }
         }

         return EnumActionResult.SUCCESS;
      }
   }

   protected double getYOffset(bij p_190909_1_, BlockPos p_190909_2_) {
      AxisAlignedBB axisalignedbb = (new AxisAlignedBB(p_190909_2_)).expand(0.0, -1.0, 0.0);
      List<AxisAlignedBB> list = p_190909_1_.getCollisionBoxes((Ig)null, axisalignedbb);
      if (list.isEmpty()) {
         return 0.0;
      } else {
         double d0 = axisalignedbb.minY;

         AxisAlignedBB axisalignedbb1;
         for(Iterator var7 = list.iterator(); var7.hasNext(); d0 = Math.max(axisalignedbb1.maxY, d0)) {
            axisalignedbb1 = (AxisAlignedBB)var7.next();
         }

         return d0 - (double)p_190909_2_.getY();
      }
   }

   public static void applyItemEntityDataToEntity(bij entityWorld, @Nullable ME player, Qy stack, @Nullable Ig targetEntity) {
      Xx minecraftserver = entityWorld.getMinecraftServer();
      if (minecraftserver != null && targetEntity != null) {
         QQ nbttagcompound = stack.getTagCompound();
         if (nbttagcompound != null && nbttagcompound.hasKey("EntityTag", 10)) {
            if (!entityWorld.isRemote && targetEntity.ignoreItemEntityData() && (player == null || !minecraftserver.getPlayerList().canSendCommands(player.getGameProfile()))) {
               return;
            }

            QQ nbttagcompound1 = targetEntity.writeToNBT(new QQ());
            UUID uuid = targetEntity.getUniqueID();
            nbttagcompound1.merge(nbttagcompound.getCompoundTag("EntityTag"));
            targetEntity.setUniqueId(uuid);
            targetEntity.readFromNBT(nbttagcompound1);
         }
      }

   }

   public ActionResult<Qy> onItemRightClick(bij worldIn, ME playerIn, EnumHand handIn) {
      Qy itemstack = playerIn.getHeldItem(handIn);
      if (worldIn.isRemote) {
         return new ActionResult(EnumActionResult.PASS, itemstack);
      } else {
         RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, true);
         if (raytraceresult != null && raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK) {
            BlockPos blockpos = raytraceresult.getBlockPos();
            if (!(worldIn.getBlockState(blockpos).getBlock() instanceof eB)) {
               return new ActionResult(EnumActionResult.PASS, itemstack);
            } else if (worldIn.isBlockModifiable(playerIn, blockpos) && playerIn.canPlayerEdit(blockpos, raytraceresult.sideHit, itemstack)) {
               Ig entity = spawnCreature(worldIn, getNamedIdFrom(itemstack), (double)blockpos.getX() + 0.5, (double)blockpos.getY() + 0.5, (double)blockpos.getZ() + 0.5);
               if (entity == null) {
                  return new ActionResult(EnumActionResult.PASS, itemstack);
               } else {
                  if (entity instanceof Iw && itemstack.hasDisplayName()) {
                     entity.setCustomNameTag(itemstack.getDisplayName());
                  }

                  applyItemEntityDataToEntity(worldIn, playerIn, itemstack, entity);
                  if (!playerIn.capabilities.isCreativeMode) {
                     itemstack.shrink(1);
                  }

                  playerIn.addStat(XV.getObjectUseStats(this));
                  return new ActionResult(EnumActionResult.SUCCESS, itemstack);
               }
            } else {
               return new ActionResult(EnumActionResult.FAIL, itemstack);
            }
         } else {
            return new ActionResult(EnumActionResult.PASS, itemstack);
         }
      }
   }

   @Nullable
   public static Ig spawnCreature(bij worldIn, @Nullable ResourceLocation entityID, double x, double y, double z) {
      if (entityID != null && Ir.ENTITY_EGGS.containsKey(entityID)) {
         Ig entity = null;

         for(int i = 0; i < 1; ++i) {
            entity = Ir.createEntityByIDFromName(entityID, worldIn);
            if (entity instanceof Iu) {
               Iu entityliving = (Iu)entity;
               entity.setLocationAndAngles(x, y, z, MathHelper.wrapDegrees(worldIn.rand.nextFloat() * 360.0F), 0.0F);
               entityliving.rotationYawHead = entityliving.rotationYaw;
               entityliving.renderYawOffset = entityliving.rotationYaw;
               entityliving.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityliving)), (ID)null);
               worldIn.spawnEntity(entity);
               entityliving.playLivingSound();
            }
         }

         return entity;
      } else {
         return null;
      }
   }

   public void getSubItems(EN tab, NonNullList<Qy> items) {
      if (this.isInCreativeTab(tab)) {
         Iterator var3 = Ir.ENTITY_EGGS.values().iterator();

         while(var3.hasNext()) {
            Iq entitylist$entityegginfo = (Iq)var3.next();
            Qy itemstack = new Qy(this, 1);
            applyEntityIdToItemStack(itemstack, entitylist$entityegginfo.spawnedID);
            items.add(itemstack);
         }
      }

   }

   public static void applyEntityIdToItemStack(Qy stack, ResourceLocation entityId) {
      QQ nbttagcompound = stack.hasTagCompound() ? stack.getTagCompound() : new QQ();
      QQ nbttagcompound1 = new QQ();
      nbttagcompound1.setString("id", entityId.toString());
      nbttagcompound.setTag("EntityTag", nbttagcompound1);
      stack.setTagCompound(nbttagcompound);
   }

   @Nullable
   public static ResourceLocation getNamedIdFrom(Qy stack) {
      QQ nbttagcompound = stack.getTagCompound();
      if (nbttagcompound == null) {
         return null;
      } else if (!nbttagcompound.hasKey("EntityTag", 10)) {
         return null;
      } else {
         QQ nbttagcompound1 = nbttagcompound.getCompoundTag("EntityTag");
         if (!nbttagcompound1.hasKey("id", 8)) {
            return null;
         } else {
            String s = nbttagcompound1.getString("id");
            ResourceLocation resourcelocation = new ResourceLocation(s);
            if (!s.contains(":")) {
               nbttagcompound1.setString("id", resourcelocation.toString());
            }

            return resourcelocation;
         }
      }
   }
}
