package neo;

import com.mojang.authlib.GameProfile;
import io.netty.buffer.Unpooled;
import java.util.Iterator;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.StringUtils;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

@ParametersAreNonnullByDefault
public class 0bl implements Ts {
   public boolean doneLoadingTerrain;
   public final 0bi netManager;
   public final 0a bot;
   public final GameProfile profile;
   private static String _ _;

   private static 0a _HGAPSaSwL/* $FF was: 4HGAPSaSwL*/(0bl var0) {
      return var0.bot;
   }

   private static 0a rYaFLc1Gev(0bl var0) {
      return var0.bot;
   }

   private static 0a LqYvK2br3W(0bl var0) {
      return var0.bot;
   }

   private static 0bo VwBi3FST6P(0a var0) {
      return var0.world;
   }

   private static double OL4qNFJ0u6(Ig var0) {
      return var0.posX;
   }

   private static double Q3l2vNDyo9(MU var0) {
      return var0.posZ;
   }

   private static void SrnRV6u2Ab(0f var0, int var1) {
      var0.dimension = var1;
   }

   private static 0a GFLUZG7qq9(0bl var0) {
      return var0.bot;
   }

   private static 0bo q1a2XkD8Dt(0a var0) {
      return var0.world;
   }

   private static double Pq9jo9nKdF(Ig var0) {
      return var0.posX;
   }

   private static 0a SJeEWGtVyY(0bl var0) {
      return var0.bot;
   }

   private static 0a dOOqfAzWyW(0bl var0) {
      return var0.bot;
   }

   private static 0a gTKBW7FSpY(0bl var0) {
      return var0.bot;
   }

   private static 0bo c7p2T9vH4F(0a var0) {
      return var0.world;
   }

   private static 0f SWXEMVAvup(0a var0) {
      return var0.player;
   }

   private static Container pOyjByO26j(0f var0) {
      return var0.openContainer;
   }

   private static 0a oF0rSM6d1l(0bl var0) {
      return var0.bot;
   }

   private static 0bo uAYIA6iNs4(0a var0) {
      return var0.world;
   }

   public void handleSoundEffect(UL a) {
      0cG.method_bwd().method_bwh().forEach((b) -> {
         String var10001 = cYnJb44IaB("ۦۥ۔ۖ۞ېہۦۚۀۛۑ۰ۓۓېۖہ");
         Object[] var10002 = new Object[3579 ^ -11509 ^ 3500 ^ -11426];
         var10002[6317 ^ -17857 ^ 22491 ^ -2743] = qxqY2qLryI(this);
         var10002[3862 ^ -26104 ^ 26859 ^ -524] = a;
         b.method_bCr(var10001, var10002);
      });
   }

   private static 0a DODp7JQAJY(0bl var0) {
      return var0.bot;
   }

   private static 0a OcCMJIadTM(0bl var0) {
      return var0.bot;
   }

   private static double gE2Ov6LKLB(0f var0) {
      return var0.motionZ;
   }

   public void handleTitle(UY a) {
      0cG.method_bwd().method_bwh().forEach((b) -> {
         String var10001 = cYnJb44IaB("ۦۥ۔ۖ۞ېہۡۜہۙې");
         Object[] var10002 = new Object[5423 ^ -20404 ^ 30531 ^ -11742];
         var10002[24559 ^ -21981 ^ 24183 ^ -21573] = d1R29nHjtG(this);
         var10002[9595 ^ -29424 ^ 9074 ^ -29928] = a;
         b.method_bCr(var10001, var10002);
      });
   }

   private static void _4DK6XOWo2/* $FF was: 04DK6XOWo2*/(0f var0, double var1) {
      var0.motionZ = var1;
   }

   private static 0bo KFMkVw7S96(0a var0) {
      return var0.world;
   }

   private static 0a J7dN4j4zDE(0bl var0) {
      return var0.bot;
   }

   private static MJ g9iyrYq2cC(0f var0) {
      return var0.inventory;
   }

   public void handleTeams(UV e) {
      Ws f = fvYVkFsoNI(qoy9164MaL(this)).getScoreboard();
      WA g = f.getTeam(e.getName());
      if (e.getAction() == 0 && g == null) {
         g = f.createTeam(e.getName());
      }

      if (g != null) {
         g.setDisplayName(e.getDisplayName());
         g.setPrefix(e.getPrefix());
         g.setSuffix(e.getSuffix());
         g.setColor(TextFormatting.fromColorIndex(e.getColor()));
         g.setFriendlyFlags(e.getFriendlyFlags());
         WD a = WD.getByName(e.getNameTagVisibility());
         if (a != null) {
            g.setNameTagVisibility(a);
         }

         WC b = WC.getByName(e.getCollisionRule());
         if (b != null) {
            g.setCollisionRule(b);
         }
      }

      Iterator var6;
      String d;
      if (e.getAction() == 0 || e.getAction() == (24577 ^ -29554 ^ 11140 ^ -14584)) {
         var6 = e.getPlayers().iterator();

         while(var6.hasNext()) {
            d = (String)var6.next();
            f.addPlayerToTeam(d, e.getName());
         }
      }

      if (e.getAction() == (28819 ^ -1604 ^ 22189 ^ -8314)) {
         var6 = e.getPlayers().iterator();

         while(var6.hasNext()) {
            d = (String)var6.next();
            if (g != null && f.getPlayersTeam(d) == g) {
               f.removePlayerFromTeam(d, g);
            }
         }
      }

   }

   private static Tg zk1rq7Db7o() {
      return Tg.SUCCESSFULLY_LOADED;
   }

   private static 0bo EnGtsYoiwS(0a var0) {
      return var0.world;
   }

   private static 0a asAsSORvy4(0bl var0) {
      return var0.bot;
   }

   private static 0bo oTS0tPinYG(0a var0) {
      return var0.world;
   }

   private static void ELIqL1Qcab(Iw var0, double var1) {
      var0.motionY = var1;
   }

   private static void aiSY8m3NvQ(0f var0, double var1) {
      var0.prevPosY = var1;
   }

   public void handleExplosion(Ug a) {
      baX b = new baX(DCGvvebbCF(FlQGALAcjV(this)), (Ig)null, a.getX(), a.getY(), a.getZ(), a.getStrength(), a.getAffectedBlockPositions());
      b.doExplosionB((boolean)(2960 ^ -26500 ^ 22442 ^ -15290));
      0f var10000 = jZBsblBoTv(CSwaD6L4aq(this));
      GQFOTvS40A(var10000, 5hu6qjDbwY(var10000) + (double)a.getMotionX());
      var10000 = tBgOSiziwb(TlPOuhN4sI(this));
      jy6PrfDg93(var10000, tsQB9NBRvf(var10000) + (double)a.getMotionY());
      var10000 = I3T2tOYBxN(Dep0LpA9Ts(this));
      BLsXy3vRlY(var10000, gE2Ov6LKLB(var10000) + (double)a.getMotionZ());
   }

   private static 0f vdReggC2Q7(0a var0) {
      return var0.player;
   }

   private static 0a DEVdSoDMyg(0bl var0) {
      return var0.bot;
   }

   public void handleSpawnPainting(UQ a) {
      Jq b = new Jq(atsq0qjUIg(TW71rXjJF6(this)), a.getPosition(), a.getFacing(), a.getTitle());
      b.setUniqueId(a.getUniqueId());
      5M8TCJyvbV(T1LidHV4EY(this)).addEntityToWorld(a.getEntityID(), b);
   }

   private static 0bo WFMaQ7snJv(0a var0) {
      return var0.world;
   }

   private static 0bo v5luQSnGQj(0a var0) {
      return var0.world;
   }

   private static 0bo OjFVAdbOCY(0a var0) {
      return var0.world;
   }

   private static 0a QrFk8Yx3Mf(0bl var0) {
      return var0.bot;
   }

   private static 0a Mnk6o9BQGl(0bl var0) {
      return var0.bot;
   }

   private static 0a MLXhCN2NYd(0bl var0) {
      return var0.bot;
   }

   public void cleanup() {
      LDsLJfRM5v(gYyefulyhY(this)).loadWorld((0bo)null);
   }

   private static long TOM77jFoAE(Ig var0) {
      return var0.serverPosZ;
   }

   private static EnumHand _LWBxtW5LC/* $FF was: 2LWBxtW5LC*/() {
      return EnumHand.MAIN_HAND;
   }

   private static 0a LOTgSvvHI6(0bl var0) {
      return var0.bot;
   }

   public void handleSpawnPosition(US a) {
      VZ9dMbWi9y(C2VEapiYmt(this)).setSpawnPoint(a.getSpawnPos(), (boolean)(9368 ^ -9196 ^ 8092 ^ -6383));
      AHW7RD3Os9(ItqlGBDJ64(this)).getWorldInfo().setSpawn(a.getSpawnPos());
   }

   private static void J6GF0e441a(0bl var0, boolean var1) {
      var0.doneLoadingTerrain = var1;
   }

   private static 0a b4DrQtCb2t(0bl var0) {
      return var0.bot;
   }

   private static 0d DYkY7bm2v5(0a var0) {
      return var0.mc;
   }

   private static 0bi oDkMWFQEPu(0bl var0) {
      return var0.netManager;
   }

   private static 0bo _tIYDdj71u/* $FF was: 2tIYDdj71u*/(0a var0) {
      return var0.world;
   }

   private static 0bo bvglEmzv2F(0a var0) {
      return var0.world;
   }

   private static 0a n1quPgBFNy(0bl var0) {
      return var0.bot;
   }

   private static 0a v9wMIujKpF(0bl var0) {
      return var0.bot;
   }

   private static MB nTmXoRJBUW() {
      return MB.FULL;
   }

   private static 0f CbFv4zJtr4(0a var0) {
      return var0.player;
   }

   public void handleConfirmTransaction(TK a) {
      Container b = null;
      0f c = QGWpe6quFQ(68qkQo526p(this));
      if (a.getWindowId() == 0) {
         b = 1AYbrIUobk(c);
      } else if (a.getWindowId() == 5Ko60vbxeB(W1KQ74Al2f(c))) {
         b = pOyjByO26j(c);
      }

      if (b != null && !a.wasAccepted()) {
         this.sendPacket(new SL(a.getWindowId(), a.getActionNumber(), (boolean)(12840 ^ -10952 ^ 8918 ^ -14905)));
      }

   }

   private static 0bo u99PXbkoe2(0a var0) {
      return var0.world;
   }

   private static float _pVv3UVIfR/* $FF was: 9pVv3UVIfR*/(0f var0) {
      return var0.rotationPitch;
   }

   public void handleSelectAdvancementsTab(UF a) {
   }

   private static GameProfile lTGo1AIlCC(0bl var0) {
      return var0.profile;
   }

   private static 0bo a7btiIVG6n(0a var0) {
      return var0.world;
   }

   public void onDisconnect(ITextComponent a) {
      oDkMWFQEPu(this).closeChannel();
   }

   private static 0a _45qTwwViG/* $FF was: 645qTwwViG*/(0bl var0) {
      return var0.bot;
   }

   private static void GQFOTvS40A(0f var0, double var1) {
      var0.motionX = var1;
   }

   private static 0ei a6HHKNE7eH(0a var0) {
      return var0.captcha;
   }

   private static 0bo FiAe77AaY4(0a var0) {
      return var0.world;
   }

   private static Container TcqgrHBQFO(0f var0) {
      return var0.openContainer;
   }

   private static Tg _rILPDWyZg/* $FF was: 1rILPDWyZg*/() {
      return Tg.ACCEPTED;
   }

   private static 0bo bWh87v4II3(0a var0) {
      return var0.world;
   }

   private static 0a vt9Pr9dLhI(0bl var0) {
      return var0.bot;
   }

   private static 0a _ftkr8UrNd/* $FF was: 4ftkr8UrNd*/(0bl var0) {
      return var0.bot;
   }

   private static long OHY4Bg9wwa(Ig var0) {
      return var0.serverPosY;
   }

   private static void ljUNqGeYQz(Iw var0, double var1) {
      var0.motionZ = var1;
   }

   private static 0f Wl92vXa1dZ(0a var0) {
      return var0.player;
   }

   private static Ux gOwJReOtXT() {
      return Ux.Z;
   }

   private static 0f _QZN11lZe6/* $FF was: 5QZN11lZe6*/(0a var0) {
      return var0.player;
   }

   public _bl/* $FF was: 0bl*/(0a a, GameProfile b) {
      this.bot = a;
      this.netManager = a.networkManager;
      this.profile = b;
      this.bot.connection = this;
   }

   private static 0a NvVV1liF5a(0bl var0) {
      return var0.bot;
   }

   private static Container _GqJ3m90lk/* $FF was: 1GqJ3m90lk*/(0f var0) {
      return var0.inventoryContainer;
   }

   private static 0a L6eiOHZChO(0bl var0) {
      return var0.bot;
   }

   private static 0f _6ZbiLCyqD/* $FF was: 26ZbiLCyqD*/(0a var0) {
      return var0.player;
   }

   private static 0a gzDwoqDGZ9(0bl var0) {
      return var0.bot;
   }

   private static 0a VFbQAYrAMi(0bl var0) {
      return var0.bot;
   }

   private static 0bo _M8TCJyvbV/* $FF was: 5M8TCJyvbV*/(0a var0) {
      return var0.world;
   }

   private static 0a Whx59V4HOc(0bl var0) {
      return var0.bot;
   }

   private static 0f Uog40rDYCL(0a var0) {
      return var0.player;
   }

   private static 0a Sbv1xeCfbs(0bl var0) {
      return var0.bot;
   }

   private static 0bo RGgLTvzCct(0a var0) {
      return var0.world;
   }

   private static 0bo AHW7RD3Os9(0a var0) {
      return var0.world;
   }

   private static 0bo d0gOafhY1V(0a var0) {
      return var0.world;
   }

