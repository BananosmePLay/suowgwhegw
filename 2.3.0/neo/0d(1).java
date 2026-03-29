package neo;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Queues;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import org.apache.commons.lang3.Validate;

public class 0d implements IThreadListener, Wh {
   public RayTraceResult objectMouseOver;
   public final 0a bot;
   public final Queue<FutureTask<?>> scheduledTasks = Queues.newArrayDeque();
   public int rightClickDelayTimer;
   private static String _ _;

   private static 0a MGdABKQFTA(0d var0) {
      return var0.bot;
   }

   public void rightClickMouse() {
      lyOd4qkjYn(this, 6413 ^ -497 ^ 12329 ^ -10449);
      if (!BFeTO8sjHK(9CarjJ9eLc(this)).isRowingBoat()) {
         EnumHand[] var1 = EnumHand.values();
         int var2 = var1.length;

         for(int var3 = 22023 ^ -16928 ^ 29556 ^ -26477; var3 < var2; ++var3) {
            EnumHand d = var1[var3];
            Qy c = iSrVAqBbYS(zqrSIJVSVq(this)).getHeldItem(d);
            if (hqR9L1t071(this) != null) {
               switch (OY4BVgnjY2()[5W9FcJSmWK(RiJ6tejygU(this)).ordinal()]) {
                  case 1:
                     if (1SBqbJFOlG(VeebsO7EJT(this)).interactWithEntity(Lxy9WMblOT(9OZmwy4Dp9(this)), pVteApVCxz(this), d) == pMDQgOBeQ3()) {
                        return;
                     }

                     if (jW9Bq28Fr2(bhobfmYFtk(this)).interactWithEntity(dYqL5VkUUi(covTfv4BJ4(this)), d) == uFTzQirS20()) {
                        return;
                     }
                     break;
                  case 2:
                     BlockPos b = f1eqWTQDLh(this).getBlockPos();
                     if (Y9sFzHOah1(GKBEjZFBmJ(this)).getBlockState(b).getMaterial() != YmD31plWky()) {
                        EnumActionResult a = wVl2Imqyni(bpj6FByFhn(this)).processRightClickBlock(b, rzIi2wLSCJ(wqA824DpYE(this)), O06WFBddVV(BTqWZwFdnF(this)), d);
                        if (a == akAqTfEyoq()) {
                           nLnzbnNNrk(6yF6lYoiXn(this)).swingArm(d);
                           return;
                        }
                     }
               }
            }

            if (!c.isEmpty()) {
               kdrGfv9RFq(0GE42Yxjkn(this)).processRightClick(d);
            }
         }
      }

   }

   private static 0a GgeA1nAbNz(0d var0) {
      return var0.bot;
   }

   private static 0a _yF6lYoiXn/* $FF was: 6yF6lYoiXn*/(0d var0) {
      return var0.bot;
   }

   private static 0f Et0eeYsWAV(0a var0) {
      return var0.player;
   }

   private static 0a A6QVBJv7Mj(0d var0) {
      return var0.bot;
   }

   private static void KWAFXOZHwM(0f var0, 0h var1) {
      var0.movementInput = var1;
   }

   private static 0f nLnzbnNNrk(0a var0) {
      return var0.player;
   }

   private static RayTraceResult K4Q4GVN6vc(0d var0) {
      return var0.objectMouseOver;
   }

   private static 0a Ate5QXQIe6(0d var0) {
      return var0.bot;
   }

   private static 0a SJZd5TQvnp(0d var0) {
      return var0.bot;
   }

   private static 0a qrk4CSDdTF(0d var0) {
      return var0.bot;
   }

   private static 0a iG4YleaPJy(0d var0) {
      return var0.bot;
   }

