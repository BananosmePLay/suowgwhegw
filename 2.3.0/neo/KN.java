package neo;

import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class KN extends Iu implements Lo {
   private static final Rd<Integer> SLIME_SIZE;
   public float squishAmount;
   public float squishFactor;
   public float prevSquishFactor;
   private boolean wasOnGround;

   public KN(bij worldIn) {
      super(worldIn);
      this.moveHelper = new KM(this);
   }

   protected void initEntityAI() {
      this.tasks.addTask(1, new KK(this));
      this.tasks.addTask(2, new KI(this));
      this.tasks.addTask(3, new KJ(this));
      this.tasks.addTask(5, new KL(this));
      this.targetTasks.addTask(1, new Gs(this));
      this.targetTasks.addTask(3, new Gq(this, Kj.class));
   }

   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(SLIME_SIZE, 1);
   }

   protected void setSlimeSize(int size, boolean resetHealth) {
      this.dataManager.set(SLIME_SIZE, size);
      this.setSize(0.51000005F * (float)size, 0.51000005F * (float)size);
      this.setPosition(this.posX, this.posY, this.posZ);
      this.getEntityAttribute(Ni.MAX_HEALTH).setBaseValue((double)(size * size));
      this.getEntityAttribute(Ni.MOVEMENT_SPEED).setBaseValue((double)(0.2F + 0.1F * (float)size));
      if (resetHealth) {
         this.setHealth(this.getMaxHealth());
      }

      this.experienceValue = size;
   }

   public int getSlimeSize() {
      return (Integer)this.dataManager.get(SLIME_SIZE);
   }

   public static void registerFixesSlime(DataFixer fixer) {
      Iu.registerFixesMob(fixer, KN.class);
   }

   public void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      compound.setInteger("Size", this.getSlimeSize() - 1);
      compound.setBoolean("wasOnGround", this.wasOnGround);
   }

   public void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      int i = compound.getInteger("Size");
      if (i < 0) {
         i = 0;
      }

      this.setSlimeSize(i + 1, false);
      this.wasOnGround = compound.getBoolean("wasOnGround");
   }

   public boolean isSmallSlime() {
      return this.getSlimeSize() <= 1;
   }

   protected EnumParticleTypes getParticleType() {
      return EnumParticleTypes.SLIME;
   }

   public void onUpdate() {
      if (!this.world.isRemote && this.world.getDifficulty() == baV.PEACEFUL && this.getSlimeSize() > 0) {
         this.isDead = true;
      }

      this.squishFactor += (this.squishAmount - this.squishFactor) * 0.5F;
      this.prevSquishFactor = this.squishFactor;
      super.onUpdate();
      if (this.onGround && !this.wasOnGround) {
         int i = this.getSlimeSize();

         for(int j = 0; j < i * 8; ++j) {
            float f = this.rand.nextFloat() * 6.2831855F;
            float f1 = this.rand.nextFloat() * 0.5F + 0.5F;
            float f2 = MathHelper.sin(f) * (float)i * 0.5F * f1;
            float f3 = MathHelper.cos(f) * (float)i * 0.5F * f1;
            bij world = this.world;
            EnumParticleTypes enumparticletypes = this.getParticleType();
            double d0 = this.posX + (double)f2;
            double d1 = this.posZ + (double)f3;
            world.spawnParticle(enumparticletypes, d0, this.getEntityBoundingBox().minY, d1, 0.0, 0.0, 0.0);
         }

         this.playSound(this.getSquishSound(), this.getSoundVolume(), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
         this.squishAmount = -0.5F;
      } else if (!this.onGround && this.wasOnGround) {
         this.squishAmount = 1.0F;
      }

      this.wasOnGround = this.onGround;
      this.alterSquishAmount();
   }

   protected void alterSquishAmount() {
      this.squishAmount *= 0.6F;
   }

   protected int getJumpDelay() {
      return this.rand.nextInt(20) + 10;
   }

   protected KN createInstance() {
      return new KN(this.world);
   }

   public void notifyDataManagerChange(Rd<?> key) {
      if (SLIME_SIZE.equals(key)) {
         int i = this.getSlimeSize();
         this.setSize(0.51000005F * (float)i, 0.51000005F * (float)i);
         this.rotationYaw = this.rotationYawHead;
         this.renderYawOffset = this.rotationYawHead;
         if (this.isInWater() && this.rand.nextInt(20) == 0) {
            this.doWaterSplashEffect();
         }
      }

      super.notifyDataManagerChange(key);
   }

   public void setDead() {
      int i = this.getSlimeSize();
      if (!this.world.isRemote && i > 1 && this.getHealth() <= 0.0F) {
         int j = 2 + this.rand.nextInt(3);

         for(int k = 0; k < j; ++k) {
            float f = ((float)(k % 2) - 0.5F) * (float)i / 4.0F;
            float f1 = ((float)(k / 2) - 0.5F) * (float)i / 4.0F;
            KN entityslime = this.createInstance();
            if (this.hasCustomName()) {
               entityslime.setCustomNameTag(this.getCustomNameTag());
            }

            if (this.isNoDespawnRequired()) {
               entityslime.enablePersistence();
            }

            entityslime.setSlimeSize(i / 2, true);
            entityslime.setLocationAndAngles(this.posX + (double)f, this.posY + 0.5, this.posZ + (double)f1, this.rand.nextFloat() * 360.0F, 0.0F);
            this.world.spawnEntity(entityslime);
         }
      }

      super.setDead();
   }

   public void applyEntityCollision(Ig entityIn) {
      super.applyEntityCollision(entityIn);
      if (entityIn instanceof Kj && this.canDamagePlayer()) {
         this.dealDamage((Iw)entityIn);
      }

   }

   public void onCollideWithPlayer(ME entityIn) {
      if (this.canDamagePlayer()) {
         this.dealDamage(entityIn);
      }

   }

   protected void dealDamage(Iw entityIn) {
      int i = this.getSlimeSize();
      if (this.canEntityBeSeen(entityIn) && this.getDistanceSq(entityIn) < 0.6 * (double)i * 0.6 * (double)i && entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), (float)this.getAttackStrength())) {
         this.playSound(NO.ENTITY_SLIME_ATTACK, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
         this.applyEnchantments(this, entityIn);
      }

   }

   public float getEyeHeight() {
      return 0.625F * this.height;
   }

   protected boolean canDamagePlayer() {
      return !this.isSmallSlime();
   }

   protected int getAttackStrength() {
      return this.getSlimeSize();
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return this.isSmallSlime() ? NO.ENTITY_SMALL_SLIME_HURT : NO.ENTITY_SLIME_HURT;
   }

   protected SoundEvent getDeathSound() {
      return this.isSmallSlime() ? NO.ENTITY_SMALL_SLIME_DEATH : NO.ENTITY_SLIME_DEATH;
   }

   protected SoundEvent getSquishSound() {
      return this.isSmallSlime() ? NO.ENTITY_SMALL_SLIME_SQUISH : NO.ENTITY_SLIME_SQUISH;
   }

   protected OL getDropItem() {
      return this.getSlimeSize() == 1 ? NK.SLIME_BALL : null;
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return this.getSlimeSize() == 1 ? bhq.ENTITIES_SLIME : bhq.EMPTY;
   }

   public boolean getCanSpawnHere() {
      BlockPos blockpos = new BlockPos(MathHelper.floor(this.posX), 0, MathHelper.floor(this.posZ));
      bam chunk = this.world.getChunk(blockpos);
      if (this.world.getWorldInfo().getTerrainType() == bix.FLAT && this.rand.nextInt(4) != 1) {
         return false;
      } else {
         if (this.world.getDifficulty() != baV.PEACEFUL) {
            Zi biome = this.world.getBiome(blockpos);
            if (biome == Nj.SWAMPLAND && this.posY > 50.0 && this.posY < 70.0 && this.rand.nextFloat() < 0.5F && this.rand.nextFloat() < this.world.getCurrentMoonPhaseFactor() && this.world.getLightFromNeighbors(new BlockPos(this)) <= this.rand.nextInt(8)) {
               return super.getCanSpawnHere();
            }

            if (this.rand.nextInt(10) == 0 && chunk.getRandomWithSeed(987234911L).nextInt(10) == 0 && this.posY < 40.0) {
               return super.getCanSpawnHere();
            }
         }

         return false;
      }
   }

   protected float getSoundVolume() {
      return 0.4F * (float)this.getSlimeSize();
   }

   public int getVerticalFaceSpeed() {
      return 0;
   }

   protected boolean makesSoundOnJump() {
      return this.getSlimeSize() > 0;
   }

   protected void jump() {
      this.motionY = 0.41999998688697815;
      this.isAirBorne = true;
   }

   @Nullable
   public ID onInitialSpawn(baL difficulty, @Nullable ID livingdata) {
      int i = this.rand.nextInt(3);
      if (i < 2 && this.rand.nextFloat() < 0.5F * difficulty.getClampedAdditionalDifficulty()) {
         ++i;
      }

      int j = 1 << i;
      this.setSlimeSize(j, true);
      return super.onInitialSpawn(difficulty, livingdata);
   }

   protected SoundEvent getJumpSound() {
      return this.isSmallSlime() ? NO.ENTITY_SMALL_SLIME_JUMP : NO.ENTITY_SLIME_JUMP;
   }

   static {
      SLIME_SIZE = Rv.createKey(KN.class, Rt.VARINT);
   }
}
