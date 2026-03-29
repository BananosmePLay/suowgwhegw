package neo;

import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

public class 0i {
   public final 0a bot;
   public int currentPlayerItem;
   public int blockHitDelay;
   public boolean isHittingBlock;
   public BlockPos currentBlock = new BlockPos(-10755 ^ -2277 ^ 26107 ^ -18206, -14567 ^ -21003 ^ 14105 ^ -24054, -16513 ^ -15087 ^ 7632 ^ -26559);
   public float curBlockDamageMP;
   public final 0bl connection;
   public bbb gameType;
   public Qy currentItemHittingBlock;
   private static String _ _;

   private static 0a _WD9QXKOg9/* $FF was: 6WD9QXKOg9*/(0i var0) {
      return var0.bot;
   }

   private static 0f iSjiE0v2wn(0a var0) {
      return var0.player;
   }

   private static 0bl rzQ2hjZJNv(0i var0) {
      return var0.connection;
   }

   private static Container jWwJCqw5XV(0f var0) {
      return var0.openContainer;
   }

   private static 0a lM9ipA7WOW(0i var0) {
      return var0.bot;
   }

   private static 0a WV7rkzIEw7(0i var0) {
      return var0.bot;
   }

   private static 0a _OtMOqAODF/* $FF was: 0OtMOqAODF*/(0i var0) {
      return var0.bot;
   }

   private static double axq99kVg1b(Ig var0) {
      return var0.posX;
   }

   private static Qy Bj2KvKaNx1(0i var0) {
      return var0.currentItemHittingBlock;
   }

   private static bbb s4W6OkF2VI(0i var0) {
      return var0.gameType;
   }

   private static 0f X5YVij6xTg(0a var0) {
      return var0.player;
   }

   private static 0f JDR8161ga1(0a var0) {
      return var0.player;
   }

   private static Qy _yArvfP527/* $FF was: 1yArvfP527*/(0i var0) {
      return var0.currentItemHittingBlock;
   }

   public boolean onPlayerDamageBlock(BlockPos b, EnumFacing c) {
      this.syncCurrentPlayItem();
      if (ovxrlvkgDQ(this) > 0) {
         oocrNF1JBf(this, Lkw0jqFlMl(this) - (7605 ^ -4319 ^ 6853 ^ -6064));
         return (boolean)(14086 ^ -20176 ^ 3169 ^ -30122);
      } else if (aXxaiNDBAO(this).isCreative() && qejSAIRfW4(scanhWoc2o(this)).getWorldBorder().contains(b)) {
         dlOjraTjke(this, 213 ^ -30376 ^ 23646 ^ -10794);
         Bgr9eGv1gB(this).sendPacket(new Tb(OO2JIelxMj(), b, c));
         this.clickBlockCreative(b, c);
         return (boolean)(4171 ^ -14264 ^ 8916 ^ -1322);
      } else if (this.isHittingPosition(b)) {
         in a = sSaawGDuPk(aTUDqkQQhB(this)).getBlockState(b);
         if (a.getMaterial() == j2VFqBewtV()) {
            4i2BZ5fbgs(this, (boolean)(15759 ^ -519 ^ 5752 ^ -10738));
            return (boolean)(16548 ^ -3298 ^ 643 ^ -20167);
         } else {
            hjkLASqO1L(this, OljXrIly2d(this) + a.getPlayerRelativeBlockHardness(gP5UVoveLp(QA1ejwGaet(this)), yclJWJSanI(rG7dsQDlyg(tGzisRDiKF(this))), b));
            if (DGabb6mnSS(this) >= Float.intBitsToFloat(102623 ^ 129759 ^ 23842 ^ 512411886 ^ 129545 ^ 115491 ^ 23997 ^ 554343259)) {
               yBURVxGFvk(this, (boolean)(1921 ^ -29033 ^ 17420 ^ -13030));
               lod04BhcBT(this).sendPacket(new Tb(R41bDxbUdy(), b, c));
               this.onPlayerDestroyBlock(b);
               ZSj3QpNoMY(this, Float.intBitsToFloat(30724 ^ 494995 ^ 10737 ^ 772522882 ^ '駪' ^ 493134 ^ '\ude43' ^ 772529667));
               rwgSQj9QnN(this, 8435 ^ -3189 ^ 7393 ^ -12388);
            }

            DBSS6ESq2a(eGIIeSL22J(this)).sendBlockBreakProgress(SZk1UmTjGa(6vGNRbeGCv(this)).getEntityId(), CdPsjrWPQh(this), (int)(SL6jRTmVvE(this) * Float.intBitsToFloat(2080275 ^ 2093282 ^ 15227 ^ -122716013 ^ 2068677 ^ 2072592 ^ 3549 ^ -1181759471)) - (9142 ^ -28922 ^ 20343 ^ -7226));
            return (boolean)(15760 ^ -25460 ^ 13654 ^ -27573);
         }
      } else {
         return this.clickBlock(b, c);
      }
   }

   private static 0a e7p0LfJFnr(0i var0) {
      return var0.bot;
   }

   private static 0a yVodkTbDjb(0i var0) {
      return var0.bot;
   }

   private static 0f VMLCq9ilQn(0a var0) {
      return var0.player;
   }

   private static int ovxrlvkgDQ(0i var0) {
      return var0.blockHitDelay;
   }

   private static bbb _KjFQy06Do/* $FF was: 2KjFQy06Do*/() {
      return bbb.SPECTATOR;
   }

   private static 0f cldsPJNHQs(0a var0) {
      return var0.player;
   }

   private static Vec3d hbTz5u7RXr(RayTraceResult var0) {
      return var0.hitVec;
   }

   private static 0a aS2DB8iQvT(0i var0) {
      return var0.bot;
   }

   private static bbb eSeSJG8Iba(0i var0) {
      return var0.gameType;
   }

   private static bbb _LnmeelGD8/* $FF was: 9LnmeelGD8*/() {
      return bbb.SPECTATOR;
   }

   public bbb getGameType() {
      return DUFnvSJNV6(this);
   }

   private static 0f _F2exoAWy5/* $FF was: 0F2exoAWy5*/(0a var0) {
      return var0.player;
   }

   private static boolean g9SSOOE2oX(0i var0) {
      return var0.isHittingBlock;
   }