   public void setDimensionAndSpawnPlayer(int a) {
      7IwIyrTB4d(4YqKCIya1q(this)).setInitialSpawnLocation();
      FUNgPJFqTA(xRbx2yiQAT(this)).removeAllEntities();
      int b = 4863 ^ -20193 ^ 24344 ^ -776;
      String c = null;
      if (gFg07OvV4j(aVKDTezTkb(this)) != null) {
         b = 3S2FrtLF7v(NAaeiDSvje(this)).getEntityId();
         exeGUhS289(6QbL6aDExV(this)).removeEntity(L796dfrEFW(DDdOnen0gn(this)));
         c = bdVyoCDl2B(Lw3I29WRVR(this)).getServerBrand();
      }

      dW5VqW08mQ(8VyKtkVkex(this), new 0f(xFnJJGn4VS(this)));
      rLXoSEAxqy(qrk4CSDdTF(this)).getDataManager().setEntryValues((List)Objects.requireNonNull(KG2d6KiDqG(TSnPoiDixI(this)).getDataManager().getAll()));
      ztjgFF71Bo(L3gjlBGZ5S(TxdrOgsB2t(this)), a);
      7kFlSSJQjQ(95Avl52qx1(this)).preparePlayerToSpawn();
      jeHVYU9kK8(GgeA1nAbNz(this)).setServerBrand(c == null ? GqFGoOaFMT("") : c);
      WlugheYSLN(nQeJFaWBGr(this)).spawnEntity(gQCr5GZSai(gAyq1o6i22(this)));
      VgYrgVK4cJ(jOjJKfGlLQ(Vs2O7oQgd9(this)), Float.intBitsToFloat(4857 ^ '靥' ^ 10127 ^ 148792932 ^ 119508 ^ 110707 ^ 5679 ^ -873811713));
      jUAS4fE8Lr(tSPPDnAEiV(j1iFSb1Dr9(this)), new 0h(yURBZMVtV5(Lv14OYoQRE(this))));
      C7UiCt6vN3(h2n8UbkLEX(this)).setEntityId(b);
      GrO4SAhGIB(iG4YleaPJy(this)).setPlayerCapabilities(Et0eeYsWAV(fjjOyjXVzT(this)));
      Begt7Uavz9(z8CiDhDbDX(this)).setReducedDebug(wKkLKoTLlr(Ym79XTEQDb(this)).hasReducedDebug());
   }

   private static double _YDr0jqjs7/* $FF was: 5YDr0jqjs7*/(Vec3d var0) {
      return var0.x;
   }

   private static 0g yURBZMVtV5(0a var0) {
      return var0.keyboard;
   }

   private static EnumHand SaQZ2NjqhL() {
      return EnumHand.MAIN_HAND;
   }

   private static 0a VeebsO7EJT(0d var0) {
      return var0.bot;
   }

   private static void o5c7NOPtsA(0a var0, 0f var1) {
      var0.player = var1;
   }

   private static RayTraceResult pVteApVCxz(0d var0) {
      return var0.objectMouseOver;
   }

   public static void runTask(FutureTask<?> a) {
      try {
         a.run();
         a.get();
      } catch (Exception var2) {
      }

   }

   private static hM YmD31plWky() {
      return hM.AIR;
   }

   private static 0g kEwc2ociiZ(0a var0) {
      return var0.keyboard;
   }

   private static void jUAS4fE8Lr(0f var0, 0h var1) {
      var0.movementInput = var1;
   }

   private static hM iACJcqPfbq() {
      return hM.AIR;
   }

   private static 0a h2n8UbkLEX(0d var0) {
      return var0.bot;
   }

   private static RayTraceResult _g7pBf5lpE/* $FF was: 6g7pBf5lpE*/(0d var0) {
      return var0.objectMouseOver;
   }

   private static RayTraceResult yQoCNXxyWF(0d var0) {
      return var0.objectMouseOver;
   }

   private static 0a OOj4qdWMSo(0d var0) {
      return var0.bot;
   }

   private static 0bo _m8LjoStY9/* $FF was: 2m8LjoStY9*/(0a var0) {
      return var0.world;
   }

   private static double NYQOaqISAx(Vec3d var0) {
      return var0.z;
   }

   private static 0f JBgY41NYYP(0a var0) {
      return var0.player;
   }

   private static 0f Begt7Uavz9(0a var0) {
      return var0.player;
   }

   private static 0a SFZjrd47OY(0d var0) {
      return var0.bot;
   }

   private static 0f rLXoSEAxqy(0a var0) {
      return var0.player;
   }

   private static 0f lrSPddQJVK(0a var0) {
      return var0.player;
   }

   private static 0i IyjBOsT1Wc(0a var0) {
      return var0.controller;
   }

   private static 0a _qvgvB1Qg0/* $FF was: 6qvgvB1Qg0*/(0d var0) {
      return var0.bot;
   }

   private static 0f iLbTWYAoIl(0a var0) {
      return var0.player;
   }

   public void runTickKeyboard() {
      if (ai410yq6rG(this) > 0) {
         R712McF62d(this, ePUNb489JI(this) - (24567 ^ -32364 ^ 26578 ^ -18000));
      }

      this.getMouseOver();
      if (jeYq4WTxc4(kEwc2ociiZ(ogLNhntKAG(this)))) {
         this.leftClickMouse();
      } else if (JSvOTQveaX(d0yQhzemr0(KnVyjehuO2(this))) && O7rjn5L6VT(this) == 0) {
         this.rightClickMouse();
      }

      this.sendClickBlockToController(xvDA2DQPWg(eXSHWr9qbw(SCtICAutDB(this))));
   }

