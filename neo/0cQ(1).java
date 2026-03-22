package neo;

import io.netty.buffer.Unpooled;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCommandBlock;
import net.minecraft.block.BlockStructure;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketClickWindow;
import net.minecraft.network.play.client.CPacketCreativeInventoryAction;
import net.minecraft.network.play.client.CPacketCustomPayload;
import net.minecraft.network.play.client.CPacketEnchantItem;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameType;
import net.minecraft.world.World;

public class 0cQ {
   public boolean isHittingBlock;
   public ItemStack currentItemHittingBlock;
   public float stepSoundTickCounter;
   public GameType currentGameType;
   public final 0cL connection;
   private final 0cH mc;
   public int blockHitDelay;
   public final 0cC pbot;
   public float curBlockDamageMP;
   public int currentPlayerItem;
   public BlockPos currentBlock = new BlockPos(-8356 ^ -18005 ^ 3438 ^ -27546, -7629 ^ -10877 ^ 11317 ^ -7046, -469 ^ -26939 ^ 19009 ^ -8880);

   private static GameType ijH3SWeVoD(0cQ var0) {
      return var0.currentGameType;
   }

   public ItemStack windowClick(int windowId, int slotId, int mouseButton, ClickType type, EntityPlayer player) {
      try {
         short short1 = 9Fazg5yLau(player).getNextTransactionID(wWnEieZVyy(player));
         ItemStack itemstack = meGjpyyDa4(player).slotClick(slotId, mouseButton, type, player);
         mWeMrSJPMQ(this).sendPacket(new CPacketClickWindow(windowId, slotId, mouseButton, type, itemstack, short1));
         return itemstack;
      } catch (Exception var8) {
         Exception ex = var8;
         ex.printStackTrace();
         return null;
      }
   }

   public boolean shouldDrawHUD() {
      return ayT4bUN2n7(this).isSurvivalOrAdventure();
   }

   private static 0cL bp7bcDvvgJ(0cQ var0) {
      return var0.connection;
   }

   private static BlockPos bCJrlCx7z2(0cQ var0) {
      return var0.currentBlock;
   }

   private static BlockPos YVbJawtQyX(0cQ var0) {
      return var0.currentBlock;
   }

   private static BlockPos L9lzLgFDbr() {
      return BlockPos.ORIGIN;
   }

   public boolean gameIsSurvivalOrAdventure() {
      return uW2YmgHgHp(this).isSurvivalOrAdventure();
   }

   private static void Fo2NPFReQt(EntityPlayer var0, float var1) {
      var0.rotationYaw = var1;
   }

   private static float lljIdariu4(0cQ var0) {
      return var0.curBlockDamageMP;
   }

   private static GameType tNridMudWM() {
      return GameType.SPECTATOR;
   }

   private static 0cL JRB4NTYckv(0cQ var0) {
      return var0.connection;
   }

   public void updateController() {
      this.syncCurrentPlayItem();
      if (DOep3fo0Ie(this).getNetworkManager().isChannelOpen()) {
         1nBjsO8XjL(this).getNetworkManager().processReceivedPackets();
      } else {
         4c8bvEEPXd(this).getNetworkManager().handleDisconnection();
      }

   }

   private static 0cC _qIBnUNovD/* $FF was: 7qIBnUNovD*/(0cQ var0) {
      return var0.pbot;
   }

   private static GameType D2TzRj269U() {
      return GameType.SPECTATOR;
   }

   private static 0cL BvwVSLwaaD(0cQ var0) {
      return var0.connection;
   }

   private static 0cD vFoRvQBrLZ(0cC var0) {
      return var0.player;
   }

   private static 0cL oWFao98jRb(0cQ var0) {
      return var0.connection;
   }

   private static 0cC B8bTByQOQi(0cQ var0) {
      return var0.pbot;
   }

   private static CPacketPlayerDigging.Action ylr2SvjwtN() {
      return CPacketPlayerDigging.Action.START_DESTROY_BLOCK;
   }

   private static 0cD gjWS5ynEJp(0cC var0) {
      return var0.player;
   }

   public void pickItem(int index) {
      eIUu1BoIES(this).sendPacket(new CPacketCustomPayload(gAuIetwnO4("DJuY`jb@}ld"), (new PacketBuffer(Unpooled.buffer())).writeVarInt(index)));
   }

   private static 0cL vcnfAKPHC7(0cQ var0) {
      return var0.connection;
   }

   public void attackEntity(EntityPlayer playerIn, Entity targetEntity) {
      this.syncCurrentPlayItem();
      WucOVSFLNu(this).sendPacket(new CPacketUseEntity(targetEntity));
      if (gstVn72uAo(this) != AyzT9Kn7vO()) {
         playerIn.attackTargetEntityWithCurrentItem(targetEntity);
         playerIn.resetCooldown();
      }

   }

   private static 0cC AgViPlabWw(0cQ var0) {
      return var0.pbot;
   }

   private static float jSyALaPG4W(0cQ var0) {
      return var0.curBlockDamageMP;
   }

   private static float LSPmAMhBYb(0cQ var0) {
      return var0.curBlockDamageMP;
   }

   private static 0cC bRjnrElFr7(0cQ var0) {
      return var0.pbot;
   }

   private static EnumActionResult IBUaynVqq1() {
      return EnumActionResult.PASS;
   }

   private static void OWntqoj5GV(0cQ var0, float var1) {
      var0.curBlockDamageMP = var1;
   }

   private static 0cL nZB7eNkTAg(0cQ var0) {
      return var0.connection;
   }

   private static 0cC V9eNQET6T4(0cQ var0) {
      return var0.pbot;
   }

   private static boolean voYVISERev(0cQ var0) {
      return var0.isHittingBlock;
   }

   private static 0cD JBtQrnwiYg(0cC var0) {
      return var0.player;
   }

   private static GameType tD7fvVxNWZ(0cQ var0) {
      return var0.currentGameType;
   }

   private void syncCurrentPlayItem() {
      int i = L9ausygHDg(TmtALNy7YS(lGKb7WevUs(zGJOR6LNb0(this))));
      if (i != gnpoO5KgVP(this)) {
         6Ew6OznGNw(this, i);
         y1IZa4odsj(this).sendPacket(new CPacketHeldItemChange(LeHhnV1tfn(this)));
      }

   }

