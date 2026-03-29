package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;

public class gc extends ho {
   private static final Map<bij, List<gb>> toggles = Maps.newHashMap();
   private final boolean isOn;

   private boolean isBurnedOut(bij worldIn, BlockPos pos, boolean turnOff) {
      if (!toggles.containsKey(worldIn)) {
         toggles.put(worldIn, Lists.newArrayList());
      }

      List<gb> list = (List)toggles.get(worldIn);
      if (turnOff) {
         list.add(new gb(pos, worldIn.getTotalWorldTime()));
      }

      int i = 0;

      for(int j = 0; j < list.size(); ++j) {
         gb blockredstonetorch$toggle = (gb)list.get(j);
         if (blockredstonetorch$toggle.pos.equals(pos)) {
            ++i;
            if (i >= 8) {
               return true;
            }
         }
      }

      return false;
   }

   protected gc(boolean isOn) {
      this.isOn = isOn;
      this.setTickRandomly(true);
      this.setCreativeTab((EN)null);
   }

   public int tickRate(bij worldIn) {
      return 2;
   }

   public void onBlockAdded(bij worldIn, BlockPos pos, in state) {
      if (this.isOn) {
         EnumFacing[] var4 = EnumFacing.values();
         int var5 = var4.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            EnumFacing enumfacing = var4[var6];
            worldIn.notifyNeighborsOfStateChange(pos.offset(enumfacing), this, false);
         }
      }

   }

   public void breakBlock(bij worldIn, BlockPos pos, in state) {
      if (this.isOn) {
         EnumFacing[] var4 = EnumFacing.values();
         int var5 = var4.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            EnumFacing enumfacing = var4[var6];
            worldIn.notifyNeighborsOfStateChange(pos.offset(enumfacing), this, false);
         }
      }

   }

   /** @deprecated */
   public int getWeakPower(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      return this.isOn && blockState.getValue(FACING) != side ? 15 : 0;
   }

   private boolean shouldBeOff(bij worldIn, BlockPos pos, in state) {
      EnumFacing enumfacing = ((EnumFacing)state.getValue(FACING)).getOpposite();
      return worldIn.isSidePowered(pos.offset(enumfacing), enumfacing);
   }

   public void randomTick(bij worldIn, BlockPos pos, in state, Random random) {
   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      boolean flag = this.shouldBeOff(worldIn, pos, state);
      List<gb> list = (List)toggles.get(worldIn);

      while(list != null && !list.isEmpty() && worldIn.getTotalWorldTime() - ((gb)list.get(0)).time > 60L) {
         list.remove(0);
      }

      if (this.isOn) {
         if (flag) {
            worldIn.setBlockState(pos, Nk.UNLIT_REDSTONE_TORCH.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
            if (this.isBurnedOut(worldIn, pos, true)) {
               worldIn.playSound((ME)null, pos, NO.BLOCK_REDSTONE_TORCH_BURNOUT, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);

               for(int i = 0; i < 5; ++i) {
                  double d0 = (double)pos.getX() + rand.nextDouble() * 0.6 + 0.2;
                  double d1 = (double)pos.getY() + rand.nextDouble() * 0.6 + 0.2;
                  double d2 = (double)pos.getZ() + rand.nextDouble() * 0.6 + 0.2;
                  worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0, 0.0, 0.0);
               }

               worldIn.scheduleUpdate(pos, worldIn.getBlockState(pos).getBlock(), 160);
            }
         }
      } else if (!flag && !this.isBurnedOut(worldIn, pos, false)) {
         worldIn.setBlockState(pos, Nk.REDSTONE_TORCH.getDefaultState().withProperty(FACING, state.getValue(FACING)), 3);
      }

   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      if (!this.onNeighborChangeInternal(worldIn, pos, state) && this.isOn == this.shouldBeOff(worldIn, pos, state)) {
         worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
      }

   }

   /** @deprecated */
   public int getStrongPower(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      return side == EnumFacing.DOWN ? blockState.getWeakPower(blockAccess, pos, side) : 0;
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return OL.getItemFromBlock(Nk.REDSTONE_TORCH);
   }

   /** @deprecated */
   public boolean canProvidePower(in state) {
      return true;
   }

   public void randomDisplayTick(in stateIn, bij worldIn, BlockPos pos, Random rand) {
      if (this.isOn) {
         double d0 = (double)pos.getX() + 0.5 + (rand.nextDouble() - 0.5) * 0.2;
         double d1 = (double)pos.getY() + 0.7 + (rand.nextDouble() - 0.5) * 0.2;
         double d2 = (double)pos.getZ() + 0.5 + (rand.nextDouble() - 0.5) * 0.2;
         EnumFacing enumfacing = (EnumFacing)stateIn.getValue(FACING);
         if (enumfacing.getAxis().isHorizontal()) {
            EnumFacing enumfacing1 = enumfacing.getOpposite();
            double d3 = 0.27;
            d0 += 0.27 * (double)enumfacing1.getXOffset();
            d1 += 0.22;
            d2 += 0.27 * (double)enumfacing1.getZOffset();
         }

         worldIn.spawnParticle(EnumParticleTypes.REDSTONE, d0, d1, d2, 0.0, 0.0, 0.0);
      }

   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      return new Qy(Nk.REDSTONE_TORCH);
   }

   public boolean isAssociatedBlock(co other) {
      return other == Nk.UNLIT_REDSTONE_TORCH || other == Nk.REDSTONE_TORCH;
   }
}
