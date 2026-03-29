package neo;

import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import io.netty.buffer.Unpooled;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.ContainerHorseInventory;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.CooldownTrackerServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.IDataFixer;
import net.minecraft.util.datafix.IDataWalker;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MG extends ME implements IContainerListener {
   private static final Logger LOGGER = LogManager.getLogger();
   private String language = "en_US";
   public RY connection;
   public final Xx server;
   public final WW interactionManager;
   public double managedPosX;
   public double managedPosZ;
   private final List<Integer> entityRemoveQueue = Lists.newLinkedList();
   private final cl advancements;
   private final XU statsFile;
   private float lastHealthScore = Float.MIN_VALUE;
   private int lastFoodScore = Integer.MIN_VALUE;
   private int lastAirScore = Integer.MIN_VALUE;
   private int lastArmorScore = Integer.MIN_VALUE;
   private int lastLevelScore = Integer.MIN_VALUE;
   private int lastExperienceScore = Integer.MIN_VALUE;
   private float lastHealth = -1.0E8F;
   private int lastFoodLevel = -99999999;
   private boolean wasHungry = true;
   private int lastExperience = -99999999;
   private int respawnInvulnerabilityTicks = 60;
   private MB chatVisibility;
   private boolean chatColours = true;
   private long playerLastActiveTime = System.currentTimeMillis();
   private Ig spectatingEntity;
   private boolean invulnerableDimensionChange;
   private boolean seenCredits;
   private final XL recipeBook = new XL();
   private Vec3d levitationStartPos;
   private int levitatingSince;
   private boolean disconnected;
   private Vec3d enteredNetherPosition;
   private int currentWindowId;
   public boolean isChangingQuantityOnly;
   public int ping;
   public boolean queuedEndExit;

   public MG(Xx server, bis worldIn, GameProfile profile, WW interactionManagerIn) {
      super(worldIn, profile);
      interactionManagerIn.player = this;
      this.interactionManager = interactionManagerIn;
      BlockPos blockpos = worldIn.getSpawnPoint();
      if (worldIn.provider.hasSkyLight() && worldIn.getWorldInfo().getGameType() != bbb.ADVENTURE) {
         int i = Math.max(0, server.getSpawnRadius(worldIn));
         int j = MathHelper.floor(worldIn.getWorldBorder().getClosestDistance((double)blockpos.getX(), (double)blockpos.getZ()));
         if (j < i) {
            i = j;
         }

         if (j <= 1) {
            i = 1;
         }

         blockpos = worldIn.getTopSolidOrLiquidBlock(blockpos.add(this.rand.nextInt(i * 2 + 1) - i, 0, this.rand.nextInt(i * 2 + 1) - i));
      }

      this.server = server;
      this.statsFile = server.getPlayerList().getPlayerStatsFile(this);
      this.advancements = server.getPlayerList().getPlayerAdvancements(this);
      this.stepHeight = 1.0F;
      this.moveToBlockPosAndAngles(blockpos, 0.0F, 0.0F);

      while(!worldIn.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty() && this.posY < 255.0) {
         this.setPosition(this.posX, this.posY + 1.0, this.posZ);
      }

   }

   public void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      if (compound.hasKey("playerGameType", 99)) {
         if (this.getServer().getForceGamemode()) {
            this.interactionManager.setGameType(this.getServer().getGameType());
         } else {
            this.interactionManager.setGameType(bbb.getByID(compound.getInteger("playerGameType")));
         }
      }

      if (compound.hasKey("enteredNetherPosition", 10)) {
         QQ nbttagcompound = compound.getCompoundTag("enteredNetherPosition");
         this.enteredNetherPosition = new Vec3d(nbttagcompound.getDouble("x"), nbttagcompound.getDouble("y"), nbttagcompound.getDouble("z"));
      }

      this.seenCredits = compound.getBoolean("seenCredits");
      if (compound.hasKey("recipeBook", 10)) {
         this.recipeBook.read(compound.getCompoundTag("recipeBook"));
      }

   }

   public static void registerFixesPlayerMP(DataFixer p_191522_0_) {
      p_191522_0_.registerWalker(FixTypes.PLAYER, new IDataWalker() {
         public QQ process(IDataFixer fixer, QQ compound, int versionIn) {
            if (compound.hasKey("RootVehicle", 10)) {
               QQ nbttagcompound = compound.getCompoundTag("RootVehicle");
               if (nbttagcompound.hasKey("Entity", 10)) {
                  nbttagcompound.setTag("Entity", fixer.process(FixTypes.ENTITY, nbttagcompound.getCompoundTag("Entity"), versionIn));
               }
            }

            return compound;
         }
      });
   }

   public void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      compound.setInteger("playerGameType", this.interactionManager.getGameType().getID());
      compound.setBoolean("seenCredits", this.seenCredits);
      if (this.enteredNetherPosition != null) {
         QQ nbttagcompound = new QQ();
         nbttagcompound.setDouble("x", this.enteredNetherPosition.x);
         nbttagcompound.setDouble("y", this.enteredNetherPosition.y);
         nbttagcompound.setDouble("z", this.enteredNetherPosition.z);
         compound.setTag("enteredNetherPosition", nbttagcompound);
      }

      Ig entity1 = this.getLowestRidingEntity();
      Ig entity = this.getRidingEntity();
      if (entity != null && entity1 != this && entity1.getRecursivePassengersByType(MG.class).size() == 1) {
         QQ nbttagcompound1 = new QQ();
         QQ nbttagcompound2 = new QQ();
         entity1.writeToNBTOptional(nbttagcompound2);
         nbttagcompound1.setUniqueId("Attach", entity.getUniqueID());
         nbttagcompound1.setTag("Entity", nbttagcompound2);
         compound.setTag("RootVehicle", nbttagcompound1);
      }

      compound.setTag("recipeBook", this.recipeBook.write());
   }

   public void addExperienceLevel(int levels) {
      super.addExperienceLevel(levels);
      this.lastExperience = -1;
   }

   public void onEnchant(Qy enchantedItem, int cost) {
      super.onEnchant(enchantedItem, cost);
      this.lastExperience = -1;
   }

   public void addSelfToInternalCraftingInventory() {
      this.openContainer.addListener(this);
   }

   public void sendEnterCombat() {
      super.sendEnterCombat();
      this.connection.sendPacket(new TJ(this.getCombatTracker(), TI.ENTER_COMBAT));
   }

   public void sendEndCombat() {
      super.sendEndCombat();
      this.connection.sendPacket(new TJ(this.getCombatTracker(), TI.END_COMBAT));
   }

   protected void onInsideBlock(in p_191955_1_) {
      bY.ENTER_BLOCK.trigger(this, p_191955_1_);
   }

   protected CooldownTracker createCooldownTracker() {
      return new CooldownTrackerServer(this);
   }

   public void onUpdate() {
      this.interactionManager.updateBlockRemoving();
      --this.respawnInvulnerabilityTicks;
      if (this.hurtResistantTime > 0) {
         --this.hurtResistantTime;
      }

      this.openContainer.detectAndSendChanges();
      if (!this.world.isRemote && !this.openContainer.canInteractWith(this)) {
         this.closeScreen();
         this.openContainer = this.inventoryContainer;
      }

      while(!this.entityRemoveQueue.isEmpty()) {
         int i = Math.min(this.entityRemoveQueue.size(), Integer.MAX_VALUE);
         int[] aint = new int[i];
         Iterator<Integer> iterator = this.entityRemoveQueue.iterator();
         int j = 0;

         while(iterator.hasNext() && j < i) {
            aint[j++] = (Integer)iterator.next();
            iterator.remove();
         }

         this.connection.sendPacket(new TO(aint));
      }

      Ig entity = this.getSpectatingEntity();
      if (entity != this) {
         if (entity.isEntityAlive()) {
            this.setPositionAndRotation(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
            this.server.getPlayerList().serverUpdateMovingPlayer(this);
            if (this.isSneaking()) {
               this.setSpectatingEntity(this);
            }
         } else {
            this.setSpectatingEntity(this);
         }
      }

      bY.TICK.trigger(this);
      if (this.levitationStartPos != null) {
         bY.LEVITATION.trigger(this, this.levitationStartPos, this.ticksExisted - this.levitatingSince);
      }

      this.advancements.flushDirty(this);
   }

   public void onUpdateEntity() {
      try {
         super.onUpdate();

         for(int i = 0; i < this.inventory.getSizeInventory(); ++i) {
            Qy itemstack = this.inventory.getStackInSlot(i);
            if (!itemstack.isEmpty() && itemstack.getItem().isMap()) {
               Sz<?> packet = ((PU)itemstack.getItem()).createMapDataPacket(itemstack, this.world, this);
               if (packet != null) {
                  this.connection.sendPacket(packet);
               }
            }
         }

         if (this.getHealth() != this.lastHealth || this.lastFoodLevel != this.foodStats.getFoodLevel() || this.foodStats.getSaturationLevel() == 0.0F != this.wasHungry) {
            this.connection.sendPacket(new Vd(this.getHealth(), this.foodStats.getFoodLevel(), this.foodStats.getSaturationLevel()));
            this.lastHealth = this.getHealth();
            this.lastFoodLevel = this.foodStats.getFoodLevel();
            this.wasHungry = this.foodStats.getSaturationLevel() == 0.0F;
         }

         if (this.getHealth() + this.getAbsorptionAmount() != this.lastHealthScore) {
            this.lastHealthScore = this.getHealth() + this.getAbsorptionAmount();
            this.updateScorePoints(Wo.HEALTH, MathHelper.ceil(this.lastHealthScore));
         }

         if (this.foodStats.getFoodLevel() != this.lastFoodScore) {
            this.lastFoodScore = this.foodStats.getFoodLevel();
            this.updateScorePoints(Wo.FOOD, MathHelper.ceil((float)this.lastFoodScore));
         }

         if (this.getAir() != this.lastAirScore) {
            this.lastAirScore = this.getAir();
            this.updateScorePoints(Wo.AIR, MathHelper.ceil((float)this.lastAirScore));
         }

         if (this.getTotalArmorValue() != this.lastArmorScore) {
            this.lastArmorScore = this.getTotalArmorValue();
            this.updateScorePoints(Wo.ARMOR, MathHelper.ceil((float)this.lastArmorScore));
         }

         if (this.experienceTotal != this.lastExperienceScore) {
            this.lastExperienceScore = this.experienceTotal;
            this.updateScorePoints(Wo.XP, MathHelper.ceil((float)this.lastExperienceScore));
         }

         if (this.experienceLevel != this.lastLevelScore) {
            this.lastLevelScore = this.experienceLevel;
            this.updateScorePoints(Wo.LEVEL, MathHelper.ceil((float)this.lastLevelScore));
         }

         if (this.experienceTotal != this.lastExperience) {
            this.lastExperience = this.experienceTotal;
            this.connection.sendPacket(new UH(this.experience, this.experienceTotal, this.experienceLevel));
         }

         if (this.ticksExisted % 20 == 0) {
            bY.LOCATION.trigger(this);
         }

      } catch (Throwable var4) {
         Throwable throwable = var4;
         Er crashreport = Er.makeCrashReport(throwable, "Ticking player");
         Ey crashreportcategory = crashreport.makeCategory("Player being ticked");
         this.addEntityCrashInfo(crashreportcategory);
         throw new ReportedException(crashreport);
      }
   }

   private void updateScorePoints(Wo criteria, int points) {
      Iterator var3 = this.getWorldScoreboard().getObjectivesFromCriteria(criteria).iterator();

      while(var3.hasNext()) {
         Wz scoreobjective = (Wz)var3.next();
         Wr score = this.getWorldScoreboard().getOrCreateScore(this.getName(), scoreobjective);
         score.setScorePoints(points);
      }

   }

   public void onDeath(DamageSource cause) {
      boolean flag = this.world.getGameRules().getBoolean("showDeathMessages");
      this.connection.sendPacket(new TJ(this.getCombatTracker(), TI.ENTITY_DIED, flag));
      if (flag) {
         WE team = this.getTeam();
         if (team != null && team.getDeathMessageVisibility() != WD.ALWAYS) {
            if (team.getDeathMessageVisibility() == WD.HIDE_FOR_OTHER_TEAMS) {
               this.server.getPlayerList().sendMessageToAllTeamMembers(this, this.getCombatTracker().getDeathMessage());
            } else if (team.getDeathMessageVisibility() == WD.HIDE_FOR_OWN_TEAM) {
               this.server.getPlayerList().sendMessageToTeamOrAllPlayers(this, this.getCombatTracker().getDeathMessage());
            }
         } else {
            this.server.getPlayerList().sendMessage(this.getCombatTracker().getDeathMessage());
         }
      }

      this.spawnShoulderEntities();
      if (!this.world.getGameRules().getBoolean("keepInventory") && !this.isSpectator()) {
         this.destroyVanishingCursedItems();
         this.inventory.dropAllItems();
      }

      Iterator var6 = this.world.getScoreboard().getObjectivesFromCriteria(Wo.DEATH_COUNT).iterator();

      while(var6.hasNext()) {
         Wz scoreobjective = (Wz)var6.next();
         Wr score = this.getWorldScoreboard().getOrCreateScore(this.getName(), scoreobjective);
         score.incrementScore();
      }

      Iw entitylivingbase = this.getAttackingEntity();
      if (entitylivingbase != null) {
         Iq entitylist$entityegginfo = (Iq)Ir.ENTITY_EGGS.get(Ir.getKey((Ig)entitylivingbase));
         if (entitylist$entityegginfo != null) {
            this.addStat(entitylist$entityegginfo.entityKilledByStat);
         }

         entitylivingbase.awardKillScore(this, this.scoreValue, cause);
      }

      this.addStat(XV.DEATHS);
      this.takeStat(XV.TIME_SINCE_DEATH);
      this.extinguish();
      this.setFlag(0, false);
      this.getCombatTracker().reset();
   }

   public void awardKillScore(Ig p_191956_1_, int p_191956_2_, DamageSource p_191956_3_) {
      if (p_191956_1_ != this) {
         super.awardKillScore(p_191956_1_, p_191956_2_, p_191956_3_);
         this.addScore(p_191956_2_);
         Collection<Wz> collection = this.getWorldScoreboard().getObjectivesFromCriteria(Wo.TOTAL_KILL_COUNT);
         if (p_191956_1_ instanceof ME) {
            this.addStat(XV.PLAYER_KILLS);
            collection.addAll(this.getWorldScoreboard().getObjectivesFromCriteria(Wo.PLAYER_KILL_COUNT));
         } else {
            this.addStat(XV.MOB_KILLS);
         }

         collection.addAll(this.awardTeamKillScores(p_191956_1_));
         Iterator var5 = collection.iterator();

         while(var5.hasNext()) {
            Wz scoreobjective = (Wz)var5.next();
            this.getWorldScoreboard().getOrCreateScore(this.getName(), scoreobjective).incrementScore();
         }

         bY.PLAYER_KILLED_ENTITY.trigger(this, p_191956_1_, p_191956_3_);
      }

   }

   private Collection<Wz> awardTeamKillScores(Ig p_192038_1_) {
      String s = p_192038_1_ instanceof ME ? p_192038_1_.getName() : p_192038_1_.getCachedUniqueIdString();
      WA scoreplayerteam = this.getWorldScoreboard().getPlayersTeam(this.getName());
      if (scoreplayerteam != null) {
         int i = scoreplayerteam.getColor().getColorIndex();
         if (i >= 0 && i < Wo.KILLED_BY_TEAM.length) {
            Iterator var5 = this.getWorldScoreboard().getObjectivesFromCriteria(Wo.KILLED_BY_TEAM[i]).iterator();

            while(var5.hasNext()) {
               Wz scoreobjective = (Wz)var5.next();
               Wr score = this.getWorldScoreboard().getOrCreateScore(s, scoreobjective);
               score.incrementScore();
            }
         }
      }

      WA scoreplayerteam1 = this.getWorldScoreboard().getPlayersTeam(s);
      if (scoreplayerteam1 != null) {
         int j = scoreplayerteam1.getColor().getColorIndex();
         if (j >= 0 && j < Wo.TEAM_KILL.length) {
            return this.getWorldScoreboard().getObjectivesFromCriteria(Wo.TEAM_KILL[j]);
         }
      }

      return Lists.newArrayList();
   }

   public boolean attackEntityFrom(DamageSource source, float amount) {
      if (this.isEntityInvulnerable(source)) {
         return false;
      } else {
         boolean flag = this.server.isDedicatedServer() && this.canPlayersAttack() && "fall".equals(source.damageType);
         if (!flag && this.respawnInvulnerabilityTicks > 0 && source != DamageSource.OUT_OF_WORLD) {
            return false;
         } else {
            if (source instanceof EntityDamageSource) {
               Ig entity = source.getTrueSource();
               if (entity instanceof ME && !this.canAttackPlayer((ME)entity)) {
                  return false;
               }

               if (entity instanceof MO) {
                  MO entityarrow = (MO)entity;
                  if (entityarrow.shootingEntity instanceof ME && !this.canAttackPlayer((ME)entityarrow.shootingEntity)) {
                     return false;
                  }
               }
            }

            return super.attackEntityFrom(source, amount);
         }
      }
   }

   public boolean canAttackPlayer(ME other) {
      return !this.canPlayersAttack() ? false : super.canAttackPlayer(other);
   }

   private boolean canPlayersAttack() {
      return this.server.isPVPEnabled();
   }

   @Nullable
   public Ig changeDimension(int dimensionIn) {
      this.invulnerableDimensionChange = true;
      if (this.dimension == 0 && dimensionIn == -1) {
         this.enteredNetherPosition = new Vec3d(this.posX, this.posY, this.posZ);
      } else if (this.dimension != -1 && dimensionIn != 0) {
         this.enteredNetherPosition = null;
      }

      if (this.dimension == 1 && dimensionIn == 1) {
         this.world.removeEntity(this);
         if (!this.queuedEndExit) {
            this.queuedEndExit = true;
            this.connection.sendPacket(new TC(4, this.seenCredits ? 0.0F : 1.0F));
            this.seenCredits = true;
         }

         return this;
      } else {
         if (this.dimension == 0 && dimensionIn == 1) {
            dimensionIn = 1;
         }

         this.server.getPlayerList().changePlayerDimension(this, dimensionIn);
         this.connection.sendPacket(new TR(1032, BlockPos.ORIGIN, 0, false));
         this.lastExperience = -1;
         this.lastHealth = -1.0F;
         this.lastFoodLevel = -1;
         return this;
      }
   }

   public boolean isSpectatedByPlayer(MG player) {
      if (player.isSpectator()) {
         return this.getSpectatingEntity() == this;
      } else {
         return this.isSpectator() ? false : super.isSpectatedByPlayer(player);
      }
   }

   private void sendTileEntityUpdate(Yg p_147097_1_) {
      if (p_147097_1_ != null) {
         Vg spacketupdatetileentity = p_147097_1_.getUpdatePacket();
         if (spacketupdatetileentity != null) {
            this.connection.sendPacket(spacketupdatetileentity);
         }
      }

   }

   public void onItemPickup(Ig entityIn, int quantity) {
      super.onItemPickup(entityIn, quantity);
      this.openContainer.detectAndSendChanges();
   }

   public MD trySleep(BlockPos bedLocation) {
      MD entityplayer$sleepresult = super.trySleep(bedLocation);
      if (entityplayer$sleepresult == MD.OK) {
         this.addStat(XV.SLEEP_IN_BED);
         Sz<?> packet = new Vh(this, bedLocation);
         this.getServerWorld().getEntityTracker().sendToTracking(this, packet);
         this.connection.setPlayerLocation(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
         this.connection.sendPacket(packet);
         bY.SLEPT_IN_BED.trigger(this);
      }

      return entityplayer$sleepresult;
   }

   public void wakeUpPlayer(boolean immediately, boolean updateWorldFlag, boolean setSpawn) {
      if (this.isPlayerSleeping()) {
         this.getServerWorld().getEntityTracker().sendToTrackingAndSelf(this, new Tx(this, 2));
      }

      super.wakeUpPlayer(immediately, updateWorldFlag, setSpawn);
      if (this.connection != null) {
         this.connection.setPlayerLocation(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
      }

   }

   public boolean startRiding(Ig entityIn, boolean force) {
      Ig entity = this.getRidingEntity();
      if (!super.startRiding(entityIn, force)) {
         return false;
      } else {
         Ig entity1 = this.getRidingEntity();
         if (entity1 != entity && this.connection != null) {
            this.connection.setPlayerLocation(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
         }

         return true;
      }
   }

   public void dismountRidingEntity() {
      Ig entity = this.getRidingEntity();
      super.dismountRidingEntity();
      Ig entity1 = this.getRidingEntity();
      if (entity1 != entity && this.connection != null) {
         this.connection.setPlayerLocation(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
      }

   }

   public boolean isEntityInvulnerable(DamageSource source) {
      return super.isEntityInvulnerable(source) || this.isInvulnerableDimensionChange();
   }

   protected void updateFallState(double y, boolean onGroundIn, in state, BlockPos pos) {
   }

   protected void frostWalk(BlockPos pos) {
      if (!this.isSpectator()) {
         super.frostWalk(pos);
      }

   }

   public void handleFalling(double y, boolean onGroundIn) {
      int i = MathHelper.floor(this.posX);
      int j = MathHelper.floor(this.posY - 0.20000000298023224);
      int k = MathHelper.floor(this.posZ);
      BlockPos blockpos = new BlockPos(i, j, k);
      in iblockstate = this.world.getBlockState(blockpos);
      if (iblockstate.getMaterial() == hM.AIR) {
         BlockPos blockpos1 = blockpos.down();
         in iblockstate1 = this.world.getBlockState(blockpos1);
         co block = iblockstate1.getBlock();
         if (block instanceof dL || block instanceof hz || block instanceof dM) {
            blockpos = blockpos1;
            iblockstate = iblockstate1;
         }
      }

      super.updateFallState(y, onGroundIn, iblockstate, blockpos);
   }

   public void openEditSign(YQ signTile) {
      signTile.setPlayer(this);
      this.connection.sendPacket(new UK(signTile.getPos()));
   }

   private void getNextWindowId() {
      this.currentWindowId = this.currentWindowId % 100 + 1;
   }

   public void displayGui(bga guiOwner) {
      if (guiOwner instanceof bhb && ((bhb)guiOwner).getLootTable() != null && this.isSpectator()) {
         this.sendStatusMessage((new TextComponentTranslation("container.spectatorCantOpen", new Object[0])).setStyle((new Style()).setColor(TextFormatting.RED)), true);
      } else {
         this.getNextWindowId();
         this.connection.sendPacket(new Uo(this.currentWindowId, guiOwner.getGuiID(), guiOwner.getDisplayName()));
         this.openContainer = guiOwner.createContainer(this.inventory, this);
         this.openContainer.windowId = this.currentWindowId;
         this.openContainer.addListener(this);
      }

   }

   public void displayGUIChest(IInventory chestInventory) {
      if (chestInventory instanceof bhb && ((bhb)chestInventory).getLootTable() != null && this.isSpectator()) {
         this.sendStatusMessage((new TextComponentTranslation("container.spectatorCantOpen", new Object[0])).setStyle((new Style()).setColor(TextFormatting.RED)), true);
      } else {
         if (this.openContainer != this.inventoryContainer) {
            this.closeScreen();
         }

         if (chestInventory instanceof bgb) {
            bgb ilockablecontainer = (bgb)chestInventory;
            if (ilockablecontainer.isLocked() && !this.canOpen(ilockablecontainer.getLockCode()) && !this.isSpectator()) {
               this.connection.sendPacket(new TD(new TextComponentTranslation("container.isLocked", new Object[]{chestInventory.getDisplayName()}), ChatType.GAME_INFO));
               this.connection.sendPacket(new UL(NO.BLOCK_CHEST_LOCKED, SoundCategory.BLOCKS, this.posX, this.posY, this.posZ, 1.0F, 1.0F));
               return;
            }
         }

         this.getNextWindowId();
         if (chestInventory instanceof bga) {
            this.connection.sendPacket(new Uo(this.currentWindowId, ((bga)chestInventory).getGuiID(), chestInventory.getDisplayName(), chestInventory.getSizeInventory()));
            this.openContainer = ((bga)chestInventory).createContainer(this.inventory, this);
         } else {
            this.connection.sendPacket(new Uo(this.currentWindowId, "minecraft:container", chestInventory.getDisplayName(), chestInventory.getSizeInventory()));
            this.openContainer = new ContainerChest(this.inventory, chestInventory, this);
         }

         this.openContainer.windowId = this.currentWindowId;
         this.openContainer.addListener(this);
      }

   }

   public void displayVillagerTradeGui(IH villager) {
      this.getNextWindowId();
      this.openContainer = new ContainerMerchant(this.inventory, villager, this.world);
      this.openContainer.windowId = this.currentWindowId;
      this.openContainer.addListener(this);
      IInventory iinventory = ((ContainerMerchant)this.openContainer).getMerchantInventory();
      ITextComponent itextcomponent = villager.getDisplayName();
      this.connection.sendPacket(new Uo(this.currentWindowId, "minecraft:villager", itextcomponent, iinventory.getSizeInventory()));
      YX merchantrecipelist = villager.getRecipes(this);
      if (merchantrecipelist != null) {
         SA packetbuffer = new SA(Unpooled.buffer());
         packetbuffer.writeInt(this.currentWindowId);
         merchantrecipelist.writeToBuf(packetbuffer);
         this.connection.sendPacket(new TM("MC|TrList", packetbuffer));
      }

   }

   public void openGuiHorseInventory(Lw horse, IInventory inventoryIn) {
      if (this.openContainer != this.inventoryContainer) {
         this.closeScreen();
      }

      this.getNextWindowId();
      this.connection.sendPacket(new Uo(this.currentWindowId, "EntityHorse", inventoryIn.getDisplayName(), inventoryIn.getSizeInventory(), horse.getEntityId()));
      this.openContainer = new ContainerHorseInventory(this.inventory, inventoryIn, horse, this);
      this.openContainer.windowId = this.currentWindowId;
      this.openContainer.addListener(this);
   }

   public void openBook(Qy stack, EnumHand hand) {
      OL item = stack.getItem();
      if (item == NK.WRITTEN_BOOK) {
         SA packetbuffer = new SA(Unpooled.buffer());
         packetbuffer.writeEnumValue(hand);
         this.connection.sendPacket(new TM("MC|BOpen", packetbuffer));
      }

   }

   public void displayGuiCommandBlock(Yq commandBlock) {
      commandBlock.setSendToClient(true);
      this.sendTileEntityUpdate(commandBlock);
   }

   public void sendSlotContents(Container containerToSend, int slotInd, Qy stack) {
      if (!(containerToSend.getSlot(slotInd) instanceof SlotCrafting)) {
         if (containerToSend == this.inventoryContainer) {
            bY.INVENTORY_CHANGED.trigger(this, this.inventory);
         }

         if (!this.isChangingQuantityOnly) {
            this.connection.sendPacket(new UJ(containerToSend.windowId, slotInd, stack));
         }
      }

   }

   public void sendContainerToPlayer(Container containerIn) {
      this.sendAllContents(containerIn, containerIn.getInventory());
   }

   public void sendAllContents(Container containerToSend, NonNullList<Qy> itemsList) {
      this.connection.sendPacket(new Vi(containerToSend.windowId, itemsList));
      this.connection.sendPacket(new UJ(-1, -1, this.inventory.getItemStack()));
   }

   public void sendWindowProperty(Container containerIn, int varToUpdate, int newValue) {
      this.connection.sendPacket(new Vj(containerIn.windowId, varToUpdate, newValue));
   }

   public void sendAllWindowProperties(Container containerIn, IInventory inventory) {
      for(int i = 0; i < inventory.getFieldCount(); ++i) {
         this.connection.sendPacket(new Vj(containerIn.windowId, i, inventory.getField(i)));
      }

   }

   public void closeScreen() {
      this.connection.sendPacket(new TF(this.openContainer.windowId));
      this.closeContainer();
   }

   public void updateHeldItem() {
      if (!this.isChangingQuantityOnly) {
         this.connection.sendPacket(new UJ(-1, -1, this.inventory.getItemStack()));
      }

   }

   public void closeContainer() {
      this.openContainer.onContainerClosed(this);
      this.openContainer = this.inventoryContainer;
   }

   public void setEntityActionState(float strafe, float forward, boolean jumping, boolean sneaking) {
      if (this.isRiding()) {
         if (strafe >= -1.0F && strafe <= 1.0F) {
            this.moveStrafing = strafe;
         }

         if (forward >= -1.0F && forward <= 1.0F) {
            this.moveForward = forward;
         }

         this.isJumping = jumping;
         this.setSneaking(sneaking);
      }

   }

   public void addStat(XQ stat, int amount) {
      if (stat != null) {
         this.statsFile.increaseStat(this, stat, amount);
         Iterator var3 = this.getWorldScoreboard().getObjectivesFromCriteria(stat.getCriteria()).iterator();

         while(var3.hasNext()) {
            Wz scoreobjective = (Wz)var3.next();
            this.getWorldScoreboard().getOrCreateScore(this.getName(), scoreobjective).increaseScore(amount);
         }
      }

   }

   public void takeStat(XQ stat) {
      if (stat != null) {
         this.statsFile.unlockAchievement(this, stat, 0);
         Iterator var2 = this.getWorldScoreboard().getObjectivesFromCriteria(stat.getCriteria()).iterator();

         while(var2.hasNext()) {
            Wz scoreobjective = (Wz)var2.next();
            this.getWorldScoreboard().getOrCreateScore(this.getName(), scoreobjective).setScorePoints(0);
         }
      }

   }

   public void unlockRecipes(List<NT> p_192021_1_) {
      this.recipeBook.add(p_192021_1_, this);
   }

   public void unlockRecipes(ResourceLocation[] p_193102_1_) {
      List<NT> list = Lists.newArrayList();
      ResourceLocation[] var3 = p_193102_1_;
      int var4 = p_193102_1_.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         ResourceLocation resourcelocation = var3[var5];
         list.add(NP.getRecipe(resourcelocation));
      }

      this.unlockRecipes((List)list);
   }

   public void resetRecipes(List<NT> p_192022_1_) {
      this.recipeBook.remove(p_192022_1_, this);
   }

   public void mountEntityAndWakeUp() {
      this.disconnected = true;
      this.removePassengers();
      if (this.sleeping) {
         this.wakeUpPlayer(true, false, false);
      }

   }

   public boolean hasDisconnected() {
      return this.disconnected;
   }

   public void setPlayerHealthUpdated() {
      this.lastHealth = -1.0E8F;
   }

   public void sendStatusMessage(ITextComponent chatComponent, boolean actionBar) {
      this.connection.sendPacket(new TD(chatComponent, actionBar ? ChatType.GAME_INFO : ChatType.CHAT));
   }

   protected void onItemUseFinish() {
      if (!this.activeItemStack.isEmpty() && this.isHandActive()) {
         this.connection.sendPacket(new Ud(this, (byte)9));
         super.onItemUseFinish();
      }

   }

   public void copyFrom(MG that, boolean keepEverything) {
      if (keepEverything) {
         this.inventory.copyInventory(that.inventory);
         this.setHealth(that.getHealth());
         this.foodStats = that.foodStats;
         this.experienceLevel = that.experienceLevel;
         this.experienceTotal = that.experienceTotal;
         this.experience = that.experience;
         this.setScore(that.getScore());
         this.lastPortalPos = that.lastPortalPos;
         this.lastPortalVec = that.lastPortalVec;
         this.teleportDirection = that.teleportDirection;
      } else if (this.world.getGameRules().getBoolean("keepInventory") || that.isSpectator()) {
         this.inventory.copyInventory(that.inventory);
         this.experienceLevel = that.experienceLevel;
         this.experienceTotal = that.experienceTotal;
         this.experience = that.experience;
         this.setScore(that.getScore());
      }

      this.xpSeed = that.xpSeed;
      this.enderChest = that.enderChest;
      this.getDataManager().set(PLAYER_MODEL_FLAG, that.getDataManager().get(PLAYER_MODEL_FLAG));
      this.lastExperience = -1;
      this.lastHealth = -1.0F;
      this.lastFoodLevel = -1;
      this.recipeBook.copyFrom(that.recipeBook);
      this.entityRemoveQueue.addAll(that.entityRemoveQueue);
      this.seenCredits = that.seenCredits;
      this.enteredNetherPosition = that.enteredNetherPosition;
      this.setLeftShoulderEntity(that.getLeftShoulderEntity());
      this.setRightShoulderEntity(that.getRightShoulderEntity());
   }

   protected void onNewPotionEffect(VZ id) {
      super.onNewPotionEffect(id);
      this.connection.sendPacket(new TX(this.getEntityId(), id));
      if (id.getPotion() == NL.LEVITATION) {
         this.levitatingSince = this.ticksExisted;
         this.levitationStartPos = new Vec3d(this.posX, this.posY, this.posZ);
      }

      bY.EFFECTS_CHANGED.trigger(this);
   }

   protected void onChangedPotionEffect(VZ id, boolean p_70695_2_) {
      super.onChangedPotionEffect(id, p_70695_2_);
      this.connection.sendPacket(new TX(this.getEntityId(), id));
      bY.EFFECTS_CHANGED.trigger(this);
   }

   protected void onFinishedPotionEffect(VZ effect) {
      super.onFinishedPotionEffect(effect);
      this.connection.sendPacket(new UB(this.getEntityId(), effect.getPotion()));
      if (effect.getPotion() == NL.LEVITATION) {
         this.levitationStartPos = null;
      }

      bY.EFFECTS_CHANGED.trigger(this);
   }

   public void setPositionAndUpdate(double x, double y, double z) {
      this.connection.setPlayerLocation(x, y, z, this.rotationYaw, this.rotationPitch);
   }

   public void onCriticalHit(Ig entityHit) {
      this.getServerWorld().getEntityTracker().sendToTrackingAndSelf(this, new Tx(entityHit, 4));
   }

   public void onEnchantmentCritical(Ig entityHit) {
      this.getServerWorld().getEntityTracker().sendToTrackingAndSelf(this, new Tx(entityHit, 5));
   }

   public void sendPlayerAbilities() {
      if (this.connection != null) {
         this.connection.sendPacket(new Ur(this.capabilities));
         this.updatePotionMetadata();
      }

   }

   public bis getServerWorld() {
      return (bis)this.world;
   }

   public void setGameType(bbb gameType) {
      this.interactionManager.setGameType(gameType);
      this.connection.sendPacket(new TC(3, (float)gameType.getID()));
      if (gameType == bbb.SPECTATOR) {
         this.spawnShoulderEntities();
         this.dismountRidingEntity();
      } else {
         this.setSpectatingEntity(this);
      }

      this.sendPlayerAbilities();
      this.markPotionsDirty();
   }

   public boolean isSpectator() {
      return this.interactionManager.getGameType() == bbb.SPECTATOR;
   }

   public boolean isCreative() {
      return this.interactionManager.getGameType() == bbb.CREATIVE;
   }

   public void sendMessage(ITextComponent component) {
      this.connection.sendPacket(new TD(component));
   }

   public boolean canUseCommand(int permLevel, String commandName) {
      if ("seed".equals(commandName) && !this.server.isDedicatedServer()) {
         return true;
      } else if (!"tell".equals(commandName) && !"help".equals(commandName) && !"me".equals(commandName) && !"trigger".equals(commandName)) {
         if (this.server.getPlayerList().canSendCommands(this.getGameProfile())) {
            Xr userlistopsentry = (Xr)this.server.getPlayerList().getOppedPlayers().getEntry(this.getGameProfile());
            if (userlistopsentry != null) {
               return userlistopsentry.getPermissionLevel() >= permLevel;
            } else {
               return this.server.getOpPermissionLevel() >= permLevel;
            }
         } else {
            return false;
         }
      } else {
         return true;
      }
   }

   public String getPlayerIP() {
      String s = this.connection.netManager.getRemoteAddress().toString();
      s = s.substring(s.indexOf("/") + 1);
      s = s.substring(0, s.indexOf(":"));
      return s;
   }

   public void handleClientSettings(SG packetIn) {
      this.language = packetIn.getLang();
      this.chatVisibility = packetIn.getChatVisibility();
      this.chatColours = packetIn.isColorsEnabled();
      this.getDataManager().set(PLAYER_MODEL_FLAG, (byte)packetIn.getModelPartFlags());
      this.getDataManager().set(MAIN_HAND, (byte)(packetIn.getMainHand() == EnumHandSide.LEFT ? 0 : 1));
   }

   public MB getChatVisibility() {
      return this.chatVisibility;
   }

   public void loadResourcePack(String url, String hash) {
      this.connection.sendPacket(new UC(url, hash));
   }

   public BlockPos getPosition() {
      return new BlockPos(this.posX, this.posY + 0.5, this.posZ);
   }

   public void markPlayerActive() {
      this.playerLastActiveTime = Xx.getCurrentTimeMillis();
   }

   public XU getStatFile() {
      return this.statsFile;
   }

   public XL getRecipeBook() {
      return this.recipeBook;
   }

   public void removeEntity(Ig entityIn) {
      if (entityIn instanceof ME) {
         this.connection.sendPacket(new TO(new int[]{entityIn.getEntityId()}));
      } else {
         this.entityRemoveQueue.add(entityIn.getEntityId());
      }

   }

   public void addEntity(Ig entityIn) {
      this.entityRemoveQueue.remove(entityIn.getEntityId());
   }

   protected void updatePotionMetadata() {
      if (this.isSpectator()) {
         this.resetPotionEffectMetadata();
         this.setInvisible(true);
      } else {
         super.updatePotionMetadata();
      }

      this.getServerWorld().getEntityTracker().updateVisibility(this);
   }

   public Ig getSpectatingEntity() {
      return (Ig)(this.spectatingEntity == null ? this : this.spectatingEntity);
   }

   public void setSpectatingEntity(Ig entityToSpectate) {
      Ig entity = this.getSpectatingEntity();
      this.spectatingEntity = (Ig)(entityToSpectate == null ? this : entityToSpectate);
      if (entity != this.spectatingEntity) {
         this.connection.sendPacket(new TB(this.spectatingEntity));
         this.setPositionAndUpdate(this.spectatingEntity.posX, this.spectatingEntity.posY, this.spectatingEntity.posZ);
      }

   }

   protected void decrementTimeUntilPortal() {
      if (this.timeUntilPortal > 0 && !this.invulnerableDimensionChange) {
         --this.timeUntilPortal;
      }

   }

   public void attackTargetEntityWithCurrentItem(Ig targetEntity) {
      if (this.interactionManager.getGameType() == bbb.SPECTATOR) {
         this.setSpectatingEntity(targetEntity);
      } else {
         super.attackTargetEntityWithCurrentItem(targetEntity);
      }

   }

   public long getLastActiveTime() {
      return this.playerLastActiveTime;
   }

   @Nullable
   public ITextComponent getTabListDisplayName() {
      return null;
   }

   public void swingArm(EnumHand hand) {
      super.swingArm(hand);
      this.resetCooldown();
   }

   public boolean isInvulnerableDimensionChange() {
      return this.invulnerableDimensionChange;
   }

   public void clearInvulnerableDimensionChange() {
      this.invulnerableDimensionChange = false;
   }

   public void setElytraFlying() {
      this.setFlag(7, true);
   }

   public void clearElytraFlying() {
      this.setFlag(7, true);
      this.setFlag(7, false);
   }

   public cl getAdvancements() {
      return this.advancements;
   }

   @Nullable
   public Vec3d getEnteredNetherPosition() {
      return this.enteredNetherPosition;
   }
}
