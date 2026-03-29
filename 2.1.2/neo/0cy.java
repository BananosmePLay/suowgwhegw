package neo;

import java.awt.Color;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.opengl.GL11;

public class 0cy extends 0cB {
   public static 0bw color;
   public 0bv onlyplayers = new 0bv(NbleJJRAsa("ٔوٍغٌوɖَمضوٌَ"), (boolean)(16976 ^ -16026 ^ 31362 ^ -1611));

   private static boolean BsqWIHTqXT(0bv var0) {
      return var0.value;
   }

   private static double oWCb1kTbek(Entity var0) {
      return var0.posZ;
   }

   private static EntityPlayerSP eTj4HpAeql() {
      return Minecraft.player;
   }

   private static EntityPlayerSP Oe2lQDJS79() {
      return Minecraft.player;
   }

   private static int SA9MFStAD4(0bw var0) {
      return var0.color;
   }

   private static Minecraft Vvl3GzzigV() {
      return mc;
   }

   private static double QFN7O6hVlI(Entity var0) {
      return var0.lastTickPosZ;
   }

   private static double Ba6sBS8ebh(Vec3d var0) {
      return var0.x;
   }

   private static GameSettings to2rS9XTTg() {
      return Minecraft.gameSettings;
   }

   private static float _LHsWoVOWF/* $FF was: 0LHsWoVOWF*/(EntityPlayerSP var0) {
      return var0.rotationPitch;
   }

   private static Minecraft bRQyJZQZTO() {
      return mc;
   }

   static {
      color = new 0bw(NbleJJRAsa("ِلكش"), Color.WHITE.getRGB());
   }

   private static double _AtMXmgeYb/* $FF was: 3AtMXmgeYb*/(Vec3d var0) {
      return var0.z;
   }

   private static double xgnA87FOyJ(Entity var0) {
      return var0.lastTickPosY;
   }

   private static double _ySm22Thca/* $FF was: 6ySm22Thca*/(Vec3d var0) {
      return var0.y;
   }

   private static double NTdBtbl9DR() {
      return RenderManager.renderPosY;
   }

   private static Minecraft qAt3Jtn2Q2() {
      return mc;
   }

   private static WorldClient uXPVrXcB92(Minecraft var0) {
      return var0.world;
   }

   private static Minecraft oFENk8pLEb() {
      return mc;
   }

   private static double FwYZP9wXv3(Entity var0) {
      return var0.posY;
   }

   private static Minecraft wdX9J6A4JI() {
      return mc;
   }

   public _cy/* $FF was: 0cy*/() {
      super(NbleJJRAsa("ȢȄȗȕȓȄȅ"), 0bV.Render);
      0bC[] var10001 = new 0bC[23547 ^ -11532 ^ 27997 ^ -7088];
      var10001[2164 ^ -30640 ^ 5876 ^ -26928] = this.onlyplayers;
      var10001[29101 ^ -2963 ^ 7156 ^ -25035] = color;
      this.addSetting(var10001);
   }