   private static GameType CahHbM7NVG() {
      return GameType.SPECTATOR;
   }

   private static void K8kh0rXJY5(0cQ var0, BlockPos var1) {
      var0.currentBlock = var1;
   }

   public EnumActionResult processRightClickBlock(0cD player, World worldIn, BlockPos pos, EnumFacing direction, Vec3d vec, EnumHand hand) {
      this.syncCurrentPlayItem();
      ItemStack itemstack = player.getHeldItem(hand);
      float f = (float)(wqA1XnDI15(vec) - (double)pos.getX());
      float f1 = (float)(zT2YSBnljh(vec) - (double)pos.getY());
      float f2 = (float)(RgySZJAPet(vec) - (double)pos.getZ());
      boolean flag = 1630 ^ -31608 ^ 8160 ^ -25290;
      if (!V9eNQET6T4(this).getWorld().getWorldBorder().contains(pos)) {
         return 6n2FYvdgyd();
      } else {
         if (H2tEXhvv1k(this) != 5Y2zxqZJBQ()) {
            IBlockState iblockstate = worldIn.getBlockState(pos);
            if ((!player.isSneaking() || player.getHeldItemMainhand().isEmpty() && player.getHeldItemOffhand().isEmpty()) && iblockstate.getBlock().onBlockActivated(worldIn, pos, iblockstate, player, hand, direction, f, f1, f2)) {
               flag = 30680 ^ -12665 ^ 17614 ^ -624;
            }

            if (flag == 0 && itemstack.getItem() instanceof ItemBlock) {
               ItemBlock itemblock = (ItemBlock)itemstack.getItem();
               if (!itemblock.canPlaceBlockOnSide(worldIn, pos, direction, player, itemstack)) {
                  return Gd2yonfDFW();
               }
            }
         }

         nZB7eNkTAg(this).sendPacket(new CPacketPlayerTryUseItemOnBlock(pos, direction, hand, f, f1, f2));
         if (flag == 0 && rZNi4CSN83(this) != CahHbM7NVG()) {
            if (itemstack.isEmpty()) {
               return waqpTZmv5G();
            } else if (player.getCooldownTracker().hasCooldown(itemstack.getItem())) {
               return qNajeuodSe();
            } else {
               if (itemstack.getItem() instanceof ItemBlock && !player.canUseCommandBlock()) {
                  Block block = ((ItemBlock)itemstack.getItem()).getBlock();
                  if (block instanceof BlockCommandBlock || block instanceof BlockStructure) {
                     return XTAKOGnbrk();
                  }
               }

               if (H1tNIBrV4q(this).isCreative()) {
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
            return fjP1y4JR7P();
         }
      }
   }

   private static 0cC LTKHEf7j4H(0cQ var0) {
      return var0.pbot;
   }

   private static double wqA1XnDI15(Vec3d var0) {
      return var0.x;
   }

   public void onStoppedUsingItem(EntityPlayer playerIn) {
      this.syncCurrentPlayItem();
      vcnfAKPHC7(this).sendPacket(new CPacketPlayerDigging(3yiTCyCBdQ(), L9lzLgFDbr(), rT59cOLjM4()));
      playerIn.stopActiveHand();
   }

   private static EnumActionResult dlBWjvJjz7() {
      return EnumActionResult.PASS;
   }

   private static 0cC SW8B6uV1Ak(0cQ var0) {
      return var0.pbot;
   }

   private static GameType k2ov3YOjnV(0cQ var0) {
      return var0.currentGameType;
   }

   private static 0cD oRwWD1hLfl(0cC var0) {
      return var0.player;
   }

   private static GameType DSmsjRfoc3(0cQ var0) {
      return var0.currentGameType;
   }

   public _cQ/* $FF was: 0cQ*/(0cC pbot) {
      this.currentItemHittingBlock = ItemStack.EMPTY;
      this.currentGameType = GameType.SURVIVAL;
      this.pbot = pbot;
      this.mc = pbot.mc;
      this.connection = pbot.getPlayHandler();
   }

   private static CPacketPlayerDigging.Action kZCTQycghD() {
      return CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK;
   }

   private static CPacketPlayerDigging.Action cbJOXtUVM8() {
      return CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK;
   }

   public boolean isSpectator() {
      return (boolean)(FF6chLJ3OF(this) == 1dfiONVBeL() ? 25647 ^ -11640 ^ 28855 ^ -14831 : 3108 ^ -32594 ^ 31406 ^ -2524);
   }

   private static double too9DFSnLm(Entity var0) {
      return var0.posX;
   }

   private static 0cC aWyrB0Gkq1(0cQ var0) {
      return var0.pbot;
   }

   private static 0cL AtLbCldVco(0cQ var0) {
      return var0.connection;
   }

   private static boolean KOt9iSWBYn(0cQ var0) {
      return var0.isHittingBlock;
   }

   private static BlockPos _JJDGdBYCY/* $FF was: 2JJDGdBYCY*/(0cQ var0) {
      return var0.currentBlock;
   }

   private static double OzboPQ2o5w(Vec3d var0) {
      return var0.z;
   }

   private static 0cL Aj7IbvJNje(0cQ var0) {
      return var0.connection;
   }

   private static boolean _nvNXO7xrv/* $FF was: 0nvNXO7xrv*/(0cQ var0) {
      return var0.isHittingBlock;
   }

   private static 0cL YGVEVqOAjF(0cQ var0) {
      return var0.connection;
   }

   private static 0cC dzwylQODkS(0cQ var0) {
      return var0.pbot;
   }

   private static void QOTiU2zXAd(0cQ var0, boolean var1) {
      var0.isHittingBlock = var1;
   }

   public void sendEnchantPacket(int windowID, int button) {
      ywVGw9GPIG(this).sendPacket(new CPacketEnchantItem(windowID, button));
   }

   private static ItemStack D35gLlfraG(0cQ var0) {
      return var0.currentItemHittingBlock;
   }

   private static GameType oNUcGBI6ND(0cQ var0) {
      return var0.currentGameType;
   }

   private static GameType Wv5BDPerPd(0cQ var0) {
      return var0.currentGameType;
   }

   private static void rNu84dG7vu(0cQ var0, int var1) {
      var0.blockHitDelay = var1;
   }

   private static double zteitKtFT9(Vec3d var0) {
      return var0.y;
   }

   private static 0cC l5wlbuGndY(0cQ var0) {
      return var0.pbot;
   }

   private static BlockPos xliensf2dO(0cQ var0) {
      return var0.currentBlock;
   }

   private static void Osn9ApOZeA(0cQ var0, int var1) {
      var0.blockHitDelay = var1;
   }

   public EnumActionResult processRightClick(EntityPlayer player, World worldIn, EnumHand hand) {
      if (tD7fvVxNWZ(this) == QTe7yuqPvY()) {
         return 9VcAnQ0OKT();
      } else {
         this.syncCurrentPlayItem();
         BvwVSLwaaD(this).sendPacket(new CPacketPlayerTryUseItem(hand));
         ItemStack itemstack = player.getHeldItem(hand);
         if (player.getCooldownTracker().hasCooldown(itemstack.getItem())) {
            return dlBWjvJjz7();
         } else {
            int i = itemstack.getCount();
            ActionResult<ItemStack> actionresult = itemstack.useItemRightClick(worldIn, player, hand);
            ItemStack itemstack1 = (ItemStack)actionresult.getResult();
            if (itemstack1 != itemstack || itemstack1.getCount() != i) {
               player.setHeldItem(hand, itemstack1);
            }

            return actionresult.getType();
         }
      }
   }

   private static int gnpoO5KgVP(0cQ var0) {
      return var0.currentPlayerItem;
   }

   private static 0cC DhfOjnin0v(0cQ var0) {
      return var0.pbot;
   }

   private static GameType NIgLsxI1Jb(0cQ var0) {
      return var0.currentGameType;
   }

   private static void _m69g5BenH/* $FF was: 2m69g5BenH*/(0cQ var0, ItemStack var1) {
      var0.currentItemHittingBlock = var1;
   }

   private static EnumFacing fhOyj9rFi4() {
      return EnumFacing.DOWN;
   }

   private static 0cC qWejTWesRj(0cQ var0) {
      return var0.pbot;
   }

   private static 0cD SQQDN8AEBb(0cC var0) {
      return var0.player;
   }

   private static InventoryPlayer TmtALNy7YS(0cD var0) {
      return var0.inventory;
   }

   public boolean extendedReach() {
      return X1QLVVcgts(this).isCreative();
   }

   public void sendSlotPacket(ItemStack itemStackIn, int slotId) {
      if (wlB2aTzwbi(this).isCreative()) {
         oWFao98jRb(this).sendPacket(new CPacketCreativeInventoryAction(slotId, itemStackIn));
      }

   }

   private static float _jsZsAQ6QY/* $FF was: 1jsZsAQ6QY*/(0cQ var0) {
      return var0.stepSoundTickCounter;
   }

   private static 0cD wETb09SB4i(0cC var0) {
      return var0.player;
   }

   private static EnumActionResult _n2FYvdgyd/* $FF was: 6n2FYvdgyd*/() {
      return EnumActionResult.FAIL;
   }

   private static 0cD b7y1oSe97h(0cC var0) {
      return var0.player;
   }

   private static EnumActionResult Gd2yonfDFW() {
      return EnumActionResult.FAIL;
   }

   private static Vec3d artYXdnIGN(RayTraceResult var0) {
      return var0.hitVec;
   }

   private static void xx79UGPnop(0cQ var0, boolean var1) {
      var0.isHittingBlock = var1;
   }

   private static 0cC SNBr95WYMV(0cQ var0) {
      return var0.pbot;
   }

   public EnumActionResult interactWithEntity(EntityPlayer player, Entity target, EnumHand hand) {
      if (target == null) {
         return noOWFW6ZYL();
      } else {
         this.syncCurrentPlayItem();
         CE8xbGnuep(this).sendPacket(new CPacketUseEntity(target, hand));
         return MByDBwJQw7(this) == GL7yn7FaTm() ? 1t4QTdiYRg() : player.interactOn(target, hand);
      }
   }

   private static EnumFacing rT59cOLjM4() {
      return EnumFacing.DOWN;
   }

   private static 0cC GN97ogyEKn(0cQ var0) {
      return var0.pbot;
   }

   private static 0cC Rsxq0vyemV(0cQ var0) {
      return var0.pbot;
   }

   private static 0cC VrODEAu1km(0cQ var0) {
      return var0.pbot;
   }

   private static 0cD JqPqglreez(0cC var0) {
      return var0.player;
   }

   private static 0cD eFVOpoDvvt(0cC var0) {
      return var0.player;
   }

   private static 0cL y1IZa4odsj(0cQ var0) {
      return var0.connection;
   }

   private static 0cC A6JVg2dsup(0cQ var0) {
      return var0.pbot;
   }

   private static ItemStack Q1XYyjs4m8(0cQ var0) {
      return var0.currentItemHittingBlock;
   }

   private static GameType uW2YmgHgHp(0cQ var0) {
      return var0.currentGameType;
   }

   public boolean isNotCreative() {
      return (boolean)(!NIgLsxI1Jb(this).isCreative() ? 10851 ^ -22001 ^ 25189 ^ -7672 : 27139 ^ -24475 ^ 25909 ^ -20653);
   }

   private static 0cC evQ6JfYm8t(0cQ var0) {
      return var0.pbot;
   }

   private static EnumHand BZe9AFjcZq() {
      return EnumHand.MAIN_HAND;
   }

   private static 0cC UGaLn7m4de(0cQ var0) {
      return var0.pbot;
   }

   private static 0cL _nBjsO8XjL/* $FF was: 1nBjsO8XjL*/(0cQ var0) {
      return var0.connection;
   }

   public float getBlockReachDistance() {
      return ylJOGvvCCT(this).isCreative() ? Float.intBitsToFloat(98941 ^ 98417 ^ 8206 ^ 745964941 ^ 19475 ^ 95668 ^ 130840 ^ 1825988912) : Float.intBitsToFloat(125700 ^ 8808 ^ 122484 ^ 1089654688 ^ 103590 ^ 19504 ^ 113475 ^ 6470509);
   }

   private static GameType GL7yn7FaTm() {
      return GameType.SPECTATOR;
   }

   private static 0cD dvZrYp23aC(0cC var0) {
      return var0.player;
   }

   private static 0cC JQBNLMimE3(0cQ var0) {
      return var0.pbot;
   }

   private static 0cD iNosbc6C8t(0cC var0) {
      return var0.player;
   }

   private static GameType wlB2aTzwbi(0cQ var0) {
      return var0.currentGameType;
   }

   public boolean isInCreativeMode() {
      return GxaGllJHQ9(this).isCreative();
   }

   private static int L9ausygHDg(InventoryPlayer var0) {
      return var0.currentItem;
   }

   private static GameType cPjmG7JdbN() {
      return GameType.SPECTATOR;
   }

   private static GameType _5tU1rn1DE/* $FF was: 25tU1rn1DE*/(0cQ var0) {
      return var0.currentGameType;
   }

   private static GameType PIVcehJSlG() {
      return GameType.SPECTATOR;
   }

   public boolean isSpectatorMode() {
      return (boolean)(25tU1rn1DE(this) == cPjmG7JdbN() ? 10790 ^ -15130 ^ 29418 ^ -25557 : 19048 ^ -11393 ^ 443 ^ -26452);
   }

   private static EnumActionResult qNajeuodSe() {
      return EnumActionResult.PASS;
   }

   private static GameType rZNi4CSN83(0cQ var0) {
      return var0.currentGameType;
   }

   private static 0cD qCDjhJnrpL(0cC var0) {
      return var0.player;
   }

   private static 0cD m4vvJ4BSPt(0cC var0) {
      return var0.player;
   }

   private static float d9sImKtHoY(0cQ var0) {
      return var0.stepSoundTickCounter;
   }

   private static 0cC QovBy1QH07(0cQ var0) {
      return var0.pbot;
   }

   private static Container meGjpyyDa4(EntityPlayer var0) {
      return var0.openContainer;
   }

   private static 0cL glaegdrDb9(0cQ var0) {
      return var0.connection;
   }

   private static 0cD b5civOYVYR(0cC var0) {
      return var0.player;
   }

   private static double RgySZJAPet(Vec3d var0) {
      return var0.z;
   }

   private static Vec3d hwLqEB6jfS(RayTraceResult var0) {
      return var0.hitVec;
   }

   private static 0cD _aYLWL4Cyl/* $FF was: 9aYLWL4Cyl*/(0cC var0) {
      return var0.player;
   }

   private static 0cC qs5bmo0j7F(0cQ var0) {
      return var0.pbot;
   }

   public void sendPacketDropItem(ItemStack itemStackIn) {
      if (9tvBVJIbAq(this).isCreative() && !itemStackIn.isEmpty()) {
         bp7bcDvvgJ(this).sendPacket(new CPacketCreativeInventoryAction(-21600 ^ -29237 ^ 6729 ^ -15395, itemStackIn));
      }

   }

   private static float _P226hQGAL/* $FF was: 8P226hQGAL*/(0cQ var0) {
      return var0.curBlockDamageMP;
   }

   private static EnumActionResult _VcAnQ0OKT/* $FF was: 9VcAnQ0OKT*/() {
      return EnumActionResult.PASS;
   }

   private static GameType yJPq6qjead(0cQ var0) {
      return var0.currentGameType;
   }

   private static 0cC ROaYkqRkSe(0cQ var0) {
      return var0.pbot;
   }

   private static boolean _oGvaZAqgA/* $FF was: 5oGvaZAqgA*/(0cQ var0) {
      return var0.isHittingBlock;
   }

   private static PlayerCapabilities Dvrfaa68vg(0cD var0) {
      return var0.capabilities;
   }

   private static ItemStack _iLcFQfV2o/* $FF was: 4iLcFQfV2o*/(0cQ var0) {
      return var0.currentItemHittingBlock;
   }

   public GameType getCurrentGameType() {
      return DSmsjRfoc3(this);
   }

   private static 0cD iJkaL6Yo1B(0cC var0) {
      return var0.player;
   }

   private static 0cC qg6UWO0wia(0cQ var0) {
      return var0.pbot;
   }

   public boolean isRidingHorse() {
      return (boolean)(yCNVdeSqLW(SNBr95WYMV(this)).isRiding() && iNosbc6C8t(bRjnrElFr7(this)).getRidingEntity() instanceof AbstractHorse ? 23458 ^ -32578 ^ 5510 ^ -12645 : 123 ^ -27191 ^ 19012 ^ -8202);
   }

   private static 0cL mWeMrSJPMQ(0cQ var0) {
      return var0.connection;
   }

   private static double v4dfZSfdzR(Entity var0) {
      return var0.posY;
   }

   private static GameType lobgXgcDDl(0cQ var0) {
      return var0.currentGameType;
   }

   public boolean onPlayerDestroyBlock(BlockPos pos) {
      if (Uq4pSPNgVe(this).equals(iqy4a6lto3())) {
         if (oNUcGBI6ND(this) == tNridMudWM()) {
            return (boolean)(17022 ^ -11742 ^ 28042 ^ -554);
         }

         if (!4QHTJyLoi7(Rsxq0vyemV(this)).isAllowEdit()) {
            ItemStack itemstack = qCDjhJnrpL(VrODEAu1km(this)).getHeldItemMainhand();
            if (itemstack.isEmpty()) {
               return (boolean)(5553 ^ -17312 ^ 15784 ^ -27527);
            }

            if (!itemstack.canDestroy(ROaYkqRkSe(this).getWorld().getBlockState(pos).getBlock())) {
               return (boolean)(9414 ^ -9417 ^ 29157 ^ -29164);
            }
         }
      }

      if (k2ov3YOjnV(this).isCreative() && !wETb09SB4i(adrsrUXggP(this)).getHeldItemMainhand().isEmpty() && b7y1oSe97h(iISrW32j9N(this)).getHeldItemMainhand().getItem() instanceof ItemSword) {
         return (boolean)(21856 ^ -2362 ^ 28387 ^ -12987);
      } else {
         World world = qWejTWesRj(this).getWorld();
         IBlockState iblockstate = ((World)world).getBlockState(pos);
         Block block = iblockstate.getBlock();
         if ((block instanceof BlockCommandBlock || block instanceof BlockStructure) && !vFoRvQBrLZ(evQ6JfYm8t(this)).canUseCommandBlock()) {
            return (boolean)(20811 ^ -12761 ^ 270 ^ -24990);
         } else if (iblockstate.getMaterial() == nDbTxJFFFP()) {
            return (boolean)(10104 ^ -24059 ^ 216 ^ -31323);
         } else {
            ((World)world).playEvent(7944 ^ -20772 ^ 15289 ^ -29252, pos, Block.getStateId(iblockstate));
            block.onBlockHarvested(world, pos, iblockstate, WFQrpQlFIV(bA9wylOrIW(this)));
            boolean flag = ((World)world).setBlockState(pos, obVYoot2bn().getDefaultState(), 21359 ^ -26655 ^ 20395 ^ -29906);
            if (flag) {
               block.onPlayerDestroy(world, pos, iblockstate);
            }

            HNTveSzJ3d(this, new BlockPos(ljwQPPvgSP(this).getX(), -11766 ^ -13754 ^ 20720 ^ -18621, pTBev2U520(this).getZ()));
            if (!yJPq6qjead(this).isCreative()) {
               ItemStack itemstack1 = eFVOpoDvvt(qg6UWO0wia(this)).getHeldItemMainhand();
               if (!itemstack1.isEmpty()) {
                  itemstack1.onBlockDestroyed(world, iblockstate, pos, WzF6WVYl2z(7qIBnUNovD(this)));
                  if (itemstack1.isEmpty()) {
                     gnODolFolP(B8bTByQOQi(this)).setHeldItem(BZe9AFjcZq(), v06CkHgZXT());
                  }
               }
            }

            return flag;
         }
      }
   }

   private static GameType X1QLVVcgts(0cQ var0) {
      return var0.currentGameType;
   }

   private static void WD2fDqd6lI(0cQ var0, float var1) {
      var0.curBlockDamageMP = var1;
   }

   private static 0cC bA9wylOrIW(0cQ var0) {
      return var0.pbot;
   }

   private static void eu79W6TdCd(0cQ var0, GameType var1) {
      var0.currentGameType = var1;
   }

   private static 0cC AODYimFOEp(0cQ var0) {
      return var0.pbot;
   }

   private static void R1LOV5B9j6(0cQ var0, float var1) {
      var0.stepSoundTickCounter = var1;
   }

   private static 0cC rDW2tmeh1h(0cQ var0) {
      return var0.pbot;
   }

   private static 0cL _4M6iYZyWg/* $FF was: 24M6iYZyWg*/(0cQ var0) {
      return var0.connection;
   }

   private static int VLI3PNe2JC(0cQ var0) {
      return var0.blockHitDelay;
   }

   private static GameType AyzT9Kn7vO() {
      return GameType.SPECTATOR;
   }

   private static 0cL _c8bvEEPXd/* $FF was: 4c8bvEEPXd*/(0cQ var0) {
      return var0.connection;
   }

   private static 0cC _tuQtiNXil/* $FF was: 3tuQtiNXil*/(0cQ var0) {
      return var0.pbot;
   }

   private static double F927FTnjgD(Entity var0) {
      return var0.posZ;
   }

   private static void Uql8aYjl9j(0cQ var0, boolean var1) {
      var0.isHittingBlock = var1;
   }

   private static int LeHhnV1tfn(0cQ var0) {
      return var0.currentPlayerItem;
   }

   private static void AAJqBgPZZM(0cQ var0, float var1) {
      var0.curBlockDamageMP = var1;
   }

   private static void rjlzOackCd(0cQ var0, boolean var1) {
      var0.isHittingBlock = var1;
   }

   private boolean isHittingPosition(BlockPos pos) {
      ItemStack itemstack = SCSqtjn8h9(5frgX2uAtY(this)).getHeldItemMainhand();
      boolean flag = Q1XYyjs4m8(this).isEmpty() && itemstack.isEmpty() ? 8223 ^ -16720 ^ 1003 ^ -25275 : 17317 ^ -24845 ^ 1821 ^ -9653;
      if (!D35gLlfraG(this).isEmpty() && !itemstack.isEmpty()) {
         flag = itemstack.getItem() != 7vpXtNSGee(this).getItem() || !ItemStack.areItemStackTagsEqual(itemstack, mW0nImNviV(this)) || !itemstack.isItemStackDamageable() && itemstack.getMetadata() != 4iLcFQfV2o(this).getMetadata() ? 18508 ^ -2024 ^ 19556 ^ -976 : 15747 ^ -24717 ^ 24345 ^ -536;
      }

      return (boolean)(pos.equals(xliensf2dO(this)) && flag != 0 ? 19266 ^ -18296 ^ 10525 ^ -9514 : 28688 ^ -27560 ^ 17617 ^ -24423);
   }

   private static EnumActionResult noOWFW6ZYL() {
      return EnumActionResult.FAIL;
   }

   private static void _Ew6OznGNw/* $FF was: 6Ew6OznGNw*/(0cQ var0, int var1) {
      var0.currentPlayerItem = var1;
   }

   private static 0cC cw4oVD2Vht(0cQ var0) {
      return var0.pbot;
   }

   private static ItemStack mW0nImNviV(0cQ var0) {
      return var0.currentItemHittingBlock;
   }

   private static GameType H2tEXhvv1k(0cQ var0) {
      return var0.currentGameType;
   }

   private static 0cD YrX3bKJJkb(0cC var0) {
      return var0.player;
   }

   private static World GiLVDGeGsc(0cD var0) {
      return var0.world;
   }

   public void clickBlockCreative(0cQ playerController, BlockPos pos, EnumFacing facing) {
      if (!SW8B6uV1Ak(this).getWorld().extinguishFire(SQQDN8AEBb(AgViPlabWw(this)), pos, facing)) {
         playerController.onPlayerDestroyBlock(pos);
      }

   }

   private static GameType GxaGllJHQ9(0cQ var0) {
      return var0.currentGameType;
   }

   private static GameType _dfiONVBeL/* $FF was: 1dfiONVBeL*/() {
      return GameType.SPECTATOR;
   }

   private static double oFKdQLtwJI(Vec3d var0) {
      return var0.x;
   }

   private static GameType H1tNIBrV4q(0cQ var0) {
      return var0.currentGameType;
   }

   private static 0cL WucOVSFLNu(0cQ var0) {
      return var0.connection;
   }

   private static 0cC iISrW32j9N(0cQ var0) {
      return var0.pbot;
   }

   private static GameType Uq4pSPNgVe(0cQ var0) {
      return var0.currentGameType;
   }

   private static void eaJ36X4NPQ(0cQ var0, float var1) {
      var0.stepSoundTickCounter = var1;
   }

   private static CPacketPlayerDigging.Action cU0YMVEJyz() {
      return CPacketPlayerDigging.Action.START_DESTROY_BLOCK;
   }

   private static ItemStack _vpXtNSGee/* $FF was: 7vpXtNSGee*/(0cQ var0) {
      return var0.currentItemHittingBlock;
   }

   private static GameType iqy4a6lto3() {
      return GameType.ADVENTURE;
   }

   private static void oNnDTITmhq(0cQ var0, int var1) {
      var0.blockHitDelay = var1;
   }

   private static 0cC adrsrUXggP(0cQ var0) {
      return var0.pbot;
   }

   private static 0cL eIUu1BoIES(0cQ var0) {
      return var0.connection;
   }

   private static GameType _Y2zxqZJBQ/* $FF was: 5Y2zxqZJBQ*/() {
      return GameType.SPECTATOR;
   }

   private static CPacketPlayerDigging.Action qeDWI8LE1i() {
      return CPacketPlayerDigging.Action.START_DESTROY_BLOCK;
   }

   private static EnumActionResult waqpTZmv5G() {
      return EnumActionResult.PASS;
   }

   public boolean getIsHittingBlock() {
      return voYVISERev(this);
   }

   private static GameType _tvBVJIbAq/* $FF was: 9tvBVJIbAq*/(0cQ var0) {
      return var0.currentGameType;
   }

   private static GameType gstVn72uAo(0cQ var0) {
      return var0.currentGameType;
   }

   private static Vec3d p5her4r7ku(RayTraceResult var0) {
      return var0.hitVec;
   }

   private static EnumActionResult _t4QTdiYRg/* $FF was: 1t4QTdiYRg*/() {
      return EnumActionResult.PASS;
   }

   private static ItemStack v06CkHgZXT() {
      return ItemStack.EMPTY;
   }

   private static GameType ayT4bUN2n7(0cQ var0) {
      return var0.currentGameType;
   }

   private static BlockPos pTBev2U520(0cQ var0) {
      return var0.currentBlock;
   }

   private static 0cD _QHTJyLoi7/* $FF was: 4QHTJyLoi7*/(0cC var0) {
      return var0.player;
   }

   public boolean clickBlock(BlockPos loc, EnumFacing face) {
      if (ijH3SWeVoD(this).equals(uyBenPpWZj())) {
         if (7i1HGnKwLq(this) == D2TzRj269U()) {
            return (boolean)(32549 ^ -19298 ^ 29262 ^ -17931);
         }

         if (!YrX3bKJJkb(aWyrB0Gkq1(this)).isAllowEdit()) {
            ItemStack itemstack = m4vvJ4BSPt(S2ggVTm1f2(this)).getHeldItemMainhand();
            if (itemstack.isEmpty()) {
               return (boolean)(15013 ^ -28250 ^ 31693 ^ -12082);
            }

            if (!itemstack.canDestroy(9JlWbN0Yt9(this).getWorld().getBlockState(loc).getBlock())) {
               return (boolean)(18430 ^ -16716 ^ 13738 ^ -13088);
            }
         }
      }

      if (!qs5bmo0j7F(this).getWorld().getWorldBorder().contains(loc)) {
         return (boolean)(10918 ^ -15506 ^ 7259 ^ -2669);
      } else {
         if (rGjx9vcfd3(this).isCreative()) {
            Aj7IbvJNje(this).sendPacket(new CPacketPlayerDigging(qeDWI8LE1i(), loc, face));
            this.clickBlockCreative(this, loc, face);
            BbvJaFKqdt(this, 14162 ^ -8908 ^ 2263 ^ -7500);
         } else if (!5oGvaZAqgA(this) || !this.isHittingPosition(loc)) {
            if (KOt9iSWBYn(this)) {
               glaegdrDb9(this).sendPacket(new CPacketPlayerDigging(SAK8iKavAO(), bCJrlCx7z2(this), face));
            }

            IBlockState iblockstate = PIA1pHfSDm(this).getWorld().getBlockState(loc);
            AtLbCldVco(this).sendPacket(new CPacketPlayerDigging(ylr2SvjwtN(), loc, face));
            boolean flag = iblockstate.getMaterial() != bDyhgVq4w9() ? 26860 ^ -13825 ^ 7271 ^ -17035 : 15608 ^ -32082 ^ 12725 ^ -28701;
            if (flag != 0 && LSPmAMhBYb(this) == Float.intBitsToFloat(25176 ^ 254410 ^ 3530 ^ -1133920476 ^ 22539 ^ 253750 ^ 23294 ^ -1133908801)) {
               iblockstate.getBlock().onBlockClicked(QovBy1QH07(this).getWorld(), loc, gjWS5ynEJp(AODYimFOEp(this)));
            }

            if (flag != 0 && iblockstate.getPlayerRelativeBlockHardness(5wBFLErv7T(LTKHEf7j4H(this)), GiLVDGeGsc(dvZrYp23aC(XiK8S4BlIT(this))), loc) >= Float.intBitsToFloat(3187 ^ '锜' ^ 25717 ^ -1011652017 ^ 3786 ^ 30089 ^ 31112 ^ -63728226)) {
               this.onPlayerDestroyBlock(loc);
            } else {
               rjlzOackCd(this, (boolean)(22002 ^ -17628 ^ 25094 ^ -29487));
               K8kh0rXJY5(this, loc);
               2m69g5BenH(this, a2Q9XNBQdl(UGaLn7m4de(this)).getHeldItemMainhand());
               WD2fDqd6lI(this, Float.intBitsToFloat(26042 ^ 242033 ^ 12037 ^ -143793610 ^ 17662 ^ 253542 ^ 6167 ^ -143811721));
               R1LOV5B9j6(this, Float.intBitsToFloat('\uf174' ^ 'ꅷ' ^ 2728 ^ 498279899 ^ '롇' ^ 'ꮎ' ^ 1045 ^ 498297004));
               vMXbhknO7T(this).getWorld().sendBlockBreakProgress(A1mLogVj0t(DhfOjnin0v(this)).getEntityId(), L9vSXQaqf1(this), (int)(jSyALaPG4W(this) * Float.intBitsToFloat(2778 ^ '饺' ^ 23057 ^ 170810267 ^ 16154 ^ '韊' ^ 17334 ^ 1259239756)) - (24754 ^ -7099 ^ 14423 ^ -17247));
            }
         }

         return (boolean)(13775 ^ -13809 ^ 29134 ^ -29169);
      }
   }

   private static int c0d79pbuS6(0cQ var0) {
      return var0.blockHitDelay;
   }

   private static 0cC vMXbhknO7T(0cQ var0) {
      return var0.pbot;
   }

   private static GameType C771wi3YVJ(0cQ var0) {
      return var0.currentGameType;
   }

   private static BlockPos XdgJaVZqkA(0cQ var0) {
      return var0.currentBlock;
   }

   private static 0cD WzF6WVYl2z(0cC var0) {
      return var0.player;
   }

   private static 0cD SCSqtjn8h9(0cC var0) {
      return var0.player;
   }

   private static Container _Fazg5yLau/* $FF was: 9Fazg5yLau*/(EntityPlayer var0) {
      return var0.openContainer;
   }

   private static 0cC _frgX2uAtY/* $FF was: 5frgX2uAtY*/(0cQ var0) {
      return var0.pbot;
   }

   private static GameType FF6chLJ3OF(0cQ var0) {
      return var0.currentGameType;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String gAuIetwnO4(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 13467 ^ -17904 ^ 13216 ^ -17109; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 13042 ^ -17034 ^ 26309 ^ -5816));
      }

      return var1.toString();
   }

