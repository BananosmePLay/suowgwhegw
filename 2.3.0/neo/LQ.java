package neo;

import com.google.common.collect.Sets;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class LQ extends Ly {
   private static final Rd<Boolean> SADDLED;
   private static final Rd<Integer> BOOST_TIME;
   private static final Set<OL> TEMPTATION_ITEMS;
   private boolean boosting;
   private int boostTime;
   private int totalBoostTime;

   public LQ(bij worldIn) {
      super(worldIn);
      this.setSize(0.9F, 0.9F);
   }

   protected void initEntityAI() {
      this.tasks.addTask(0, new He(this));
      this.tasks.addTask(1, new GX(this, 1.25));
      this.tasks.addTask(3, new GI(this, 1.0));
      this.tasks.addTask(4, new Hj(this, 1.2, NK.CARROT_ON_A_STICK, false));
      this.tasks.addTask(4, new Hj(this, 1.2, false, TEMPTATION_ITEMS));
      this.tasks.addTask(5, new Gz(this, 1.1));
      this.tasks.addTask(6, new Ho(this, 1.0));
      this.tasks.addTask(7, new Hq(this, ME.class, 6.0F));
      this.tasks.addTask(8, new GH(this));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(Ni.MAX_HEALTH).setBaseValue(10.0);
      this.getEntityAttribute(Ni.MOVEMENT_SPEED).setBaseValue(0.25);
   }

   @Nullable
   public Ig getControllingPassenger() {
      return this.getPassengers().isEmpty() ? null : (Ig)this.getPassengers().get(0);
   }

   public boolean canBeSteered() {
      Ig entity = this.getControllingPassenger();
      if (!(entity instanceof ME)) {
         return false;
      } else {
         ME entityplayer = (ME)entity;
         return entityplayer.getHeldItemMainhand().getItem() == NK.CARROT_ON_A_STICK || entityplayer.getHeldItemOffhand().getItem() == NK.CARROT_ON_A_STICK;
      }
   }

   public void notifyDataManagerChange(Rd<?> key) {
      if (BOOST_TIME.equals(key) && this.world.isRemote) {
         this.boosting = true;
         this.boostTime = 0;
         this.totalBoostTime = (Integer)this.dataManager.get(BOOST_TIME);
      }

      super.notifyDataManagerChange(key);
   }

   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(SADDLED, false);
      this.dataManager.register(BOOST_TIME, 0);
   }

   public static void registerFixesPig(DataFixer fixer) {
      Iu.registerFixesMob(fixer, LQ.class);
   }

   public void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      compound.setBoolean("Saddle", this.getSaddled());
   }

   public void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      this.setSaddled(compound.getBoolean("Saddle"));
   }

   protected SoundEvent getAmbientSound() {
      return NO.ENTITY_PIG_AMBIENT;
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return NO.ENTITY_PIG_HURT;
   }

   protected SoundEvent getDeathSound() {
      return NO.ENTITY_PIG_DEATH;
   }

   protected void playStepSound(BlockPos pos, co blockIn) {
      this.playSound(NO.ENTITY_PIG_STEP, 0.15F, 1.0F);
   }

   public boolean processInteract(ME player, EnumHand hand) {
      if (!super.processInteract(player, hand)) {
         Qy itemstack = player.getHeldItem(hand);
         if (itemstack.getItem() == NK.NAME_TAG) {
            itemstack.interactWithEntity(player, this, hand);
            return true;
         } else if (this.getSaddled() && !this.isBeingRidden()) {
            if (!this.world.isRemote) {
               player.startRiding(this);
            }

            return true;
         } else if (itemstack.getItem() == NK.SADDLE) {
            itemstack.interactWithEntity(player, this, hand);
            return true;
         } else {
            return false;
         }
      } else {
         return true;
      }
   }

   public void onDeath(DamageSource cause) {
      super.onDeath(cause);
      if (!this.world.isRemote && this.getSaddled()) {
         this.dropItem(NK.SADDLE, 1);
      }

   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_PIG;
   }

   public boolean getSaddled() {
      return (Boolean)this.dataManager.get(SADDLED);
   }

   public void setSaddled(boolean saddled) {
      if (saddled) {
         this.dataManager.set(SADDLED, true);
      } else {
         this.dataManager.set(SADDLED, false);
      }

   }

   public void onStruckByLightning(HX lightningBolt) {
      if (!this.world.isRemote && !this.isDead) {
         Ko entitypigzombie = new Ko(this.world);
         entitypigzombie.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new Qy(NK.GOLDEN_SWORD));
         entitypigzombie.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
         entitypigzombie.setNoAI(this.isAIDisabled());
         if (this.hasCustomName()) {
            entitypigzombie.setCustomNameTag(this.getCustomNameTag());
            entitypigzombie.setAlwaysRenderNameTag(this.getAlwaysRenderNameTag());
         }

         this.world.spawnEntity(entitypigzombie);
         this.setDead();
      }

   }

   public void travel(float strafe, float vertical, float forward) {
      Ig entity = this.getPassengers().isEmpty() ? null : (Ig)this.getPassengers().get(0);
      if (this.isBeingRidden() && this.canBeSteered()) {
         this.rotationYaw = entity.rotationYaw;
         this.prevRotationYaw = this.rotationYaw;
         this.rotationPitch = entity.rotationPitch * 0.5F;
         this.setRotation(this.rotationYaw, this.rotationPitch);
         this.renderYawOffset = this.rotationYaw;
         this.rotationYawHead = this.rotationYaw;
         this.stepHeight = 1.0F;
         this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;
         if (this.boosting && this.boostTime++ > this.totalBoostTime) {
            this.boosting = false;
         }

         if (this.canPassengerSteer()) {
            float f = (float)this.getEntityAttribute(Ni.MOVEMENT_SPEED).getAttributeValue() * 0.225F;
            if (this.boosting) {
               f += f * 1.15F * MathHelper.sin((float)this.boostTime / (float)this.totalBoostTime * 3.1415927F);
            }

            this.setAIMoveSpeed(f);
            super.travel(0.0F, 0.0F, 1.0F);
         } else {
            this.motionX = 0.0;
            this.motionY = 0.0;
            this.motionZ = 0.0;
         }

         this.prevLimbSwingAmount = this.limbSwingAmount;
         double d1 = this.posX - this.prevPosX;
         double d0 = this.posZ - this.prevPosZ;
         float f1 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;
         if (f1 > 1.0F) {
            f1 = 1.0F;
         }

         this.limbSwingAmount += (f1 - this.limbSwingAmount) * 0.4F;
         this.limbSwing += this.limbSwingAmount;
      } else {
         this.stepHeight = 0.5F;
         this.jumpMovementFactor = 0.02F;
         super.travel(strafe, vertical, forward);
      }

   }

   public boolean boost() {
      if (this.boosting) {
         return false;
      } else {
         this.boosting = true;
         this.boostTime = 0;
         this.totalBoostTime = this.getRNG().nextInt(841) + 140;
         this.getDataManager().set(BOOST_TIME, this.totalBoostTime);
         return true;
      }
   }

   public LQ createChild(Ih ageable) {
      return new LQ(this.world);
   }

   public boolean isBreedingItem(Qy stack) {
      return TEMPTATION_ITEMS.contains(stack.getItem());
   }

   // $FF: synthetic method
   // $FF: bridge method
   public Ih createChild(Ih var1) {
      return this.createChild(var1);
   }

   static {
      SADDLED = Rv.createKey(LQ.class, Rt.BOOLEAN);
      BOOST_TIME = Rv.createKey(LQ.class, Rt.VARINT);
      TEMPTATION_ITEMS = Sets.newHashSet(new OL[]{NK.CARROT, NK.POTATO, NK.BEETROOT});
   }
}
