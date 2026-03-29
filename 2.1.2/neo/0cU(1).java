package neo;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.multiplayer.ChunkProviderClient;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.profiler.Profiler;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.IntHashMap;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.DimensionType;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.SaveDataMemoryStorage;
import net.minecraft.world.storage.SaveHandlerMP;
import net.minecraft.world.storage.WorldInfo;
import org.jetbrains.annotations.NotNull;

public class 0cU extends World {
   public final Set<Entity> entitySpawnQueue = Sets.newHashSet();
   public static ChunkProviderClient clientChunkProvider;
   public final Set<Entity> entityList = Sets.newHashSet();
   public final 0cL connection;
   public final 0cC pbot;

   public void doPreChunk(int chunkX, int chunkZ, boolean loadChunk) {
      if (loadChunk) {
         6syNeqZJCT().loadChunk(chunkX, chunkZ);
      } else {
         KBDgrro0oS().unloadChunk(chunkX, chunkZ);
         this.markBlockRangeForRenderUpdate(chunkX * (5304 ^ -8955 ^ 29820 ^ -16943), 19574 ^ -5055 ^ 6708 ^ -17917, chunkZ * (29982 ^ -21017 ^ 22165 ^ -29060), chunkX * (10838 ^ -478 ^ 29175 ^ -23149) + (21733 ^ -11287 ^ 12191 ^ -22372), 9373 ^ -18123 ^ 30027 ^ -5661, chunkZ * (2764 ^ -29191 ^ 8761 ^ -23268) + (9236 ^ -27110 ^ 16653 ^ -3316));
      }

   }

   private static List _c1WWzoD31/* $FF was: 6c1WWzoD31*/(0cU var0) {
      return var0.loadedEntityList;
   }

   private static List Dbw4GJusWc(0cU var0) {
      return var0.unloadedEntityList;
   }

   public _cU/* $FF was: 0cU*/(0cC bot, 0cL netHandler, WorldSettings settings, int dimension, EnumDifficulty difficulty, Profiler profilerIn) {
      super(new SaveHandlerMP(), new WorldInfo(settings, M7dAgnSXS2("ߕߨߋ߽ߪ߽߮ߪ")), DimensionType.getById(dimension).createDimension(), profilerIn, (boolean)(22890 ^ -28472 ^ 13926 ^ -59));
      this.pbot = bot;
      0cC var10000 = this.pbot;
      var10000.worldId += 18560 ^ -1473 ^ 4780 ^ -24558;
      this.connection = netHandler;
      this.getWorldInfo().setDifficulty(difficulty);
      this.setSpawnPoint(new BlockPos(17408 ^ -25348 ^ 19449 ^ -27891, 7972 ^ -12747 ^ 24413 ^ -29172, 32028 ^ -12044 ^ 8819 ^ -28781));
      this.provider.setWorld(this);
      this.chunkProvider = this.createChunkProvider();
      this.mapStorage = new SaveDataMemoryStorage();
      this.calculateInitialSkylight();
      this.calculateInitialWeather();
   }

   private static List _4zOtVciOj/* $FF was: 94zOtVciOj*/(0cU var0) {
      return var0.loadedEntityList;
   }

   private static ChunkProviderClient _syNeqZJCT/* $FF was: 6syNeqZJCT*/() {
      return clientChunkProvider;
   }

   private static int TVGe0OSafe(Entity var0) {
      return var0.chunkCoordX;
   }

   private static ChunkProviderClient SvandqYwJ6() {
      return clientChunkProvider;
   }