   private static EnumActionResult rreODreLuB() {
      return EnumActionResult.PASS;
   }

   private static 0f oEC4bzyJvO(0a var0) {
      return var0.player;
   }

   private static 0a y7b12nwFiF(0i var0) {
      return var0.bot;
   }

   private static EnumActionResult DxQTQx90wp() {
      return EnumActionResult.FAIL;
   }

   private static Ta R41bDxbUdy() {
      return Ta.STOP_DESTROY_BLOCK;
   }

   public void setGameType(bbb a) {
      aAEMOu7ybT(this, a);
      6WeycLAgOn(this).configurePlayerCapabilities(w7ow26rqRZ(bbU5ysjUHw(AWKPdJjc4a(this))));
   }

   private static 0a _QHnA0I2kT/* $FF was: 0QHnA0I2kT*/(0i var0) {
      return var0.bot;
   }

   public void windowClick(int d, int e, ClickType f) {
      try {
         short a = rvzrN5hxRm(WbQ0M1GenY(3K2oT0uBQv(this))).getNextTransactionID(Y3XDG8phja(BOfuEqQMvL(VXfIcXaNBg(this))));
         Qy b = 3fWl9DcT8D(cldsPJNHQs(gzCBtmKJqP(this))).slotClick(d, e, f, WYQt1lP41n(aS2DB8iQvT(this)));
         kj2dDGHr29(this).sendPacket(new SF(0sTTO5su3D(jWwJCqw5XV(xNnd8dZGIa(aWs1P7F82S(this)))), d, e, f, b, a));
      } catch (Exception var6) {
         Exception c = var6;
         c.printStackTrace();
      }

   }

   private static MJ JTUwLbSggJ(0f var0) {
      return var0.inventory;
   }

   private static int _rMAAgT6BY/* $FF was: 6rMAAgT6BY*/(MJ var0) {
      return var0.currentItem;
   }

   private static double tQtlJSWO4D(Ig var0) {
      return var0.posZ;
   }

   private static 0bl _qe67FyrKN/* $FF was: 4qe67FyrKN*/(0i var0) {
      return var0.connection;
   }

   private static BlockPos LwlGMrsF5Z(0i var0) {
      return var0.currentBlock;
   }

   private static 0a _JhAS7SDl6/* $FF was: 9JhAS7SDl6*/(0i var0) {
      return var0.bot;
   }

   private static 0f tVjuDFWMDy(0a var0) {
      return var0.player;
   }

   private static 0a ErYnxAXJoW(0i var0) {
      return var0.bot;
   }

   private static 0f _hyPaq97Rv/* $FF was: 1hyPaq97Rv*/(0a var0) {
      return var0.player;
   }

   private static Container rvzrN5hxRm(0f var0) {
      return var0.openContainer;
   }

   private static void hjkLASqO1L(0i var0, float var1) {
      var0.curBlockDamageMP = var1;
   }

   public void clickBlockCreative(BlockPos a, EnumFacing b) {
      if (!98td6iiILP(JaGA59PLBG(this)).extinguishFire(gSOM7ON2qQ(yKTF6MZNU1(this)), a, b)) {
         this.onPlayerDestroyBlock(a);
      }

   }

   private static Ta WhtJ1eDKmo() {
      return Ta.ABORT_DESTROY_BLOCK;
   }

   private static 0a gzCBtmKJqP(0i var0) {
      return var0.bot;
   }

   private static void aSWIQ70uBO(0i var0, float var1) {
      var0.curBlockDamageMP = var1;
   }

   private static 0a MqlgyN277g(0i var0) {
      return var0.bot;
   }

   private static 0a _GNGY0a7G0/* $FF was: 4GNGY0a7G0*/(0i var0) {
      return var0.bot;
   }

   private static 0f _gFKGDOVNM/* $FF was: 4gFKGDOVNM*/(0a var0) {
      return var0.player;
   }

   public void attackEntity(Ig a) {
      this.syncCurrentPlayItem();
      s9IfZL27o4(this).sendPacket(new Tp(a));
      if (s4W6OkF2VI(this) != M31VWgeHYP()) {
         ld1sEgTABi(y7b12nwFiF(this)).attackTargetEntityWithCurrentItem(a);
         Zol5bDq8Tx(yO1RXj1N4F(this)).resetCooldown();
      }

   }

   private static BlockPos CdPsjrWPQh(0i var0) {
      return var0.currentBlock;
   }

   public boolean extendedReach() {
      return wvnNVWaEFt(this).isCreative();
   }

   private static double d8fGISFtwl(Vec3d var0) {
      return var0.z;
   }

   private static Vec3d gOfgqvUQim(RayTraceResult var0) {
      return var0.hitVec;
   }

   private static 0a _iFJVh7GQl/* $FF was: 8iFJVh7GQl*/(0i var0) {
      return var0.bot;
   }

   private static 0f WbQ0M1GenY(0a var0) {
      return var0.player;
   }

   private static 0bl gHwmOT3TGe(0i var0) {
      return var0.connection;
   }

   private void syncCurrentPlayItem() {
      int a = 6rMAAgT6BY(JTUwLbSggJ(JDR8161ga1(e5gOeW6C2J(this))));
      if (a != YENaFysMLV(this)) {
         iWYJobBl0I(this, a);
         gDKoSeGJ4W(this).sendPacket(new SR(i4sPWi9im4(this)));
      }

   }

   private static bbb WroSvLGrYV(0i var0) {
      return var0.gameType;
   }

   private static 0a YyXCBA8Mty(0i var0) {
      return var0.bot;
   }

   private static ML w7ow26rqRZ(0f var0) {
      return var0.capabilities;
   }

   private static BlockPos QgSgp8Cj6R(0i var0) {
      return var0.currentBlock;
   }

   private static 0a _qIuJqpG4J/* $FF was: 7qIuJqpG4J*/(0i var0) {
      return var0.bot;
   }

   private static 0bo lYlq2DJw2Q(0a var0) {
      return var0.world;
   }

   private static 0f IWBqEMagfF(0a var0) {
      return var0.player;
   }

   private static 0a yL7Y0TseiQ(0i var0) {
      return var0.bot;
   }

   private static 0a fa5klse9bh(0i var0) {
      return var0.bot;
   }

   private static 0bo o80ytTIt4d(0a var0) {
      return var0.world;
   }