   private static 0bo k2lWSoGZSK(0a var0) {
      return var0.world;
   }

   private static 0f IuCQeXaqS9(0a var0) {
      return var0.player;
   }

   private static 0f _USL77Kvde/* $FF was: 1USL77Kvde*/(0a var0) {
      return var0.player;
   }

   private static 0f gSK1xX5YAW(0a var0) {
      return var0.player;
   }

   private static 0bo _8Ad4aQ7vV/* $FF was: 48Ad4aQ7vV*/(0a var0) {
      return var0.world;
   }

   private static 0a do8Ip9Stn4(0bl var0) {
      return var0.bot;
   }

   private static double Yx9pJbL8bJ(MU var0) {
      return var0.posX;
   }

   private static 0a UM1Z7IIzIA(0bl var0) {
      return var0.bot;
   }

   public void handleAdvancementInfo(Tw a) {
   }

   public void handleParticles(Up a) {
      if (a.getParticleType().equals(ViajYgqvMh()) && ztRfWQSlcA(this).getFunction().method_cS() instanceof 0w && ckIrfByGPD(KkGcoyUBt4(ASA26laKb6(this))) != null && 0X.getDistance(Yx9pJbL8bJ(iWgI5fYlyr(bKnep0o6lt(Sbv1xeCfbs(this)))), Double.longBitsToDouble(-7531853356721838074L ^ -7531853356721838074L), Q3l2vNDyo9(GWFYJWwgLE(Z2eGIND2fv(M0zcO972FR(this)))), a.getXCoordinate(), Double.longBitsToDouble(-1404229110162012133L ^ -1404229110162012133L), a.getZCoordinate()) < Double.longBitsToDouble(2451946529512005507L ^ 2152309375313184281L)) {
         ((0w)qbpTMHHe2K(this).getFunction().method_cS()).method_ey();
      }

      0cG.method_bwd().method_bwh().forEach((b) -> {
         String var10001 = cYnJb44IaB("ۦۥ۔ۖ۞ېہۥ۔ۇہۜۖۙېۆ");
         Object[] var10002 = new Object[19673 ^ -16945 ^ 25332 ^ -27680];
         var10002[27135 ^ -15786 ^ 15999 ^ -27178] = 1Sh2LgnANj(this);
         var10002[13893 ^ -10087 ^ 12833 ^ -8964] = a;
         b.method_bCr(var10001, var10002);
      });
   }

   private static 0a _Sh2LgnANj/* $FF was: 1Sh2LgnANj*/(0bl var0) {
      return var0.bot;
   }

   private static 0a N0bvfj7hg6(0bl var0) {
      return var0.bot;
   }

   private static double _hu6qjDbwY/* $FF was: 5hu6qjDbwY*/(0f var0) {
      return var0.motionX;
   }

   private static 0f D9dtw6QAlW(0a var0) {
      return var0.player;
   }

   private static void BLsXy3vRlY(0f var0, double var1) {
      var0.motionZ = var1;
   }

   private static 0a gYyefulyhY(0bl var0) {
      return var0.bot;
   }

   private static 0a SOHQzTbn9L(0bl var0) {
      return var0.bot;
   }

   private static 0a _8qkQo526p/* $FF was: 68qkQo526p*/(0bl var0) {
      return var0.bot;
   }

   private static 0f I3T2tOYBxN(0a var0) {
      return var0.player;
   }

   public void handleSpawnGlobalEntity(UN a) {
      double b = a.getX();
      double c = a.getY();
      double d = a.getZ();
   }

   private static 0cp JiZOe5elqv() {
      return 0bH.field_m;
   }

   private static 0a KSdBdhYnyT(0bl var0) {
      return var0.bot;
   }

   private static double QpL4ioYYYQ(Ig var0) {
      return var0.posZ;
   }

   private static 0f AdUFrpjJuV(0a var0) {
      return var0.player;
   }

   private static 0bo sNfGk2ZH6m(0a var0) {
      return var0.world;
   }

   public void handleSpawnPlayer(UR a) {
   }

   private static 0bo jvYTgs6Kh8(0a var0) {
      return var0.world;
   }

   private static 0f bB7HoSiNpB(0a var0) {
      return var0.player;
   }

   private static 0a qoy9164MaL(0bl var0) {
      return var0.bot;
   }

   private static Container uugTQJonv3(0f var0) {
      return var0.openContainer;
   }

   public void handleEntityHeadLook(TZ a) {
   }

   private static Container _AYbrIUobk/* $FF was: 1AYbrIUobk*/(0f var0) {
      return var0.inventoryContainer;
   }

   private static void o3mZkVnlEB(Iw var0, double var1) {
      var0.motionX = var1;
   }

   private static 0bo atsq0qjUIg(0a var0) {
      return var0.world;
   }

   private static 0bo _w7YwDDeYt/* $FF was: 0w7YwDDeYt*/(0a var0) {
      return var0.world;
   }

   private static 0a _KyFIrfgYn/* $FF was: 8KyFIrfgYn*/(0bl var0) {
      return var0.bot;
   }

   private static long No95TwlrQB(Ig var0) {
      return var0.serverPosY;
   }

   private static 0f vsToyvqTyj(0a var0) {
      return var0.player;
   }

   private static 0a W5QB0j6M9W(0bl var0) {
      return var0.bot;
   }

   private static double eKH3iqaCjA(0f var0) {
      return var0.posY;
   }

   private static MU iWgI5fYlyr(0f var0) {
      return var0.fishEntity;
   }

   private static 0bi _QGL7JbRJw/* $FF was: 8QGL7JbRJw*/(0bl var0) {
      return var0.netManager;
   }

   private static 0a wgw22b2tyL(0bl var0) {
      return var0.bot;
   }

   private static 0bo E9mB9g5WSl(0a var0) {
      return var0.world;
   }

   private static 0a OaVgdzjiAu(0bl var0) {
      return var0.bot;
   }

   public void handleChunkData(TE d) {
      if (d.isFullChunk()) {
         AXD5QUYiL5(OjSFPqnrmO(this)).doPreChunk(d.getChunkX(), d.getChunkZ(), (boolean)(24430 ^ -16090 ^ 4944 ^ -29415));
      }

      bam e = UOQAoWJPbH(nucqAo9I3q(this)).getChunk(d.getChunkX(), d.getChunkZ());
      e.read(d.getReadBuffer(), d.getExtractedSize(), d.isFullChunk());
      FQaWIwwBt6(LOTgSvvHI6(this)).markBlockRangeForRenderUpdate(d.getChunkX() << (13425 ^ -31595 ^ 17049 ^ -3463), 15939 ^ -1215 ^ 22770 ^ -25104, d.getChunkZ() << (22692 ^ -30618 ^ 24353 ^ -28697), (d.getChunkX() << (3398 ^ -18655 ^ 27510 ^ -12011)) + (12888 ^ -1929 ^ 1888 ^ -12992), 11515 ^ -23627 ^ 27012 ^ -6198, (d.getChunkZ() << (24537 ^ -8276 ^ 26124 ^ -6531)) + (12855 ^ -4822 ^ 30921 ^ -22565));
      if (!d.isFullChunk() || !(AQv9aMTubL(sNfGk2ZH6m(t8fr11Lzbf(this))) instanceof bip)) {
         e.resetRelightChecks();
      }

      Iterator var3 = d.getTileEntityTags().iterator();

      while(var3.hasNext()) {
         QQ c = (QQ)var3.next();
         if (c != null) {
            BlockPos a = new BlockPos(c.getInteger(cYnJb44IaB("ۍ")), c.getInteger(cYnJb44IaB("ی")), c.getInteger(cYnJb44IaB("ۏ")));
            Yg b = cUAdAwLuYl(DQ1GxIq5NL(this)).getTileEntity(a);
            if (b != null) {
               b.readFromNBT(c);
            }
         }
      }

   }

   private static 0bo iIJSU7pegi(0a var0) {
      return var0.world;
   }

   private static void _lIFjqP4Iq/* $FF was: 2lIFjqP4Iq*/(0a var0, 0i var1) {
      var0.controller = var1;
   }

   private static double _tbuszNyzi/* $FF was: 5tbuszNyzi*/(Ig var0) {
      return var0.posZ;
   }

   private static 0bo C5jcde7gg5(0a var0) {
      return var0.world;
   }

   private static 0f lAmkiGNKbZ(0a var0) {
      return var0.player;
   }

   public void handleRecipeBook(UA a) {
   }

   private static double DX4irQ42NO(0f var0) {
      return var0.posX;
   }

   public void handleUpdateTileEntity(Vg d) {
      if (mbOOOqoKYs(rsJQyXWbvl(this)).isBlockLoaded(d.getPos())) {
         Yg a = LVq9qpfqDo(KSdBdhYnyT(this)).getTileEntity(d.getPos());
         int b = d.getTileEntityType();
         boolean c = b == (7787 ^ -12585 ^ 1476 ^ -10886) && a instanceof Yq ? 30357 ^ -24067 ^ 32214 ^ -21825 : 438 ^ -21239 ^ 22827 ^ -2668;
         if (b == (21029 ^ -25360 ^ 16695 ^ -28701) && a instanceof YG || c != 0 || b == (8071 ^ -23204 ^ 29735 ^ -12545) && a instanceof Yj || b == (20141 ^ -21857 ^ 28104 ^ -30210) && a instanceof YR || b == (7342 ^ -10478 ^ 20852 ^ -25907) && a instanceof Yz || b == (4145 ^ -23771 ^ 3081 ^ -16613) && a instanceof Yh || b == (3500 ^ -15779 ^ 24353 ^ -28457) && a instanceof YV || b == (7896 ^ -7 ^ 26048 ^ -31511) && a instanceof Yx || b == (4227 ^ -29626 ^ 27258 ^ -2378) && a instanceof YQ || b == (986 ^ -862 ^ 3259 ^ -3127) && a instanceof YN || b == (17623 ^ -24877 ^ 5014 ^ -13927) && a instanceof Yk) {
            a.readFromNBT(d.getNbtCompound());
         }
      }

   }

   public void handleResourcePack(UC a) {
      if (CcLqo54XqS().method_bna()) {
         0bz.method_Qm().method_Qp().addScheduler(() -> {
            this.sendPacket(new Th(1rILPDWyZg()));
         }, 100L).addScheduler(() -> {
            this.sendPacket(new Th(zk1rq7Db7o()));
         }, 1000L);
      }

      0cG.method_bwd().method_bwh().forEach((b) -> {
         String var10001 = cYnJb44IaB("ۦۥ۔ۖ۞ېہۧېۆۚۀۇۖېۥ۔ۖ۞ۦېۛۑ");
         Object[] var10002 = new Object[8625 ^ -18329 ^ 24409 ^ -14707];
         var10002[20791 ^ -2927 ^ 24514 ^ -1436] = rYaFLc1Gev(this);
         var10002[24212 ^ -3341 ^ 2360 ^ -23202] = a;
         b.method_bCr(var10001, var10002);
      });
   }

   public void handleDisplayObjective(TQ b) {
      Ws c = Ztk67LbU6s(dNkgBqayil(this)).getScoreboard();
      if (b.getName().isEmpty()) {
         c.setObjectiveInDisplaySlot(b.getPosition(), (Wz)null);
      } else {
         Wz a = c.getObjective(b.getName());
         if (a != null) {
            c.setObjectiveInDisplaySlot(b.getPosition(), a);
         }
      }

   }

