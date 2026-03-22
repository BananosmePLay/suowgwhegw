package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.EnumDifficulty;

public class SPacketServerDifficulty implements Packet<INetHandlerPlayClient> {
   private EnumDifficulty difficulty;
   private boolean difficultyLocked;

   public SPacketServerDifficulty() {
   }

   public SPacketServerDifficulty(EnumDifficulty difficultyIn, boolean difficultyLockedIn) {
      this.difficulty = difficultyIn;
      this.difficultyLocked = difficultyLockedIn;
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleServerDifficulty(this);
   }

   public void readPacketData(PacketBuffer buf) throws IOException {
      this.difficulty = EnumDifficulty.byId(buf.readUnsignedByte());
   }

   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeByte(this.difficulty.getId());
   }

   public boolean isDifficultyLocked() {
      return this.difficultyLocked;
   }

   public EnumDifficulty getDifficulty() {
      return this.difficulty;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(INetHandler var1) {
      this.processPacket((INetHandlerPlayClient)var1);
   }
}
