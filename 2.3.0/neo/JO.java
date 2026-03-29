package neo;

import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;

class JO extends KQ {
   // $FF: synthetic field
   final JR this$0;

   private JO(JR this$0) {
      super(this$0);
      this.this$0 = this$0;
   }

   public boolean shouldExecute() {
      if (!super.shouldExecute()) {
         return false;
      } else {
         int i = this.this$0.world.getEntitiesWithinAABB(Lc.class, this.this$0.getEntityBoundingBox().grow(16.0)).size();
         return JR.access$400(this.this$0).nextInt(8) + 1 > i;
      }
   }

   protected int getCastingTime() {
      return 100;
   }

   protected int getCastingInterval() {
      return 340;
   }

   protected void castSpell() {
      for(int i = 0; i < 3; ++i) {
         BlockPos blockpos = (new BlockPos(this.this$0)).add(-2 + JR.access$500(this.this$0).nextInt(5), 1, -2 + JR.access$600(this.this$0).nextInt(5));
         Lc entityvex = new Lc(this.this$0.world);
         entityvex.moveToBlockPosAndAngles(blockpos, 0.0F, 0.0F);
         entityvex.onInitialSpawn(this.this$0.world.getDifficultyForLocation(blockpos), (ID)null);
         entityvex.setOwner(this.this$0);
         entityvex.setBoundOrigin(blockpos);
         entityvex.setLimitedLife(20 * (30 + JR.access$700(this.this$0).nextInt(90)));
         this.this$0.world.spawnEntity(entityvex);
      }

   }

   protected SoundEvent getSpellPrepareSound() {
      return NO.EVOCATION_ILLAGER_PREPARE_SUMMON;
   }

   protected KR getSpellType() {
      return KR.SUMMON_VEX;
   }

   // $FF: synthetic method
   JO(JR x0, Object x1) {
      this(x0);
   }
}