   private static 0cC S2ggVTm1f2(0cQ var0) {
      return var0.pbot;
   }

   private static 0cC PIA1pHfSDm(0cQ var0) {
      return var0.pbot;
   }

   private static void owAWoDyynT(0cQ var0, float var1) {
      var0.curBlockDamageMP = var1;
   }

   private static PlayerCapabilities VVQniSVtF9(EntityPlayer var0) {
      return var0.capabilities;
   }

   private static 0cL ywVGw9GPIG(0cQ var0) {
      return var0.connection;
   }

   private static float _BqjBpglIy/* $FF was: 1BqjBpglIy*/(0cQ var0) {
      return var0.curBlockDamageMP;
   }

   private static GameType QTe7yuqPvY() {
      return GameType.SPECTATOR;
   }

   private static EnumActionResult fjP1y4JR7P() {
      return EnumActionResult.SUCCESS;
   }

   private static 0cC arjzV1iJVG(0cQ var0) {
      return var0.pbot;
   }

   private static void BbvJaFKqdt(0cQ var0, int var1) {
      var0.blockHitDelay = var1;
   }

   private static CPacketPlayerDigging.Action SAK8iKavAO() {
      return CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK;
   }

   private static GameType gIt1gGETvx(0cQ var0) {
      return var0.currentGameType;
   }