   public void handleSpawnObject(UP i) {
      double j = i.getX();
      double k = i.getY();
      double l = i.getZ();
      Ig m = null;
      if (i.getType() == (29313 ^ -22912 ^ 28307 ^ -17768)) {
         m = Jc.create(XytrSEo4Wv(UGwmi22TnD(this)), j, k, l, Jb.getById(i.getData()));
      } else if (i.getType() == (21589 ^ -23849 ^ 19088 ^ -17336)) {
         Ig a = A9DV4FknVT(SOHQzTbn9L(this)).getEntityByID(i.getData());
         if (a instanceof ME) {
            m = new MU(jvYTgs6Kh8(8F9bvWjdgY(this)), (ME)a, j, k, l);
         }

         i.setData(27568 ^ -2406 ^ 2637 ^ -26777);
      } else if (i.getType() == (29835 ^ -23804 ^ 10225 ^ -4030)) {
         m = new Ne(IazVDlStIN(b4DrQtCb2t(this)), j, k, l);
      } else if (i.getType() == (15245 ^ -6284 ^ 30602 ^ -21720)) {
         m = new Nc(KFMkVw7S96(NvVV1liF5a(this)), j, k, l);
      } else if (i.getType() == (6697 ^ -21393 ^ 4251 ^ -22816)) {
         m = new Nb(tUTiF3oDb6(bjyuZL3NpV(this)), j, k, l);
      } else if (i.getType() == (23292 ^ -18071 ^ 1964 ^ -7043)) {
         m = new MW(2tIYDdj71u(9qAMBDVAqs(this)), j, k, l, (double)i.getSpeedX() / Double.longBitsToDouble(3492317601153785707L ^ 8126873461438914411L), (double)i.getSpeedY() / Double.longBitsToDouble(-4202190667457654110L ^ -8858138625972792670L), (double)i.getSpeedZ() / Double.longBitsToDouble(-2193274943444300608L ^ -6831771453403378496L));
      } else if (i.getType() == (26261 ^ -23875 ^ 1820 ^ -15501)) {
         m = new IZ(WFMaQ7snJv(1oKJhJyCoD(this)), new BlockPos(j, k, l), EnumFacing.byHorizontalIndex(i.getData()));
         i.setData(1346 ^ -20795 ^ 22096 ^ -553);
      } else if (i.getType() == (19396 ^ -2010 ^ 8130 ^ -21395)) {
         m = new Ip(iIJSU7pegi(mXFpDdDKls(this)), new BlockPos(MathHelper.floor(j), MathHelper.floor(k), MathHelper.floor(l)));
         i.setData(3074 ^ -5816 ^ 20358 ^ -21812);
      } else if (i.getType() == (24194 ^ -3166 ^ 1425 ^ -22288)) {
         m = new IU(aDyokDwJ6b(4I1qHAGYAP(this)), j, k, l);
      } else if (i.getType() == (5475 ^ -18060 ^ 12770 ^ -25155)) {
         m = new IT(OjFVAdbOCY(JdLKezKa4W(this)), j, k, l);
      } else if (i.getType() == (3231 ^ -7471 ^ 32076 ^ -27826)) {
         m = new IX(VnG7VVjRXy(asAsSORvy4(this)), j, k, l, xrJBwyVXiN());
      } else if (i.getType() == (21488 ^ -11815 ^ 30684 ^ -2614)) {
         m = new MV(aIV0jFjLT4(BIDSglLizw(this)), j, k, l, (double)i.getSpeedX() / Double.longBitsToDouble(-4864799778699119050L ^ -233199405669452234L), (double)i.getSpeedY() / Double.longBitsToDouble(7206730466375075321L ^ 2647046949894981113L), (double)i.getSpeedZ() / Double.longBitsToDouble(-1685911718177192734L ^ -6330600677623905054L));
         i.setData(19372 ^ -11214 ^ 32545 ^ -8001);
      } else if (i.getType() == (996 ^ -6171 ^ 5607 ^ -3653)) {
         m = new MP(yejqqGXSqN(tXVeiAGt6V(this)), j, k, l, (double)i.getSpeedX() / Double.longBitsToDouble(-1682335284650245893L ^ -6334201856003079941L), (double)i.getSpeedY() / Double.longBitsToDouble(-5461079549206298282L ^ -826101476456103594L), (double)i.getSpeedZ() / Double.longBitsToDouble(-4753454054949511321L ^ -92580284341936281L));
         i.setData(13687 ^ -16319 ^ 6669 ^ -4293);
      } else if (i.getType() == (22147 ^ -7028 ^ 26230 ^ -11207)) {
         m = new Na(oTS0tPinYG(PNlJ7O0QzI(this)), j, k, l, (double)i.getSpeedX() / Double.longBitsToDouble(1525800302108834037L ^ 6166548611881597173L), (double)i.getSpeedY() / Double.longBitsToDouble(-2109256051185154532L ^ -6771959409141348836L), (double)i.getSpeedZ() / Double.longBitsToDouble(-8402959323459841796L ^ -3756581514152865540L));
         i.setData(30742 ^ -18781 ^ 20329 ^ -32292);
      } else if (i.getType() == (4020 ^ -3962 ^ 21794 ^ -21934)) {
         m = new Nf(CfIklX5XAk(QjNJswNWDJ(this)), j, k, l, (double)i.getSpeedX() / Double.longBitsToDouble(-5138183859905353472L ^ -572307893937624832L), (double)i.getSpeedY() / Double.longBitsToDouble(3968457167993094633L ^ 8623701439066456553L), (double)i.getSpeedZ() / Double.longBitsToDouble(899268730809667328L ^ 5531995003746176768L));
         i.setData(12014 ^ -5894 ^ 271 ^ -14565);
      } else if (i.getType() == (2458 ^ -21476 ^ 32304 ^ -9227)) {
         m = new MZ(v5luQSnGQj(oF0rSM6d1l(this)), j, k, l, (double)i.getSpeedX() / Double.longBitsToDouble(9122847055530419482L ^ 4478158096083707162L), (double)i.getSpeedY() / Double.longBitsToDouble(5759036149416153684L ^ 1104354828296213076L), (double)i.getSpeedZ() / Double.longBitsToDouble(-8706877146872432461L ^ -4065143674681182029L));
         i.setData(22056 ^ -1016 ^ 30920 ^ -11544);
      } else if (i.getType() == (1105 ^ -12340 ^ 1709 ^ -13042)) {
         m = new MQ(vjQKCZENl9(gzDwoqDGZ9(this)), j, k, l);
      } else if (i.getType() == (13995 ^ -16856 ^ 15485 ^ -19279)) {
         m = new MR(d0gOafhY1V(gTKBW7FSpY(this)), j, k, l, Float.intBitsToFloat(17373 ^ '飰' ^ 16831 ^ -1322566876 ^ 7754 ^ 10191 ^ 7435 ^ -1322547912), 10443 ^ -24247 ^ 25072 ^ -6030, (Iw)null);
      } else if (i.getType() == (1995 ^ -22985 ^ 12632 ^ -28435)) {
         m = new MY(bWh87v4II3(CSl0pohGn9(this)), j, k, l, rIG9Npef9A());
         i.setData(9031 ^ -26124 ^ 28308 ^ -11225);
      } else if (i.getType() == (26624 ^ -10138 ^ 16242 ^ -28833)) {
         m = new IV(HoHWNg7kjY(rJLGFeV9Vm(this)), j, k, l);
         i.setData(13493 ^ -16983 ^ 13111 ^ -17877);
      } else if (i.getType() == (11415 ^ -21652 ^ 27747 ^ -5223)) {
         m = new IR(DQoFfcSoTG(7TNv16N1YV(this)), j, k, l);
      } else if (i.getType() == (8170 ^ -7433 ^ 22553 ^ -23242)) {
         m = new Jr(VwBi3FST6P(QeJgnPnBCv(this)), j, k, l, (Iw)null);
      } else if (i.getType() == (29548 ^ -14131 ^ 21937 ^ -4514)) {
         m = new IN(nLbixja4gW(q1hRzliRkd(this)), j, k, l);
      } else if (i.getType() == (29051 ^ -11317 ^ 28283 ^ -13064)) {
         m = new IS(k2lWSoGZSK(v9wMIujKpF(this)), j, k, l);
      } else if (i.getType() == (26740 ^ -28668 ^ 9379 ^ -9007)) {
         m = new IY(FcptGRY5vB(6Zo9b7eYY9(this)), j, k, l);
      } else if (i.getType() == (1968 ^ -4976 ^ 17089 ^ -22105)) {
         m = new IW(2AIHRxNaSh(gTbTWfKknO(this)), j, k, l, co.getStateById(i.getData() & (130244 ^ 114861 ^ 30168 ^ '뙎')));
         i.setData(8381 ^ -18722 ^ 19058 ^ -9199);
      } else if (i.getType() == (10826 ^ -12245 ^ 11045 ^ -11961)) {
         m = new Ii(zb2rNDB3q9(ijj2eAr1O0(this)), j, k, l);
      }

      if (m != null) {
         Iz.updateServerPosition((Ig)m, j, k, l);
         gTybTuhiVe((Ig)m, (float)(i.getPitch() * (24704 ^ -6755 ^ 22502 ^ -11373)) / Float.intBitsToFloat(31162 ^ '\ueb1b' ^ 25320 ^ 81088846 ^ 16976 ^ 222735 ^ 260976 ^ 1196785192));
         cJWgQ1jx5O((Ig)m, (float)(i.getYaw() * (20211 ^ -528 ^ 18392 ^ -2637)) / Float.intBitsToFloat('낱' ^ '芩' ^ 6588 ^ -2019016208 ^ 'ꇥ' ^ '\ua6fb' ^ 25047 ^ -1004008291));
         Ig[] h = ((Ig)m).getParts();
         if (h != null) {
            int e = i.getEntityID() - ((Ig)m).getEntityId();
            Ig[] f = h;
            int c = h.length;

            for(int d = 17961 ^ -15551 ^ 31559 ^ -465; d < c; ++d) {
               Ig b = f[d];
               b.setEntityId(b.getEntityId() + e);
            }
         }

         ((Ig)m).setEntityId(i.getEntityID());
         ((Ig)m).setUniqueId(i.getUniqueId());
         yg5INbtBJ3(dOOqfAzWyW(this)).addEntityToWorld(i.getEntityID(), (Ig)m);
         if (i.getData() > 0) {
            if (i.getType() == (8020 ^ -16561 ^ 17009 ^ -7594) || i.getType() == (28745 ^ -1262 ^ 18153 ^ -12823)) {
               Ig g = y1keVJfEYl(do8Ip9Stn4(this)).getEntityByID(i.getData() - (5768 ^ -24466 ^ 18132 ^ -4045));
               if (g instanceof Iw && m instanceof MO) {
                  zN6wBQJG6L((MO)m, g);
               }
            }

            ((Ig)m).setVelocity((double)i.getSpeedX() / Double.longBitsToDouble(-4357798040716977030L ^ -8990665051141841798L), (double)i.getSpeedY() / Double.longBitsToDouble(-1752602060753601737L ^ -6407846331826963657L), (double)i.getSpeedZ() / Double.longBitsToDouble(3894791981868401861L ^ 8552991740197225669L));
         }
      }

   }

   private static double I29HLmNVaI(0f var0) {
      return var0.posZ;
   }

   private static void d9BoYwir9z(MJ var0, int var1) {
      var0.currentItem = var1;
   }

   private static 0bo tUTiF3oDb6(0a var0) {
      return var0.world;
   }

   private static 0a HSp03eSwGt(0bl var0) {
      return var0.bot;
   }

   private static 0a nZIq52n2r4(0bl var0) {
      return var0.bot;
   }

   private static 0a VqU6lzr7cF(0bl var0) {
      return var0.bot;
   }

   private static 0a wV60pGh2yW(0bl var0) {
      return var0.bot;
   }

   private static 0a in56n01mds(0bl var0) {
      return var0.bot;
   }

   private static EnumHandSide NJEWSVQAqq() {
      return EnumHandSide.RIGHT;
   }

   private static double m1lnTW6qa1(0f var0) {
      return var0.posY;
   }

   private static 0a mXFpDdDKls(0bl var0) {
      return var0.bot;
   }

   private static 0bo DQoFfcSoTG(0a var0) {
      return var0.world;
   }

   private static 0a aT1aObe1nW(0bl var0) {
      return var0.bot;
   }

   private static void cJWgQ1jx5O(Ig var0, float var1) {
      var0.rotationYaw = var1;
   }

   private static 0bi sXztV1VxF2(0bl var0) {
      return var0.netManager;
   }

   private static 0a QwFaerb6bB(0bl var0) {
      return var0.bot;
   }

   private static 0cs WwFJvAIHoA() {
      return 0bC.field_f;
   }

   private static void r44QzDoQgj(Iw var0, float var1) {
      var0.rotationYawHead = var1;
   }

   private static 0a iobuD9dkGZ(0bl var0) {
      return var0.bot;
   }

   private static void ICCH9FQm8v(Ig var0, boolean var1) {
      var0.onGround = var1;
   }

   private static 0bo yg5INbtBJ3(0a var0) {
      return var0.world;
   }

   private static 0a G2FyBDfFWQ(0bl var0) {
      return var0.bot;
   }

   private static SH LYAL06jTRd() {
      return SH.PERFORM_RESPAWN;
   }

   private static 0a laXLi9RgLj(0bl var0) {
      return var0.bot;
   }

   private static float _cJN1XYMFf/* $FF was: 4cJN1XYMFf*/(Ig var0) {
      return var0.rotationPitch;
   }

   private static 0bo cUAdAwLuYl(0a var0) {
      return var0.world;
   }

   public void handleCustomSound(TN a) {
   }

   private static 0a qxqY2qLryI(0bl var0) {
      return var0.bot;
   }

   private static int r2gWqAd4dj(0f var0) {
      return var0.dimension;
   }

   private static 0bi QuOyG4anJH(0bl var0) {
      return var0.netManager;
   }

   private static float yKGRbnLq5P(Ig var0) {
      return var0.rotationYaw;
   }

   private static 0bo mbOOOqoKYs(0a var0) {
      return var0.world;
   }

   private static 0i vjQr9BDhac(0a var0) {
      return var0.controller;
   }

   private static 0bo sX9LlDAlg4(0a var0) {
      return var0.world;
   }

   private static 0bi _Gnz0AwBwY/* $FF was: 2Gnz0AwBwY*/(0bl var0) {
      return var0.netManager;
   }

   public void processChunkUnload(UZ a) {
      nn8cLOiOjj(xvciSUna7A(this)).doPreChunk(a.getX(), a.getZ(), (boolean)(30609 ^ -29869 ^ 21868 ^ -22098));
   }

   private static void _YaU6DPWyO/* $FF was: 8YaU6DPWyO*/(ML var0, boolean var1) {
      var0.allowFlying = var1;
   }

   private static 0a rVGiUKDIef(0bl var0) {
      return var0.bot;
   }

   private static 0a LvaLL6NBe9(0bl var0) {
      return var0.bot;
   }

   private static 0a AFJLldDP9b(0bl var0) {
      return var0.bot;
   }

   public void handleEntityTeleport(Ue f) {
      Ig g = EnGtsYoiwS(8KyFIrfgYn(this)).getEntityByID(f.getEntityId());
      if (g != null) {
         double c = f.getX();
         double d = f.getY();
         double e = f.getZ();
         Iz.updateServerPosition(g, c, d, e);
         if (!g.canPassengerSteer()) {
            float a = (float)(f.getYaw() * (24374 ^ -11217 ^ 23840 ^ -10415)) / Float.intBitsToFloat('\ueb25' ^ '행' ^ 17304 ^ -135454809 ^ 'ꙹ' ^ 28994 ^ '뗟' ^ -1267924873);
            float b = (float)(f.getPitch() * (11261 ^ -22180 ^ 7809 ^ -25272)) / Float.intBitsToFloat('ꩩ' ^ 32225 ^ '큼' ^ 719858627 ^ 462 ^ 11751 ^ 6803 ^ 1768430221);
            if (Math.abs(Pq9jo9nKdF(g) - c) < Double.longBitsToDouble(982115532460330119L ^ 3603210515589958791L) && Math.abs(qWtHSeA7ai(g) - d) < Double.longBitsToDouble(6660367563955801232L ^ 7205303118867631248L) && Math.abs(5tbuszNyzi(g) - e) < Double.longBitsToDouble(5850899037822017356L ^ 7967590862686150476L)) {
               g.setPositionAndRotationDirect(OL4qNFJ0u6(g), TlHecUBj7A(g), QpL4ioYYYQ(g), a, b, 22814 ^ -15742 ^ 2361 ^ -27995, (boolean)(9275 ^ -18837 ^ 26412 ^ -2691));
            } else {
               g.setPositionAndRotationDirect(c, d, e, a, b, 12899 ^ -31724 ^ 17009 ^ -3067, (boolean)(2548 ^ -5907 ^ 5118 ^ -3354));
            }

            MhKI4AxQKV(g, f.getOnGround());
         }
      }

   }

