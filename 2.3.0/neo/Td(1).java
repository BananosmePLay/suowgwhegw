package neo;

import java.io.IOException;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;

public class Td implements Sz<Tt> {
   private BlockPos position;
   private EnumFacing placedBlockDirection;
   private EnumHand hand;
   private float facingX;
   private float facingY;
   private float facingZ;

   public Td() {
   }

   public Td(BlockPos posIn, EnumFacing placedBlockDirectionIn, EnumHand handIn, float facingXIn, float facingYIn, float facingZIn) {
      this.position = posIn;
      this.placedBlockDirection = placedBlockDirectionIn;
      this.hand = handIn;
      this.facingX = facingXIn;
      this.facingY = facingYIn;
      this.facingZ = facingZIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.position = buf.readBlockPos();
      this.placedBlockDirection = (EnumFacing)buf.readEnumValue(EnumFacing.class);
      this.hand = (EnumHand)buf.readEnumValue(EnumHand.class);
      this.facingX = buf.readFloat();
      this.facingY = buf.readFloat();
      this.facingZ = buf.readFloat();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeBlockPos(this.position);
      buf.writeEnumValue(this.placedBlockDirection);
      buf.writeEnumValue(this.hand);
      buf.writeFloat(this.facingX);
      buf.writeFloat(this.facingY);
      buf.writeFloat(this.facingZ);
   }

   public void processPacket(Tt handler) {
      handler.processTryUseItemOnBlock(this);
   }

   public BlockPos getPos() {
      return this.position;
   }

   public EnumFacing getDirection() {
      return this.placedBlockDirection;
   }

   public EnumHand getHand() {
      return this.hand;
   }

   public float getFacingX() {
      return this.facingX;
   }

   public float getFacingY() {
      return this.facingY;
   }

   public float getFacingZ() {
      return this.facingZ;
   }

   public String toString() {
      return "CPacketPlayerTryUseItemOnBlock{position=" + this.position + ", placedBlockDirection=" + this.placedBlockDirection + ", hand=" + this.hand + ", facingX=" + this.facingX + ", facingY=" + this.facingY + ", facingZ=" + this.facingZ + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Tt)var1);
   }
}