   public void resetBlockRemoving() {
      if (0nvNXO7xrv(this)) {
         24M6iYZyWg(this).sendPacket(new CPacketPlayerDigging(cbJOXtUVM8(), 2JJDGdBYCY(this), fhOyj9rFi4()));
         Uql8aYjl9j(this, (boolean)(15941 ^ -15528 ^ 2578 ^ -2289));
         OWntqoj5GV(this, Float.intBitsToFloat(128817 ^ 28411 ^ 127976 ^ -680740854 ^ 127346 ^ 124461 ^ 26701 ^ -680743622));
         dzwylQODkS(this).getWorld().sendBlockBreakProgress(JBtQrnwiYg(GN97ogyEKn(this)).getEntityId(), XdgJaVZqkA(this), -1827 ^ -26530 ^ 827 ^ -25529);
         b5civOYVYR(l5wlbuGndY(this)).resetCooldown();
      }

   }

   private static Material bDyhgVq4w9() {
      return Material.AIR;
   }

   private static BlockPos L9vSXQaqf1(0cQ var0) {
      return var0.currentBlock;
   }

   private static GameType ylJOGvvCCT(0cQ var0) {
      return var0.currentGameType;
   }

   private static World pjwCAZVzsF(0cD var0) {
      return var0.world;
   }