   private static 0f _VPobi4EOF/* $FF was: 1VPobi4EOF*/(0a var0) {
      return var0.player;
   }

   private static void EQ3nw4tZqG(0i var0, BlockPos var1) {
      var0.currentBlock = var1;
   }

   private static 0a xkt4gKnJiZ(0i var0) {
      return var0.bot;
   }

   private static void oocrNF1JBf(0i var0, int var1) {
      var0.blockHitDelay = var1;
   }

   private static 0a OTBISM05rB(0i var0) {
      return var0.bot;
   }

   private static 0bl Bgr9eGv1gB(0i var0) {
      return var0.connection;
   }

   private static 0f ijwLn1r29f(0a var0) {
      return var0.player;
   }

   private static 0a _O9pLHT2jL/* $FF was: 5O9pLHT2jL*/(0i var0) {
      return var0.bot;
   }

   private static 0bo gF4JoYOdYu(0a var0) {
      return var0.world;
   }

   private static 0bl s9IfZL27o4(0i var0) {
      return var0.connection;
   }

   private static void tcTCM1Dege(0i var0, int var1) {
      var0.blockHitDelay = var1;
   }

   private static 0a e5gOeW6C2J(0i var0) {
      return var0.bot;
   }

   private static 0a FW1DNmiTva(0i var0) {
      return var0.bot;
   }

   private static void dlOjraTjke(0i var0, int var1) {
      var0.blockHitDelay = var1;
   }

   private static double Sy1SvQa2qJ(Ig var0) {
      return var0.posY;
   }

   private static bbb o7XzdKZLWV(0i var0) {
      return var0.gameType;
   }

   private static double BYTaQg8AGW(Vec3d var0) {
      return var0.y;
   }

   private static 0a QA1ejwGaet(0i var0) {
      return var0.bot;
   }

   private static 0f w7lWiUCdV8(0a var0) {
      return var0.player;
   }

   private static 0a BVJdDz2Gvl(0i var0) {
      return var0.bot;
   }

   private static EnumFacing L7gqGFMY9O() {
      return EnumFacing.DOWN;
   }

   private static int i4sPWi9im4(0i var0) {
      return var0.currentPlayerItem;
   }

   private static 0a pENmTnPQA2(0i var0) {
      return var0.bot;
   }

   public EnumActionResult interactWithEntity(Ig a, EnumHand b) {
      if (a != null && a.getEntityId() != WLlTezTr2M(HFWbSniVp7(this)).getEntityId()) {
         this.syncCurrentPlayItem();
         lSTNgOe2rB(this).sendPacket(new Tp(a, b));
         return o7XzdKZLWV(this) == g1TwkqaPWS() ? cFar4RJ9jn() : WTGb4I6uOT(YyXCBA8Mty(this)).interactOn(a, b);
      } else {
         return 4iGQt6K1qW();
      }
   }

   private static bbb _eeuN2QfJT/* $FF was: 7eeuN2QfJT*/() {
      return bbb.SPECTATOR;
   }

   private static 0a eGIIeSL22J(0i var0) {
      return var0.bot;
   }

   private static 0f _ZNyWN4Vlv/* $FF was: 9ZNyWN4Vlv*/(0a var0) {
      return var0.player;
   }

   private static EnumActionResult bYCn3B5267() {
      return EnumActionResult.FAIL;
   }

   private static 0a _wmwQgqY8w/* $FF was: 2wmwQgqY8w*/(0i var0) {
      return var0.bot;
   }

   private static 0a aWs1P7F82S(0i var0) {
      return var0.bot;
   }

   private static 0a O5z6L6v8JD(0i var0) {
      return var0.bot;
   }

   private static ML Jd3o6qSzaJ(ME var0) {
      return var0.capabilities;
   }

   private static 0f Zol5bDq8Tx(0a var0) {
      return var0.player;
   }

   private static 0a VXfIcXaNBg(0i var0) {
      return var0.bot;
   }

   private static Qy Hdpjj2EV1t() {
      return Qy.EMPTY;
   }

   private static 0a aTUDqkQQhB(0i var0) {
      return var0.bot;
   }

   private static 0f JJrzKQfnYr(0a var0) {
      return var0.player;
   }

   private static 0a _d7bTYoWtK/* $FF was: 7d7bTYoWtK*/(0i var0) {
      return var0.bot;
   }

   private static 0a tGzisRDiKF(0i var0) {
      return var0.bot;
   }

   private static 0f sJ4FdGOkOS(0a var0) {
      return var0.player;
   }

   private static 0a oecOSfK9FA(0i var0) {
      return var0.bot;
   }

   public boolean isSpectator() {
      return (boolean)(bdNTPOtgql(this) == 9LnmeelGD8() ? 24390 ^ -16318 ^ 7921 ^ -32268 : 2703 ^ -26748 ^ 22784 ^ -15349);
   }

   private static 0f Q25Ye2riK9(0a var0) {
      return var0.player;
   }

   private static 0bl RUGN9TUngA(0i var0) {
      return var0.connection;
   }

   private static 0f rG7dsQDlyg(0a var0) {
      return var0.player;
   }

   private static 0f PFgYYtf3tr(0a var0) {
      return var0.player;
   }

   private static 0a idHcs1m1CR(0i var0) {
      return var0.bot;
   }

   private static 0a bavjevcujy(0i var0) {
      return var0.bot;
   }

