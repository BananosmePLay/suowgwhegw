package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;

public class SPacketUpdateScore implements Packet<INetHandlerPlayClient> {
   private String name = "";
   private String objective = "";
   private int value;
   private Action action;

   public SPacketUpdateScore() {
   }

   public SPacketUpdateScore(Score scoreIn) {
      this.name = scoreIn.getPlayerName();
      this.objective = scoreIn.getObjective().getName();
      this.value = scoreIn.getScorePoints();
      this.action = SPacketUpdateScore.Action.CHANGE;
   }

   public SPacketUpdateScore(String nameIn) {
      this.name = nameIn;
      this.objective = "";
      this.value = 0;
      this.action = SPacketUpdateScore.Action.REMOVE;
   }

   public SPacketUpdateScore(String nameIn, ScoreObjective objectiveIn) {
      this.name = nameIn;
      this.objective = objectiveIn.getName();
      this.value = 0;
      this.action = SPacketUpdateScore.Action.REMOVE;
   }

   public void readPacketData(PacketBuffer buf) throws IOException {
      this.name = buf.readString(40);
      this.action = (Action)buf.readEnumValue(Action.class);
      this.objective = buf.readString(16);
      if (this.action != SPacketUpdateScore.Action.REMOVE) {
         this.value = buf.readVarInt();
      }

   }

   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeString(this.name);
      buf.writeEnumValue(this.action);
      buf.writeString(this.objective);
      if (this.action != SPacketUpdateScore.Action.REMOVE) {
         buf.writeVarInt(this.value);
      }

   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleUpdateScore(this);
   }

   public String getPlayerName() {
      return this.name;
   }

   public String getObjectiveName() {
      return this.objective;
   }

   public int getScoreValue() {
      return this.value;
   }

   public Action getScoreAction() {
      return this.action;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(INetHandler var1) {
      this.processPacket((INetHandlerPlayClient)var1);
   }

   public static enum Action {
      CHANGE,
      REMOVE;

      private Action() {
      }
   }
}
