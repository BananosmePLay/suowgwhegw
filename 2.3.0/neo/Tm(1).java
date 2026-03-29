package neo;

import java.io.IOException;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import org.apache.commons.lang3.StringUtils;

public class Tm implements Sz<Tt> {
   private String message;
   private boolean hasTargetBlock;
   @Nullable
   private BlockPos targetBlock;

   public Tm() {
   }

   public Tm(String messageIn, @Nullable BlockPos targetBlockIn, boolean hasTargetBlockIn) {
      this.message = messageIn;
      this.targetBlock = targetBlockIn;
      this.hasTargetBlock = hasTargetBlockIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.message = buf.readString(32767);
      this.hasTargetBlock = buf.readBoolean();
      boolean flag = buf.readBoolean();
      if (flag) {
         this.targetBlock = buf.readBlockPos();
      }

   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeString(StringUtils.substring(this.message, 0, 32767));
      buf.writeBoolean(this.hasTargetBlock);
      boolean flag = this.targetBlock != null;
      buf.writeBoolean(flag);
      if (flag) {
         buf.writeBlockPos(this.targetBlock);
      }

   }

   public void processPacket(Tt handler) {
      handler.processTabComplete(this);
   }

   public String getMessage() {
      return this.message;
   }

   @Nullable
   public BlockPos getTargetBlock() {
      return this.targetBlock;
   }

   public boolean hasTargetBlock() {
      return this.hasTargetBlock;
   }

   public String toString() {
      return "CPacketTabComplete{message='" + this.message + '\'' + ", hasTargetBlock=" + this.hasTargetBlock + ", targetBlock=" + this.targetBlock + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Tt)var1);
   }
}
