package neo;

import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;

public class KW extends Kl {
   private static final Rd<Byte> CLIMBING;

   public KW(bij worldIn) {
      super(worldIn);
      this.setSize(1.4F, 0.9F);
   }

   public static void registerFixesSpider(DataFixer fixer) {
      Iu.registerFixesMob(fixer, KW.class);
   }

   protected void initEntityAI() {
      this.tasks.addTask(1, new He(this));
      this.tasks.addTask(3, new GD(this, 0.4F));
      this.tasks.addTask(4, new KT(this));
      this.tasks.addTask(5, new Ho(this, 0.8));
      this.tasks.addTask(6, new Hq(this, ME.class, 8.0F));
      this.tasks.addTask(6, new GH(this));
      this.targetTasks.addTask(1, new GB(this, false, new Class[0]));
      this.targetTasks.addTask(2, new KU(this, ME.class));
      this.targetTasks.addTask(3, new KU(this, Kj.class));
   }

   public double getMountedYOffset() {
      return (double)(this.height * 0.5F);
   }

   protected VL createNavigator(bij worldIn) {
      return new VM(this, worldIn);
   }

   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(CLIMBING, (byte)0);
   }

   public void onUpdate() {
      super.onUpdate();
      if (!this.world.isRemote) {
         this.setBesideClimbableBlock(this.collidedHorizontally);
      }

   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(Ni.MAX_HEALTH).setBaseValue(16.0);
      this.getEntityAttribute(Ni.MOVEMENT_SPEED).setBaseValue(0.30000001192092896);
   }

   protected SoundEvent getAmbientSound() {
      return NO.ENTITY_SPIDER_AMBIENT;
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return NO.ENTITY_SPIDER_HURT;
   }

   protected SoundEvent getDeathSound() {
      return NO.ENTITY_SPIDER_DEATH;
   }

   protected void playStepSound(BlockPos pos, co blockIn) {
      this.playSound(NO.ENTITY_SPIDER_STEP, 0.15F, 1.0F);
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_SPIDER;
   }

   public boolean isOnLadder() {
      return this.isBesideClimbableBlock();
   }

   public void setInWeb() {
   }

   public IB getCreatureAttribute() {
      return IB.ARTHROPOD;
   }

   public boolean isPotionApplicable(VZ potioneffectIn) {
      return potioneffectIn.getPotion() == NL.POISON ? false : super.isPotionApplicable(potioneffectIn);
   }

   public boolean isBesideClimbableBlock() {
      return ((Byte)this.dataManager.get(CLIMBING) & 1) != 0;
   }

   public void setBesideClimbableBlock(boolean climbing) {
      byte b0 = (Byte)this.dataManager.get(CLIMBING);
      if (climbing) {
         b0 = (byte)(b0 | 1);
      } else {
         b0 &= -2;
      }

      this.dataManager.set(CLIMBING, b0);
   }

   @Nullable
   public ID onInitialSpawn(baL difficulty, @Nullable ID livingdata) {
      ID livingdata = super.onInitialSpawn(difficulty, livingdata);
      if (this.world.rand.nextInt(100) == 0) {
         KH entityskeleton = new KH(this.world);
         entityskeleton.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
         entityskeleton.onInitialSpawn(difficulty, (ID)null);
         this.world.spawnEntity(entityskeleton);
         entityskeleton.startRiding(this);
      }

      if (livingdata == null) {
         livingdata = new KV();
         if (this.world.getDifficulty() == baV.HARD && this.world.rand.nextFloat() < 0.1F * difficulty.getClampedAdditionalDifficulty()) {
            ((KV)livingdata).setRandomEffect(this.world.rand);
         }
      }

      if (livingdata instanceof KV) {
         VW potion = ((KV)livingdata).effect;
         if (potion != null) {
            this.addPotionEffect(new VZ(potion, Integer.MAX_VALUE));
         }
      }

      return (ID)livingdata;
   }

   public float getEyeHeight() {
      return 0.65F;
   }

   static {
      CLIMBING = Rv.createKey(KW.class, Rt.BYTE);
   }
}
