package neo;

import java.io.IOException;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class Tb implements Sz<Tt> {
   private BlockPos position;
   private EnumFacing facing;
   private Ta action;

   public Tb() {
   }

   public Tb(Ta actionIn, BlockPos posIn, EnumFacing facingIn) {
      this.action = actionIn;
      this.position = posIn;
      this.facing = facingIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.action = (Ta)buf.readEnumValue(Ta.class);
      this.position = buf.readBlockPos();
      this.facing = EnumFacing.byIndex(buf.readUnsignedByte());
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeEnumValue(this.action);
      buf.writeBlockPos(this.position);
      buf.writeByte(this.facing.getIndex());
   }

   public void processPacket(Tt handler) {
      handler.processPlayerDigging(this);
   }

   public BlockPos getPosition() {
      return this.position;
   }

   public EnumFacing getFacing() {
      return this.facing;
   }

   public Ta getAction() {
      return this.action;
   }

   public String toString() {
      return "CPacketPlayerDigging{position=" + this.position + ", facing=" + this.facing + ", action=" + this.action + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Tt)var1);
   }
}
