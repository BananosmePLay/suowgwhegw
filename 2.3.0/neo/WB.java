package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class WB extends Ws {
   private final Xx server;
   private final Set<Wz> addedObjectives = Sets.newHashSet();
   private Runnable[] dirtyRunnables = new Runnable[0];

   public WB(Xx mcServer) {
      this.server = mcServer;
   }

   public void onScoreUpdated(Wr scoreIn) {
      super.onScoreUpdated(scoreIn);
      if (this.addedObjectives.contains(scoreIn.getObjective())) {
         this.server.getPlayerList().sendPacketToAllPlayers(new Vf(scoreIn));
      }

      this.markSaveDataDirty();
   }

   public void broadcastScoreUpdate(String scoreName) {
      super.broadcastScoreUpdate(scoreName);
      this.server.getPlayerList().sendPacketToAllPlayers(new Vf(scoreName));
      this.markSaveDataDirty();
   }

   public void broadcastScoreUpdate(String scoreName, Wz objective) {
      super.broadcastScoreUpdate(scoreName, objective);
      this.server.getPlayerList().sendPacketToAllPlayers(new Vf(scoreName, objective));
      this.markSaveDataDirty();
   }

   public void setObjectiveInDisplaySlot(int objectiveSlot, Wz objective) {
      Wz scoreobjective = this.getObjectiveInDisplaySlot(objectiveSlot);
      super.setObjectiveInDisplaySlot(objectiveSlot, objective);
      if (scoreobjective != objective && scoreobjective != null) {
         if (this.getObjectiveDisplaySlotCount(scoreobjective) > 0) {
            this.server.getPlayerList().sendPacketToAllPlayers(new TQ(objectiveSlot, objective));
         } else {
            this.sendDisplaySlotRemovalPackets(scoreobjective);
         }
      }

      if (objective != null) {
         if (this.addedObjectives.contains(objective)) {
            this.server.getPlayerList().sendPacketToAllPlayers(new TQ(objectiveSlot, objective));
         } else {
            this.addObjective(objective);
         }
      }

      this.markSaveDataDirty();
   }

   public boolean addPlayerToTeam(String player, String newTeam) {
      if (super.addPlayerToTeam(player, newTeam)) {
         WA scoreplayerteam = this.getTeam(newTeam);
         this.server.getPlayerList().sendPacketToAllPlayers(new UV(scoreplayerteam, Arrays.asList(player), 3));
         this.markSaveDataDirty();
         return true;
      } else {
         return false;
      }
   }

   public void removePlayerFromTeam(String username, WA playerTeam) {
      super.removePlayerFromTeam(username, playerTeam);
      this.server.getPlayerList().sendPacketToAllPlayers(new UV(playerTeam, Arrays.asList(username), 4));
      this.markSaveDataDirty();
   }

   public void onScoreObjectiveAdded(Wz scoreObjectiveIn) {
      super.onScoreObjectiveAdded(scoreObjectiveIn);
      this.markSaveDataDirty();
   }

   public void onObjectiveDisplayNameChanged(Wz objective) {
      super.onObjectiveDisplayNameChanged(objective);
      if (this.addedObjectives.contains(objective)) {
         this.server.getPlayerList().sendPacketToAllPlayers(new UE(objective, 2));
      }

      this.markSaveDataDirty();
   }

   public void onScoreObjectiveRemoved(Wz objective) {
      super.onScoreObjectiveRemoved(objective);
      if (this.addedObjectives.contains(objective)) {
         this.sendDisplaySlotRemovalPackets(objective);
      }

      this.markSaveDataDirty();
   }

   public void broadcastTeamCreated(WA playerTeam) {
      super.broadcastTeamCreated(playerTeam);
      this.server.getPlayerList().sendPacketToAllPlayers(new UV(playerTeam, 0));
      this.markSaveDataDirty();
   }

   public void broadcastTeamInfoUpdate(WA playerTeam) {
      super.broadcastTeamInfoUpdate(playerTeam);
      this.server.getPlayerList().sendPacketToAllPlayers(new UV(playerTeam, 2));
      this.markSaveDataDirty();
   }

   public void broadcastTeamRemove(WA playerTeam) {
      super.broadcastTeamRemove(playerTeam);
      this.server.getPlayerList().sendPacketToAllPlayers(new UV(playerTeam, 1));
      this.markSaveDataDirty();
   }

   public void addDirtyRunnable(Runnable runnable) {
      this.dirtyRunnables = (Runnable[])((Runnable[])Arrays.copyOf(this.dirtyRunnables, this.dirtyRunnables.length + 1));
      this.dirtyRunnables[this.dirtyRunnables.length - 1] = runnable;
   }

   protected void markSaveDataDirty() {
      Runnable[] var1 = this.dirtyRunnables;
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         Runnable runnable = var1[var3];
         runnable.run();
      }

   }

   public List<Sz<?>> getCreatePackets(Wz objective) {
      List<Sz<?>> list = Lists.newArrayList();
      list.add(new UE(objective, 0));

      for(int i = 0; i < 19; ++i) {
         if (this.getObjectiveInDisplaySlot(i) == objective) {
            list.add(new TQ(i, objective));
         }
      }

      Iterator var5 = this.getSortedScores(objective).iterator();

      while(var5.hasNext()) {
         Wr score = (Wr)var5.next();
         list.add(new Vf(score));
      }

      return list;
   }

   public void addObjective(Wz objective) {
      List<Sz<?>> list = this.getCreatePackets(objective);
      Iterator var3 = this.server.getPlayerList().getPlayers().iterator();

      while(var3.hasNext()) {
         MG entityplayermp = (MG)var3.next();
         Iterator var5 = list.iterator();

         while(var5.hasNext()) {
            Sz<?> packet = (Sz)var5.next();
            entityplayermp.connection.sendPacket(packet);
         }
      }

      this.addedObjectives.add(objective);
   }

   public List<Sz<?>> getDestroyPackets(Wz p_96548_1_) {
      List<Sz<?>> list = Lists.newArrayList();
      list.add(new UE(p_96548_1_, 1));

      for(int i = 0; i < 19; ++i) {
         if (this.getObjectiveInDisplaySlot(i) == p_96548_1_) {
            list.add(new TQ(i, p_96548_1_));
         }
      }

      return list;
   }

   public void sendDisplaySlotRemovalPackets(Wz p_96546_1_) {
      List<Sz<?>> list = this.getDestroyPackets(p_96546_1_);
      Iterator var3 = this.server.getPlayerList().getPlayers().iterator();

      while(var3.hasNext()) {
         MG entityplayermp = (MG)var3.next();
         Iterator var5 = list.iterator();

         while(var5.hasNext()) {
            Sz<?> packet = (Sz)var5.next();
            entityplayermp.connection.sendPacket(packet);
         }
      }

      this.addedObjectives.remove(p_96546_1_);
   }

   public int getObjectiveDisplaySlotCount(Wz p_96552_1_) {
      int i = 0;

      for(int j = 0; j < 19; ++j) {
         if (this.getObjectiveInDisplaySlot(j) == p_96552_1_) {
            ++i;
         }
      }

      return i;
   }
}
