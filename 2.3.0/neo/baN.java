package neo;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class baN {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final Predicate<MG> VALID_PLAYER;
   private final baf bossInfo;
   private final bis world;
   private final List<Integer> gateways;
   private final it portalPattern;
   private int ticksSinceDragonSeen;
   private int aliveCrystals;
   private int ticksSinceCrystalsScanned;
   private int ticksSinceLastPlayerScan;
   private boolean dragonKilled;
   private boolean previouslyKilled;
   private UUID dragonUniqueId;
   private boolean scanForLegacyFight;
   private BlockPos exitPortalLocation;
   private baT respawnState;
   private int respawnStateTicks;
   private List<IS> crystals;

   public baN(bis worldIn, QQ compound) {
      this.bossInfo = (baf)(new baf(new TextComponentTranslation("entity.EnderDragon.name", new Object[0]), bac.PINK, bad.PROGRESS)).setPlayEndBossMusic(true).setCreateFog(true);
      this.gateways = Lists.newArrayList();
      this.scanForLegacyFight = true;
      this.world = worldIn;
      if (compound.hasKey("DragonKilled", 99)) {
         if (compound.hasUniqueId("DragonUUID")) {
            this.dragonUniqueId = compound.getUniqueId("DragonUUID");
         }

         this.dragonKilled = compound.getBoolean("DragonKilled");
         this.previouslyKilled = compound.getBoolean("PreviouslyKilled");
         if (compound.getBoolean("IsRespawning")) {
            this.respawnState = baT.START;
         }

         if (compound.hasKey("ExitPortalLocation", 10)) {
            this.exitPortalLocation = Rb.getPosFromTag(compound.getCompoundTag("ExitPortalLocation"));
         }
      } else {
         this.dragonKilled = true;
         this.previouslyKilled = true;
      }

      if (compound.hasKey("Gateways", 9)) {
         QW nbttaglist = compound.getTagList("Gateways", 3);

         for(int i = 0; i < nbttaglist.tagCount(); ++i) {
            this.gateways.add(nbttaglist.getIntAt(i));
         }
      } else {
         this.gateways.addAll(ContiguousSet.create(Range.closedOpen(0, 20), DiscreteDomain.integers()));
         Collections.shuffle(this.gateways, new Random(worldIn.getSeed()));
      }

      this.portalPattern = iw.start().aisle("       ", "       ", "       ", "   #   ", "       ", "       ", "       ").aisle("       ", "       ", "       ", "   #   ", "       ", "       ", "       ").aisle("       ", "       ", "       ", "   #   ", "       ", "       ", "       ").aisle("  ###  ", " #   # ", "#     #", "#  #  #", "#     #", " #   # ", "  ###  ").aisle("       ", "  ###  ", " ##### ", " ##### ", " ##### ", "  ###  ", "       ").where('#', ik.hasState(ip.forBlock(Nk.BEDROCK))).build();
   }

   public QQ getCompound() {
      QQ nbttagcompound = new QQ();
      if (this.dragonUniqueId != null) {
         nbttagcompound.setUniqueId("DragonUUID", this.dragonUniqueId);
      }

      nbttagcompound.setBoolean("DragonKilled", this.dragonKilled);
      nbttagcompound.setBoolean("PreviouslyKilled", this.previouslyKilled);
      if (this.exitPortalLocation != null) {
         nbttagcompound.setTag("ExitPortalLocation", Rb.createPosTag(this.exitPortalLocation));
      }

      QW nbttaglist = new QW();
      Iterator iterator = this.gateways.iterator();

      while(iterator.hasNext()) {
         int i = (Integer)iterator.next();
         nbttaglist.appendTag(new QU(i));
      }

      nbttagcompound.setTag("Gateways", nbttaglist);
      return nbttagcompound;
   }

   public void tick() {
      this.bossInfo.setVisible(!this.dragonKilled);
      if (++this.ticksSinceLastPlayerScan >= 20) {
         this.updatePlayers();
         this.ticksSinceLastPlayerScan = 0;
      }

      if (!this.bossInfo.getPlayers().isEmpty()) {
         if (this.scanForLegacyFight) {
            LOGGER.info("Scanning for legacy world dragon fight...");
            this.loadChunks();
            this.scanForLegacyFight = false;
            boolean flag = this.hasDragonBeenKilled();
            if (flag) {
               LOGGER.info("Found that the dragon has been killed in this world already.");
               this.previouslyKilled = true;
            } else {
               LOGGER.info("Found that the dragon has not yet been killed in this world.");
               this.previouslyKilled = false;
               this.generatePortal(false);
            }

            List<HS> list = this.world.getEntities(HS.class, EntitySelectors.IS_ALIVE);
            if (list.isEmpty()) {
               this.dragonKilled = true;
            } else {
               HS entitydragon = (HS)list.get(0);
               this.dragonUniqueId = entitydragon.getUniqueID();
               LOGGER.info("Found that there's a dragon still alive ({})", entitydragon);
               this.dragonKilled = false;
               if (!flag) {
                  LOGGER.info("But we didn't have a portal, let's remove it.");
                  entitydragon.setDead();
                  this.dragonUniqueId = null;
               }
            }

            if (!this.previouslyKilled && this.dragonKilled) {
               this.dragonKilled = false;
            }
         }

         if (this.respawnState != null) {
            if (this.crystals == null) {
               this.respawnState = null;
               this.respawnDragon();
            }

            this.respawnState.process(this.world, this, this.crystals, this.respawnStateTicks++, this.exitPortalLocation);
         }

         if (!this.dragonKilled) {
            if (this.dragonUniqueId == null || ++this.ticksSinceDragonSeen >= 1200) {
               this.loadChunks();
               List<HS> list1 = this.world.getEntities(HS.class, EntitySelectors.IS_ALIVE);
               if (list1.isEmpty()) {
                  LOGGER.debug("Haven't seen the dragon, respawning it");
                  this.createNewDragon();
               } else {
                  LOGGER.debug("Haven't seen our dragon, but found another one to use.");
                  this.dragonUniqueId = ((HS)list1.get(0)).getUniqueID();
               }

               this.ticksSinceDragonSeen = 0;
            }

            if (++this.ticksSinceCrystalsScanned >= 100) {
               this.findAliveCrystals();
               this.ticksSinceCrystalsScanned = 0;
            }
         }
      }

   }

   protected void setRespawnState(baT state) {
      if (this.respawnState == null) {
         throw new IllegalStateException("Dragon respawn isn't in progress, can't skip ahead in the animation.");
      } else {
         this.respawnStateTicks = 0;
         if (state == baT.END) {
            this.respawnState = null;
            this.dragonKilled = false;
            HS entitydragon = this.createNewDragon();
            Iterator var3 = this.bossInfo.getPlayers().iterator();

            while(var3.hasNext()) {
               MG entityplayermp = (MG)var3.next();
               bY.SUMMONED_ENTITY.trigger(entityplayermp, entitydragon);
            }
         } else {
            this.respawnState = state;
         }

      }
   }

   private boolean hasDragonBeenKilled() {
      for(int i = -8; i <= 8; ++i) {
         for(int j = -8; j <= 8; ++j) {
            bam chunk = this.world.getChunk(i, j);
            Iterator var4 = chunk.getTileEntityMap().values().iterator();

            while(var4.hasNext()) {
               Yg tileentity = (Yg)var4.next();
               if (tileentity instanceof Yy) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   @Nullable
   private is findExitPortal() {
      int i;
      int l;
      for(i = -8; i <= 8; ++i) {
         for(l = -8; l <= 8; ++l) {
            bam chunk = this.world.getChunk(i, l);
            Iterator var4 = chunk.getTileEntityMap().values().iterator();

            while(var4.hasNext()) {
               Yg tileentity = (Yg)var4.next();
               if (tileentity instanceof Yy) {
                  is blockpattern$patternhelper = this.portalPattern.match(this.world, tileentity.getPos());
                  if (blockpattern$patternhelper != null) {
                     BlockPos blockpos = blockpattern$patternhelper.translateOffset(3, 3, 3).getPos();
                     if (this.exitPortalLocation == null && blockpos.getX() == 0 && blockpos.getZ() == 0) {
                        this.exitPortalLocation = blockpos;
                     }

                     return blockpattern$patternhelper;
                  }
               }
            }
         }
      }

      i = this.world.getHeight(bbD.END_PODIUM_LOCATION).getY();

      for(l = i; l >= 0; --l) {
         is blockpattern$patternhelper1 = this.portalPattern.match(this.world, new BlockPos(bbD.END_PODIUM_LOCATION.getX(), l, bbD.END_PODIUM_LOCATION.getZ()));
         if (blockpattern$patternhelper1 != null) {
            if (this.exitPortalLocation == null) {
               this.exitPortalLocation = blockpattern$patternhelper1.translateOffset(3, 3, 3).getPos();
            }

            return blockpattern$patternhelper1;
         }
      }

      return null;
   }

   private void loadChunks() {
      for(int i = -8; i <= 8; ++i) {
         for(int j = -8; j <= 8; ++j) {
            this.world.getChunk(i, j);
         }
      }

   }

   private void updatePlayers() {
      Set<MG> set = Sets.newHashSet();
      Iterator var2 = this.world.getPlayers(MG.class, VALID_PLAYER).iterator();

      while(var2.hasNext()) {
         MG entityplayermp = (MG)var2.next();
         this.bossInfo.addPlayer(entityplayermp);
         set.add(entityplayermp);
      }

      Set<MG> set1 = Sets.newHashSet(this.bossInfo.getPlayers());
      set1.removeAll(set);
      Iterator var6 = set1.iterator();

      while(var6.hasNext()) {
         MG entityplayermp1 = (MG)var6.next();
         this.bossInfo.removePlayer(entityplayermp1);
      }

   }

   private void findAliveCrystals() {
      this.ticksSinceCrystalsScanned = 0;
      this.aliveCrystals = 0;
      bcc[] var1 = Zw.getSpikesForWorld(this.world);
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         bcc worldgenspikes$endspike = var1[var3];
         this.aliveCrystals += this.world.getEntitiesWithinAABB(IS.class, worldgenspikes$endspike.getTopBoundingBox()).size();
      }

      LOGGER.debug("Found {} end crystals still alive", this.aliveCrystals);
   }

   public void processDragonDeath(HS dragon) {
      if (dragon.getUniqueID().equals(this.dragonUniqueId)) {
         this.bossInfo.setPercent(0.0F);
         this.bossInfo.setVisible(false);
         this.generatePortal(true);
         this.spawnNewGateway();
         if (!this.previouslyKilled) {
            this.world.setBlockState(this.world.getHeight(bbD.END_PODIUM_LOCATION), Nk.DRAGON_EGG.getDefaultState());
         }

         this.previouslyKilled = true;
         this.dragonKilled = true;
      }

   }

   private void spawnNewGateway() {
      if (!this.gateways.isEmpty()) {
         int i = (Integer)this.gateways.remove(this.gateways.size() - 1);
         int j = (int)(96.0 * Math.cos(2.0 * (-3.141592653589793 + 0.15707963267948966 * (double)i)));
         int k = (int)(96.0 * Math.sin(2.0 * (-3.141592653589793 + 0.15707963267948966 * (double)i)));
         this.generateGateway(new BlockPos(j, 75, k));
      }

   }

   private void generateGateway(BlockPos pos) {
      this.world.playEvent(3000, pos, 0);
      (new bbB()).generate(this.world, new Random(), pos);
   }

   private void generatePortal(boolean active) {
      bbD worldgenendpodium = new bbD(active);
      if (this.exitPortalLocation == null) {
         for(this.exitPortalLocation = this.world.getTopSolidOrLiquidBlock(bbD.END_PODIUM_LOCATION).down(); this.world.getBlockState(this.exitPortalLocation).getBlock() == Nk.BEDROCK && this.exitPortalLocation.getY() > this.world.getSeaLevel(); this.exitPortalLocation = this.exitPortalLocation.down()) {
         }
      }

      worldgenendpodium.generate(this.world, new Random(), this.exitPortalLocation);
   }

   private HS createNewDragon() {
      this.world.getChunk(new BlockPos(0, 128, 0));
      HS entitydragon = new HS(this.world);
      entitydragon.getPhaseManager().setPhase(HK.HOLDING_PATTERN);
      entitydragon.setLocationAndAngles(0.0, 128.0, 0.0, this.world.rand.nextFloat() * 360.0F, 0.0F);
      this.world.spawnEntity(entitydragon);
      this.dragonUniqueId = entitydragon.getUniqueID();
      return entitydragon;
   }

   public void dragonUpdate(HS dragonIn) {
      if (dragonIn.getUniqueID().equals(this.dragonUniqueId)) {
         this.bossInfo.setPercent(dragonIn.getHealth() / dragonIn.getMaxHealth());
         this.ticksSinceDragonSeen = 0;
         if (dragonIn.hasCustomName()) {
            this.bossInfo.setName(dragonIn.getDisplayName());
         }
      }

   }

   public int getNumAliveCrystals() {
      return this.aliveCrystals;
   }

   public void onCrystalDestroyed(IS crystal, DamageSource dmgSrc) {
      if (this.respawnState != null && this.crystals.contains(crystal)) {
         LOGGER.debug("Aborting respawn sequence");
         this.respawnState = null;
         this.respawnStateTicks = 0;
         this.resetSpikeCrystals();
         this.generatePortal(true);
      } else {
         this.findAliveCrystals();
         Ig entity = this.world.getEntityFromUuid(this.dragonUniqueId);
         if (entity instanceof HS) {
            ((HS)entity).onCrystalDestroyed(crystal, new BlockPos(crystal), dmgSrc);
         }
      }

   }

   public boolean hasPreviouslyKilledDragon() {
      return this.previouslyKilled;
   }

   public void respawnDragon() {
      if (this.dragonKilled && this.respawnState == null) {
         BlockPos blockpos = this.exitPortalLocation;
         if (blockpos == null) {
            LOGGER.debug("Tried to respawn, but need to find the portal first.");
            is blockpattern$patternhelper = this.findExitPortal();
            if (blockpattern$patternhelper == null) {
               LOGGER.debug("Couldn't find a portal, so we made one.");
               this.generatePortal(true);
            } else {
               LOGGER.debug("Found the exit portal & temporarily using it.");
            }

            blockpos = this.exitPortalLocation;
         }

         List<IS> list1 = Lists.newArrayList();
         BlockPos blockpos1 = blockpos.up(1);
         Iterator var4 = EnumFacing.Plane.HORIZONTAL.iterator();

         while(var4.hasNext()) {
            EnumFacing enumfacing = (EnumFacing)var4.next();
            List<IS> list = this.world.getEntitiesWithinAABB(IS.class, new AxisAlignedBB(blockpos1.offset(enumfacing, 2)));
            if (list.isEmpty()) {
               return;
            }

            list1.addAll(list);
         }

         LOGGER.debug("Found all crystals, respawning dragon.");
         this.respawnDragon(list1);
      }

   }

   private void respawnDragon(List<IS> crystalsIn) {
      if (this.dragonKilled && this.respawnState == null) {
         for(is blockpattern$patternhelper = this.findExitPortal(); blockpattern$patternhelper != null; blockpattern$patternhelper = this.findExitPortal()) {
            for(int i = 0; i < this.portalPattern.getPalmLength(); ++i) {
               for(int j = 0; j < this.portalPattern.getThumbLength(); ++j) {
                  for(int k = 0; k < this.portalPattern.getFingerLength(); ++k) {
                     ik blockworldstate = blockpattern$patternhelper.translateOffset(i, j, k);
                     if (blockworldstate.getBlockState().getBlock() == Nk.BEDROCK || blockworldstate.getBlockState().getBlock() == Nk.END_PORTAL) {
                        this.world.setBlockState(blockworldstate.getPos(), Nk.END_STONE.getDefaultState());
                     }
                  }
               }
            }
         }

         this.respawnState = baT.START;
         this.respawnStateTicks = 0;
         this.generatePortal(false);
         this.crystals = crystalsIn;
      }

   }

   public void resetSpikeCrystals() {
      bcc[] var1 = Zw.getSpikesForWorld(this.world);
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         bcc worldgenspikes$endspike = var1[var3];
         Iterator var5 = this.world.getEntitiesWithinAABB(IS.class, worldgenspikes$endspike.getTopBoundingBox()).iterator();

         while(var5.hasNext()) {
            IS entityendercrystal = (IS)var5.next();
            entityendercrystal.setEntityInvulnerable(false);
            entityendercrystal.setBeamTarget((BlockPos)null);
         }
      }

   }

   static {
      VALID_PLAYER = Predicates.and(EntitySelectors.IS_ALIVE, EntitySelectors.withinRange(0.0, 128.0, 0.0, 192.0));
   }
}