   private static Ve NnawJCyl4b() {
      return Ve.CHANGE;
   }

   private static 0f eBPB9WDBwI(0a var0) {
      return var0.player;
   }

   public void handleCollectItem(TG a) {
   }

   private static ML wTNViOqAbd(0f var0) {
      return var0.capabilities;
   }

   public void handleEntityEquipment(TY a) {
      Ig b = eZueNO9Idt(in56n01mds(this)).getEntityByID(a.getEntityID());
      if (b != null) {
         b.setItemStackToSlot(a.getEquipmentSlot(), a.getItemStack());
      }

   }

   public GameProfile getGameProfile() {
      return lTGo1AIlCC(this);
   }

   private static void _Sy2NoGzQ7/* $FF was: 4Sy2NoGzQ7*/(0a var0, String var1) {
      var0.windowTitle = var1;
   }

   private static ML i7usOHiAtt(0f var0) {
      return var0.capabilities;
   }

   public void handleAnimation(Tx d) {
      Ig e = pLyiFjgjzT(V9Ywvzio1I(this)).getEntityByID(d.getEntityID());
      if (e != null) {
         Iw b;
         if (d.getAnimationType() == 0) {
            b = (Iw)e;
            b.swingArm(2LWBxtW5LC());
         } else if (d.getAnimationType() == (13642 ^ -7185 ^ 23117 ^ -29461)) {
            b = (Iw)e;
            b.swingArm(5LPxjfQNYt());
         } else if (d.getAnimationType() == (14439 ^ -27857 ^ 19923 ^ -6502)) {
            e.performHurtAnimation();
         } else if (d.getAnimationType() == (23867 ^ -31175 ^ 18228 ^ -25548)) {
            ME c = (ME)e;
            c.wakeUpPlayer((boolean)(5589 ^ -21782 ^ 263 ^ -16840), (boolean)(27497 ^ -27918 ^ 25909 ^ -25426), (boolean)(19761 ^ -13262 ^ 11947 ^ -20568));
         }
      }

   }

   public void handleWorldBorder(Vm a) {
      a.apply(BBNlYVOY57(a3UIbia1vF(this)).getWorldBorder());
   }

   private static 0bo CfIklX5XAk(0a var0) {
      return var0.world;
   }

   private static 0f yi6EujrNre(0a var0) {
      return var0.player;
   }

   public void handleDestroyEntities(TO b) {
      for(int a = 21639 ^ -20607 ^ 448 ^ -1338; a < b.getEntityIDs().length; ++a) {
         OGGkXJLYaH(VqU6lzr7cF(this)).removeEntityFromWorld(b.getEntityIDs()[a]);
      }

   }

   private static 0bo ZkHIcVQqqA(0a var0) {
      return var0.world;
   }

   private static 0a Nysj4WRY2A(0bl var0) {
      return var0.bot;
   }

   private static 0a rGgO8jhdCW(0bl var0) {
      return var0.bot;
   }

   private static long vrAev2Deak(Ig var0) {
      return var0.serverPosX;
   }

   private static 0a _GQrk9Xv4S/* $FF was: 9GQrk9Xv4S*/(0bl var0) {
      return var0.bot;
   }

   public void handleEntityMovement(TV f) {
      Ig g = f.getEntity(VcQtdzIfBd(MLXhCN2NYd(this)));
      if (g != null) {
         AVuKnGXqVe(g, dl8a4m5Asp(g) + (long)f.getX());
         kfTyfDzLT9(g, No95TwlrQB(g) + (long)f.getY());
         wb74T8TLk1(g, TOM77jFoAE(g) + (long)f.getZ());
         double c = (double)vrAev2Deak(g) / Double.longBitsToDouble(-6638416459612779568L ^ -2058255638576985136L);
         double d = (double)OHY4Bg9wwa(g) / Double.longBitsToDouble(1775281072660912615L ^ 6346434694441966055L);
         double e = (double)lLixgGJX0t(g) / Double.longBitsToDouble(-5358729895991319869L ^ -787576274210266429L);
         if (!g.canPassengerSteer()) {
            float a = f.isRotating() ? (float)(f.getYaw() * (8096 ^ -7874 ^ 9037 ^ -9029)) / Float.intBitsToFloat(114396 ^ 11587 ^ 108494 ^ -1418029893 ^ 113537 ^ '\uf6f1' ^ 112363 ^ -386245775) : yKGRbnLq5P(g);
            float b = f.isRotating() ? (float)(f.getPitch() * (29443 ^ -7796 ^ 3587 ^ -25116)) / Float.intBitsToFloat(22642 ^ 'ꉔ' ^ 12436 ^ -301961545 ^ 110107 ^ 87185 ^ 3390 ^ -1384098895) : 4cJN1XYMFf(g);
            g.setPositionAndRotationDirect(c, d, e, a, b, 17693 ^ -5518 ^ 263 ^ -20885, (boolean)(15883 ^ -5500 ^ 12626 ^ -6691));
            ICCH9FQm8v(g, f.getOnGround());
         }
      }

   }

   private static 0a hAtK5NGd2z(0bl var0) {
      return var0.bot;
   }

   private static 0a CZnay6vIx9(0bl var0) {
      return var0.bot;
   }

   private static Ux _3osLAT569/* $FF was: 63osLAT569*/() {
      return Ux.X;
   }

   private static float eNyev7bWev(0f var0) {
      return var0.rotationYaw;
   }

   private static 0a cf0NnwWZ81(0bl var0) {
      return var0.bot;
   }

   private static 0bo yejqqGXSqN(0a var0) {
      return var0.world;
   }

   private static void DqdG9Bj5DA(Container var0, int var1) {
      var0.windowId = var1;
   }

   private static MJ ewPWXjwmWI(0f var0) {
      return var0.inventory;
   }

   private static int tY2tf8xuJu(Container var0) {
      return var0.windowId;
   }

   private static MU ckIrfByGPD(0f var0) {
      return var0.fishEntity;
   }

   private static 0f WqxdSG1KRw(0a var0) {
      return var0.player;
   }

   private static 0a NF2KrBepa9(0bl var0) {
      return var0.bot;
   }

   public void handleBlockChange(TA a) {
      bvglEmzv2F(n1quPgBFNy(this)).invalidateRegionAndSetBlock(a.getBlockPosition(), a.getBlockState());
   }

   private static void _W23AftzfU/* $FF was: 8W23AftzfU*/(0f var0, double var1) {
      var0.prevPosX = var1;
   }

   public void handleEntityVelocity(Uf a) {
      Ig b = E9mB9g5WSl(9I344OgDB8(this)).getEntityByID(a.getEntityID());
      if (b != null) {
         b.setVelocity((double)a.getMotionX() / Double.longBitsToDouble(-8788027038187298358L ^ -4128138429998210614L), (double)a.getMotionY() / Double.longBitsToDouble(103743185510681880L ^ 4742239695469759768L), (double)a.getMotionZ() / Double.longBitsToDouble(88929602604374896L ^ 4721092925587463024L));
      }

   }

   private static 0a LXjFOQklGB(0bl var0) {
      return var0.bot;
   }

   private static 0a qiKNV2BBws(0bl var0) {
      return var0.bot;
   }

   private static 0bo fsLAyeNLXE(0a var0) {
      return var0.world;
   }

   public void handleOpenWindow(Uo b) {
      0f c = lAmkiGNKbZ(uAmWdMwwRv(this));
      nqvv1m6fLn(c, b.getWindowTitle().getFormattedText());
      itTjmcUBtl(TKr6oQT8Cb(this), b.getWindowTitle().getFormattedText());
      String var3 = b.getGuiId();
      int var4 = -27964 ^ -6988 ^ 20768 ^ -10065;
      switch (var3.hashCode()) {
         case -1879003021:
            if (var3.equals(cYnJb44IaB("ۘۜۛېۖۇ۔ۓہڏۃۜۙۙ۔ےېۇ"))) {
               var4 = 2850 ^ -586 ^ 22300 ^ -24183;
            }
            break;
         case -1366784614:
            if (var3.equals(cYnJb44IaB("۰ۛہۜہی۽ۚۇۆې"))) {
               var4 = 12792 ^ -22347 ^ 26257 ^ -34;
            }
            break;
         case 1438413556:
            if (var3.equals(cYnJb44IaB("ۘۜۛېۖۇ۔ۓہڏۖۚۛہ۔ۜۛېۇ"))) {
               var4 = 21322 ^ -252 ^ 23683 ^ -3891;
            }
      }

      switch (var4) {
         case 0:
            c.displayGUIChest(new InventoryBasic(b.getWindowTitle(), b.getSlotCount()));
            DqdG9Bj5DA(8OYGZLd0Yy(c), b.getWindowId());
            break;
         case 1:
            c.displayVillagerTradeGui(new Ls(c, b.getWindowTitle()));
            FjOI6nwyBd(IPQhQoytnw(c), b.getWindowId());
            break;
         case 2:
            Ig a = a7btiIVG6n(laXLi9RgLj(this)).getEntityByID(b.getEntityId());
            if (a instanceof Lw) {
               KAc496YevF(TcqgrHBQFO(c), b.getWindowId());
            }
      }

   }

   private static 0a oUFvStHGQQ(0bl var0) {
      return var0.bot;
   }

   public void handleEntityMetadata(Ua a) {
      Ig b = uAYIA6iNs4(Nz6P79FouO(this)).getEntityByID(a.getEntityId());
      if (b != null && a.getDataManagerEntries() != null) {
         b.getDataManager().setEntryValues(a.getDataManagerEntries());
      }

   }

   private static void tULxDWALAL(Iw var0, float var1) {
      var0.renderYawOffset = var1;
   }

   private static int ic6qnUtFMO(Container var0) {
      return var0.windowId;
   }

   private static 0a V9Ywvzio1I(0bl var0) {
      return var0.bot;
   }

   private static 0bo VcQtdzIfBd(0a var0) {
      return var0.world;
   }

   private static 0a N4nJB6DvGG(0bl var0) {
      return var0.bot;
   }

   private static 0a NHkoGDA96L(0bl var0) {
      return var0.bot;
   }

   private static double ZZwNrR7LVy(0f var0) {
      return var0.posZ;
   }

   private static 0f bKnep0o6lt(0a var0) {
      return var0.player;
   }

   private static 0cp qEM3nAZw1u() {
      return 0bF.field_e;
   }

   private static 0a vy4OTjLyYl(0bl var0) {
      return var0.bot;
   }

   private static 0bo TU0CmbL3Ty(0a var0) {
      return var0.world;
   }

   private static 0a ASA26laKb6(0bl var0) {
      return var0.bot;
   }

   private static 0a _TNv16N1YV/* $FF was: 7TNv16N1YV*/(0bl var0) {
      return var0.bot;
   }

   private static 0f KyLlQGQIt4(0a var0) {
      return var0.player;
   }

   private static 0bo eZueNO9Idt(0a var0) {
      return var0.world;
   }

   private static 0bo BBNlYVOY57(0a var0) {
      return var0.world;
   }

   private static 0a nucqAo9I3q(0bl var0) {
      return var0.bot;
   }

   private static void PHHoqIFjKB(ML var0, boolean var1) {
      var0.disableDamage = var1;
   }

   private static 0a oTytr7x2zv(0bl var0) {
      return var0.bot;
   }

   private static 0a BIDSglLizw(0bl var0) {
      return var0.bot;
   }

   private static void MhKI4AxQKV(Ig var0, boolean var1) {
      var0.onGround = var1;
   }

   private static Container wcxo7e14Bw(0f var0) {
      return var0.openContainer;
   }

   private static 0bo y1keVJfEYl(0a var0) {
      return var0.world;
   }

   private static 0a Y21rv6GtYh(0bl var0) {
      return var0.bot;
   }

   private static EnumParticleTypes ViajYgqvMh() {
      return EnumParticleTypes.WATER_BUBBLE;
   }

   private static 0f tBgOSiziwb(0a var0) {
      return var0.player;
   }

   private static 0a DQ1GxIq5NL(0bl var0) {
      return var0.bot;
   }

   private static 0bo _AjuttunCv/* $FF was: 4AjuttunCv*/(0a var0) {
      return var0.world;
   }

   private static 0a _yDV20kJMO/* $FF was: 6yDV20kJMO*/(0bl var0) {
      return var0.bot;
   }

   private static 0f vq4eM0LMWW(0a var0) {
      return var0.player;
   }

   private static 0a xvciSUna7A(0bl var0) {
      return var0.bot;
   }

   private static ML JGBwyngNye(0f var0) {
      return var0.capabilities;
   }

   private static 0bo As4I2ckjgO(0a var0) {
      return var0.world;
   }

   public void handleStatistics(UT a) {
   }

   private static Ve fXN1zJ4Tdy() {
      return Ve.REMOVE;
   }

   private static 0a UwV1WHTAF3(0bl var0) {
      return var0.bot;
   }

   public void handleCustomPayload(TM a) {
   }

   private static 0cp Xktjz5lou9() {
      return 0bF.field_h;
   }

   private static 0a a3UIbia1vF(0bl var0) {
      return var0.bot;
   }