   public boolean onPlayerDestroyBlock(BlockPos g) {
      if (7o7CuR0SiZ(this).hasLimitedInteractions()) {
         if (eSeSJG8Iba(this) == 7eeuN2QfJT()) {
            return (boolean)(18641 ^ -25258 ^ 32178 ^ -22475);
         }

         if (!IWBqEMagfF(VXnttntixw(this)).isAllowEdit()) {
            Qy a = 4gFKGDOVNM(lM9ipA7WOW(this)).getHeldItemMainhand();
            if (a.isEmpty()) {
               return (boolean)(22958 ^ -16250 ^ 10724 ^ -20276);
            }

            if (!a.canDestroy(JBYUg2kGW3(M4Tbv8YFRl(this)).getBlockState(g).getBlock())) {
               return (boolean)(15989 ^ -5102 ^ 13550 ^ -6519);
            }
         }
      }

      if (uKKpBui9ZZ(this).isCreative() && !djNW3HVWUL(NUAwLQ4ouJ(this)).getHeldItemMainhand().isEmpty() && 4FHAF1BJSQ(yVodkTbDjb(this)).getHeldItemMainhand().getItem() instanceof Qz) {
         return (boolean)(20123 ^ -6756 ^ 13655 ^ -25008);
      } else {
         bij d = aau8Kc2E0x(WV7rkzIEw7(this));
         in e = ((bij)d).getBlockState(g);
         co f = e.getBlock();
         if ((f instanceof da || f instanceof hh) && !w7lWiUCdV8(sVNJO1vky4(this)).canUseCommandBlock()) {
            return (boolean)(25000 ^ -5883 ^ 17976 ^ -12651);
         } else if (e.getMaterial() == lNGy7qiBYu()) {
            return (boolean)(518 ^ -10896 ^ 7561 ^ -13569);
         } else {
            ((bij)d).playEvent(23014 ^ -6977 ^ 882 ^ -17926, g, co.getStateId(e));
            f.onBlockHarvested(d, g, e, 1hyPaq97Rv(ErYnxAXJoW(this)));
            boolean c = ((bij)d).setBlockState(g, diDqjByJSV().getDefaultState(), 17369 ^ -25308 ^ 17642 ^ -26084);
            if (c) {
               f.onPlayerDestroy(d, g, e);
            }

            EQ3nw4tZqG(this, new BlockPos(ieJVyV37tf(this).getX(), -23142 ^ -20382 ^ 24723 ^ -30060, QgSgp8Cj6R(this).getZ()));
            if (!lmErKou8e4(this).isCreative()) {
               Qy b = pqDnoWYkTo(oecOSfK9FA(this)).getHeldItemMainhand();
               if (!b.isEmpty()) {
                  b.onBlockDestroyed(d, e, g, jt74x8DZq9(0OtMOqAODF(this)));
                  if (b.isEmpty()) {
                     VMLCq9ilQn(6WD9QXKOg9(this)).setHeldItem(QPKyggNT6U(), Hdpjj2EV1t());
                  }
               }
            }

            return c;
         }
      }
   }

   private static EnumActionResult _vVtLTkMqW/* $FF was: 0vVtLTkMqW*/() {
      return EnumActionResult.PASS;
   }

   private static 0bl MZJlBsaFZl(0i var0) {
      return var0.connection;
   }

   private static int Lkw0jqFlMl(0i var0) {
      return var0.blockHitDelay;
   }

   private static bbb wvnNVWaEFt(0i var0) {
      return var0.gameType;
   }

   private static 0a Fy9thuWtyF(0i var0) {
      return var0.bot;
   }

   private static 0f ld1sEgTABi(0a var0) {
      return var0.player;
   }

   private static BlockPos r5gA9sD140(0i var0) {
      return var0.currentBlock;
   }

   private static 0a _0SgstIwQ0/* $FF was: 10SgstIwQ0*/(0i var0) {
      return var0.bot;
   }

   private static bbb DUFnvSJNV6(0i var0) {
      return var0.gameType;
   }

   private static void nKZvak5IOq(0i var0, float var1) {
      var0.curBlockDamageMP = var1;
   }

   private static 0a uAetnhxgFK(0i var0) {
      return var0.bot;
   }

   private static 0f oEA3itrWM7(0a var0) {
      return var0.player;
   }

   private static bbb bdNTPOtgql(0i var0) {
      return var0.gameType;
   }

   private boolean isHittingPosition(BlockPos a) {
      Qy b = v6fFimrVze(O4GnRAAdIN(this)).getHeldItemMainhand();
      boolean c = NFWueHJSOV(this).isEmpty() && b.isEmpty() ? 27692 ^ -26359 ^ 19322 ^ -16802 : 6210 ^ -15493 ^ 24210 ^ -31317;
      if (!f8LBgF83TJ(this).isEmpty() && !b.isEmpty()) {
         c = b.getItem() != Bj2KvKaNx1(this).getItem() || !Qy.areItemStackTagsEqual(b, 1yArvfP527(this)) || !b.isItemStackDamageable() && b.getMetadata() != viviR4Ebqp(this).getMetadata() ? 15488 ^ -791 ^ 3831 ^ -12642 : 12490 ^ -17205 ^ 10667 ^ -23125;
      }

      return (boolean)(a.equals(bC5tQmg169(this)) && c != 0 ? 28006 ^ -28395 ^ 16258 ^ -15376 : 13625 ^ -21109 ^ 23326 ^ -15444);
   }

   private static 0f bbU5ysjUHw(0a var0) {
      return var0.player;
   }

   private static 0f xNnd8dZGIa(0a var0) {
      return var0.player;
   }

   private static void yBURVxGFvk(0i var0, boolean var1) {
      var0.isHittingBlock = var1;
   }

   private static 0bl UjyoLOyfC1(0i var0) {
      return var0.connection;
   }

   private static 0a hbFpu1lvrz(0i var0) {
      return var0.bot;
   }

   private static double YaKEyTllmw(Vec3d var0) {
      return var0.x;
   }

   private static 0f adNyvUuG4d(0a var0) {
      return var0.player;
   }

   private static float DGabb6mnSS(0i var0) {
      return var0.curBlockDamageMP;
   }

   private static 0f BeenWtrqnV(0a var0) {
      return var0.player;
   }

   private static hM j2VFqBewtV() {
      return hM.AIR;
   }

   private static Container _fWl9DcT8D/* $FF was: 3fWl9DcT8D*/(0f var0) {
      return var0.openContainer;
   }

   private static 0bl gDKoSeGJ4W(0i var0) {
      return var0.connection;
   }

   private static 0a _vGNRbeGCv/* $FF was: 6vGNRbeGCv*/(0i var0) {
      return var0.bot;
   }

   private static bbb YrtJyo4rLD(0i var0) {
      return var0.gameType;
   }

   private static 0bl zeu7JA9X15(0i var0) {
      return var0.connection;
   }

   private static void rwgSQj9QnN(0i var0, int var1) {
      var0.blockHitDelay = var1;
   }

   private static 0a _YlnW9JCOA/* $FF was: 5YlnW9JCOA*/(0i var0) {
      return var0.bot;
   }

   private static 0bo RoSLYl3GOW(0a var0) {
      return var0.world;
   }

