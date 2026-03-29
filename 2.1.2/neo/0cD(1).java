package neo;

import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IJumpingMount;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerBeacon;
import net.minecraft.inventory.ContainerBrewingStand;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.ContainerDispenser;
import net.minecraft.inventory.ContainerEnchantment;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.ContainerHopper;
import net.minecraft.inventory.ContainerHorseInventory;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.inventory.ContainerRepair;
import net.minecraft.inventory.ContainerShulkerBox;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemElytra;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketChatMessage;
import net.minecraft.network.play.client.CPacketClientSettings;
import net.minecraft.network.play.client.CPacketClientStatus;
import net.minecraft.network.play.client.CPacketCloseWindow;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.client.CPacketInput;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerAbilities;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketRecipeInfo;
import net.minecraft.network.play.client.CPacketVehicleMove;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.RecipeBook;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatisticsManager;
import net.minecraft.tileentity.CommandBlockBaseLogic;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.tileentity.TileEntityStructure;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.MovementInput;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.GameType;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;

public class 0cD extends EntityPlayer {
   public float prevTimeInPortal;
   public double lastReportedPosZ;
   public float renderArmYaw;
   public int permissionLevel = 30675 ^ -3629 ^ 5866 ^ -28438;
   public boolean rowingBoat;
   public boolean serverSprintState;
   public float prevRenderArmYaw;
   public float horseJumpPower;
   public String serverBrand;
   public int sprintingTicksLeft;
   public final 0cH mc;
   public final 0cC pbot;
   public final 0cL connection;
   public float prevRenderArmPitch;
   public boolean autoJumpEnabled = 26900 ^ -32176 ^ 5604 ^ -351;
   public int sprintToggleTimer;
   public int horseJumpPowerCounter;
   public EnumHand activeHand;
   public final Set<EnumPlayerModelParts> setModelParts = Sets.newHashSet((Object[])EnumPlayerModelParts.values());
   public final RecipeBook recipeBook;
   public int autoJumpTime;
   public float lastReportedYaw;
   public boolean handActive;
   public final StatisticsManager statWriter;
   public float lastReportedPitch;
   public double lastReportedPosY;
   public boolean hasValidHealth;
   public float timeInPortal;
   public boolean wasFallFlying;
   public boolean prevOnGround;
   public int positionUpdateTicks;
   public double lastReportedPosX;
   public float renderArmPitch;
   public MovementInput movementInput;
   public boolean serverSneakState;

   public int getPermissionLevel() {
      return 6wayLuqqF0(this);
   }

   private static 0cL CmbtYfqFdC(0cD var0) {
      return var0.connection;
   }

   private static boolean LWbNg2lt9d(MovementInput var0) {
      return var0.leftKeyDown;
   }

   private static float WDLnW4Qpfj(0cD var0) {
      return var0.width;
   }

   private static int oO9qOreimf(0cD var0) {
      return var0.maxHurtResistantTime;
   }

   private static void a8bjlTnoSY(0cD var0, float var1) {
      var0.horseJumpPower = var1;
   }

   private static boolean uLme2YnAMw(0cD var0) {
      return var0.onGround;
   }

   private static void _y2yIYneKO/* $FF was: 3y2yIYneKO*/(0cD var0, int var1) {
      var0.positionUpdateTicks = var1;
   }

   private static void FDK7WgD2jX(0cD var0, Container var1) {
      var0.openContainer = var1;
   }

   private static float qvEnWiqyPO(Vec2f var0) {
      return var0.y;
   }

   private static void _7NZi1DiYe/* $FF was: 47NZi1DiYe*/(0cD var0, boolean var1) {
      var0.handActive = var1;
   }

   private static void bjvzQ5jZVK(MovementInput var0, boolean var1) {
      var0.jump = var1;
   }

   private static World _erlIAl8vc/* $FF was: 2erlIAl8vc*/(0cD var0) {
      return var0.world;
   }