   public void removeAllEntities() {
      2DnjBPDIQB(this).removeAll(Dbw4GJusWc(this));
      Iterator var1 = W6fCiEj8W6(this).iterator();

      Entity entity1;
      int k1;
      while(var1.hasNext()) {
         entity1 = (Entity)var1.next();
         int j = TVGe0OSafe(entity1);
         k1 = QbQmyJII6Y(entity1);
         if (QRWAlJVYro(entity1) && this.isChunkLoaded(j, k1, (boolean)(11971 ^ -32342 ^ 14887 ^ -27313))) {
            this.getChunk(j, k1).removeEntity(entity1);
         }
      }

      var1 = bAe7tUeto4(this).iterator();

      while(var1.hasNext()) {
         entity1 = (Entity)var1.next();
         this.onEntityRemoved(entity1);
      }

      7ysb3HdaO1(this).clear();

      for(int j1 = 4236 ^ -10773 ^ 29074 ^ -19211; j1 < DcTJDHlqA1(this).size(); ++j1) {
         entity1 = (Entity)94zOtVciOj(this).get(j1);
         Entity entity2 = entity1.getRidingEntity();
         if (entity2 != null) {
            if (!jEbBbDSaNy(entity2) && entity2.isPassenger(entity1)) {
               continue;
            }

            entity1.dismountRidingEntity();
         }

         if (YnkyGighpg(entity1)) {
            k1 = 3yQ9O3zdyF(entity1);
            int l = Mq4oa5q2hT(entity1);
            if (QLLiBfLLI1(entity1) && this.isChunkLoaded(k1, l, (boolean)(23141 ^ -26848 ^ 13557 ^ -1615))) {
               this.getChunk(k1, l).removeEntity(entity1);
            }

            Oy9N3l3fJy(this).remove(j1--);
            this.onEntityRemoved(entity1);
         }
      }

   }

