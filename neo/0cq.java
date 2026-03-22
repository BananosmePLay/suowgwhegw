package neo;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.inventory.Container;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.AxisAlignedBB;

public class 0cq extends 0cB {
   public double z;
   public final 0bz speed = new 0bz(legWohDgx7("ǊǑǕƫǕƪƩƧ\u05cbǔǕǐǞƩǛ"), Float.intBitsToFloat(19455 ^ '쫾' ^ 5202 ^ 1231429360 ^ 100587 ^ '衾' ^ 109815 ^ 1986406337), Float.intBitsToFloat(10928 ^ '\ue90b' ^ 1334 ^ -1310499835 ^ '\uebd5' ^ 110807 ^ '\uea4b' ^ -1943083252), Float.intBitsToFloat(2934 ^ '饆' ^ 30281 ^ 633497025 ^ 2747 ^ '콈' ^ 1274 ^ 440549553), Float.intBitsToFloat('煉' ^ '췟' ^ 10563 ^ 628311492 ^ 16739 ^ 21903 ^ 2998 ^ 415206236));
   public double y;
   public double x;

   private static float qOAlPintIB(0bz var0) {
      return var0.value;
   }

   private static float Q2fBKGNFJ6(EntityPlayerSP var0) {
      return var0.rotationYawHead;
   }

   private static Minecraft RnYz9rjtJV() {
      return mc;
   }

   private static Minecraft Tzb3Rl4iBF() {
      return mc;
   }

   private static Minecraft v6T4QfNt4n() {
      return mc;
   }

   private static Minecraft djzpcETAsa() {
      return mc;
   }

   @0X
   public void onScreen(0P e) {
   }

   private static Minecraft fyFB0EaHVO() {
      return mc;
   }

