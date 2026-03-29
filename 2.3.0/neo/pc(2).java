package neo;

import io.netty.buffer.Unpooled;
import net.minecraft.inventory.ClickType;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

public class pc {
   private final nC mc;
   private final py connection;
   private BlockPos currentBlock = new BlockPos(-1, -1, -1);
   private Qy currentItemHittingBlock;
   private float curBlockDamageMP;
   private float stepSoundTickCounter;
   private int blockHitDelay;
   private boolean isHittingBlock;
   private bbb currentGameType;
   private int currentPlayerItem;

   public pc(nC mcIn, py netHandler) {
      this.currentItemHittingBlock = Qy.EMPTY;
      this.currentGameType = bbb.SURVIVAL;
      this.mc = mcIn;
      this.connection = netHandler;
   }

   public static void clickBlockCreative(nC mcIn, pc playerController, BlockPos pos, EnumFacing facing) {
      if (!mcIn.world.extinguishFire(nC.player, pos, facing)) {
         playerController.onPlayerDestroyBlock(pos);
      }

   }

   public void setPlayerCapabilities(ME player) {
      this.currentGameType.configurePlayerCapabilities(player.capabilities);
   }

   public boolean isSpectator() {
      return this.currentGameType == bbb.SPECTATOR;
   }

   public void setGameType(bbb type) {
      this.currentGameType = type;
      nC var10001 = this.mc;
      this.currentGameType.configurePlayerCapabilities(nC.player.capabilities);
   }

   public void flipPlayer(ME playerIn) {
      playerIn.rotationYaw = -180.0F;
   }

   public boolean shouldDrawHUD() {
      return this.currentGameType.isSurvivalOrAdventure();
   }

   public boolean onPlayerDestroyBlock(BlockPos pos) {
      nC var10000;
      if (this.currentGameType.hasLimitedInteractions()) {
         if (this.currentGameType == bbb.SPECTATOR) {
            return false;
         }

         var10000 = this.mc;
         if (!nC.player.isAllowEdit()) {
            var10000 = this.mc;
            Qy itemstack = nC.player.getHeldItemMainhand();
            if (itemstack.isEmpty()) {
               return false;
            }

            if (!itemstack.canDestroy(this.mc.world.getBlockState(pos).getBlock())) {
               return false;
            }
         }
      }

      if (this.currentGameType.isCreative()) {
         var10000 = this.mc;
         if (!nC.player.getHeldItemMainhand().isEmpty()) {
            var10000 = this.mc;
            if (nC.player.getHeldItemMainhand().getItem() instanceof Qz) {
               return false;
            }
         }
      }

      bij world = this.mc.world;
      in iblockstate = ((bij)world).getBlockState(pos);
      co block = iblockstate.getBlock();
      if (block instanceof da || block instanceof hh) {
         var10000 = this.mc;
         if (!nC.player.canUseCommandBlock()) {
            return false;
         }
      }

      if (iblockstate.getMaterial() == hM.AIR) {
         return false;
      } else {
         ((bij)world).playEvent(2001, pos, co.getStateId(iblockstate));
         nC var10004 = this.mc;
         block.onBlockHarvested(world, pos, iblockstate, nC.player);
         boolean flag = ((bij)world).setBlockState(pos, Nk.AIR.getDefaultState(), 11);
         if (flag) {
            block.onPlayerDestroy(world, pos, iblockstate);
         }

         this.currentBlock = new BlockPos(this.currentBlock.getX(), -1, this.currentBlock.getZ());
         if (!this.currentGameType.isCreative()) {
            var10000 = this.mc;
            Qy itemstack1 = nC.player.getHeldItemMainhand();
            if (!itemstack1.isEmpty()) {
               var10004 = this.mc;
               itemstack1.onBlockDestroyed(world, iblockstate, pos, nC.player);
               if (itemstack1.isEmpty()) {
                  var10000 = this.mc;
                  nC.player.setHeldItem(EnumHand.MAIN_HAND, Qy.EMPTY);
               }
            }
         }

         return flag;
      }
   }