   public void flipPlayer(EntityPlayer playerIn) {
      Fo2NPFReQt(playerIn, Float.intBitsToFloat('衙' ^ 225344 ^ 23463 ^ 648932775 ^ 5825 ^ 22426 ^ 10797 ^ -442882705));
   }

   private static Material oPT1kmdr1s() {
      return Material.AIR;
   }

   public EnumActionResult interactWithEntity(EntityPlayer player, Entity target, RayTraceResult ray, EnumHand hand) {
      if (target == null) {
         return Oe1eT7B0n5();
      } else {
         this.syncCurrentPlayItem();
         Vec3d vec3d = new Vec3d(oFKdQLtwJI(hwLqEB6jfS(ray)) - too9DFSnLm(target), zteitKtFT9(p5her4r7ku(ray)) - v4dfZSfdzR(target), OzboPQ2o5w(artYXdnIGN(ray)) - F927FTnjgD(target));
         JRB4NTYckv(this).sendPacket(new CPacketUseEntity(target, hand, vec3d));
         return C771wi3YVJ(this) == PIVcehJSlG() ? IBUaynVqq1() : target.applyPlayerInteraction(player, vec3d, hand);
      }
   }

   private static GameType rGjx9vcfd3(0cQ var0) {
      return var0.currentGameType;
   }