   private static EntityPlayerSP rpSQXCbRXm() {
      return Minecraft.player;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String legWohDgx7(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 14078 ^ -24441 ^ 18639 ^ -8522; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ '蓵' ^ -25535 ^ '\uda8c' ^ -14381));
      }

      return var1.toString();
   }

   @0X
   public void onPacket(0v event) {
      v6T4QfNt4n();
      if (iVwSiyoo2Y(RiK9oqolDh())) {
         this.toggle();
      }

   }

   private static Minecraft wgdE6v5vSc() {
      return mc;
   }

   private static void vrOa2y24PM(EntityOtherPlayerMP var0, Container var1) {
      var0.inventoryContainer = var1;
   }

   private static EntityPlayerSP _7JevIieM6/* $FF was: 37JevIieM6*/() {
      return Minecraft.player;
   }

   private static double T6aVTOyDOC(0cq var0) {
      return var0.z;
   }

   private static EntityPlayerSP ZNDwLFTby5() {
      return Minecraft.player;
   }

   private static boolean KX71NLqljN(KeyBinding var0) {
      return var0.pressed;
   }

   public _cq/* $FF was: 0cq*/() {
      super(legWohDgx7("֭֙֎֎֨֊ֆ"), 0bV.Player);
      0bC[] var10001 = new 0bC[11788 ^ -8669 ^ 29081 ^ -32329];
      var10001[11619 ^ -20923 ^ 11382 ^ -20656] = this.speed;
      this.addSetting(var10001);
   }

   private static boolean l6QHzhxYzj(EntityPlayerSP var0) {
      return var0.onGround;
   }

   private static Minecraft FABfNro1DN() {
      return mc;
   }

   private static double hWXELLQt9F(EntityPlayerSP var0) {
      return var0.posX;
   }

   private static WorldClient IidoO4DLIa(Minecraft var0) {
      return var0.world;
   }

   private static double Lgl62wGyTw(EntityPlayerSP var0) {
      return var0.posY;
   }

   private static EntityPlayerSP VyTQQS3nYS() {
      return Minecraft.player;
   }

   private static float Skc4tNL9q9(EntityPlayerSP var0) {
      return var0.rotationYaw;
   }

   private static PlayerCapabilities ECBkfOMaIv(EntityPlayerSP var0) {
      return var0.capabilities;
   }

   private static void _ePkriAnD4/* $FF was: 6ePkriAnD4*/(0cq var0, double var1) {
      var0.z = var1;
   }

   public void onEnable() {
      super.onEnable();
      2FQA1cQrMe();
      if (4imM7GPYH2(FXwYSUJ0kF())) {
         this.toggle();
      }

      fyFB0EaHVO();
      9Q9mSV5OlX(this, hWXELLQt9F(3i1pO7ndET()));
      s61QGDnYBq();
      OWWNV2o7hP(this, Lgl62wGyTw(BaW9JWnRTN()));
      95OFLNZa7G();
      6ePkriAnD4(this, 6tbKF3Q7kR(eaX7y4vH4B()));
      WorldClient var10002 = TfWh7t67Q1(wgdE6v5vSc());
      gmRKLgVbis();
      EntityOtherPlayerMP ent = new EntityOtherPlayerMP(var10002, xDN4i4jQSZ().getGameProfile());
      dLQnWNWjob();
      rOrmiPEeLB(ent, X2TQInoYbG(bylGZ2Jevw()));
      Nlyn41zQd0();
      vrOa2y24PM(ent, yZb7CP7sne(TI02UwMBhS()));
      SYzhVRV4vH();
      ent.setHealth(JGcF4vLSTs().getHealth());
      double var10001 = U9p3XcvOpM(this);
      H4wvNwjmkT();
      double var2 = GVqT6eZmFl(lyzV23jdkB().getEntityBoundingBox());
      double var10003 = T6aVTOyDOC(this);
      QLefLRLcuR();
      float var10004 = Skc4tNL9q9(jAtRY6xxIM());
      TQBD9W9tTb();
      ent.setPositionAndRotation(var10001, var2, var10003, var10004, Ka67xyxVHK(rpSQXCbRXm()));
      2etQ076g6l();
      ZTUOAneioY(ent, Q2fBKGNFJ6(ZNDwLFTby5()));
      tNEGJ0SS3M(LoalWY795O()).addEntityToWorld(-952 ^ -13119 ^ 7320 ^ -11282, ent);
   }

   private static Minecraft _5OFLNZa7G/* $FF was: 95OFLNZa7G*/() {
      return mc;
   }

   private static Minecraft ODDejJOTuC() {
      return mc;
   }

   private static Minecraft _Gh3rKk11Y/* $FF was: 4Gh3rKk11Y*/() {
      return mc;
   }

   private static EntityPlayerSP QYe2F2y62z() {
      return Minecraft.player;
   }

   @0X
   public void onPreMotion(0K e) {
      ODDejJOTuC();
      if (glUnOmQtsz() != null && GVMn1gzXre(HhMvoTp6l9()) != null) {
         Tzb3Rl4iBF();
         PcJ6spmN5R(37JevIieM6(), Double.longBitsToDouble(-8808647222752880777L ^ -8808647222752880777L));
         yl9iYc4OIb();
         if (KX71NLqljN(i4nSZEjGyQ(zPSS9iLaIo()))) {
            QTLUBsy6ia();
            DiLeCKAFvl(L1kTEYcqDB(), (double)Jgr29E9kue(ZaAdBAqFpG(this)));
         }

         vTO0SLeAmB();
         if (iLRLeqStAI(jfVgGNF1Fe(WF6Q9njJ6A()))) {
            taaUqmWdan();
            rLGfSAoNdH(v2SeooDbt7(), (double)(-ENmXtQloiw(q1jbd7nIBx(this))));
         }

         nQYjXQH1Tv();
         iuIpADhsJ7(YyjYwPQlTK(), (boolean)(5326 ^ -15539 ^ 17855 ^ -28099));
         0eo.setSpeed((double)qOAlPintIB(TVt0NXwKn6(this)));
      }
   }

   private static void PcJ6spmN5R(EntityPlayerSP var0, double var1) {
      var0.motionY = var1;
   }

   private static EntityPlayerSP lyzV23jdkB() {
      return Minecraft.player;
   }

   private static void DiLeCKAFvl(EntityPlayerSP var0, double var1) {
      var0.motionY = var1;
   }

   private static 0bz ZaAdBAqFpG(0cq var0) {
      return var0.speed;
   }

   private static WorldClient tNEGJ0SS3M(Minecraft var0) {
      return var0.world;
   }

   private static EntityPlayerSP JGcF4vLSTs() {
      return Minecraft.player;
   }

   private static Minecraft _FQA1cQrMe/* $FF was: 2FQA1cQrMe*/() {
      return mc;
   }

   private static EntityPlayerSP PWEDbnIsAM() {
      return Minecraft.player;
   }

   private static boolean iVwSiyoo2Y(EntityPlayerSP var0) {
      return var0.isDead;
   }

   private static EntityPlayerSP e164j67TUY() {
      return Minecraft.player;
   }

   private static EntityPlayerSP TI02UwMBhS() {
      return Minecraft.player;
   }

   private static EntityPlayerSP BaW9JWnRTN() {
      return Minecraft.player;
   }

   private static Minecraft w6NHDGbegb() {
      return mc;
   }

   private static 0bz TVt0NXwKn6(0cq var0) {
      return var0.speed;
   }

   private static Minecraft nQYjXQH1Tv() {
      return mc;
   }

   private static void iuIpADhsJ7(EntityPlayerSP var0, boolean var1) {
      var0.noClip = var1;
   }

   private static void OWWNV2o7hP(0cq var0, double var1) {
      var0.y = var1;
   }

   private static KeyBinding jfVgGNF1Fe(GameSettings var0) {
      return var0.keyBindSneak;
   }

   private static Minecraft eOSt7HTgRQ() {
      return mc;
   }

   private static Minecraft _etQ076g6l/* $FF was: 2etQ076g6l*/() {
      return mc;
   }

   private static void A7T9nAKZbu(PlayerCapabilities var0, boolean var1) {
      var0.isFlying = var1;
   }

   private static WorldClient GVMn1gzXre(Minecraft var0) {
      return var0.world;
   }

   private static Minecraft Nlyn41zQd0() {
      return mc;
   }

   private static Minecraft H4wvNwjmkT() {
      return mc;
   }

   private static EntityPlayerSP L1kTEYcqDB() {
      return Minecraft.player;
   }

   private static double U9p3XcvOpM(0cq var0) {
      return var0.x;
   }

   private static EntityPlayerSP jAtRY6xxIM() {
      return Minecraft.player;
   }

   private static Minecraft _DJuA1LYRB/* $FF was: 1DJuA1LYRB*/() {
      return mc;
   }

   private static Minecraft s61QGDnYBq() {
      return mc;
   }

   private static WorldClient TfWh7t67Q1(Minecraft var0) {
      return var0.world;
   }

   private static Minecraft dLQnWNWjob() {
      return mc;
   }

   private static Minecraft yl9iYc4OIb() {
      return mc;
   }

   private static double tJSbljDXai(EntityPlayerSP var0) {
      return var0.posZ;
   }

   private static float ENmXtQloiw(0bz var0) {
      return var0.value;
   }

   private static Minecraft QLefLRLcuR() {
      return mc;
   }

   private static double wL72jWjUSj(0cq var0) {
      return var0.y;
   }

   private static EntityPlayerSP RiK9oqolDh() {
      return Minecraft.player;
   }

   private static EntityPlayerSP FXwYSUJ0kF() {
      return Minecraft.player;
   }

   private static double _tbKF3Q7kR/* $FF was: 6tbKF3Q7kR*/(EntityPlayerSP var0) {
      return var0.posZ;
   }

   private static EntityPlayerSP eaX7y4vH4B() {
      return Minecraft.player;
   }

   private static Container yZb7CP7sne(EntityPlayerSP var0) {
      return var0.inventoryContainer;
   }

   private static void rOrmiPEeLB(EntityOtherPlayerMP var0, InventoryPlayer var1) {
      var0.inventory = var1;
   }

   private static double GVqT6eZmFl(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static EntityPlayerSP glUnOmQtsz() {
      return Minecraft.player;
   }

   private static Minecraft vTO0SLeAmB() {
      return mc;
   }

   private static InventoryPlayer X2TQInoYbG(EntityPlayerSP var0) {
      return var0.inventory;
   }

   private static boolean iLRLeqStAI(KeyBinding var0) {
      return var0.pressed;
   }

   private static EntityPlayerSP _i1pO7ndET/* $FF was: 3i1pO7ndET*/() {
      return Minecraft.player;
   }

   private static Minecraft SYzhVRV4vH() {
      return mc;
   }

   private static Minecraft HhMvoTp6l9() {
      return mc;
   }

   private static Minecraft yTjenH78FB() {
      return mc;
   }

   private static Minecraft LoalWY795O() {
      return mc;
   }

   private static KeyBinding i4nSZEjGyQ(GameSettings var0) {
      return var0.keyBindJump;
   }

   private static void ZTUOAneioY(EntityOtherPlayerMP var0, float var1) {
      var0.rotationYawHead = var1;
   }

   private static double d5aJ2ToqOa(0cq var0) {
      return var0.z;
   }

   private static EntityPlayerSP xiVIYDOKyS() {
      return Minecraft.player;
   }

   private static Minecraft taaUqmWdan() {
      return mc;
   }

   private static double YPJTa1nDMX(EntityPlayerSP var0) {
      return var0.posX;
   }

   private static void _Q9mSV5OlX/* $FF was: 9Q9mSV5OlX*/(0cq var0, double var1) {
      var0.x = var1;
   }

   private static float Jgr29E9kue(0bz var0) {
      return var0.value;
   }

   private static void WQJjMvnu99(EntityPlayerSP var0, boolean var1) {
      var0.noClip = var1;
   }

   private static EntityPlayerSP bylGZ2Jevw() {
      return Minecraft.player;
   }

   private static EntityPlayerSP eWN2BVjSB2() {
      return Minecraft.player;
   }

   private static EntityPlayerSP YyjYwPQlTK() {
      return Minecraft.player;
   }

   private static double g66OaWaqN4(EntityPlayerSP var0) {
      return var0.posY;
   }

   private static Minecraft QTLUBsy6ia() {
      return mc;
   }

   private static Minecraft gmRKLgVbis() {
      return mc;
   }

   private static Minecraft iyGHoj8Sa2() {
      return mc;
   }

   private static void rLGfSAoNdH(EntityPlayerSP var0, double var1) {
      var0.motionY = var1;
   }

   private static EntityPlayerSP v2SeooDbt7() {
      return Minecraft.player;
   }

   private static GameSettings zPSS9iLaIo() {
      return Minecraft.gameSettings;
   }

   private static EntityPlayerSP xDN4i4jQSZ() {
      return Minecraft.player;
   }

   private static boolean _imM7GPYH2/* $FF was: 4imM7GPYH2*/(EntityPlayerSP var0) {
      return var0.isDead;
   }

   private static 0bz q1jbd7nIBx(0cq var0) {
      return var0.speed;
   }

   private static EntityPlayerSP AjNnd8zW1s() {
      return Minecraft.player;
   }

   private static Minecraft TQBD9W9tTb() {
      return mc;
   }

   private static float Ka67xyxVHK(EntityPlayerSP var0) {
      return var0.rotationPitch;
   }

   public void onDisable() {
      super.onDisable();
      RnYz9rjtJV();
      xiVIYDOKyS().setPosition(Nb9wgvHsI3(this), wL72jWjUSj(this), d5aJ2ToqOa(this));
      NetHandlerPlayClient var10000 = eOSt7HTgRQ().getConnection();
      djzpcETAsa();
      double var10003 = YPJTa1nDMX(eWN2BVjSB2());
      1DJuA1LYRB();
      double var10004 = g66OaWaqN4(VyTQQS3nYS()) + Double.longBitsToDouble(-3650971861435820166L ^ -949874078972001535L);
      4Gh3rKk11Y();
      double var10005 = tJSbljDXai(AjNnd8zW1s());
      FABfNro1DN();
      var10000.sendPacket(new CPacketPlayer.Position(var10003, var10004, var10005, l6QHzhxYzj(QYe2F2y62z())));
      w6NHDGbegb();
      A7T9nAKZbu(ECBkfOMaIv(e164j67TUY()), (boolean)(7577 ^ -10132 ^ 14223 ^ -3462));
      yTjenH78FB();
      WQJjMvnu99(PWEDbnIsAM(), (boolean)(12945 ^ -23330 ^ 22860 ^ -12541));
      IidoO4DLIa(iyGHoj8Sa2()).removeEntityFromWorld(-4741 ^ -825 ^ 24634 ^ -29063);
   }

   private static GameSettings WF6Q9njJ6A() {
      return Minecraft.gameSettings;
   }

   private static double Nb9wgvHsI3(0cq var0) {
      return var0.x;
   }
}
