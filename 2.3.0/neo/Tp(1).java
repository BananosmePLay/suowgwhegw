package neo;

import java.io.IOException;
import javax.annotation.Nullable;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.Vec3d;

public class Tp implements Sz<Tt> {
   private int entityId;
   private To action;
   private Vec3d hitVec;
   private EnumHand hand;

   public Tp() {
   }

   public Tp(Ig entityIn) {
      this.entityId = entityIn.getEntityId();
      this.action = To.ATTACK;
   }

   public Tp(Ig entityIn, EnumHand handIn) {
      this.entityId = entityIn.getEntityId();
      this.action = To.INTERACT;
      this.hand = handIn;
   }

   public Tp(int entityId, EnumHand handIn) {
      this.entityId = entityId;
      this.action = To.INTERACT;
      this.hand = handIn;
   }

   public Tp(int entityId) {
      this.entityId = entityId;
      this.action = To.ATTACK;
   }

   public Tp(Ig entityIn, EnumHand handIn, Vec3d hitVecIn) {
      this.entityId = entityIn.getEntityId();
      this.action = To.INTERACT_AT;
      this.hand = handIn;
      this.hitVec = hitVecIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.entityId = buf.readVarInt();
      this.action = (To)buf.readEnumValue(To.class);
      if (this.action == To.INTERACT_AT) {
         this.hitVec = new Vec3d((double)buf.readFloat(), (double)buf.readFloat(), (double)buf.readFloat());
      }

      if (this.action == To.INTERACT || this.action == To.INTERACT_AT) {
         this.hand = (EnumHand)buf.readEnumValue(EnumHand.class);
      }

   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeVarInt(this.entityId);
      buf.writeEnumValue(this.action);
      if (this.action == To.INTERACT_AT) {
         buf.writeFloat((float)this.hitVec.x);
         buf.writeFloat((float)this.hitVec.y);
         buf.writeFloat((float)this.hitVec.z);
      }

      if (this.action == To.INTERACT || this.action == To.INTERACT_AT) {
         buf.writeEnumValue(this.hand);
      }

   }

   public void processPacket(Tt handler) {
      handler.processUseEntity(this);
   }

   @Nullable
   public Ig getEntityFromWorld(bij worldIn) {
      return worldIn.getEntityByID(this.entityId);
   }

   public int getEntityId() {
      return this.entityId;
   }

   public To getAction() {
      return this.action;
   }

   public EnumHand getHand() {
      return this.hand;
   }

   public Vec3d getHitVec() {
      return this.hitVec;
   }

   public String toString() {
      return "CPacketUseEntity{entityId=" + this.entityId + ", action=" + this.action + ", hitVec=" + this.hitVec + ", hand=" + this.hand + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Tt)var1);
   }
}
