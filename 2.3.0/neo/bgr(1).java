package neo;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class bgr implements bgv {
   private final Map<String, bhC> scores;
   private final bhf target;

   public bgr(Map<String, bhC> scoreIn, bhf targetIn) {
      this.scores = scoreIn;
      this.target = targetIn;
   }

   public boolean testCondition(Random rand, bhg context) {
      Ig entity = context.getEntity(this.target);
      if (entity == null) {
         return false;
      } else {
         Ws scoreboard = entity.world.getScoreboard();
         Iterator var5 = this.scores.entrySet().iterator();

         Map.Entry entry;
         do {
            if (!var5.hasNext()) {
               return true;
            }

            entry = (Map.Entry)var5.next();
         } while(this.entityScoreMatch(entity, scoreboard, (String)entry.getKey(), (bhC)entry.getValue()));

         return false;
      }
   }

   protected boolean entityScoreMatch(Ig entityIn, Ws scoreboardIn, String objectiveStr, bhC rand) {
      Wz scoreobjective = scoreboardIn.getObjective(objectiveStr);
      if (scoreobjective == null) {
         return false;
      } else {
         String s = entityIn instanceof MG ? entityIn.getName() : entityIn.getCachedUniqueIdString();
         return !scoreboardIn.entityHasObjective(s, scoreobjective) ? false : rand.isInRange(scoreboardIn.getOrCreateScore(s, scoreobjective).getScorePoints());
      }
   }

   // $FF: synthetic method
   static Map access$000(bgr x0) {
      return x0.scores;
   }

   // $FF: synthetic method
   static bhf access$100(bgr x0) {
      return x0.target;
   }
}