   private static 0f ZyrCY4gltb(0a var0) {
      return var0.player;
   }

   private static 0f gP5UVoveLp(0a var0) {
      return var0.player;
   }

   private static bbb uKKpBui9ZZ(0i var0) {
      return var0.gameType;
   }

   private static Qy f8LBgF83TJ(0i var0) {
      return var0.currentItemHittingBlock;
   }

   private static bbb Ai3tgFv7TC(0i var0) {
      return var0.gameType;
   }

   private static 0a xnDInT9X2m(0i var0) {
      return var0.bot;
   }

   private static void iWYJobBl0I(0i var0, int var1) {
      var0.currentPlayerItem = var1;
   }

   private static 0a V7fSFScWyA(0i var0) {
      return var0.bot;
   }

   private static float OljXrIly2d(0i var0) {
      return var0.curBlockDamageMP;
   }

   public _i/* $FF was: 0i*/(0a a) {
      this.currentItemHittingBlock = Qy.EMPTY;
      this.bot = a;
      this.gameType = bbb.SURVIVAL;
      this.connection = a.connection;
   }

   private static 0a scanhWoc2o(0i var0) {
      return var0.bot;
   }

   private static void sgwUEWQxnI(0i var0, BlockPos var1) {
      var0.currentBlock = var1;
   }

   private static bbb _o7CuR0SiZ/* $FF was: 7o7CuR0SiZ*/(0i var0) {
      return var0.gameType;
   }

   private static 0f IjJimjm9An(0a var0) {
      return var0.player;
   }

   private static bbb _uT4iyImGh/* $FF was: 2uT4iyImGh*/(0i var0) {
      return var0.gameType;
   }

   private static 0a yO1RXj1N4F(0i var0) {
      return var0.bot;
   }

   private static double iinQ1Vrria(Vec3d var0) {
      return var0.x;
   }

   private static 0a M4Tbv8YFRl(0i var0) {
      return var0.bot;
   }

   private static 0bo latKXBjybM(0a var0) {
      return var0.world;
   }

   public boolean clickBlock(BlockPos d, EnumFacing e) {
      if (2uT4iyImGh(this).hasLimitedInteractions()) {
         if (AdAiMmlaC7(this) == 2KjFQy06Do()) {
            return (boolean)(21461 ^ -8055 ^ 21704 ^ -6252);
         }

         if (!ZyrCY4gltb(fa5klse9bh(this)).isAllowEdit()) {
            Qy a = G8132p8FgJ(MqlgyN277g(this)).getHeldItemMainhand();
            if (a.isEmpty()) {
               return (boolean)(22277 ^ -24459 ^ 5377 ^ -7567);
            }

            if (!a.canDestroy(FionB7i7vA(1RwUweNbQe(this)).getBlockState(d).getBlock())) {
               return (boolean)(26221 ^ -15852 ^ 24114 ^ -1461);
            }
         }
      }

      if (!lYlq2DJw2Q(O5z6L6v8JD(this)).getWorldBorder().contains(d)) {
         return (boolean)(20188 ^ -1364 ^ 5192 ^ -24520);
      } else {
         if (G2jSQfdygH(this).isCreative()) {
            zeu7JA9X15(this).sendPacket(new Tb(z6wbDQGijb(), d, e));
            this.clickBlockCreative(d, e);
            tcTCM1Dege(this, 3009 ^ -19710 ^ 3560 ^ -19154);
         } else if (!g9SSOOE2oX(this) || !this.isHittingPosition(d)) {
            if (vHPofDtJQ0(this)) {
               RUGN9TUngA(this).sendPacket(new Tb(WhtJ1eDKmo(), VL7Ot17SOx(this), e));
            }

            in b = RoSLYl3GOW(7d7bTYoWtK(this)).getBlockState(d);
            rzQ2hjZJNv(this).sendPacket(new Tb(goAQAi7NND(), d, e));
            boolean c = b.getMaterial() != vC7woGtUIU() ? 29507 ^ -29350 ^ 8614 ^ -8258 : 6524 ^ -6470 ^ 11457 ^ -11513;
            if (c != 0 && xxBa8FHhGu(this) == Float.intBitsToFloat(23310 ^ 236852 ^ 9801 ^ 846319653 ^ 23606 ^ 24951 ^ 1924 ^ 846334611)) {
               b.getBlock().onBlockClicked(pAfeqJ5Ajt(hbFpu1lvrz(this)), d, O9c1jLxavC(idHcs1m1CR(this)));
            }

            if (c != 0 && b.getPlayerRelativeBlockHardness(obfBJ9mJoD(bavjevcujy(this)), xepXODalgN(YFvnUBwTOg(7qIuJqpG4J(this))), d) >= Float.intBitsToFloat(8050 ^ 260020 ^ 2701 ^ -704517218 ^ 12382 ^ 239779 ^ 23970 ^ -377369462)) {
               this.onPlayerDestroyBlock(d);
            } else {
               Neg5JzhojO(this, (boolean)(4868 ^ -22428 ^ 7470 ^ -22961));
               sgwUEWQxnI(this, d);
               eKqx2fvfk4(this, PFgYYtf3tr(5O9pLHT2jL(this)).getHeldItemMainhand());
               aSWIQ70uBO(this, Float.intBitsToFloat(1680 ^ 234006 ^ 256941 ^ 1464188398 ^ 6954 ^ '鴊' ^ 4985 ^ 1464162204));
               latKXBjybM(xnDInT9X2m(this)).sendBlockBreakProgress(sJ4FdGOkOS(2wmwQgqY8w(this)).getEntityId(), r5gA9sD140(this), (int)(OMDtYcGgqW(this) * Float.intBitsToFloat(27367 ^ 27920 ^ 6685 ^ -921304160 ^ 29058 ^ 258700 ^ 15047 ^ -2009749629)) - (11108 ^ -7396 ^ 21937 ^ -25144));
            }
         }

         return (boolean)(20525 ^ -28933 ^ 28568 ^ -20145);
      }
   }

   private static 0bl B0VBSZjiD8(0i var0) {
      return var0.connection;
   }

   private static 0bo QqUwKJ6Yo7(0a var0) {
      return var0.world;
   }

   private static 0f gSOM7ON2qQ(0a var0) {
      return var0.player;
   }

   private static 0bl lod04BhcBT(0i var0) {
      return var0.connection;
   }

