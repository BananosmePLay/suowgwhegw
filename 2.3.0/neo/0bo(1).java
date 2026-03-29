package neo;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.util.IntHashMap;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class 0bo extends bij {
   public 0bn botChunkProvider;
   public final int maxPlayers;
   public final 0a bot;
   public final baM dimension;
   public final Set<Ig> entityList;
   public final Set<Ig> entitySpawnQueue;
   private static int _DSC GG NEOWARECLIENT _;

   private static int vdt5q6Dk1b(Ig var0) {
      return var0.chunkCoordX;
   }

   private static 0a e2mdssHey1(0bo var0) {
      return var0.bot;
   }

   private static int _TSOiLDNyU/* $FF was: 6TSOiLDNyU*/(Ig var0) {
      return var0.chunkCoordX;
   }

   private static List _tfCo0lRe6/* $FF was: 7tfCo0lRe6*/(0bo var0) {
      return var0.unloadedEntityList;
   }

   public void addEntityToWorld(int a, Ig b) {
      if (!dtg69t4jnb().method_bna() || 0Y.method_kW(b)) {
         Ig c = this.getEntityByID(a);
         if (c != null) {
            this.removeEntity(c);
         }

         Tkecdoy2br(this).add(b);
         b.setEntityId(a);
         if (!this.spawnEntity(b)) {
            GTpiOr73gA(this).add(b);
         }

         3C0hrD91Qo(this).addKey(a, b);
      }
   }

   private static 0bn GvGp9UJVDG(0bo var0) {
      return var0.botChunkProvider;
   }

   private static double M61y4TlJb3(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static 0bn jGI2ta8ypt(0bo var0) {
      return var0.botChunkProvider;
   }

   protected void updateWeather() {
   }

   private static IntHashMap _C0hrD91Qo/* $FF was: 3C0hrD91Qo*/(0bo var0) {
      return var0.entitiesById;
   }

   public void sendQuittingDisconnectingPacket() {
      hT3SpnrltS(KrC7J49B9j(this)).getNetworkManager().closeChannel();
   }

   public List<Ig> getEntitiesInAABBexcluding(@Nullable Ig d, AxisAlignedBB e, @Nullable Predicate<? super Ig> f) {
      List<Ig> g = Lists.newArrayList();
      int h = MathHelper.floor((jdlk6pIQbA(e) - Double.longBitsToDouble(5638458414865745322L ^ 1026772396438357418L)) / Double.longBitsToDouble(5653123200455900915L ^ 1027926383146401523L));
      int i = MathHelper.floor((xf4RjLidaj(e) + Double.longBitsToDouble(-2389774433606481082L ^ -7001460452033868986L)) / Double.longBitsToDouble(-588871200183462012L ^ -5196053618983479420L));
      int j = MathHelper.floor((M61y4TlJb3(e) - Double.longBitsToDouble(3398868048734961236L ^ 8010554067162349140L)) / Double.longBitsToDouble(155532490531355144L ^ 4762714909331372552L));
      int k = MathHelper.floor((Ua8ts6vzP5(e) + Double.longBitsToDouble(2992009254956041713L ^ 7603695273383429617L)) / Double.longBitsToDouble(4659756017010596868L ^ 43566398955838468L));

      for(int c = h; c <= i; ++c) {
         for(int b = j; b <= k; ++b) {
            if (this.isChunkLoaded(c, b, (boolean)(14853 ^ -18636 ^ 1715 ^ -29821)) && d != null && f != null) {
               try {
                  this.getEntitiesWithinAABBForEntity(this.getChunk(c, b), d, e, g, f);
               } catch (Exception var12) {
                  Exception a = var12;
                  a.printStackTrace();
               }
            }
         }
      }

      return g;
   }

   private static Set lDw6eZUHTq(0bo var0) {
      return var0.entitySpawnQueue;
   }

   protected void onEntityAdded(Ig a) {
      super.onEntityAdded(a);
      beVgafNdBk(this).remove(a);
   }

   public 0bn getChunkProvider() {
      return (0bn)super.getChunkProvider();
   }

   protected void updateBlocks() {
   }

   private static 0a KrC7J49B9j(0bo var0) {
      return var0.bot;
   }

   private static List xj7o4vmNg9(0bo var0) {
      return var0.unloadedEntityList;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String Y4o9f66L6D(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 7209 ^ -29578 ^ 18677 ^ -10070; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 19015 ^ -16565 ^ 2795 ^ -476));
      }

      return var1.toString();
   }

   public void removeAllEntities() {
      TEjYiiVYrs(this).removeAll(7tfCo0lRe6(this));
      Iterator var1 = oF9Yo2CpqY(this).iterator();

      Ig g;
      int e;
      while(var1.hasNext()) {
         g = (Ig)var1.next();
         int a = 6TSOiLDNyU(g);
         e = qF1BkwDM22(g);
         if (yKPtJJ3W1g(g) && this.isChunkLoaded(a, e, (boolean)(20238 ^ -24160 ^ 483 ^ -4276))) {
            this.getChunk(a, e).removeEntity(g);
         }
      }

      var1 = xj7o4vmNg9(this).iterator();

      while(var1.hasNext()) {
         g = (Ig)var1.next();
         this.onEntityRemoved(g);
      }

      sIWGnwA2Bi(this).clear();

      for(int i = 10985 ^ -27661 ^ 24598 ^ -9972; i < 8j9IntpEL5(this).size(); ++i) {
         g = (Ig)NjLcBtBnNo(this).get(i);
         Ig h = g.getRidingEntity();
         if (h != null) {
            if (!5TMGUKEn76(h) && h.isPassenger(g)) {
               continue;
            }

            g.dismountRidingEntity();
         }

         if (D8VZBU7TQT(g)) {
            e = vdt5q6Dk1b(g);
            int f = OrQy1DCLnY(g);
            if (Lqh2BKAjt5(g) && this.isChunkLoaded(e, f, (boolean)(30154 ^ -14515 ^ 13688 ^ -30722))) {
               this.getChunk(e, f).removeEntity(g);
            }

            kSCohCOvSL(this).remove(i--);
            this.onEntityRemoved(g);
         }
      }

   }

   public _bo/* $FF was: 0bo*/(0a a, biw b, int c, baV d, int e) {
      super((bgm)null, new bhY(b, Y4o9f66L6D("ƎƳƐƦƱƵƦƱ")), baM.getById(c).createDimension(), nC.getMinecraft().profiler, (boolean)(23164 ^ -16898 ^ 5975 ^ -3884));
      this.bot = a;
      this.entityList = Sets.newHashSet();
      this.dimension = baM.getById(c);
      this.maxPlayers = e;
      this.entitySpawnQueue = Sets.newHashSet();
      this.getWorldInfo().setDifficulty(d);
      this.provider.setWorld(this);
      this.setSpawnPoint(new BlockPos(29561 ^ -21499 ^ 17968 ^ -26300, 18380 ^ -28157 ^ 18906 ^ -25515, 29957 ^ -27723 ^ 4722 ^ -2870));
      this.chunkProvider = this.createChunkProvider();
      this.mapStorage = new bhJ();
      this.calculateInitialSkylight();
      this.calculateInitialWeather();
      if (this.bot.isCached() && 0bF.field_c.method_bna()) {
         String var10000 = Y4o9f66L6D("ơƬƷǭƠƢƠƫƦǭƴƬƱƯƧ");
         Object[] var10001 = new Object[26213 ^ -9446 ^ 7778 ^ -23780];
         var10001[22121 ^ -9210 ^ 27784 ^ -6425] = this.bot.getNickname();
         0ek.addMessage(0cT.method_byW(var10000, var10001));
      }

   }

   private static List NjLcBtBnNo(0bo var0) {
      return var0.loadedEntityList;
   }

   private static double ed63tTJIoj(AxisAlignedBB var0) {
      return var0.maxY;
   }

   public void setWorldTime(long a) {
      if (a < 0L) {
         a = -a;
         this.getGameRules().setOrCreateGameRule(Y4o9f66L6D("ƧƬƇƢƺƯƪƤƫƷƀƺƠƯƦ"), Y4o9f66L6D("ƥƢƯưƦ"));
      } else {
         this.getGameRules().setOrCreateGameRule(Y4o9f66L6D("ƧƬƇƢƺƯƪƤƫƷƀƺƠƯƦ"), Y4o9f66L6D("ƷƱƶƦ"));
      }

      super.setWorldTime(a);
   }

   private static boolean yKPtJJ3W1g(Ig var0) {
      return var0.addedToChunk;
   }

   private static 0cp dtg69t4jnb() {
      return 0bH.field_p;
   }

   public void doPreChunk(int a, int b, boolean c) {
      if (c) {
         GWXknRYqAN(this).loadChunk(this, a, b);
      } else {
         jGI2ta8ypt(this).unloadChunk(a, b);
      }

   }

   private static int OrQy1DCLnY(Ig var0) {
      return var0.chunkCoordZ;
   }

   private static List TEjYiiVYrs(0bo var0) {
      return var0.loadedEntityList;
   }

   protected void playMoodSoundAndCheckLight(int a, int b, bam c) {
   }

   public void playSound(double a, double b, double c, SoundEvent d, SoundCategory e, float f, float g, boolean h) {
   }

   private static Set beVgafNdBk(0bo var0) {
      return var0.entitySpawnQueue;
   }

   private static 0f _VNHn12nbW/* $FF was: 3VNHn12nbW*/(0a var0) {
      return var0.player;
   }

   public void sendPacketToServer(Sz<?> a) {
      9pgb5ltQDM(e2mdssHey1(this)).sendPacket(a);
   }

   private static 0f S2PpmPSll4(0a var0) {
      return var0.player;
   }

   private static Set _yH2gqmELg/* $FF was: 2yH2gqmELg*/(0bo var0) {
      return var0.entityList;
   }

   private static boolean Lqh2BKAjt5(Ig var0) {
      return var0.addedToChunk;
   }

   private static 0bn bB2iFYhbSM(0bo var0) {
      return var0.botChunkProvider;
   }

   private static Set _Tei92LV30/* $FF was: 9Tei92LV30*/(0bo var0) {
      return var0.entitySpawnQueue;
   }

   public void removeEntityFromWorld(int a) {
      Ig b = (Ig)nPCw8pBV8X(this).removeObject(a);
      if (b != null) {
         2yH2gqmELg(this).remove(b);
         this.removeEntity(b);
      }

   }

   protected boolean isChunkLoaded(int a, int b, boolean c) {
      return (boolean)(!c && this.getChunkProvider().provideChunk(a, b).isEmpty() ? 7499 ^ -23221 ^ 25438 ^ -9378 : 15284 ^ -1237 ^ 18250 ^ -30764);
   }

   private static 0a x9NtuTaWiG(0bo var0) {
      return var0.bot;
   }

   public void tick() {
      this.setTotalWorldTime(this.getTotalWorldTime() + 1L);
      if (this.getGameRules().getBoolean(Y4o9f66L6D("ƧƬƇƢƺƯƪƤƫƷƀƺƠƯƦ"))) {
         this.setWorldTime(this.getWorldTime() + 1L);
      }

      for(int b = 2763 ^ -29131 ^ 21504 ^ -12034; b < (25295 ^ -20308 ^ 6312 ^ -13631) && !lDw6eZUHTq(this).isEmpty(); ++b) {
         Ig a = (Ig)9Tei92LV30(this).iterator().next();
         nQrg0IJqyO(this).remove(a);
         if (NTSIa9vB0e(this).contains(a)) {
            this.spawnEntity(a);
         }
      }

   }

   private static 0bo Gqt61ziBSF(0a var0) {
      return var0.world;
   }

   private static Set nQrg0IJqyO(0bo var0) {
      return var0.entitySpawnQueue;
   }

   public void setWorldScoreboard(Ws a) {
      IdFnv2Yq6O(this, a);
   }

   private static double xf4RjLidaj(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static 0a iw9k2wmdBK(0bo var0) {
      return var0.bot;
   }

   public @NotNull bam getChunk(int a, int b) {
      bam c = bB2iFYhbSM(this).provideChunk(a, b);
      if (NHtbMe7zGg(this).isCached()) {
         c.setWorld(Gqt61ziBSF(QuVrt2v6wa(this)));
         c.resetRelightChecks();
      }

      return c;
   }

   private static boolean D8VZBU7TQT(Ig var0) {
      return var0.isDead;
   }

   private static IntHashMap nPCw8pBV8X(0bo var0) {
      return var0.entitiesById;
   }

   public baM getDimension() {
      return p6ZaADknoj(this);
   }

   private static Set t0TemBcNv4(0bo var0) {
      return var0.entityList;
   }

   private void getEntitiesWithinAABBForEntity(bam e, Ig f, AxisAlignedBB g, List<Ig> h, Predicate<? super Ig> i) {
      int j = MathHelper.floor((liTtPey2t9(g) - Double.longBitsToDouble(-2761204139578671931L ^ -7372890158006059835L)) / Double.longBitsToDouble(-6964407620449783096L ^ -2348218002395024696L));
      int k = MathHelper.floor((ed63tTJIoj(g) + Double.longBitsToDouble(-4376038003686664109L ^ -8987724022114052013L)) / Double.longBitsToDouble(4088620923474140385L ^ 8686796143019416801L));
      j = MathHelper.clamp(j, 29533 ^ -2938 ^ 4316 ^ -26873, e.getEntityLists().length - (21420 ^ -5819 ^ 28659 ^ -10981));
      k = MathHelper.clamp(k, 30030 ^ -9089 ^ 27922 ^ -15325, e.getEntityLists().length - (12527 ^ -8782 ^ 28271 ^ -31949));

      label68:
      for(int d = j; d <= k; ++d) {
         if (!e.getEntityLists()[d].isEmpty()) {
            Iterator var9 = (new CopyOnWriteArrayList(e.getEntityLists()[d])).iterator();

            while(true) {
               Ig[] b;
               do {
                  Ig c;
                  do {
                     do {
                        do {
                           if (!var9.hasNext()) {
                              continue label68;
                           }

                           c = (Ig)var9.next();
                        } while(c == null);
                     } while(!c.getEntityBoundingBox().intersects(g));
                  } while(c == f);

                  if (i.apply(c)) {
                     h.add(c);
                  }

                  b = c.getParts();
               } while(b == null);

               Ig[] var12 = b;
               int var13 = b.length;

               for(int var14 = 4290 ^ -12010 ^ 17481 ^ -31331; var14 < var13; ++var14) {
                  Ig a = var12[var14];
                  if (a != f && a.getEntityBoundingBox().intersects(g) && (i == null || i.apply(a))) {
                     h.add(a);
                  }
               }
            }
         }
      }

   }

   private static 0bl _pgb5ltQDM/* $FF was: 9pgb5ltQDM*/(0a var0) {
      return var0.connection;
   }

   protected bar createChunkProvider() {
      0bq.method_LP(this);
      5dT93v1amX(this, new 0bn(iSDDv52XVo(this)));
      return GvGp9UJVDG(this);
   }

   public void makeFireworks(double a, double b, double c, double d, double e, double f, @Nullable QQ g) {
   }

   private static double Ua8ts6vzP5(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   public void invalidateRegionAndSetBlock(BlockPos a, in b) {
      super.setBlockState(a, b, 10981 ^ -1608 ^ 20469 ^ -25429);
   }

   private static 0a iSDDv52XVo(0bo var0) {
      return var0.bot;
   }

   private static Set W4V6dfkO5S(0bo var0) {
      return var0.entitySpawnQueue;
   }

   private static Set Tkecdoy2br(0bo var0) {
      return var0.entityList;
   }

   private static boolean _TMGUKEn76/* $FF was: 5TMGUKEn76*/(Ig var0) {
      return var0.isDead;
   }

   private static List sIWGnwA2Bi(0bo var0) {
      return var0.unloadedEntityList;
   }

   private static baM p6ZaADknoj(0bo var0) {
      return var0.dimension;
   }

   private static double liTtPey2t9(AxisAlignedBB var0) {
      return var0.minY;
   }

   private static 0a QuVrt2v6wa(0bo var0) {
      return var0.bot;
   }

   private static List _j9IntpEL5/* $FF was: 8j9IntpEL5*/(0bo var0) {
      return var0.loadedEntityList;
   }

   public void removeEntity(Ig a) {
      super.removeEntity(a);
      A9Ga2Al2G3(this).remove(a);
   }

   private static void IdFnv2Yq6O(0bo var0, Ws var1) {
      var0.worldScoreboard = var1;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public bar getChunkProvider() {
      return this.getChunkProvider();
   }

   private static 0bn GWXknRYqAN(0bo var0) {
      return var0.botChunkProvider;
   }

   private static Set GTpiOr73gA(0bo var0) {
      return var0.entitySpawnQueue;
   }

   private static List oF9Yo2CpqY(0bo var0) {
      return var0.unloadedEntityList;
   }

   private static 0bl hT3SpnrltS(0a var0) {
      return var0.connection;
   }

   private static List kSCohCOvSL(0bo var0) {
      return var0.loadedEntityList;
   }

   public int getMaxPlayers() {
      return iBb2ajGIuB(this);
   }

   private static int qF1BkwDM22(Ig var0) {
      return var0.chunkCoordZ;
   }

   private static Set A9Ga2Al2G3(0bo var0) {
      return var0.entityList;
   }

   private static List NTSIa9vB0e(0bo var0) {
      return var0.loadedEntityList;
   }

   private static double jdlk6pIQbA(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static Set _YbybK7YYg/* $FF was: 4YbybK7YYg*/(0bo var0) {
      return var0.entityList;
   }

   private static 0a NHtbMe7zGg(0bo var0) {
      return var0.bot;
   }

   private static int iBb2ajGIuB(0bo var0) {
      return var0.maxPlayers;
   }

   private static void _dT93v1amX/* $FF was: 5dT93v1amX*/(0bo var0, 0bn var1) {
      var0.botChunkProvider = var1;
   }

   @Nullable
   public Ig getEntityByID(int a) {
      return (Ig)(a == 3VNHn12nbW(iw9k2wmdBK(this)).getEntityId() ? S2PpmPSll4(x9NtuTaWiG(this)) : super.getEntityByID(a));
   }

   public void playSound(@Nullable ME a, double b, double c, double d, SoundEvent e, SoundCategory f, float g, float h) {
   }

   protected void onEntityRemoved(Ig a) {
      super.onEntityRemoved(a);
      if (t0TemBcNv4(this).contains(a)) {
         if (a.isEntityAlive()) {
            W4V6dfkO5S(this).add(a);
         } else {
            4YbybK7YYg(this).remove(a);
         }
      }

   }
}
