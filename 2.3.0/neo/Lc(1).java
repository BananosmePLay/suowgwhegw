package neo;

import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;

public class Lc extends Kl {
   protected static final Rd<Byte> VEX_FLAGS;
   private Iu owner;
   @Nullable
   private BlockPos boundOrigin;
   private boolean limitedLifespan;
   private int limitedLifeTicks;

   public Lc(bij worldIn) {
      super(worldIn);
      this.isImmuneToFire = true;
      this.moveHelper = new La(this, this);
      this.setSize(0.4F, 0.8F);
      this.experienceValue = 3;
   }

   public void move(Lq type, double x, double y, double z) {
      super.move(type, x, y, z);
      this.doBlockCollisions();
   }

   public void onUpdate() {
      this.noClip = true;
      super.onUpdate();
      this.noClip = false;
      this.setNoGravity(true);
      if (this.limitedLifespan && --this.limitedLifeTicks <= 0) {
         this.limitedLifeTicks = 20;
         this.attackEntityFrom(DamageSource.STARVE, 1.0F);
      }

   }

   protected void initEntityAI() {
      super.initEntityAI();
      this.tasks.addTask(0, new He(this));
      this.tasks.addTask(4, new KY(this));
      this.tasks.addTask(8, new Lb(this));
      this.tasks.addTask(9, new Hq(this, ME.class, 3.0F, 1.0F));
      this.tasks.addTask(10, new Hq(this, Iu.class, 8.0F));
      this.targetTasks.addTask(1, new GB(this, true, new Class[]{Lc.class}));
      this.targetTasks.addTask(2, new KZ(this, this));
      this.targetTasks.addTask(3, new GR(this, ME.class, true));
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(Ni.MAX_HEALTH).setBaseValue(14.0);
      this.getEntityAttribute(Ni.ATTACK_DAMAGE).setBaseValue(4.0);
   }

   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(VEX_FLAGS, (byte)0);
   }

   public static void registerFixesVex(DataFixer fixer) {
      Iu.registerFixesMob(fixer, Lc.class);
   }

   public void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      if (compound.hasKey("BoundX")) {
         this.boundOrigin = new BlockPos(compound.getInteger("BoundX"), compound.getInteger("BoundY"), compound.getInteger("BoundZ"));
      }

      if (compound.hasKey("LifeTicks")) {
         this.setLimitedLife(compound.getInteger("LifeTicks"));
      }

   }

   public void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      if (this.boundOrigin != null) {
         compound.setInteger("BoundX", this.boundOrigin.getX());
         compound.setInteger("BoundY", this.boundOrigin.getY());
         compound.setInteger("BoundZ", this.boundOrigin.getZ());
      }

      if (this.limitedLifespan) {
         compound.setInteger("LifeTicks", this.limitedLifeTicks);
      }

   }

   public Iu getOwner() {
      return this.owner;
   }

   @Nullable
   public BlockPos getBoundOrigin() {
      return this.boundOrigin;
   }

   public void setBoundOrigin(@Nullable BlockPos boundOriginIn) {
      this.boundOrigin = boundOriginIn;
   }

   private boolean getVexFlag(int mask) {
      int i = (Byte)this.dataManager.get(VEX_FLAGS);
      return (i & mask) != 0;
   }

   private void setVexFlag(int mask, boolean value) {
      int i = (Byte)this.dataManager.get(VEX_FLAGS);
      if (value) {
         i |= mask;
      } else {
         i &= ~mask;
      }

      this.dataManager.set(VEX_FLAGS, (byte)(i & 255));
   }

   public boolean isCharging() {
      return this.getVexFlag(1);
   }

   public void setCharging(boolean charging) {
      this.setVexFlag(1, charging);
   }

   public void setOwner(Iu ownerIn) {
      this.owner = ownerIn;
   }

   public void setLimitedLife(int limitedLifeTicksIn) {
      this.limitedLifespan = true;
      this.limitedLifeTicks = limitedLifeTicksIn;
   }

   protected SoundEvent getAmbientSound() {
      return NO.ENTITY_VEX_AMBIENT;
   }

   protected SoundEvent getDeathSound() {
      return NO.ENTITY_VEX_DEATH;
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return NO.ENTITY_VEX_HURT;
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_VEX;
   }

   public int getBrightnessForRender() {
      return 15728880;
   }

   public float getBrightness() {
      return 1.0F;
   }

   @Nullable
   public ID onInitialSpawn(baL difficulty, @Nullable ID livingdata) {
      this.setEquipmentBasedOnDifficulty(difficulty);
      this.setEnchantmentBasedOnDifficulty(difficulty);
      return super.onInitialSpawn(difficulty, livingdata);
   }

   protected void setEquipmentBasedOnDifficulty(baL difficulty) {
      this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new Qy(NK.IRON_SWORD));
      this.setDropChance(EntityEquipmentSlot.MAINHAND, 0.0F);
   }

   // $FF: synthetic method
   static Random access$000(Lc x0) {
      return x0.rand;
   }

   // $FF: synthetic method
   static Hx access$100(Lc x0) {
      return x0.moveHelper;
   }

   // $FF: synthetic method
   static Hx access$200(Lc x0) {
      return x0.moveHelper;
   }

   // $FF: synthetic method
   static Iu access$300(Lc x0) {
      return x0.owner;
   }

   // $FF: synthetic method
   static Random access$400(Lc x0) {
      return x0.rand;
   }

   // $FF: synthetic method
   static Random access$500(Lc x0) {
      return x0.rand;
   }

   // $FF: synthetic method
   static Random access$600(Lc x0) {
      return x0.rand;
   }

   // $FF: synthetic method
   static Random access$700(Lc x0) {
      return x0.rand;
   }

   // $FF: synthetic method
   static Hx access$800(Lc x0) {
      return x0.moveHelper;
   }

   static {
      VEX_FLAGS = Rv.createKey(Lc.class, Rt.BYTE);
   }
}