   private static 0a zKQhO2O4B9(0bl var0) {
      return var0.bot;
   }

   private static MJ HszNn2ygWd(0f var0) {
      return var0.inventory;
   }

   public void handleWindowItems(Vi a) {
      0f b = 5QZN11lZe6(LIxaI5Dg2U(this));
      if (a.getWindowId() == 0) {
         oyhyiLWVWJ(b).setAll(a.getItemStacks());
      } else {
         ICa1T044aL(b).setAll(a.getItemStacks());
      }

   }

   private static void aiY9XhHu82(0f var0, double var1) {
      var0.motionY = var1;
   }

   private static 0f lBIb5dkJ6S(0a var0) {
      return var0.player;
   }

   public void handleSetSlot(UJ b) {
      Qy c = b.getStack();
      int d = b.getSlot();
      if (b.getWindowId() == (-27995 ^ -18920 ^ 5193 ^ -12533)) {
         g9iyrYq2cC(ez0Qolvjnn(OcCMJIadTM(this))).setItemStack(c);
      } else if (b.getWindowId() == (-18914 ^ -15613 ^ 17111 ^ -14284)) {
         ewPWXjwmWI(KyLlQGQIt4(Wobb8vfNd5(this))).setInventorySlotContents(d, c);
      } else if (b.getWindowId() == 0 && b.getSlot() >= (15719 ^ -26077 ^ 6201 ^ -16551) && d < (29879 ^ -2913 ^ 4485 ^ -28288)) {
         if (!c.isEmpty()) {
            Qy a = EpYlldHdC6(cyEUilNwSq(HGPF6MEPk6(this))).getSlot(d).getStack();
            if (a.isEmpty() || a.getCount() < c.getCount()) {
               c.setAnimationsToGo(276 ^ -8957 ^ 22699 ^ -31559);
            }
         }

         1GqJ3m90lk(lBIb5dkJ6S(jxzqaFGsit(this))).putStackInSlot(d, c);
      } else if (b.getWindowId() == ic6qnUtFMO(GAqLqyajo1(yi6EujrNre(4HGAPSaSwL(this))))) {
         uugTQJonv3(CbFv4zJtr4(wAVagVL1aA(this))).putStackInSlot(d, c);
      }

   }

   private static 0a uAmWdMwwRv(0bl var0) {
      return var0.bot;
   }

   private static 0a Wobb8vfNd5(0bl var0) {
      return var0.bot;
   }

   private static void itTjmcUBtl(0a var0, String var1) {
      var0.windowTitle = var1;
   }

   private static 0a SQQoVovcUb(0bl var0) {
      return var0.bot;
   }

   private static 0ct WaY72BlcTl() {
      return 0bD.field_f;
   }

   private static 0bo OLjtLY2ANA(0a var0) {
      return var0.world;
   }

   private static 0f _2fq9aBj2U/* $FF was: 72fq9aBj2U*/(0a var0) {
      return var0.player;
   }

   private static 0bi uxGB39BabJ(0bl var0) {
      return var0.netManager;
   }

   public void handleChangeGameState(TC a) {
      int b = a.getGameState();
      float c = a.getValue();
      int d = MathHelper.floor(c + Float.intBitsToFloat(258242 ^ 29208 ^ 253652 ^ 318609308 ^ 9716 ^ 223265 ^ 256089 ^ 771583518));
      if (b == (359 ^ -4646 ^ 3898 ^ -7290)) {
         JOnYluPTTA(ITyU6kn8h5(this)).getWorldInfo().setRaining((boolean)(31112 ^ -31022 ^ 18404 ^ -18241));
         W6YuoQZ1L9(bY2SBhZdVx(this)).setRainStrength(Float.intBitsToFloat(100517 ^ 28285 ^ 126256 ^ -1939701078 ^ 11398 ^ '\udd02' ^ 3477 ^ -1939704493));
      } else if (b == (27165 ^ -12875 ^ 6858 ^ -17056)) {
         Jujk9LbQSP(wV60pGh2yW(this)).getWorldInfo().setRaining((boolean)(3324 ^ -24675 ^ 9446 ^ -18553));
         ZkHIcVQqqA(wgw22b2tyL(this)).setRainStrength(Float.intBitsToFloat(15712 ^ 229116 ^ 240650 ^ -1964674672 ^ 237547 ^ 221181 ^ 4460 ^ -1251651716));
      } else if (b == (2513 ^ -14103 ^ 14027 ^ -2064)) {
         64068Gv2WA(WhxF28kZ7t(this)).setGameType(bbb.getByID(d));
      } else if (b == (21375 ^ -15742 ^ 29253 ^ -7236)) {
         if (d == 0) {
            this.sendPacket(new SI(LYAL06jTRd()));
         }
      } else if (b == (21893 ^ -19894 ^ 30424 ^ -28400)) {
         fsLAyeNLXE(eQJG6l7uCf(this)).setRainStrength(c);
      } else if (b == (10337 ^ -3372 ^ 15581 ^ -6560)) {
         sX9LlDAlg4(FSSqPRfCaq(this)).setThunderStrength(c);
      }

   }

   private static 0a FlQGALAcjV(0bl var0) {
      return var0.bot;
   }

   public void handleEntityAttach(TW a) {
      Ig b = c7p2T9vH4F(QrFk8Yx3Mf(this)).getEntityByID(a.getEntityId());
      Ig c = wbqbxm64dw(6yDV20kJMO(this)).getEntityByID(a.getVehicleEntityId());
      if (b instanceof Iu) {
         if (c != null) {
            ((Iu)b).setLeashHolder(c, (boolean)(14580 ^ -24506 ^ 3513 ^ -27381));
         } else {
            ((Iu)b).clearLeashed((boolean)(23667 ^ -20291 ^ 10611 ^ -14915), (boolean)(29884 ^ -8718 ^ 25488 ^ -13602));
         }
      }

   }

   private static 0bo PyO9XflgeT(0a var0) {
      return var0.world;
   }

   public void handleSetPassengers(UI c) {
      Ig d = q1a2XkD8Dt(aL2d70tuO4(this)).getEntityByID(c.getEntityId());
      if (d != null) {
         d.removePassengers();
         int[] var3 = c.getPassengerIds();
         int var4 = var3.length;

         for(int var5 = 24139 ^ -22674 ^ 9545 ^ -9108; var5 < var4; ++var5) {
            int b = var3[var5];
            Ig a = 7bji7b7MWY(J7dN4j4zDE(this)).getEntityByID(b);
            if (a != null) {
               a.startRiding(d, (boolean)(30490 ^ -8288 ^ 13091 ^ -25704));
            }
         }
      }

   }

   private static 0bo _AIHRxNaSh/* $FF was: 2AIHRxNaSh*/(0a var0) {
      return var0.world;
   }

   private static 0a mafCJHr5Jw(0bl var0) {
      return var0.bot;
   }

   private static 0bo A9DV4FknVT(0a var0) {
      return var0.world;
   }

   private static void nqvv1m6fLn(0f var0, String var1) {
      var0.currentContainerName = var1;
   }

   private static Qy xrJBwyVXiN() {
      return Qy.EMPTY;
   }

   public void handleUpdateHealth(Vd a) {
      IuCQeXaqS9(hd6nOTjsb9(this)).setPlayerSPHealth(a.getHealth());
      eV4Jlkvs1u(1W6OlVQDNf(this)).getFoodStats().setFoodLevel(a.getFoodLevel());
      n1lyxSmPk3(Mnk6o9BQGl(this)).getFoodStats().setFoodSaturationLevel(a.getSaturationLevel());
   }

   private static Container GAqLqyajo1(0f var0) {
      return var0.openContainer;
   }

   private static 0bo AXD5QUYiL5(0a var0) {
      return var0.world;
   }

   private static EnumHand _LPxjfQNYt/* $FF was: 5LPxjfQNYt*/() {
      return EnumHand.OFF_HAND;
   }

   private static 0a ev14cbdaWj(0bl var0) {
      return var0.bot;
   }

   private static 0f W4JheIg0K2(0a var0) {
      return var0.player;
   }

   private static 0bo VnG7VVjRXy(0a var0) {
      return var0.world;
   }

   public void handleSpawnMob(UO e) {
      double f = e.getX();
      double g = e.getY();
      double h = e.getZ();
      float i = (float)(e.getYaw() * (12792 ^ -23200 ^ 14635 ^ -21285)) / Float.intBitsToFloat(9126 ^ '\ud9fc' ^ 7063 ^ -2133105969 ^ 6166 ^ '駻' ^ 13080 ^ -1017433609);
      float j = (float)(e.getPitch() * (28693 ^ -23146 ^ 31200 ^ -21237)) / Float.intBitsToFloat(1033895 ^ 1022059 ^ 9603 ^ -1002979446 ^ 1032576 ^ 985805 ^ 17611 ^ -2018030781);
      Iw k = (Iw)Ir.createEntityByID(e.getEntityType(), PyO9XflgeT(rM4WJPqva1(this)));
      if (k != null) {
         Iz.updateServerPosition(k, f, g, h);
         tULxDWALAL(k, (float)(e.getHeadPitch() * (32561 ^ -5263 ^ 4874 ^ -31198)) / Float.intBitsToFloat(30358 ^ 111452 ^ 16761 ^ -776305550 ^ 23005 ^ 129625 ^ 8471 ^ -1841659310));
         r44QzDoQgj(k, (float)(e.getHeadPitch() * (27259 ^ -28229 ^ 25713 ^ -24871)) / Float.intBitsToFloat(26389 ^ '鷲' ^ 12805 ^ -2138095167 ^ 2068284 ^ 2035525 ^ 616 ^ -1022424270));
         Ig[] c = k.getParts();
         if (c != null) {
            int b = e.getEntityID() - k.getEntityId();
            Ig[] var13 = c;
            int var14 = c.length;

            for(int var15 = 26555 ^ -29184 ^ 387 ^ -6088; var15 < var14; ++var15) {
               Ig a = var13[var15];
               a.setEntityId(a.getEntityId() + b);
            }
         }

         k.setEntityId(e.getEntityID());
         k.setUniqueId(e.getUniqueId());
         k.setPositionAndRotation(f, g, h, i, j);
         o3mZkVnlEB(k, (double)((float)e.getVelocityX() / Float.intBitsToFloat(2726 ^ '\ude50' ^ 1618 ^ 948138802 ^ 21811 ^ '츫' ^ 515 ^ 2105096333)));
         ELIqL1Qcab(k, (double)((float)e.getVelocityY() / Float.intBitsToFloat(125116 ^ 24161 ^ 124044 ^ -660819343 ^ 7920 ^ 106410 ^ 113592 ^ -1654203710)));
         ljUNqGeYQz(k, (double)((float)e.getVelocityZ() / Float.intBitsToFloat(125081 ^ 83782 ^ 13293 ^ -1342940903 ^ 6298 ^ 101754 ^ 117848 ^ -368141165)));
         As4I2ckjgO(SQQoVovcUb(this)).addEntityToWorld(e.getEntityID(), k);
         List<Ru<?>> d = e.getDataManagerEntries();
         if (d != null) {
            k.getDataManager().setEntryValues(d);
         }
      }

   }

   private static Container ICa1T044aL(0f var0) {
      return var0.openContainer;
   }

   private static Ux ciNvBn4PLY() {
      return Ux.Y_ROT;
   }

   private static void KAc496YevF(Container var0, int var1) {
      var0.windowId = var1;
   }

   private static 0a WhxF28kZ7t(0bl var0) {
      return var0.bot;
   }

   private static 0a rJLGFeV9Vm(0bl var0) {
      return var0.bot;
   }

   private static 0bo JOnYluPTTA(0a var0) {
      return var0.world;
   }

   private static 0bo pLyiFjgjzT(0a var0) {
      return var0.world;
   }

   private static 0bo fvYVkFsoNI(0a var0) {
      return var0.world;
   }

   private static 0a dclbW7RlLO(0bl var0) {
      return var0.bot;
   }

   public void handleDisconnect(TP b) {
      0cG.method_bwd().method_bwh().forEach((bx) -> {
         String var10001 = cYnJb44IaB("ۦۥ۔ۖ۞ېہ۱ۜۆۖۚۛۛېۖہ");
         Object[] var10002 = new Object[29293 ^ -32669 ^ 14466 ^ -13682];
         var10002[12077 ^ -1857 ^ 28851 ^ -22751] = LgiAkDRFVX(this);
         var10002[8827 ^ -27567 ^ 27223 ^ -9092] = b;
         bx.method_bCr(var10001, var10002);
      });
      if (Xktjz5lou9().method_bna()) {
         String var10002 = cYnJb44IaB("ۗۚہڛۅۙ۔یڛۑۜۆۖۚۛۛېۖہ");
         Object[] var10003 = new Object[26514 ^ -6482 ^ 3329 ^ -29636];
         var10003[23154 ^ -1799 ^ 2041 ^ -23182] = 4ftkr8UrNd(this).getNickname();
         TextComponentString a = new TextComponentString(0cT.method_byW(var10002, var10003));
         a.appendSibling(b.getReason());
         0ek.addMessage((ITextComponent)a);
      }

      2Gnz0AwBwY(this).closeChannel();
      deeMN8krhw(this).disconnect();
   }

   private static 0bo eTSOqO6lU9(0a var0) {
      return var0.world;
   }

   private static 0bo nLbixja4gW(0a var0) {
      return var0.world;
   }

   private static ML tesYyBWMHy(0f var0) {
      return var0.capabilities;
   }

   private static 0a Nz6P79FouO(0bl var0) {
      return var0.bot;
   }

   private static Wo Iu2cGQLmJd() {
      return Wo.DUMMY;
   }

