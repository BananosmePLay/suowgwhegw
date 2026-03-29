package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class bhE extends bhZ {
   public int xCenter;
   public int zCenter;
   public byte dimension;
   public boolean trackingPosition;
   public boolean unlimitedTracking;
   public byte scale;
   public byte[] colors = new byte[16384];
   public List<bhD> playersArrayList = Lists.newArrayList();
   private final Map<ME, bhD> playersHashMap = Maps.newHashMap();
   public Map<String, bhG> mapDecorations = Maps.newLinkedHashMap();

   public bhE(String mapname) {
      super(mapname);
   }

   public void calculateMapCenter(double x, double z, int mapScale) {
      int i = 128 * (1 << mapScale);
      int j = MathHelper.floor((x + 64.0) / (double)i);
      int k = MathHelper.floor((z + 64.0) / (double)i);
      this.xCenter = j * i + i / 2 - 64;
      this.zCenter = k * i + i / 2 - 64;
   }

   public void readFromNBT(QQ nbt) {
      this.dimension = nbt.getByte("dimension");
      this.xCenter = nbt.getInteger("xCenter");
      this.zCenter = nbt.getInteger("zCenter");
      this.scale = nbt.getByte("scale");
      this.scale = (byte)MathHelper.clamp(this.scale, 0, 4);
      if (nbt.hasKey("trackingPosition", 1)) {
         this.trackingPosition = nbt.getBoolean("trackingPosition");
      } else {
         this.trackingPosition = true;
      }

      this.unlimitedTracking = nbt.getBoolean("unlimitedTracking");
      int i = nbt.getShort("width");
      int j = nbt.getShort("height");
      if (i == 128 && j == 128) {
         this.colors = nbt.getByteArray("colors");
      } else {
         byte[] abyte = nbt.getByteArray("colors");
         this.colors = new byte[16384];
         int k = (128 - i) / 2;
         int l = (128 - j) / 2;

         for(int i1 = 0; i1 < j; ++i1) {
            int j1 = i1 + l;
            if (j1 >= 0 || j1 < 128) {
               for(int k1 = 0; k1 < i; ++k1) {
                  int l1 = k1 + k;
                  if (l1 >= 0 || l1 < 128) {
                     this.colors[l1 + j1 * 128] = abyte[k1 + i1 * i];
                  }
               }
            }
         }
      }

   }

   public QQ writeToNBT(QQ compound) {
      compound.setByte("dimension", this.dimension);
      compound.setInteger("xCenter", this.xCenter);
      compound.setInteger("zCenter", this.zCenter);
      compound.setByte("scale", this.scale);
      compound.setShort("width", (short)128);
      compound.setShort("height", (short)128);
      compound.setByteArray("colors", this.colors);
      compound.setBoolean("trackingPosition", this.trackingPosition);
      compound.setBoolean("unlimitedTracking", this.unlimitedTracking);
      return compound;
   }

   public void updateVisiblePlayers(ME player, Qy mapStack) {
      if (!this.playersHashMap.containsKey(player)) {
         bhD mapdata$mapinfo = new bhD(this, player);
         this.playersHashMap.put(player, mapdata$mapinfo);
         this.playersArrayList.add(mapdata$mapinfo);
      }

      if (!player.inventory.hasItemStack(mapStack)) {
         this.mapDecorations.remove(player.getName());
      }

      for(int i = 0; i < this.playersArrayList.size(); ++i) {
         bhD mapdata$mapinfo1 = (bhD)this.playersArrayList.get(i);
         if (mapdata$mapinfo1.player.isDead || !mapdata$mapinfo1.player.inventory.hasItemStack(mapStack) && !mapStack.isOnItemFrame()) {
            this.playersHashMap.remove(mapdata$mapinfo1.player);
            this.playersArrayList.remove(mapdata$mapinfo1);
         } else if (!mapStack.isOnItemFrame() && mapdata$mapinfo1.player.dimension == this.dimension && this.trackingPosition) {
            this.updateDecorations(bhF.PLAYER, mapdata$mapinfo1.player.world, mapdata$mapinfo1.player.getName(), mapdata$mapinfo1.player.posX, mapdata$mapinfo1.player.posZ, (double)mapdata$mapinfo1.player.rotationYaw);
         }
      }

      if (mapStack.isOnItemFrame() && this.trackingPosition) {
         IZ entityitemframe = mapStack.getItemFrame();
         BlockPos blockpos = entityitemframe.getHangingPosition();
         this.updateDecorations(bhF.FRAME, player.world, "frame-" + entityitemframe.getEntityId(), (double)blockpos.getX(), (double)blockpos.getZ(), (double)(entityitemframe.facingDirection.getHorizontalIndex() * 90));
      }

      if (mapStack.hasTagCompound() && mapStack.getTagCompound().hasKey("Decorations", 9)) {
         QW nbttaglist = mapStack.getTagCompound().getTagList("Decorations", 10);

         for(int j = 0; j < nbttaglist.tagCount(); ++j) {
            QQ nbttagcompound = nbttaglist.getCompoundTagAt(j);
            if (!this.mapDecorations.containsKey(nbttagcompound.getString("id"))) {
               this.updateDecorations(bhF.byIcon(nbttagcompound.getByte("type")), player.world, nbttagcompound.getString("id"), nbttagcompound.getDouble("x"), nbttagcompound.getDouble("z"), nbttagcompound.getDouble("rot"));
            }
         }
      }

   }

   public static void addTargetDecoration(Qy map, BlockPos target, String decorationName, bhF type) {
      QW nbttaglist;
      if (map.hasTagCompound() && map.getTagCompound().hasKey("Decorations", 9)) {
         nbttaglist = map.getTagCompound().getTagList("Decorations", 10);
      } else {
         nbttaglist = new QW();
         map.setTagInfo("Decorations", nbttaglist);
      }

      QQ nbttagcompound = new QQ();
      nbttagcompound.setByte("type", type.getIcon());
      nbttagcompound.setString("id", decorationName);
      nbttagcompound.setDouble("x", (double)target.getX());
      nbttagcompound.setDouble("z", (double)target.getZ());
      nbttagcompound.setDouble("rot", 180.0);
      nbttaglist.appendTag(nbttagcompound);
      if (type.hasMapColor()) {
         QQ nbttagcompound1 = map.getOrCreateSubCompound("display");
         nbttagcompound1.setInteger("MapColor", type.getMapColor());
      }

   }

   private void updateDecorations(bhF type, bij worldIn, String decorationName, double worldX, double worldZ, double rotationIn) {
      int i = 1 << this.scale;
      float f = (float)(worldX - (double)this.xCenter) / (float)i;
      float f1 = (float)(worldZ - (double)this.zCenter) / (float)i;
      byte b0 = (byte)((int)((double)(f * 2.0F) + 0.5));
      byte b1 = (byte)((int)((double)(f1 * 2.0F) + 0.5));
      int j = true;
      byte b2;
      if (f >= -63.0F && f1 >= -63.0F && f <= 63.0F && f1 <= 63.0F) {
         rotationIn += rotationIn < 0.0 ? -8.0 : 8.0;
         b2 = (byte)((int)(rotationIn * 16.0 / 360.0));
         if (this.dimension < 0) {
            int l = (int)(worldIn.getWorldInfo().getWorldTime() / 10L);
            b2 = (byte)(l * l * 34187121 + l * 121 >> 15 & 15);
         }
      } else {
         if (type != bhF.PLAYER) {
            this.mapDecorations.remove(decorationName);
            return;
         }

         int k = true;
         if (Math.abs(f) < 320.0F && Math.abs(f1) < 320.0F) {
            type = bhF.PLAYER_OFF_MAP;
         } else {
            if (!this.unlimitedTracking) {
               this.mapDecorations.remove(decorationName);
               return;
            }

            type = bhF.PLAYER_OFF_LIMITS;
         }

         b2 = 0;
         if (f <= -63.0F) {
            b0 = -128;
         }

         if (f1 <= -63.0F) {
            b1 = -128;
         }

         if (f >= 63.0F) {
            b0 = 127;
         }

         if (f1 >= 63.0F) {
            b1 = 127;
         }
      }

      this.mapDecorations.put(decorationName, new bhG(type, b0, b1, b2));
   }

   @Nullable
   public Sz<?> getMapPacket(Qy mapStack, bij worldIn, ME player) {
      bhD mapdata$mapinfo = (bhD)this.playersHashMap.get(player);
      return mapdata$mapinfo == null ? null : mapdata$mapinfo.getPacket(mapStack);
   }

   public void updateMapData(int x, int y) {
      super.markDirty();
      Iterator var3 = this.playersArrayList.iterator();

      while(var3.hasNext()) {
         bhD mapdata$mapinfo = (bhD)var3.next();
         mapdata$mapinfo.update(x, y);
      }

   }

   public bhD getMapInfo(ME player) {
      bhD mapdata$mapinfo = (bhD)this.playersHashMap.get(player);
      if (mapdata$mapinfo == null) {
         mapdata$mapinfo = new bhD(this, player);
         this.playersHashMap.put(player, mapdata$mapinfo);
         this.playersArrayList.add(mapdata$mapinfo);
      }

      return mapdata$mapinfo;
   }
}
