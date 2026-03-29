package neo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HL {
   private static final Logger LOGGER = LogManager.getLogger();
   private final HS dragon;
   private final HB[] phases = new HB[HK.getTotalPhases()];
   private HB phase;

   public HL(HS dragonIn) {
      this.dragon = dragonIn;
      this.setPhase(HK.HOVER);
   }

   public void setPhase(HK<?> phaseIn) {
      if (this.phase == null || phaseIn != this.phase.getType()) {
         if (this.phase != null) {
            this.phase.removeAreaEffect();
         }

         this.phase = this.getPhase(phaseIn);
         if (!this.dragon.world.isRemote) {
            this.dragon.getDataManager().set(HS.PHASE, phaseIn.getId());
         }

         LOGGER.debug("Dragon is now in phase {} on the {}", phaseIn, this.dragon.world.isRemote ? "client" : "server");
         this.phase.initPhase();
      }

   }

   public HB getCurrentPhase() {
      return this.phase;
   }

   public <T extends HB> T getPhase(HK<T> phaseIn) {
      int i = phaseIn.getId();
      if (this.phases[i] == null) {
         this.phases[i] = phaseIn.createPhase(this.dragon);
      }

      return this.phases[i];
   }
}