   public void handleChat(TD b) {
      0cG.method_bwd().method_bwh().forEach((bx) -> {
         String var10001 = cYnJb44IaB("ۦۥ۔ۖ۞ېہ۶\u06dd۔ہ");
         Object[] var10002 = new Object[19826 ^ -23529 ^ 25534 ^ -29991];
         var10002[2564 ^ -28720 ^ 6044 ^ -28088] = rm7Iv481q3(this);
         var10002[1183 ^ -16570 ^ 21313 ^ -5991] = b;
         bx.method_bCr(var10001, var10002);
      });
      if (qEM3nAZw1u().method_bna()) {
         String var10002 = cYnJb44IaB("ۗۚہڛۅۙ۔یڛۖ\u06dd۔ہ");
         Object[] var10003 = new Object[13296 ^ -1727 ^ 15571 ^ -2461];
         var10003[25433 ^ -10505 ^ 21547 ^ -7803] = Y21rv6GtYh(this).getNickname();
         0ek.addMessage((new TextComponentString(0cT.method_byW(var10002, var10003))).appendSibling(b.getChatComponent()));
      }

      String c = 0X.stripColor(b.getChatComponent().getFormattedText());
      GqynMr37xA(this).setParameter(cYnJb44IaB("ۙ۔ۆہۘېۆۆ۔ےې"), c);
      if ((c.contains(cYnJb44IaB("ʏʅʊ˲")) || c.contains(cYnJb44IaB("ʋ˷ʇʀ˷")) || c.contains(cYnJb44IaB("ۖ۔ۅہۖ\u06dd")) || c.contains(cYnJb44IaB("۔ۛۆۂېۇ")) || c.contains(cYnJb44IaB("ڕʏʋʁ")) || c.contains(cYnJb44IaB("ʏʅ˵˷ʍʈʏʍ")) || c.contains(cYnJb44IaB("ʈʀʇʀ˵ʈ˾ʀڕ˴ʍʉʇʋʎ˾")) || c.contains(cYnJb44IaB("ʈʀʇʀ˵ʈʋڔ"))) && (c.contains(cYnJb44IaB("ʈʀʇʀ˵ʈ")) || c.contains(cYnJb44IaB("ʈʀʊ˵ʅʇʍʎ˹ʈ")) || c.contains(cYnJb44IaB("ʈʀڕʊ˵ʅʇʍʎ˹ʈ")) || c.contains(cYnJb44IaB("ʀ˼ʀڕ˵ʅʂ")) || c.contains(cYnJb44IaB("ہۇیڕ۔ے۔ۜۛ")))) {
         a6HHKNE7eH(SJeEWGtVyY(this)).reset();
         bao7s1LjsV(this).setParameter(cYnJb44IaB("ۖ۔ۅہۖ\u06dd۔ۑېہېۖہېۑ"), Boolean.valueOf((boolean)(687 ^ -4057 ^ 2123 ^ -1341)));
      }

      if (0bJ.method_Su(c)) {
         if (0bz.method_Qm().method_Qs().method_bxY(0bJ.class).method_bBh()) {
            if (!IrYeDdpBVk(this).getBooleanParameter(cYnJb44IaB("ۂېۗۑېہېۖہېۑ"))) {
               String a = cYnJb44IaB("\u06ddہہۅۆڏښښ") + c.split(cYnJb44IaB("ڏښښ"))[30855 ^ -20443 ^ 25095 ^ -21852].split(cYnJb44IaB("ڕ"))[9081 ^ -26300 ^ 2939 ^ -20154];
               AFJLldDP9b(this).setParameter(cYnJb44IaB("ۂېۗۑېہېۖہېۑ"), Boolean.valueOf((boolean)(21655 ^ -12752 ^ 29206 ^ -5968)));
               0cG.method_bwd().method_bwj().method_LD(new 0da(a, ZTLjVlJzVJ(this)));
            }
         } else if (JiZOe5elqv().method_bna()) {
            0cG.method_bwd().method_bwe().remove(0Ow56leQSe(this).getProxy());
            rVGiUKDIef(this).stopBot();
         }
      }

      if (0bz.method_Qm().method_Qs().method_bxY(0bD.class).method_bBh() && 0bD.method_RD(c) && !L6eiOHZChO(this).getBooleanParameter(cYnJb44IaB("۔ۀہ\u06ddۚۇۜۏ۔ہۜۚۛ"))) {
         0bz.method_Qm().method_Qp().addScheduler(UwV1WHTAF3(this)::auth, (long)WaY72BlcTl().method_bnH());
         2wH5S71ajJ(this).setParameter(cYnJb44IaB("۔ۀہ\u06ddۚۇۜۏ۔ہۜۚۛ"), Boolean.valueOf((boolean)(30695 ^ -30385 ^ 6703 ^ -7034)));
      }

      if (WwFJvAIHoA().method_bnq().equalsIgnoreCase(cYnJb44IaB("ۚۛ۸ېۆۆ۔ےې")) && c.contains(Zra4aHSlAT().method_bnP())) {
         645qTwwViG(this).getFunction().method_cR(new 0E(tgqiQwbn1y(this)));
      }

   }

   private static 0bo W65LXDOoTy(0a var0) {
      return var0.world;
   }

   private static void STVXAXWSiC(0f var0, double var1) {
      var0.motionX = var1;
   }

   private static 0cp CcLqo54XqS() {
      return 0bH.field_l;
   }

   private static void AVuKnGXqVe(Ig var0, long var1) {
      var0.serverPosX = var1;
   }

   private static 0bo _6eiFyKYt5/* $FF was: 16eiFyKYt5*/(0a var0) {
      return var0.world;
   }

   public void handleHeldItemChange(Uh a) {
      if (MJ.isHotbar(a.getHeldItemHotbarIndex())) {
         d9BoYwir9z(HszNn2ygWd(AdUFrpjJuV(W5QB0j6M9W(this))), a.getHeldItemHotbarIndex());
      }

   }

   private static 0a aL2d70tuO4(0bl var0) {
      return var0.bot;
   }

   private static 0bo _bji7b7MWY/* $FF was: 7bji7b7MWY*/(0a var0) {
      return var0.world;
   }

   private static 0a _7nSB0W634/* $FF was: 97nSB0W634*/(0bl var0) {
      return var0.bot;
   }

   private static 0bo _f6Ew2TCV1/* $FF was: 1f6Ew2TCV1*/(0a var0) {
      return var0.world;
   }

   private static 0a f6S4LbnUOY(0bl var0) {
      return var0.bot;
   }

   private static 0a d1R29nHjtG(0bl var0) {
      return var0.bot;
   }

   private static 0f u3qzCqr98d(0a var0) {
      return var0.player;
   }

   public void handleEffect(TR a) {
   }

   private static 0a _qAMBDVAqs/* $FF was: 9qAMBDVAqs*/(0bl var0) {
      return var0.bot;
   }

   private static 0bo FcptGRY5vB(0a var0) {
      return var0.world;
   }

   private static 0bo rqYQ1tD29N(0a var0) {
      return var0.world;
   }

   private static bil AQv9aMTubL(0bo var0) {
      return var0.provider;
   }

   private static 0a q1hRzliRkd(0bl var0) {
      return var0.bot;
   }

   public void handleRespawn(UD c) {
      int d = W4JheIg0K2(OFjGiW6gTw(this)).getEntityId();
      String e = 26ZbiLCyqD(GFLUZG7qq9(this)).getServerBrand();
      int f = FAv5XDBGwI(N0bvfj7hg6(this)).getMaxPlayers();
      if (c.getDimensionID() != r2gWqAd4dj(vq4eM0LMWW(yQcpejCAWG(this)))) {
         BpDl4jqKss(this, (boolean)(6745 ^ -2820 ^ 30149 ^ -25760));
         Ws a = 3qSGjP324l(i6djwONvlz(this)).getScoreboard();
         biw b = new biw(0L, c.getGameType(), (boolean)(32334 ^ -21441 ^ 24707 ^ -19725), 48Ad4aQ7vV(NHkoGDA96L(this)).getWorldInfo().isHardcoreModeEnabled(), c.getWorldType());
         OLjtLY2ANA(PVE6MO6WP3(this)).setWorldScoreboard(a);
         DYkY7bm2v5(oTSeYOANte(this)).loadWorld(new 0bo(DEVdSoDMyg(this), b, c.getDimensionID(), c.getDifficulty(), f));
         BMPhHs3T4y(WqxdSG1KRw(LXjFOQklGB(this)), c.getDimensionID());
      }

      YebcboaBNp(T6GQYwU8nw(this)).setDimensionAndSpawnPlayer(c.getDimensionID());
      8EXiC1TSJT(AB3TeOHNer(this)).setGameType(c.getGameType());
      eLKLl7Wada(N4nJB6DvGG(this)).setEntityId(d);
      93nrD98jbK(97nSB0W634(this)).setServerBrand(e);
   }

   private static 0a OjSFPqnrmO(0bl var0) {
      return var0.bot;
   }

   public void handleCooldown(TL a) {
      if (a.getTicks() == 0) {
         72fq9aBj2U(UM1Z7IIzIA(this)).getCooldownTracker().removeCooldown(a.getItem());
      } else {
         vdReggC2Q7(oUFvStHGQQ(this)).getCooldownTracker().setCooldown(a.getItem(), a.getTicks());
      }

   }

   private static 0bo aIV0jFjLT4(0a var0) {
      return var0.world;
   }

   private static 0a ZTLjVlJzVJ(0bl var0) {
      return var0.bot;
   }

   public void sendPacket(Sz<?> a) {
      sXztV1VxF2(this).sendPacket(a);
   }

   private static int _Ko60vbxeB/* $FF was: 5Ko60vbxeB*/(Container var0) {
      return var0.windowId;
   }

   private static 0a WdDzu3Dlgv(0bl var0) {
      return var0.bot;
   }

   private static void FjOI6nwyBd(Container var0, int var1) {
      var0.windowId = var1;
   }

   private static 0f VZ9dMbWi9y(0a var0) {
      return var0.player;
   }

   public void handleCamera(TB a) {
   }

   private static 0f Z2eGIND2fv(0a var0) {
      return var0.player;
   }

   private static double WJ9OO7WOrt(0f var0) {
      return var0.posX;
   }

   private static 0a ItqlGBDJ64(0bl var0) {
      return var0.bot;
   }

   private static 0a eQJG6l7uCf(0bl var0) {
      return var0.bot;
   }

   private static 0a IrYeDdpBVk(0bl var0) {
      return var0.bot;
   }

   private static 0bo HoHWNg7kjY(0a var0) {
      return var0.world;
   }

   private static 0a i6djwONvlz(0bl var0) {
      return var0.bot;
   }

   private static 0a TKr6oQT8Cb(0bl var0) {
      return var0.bot;
   }

   private static 0a _I344OgDB8/* $FF was: 9I344OgDB8*/(0bl var0) {
      return var0.bot;
   }

   public void handlePlayerListItem(Uw a) {
   }

   private static 0bo IazVDlStIN(0a var0) {
      return var0.world;
   }

   private static 0a TW71rXjJF6(0bl var0) {
      return var0.bot;
   }

   private static 0a AB3TeOHNer(0bl var0) {
      return var0.bot;
   }

   private static void zN6wBQJG6L(MO var0, Ig var1) {
      var0.shootingEntity = var1;
   }