   public void attackEntityNoEvent(ME playerIn, Ig targetEntity) {
      this.syncCurrentPlayItem();
      this.connection.sendPacket(new Tp(targetEntity));
      if (this.currentGameType != bbb.SPECTATOR) {
         playerIn.attackTargetEntityWithCurrentItem(targetEntity);
         playerIn.resetCooldown();
      }

   }

   public boolean clickBlock(BlockPos loc, EnumFacing face) {
      if (this.currentGameType.hasLimitedInteractions()) {
         if (this.currentGameType == bbb.SPECTATOR) {
            return false;
         }

         nC var10000 = this.mc;
         if (!nC.player.isAllowEdit()) {
            var10000 = this.mc;
            Qy itemstack = nC.player.getHeldItemMainhand();
            if (itemstack.isEmpty()) {
               return false;
            }

            if (!itemstack.canDestroy(this.mc.world.getBlockState(loc).getBlock())) {
               return false;
            }
         }
      }

      if (!this.mc.world.getWorldBorder().contains(loc)) {
         return false;
      } else {
         if (this.currentGameType.isCreative()) {
            this.mc.getTutorial().onHitBlock(this.mc.world, loc, this.mc.world.getBlockState(loc), 1.0F);
            this.connection.sendPacket(new Tb(Ta.START_DESTROY_BLOCK, loc, face));
            clickBlockCreative(this.mc, this, loc, face);
            this.blockHitDelay = 5;
         } else if (!this.isHittingBlock || !this.isHittingPosition(loc)) {
            if (this.isHittingBlock) {
               this.connection.sendPacket(new Tb(Ta.ABORT_DESTROY_BLOCK, this.currentBlock, face));
            }

            in iblockstate = this.mc.world.getBlockState(loc);
            this.mc.getTutorial().onHitBlock(this.mc.world, loc, iblockstate, 0.0F);
            this.connection.sendPacket(new Tb(Ta.START_DESTROY_BLOCK, loc, face));
            boolean flag = iblockstate.getMaterial() != hM.AIR;
            if (flag && this.curBlockDamageMP == 0.0F) {
               co var6 = iblockstate.getBlock();
               nC var10003 = this.mc;
               var6.onBlockClicked(this.mc.world, loc, nC.player);
            }

            nC var10001;
            if (flag) {
               var10001 = this.mc;
               nC var10002 = this.mc;
               if (iblockstate.getPlayerRelativeBlockHardness(nC.player, nC.player.world, loc) >= 1.0F) {
                  this.onPlayerDestroyBlock(loc);
                  return true;
               }
            }

            this.isHittingBlock = true;
            this.currentBlock = loc;
            var10001 = this.mc;
            this.currentItemHittingBlock = nC.player.getHeldItemMainhand();
            this.curBlockDamageMP = 0.0F;
            this.stepSoundTickCounter = 0.0F;
            var10001 = this.mc;
            this.mc.world.sendBlockBreakProgress(nC.player.getEntityId(), this.currentBlock, (int)(this.curBlockDamageMP * 10.0F) - 1);
         }

         return true;
      }
   }

   public void resetBlockRemoving() {
      if (this.isHittingBlock) {
         this.mc.getTutorial().onHitBlock(this.mc.world, this.currentBlock, this.mc.world.getBlockState(this.currentBlock), -1.0F);
         this.connection.sendPacket(new Tb(Ta.ABORT_DESTROY_BLOCK, this.currentBlock, EnumFacing.DOWN));
         this.isHittingBlock = false;
         this.curBlockDamageMP = 0.0F;
         nC var10001 = this.mc;
         this.mc.world.sendBlockBreakProgress(nC.player.getEntityId(), this.currentBlock, -1);
         nC var10000 = this.mc;
         nC.player.resetCooldown();
      }

   }

