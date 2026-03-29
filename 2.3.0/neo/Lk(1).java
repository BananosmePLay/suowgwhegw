package neo;

import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class Lk extends Kl {
   protected static final FY SPAWN_REINFORCEMENTS_CHANCE = (new Gc((FY)null, "zombie.spawnReinforcements", 0.0, 0.0, 1.0)).setDescription("Spawn Reinforcements Chance");
   private static final UUID BABY_SPEED_BOOST_ID = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");
   private static final FW BABY_SPEED_BOOST;
   private static final Rd<Boolean> IS_CHILD;
   private static final Rd<Integer> VILLAGER_TYPE;
   private static final Rd<Boolean> ARMS_RAISED;
   private final Gk breakDoor = new Gk(this);
   private boolean isBreakDoorsTaskSet;
   private float zombieWidth = -1.0F;
   private float zombieHeight;

   public Lk(bij worldIn) {
      super(worldIn);
      this.setSize(0.6F, 1.95F);
   }

   protected void initEntityAI() {
      this.tasks.addTask(0, new He(this));
      this.tasks.addTask(2, new Hs(this, 1.0, false));
      this.tasks.addTask(5, new GM(this, 1.0));
      this.tasks.addTask(7, new Ho(this, 1.0));
      this.tasks.addTask(8, new Hq(this, ME.class, 8.0F));
      this.tasks.addTask(8, new GH(this));
      this.applyEntityAI();
   }

   protected void applyEntityAI() {
      this.tasks.addTask(6, new GK(this, 1.0, false));
      this.targetTasks.addTask(1, new GB(this, true, new Class[]{Ko.class}));
      this.targetTasks.addTask(2, new GR(this, ME.class, true));
      this.targetTasks.addTask(3, new GR(this, Mq.class, false));
      this.targetTasks.addTask(3, new GR(this, Kj.class, true));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(Ni.FOLLOW_RANGE).setBaseValue(35.0);
      this.getEntityAttribute(Ni.MOVEMENT_SPEED).setBaseValue(0.23000000417232513);
      this.getEntityAttribute(Ni.ATTACK_DAMAGE).setBaseValue(3.0);
      this.getEntityAttribute(Ni.ARMOR).setBaseValue(2.0);
      this.getAttributeMap().registerAttribute(SPAWN_REINFORCEMENTS_CHANCE).setBaseValue(this.rand.nextDouble() * 0.10000000149011612);
   }

   protected void entityInit() {
      super.entityInit();
      this.getDataManager().register(IS_CHILD, false);
      this.getDataManager().register(VILLAGER_TYPE, 0);
      this.getDataManager().register(ARMS_RAISED, false);
   }

   public void setArmsRaised(boolean armsRaised) {
      this.getDataManager().set(ARMS_RAISED, armsRaised);
   }

   public boolean isArmsRaised() {
      return (Boolean)this.getDataManager().get(ARMS_RAISED);
   }

   public boolean isBreakDoorsTaskSet() {
      return this.isBreakDoorsTaskSet;
   }

   public void setBreakDoorsAItask(boolean enabled) {
      if (this.isBreakDoorsTaskSet != enabled) {
         this.isBreakDoorsTaskSet = enabled;
         ((VO)this.getNavigator()).setBreakDoors(enabled);
         if (enabled) {
            this.tasks.addTask(1, this.breakDoor);
         } else {
            this.tasks.removeTask(this.breakDoor);
         }
      }

   }

   public boolean isChild() {
      return (Boolean)this.getDataManager().get(IS_CHILD);
   }

   protected int getExperiencePoints(ME player) {
      if (this.isChild()) {
         this.experienceValue = (int)((float)this.experienceValue * 2.5F);
      }

      return super.getExperiencePoints(player);
   }

   public void setChild(boolean childZombie) {
      this.getDataManager().set(IS_CHILD, childZombie);
      if (this.world != null && !this.world.isRemote) {
         FZ iattributeinstance = this.getEntityAttribute(Ni.MOVEMENT_SPEED);
         iattributeinstance.removeModifier(BABY_SPEED_BOOST);
         if (childZombie) {
            iattributeinstance.applyModifier(BABY_SPEED_BOOST);
         }
      }

      this.setChildSize(childZombie);
   }

   public void notifyDataManagerChange(Rd<?> key) {
      if (IS_CHILD.equals(key)) {
         this.setChildSize(this.isChild());
      }

      super.notifyDataManagerChange(key);
   }

   public void onLivingUpdate() {
      if (this.world.isDaytime() && !this.world.isRemote && !this.isChild() && this.shouldBurnInDay()) {
         float f = this.getBrightness();
         if (f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.world.canSeeSky(new BlockPos(this.posX, this.posY + (double)this.getEyeHeight(), this.posZ))) {
            boolean flag = true;
            Qy itemstack = this.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
            if (!itemstack.isEmpty()) {
               if (itemstack.isItemStackDamageable()) {
                  itemstack.setItemDamage(itemstack.getItemDamage() + this.rand.nextInt(2));
                  if (itemstack.getItemDamage() >= itemstack.getMaxDamage()) {
                     this.renderBrokenItemStack(itemstack);
                     this.setItemStackToSlot(EntityEquipmentSlot.HEAD, Qy.EMPTY);
                  }
               }

               flag = false;
            }

            if (flag) {
               this.setFire(8);
            }
         }
      }

      super.onLivingUpdate();
   }

   protected boolean shouldBurnInDay() {
      return true;
   }

   public boolean attackEntityFrom(DamageSource source, float amount) {
      if (super.attackEntityFrom(source, amount)) {
         Iw entitylivingbase = this.getAttackTarget();
         if (entitylivingbase == null && source.getTrueSource() instanceof Iw) {
            entitylivingbase = (Iw)source.getTrueSource();
         }

         if (entitylivingbase != null && this.world.getDifficulty() == baV.HARD && (double)this.rand.nextFloat() < this.getEntityAttribute(SPAWN_REINFORCEMENTS_CHANCE).getAttributeValue() && this.world.getGameRules().getBoolean("doMobSpawning")) {
            int i = MathHelper.floor(this.posX);
            int j = MathHelper.floor(this.posY);
            int k = MathHelper.floor(this.posZ);
            Lk entityzombie = new Lk(this.world);

            for(int l = 0; l < 50; ++l) {
               int i1 = i + MathHelper.getInt((Random)this.rand, 7, 40) * MathHelper.getInt((Random)this.rand, -1, 1);
               int j1 = j + MathHelper.getInt((Random)this.rand, 7, 40) * MathHelper.getInt((Random)this.rand, -1, 1);
               int k1 = k + MathHelper.getInt((Random)this.rand, 7, 40) * MathHelper.getInt((Random)this.rand, -1, 1);
               if (this.world.getBlockState(new BlockPos(i1, j1 - 1, k1)).isTopSolid() && this.world.getLightFromNeighbors(new BlockPos(i1, j1, k1)) < 10) {
                  entityzombie.setPosition((double)i1, (double)j1, (double)k1);
                  if (!this.world.isAnyPlayerWithinRangeAt((double)i1, (double)j1, (double)k1, 7.0) && this.world.checkNoEntityCollision(entityzombie.getEntityBoundingBox(), entityzombie) && this.world.getCollisionBoxes(entityzombie, entityzombie.getEntityBoundingBox()).isEmpty() && !this.world.containsAnyLiquid(entityzombie.getEntityBoundingBox())) {
                     this.world.spawnEntity(entityzombie);
                     entityzombie.setAttackTarget(entitylivingbase);
                     entityzombie.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(entityzombie)), (ID)null);
                     this.getEntityAttribute(SPAWN_REINFORCEMENTS_CHANCE).applyModifier(new FW("Zombie reinforcement caller charge", -0.05000000074505806, 0));
                     entityzombie.getEntityAttribute(SPAWN_REINFORCEMENTS_CHANCE).applyModifier(new FW("Zombie reinforcement callee charge", -0.05000000074505806, 0));
                     break;
                  }
               }
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public boolean attackEntityAsMob(Ig entityIn) {
      boolean flag = super.attackEntityAsMob(entityIn);
      if (flag) {
         float f = this.world.getDifficultyForLocation(new BlockPos(this)).getAdditionalDifficulty();
         if (this.getHeldItemMainhand().isEmpty() && this.isBurning() && this.rand.nextFloat() < f * 0.3F) {
            entityIn.setFire(2 * (int)f);
         }
      }

      return flag;
   }

   protected SoundEvent getAmbientSound() {
      return NO.ENTITY_ZOMBIE_AMBIENT;
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return NO.ENTITY_ZOMBIE_HURT;
   }

   protected SoundEvent getDeathSound() {
      return NO.ENTITY_ZOMBIE_DEATH;
   }

   protected SoundEvent getStepSound() {
      return NO.ENTITY_ZOMBIE_STEP;
   }

   protected void playStepSound(BlockPos pos, co blockIn) {
      this.playSound(this.getStepSound(), 0.15F, 1.0F);
   }

   public IB getCreatureAttribute() {
      return IB.UNDEAD;
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_ZOMBIE;
   }

   protected void setEquipmentBasedOnDifficulty(baL difficulty) {
      super.setEquipmentBasedOnDifficulty(difficulty);
      if (this.rand.nextFloat() < (this.world.getDifficulty() == baV.HARD ? 0.05F : 0.01F)) {
         int i = this.rand.nextInt(3);
         if (i == 0) {
            this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new Qy(NK.IRON_SWORD));
         } else {
            this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new Qy(NK.IRON_SHOVEL));
         }
      }

   }

   public static void registerFixesZombie(DataFixer fixer) {
      Iu.registerFixesMob(fixer, Lk.class);
   }

   public void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      if (this.isChild()) {
         compound.setBoolean("IsBaby", true);
      }

      compound.setBoolean("CanBreakDoors", this.isBreakDoorsTaskSet());
   }

   public void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      if (compound.getBoolean("IsBaby")) {
         this.setChild(true);
      }

      this.setBreakDoorsAItask(compound.getBoolean("CanBreakDoors"));
   }

   public void onKillEntity(Iw entityLivingIn) {
      super.onKillEntity(entityLivingIn);
      if ((this.world.getDifficulty() == baV.NORMAL || this.world.getDifficulty() == baV.HARD) && entityLivingIn instanceof Mq) {
         if (this.world.getDifficulty() != baV.HARD && this.rand.nextBoolean()) {
            return;
         }

         Mq entityvillager = (Mq)entityLivingIn;
         Ll entityzombievillager = new Ll(this.world);
         entityzombievillager.copyLocationAndAnglesFrom(entityvillager);
         this.world.removeEntity(entityvillager);
         entityzombievillager.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(entityzombievillager)), new Lj(this, false));
         entityzombievillager.setProfession(entityvillager.getProfession());
         entityzombievillager.setChild(entityvillager.isChild());
         entityzombievillager.setNoAI(entityvillager.isAIDisabled());
         if (entityvillager.hasCustomName()) {
            entityzombievillager.setCustomNameTag(entityvillager.getCustomNameTag());
            entityzombievillager.setAlwaysRenderNameTag(entityvillager.getAlwaysRenderNameTag());
         }

         this.world.spawnEntity(entityzombievillager);
         this.world.playEvent((ME)null, 1026, new BlockPos(this), 0);
      }

   }

   public float getEyeHeight() {
      float f = 1.74F;
      if (this.isChild()) {
         f = (float)((double)f - 0.81);
      }

      return f;
   }

   protected boolean canEquipItem(Qy stack) {
      return stack.getItem() == NK.EGG && this.isChild() && this.isRiding() ? false : super.canEquipItem(stack);
   }

   @Nullable
   public ID onInitialSpawn(baL difficulty, @Nullable ID livingdata) {
      ID livingdata = super.onInitialSpawn(difficulty, livingdata);
      float f = difficulty.getClampedAdditionalDifficulty();
      this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * f);
      if (livingdata == null) {
         livingdata = new Lj(this, this.world.rand.nextFloat() < 0.05F);
      }

      if (livingdata instanceof Lj) {
         Lj entityzombie$groupdata = (Lj)livingdata;
         if (entityzombie$groupdata.isChild) {
            this.setChild(true);
            if ((double)this.world.rand.nextFloat() < 0.05) {
               List<LA> list = this.world.getEntitiesWithinAABB(LA.class, this.getEntityBoundingBox().grow(5.0, 3.0, 5.0), EntitySelectors.IS_STANDALONE);
               if (!list.isEmpty()) {
                  LA entitychicken = (LA)list.get(0);
                  entitychicken.setChickenJockey(true);
                  this.startRiding(entitychicken);
               }
            } else if ((double)this.world.rand.nextFloat() < 0.05) {
               LA entitychicken1 = new LA(this.world);
               entitychicken1.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
               entitychicken1.onInitialSpawn(difficulty, (ID)null);
               entitychicken1.setChickenJockey(true);
               this.world.spawnEntity(entitychicken1);
               this.startRiding(entitychicken1);
            }
         }
      }

      this.setBreakDoorsAItask(this.rand.nextFloat() < f * 0.1F);
      this.setEquipmentBasedOnDifficulty(difficulty);
      this.setEnchantmentBasedOnDifficulty(difficulty);
      if (this.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty()) {
         Calendar calendar = this.world.getCurrentDate();
         if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.rand.nextFloat() < 0.25F) {
            this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new Qy(this.rand.nextFloat() < 0.1F ? Nk.LIT_PUMPKIN : Nk.PUMPKIN));
            this.inventoryArmorDropChances[EntityEquipmentSlot.HEAD.getIndex()] = 0.0F;
         }
      }

      this.getEntityAttribute(Ni.KNOCKBACK_RESISTANCE).applyModifier(new FW("Random spawn bonus", this.rand.nextDouble() * 0.05000000074505806, 0));
      double d0 = this.rand.nextDouble() * 1.5 * (double)f;
      if (d0 > 1.0) {
         this.getEntityAttribute(Ni.FOLLOW_RANGE).applyModifier(new FW("Random zombie-spawn bonus", d0, 2));
      }

      if (this.rand.nextFloat() < f * 0.05F) {
         this.getEntityAttribute(SPAWN_REINFORCEMENTS_CHANCE).applyModifier(new FW("Leader zombie bonus", this.rand.nextDouble() * 0.25 + 0.5, 0));
         this.getEntityAttribute(Ni.MAX_HEALTH).applyModifier(new FW("Leader zombie bonus", this.rand.nextDouble() * 3.0 + 1.0, 2));
         this.setBreakDoorsAItask(true);
      }

      return (ID)livingdata;
   }

   public void setChildSize(boolean isChild) {
      this.multiplySize(isChild ? 0.5F : 1.0F);
   }

   protected final void setSize(float width, float height) {
      boolean flag = this.zombieWidth > 0.0F && this.zombieHeight > 0.0F;
      this.zombieWidth = width;
      this.zombieHeight = height;
      if (!flag) {
         this.multiplySize(1.0F);
      }

   }

   protected final void multiplySize(float size) {
      super.setSize(this.zombieWidth * size, this.zombieHeight * size);
   }

   public double getYOffset() {
      return this.isChild() ? 0.0 : -0.45;
   }

   public void onDeath(DamageSource cause) {
      super.onDeath(cause);
      if (cause.getTrueSource() instanceof JB) {
         JB entitycreeper = (JB)cause.getTrueSource();
         if (entitycreeper.getPowered() && entitycreeper.ableToCauseSkullDrop()) {
            entitycreeper.incrementDroppedSkulls();
            Qy itemstack = this.getSkullDrop();
            if (!itemstack.isEmpty()) {
               this.entityDropItem(itemstack, 0.0F);
            }
         }
      }

   }

   protected Qy getSkullDrop() {
      return new Qy(NK.SKULL, 1, 2);
   }

   static {
      BABY_SPEED_BOOST = new FW(BABY_SPEED_BOOST_ID, "Baby speed boost", 0.5, 1);
      IS_CHILD = Rv.createKey(Lk.class, Rt.BOOLEAN);
      VILLAGER_TYPE = Rv.createKey(Lk.class, Rt.VARINT);
      ARMS_RAISED = Rv.createKey(Lk.class, Rt.BOOLEAN);
   }
}
