package net.minecraft.network.play.server;

import java.io.IOException;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class SPacketPlayerPosLook implements Packet<INetHandlerPlayClient> {
   private double x;
   private double y;
   private double z;
   public float yaw;
   public float pitch;
   private Set<EnumFlags> flags;
   private int teleportId;

   public SPacketPlayerPosLook() {
   }

   public SPacketPlayerPosLook(double xIn, double yIn, double zIn, float yawIn, float pitchIn, Set<EnumFlags> flagsIn, int teleportIdIn) {
      this.x = xIn;
      this.y = yIn;
      this.z = zIn;
      this.yaw = yawIn;
      this.pitch = pitchIn;
      this.flags = flagsIn;
      this.teleportId = teleportIdIn;
   }

   public void readPacketData(PacketBuffer buf) throws IOException {
      this.x = buf.readDouble();
      this.y = buf.readDouble();
      this.z = buf.readDouble();
      this.yaw = buf.readFloat();
      this.pitch = buf.readFloat();
      this.flags = SPacketPlayerPosLook.EnumFlags.unpack(buf.readUnsignedByte());
      this.teleportId = buf.readVarInt();
   }

   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeDouble(this.x);
      buf.writeDouble(this.y);
      buf.writeDouble(this.z);
      buf.writeFloat(this.yaw);
      buf.writeFloat(this.pitch);
      buf.writeByte(SPacketPlayerPosLook.EnumFlags.pack(this.flags));
      buf.writeVarInt(this.teleportId);
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handlePlayerPosLook(this);
   }

   public double getX() {
      return this.x;
   }

   public double getY() {
      return this.y;
   }

   public double getZ() {
      return this.z;
   }

   public float getYaw() {
      return this.yaw;
   }

   public float getPitch() {
      return this.pitch;
   }

   public int getTeleportId() {
      return this.teleportId;
   }

   public Set<EnumFlags> getFlags() {
      return this.flags;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(INetHandler var1) {
      this.processPacket((INetHandlerPlayClient)var1);
   }

   public static enum EnumFlags {
      X(0),
      Y(1),
      Z(2),
      Y_ROT(3),
      X_ROT(4);

      private final int bit;

      private EnumFlags(int bitIn) {
         this.bit = bitIn;
      }

      private int getMask() {
         return 1 << this.bit;
      }

      private boolean isSet(int flags) {
         return (flags & this.getMask()) == this.getMask();
      }

      public static Set<EnumFlags> unpack(int flags) {
         Set<EnumFlags> set = EnumSet.noneOf(EnumFlags.class);
         EnumFlags[] var2 = values();
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            EnumFlags spacketplayerposlook$enumflags = var2[var4];
            if (spacketplayerposlook$enumflags.isSet(flags)) {
               set.add(spacketplayerposlook$enumflags);
            }
         }

         return set;
      }

      public static int pack(Set<EnumFlags> flags) {
         int i = 0;

         EnumFlags spacketplayerposlook$enumflags;
         for(Iterator var2 = flags.iterator(); var2.hasNext(); i |= spacketplayerposlook$enumflags.getMask()) {
            spacketplayerposlook$enumflags = (EnumFlags)var2.next();
         }

         return i;
      }
   }
}
