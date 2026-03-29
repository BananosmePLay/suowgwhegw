package neo;

import java.io.IOException;

public class UW implements Sz<Ts> {
   private long totalWorldTime;
   private long worldTime;

   public UW() {
   }

   public UW(long totalWorldTimeIn, long worldTimeIn, boolean doDaylightCycle) {
      this.totalWorldTime = totalWorldTimeIn;
      this.worldTime = worldTimeIn;
      if (!doDaylightCycle) {
         this.worldTime = -this.worldTime;
         if (this.worldTime == 0L) {
            this.worldTime = -1L;
         }
      }

   }

   public void readPacketData(SA buf) throws IOException {
      this.totalWorldTime = buf.readLong();
      this.worldTime = buf.readLong();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeLong(this.totalWorldTime);
      buf.writeLong(this.worldTime);
   }

   public void processPacket(Ts handler) {
      handler.handleTimeUpdate(this);
   }

   public long getTotalWorldTime() {
      return this.totalWorldTime;
   }

   public long getWorldTime() {
      return this.worldTime;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
