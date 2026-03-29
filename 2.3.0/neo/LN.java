package neo;

import com.google.common.base.Predicate;
import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;

public class LN extends Mg {
   private static final Rd<Integer> OCELOT_VARIANT;
   private Gh<ME> avoidEntity;
   private Hj aiTempt;

   public LN(bij worldIn) {
      super(worldIn);
      this.setSize(0.6F, 0.7F);
   }

   protected void initEntityAI() {
      this.aiSit = new Hc(this);
      this.aiTempt = new Hj(this, 0.6, NK.FISH, true);
      this.tasks.addTask(1, new He(this));
      this.tasks.addTask(2, this.aiSit);
      this.tasks.addTask(3, this.aiTempt);
      this.tasks.addTask(5, new Gx(this, 1.0, 10.0F, 5.0F));
      this.tasks.addTask(6, new GT(this, 0.8));
      this.tasks.addTask(7, new GD(this, 0.3F));
      this.tasks.addTask(8, new GS(this));
      this.tasks.addTask(9, new GI(this, 0.8));
      this.tasks.addTask(10, new Ho(this, 0.8, 1.0000001E-5F));
      this.tasks.addTask(11, new Hq(this, ME.class, 10.0F));
      this.targetTasks.addTask(1, new Hg(this, LA.class, false, (Predicate)null));
   }

   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(OCELOT_VARIANT, 0);
   }

   public void updateAITasks() {
      if (this.getMoveHelper().isUpdating()) {
         double d0 = this.getMoveHelper().getSpeed();
         if (d0 == 0.6) {
            this.setSneaking(true);
            this.setSprinting(false);
         } else if (d0 == 1.33) {
            this.setSneaking(false);
            this.setSprinting(true);
         } else {
            this.setSneaking(false);
            this.setSprinting(false);
         }
      } else {
         this.setSneaking(false);
         this.setSprinting(false);
      }

   }

   protected boolean canDespawn() {
      return !this.isTamed() && this.ticksExisted > 2400;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(Ni.MAX_HEALTH).setBaseValue(10.0);
      this.getEntityAttribute(Ni.MOVEMENT_SPEED).setBaseValue(0.30000001192092896);
   }

   public void fall(float distance, float damageMultiplier) {
   }

   public static void registerFixesOcelot(DataFixer fixer) {
      Iu.registerFixesMob(fixer, LN.class);
   }

   public void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      compound.setInteger("CatType", this.getTameSkin());
   }

   public void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      this.setTameSkin(compound.getInteger("CatType"));
   }

   @Nullable
   protected SoundEvent getAmbientSound() {
      if (this.isTamed()) {
         if (this.isInLove()) {
            return NO.ENTITY_CAT_PURR;
         } else {
            return this.rand.nextInt(4) == 0 ? NO.ENTITY_CAT_PURREOW : NO.ENTITY_CAT_AMBIENT;
         }
      } else {
         return null;
      }
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return NO.ENTITY_CAT_HURT;
   }

   protected SoundEvent getDeathSound() {
      return NO.ENTITY_CAT_DEATH;
   }

   protected float getSoundVolume() {
      return 0.4F;
   }

   public boolean attackEntityAsMob(Ig entityIn) {
      return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0F);
   }

   public boolean attackEntityFrom(DamageSource source, float amount) {
      if (this.isEntityInvulnerable(source)) {
         return false;
      } else {
         if (this.aiSit != null) {
            this.aiSit.setSitting(false);
         }

         return super.attackEntityFrom(source, amount);
      }
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_OCELOT;
   }

   public boolean processInteract(ME player, EnumHand hand) {
      Qy itemstack = player.getHeldItem(hand);
      if (this.isTamed()) {
         if (this.isOwner(player) && !this.world.isRemote && !this.isBreedingItem(itemstack)) {
            this.aiSit.setSitting(!this.isSitting());
         }
      } else if ((this.aiTempt == null || this.aiTempt.isRunning()) && itemstack.getItem() == NK.FISH && player.getDistanceSq(this) < 9.0) {
         if (!player.capabilities.isCreativeMode) {
            itemstack.shrink(1);
         }

         if (!this.world.isRemote) {
            if (this.rand.nextInt(3) == 0) {
               this.setTamedBy(player);
               this.setTameSkin(1 + this.world.rand.nextInt(3));
               this.playTameEffect(true);
               this.aiSit.setSitting(true);
               this.world.setEntityState(this, (byte)7);
            } else {
               this.playTameEffect(false);
               this.world.setEntityState(this, (byte)6);
            }
         }

         return true;
      }

      return super.processInteract(player, hand);
   }

   public LN createChild(Ih ageable) {
      LN entityocelot = new LN(this.world);
      if (this.isTamed()) {
         entityocelot.setOwnerId(this.getOwnerId());
         entityocelot.setTamed(true);
         entityocelot.setTameSkin(this.getTameSkin());
      }

      return entityocelot;
   }

   public boolean isBreedingItem(Qy stack) {
      return stack.getItem() == NK.FISH;
   }

   public boolean canMateWith(Ly otherAnimal) {
      if (otherAnimal == this) {
         return false;
      } else if (!this.isTamed()) {
         return false;
      } else if (!(otherAnimal instanceof LN)) {
         return false;
      } else {
         LN entityocelot = (LN)otherAnimal;
         if (!entityocelot.isTamed()) {
            return false;
         } else {
            return this.isInLove() && entityocelot.isInLove();
         }
      }
   }

   public int getTameSkin() {
      return (Integer)this.dataManager.get(OCELOT_VARIANT);
   }

   public void setTameSkin(int skinId) {
      this.dataManager.set(OCELOT_VARIANT, skinId);
   }

   public boolean getCanSpawnHere() {
      return this.world.rand.nextInt(3) != 0;
   }

   public boolean isNotColliding() {
      if (this.world.checkNoEntityCollision(this.getEntityBoundingBox(), this) && this.world.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty() && !this.world.containsAnyLiquid(this.getEntityBoundingBox())) {
         BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);
         if (blockpos.getY() < this.world.getSeaLevel()) {
            return false;
         }

         in iblockstate = this.world.getBlockState(blockpos.down());
         co block = iblockstate.getBlock();
         if (block == Nk.GRASS || iblockstate.getMaterial() == hM.LEAVES) {
            return true;
         }
      }

      return false;
   }

   public String getName() {
      if (this.hasCustomName()) {
         return this.getCustomNameTag();
      } else {
         return this.isTamed() ? I18n.translateToLocal("entity.Cat.name") : super.getName();
      }
   }

   protected void setupTamedAI() {
      if (this.avoidEntity == null) {
         this.avoidEntity = new Gh(this, ME.class, 16.0F, 0.8, 1.33);
      }

      this.tasks.removeTask(this.avoidEntity);
      if (!this.isTamed()) {
         this.tasks.addTask(4, this.avoidEntity);
      }

   }

   @Nullable
   public ID onInitialSpawn(baL difficulty, @Nullable ID livingdata) {
      livingdata = super.onInitialSpawn(difficulty, livingdata);
      if (this.getTameSkin() == 0 && this.world.rand.nextInt(7) == 0) {
         for(int i = 0; i < 2; ++i) {
            LN entityocelot = new LN(this.world);
            entityocelot.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
            entityocelot.setGrowingAge(-24000);
            this.world.spawnEntity(entityocelot);
         }
      }

      return livingdata;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public Ih createChild(Ih var1) {
      return this.createChild(var1);
   }

   static {
      OCELOT_VARIANT = Rv.createKey(LN.class, Rt.VARINT);
   }
}