   private static 0cD gnODolFolP(0cC var0) {
      return var0.player;
   }

   private static 0cL _nnb1TnAGN/* $FF was: 1nnb1TnAGN*/(0cQ var0) {
      return var0.connection;
   }

   private static GameType uyBenPpWZj() {
      return GameType.ADVENTURE;
   }

   public boolean onPlayerDamageBlock(BlockPos posBlock, EnumFacing directionFacing) {
      this.syncCurrentPlayItem();
      if (VLI3PNe2JC(this) > 0) {
         Osn9ApOZeA(this, c0d79pbuS6(this) - (3469 ^ -29041 ^ 25810 ^ -6191));
         return (boolean)(7335 ^ -3865 ^ 17889 ^ -22112);
      } else if (lobgXgcDDl(this).isCreative() && j7IkbvG7NQ(this).getWorld().getWorldBorder().contains(posBlock)) {
         oNnDTITmhq(this, 3380 ^ -5286 ^ 5980 ^ -3785);
         1nnb1TnAGN(this).sendPacket(new CPacketPlayerDigging(cU0YMVEJyz(), posBlock, directionFacing));
         this.clickBlockCreative(this, posBlock, directionFacing);
         return (boolean)(27517 ^ -17525 ^ 26544 ^ -18617);
      } else if (this.isHittingPosition(posBlock)) {
         IBlockState iblockstate = 3tuQtiNXil(this).getWorld().getBlockState(posBlock);
         Block block = iblockstate.getBlock();
         if (iblockstate.getMaterial() == oPT1kmdr1s()) {
            xx79UGPnop(this, (boolean)(10888 ^ -30704 ^ 22375 ^ -2561));
            return (boolean)(21354 ^ -13977 ^ 29944 ^ -4363);
         } else {
            AAJqBgPZZM(this, lljIdariu4(this) + iblockstate.getPlayerRelativeBlockHardness(oRwWD1hLfl(rDW2tmeh1h(this)), pjwCAZVzsF(iJkaL6Yo1B(arjzV1iJVG(this))), posBlock));
            if (d9sImKtHoY(this) % Float.intBitsToFloat(12294 ^ 17177 ^ 387 ^ -1546276599 ^ 8151 ^ '긠' ^ 6110 ^ -480942660) == Float.intBitsToFloat(238926 ^ 'ￜ' ^ 247768 ^ -676253238 ^ 22938 ^ '붉' ^ 6215 ^ -676245292)) {
               block.getSoundType();
            }

            eaJ36X4NPQ(this, 1jsZsAQ6QY(this) + Float.intBitsToFloat('ꋶ' ^ 87002 ^ 4865 ^ -1986209391 ^ 21727 ^ 19187 ^ 3994 ^ -1239602678));
            if (1BqjBpglIy(this) >= Float.intBitsToFloat(19752 ^ '鴶' ^ 1946 ^ 1191471926 ^ 106402 ^ 27812 ^ 121574 ^ 2021954898)) {
               QOTiU2zXAd(this, (boolean)(2407 ^ -16981 ^ 22379 ^ -7257));
               YGVEVqOAjF(this).sendPacket(new CPacketPlayerDigging(kZCTQycghD(), posBlock, directionFacing));
               this.onPlayerDestroyBlock(posBlock);
               owAWoDyynT(this, Float.intBitsToFloat(24683 ^ 11360 ^ 21028 ^ 970722119 ^ 20835 ^ 21052 ^ 5328 ^ 970719975));
               xThdLJQoFb(this, Float.intBitsToFloat(1035331 ^ 1035961 ^ 18204 ^ 2088221133 ^ 1027600 ^ 1041751 ^ 7436 ^ 2088217184));
               rNu84dG7vu(this, 3788 ^ -10015 ^ 15399 ^ -5617);
            }

            cw4oVD2Vht(this).getWorld().sendBlockBreakProgress(9aYLWL4Cyl(JQBNLMimE3(this)).getEntityId(), YVbJawtQyX(this), (int)(8P226hQGAL(this) * Float.intBitsToFloat(16608 ^ '蘲' ^ 28428 ^ 1764638163 ^ 29433 ^ '骍' ^ 13370 ^ 672024643)) - (7311 ^ -26329 ^ 16754 ^ -15141));
            return (boolean)(7880 ^ -11110 ^ 9702 ^ -4171);
         }
      } else {
         return this.clickBlock(posBlock, directionFacing);
      }
   }