   public boolean onPlayerDamageBlock(BlockPos posBlock, EnumFacing directionFacing) {
      this.syncCurrentPlayItem();
      0di eventBlockInteract = new 0di(posBlock, directionFacing);
      0bz.method_Qm().method_Qn().post(eventBlockInteract);
      if (eventBlockInteract.method_bzU()) {
         return false;
      } else if (this.blockHitDelay > 0) {
         --this.blockHitDelay;
         return true;
      } else if (this.currentGameType.isCreative() && this.mc.world.getWorldBorder().contains(posBlock)) {
         this.blockHitDelay = 5;
         this.mc.getTutorial().onHitBlock(this.mc.world, posBlock, this.mc.world.getBlockState(posBlock), 1.0F);
         this.connection.sendPacket(new Tb(Ta.START_DESTROY_BLOCK, posBlock, directionFacing));
         clickBlockCreative(this.mc, this, posBlock, directionFacing);
         return true;
      } else if (this.isHittingPosition(posBlock)) {
         in iblockstate = this.mc.world.getBlockState(posBlock);
         co block = iblockstate.getBlock();
         if (iblockstate.getMaterial() == hM.AIR) {
            this.isHittingBlock = false;
            return false;
         } else {
            nC var10003 = this.mc;
            nC var10004 = this.mc;
            this.curBlockDamageMP += iblockstate.getPlayerRelativeBlockHardness(nC.player, nC.player.world, posBlock);
            if (this.stepSoundTickCounter % 4.0F == 0.0F) {
               ia soundtype = block.getSoundType();
               this.mc.getSoundHandler().playSound(new iN(soundtype.getHitSound(), SoundCategory.NEUTRAL, (soundtype.getVolume() + 1.0F) / 8.0F, soundtype.getPitch() * 0.5F, posBlock));
            }

            ++this.stepSoundTickCounter;
            this.mc.getTutorial().onHitBlock(this.mc.world, posBlock, iblockstate, MathHelper.clamp(this.curBlockDamageMP, 0.0F, 1.0F));
            if (this.curBlockDamageMP >= 1.0F) {
               this.isHittingBlock = false;
               this.connection.sendPacket(new Tb(Ta.STOP_DESTROY_BLOCK, posBlock, directionFacing));
               this.onPlayerDestroyBlock(posBlock);
               this.curBlockDamageMP = 0.0F;
               this.stepSoundTickCounter = 0.0F;
               this.blockHitDelay = 5;
            }

            nC var10001 = this.mc;
            this.mc.world.sendBlockBreakProgress(nC.player.getEntityId(), this.currentBlock, (int)(this.curBlockDamageMP * 10.0F) - 1);
            return true;
         }
      } else {
         return this.clickBlock(posBlock, directionFacing);
      }
   }

   public float getBlockReachDistance() {
      return this.currentGameType.isCreative() ? 5.0F : 4.5F;
   }

   public void updateController() {
      this.syncCurrentPlayItem();
      if (this.connection.getNetworkManager().isChannelOpen()) {
         this.connection.getNetworkManager().processReceivedPackets();
      } else {
         this.connection.getNetworkManager().handleDisconnection();
      }

   }

   private boolean isHittingPosition(BlockPos pos) {
      nC var10000 = this.mc;
      Qy itemstack = nC.player.getHeldItemMainhand();
      boolean flag = this.currentItemHittingBlock.isEmpty() && itemstack.isEmpty();
      if (!this.currentItemHittingBlock.isEmpty() && !itemstack.isEmpty()) {
         flag = itemstack.getItem() == this.currentItemHittingBlock.getItem() && Qy.areItemStackTagsEqual(itemstack, this.currentItemHittingBlock) && (itemstack.isItemStackDamageable() || itemstack.getMetadata() == this.currentItemHittingBlock.getMetadata());
      }

      return pos.equals(this.currentBlock) && flag;
   }