   private static 0i jW9Bq28Fr2(0a var0) {
      return var0.controller;
   }

   private static 0f PU5q9JpoYj(0a var0) {
      return var0.player;
   }

   private static Vec3d GFOHBSbdqY(RayTraceResult var0) {
      return var0.hitVec;
   }

   private static 0a TSnPoiDixI(0d var0) {
      return var0.bot;
   }

   private static 0f bdVyoCDl2B(0a var0) {
      return var0.player;
   }

   private static int[] _QsiLHqzco/* $FF was: 1QsiLHqzco*/() {
      return 0c.$SwitchMap$net$minecraft$util$math$RayTraceResult$Type;
   }

   private static Ig dYqL5VkUUi(RayTraceResult var0) {
      return var0.entityHit;
   }

   private static Queue Qn7JJeETBg(0d var0) {
      return var0.scheduledTasks;
   }

   private static RayTraceResult covTfv4BJ4(0d var0) {
      return var0.objectMouseOver;
   }

   private static 0a kGUsE6x7qJ(0d var0) {
      return var0.bot;
   }

   private static RayTraceResult.Type _W9FcJSmWK/* $FF was: 5W9FcJSmWK*/(RayTraceResult var0) {
      return var0.typeOfHit;
   }

   private static 0a BsgwSGLttb(0d var0) {
      return var0.bot;
   }

   public void leftClickMouse() {
      if (yQoCNXxyWF(this) != null) {
         if (!GFynJtcQQ1(mfvajgSTR9(this)).isRowingBoat()) {
            switch (1QsiLHqzco()[24NXOgYDvV(6g7pBf5lpE(this)).ordinal()]) {
               case 1:
                  1MGkIifeOQ(SdnrGGYJG2(this)).attackEntity(gFJBFtYAD0(ynGDXoeSwl(this)));
                  break;
               case 2:
                  BlockPos a = LsFCeFgkop(this).getBlockPos();
                  if (bqITF8wJfh(MGdABKQFTA(this)).getBlockState(a).getMaterial() != Dtfb44OTQd()) {
                     TGh2jWrJ0q(xr2aXkF2lO(this)).clickBlock(a, vdIq6Ml8Q6(T1ftKK61Xg(this)));
                     break;
                  }
               case 3:
                  PAu0VNYVIS(SFZjrd47OY(this)).resetCooldown();
            }

            lrSPddQJVK(BaYXiCq7Cj(this)).swingArm(armDnrLrBz());
         }

      }
   }

   private static RayTraceResult Dr5Sl6KSAN(0d var0) {
      return var0.objectMouseOver;
   }

   private static int O7rjn5L6VT(0d var0) {
      return var0.rightClickDelayTimer;
   }

   private static 0bo FUNgPJFqTA(0a var0) {
      return var0.world;
   }

   private static RayTraceResult _OZmwy4Dp9/* $FF was: 9OZmwy4Dp9*/(0d var0) {
      return var0.objectMouseOver;
   }

   private void sendClickBlockToController(boolean b) {
      if (!leWnYz8qFk(LG7uriU7y4(this)).isHandActive()) {
         if (b && K4Q4GVN6vc(this) != null && C8VOgjdAj8(sF7LaLAMPp(this)) == KfZKIQ1xsG()) {
            BlockPos a = d7e1wCg5JB(this).getBlockPos();
            if (2m8LjoStY9(bsF6NUdNyi(this)).getBlockState(a).getMaterial() != iACJcqPfbq() && s7fv2ajlEw(YRGO6L6SoQ(this)).onPlayerDamageBlock(a, Gp6TATLv7Q(nIA7B2vvMZ(this)))) {
               IS1Vn9Ha7n(9kOCU4kAvj(this)).swingArm(SaQZ2NjqhL());
            }
         } else {
            bYZVon6u6r(IaYzt2SgLC(this)).resetBlockRemoving();
         }
      }

   }

   private static 0a Lw3I29WRVR(0d var0) {
      return var0.bot;
   }

   private static 0f IS1Vn9Ha7n(0a var0) {
      return var0.player;
   }

   private static 0i kdrGfv9RFq(0a var0) {
      return var0.controller;
   }

   private static 0f wKkLKoTLlr(0a var0) {
      return var0.player;
   }

   private static void aTpWddvuMg(0d var0, RayTraceResult var1) {
      var0.objectMouseOver = var1;
   }

   private static 0a gAyq1o6i22(0d var0) {
      return var0.bot;
   }

   private static RayTraceResult sF7LaLAMPp(0d var0) {
      return var0.objectMouseOver;
   }

   private static double sNmh4QCBn4(Vec3d var0) {
      return var0.y;
   }