   private static EnumActionResult Oe1eT7B0n5() {
      return EnumActionResult.FAIL;
   }

   private static Block obVYoot2bn() {
      return Blocks.AIR;
   }

   private static 0cL DOep3fo0Ie(0cQ var0) {
      return var0.connection;
   }

   private static CPacketPlayerDigging.Action _yiTCyCBdQ/* $FF was: 3yiTCyCBdQ*/() {
      return CPacketPlayerDigging.Action.RELEASE_USE_ITEM;
   }

   private static 0cD lGKb7WevUs(0cC var0) {
      return var0.player;
   }

   private static 0cD A1mLogVj0t(0cC var0) {
      return var0.player;
   }

   private static 0cC j7IkbvG7NQ(0cQ var0) {
      return var0.pbot;
   }

   private static GameType _i1HGnKwLq/* $FF was: 7i1HGnKwLq*/(0cQ var0) {
      return var0.currentGameType;
   }

   private static 0cC XiK8S4BlIT(0cQ var0) {
      return var0.pbot;
   }

   private static EnumActionResult XTAKOGnbrk() {
      return EnumActionResult.FAIL;
   }

   private static 0cD WFQrpQlFIV(0cC var0) {
      return var0.player;
   }

   private static double zT2YSBnljh(Vec3d var0) {
      return var0.y;
   }