   private static double qWtHSeA7ai(Ig var0) {
      return var0.posY;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String cYnJb44IaB(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 32608 ^ -25119 ^ 26365 ^ -31620; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 3741 ^ -12055 ^ 19074 ^ -28093));
      }

      return var1.toString();
   }

   private static 0bo vjQKCZENl9(0a var0) {
      return var0.world;
   }

   private static 0a PVE6MO6WP3(0bl var0) {
      return var0.bot;
   }

   public void handleBlockAction(Ty a) {
      8D9ZZn2n2u(oTytr7x2zv(this)).addBlockEvent(a.getBlockPosition(), a.getBlockType(), a.getData1(), a.getData2());
   }

   private static 0bo Ztk67LbU6s(0a var0) {
      return var0.world;
   }

   private static 0a _F9bvWjdgY/* $FF was: 8F9bvWjdgY*/(0bl var0) {
      return var0.bot;
   }

   private static 0a MbewjD97AD(0bl var0) {
      return var0.bot;
   }

   private static 0a Rtv4TQvxFe(0bl var0) {
      return var0.bot;
   }

   private static 0a QeJgnPnBCv(0bl var0) {
      return var0.bot;
   }

   private static 0bo OGGkXJLYaH(0a var0) {
      return var0.world;
   }

   private static MU GWFYJWwgLE(0f var0) {
      return var0.fishEntity;
   }

   private static 0d iS5FpvAkqd(0a var0) {
      return var0.mc;
   }

   private static Ux FvxzGvIbht() {
      return Ux.X_ROT;
   }

   private static 0a _oKJhJyCoD/* $FF was: 1oKJhJyCoD*/(0bl var0) {
      return var0.bot;
   }

   private static 0a _sJNB44ne2/* $FF was: 2sJNB44ne2*/(0bl var0) {
      return var0.bot;
   }

   private static 0d YebcboaBNp(0a var0) {
      return var0.mc;
   }

   private static 0a rM4WJPqva1(0bl var0) {
      return var0.bot;
   }

   private static 0bo _qSGjP324l/* $FF was: 3qSGjP324l*/(0a var0) {
      return var0.world;
   }

   private static 0bo nn8cLOiOjj(0a var0) {
      return var0.world;
   }

   private static 0f KkGcoyUBt4(0a var0) {
      return var0.player;
   }

   private static 0a cSnbQ9U07j(0bl var0) {
      return var0.bot;
   }

   private static 0f fDiu9ycVnv(0a var0) {
      return var0.player;
   }

   private static 0bi UwcHDtJOZh(0bl var0) {
      return var0.netManager;
   }

   private static double NdSWIMITJG(0f var0) {
      return var0.posZ;
   }

   private static 0d LDsLJfRM5v(0a var0) {
      return var0.mc;
   }

   private static 0i _4068Gv2WA/* $FF was: 64068Gv2WA*/(0a var0) {
      return var0.controller;
   }

   private static 0i _EXiC1TSJT/* $FF was: 8EXiC1TSJT*/(0a var0) {
      return var0.controller;
   }

   public void handleMultiBlockChange(Un b) {
      Um[] var2 = b.getChangedBlocks();
      int var3 = var2.length;

      for(int var4 = 18325 ^ -25089 ^ 20574 ^ -30156; var4 < var3; ++var4) {
         Um a = var2[var4];
         FiAe77AaY4(fqNjzsfWbw(this)).invalidateRegionAndSetBlock(a.getPos(), a.getBlockState());
      }

   }

   private static 0a oTSeYOANte(0bl var0) {
      return var0.bot;
   }

   private static 0a deeMN8krhw(0bl var0) {
      return var0.bot;
   }

   private static 0a _Zo9b7eYY9/* $FF was: 6Zo9b7eYY9*/(0bl var0) {
      return var0.bot;
   }

   private static 0a _Ow56leQSe/* $FF was: 0Ow56leQSe*/(0bl var0) {
      return var0.bot;
   }

   private static 0f n1lyxSmPk3(0a var0) {
      return var0.player;
   }

   public void handleSetExperience(UH a) {
      eBPB9WDBwI(qiKNV2BBws(this)).setXPStats(a.getExperienceBar(), a.getTotalExperience(), a.getLevel());
   }

   private static 0a AdU2gh9gbe(0bl var0) {
      return var0.bot;
   }

   private static 0bo UOQAoWJPbH(0a var0) {
      return var0.world;
   }

   private static 0a T1LidHV4EY(0bl var0) {
      return var0.bot;
   }

   private static 0a ITyU6kn8h5(0bl var0) {
      return var0.bot;
   }

   private static 0a CSl0pohGn9(0bl var0) {
      return var0.bot;
   }

   private static ML SSct55AsSe(0f var0) {
      return var0.capabilities;
   }

   public void handleWindowProperty(Vj a) {
      0f b = Wl92vXa1dZ(THhK7GTYba(this));
      if (mAie7PcSfj(b) != null && tY2tf8xuJu(WyTI41NsCr(b)) == a.getWindowId()) {
         wcxo7e14Bw(b).updateProgressBar(a.getProperty(), a.getValue());
      }

   }

   private static 0cu Zra4aHSlAT() {
      return 0bC.field_d;
   }

   private static void BpDl4jqKss(0bl var0, boolean var1) {
      var0.doneLoadingTerrain = var1;
   }

   private static 0bo FQaWIwwBt6(0a var0) {
      return var0.world;
   }

   public void handleTabComplete(UU a) {
      0cG.method_bwd().method_bwh().forEach((b) -> {
         String var10001 = cYnJb44IaB("ۦۥ۔ۖ۞ېہۡ۔ۗ۶ۚۘۅۙېہې");
         Object[] var10002 = new Object[28636 ^ -28669 ^ 29396 ^ -29431];
         var10002[25014 ^ -30959 ^ 31933 ^ -26086] = HSp03eSwGt(this);
         var10002[8661 ^ -27583 ^ 29149 ^ -15288] = a;
         b.method_bCr(var10001, var10002);
      });
   }

   private static 0a LIxaI5Dg2U(0bl var0) {
      return var0.bot;
   }

   private static 0a lOTWwRYNKl(0bl var0) {
      return var0.bot;
   }

   private static void FwpdBy14XJ(0f var0, double var1) {
      var0.prevPosZ = var1;
   }

   private static 0a _Lt1xXIDGG/* $FF was: 8Lt1xXIDGG*/(0bl var0) {
      return var0.bot;
   }

   private static Ux _VqSgKbcBd/* $FF was: 1VqSgKbcBd*/() {
      return Ux.Y;
   }

   private static 0f _3nrD98jbK/* $FF was: 93nrD98jbK*/(0a var0) {
      return var0.player;
   }

   private static 0bo Jujk9LbQSP(0a var0) {
      return var0.world;
   }

   private static 0a qbpTMHHe2K(0bl var0) {
      return var0.bot;
   }

   private static 0a OFjGiW6gTw(0bl var0) {
      return var0.bot;
   }

   private static 0a eVSgY2vlUR(0bl var0) {
      return var0.bot;
   }

   public 0bi getNetworkManager() {
      return QuOyG4anJH(this);
   }

   private static Container _OYGZLd0Yy/* $FF was: 8OYGZLd0Yy*/(0f var0) {
      return var0.openContainer;
   }

   private static 0f qQSwaOywTt(0a var0) {
      return var0.player;
   }

   private static 0bo LVq9qpfqDo(0a var0) {
      return var0.world;
   }

   private static 0a HGPF6MEPk6(0bl var0) {
      return var0.bot;
   }

   private static 0a _d1gKWR9br/* $FF was: 5d1gKWR9br*/(0bl var0) {
      return var0.bot;
   }

   public void handleRemoveEntityEffect(UB a) {
      Ig b = a.getEntity(RGgLTvzCct(wj9J2YZABT(this)));
      if (b instanceof Iw) {
         ((Iw)b).removeActivePotionEffect(a.getPotion());
      }

   }

   public void handleScoreboardObjective(UE c) {
      Ws d = 0w7YwDDeYt(vy4OTjLyYl(this)).getScoreboard();
      Wz b;
      if (c.getAction() == 0) {
         b = d.addScoreObjective(c.getObjectiveName(), Iu2cGQLmJd());
         b.setDisplayName(c.getObjectiveValue());
         b.setRenderType(c.getRenderType());
      } else {
         b = d.getObjective(c.getObjectiveName());
         if (c.getAction() == (4365 ^ -12408 ^ 24494 ^ -32470)) {
            if (b != null) {
               d.removeObjective(b);
            }
         } else if (c.getAction() == (7473 ^ -24210 ^ 14589 ^ -31584) && b != null) {
            b.setDisplayName(c.getObjectiveValue());
            b.setRenderType(c.getRenderType());
         }
      }

   }

   private static 0f e8LJwXQKqL(0a var0) {
      return var0.player;
   }

   public void handleTimeUpdate(UW a) {
      eTSOqO6lU9(aT1aObe1nW(this)).setTotalWorldTime(a.getTotalWorldTime());
      VA8XMhZGmA(LvaLL6NBe9(this)).setWorldTime(a.getWorldTime());
   }

   private static 0a CSwaD6L4aq(0bl var0) {
      return var0.bot;
   }

   private static Container EpYlldHdC6(0f var0) {
      return var0.inventoryContainer;
   }

   private static 0bo XytrSEo4Wv(0a var0) {
      return var0.world;
   }

   private static float gmFtNpBSo6(0f var0) {
      return var0.rotationPitch;
   }

   public void handleKeepAlive(Uj a) {
      this.sendPacket(new ST(a.getId()));
   }

   private static 0a ijj2eAr1O0(0bl var0) {
      return var0.bot;
   }

   private static 0a LgiAkDRFVX(0bl var0) {
      return var0.bot;
   }

   private static long dl8a4m5Asp(Ig var0) {
      return var0.serverPosX;
   }

   private static 0a tgqiQwbn1y(0bl var0) {
      return var0.bot;
   }

   private static void jy6PrfDg93(0f var0, double var1) {
      var0.motionY = var1;
   }

   private static boolean TDcpg3rGYo(0bl var0) {
      return var0.doneLoadingTerrain;
   }

   private static 0a THhK7GTYba(0bl var0) {
      return var0.bot;
   }

   private static 0a a2nHH4gQjr(0bl var0) {
      return var0.bot;
   }

   private static ML niQcakWDQY(0f var0) {
      return var0.capabilities;
   }

   private static 0a VdZZreJbhZ(0bl var0) {
      return var0.bot;
   }

   public void handleSpawnExperienceOrb(UM a) {
   }

   private static double tsQB9NBRvf(0f var0) {
      return var0.motionY;
   }

   private static 0a bjyuZL3NpV(0bl var0) {
      return var0.bot;
   }

   private static 0f eLKLl7Wada(0a var0) {
      return var0.player;
   }

   private static 0a ztRfWQSlcA(0bl var0) {
      return var0.bot;
   }

   public void handlePlayerPosLook(Uy a) {
      0f b = 9DADAR4tq9(zKQhO2O4B9(this));
      double c = a.getX();
      double d = a.getY();
      double e = a.getZ();
      float f = a.getYaw();
      float g = a.getPitch();
      if (a.getFlags().contains(63osLAT569())) {
         c += Iq1PHiTWwQ(b);
      } else {
         STVXAXWSiC(b, Double.longBitsToDouble(-2771186229693576185L ^ -2771186229693576185L));
      }

      if (a.getFlags().contains(1VqSgKbcBd())) {
         d += eKH3iqaCjA(b);
      } else {
         aiY9XhHu82(b, Double.longBitsToDouble(5271139988967832761L ^ 5271139988967832761L));
      }

      if (a.getFlags().contains(gOwJReOtXT())) {
         e += ZZwNrR7LVy(b);
      } else {
         04DK6XOWo2(b, Double.longBitsToDouble(7435283694322127108L ^ 7435283694322127108L));
      }

      if (a.getFlags().contains(FvxzGvIbht())) {
         g += 9pVv3UVIfR(b);
      }

      if (a.getFlags().contains(ciNvBn4PLY())) {
         f += lclYreknde(b);
      }

      b.setPositionAndRotation(c, d, e, f, g);
      uxGB39BabJ(this).sendPacket(new SK(a.getTeleportId()));
      8QGL7JbRJw(this).sendPacket(new SW(WJ9OO7WOrt(b), v2uNCI1hIW(b.getEntityBoundingBox()), I29HLmNVaI(b), eNyev7bWev(b), gmFtNpBSo6(b), (boolean)(6599 ^ -20423 ^ 21727 ^ -735)));
      if (!TDcpg3rGYo(this)) {
         8W23AftzfU(Uog40rDYCL(cf0NnwWZ81(this)), DX4irQ42NO(e8LJwXQKqL(f6S4LbnUOY(this))));
         aiSY8m3NvQ(bB7HoSiNpB(9GQrk9Xv4S(this)), m1lnTW6qa1(1USL77Kvde(vt9Pr9dLhI(this))));
         FwpdBy14XJ(GhIO8l9DX9(NF2KrBepa9(this)), NdSWIMITJG(SWXEMVAvup(lOTWwRYNKl(this))));
         J6GF0e441a(this, (boolean)(2442 ^ -2403 ^ 7611 ^ -7507));
      }

   }

   private static void gTybTuhiVe(Ig var0, float var1) {
      var0.rotationPitch = var1;
   }

   private static 0a PNlJ7O0QzI(0bl var0) {
      return var0.bot;
   }

   private static 0a C2VEapiYmt(0bl var0) {
      return var0.bot;
   }

   private static void LvFaRWDrgI(ML var0, boolean var1) {
      var0.isFlying = var1;
   }

   public void handleMaps(Uk b) {
      bhE c = PT.loadMapData(b.getMapId(), u99PXbkoe2(LlD1fl7zHB(this)));
      if (c == null) {
         String a = cYnJb44IaB("ۘ۔ۅ۪") + b.getMapId();
         c = new bhE(a);
         W65LXDOoTy(DODp7JQAJY(this)).setData(a, c);
      }

      b.setMapdataTo(c);
   }

   private static 0a T6GQYwU8nw(0bl var0) {
      return var0.bot;
   }

   private static 0f eV4Jlkvs1u(0a var0) {
      return var0.player;
   }

   private static Container WyTI41NsCr(0f var0) {
      return var0.openContainer;
   }

   private static 0bo Ex7xR4JdT7(0a var0) {
      return var0.world;
   }

   public void handleJoinGame(Ui a) {
      if (aiPqqs4TL7().method_bnr(cYnJb44IaB("۴ۀہۚ")) && a.getMaxPlayers() > (12841 ^ -20617 ^ 21479 ^ -12611)) {
         8Lt1xXIDGG(this).setParameter(cYnJb44IaB("ۖ۔ۖ\u06ddېۑ"), Boolean.valueOf((boolean)(25808 ^ -23333 ^ 27175 ^ -21971)));
      }

      0cG.method_bwd().method_bwg().method_j().method_bL(Rtv4TQvxFe(this));
      2lIFjqP4Iq(cSnbQ9U07j(this), new 0i(MbewjD97AD(this)));
      biw b = new biw(0L, a.getGameType(), (boolean)(31375 ^ -8303 ^ 6826 ^ -16459), a.isHardcoreMode(), a.getWorldType());
      iS5FpvAkqd(nZIq52n2r4(this)).loadWorld(new 0bo(y1oEtpuYnG(this), b, a.getDimension(), a.getDifficulty(), a.getMaxPlayers()));
      SrnRV6u2Ab(u3qzCqr98d(2sJNB44ne2(this)), a.getDimension());
      qQSwaOywTt(AdU2gh9gbe(this)).setEntityId(a.getPlayerId());
      fDiu9ycVnv(QwFaerb6bB(this)).setReducedDebug(a.isReducedDebugInfo());
      vjQr9BDhac(dclbW7RlLO(this)).setGameType(a.getGameType());
      this.sendPacket(new SG(cYnJb44IaB("ۇۀ۪ۧ۠"), 21297 ^ -9264 ^ 11847 ^ -22875, nTmXoRJBUW(), (boolean)(25507 ^ -16284 ^ 6937 ^ -18209), 3998 ^ -25379 ^ 7592 ^ -29036, NJEWSVQAqq()));
      UwcHDtJOZh(this).sendPacket(new SN(cYnJb44IaB("۸۶ۉ۷ۇ۔ۛۑ"), (new SA(Unpooled.buffer())).writeString(je.getClientModName())));
      if (nijdvYjagi().method_bnq().equalsIgnoreCase(cYnJb44IaB("ۚۛۿۚۜۛ"))) {
         mafCJHr5Jw(this).getFunction().method_cR(new 0E(yUDIisFun3(this)));
      }

      0cG.method_bwd().method_bwh().forEach((bx) -> {
         String var10001 = cYnJb44IaB("ۦۥ۔ۖ۞ېہۿۚۜۛ۲۔ۘې");
         Object[] var10002 = new Object[20983 ^ -23692 ^ 23892 ^ -20523];
         var10002[14482 ^ -20406 ^ 7868 ^ -27036] = LqYvK2br3W(this);
         var10002[32746 ^ -9008 ^ 15916 ^ -25321] = a;
         bx.method_bCr(var10001, var10002);
      });
   }

   private static void BMPhHs3T4y(0f var0, int var1) {
      var0.dimension = var1;
   }

   private static 0bo zb2rNDB3q9(0a var0) {
      return var0.world;
   }

   private static 0a Dep0LpA9Ts(0bl var0) {
      return var0.bot;
   }

   public void handleEntityProperties(Uc e) {
      Ig f = Ex7xR4JdT7(5d1gKWR9br(this)).getEntityByID(e.getEntityId());
      if (f != null) {
         if (!(f instanceof Iw)) {
            throw new IllegalStateException(cYnJb44IaB("ۦېۇۃېۇڕہۇۜېۑڕہۚڕۀۅۑ۔ہېڕ۔ہہۇۜۗۀہېۆڕۚۓڕ۔ڕۛۚۛژۙۜۃۜۛےڕېۛہۜہیڕڝ۔ۖہۀ۔ۙۙیڏڕ") + f + cYnJb44IaB("ڜ"));
         }

         FU d = ((Iw)f).getAttributeMap();
         Iterator var4 = e.getSnapshots().iterator();

         while(var4.hasNext()) {
            Ub c = (Ub)var4.next();
            FZ b = d.getAttributeInstanceByName(c.getName());
            if (b == null) {
               b = d.registerAttribute(new Gc((FY)null, c.getName(), Double.longBitsToDouble(-975486067524963681L ^ -975486067524963681L), Double.longBitsToDouble(5660386700245016535L ^ -1045473144909652009L), Double.longBitsToDouble(6863018829325680533L ^ 2364856807156465770L)));
            }

            b.setBaseValue(c.getBaseValue());
            b.removeAllModifiers();
            Iterator var7 = c.getModifiers().iterator();

            while(var7.hasNext()) {
               FW a = (FW)var7.next();
               b.applyModifier(a);
            }
         }
      }

   }

   private static 0a JdLKezKa4W(0bl var0) {
      return var0.bot;
   }

   public void handleBlockBreakAnim(Tz a) {
      4AjuttunCv(eVSgY2vlUR(this)).sendBlockBreakProgress(a.getBreakerId(), a.getPosition(), a.getProgress());
   }

   private static 0cs aiPqqs4TL7() {
      return 0bH.field_c;
   }

   private static 0a yUDIisFun3(0bl var0) {
      return var0.bot;
   }

   private static 0a LlD1fl7zHB(0bl var0) {
      return var0.bot;
   }

   private static 0bo FAv5XDBGwI(0a var0) {
      return var0.world;
   }

   private static Qy rIG9Npef9A() {
      return Qy.EMPTY;
   }

   private static 0a y1oEtpuYnG(0bl var0) {
      return var0.bot;
   }

   private static long lLixgGJX0t(Ig var0) {
      return var0.serverPosZ;
   }

   private static 0a t8fr11Lzbf(0bl var0) {
      return var0.bot;
   }

   private static 0bo LynfZDn2Vp(0a var0) {
      return var0.world;
   }

   public void func_194307_a(Uq a) {
   }

   public void handleUseBed(Vh a) {
   }

   private static 0bo aDyokDwJ6b(0a var0) {
      return var0.world;
   }

   private static double Iq1PHiTWwQ(0f var0) {
      return var0.posX;
   }

   private static 0a _wH5S71ajJ/* $FF was: 2wH5S71ajJ*/(0bl var0) {
      return var0.bot;
   }

   private static 0a _I1qHAGYAP/* $FF was: 4I1qHAGYAP*/(0bl var0) {
      return var0.bot;
   }

   public void handleCombatEvent(TJ a) {
   }

   private static 0a Uod4tcfjSY(0bl var0) {
      return var0.bot;
   }

   private static 0bo W6YuoQZ1L9(0a var0) {
      return var0.world;
   }

   private static Container mAie7PcSfj(0f var0) {
      return var0.openContainer;
   }

   private static 0bo VA8XMhZGmA(0a var0) {
      return var0.world;
   }

   private static 0a GqynMr37xA(0bl var0) {
      return var0.bot;
   }

   private static 0a rsJQyXWbvl(0bl var0) {
      return var0.bot;
   }

   private static 0a NVpchvXSaH(0bl var0) {
      return var0.bot;
   }

   private static void wb74T8TLk1(Ig var0, long var1) {
      var0.serverPosZ = var1;
   }

   private static 0a QjNJswNWDJ(0bl var0) {
      return var0.bot;
   }

   private static 0a _W6OlVQDNf/* $FF was: 1W6OlVQDNf*/(0bl var0) {
      return var0.bot;
   }

   private static 0a rm7Iv481q3(0bl var0) {
      return var0.bot;
   }

   private static Container oyhyiLWVWJ(0f var0) {
      return var0.inventoryContainer;
   }

   private static double TlHecUBj7A(Ig var0) {
      return var0.posY;
   }

   private static 0f ez0Qolvjnn(0a var0) {
      return var0.player;
   }

   private static float lclYreknde(0f var0) {
      return var0.rotationYaw;
   }

   private static 0a UGwmi22TnD(0bl var0) {
      return var0.bot;
   }

   private static 0bo DCGvvebbCF(0a var0) {
      return var0.world;
   }

   private static Container IPQhQoytnw(0f var0) {
      return var0.openContainer;
   }

   public void handlePlayerListHeaderFooter(Us a) {
      0cG.method_bwd().method_bwh().forEach((b) -> {
         String var10001 = cYnJb44IaB("ۦۥ۔ۖ۞ېہۥۙ۔یېۇ۹ۜۆہ۽ې۔ۑېۇ۳ۚۚہېۇ");
         Object[] var10002 = new Object[31296 ^ -20032 ^ 7410 ^ -10384];
         var10002[2110 ^ -31359 ^ 5096 ^ -25001] = NVpchvXSaH(this);
         var10002[27194 ^ -24020 ^ 29488 ^ -17625] = a;
         b.method_bCr(var10001, var10002);
      });
   }

   private static 0bo _BaMlZgPWD/* $FF was: 7BaMlZgPWD*/(0a var0) {
      return var0.world;
   }

   private static 0a TlPOuhN4sI(0bl var0) {
      return var0.bot;
   }

   private static 0cs nijdvYjagi() {
      return 0bC.field_f;
   }

   private static 0a wj9J2YZABT(0bl var0) {
      return var0.bot;
   }

   private static 0f _DADAR4tq9/* $FF was: 9DADAR4tq9*/(0a var0) {
      return var0.player;
   }

   private static 0a jxzqaFGsit(0bl var0) {
      return var0.bot;
   }

   private static 0a tXVeiAGt6V(0bl var0) {
      return var0.bot;
   }

   public void handleEntityEffect(TX c) {
      Ig d = 7BaMlZgPWD(a2nHH4gQjr(this)).getEntityByID(c.getEntityId());
      VW b;
      if (d instanceof Iw && (b = VW.getPotionById(c.getEffectId())) != null) {
         VZ a = new VZ(b, c.getDuration(), c.getAmplifier(), c.getIsAmbient(), c.doesShowParticles());
         a.setPotionDurationMax(c.isMaxDuration());
         ((Iw)d).addPotionEffect(a);
      }

   }

   private static double v2uNCI1hIW(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static 0bi _44RV2PyvJ/* $FF was: 644RV2PyvJ*/(0bl var0) {
      return var0.netManager;
   }

   private static 0bo _D9ZZn2n2u/* $FF was: 8D9ZZn2n2u*/(0a var0) {
      return var0.world;
   }

   private static 0f GhIO8l9DX9(0a var0) {
      return var0.player;
   }

   public void handleCloseWindow(TF a) {
      4Sy2NoGzQ7(ev14cbdaWj(this), (String)null);
      blsGArF2Km(G2FyBDfFWQ(this)).closeScreenAndDropStack();
   }

   public void handleServerDifficulty(UG a) {
      TU0CmbL3Ty(VdZZreJbhZ(this)).getWorldInfo().setDifficulty(a.getDifficulty());
      rqYQ1tD29N(rGgO8jhdCW(this)).getWorldInfo().setDifficultyLocked(a.isDifficultyLocked());
   }

   private static 0bo wbqbxm64dw(0a var0) {
      return var0.world;
   }

   public void handlePlayerAbilities(Ur a) {
      0f b = vsToyvqTyj(CZnay6vIx9(this));
      LvFaRWDrgI(tesYyBWMHy(b), a.isFlying());
      9Ah4JQfyzs(wTNViOqAbd(b), a.isCreativeMode());
      PHHoqIFjKB(i7usOHiAtt(b), a.isInvulnerable());
      8YaU6DPWyO(JGBwyngNye(b), a.isAllowFlying());
      niQcakWDQY(b).setFlySpeed(a.getFlySpeed());
      SSct55AsSe(b).setPlayerWalkSpeed(a.getWalkSpeed());
   }

   private static 0a dNkgBqayil(0bl var0) {
      return var0.bot;
   }

   public void handleUpdateBossInfo(Vc a) {
      0cG.method_bwd().method_bwh().forEach((b) -> {
         String var10001 = cYnJb44IaB("ۦۥ۔ۖ۞ېہ۠ۅۑ۔ہې۷ۚۆۆۼۛۓۚ");
         Object[] var10002 = new Object[23118 ^ -16020 ^ 32666 ^ -6982];
         var10002[21397 ^ -14654 ^ 18366 ^ -11543] = hAtK5NGd2z(this);
         var10002[10851 ^ -10762 ^ 27032 ^ -27124] = a;
         b.method_bCr(var10001, var10002);
      });
   }

   private static 0a hd6nOTjsb9(0bl var0) {
      return var0.bot;
   }

   public void handleEntityStatus(Ud a) {
      Ig b = a.getEntity(1f6Ew2TCV1(Uod4tcfjSY(this)));
      if (b != null && a.getOpCode() != (19684 ^ -9580 ^ 30694 ^ -7805) && a.getOpCode() != (15774 ^ -1766 ^ 2782 ^ -12679)) {
         b.handleStatusUpdate(a.getOpCode());
      }

   }

   private static 0a bY2SBhZdVx(0bl var0) {
      return var0.bot;
   }

   private static 0a gTbTWfKknO(0bl var0) {
      return var0.bot;
   }

   private static void kfTyfDzLT9(Ig var0, long var1) {
      var0.serverPosY = var1;
   }

   private static 0a fqNjzsfWbw(0bl var0) {
      return var0.bot;
   }

   private static 0f blsGArF2Km(0a var0) {
      return var0.player;
   }

   private static 0f QGWpe6quFQ(0a var0) {
      return var0.player;
   }

   private static 0a wAVagVL1aA(0bl var0) {
      return var0.bot;
   }

   private static 0f c6lfWVDDbb(0a var0) {
      return var0.player;
   }

   private static 0a bao7s1LjsV(0bl var0) {
      return var0.bot;
   }

   private static 0f jZBsblBoTv(0a var0) {
      return var0.player;
   }

   public void handleUpdateScore(Vf b) {
      Ws c = C5jcde7gg5(WdDzu3Dlgv(this)).getScoreboard();
      Wz d = c.getObjective(b.getObjectiveName());
      if (d != null) {
         if (b.getScoreAction() == NnawJCyl4b()) {
            Wr a = c.getOrCreateScore(b.getPlayerName(), d);
            a.setScorePoints(b.getScoreValue());
         } else if (b.getScoreAction() == fXN1zJ4Tdy()) {
            if (StringUtils.isNullOrEmpty(b.getObjectiveName())) {
               c.removeObjectiveFromEntity(b.getPlayerName(), (Wz)null);
            } else {
               c.removeObjectiveFromEntity(b.getPlayerName(), d);
            }
         }
      }

   }

   private static 0f cyEUilNwSq(0a var0) {
      return var0.player;
   }

   private static 0a FSSqPRfCaq(0bl var0) {
      return var0.bot;
   }

   private static Container W1KQ74Al2f(0f var0) {
      return var0.openContainer;
   }

   private static void _Ah4JQfyzs/* $FF was: 9Ah4JQfyzs*/(ML var0, boolean var1) {
      var0.isCreativeMode = var1;
   }

   public void handleSignEditorOpen(UK a) {
      Yg b = 16eiFyKYt5(VFbQAYrAMi(this)).getTileEntity(a.getSignPosition());
      if (!(b instanceof YQ)) {
         b = new YQ();
         ((Yg)b).setWorld(LynfZDn2Vp(OaVgdzjiAu(this)));
         ((Yg)b).setPos(a.getSignPosition());
      }

      gSK1xX5YAW(Whx59V4HOc(this)).openEditSign((YQ)b);
   }

   private static 0a M0zcO972FR(0bl var0) {
      return var0.bot;
   }

   private static 0a yQcpejCAWG(0bl var0) {
      return var0.bot;
   }

   public void handleMoveVehicle(Ul a) {
      Ig b = D9dtw6QAlW(Nysj4WRY2A(this)).getLowestRidingEntity();
      if (b != c6lfWVDDbb(iobuD9dkGZ(this)) && b.canPassengerSteer()) {
         b.setPositionAndRotation(a.getX(), a.getY(), a.getZ(), a.getYaw(), a.getPitch());
         644RV2PyvJ(this).sendPacket(new Tq(b));
      }

   }
}
