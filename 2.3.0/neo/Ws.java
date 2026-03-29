package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.util.text.TextFormatting;

public class Ws {
   private final Map<String, Wz> scoreObjectives = Maps.newHashMap();
   private final Map<Wo, List<Wz>> scoreObjectiveCriterias = Maps.newHashMap();
   private final Map<String, Map<Wz, Wr>> entitiesScoreObjectives = Maps.newHashMap();
   public final Wz[] objectiveDisplaySlots = new Wz[19];
   private final Map<String, WA> teams = Maps.newHashMap();
   private final Map<String, WA> teamMemberships = Maps.newHashMap();
   private static String[] displaySlots;

   public Ws() {
   }

   @Nullable
   public Wz getObjective(String name) {
      return (Wz)this.scoreObjectives.get(name);
   }

   public Wz addScoreObjective(String name, Wo criteria) {
      if (name.length() > 16) {
         throw new IllegalArgumentException("The objective name '" + name + "' is too long!");
      } else {
         Wz scoreobjective = this.getObjective(name);
         if (scoreobjective != null) {
            throw new IllegalArgumentException("An objective with the name '" + name + "' already exists!");
         } else {
            scoreobjective = new Wz(this, name, criteria);
            List<Wz> list = (List)this.scoreObjectiveCriterias.get(criteria);
            if (list == null) {
               list = Lists.newArrayList();
               this.scoreObjectiveCriterias.put(criteria, list);
            }

            ((List)list).add(scoreobjective);
            this.scoreObjectives.put(name, scoreobjective);
            this.onScoreObjectiveAdded(scoreobjective);
            return scoreobjective;
         }
      }
   }

   public Collection<Wz> getObjectivesFromCriteria(Wo criteria) {
      Collection<Wz> collection = (Collection)this.scoreObjectiveCriterias.get(criteria);
      return collection == null ? Lists.newArrayList() : Lists.newArrayList(collection);
   }

   public boolean entityHasObjective(String name, Wz objective) {
      Map<Wz, Wr> map = (Map)this.entitiesScoreObjectives.get(name);
      if (map == null) {
         return false;
      } else {
         Wr score = (Wr)map.get(objective);
         return score != null;
      }
   }

   public Wr getOrCreateScore(String username, Wz objective) {
      if (username.length() > 40) {
         throw new IllegalArgumentException("The player name '" + username + "' is too long!");
      } else {
         Map<Wz, Wr> map = (Map)this.entitiesScoreObjectives.get(username);
         if (map == null) {
            map = Maps.newHashMap();
            this.entitiesScoreObjectives.put(username, map);
         }

         Wr score = (Wr)((Map)map).get(objective);
         if (score == null) {
            score = new Wr(this, objective, username);
            ((Map)map).put(objective, score);
         }

         return score;
      }
   }

   public Collection<Wr> getSortedScores(Wz objective) {
      List<Wr> list = Lists.newArrayList();
      Iterator var3 = this.entitiesScoreObjectives.values().iterator();

      while(var3.hasNext()) {
         Map<Wz, Wr> map = (Map)var3.next();
         Wr score = (Wr)map.get(objective);
         if (score != null) {
            list.add(score);
         }
      }

      Collections.sort(list, Wr.SCORE_COMPARATOR);
      return list;
   }

   public Collection<Wz> getScoreObjectives() {
      return this.scoreObjectives.values();
   }

   public Collection<String> getObjectiveNames() {
      return this.entitiesScoreObjectives.keySet();
   }

   public void removeObjectiveFromEntity(String name, Wz objective) {
      Map map2;
      if (objective == null) {
         map2 = (Map)this.entitiesScoreObjectives.remove(name);
         if (map2 != null) {
            this.broadcastScoreUpdate(name);
         }
      } else {
         map2 = (Map)this.entitiesScoreObjectives.get(name);
         if (map2 != null) {
            Wr score = (Wr)map2.remove(objective);
            if (map2.size() < 1) {
               Map<Wz, Wr> map1 = (Map)this.entitiesScoreObjectives.remove(name);
               if (map1 != null) {
                  this.broadcastScoreUpdate(name);
               }
            } else if (score != null) {
               this.broadcastScoreUpdate(name, objective);
            }
         }
      }

   }

   public Collection<Wr> getScores() {
      Collection<Map<Wz, Wr>> collection = this.entitiesScoreObjectives.values();
      List<Wr> list = Lists.newArrayList();
      Iterator var3 = collection.iterator();

      while(var3.hasNext()) {
         Map<Wz, Wr> map = (Map)var3.next();
         list.addAll(map.values());
      }

      return list;
   }

   public Map<Wz, Wr> getObjectivesForEntity(String name) {
      Map<Wz, Wr> map = (Map)this.entitiesScoreObjectives.get(name);
      if (map == null) {
         map = Maps.newHashMap();
      }

      return (Map)map;
   }

   public void removeObjective(Wz objective) {
      this.scoreObjectives.remove(objective.getName());

      for(int i = 0; i < 19; ++i) {
         if (this.getObjectiveInDisplaySlot(i) == objective) {
            this.setObjectiveInDisplaySlot(i, (Wz)null);
         }
      }

      List<Wz> list = (List)this.scoreObjectiveCriterias.get(objective.getCriteria());
      if (list != null) {
         list.remove(objective);
      }

      Iterator var3 = this.entitiesScoreObjectives.values().iterator();

      while(var3.hasNext()) {
         Map<Wz, Wr> map = (Map)var3.next();
         map.remove(objective);
      }

      this.onScoreObjectiveRemoved(objective);
   }

