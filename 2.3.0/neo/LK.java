package neo;

import java.util.Iterator;
import javax.annotation.Nullable;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class LK extends Lu implements IK {
   private static final Rd<Integer> DATA_STRENGTH_ID;
   private static final Rd<Integer> DATA_COLOR_ID;
   private static final Rd<Integer> DATA_VARIANT_ID;
   private boolean didSpit;
   @Nullable
   private LK caravanHead;
   @Nullable
   private LK caravanTail;

   public LK(bij worldIn) {
      super(worldIn);
      this.setSize(0.9F, 1.87F);
   }

   private void setStrength(int strengthIn) {
      this.dataManager.set(DATA_STRENGTH_ID, Math.max(1, Math.min(5, strengthIn)));
   }

   private void setRandomStrength() {
      int i = this.rand.nextFloat() < 0.04F ? 5 : 3;
      this.setStrength(1 + this.rand.nextInt(i));
   }

   public int getStrength() {
      return (Integer)this.dataManager.get(DATA_STRENGTH_ID);
   }

   public void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      compound.setInteger("Variant", this.getVariant());
      compound.setInteger("Strength", this.getStrength());
      if (!this.horseChest.getStackInSlot(1).isEmpty()) {
         compound.setTag("DecorItem", this.horseChest.getStackInSlot(1).writeToNBT(new QQ()));
      }

   }

   public void readEntityFromNBT(QQ compound) {
      this.setStrength(compound.getInteger("Strength"));
      super.readEntityFromNBT(compound);
      this.setVariant(compound.getInteger("Variant"));
      if (compound.hasKey("DecorItem", 10)) {
         this.horseChest.setInventorySlotContents(1, new Qy(compound.getCompoundTag("DecorItem")));
      }

      this.updateHorseSlots();
   }

   protected void initEntityAI() {
      this.tasks.addTask(0, new He(this));
      this.tasks.addTask(1, new Hb(this, 1.2));
      this.tasks.addTask(2, new GE(this, 2.0999999046325684));
      this.tasks.addTask(3, new Ge(this, 1.25, 40, 20.0F));
      this.tasks.addTask(3, new GX(this, 1.2));
      this.tasks.addTask(4, new GI(this, 1.0));
      this.tasks.addTask(5, new Gz(this, 1.0));
      this.tasks.addTask(6, new Ho(this, 0.7));
      this.tasks.addTask(7, new Hq(this, ME.class, 6.0F));
      this.tasks.addTask(8, new GH(this));
      this.targetTasks.addTask(1, new LI(this));
      this.targetTasks.addTask(2, new LH(this));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(Ni.FOLLOW_RANGE).setBaseValue(40.0);
   }

   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(DATA_STRENGTH_ID, 0);
      this.dataManager.register(DATA_COLOR_ID, -1);
      this.dataManager.register(DATA_VARIANT_ID, 0);
   }

   public int getVariant() {
      return MathHelper.clamp((Integer)this.dataManager.get(DATA_VARIANT_ID), 0, 3);
   }

   public void setVariant(int variantIn) {
      this.dataManager.set(DATA_VARIANT_ID, variantIn);
   }

   protected int getInventorySize() {
      return this.hasChest() ? 2 + 3 * this.getInventoryColumns() : super.getInventorySize();
   }

   public void updatePassenger(Ig passenger) {
      if (this.isPassenger(passenger)) {
         float f = MathHelper.cos(this.renderYawOffset * 0.017453292F);
         float f1 = MathHelper.sin(this.renderYawOffset * 0.017453292F);
         float f2 = 0.3F;
         passenger.setPosition(this.posX + (double)(0.3F * f1), this.posY + this.getMountedYOffset() + passenger.getYOffset(), this.posZ - (double)(0.3F * f));
      }

   }

   public double getMountedYOffset() {
      return (double)this.height * 0.67;
   }

   public boolean canBeSteered() {
      return false;
   }

   protected boolean handleEating(ME player, Qy stack) {
      int i = 0;
      int j = 0;
      float f = 0.0F;
      boolean flag = false;
      OL item = stack.getItem();
      if (item == NK.WHEAT) {
         i = 10;
         j = 3;
         f = 2.0F;
      } else if (item == OL.getItemFromBlock(Nk.HAY_BLOCK)) {
         i = 90;
         j = 6;
         f = 10.0F;
         if (this.isTame() && this.getGrowingAge() == 0) {
            flag = true;
            this.setInLove(player);
         }
      }

      if (this.getHealth() < this.getMaxHealth() && f > 0.0F) {
         this.heal(f);
         flag = true;
      }

      if (this.isChild() && i > 0) {
         this.world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5 + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, 0.0, 0.0, 0.0);
         if (!this.world.isRemote) {
            this.addGrowth(i);
         }

         flag = true;
      }

      if (j > 0 && (flag || !this.isTame()) && this.getTemper() < this.getMaxTemper()) {
         flag = true;
         if (!this.world.isRemote) {
            this.increaseTemper(j);
         }
      }

      if (flag && !this.isSilent()) {
         this.world.playSound((ME)null, this.posX, this.posY, this.posZ, NO.ENTITY_LLAMA_EAT, this.getSoundCategory(), 1.0F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
      }

      return flag;
   }

   protected boolean isMovementBlocked() {
      return this.getHealth() <= 0.0F || this.isEatingHaystack();
   }

   @Nullable
   public ID onInitialSpawn(baL difficulty, @Nullable ID livingdata) {
      ID livingdata = super.onInitialSpawn(difficulty, livingdata);
      this.setRandomStrength();
      int i;
      if (livingdata instanceof LJ) {
         i = ((LJ)livingdata).variant;
      } else {
         i = this.rand.nextInt(4);
         livingdata = new LJ(i);
      }

      this.setVariant(i);
      return (ID)livingdata;
   }

   public boolean hasColor() {
      return this.getColor() != null;
   }

   protected SoundEvent getAngrySound() {
      return NO.ENTITY_LLAMA_ANGRY;
   }

   protected SoundEvent getAmbientSound() {
      return NO.ENTITY_LLAMA_AMBIENT;
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return NO.ENTITY_LLAMA_HURT;
   }

   protected SoundEvent getDeathSound() {
      return NO.ENTITY_LLAMA_DEATH;
   }

   protected void playStepSound(BlockPos pos, co blockIn) {
      this.playSound(NO.ENTITY_LLAMA_STEP, 0.15F, 1.0F);
   }

   protected void playChestEquipSound() {
      this.playSound(NO.ENTITY_LLAMA_CHEST, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
   }

   public void makeMad() {
      SoundEvent soundevent = this.getAngrySound();
      if (soundevent != null) {
         this.playSound(soundevent, this.getSoundVolume(), this.getSoundPitch());
      }

   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_LLAMA;
   }

   public int getInventoryColumns() {
      return this.getStrength();
   }

   public boolean wearsArmor() {
      return true;
   }

   public boolean isArmor(Qy stack) {
      return stack.getItem() == OL.getItemFromBlock(Nk.CARPET);
   }

   public boolean canBeSaddled() {
      return false;
   }

   public void onInventoryChanged(IInventory invBasic) {
      Om enumdyecolor = this.getColor();
      super.onInventoryChanged(invBasic);
      Om enumdyecolor1 = this.getColor();
      if (this.ticksExisted > 20 && enumdyecolor1 != null && enumdyecolor1 != enumdyecolor) {
         this.playSound(NO.ENTITY_LLAMA_SWAG, 0.5F, 1.0F);
      }

   }

   protected void updateHorseSlots() {
      if (!this.world.isRemote) {
         super.updateHorseSlots();
         this.setColorByItem(this.horseChest.getStackInSlot(1));
      }

   }

   private void setColor(@Nullable Om color) {
      this.dataManager.set(DATA_COLOR_ID, color == null ? -1 : color.getMetadata());
   }

   private void setColorByItem(Qy stack) {
      if (this.isArmor(stack)) {
         this.setColor(Om.byMetadata(stack.getMetadata()));
      } else {
         this.setColor((Om)null);
      }

   }

   @Nullable
   public Om getColor() {
      int i = (Integer)this.dataManager.get(DATA_COLOR_ID);
      return i == -1 ? null : Om.byMetadata(i);
   }

   public int getMaxTemper() {
      return 30;
   }

   public boolean canMateWith(Ly otherAnimal) {
      return otherAnimal != this && otherAnimal instanceof LK && this.canMate() && ((LK)otherAnimal).canMate();
   }

   public LK createChild(Ih ageable) {
      LK entityllama = new LK(this.world);
      this.setOffspringAttributes(ageable, entityllama);
      LK entityllama1 = (LK)ageable;
      int i = this.rand.nextInt(Math.max(this.getStrength(), entityllama1.getStrength())) + 1;
      if (this.rand.nextFloat() < 0.03F) {
         ++i;
      }

      entityllama.setStrength(i);
      entityllama.setVariant(this.rand.nextBoolean() ? this.getVariant() : entityllama1.getVariant());
      return entityllama;
   }

   private void spit(Iw target) {
      MW entityllamaspit = new MW(this.world, this);
      double d0 = target.posX - this.posX;
      double d1 = target.getEntityBoundingBox().minY + (double)(target.height / 3.0F) - entityllamaspit.posY;
      double d2 = target.posZ - this.posZ;
      float f = MathHelper.sqrt(d0 * d0 + d2 * d2) * 0.2F;
      entityllamaspit.shoot(d0, d1 + (double)f, d2, 1.5F, 10.0F);
      this.world.playSound((ME)null, this.posX, this.posY, this.posZ, NO.ENTITY_LLAMA_SPIT, this.getSoundCategory(), 1.0F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
      this.world.spawnEntity(entityllamaspit);
      this.didSpit = true;
   }

   private void setDidSpit(boolean didSpitIn) {
      this.didSpit = didSpitIn;
   }

   public void fall(float distance, float damageMultiplier) {
      int i = MathHelper.ceil((distance * 0.5F - 3.0F) * damageMultiplier);
      if (i > 0) {
         if (distance >= 6.0F) {
            this.attackEntityFrom(DamageSource.FALL, (float)i);
            if (this.isBeingRidden()) {
               Iterator var4 = this.getRecursivePassengers().iterator();

               while(var4.hasNext()) {
                  Ig entity = (Ig)var4.next();
                  entity.attackEntityFrom(DamageSource.FALL, (float)i);
               }
            }
         }

         in iblockstate = this.world.getBlockState(new BlockPos(this.posX, this.posY - 0.2 - (double)this.prevRotationYaw, this.posZ));
         co block = iblockstate.getBlock();
         if (iblockstate.getMaterial() != hM.AIR && !this.isSilent()) {
            ia soundtype = block.getSoundType();
            this.world.playSound((ME)null, this.posX, this.posY, this.posZ, soundtype.getStepSound(), this.getSoundCategory(), soundtype.getVolume() * 0.5F, soundtype.getPitch() * 0.75F);
         }
      }

   }

   public void leaveCaravan() {
      if (this.caravanHead != null) {
         this.caravanHead.caravanTail = null;
      }

      this.caravanHead = null;
   }

   public void joinCaravan(LK caravanHeadIn) {
      this.caravanHead = caravanHeadIn;
      this.caravanHead.caravanTail = this;
   }

   public boolean hasCaravanTrail() {
      return this.caravanTail != null;
   }

   public boolean inCaravan() {
      return this.caravanHead != null;
   }

   @Nullable
   public LK getCaravanHead() {
      return this.caravanHead;
   }

   protected double followLeashSpeed() {
      return 2.0;
   }

   protected void followMother() {
      if (!this.inCaravan() && this.isChild()) {
         super.followMother();
      }

   }

   public boolean canEatGrass() {
      return false;
   }

   public void attackEntityWithRangedAttack(Iw target, float distanceFactor) {
      this.spit(target);
   }

   public void setSwingingArms(boolean swingingArms) {
   }

   // $FF: synthetic method
   // $FF: bridge method
   public Ih createChild(Ih var1) {
      return this.createChild(var1);
   }

   // $FF: synthetic method
   static boolean access$100(LK x0) {
      return x0.didSpit;
   }

   // $FF: synthetic method
   static void access$200(LK x0, boolean x1) {
      x0.setDidSpit(x1);
   }

   static {
      DATA_STRENGTH_ID = Rv.createKey(LK.class, Rt.VARINT);
      DATA_COLOR_ID = Rv.createKey(LK.class, Rt.VARINT);
      DATA_VARIANT_ID = Rv.createKey(LK.class, Rt.VARINT);
   }
}
