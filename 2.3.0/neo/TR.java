package neo;

import java.io.IOException;
import net.minecraft.util.math.BlockPos;

public class TR implements Sz<Ts> {
   private int soundType;
   private BlockPos soundPos;
   private int soundData;
   private boolean serverWide;

   public TR() {
   }

   public TR(int soundTypeIn, BlockPos soundPosIn, int soundDataIn, boolean serverWideIn) {
      this.soundType = soundTypeIn;
      this.soundPos = soundPosIn;
      this.soundData = soundDataIn;
      this.serverWide = serverWideIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.soundType = buf.readInt();
      this.soundPos = buf.readBlockPos();
      this.soundData = buf.readInt();
      this.serverWide = buf.readBoolean();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeInt(this.soundType);
      buf.writeBlockPos(this.soundPos);
      buf.writeInt(this.soundData);
      buf.writeBoolean(this.serverWide);
   }

   public void processPacket(Ts handler) {
      handler.handleEffect(this);
   }

   public boolean isSoundServerwide() {
      return this.serverWide;
   }

   public int getSoundType() {
      return this.soundType;
   }

   public int getSoundData() {
      return this.soundData;
   }

   public BlockPos getSoundPos() {
      return this.soundPos;
   }

   public String toString() {
      return "SPacketEffect{soundType=" + this.soundType + ", soundPos=" + this.soundPos + ", soundData=" + this.soundData + ", serverWide=" + this.serverWide + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
