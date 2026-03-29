package neo;

import com.google.common.collect.Sets;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class LA extends Ly {
   private static final Set<OL> TEMPTATION_ITEMS;
   public float wingRotation;
   public float destPos;
   public float oFlapSpeed;
   public float oFlap;
   public float wingRotDelta = 1.0F;
   public int timeUntilNextEgg;
   public boolean chickenJockey;

   public LA(bij worldIn) {
      super(worldIn);
      this.setSize(0.4F, 0.7F);
      this.timeUntilNextEgg = this.rand.nextInt(6000) + 6000;
      this.setPathPriority(VQ.WATER, 0.0F);
   }

   protected void initEntityAI() {
      this.tasks.addTask(0, new He(this));
      this.tasks.addTask(1, new GX(this, 1.4));
      this.tasks.addTask(2, new GI(this, 1.0));
      this.tasks.addTask(3, new Hj(this, 1.0, false, TEMPTATION_ITEMS));
      this.tasks.addTask(4, new Gz(this, 1.1));
      this.tasks.addTask(5, new Ho(this, 1.0));
      this.tasks.addTask(6, new Hq(this, ME.class, 6.0F));
      this.tasks.addTask(7, new GH(this));
   }

   public float getEyeHeight() {
      return this.height;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(Ni.MAX_HEALTH).setBaseValue(4.0);
      this.getEntityAttribute(Ni.MOVEMENT_SPEED).setBaseValue(0.25);
   }

   public void onLivingUpdate() {
      super.onLivingUpdate();
      this.oFlap = this.wingRotation;
      this.oFlapSpeed = this.destPos;
      this.destPos = (float)((double)this.destPos + (double)(this.onGround ? -1 : 4) * 0.3);
      this.destPos = MathHelper.clamp(this.destPos, 0.0F, 1.0F);
      if (!this.onGround && this.wingRotDelta < 1.0F) {
         this.wingRotDelta = 1.0F;
      }

      this.wingRotDelta = (float)((double)this.wingRotDelta * 0.9);
      if (!this.onGround && this.motionY < 0.0) {
         this.motionY *= 0.6;
      }

      this.wingRotation += this.wingRotDelta * 2.0F;
      if (!this.world.isRemote && !this.isChild() && !this.isChickenJockey() && --this.timeUntilNextEgg <= 0) {
         this.playSound(NO.ENTITY_CHICKEN_EGG, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
         this.dropItem(NK.EGG, 1);
         this.timeUntilNextEgg = this.rand.nextInt(6000) + 6000;
      }

   }

   public void fall(float distance, float damageMultiplier) {
   }

   protected SoundEvent getAmbientSound() {
      return NO.ENTITY_CHICKEN_AMBIENT;
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return NO.ENTITY_CHICKEN_HURT;
   }

   protected SoundEvent getDeathSound() {
      return NO.ENTITY_CHICKEN_DEATH;
   }

   protected void playStepSound(BlockPos pos, co blockIn) {
      this.playSound(NO.ENTITY_CHICKEN_STEP, 0.15F, 1.0F);
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_CHICKEN;
   }

   public LA createChild(Ih ageable) {
      return new LA(this.world);
   }

   public boolean isBreedingItem(Qy stack) {
      return TEMPTATION_ITEMS.contains(stack.getItem());
   }

   protected int getExperiencePoints(ME player) {
      return this.isChickenJockey() ? 10 : super.getExperiencePoints(player);
   }

   public static void registerFixesChicken(DataFixer fixer) {
      Iu.registerFixesMob(fixer, LA.class);
   }

   public void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      this.chickenJockey = compound.getBoolean("IsChickenJockey");
      if (compound.hasKey("EggLayTime")) {
         this.timeUntilNextEgg = compound.getInteger("EggLayTime");
      }

   }

   public void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      compound.setBoolean("IsChickenJockey", this.chickenJockey);
      compound.setInteger("EggLayTime", this.timeUntilNextEgg);
   }

   protected boolean canDespawn() {
      return this.isChickenJockey() && !this.isBeingRidden();
   }

   public void updatePassenger(Ig passenger) {
      super.updatePassenger(passenger);
      float f = MathHelper.sin(this.renderYawOffset * 0.017453292F);
      float f1 = MathHelper.cos(this.renderYawOffset * 0.017453292F);
      float f2 = 0.1F;
      float f3 = 0.0F;
      passenger.setPosition(this.posX + (double)(0.1F * f), this.posY + (double)(this.height * 0.5F) + passenger.getYOffset() + 0.0, this.posZ - (double)(0.1F * f1));
      if (passenger instanceof Iw) {
         ((Iw)passenger).renderYawOffset = this.renderYawOffset;
      }

   }

   public boolean isChickenJockey() {
      return this.chickenJockey;
   }

   public void setChickenJockey(boolean jockey) {
      this.chickenJockey = jockey;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public Ih createChild(Ih var1) {
      return this.createChild(var1);
   }

   static {
      TEMPTATION_ITEMS = Sets.newHashSet(new OL[]{NK.WHEAT_SEEDS, NK.MELON_SEEDS, NK.PUMPKIN_SEEDS, NK.BEETROOT_SEEDS});
   }
}