   private static EnumActionResult FaVoSyzciV() {
      return EnumActionResult.FAIL;
   }

   private static 0bl lSTNgOe2rB(0i var0) {
      return var0.connection;
   }

   private static BlockPos ieJVyV37tf(0i var0) {
      return var0.currentBlock;
   }

   private static EnumActionResult Jkd5bqqmuw() {
      return EnumActionResult.PASS;
   }

   private static bbb AdAiMmlaC7(0i var0) {
      return var0.gameType;
   }

   private static 0bo FionB7i7vA(0a var0) {
      return var0.world;
   }

   private static 0a IgZUHyaNjH(0i var0) {
      return var0.bot;
   }

   private static bbb eFy2bIeg8D(0i var0) {
      return var0.gameType;
   }

   private static 0f pqDnoWYkTo(0a var0) {
      return var0.player;
   }

   private static 0bo JBYUg2kGW3(0a var0) {
      return var0.world;
   }

   private static 0f WTGb4I6uOT(0a var0) {
      return var0.player;
   }

   private static bbb _WUlvGgNHi/* $FF was: 7WUlvGgNHi*/(0i var0) {
      return var0.gameType;
   }

   private static bij xepXODalgN(0f var0) {
      return var0.world;
   }

   private static bbb _WeycLAgOn/* $FF was: 6WeycLAgOn*/(0i var0) {
      return var0.gameType;
   }

   private static 0f e8b2qEZ97O(0a var0) {
      return var0.player;
   }

   private static bbb G2jSQfdygH(0i var0) {
      return var0.gameType;
   }

   private static boolean itInwYF1da(0i var0) {
      return var0.isHittingBlock;
   }

   private static bbb _qkweP89q2/* $FF was: 7qkweP89q2*/(0i var0) {
      return var0.gameType;
   }

   public void interactWithEntity(int a, EnumHand b) {
      if (a != 1VPobi4EOF(kFxjDSFDTV(this)).getEntityId()) {
         this.syncCurrentPlayItem();
         DxQ8CYaTvj(this).sendPacket(new Tp(a, b));
      }
   }

   private static 0a wYjcKOwaOZ(0i var0) {
      return var0.bot;
   }

   private static 0f obfBJ9mJoD(0a var0) {
      return var0.player;
   }

   private static bbb M31VWgeHYP() {
      return bbb.SPECTATOR;
   }

   private static bbb _Ny6CmW9l4/* $FF was: 5Ny6CmW9l4*/() {
      return bbb.SPECTATOR;
   }

   private static EnumActionResult hBdEFvrI9G() {
      return EnumActionResult.PASS;
   }

   private static 0bo PjoEh5DQPG(0a var0) {
      return var0.world;
   }

   private static 0a VXnttntixw(0i var0) {
      return var0.bot;
   }

   private static 0bo sSy4lr2oTo(0a var0) {
      return var0.world;
   }

   public void setPlayerCapabilities(ME a) {
      7WUlvGgNHi(this).configurePlayerCapabilities(Jd3o6qSzaJ(a));
   }

   private static 0a _K2oT0uBQv/* $FF was: 3K2oT0uBQv*/(0i var0) {
      return var0.bot;
   }

   private static double _BD2nj6xQM/* $FF was: 9BD2nj6xQM*/(Vec3d var0) {
      return var0.z;
   }

   private static Qy NFWueHJSOV(0i var0) {
      return var0.currentItemHittingBlock;
   }

   private static 0bo sSaawGDuPk(0a var0) {
      return var0.world;
   }

   private static bbb _dPlvAejsS/* $FF was: 1dPlvAejsS*/(0i var0) {
      return var0.gameType;
   }

   private static void _i2BZ5fbgs/* $FF was: 4i2BZ5fbgs*/(0i var0, boolean var1) {
      var0.isHittingBlock = var1;
   }

   private static MJ Y3XDG8phja(0f var0) {
      return var0.inventory;
   }

   private static 0a Si3TGr7SKj(0i var0) {
      return var0.bot;
   }

   private static 0a pQXENL3jNs(0i var0) {
      return var0.bot;
   }

   private static 0bo DBSS6ESq2a(0a var0) {
      return var0.world;
   }

   public EnumActionResult interactWithEntity(Ig a, RayTraceResult b, EnumHand c) {
      if (a != null && a.getEntityId() != adNyvUuG4d(x9dgAA5Djp(this)).getEntityId()) {
         this.syncCurrentPlayItem();
         Vec3d d = new Vec3d(iinQ1Vrria(AdK9ZGfoH9(b)) - axq99kVg1b(a), MqBGP7GytZ(hbTz5u7RXr(b)) - Sy1SvQa2qJ(a), 9BD2nj6xQM(gOfgqvUQim(b)) - tQtlJSWO4D(a));
         2r243AdeQu(this).sendPacket(new Tp(a, c, d));
         return 7qkweP89q2(this) == 5Ny6CmW9l4() ? K90X6Hcoov() : a.applyPlayerInteraction(ijwLn1r29f(9JhAS7SDl6(this)), d, c);
      } else {
         return bjWc2afWoj();
      }
   }

   private static Qy viviR4Ebqp(0i var0) {
      return var0.currentItemHittingBlock;
   }

   private static bbb UwMFbxSqgt() {
      return bbb.SPECTATOR;
   }

   private static 0f yKTWbdOA6C(0a var0) {
      return var0.player;
   }

   private static 0f lQF42iYFQj(0a var0) {
      return var0.player;
   }

   private static Ta z6wbDQGijb() {
      return Ta.START_DESTROY_BLOCK;
   }

   private static EnumActionResult K90X6Hcoov() {
      return EnumActionResult.PASS;
   }

   private static 0f G8132p8FgJ(0a var0) {
      return var0.player;
   }

   private static EnumActionResult cFar4RJ9jn() {
      return EnumActionResult.PASS;
   }

   private static 0f jt74x8DZq9(0a var0) {
      return var0.player;
   }

   private static bbb DvYmKD0oca() {
      return bbb.SPECTATOR;
   }

   private static void eKqx2fvfk4(0i var0, Qy var1) {
      var0.currentItemHittingBlock = var1;
   }

   private static bbb lmErKou8e4(0i var0) {
      return var0.gameType;
   }