   private static 0a Ym79XTEQDb(0d var0) {
      return var0.bot;
   }

   private static 0a _QbL6aDExV/* $FF was: 6QbL6aDExV*/(0d var0) {
      return var0.bot;
   }

   private static 0f KG2d6KiDqG(0a var0) {
      return var0.player;
   }

   private static Ig Lxy9WMblOT(RayTraceResult var0) {
      return var0.entityHit;
   }

   private static RayTraceResult.Type _4NXOgYDvV/* $FF was: 24NXOgYDvV*/(RayTraceResult var0) {
      return var0.typeOfHit;
   }

   private static 0a IdSjkodFUY(0d var0) {
      return var0.bot;
   }

   private static 0a mfvajgSTR9(0d var0) {
      return var0.bot;
   }

   private static void _v9tOr8g7O/* $FF was: 6v9tOr8g7O*/(0a var0, 0bo var1) {
      var0.world = var1;
   }

   private static 0g d0yQhzemr0(0a var0) {
      return var0.keyboard;
   }

   private static 0a aVKDTezTkb(0d var0) {
      return var0.bot;
   }

   private static 0f gFg07OvV4j(0a var0) {
      return var0.player;
   }

   private static RayTraceResult ynGDXoeSwl(0d var0) {
      return var0.objectMouseOver;
   }

   private static 0a _YqKCIya1q/* $FF was: 4YqKCIya1q*/(0d var0) {
      return var0.bot;
   }

   private static int[] OY4BVgnjY2() {
      return 0c.$SwitchMap$net$minecraft$util$math$RayTraceResult$Type;
   }

   private static 0a IaYzt2SgLC(0d var0) {
      return var0.bot;
   }

   private static EnumHand armDnrLrBz() {
      return EnumHand.MAIN_HAND;
   }

   private static RayTraceResult BTqWZwFdnF(0d var0) {
      return var0.objectMouseOver;
   }

   public void execTasks() {
      synchronized(Fj4WUZlK4K(this)) {
         while(!Qn7JJeETBg(this).isEmpty()) {
            runTask((FutureTask)0W7PvnSIiP(this).poll());
         }

      }
   }

   private static void ztjgFF71Bo(0f var0, int var1) {
      var0.dimension = var1;
   }

   private static 0a pMwTVTgHS6(0d var0) {
      return var0.bot;
   }

   private static 0f GFynJtcQQ1(0a var0) {
      return var0.player;
   }

   private static 0a TxdrOgsB2t(0d var0) {
      return var0.bot;
   }

   public boolean isCallingFromMinecraftThread() {
      return (boolean)(2136 ^ -15921 ^ 11642 ^ -6932);
   }

   private static RayTraceResult d7e1wCg5JB(0d var0) {
      return var0.objectMouseOver;
   }

   private static 0a Lv14OYoQRE(0d var0) {
      return var0.bot;
   }

   private static 0a xFnJJGn4VS(0d var0) {
      return var0.bot;
   }

   private static boolean jeYq4WTxc4(0g var0) {
      return var0.keyBindAttack;
   }

   private static boolean JSvOTQveaX(0g var0) {
      return var0.keyBindUseItem;
   }

   private static 0a _JJNxP7pKK/* $FF was: 1JJNxP7pKK*/(0d var0) {
      return var0.bot;
   }

   private static void _lWlMBs6X4/* $FF was: 5lWlMBs6X4*/(0a var0, 0i var1) {
      var0.controller = var1;
   }

   private static void dW5VqW08mQ(0a var0, 0f var1) {
      var0.player = var1;
   }

   private static Vec3d O06WFBddVV(RayTraceResult var0) {
      return var0.hitVec;
   }

   private static 0g sfJGLJJwzf(0a var0) {
      return var0.keyboard;
   }

   private static 0bl a7nYqWLqEa(0a var0) {
      return var0.connection;
   }

   private static RayTraceResult nIA7B2vvMZ(0d var0) {
      return var0.objectMouseOver;
   }

   private static 0f BFeTO8sjHK(0a var0) {
      return var0.player;
   }

   private static 0f Fe8LeXiT91(0a var0) {
      return var0.player;
   }

   private static 0a _CarjJ9eLc/* $FF was: 9CarjJ9eLc*/(0d var0) {
      return var0.bot;
   }

   private static 0a xr2aXkF2lO(0d var0) {
      return var0.bot;
   }

   private static 0bo HwnE5xsyso(0a var0) {
      return var0.world;
   }

   private static 0a DU7i9o6MJb(0d var0) {
      return var0.bot;
   }

   private static 0bo exeGUhS289(0a var0) {
      return var0.world;
   }

