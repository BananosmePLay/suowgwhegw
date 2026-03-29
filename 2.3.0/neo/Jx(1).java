package neo;

import java.util.Calendar;
import javax.annotation.Nullable;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public abstract class Jx extends Kl implements IK {
   private static final Rd<Boolean> SWINGING_ARMS;
   private final Gf<Jx> aiArrowAttack = new Gf(this, 1.0, 20, 15.0F);
   private final Gd aiAttackOnCollide = new Gd(this, 1.2, false) {
      public void resetTask() {
         super.resetTask();
         Jx.this.setSwingingArms(false);
      }

      public void startExecuting() {
         super.startExecuting();
         Jx.this.setSwingingArms(true);
      }
   };

   public Jx(bij worldIn) {
      super(worldIn);
      this.setSize(0.6F, 1.99F);
      this.setCombatTask();
   }

   protected void initEntityAI() {
      this.tasks.addTask(1, new He(this));
      this.tasks.addTask(2, new Ha(this));
      this.tasks.addTask(3, new Gt(this, 1.0));
      this.tasks.addTask(3, new Gh(this, Mu.class, 6.0F, 1.0, 1.2));
      this.tasks.addTask(5, new Ho(this, 1.0));
      this.tasks.addTask(6, new Hq(this, ME.class, 8.0F));
      this.tasks.addTask(6, new GH(this));
      this.targetTasks.addTask(1, new GB(this, false, new Class[0]));
      this.targetTasks.addTask(2, new GR(this, ME.class, true));
      this.targetTasks.addTask(3, new GR(this, Kj.class, true));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(Ni.MOVEMENT_SPEED).setBaseValue(0.25);
   }

   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(SWINGING_ARMS, false);
   }

   protected void playStepSound(BlockPos pos, co blockIn) {
      this.playSound(this.getStepSound(), 0.15F, 1.0F);
   }

   abstract SoundEvent getStepSound();

   public IB getCreatureAttribute() {
      return IB.UNDEAD;
   }

   public void onLivingUpdate() {
      if (this.world.isDaytime() && !this.world.isRemote) {
         float f = this.getBrightness();
         BlockPos blockpos = this.getRidingEntity() instanceof IR ? (new BlockPos(this.posX, (double)Math.round(this.posY), this.posZ)).up() : new BlockPos(this.posX, (double)Math.round(this.posY), this.posZ);
         if (f > 0.5F && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.world.canSeeSky(blockpos)) {
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

   public void updateRidden() {
      super.updateRidden();
      if (this.getRidingEntity() instanceof Ik) {
         Ik entitycreature = (Ik)this.getRidingEntity();
         this.renderYawOffset = entitycreature.renderYawOffset;
      }

   }

   protected void setEquipmentBasedOnDifficulty(baL difficulty) {
      super.setEquipmentBasedOnDifficulty(difficulty);
      this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new Qy(NK.BOW));
   }

   @Nullable
   public ID onInitialSpawn(baL difficulty, @Nullable ID livingdata) {
      livingdata = super.onInitialSpawn(difficulty, livingdata);
      this.setEquipmentBasedOnDifficulty(difficulty);
      this.setEnchantmentBasedOnDifficulty(difficulty);
      this.setCombatTask();
      this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * difficulty.getClampedAdditionalDifficulty());
      if (this.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty()) {
         Calendar calendar = this.world.getCurrentDate();
         if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.rand.nextFloat() < 0.25F) {
            this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new Qy(this.rand.nextFloat() < 0.1F ? Nk.LIT_PUMPKIN : Nk.PUMPKIN));
            this.inventoryArmorDropChances[EntityEquipmentSlot.HEAD.getIndex()] = 0.0F;
         }
      }

      return livingdata;
   }

   public void setCombatTask() {
      if (this.world != null && !this.world.isRemote) {
         this.tasks.removeTask(this.aiAttackOnCollide);
         this.tasks.removeTask(this.aiArrowAttack);
         Qy itemstack = this.getHeldItemMainhand();
         if (itemstack.getItem() == NK.BOW) {
            int i = 20;
            if (this.world.getDifficulty() != baV.HARD) {
               i = 40;
            }

            this.aiArrowAttack.setAttackCooldown(i);
            this.tasks.addTask(4, this.aiArrowAttack);
         } else {
            this.tasks.addTask(4, this.aiAttackOnCollide);
         }
      }

   }

   public void attackEntityWithRangedAttack(Iw target, float distanceFactor) {
      MO entityarrow = this.getArrow(distanceFactor);
      double d0 = target.posX - this.posX;
      double d1 = target.getEntityBoundingBox().minY + (double)(target.height / 3.0F) - entityarrow.posY;
      double d2 = target.posZ - this.posZ;
      double d3 = (double)MathHelper.sqrt(d0 * d0 + d2 * d2);
      entityarrow.shoot(d0, d1 + d3 * 0.20000000298023224, d2, 1.6F, (float)(14 - this.world.getDifficulty().getId() * 4));
      this.playSound(NO.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
      this.world.spawnEntity(entityarrow);
   }

   protected MO getArrow(float p_190726_1_) {
      Ne entitytippedarrow = new Ne(this.world, this);
      entitytippedarrow.setEnchantmentEffectsFromEntity(this, p_190726_1_);
      return entitytippedarrow;
   }

   public void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      this.setCombatTask();
   }

   public void setItemStackToSlot(EntityEquipmentSlot slotIn, Qy stack) {
      super.setItemStackToSlot(slotIn, stack);
      if (!this.world.isRemote && slotIn == EntityEquipmentSlot.MAINHAND) {
         this.setCombatTask();
      }

   }

   public float getEyeHeight() {
      return 1.74F;
   }

   public double getYOffset() {
      return -0.6;
   }

   public boolean isSwingingArms() {
      return (Boolean)this.dataManager.get(SWINGING_ARMS);
   }

   public void setSwingingArms(boolean swingingArms) {
      this.dataManager.set(SWINGING_ARMS, swingingArms);
   }

   static {
      SWINGING_ARMS = Rv.createKey(Jx.class, Rt.BOOLEAN);
   }
}