   public void setObjectiveInDisplaySlot(int objectiveSlot, Wz objective) {
      this.objectiveDisplaySlots[objectiveSlot] = objective;
   }

   @Nullable
   public Wz getObjectiveInDisplaySlot(int slotIn) {
      return this.objectiveDisplaySlots[slotIn];
   }

   public WA getTeam(String teamName) {
      return (WA)this.teams.get(teamName);
   }

   public WA createTeam(String name) {
      if (name.length() > 16) {
         throw new IllegalArgumentException("The team name '" + name + "' is too long!");
      } else {
         WA scoreplayerteam = this.getTeam(name);
         if (scoreplayerteam != null) {
            throw new IllegalArgumentException("A team with the name '" + name + "' already exists!");
         } else {
            scoreplayerteam = new WA(this, name);
            this.teams.put(name, scoreplayerteam);
            this.broadcastTeamCreated(scoreplayerteam);
            return scoreplayerteam;
         }
      }
   }

   public void removeTeam(WA playerTeam) {
      this.teams.remove(playerTeam.getName());
      Iterator var2 = playerTeam.getMembershipCollection().iterator();

      while(var2.hasNext()) {
         String s = (String)var2.next();
         this.teamMemberships.remove(s);
      }

      this.broadcastTeamRemove(playerTeam);
   }

   public boolean addPlayerToTeam(String player, String newTeam) {
      if (player.length() > 40) {
         throw new IllegalArgumentException("The player name '" + player + "' is too long!");
      } else if (!this.teams.containsKey(newTeam)) {
         return false;
      } else {
         WA scoreplayerteam = this.getTeam(newTeam);
         if (this.getPlayersTeam(player) != null) {
            this.removePlayerFromTeams(player);
         }

         this.teamMemberships.put(player, scoreplayerteam);
         scoreplayerteam.getMembershipCollection().add(player);
         return true;
      }
   }

   public boolean removePlayerFromTeams(String playerName) {
      WA scoreplayerteam = this.getPlayersTeam(playerName);
      if (scoreplayerteam != null) {
         this.removePlayerFromTeam(playerName, scoreplayerteam);
         return true;
      } else {
         return false;
      }
   }

   public void removePlayerFromTeam(String username, WA playerTeam) {
      if (this.getPlayersTeam(username) != playerTeam) {
         throw new IllegalStateException("Player is either on another team or not on any team. Cannot remove from team '" + playerTeam.getName() + "'.");
      } else {
         this.teamMemberships.remove(username);
         playerTeam.getMembershipCollection().remove(username);
      }
   }

   public Collection<String> getTeamNames() {
      return this.teams.keySet();
   }

   public Collection<WA> getTeams() {
      return this.teams.values();
   }

   @Nullable
   public WA getPlayersTeam(String username) {
      return (WA)this.teamMemberships.get(username);
   }

   public void onScoreObjectiveAdded(Wz scoreObjectiveIn) {
   }

   public void onObjectiveDisplayNameChanged(Wz objective) {
   }

   public void onScoreObjectiveRemoved(Wz objective) {
   }

   public void onScoreUpdated(Wr scoreIn) {
   }

   public void broadcastScoreUpdate(String scoreName) {
   }

   public void broadcastScoreUpdate(String scoreName, Wz objective) {
   }

   public void broadcastTeamCreated(WA playerTeam) {
   }

   public void broadcastTeamInfoUpdate(WA playerTeam) {
   }

   public void broadcastTeamRemove(WA playerTeam) {
   }

   public static String getObjectiveDisplaySlot(int id) {
      switch (id) {
         case 0:
            return "list";
         case 1:
            return "sidebar";
         case 2:
            return "belowName";
         default:
            if (id >= 3 && id <= 18) {
               TextFormatting textformatting = TextFormatting.fromColorIndex(id - 3);
               if (textformatting != null && textformatting != TextFormatting.RESET) {
                  return "sidebar.team." + textformatting.getFriendlyName();
               }
            }

            return null;
      }
   }

   public static int getObjectiveDisplaySlotNumber(String name) {
      if ("list".equalsIgnoreCase(name)) {
         return 0;
      } else if ("sidebar".equalsIgnoreCase(name)) {
         return 1;
      } else if ("belowName".equalsIgnoreCase(name)) {
         return 2;
      } else {
         if (name.startsWith("sidebar.team.")) {
            String s = name.substring("sidebar.team.".length());
            TextFormatting textformatting = TextFormatting.getValueByName(s);
            if (textformatting != null && textformatting.getColorIndex() >= 0) {
               return textformatting.getColorIndex() + 3;
            }
         }

         return -1;
      }
   }

   public static String[] getDisplaySlotStrings() {
      if (displaySlots == null) {
         displaySlots = new String[19];

         for(int i = 0; i < 19; ++i) {
            displaySlots[i] = getObjectiveDisplaySlot(i);
         }
      }

      return displaySlots;
   }

   public void removeEntity(Ig entityIn) {
      if (entityIn != null && !(entityIn instanceof ME) && !entityIn.isEntityAlive()) {
         String s = entityIn.getCachedUniqueIdString();
         this.removeObjectiveFromEntity(s, (Wz)null);
         this.removePlayerFromTeams(s);
      }

   }
}
