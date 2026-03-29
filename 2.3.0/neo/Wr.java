package neo;

import java.util.Comparator;

public class Wr {
   public static final Comparator<Wr> SCORE_COMPARATOR = new Comparator<Wr>() {
      public int compare(Wr p_compare_1_, Wr p_compare_2_) {
         if (p_compare_1_.getScorePoints() > p_compare_2_.getScorePoints()) {
            return 1;
         } else {
            return p_compare_1_.getScorePoints() < p_compare_2_.getScorePoints() ? -1 : p_compare_2_.getPlayerName().compareToIgnoreCase(p_compare_1_.getPlayerName());
         }
      }

      // $FF: synthetic method
      // $FF: bridge method
      public int compare(Object var1, Object var2) {
         return this.compare((Wr)var1, (Wr)var2);
      }
   };
   private final Ws scoreboard;
   private final Wz objective;
   private final String scorePlayerName;
   private int scorePoints;
   private boolean locked;
   private boolean forceUpdate;

   public Wr(Ws scoreboard, Wz objective, String playerName) {
      this.scoreboard = scoreboard;
      this.objective = objective;
      this.scorePlayerName = playerName;
      this.forceUpdate = true;
   }

   public void increaseScore(int amount) {
      if (this.objective.getCriteria().isReadOnly()) {
         throw new IllegalStateException("Cannot modify read-only score");
      } else {
         this.setScorePoints(this.getScorePoints() + amount);
      }
   }

   public void decreaseScore(int amount) {
      if (this.objective.getCriteria().isReadOnly()) {
         throw new IllegalStateException("Cannot modify read-only score");
      } else {
         this.setScorePoints(this.getScorePoints() - amount);
      }
   }

   public void incrementScore() {
      if (this.objective.getCriteria().isReadOnly()) {
         throw new IllegalStateException("Cannot modify read-only score");
      } else {
         this.increaseScore(1);
      }
   }

   public int getScorePoints() {
      return this.scorePoints;
   }

   public void setScorePoints(int points) {
      int i = this.scorePoints;
      this.scorePoints = points;
      if (i != points || this.forceUpdate) {
         this.forceUpdate = false;
         this.getScoreScoreboard().onScoreUpdated(this);
      }

   }

   public Wz getObjective() {
      return this.objective;
   }

   public String getPlayerName() {
      return this.scorePlayerName;
   }

   public Ws getScoreScoreboard() {
      return this.scoreboard;
   }

   public boolean isLocked() {
      return this.locked;
   }

   public void setLocked(boolean locked) {
      this.locked = locked;
   }
}