   private static void G0cVUnA1YN(0d var0, RayTraceResult var1) {
      var0.objectMouseOver = var1;
   }

   private static RayTraceResult LsFCeFgkop(0d var0) {
      return var0.objectMouseOver;
   }

   private static 0a NAaeiDSvje(0d var0) {
      return var0.bot;
   }

   private static 0bo Y9sFzHOah1(0a var0) {
      return var0.world;
   }

   private static double DF982ClDm5(Vec3d var0) {
      return var0.x;
   }

   private static 0f NbrnThnRbr(0a var0) {
      return var0.player;
   }

   private static 0a _5Avl52qx1/* $FF was: 95Avl52qx1*/(0d var0) {
      return var0.bot;
   }

   private static 0a j79p72LCLj(0d var0) {
      return var0.bot;
   }

   private static 0f PWIYeyiEAp(0a var0) {
      return var0.player;
   }

   private static 0f Gjj8A9imBh(0a var0) {
      return var0.player;
   }

   private static 0a SdnrGGYJG2(0d var0) {
      return var0.bot;
   }

   private static 0a j1iFSb1Dr9(0d var0) {
      return var0.bot;
   }

   private static 0a SCtICAutDB(0d var0) {
      return var0.bot;
   }

   private static RayTraceResult hqR9L1t071(0d var0) {
      return var0.objectMouseOver;
   }

   public void loadWorld(0bo b) {
      if (b == null) {
         0bl a = a7nYqWLqEa(SJZd5TQvnp(this));
         if (a != null) {
            a.cleanup();
         }

         5lWlMBs6X4(r22dFYwlW4(this), (0i)null);
      }

      6v9tOr8g7O(BsgwSGLttb(this), b);
      if (JBgY41NYYP(OOj4qdWMSo(this)) == null) {
         o5c7NOPtsA(tHPa8a2FNB(this), new 0f(ru4bYSFgQl(this)));
         hBlqr2lHeL(PWIYeyiEAp(pMwTVTgHS6(this)), Float.intBitsToFloat('練' ^ '\uef00' ^ 15555 ^ 2136626090 ^ '켃' ^ '팁' ^ 8617 ^ -1133624236));
      }

      if (b != null) {
         Gjj8A9imBh(PQoeqZNuDS(this)).preparePlayerToSpawn();
         gkrNBkS9vk(A6QVBJv7Mj(this)).spawnEntity(iLbTWYAoIl(CI9tqjpzS7(this)));
         KWAFXOZHwM(PU5q9JpoYj(Gwdsq5gwaF(this)), new 0h(sfJGLJJwzf(kGUsE6x7qJ(this))));
         HV9lfVPvsF(VWiYmwvn2J(this)).setPlayerCapabilities(xRNiqY2tQY(Ate5QXQIe6(this)));
      }

   }

   private static 0bo gkrNBkS9vk(0a var0) {
      return var0.world;
   }

   private static 0f PAu0VNYVIS(0a var0) {
      return var0.player;
   }

   private static 0a ru4bYSFgQl(0d var0) {
      return var0.bot;
   }

   private static 0a _kOCU4kAvj/* $FF was: 9kOCU4kAvj*/(0d var0) {
      return var0.bot;
   }

   private static 0i _SBqbJFOlG/* $FF was: 1SBqbJFOlG*/(0a var0) {
      return var0.controller;
   }

   private static 0f _S2FrtLF7v/* $FF was: 3S2FrtLF7v*/(0a var0) {
      return var0.player;
   }

   private static 0i OOdpojGeV1(0a var0) {
      return var0.controller;
   }

   private static int ePUNb489JI(0d var0) {
      return var0.rightClickDelayTimer;
   }

   private static void lyOd4qkjYn(0d var0, int var1) {
      var0.rightClickDelayTimer = var1;
   }

   private static Ig gFJBFtYAD0(RayTraceResult var0) {
      return var0.entityHit;
   }

   private static 0a ZDg7bleK22(0d var0) {
      return var0.bot;
   }

   private static 0a tHPa8a2FNB(0d var0) {
      return var0.bot;
   }

   private static 0i TGh2jWrJ0q(0a var0) {
      return var0.controller;
   }

   private static 0f NYVdVPS9x2(0a var0) {
      return var0.player;
   }

   private static boolean xvDA2DQPWg(0g var0) {
      return var0.keyBindAttack;
   }

   private static 0f L796dfrEFW(0a var0) {
      return var0.player;
   }

   private static 0a YRGO6L6SoQ(0d var0) {
      return var0.bot;
   }

   private static void R712McF62d(0d var0, int var1) {
      var0.rightClickDelayTimer = var1;
   }

