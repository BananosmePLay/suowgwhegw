package neo;

public class Wz {
   private final Ws scoreboard;
   private final String name;
   private final Wo objectiveCriteria;
   private Wn renderType;
   private String displayName;

   public Wz(Ws scoreboard, String nameIn, Wo objectiveCriteriaIn) {
      this.scoreboard = scoreboard;
      this.name = nameIn;
      this.objectiveCriteria = objectiveCriteriaIn;
      this.displayName = nameIn;
      this.renderType = objectiveCriteriaIn.getRenderType();
   }

   public Ws getScoreboard() {
      return this.scoreboard;
   }

   public String getName() {
      return this.name;
   }

   public Wo getCriteria() {
      return this.objectiveCriteria;
   }

   public String getDisplayName() {
      return this.displayName;
   }

   public void setDisplayName(String nameIn) {
      this.displayName = nameIn;
      this.scoreboard.onObjectiveDisplayNameChanged(this);
   }

   public Wn getRenderType() {
      return this.renderType;
   }

   public void setRenderType(Wn type) {
      this.renderType = type;
      this.scoreboard.onObjectiveDisplayNameChanged(this);
   }
}