   @0X
   public void onEvent3D(0Q event) {
      Iterator var2 = GmbvyPjbJt(uXPVrXcB92(SBGpXyM7kk())).iterator();

      while(true) {
         Entity entity;
         do {
            do {
               if (!var2.hasNext()) {
                  return;
               }

               entity = (Entity)var2.next();
               DwVl19uxnm();
            } while(entity == wz92JYlxgb());
         } while(BsqWIHTqXT(RVhbngaSOO(this)) && !(entity instanceof EntityPlayer));

         qAt3Jtn2Q2();
         wKsLkHervg(to2rS9XTTg(), (boolean)(18342 ^ -6922 ^ 28221 ^ -12947));
         double var10000 = ut7FLiMdFd(entity) + (cV16no3YBb(entity) - IzlBigFo5r(entity)) * (double)event.getPartialTicks();
         bRQyJZQZTO().getRenderManager();
         double x = var10000 - 7yGeAaobBt();
         var10000 = o1YbvivjlM(entity) + (FwYZP9wXv3(entity) - xgnA87FOyJ(entity)) * (double)event.getPartialTicks();
         nD6NCqQn6z().getRenderManager();
         double y = var10000 - NTdBtbl9DR() - Double.longBitsToDouble(6412686334364779671L ^ 7425996250523141271L);
         var10000 = aKvkpSVBt3(entity) + (oWCb1kTbek(entity) - QFN7O6hVlI(entity)) * (double)event.getPartialTicks();
         wdX9J6A4JI().getRenderManager();
         double z = var10000 - tnOytJwYvR();
         GlStateManager.blendFunc(7119 ^ -6159 ^ 19255 ^ -19445, 29080 ^ -8309 ^ 7595 ^ -20293);
         GL11.glEnable(15365 ^ -24718 ^ 32684 ^ -10439);
         GL11.glEnable(19871 ^ -19562 ^ 10402 ^ -8821);
         GlStateManager.glLineWidth(Float.intBitsToFloat(15837 ^ '\ue4fe' ^ 6631 ^ 1295277018 ^ 13484 ^ '譓' ^ 28797 ^ 1924420764));
         GL11.glDisable(32639 ^ -28645 ^ 19664 ^ -20907);
         GL11.glDisable(29409 ^ -34906 ^ '\uf2d6' ^ -800);
         GlStateManager.depthMask((boolean)(28240 ^ -13835 ^ 17621 ^ -7312));
         0ew.glColor(new Color(SA9MFStAD4(OBCWIOjncY())));
         GlStateManager.glBegin(11335 ^ -10509 ^ 29798 ^ -28975);
         Vec3d var11 = new Vec3d(Double.longBitsToDouble(-7759416149533619195L ^ -7759416149533619195L), Double.longBitsToDouble(-4205619955080507911L ^ -4205619955080507911L), Double.longBitsToDouble(1071438696938623314L ^ 3543914892365025618L));
         oFENk8pLEb();
         var11 = var11.rotatePitch((float)(-Math.toRadians((double)0LHsWoVOWF(Oe2lQDJS79()))));
         Vvl3GzzigV();
         Vec3d vec = var11.rotateYaw((float)(-Math.toRadians((double)QJLVqgjYir(FueJ8yhSna()))));
         float var12 = (float)Ba6sBS8ebh(vec);
         di7PSaAru2();
         GlStateManager.glVertex3f(var12, (float)((double)eTj4HpAeql().getEyeHeight() + 6ySm22Thca(vec)), (float)3AtMXmgeYb(vec));
         GlStateManager.glVertex3f((float)x, (float)(y + Double.longBitsToDouble(-8053429576053170470L ^ -5778714387307018432L)), (float)z);
         GlStateManager.glEnd();
         GL11.glEnable(16715 ^ -23675 ^ 31284 ^ -27365);
         GL11.glDisable(5696 ^ -19653 ^ 26402 ^ -13959);
         GL11.glEnable(15559 ^ -9412 ^ 24921 ^ -29229);
         GlStateManager.depthMask((boolean)(22329 ^ -22500 ^ 22383 ^ -22453));
         GL11.glEnable(13670 ^ -24813 ^ 6548 ^ -18429);
         GlStateManager.resetColor();
      }
   }

   private static EntityPlayerSP FueJ8yhSna() {
      return Minecraft.player;
   }

   private static Minecraft di7PSaAru2() {
      return mc;
   }

   private static Minecraft SBGpXyM7kk() {
      return mc;
   }

   private static Minecraft DwVl19uxnm() {
      return mc;
   }

   private static double _yGeAaobBt/* $FF was: 7yGeAaobBt*/() {
      return RenderManager.renderPosX;
   }

   private static List GmbvyPjbJt(WorldClient var0) {
      return var0.loadedEntityList;
   }

   private static Minecraft nD6NCqQn6z() {
      return mc;
   }

   private static double cV16no3YBb(Entity var0) {
      return var0.posX;
   }

   private static double IzlBigFo5r(Entity var0) {
      return var0.lastTickPosX;
   }

   private static void wKsLkHervg(GameSettings var0, boolean var1) {
      var0.viewBobbing = var1;
   }

   private static 0bv RVhbngaSOO(0cy var0) {
      return var0.onlyplayers;
   }

   private static double o1YbvivjlM(Entity var0) {
      return var0.lastTickPosY;
   }

   private static EntityPlayerSP wz92JYlxgb() {
      return Minecraft.player;
   }

   private static float QJLVqgjYir(EntityPlayerSP var0) {
      return var0.rotationYaw;
   }

   private static double aKvkpSVBt3(Entity var0) {
      return var0.lastTickPosZ;
   }

   private static double tnOytJwYvR() {
      return RenderManager.renderPosZ;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String NbleJJRAsa(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 30705 ^ -9623 ^ 12117 ^ -32051; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 31162 ^ -15496 ^ 5715 ^ -20761));
      }

      return var1.toString();
   }

   private static double ut7FLiMdFd(Entity var0) {
      return var0.lastTickPosX;
   }

   private static 0bw OBCWIOjncY() {
      return color;
   }
}