   public List<Entity> getEntitiesInAABBexcluding(@Nullable Entity entityIn, AxisAlignedBB boundingBox, @Nullable Predicate<? super Entity> predicate) {
      List<Entity> list = Lists.newArrayList();
      int j2 = MathHelper.floor((VmrdfKb2Bd(boundingBox) - Double.longBitsToDouble(4105079508503915056L ^ 8716765526931302960L)) / Double.longBitsToDouble(-1376686777844873682L ^ -5992876395899632082L));
      int k2 = MathHelper.floor((3MuIjBa2ca(boundingBox) + Double.longBitsToDouble(-2595629205842024489L ^ -7207315224269412393L)) / Double.longBitsToDouble(-2473513228697915426L ^ -7089702846752673826L));
      int l2 = MathHelper.floor((XGoTjgIYAv(boundingBox) - Double.longBitsToDouble(2276317421013316856L ^ 6888003439440704760L)) / Double.longBitsToDouble(-6472863951928080504L ^ -1865681533128063096L));
      int i3 = MathHelper.floor((99GkRiJk9V(boundingBox) + Double.longBitsToDouble(-8205762448199421523L ^ -3594076429772033619L)) / Double.longBitsToDouble(6374042727627949338L ^ 1748845910318449946L));

      for(int j3 = j2; j3 <= k2; ++j3) {
         for(int k3 = l2; k3 <= i3; ++k3) {
            if (this.isChunkLoaded(j3, k3, (boolean)(19832 ^ -3002 ^ 18525 ^ -3742))) {
               try {
                  this.getChunk(j3, k3).getEntitiesWithinAABBForEntity(entityIn, boundingBox, list, predicate);
               } catch (Exception var12) {
               }
            }
         }
      }

      return list;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public @NotNull IChunkProvider getChunkProvider() {
      return this.getChunkProvider();
   }

   private static double XGoTjgIYAv(AxisAlignedBB var0) {
      return var0.minZ;
   }

   private static 0cD GRBvQoUQGX(0cC var0) {
      return var0.player;
   }

   private static Set AtvaV3cnvw(0cU var0) {
      return var0.entityList;
   }

   public @NotNull ChunkProviderClient getChunkProvider() {
      return (ChunkProviderClient)super.getChunkProvider();
   }

   public @NotNull Chunk getChunk(int chunkX, int chunkZ) {
      Chunk chunk = u22cH9srSJ().provideChunk(chunkX, chunkZ);
      chunk.setWorld(ITy1Zjjjby(this).getWorld());
      chunk.resetRelightChecks();
      return chunk;
   }

   private static List _DnjBPDIQB/* $FF was: 2DnjBPDIQB*/(0cU var0) {
      return var0.loadedEntityList;
   }

   private static Set _BIQTiYNL0/* $FF was: 0BIQTiYNL0*/(0cU var0) {
      return var0.entityList;
   }

   protected boolean isChunkLoaded(int x, int z, boolean allowEmpty) {
      return (boolean)(!allowEmpty && this.getChunkProvider().provideChunk(x, z).isEmpty() ? 30192 ^ -9152 ^ 18344 ^ -4584 : 23161 ^ -8666 ^ 2299 ^ -29531);
   }

   private static 0cC ohwbVUGIkq(0cU var0) {
      return var0.pbot;
   }

   private static Set styeJgSlVf(0cU var0) {
      return var0.entitySpawnQueue;
   }

   private static 0bz QFdqDkxKMV() {
      return 0cd.cacheAfter;
   }

   private static ChunkProviderClient ElxeFHV9yl() {
      return clientChunkProvider;
   }

   private static IntHashMap _79j9rG4y7/* $FF was: 679j9rG4y7*/(0cU var0) {
      return var0.entitiesById;
   }

   private static 0cD nctWQzBdA2(0cC var0) {
      return var0.player;
   }

   protected @NotNull IChunkProvider createChunkProvider() {
      0dv.createChunkProvider(this);
      if ((float)IVItXcdIwN(sDGeaONZkg(this)) > KLYp1mxZec(rLtODDVqS6()) && aWrwVKotjG(4aAQ5aLMje())) {
         0dK.formatMsg(M7dAgnSXS2("ΉΦϚ\u07b8\u07be\u07fc\u07beߴ") + jjbvOBEJZB(this).getNickname() + M7dAgnSXS2("\u07b8\u07be߾\u07beߴ\u0380ϙΧΦΣϔίΦΪΨΣ\u07b8\u03a2ϕϐΠϘΦΪΨΥΥϓΡ\u07b8ΤΠϘ\u07b6"));
      }

      if (ElxeFHV9yl() == null) {
         gIKtX1y3v6((float)wVTdoSQGVo(zjQytL4XTP(this)) > vGDmqvGrUf(QFdqDkxKMV()) ? 0dv.getChunkProvider() : new ChunkProviderClient(this));
      }

      return SvandqYwJ6();
   }

   private static Set Q1pNDxLJIQ(0cU var0) {
      return var0.entityList;
   }

   private static 0cC jjbvOBEJZB(0cU var0) {
      return var0.pbot;
   }

   private static float vGDmqvGrUf(0bz var0) {
      return var0.value;
   }

   public boolean spawnEntity(@NotNull Entity entityIn) {
      boolean flag = super.spawnEntity(entityIn);
      0BIQTiYNL0(this).add(entityIn);
      if (!flag) {
         4MiTOQDwf9(this).add(entityIn);
      }

      return flag;
   }

   public void removeEntityFromWorld(int entityID) {
      Entity entity = (Entity)L3qLptbw7L(this).removeObject(entityID);
      if (entity != null) {
         89coP7BVQb(this).remove(entity);
         this.removeEntity(entity);
      }

   }

   private static boolean QRWAlJVYro(Entity var0) {
      return var0.addedToChunk;
   }

   private static float KLYp1mxZec(0bz var0) {
      return var0.value;
   }

   private static Set Vwldp2USmN(0cU var0) {
      return var0.entitySpawnQueue;
   }

   public void sendPacketToServer(@NotNull Packet packetIn) {
      t3Id9qOOG8(this).sendPacket(packetIn);
   }

   private static List DcTJDHlqA1(0cU var0) {
      return var0.loadedEntityList;
   }

   private static IntHashMap L3qLptbw7L(0cU var0) {
      return var0.entitiesById;
   }

   private static Set Z7TEVLCw6a(0cU var0) {
      return var0.entityList;
   }

   private static boolean jEbBbDSaNy(Entity var0) {
      return var0.isDead;
   }

   private static 0bv _aAQ5aLMje/* $FF was: 4aAQ5aLMje*/() {
      return 0cc.chunkCache;
   }

   public void setWorldScoreboard(Scoreboard scoreboardIn) {
      Bl37PWIQsI(this, scoreboardIn);
   }

   private static List _ysb3HdaO1/* $FF was: 7ysb3HdaO1*/(0cU var0) {
      return var0.unloadedEntityList;
   }

   public void removeEntity(@NotNull Entity entityIn) {
      super.removeEntity(entityIn);
      AtvaV3cnvw(this).remove(entityIn);
   }

   private static List Oy9N3l3fJy(0cU var0) {
      return var0.loadedEntityList;
   }

   private static int wVTdoSQGVo(0cC var0) {
      return var0.worldId;
   }

   protected void updateWeather() {
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String M7dAgnSXS2(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 13822 ^ -22319 ^ 29406 ^ -4111; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 9604 ^ -19187 ^ 1533 ^ -27924));
      }

