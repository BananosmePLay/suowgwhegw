package neo;

import java.io.IOException;
import java.util.Arrays;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;

public class Tn implements Sz<Tt> {
   private BlockPos pos;
   private String[] lines;

   public Tn() {
   }

   public Tn(BlockPos posIn, ITextComponent[] linesIn) {
      this.pos = posIn;
      this.lines = new String[]{linesIn[0].getUnformattedText(), linesIn[1].getUnformattedText(), linesIn[2].getUnformattedText(), linesIn[3].getUnformattedText()};
   }

   public void readPacketData(SA buf) throws IOException {
      this.pos = buf.readBlockPos();
      this.lines = new String[4];

      for(int i = 0; i < 4; ++i) {
         this.lines[i] = buf.readString(384);
      }

   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeBlockPos(this.pos);

      for(int i = 0; i < 4; ++i) {
         buf.writeString(this.lines[i]);
      }

   }

   public void processPacket(Tt handler) {
      handler.processUpdateSign(this);
   }

   public BlockPos getPosition() {
      return this.pos;
   }

   public String[] getLines() {
      return this.lines;
   }

   public String toString() {
      return "CPacketUpdateSign{pos=" + this.pos + ", lines=" + Arrays.toString(this.lines) + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Tt)var1);
   }
}