   private static 0a _GE42Yxjkn/* $FF was: 0GE42Yxjkn*/(0d var0) {
      return var0.bot;
   }

   private static Queue _W7PvnSIiP/* $FF was: 0W7PvnSIiP*/(0d var0) {
      return var0.scheduledTasks;
   }

   private static 0a Gwdsq5gwaF(0d var0) {
      return var0.bot;
   }

   private static 0i bYZVon6u6r(0a var0) {
      return var0.controller;
   }

   private static 0a bsF6NUdNyi(0d var0) {
      return var0.bot;
   }

   private static 0f C7UiCt6vN3(0a var0) {
      return var0.player;
   }

   private static RayTraceResult T1ftKK61Xg(0d var0) {
      return var0.objectMouseOver;
   }

   private static 0a z8CiDhDbDX(0d var0) {
      return var0.bot;
   }

   private static int ai410yq6rG(0d var0) {
      return var0.rightClickDelayTimer;
   }

   private static 0f iKFRYoTyQn(0a var0) {
      return var0.player;
   }

   private static 0f jOjJKfGlLQ(0a var0) {
      return var0.player;
   }

   private static 0g eXSHWr9qbw(0a var0) {
      return var0.keyboard;
   }

   private static 0a _T1s26ntbo/* $FF was: 0T1s26ntbo*/(0d var0) {
      return var0.bot;
   }

   private static 0a r22dFYwlW4(0d var0) {
      return var0.bot;
   }

   private static 0a fjjOyjXVzT(0d var0) {
      return var0.bot;
   }

   private static 0f tSPPDnAEiV(0a var0) {
      return var0.player;
   }

   private static EnumFacing Gp6TATLv7Q(RayTraceResult var0) {
      return var0.sideHit;
   }

   private static EnumFacing rzIi2wLSCJ(RayTraceResult var0) {
      return var0.sideHit;
   }

   public void addServerTypeToSnooper(Wm a) {
   }

   private static RayTraceResult.Type KfZKIQ1xsG() {
      return RayTraceResult.Type.BLOCK;
   }

   public <V> ListenableFuture<V> addScheduledTask(Callable<V> b) {
      Validate.notNull(b);

      try {
         return Futures.immediateFuture(b.call());
      } catch (Exception var3) {
         Exception a = var3;
         return Futures.immediateFailedCheckedFuture(a);
      }
   }

   private static 0a KnVyjehuO2(0d var0) {
      return var0.bot;
   }

   private static 0i GrO4SAhGIB(0a var0) {
      return var0.controller;
   }

   private static void VgYrgVK4cJ(0f var0, float var1) {
      var0.rotationYaw = var1;
   }

   private static 0a ogLNhntKAG(0d var0) {
      return var0.bot;
   }

   private static 0a nQeJFaWBGr(0d var0) {
      return var0.bot;
   }

   private static RayTraceResult q3rysxBI8u(0d var0) {
      return var0.objectMouseOver;
   }

   private static 0f _kFlSSJQjQ/* $FF was: 7kFlSSJQjQ*/(0a var0) {
      return var0.player;
   }

   private static 0bo _IwIyrTB4d/* $FF was: 7IwIyrTB4d*/(0a var0) {
      return var0.world;
   }

   private static 0a VWiYmwvn2J(0d var0) {
      return var0.bot;
   }

   private static RayTraceResult ddUODOSTne(0d var0) {
      return var0.objectMouseOver;
   }

   private static RayTraceResult.Type zrccSFqppn() {
      return RayTraceResult.Type.MISS;
   }

   private static 0f xRNiqY2tQY(0a var0) {
      return var0.player;
   }

   private static 0a ovBaWLeBv7(0d var0) {
      return var0.bot;
   }

   private static 0a Vs2O7oQgd9(0d var0) {
      return var0.bot;
   }

   public boolean isSnooperEnabled() {
      return (boolean)(10044 ^ -2331 ^ 1564 ^ -10299);
   }

   private static 0a PQoeqZNuDS(0d var0) {
      return var0.bot;
   }

   private static void GOz7TnRkug(0d var0, RayTraceResult var1) {
      var0.objectMouseOver = var1;
   }

   private static 0a BaYXiCq7Cj(0d var0) {
      return var0.bot;
   }

   private static RayTraceResult RiJ6tejygU(0d var0) {
      return var0.objectMouseOver;
   }

   public ListenableFuture<Object> addScheduledTask(Runnable a) {
      Validate.notNull(a);
      return this.addScheduledTask(Executors.callable(a));
   }

   private static 0a _VX9gxeJKH/* $FF was: 7VX9gxeJKH*/(0d var0) {
      return var0.bot;
   }