   private static 0a DleYOBOxtf(0i var0) {
      return var0.bot;
   }

   private static float SL6jRTmVvE(0i var0) {
      return var0.curBlockDamageMP;
   }

   private static 0f SZk1UmTjGa(0a var0) {
      return var0.player;
   }

   private static 0a sVNJO1vky4(0i var0) {
      return var0.bot;
   }

   private static 0a O4GnRAAdIN(0i var0) {
      return var0.bot;
   }

   private static 0bl _r243AdeQu/* $FF was: 2r243AdeQu*/(0i var0) {
      return var0.connection;
   }

   private static Ta goAQAi7NND() {
      return Ta.START_DESTROY_BLOCK;
   }

   private static void ZSj3QpNoMY(0i var0, float var1) {
      var0.curBlockDamageMP = var1;
   }

   private static hM vC7woGtUIU() {
      return hM.AIR;
   }

   private static float xxBa8FHhGu(0i var0) {
      return var0.curBlockDamageMP;
   }

   public EnumActionResult processRightClickBlock(BlockPos g, EnumFacing h, Vec3d i, EnumHand j) {
      this.syncCurrentPlayItem();
      Qy k = lQF42iYFQj(Fy9thuWtyF(this)).getHeldItem(j);
      float l = (float)(YaKEyTllmw(i) - (double)g.getX());
      float m = (float)(BYTaQg8AGW(i) - (double)g.getY());
      float n = (float)(d8fGISFtwl(i) - (double)g.getZ());
      boolean o = 15492 ^ -7747 ^ 24777 ^ -16912;
      if (!QqUwKJ6Yo7(FW1DNmiTva(this)).getWorldBorder().contains(g)) {
         return bYCn3B5267();
      } else {
         if (1dPlvAejsS(this) != UwMFbxSqgt()) {
            in b = sSy4lr2oTo(Si3TGr7SKj(this)).getBlockState(g);
            if ((!9ZNyWN4Vlv(xkt4gKnJiZ(this)).isSneaking() || JJrzKQfnYr(e7p0LfJFnr(this)).getHeldItemMainhand().isEmpty() && e8b2qEZ97O(laWbETbHAq(this)).getHeldItemOffhand().isEmpty()) && b.getBlock().onBlockActivated(7Sqsvy4DQa(pENmTnPQA2(this)), g, b, QZjNdSWDLl(RQ7bFQI2AB(this)), j, h, l, m, n)) {
               o = 23295 ^ -17182 ^ 1546 ^ -8170;
            }

            if (o == 0 && k.getItem() instanceof OX) {
               OX a = (OX)k.getItem();
               if (!a.canPlaceBlockOnSide(qE2aT20GZ6(pQXENL3jNs(this)), g, h, oEC4bzyJvO(OTBISM05rB(this)), k)) {
                  return DxQTQx90wp();
               }
            }
         }

         B0VBSZjiD8(this).sendPacket(new Td(g, h, j, l, m, n));
         if (o == 0 && eFy2bIeg8D(this) != StCubGIQ7O()) {
            if (k.isEmpty()) {
               return hBdEFvrI9G();
            } else if (tVjuDFWMDy(DleYOBOxtf(this)).getCooldownTracker().hasCooldown(k.getItem())) {
               return 0vVtLTkMqW();
            } else {
               if (k.getItem() instanceof OX && !0F2exoAWy5(wYjcKOwaOZ(this)).canUseCommandBlock()) {
                  co c = ((OX)k.getItem()).getBlock();
                  if (c instanceof da || c instanceof hh) {
                     return FaVoSyzciV();
                  }
               }

               if (WroSvLGrYV(this).isCreative()) {
                  int d = k.getMetadata();
                  int e = k.getCount();
                  EnumActionResult f = k.onItemUse(iSjiE0v2wn(5YlnW9JCOA(this)), gF4JoYOdYu(BVJdDz2Gvl(this)), g, j, h, l, m, n);
                  k.setItemDamage(d);
                  k.setCount(e);
                  return f;
               } else {
                  return k.onItemUse(yKTWbdOA6C(XwFBgVFjFd(this)), PjoEh5DQPG(10SgstIwQ0(this)), g, j, h, l, m, n);
               }
            }
         } else {
            return dtLcyxmx47();
         }
      }
   }

   private static 0bo _Sqsvy4DQa/* $FF was: 7Sqsvy4DQa*/(0a var0) {
      return var0.world;
   }

   private static 0f WLlTezTr2M(0a var0) {
      return var0.player;
   }

   private static bbb StCubGIQ7O() {
      return bbb.SPECTATOR;
   }

   public void resetBlockRemoving() {
      if (itInwYF1da(this)) {
         MZJlBsaFZl(this).sendPacket(new Tb(wn1nXb4MMY(), oDKJdeqMzg(this), L7gqGFMY9O()));
         D7RJNYT0KA(this, (boolean)(25225 ^ -22056 ^ 20690 ^ -25725));
         nKZvak5IOq(this, Float.intBitsToFloat(31747 ^ 208767 ^ '\udfe9' ^ 956678069 ^ 28690 ^ 2318 ^ 31542 ^ 956707082));
         o80ytTIt4d(uAetnhxgFK(this)).sendBlockBreakProgress(oEA3itrWM7(yL7Y0TseiQ(this)).getEntityId(), LwlGMrsF5Z(this), -12297 ^ -6912 ^ 4698 ^ -14510);
         Q25Ye2riK9(V7fSFScWyA(this)).resetCooldown();
      }

   }

   private static EnumActionResult _iGQt6K1qW/* $FF was: 4iGQt6K1qW*/() {
      return EnumActionResult.FAIL;
   }

   private static 0a x9dgAA5Djp(0i var0) {
      return var0.bot;
   }

   private static 0a AWKPdJjc4a(0i var0) {
      return var0.bot;
   }

   private static int _sTTO5su3D/* $FF was: 0sTTO5su3D*/(Container var0) {
      return var0.windowId;
   }

   private static BlockPos bC5tQmg169(0i var0) {
      return var0.currentBlock;
   }

   private static 0a _RwUweNbQe/* $FF was: 1RwUweNbQe*/(0i var0) {
      return var0.bot;
   }

   private static bij yclJWJSanI(0f var0) {
      return var0.world;
   }