   private void syncCurrentPlayItem() {
      nC var10000 = this.mc;
      int i = nC.player.inventory.currentItem;
      if (i != this.currentPlayerItem) {
         this.currentPlayerItem = i;
         this.connection.sendPacket(new SR(this.currentPlayerItem));
      }

   }

   public EnumActionResult processRightClickBlock(jh player, pm worldIn, BlockPos pos, EnumFacing direction, Vec3d vec, EnumHand hand) {
      this.syncCurrentPlayItem();
      Qy itemstack = player.getHeldItem(hand);
      float f = (float)(vec.x - (double)pos.getX());
      float f1 = (float)(vec.y - (double)pos.getY());
      float f2 = (float)(vec.z - (double)pos.getZ());
      boolean flag = false;
      if (!this.mc.world.getWorldBorder().contains(pos)) {
         return EnumActionResult.FAIL;
      } else {
         if (this.currentGameType != bbb.SPECTATOR) {
            in iblockstate = worldIn.getBlockState(pos);
            if ((!player.isSneaking() || player.getHeldItemMainhand().isEmpty() && player.getHeldItemOffhand().isEmpty()) && iblockstate.getBlock().onBlockActivated(worldIn, pos, iblockstate, player, hand, direction, f, f1, f2)) {
               flag = true;
            }

            if (!flag && itemstack.getItem() instanceof OX) {
               OX itemblock = (OX)itemstack.getItem();
               if (!itemblock.canPlaceBlockOnSide(worldIn, pos, direction, player, itemstack)) {
                  return EnumActionResult.FAIL;
               }
            }
         }

         this.connection.sendPacket(new Td(pos, direction, hand, f, f1, f2));
         if (!flag && this.currentGameType != bbb.SPECTATOR) {
            if (itemstack.isEmpty()) {
               return EnumActionResult.PASS;
            } else if (player.getCooldownTracker().hasCooldown(itemstack.getItem())) {
               return EnumActionResult.PASS;
            } else {
               if (itemstack.getItem() instanceof OX && !player.canUseCommandBlock()) {
                  co block = ((OX)itemstack.getItem()).getBlock();
                  if (block instanceof da || block instanceof hh) {
                     return EnumActionResult.FAIL;
                  }
               }

               if (this.currentGameType.isCreative()) {
                  int i = itemstack.getMetadata();
                  int j = itemstack.getCount();
                  EnumActionResult enumactionresult = itemstack.onItemUse(player, worldIn, pos, hand, direction, f, f1, f2);
                  itemstack.setItemDamage(i);
                  itemstack.setCount(j);
                  return enumactionresult;
               } else {
                  return itemstack.onItemUse(player, worldIn, pos, hand, direction, f, f1, f2);
               }
            }
         } else {
            return EnumActionResult.SUCCESS;
         }
      }
   }

   public EnumActionResult processRightClick(ME player, bij worldIn, EnumHand hand) {
      if (this.currentGameType == bbb.SPECTATOR) {
         return EnumActionResult.PASS;
      } else {
         this.syncCurrentPlayItem();
         this.connection.sendPacket(new Tc(hand));
         Qy itemstack = player.getHeldItem(hand);
         if (player.getCooldownTracker().hasCooldown(itemstack.getItem())) {
            return EnumActionResult.PASS;
         } else {
            int i = itemstack.getCount();
            ActionResult<Qy> actionresult = itemstack.useItemRightClick(worldIn, player, hand);
            Qy itemstack1 = (Qy)actionresult.getResult();
            if (itemstack1 != itemstack || itemstack1.getCount() != i) {
               player.setHeldItem(hand, itemstack1);
            }

            return actionresult.getType();
         }
      }
   }