   private static double IbNNtYorwl(Vec3d var0) {
      return var0.y;
   }

   private static 0bo WlugheYSLN(0a var0) {
      return var0.world;
   }

   private static 0i _MGkIifeOQ/* $FF was: 1MGkIifeOQ*/(0a var0) {
      return var0.controller;
   }

   private static 0a mF4VlYIABN(0d var0) {
      return var0.bot;
   }

   private static 0a xRbx2yiQAT(0d var0) {
      return var0.bot;
   }

   private static 0f GDviLUi4Qw(0a var0) {
      return var0.player;
   }

   private static EnumActionResult uFTzQirS20() {
      return EnumActionResult.SUCCESS;
   }

   private static Vec3d _AUvgGndt4/* $FF was: 7AUvgGndt4*/(RayTraceResult var0) {
      return var0.hitVec;
   }

   private static 0i wVl2Imqyni(0a var0) {
      return var0.controller;
   }

   private static 0f leWnYz8qFk(0a var0) {
      return var0.player;
   }

   private static 0a LG7uriU7y4(0d var0) {
      return var0.bot;
   }

   private static 0f gQCr5GZSai(0a var0) {
      return var0.player;
   }

   private static 0a CI9tqjpzS7(0d var0) {
      return var0.bot;
   }

   private static 0f YyoWbbHE9z(0a var0) {
      return var0.player;
   }

   private static RayTraceResult wqA824DpYE(0d var0) {
      return var0.objectMouseOver;
   }

   private static 0a bhobfmYFtk(0d var0) {
      return var0.bot;
   }

   private static RayTraceResult.Type C8VOgjdAj8(RayTraceResult var0) {
      return var0.typeOfHit;
   }

   private static 0a GKBEjZFBmJ(0d var0) {
      return var0.bot;
   }

   private static EnumActionResult akAqTfEyoq() {
      return EnumActionResult.SUCCESS;
   }

   private static void hBlqr2lHeL(0f var0, float var1) {
      var0.rotationYaw = var1;
   }

   private static 0a DDdOnen0gn(0d var0) {
      return var0.bot;
   }

   private static Queue Fj4WUZlK4K(0d var0) {
      return var0.scheduledTasks;
   }

   private static 0bo Dlgbe4o93S(0a var0) {
      return var0.world;
   }

   public void addServerStatsToSnooper(Wm a) {
   }

   private static 0bo bqITF8wJfh(0a var0) {
      return var0.world;
   }

   private static 0a _VyKtkVkex/* $FF was: 8VyKtkVkex*/(0d var0) {
      return var0.bot;
   }

   private static 0i s7fv2ajlEw(0a var0) {
      return var0.controller;
   }

   private static Predicate FKYBOjAfhf() {
      return EntitySelectors.NOT_SPECTATING;
   }

   private static RayTraceResult f1eqWTQDLh(0d var0) {
      return var0.objectMouseOver;
   }

   private static 0f iSrVAqBbYS(0a var0) {
      return var0.player;
   }

   private static 0f jeHVYU9kK8(0a var0) {
      return var0.player;
   }

   private static 0f RheYAv7JvQ(0a var0) {
      return var0.player;
   }

   private static Vec3d t1NPLnn8IH(RayTraceResult var0) {
      return var0.hitVec;
   }

   private static double df1eEliYwS(Vec3d var0) {
      return var0.z;
   }

   private static Vec3d gbObzvdNy0(RayTraceResult var0) {
      return var0.hitVec;
   }

   public _d/* $FF was: 0d*/(0a a) {
      this.bot = a;
   }

   private static Vec3d a4HhMfhKjZ(RayTraceResult var0) {
      return var0.hitVec;
   }

   private static 0a bpj6FByFhn(0d var0) {
      return var0.bot;
   }

   private static 0a zqrSIJVSVq(0d var0) {
      return var0.bot;
   }

