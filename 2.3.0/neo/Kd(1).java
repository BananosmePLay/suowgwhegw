package neo;

import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;

public class Kd extends Lk {
   public Kd(bij worldIn) {
      super(worldIn);
   }

   public static void registerFixesHusk(DataFixer fixer) {
      Iu.registerFixesMob(fixer, Kd.class);
   }

   public boolean getCanSpawnHere() {
      return super.getCanSpawnHere() && this.world.canSeeSky(new BlockPos(this));
   }

   protected boolean shouldBurnInDay() {
      return false;
   }

   protected SoundEvent getAmbientSound() {
      return NO.ENTITY_HUSK_AMBIENT;
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return NO.ENTITY_HUSK_HURT;
   }

   protected SoundEvent getDeathSound() {
      return NO.ENTITY_HUSK_DEATH;
   }

   protected SoundEvent getStepSound() {
      return NO.ENTITY_HUSK_STEP;
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_HUSK;
   }

   public boolean attackEntityAsMob(Ig entityIn) {
      boolean flag = super.attackEntityAsMob(entityIn);
      if (flag && this.getHeldItemMainhand().isEmpty() && entityIn instanceof Iw) {
         float f = this.world.getDifficultyForLocation(new BlockPos(this)).getAdditionalDifficulty();
         ((Iw)entityIn).addPotionEffect(new VZ(NL.HUNGER, 140 * (int)f));
      }

      return flag;
   }

   protected Qy getSkullDrop() {
      return Qy.EMPTY;
   }
}
