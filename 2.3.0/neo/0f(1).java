package neo;

import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerBeacon;
import net.minecraft.inventory.ContainerBrewingStand;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.ContainerDispenser;
import net.minecraft.inventory.ContainerEnchantment;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.ContainerHopper;
import net.minecraft.inventory.ContainerRepair;
import net.minecraft.inventory.ContainerShulkerBox;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class 0f extends ME {
   public final 0a bot;
   public 0h movementInput;
   public float renderArmYaw;
   public boolean hasValidHealth;
   public int positionUpdateTicks;
   public int autoJumpTime;
   public float renderArmPitch;
   public double lastReportedPosX;
   public float horseJumpPower;
   public EnumHand activeHand;
   public double lastReportedPosZ;
   public float prevTimeInPortal;
   public float timeInPortal;
   public String serverBrand;
   public float lastReportedYaw;
   public int sprintToggleTimer;
   public int permissionLevel;
   public boolean prevOnGround;
   public float lastReportedPitch;
   public boolean serverSneakState;
   public final 0bl connection;
   public int sprintingTicksLeft;
   public boolean rowingBoat;
   public float prevRenderArmPitch;
   public boolean handActive;
   public double lastReportedPosY;
   public boolean serverSprintState;
   public int horseJumpPowerCounter;
   public String currentContainerName;
   private static String _DSC GG NEOWARECLIENT _;

   private static void WTo1JseaaS(0f var0, float var1) {
      var0.prevTimeInPortal = var1;
   }

   private static 0h LSyw3bvT4i(0f var0) {
      return var0.movementInput;
   }

   private static 0a YqGO9pSwUC(0f var0) {
      return var0.bot;
   }

   private static boolean AW9QhLri74(0f var0) {
      return var0.handActive;
   }

   private static float ATAMWTVR9L(0f var0) {
      return var0.rotationPitch;
   }

   private static 0h _tDuVmUoQP/* $FF was: 1tDuVmUoQP*/(0f var0) {
      return var0.movementInput;
   }

   private static void knnBOFajVt(0f var0, boolean var1) {
      var0.rowingBoat = var1;
   }

   private static float oA9aNr8nx7(0f var0) {
      return var0.timeInPortal;
   }

   private static DamageSource KF2mjY9Q6l() {
      return DamageSource.GENERIC;
   }

   private static 0bl aWZGkylSTs(0f var0) {
      return var0.connection;
   }

   private static 0a SoA1eIYOxN(0f var0) {
      return var0.bot;
   }

   private static void sgw4BFSjlO(0f var0, Container var1) {
      var0.openContainer = var1;
   }

   private static void vuT7QNlwVw(0f var0, int var1) {
      var0.flyToggleTimer = var1;
   }

   private static 0g Sr9atO4VTu(0a var0) {
      return var0.keyboard;
   }

   private static void BNt2mgjt1N(0f var0, int var1) {
      var0.sprintingTicksLeft = var1;
   }

   private static boolean gaTaPfBI8A(0f var0) {
      return var0.prevOnGround;
   }

   private static float TT7mjynsRJ(0f var0) {
      return var0.width;
   }

   private static float nUlenL5La9(0f var0) {
      return var0.moveForward;
   }

   public boolean isUser() {
      return (boolean)(1449 ^ -20107 ^ 6972 ^ -20511);
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String OpOtzWn8fg(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 14449 ^ -9814 ^ 29671 ^ -28100; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 11721 ^ -7802 ^ 1874 ^ -12489));
      }

      return var1.toString();
   }

   private static 0h sAhWDFB6WO(0f var0) {
      return var0.movementInput;
   }

   private static 0a lbWbYySnYL(0f var0) {
      return var0.bot;
   }

   private static double Q2LtGi86nW(0f var0) {
      return var0.posX;
   }

   private static double ave4ovUTGv(0f var0) {
      return var0.posZ;
   }

   public void openEditStructure(YV a) {
   }

   private static boolean feZJ4jRIIw(0f var0) {
      return var0.onGround;
   }

   private static void aTFNrAe2Pp(0f var0, float var1) {
      var0.horseJumpPower = var1;
   }

   private static 0h Fwy7JiXr6Q(0f var0) {
      return var0.movementInput;
   }

   public void displayGUIChest(IInventory a) {
      String b = a instanceof bga ? ((bga)a).getGuiID() : OpOtzWn8fg("чуфящјыьўАщхфўыуфяј");
      if (OpOtzWn8fg("чуфящјыьўАщтяљў").equals(b)) {
         DyNBnZVGSc(this, new ContainerChest(QLs9688r4Z(this), a, this));
      } else if (OpOtzWn8fg("чуфящјыьўАтхњњяј").equals(b)) {
         yfWt7nBWFD(this, new ContainerHopper(VNq6IrJXbL(this), a, this));
      } else if (OpOtzWn8fg("чуфящјыьўАьџјфыщя").equals(b)) {
         bLZgNll0V1(this, new ContainerFurnace(kLUyOzbQLE(this), a));
      } else if (OpOtzWn8fg("чуфящјыьўАшјяѝуфэѵљўыфю").equals(b)) {
         sgw4BFSjlO(this, new ContainerBrewingStand(2LV8BzqTJr(this), a));
      } else if (OpOtzWn8fg("чуфящјыьўАшяыщхф").equals(b)) {
         XBTX23qDyO(this, new ContainerBeacon(po99RIoFIO(this), a));
      } else if (!OpOtzWn8fg("чуфящјыьўАюуљњяфљяј").equals(b) && !OpOtzWn8fg("чуфящјыьўАюјхњњяј").equals(b)) {
         if (OpOtzWn8fg("чуфящјыьўАљтџцсяјѵшхђ").equals(b)) {
            CdaXhFQrL7(this, new ContainerShulkerBox(jJn54anF2p(this), a, this));
         } else {
            F2qiQMT7tO(this, new ContainerChest(bqaLkDJnCi(this), a, this));
         }
      } else {
         FAhL4Um2M7(this, new ContainerDispenser(PwFJTYAZke(this), a));
      }

   }

   private static 0h hOtwFSSgXM(0f var0) {
      return var0.movementInput;
   }

   private static float ILSDWVKDJV(Vec2f var0) {
      return var0.y;
   }

   public void heal(float a) {
   }

   @Nullable
   public VZ removeActivePotionEffect(@Nullable VW a) {
      if (a == 0fI7jVZdrK()) {
         uHFUdfWC7y(this, Float.intBitsToFloat(18695 ^ 243001 ^ 255078 ^ 699247638 ^ 27951 ^ 26482 ^ 5858 ^ 699246833));
         Zrat8QjD2d(this, Float.intBitsToFloat('\udd9d' ^ 20158 ^ '큱' ^ -290053770 ^ 4871 ^ 'ﶜ' ^ '쾨' ^ -290045161));
      }

      return super.removeActivePotionEffect(a);
   }

   private static EnumHand Cjii9MogvA() {
      return EnumHand.MAIN_HAND;
   }

   private static void f1DpSCuOxi(0f var0, float var1) {
      var0.lastReportedPitch = var1;
   }

   private static boolean i1L5Tw1p0d(0f var0) {
      return var0.onGround;
   }

   public void closeScreenAndDropStack() {
      oNoYGYD9gp(this).setItemStack(7eLSB7gFwP());
      super.closeScreen();
   }

   private static double _BQd3eMFYv/* $FF was: 9BQd3eMFYv*/(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static void S0JAyr9mXR(0f var0, float var1) {
      var0.timeInPortal = var1;
   }

   public void move(Lq a, double b, double c, double d) {
      double e = q9tgFJxJre(this);
      double f = 54l1H8GiX9(this);
      super.move(a, b, c, d);
      this.updateAutoJump((float)(NMxbM3L7Xz(this) - e), (float)(pNg2SHh5jo(this) - f));
   }

   private static void oQasfGGNq2(0f var0, int var1) {
      var0.timeUntilPortal = var1;
   }

   private static boolean VsiQtwvkQ9(0h var0) {
      return var0.sneak;
   }

   private static int cvc3ZRp6LB(0f var0) {
      return var0.maxHurtResistantTime;
   }

   private static void LsNUSgD65P(0f var0, int var1) {
      var0.autoJumpTime = var1;
   }

   private static void lgIogLJtL2(ML var0, boolean var1) {
      var0.isFlying = var1;
   }

   private static double mxRkACISpE(0f var0) {
      return var0.posX;
   }

   private static 0bl sNqoqy4STy(0f var0) {
      return var0.connection;
   }

   public boolean isRidingHorse() {
      Ig a = this.getRidingEntity();
      return (boolean)(this.isRiding() && a instanceof IG && ((IG)((IG)a)).canJump() ? 21125 ^ -7827 ^ 13771 ^ -31198 : 26273 ^ -30820 ^ 31278 ^ -25837);
   }

   public boolean isCreative() {
      return (boolean)(EoT5dXjRBV(LO0DlPBkQm(this)) != null && gUSlI6od1e(lbWbYySnYL(this)).getGameType() == yg2tsO7cie() ? 159 ^ -32577 ^ 21214 ^ -11521 : 1853 ^ -28683 ^ 17283 ^ -13493);
   }

   private static ML B7WsIqRi0w(0f var0) {
      return var0.capabilities;
   }

   private static double GI65artELF(0f var0) {
      return var0.posZ;
   }

   private static void DyNBnZVGSc(0f var0, Container var1) {
      var0.openContainer = var1;
   }

   private static 0bl vWQvdSLnO9(0f var0) {
      return var0.connection;
   }

   private static boolean DyvIPY8LiW(0f var0) {
      return var0.onGround;
   }

   public void sendHorseInventory() {
      vWQvdSLnO9(this).sendPacket(new SQ(this, F8Zmr9EJBO()));
   }

   private static float ADgyP6VXY7(0f var0) {
      return var0.rotationYaw;
   }

   private static float ZSGNIiGYf1(0f var0) {
      return var0.timeInPortal;
   }

   private static Container _eOYlDBqTb/* $FF was: 5eOYlDBqTb*/(0f var0) {
      return var0.openContainer;
   }

   private static double gWeFvwjSB1(0f var0) {
      return var0.motionY;
   }

   private static void _J6oLq6so4/* $FF was: 6J6oLq6so4*/(0f var0, boolean var1) {
      var0.hasValidHealth = var1;
   }

   private static MJ jJn54anF2p(0f var0) {
      return var0.inventory;
   }

   private static float Kw6yAt51CI(0f var0) {
      return var0.width;
   }

   private static boolean YOqJ4FrI2e(0f var0) {
      return var0.serverSprintState;
   }

   private static void D1njxxFc3W(0f var0, Container var1) {
      var0.openContainer = var1;
   }

   private static 0h _eJbNoI6eW/* $FF was: 9eJbNoI6eW*/(0f var0) {
      return var0.movementInput;
   }

   private static void yleZbNbJYY(0f var0, float var1) {
      var0.timeInPortal = var1;
   }

   private static float JrrrTDZGHe(0f var0) {
      return var0.rotationPitch;
   }

   private static bij Fs8tSEdSHT(0f var0) {
      return var0.world;
   }

   private static boolean qe9KwJWpXV(0h var0) {
      return var0.jump;
   }

   private static boolean DVnjAJujwt(0f var0) {
      return var0.handActive;
   }

   private static 0h fYNgjrraO1(0f var0) {
      return var0.movementInput;
   }

   private static float _SBBnW44wC/* $FF was: 1SBBnW44wC*/(0f var0) {
      return var0.rotationYaw;
   }

   private static int _EiWaiwioi/* $FF was: 2EiWaiwioi*/(0f var0) {
      return var0.sprintToggleTimer;
   }

   private static boolean LLFnolgrFe(0h var0) {
      return var0.jump;
   }

   private static double b22nZQVDaZ(0f var0) {
      return var0.motionY;
   }

   private static float rLGwcgkwaX(0h var0) {
      return var0.moveForward;
   }

   private static float GF7cs9B2gS(0f var0) {
      return var0.width;
   }

   private static void wpQcCgbBq6(0f var0, double var1) {
      var0.motionX = var1;
   }

   private static int CLloVdcmsr(0f var0) {
      return var0.horseJumpPowerCounter;
   }

   private static 0h CqtBhS0Cql(0f var0) {
      return var0.movementInput;
   }

   private static 0i _uJSu1yyrV/* $FF was: 2uJSu1yyrV*/(0a var0) {
      return var0.controller;
   }

   private static double _30awbDzHN/* $FF was: 930awbDzHN*/(0f var0) {
      return var0.lastReportedPosX;
   }

   private static SP GFy7TLbN72() {
      return SP.START_SPRINTING;
   }

   private static boolean YLkAoEynID(0g var0) {
      return var0.keyBindSprint;
   }

   private static boolean VlGp79Q0xL(0f var0) {
      return var0.onGround;
   }

   private static void BJEc16Wqlg(0f var0, double var1) {
      var0.motionY = var1;
   }

   private static void _Kx6SrYb31/* $FF was: 1Kx6SrYb31*/(0f var0, float var1) {
      var0.prevRenderArmPitch = var1;
   }

   private static double QrOzyqAb8k(0f var0) {
      return var0.posZ;
   }

   private static boolean dJ9GH2rQKU(0f var0) {
      return var0.onGround;
   }

   private static OL rA1d5qtLFj() {
      return NK.ELYTRA;
   }

   public _f/* $FF was: 0f*/(0a a) {
      super(a.world, a.connection.getGameProfile());
      this.bot = a;
      this.permissionLevel = 8433 ^ -20012 ^ 17481 ^ -10900;
      this.currentContainerName = OpOtzWn8fg("");
      this.connection = a.connection;
      this.dimension = 5790 ^ -25995 ^ 7218 ^ -28455;
   }

   private static float JuaavRQdoo(0h var0) {
      return var0.moveForward;
   }

   private static float beWBEoSgrQ(0f var0) {
      return var0.renderArmYaw;
   }

   private static MJ w8YMqOYEfN(0f var0) {
      return var0.inventory;
   }

   private static void gOeEn6UJe0(0f var0, float var1) {
      var0.timeInPortal = var1;
   }

   private static 0h GwN4scqiA5(0f var0) {
      return var0.movementInput;
   }

   public void onEnchantmentCritical(Ig a) {
   }

   private static double dWHTI549oE(0f var0) {
      return var0.posZ;
   }

   private static void BYtBUqsV1X(0f var0, float var1) {
      var0.lastReportedYaw = var1;
   }

   public void addStat(XQ a, int b) {
      if (a != null && 1qVTOXCvSf(a)) {
         super.addStat(a, b);
      }

   }

   private static boolean _BRZyAQ6TG/* $FF was: 1BRZyAQ6TG*/(0f var0) {
      return var0.onGround;
   }

   private static 0a OvS1q2Nbs6(0f var0) {
      return var0.bot;
   }

   private static boolean QaXqFUxVyb(ML var0) {
      return var0.isFlying;
   }

   private static float SIVQp8eB2j(0h var0) {
      return var0.moveForward;
   }

   private static boolean _X7joFgRBK/* $FF was: 5X7joFgRBK*/(0h var0) {
      return var0.jump;
   }

   private static double LRjlLK7u13(Vec3d var0) {
      return var0.z;
   }

   private static double XfBBFwTVEe(0f var0) {
      return var0.posX;
   }

   private static float BIPMvn8J0A(0f var0) {
      return var0.rotationYaw;
   }

   private static void NvOxHODfJi(0f var0, boolean var1) {
      var0.prevOnGround = var1;
   }

   private static float _rNvfRCGVP/* $FF was: 4rNvfRCGVP*/(0f var0) {
      return var0.rotationYaw;
   }

   private static double JwAbCV3N28(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static float txWGZYnNeF(0f var0) {
      return var0.width;
   }

   private static 0h Bp5TDLA1BO(0f var0) {
      return var0.movementInput;
   }

   private static Ta SOtwW4AT2N() {
      return Ta.DROP_ALL_ITEMS;
   }

   private static 0h bheJ9uFz4o(0f var0) {
      return var0.movementInput;
   }

   @Nullable
   public IY dropItem(boolean a) {
      Ta b = a ? SOtwW4AT2N() : 1UqateoVYR();
      PlqXGd6w07(this).sendPacket(new Tb(b, 98JrSM7eRK(), ObesVoFqYn()));
      return null;
   }

   private static boolean Gaq8xktKmq(0h var0) {
      return var0.backKeyDown;
   }

   private static void k9QrhdtfhB(0f var0, int var1) {
      var0.horseJumpPowerCounter = var1;
   }

   private static double _7Zgk91tig/* $FF was: 47Zgk91tig*/(0f var0) {
      return var0.posX;
   }

   private static double evNdvKbJPF(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static 0bl ISXccgEa3w(0f var0) {
      return var0.connection;
   }

   private static bij Lwp9nttAov(0f var0) {
      return var0.world;
   }

   private static ML W7NGODF9O2(0f var0) {
      return var0.capabilities;
   }

   private static void CNVpj89v4d(0f var0, float var1) {
      var0.prevRotationYaw = var1;
   }

   private static 0bl IbOoMOq7vA(0f var0) {
      return var0.connection;
   }

   private static MJ NJlt4Bryy1(0f var0) {
      return var0.inventory;
   }

   private static double _nALQmMoPG/* $FF was: 0nALQmMoPG*/(0f var0) {
      return var0.posX;
   }

   private static SP F8Zmr9EJBO() {
      return SP.OPEN_INVENTORY;
   }

   private static float BhFhlb1l4Y(0f var0) {
      return var0.timeInPortal;
   }

   private static double FnowfeJ2X5(0f var0) {
      return var0.lastReportedPosZ;
   }

   public void setServerBrand(String a) {
      LYnwGCr4Py(this, a);
   }

   private static double fmHirD11t4(0f var0) {
      return var0.motionZ;
   }

   private static 0h tBPUP1y4QL(0f var0) {
      return var0.movementInput;
   }

   private static bij bGARh8E9bd(0f var0) {
      return var0.world;
   }

   private static boolean OOnbXADwTb(0f var0) {
      return var0.onGround;
   }

   private static 0bl qBeIoIIHBi(0f var0) {
      return var0.connection;
   }

   public void dismountRidingEntity() {
      super.dismountRidingEntity();
      a8ESNYk10o(this, (boolean)(29155 ^ -15209 ^ 10060 ^ -28104));
   }

   private static SP _xB1y4DfP7/* $FF was: 5xB1y4DfP7*/() {
      return SP.START_RIDING_JUMP;
   }

   private static boolean D7AvRkQDA1(0h var0) {
      return var0.leftKeyDown;
   }

   private static double TrOH1ZElr1(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static void jAeNJWGDTw(0f var0, int var1) {
      var0.horseJumpPowerCounter = var1;
   }

   private static boolean THenqVpSni(0h var0) {
      return var0.jump;
   }

   private static boolean KJ1LNjw2rB(0h var0) {
      return var0.leftKeyDown;
   }

   private static ML ai6eAYtbh8(0f var0) {
      return var0.capabilities;
   }

   private static 0h Zv0LLCoeyv(0f var0) {
      return var0.movementInput;
   }

   private static double YOwnSYJQ6E(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static 0i gUSlI6od1e(0a var0) {
      return var0.controller;
   }

   private static 0h byon7iWck8(0f var0) {
      return var0.movementInput;
   }

   private static float adhJZDGSXL(0h var0) {
      return var0.moveStrafe;
   }

   private static 0a GVDR1OyPog(0f var0) {
      return var0.bot;
   }

   private static 0h nJOOnobSut(0f var0) {
      return var0.movementInput;
   }

   private static float kozeMbRWai(0f var0) {
      return var0.width;
   }

   private static 0bl Rew8N5XJsJ(0f var0) {
      return var0.connection;
   }

   private static int hvT7kW6xoi(0f var0) {
      return var0.horseJumpPowerCounter;
   }

   private static int am2J90g6rl(Container var0) {
      return var0.windowId;
   }

   private static 0bl Ar4aetjrSj(0f var0) {
      return var0.connection;
   }

   private static 0cp e69754QDtA() {
      return 0bG.field_a;
   }

   private static float IHS14JQ1ze(0f var0) {
      return var0.width;
   }

   private static void _vldhlD6Vb/* $FF was: 9vldhlD6Vb*/(0f var0, int var1) {
      var0.sprintToggleTimer = var1;
   }

   private static int PapQr0e4Pa(0f var0) {
      return var0.horseJumpPowerCounter;
   }

   private static 0bl d4zjXnNzaC(0f var0) {
      return var0.connection;
   }

   private static int _bZ6YurTYt/* $FF was: 1bZ6YurTYt*/(0f var0) {
      return var0.horseJumpPowerCounter;
   }

   public void displayGuiCommandBlock(Yq a) {
   }

   private static void pT9oarYnTo(0f var0, EnumHand var1) {
      var0.activeHand = var1;
   }

   public void displayVillagerTradeGui(IH a) {
   }

   private static ML db1fDdbqOo(0f var0) {
      return var0.capabilities;
   }

   private static boolean JBNtFBjO6k(0h var0) {
      return var0.backKeyDown;
   }

   private static double _7IXx0lbbi/* $FF was: 67IXx0lbbi*/(Vec3d var0) {
      return var0.x;
   }

   private static float dgB3OVOw7t(0f var0) {
      return var0.timeInPortal;
   }

   private static void CdaXhFQrL7(0f var0, Container var1) {
      var0.openContainer = var1;
   }

   private static float fK4qg5APnJ(0h var0) {
      return var0.moveForward;
   }

   private static void W4L1GJr2DT(0f var0, double var1) {
      var0.lastReportedPosY = var1;
   }

   private static bij hGof9kH4eG(0f var0) {
      return var0.world;
   }

   public boolean startRiding(Ig a, boolean b) {
      if (!super.startRiding(a, b)) {
         return (boolean)(24256 ^ -22310 ^ 32028 ^ -29946);
      } else {
         if (a instanceof IR) {
            CNVpj89v4d(this, IJVwMDzkud(a));
            gqWF97LVyt(this, aTntJobfjG(a));
            this.setRotationYawHead(y5JT69Qpy7(a));
         }

         return (boolean)(6480 ^ -22192 ^ 28347 ^ -8518);
      }
   }

   private static boolean _kSHhrgFe9/* $FF was: 4kSHhrgFe9*/(0f var0) {
      return var0.hasValidHealth;
   }

   public boolean isServerWorld() {
      return (boolean)(16926 ^ -7750 ^ 3470 ^ -20949);
   }

   private static MJ oNoYGYD9gp(0f var0) {
      return var0.inventory;
   }

   private static float ZgC2Bpxz4d(0f var0) {
      return var0.timeInPortal;
   }

   private static Qy _m6O1S4IAS/* $FF was: 5m6O1S4IAS*/() {
      return Qy.EMPTY;
   }

   private static void wsZoQIVLol(0f var0, int var1) {
      var0.hurtTime = var1;
   }

   private static double EXjp9q3NoB(0f var0) {
      return var0.posY;
   }

   private static void tAT6baJj2i(0f var0, boolean var1) {
      var0.serverSprintState = var1;
   }

   private static int N2GEJw4EFy(0f var0) {
      return var0.horseJumpPowerCounter;
   }

   private static int V4FVLqB2Yj(0f var0) {
      return var0.autoJumpTime;
   }

   private static void n2bFLQqMv3(0f var0, int var1) {
      var0.permissionLevel = var1;
   }

   private static void DD2hV6iM1u(0f var0, float var1) {
      var0.timeInPortal = var1;
   }

   private static float ITLA2Y6JhZ(0f var0) {
      return var0.lastReportedPitch;
   }

   protected void updateAutoJump(float R, float S) {
      if (this.isAutoJumpEnabled() && ILSgbRbRe2(this) <= 0 && i1L5Tw1p0d(this) && !this.isSneaking() && !this.isRiding()) {
         Vec2f Q = byon7iWck8(this).getMoveVector();
         if (RSoewWBMKT(Q) != Float.intBitsToFloat(12952 ^ 2057275 ^ 2077041 ^ -1645250409 ^ 21540 ^ '鹛' ^ 30975 ^ -1645269563) || ILSDWVKDJV(Q) != Float.intBitsToFloat(108653 ^ 85413 ^ 1488 ^ -524662219 ^ 27355 ^ '驙' ^ 5302 ^ -524661223)) {
            Vec3d F = new Vec3d(mxRkACISpE(this), BGNDnrJ7WX(this.getEntityBoundingBox()), q379YrvJW7(this));
            double G = oHv1BLi4OQ(this) + (double)R;
            double H = ave4ovUTGv(this) + (double)S;
            Vec3d I = new Vec3d(G, VP4Aol7AZr(this.getEntityBoundingBox()), H);
            Vec3d J = new Vec3d((double)R, Double.longBitsToDouble(3502582487062486180L ^ 3502582487062486180L), (double)S);
            float K = this.getAIMoveSpeed();
            float L = (float)J.lengthSquared();
            float M;
            float P;
            if (L <= Float.intBitsToFloat(22448 ^ 85830 ^ 128037 ^ 2031116137 ^ 491767 ^ 17490 ^ 521810 ^ 1133744930)) {
               M = K * s6eN6XftK6(Q);
               float b = K * Qw7I4ldtNY(Q);
               float c = MathHelper.sin(DklFeJeb2N(this) * Float.intBitsToFloat(126664 ^ 98331 ^ 26985 ^ 1560461527 ^ 20273 ^ 26902 ^ 13003 ^ 1636575668));
               P = MathHelper.cos(4rNvfRCGVP(this) * Float.intBitsToFloat(241749 ^ 223072 ^ 16328 ^ -1318498014 ^ 13016 ^ '븂' ^ '\ue9e2' ^ -1914231086));
               J = new Vec3d((double)(M * P - b * c), 3FySB1ttSL(J), (double)(b * P + M * c));
               L = (float)J.lengthSquared();
               if (L <= Float.intBitsToFloat('\uf196' ^ '\udbaa' ^ 24863 ^ -313060480 ^ 12742 ^ 484629 ^ 'קּ' ^ -673979048)) {
                  return;
               }
            }

            M = (float)MathHelper.fastInvSqrt((double)L);
            Vec3d N = J.scale((double)M);
            Vec3d O = this.getForward();
            P = (float)(VhZ94aJgvI(O) * 67IXx0lbbi(N) + B1iY5S7qdb(O) * LRjlLK7u13(N));
            if (P >= Float.intBitsToFloat(2095722 ^ 2083153 ^ 11922 ^ 1877984071 ^ 13757 ^ 211507 ^ '뚥' ^ -772407713)) {
               BlockPos D = new BlockPos(Q2LtGi86nW(this), 12yq17vQze(this.getEntityBoundingBox()), 2pUrn4qH6k(this));
               in E = wQtj4onYrR(this).getBlockState(D);
               if (E.getCollisionBoundingBox(4QboNhXBBn(this), D) == null) {
                  D = D.up();
                  in C = qJ5NYl8TSg(this).getBlockState(D);
                  if (C.getCollisionBoundingBox(WIGid69Sh9(this), D) == null) {
                     float n = Float.intBitsToFloat(31253 ^ '螘' ^ '솴' ^ 1938555124 ^ 4085 ^ 219499 ^ 238848 ^ 1276466889);
                     if (this.isPotionActive(dJbMSlMNi1())) {
                        n += (float)(this.getActivePotionEffect(Y0QcGAXa01()).getAmplifier() + (6616 ^ -24090 ^ 13667 ^ -29348)) * Float.intBitsToFloat(22336 ^ 491114 ^ 515541 ^ 1609784171 ^ 500064 ^ 483630 ^ 3924 ^ 1622368398);
                     }

                     float o = Math.max(K * Float.intBitsToFloat('\udc2e' ^ 'ꄋ' ^ 4518 ^ 146021224 ^ '\ue462' ^ '\uf8e8' ^ 25839 ^ 1213468558), Float.intBitsToFloat(21616 ^ '\ue511' ^ 22024 ^ -379291758 ^ 28954 ^ '픚' ^ 333 ^ -689687114) / M);
                     Vec3d p = I.add(N.scale((double)o));
                     float q = Kw6yAt51CI(this);
                     float r = UOJ6v47Lb3(this);
                     AxisAlignedBB s = (new AxisAlignedBB(F, p.add(Double.longBitsToDouble(-520019572064238677L ^ -520019572064238677L), (double)r, Double.longBitsToDouble(3027796744791019827L ^ 3027796744791019827L)))).grow((double)q, Double.longBitsToDouble(-8879709858543452704L ^ -8879709858543452704L), (double)q);
                     Vec3d t = F.add(Double.longBitsToDouble(-9011656821562559115L ^ -9011656821562559115L), Double.longBitsToDouble(-4223890276183590817L ^ -395776048981493665L), Double.longBitsToDouble(-982523402101181296L ^ -982523402101181296L));
                     p = p.add(Double.longBitsToDouble(1952320183513484629L ^ 1952320183513484629L), Double.longBitsToDouble(-2406107039686129682L ^ -2198992955632449554L), Double.longBitsToDouble(5385536287560003250L ^ 5385536287560003250L));
                     Vec3d u = N.crossProduct(new Vec3d(Double.longBitsToDouble(644331001657135865L ^ 644331001657135865L), Double.longBitsToDouble(-3183221806799997393L ^ -1431321551752874449L), Double.longBitsToDouble(-4863032436759044934L ^ -4863032436759044934L)));
                     Vec3d v = u.scale((double)(q * Float.intBitsToFloat('齔' ^ 100596 ^ '\ue451' ^ 391826570 ^ '鮌' ^ 30530 ^ '촭' ^ 677060248)));
                     Vec3d w = t.subtract(v);
                     Vec3d x = p.subtract(v);
                     Vec3d y = t.add(v);
                     Vec3d z = p.add(v);
                     List<AxisAlignedBB> A = 3UlHlqBvty(this).getCollisionBoxes(this, s);
                     float B = Float.intBitsToFloat(23894 ^ 85618 ^ 117849 ^ -1042780434 ^ 21528 ^ 30539 ^ 6106 ^ -1042774757);
                     Iterator var35 = A.iterator();

                     label83: {
                        AxisAlignedBB l;
                        do {
                           if (!var35.hasNext()) {
                              break label83;
                           }

                           l = (AxisAlignedBB)var35.next();
                        } while(!l.intersects(w, x) && !l.intersects(y, z));

                        B = (float)JwAbCV3N28(l);
                        Vec3d i = l.getCenter();
                        BlockPos j = new BlockPos(i);

                        for(int k = 26459 ^ -25392 ^ 16657 ^ -17765; !((float)k >= n); ++k) {
                           BlockPos f = j.up(k);
                           in g = hGof9kH4eG(this).getBlockState(f);
                           AxisAlignedBB h;
                           if ((h = g.getCollisionBoundingBox(HtQxnSNoL7(this), f)) != null) {
                              B = (float)PkhL5z9R7S(h) + (float)f.getY();
                              if ((double)B - 9BQd3eMFYv(this.getEntityBoundingBox()) > (double)n) {
                                 return;
                              }
                           }

                           if (k > (24121 ^ -7510 ^ 3072 ^ -20334)) {
                              D = D.up();
                              in e = Lwp9nttAov(this).getBlockState(D);
                              if (e.getCollisionBoundingBox(bGARh8E9bd(this), D) != null) {
                                 return;
                              }
                           }
                        }
                     }

                     if (B != Float.intBitsToFloat(4061 ^ '\ud8f9' ^ 'ꬋ' ^ 2170402 ^ 3100 ^ '\ueb11' ^ '냈' ^ 2176457)) {
                        float m = (float)((double)B - wxwLDbg7aF(this.getEntityBoundingBox()));
                        if (m > Float.intBitsToFloat('퐕' ^ '쮼' ^ 8377 ^ -2048048941 ^ '蕔' ^ 'ꠈ' ^ 24747 ^ -1158860236) && m <= n) {
                           oaqD603vi3(this, 24602 ^ -8223 ^ 28202 ^ -11824);
                        }
                     }
                  }
               }
            }
         }
      }

   }

   private static 0bl GD4NiwAs9a(0f var0) {
      return var0.connection;
   }

   private static 0h uMgJj1PVVD(0f var0) {
      return var0.movementInput;
   }

   private static bbb _OrWo7XK36/* $FF was: 8OrWo7XK36*/() {
      return bbb.SPECTATOR;
   }

   private static int Q6yiteay4w(0f var0) {
      return var0.sprintingTicksLeft;
   }

   private static boolean GVVJyyWz2F(ML var0) {
      return var0.isFlying;
   }

   private static boolean _w9fq6nOMj/* $FF was: 0w9fq6nOMj*/(0h var0) {
      return var0.sneak;
   }

   private static float Eil2LyZMmg(0f var0) {
      return var0.rotationYaw;
   }

   private static void hlA7nli1H1(0f var0, int var1) {
      var0.positionUpdateTicks = var1;
   }

   private static float RSoewWBMKT(Vec2f var0) {
      return var0.x;
   }

   private static float t6DsHlWHJm(0f var0) {
      return var0.rotationPitch;
   }

   private static float vtyYSMllqI(0f var0) {
      return var0.rotationYaw;
   }

   private static 0h _q2u61FQyS/* $FF was: 4q2u61FQyS*/(0f var0) {
      return var0.movementInput;
   }

   private static float W3hc49dHCT(0f var0) {
      return var0.timeInPortal;
   }

   public void sendChatMessage(String a) {
      A0i3tbb64Y(this).sendPacket(new SE(a));
   }

   private static boolean hMoiDdlwAl(0h var0) {
      return var0.jump;
   }

   private static boolean e9SORKxrnJ(0h var0) {
      return var0.forwardKeyDown;
   }

   private static boolean bqcLahNQO4(0h var0) {
      return var0.sneak;
   }

   private static 0bl _Y2AY4Grru/* $FF was: 4Y2AY4Grru*/(0f var0) {
      return var0.connection;
   }

   private static boolean mqLgHg4bF3(0f var0) {
      return var0.onGround;
   }

   private static void _dNbW3W0zf/* $FF was: 4dNbW3W0zf*/(0f var0, float var1) {
      var0.renderArmYaw = var1;
   }

   private static boolean Ao0rVuFQIe(0f var0) {
      return var0.onGround;
   }

   private static boolean L3FqQF2wrU(0f var0) {
      return var0.onGround;
   }

   private static double oHv1BLi4OQ(0f var0) {
      return var0.posX;
   }

   protected void damageEntity(DamageSource a, float b) {
      if (!this.isEntityInvulnerable(a)) {
         this.setHealth(this.getHealth() - b);
      }

   }

   private static double hcibtTbiCj(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static 0h hrOb8ejyel(0f var0) {
      return var0.movementInput;
   }

   private static double hIQ1THCi7r(0f var0) {
      return var0.posX;
   }

   private static void xjTfw2Lh0A(0f var0, int var1) {
      var0.sprintToggleTimer = var1;
   }

   private static SH vcJ0CRaWfk() {
      return SH.PERFORM_RESPAWN;
   }

   private static EnumHand A3VH123tOy() {
      return EnumHand.OFF_HAND;
   }

   private static double _pUrn4qH6k/* $FF was: 2pUrn4qH6k*/(0f var0) {
      return var0.posZ;
   }

   private static void _bzbndqA9J/* $FF was: 9bzbndqA9J*/(0h var0, float var1) {
      var0.moveStrafe = var1;
   }

   private static void AJi0fj6C85(0f var0, int var1) {
      var0.flyToggleTimer = var1;
   }

   private static bij wQtj4onYrR(0f var0) {
      return var0.world;
   }

   private static Rd bTGIr3MUe9() {
      return HAND_STATES;
   }

   private static double tBQW01qes5(0f var0) {
      return var0.posZ;
   }

   private static double iteR2B2jbS(0f var0) {
      return var0.posX;
   }

   private static float o2MiHO4VhV(0f var0) {
      return var0.timeInPortal;
   }

   private static float Qw7I4ldtNY(Vec2f var0) {
      return var0.y;
   }

   private static 0h TdWWqUGtJI(0f var0) {
      return var0.movementInput;
   }

   private static 0a LO0DlPBkQm(0f var0) {
      return var0.bot;
   }

   public void setXPStats(float a, int b, int c) {
      tcabDavBdJ(this, a);
      UGBIaqlg1S(this, b);
      eedOnyTTkQ(this, c);
   }

   private static boolean aloDO97ayk(0f var0) {
      return var0.sleeping;
   }

   private static float _gv3WvbMed/* $FF was: 3gv3WvbMed*/(0f var0) {
      return var0.width;
   }

   private boolean isOpenBlockSpace(BlockPos a) {
      return (boolean)(!WEuhyJGSaQ(this).getBlockState(a).isNormalCube() && !LnqoIvQY20(this).getBlockState(a.up()).isNormalCube() ? 10376 ^ -16045 ^ 18621 ^ -24217 : 15200 ^ -10935 ^ 31065 ^ -26768);
   }

   private static boolean ylJjmqqR87(0f var0) {
      return var0.handActive;
   }

   private static void oaqD603vi3(0f var0, int var1) {
      var0.autoJumpTime = var1;
   }

   private static double pNg2SHh5jo(0f var0) {
      return var0.posZ;
   }

   private static Rv B6bcxN0kGX(0f var0) {
      return var0.dataManager;
   }

   private static double ex0avMn3h6(0f var0) {
      return var0.motionY;
   }

   private static double BGNDnrJ7WX(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static 0h uyyQlM5S7t(0f var0) {
      return var0.movementInput;
   }

   public void resetActiveHand() {
      super.resetActiveHand();
      fRFo7o06qv(this, (boolean)(14423 ^ -14150 ^ 28794 ^ -32617));
   }

   public void swingArm(EnumHand a) {
      super.swingArm(a);
      oS31a7lJkR(this).sendPacket(new SD(a));
   }

   private static float BVidjGkFGB(0f var0) {
      return var0.renderArmPitch;
   }

   private static double dJE8SyyftR(0f var0) {
      return var0.posZ;
   }

   private static 0bl PlqXGd6w07(0f var0) {
      return var0.connection;
   }

   private static void bLZgNll0V1(0f var0, Container var1) {
      var0.openContainer = var1;
   }

   private static float _o4BTw0W7F/* $FF was: 4o4BTw0W7F*/(0h var0) {
      return var0.moveStrafe;
   }

   private static double N6XLCIjn39(0f var0) {
      return var0.lastReportedPosY;
   }

   private static bij _QboNhXBBn/* $FF was: 4QboNhXBBn*/(0f var0) {
      return var0.world;
   }

   private static ML _VSWGMagWt/* $FF was: 2VSWGMagWt*/(0f var0) {
      return var0.capabilities;
   }

   private static 0h OFUJLqFUw7(0f var0) {
      return var0.movementInput;
   }

   private static Rd bjJUzmCRA4() {
      return HAND_STATES;
   }

   private static boolean wNYF39Uo3A(0h var0) {
      return var0.jump;
   }

   private static void ewFsnDFmh9(0f var0, boolean var1) {
      var0.isJumping = var1;
   }

   private static double p7y11BZIw4(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static float DqVnzjAzqE(0f var0) {
      return var0.width;
   }

   private static void _4PgHrjDmw/* $FF was: 54PgHrjDmw*/(0f var0, int var1) {
      var0.horseJumpPowerCounter = var1;
   }

   private static MJ bqaLkDJnCi(0f var0) {
      return var0.inventory;
   }

   private static double _FySB1ttSL/* $FF was: 3FySB1ttSL*/(Vec3d var0) {
      return var0.y;
   }

   private static float AxQLMKNOYl(0f var0) {
      return var0.renderArmPitch;
   }

   private static 0h N6UVwAXD4d(0f var0) {
      return var0.movementInput;
   }

   private static ML _tmTySjcXm/* $FF was: 7tmTySjcXm*/(0f var0) {
      return var0.capabilities;
   }

   private static void xrmSady8fb(0f var0, int var1) {
      var0.hurtResistantTime = var1;
   }

   private static int J8WVyj1tlN(0f var0) {
      return var0.permissionLevel;
   }

   private static float T9vad7wOAb(0f var0) {
      return var0.rotationYaw;
   }

   private static void NMekFFuoZp(0f var0, float var1) {
      var0.moveStrafing = var1;
   }

   public void notifyDataManagerChange(Rd<?> c) {
      super.notifyDataManagerChange(c);
      if (bjJUzmCRA4().equals(c)) {
         boolean a = ((Byte)B6bcxN0kGX(this).get(Y729BrBD6B()) & (715 ^ -15650 ^ 22458 ^ -26706)) > 0 ? 29179 ^ -31924 ^ 3619 ^ -875 : 6091 ^ -19018 ^ 17786 ^ -6393;
         EnumHand b = ((Byte)gVmNchiJq8(this).get(bTGIr3MUe9()) & (6913 ^ -4892 ^ 30814 ^ -28743)) > 0 ? A3VH123tOy() : Cjii9MogvA();
         if (a != 0 && !DVnjAJujwt(this)) {
            this.setActiveHand(b);
         } else if (a == 0 && AW9QhLri74(this)) {
            this.resetActiveHand();
         }
      }

   }

   private static double D5gI7ylQOu(0f var0) {
      return var0.posX;
   }

   private static void jV3jjqmwTa(0f var0, float var1) {
      var0.moveForward = var1;
   }

   private static double _rgGg79DpQ/* $FF was: 4rgGg79DpQ*/(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static SP _sGqVAiJeB/* $FF was: 1sGqVAiJeB*/() {
      return SP.START_SNEAKING;
   }

   public boolean isAutoJumpEnabled() {
      return e69754QDtA().method_bna();
   }

   private static boolean eiBSVQjqN6(0f var0) {
      return var0.onGround;
   }

   private static 0bl v6LuuyDUiF(0f var0) {
      return var0.connection;
   }

   private static void LBfNgyRoxW(0f var0, boolean var1) {
      var0.handActive = var1;
   }

   private static bij LnqoIvQY20(0f var0) {
      return var0.world;
   }

   private static void Dr2y5TY8EF(0f var0, float var1) {
      var0.renderArmPitch = var1;
   }

   public void onLivingUpdate() {
      VACbh1AYng(this, Q6yiteay4w(this) + (21552 ^ -12997 ^ 21015 ^ -13539));
      if (qFM27eyKOO(this) > 0) {
         qWbMnGTzAt(this, 9fszZTAM47(this) - (14055 ^ -29682 ^ 16559 ^ -1465));
      }

      WTo1JseaaS(this, 9LLQZda7hU(this));
      if (1QM8jm4ogf(this)) {
         yleZbNbJYY(this, BhFhlb1l4Y(this) + Float.intBitsToFloat(1030486 ^ 1030862 ^ 19454 ^ 1898423508 ^ 24797 ^ 102518 ^ 6467 ^ 1298844055));
         if (W3hc49dHCT(this) >= Float.intBitsToFloat(5999 ^ '膰' ^ 4434 ^ 841493632 ^ 17806 ^ 103747 ^ 124727 ^ 229154039)) {
            19anY4Q2Oa(this, Float.intBitsToFloat(236568 ^ 250674 ^ 32027 ^ -2070819343 ^ 232926 ^ 239196 ^ 25148 ^ -1156467074));
         }

         HKna7byC22(this, (boolean)(19386 ^ -29734 ^ 13466 ^ -2822));
      } else if (this.isPotionActive(ZeCN4aokAW()) && this.getActivePotionEffect(wWvuCA6vbF()).getDuration() > (5850 ^ -7363 ^ 1046 ^ -3635)) {
         DD2hV6iM1u(this, dgB3OVOw7t(this) + Float.intBitsToFloat(12453 ^ '좜' ^ '릤' ^ 940710828 ^ 18512 ^ 27704 ^ 11114 ^ 63447357));
         if (ZSGNIiGYf1(this) > Float.intBitsToFloat(16069 ^ '꼦' ^ 9541 ^ 755576996 ^ 21593 ^ '샪' ^ 19052 ^ 310991581)) {
            gOeEn6UJe0(this, Float.intBitsToFloat('\uea03' ^ '軅' ^ 10197 ^ -1630723284 ^ '낣' ^ '钾' ^ 23994 ^ -1588782696));
         }
      } else {
         if (oA9aNr8nx7(this) > Float.intBitsToFloat(30782 ^ 85002 ^ '\udf4e' ^ -1939423190 ^ 20192 ^ 91697 ^ '콏' ^ -1939419954)) {
            S0JAyr9mXR(this, ZgC2Bpxz4d(this) - Float.intBitsToFloat(103009 ^ 98060 ^ 18948 ^ 947025658 ^ 8436 ^ 257801 ^ 29908 ^ 87926391));
         }

         if (o2MiHO4VhV(this) < Float.intBitsToFloat(8350 ^ 98474 ^ 120690 ^ -396314732 ^ 4198 ^ 25464 ^ 10164 ^ -396323720)) {
            85He9KQW7T(this, Float.intBitsToFloat(5145 ^ 4155605 ^ 4190711 ^ 1008691810 ^ 4190000 ^ 4155211 ^ 7015 ^ 1008694341));
         }
      }

      if (QrGgjwgWzA(this) > 0) {
         oQasfGGNq2(this, fUTnycnLQA(this) - (27804 ^ -28193 ^ 27115 ^ -27479));
      }

      boolean c = qe9KwJWpXV(vwRWbu5WjQ(this));
      boolean d = VsiQtwvkQ9(GwN4scqiA5(this));
      boolean e = SIVQp8eB2j(qjPdeHwOdh(this)) >= Float.intBitsToFloat(108417 ^ '豷' ^ 128571 ^ 169507952 ^ '\uf753' ^ '큡' ^ 19616 ^ 894830306) ? 19693 ^ -32665 ^ 5331 ^ -10152 : 7474 ^ -2997 ^ 18820 ^ -24323;
      1Nbvymobt1(this).updatePlayerMoveState();
      if (this.isHandActive() && !this.isRiding()) {
         0h var10000 = fYNgjrraO1(this);
         9bzbndqA9J(var10000, ka4TFoWlio(var10000) * Float.intBitsToFloat('\udb44' ^ 222625 ^ 13784 ^ -191497346 ^ 107353 ^ 84547 ^ 3789 ^ -891659431));
         var10000 = TdWWqUGtJI(this);
         oHNgmkKjVW(var10000, wTE3GqGN5x(var10000) * Float.intBitsToFloat('ꄝ' ^ 20709 ^ '떊' ^ 2104043947 ^ 29895 ^ 237360 ^ 249447 ^ 1126532228));
         9vldhlD6Vb(this, 9546 ^ -27984 ^ 32334 ^ -13900);
      }

      boolean f = 18650 ^ -12797 ^ 4727 ^ -27474;
      if (grfcH9OGB3(this) > 0) {
         LsNUSgD65P(this, V4FVLqB2Yj(this) - (27052 ^ -5276 ^ 12021 ^ -21444));
         f = 11983 ^ -32574 ^ 7900 ^ -20272;
         vpv7jmu6Ay(1tDuVmUoQP(this), (boolean)(24260 ^ -23288 ^ 1310 ^ -301));
      }

      AxisAlignedBB g = this.getEntityBoundingBox();
      this.pushOutOfBlocks(XfBBFwTVEe(this) - (double)IHS14JQ1ze(this) * Double.longBitsToDouble(-8966112132679206260L ^ -4879756971471561494L), YM67yg72HY(g) + Double.longBitsToDouble(-8032520705185508320L ^ -5807742489264483296L), GI65artELF(this) + (double)DqVnzjAzqE(this) * Double.longBitsToDouble(3958227501532643768L ^ 664303581921241054L));
      this.pushOutOfBlocks(D5gI7ylQOu(this) - (double)txWGZYnNeF(this) * Double.longBitsToDouble(-7309459761602125059L ^ -6531922102434937701L), TrOH1ZElr1(g) + Double.longBitsToDouble(7255408785820035575L ^ 6579868841714461175L), asbIyeFbGN(this) - (double)3gv3WvbMed(this) * Double.longBitsToDouble(3295644233274989989L ^ 1326904507024407491L));
      this.pushOutOfBlocks(hIQ1THCi7r(this) + (double)GF7cs9B2gS(this) * Double.longBitsToDouble(-8942707470166059079L ^ -4885428637456436769L), evNdvKbJPF(g) + Double.longBitsToDouble(-9207029725080066548L ^ -4622365304416901620L), rivRwxLDKV(this) - (double)TT7mjynsRJ(this) * Double.longBitsToDouble(-2536265874789331054L ^ -2082006658915309068L));
      this.pushOutOfBlocks(iteR2B2jbS(this) + (double)kozeMbRWai(this) * Double.longBitsToDouble(6202040058992978570L ^ 7621345062909283564L), hcibtTbiCj(g) + Double.longBitsToDouble(-3754627448614512581L ^ -863316487842654149L), jQD52ZvDIi(this) + (double)g7rDUJeV7s(this) * Double.longBitsToDouble(5972379419505426325L ^ 7868986011754109427L));
      boolean h = !((float)this.getFoodStats().getFoodLevel() > Float.intBitsToFloat(30914 ^ 100379 ^ 13961 ^ 24429515 ^ 14429 ^ 126148 ^ 23265 ^ 1102351331)) && !g8v6uTOFrV(3KfQLlRlA4(this)) ? 22575 ^ -14084 ^ 26207 ^ -2420 : 9436 ^ -16800 ^ 11017 ^ -20044;
      if (qNKlo2aYe2(this) && !d && e == 0 && fK4qg5APnJ(v0H4wJqoZj(this)) >= Float.intBitsToFloat('︻' ^ '꼭' ^ 31196 ^ -1645785710 ^ 18150 ^ 26332 ^ 10377 ^ -1565810394) && !this.isSprinting() && h != 0 && !this.isHandActive() && !this.isPotionActive(NNOebvYvAb())) {
         if (2EiWaiwioi(this) <= 0 && !YLkAoEynID(FoVY1tNgXT(BsKlZngq1q(this)))) {
            xjTfw2Lh0A(this, 1902 ^ -31783 ^ 24942 ^ -6690);
         } else {
            this.setSprinting((boolean)(28306 ^ -14009 ^ 1740 ^ -24296));
         }
      }

      if (!this.isSprinting() && JuaavRQdoo(jBg0FergqJ(this)) >= Float.intBitsToFloat(22856 ^ 260618 ^ 16592 ^ 450478737 ^ 5188 ^ 20298 ^ 2170 ^ 630636218) && h != 0 && !this.isHandActive() && !this.isPotionActive(24r2e01V30()) && AGa9ElOdSB(Sr9atO4VTu(OvS1q2Nbs6(this)))) {
         this.setSprinting((boolean)(19733 ^ -27966 ^ 21175 ^ -29343));
      }

      if (this.isSprinting() && (ww4vzAYwRK(hrOb8ejyel(this)) < Float.intBitsToFloat(240100 ^ 238261 ^ 17413 ^ 422687876 ^ 28254 ^ 101419 ^ 11637 ^ 645724189) || JFiT1hn4lH(this) || h == 0)) {
         this.setSprinting((boolean)(24792 ^ -14060 ^ 24294 ^ -2262));
      }

      if (hf7ZDFdW9M(W7NGODF9O2(this))) {
         if (JZYpBqN1Cm(GVDR1OyPog(this)).isSpectator()) {
            if (!GVVJyyWz2F(AqvxqFWL7n(this))) {
               nUJ2JCF6X6(L5iqidCunD(this), (boolean)(22292 ^ -30079 ^ 26649 ^ -19059));
               this.sendPlayerAbilities();
            }
         } else if (!c && wNYF39Uo3A(iC1u2BawFO(this)) && f == 0) {
            if (z6cTbyaSv9(this) == 0) {
               vuT7QNlwVw(this, 4527 ^ -15377 ^ 1002 ^ -11859);
            } else {
               lgIogLJtL2(B7WsIqRi0w(this), (boolean)(!QaXqFUxVyb(CFtdY4zb27(this)) ? 3534 ^ -28873 ^ 18167 ^ -15345 : 4970 ^ -7481 ^ 24359 ^ -20854));
               this.sendPlayerAbilities();
               AJi0fj6C85(this, 8410 ^ -11248 ^ 32427 ^ -30111);
            }
         }
      }

      if (qFGVF6ONA7(Zv0LLCoeyv(this)) && !c && !OOnbXADwTb(this) && b22nZQVDaZ(this) < Double.longBitsToDouble(6158125417753361455L ^ 6158125417753361455L) && !this.isElytraFlying() && !fFKvKFDl64(7tmTySjcXm(this))) {
         Qy a = this.getItemStackFromSlot(51WTY1obQ6());
         if (a.getItem() == rA1d5qtLFj() && Pt.isUsable(a)) {
            4Y2AY4Grru(this).sendPacket(new SQ(this, 2NqnRNgTGJ()));
         }
      }

      if (USWGaTIA1R(2VSWGMagWt(this))) {
         if (fwdFlmnNlB(OFUJLqFUw7(this))) {
            WtSkL4wI2B(N6UVwAXD4d(this), (float)((double)adhJZDGSXL(LSyw3bvT4i(this)) / Double.longBitsToDouble(-296722212621806760L ^ -4309127309043523477L)));
            ZekizLeS1I(Fwy7JiXr6Q(this), (float)((double)rLGwcgkwaX(DbemgVxwFu(this)) / Double.longBitsToDouble(-4931720691217535476L ^ -8909191974708585153L)));
            r7SSbe7Xjy(this, gWeFvwjSB1(this) - (double)(EiHbd9UcvO(this).getFlySpeed() * Float.intBitsToFloat(1440 ^ '볛' ^ 4205 ^ -1035050100 ^ 5264 ^ 109271 ^ 120745 ^ -2112969868)));
         }

         if (5X7joFgRBK(uMgJj1PVVD(this))) {
            BJEc16Wqlg(this, ex0avMn3h6(this) + (double)(db1fDdbqOo(this).getFlySpeed() * Float.intBitsToFloat(1178 ^ 222089 ^ 236441 ^ 779175461 ^ 27674 ^ 20932 ^ 7019 ^ 1848744986)));
         }
      }

      if (this.isRidingHorse()) {
         IG b = (IG)this.getRidingEntity();
         if (z6jiJrSjI1(this) < 0) {
            k9QrhdtfhB(this, hvT7kW6xoi(this) + (2608 ^ -28036 ^ 13984 ^ -20755));
            if (PapQr0e4Pa(this) == 0) {
               TmeeDVXdyK(this, Float.intBitsToFloat('鯌' ^ 257670 ^ '초' ^ -586832771 ^ '銉' ^ 261414 ^ '\uef34' ^ -586834780));
            }
         }

         if (c && !LLFnolgrFe(Bp5TDLA1BO(this))) {
            54PgHrjDmw(this, -19303 ^ -22774 ^ 21029 ^ -16832);
            b.setJumpPower(MathHelper.floor(this.getHorseJumpPower() * Float.intBitsToFloat(19357 ^ '걿' ^ 7774 ^ -332704688 ^ 29191 ^ 466398 ^ 520813 ^ -1360842664)));
            this.sendHorseJump();
         } else if (!c && THenqVpSni(bheJ9uFz4o(this))) {
            jAeNJWGDTw(this, 31315 ^ -19112 ^ 2349 ^ -14810);
            FXtJV9qbIm(this, Float.intBitsToFloat('\uf11c' ^ 20565 ^ '\udb37' ^ -1805898388 ^ '\uf33d' ^ '袹' ^ 5790 ^ -1805895160));
         } else if (c) {
            kQInOSw1iu(this, N2GEJw4EFy(this) + (299 ^ -29624 ^ 8669 ^ -21313));
            wNgAuvcQSA(this, ynbrvNIDou(this) < (3240 ^ -1186 ^ 7601 ^ -5555) ? (float)1bZ6YurTYt(this) * Float.intBitsToFloat(12365 ^ '覦' ^ 31088 ^ 1962299377 ^ '\uf153' ^ 10316 ^ '\udb9c' ^ 1228554532) : Float.intBitsToFloat(4419 ^ 98886 ^ 30547 ^ 1707917762 ^ 241294 ^ 231299 ^ 20576 ^ 1518440500) + Float.intBitsToFloat('뚑' ^ '뽒' ^ 28298 ^ 1029113028 ^ 'ꓕ' ^ 105975 ^ '뛯' ^ 2102848576) / (float)(CLloVdcmsr(this) - (20444 ^ -24199 ^ 8510 ^ -12398)) * Float.intBitsToFloat(130310 ^ 108012 ^ 17947 ^ -1031213876 ^ 30706 ^ 494788 ^ 3055 ^ -12333527));
         }
      } else {
         aTFNrAe2Pp(this, Float.intBitsToFloat(5918 ^ '豥' ^ '\ue907' ^ -11789006 ^ 6168 ^ '騉' ^ '\uf616' ^ -11789495));
      }

      super.onLivingUpdate();
      if (eiBSVQjqN6(this) && 9kYQndn1Jv(ai6eAYtbh8(this)) && !D90qhan8OO(hYFeDjtavh(this)).isSpectator()) {
         iyc66umly2(95486lOLAb(this), (boolean)(22020 ^ -16331 ^ 30997 ^ -4316));
         this.sendPlayerAbilities();
      }

   }

   public boolean attackEntityFrom(DamageSource a, float b) {
      return (boolean)(8971 ^ -30096 ^ 24073 ^ -2190);
   }

   public BlockPos getPosition() {
      return new BlockPos(gTdLPjoDTl(this) + Double.longBitsToDouble(8236968365858370179L ^ 5597858984219259523L), EXjp9q3NoB(this) + Double.longBitsToDouble(7193381569972038205L ^ 6643942415432837693L), RQQ2aPDhGQ(this) + Double.longBitsToDouble(-6692041398386766070L ^ -7151408560378556662L));
   }

   private static VW _4r2e01V30/* $FF was: 24r2e01V30*/() {
      return NL.BLINDNESS;
   }

   private static double _rxDq4XAw8/* $FF was: 9rxDq4XAw8*/(0f var0) {
      return var0.posX;
   }

   private static float UOJ6v47Lb3(0f var0) {
      return var0.height;
   }

   private static double NMxbM3L7Xz(0f var0) {
      return var0.posX;
   }

   private static int eLrbOiclYB(0f var0) {
      return var0.maxHurtResistantTime;
   }

   private static bij QseMHDlGJs(0f var0) {
      return var0.world;
   }

   private static int z6jiJrSjI1(0f var0) {
      return var0.horseJumpPowerCounter;
   }

   private static int z6cTbyaSv9(0f var0) {
      return var0.flyToggleTimer;
   }

   public void setPermissionLevel(int a) {
      n2bFLQqMv3(this, a);
   }

   private static 0g FoVY1tNgXT(0a var0) {
      return var0.keyboard;
   }

   private static 0h DV4yUoYdCA(0f var0) {
      return var0.movementInput;
   }

   private static void WbemBhY8n6(0f var0, int var1) {
      var0.maxHurtTime = var1;
   }

   private static void kQInOSw1iu(0f var0, int var1) {
      var0.horseJumpPowerCounter = var1;
   }

   private static MJ QLs9688r4Z(0f var0) {
      return var0.inventory;
   }

   private static void HKna7byC22(0f var0, boolean var1) {
      var0.inPortal = var1;
   }

   private static 0h vwRWbu5WjQ(0f var0) {
      return var0.movementInput;
   }

   private static MJ _LV8BzqTJr/* $FF was: 2LV8BzqTJr*/(0f var0) {
      return var0.inventory;
   }

   private static float Nm9Iv6w4X3(0f var0) {
      return var0.horseJumpPower;
   }

   private static int JdkGBeNnbZ(0f var0) {
      return var0.positionUpdateTicks;
   }

   private static boolean qNKlo2aYe2(0f var0) {
      return var0.onGround;
   }

   private static void gqWF97LVyt(0f var0, float var1) {
      var0.rotationYaw = var1;
   }

   private static boolean _JnnnbjVxO/* $FF was: 8JnnnbjVxO*/(0h var0) {
      return var0.rightKeyDown;
   }

   private static bbb yg2tsO7cie() {
      return bbb.CREATIVE;
   }

   private static double gTdLPjoDTl(0f var0) {
      return var0.posX;
   }

   private static boolean _QM8jm4ogf/* $FF was: 1QM8jm4ogf*/(0f var0) {
      return var0.inPortal;
   }

   private static void qWbMnGTzAt(0f var0, int var1) {
      var0.sprintToggleTimer = var1;
   }

   private static float wLGERgLd4q(0f var0) {
      return var0.rotationPitch;
   }

   public void updateEntityActionState() {
      super.updateEntityActionState();
      NMekFFuoZp(this, 4o4BTw0W7F(sAhWDFB6WO(this)));
      jV3jjqmwTa(this, qIPegdmUGA(9ELj7B2dDD(this)));
      ewFsnDFmh9(this, WuX7LVbOag(uyyQlM5S7t(this)));
      1Kx6SrYb31(this, BVidjGkFGB(this));
      Dr2y5TY8EF(this, (float)((double)AxQLMKNOYl(this) + (double)(ATAMWTVR9L(this) - TwSVAmvwW8(this)) * Double.longBitsToDouble(-2926543139193333219L ^ -1692556841293817315L)));
      4dNbW3W0zf(this, (float)((double)beWBEoSgrQ(this) + (double)(ADgyP6VXY7(this) - Zyv7m5stxM(this)) * Double.longBitsToDouble(-9208646981075395642L ^ -4623982560412230714L)));
   }

   private static double ozywfnxTcR(0f var0) {
      return var0.motionX;
   }

   private static ML AqvxqFWL7n(0f var0) {
      return var0.capabilities;
   }

   protected boolean pushOutOfBlocks(double d, double e, double f) {
      if (aoj40FfYBR(this)) {
         return (boolean)(28347 ^ -15893 ^ 21558 ^ -1178);
      } else {
         BlockPos g = new BlockPos(d, e, f);
         double h = d - (double)g.getX();
         double i = f - (double)g.getZ();
         if (!this.isOpenBlockSpace(g)) {
            int a = -32434 ^ -7453 ^ 21471 ^ -12403;
            double b = Double.longBitsToDouble(-7035833433777810533L ^ -2407115430480358501L);
            if (this.isOpenBlockSpace(g.west()) && h < b) {
               b = h;
               a = 3379 ^ -30646 ^ 32583 ^ -1474;
            }

            if (this.isOpenBlockSpace(g.east()) && Double.longBitsToDouble(5595479089017412399L ^ 8239092070283893551L) - h < b) {
               b = Double.longBitsToDouble(5366659650587279429L ^ 8469639793845551173L) - h;
               a = 18484 ^ -1479 ^ 10720 ^ -25620;
            }

            if (this.isOpenBlockSpace(g.north()) && i < b) {
               b = i;
               a = 13684 ^ -3024 ^ 28959 ^ -20385;
            }

            if (this.isOpenBlockSpace(g.south()) && Double.longBitsToDouble(7855850054189372996L ^ 5977849009575876164L) - i < b) {
               b = Double.longBitsToDouble(-2721189545270951047L ^ -1888023614207409287L) - i;
               a = 7372 ^ -19750 ^ 22175 ^ -1908;
            }

            float c = Float.intBitsToFloat(105675 ^ 106126 ^ 4894 ^ -1048802810 ^ '흚' ^ '馞' ^ 16061 ^ -55564311);
            if (a == 0) {
               xEyiBSdVAG(this, Double.longBitsToDouble(-8208195139701340696L ^ 3580145768960591336L));
            }

            if (a == (26574 ^ -5876 ^ 5086 ^ -25315)) {
               wpQcCgbBq6(this, Double.longBitsToDouble(-8010073887902267759L ^ -5805393196738148719L));
            }

            if (a == (24963 ^ -23326 ^ 9508 ^ -8127)) {
               inIyXKQuVm(this, Double.longBitsToDouble(-1851786168187389476L ^ 6482012279363959260L));
            }

            if (a == (11566 ^ -2818 ^ 27587 ^ -19946)) {
               KziwIQh7wO(this, Double.longBitsToDouble(2651453292030298332L ^ 1977716996355229916L));
            }
         }

         return (boolean)(17843 ^ -24806 ^ 26715 ^ -19726);
      }
   }

   private static void F2qiQMT7tO(0f var0, Container var1) {
      var0.openContainer = var1;
   }

   private static boolean t93pUYBYQA(0f var0) {
      return var0.serverSneakState;
   }

   private static VW _fI7jVZdrK/* $FF was: 0fI7jVZdrK*/() {
      return NL.NAUSEA;
   }

   public EnumHand getActiveHand() {
      return yq92qBeIjT(this);
   }

   private static bij JLARsuTVt7(0f var0) {
      return var0.world;
   }

   private static double g5hV26jJW7(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static int ynbrvNIDou(0f var0) {
      return var0.horseJumpPowerCounter;
   }

   private static void nUJ2JCF6X6(ML var0, boolean var1) {
      var0.isFlying = var1;
   }

   private static float Zyv7m5stxM(0f var0) {
      return var0.renderArmYaw;
   }

   protected Qy dropItemAndGetStack(IY a) {
      return 5m6O1S4IAS();
   }

   private static Rd Y729BrBD6B() {
      return HAND_STATES;
   }

   private static void _5He9KQW7T/* $FF was: 85He9KQW7T*/(0f var0, float var1) {
      var0.timeInPortal = var1;
   }

   private static 0i zRLENCtFmb(0a var0) {
      return var0.controller;
   }

   private static bij WIGid69Sh9(0f var0) {
      return var0.world;
   }

   private static void r7SSbe7Xjy(0f var0, double var1) {
      var0.motionY = var1;
   }

   public void playSound(SoundEvent a, float b, float c) {
   }

   private static void tcabDavBdJ(0f var0, float var1) {
      var0.experience = var1;
   }

   private static VW wWvuCA6vbF() {
      return NL.NAUSEA;
   }

   public Vec3d getLook(float a) {
      return getVectorForRotation(t6DsHlWHJm(this), Eil2LyZMmg(this));
   }

   public void setActiveHand(EnumHand a) {
      Qy b = this.getHeldItem(a);
      if (!b.isEmpty() && !this.isHandActive()) {
         super.setActiveHand(a);
         LBfNgyRoxW(this, (boolean)(23373 ^ -5312 ^ 28071 ^ -8789));
         pT9oarYnTo(this, a);
      }

   }

   private static int fUTnycnLQA(0f var0) {
      return var0.timeUntilPortal;
   }

   private static void VACbh1AYng(0f var0, int var1) {
      var0.sprintingTicksLeft = var1;
   }

   public void onUpdateWalkingPlayer() {
      boolean a = this.isSprinting();
      if (a != YOqJ4FrI2e(this)) {
         if (a) {
            IbOoMOq7vA(this).sendPacket(new SQ(this, GFy7TLbN72()));
         } else {
            QdMXKQGi9s(this).sendPacket(new SQ(this, 67QEvXqoIE()));
         }

         tAT6baJj2i(this, a);
      }

      boolean b = this.isSneaking();
      if (b != t93pUYBYQA(this)) {
         if (b) {
            NN9OuWQS7C(this).sendPacket(new SQ(this, 1sGqVAiJeB()));
         } else {
            Ar4aetjrSj(this).sendPacket(new SQ(this, S9oPWnD5WW()));
         }

         6s86uYbJPA(this, b);
      }

      AxisAlignedBB c = this.getEntityBoundingBox();
      double d = 47Zgk91tig(this) - 930awbDzHN(this);
      double e = p7y11BZIw4(c) - N6XLCIjn39(this);
      double f = tBQW01qes5(this) - FnowfeJ2X5(this);
      double g = (double)(T9vad7wOAb(this) - B9sVbN6VGI(this));
      double h = (double)(rAfWdIIZwb(this) - ITLA2Y6JhZ(this));
      hlA7nli1H1(this, HLXs5JoOL1(this) + (30104 ^ -32265 ^ 19127 ^ -16679));
      boolean i = !(d * d + e * e + f * f > Double.longBitsToDouble(-3767097863594793821L ^ -795472692513640655L)) && JdkGBeNnbZ(this) < (20975 ^ -19967 ^ 10764 ^ -13834) ? 25685 ^ -10587 ^ 8578 ^ -27790 : 7730 ^ -31112 ^ 8416 ^ -18261;
      boolean j = g == Double.longBitsToDouble(-4760648435511741361L ^ -4760648435511741361L) && h == Double.longBitsToDouble(-5953847640870334796L ^ -5953847640870334796L) ? 8475 ^ -26826 ^ 30543 ^ -16030 : 29274 ^ -23720 ^ 20002 ^ -24799;
      if (this.isRiding()) {
         d4zjXnNzaC(this).sendPacket(new SW(ozywfnxTcR(this), Double.longBitsToDouble(4185272790573865127L ^ -388950667671938905L), fmHirD11t4(this), BIPMvn8J0A(this), wLGERgLd4q(this), yejwiuQ2Ew(this)));
         i = 10355 ^ -13084 ^ 11418 ^ -14323;
      } else if (i != 0 && j != 0) {
         8bgiytBR1G(this).sendPacket(new SW(iLZT7FGo2w(this), 4rgGg79DpQ(c), dWHTI549oE(this), 1SBBnW44wC(this), ZYZhqu8KAq(this), L3FqQF2wrU(this)));
      } else if (i != 0) {
         v6LuuyDUiF(this).sendPacket(new SV(9rxDq4XAw8(this), YOwnSYJQ6E(c), EtqAQJbZNa(this), DyvIPY8LiW(this)));
      } else if (j != 0) {
         zioeak4dJg(this).sendPacket(new SX(yayjDamibe(this), JrrrTDZGHe(this), VlGp79Q0xL(this)));
      } else if (gaTaPfBI8A(this) != feZJ4jRIIw(this)) {
         Rew8N5XJsJ(this).sendPacket(new SY(mqLgHg4bF3(this)));
      }

      if (i != 0) {
         nDhAOAN1rl(this, dY0SSYJXUF(this));
         W4L1GJr2DT(this, g5hV26jJW7(c));
         avqVn4sbTU(this, QrOzyqAb8k(this));
         aYRyBWbFSZ(this, 5701 ^ -1433 ^ 11002 ^ -14632);
      }

      if (j != 0) {
         BYtBUqsV1X(this, vtyYSMllqI(this));
         f1DpSCuOxi(this, ojiDqpvGKg(this));
      }

      NvOxHODfJi(this, dJ9GH2rQKU(this));
   }

   private static bij WEuhyJGSaQ(0f var0) {
      return var0.world;
   }

   private static void iyc66umly2(ML var0, boolean var1) {
      var0.isFlying = var1;
   }

   private static double asbIyeFbGN(0f var0) {
      return var0.posZ;
   }

   public void jump() {
      if (Ao0rVuFQIe(this)) {
         super.jump();
      }

   }

   private static VW ZeCN4aokAW() {
      return NL.NAUSEA;
   }

   private static float IJVwMDzkud(Ig var0) {
      return var0.rotationYaw;
   }

   private static void FXtJV9qbIm(0f var0, float var1) {
      var0.horseJumpPower = var1;
   }

   private static float ojiDqpvGKg(0f var0) {
      return var0.rotationPitch;
   }

   private static EnumHand yq92qBeIjT(0f var0) {
      return var0.activeHand;
   }

   public void handleStatusUpdate(byte a) {
      if (a >= (23087 ^ -8348 ^ 14115 ^ -19856) && a <= (21065 ^ -6619 ^ 22843 ^ -4789)) {
         this.setPermissionLevel(a - (27031 ^ -12977 ^ 4859 ^ -18885));
      } else {
         super.handleStatusUpdate(a);
      }

   }

   private static void aYRyBWbFSZ(0f var0, int var1) {
      var0.positionUpdateTicks = var1;
   }

   private static void nDhAOAN1rl(0f var0, double var1) {
      var0.lastReportedPosX = var1;
   }

   private static void inIyXKQuVm(0f var0, double var1) {
      var0.motionZ = var1;
   }

   public int getPermissionLevel() {
      return J8WVyj1tlN(this);
   }

   private static String AtVokXSDCG(0f var0) {
      return var0.serverBrand;
   }

   private static void uHFUdfWC7y(0f var0, float var1) {
      var0.prevTimeInPortal = var1;
   }

   private static void xEyiBSdVAG(0f var0, double var1) {
      var0.motionX = var1;
   }

   private static boolean _qVTOXCvSf/* $FF was: 1qVTOXCvSf*/(XQ var0) {
      return var0.isIndependent;
   }

   private static BlockPos _8JrSM7eRK/* $FF was: 98JrSM7eRK*/() {
      return BlockPos.ORIGIN;
   }

   private static 0bl KhnAbn1JR7(0f var0) {
      return var0.connection;
   }

   private static float ZYZhqu8KAq(0f var0) {
      return var0.rotationPitch;
   }

   private static float dGVIzr0Llt(0f var0) {
      return var0.rotationPitch;
   }

   private static SP _NqnRNgTGJ/* $FF was: 2NqnRNgTGJ*/() {
      return SP.START_FALL_FLYING;
   }

   private static Ta _UqateoVYR/* $FF was: 1UqateoVYR*/() {
      return Ta.DROP_ITEM;
   }

   private static boolean aoj40FfYBR(0f var0) {
      return var0.noClip;
   }

   private static double q379YrvJW7(0f var0) {
      return var0.posZ;
   }

   private static double EtqAQJbZNa(0f var0) {
      return var0.posZ;
   }

   private static void vpv7jmu6Ay(0h var0, boolean var1) {
      var0.jump = var1;
   }

   private static void DohGthfzJl(0f var0, boolean var1) {
      var0.rowingBoat = var1;
   }

   private static boolean g8v6uTOFrV(ML var0) {
      return var0.allowFlying;
   }

   private static double RQQ2aPDhGQ(0f var0) {
      return var0.posZ;
   }

   private static int _fszZTAM47/* $FF was: 9fszZTAM47*/(0f var0) {
      return var0.sprintToggleTimer;
   }

   private static int grfcH9OGB3(0f var0) {
      return var0.autoJumpTime;
   }

   private static SP _7QEvXqoIE/* $FF was: 67QEvXqoIE*/() {
      return SP.STOP_SPRINTING;
   }

   private static ML CFtdY4zb27(0f var0) {
      return var0.capabilities;
   }

   private static double YM67yg72HY(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static 0a hYFeDjtavh(0f var0) {
      return var0.bot;
   }

   private static float aTntJobfjG(Ig var0) {
      return var0.rotationYaw;
   }

   private static SP S9oPWnD5WW() {
      return SP.STOP_SNEAKING;
   }

   private static void UGBIaqlg1S(0f var0, int var1) {
      var0.experienceTotal = var1;
   }

   public boolean isSneaking() {
      boolean a = N4GS0WrU7L(this) != null && 0w9fq6nOMj(fUBYS4JwwS(this)) ? 11847 ^ -28993 ^ 17710 ^ -6697 : 7557 ^ -10280 ^ 22686 ^ -27965;
      return (boolean)(a != 0 && !aloDO97ayk(this) ? 20929 ^ -21606 ^ 1028 ^ -418 : 7878 ^ -5553 ^ 30340 ^ -32243);
   }

   private static float B6QStG6MH7(0f var0) {
      return var0.moveStrafing;
   }

   public boolean isHandActive() {
      return ylJjmqqR87(this);
   }

   private static double VP4Aol7AZr(AxisAlignedBB var0) {
      return var0.minY;
   }

   public void respawnPlayer() {
      sNqoqy4STy(this).sendPacket(new SI(vcJ0CRaWfk()));
   }

   private static int ILSgbRbRe2(0f var0) {
      return var0.autoJumpTime;
   }

   private static double _4l1H8GiX9/* $FF was: 54l1H8GiX9*/(0f var0) {
      return var0.posZ;
   }

   private static void avqVn4sbTU(0f var0, double var1) {
      var0.lastReportedPosZ = var1;
   }

   private static 0h N4GS0WrU7L(0f var0) {
      return var0.movementInput;
   }

   private static 0bl NN9OuWQS7C(0f var0) {
      return var0.connection;
   }

   private static void eedOnyTTkQ(0f var0, int var1) {
      var0.experienceLevel = var1;
   }

   private static 0bl oS31a7lJkR(0f var0) {
      return var0.connection;
   }

   private static float _LLQZda7hU/* $FF was: 9LLQZda7hU*/(0f var0) {
      return var0.timeInPortal;
   }

   private static float ww4vzAYwRK(0h var0) {
      return var0.moveForward;
   }

   private static void tVkvK8lybZ(0f var0, Container var1) {
      var0.openContainer = var1;
   }

   private static boolean USWGaTIA1R(ML var0) {
      return var0.isFlying;
   }

   private static double PkhL5z9R7S(AxisAlignedBB var0) {
      return var0.maxY;
   }

   private static float qIPegdmUGA(0h var0) {
      return var0.moveForward;
   }

   private static ML _5486lOLAb/* $FF was: 95486lOLAb*/(0f var0) {
      return var0.capabilities;
   }

   private static bij qJ5NYl8TSg(0f var0) {
      return var0.world;
   }

   private static float mNSils4ayI(0f var0) {
      return var0.rotationYaw;
   }

   private static Qy _eLSB7gFwP/* $FF was: 7eLSB7gFwP*/() {
      return Qy.EMPTY;
   }

   private static void XBTX23qDyO(0f var0, Container var1) {
      var0.openContainer = var1;
   }

   private static void LYnwGCr4Py(0f var0, String var1) {
      var0.serverBrand = var1;
   }

   private static void ZekizLeS1I(0h var0, float var1) {
      var0.moveForward = var1;
   }

   private static 0h jBg0FergqJ(0f var0) {
      return var0.movementInput;
   }

   public void openEditSign(YQ a) {
   }

   private static 0h lk0ICcIDWk(0f var0) {
      return var0.movementInput;
   }

   private static void TyuCAnGl0q(0f var0, int var1) {
      var0.hurtResistantTime = var1;
   }

   private static boolean fwdFlmnNlB(0h var0) {
      return var0.sneak;
   }

   private static bij _UlHlqBvty/* $FF was: 3UlHlqBvty*/(0f var0) {
      return var0.world;
   }

   private static boolean yejwiuQ2Ew(0f var0) {
      return var0.onGround;
   }

   private static double iLZT7FGo2w(0f var0) {
      return var0.posX;
   }

   private static float yayjDamibe(0f var0) {
      return var0.rotationYaw;
   }

   private static void _9anY4Q2Oa/* $FF was: 19anY4Q2Oa*/(0f var0, float var1) {
      var0.timeInPortal = var1;
   }

   public void setSprinting(boolean a) {
      super.setSprinting(a);
      BNt2mgjt1N(this, 29305 ^ -16348 ^ 9755 ^ -27578);
   }

   private static float B9sVbN6VGI(0f var0) {
      return var0.lastReportedYaw;
   }

   private static float TwSVAmvwW8(0f var0) {
      return var0.renderArmPitch;
   }

   private static boolean dAa6mnFv1j(0h var0) {
      return var0.forwardKeyDown;
   }

   private static 0bl A0i3tbb64Y(0f var0) {
      return var0.connection;
   }

   private static Rv gVmNchiJq8(0f var0) {
      return var0.dataManager;
   }

   public void displayGui(bga a) {
      String b = a.getGuiID();
      int var4 = -985 ^ -11811 ^ 29377 ^ -24380;
      switch (b.hashCode()) {
         case -1150744385:
            if (b.equals(OpOtzWn8fg("чуфящјыьўАыфќуц"))) {
               var4 = 8649 ^ -30907 ^ 28962 ^ -10324;
            }
            break;
         case -1124126594:
            if (b.equals(OpOtzWn8fg("чуфящјыьўАщјыьўуфэѵўышця"))) {
               var4 = 30725 ^ -8907 ^ 6595 ^ -17165;
            }
            break;
         case 319164197:
            if (b.equals(OpOtzWn8fg("чуфящјыьўАяфщтыфўуфэѵўышця"))) {
               var4 = 24402 ^ -21023 ^ 6252 ^ -5410;
            }
      }

      switch (var4) {
         case 0:
         default:
            break;
         case 1:
            D1njxxFc3W(this, new ContainerEnchantment(NJlt4Bryy1(this), JLARsuTVt7(this)));
            break;
         case 2:
            tVkvK8lybZ(this, new ContainerRepair(w8YMqOYEfN(this), QseMHDlGJs(this), this));
      }

   }

   private static 0bl QdMXKQGi9s(0f var0) {
      return var0.connection;
   }

   private static float rAfWdIIZwb(0f var0) {
      return var0.rotationPitch;
   }

   private static 0h qjPdeHwOdh(0f var0) {
      return var0.movementInput;
   }

   private static 0a BsKlZngq1q(0f var0) {
      return var0.bot;
   }

   private static void oHNgmkKjVW(0h var0, float var1) {
      var0.moveForward = var1;
   }

   private static double dY0SSYJXUF(0f var0) {
      return var0.posX;
   }

   private static void _s86uYbJPA/* $FF was: 6s86uYbJPA*/(0f var0, boolean var1) {
      var0.serverSneakState = var1;
   }

   public void displayGuiEditCommandCart(XZ a) {
   }

   private static EnumFacing ObesVoFqYn() {
      return EnumFacing.DOWN;
   }

   private static void TmeeDVXdyK(0f var0, float var1) {
      var0.horseJumpPower = var1;
   }

   private static void FAhL4Um2M7(0f var0, Container var1) {
      var0.openContainer = var1;
   }

   private static float y5JT69Qpy7(Ig var0) {
      return var0.rotationYaw;
   }

   private static 0h OvC9GNiw1O(0f var0) {
      return var0.movementInput;
   }

   private static ML _KfQLlRlA4/* $FF was: 3KfQLlRlA4*/(0f var0) {
      return var0.capabilities;
   }

   private static void a8ESNYk10o(0f var0, boolean var1) {
      var0.rowingBoat = var1;
   }

   private static double B1iY5S7qdb(Vec3d var0) {
      return var0.z;
   }

   private static double VhZ94aJgvI(Vec3d var0) {
      return var0.x;
   }

   private static 0bl Y1eOFcQFGe(0f var0) {
      return var0.connection;
   }

   private static MJ kLUyOzbQLE(0f var0) {
      return var0.inventory;
   }

   private static double jQD52ZvDIi(0f var0) {
      return var0.posZ;
   }

   public boolean isSpectator() {
      return (boolean)(2uJSu1yyrV(YqGO9pSwUC(this)) != null && zRLENCtFmb(SoA1eIYOxN(this)).getGameType() == 8OrWo7XK36() ? 14960 ^ -10443 ^ 8036 ^ -3552 : 7944 ^ -19117 ^ 12834 ^ -26503);
   }

   public void openBook(Qy a, EnumHand b) {
   }

   private static void KziwIQh7wO(0f var0, double var1) {
      var0.motionZ = var1;
   }

   private static float DklFeJeb2N(0f var0) {
      return var0.rotationYaw;
   }

   private static 0h _ELj7B2dDD/* $FF was: 9ELj7B2dDD*/(0f var0) {
      return var0.movementInput;
   }

   private static boolean WuX7LVbOag(0h var0) {
      return var0.jump;
   }

   private static 0h DbemgVxwFu(0f var0) {
      return var0.movementInput;
   }

   private static 0i D90qhan8OO(0a var0) {
      return var0.controller;
   }

   public void sendPlayerAbilities() {
      ISXccgEa3w(this).sendPacket(new SZ(ecoF9gyPTq(this)));
   }

   private static boolean hf7ZDFdW9M(ML var0) {
      return var0.allowFlying;
   }

   private static boolean _kYQndn1Jv/* $FF was: 9kYQndn1Jv*/(ML var0) {
      return var0.isFlying;
   }

   private static MJ PwFJTYAZke(0f var0) {
      return var0.inventory;
   }

   private static boolean qFGVF6ONA7(0h var0) {
      return var0.jump;
   }

   private static double wxwLDbg7aF(AxisAlignedBB var0) {
      return var0.minY;
   }

   public void onCriticalHit(Ig a) {
   }

   private static VW Y0QcGAXa01() {
      return NL.JUMP_BOOST;
   }

   private static ML L5iqidCunD(0f var0) {
      return var0.capabilities;
   }

   private static double q9tgFJxJre(0f var0) {
      return var0.posX;
   }

   private static VW dJbMSlMNi1() {
      return NL.JUMP_BOOST;
   }

   public void closeScreen() {
      qBeIoIIHBi(this).sendPacket(new SJ(am2J90g6rl(5eOYlDBqTb(this))));
      this.closeScreenAndDropStack();
   }

   private static int QrGgjwgWzA(0f var0) {
      return var0.timeUntilPortal;
   }

   private static VW NNOebvYvAb() {
      return NL.BLINDNESS;
   }

   private static int qFM27eyKOO(0f var0) {
      return var0.sprintToggleTimer;
   }

   private static void Zrat8QjD2d(0f var0, float var1) {
      var0.timeInPortal = var1;
   }

   private static boolean _MOaL4yb1g/* $FF was: 7MOaL4yb1g*/(0h var0) {
      return var0.rightKeyDown;
   }

   private static 0i EoT5dXjRBV(0a var0) {
      return var0.controller;
   }

   private static float wTE3GqGN5x(0h var0) {
      return var0.moveForward;
   }

   private static MJ po99RIoFIO(0f var0) {
      return var0.inventory;
   }

   private static 0i JZYpBqN1Cm(0a var0) {
      return var0.controller;
   }

   private static 0bl _bgiytBR1G/* $FF was: 8bgiytBR1G*/(0f var0) {
      return var0.connection;
   }

   private static 0bl zioeak4dJg(0f var0) {
      return var0.connection;
   }

   private static void fRFo7o06qv(0f var0, boolean var1) {
      var0.handActive = var1;
   }

   private static ML ecoF9gyPTq(0f var0) {
      return var0.capabilities;
   }

   public void setPlayerSPHealth(float b) {
      if (4kSHhrgFe9(this)) {
         float a = this.getHealth() - b;
         if (a <= Float.intBitsToFloat(15151 ^ '莺' ^ '꜓' ^ -2000271887 ^ '﨩' ^ 225409 ^ 9823 ^ -2000292224)) {
            this.setHealth(b);
            if (a < Float.intBitsToFloat('덹' ^ 'ꑈ' ^ 4349 ^ -1608947485 ^ '\ue70e' ^ 'ꋻ' ^ 4061 ^ -1608964857)) {
               xrmSady8fb(this, eLrbOiclYB(this) / (26107 ^ -7932 ^ 15093 ^ -16888));
            }
         } else {
            O7zws8N3aT(this, a);
            this.setHealth(this.getHealth());
            TyuCAnGl0q(this, cvc3ZRp6LB(this));
            this.damageEntity(KF2mjY9Q6l(), a);
            int var10003 = 20961 ^ -13026 ^ 29385 ^ -4548;
            WbemBhY8n6(this, 20961 ^ -13026 ^ 29385 ^ -4548);
            wsZoQIVLol(this, var10003);
         }
      } else {
         this.setHealth(b);
         6J6oLq6so4(this, (boolean)(20317 ^ -19044 ^ 31844 ^ -31068));
      }

   }

   private static bij HtQxnSNoL7(0f var0) {
      return var0.world;
   }

   private static boolean JFiT1hn4lH(0f var0) {
      return var0.collidedHorizontally;
   }

   public void onUpdate() {
      if (Fs8tSEdSHT(this).isBlockLoaded(new BlockPos(0nALQmMoPG(this), Double.longBitsToDouble(3837312577377037410L ^ 3837312577377037410L), dJE8SyyftR(this)))) {
         super.onUpdate();
         if (this.isRiding()) {
            GD4NiwAs9a(this).sendPacket(new SX(mNSils4ayI(this), dGVIzr0Llt(this), 1BRZyAQ6TG(this)));
            KhnAbn1JR7(this).sendPacket(new SS(B6QStG6MH7(this), nUlenL5La9(this), hMoiDdlwAl(DV4yUoYdCA(this)), bqcLahNQO4(4q2u61FQyS(this))));
            Ig a = this.getLowestRidingEntity();
            if (a != this && a.canPassengerSteer()) {
               Y1eOFcQFGe(this).sendPacket(new Tq(a));
            }
         } else {
            this.onUpdateWalkingPlayer();
         }
      }

   }

   private static boolean _4VpRTDbc9/* $FF was: 24VpRTDbc9*/(0f var0) {
      return var0.rowingBoat;
   }

   private static MJ VNq6IrJXbL(0f var0) {
      return var0.inventory;
   }

   private static void WtSkL4wI2B(0h var0, float var1) {
      var0.moveStrafe = var1;
   }

   private static int HLXs5JoOL1(0f var0) {
      return var0.positionUpdateTicks;
   }

   private static double _2yq17vQze/* $FF was: 12yq17vQze*/(AxisAlignedBB var0) {
      return var0.maxY;
   }

   public boolean isRowingBoat() {
      return 24VpRTDbc9(this);
   }

   private static EntityEquipmentSlot _1WTY1obQ6/* $FF was: 51WTY1obQ6*/() {
      return EntityEquipmentSlot.CHEST;
   }

   private static 0h bldRXHWrwa(0f var0) {
      return var0.movementInput;
   }

   private static 0h v0H4wJqoZj(0f var0) {
      return var0.movementInput;
   }

   private static 0h _Nbvymobt1/* $FF was: 1Nbvymobt1*/(0f var0) {
      return var0.movementInput;
   }

   private static void wNgAuvcQSA(0f var0, float var1) {
      var0.horseJumpPower = var1;
   }

   private static float s6eN6XftK6(Vec2f var0) {
      return var0.x;
   }

   private static void O7zws8N3aT(0f var0, float var1) {
      var0.lastDamage = var1;
   }

   private static boolean fFKvKFDl64(ML var0) {
      return var0.isFlying;
   }

   private static boolean AGa9ElOdSB(0g var0) {
      return var0.keyBindSprint;
   }

   private static 0h iC1u2BawFO(0f var0) {
      return var0.movementInput;
   }

   private static double rivRwxLDKV(0f var0) {
      return var0.posZ;
   }

   private static float ka4TFoWlio(0h var0) {
      return var0.moveStrafe;
   }

   private static 0h fUBYS4JwwS(0f var0) {
      return var0.movementInput;
   }

   public float getHorseJumpPower() {
      return Nm9Iv6w4X3(this);
   }

   private static float g7rDUJeV7s(0f var0) {
      return var0.width;
   }

   public void updateRidden() {
      super.updateRidden();
      knnBOFajVt(this, (boolean)(28543 ^ -7952 ^ 14385 ^ -18498));
      if (this.getRidingEntity() instanceof IR) {
         IR a = (IR)this.getRidingEntity();
         a.updateInputs(D7AvRkQDA1(lk0ICcIDWk(this)), 8JnnnbjVxO(9eJbNoI6eW(this)), e9SORKxrnJ(hOtwFSSgXM(this)), JBNtFBjO6k(nJOOnobSut(this)));
         DohGthfzJl(this, (boolean)(9NBPJuqsWn(this) | (!KJ1LNjw2rB(CqtBhS0Cql(this)) && !7MOaL4yb1g(bldRXHWrwa(this)) && !dAa6mnFv1j(OvC9GNiw1O(this)) && !Gaq8xktKmq(tBPUP1y4QL(this)) ? 23095 ^ -12898 ^ 21274 ^ -15181 : 19748 ^ -25944 ^ 24820 ^ -18567)));
      }

   }

   private static ML EiHbd9UcvO(0f var0) {
      return var0.capabilities;
   }

   private static void yfWt7nBWFD(0f var0, Container var1) {
      var0.openContainer = var1;
   }

   private static boolean _NBPJuqsWn/* $FF was: 9NBPJuqsWn*/(0f var0) {
      return var0.rowingBoat;
   }

   public String getServerBrand() {
      return AtVokXSDCG(this);
   }

   protected void sendHorseJump() {
      aWZGkylSTs(this).sendPacket(new SQ(this, 5xB1y4DfP7(), MathHelper.floor(this.getHorseJumpPower() * Float.intBitsToFloat('陒' ^ 254790 ^ '\udede' ^ -1346782531 ^ 29991 ^ 251068 ^ 8797 ^ -311323983))));
   }
}
