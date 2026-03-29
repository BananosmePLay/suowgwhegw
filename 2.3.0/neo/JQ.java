package neo;

import com.google.common.base.Predicate;
import java.util.List;
import net.minecraft.util.SoundEvent;

public class JQ extends KQ {
   final Predicate<Mb> wololoSelector;
   // $FF: synthetic field
   final JR this$0;

   public JQ(JR this$0) {
      super(this$0);
      this.this$0 = this$0;
      this.wololoSelector = new Predicate<Mb>() {
         public boolean apply(Mb p_apply_1_) {
            return p_apply_1_.getFleeceColor() == Om.BLUE;
         }

         // $FF: synthetic method
         // $FF: bridge method
         public boolean apply(Object var1) {
            return this.apply((Mb)var1);
         }
      };
   }

   public boolean shouldExecute() {
      if (this.this$0.getAttackTarget() != null) {
         return false;
      } else if (this.this$0.isSpellcasting()) {
         return false;
      } else if (this.this$0.ticksExisted < this.spellCooldown) {
         return false;
      } else if (!this.this$0.world.getGameRules().getBoolean("mobGriefing")) {
         return false;
      } else {
         List<Mb> list = this.this$0.world.getEntitiesWithinAABB(Mb.class, this.this$0.getEntityBoundingBox().grow(16.0, 4.0, 16.0), this.wololoSelector);
         if (list.isEmpty()) {
            return false;
         } else {
            JR.access$900(this.this$0, (Mb)list.get(JR.access$800(this.this$0).nextInt(list.size())));
            return true;
         }
      }
   }

   public boolean shouldContinueExecuting() {
      return JR.access$300(this.this$0) != null && this.spellWarmup > 0;
   }

   public void resetTask() {
      super.resetTask();
      JR.access$900(this.this$0, (Mb)null);
   }

   protected void castSpell() {
      Mb entitysheep = JR.access$300(this.this$0);
      if (entitysheep != null && entitysheep.isEntityAlive()) {
         entitysheep.setFleeceColor(Om.RED);
      }

   }

   protected int getCastWarmupTime() {
      return 40;
   }

   protected int getCastingTime() {
      return 60;
   }

   protected int getCastingInterval() {
      return 140;
   }

   protected SoundEvent getSpellPrepareSound() {
      return NO.EVOCATION_ILLAGER_PREPARE_WOLOLO;
   }

   protected KR getSpellType() {
      return KR.WOLOLO;
   }
}
