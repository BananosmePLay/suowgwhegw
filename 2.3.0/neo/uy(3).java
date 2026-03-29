package neo;

import javax.annotation.Nullable;
import net.minecraft.util.ObjectIntIdentityMap;
import net.minecraft.util.math.BlockPos;

public class uy {
   private final ObjectIntIdentityMap<uz> mapBlockColors = new ObjectIntIdentityMap(32);

   public uy() {
   }

   public static uy init() {
      final uy blockcolors = new uy();
      blockcolors.registerBlockColorHandler(new uz() {
         public int colorMultiplier(in state, @Nullable bfZ worldIn, @Nullable BlockPos pos, int tintIndex) {
            dq blockdoubleplant$enumplanttype = (dq)state.getValue(dr.VARIANT);
            return worldIn != null && pos != null && (blockdoubleplant$enumplanttype == dq.GRASS || blockdoubleplant$enumplanttype == dq.FERN) ? Zq.getGrassColorAtPos(worldIn, state.getValue(dr.HALF) == dp.UPPER ? pos.down() : pos) : -1;
         }
      }, Nk.DOUBLE_PLANT);
      blockcolors.registerBlockColorHandler(new uz() {
         public int colorMultiplier(in state, @Nullable bfZ worldIn, @Nullable BlockPos pos, int tintIndex) {
            if (worldIn != null && pos != null) {
               Yg tileentity = worldIn.getTileEntity(pos);
               if (tileentity instanceof Yz) {
                  OL item = ((Yz)tileentity).getFlowerPotItem();
                  in iblockstate = co.getBlockFromItem(item).getDefaultState();
                  return blockcolors.colorMultiplier(iblockstate, worldIn, pos, tintIndex);
               } else {
                  return -1;
               }
            } else {
               return -1;
            }
         }
      }, Nk.FLOWER_POT);
      blockcolors.registerBlockColorHandler(new uz() {
         public int colorMultiplier(in state, @Nullable bfZ worldIn, @Nullable BlockPos pos, int tintIndex) {
            return worldIn != null && pos != null ? Zq.getGrassColorAtPos(worldIn, pos) : baK.getGrassColor(0.5, 1.0);
         }
      }, Nk.GRASS);
      blockcolors.registerBlockColorHandler(new uz() {
         public int colorMultiplier(in state, @Nullable bfZ worldIn, @Nullable BlockPos pos, int tintIndex) {
            fk blockplanks$enumtype = (fk)state.getValue(eW.VARIANT);
            if (blockplanks$enumtype == fk.SPRUCE) {
               return baJ.getFoliageColorPine();
            } else if (blockplanks$enumtype == fk.BIRCH) {
               return baJ.getFoliageColorBirch();
            } else {
               return worldIn != null && pos != null ? Zq.getFoliageColorAtPos(worldIn, pos) : baJ.getFoliageColorBasic();
            }
         }
      }, Nk.LEAVES);
      blockcolors.registerBlockColorHandler(new uz() {
         public int colorMultiplier(in state, @Nullable bfZ worldIn, @Nullable BlockPos pos, int tintIndex) {
            return worldIn != null && pos != null ? Zq.getFoliageColorAtPos(worldIn, pos) : baJ.getFoliageColorBasic();
         }
      }, Nk.LEAVES2);
      blockcolors.registerBlockColorHandler(new uz() {
         public int colorMultiplier(in state, @Nullable bfZ worldIn, @Nullable BlockPos pos, int tintIndex) {
            return worldIn != null && pos != null ? Zq.getWaterColorAtPos(worldIn, pos) : -1;
         }
      }, Nk.WATER, Nk.FLOWING_WATER);
      blockcolors.registerBlockColorHandler(new uz() {
         public int colorMultiplier(in state, @Nullable bfZ worldIn, @Nullable BlockPos pos, int tintIndex) {
            return gf.colorMultiplier((Integer)state.getValue(gf.POWER));
         }
      }, Nk.REDSTONE_WIRE);
      blockcolors.registerBlockColorHandler(new uz() {
         public int colorMultiplier(in state, @Nullable bfZ worldIn, @Nullable BlockPos pos, int tintIndex) {
            return worldIn != null && pos != null ? Zq.getGrassColorAtPos(worldIn, pos) : -1;
         }
      }, Nk.REEDS);
      blockcolors.registerBlockColorHandler(new uz() {
         public int colorMultiplier(in state, @Nullable bfZ worldIn, @Nullable BlockPos pos, int tintIndex) {
            int i = (Integer)state.getValue(gX.AGE);
            int j = i * 32;
            int k = 255 - i * 8;
            int l = i * 4;
            return j << 16 | k << 8 | l;
         }
      }, Nk.MELON_STEM, Nk.PUMPKIN_STEM);
      blockcolors.registerBlockColorHandler(new uz() {
         public int colorMultiplier(in state, @Nullable bfZ worldIn, @Nullable BlockPos pos, int tintIndex) {
            if (worldIn != null && pos != null) {
               return Zq.getGrassColorAtPos(worldIn, pos);
            } else {
               return state.getValue(hk.TYPE) == hj.DEAD_BUSH ? 16777215 : baK.getGrassColor(0.5, 1.0);
            }
         }
      }, Nk.TALLGRASS);
      blockcolors.registerBlockColorHandler(new uz() {
         public int colorMultiplier(in state, @Nullable bfZ worldIn, @Nullable BlockPos pos, int tintIndex) {
            return worldIn != null && pos != null ? Zq.getFoliageColorAtPos(worldIn, pos) : baJ.getFoliageColorBasic();
         }
      }, Nk.VINE);
      blockcolors.registerBlockColorHandler(new uz() {
         public int colorMultiplier(in state, @Nullable bfZ worldIn, @Nullable BlockPos pos, int tintIndex) {
            return worldIn != null && pos != null ? 2129968 : 7455580;
         }
      }, Nk.WATERLILY);
      return blockcolors;
   }

   public int getColor(in state, bij p_189991_2_, BlockPos p_189991_3_) {
      uz iblockcolor = (uz)this.mapBlockColors.getByValue(co.getIdFromBlock(state.getBlock()));
      if (iblockcolor != null) {
         return iblockcolor.colorMultiplier(state, (bfZ)null, (BlockPos)null, 0);
      } else {
         hK mapcolor = state.getMapColor(p_189991_2_, p_189991_3_);
         return mapcolor != null ? mapcolor.colorValue : -1;
      }
   }

   public int colorMultiplier(in state, @Nullable bfZ blockAccess, @Nullable BlockPos pos, int tintIndex) {
      uz iblockcolor = (uz)this.mapBlockColors.getByValue(co.getIdFromBlock(state.getBlock()));
      return iblockcolor == null ? -1 : iblockcolor.colorMultiplier(state, blockAccess, pos, tintIndex);
   }

   public void registerBlockColorHandler(uz blockColor, co... blocksIn) {
      co[] var3 = blocksIn;
      int var4 = blocksIn.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         co block = var3[var5];
         this.mapBlockColors.put(blockColor, co.getIdFromBlock(block));
      }

   }
}
