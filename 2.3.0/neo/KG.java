package neo;

import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;

public class KG extends Kl {
   private KF summonSilverfish;

   public KG(bij worldIn) {
      super(worldIn);
      this.setSize(0.4F, 0.3F);
   }

   public static void registerFixesSilverfish(DataFixer fixer) {
      Iu.registerFixesMob(fixer, KG.class);
   }

   protected void initEntityAI() {
      this.summonSilverfish = new KF(this);
      this.tasks.addTask(1, new He(this));
      this.tasks.addTask(3, this.summonSilverfish);
      this.tasks.addTask(4, new Gd(this, 1.0, false));
      this.tasks.addTask(5, new KE(this));
      this.targetTasks.addTask(1, new GB(this, true, new Class[0]));
      this.targetTasks.addTask(2, new GR(this, ME.class, true));
   }

   public double getYOffset() {
      return 0.1;
   }

   public float getEyeHeight() {
      return 0.1F;
   }

   protected void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(Ni.MAX_HEALTH).setBaseValue(8.0);
      this.getEntityAttribute(Ni.MOVEMENT_SPEED).setBaseValue(0.25);
      this.getEntityAttribute(Ni.ATTACK_DAMAGE).setBaseValue(1.0);
   }

   protected boolean canTriggerWalking() {
      return false;
   }

   protected SoundEvent getAmbientSound() {
      return NO.ENTITY_SILVERFISH_AMBIENT;
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return NO.ENTITY_SILVERFISH_HURT;
   }

   protected SoundEvent getDeathSound() {
      return NO.ENTITY_SILVERFISH_DEATH;
   }

   protected void playStepSound(BlockPos pos, co blockIn) {
      this.playSound(NO.ENTITY_SILVERFISH_STEP, 0.15F, 1.0F);
   }

   public boolean attackEntityFrom(DamageSource source, float amount) {
      if (this.isEntityInvulnerable(source)) {
         return false;
      } else {
         if ((source instanceof EntityDamageSource || source == DamageSource.MAGIC) && this.summonSilverfish != null) {
            this.summonSilverfish.notifyHurt();
         }

         return super.attackEntityFrom(source, amount);
      }
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_SILVERFISH;
   }

   public void onUpdate() {
      this.renderYawOffset = this.rotationYaw;
      super.onUpdate();
   }

   public void setRenderYawOffset(float offset) {
      this.rotationYaw = offset;
      super.setRenderYawOffset(offset);
   }

   public float getBlockPathWeight(BlockPos pos) {
      return this.world.getBlockState(pos.down()).getBlock() == Nk.STONE ? 10.0F : super.getBlockPathWeight(pos);
   }

   protected boolean isValidLightLevel() {
      return true;
   }

   public boolean getCanSpawnHere() {
      if (super.getCanSpawnHere()) {
         ME entityplayer = this.world.getNearestPlayerNotCreative(this, 5.0);
         return entityplayer == null;
      } else {
         return false;
      }
   }

   public IB getCreatureAttribute() {
      return IB.ARTHROPOD;
   }
}