   private static Vec3d AdK9ZGfoH9(RayTraceResult var0) {
      return var0.hitVec;
   }

   private static 0f WYQt1lP41n(0a var0) {
      return var0.player;
   }

   private static 0f oG1FoipiVt(0a var0) {
      return var0.player;
   }

   private static 0bo pAfeqJ5Ajt(0a var0) {
      return var0.world;
   }

   private static bbb g1TwkqaPWS() {
      return bbb.SPECTATOR;
   }

   private static EnumActionResult dtLcyxmx47() {
      return EnumActionResult.SUCCESS;
   }

   private static 0a fOikFsaVI0(0i var0) {
      return var0.bot;
   }

   private static hM lNGy7qiBYu() {
      return hM.AIR;
   }

   private static 0a HFWbSniVp7(0i var0) {
      return var0.bot;
   }

   private static 0a NUAwLQ4ouJ(0i var0) {
      return var0.bot;
   }

   private static double MqBGP7GytZ(Vec3d var0) {
      return var0.y;
   }

   private static float OMDtYcGgqW(0i var0) {
      return var0.curBlockDamageMP;
   }

   private static EnumActionResult bjWc2afWoj() {
      return EnumActionResult.FAIL;
   }

   private static 0bo _8td6iiILP/* $FF was: 98td6iiILP*/(0a var0) {
      return var0.world;
   }

   private static BlockPos VL7Ot17SOx(0i var0) {
      return var0.currentBlock;
   }

   private static 0bo aau8Kc2E0x(0a var0) {
      return var0.world;
   }

   private static 0a ONgIl8b9hr(0i var0) {
      return var0.bot;
   }

   private static boolean vHPofDtJQ0(0i var0) {
      return var0.isHittingBlock;
   }

   private static 0f _FHAF1BJSQ/* $FF was: 4FHAF1BJSQ*/(0a var0) {
      return var0.player;
   }

   private static BlockPos oDKJdeqMzg(0i var0) {
      return var0.currentBlock;
   }

   private static int YENaFysMLV(0i var0) {
      return var0.currentPlayerItem;
   }

   private static EnumHand QPKyggNT6U() {
      return EnumHand.MAIN_HAND;
   }

   private static 0a JaGA59PLBG(0i var0) {
      return var0.bot;
   }

   private static 0a laWbETbHAq(0i var0) {
      return var0.bot;
   }

   private static 0a XwFBgVFjFd(0i var0) {
      return var0.bot;
   }

   private static void aAEMOu7ybT(0i var0, bbb var1) {
      var0.gameType = var1;
   }

   private static 0bl kj2dDGHr29(0i var0) {
      return var0.connection;
   }

   private static 0bl DxQ8CYaTvj(0i var0) {
      return var0.connection;
   }

   private static 0f a9gr5GopOu(0a var0) {
      return var0.player;
   }

   private static 0bo qejSAIRfW4(0a var0) {
      return var0.world;
   }

   private static void D7RJNYT0KA(0i var0, boolean var1) {
      var0.isHittingBlock = var1;
   }

   private static 0f YFvnUBwTOg(0a var0) {
      return var0.player;
   }

   private static Ta wn1nXb4MMY() {
      return Ta.ABORT_DESTROY_BLOCK;
   }

   private static 0a yKTF6MZNU1(0i var0) {
      return var0.bot;
   }

   private static Ta OO2JIelxMj() {
      return Ta.START_DESTROY_BLOCK;
   }

   private static co diDqjByJSV() {
      return Nk.AIR;
   }

   private static bbb aXxaiNDBAO(0i var0) {
      return var0.gameType;
   }

   public void updateController() {
      if (a9gr5GopOu(4GNGY0a7G0(this)) != null) {
         this.syncCurrentPlayItem();
      }

      if (4qe67FyrKN(this).getNetworkManager().isChannelOpen()) {
         UjyoLOyfC1(this).getNetworkManager().processReceivedPackets();
      }

   }

   private static void Neg5JzhojO(0i var0, boolean var1) {
      var0.isHittingBlock = var1;
   }

   public float getBlockReachDistance() {
      return YrtJyo4rLD(this).isCreative() ? Float.intBitsToFloat('\ue3eb' ^ '쭡' ^ 21807 ^ -897769862 ^ '컊' ^ '줳' ^ 13222 ^ -1965205632) : Float.intBitsToFloat(256990 ^ 219428 ^ 14988 ^ 1880968988 ^ 259902 ^ 32361 ^ 257047 ^ 814588458);
   }

   private static 0bo _l7jxDR6YJ/* $FF was: 9l7jxDR6YJ*/(0a var0) {
      return var0.world;
   }

   private static 0f v6fFimrVze(0a var0) {
      return var0.player;
   }

   public EnumActionResult processRightClick(EnumHand e) {
      if (Ai3tgFv7TC(this) == DvYmKD0oca()) {
         return rreODreLuB();
      } else {
         this.syncCurrentPlayItem();
         gHwmOT3TGe(this).sendPacket(new Tc(e));
         Qy d = X5YVij6xTg(8iFJVh7GQl(this)).getHeldItem(e);
         if (IjJimjm9An(IgZUHyaNjH(this)).getCooldownTracker().hasCooldown(d.getItem())) {
            return Jkd5bqqmuw();
         } else {
            int a = d.getCount();
            ActionResult<Qy> b = d.useItemRightClick(9l7jxDR6YJ(ONgIl8b9hr(this)), BeenWtrqnV(0QHnA0I2kT(this)), e);
            Qy c = (Qy)b.getResult();
            if (c != d || c.getCount() != a) {
               oG1FoipiVt(fOikFsaVI0(this)).setHeldItem(e, c);
            }

            return b.getType();
         }
      }
   }

   private static 0a RQ7bFQI2AB(0i var0) {
      return var0.bot;
   }

   private static 0f QZjNdSWDLl(0a var0) {
      return var0.player;
   }

   private static 0f djNW3HVWUL(0a var0) {
      return var0.player;
   }

   private static 0bo qE2aT20GZ6(0a var0) {
      return var0.world;
   }

   private static 0a kFxjDSFDTV(0i var0) {
      return var0.bot;
   }

   private static 0f BOfuEqQMvL(0a var0) {
      return var0.player;
   }

   private static 0f O9c1jLxavC(0a var0) {
      return var0.player;
   }
}
