package neo;

import java.io.IOException;
import java.util.Collection;

public class Uk implements Sz<Ts> {
   private int mapId;
   private byte mapScale;
   private boolean trackingPosition;
   private bhG[] icons;
   private int minX;
   private int minZ;
   private int columns;
   private int rows;
   private byte[] mapDataBytes;

   public Uk() {
   }

   public Uk(int mapIdIn, byte mapScaleIn, boolean trackingPositionIn, Collection<bhG> iconsIn, byte[] p_i46937_5_, int minXIn, int minZIn, int columnsIn, int rowsIn) {
      this.mapId = mapIdIn;
      this.mapScale = mapScaleIn;
      this.trackingPosition = trackingPositionIn;
      this.icons = (bhG[])((bhG[])iconsIn.toArray(new bhG[iconsIn.size()]));
      this.minX = minXIn;
      this.minZ = minZIn;
      this.columns = columnsIn;
      this.rows = rowsIn;
      this.mapDataBytes = new byte[columnsIn * rowsIn];

      for(int i = 0; i < columnsIn; ++i) {
         for(int j = 0; j < rowsIn; ++j) {
            this.mapDataBytes[i + j * columnsIn] = p_i46937_5_[minXIn + i + (minZIn + j) * 128];
         }
      }

   }

   public void readPacketData(SA buf) throws IOException {
      this.mapId = buf.readVarInt();
      this.mapScale = buf.readByte();
      this.trackingPosition = buf.readBoolean();
      this.icons = new bhG[buf.readVarInt()];

      for(int i = 0; i < this.icons.length; ++i) {
         short short1 = (short)buf.readByte();
         this.icons[i] = new bhG(bhF.byIcon((byte)(short1 >> 4 & 15)), buf.readByte(), buf.readByte(), (byte)(short1 & 15));
      }

      this.columns = buf.readUnsignedByte();
      if (this.columns > 0) {
         this.rows = buf.readUnsignedByte();
         this.minX = buf.readUnsignedByte();
         this.minZ = buf.readUnsignedByte();
         this.mapDataBytes = buf.readByteArray();
      }

   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeVarInt(this.mapId);
      buf.writeByte(this.mapScale);
      buf.writeBoolean(this.trackingPosition);
      buf.writeVarInt(this.icons.length);
      bhG[] var2 = this.icons;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         bhG mapdecoration = var2[var4];
         buf.writeByte((mapdecoration.getImage() & 15) << 4 | mapdecoration.getRotation() & 15);
         buf.writeByte(mapdecoration.getX());
         buf.writeByte(mapdecoration.getY());
      }

      buf.writeByte(this.columns);
      if (this.columns > 0) {
         buf.writeByte(this.rows);
         buf.writeByte(this.minX);
         buf.writeByte(this.minZ);
         buf.writeByteArray(this.mapDataBytes);
      }

   }

   public void processPacket(Ts handler) {
      handler.handleMaps(this);
   }

   public int getMapId() {
      return this.mapId;
   }

   public void setMapdataTo(bhE mapdataIn) {
      mapdataIn.scale = this.mapScale;
      mapdataIn.trackingPosition = this.trackingPosition;
      mapdataIn.mapDecorations.clear();

      int j;
      for(j = 0; j < this.icons.length; ++j) {
         bhG mapdecoration = this.icons[j];
         mapdataIn.mapDecorations.put("icon-" + j, mapdecoration);
      }

      for(j = 0; j < this.columns; ++j) {
         for(int k = 0; k < this.rows; ++k) {
            mapdataIn.colors[this.minX + j + (this.minZ + k) * 128] = this.mapDataBytes[j + k * this.columns];
         }
      }

   }

   public byte[] getMapDataBytes() {
      return this.mapDataBytes;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