   public jh createPlayer(bij p_192830_1_, XT p_192830_2_, XK p_192830_3_) {
      return new jh(this.mc, p_192830_1_, this.connection, p_192830_2_, p_192830_3_);
   }

   public void attackEntity(ME playerIn, Ig targetEntity) {
      this.syncCurrentPlayItem();
      this.connection.sendPacket(new Tp(targetEntity));
      if (this.currentGameType != bbb.SPECTATOR) {
         playerIn.attackTargetEntityWithCurrentItem(targetEntity);
         playerIn.resetCooldown();
      }

   }

   public EnumActionResult interactWithEntity(ME player, Ig target, EnumHand hand) {
      this.syncCurrentPlayItem();
      this.connection.sendPacket(new Tp(target, hand));
      return this.currentGameType == bbb.SPECTATOR ? EnumActionResult.PASS : player.interactOn(target, hand);
   }

   public EnumActionResult interactWithEntity(ME player, Ig target, RayTraceResult ray, EnumHand hand) {
      this.syncCurrentPlayItem();
      Vec3d vec3d = new Vec3d(ray.hitVec.x - target.posX, ray.hitVec.y - target.posY, ray.hitVec.z - target.posZ);
      this.connection.sendPacket(new Tp(target, hand, vec3d));
      return this.currentGameType == bbb.SPECTATOR ? EnumActionResult.PASS : target.applyPlayerInteraction(player, vec3d, hand);
   }

   public Qy windowClick(int windowId, int slotId, int mouseButton, ClickType type, ME player) {
      short short1 = player.openContainer.getNextTransactionID(player.inventory);
      Qy itemstack = player.openContainer.slotClick(slotId, mouseButton, type, player);
      this.connection.sendPacket(new SF(windowId, slotId, mouseButton, type, itemstack, short1));
      return itemstack;
   }

   public void func_194338_a(int p_194338_1_, NT p_194338_2_, boolean p_194338_3_, ME p_194338_4_) {
      this.connection.sendPacket(new SU(p_194338_1_, p_194338_2_, p_194338_3_));
   }

   public void sendEnchantPacket(int windowID, int button) {
      this.connection.sendPacket(new SO(windowID, button));
   }

   public void sendSlotPacket(Qy itemStackIn, int slotId) {
      if (this.currentGameType.isCreative()) {
         this.connection.sendPacket(new SM(slotId, itemStackIn));
      }

   }

   public void sendPacketDropItem(Qy itemStackIn) {
      if (this.currentGameType.isCreative() && !itemStackIn.isEmpty()) {
         this.connection.sendPacket(new SM(-1, itemStackIn));
      }

   }

   public void onStoppedUsingItem(ME playerIn) {
      this.syncCurrentPlayItem();
      this.connection.sendPacket(new Tb(Ta.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
      playerIn.stopActiveHand();
   }

   public boolean gameIsSurvivalOrAdventure() {
      return this.currentGameType.isSurvivalOrAdventure();
   }

   public boolean isNotCreative() {
      return !this.currentGameType.isCreative();
   }

   public boolean isInCreativeMode() {
      return this.currentGameType.isCreative();
   }

   public boolean extendedReach() {
      return this.currentGameType.isCreative();
   }

   public boolean isRidingHorse() {
      nC var10000 = this.mc;
      boolean var1;
      if (nC.player.isRiding()) {
         var10000 = this.mc;
         if (nC.player.getRidingEntity() instanceof Lw) {
            var1 = true;
            return var1;
         }
      }

      var1 = false;
      return var1;
   }

   public boolean isSpectatorMode() {
      return this.currentGameType == bbb.SPECTATOR;
   }

   public bbb getCurrentGameType() {
      return this.currentGameType;
   }

   public boolean getIsHittingBlock() {
      return this.isHittingBlock;
   }

   public void pickItem(int index) {
      this.connection.sendPacket(new SN("MC|PickItem", (new SA(Unpooled.buffer())).writeVarInt(index)));
   }
}
