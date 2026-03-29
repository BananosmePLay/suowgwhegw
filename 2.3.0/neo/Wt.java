package neo;

import java.util.Iterator;
import net.minecraft.util.text.TextFormatting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Wt extends bhZ {
   private static final Logger LOGGER = LogManager.getLogger();
   private Ws scoreboard;
   private QQ delayedInitNbt;

   public Wt() {
      this("scoreboard");
   }

   public Wt(String name) {
      super(name);
   }

   public void setScoreboard(Ws scoreboardIn) {
      this.scoreboard = scoreboardIn;
      if (this.delayedInitNbt != null) {
         this.readFromNBT(this.delayedInitNbt);
      }

   }

   public void readFromNBT(QQ nbt) {
      if (this.scoreboard == null) {
         this.delayedInitNbt = nbt;
      } else {
         this.readObjectives(nbt.getTagList("Objectives", 10));
         this.readScores(nbt.getTagList("PlayerScores", 10));
         if (nbt.hasKey("DisplaySlots", 10)) {
            this.readDisplayConfig(nbt.getCompoundTag("DisplaySlots"));
         }

         if (nbt.hasKey("Teams", 9)) {
            this.readTeams(nbt.getTagList("Teams", 10));
         }
      }

   }

   protected void readTeams(QW tagList) {
      for(int i = 0; i < tagList.tagCount(); ++i) {
         QQ nbttagcompound = tagList.getCompoundTagAt(i);
         String s = nbttagcompound.getString("Name");
         if (s.length() > 16) {
            s = s.substring(0, 16);
         }

         WA scoreplayerteam = this.scoreboard.createTeam(s);
         String s1 = nbttagcompound.getString("DisplayName");
         if (s1.length() > 32) {
            s1 = s1.substring(0, 32);
         }

         scoreplayerteam.setDisplayName(s1);
         if (nbttagcompound.hasKey("TeamColor", 8)) {
            scoreplayerteam.setColor(TextFormatting.getValueByName(nbttagcompound.getString("TeamColor")));
         }

         scoreplayerteam.setPrefix(nbttagcompound.getString("Prefix"));
         scoreplayerteam.setSuffix(nbttagcompound.getString("Suffix"));
         if (nbttagcompound.hasKey("AllowFriendlyFire", 99)) {
            scoreplayerteam.setAllowFriendlyFire(nbttagcompound.getBoolean("AllowFriendlyFire"));
         }

         if (nbttagcompound.hasKey("SeeFriendlyInvisibles", 99)) {
            scoreplayerteam.setSeeFriendlyInvisiblesEnabled(nbttagcompound.getBoolean("SeeFriendlyInvisibles"));
         }

         WD team$enumvisible1;
         if (nbttagcompound.hasKey("NameTagVisibility", 8)) {
            team$enumvisible1 = WD.getByName(nbttagcompound.getString("NameTagVisibility"));
            if (team$enumvisible1 != null) {
               scoreplayerteam.setNameTagVisibility(team$enumvisible1);
            }
         }

         if (nbttagcompound.hasKey("DeathMessageVisibility", 8)) {
            team$enumvisible1 = WD.getByName(nbttagcompound.getString("DeathMessageVisibility"));
            if (team$enumvisible1 != null) {
               scoreplayerteam.setDeathMessageVisibility(team$enumvisible1);
            }
         }

         if (nbttagcompound.hasKey("CollisionRule", 8)) {
            WC team$collisionrule = WC.getByName(nbttagcompound.getString("CollisionRule"));
            if (team$collisionrule != null) {
               scoreplayerteam.setCollisionRule(team$collisionrule);
            }
         }

         this.loadTeamPlayers(scoreplayerteam, nbttagcompound.getTagList("Players", 8));
      }

   }

   protected void loadTeamPlayers(WA playerTeam, QW tagList) {
      for(int i = 0; i < tagList.tagCount(); ++i) {
         this.scoreboard.addPlayerToTeam(tagList.getStringTagAt(i), playerTeam.getName());
      }

   }

   protected void readDisplayConfig(QQ compound) {
      for(int i = 0; i < 19; ++i) {
         if (compound.hasKey("slot_" + i, 8)) {
            String s = compound.getString("slot_" + i);
            Wz scoreobjective = this.scoreboard.getObjective(s);
            this.scoreboard.setObjectiveInDisplaySlot(i, scoreobjective);
         }
      }

   }

   protected void readObjectives(QW nbt) {
      for(int i = 0; i < nbt.tagCount(); ++i) {
         QQ nbttagcompound = nbt.getCompoundTagAt(i);
         Wo iscorecriteria = (Wo)Wo.INSTANCES.get(nbttagcompound.getString("CriteriaName"));
         if (iscorecriteria != null) {
            String s = nbttagcompound.getString("Name");
            if (s.length() > 16) {
               s = s.substring(0, 16);
            }

            Wz scoreobjective = this.scoreboard.addScoreObjective(s, iscorecriteria);
            scoreobjective.setDisplayName(nbttagcompound.getString("DisplayName"));
            scoreobjective.setRenderType(Wn.getByName(nbttagcompound.getString("RenderType")));
         }
      }

   }

   protected void readScores(QW nbt) {
      for(int i = 0; i < nbt.tagCount(); ++i) {
         QQ nbttagcompound = nbt.getCompoundTagAt(i);
         Wz scoreobjective = this.scoreboard.getObjective(nbttagcompound.getString("Objective"));
         String s = nbttagcompound.getString("Name");
         if (s.length() > 40) {
            s = s.substring(0, 40);
         }

         Wr score = this.scoreboard.getOrCreateScore(s, scoreobjective);
         score.setScorePoints(nbttagcompound.getInteger("Score"));
         if (nbttagcompound.hasKey("Locked")) {
            score.setLocked(nbttagcompound.getBoolean("Locked"));
         }
      }

   }

   public QQ writeToNBT(QQ compound) {
      if (this.scoreboard == null) {
         LOGGER.warn("Tried to save scoreboard without having a scoreboard...");
         return compound;
      } else {
         compound.setTag("Objectives", this.objectivesToNbt());
         compound.setTag("PlayerScores", this.scoresToNbt());
         compound.setTag("Teams", this.teamsToNbt());
         this.fillInDisplaySlots(compound);
         return compound;
      }
   }

   protected QW teamsToNbt() {
      QW nbttaglist = new QW();
      Iterator var2 = this.scoreboard.getTeams().iterator();

      while(var2.hasNext()) {
         WA scoreplayerteam = (WA)var2.next();
         QQ nbttagcompound = new QQ();
         nbttagcompound.setString("Name", scoreplayerteam.getName());
         nbttagcompound.setString("DisplayName", scoreplayerteam.getDisplayName());
         if (scoreplayerteam.getColor().getColorIndex() >= 0) {
            nbttagcompound.setString("TeamColor", scoreplayerteam.getColor().getFriendlyName());
         }

         nbttagcompound.setString("Prefix", scoreplayerteam.getPrefix());
         nbttagcompound.setString("Suffix", scoreplayerteam.getSuffix());
         nbttagcompound.setBoolean("AllowFriendlyFire", scoreplayerteam.getAllowFriendlyFire());
         nbttagcompound.setBoolean("SeeFriendlyInvisibles", scoreplayerteam.getSeeFriendlyInvisiblesEnabled());
         nbttagcompound.setString("NameTagVisibility", scoreplayerteam.getNameTagVisibility().internalName);
         nbttagcompound.setString("DeathMessageVisibility", scoreplayerteam.getDeathMessageVisibility().internalName);
         nbttagcompound.setString("CollisionRule", scoreplayerteam.getCollisionRule().name);
         QW nbttaglist1 = new QW();
         Iterator var6 = scoreplayerteam.getMembershipCollection().iterator();

         while(var6.hasNext()) {
            String s = (String)var6.next();
            nbttaglist1.appendTag(new Ra(s));
         }

         nbttagcompound.setTag("Players", nbttaglist1);
         nbttaglist.appendTag(nbttagcompound);
      }

      return nbttaglist;
   }

   protected void fillInDisplaySlots(QQ compound) {
      QQ nbttagcompound = new QQ();
      boolean flag = false;

      for(int i = 0; i < 19; ++i) {
         Wz scoreobjective = this.scoreboard.getObjectiveInDisplaySlot(i);
         if (scoreobjective != null) {
            nbttagcompound.setString("slot_" + i, scoreobjective.getName());
            flag = true;
         }
      }

      if (flag) {
         compound.setTag("DisplaySlots", nbttagcompound);
      }

   }

   protected QW objectivesToNbt() {
      QW nbttaglist = new QW();
      Iterator var2 = this.scoreboard.getScoreObjectives().iterator();

      while(var2.hasNext()) {
         Wz scoreobjective = (Wz)var2.next();
         if (scoreobjective.getCriteria() != null) {
            QQ nbttagcompound = new QQ();
            nbttagcompound.setString("Name", scoreobjective.getName());
            nbttagcompound.setString("CriteriaName", scoreobjective.getCriteria().getName());
            nbttagcompound.setString("DisplayName", scoreobjective.getDisplayName());
            nbttagcompound.setString("RenderType", scoreobjective.getRenderType().getRenderType());
            nbttaglist.appendTag(nbttagcompound);
         }
      }

      return nbttaglist;
   }

   protected QW scoresToNbt() {
      QW nbttaglist = new QW();
      Iterator var2 = this.scoreboard.getScores().iterator();

      while(var2.hasNext()) {
         Wr score = (Wr)var2.next();
         if (score.getObjective() != null) {
            QQ nbttagcompound = new QQ();
            nbttagcompound.setString("Name", score.getPlayerName());
            nbttagcompound.setString("Objective", score.getObjective().getName());
            nbttagcompound.setInteger("Score", score.getScorePoints());
            nbttagcompound.setBoolean("Locked", score.isLocked());
            nbttaglist.appendTag(nbttagcompound);
         }
      }

      return nbttaglist;
   }
}