      return var1.toString();
   }

   private static 0cL BUyueci8Wq(0cU var0) {
      return var0.connection;
   }

   private static 0cC ITy1Zjjjby(0cU var0) {
      return var0.pbot;
   }

   private static void gIKtX1y3v6(ChunkProviderClient var0) {
      clientChunkProvider = var0;
   }

   public void invalidateRegionAndSetBlock(BlockPos pos, IBlockState state) {
      super.setBlockState(pos, state, 27205 ^ -13291 ^ 29931 ^ -11592);
   }

   private static 0cC sDGeaONZkg(0cU var0) {
      return var0.pbot;
   }

   private static Set iFrY8r97yv(0cU var0) {
      return var0.entitySpawnQueue;
   }

   private static int _yQ9O3zdyF/* $FF was: 3yQ9O3zdyF*/(Entity var0) {
      return var0.chunkCoordX;
   }

   public void tick() {
      super.tick();
      this.setTotalWorldTime(this.getTotalWorldTime() + 1L);
      if (this.getGameRules().getBoolean(M7dAgnSXS2("\u07fc߷ߜ߹ߡߴ߱߿߰߬ߛߡ\u07fbߴ߽"))) {
         this.setWorldTime(this.getWorldTime() + 1L);
      }

      for(int i = 14825 ^ -5951 ^ 25418 ^ -19870; i < (30724 ^ -22174 ^ 3964 ^ -8688) && !styeJgSlVf(this).isEmpty(); ++i) {
         Entity entity = (Entity)iFrY8r97yv(this).iterator().next();
         GvC7jkF1sQ(this).remove(entity);
         if (!6c1WWzoD31(this).contains(entity)) {
            this.spawnEntity(entity);
         }
      }

      this.updateBlocks();
   }

   private static boolean aWrwVKotjG(0bv var0) {
      return var0.value;
   }

   @Nullable
   public Entity getEntityByID(int id) {
      return (Entity)(ohwbVUGIkq(this).isOnline() && nctWQzBdA2(nlTbbDjQ4H(this)).getEntityId() == id ? GRBvQoUQGX(V9yIsCVRIG(this)) : super.getEntityByID(id));
   }

   private static double _9GkRiJk9V/* $FF was: 99GkRiJk9V*/(AxisAlignedBB var0) {
      return var0.maxZ;
   }

   private static double VmrdfKb2Bd(AxisAlignedBB var0) {
      return var0.minX;
   }

   private static List W6fCiEj8W6(0cU var0) {
      return var0.unloadedEntityList;
   }

   private static int QbQmyJII6Y(Entity var0) {
      return var0.chunkCoordZ;
   }

   private static ChunkProviderClient u22cH9srSJ() {
      return clientChunkProvider;
   }

   private static boolean QLLiBfLLI1(Entity var0) {
      return var0.addedToChunk;
   }

   private static double _MuIjBa2ca/* $FF was: 3MuIjBa2ca*/(AxisAlignedBB var0) {
      return var0.maxX;
   }

   private static Set XrlVY2AeTV(0cU var0) {
      return var0.entityList;
   }

   public void sendQuittingDisconnectingPacket() {
      BUyueci8Wq(this).getNetworkManager().closeChannel(new TextComponentString(M7dAgnSXS2("߉߭߱߬߬߱߶߿")));
   }

   private static 0cC zjQytL4XTP(0cU var0) {
      return var0.pbot;
   }

   public void setWorldTime(long time) {
      if (time < 0L) {
         time = -time;
         this.getGameRules().setOrCreateGameRule(M7dAgnSXS2("\u07fc߷ߜ߹ߡߴ߱߿߰߬ߛߡ\u07fbߴ߽"), M7dAgnSXS2("߾߹ߴ߽߫"));
      } else {
         this.getGameRules().setOrCreateGameRule(M7dAgnSXS2("\u07fc߷ߜ߹ߡߴ߱߿߰߬ߛߡ\u07fbߴ߽"), M7dAgnSXS2("߬ߪ߽߭"));
      }

      super.setWorldTime(time);
   }

   private static List bAe7tUeto4(0cU var0) {
      return var0.unloadedEntityList;
   }

   private static Set _9coP7BVQb/* $FF was: 89coP7BVQb*/(0cU var0) {
      return var0.entityList;
   }

   public void addEntityToWorld(int entityID, Entity entityToSpawn) {
      Entity entity = this.getEntityByID(entityID);
      if (entity != null) {
         this.removeEntity(entity);
      }

      Q1pNDxLJIQ(this).add(entityToSpawn);
      entityToSpawn.setEntityId(entityID);
      if (!this.spawnEntity(entityToSpawn)) {
         rxpb7NoLEQ(this).add(entityToSpawn);
      }

      679j9rG4y7(this).addKey(entityID, entityToSpawn);
   }

   private static int IVItXcdIwN(0cC var0) {
      return var0.worldId;
   }

   private static 0cC nlTbbDjQ4H(0cU var0) {
      return var0.pbot;
   }

   private static Set rxpb7NoLEQ(0cU var0) {
      return var0.entitySpawnQueue;
   }

   private static 0cL t3Id9qOOG8(0cU var0) {
      return var0.connection;
   }

   private static Set GvC7jkF1sQ(0cU var0) {
      return var0.entitySpawnQueue;
   }

   protected void onEntityAdded(@NotNull Entity entityIn) {
      super.onEntityAdded(entityIn);
      zGxdxVbH9G(this).remove(entityIn);
   }

   private static 0bz rLtODDVqS6() {
      return 0cd.cacheAfter;
   }

   private static void Bl37PWIQsI(0cU var0, Scoreboard var1) {
      var0.worldScoreboard = var1;
   }

   private static int Mq4oa5q2hT(Entity var0) {
      return var0.chunkCoordZ;
   }

   private static Set zGxdxVbH9G(0cU var0) {
      return var0.entitySpawnQueue;
   }

   private static ChunkProviderClient KBDgrro0oS() {
      return clientChunkProvider;
   }

   private static boolean YnkyGighpg(Entity var0) {
      return var0.isDead;
   }

   protected void onEntityRemoved(@NotNull Entity entityIn) {
      super.onEntityRemoved(entityIn);
      if (Z7TEVLCw6a(this).contains(entityIn)) {
         if (entityIn.isEntityAlive()) {
            Vwldp2USmN(this).add(entityIn);
         } else {
            XrlVY2AeTV(this).remove(entityIn);
         }
      }

   }

   private static Set _MiTOQDwf9/* $FF was: 4MiTOQDwf9*/(0cU var0) {
      return var0.entitySpawnQueue;
   }

   private static 0cC V9yIsCVRIG(0cU var0) {
      return var0.pbot;
   }
}