   public void setPlayerCapabilities(EntityPlayer player) {
      gIt1gGETvx(this).configurePlayerCapabilities(VVQniSVtF9(player));
   }

   private static 0cD yCNVdeSqLW(0cC var0) {
      return var0.player;
   }

   private static Material nDbTxJFFFP() {
      return Material.AIR;
   }

   private static InventoryPlayer wWnEieZVyy(EntityPlayer var0) {
      return var0.inventory;
   }

   private static 0cC zGJOR6LNb0(0cQ var0) {
      return var0.pbot;
   }

   private static 0cD _wBFLErv7T/* $FF was: 5wBFLErv7T*/(0cC var0) {
      return var0.player;
   }

   private static void HNTveSzJ3d(0cQ var0, BlockPos var1) {
      var0.currentBlock = var1;
   }

   private static 0cC _JlWbN0Yt9/* $FF was: 9JlWbN0Yt9*/(0cQ var0) {
      return var0.pbot;
   }

   private static BlockPos ljwQPPvgSP(0cQ var0) {
      return var0.currentBlock;
   }

   public void setGameType(GameType type) {
      eu79W6TdCd(this, type);
      Wv5BDPerPd(this).configurePlayerCapabilities(Dvrfaa68vg(JqPqglreez(A6JVg2dsup(this))));
   }

   private static 0cD a2Q9XNBQdl(0cC var0) {
      return var0.player;
   }

   private static void xThdLJQoFb(0cQ var0, float var1) {
      var0.stepSoundTickCounter = var1;
   }

   private static 0cL CE8xbGnuep(0cQ var0) {
      return var0.connection;
   }

   private static GameType MByDBwJQw7(0cQ var0) {
      return var0.currentGameType;
   }
}
