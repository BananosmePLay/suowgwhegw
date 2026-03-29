package neo;

import net.minecraft.util.math.BlockPos;

public class bqT {
   public bqT() {
   }

   public static String getTileEntityName(bfZ blockAccess, BlockPos blockPos) {
      Yg tileentity = blockAccess.getTileEntity(blockPos);
      return getTileEntityName(tileentity);
   }

   public static String getTileEntityName(Yg te) {
      if (!(te instanceof bgd)) {
         return null;
      } else {
         bgd iworldnameable = (bgd)te;
         updateTileEntityName(te);
         return !iworldnameable.hasCustomName() ? null : iworldnameable.getName();
      }
   }

   public static void updateTileEntityName(Yg te) {
      BlockPos blockpos = te.getPos();
      String s = getTileEntityRawName(te);
      if (s == null) {
         String s1 = getServerTileEntityRawName(blockpos);
         s1 = XH.normalize(s1);
         setTileEntityRawName(te, s1);
      }

   }

   public static String getServerTileEntityRawName(BlockPos blockPos) {
      Yg tileentity = bqt.getTileEntity(blockPos);
      return tileentity == null ? null : getTileEntityRawName(tileentity);
   }

   public static String getTileEntityRawName(Yg te) {
      if (te instanceof Yj) {
         return (String)bnK.getFieldValue(te, bnK.TileEntityBeacon_customName);
      } else if (te instanceof Yl) {
         return (String)bnK.getFieldValue(te, bnK.TileEntityBrewingStand_customName);
      } else if (te instanceof Yv) {
         return (String)bnK.getFieldValue(te, bnK.TileEntityEnchantmentTable_customName);
      } else if (te instanceof YA) {
         return (String)bnK.getFieldValue(te, bnK.TileEntityFurnace_customName);
      } else {
         return te instanceof YD ? (String)bnK.getFieldValue(te, bnK.TileEntityLockableLoot_customName) : null;
      }
   }

   public static boolean setTileEntityRawName(Yg te, String name) {
      if (te instanceof Yj) {
         return bnK.setFieldValue(te, bnK.TileEntityBeacon_customName, name);
      } else if (te instanceof Yl) {
         return bnK.setFieldValue(te, bnK.TileEntityBrewingStand_customName, name);
      } else if (te instanceof Yv) {
         return bnK.setFieldValue(te, bnK.TileEntityEnchantmentTable_customName, name);
      } else if (te instanceof YA) {
         return bnK.setFieldValue(te, bnK.TileEntityFurnace_customName, name);
      } else {
         return te instanceof YD ? bnK.setFieldValue(te, bnK.TileEntityLockableLoot_customName, name) : false;
      }
   }
}
