package neo;

import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;

public class KX extends Jx {
   public KX(bij worldIn) {
      super(worldIn);
   }

   public static void registerFixesStray(DataFixer fixer) {
      Iu.registerFixesMob(fixer, KX.class);
   }

   public boolean getCanSpawnHere() {
      return super.getCanSpawnHere() && this.world.canSeeSky(new BlockPos(this));
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_STRAY;
   }

   protected SoundEvent getAmbientSound() {
      return NO.ENTITY_STRAY_AMBIENT;
   }

   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
      return NO.ENTITY_STRAY_HURT;
   }

   protected SoundEvent getDeathSound() {
      return NO.ENTITY_STRAY_DEATH;
   }

   SoundEvent getStepSound() {
      return NO.ENTITY_STRAY_STEP;
   }

   protected MO getArrow(float p_190726_1_) {
      MO entityarrow = super.getArrow(p_190726_1_);
      if (entityarrow instanceof Ne) {
         ((Ne)entityarrow).addEffect(new VZ(NL.SLOWNESS, 600));
      }

      return entityarrow;
   }
}