   private static double TAapFVWJoI(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static 0cE AZeaC4Tg7t(0cH var0) {
      return var0.gameSettings;
   }

   private static DataParameter _Zq4r2ttDo/* $FF was: 6Zq4r2ttDo*/() {
      return HAND_STATES;
   }

   private static float gTNa1A1L8O(0cD var0) {
      return var0.width;
   }

   public void setPermissionLevel(int p_184839_1_) {
      D1QbAo2Avg(this, p_184839_1_);
   }

   private static PlayerCapabilities jWQIYN4bnO(0cD var0) {
      return var0.capabilities;
   }

   private static void bSSM22rjiM(0cD var0, float var1) {
      var0.horseJumpPower = var1;
   }

   private static float ZeYQGnw7Pu(Entity var0) {
      return var0.rotationYaw;
   }

   private static void SO0WVrTLt6(0cD var0, float var1) {
      var0.rotationPitch = var1;
   }

   private static double ZDqf8oxKaW(0cD var0) {
      return var0.posX;
   }

   public float getHorseJumpPower() {
      return fvYWtDGMJj(this);
   }

   private static double _BOvofDcXL/* $FF was: 7BOvofDcXL*/(0cD var0) {
      return var0.posY;
   }

   private static void l4bLdlQZ2C(0cD var0, float var1) {
      var0.prevTimeInPortal = var1;
   }

   public void respawnPlayer() {
      8uzpuyAjjo(this).sendPacket(new CPacketClientStatus(JfgSnp5r9H()));
   }

   private static boolean lTjolRA9tO(MovementInput var0) {
      return var0.backKeyDown;
   }

   private static int jFacf8dTYm(0cD var0) {
      return var0.positionUpdateTicks;
   }

   private static Set b2oNYAImNJ(0cD var0) {
      return var0.setModelParts;
   }

   private static void _eSIQeHiV5/* $FF was: 2eSIQeHiV5*/(0cD var0, double var1) {
      var0.motionX = var1;
   }

   private static float gQDnOjekQL(0cD var0) {
      return var0.rotationYaw;
   }

   private static void D3JqlctDJz(PlayerCapabilities var0, boolean var1) {
      var0.isFlying = var1;
   }

   private static MovementInput jqI1AFew1z(0cD var0) {
      return var0.movementInput;
   }

   private static double wIFUgJdwFc(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static double fatg9tiQRJ(Vec3d var0) {
      return var0.z;
   }

   private static 0cL cPZxbkjuAj(0cD var0) {
      return var0.connection;
   }

   private static void V36yL8vmJm(0cD var0, float var1) {
      var0.moveStrafing = var1;
   }

   private static CPacketEntityAction.Action _yUTlenqGR/* $FF was: 1yUTlenqGR*/() {
      return CPacketEntityAction.Action.START_SNEAKING;
   }

   private static boolean Ux7CA7pLcH(MovementInput var0) {
      return var0.forwardKeyDown;
   }

   private static void Bj5vK6EAdF(0cD var0, float var1) {
      var0.horseJumpPower = var1;
   }

   public boolean isHandActive() {
      return ewDipLJbzN(this);
   }

   private static double SYFE2Qto6V(0cD var0) {
      return var0.posY;
   }

   public boolean isRowingBoat() {
      return zNgzWg8ui9(this);
   }

   private static double mBjVujPfno(0cD var0) {
      return var0.motionY;
   }

   private static InventoryPlayer _LSoKc9gwx/* $FF was: 6LSoKc9gwx*/(0cD var0) {
      return var0.inventory;
   }

   private static double QLzcBnOf2q(0cD var0) {
      return var0.posX;
   }

   private static float BlvGcNGQO3(0cD var0) {
      return var0.lastReportedYaw;
   }

   private static int jxfirOZiPT(0cD var0) {
      return var0.timeUntilPortal;
   }

   private static PlayerCapabilities a2g6aUNywQ(0cD var0) {
      return var0.capabilities;
   }

   private static void XB7mDqEMAW(0cD var0, float var1) {
      var0.rotationYaw = var1;
   }

   private static PlayerCapabilities WADvNlvvd4(0cD var0) {
      return var0.capabilities;
   }

   private static void TTW0jkB2pw(0cD var0, int var1) {
      var0.maxHurtTime = var1;
   }

   private static boolean R1VjsLFJjY(0cD var0) {
      return var0.autoJumpEnabled;
   }

   private static void _1FcyjjD72/* $FF was: 71FcyjjD72*/(0cD var0, float var1) {
      var0.prevRotationYaw = var1;
   }

   private static double Djotlo54l4(0cD var0) {
      return var0.posX;
   }

   private static double _AqSHHQg1j/* $FF was: 4AqSHHQg1j*/(0cD var0) {
      return var0.lastReportedPosX;
   }

   private static void l2C64Dv0iW(0cD var0, int var1) {
      var0.timeUntilPortal = var1;
   }

   private static MovementInput yu3Uv4BIps(0cD var0) {
      return var0.movementInput;
   }

   private static 0cL foqQj8LFNO(0cD var0) {
      return var0.connection;
   }

   private static 0cL _OOqzQtIOR/* $FF was: 4OOqzQtIOR*/(0cD var0) {
      return var0.connection;
   }

   private static 0cL an569nanWp(0cD var0) {
      return var0.connection;
   }

   private static double k0H4nA03vM(0cD var0) {
      return var0.posX;
   }

   private static boolean DRHkWIlAl7(MovementInput var0) {
      return var0.jump;
   }

   private static EntityPlayer.EnumChatVisibility DtYi94FnS7() {
      return EntityPlayer.EnumChatVisibility.FULL;
   }

   private static int _wayLuqqF0/* $FF was: 6wayLuqqF0*/(0cD var0) {
      return var0.permissionLevel;
   }

   public void removeRecipeHighlight(IRecipe p_193103_1_) {
      if (LuY6Nthaju(this).isNew(p_193103_1_)) {
         O1sqgSpgEb(this).markSeen(p_193103_1_);
         CmbtYfqFdC(this).sendPacket(new CPacketRecipeInfo(p_193103_1_));
      }

   }

   public void openGuiHorseInventory(AbstractHorse horse, IInventory inventoryIn) {
      LwYXilyeEi(this, new ContainerHorseInventory(lV5jVfsBlH(this), inventoryIn, horse, this));
   }

   private static float ZXVCujFFo6(0cD var0) {
      return var0.width;
   }

   private static 0cL OYIgSptlnY(0cD var0) {
      return var0.connection;
   }

   private static MovementInput rK4VAotorN(0cD var0) {
      return var0.movementInput;
   }

   private static double t9LLRK448N(0cD var0) {
      return var0.posX;
   }

   private static double IfqqgB4jeJ(0cD var0) {
      return var0.posX;
   }

   private static MovementInput dAgAtoQKwJ(0cD var0) {
      return var0.movementInput;
   }

   private static 0cQ Gvyl9qjJzc(0cH var0) {
      return var0.playerController;
   }

   private static float S12Wxijr0o(0cD var0) {
      return var0.renderArmPitch;
   }

   private static 0cC unlVX1qutC(0cD var0) {
      return var0.pbot;
   }

   private static void EFU0yPToKT(0cD var0, boolean var1) {
      var0.wasFallFlying = var1;
   }

   private static boolean zNgzWg8ui9(0cD var0) {
      return var0.rowingBoat;
   }

   public void updateRidden() {
      super.updateRidden();
      Nk9Dy4sbes(this, (boolean)(14475 ^ -22401 ^ 24979 ^ -3737));
      if (this.getRidingEntity() instanceof EntityBoat) {
         EntityBoat entityboat = (EntityBoat)this.getRidingEntity();
         entityboat.updateInputs(NJ5XpDMjNF(rK4VAotorN(this)), X4KShI9QWX(PiE0MtzVJx(this)), 3MrFBA88iS(rvezaaE8DT(this)), temwVbooA9(3DzuPB9SQg(this)));
         DvQYsVY0JS(this, (boolean)(DpyLybC0gU(this) | (!LWbNg2lt9d(cxNoibCrqI(this)) && !2RGcY2en7S(AVl6eQgLLd(this)) && !Ux7CA7pLcH(yu3Uv4BIps(this)) && !lTjolRA9tO(A3QzBKBbYy(this)) ? 11962 ^ -2887 ^ 11251 ^ -3600 : 30049 ^ -25526 ^ 32503 ^ -26659)));
      }

   }

   private static float KjneZfrLRG(MovementInput var0) {
      return var0.moveStrafe;
   }

   private static void omLWjQIDnA(PlayerCapabilities var0, boolean var1) {
      var0.isFlying = var1;
   }

   private static double Do9AL3xEKL(0cD var0) {
      return var0.posY;
   }

   public String getServerBrand() {
      return pj3g65vPQc(this);
   }

   public void heal(float healAmount) {
   }

   public void sendSettingsToServer() {
      if (ngAg2FrMVB(eAgGJrlaQR(this)) != null) {
         int i = 10322 ^ -22169 ^ 22619 ^ -9874;

         EnumPlayerModelParts enumplayermodelparts;
         for(Iterator var2 = b2oNYAImNJ(this).iterator(); var2.hasNext(); i |= enumplayermodelparts.getPartMask()) {
            enumplayermodelparts = (EnumPlayerModelParts)var2.next();
         }

         gDilWCRNAB(this).sendPacket(new CPacketClientSettings(ljSpJutVPH("EN\u007fUS"), 17691 ^ -9432 ^ 7649 ^ -31786, DtYi94FnS7(), (boolean)(9046 ^ -20562 ^ 14318 ^ -17641), i, 9NtfNOOmwT()));
      }

   }

   protected void updateAutoJump(float p_189810_1_, float p_189810_2_) {
      if (this.isAutoJumpEnabled() && yveNXX6zn3(this) <= 0 && bDn8eBb0BS(this) && !this.isSneaking() && !this.isRiding()) {
         Vec2f vec2f = NSPOIAVxS1(this).getMoveVector();
         if (9gN233vRzQ(vec2f) != Float.intBitsToFloat(19200 ^ 507585 ^ 522285 ^ 1057206597 ^ 495180 ^ 486946 ^ 603 ^ 1057185436) || qvEnWiqyPO(vec2f) != Float.intBitsToFloat(103913 ^ '袦' ^ 106254 ^ -484829517 ^ 28783 ^ '묬' ^ 10929 ^ -484805376)) {
            Vec3d vec3d = new Vec3d(NG1vKD6FNV(this), wOrnRQLraG(this.getEntityBoundingBox()), vDSy4H7mGw(this));
            double d0 = t9LLRK448N(this) + (double)p_189810_1_;
            double d1 = NSDF7DGpZJ(this) + (double)p_189810_2_;
            Vec3d vec3d1 = new Vec3d(d0, H1aa5Drxyn(this.getEntityBoundingBox()), d1);
            Vec3d vec3d2 = new Vec3d((double)p_189810_1_, Double.longBitsToDouble(2332542905155963981L ^ 2332542905155963981L), (double)p_189810_2_);
            float f = this.getAIMoveSpeed();
            float f1 = (float)vec3d2.lengthSquared();
            float f12;
            float f13;
            if (f1 <= Float.intBitsToFloat(10499 ^ 243414 ^ 255324 ^ 1534701475 ^ 99641 ^ 97171 ^ 17929 ^ 1643806694)) {
               f12 = f * 61qQXvtjNZ(vec2f);
               float f3 = f * pqc9EoqEri(vec2f);
               float f4 = MathHelper.sin(1QBbWNEbIF(this) * Float.intBitsToFloat(29136 ^ 492955 ^ 8859 ^ -681851031 ^ 7147 ^ 123793 ^ 129790 ^ -338499320));
               f13 = MathHelper.cos(6h2O1aRyDA(this) * Float.intBitsToFloat('醣' ^ 503556 ^ '\ued84' ^ 904644962 ^ 117212 ^ 75457 ^ 27242 ^ 157443331));
               vec3d2 = new Vec3d((double)(f12 * f13 - f3 * f4), DSzCIVB2WF(vec3d2), (double)(f3 * f13 + f12 * f4));
               f1 = (float)vec3d2.lengthSquared();
               if (f1 <= Float.intBitsToFloat(11874 ^ '괌' ^ 10889 ^ -2066404472 ^ 31435 ^ 'ꃍ' ^ 22384 ^ -1101652618)) {
                  return;
               }
            }

            f12 = (float)MathHelper.fastInvSqrt((double)f1);
            Vec3d vec3d12 = vec3d2.scale((double)f12);
            Vec3d vec3d13 = this.getForward();
            f13 = (float)(M1S50NRgJj(vec3d13) * Ek7GEWiJ1K(vec3d12) + mLe4Cob9A0(vec3d13) * fatg9tiQRJ(vec3d12));
            if (f13 >= Float.intBitsToFloat(520471 ^ 518118 ^ 1839 ^ -799817013 ^ '\uf83c' ^ 245599 ^ '\udaa2' ^ 1850333006)) {
               BlockPos blockpos = new BlockPos(ZDqf8oxKaW(this), SLAanuFRJo(this.getEntityBoundingBox()), oZO7DpBF5J(this));
               IBlockState iblockstate = jQq0dI0B9j(this).getBlockState(blockpos);
               if (iblockstate.getCollisionBoundingBox(3ghPHTAFHY(this), blockpos) == null) {
                  blockpos = blockpos.up();
                  IBlockState iblockstate1 = 2erlIAl8vc(this).getBlockState(blockpos);
                  if (iblockstate1.getCollisionBoundingBox(oWGsY80JvH(this), blockpos) == null) {
                     float f7 = Float.intBitsToFloat(6598 ^ '둲' ^ 21014 ^ 1458657007 ^ 918 ^ 1018091 ^ 1324 ^ 1768406662);
                     if (this.isPotionActive(ixJdWpiWdl())) {
                        f7 += (float)(this.getActivePotionEffect(W3jOLsgGye()).getAmplifier() + (19267 ^ -31165 ^ 22679 ^ -27242)) * Float.intBitsToFloat(30884 ^ '돻' ^ 8542 ^ -300783459 ^ 13906 ^ '\ud968' ^ 7796 ^ -783125550);
                     }

                     float f8 = Math.max(f * Float.intBitsToFloat(20994 ^ '\uf118' ^ 18004 ^ -262913446 ^ 6109 ^ '뱧' ^ 32571 ^ -1330351211), Float.intBitsToFloat('\ueaba' ^ 77781 ^ 17899 ^ -1126761013 ^ '푊' ^ '팑' ^ 25898 ^ -2091442370) / f12);
                     Vec3d vec3d4 = vec3d1.add(vec3d12.scale((double)f8));
                     float f9 = WDLnW4Qpfj(this);
                     float f10 = stmnce5rAG(this);
                     AxisAlignedBB axisalignedbb = (new AxisAlignedBB(vec3d, vec3d4.add(Double.longBitsToDouble(7846317715734301536L ^ 7846317715734301536L), (double)f10, Double.longBitsToDouble(-4382441573052221398L ^ -4382441573052221398L)))).grow((double)f9, Double.longBitsToDouble(-6443332876007106840L ^ -6443332876007106840L), (double)f9);
                     Vec3d lvt_19_1_ = vec3d.add(Double.longBitsToDouble(-4016183041296446134L ^ -4016183041296446134L), Double.longBitsToDouble(-893873187521221051L ^ -3713214620412072379L), Double.longBitsToDouble(-2502833773584288195L ^ -2502833773584288195L));
                     vec3d4 = vec3d4.add(Double.longBitsToDouble(-8721916297344564739L ^ -8721916297344564739L), Double.longBitsToDouble(-9126545429069022178L ^ -4704099271033471970L), Double.longBitsToDouble(-9186039186693142329L ^ -9186039186693142329L));
                     Vec3d vec3d5 = vec3d12.crossProduct(new Vec3d(Double.longBitsToDouble(8161392468244758002L ^ 8161392468244758002L), Double.longBitsToDouble(-8868681640500033902L ^ -4964060763569813870L), Double.longBitsToDouble(-996807949191251629L ^ -996807949191251629L)));
                     Vec3d vec3d6 = vec3d5.scale((double)(f9 * Float.intBitsToFloat(4286 ^ 20386 ^ 918 ^ 1601548436 ^ 255367 ^ 18053 ^ 258780 ^ 1618329024)));
                     Vec3d vec3d7 = lvt_19_1_.subtract(vec3d6);
                     Vec3d vec3d8 = vec3d4.subtract(vec3d6);
                     Vec3d vec3d9 = lvt_19_1_.add(vec3d6);
                     Vec3d vec3d10 = vec3d4.add(vec3d6);
                     List<AxisAlignedBB> list = iF7FbCWtOw(this).getCollisionBoxes(this, axisalignedbb);
                     if (!list.isEmpty()) {
                     }

                     float f11 = Float.intBitsToFloat('ꏛ' ^ '荦' ^ 1988 ^ 2073761971 ^ 22881 ^ 29121 ^ 10211 ^ 2073768072);
                     Iterator var35 = list.iterator();

                     label86: {
                        AxisAlignedBB axisalignedbb2;
                        do {
                           if (!var35.hasNext()) {
                              break label86;
                           }

                           axisalignedbb2 = (AxisAlignedBB)var35.next();
                        } while(!axisalignedbb2.intersects(vec3d7, vec3d8) && !axisalignedbb2.intersects(vec3d9, vec3d10));

                        f11 = (float)qVrbB45lvB(axisalignedbb2);
                        Vec3d vec3d11 = axisalignedbb2.getCenter();
                        BlockPos blockpos1 = new BlockPos(vec3d11);

                        for(int i = 31917 ^ -19804 ^ 15081 ^ -2847; !((float)i >= f7); ++i) {
                           BlockPos blockpos2 = blockpos1.up(i);
                           IBlockState iblockstate2 = rF2LI1cyga(this).getBlockState(blockpos2);
                           AxisAlignedBB axisalignedbb1;
                           if ((axisalignedbb1 = iblockstate2.getCollisionBoundingBox(GjdMIgotBO(this), blockpos2)) != null) {
                              f11 = (float)eAftOGikgT(axisalignedbb1) + (float)blockpos2.getY();
                              if ((double)f11 - SVStt7id2f(this.getEntityBoundingBox()) > (double)f7) {
                                 return;
                              }
                           }

                           if (i > (22704 ^ -12267 ^ 30620 ^ -200)) {
                              blockpos = blockpos.up();
                              IBlockState iblockstate3 = gpwrgwwIi7(this).getBlockState(blockpos);
                              if (iblockstate3.getCollisionBoundingBox(yb24C4iAcU(this), blockpos) != null) {
                                 return;
                              }
                           }
                        }
                     }

                     if (f11 != Float.intBitsToFloat(4689 ^ '쇺' ^ '믋' ^ -2007496210 ^ '\ue034' ^ '鈝' ^ 22593 ^ -2007479321)) {
                        float f14 = (float)((double)f11 - r4TKWVNDHh(this.getEntityBoundingBox()));
                        if (f14 > Float.intBitsToFloat('뭡' ^ '\uf598' ^ 7859 ^ 1892165912 ^ '\uea93' ^ '뙼' ^ 18691 ^ 1338535102) && f14 <= f7) {
                           GMvqvYnFOL(this, 2061 ^ -9149 ^ 753 ^ -10562);
                        }
                     }
                  }
               }
            }
         }
      }

   }

   private static void Nk9Dy4sbes(0cD var0, boolean var1) {
      var0.rowingBoat = var1;
   }

   private static boolean _Zywg28gp4/* $FF was: 9Zywg28gp4*/(MovementInput var0) {
      return var0.jump;
   }

   private static void _yDNiCJjJt/* $FF was: 4yDNiCJjJt*/(0cD var0, int var1) {
      var0.sprintToggleTimer = var1;
   }

   private static int ENiFVFIv6V(0cD var0) {
      return var0.horseJumpPowerCounter;
   }

   private static ItemStack YshbvfAPa5() {
      return ItemStack.EMPTY;
   }

   private static int rb9rHYGShr(0cD var0) {
      return var0.horseJumpPowerCounter;
   }

   private static MovementInput ZvTJk4zIej(0cD var0) {
      return var0.movementInput;
   }

   public void setXPStats(float currentXP, int maxXP, int level) {
      D7rcGXNR7K(this, currentXP);
      1ESN5imHXE(this, maxXP);
      96oEt2IbkJ(this, level);
   }

   private static void wI2uedLoqh(0cD var0, float var1) {
      var0.rotationYaw = var1;
   }

   private static void D1QbAo2Avg(0cD var0, int var1) {
      var0.permissionLevel = var1;
   }

   public void playSound(SoundEvent soundIn, float volume, float pitch) {
   }

   private static MovementInput ttQwoQ8cnR(0cD var0) {
      return var0.movementInput;
   }

   private static void TPeAvk6nNO(0cD var0, int var1) {
      var0.autoJumpTime = var1;
   }

   public void setPositionAndRotation(double x, double y, double z, float yaw, float pitch) {
      rwUb8FZgI6(this, MathHelper.clamp(x, Double.longBitsToDouble(2263924820735974944L ^ -2443326384690930144L), Double.longBitsToDouble(-110938769960459620L ^ -4681137775130434916L)));
      vMDalkWiD4(this, y);
      nlFJ7G69vX(this, MathHelper.clamp(z, Double.longBitsToDouble(7047895820558428002L ^ -6866938503467866270L), Double.longBitsToDouble(6160857330004615687L ^ 1442100903624139271L)));
      xB4xNWvruq(this, QLzcBnOf2q(this));
      V97mqDdq7n(this, zO8D8tL7AB(this));
      AV69F68rQc(this, ttHiq1a22s(this));
      pitch = MathHelper.clamp(pitch, Float.intBitsToFloat(7232 ^ '\uf153' ^ 25386 ^ -1434440837 ^ 6786 ^ 102238 ^ 106517 ^ 1748275339), Float.intBitsToFloat(13280 ^ '\uf565' ^ 31030 ^ -1754583435 ^ 22296 ^ '븾' ^ 12343 ^ -706787113));
      dMFnFywRIF(this, yaw);
      diEaGDeaBq(this, pitch);
      FQl8Bt6mIw(this, r92WugQchO(this));
      gefjgjfrQA(this, n7G0Q6wLx7(this));
      double d0 = (double)(HjIrrBejfk(this) - yaw);
      if (d0 < Double.longBitsToDouble(6768272864968807148L ^ -7094510475519356180L)) {
         71FcyjjD72(this, jumtg6jp2p(this) + Float.intBitsToFloat('\uf51a' ^ 27731 ^ '얬' ^ 392500826 ^ 9366 ^ 31987 ^ 5589 ^ 1422984975));
      }

      if (d0 >= Double.longBitsToDouble(3099805950476355100L ^ 7737809879226189340L)) {
         6v2JFcFOvq(this, OgyKytBbWR(this) - Float.intBitsToFloat(497786 ^ 481492 ^ 32 ^ 1666938117 ^ 3958 ^ 28030 ^ 32150 ^ 552577557));
      }

      this.setPosition(Djotlo54l4(this), SYFE2Qto6V(this), jQrTwqrS8o(this));
      this.setRotation(yaw, pitch);
   }

   private static int FD3YKJ2uAv(0cD var0) {
      return var0.maxHurtResistantTime;
   }

   private static MovementInput TxDu2Qs2rw(0cD var0) {
      return var0.movementInput;
   }

   private static boolean nbI6gLT3nW(MovementInput var0) {
      return var0.jump;
   }

   private static float AAJXOV7aVW(MovementInput var0) {
      return var0.moveForward;
   }

   private static boolean RykFVOcWvo(0cD var0) {
      return var0.onGround;
   }

   private static float BYDniFQchD(0cD var0) {
      return var0.timeInPortal;
   }

   private static void AobWi2Ib01(0cD var0, int var1) {
      var0.hurtTime = var1;
   }

   private static EnumHand jFeD6quhiD() {
      return EnumHand.MAIN_HAND;
   }

   private static void O90ESK99qj(0cD var0, int var1) {
      var0.horseJumpPowerCounter = var1;
   }

   private static int FgAYP77Cbi(0cD var0) {
      return var0.horseJumpPowerCounter;
   }

   public void displayGuiEditCommandCart(CommandBlockBaseLogic commandBlock) {
   }

   private static MovementInput gIyYBJoaUa(0cD var0) {
      return var0.movementInput;
   }

   private static boolean t8gmnyMqoS(0cD var0) {
      return var0.onGround;
   }

   private static int GMwBIw1Dyo(0cD var0) {
      return var0.positionUpdateTicks;
   }

   private static 0cL UvFtlkGm1b(0cD var0) {
      return var0.connection;
   }

   private static 0cL ipl79Qhp9a(0cD var0) {
      return var0.connection;
   }

   private static EntityEquipmentSlot STOJPr2HZ7() {
      return EntityEquipmentSlot.CHEST;
   }

   private static MovementInput RCNDBwgdTG(0cD var0) {
      return var0.movementInput;
   }

   private static float rS1BAu8rfq(MovementInput var0) {
      return var0.moveForward;
   }

   public void addStat(StatBase stat, int amount) {
      if (stat != null && Caaf96Qwhy(stat)) {
         super.addStat(stat, amount);
      }

   }

   private static float G0Ul3reHuV(0cD var0) {
      return var0.width;
   }

   private static double AaDLOy3t0e(0cD var0) {
      return var0.posX;
   }

   private static double AJ7StwvwFg(0cD var0) {
      return var0.posX;
   }

   private static double jF7rlTnrUQ(0cD var0) {
      return var0.posZ;
   }

   private static void zrsGtL9bTI(0cD var0, float var1) {
      var0.horseJumpPower = var1;
   }

   private static RecipeBook z45pQVIqbP(0cD var0) {
      return var0.recipeBook;
   }

   private static double jNGYfCjwin(0cD var0) {
      return var0.posZ;
   }

   private static float i06y0IFXnm(0cD var0) {
      return var0.rotationPitch;
   }

   private static float HVlt22FYS6(0cD var0) {
      return var0.rotationPitch;
   }

   private static void UY6iGyjbhe(0cD var0, float var1) {
      var0.prevRotationYaw = var1;
   }

   private static double wVx2gXMATG(0cD var0) {
      return var0.posX;
   }

   private static boolean _MrFBA88iS/* $FF was: 3MrFBA88iS*/(MovementInput var0) {
      return var0.forwardKeyDown;
   }

   private static MovementInput cxNoibCrqI(0cD var0) {
      return var0.movementInput;
   }

   private static MovementInput _DzuPB9SQg/* $FF was: 3DzuPB9SQg*/(0cD var0) {
      return var0.movementInput;
   }

   private static double M1S50NRgJj(Vec3d var0) {
      return var0.x;
   }

   private static PlayerCapabilities _vQP9qwxGf/* $FF was: 5vQP9qwxGf*/(0cD var0) {
      return var0.capabilities;
   }

   private static DataParameter wqVpQ8JCci() {
      return HAND_STATES;
   }

   private static void fljehSSDy7(0cD var0, boolean var1) {
      var0.hasValidHealth = var1;
   }

   private static void aRmXd17otk(0cD var0, float var1) {
      var0.timeInPortal = var1;
   }

   private static void AtgDeFjXkK(0cD var0, boolean var1) {
      var0.handActive = var1;
   }

   private static EntityDataManager AV4zUYWpnN(0cD var0) {
      return var0.dataManager;
   }

   private static GameType GEXvv7nmUW() {
      return GameType.SPECTATOR;
   }

   private static double zO8D8tL7AB(0cD var0) {
      return var0.posY;
   }

   private static double wOrnRQLraG(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static int IC0kI5a1QR(0cD var0) {
      return var0.timeUntilPortal;
   }

   private static void DvQYsVY0JS(0cD var0, boolean var1) {
      var0.rowingBoat = var1;
   }

   private static World metORSZCQK(0cD var0) {
      return var0.world;
   }

   private static MovementInput ZWr5maxaPl(0cD var0) {
      return var0.movementInput;
   }

   private static void XO9YIaBO6i(0cD var0, float var1) {
      var0.lastReportedYaw = var1;
   }

   protected ItemStack dropItemAndGetStack(EntityItem p_184816_1_) {
      return MJbo7bUOoW();
   }

   private static float bBjnneWeW2(0cD var0) {
      return var0.renderArmPitch;
   }

   private static float _wfP73MvQB/* $FF was: 1wfP73MvQB*/(0cD var0) {
      return var0.timeInPortal;
   }

   private static void ybwoeWYt4l(0cD var0, boolean var1) {
      var0.serverSprintState = var1;
   }

   public void closeScreen() {
      7lTbs789Aj(this).sendPacket(new CPacketCloseWindow(2ZlFLyqUQV(iinjAbYo6h(this))));
      this.closeScreenAndDropStack();
   }

   private static void VzvTIgqFG4(0cD var0, int var1) {
      var0.hurtResistantTime = var1;
   }

   private static boolean ewDipLJbzN(0cD var0) {
      return var0.handActive;
   }

   private static 0cL _LZVyEjNh5/* $FF was: 8LZVyEjNh5*/(0cD var0) {
      return var0.connection;
   }

   private static void UdId2DVtwG(0cD var0, float var1) {
      var0.prevRenderArmPitch = var1;
   }

   private static InventoryPlayer _gvwdqJ7W7/* $FF was: 2gvwdqJ7W7*/(0cD var0) {
      return var0.inventory;
   }

   private static MovementInput A3QzBKBbYy(0cD var0) {
      return var0.movementInput;
   }

   private static void XW6gazzFYD(MovementInput var0, float var1) {
      var0.moveForward = var1;
   }

   private static void _doYTk4C1n/* $FF was: 7doYTk4C1n*/(0cD var0, boolean var1) {
      var0.rowingBoat = var1;
   }

   private static boolean fSbaQ6TmwV(MovementInput var0) {
      return var0.jump;
   }

   private static boolean X4KShI9QWX(MovementInput var0) {
      return var0.rightKeyDown;
   }

   private boolean isOpenBlockSpace(BlockPos pos) {
      return (boolean)(!metORSZCQK(this).getBlockState(pos).isNormalCube() && !tROO2mh7Fb(this).getBlockState(pos.up()).isNormalCube() ? 24474 ^ -436 ^ 152 ^ -24241 : 24223 ^ -7300 ^ 23874 ^ -8031);
   }

   private static 0cL _lTbs789Aj/* $FF was: 7lTbs789Aj*/(0cD var0) {
      return var0.connection;
   }

   private static float _sha657l9p/* $FF was: 5sha657l9p*/(0cD var0) {
      return var0.lastReportedPitch;
   }

   @Nullable
   public EntityItem dropItem(boolean dropAll) {
      CPacketPlayerDigging.Action cpacketplayerdigging$action = dropAll ? wiAaUhGrto() : E9wenOerjc();
      TaeYV2eYDx(this).sendPacket(new CPacketPlayerDigging(cpacketplayerdigging$action, Aa15MoeP5B(), A3sSBdleLn()));
      return null;
   }

   private static int Vna5miN329(0cD var0) {
      return var0.autoJumpTime;
   }

   private static float OgyKytBbWR(0cD var0) {
      return var0.prevRotationYaw;
   }

   private static World _ghPHTAFHY/* $FF was: 3ghPHTAFHY*/(0cD var0) {
      return var0.world;
   }

   public void onUpdate() {
      if (h3SjOh1xFG(this).isBlockLoaded(new BlockPos(X43wJ94A7l(this), Double.longBitsToDouble(613084091358509173L ^ 613084091358509173L), 5QzOqj3NBv(this)))) {
         super.onUpdate();
         if (this.isRiding()) {
            ipl79Qhp9a(this).sendPacket(new CPacketPlayer.Rotation(ATyWHhnTdV(this), ZGEjFwrSwi(this), LFa2CoRYM2(this)));
            eYzvjCgeT5(this).sendPacket(new CPacketInput(lVOv1J9as4(this), 8VX8YWwiNA(this), azdQU0vtGK(jqI1AFew1z(this)), D6JwmwVvCd(KsT8IY1bJJ(this))));
            Entity entity = this.getLowestRidingEntity();
            if (entity != this && entity.canPassengerSteer()) {
               cPZxbkjuAj(this).sendPacket(new CPacketVehicleMove(entity));
            }
         } else {
            this.onUpdateWalkingPlayer();
         }
      }

   }

   private static double RQdvweNV8x(0cD var0) {
      return var0.motionZ;
   }

   private static float tFdU4AUYqE(0cD var0) {
      return var0.width;
   }

   private static PlayerCapabilities buP8wUJmpK(0cD var0) {
      return var0.capabilities;
   }

   private static double ttHiq1a22s(0cD var0) {
      return var0.posZ;
   }

   private static double e9GD3awOY9(0cD var0) {
      return var0.posZ;
   }

   private static EntityDataManager _Gi2VX0gVL/* $FF was: 0Gi2VX0gVL*/(0cD var0) {
      return var0.dataManager;
   }

   private static boolean DE7dt4kFtN(0cD var0) {
      return var0.handActive;
   }

   public void sendChatMessage(String message) {
      an569nanWp(this).sendPacket(new CPacketChatMessage(message));
   }

   public boolean isAutoJumpEnabled() {
      return R1VjsLFJjY(this);
   }

   private static EnumHand bvldBvlKND() {
      return EnumHand.OFF_HAND;
   }

   private static boolean uqJqmqOaBI(PlayerCapabilities var0) {
      return var0.isFlying;
   }

   private static World oWGsY80JvH(0cD var0) {
      return var0.world;
   }

   private static 0cD ngAg2FrMVB(0cC var0) {
      return var0.player;
   }

   private static float JWm1rurBJv(MovementInput var0) {
      return var0.moveStrafe;
   }

   private static CPacketEntityAction.Action taQ9JxQFat() {
      return CPacketEntityAction.Action.START_RIDING_JUMP;
   }

   private static void vBAlBXVTWT(MovementInput var0, float var1) {
      var0.moveStrafe = var1;
   }

   private static 0cH DiWSjlYrxd(0cD var0) {
      return var0.mc;
   }

   public void setRotation(float yaw, float pitch) {
      XB7mDqEMAW(this, yaw % Float.intBitsToFloat(121007 ^ 88939 ^ 37 ^ 791043902 ^ 15567 ^ 23337 ^ 377 ^ 1821555264));
      SO0WVrTLt6(this, pitch % Float.intBitsToFloat(101512 ^ 123627 ^ 26814 ^ -1031997042 ^ 114782 ^ 119743 ^ 24143 ^ -2117553411));
   }

   private static boolean DpyLybC0gU(0cD var0) {
      return var0.rowingBoat;
   }

   private static void nWr7vSLEEv(0cD var0, Container var1) {
      var0.openContainer = var1;
   }

   private static double gy1HjAY41k(0cD var0) {
      return var0.posZ;
   }

   private static double qVrbB45lvB(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static Item vOj6vNQToN() {
      return Items.ELYTRA;
   }

   private static double WgWFMQkDgJ(0cD var0) {
      return var0.posX;
   }

   private static World gpwrgwwIi7(0cD var0) {
      return var0.world;
   }

   private static MovementInput BxIxnpRyTx(0cD var0) {
      return var0.movementInput;
   }

   private static boolean DF9wqO0kjd(MovementInput var0) {
      return var0.sneak;
   }

   private static boolean trolWynTz6(0cD var0) {
      return var0.onGround;
   }

   protected boolean pushOutOfBlocks(double x, double y, double z) {
      if (qOeYFs1ANv(this)) {
         return (boolean)(27683 ^ -18979 ^ 683 ^ -9387);
      } else {
         BlockPos blockpos = new BlockPos(x, y, z);
         double d0 = x - (double)blockpos.getX();
         double d1 = z - (double)blockpos.getZ();
         if (!this.isOpenBlockSpace(blockpos)) {
            int i = -25629 ^ -23363 ^ 29847 ^ -19402;
            double d2 = Double.longBitsToDouble(6063230998000728057L ^ 1506291312787748857L);
            if (this.isOpenBlockSpace(blockpos.west()) && d0 < d2) {
               d2 = d0;
               i = 16896 ^ -9966 ^ 31382 ^ -7804;
            }

            if (this.isOpenBlockSpace(blockpos.east()) && Double.longBitsToDouble(-6386283572396459540L ^ -7444629484828526100L) - d0 < d2) {
               d2 = Double.longBitsToDouble(9045717144377519025L ^ 4789815496512400305L) - d0;
               i = 18171 ^ -23643 ^ 8673 ^ -15170;
            }

            if (this.isOpenBlockSpace(blockpos.north()) && d1 < d2) {
               d2 = d1;
               i = 4367 ^ -13941 ^ 19514 ^ -27462;
            }

            if (this.isOpenBlockSpace(blockpos.south()) && Double.longBitsToDouble(-3831015434062913749L ^ -782078486333087957L) - d1 < d2) {
               d2 = Double.longBitsToDouble(-7562614392412603190L ^ -6270081299357270838L) - d1;
               i = 31573 ^ -22981 ^ 20910 ^ -29499;
            }

            if (i == 0) {
               2eSIQeHiV5(this, Double.longBitsToDouble(5515082329728460588L ^ -923207476545870036L));
            }

            if (i == (8934 ^ -27461 ^ 17680 ^ -3252)) {
               7aOeeWNqSN(this, Double.longBitsToDouble(-4226069657895895681L ^ -369183496854372993L));
            }

            if (i == (18301 ^ -20949 ^ 22385 ^ -16861)) {
               TZtaLPiL7N(this, Double.longBitsToDouble(1354306392573915576L ^ -5948428392211107400L));
            }

            if (i == (23997 ^ -3913 ^ 4918 ^ -16839)) {
               JqRIvAOGGr(this, Double.longBitsToDouble(3759307806848700447L ^ 833771839222304799L));
            }
         }

         return (boolean)(16583 ^ -20035 ^ 17627 ^ -19039);
      }
   }

   private static float L6TVjEowuT(MovementInput var0) {
      return var0.moveForward;
   }

   private static double vDSy4H7mGw(0cD var0) {
      return var0.posZ;
   }

   public boolean isSneaking() {
      boolean flag = jbSIrSHjiO(this) != null && DF9wqO0kjd(PCzaKLJbSy(this)) ? 32757 ^ -3010 ^ 6106 ^ -25584 : 4359 ^ -27947 ^ 4563 ^ -28159;
      return (boolean)(flag != 0 && !NXs1NfA62y(this) ? 2220 ^ -3925 ^ 20495 ^ -22519 : 8232 ^ -14255 ^ 22401 ^ -16392);
   }

   private static World xlg4RGJaaL(0cD var0) {
      return var0.world;
   }

   private static boolean _RGcY2en7S/* $FF was: 2RGcY2en7S*/(MovementInput var0) {
      return var0.rightKeyDown;
   }

   private static float QeygmyinXf(MovementInput var0) {
      return var0.moveForward;
   }

   private static int yveNXX6zn3(0cD var0) {
      return var0.autoJumpTime;
   }

   private static boolean G2DOQdfc26(0cD var0) {
      return var0.serverSneakState;
   }

   private static float _gN233vRzQ/* $FF was: 9gN233vRzQ*/(Vec2f var0) {
      return var0.x;
   }

   private static void _aOeeWNqSN/* $FF was: 7aOeeWNqSN*/(0cD var0, double var1) {
      var0.motionX = var1;
   }

   private static void SNBxwFGIae(0cD var0, boolean var1) {
      var0.serverSneakState = var1;
   }

   private static int _rP9wflEyn/* $FF was: 7rP9wflEyn*/(0cD var0) {
      return var0.horseJumpPowerCounter;
   }

   private static CPacketEntityAction.Action d7gIiTZlEp() {
      return CPacketEntityAction.Action.STOP_SPRINTING;
   }

   private static float _Q0SKBt271/* $FF was: 3Q0SKBt271*/(0cD var0) {
      return var0.rotationPitch;
   }

   private static int FOg7jOyJgi(0cD var0) {
      return var0.sprintToggleTimer;
   }

   private static double nXL5Y7SAJ5(0cD var0) {
      return var0.posZ;
   }

   private static int tm172tYBBj(0cD var0) {
      return var0.sprintToggleTimer;
   }

   private static void vMDalkWiD4(0cD var0, double var1) {
      var0.posY = var1;
   }

   private static boolean NXs1NfA62y(0cD var0) {
      return var0.sleeping;
   }

   private static 0cC eAgGJrlaQR(0cD var0) {
      return var0.pbot;
   }

   private static boolean WIGJyXKvbJ(0cD var0) {
      return var0.handActive;
   }

   private static CPacketPlayerDigging.Action wiAaUhGrto() {
      return CPacketPlayerDigging.Action.DROP_ALL_ITEMS;
   }

   private static boolean Qido1acSSy(0cD var0) {
      return var0.onGround;
   }

   private static float eRo26YtG7A(0cD var0) {
      return var0.rotationPitch;
   }

   private static MovementInput AVl6eQgLLd(0cD var0) {
      return var0.movementInput;
   }

   private static MovementInput PiE0MtzVJx(0cD var0) {
      return var0.movementInput;
   }

   private static InventoryPlayer SONhQOnpYZ(0cD var0) {
      return var0.inventory;
   }

   private static void e6rawJjpdy(0cD var0, EnumHand var1) {
      var0.activeHand = var1;
   }

   private static double rJj3aRES2v(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static InventoryPlayer UOZO9qW2OQ(0cD var0) {
      return var0.inventory;
   }

   private static MovementInput rvezaaE8DT(0cD var0) {
      return var0.movementInput;
   }

   public boolean canUseCommand(int permLevel, String commandName) {
      return (boolean)(permLevel <= this.getPermissionLevel() ? 1645 ^ -18294 ^ 18443 ^ -2323 : 25602 ^ -13011 ^ 6569 ^ -20346);
   }

   private static InventoryPlayer w2q5LwxlBo(0cD var0) {
      return var0.inventory;
   }

   private static void V97mqDdq7n(0cD var0, double var1) {
      var0.prevPosY = var1;
   }

   private static double iVSQoa3GHF(0cD var0) {
      return var0.posZ;
   }

   private static 0cL _ABjfSr8iD/* $FF was: 6ABjfSr8iD*/(0cD var0) {
      return var0.connection;
   }

   private static double H1aa5Drxyn(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static InventoryPlayer _TvWVt6NAN/* $FF was: 2TvWVt6NAN*/(0cD var0) {
      return var0.inventory;
   }

   private static void nIirDvT4DG(0cD var0, int var1) {
      var0.positionUpdateTicks = var1;
   }

   private static void T476gjBqOe(0cD var0, int var1) {
      var0.sprintToggleTimer = var1;
   }

   private static void rwUb8FZgI6(0cD var0, double var1) {
      var0.posX = var1;
   }

   private static float sM0lx28RtG(0cD var0) {
      return var0.rotationYaw;
   }

   private static float _h2O1aRyDA/* $FF was: 6h2O1aRyDA*/(0cD var0) {
      return var0.rotationYaw;
   }

   private static double xtTuCSDOv0(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static MovementInput VZrExWWLJi(0cD var0) {
      return var0.movementInput;
   }

   private static boolean y4TLj1qUu6(0cD var0) {
      return var0.hasValidHealth;
   }

   private static 0cL _uzpuyAjjo/* $FF was: 8uzpuyAjjo*/(0cD var0) {
      return var0.connection;
   }

   private static float jlgS6WjS6g(0cD var0) {
      return var0.renderArmPitch;
   }

   private static float D6uEBWeGbJ(0cD var0) {
      return var0.timeInPortal;
   }

   private static 0cL _QPdaq44Ov/* $FF was: 7QPdaq44Ov*/(0cD var0) {
      return var0.connection;
   }

   public void swingArm(EnumHand hand) {
      super.swingArm(hand);
      aDA5oKpcgt(this).sendPacket(new CPacketAnimation(hand));
   }

   private static boolean _vk0GiKwmD/* $FF was: 6vk0GiKwmD*/(0cD var0) {
      return var0.onGround;
   }

   private static int uAJjPGpIij(0cD var0) {
      return var0.horseJumpPowerCounter;
   }

   private static DamageSource DJDDwSlFTY() {
      return DamageSource.GENERIC;
   }

   private static PlayerCapabilities i6QB4ie171(0cD var0) {
      return var0.capabilities;
   }

   private static void Jzait4mr51(0cD var0, float var1) {
      var0.timeInPortal = var1;
   }

   private static DataParameter l4DfydWdtz() {
      return HAND_STATES;
   }

   private static MovementInput tyrDfKwSQV(0cD var0) {
      return var0.movementInput;
   }

   private static EnumHandSide _NtfNOOmwT/* $FF was: 9NtfNOOmwT*/() {
      return EnumHandSide.RIGHT;
   }

   private static boolean i5jnrehj9H(0cD var0) {
      return var0.prevOnGround;
   }

   private static Potion W3jOLsgGye() {
      return MobEffects.JUMP_BOOST;
   }

   private static double r4TKWVNDHh(AxisAlignedBB var0) {
      return var0.minY;
   }

   public void onLivingUpdate() {
      T5xwfN7Lsy(this, pGEwBdejQr(this) + (17538 ^ -7967 ^ 11668 ^ -30218));
      if (FOg7jOyJgi(this) > 0) {
         EuxtSitbVf(this, JZGswdI1LS(this) - (17775 ^ -21944 ^ 27284 ^ -31310));
      }

      3RcvotA2aA(this, D6uEBWeGbJ(this));
      if (krRNbG9azr(this)) {
         aRmXd17otk(this, dJlxr6V9G0(this) + Float.intBitsToFloat(236864 ^ 32740 ^ 258308 ^ -1360942628 ^ '舢' ^ 20386 ^ '迎' ^ -1834142465));
         if (1wfP73MvQB(this) >= Float.intBitsToFloat('긄' ^ '\udfd1' ^ 4210 ^ -41021207 ^ '\uf7cd' ^ '욽' ^ 3361 ^ -1039250145)) {
            vH38yQUOqw(this, Float.intBitsToFloat('쓰' ^ 76373 ^ 27802 ^ -444537150 ^ '\uf013' ^ '꒤' ^ 10571 ^ -637462271));
         }

         HscODu7V4t(this, (boolean)(20128 ^ -17967 ^ 31276 ^ -29347));
      } else if (this.isPotionActive(9NqM8FB4xX()) && this.getActivePotionEffect(JyvCeRSa29()).getDuration() > (32042 ^ -22001 ^ 17609 ^ -27696)) {
         e3iqGNeL2j(this, 60gLLxvVt1(this) + Float.intBitsToFloat(26943 ^ 'ꀲ' ^ 10246 ^ -267564863 ^ 561 ^ 1023755 ^ 1022472 ^ -875046154));
         if (CuD7JLIWoS(this) > Float.intBitsToFloat(119494 ^ 106188 ^ 12963 ^ -1383037853 ^ 100387 ^ 125738 ^ 23942 ^ -1844394939)) {
            pwGi3qrnAq(this, Float.intBitsToFloat(100358 ^ 102559 ^ 3977 ^ -1418802975 ^ 119406 ^ 118101 ^ 16574 ^ -1796308876));
         }
      } else {
         if (BYDniFQchD(this) > Float.intBitsToFloat(21642 ^ 32135 ^ 14875 ^ 614968607 ^ 21209 ^ 1004574 ^ 1038198 ^ 614952888)) {
            lb9l1qmbnx(this, OVGSrme4q2(this) - Float.intBitsToFloat(107576 ^ 83524 ^ 26642 ^ 1797553443 ^ 27492 ^ 1029422 ^ 13326 ^ 1449644484));
         }

         if (rngBddWN2G(this) < Float.intBitsToFloat(23639 ^ '鹟' ^ 4137 ^ -7114875 ^ 492560 ^ '둀' ^ 522869 ^ -7112831)) {
            Jzait4mr51(this, Float.intBitsToFloat(2072768 ^ 2092826 ^ 1854 ^ 537898085 ^ 2075364 ^ 7430 ^ 2077553 ^ 537911314));
         }
      }

      if (jxfirOZiPT(this) > 0) {
         l2C64Dv0iW(this, IC0kI5a1QR(this) - (7617 ^ -11742 ^ 3927 ^ -16203));
      }

      boolean flag = 9Zywg28gp4(WNo6niNidR(this));
      boolean flag1 = OS7XgXVgvr(ODD1uQfUqF(this));
      boolean flag2 = eo2lZZ8xlu(vd1gTqeGYY(this)) >= Float.intBitsToFloat(107893 ^ 27678 ^ 130000 ^ 10657710 ^ '\ud8f6' ^ '锴' ^ 11306 ^ 1072562224) ? 19802 ^ -30720 ^ 30127 ^ -20236 : 1149 ^ -20079 ^ 27261 ^ -8303;
      ttQwoQ8cnR(this).updatePlayerMoveState();
      if (this.isHandActive() && !this.isRiding()) {
         MovementInput var10000 = ZWr5maxaPl(this);
         vBAlBXVTWT(var10000, fildTlTJTC(var10000) * Float.intBitsToFloat('Ꟶ' ^ 19082 ^ '챘' ^ -200993142 ^ 229725 ^ 256106 ^ 15741 ^ -901140694));
         var10000 = obdUQ79Ow7(this);
         XW6gazzFYD(var10000, t7S0WoUxVs(var10000) * Float.intBitsToFloat(239081 ^ 19655 ^ 256212 ^ -1973045750 ^ '醍' ^ '펙' ^ 8131 ^ -1272371478));
         4yDNiCJjJt(this, 17621 ^ -29075 ^ 14999 ^ -4049);
      }

      if (7hsLjpDwDG(this) > 0) {
         TPeAvk6nNO(this, Vna5miN329(this) - (32319 ^ -24920 ^ 32112 ^ -25114));
         bjvzQ5jZVK(KOLrPYKAfg(this), (boolean)(25191 ^ -19384 ^ 21367 ^ -31399));
      }

      AxisAlignedBB axisalignedbb = this.getEntityBoundingBox();
      this.pushOutOfBlocks(JwquAxWef2(this) - (double)gTNa1A1L8O(this) * Double.longBitsToDouble(-3991454923068100948L ^ -626591321679935286L), xtTuCSDOv0(axisalignedbb) + Double.longBitsToDouble(-1801386768927696313L ^ -2819200284713428409L), MTB6r4sNvQ(this) + (double)WQJoZnSIqA(this) * Double.longBitsToDouble(3152645371353539185L ^ 1447366481005822999L));
      this.pushOutOfBlocks(WgWFMQkDgJ(this) - (double)q1eb1ioTVz(this) * Double.longBitsToDouble(2205710640477715546L ^ 2398769618565945916L), rJj3aRES2v(axisalignedbb) + Double.longBitsToDouble(-2276647920277984254L ^ -2339698315061171198L), gy1HjAY41k(this) - (double)G0Ul3reHuV(this) * Double.longBitsToDouble(-2799379955476535088L ^ -1805665663832912202L));
      this.pushOutOfBlocks(WrLdTQZyEr(this) + (double)ZXVCujFFo6(this) * Double.longBitsToDouble(5162129965260036904L ^ 8680116243818036558L), 0MuIG6wfHD(axisalignedbb) + Double.longBitsToDouble(-8293101137972347908L ^ -5545905365276345348L), 66ay7tWZAb(this) - (double)tFdU4AUYqE(this) * Double.longBitsToDouble(888767368912961431L ^ 3712073648893696497L));
      this.pushOutOfBlocks(wVx2gXMATG(this) + (double)OYZy0Ylk4t(this) * Double.longBitsToDouble(-3085969172633465159L ^ -1514888406050133793L), V7r1AG6TSI(axisalignedbb) + Double.longBitsToDouble(5859614179270140167L ^ 7976306004134273287L), t5V5JlG7eL(this) + (double)i4RebFZxGL(this) * Double.longBitsToDouble(-6814172163701350495L ^ -7009478009776260665L));
      boolean flag4 = !((float)this.getFoodStats().getFoodLevel() > Float.intBitsToFloat('젷' ^ '\uf61d' ^ 5859 ^ 1519344883 ^ 'ꂠ' ^ '诊' ^ 134 ^ 441409494)) && !sT7UaUBx34(i6QB4ie171(this)) ? 25426 ^ -14975 ^ 20163 ^ -6128 : 6075 ^ -29075 ^ 21381 ^ -13742;
      if (00WrjRzZNE(this) && !flag1 && flag2 == 0 && QeygmyinXf(7PGbDc30Fx(this)) >= Float.intBitsToFloat(12589 ^ 473970 ^ 'ꁅ' ^ -1341393698 ^ 8362 ^ 22353 ^ 27594 ^ -1891594696) && !this.isSprinting() && flag4 != 0 && !this.isHandActive() && !this.isPotionActive(7tOBguZfpn())) {
         if (tm172tYBBj(this) <= 0 && !VBrfrpGaLB(AZeaC4Tg7t(DiWSjlYrxd(this)))) {
            T476gjBqOe(this, 1032 ^ -21390 ^ 21631 ^ -1022);
         } else {
            this.setSprinting((boolean)(7023 ^ -3697 ^ 19013 ^ -24412));
         }
      }

      if (!this.isSprinting() && rS1BAu8rfq(pJWr1Y4PB2(this)) >= Float.intBitsToFloat(14750 ^ '컊' ^ 4633 ^ -518750860 ^ 15461 ^ 17637 ^ 7032 ^ -564644084) && flag4 != 0 && !this.isHandActive() && !this.isPotionActive(V6WMLN9Pt1()) && 6LpByqqlbT(YqFBHpOt6m(bHWPFiYINu(this)))) {
         this.setSprinting((boolean)(19083 ^ -15096 ^ 1460 ^ -30154));
      }

      if (this.isSprinting() && (Qqw19aCKF4(dAgAtoQKwJ(this)) < Float.intBitsToFloat(5832 ^ '\ue3f6' ^ '퓋' ^ -1411103696 ^ 22858 ^ 82917 ^ 127118 ^ -1800912087) || 2idMNYCt5j(this) || flag4 == 0)) {
         this.setSprinting((boolean)(31887 ^ -19078 ^ 22136 ^ -24691));
      }

      if (PbFaJLkBXo(Q6oRRywFBg(this)) && !flag && !6vk0GiKwmD(this) && Ol3SQHnS9O(this) < Double.longBitsToDouble(-6650370085534224578L ^ -6650370085534224578L) && !this.isElytraFlying() && !uqJqmqOaBI(jWQIYN4bnO(this))) {
         ItemStack itemstack = this.getItemStackFromSlot(STOJPr2HZ7());
         if (itemstack.getItem() == vOj6vNQToN() && !ItemElytra.isUsable(itemstack)) {
            8LZVyEjNh5(this).sendPacket(new CPacketEntityAction(this, euSqjLOhRK()));
         }
      }

      EFU0yPToKT(this, this.isElytraFlying());
      if (Bi2DtbDbdl(YlNJ9OJSpL(this)) && this.isCurrentViewEntity()) {
         if (bShbN1yry7(FT05n0T89N(this))) {
            vUG8aYdzRt(C682NBQH6T(this), (float)((double)JWm1rurBJv(RCNDBwgdTG(this)) / Double.longBitsToDouble(7816086138242483017L ^ 6029022328497598586L)));
            WFcWS2YQYR(ZvTJk4zIej(this), (float)((double)L6TVjEowuT(rMaFOrZosP(this)) / Double.longBitsToDouble(3290051793377288259L ^ 1331851214531384176L)));
            QaPvyfnVhE(this, gJJuVYJYtL(this) - (double)(WADvNlvvd4(this).getFlySpeed() * Float.intBitsToFloat(17441 ^ 22099 ^ 4545 ^ -1637043177 ^ 113723 ^ 101463 ^ 10462 ^ -567496938)));
         }

         if (Bclldu9ne0(BxIxnpRyTx(this))) {
            IYvnKPYq4Q(this, mBjVujPfno(this) + (double)(wiGNIrjFMv(this).getFlySpeed() * Float.intBitsToFloat(18957 ^ 15918 ^ 20178 ^ 319685302 ^ '뚑' ^ 234491 ^ '챦' ^ 1397608779)));
         }
      }

      if (this.isRidingHorse()) {
         IJumpingMount ijumpingmount = (IJumpingMount)this.getRidingEntity();
         if (uAJjPGpIij(this) < 0) {
            4atNva41Fp(this, rb9rHYGShr(this) + (11777 ^ -8175 ^ 25176 ^ -21431));
            if (cqoT9aDSjH(this) == 0) {
               bSSM22rjiM(this, Float.intBitsToFloat(18936 ^ 5414 ^ 24311 ^ 1135411558 ^ 21242 ^ 1628 ^ 22337 ^ 1135411368));
            }
         }

         if (flag && !nbI6gLT3nW(VZrExWWLJi(this))) {
            O90ESK99qj(this, -19826 ^ -4641 ^ 26771 ^ -14284);
            ijumpingmount.setJumpPower(MathHelper.floor(this.getHorseJumpPower() * Float.intBitsToFloat(19358 ^ 126998 ^ 21592 ^ -2037726925 ^ 'ꉜ' ^ 106635 ^ '\ue5e9' ^ -1002255907)));
            this.sendHorseJump();
         } else if (!flag && fSbaQ6TmwV(gNjbl75LtU(this))) {
            QnTMox9lSL(this, 2546 ^ -28767 ^ 31293 ^ -914);
            Nnn7oDQ0SW(this, Float.intBitsToFloat(20392 ^ 7483 ^ 23608 ^ -1704557764 ^ 28362 ^ 27612 ^ 2474 ^ -1704558293));
         } else if (flag) {
            vawW19rkNc(this, FgAYP77Cbi(this) + (9031 ^ -23279 ^ 18883 ^ -12396));
            if (ENiFVFIv6V(this) < (5727 ^ -31084 ^ 4 ^ -28475)) {
               a8bjlTnoSY(this, (float)Ea7fGyhTac(this) * Float.intBitsToFloat(27373 ^ '슇' ^ 21149 ^ -1076729540 ^ '腍' ^ 218686 ^ 31512 ^ -2111966355));
            } else {
               Bj5vK6EAdF(this, Float.intBitsToFloat(30668 ^ 252054 ^ 27927 ^ -523646301 ^ 125193 ^ 20990 ^ 125540 ^ -544827728) + Float.intBitsToFloat('샫' ^ '\uef81' ^ 12387 ^ -164544373 ^ '삑' ^ 28827 ^ '裸' ^ -1238296934) / (float)(7rP9wflEyn(this) - (31998 ^ -1382 ^ 669 ^ -31504)) * Float.intBitsToFloat('\udacc' ^ 'ꤱ' ^ 27204 ^ 1890936088 ^ 234328 ^ 17203 ^ 258509 ^ 1299812810));
            }
         }
      } else {
         zrsGtL9bTI(this, Float.intBitsToFloat(512574 ^ 519099 ^ 32641 ^ -1332500211 ^ 507327 ^ 507484 ^ 22949 ^ -1332497073));
      }

      super.onLivingUpdate();
      if (GNDjwgwydA(this) && lVBe1VxMbP(buP8wUJmpK(this)) && !Gvyl9qjJzc(UtlCrjWLru(this)).isSpectatorMode()) {
         omLWjQIDnA(5vQP9qwxGf(this), (boolean)(11014 ^ -23014 ^ 11444 ^ -24152));
         this.sendPlayerAbilities();
         this.jump();
         D3JqlctDJz(5i27Zoy92b(this), (boolean)(4249 ^ -7433 ^ 27467 ^ -26332));
         this.sendPlayerAbilities();
      }

   }

   private static float rngBddWN2G(0cD var0) {
      return var0.timeInPortal;
   }

   private static 0cL NTZVAXcYVL(0cD var0) {
      return var0.connection;
   }

   private static boolean bShbN1yry7(MovementInput var0) {
      return var0.sneak;
   }

   private static double oZO7DpBF5J(0cD var0) {
      return var0.posZ;
   }

   private static 0cL vYZxO4Fn1c(0cD var0) {
      return var0.connection;
   }

   private static float fvYWtDGMJj(0cD var0) {
      return var0.horseJumpPower;
   }

   private static boolean QOAJnqGvzZ(0cD var0) {
      return var0.wasFallFlying;
   }

   public void resetActiveHand() {
      super.resetActiveHand();
      47NZi1DiYe(this, (boolean)(30143 ^ -4529 ^ 29255 ^ -5705));
   }

   private static World GjdMIgotBO(0cD var0) {
      return var0.world;
   }

   private static float qp3nqLn42X(0cD var0) {
      return var0.rotationYaw;
   }

   private static void pyFGj7khGj(0cD var0, int var1) {
      var0.sprintingTicksLeft = var1;
   }

   private static void IYvnKPYq4Q(0cD var0, double var1) {
      var0.motionY = var1;
   }

   private static float lVOv1J9as4(0cD var0) {
      return var0.moveStrafing;
   }

   private static World jQq0dI0B9j(0cD var0) {
      return var0.world;
   }

   private static Potion ixJdWpiWdl() {
      return MobEffects.JUMP_BOOST;
   }

   private static double eAftOGikgT(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static void BWej2SegYw(0cD var0, float var1) {
      var0.lastDamage = var1;
   }

   private static ItemStack MJbo7bUOoW() {
      return ItemStack.EMPTY;
   }

   private static Potion xw0VbcPQWc() {
      return MobEffects.NAUSEA;
   }

   private static 0cL agrnAvkppQ(0cD var0) {
      return var0.connection;
   }

   private static 0cL tsJYQ47nrb(0cD var0) {
      return var0.connection;
   }

   private static float ZGEjFwrSwi(0cD var0) {
      return var0.rotationPitch;
   }

   private static float fildTlTJTC(MovementInput var0) {
      return var0.moveStrafe;
   }

   private static void RVt69zOzFF(0cD var0, float var1) {
      var0.lastReportedPitch = var1;
   }

   private static void i9Kna2Cjaa(0cD var0, Container var1) {
      var0.openContainer = var1;
   }

   private static 0cH UtlCrjWLru(0cD var0) {
      return var0.mc;
   }

   public void updateEntityActionState() {
      super.updateEntityActionState();
      if (this.isCurrentViewEntity()) {
         V36yL8vmJm(this, KjneZfrLRG(tyrDfKwSQV(this)));
         wGSRGF2wad(this, AAJXOV7aVW(TxDu2Qs2rw(this)));
         Hwbt1hlGqN(this, DRHkWIlAl7(gIyYBJoaUa(this)));
         dZZiJJwFG2(this, lGf33dE0Mo(this));
         UdId2DVtwG(this, jlgS6WjS6g(this));
         CoV93yViF7(this, (float)((double)bBjnneWeW2(this) + (double)(MjY6QonJIG(this) - S12Wxijr0o(this)) * Double.longBitsToDouble(5684786550836052350L ^ 8143751947380343166L)));
         CgJrLRkBo6(this, (float)((double)0deBqpwtTN(this) + (double)(fDg9rK6lG9(this) - Yn0MLJi47E(this)) * Double.longBitsToDouble(754779780587887754L ^ 3862263523473529994L)));
      }

   }

   private static double mLe4Cob9A0(Vec3d var0) {
      return var0.z;
   }

   public void sendPlayerAbilities() {
      foqQj8LFNO(this).sendPacket(new CPacketPlayerAbilities(a2g6aUNywQ(this)));
   }

   private static void _mW2V6gT6X/* $FF was: 9mW2V6gT6X*/(0cD var0, float var1) {
      var0.timeInPortal = var1;
   }

   public void sendHorseInventory() {
      7QPdaq44Ov(this).sendPacket(new CPacketEntityAction(this, rRJUlSGWwi()));
   }

   private static EnumHand Wm2gjWbrHW(0cD var0) {
      return var0.activeHand;
   }

   public Vec3d getLook(float partialTicks) {
      return getVectorForRotation(l12tl6jVwX(this), qp3nqLn42X(this));
   }

   private static int pGEwBdejQr(0cD var0) {
      return var0.sprintingTicksLeft;
   }

   private static boolean lKdFVF9k9V(0cD var0) {
      return var0.serverSprintState;
   }

   private static float ATyWHhnTdV(0cD var0) {
      return var0.rotationYaw;
   }

   private static float t7S0WoUxVs(MovementInput var0) {
      return var0.moveForward;
   }

   private static 0cL RGBiDcQwJx(0cD var0) {
      return var0.connection;
   }

   private static 0cE YqFBHpOt6m(0cH var0) {
      return var0.gameSettings;
   }

   private static float _0gLLxvVt1/* $FF was: 60gLLxvVt1*/(0cD var0) {
      return var0.timeInPortal;
   }

   public boolean isSpectator() {
      NetworkPlayerInfo networkplayerinfo = lNbhepSoBv(this).getPlayerInfo(this.getGameProfile().getId());
      return (boolean)(networkplayerinfo != null && networkplayerinfo.getGameType() == GEXvv7nmUW() ? 21253 ^ -30449 ^ 1078 ^ -8643 : 3633 ^ -29933 ^ 5518 ^ -28500);
   }

   private static MovementInput vd1gTqeGYY(0cD var0) {
      return var0.movementInput;
   }

   private static Potion _NqM8FB4xX/* $FF was: 9NqM8FB4xX*/() {
      return MobEffects.NAUSEA;
   }

   private static boolean azdQU0vtGK(MovementInput var0) {
      return var0.jump;
   }

   private static Potion _tOBguZfpn/* $FF was: 7tOBguZfpn*/() {
      return MobEffects.BLINDNESS;
   }

   private static float Qqw19aCKF4(MovementInput var0) {
      return var0.moveForward;
   }

   private static void Nnn7oDQ0SW(0cD var0, float var1) {
      var0.horseJumpPower = var1;
   }

   private static double _oBAl76P38/* $FF was: 6oBAl76P38*/(0cD var0) {
      return var0.motionX;
   }

   public _cD/* $FF was: 0cD*/(0cC pbot) {
      super(pbot.getWorld(), pbot.getPlayHandler().getGameProfile());
      this.pbot = pbot;
      this.connection = pbot.getPlayHandler();
      this.statWriter = new StatisticsManager();
      this.recipeBook = new RecipeBook();
      this.mc = pbot.mc;
      this.dimension = 6694 ^ -23079 ^ 27943 ^ -11560;
      this.pbot.player = this;
   }

   private static float dJlxr6V9G0(0cD var0) {
      return var0.timeInPortal;
   }

   private static void EAIrdU6M09(0cD var0, Container var1) {
      var0.openContainer = var1;
   }

   private static int Ea7fGyhTac(0cD var0) {
      return var0.horseJumpPowerCounter;
   }

   private static double NG1vKD6FNV(0cD var0) {
      return var0.posX;
   }

   protected boolean isCurrentViewEntity() {
      return (boolean)(10398 ^ -1016 ^ 12655 ^ -6664);
   }

   private static World rF2LI1cyga(0cD var0) {
      return var0.world;
   }

   private static boolean _idMNYCt5j/* $FF was: 2idMNYCt5j*/(0cD var0) {
      return var0.collidedHorizontally;
   }

   private static boolean VBrfrpGaLB(0cE var0) {
      return var0.keyBindSprint;
   }

   private static InventoryPlayer VrbeJBWTRy(0cD var0) {
      return var0.inventory;
   }

   private static void GMvqvYnFOL(0cD var0, int var1) {
      var0.autoJumpTime = var1;
   }

   private static boolean sT7UaUBx34(PlayerCapabilities var0) {
      return var0.allowFlying;
   }

   private static MovementInput WNo6niNidR(0cD var0) {
      return var0.movementInput;
   }

   private static double WrLdTQZyEr(0cD var0) {
      return var0.posX;
   }

   private static PlayerCapabilities YlNJ9OJSpL(0cD var0) {
      return var0.capabilities;
   }

   @Nullable
   public PotionEffect removeActivePotionEffect(@Nullable Potion potioneffectin) {
      if (potioneffectin == xw0VbcPQWc()) {
         l4bLdlQZ2C(this, Float.intBitsToFloat(27861 ^ '캹' ^ 28038 ^ 1382314312 ^ 106558 ^ 23298 ^ 123082 ^ 1382327636));
         9mW2V6gT6X(this, Float.intBitsToFloat('쇣' ^ '\ue0a6' ^ 24135 ^ 1505371993 ^ '馞' ^ '镾' ^ 17604 ^ 1505366143));
      }

      return super.removeActivePotionEffect(potioneffectin);
   }

   private static void DdKaQeTIJ9(0cD var0, double var1) {
      var0.posZ = var1;
   }

   private static void QnTMox9lSL(0cD var0, int var1) {
      var0.horseJumpPowerCounter = var1;
   }

   private static double jQrTwqrS8o(0cD var0) {
      return var0.posZ;
   }

   private static double Ol3SQHnS9O(0cD var0) {
      return var0.motionY;
   }

   private static void CgJrLRkBo6(0cD var0, float var1) {
      var0.renderArmYaw = var1;
   }

   private static void AV69F68rQc(0cD var0, double var1) {
      var0.prevPosZ = var1;
   }

   private static float Ibkv7bQZxP(Entity var0) {
      return var0.rotationYaw;
   }

   public void setPlayerSPHealth(float health) {
      if (y4TLj1qUu6(this)) {
         float f = this.getHealth() - health;
         if (f <= Float.intBitsToFloat(12995 ^ 114421 ^ 25662 ^ 386069223 ^ '骻' ^ 99006 ^ 'וּ' ^ 386071007)) {
            this.setHealth(health);
            if (f < Float.intBitsToFloat(3040 ^ '\udce7' ^ 19250 ^ -1029227505 ^ 23332 ^ 993418 ^ 1045012 ^ -1029234304)) {
               VzvTIgqFG4(this, oO9qOreimf(this) / (24199 ^ -21976 ^ 16934 ^ -18805));
            }
         } else {
            BWej2SegYw(this, f);
            this.setHealth(this.getHealth());
            BtLVbiG7hp(this, FD3YKJ2uAv(this));
            this.damageEntity(DJDDwSlFTY(), f);
            TTW0jkB2pw(this, 7229 ^ -12591 ^ 11162 ^ -1668);
            AobWi2Ib01(this, QdbqOIQUL7(this));
         }
      } else {
         this.setHealth(health);
         fljehSSDy7(this, (boolean)(14372 ^ -15272 ^ 22055 ^ -21926));
      }

   }

   private static World iF7FbCWtOw(0cD var0) {
      return var0.world;
   }

   private static double DSzCIVB2WF(Vec3d var0) {
      return var0.y;
   }

   private static void AIgvtLFxqY(0cD var0, double var1) {
      var0.lastReportedPosX = var1;
   }

   private static float lGf33dE0Mo(0cD var0) {
      return var0.renderArmYaw;
   }

   private static InventoryPlayer gW1aVByVix(0cD var0) {
      return var0.inventory;
   }

   private static void J9xOeBEtTa(0cD var0, boolean var1) {
      var0.prevOnGround = var1;
   }

   private static StatisticsManager IG9oCneu3d(0cD var0) {
      return var0.statWriter;
   }

   private static void tJBWh7NLEN(0cD var0, Container var1) {
      var0.openContainer = var1;
   }

   private static World BFiOeVsA9Y(0cD var0) {
      return var0.world;
   }

   private static boolean _LpByqqlbT/* $FF was: 6LpByqqlbT*/(0cE var0) {
      return var0.keyBindSprint;
   }

   public BlockPos getPosition() {
      return new BlockPos(AJ7StwvwFg(this) + Double.longBitsToDouble(-2381933641570150461L ^ -2228811254239553597L), ZUjNT3WC9B(this) + Double.longBitsToDouble(-2705545556023676980L ^ -1903904822351728692L), nXL5Y7SAJ5(this) + Double.longBitsToDouble(4824977416883973069L ^ 9013325070338534349L));
   }

   private static void SwW98gWitZ(0cD var0, Container var1) {
      var0.openContainer = var1;
   }

   private static float jumtg6jp2p(0cD var0) {
      return var0.prevRotationYaw;
   }

   private static float fDg9rK6lG9(0cD var0) {
      return var0.rotationYaw;
   }

   private static float n7G0Q6wLx7(0cD var0) {
      return var0.rotationPitch;
   }

   public void setActiveHand(EnumHand hand) {
      ItemStack itemstack = this.getHeldItem(hand);
      if (!itemstack.isEmpty() && !this.isHandActive()) {
         super.setActiveHand(hand);
         AtgDeFjXkK(this, (boolean)(22151 ^ -8663 ^ 12941 ^ -17886));
         e6rawJjpdy(this, hand);
      }

   }

   private static 0dB EhS6kWYQcI(0bK var0) {
      return var0.pBotsScriptManager;
   }

   private static boolean vH0F1vAvdo(0cD var0) {
      return var0.onGround;
   }

   private static double V7r1AG6TSI(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static PlayerCapabilities wiGNIrjFMv(0cD var0) {
      return var0.capabilities;
   }

   private static double NSDF7DGpZJ(0cD var0) {
      return var0.posZ;
   }

   public void sendStatusMessage(ITextComponent chatComponent, boolean actionBar) {
      if (actionBar) {
      }

   }

   private static MovementInput Q6oRRywFBg(0cD var0) {
      return var0.movementInput;
   }

   private static double TQWoRApsre(0cD var0) {
      return var0.posX;
   }

   private static CPacketEntityAction.Action YFWo46p1mG() {
      return CPacketEntityAction.Action.STOP_SNEAKING;
   }

   private static void HscODu7V4t(0cD var0, boolean var1) {
      var0.inPortal = var1;
   }

   private static double JwquAxWef2(0cD var0) {
      return var0.posX;
   }

   private static double SVStt7id2f(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static PlayerCapabilities _i27Zoy92b/* $FF was: 5i27Zoy92b*/(0cD var0) {
      return var0.capabilities;
   }

   private static boolean qOeYFs1ANv(0cD var0) {
      return var0.noClip;
   }

   private static MovementInput NSPOIAVxS1(0cD var0) {
      return var0.movementInput;
   }

   private static MovementInput KOLrPYKAfg(0cD var0) {
      return var0.movementInput;
   }

   private static 0cL wcD1G6gOHH(0cD var0) {
      return var0.connection;
   }

   public void onEnchantmentCritical(Entity entityHit) {
   }

   public void openBook(ItemStack stack, EnumHand hand) {
      Item item = stack.getItem();
      if (item == vyLbHJA9ny()) {
      }

   }

   private static World tROO2mh7Fb(0cD var0) {
      return var0.world;
   }

   private static MovementInput PCzaKLJbSy(0cD var0) {
      return var0.movementInput;
   }

   private static double t5V5JlG7eL(0cD var0) {
      return var0.posZ;
   }

   public void sendMessage(ITextComponent component) {
   }

   private static float q1eb1ioTVz(0cD var0) {
      return var0.width;
   }

   private static 0cL eYzvjCgeT5(0cD var0) {
      return var0.connection;
   }

   private static 0cH bHWPFiYINu(0cD var0) {
      return var0.mc;
   }

   private static float stmnce5rAG(0cD var0) {
      return var0.height;
   }

   private static boolean PbFaJLkBXo(MovementInput var0) {
      return var0.jump;
   }

   private static double X43wJ94A7l(0cD var0) {
      return var0.posX;
   }

   private static void NxiJrftHqu(0cD var0, double var1) {
      var0.posX = var1;
   }

   private static void CoV93yViF7(0cD var0, float var1) {
      var0.renderArmPitch = var1;
   }

   private static float _VX8YWwiNA/* $FF was: 8VX8YWwiNA*/(0cD var0) {
      return var0.moveVertical;
   }

   private static double SLAanuFRJo(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static boolean Caaf96Qwhy(StatBase var0) {
      return var0.isIndependent;
   }

   private static double JQ3gtnPqbD(0cD var0) {
      return var0.posX;
   }

   private static GameType FKcNp8OTHj() {
      return GameType.CREATIVE;
   }

   private static void WFcWS2YQYR(MovementInput var0, float var1) {
      var0.moveForward = var1;
   }

   private static InventoryPlayer etDML72byg(0cD var0) {
      return var0.inventory;
   }

   private static int _hsLjpDwDG/* $FF was: 7hsLjpDwDG*/(0cD var0) {
      return var0.autoJumpTime;
   }

   private static double _jKA92DTeZ/* $FF was: 0jKA92DTeZ*/(0cD var0) {
      return var0.posZ;
   }

   private static float WQJoZnSIqA(0cD var0) {
      return var0.width;
   }

   public boolean startRiding(Entity entityIn, boolean force) {
      if (!super.startRiding(entityIn, force)) {
         return (boolean)(2322 ^ -18994 ^ 29087 ^ -12989);
      } else {
         if (entityIn instanceof EntityBoat) {
            UY6iGyjbhe(this, 524GY8VTK1(entityIn));
            wI2uedLoqh(this, Ibkv7bQZxP(entityIn));
            this.setRotationYawHead(ZeYQGnw7Pu(entityIn));
         }

         return (boolean)(4280 ^ -20138 ^ 16353 ^ -25074);
      }
   }

   private static InventoryPlayer lV5jVfsBlH(0cD var0) {
      return var0.inventory;
   }

   public void setSprinting(boolean sprinting) {
      super.setSprinting(sprinting);
      pyFGj7khGj(this, 13779 ^ -18731 ^ 13919 ^ -19111);
   }

   private static void gefjgjfrQA(0cD var0, float var1) {
      var0.prevRotationPitch = var1;
   }

   private static float lXDynLqELF(0cD var0) {
      return var0.width;
   }

   private static double ZUjNT3WC9B(0cD var0) {
      return var0.posY;
   }

   private static float _1qQXvtjNZ/* $FF was: 61qQXvtjNZ*/(Vec2f var0) {
      return var0.x;
   }

   private static float l12tl6jVwX(0cD var0) {
      return var0.rotationPitch;
   }

   private static 0cL aDA5oKpcgt(0cD var0) {
      return var0.connection;
   }

   private static DataParameter aoTDpwoy5Z() {
      return FLAGS;
   }

   public boolean isServerWorld() {
      return (boolean)(20979 ^ -28964 ^ 7863 ^ -15975);
   }

   private static void xB4xNWvruq(0cD var0, double var1) {
      var0.prevPosX = var1;
   }

   private static CPacketEntityAction.Action rRJUlSGWwi() {
      return CPacketEntityAction.Action.OPEN_INVENTORY;
   }

   private static void LwYXilyeEi(0cD var0, Container var1) {
      var0.openContainer = var1;
   }

   private static boolean _0WrjRzZNE/* $FF was: 00WrjRzZNE*/(0cD var0) {
      return var0.onGround;
   }

   private static double APw6vb7VJ4(0cD var0) {
      return var0.lastReportedPosY;
   }

   private static MovementInput rMaFOrZosP(0cD var0) {
      return var0.movementInput;
   }

   private static boolean lVBe1VxMbP(PlayerCapabilities var0) {
      return var0.isFlying;
   }

   private static 0cL TaeYV2eYDx(0cD var0) {
      return var0.connection;
   }

   private static double _QzOqj3NBv/* $FF was: 5QzOqj3NBv*/(0cD var0) {
      return var0.posZ;
   }

   private static float _deBqpwtTN/* $FF was: 0deBqpwtTN*/(0cD var0) {
      return var0.renderArmYaw;
   }

   private static int cqoT9aDSjH(0cD var0) {
      return var0.horseJumpPowerCounter;
   }

   public void displayGui(IInteractionObject guiOwner) {
      String s = guiOwner.getGuiID();
      if (!ljSpJutVPH("MINECRAFT\u001aCRAFTING\u007fTABLE").equals(s)) {
         if (ljSpJutVPH("MINECRAFT\u001aENCHANTING\u007fTABLE").equals(s)) {
            XQWWhW46ki(this, new ContainerEnchantment(iByvBdU2nb(this), VO8GESn1HY(this)));
         } else if (ljSpJutVPH("MINECRAFT\u001aANVIL").equals(s)) {
            eOeVQLxSbl(this, new ContainerRepair(6LSoKc9gwx(this), BFiOeVsA9Y(this), this));
         }
      }

   }

   public boolean isRidingHorse() {
      Entity entity = this.getRidingEntity();
      return (boolean)(this.isRiding() && entity instanceof IJumpingMount && ((IJumpingMount)entity).canJump() ? 606 ^ -8104 ^ 14607 ^ -9464 : 10118 ^ -13315 ^ 64 ^ -5061);
   }

   private static boolean GNDjwgwydA(0cD var0) {
      return var0.onGround;
   }

   private static Potion JyvCeRSa29() {
      return MobEffects.NAUSEA;
   }

   private static CPacketEntityAction.Action W63dKVc1wa() {
      return CPacketEntityAction.Action.START_SPRINTING;
   }

   private static RecipeBook O1sqgSpgEb(0cD var0) {
      return var0.recipeBook;
   }

   private static void vgaaBO04yS(0cD var0, Container var1) {
      var0.openContainer = var1;
   }

   private static Potion V6WMLN9Pt1() {
      return MobEffects.BLINDNESS;
   }

   private static boolean krRNbG9azr(0cD var0) {
      return var0.inPortal;
   }

   private static boolean LFa2CoRYM2(0cD var0) {
      return var0.onGround;
   }

   private static double Ek7GEWiJ1K(Vec3d var0) {
      return var0.x;
   }

   private static float pqc9EoqEri(Vec2f var0) {
      return var0.y;
   }

   private static CPacketEntityAction.Action euSqjLOhRK() {
      return CPacketEntityAction.Action.START_FALL_FLYING;
   }

   private static void g6Hf6BDbdi(0cD var0, Container var1) {
      var0.openContainer = var1;
   }

   private static int _ZlFLyqUQV/* $FF was: 2ZlFLyqUQV*/(Container var0) {
      return var0.windowId;
   }

   private static float _24GY8VTK1/* $FF was: 524GY8VTK1*/(Entity var0) {
      return var0.rotationYaw;
   }

   private static float _QBbWNEbIF/* $FF was: 1QBbWNEbIF*/(0cD var0) {
      return var0.rotationYaw;
   }

   private static Container iinjAbYo6h(0cD var0) {
      return var0.openContainer;
   }

   private static void lb9l1qmbnx(0cD var0, float var1) {
      var0.timeInPortal = var1;
   }

   private static 0cL qtQEPrrqIk(0cD var0) {
      return var0.connection;
   }

   private static String pj3g65vPQc(0cD var0) {
      return var0.serverBrand;
   }

   private static int JZGswdI1LS(0cD var0) {
      return var0.sprintToggleTimer;
   }

   private static MovementInput FT05n0T89N(0cD var0) {
      return var0.movementInput;
   }

   private static void dMFnFywRIF(0cD var0, float var1) {
      var0.rotationYaw = var1;
   }

   private static void _RcvotA2aA/* $FF was: 3RcvotA2aA*/(0cD var0, float var1) {
      var0.prevTimeInPortal = var1;
   }

   private static boolean temwVbooA9(MovementInput var0) {
      return var0.backKeyDown;
   }

   private static double trnc774qur(0cD var0) {
      return var0.posZ;
   }

   private static Item vyLbHJA9ny() {
      return Items.WRITABLE_BOOK;
   }

   public void openEditSign(TileEntitySign signTile) {
      0dB var10000 = EhS6kWYQcI(0bK.getInstance());
      String var10001 = ljSpJutVPH("ONoPENeDITsIGN");
      Object[] var10002 = new Object[10642 ^ -27834 ^ 30595 ^ -12971];
      var10002[21020 ^ -17076 ^ 604 ^ -4852] = unlVX1qutC(this);
      var10002[10512 ^ -23345 ^ 1121 ^ -30273] = signTile;
      var10000.invokeMethod(var10001, var10002);
   }

   private static double wnazUQD14D(0cD var0) {
      return var0.posX;
   }

   public void handleStatusUpdate(byte id) {
      if (id >= (1232 ^ -17553 ^ 2789 ^ -19134) && id <= (32524 ^ -21863 ^ 13788 ^ -8107)) {
         this.setPermissionLevel(id - (17208 ^ -30763 ^ 30730 ^ -17153));
      } else {
         super.handleStatusUpdate(id);
      }

   }

   private static BlockPos Aa15MoeP5B() {
      return BlockPos.ORIGIN;
   }

   private static MovementInput jbSIrSHjiO(0cD var0) {
      return var0.movementInput;
   }

   private static float Yn0MLJi47E(0cD var0) {
      return var0.renderArmYaw;
   }

   private static void T5xwfN7Lsy(0cD var0, int var1) {
      var0.sprintingTicksLeft = var1;
   }

   private static void vUG8aYdzRt(MovementInput var0, float var1) {
      var0.moveStrafe = var1;
   }

   private static void QaPvyfnVhE(0cD var0, double var1) {
      var0.motionY = var1;
   }

   private static void BtLVbiG7hp(0cD var0, int var1) {
      var0.hurtResistantTime = var1;
   }

   private static boolean Bclldu9ne0(MovementInput var0) {
      return var0.jump;
   }

   public void displayGUIChest(IInventory chestInventory) {
      String s = chestInventory instanceof IInteractionObject ? ((IInteractionObject)chestInventory).getGuiID() : ljSpJutVPH("MINECRAFT\u001aCONTAINER");
      if (ljSpJutVPH("MINECRAFT\u001aCHEST").equals(s)) {
         SwW98gWitZ(this, new ContainerChest(6dnjida36W(this), chestInventory, this));
      } else if (ljSpJutVPH("MINECRAFT\u001aHOPPER").equals(s)) {
         VfGqYOtQ8Q(this, new ContainerHopper(UOZO9qW2OQ(this), chestInventory, this));
      } else if (ljSpJutVPH("MINECRAFT\u001aFURNACE").equals(s)) {
         vgaaBO04yS(this, new ContainerFurnace(2TvWVt6NAN(this), chestInventory));
      } else if (ljSpJutVPH("MINECRAFT\u001aBREWING\u007fSTAND").equals(s)) {
         g6Hf6BDbdi(this, new ContainerBrewingStand(etDML72byg(this), chestInventory));
      } else if (ljSpJutVPH("MINECRAFT\u001aBEACON").equals(s)) {
         FDK7WgD2jX(this, new ContainerBeacon(2gvwdqJ7W7(this), chestInventory));
      } else if (!ljSpJutVPH("MINECRAFT\u001aDISPENSER").equals(s) && !ljSpJutVPH("MINECRAFT\u001aDROPPER").equals(s)) {
         if (ljSpJutVPH("MINECRAFT\u001aSHULKER\u007fBOX").equals(s)) {
            EAIrdU6M09(this, new ContainerShulkerBox(SONhQOnpYZ(this), chestInventory, this));
         } else {
            i9Kna2Cjaa(this, new ContainerChest(pN4AoLF22g(this), chestInventory, this));
         }
      } else {
         nWr7vSLEEv(this, new ContainerDispenser(w2q5LwxlBo(this), chestInventory));
      }

   }

   private static void e3iqGNeL2j(0cD var0, float var1) {
      var0.timeInPortal = var1;
   }

   private static MovementInput _PGbDc30Fx/* $FF was: 7PGbDc30Fx*/(0cD var0) {
      return var0.movementInput;
   }

   public StatisticsManager getStatFileWriter() {
      return IG9oCneu3d(this);
   }

   private static float i4RebFZxGL(0cD var0) {
      return var0.width;
   }

   public void openEditStructure(TileEntityStructure structure) {
   }

   private static World yb24C4iAcU(0cD var0) {
      return var0.world;
   }

   private static InventoryPlayer iByvBdU2nb(0cD var0) {
      return var0.inventory;
   }

   private static void vawW19rkNc(0cD var0, int var1) {
      var0.horseJumpPowerCounter = var1;
   }

   private static void XQWWhW46ki(0cD var0, Container var1) {
      var0.openContainer = var1;
   }

   private static void FQl8Bt6mIw(0cD var0, float var1) {
      var0.prevRotationYaw = var1;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String ljSpJutVPH(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 29196 ^ -26322 ^ 28317 ^ -31297; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 25041 ^ -28026 ^ 22329 ^ -23474));
      }

      return var1.toString();
   }

   private static void _jjyVw2NfD/* $FF was: 6jjyVw2NfD*/(0cD var0, boolean var1) {
      var0.autoJumpEnabled = var1;
   }

   private static boolean FibJnyQIjA(0cD var0) {
      return var0.onGround;
   }

   private static MovementInput obdUQ79Ow7(0cD var0) {
      return var0.movementInput;
   }

   private static 0cC gDilWCRNAB(0cD var0) {
      return var0.pbot;
   }

   private static double MTB6r4sNvQ(0cD var0) {
      return var0.posZ;
   }

   private static int QdbqOIQUL7(0cD var0) {
      return var0.maxHurtTime;
   }

   public boolean isUser() {
      return (boolean)(27481 ^ -12671 ^ 16972 ^ -6251);
   }

   private static double hba4aXt337(0cD var0) {
      return var0.lastReportedPosZ;
   }

   private static void D7rcGXNR7K(0cD var0, float var1) {
      var0.experience = var1;
   }

   private static World h3SjOh1xFG(0cD var0) {
      return var0.world;
   }

   private static void roCoNSaQbD(0cD var0, String var1) {
      var0.serverBrand = var1;
   }

   private static void vH38yQUOqw(0cD var0, float var1) {
      var0.timeInPortal = var1;
   }

   private static float WldBSotFwd(0cD var0) {
      return var0.height;
   }

   private static double _MuIG6wfHD/* $FF was: 0MuIG6wfHD*/(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static RecipeBook LuY6Nthaju(0cD var0) {
      return var0.recipeBook;
   }

   private static void Hwbt1hlGqN(0cD var0, boolean var1) {
      var0.isJumping = var1;
   }

   public void displayVillagerTradeGui(IMerchant villager) {
      tJBWh7NLEN(this, new ContainerMerchant(VrbeJBWTRy(this), villager, xlg4RGJaaL(this)));
   }

   private static boolean OS7XgXVgvr(MovementInput var0) {
      return var0.sneak;
   }

   private static boolean Bi2DtbDbdl(PlayerCapabilities var0) {
      return var0.isFlying;
   }

   private static CPacketPlayerDigging.Action E9wenOerjc() {
      return CPacketPlayerDigging.Action.DROP_ITEM;
   }

   private static InventoryPlayer _dnjida36W/* $FF was: 6dnjida36W*/(0cD var0) {
      return var0.inventory;
   }

   protected void sendHorseJump() {
      agrnAvkppQ(this).sendPacket(new CPacketEntityAction(this, taQ9JxQFat(), MathHelper.floor(this.getHorseJumpPower() * Float.intBitsToFloat(6220 ^ 13096 ^ 9381 ^ 2117468524 ^ 14719 ^ 238058 ^ 17878 ^ 1023267822))));
   }

   private static void XNnndMVweh(0cD var0, double var1) {
      var0.posY = var1;
   }

   private static void dZZiJJwFG2(0cD var0, float var1) {
      var0.prevRenderArmYaw = var1;
   }

   private static float eo2lZZ8xlu(MovementInput var0) {
      return var0.moveForward;
   }

   public void closeScreenAndDropStack() {
      gW1aVByVix(this).setItemStack(YshbvfAPa5());
      super.closeScreen();
   }

   private static float q43oyANabr(0cD var0) {
      return var0.rotationYaw;
   }

   private static float OVGSrme4q2(0cD var0) {
      return var0.timeInPortal;
   }

   public RecipeBook getRecipeBook() {
      return z45pQVIqbP(this);
   }

   public void moveEntity(MoverType type, double x, double y, double z) {
      double d0 = IfqqgB4jeJ(this);
      double d1 = 0jKA92DTeZ(this);
      super.move(type, x, y, z);
      this.updateAutoJump((float)(AaDLOy3t0e(this) - d0), (float)(e9GD3awOY9(this) - d1));
   }

   private static boolean NJ5XpDMjNF(MovementInput var0) {
      return var0.leftKeyDown;
   }

   public boolean isCreative() {
      NetworkPlayerInfo networkplayerinfo = OYIgSptlnY(this).getPlayerInfo(this.getGameProfile().getId());
      return (boolean)(networkplayerinfo != null && networkplayerinfo.getGameType() == FKcNp8OTHj() ? 14416 ^ -19690 ^ 15194 ^ -20451 : 25158 ^ -16868 ^ 25428 ^ -16626);
   }

   private static MovementInput gNjbl75LtU(0cD var0) {
      return var0.movementInput;
   }

   private static float MjY6QonJIG(0cD var0) {
      return var0.rotationPitch;
   }

   public void onCriticalHit(Entity entityHit) {
   }

   private static void pwGi3qrnAq(0cD var0, float var1) {
      var0.timeInPortal = var1;
   }

   private static 0cL lNbhepSoBv(0cD var0) {
      return var0.connection;
   }

   private static boolean D6JwmwVvCd(MovementInput var0) {
      return var0.sneak;
   }

   private static float DTw1wJbTfe(0cD var0) {
      return var0.rotationYaw;
   }

   private static MovementInput pJWr1Y4PB2(0cD var0) {
      return var0.movementInput;
   }

   public void setServerBrand(String brand) {
      roCoNSaQbD(this, brand);
   }

   private static void eOeVQLxSbl(0cD var0, Container var1) {
      var0.openContainer = var1;
   }

   public EnumHand getActiveHand() {
      return Wm2gjWbrHW(this);
   }

   private static float CuD7JLIWoS(0cD var0) {
      return var0.timeInPortal;
   }

   protected void damageEntity(DamageSource damageSrc, float damageAmount) {
      if (!this.isEntityInvulnerable(damageSrc)) {
         this.setHealth(this.getHealth() - damageAmount);
      }

   }

   private static void nlFJ7G69vX(0cD var0, double var1) {
      var0.posZ = var1;
   }

   private static void oRO1ogeAAB(0cD var0, double var1) {
      var0.lastReportedPosZ = var1;
   }

   private static double _6ay7tWZAb/* $FF was: 66ay7tWZAb*/(0cD var0) {
      return var0.posZ;
   }

   private static CPacketClientStatus.State JfgSnp5r9H() {
      return CPacketClientStatus.State.PERFORM_RESPAWN;
   }

   private static boolean bDn8eBb0BS(0cD var0) {
      return var0.onGround;
   }

   private static void OWWJ3MlLAV(0cD var0, double var1) {
      var0.lastReportedPosY = var1;
   }

   private static void _ESN5imHXE/* $FF was: 1ESN5imHXE*/(0cD var0, int var1) {
      var0.experienceTotal = var1;
   }

   private static void _6oEt2IbkJ/* $FF was: 96oEt2IbkJ*/(0cD var0, int var1) {
      var0.experienceLevel = var1;
   }

   public void setPosition(double x, double y, double z) {
      NxiJrftHqu(this, x);
      XNnndMVweh(this, y);
      DdKaQeTIJ9(this, z);
      float f = lXDynLqELF(this) / Float.intBitsToFloat(101926 ^ 117404 ^ 12431 ^ 157847510 ^ 112090 ^ 124184 ^ 7951 ^ 1231598638);
      float f1 = WldBSotFwd(this);
      this.setEntityBoundingBox(new AxisAlignedBB(x - (double)f, y, z - (double)f, x + (double)f, y + (double)f1, z + (double)f));
   }

   private void onUpdateWalkingPlayer() {
      boolean flag = this.isSprinting();
      if (flag != lKdFVF9k9V(this)) {
         if (flag) {
            UvFtlkGm1b(this).sendPacket(new CPacketEntityAction(this, W63dKVc1wa()));
         } else {
            vYZxO4Fn1c(this).sendPacket(new CPacketEntityAction(this, d7gIiTZlEp()));
         }

         ybwoeWYt4l(this, flag);
      }

      boolean flag1 = this.isSneaking();
      if (flag1 != G2DOQdfc26(this)) {
         if (flag1) {
            4OOqzQtIOR(this).sendPacket(new CPacketEntityAction(this, 1yUTlenqGR()));
         } else {
            wcD1G6gOHH(this).sendPacket(new CPacketEntityAction(this, YFWo46p1mG()));
         }

         SNBxwFGIae(this, flag1);
      }

      if (this.isCurrentViewEntity()) {
         AxisAlignedBB axisalignedbb = this.getEntityBoundingBox();
         double d0 = k0H4nA03vM(this) - 4AqSHHQg1j(this);
         double d1 = wIFUgJdwFc(axisalignedbb) - APw6vb7VJ4(this);
         double d2 = jNGYfCjwin(this) - hba4aXt337(this);
         float yaw = sM0lx28RtG(this);
         float pitch = eRo26YtG7A(this);
         double d3 = (double)(yaw - BlvGcNGQO3(this));
         double d4 = (double)(pitch - 5sha657l9p(this));
         nIirDvT4DG(this, jFacf8dTYm(this) + (701 ^ -14161 ^ 13009 ^ -1854));
         boolean flag2 = !(d0 * d0 + d1 * d1 + d2 * d2 > Double.longBitsToDouble(8192693927252072L ^ 4562259163190025210L)) && GMwBIw1Dyo(this) < (30280 ^ -947 ^ 12320 ^ -17871) ? 3157 ^ -7335 ^ 21225 ^ -16923 : 8320 ^ -30613 ^ 29566 ^ -9324;
         boolean flag3 = d3 == Double.longBitsToDouble(4898259608948969429L ^ 4898259608948969429L) && d4 == Double.longBitsToDouble(-1327623823358903819L ^ -1327623823358903819L) ? 4476 ^ -25717 ^ 4815 ^ -26568 : 14846 ^ -19992 ^ 7316 ^ -27517;
         if (this.isRiding()) {
            RGBiDcQwJx(this).sendPacket(new CPacketPlayer.PositionRotation(6oBAl76P38(this), Double.longBitsToDouble(1689619300976393114L ^ -2883003268339369062L), RQdvweNV8x(this), gQDnOjekQL(this), 3Q0SKBt271(this), Qido1acSSy(this)));
            flag2 = 24051 ^ -21722 ^ 14422 ^ -12669;
         } else if (flag2 != 0 && flag3 != 0) {
            NTZVAXcYVL(this).sendPacket(new CPacketPlayer.PositionRotation(wnazUQD14D(this), Do9AL3xEKL(this), iVSQoa3GHF(this), q43oyANabr(this), i06y0IFXnm(this), uLme2YnAMw(this)));
         } else if (flag2 != 0) {
            qtQEPrrqIk(this).sendPacket(new CPacketPlayer.Position(JQ3gtnPqbD(this), 7BOvofDcXL(this), trnc774qur(this), FibJnyQIjA(this)));
         } else if (flag3 != 0) {
            6ABjfSr8iD(this).sendPacket(new CPacketPlayer.Rotation(DTw1wJbTfe(this), HVlt22FYS6(this), RykFVOcWvo(this)));
         } else if (i5jnrehj9H(this) != vH0F1vAvdo(this)) {
            tsJYQ47nrb(this).sendPacket(new CPacketPlayer(trolWynTz6(this)));
         }

         if (flag2 != 0) {
            AIgvtLFxqY(this, TQWoRApsre(this));
            OWWJ3MlLAV(this, TAapFVWJoI(axisalignedbb));
            oRO1ogeAAB(this, jF7rlTnrUQ(this));
            3y2yIYneKO(this, 28503 ^ -20773 ^ 19110 ^ -29910);
         }

         if (flag3 != 0) {
            XO9YIaBO6i(this, yaw);
            RVt69zOzFF(this, pitch);
         }

         J9xOeBEtTa(this, t8gmnyMqoS(this));
         6jjyVw2NfD(this, (boolean)(18818 ^ -12262 ^ 25399 ^ -1362));
      }

   }

   private static MovementInput C682NBQH6T(0cD var0) {
      return var0.movementInput;
   }

   private static void VfGqYOtQ8Q(0cD var0, Container var1) {
      var0.openContainer = var1;
   }

   public void notifyDataManagerChange(DataParameter<?> key) {
      super.notifyDataManagerChange(key);
      if (wqVpQ8JCci().equals(key)) {
         boolean flag = ((Byte)0Gi2VX0gVL(this).get(l4DfydWdtz()) & (17343 ^ -8815 ^ 24873 ^ -250)) > 0 ? 31673 ^ -2151 ^ 22402 ^ -9309 : 18659 ^ -28953 ^ 19205 ^ -29439;
         EnumHand enumhand = ((Byte)AV4zUYWpnN(this).get(6Zq4r2ttDo()) & (19469 ^ -18187 ^ 17338 ^ -18624)) > 0 ? bvldBvlKND() : jFeD6quhiD();
         if (flag != 0 && !DE7dt4kFtN(this)) {
            this.setActiveHand(enumhand);
         } else if (flag == 0 && WIGJyXKvbJ(this)) {
            this.resetActiveHand();
         }
      }

      if (aoTDpwoy5Z().equals(key) && this.isElytraFlying() && !QOAJnqGvzZ(this)) {
      }

   }

   private static void diEaGDeaBq(0cD var0, float var1) {
      var0.rotationPitch = var1;
   }

   private static void JqRIvAOGGr(0cD var0, double var1) {
      var0.motionZ = var1;
   }

   private static float HjIrrBejfk(0cD var0) {
      return var0.prevRotationYaw;
   }

   private static void wGSRGF2wad(0cD var0, float var1) {
      var0.moveForward = var1;
   }

   public boolean attackEntityFrom(DamageSource source, float amount) {
      return (boolean)(16552 ^ -4697 ^ 32195 ^ -12084);
   }

   private static double gJJuVYJYtL(0cD var0) {
      return var0.motionY;
   }

   public void dismountRidingEntity() {
      super.dismountRidingEntity();
      7doYTk4C1n(this, (boolean)(2383 ^ -4777 ^ 26439 ^ -31905));
   }

   private static World VO8GESn1HY(0cD var0) {
      return var0.world;
   }

   private static void _v2JFcFOvq/* $FF was: 6v2JFcFOvq*/(0cD var0, float var1) {
      var0.prevRotationYaw = var1;
   }

   private static MovementInput ODD1uQfUqF(0cD var0) {
      return var0.movementInput;
   }

   private static void _atNva41Fp/* $FF was: 4atNva41Fp*/(0cD var0, int var1) {
      var0.horseJumpPowerCounter = var1;
   }

   public void displayGuiCommandBlock(TileEntityCommandBlock commandBlock) {
   }

   private static EnumFacing A3sSBdleLn() {
      return EnumFacing.DOWN;
   }

   private static float OYZy0Ylk4t(0cD var0) {
      return var0.width;
   }

   private static MovementInput KsT8IY1bJJ(0cD var0) {
      return var0.movementInput;
   }

   private static InventoryPlayer pN4AoLF22g(0cD var0) {
      return var0.inventory;
   }

   private static float r92WugQchO(0cD var0) {
      return var0.rotationYaw;
   }

   private static void EuxtSitbVf(0cD var0, int var1) {
      var0.sprintToggleTimer = var1;
   }

   private static void TZtaLPiL7N(0cD var0, double var1) {
      var0.motionZ = var1;
   }
}