   private static hM Dtfb44OTQd() {
      return hM.AIR;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String GqFGoOaFMT(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 21426 ^ -18483 ^ 10605 ^ -13038; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 21629 ^ -25129 ^ 28598 ^ -24004));
      }

      return var1.toString();
   }

   private static 0i HV9lfVPvsF(0a var0) {
      return var0.controller;
   }

   public void getMouseOver() {
      try {
         if (RheYAv7JvQ(6qvgvB1Qg0(this)) == null) {
            return;
         }

         if (HwnE5xsyso(IdSjkodFUY(this)) != null) {
            double e = (double)OOdpojGeV1(mF4VlYIABN(this)).getBlockReachDistance();
            aTpWddvuMg(this, Fe8LeXiT91(ovBaWLeBv7(this)).rayTrace(e, Float.intBitsToFloat(106821 ^ 125890 ^ 23582 ^ -1585255479 ^ 119448 ^ 130523 ^ 9665 ^ -1643972654)));
            Vec3d f = YyoWbbHE9z(7VX9gxeJKH(this)).getPositionEyes(Float.intBitsToFloat('\udd5f' ^ '냜' ^ 15671 ^ -1429660280 ^ 7093 ^ '\uaafa' ^ '\ud9fc' ^ -1790368369));
            boolean g = 18818 ^ -25980 ^ 3345 ^ -8681;
            double h = e;
            if (IyjBOsT1Wc(9FeQOAOrf2(this)).extendedReach()) {
               h = Double.longBitsToDouble(-1787459403121073764L ^ -6401397221362146916L);
               e = h;
            } else if (e > Double.longBitsToDouble(738534121154726697L ^ 5347968339768429353L)) {
               g = 14999 ^ -29023 ^ 8111 ^ -21608;
            }

            if (Dr5Sl6KSAN(this) != null) {
               h = t1NPLnn8IH(q3rysxBI8u(this)).distanceTo(f);
            }

            Vec3d i = NbrnThnRbr(0T1s26ntbo(this)).getLook(Float.intBitsToFloat('꒬' ^ 17135 ^ '졕' ^ 501684783 ^ '놺' ^ 110373 ^ 'ﺒ' ^ 577164340));
            Vec3d j = f.add(DF982ClDm5(i) * e, sNmh4QCBn4(i) * e, NYQOaqISAx(i) * e);
            Ig n = null;
            Vec3d k = null;
            List<Ig> l = Dlgbe4o93S(ZDg7bleK22(this)).getEntitiesInAABBexcluding(NYVdVPS9x2(DU7i9o6MJb(this)), GDviLUi4Qw(1JJNxP7pKK(this)).getEntityBoundingBox().grow(5YDr0jqjs7(i) * e, IbNNtYorwl(i) * e, df1eEliYwS(i) * e).grow(Double.longBitsToDouble(7620828738702796385L ^ 6211202055335831137L), Double.longBitsToDouble(7276511303810091345L ^ 6560438963058182481L), Double.longBitsToDouble(6228715749266397535L ^ 7602313635614398815L)), Predicates.and(FKYBOjAfhf(), new 0b(this)));
            double m = h;
            Iterator var14 = l.iterator();

            while(true) {
               while(var14.hasNext()) {
                  Ig d = (Ig)var14.next();
                  AxisAlignedBB b = d.getEntityBoundingBox().grow((double)d.getCollisionBorderSize());
                  RayTraceResult c = b.calculateIntercept(f, j);
                  if (b.contains(f)) {
                     if (m >= Double.longBitsToDouble(5946225860854456790L ^ 5946225860854456790L)) {
                        n = d;
                        k = c == null ? f : 7AUvgGndt4(c);
                        m = Double.longBitsToDouble(-3467400637874674532L ^ -3467400637874674532L);
                     }
                  } else if (c != null) {
                     double a = f.distanceTo(gbObzvdNy0(c));
                     if (a < m || m == Double.longBitsToDouble(1040387674217648170L ^ 1040387674217648170L)) {
                        if (d.getLowestRidingEntity() == iKFRYoTyQn(j79p72LCLj(this)).getLowestRidingEntity()) {
                           if (m == Double.longBitsToDouble(-8938507857681897371L ^ -8938507857681897371L)) {
                              n = d;
                              k = GFOHBSbdqY(c);
                           }
                        } else {
                           n = d;
                           k = a4HhMfhKjZ(c);
                           m = a;
                        }
                     }
                  }
               }

               if (n != null && g != 0 && f.distanceTo(k) > Double.longBitsToDouble(482578084273450524L ^ 5096515902514523676L)) {
                  n = null;
                  G0cVUnA1YN(this, new RayTraceResult(zrccSFqppn(), k, (EnumFacing)null, new BlockPos(k)));
               }

               if (n != null && (m < h || ddUODOSTne(this) == null)) {
                  GOz7TnRkug(this, new RayTraceResult(n, k));
               }
               break;
            }
         }
      } catch (Exception var20) {
      }

   }

   private static 0a _FeQOAOrf2/* $FF was: 9FeQOAOrf2*/(0d var0) {
      return var0.bot;
   }

   private static 0f L3gjlBGZ5S(0a var0) {
      return var0.player;
   }

   private static EnumFacing vdIq6Ml8Q6(RayTraceResult var0) {
      return var0.sideHit;
   }

   private static EnumActionResult pMDQgOBeQ3() {
      return EnumActionResult.SUCCESS;
   }
}
