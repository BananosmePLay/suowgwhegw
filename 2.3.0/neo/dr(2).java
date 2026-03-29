package neo;

import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class dr extends cI implements hH {
   public static final hX<dq> VARIANT = hX.create("variant", dq.class);
   public static final hX<dp> HALF = hX.create("half", dp.class);
   public static final hX<EnumFacing> FACING;

   public dr() {
      super(hM.VINE);
      this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, dq.SUNFLOWER).withProperty(HALF, dp.LOWER).withProperty(FACING, EnumFacing.NORTH));
      this.setHardness(0.0F);
      this.setSoundType(ia.PLANT);
      this.setTranslationKey("doublePlant");
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      return FULL_BLOCK_AABB;
   }

   private dq getType(bfZ blockAccess, BlockPos pos, in state) {
      if (state.getBlock() == this) {
         state = state.getActualState(blockAccess, pos);
         return (dq)state.getValue(VARIANT);
      } else {
         return dq.FERN;
      }
   }

   public boolean canPlaceBlockAt(bij worldIn, BlockPos pos) {
      return super.canPlaceBlockAt(worldIn, pos) && worldIn.isAirBlock(pos.up());
   }

   public boolean isReplaceable(bfZ worldIn, BlockPos pos) {
      in iblockstate = worldIn.getBlockState(pos);
      if (iblockstate.getBlock() != this) {
         return true;
      } else {
         dq blockdoubleplant$enumplanttype = (dq)iblockstate.getActualState(worldIn, pos).getValue(VARIANT);
         return blockdoubleplant$enumplanttype == dq.FERN || blockdoubleplant$enumplanttype == dq.GRASS;
      }
   }

   protected void checkAndDropBlock(bij worldIn, BlockPos pos, in state) {
      if (!this.canBlockStay(worldIn, pos, state)) {
         boolean flag = state.getValue(HALF) == dp.UPPER;
         BlockPos blockpos = flag ? pos : pos.up();
         BlockPos blockpos1 = flag ? pos.down() : pos;
         co block = flag ? this : worldIn.getBlockState(blockpos).getBlock();
         co block1 = flag ? worldIn.getBlockState(blockpos1).getBlock() : this;
         if (block == this) {
            worldIn.setBlockState(blockpos, Nk.AIR.getDefaultState(), 2);
         }

         if (block1 == this) {
            worldIn.setBlockState(blockpos1, Nk.AIR.getDefaultState(), 3);
            if (!flag) {
               this.dropBlockAsItem(worldIn, blockpos1, state, 0);
            }
         }
      }

   }

   public boolean canBlockStay(bij worldIn, BlockPos pos, in state) {
      if (state.getValue(HALF) == dp.UPPER) {
         return worldIn.getBlockState(pos.down()).getBlock() == this;
      } else {
         in iblockstate = worldIn.getBlockState(pos.up());
         return iblockstate.getBlock() == this && super.canBlockStay(worldIn, pos, iblockstate);
      }
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      if (state.getValue(HALF) == dp.UPPER) {
         return NK.AIR;
      } else {
         dq blockdoubleplant$enumplanttype = (dq)state.getValue(VARIANT);
         if (blockdoubleplant$enumplanttype == dq.FERN) {
            return NK.AIR;
         } else if (blockdoubleplant$enumplanttype == dq.GRASS) {
            return rand.nextInt(8) == 0 ? NK.WHEAT_SEEDS : NK.AIR;
         } else {
            return super.getItemDropped(state, rand, fortune);
         }
      }
   }

   public int damageDropped(in state) {
      return state.getValue(HALF) != dp.UPPER && state.getValue(VARIANT) != dq.GRASS ? ((dq)state.getValue(VARIANT)).getMeta() : 0;
   }

   public void placeAt(bij worldIn, BlockPos lowerPos, dq variant, int flags) {
      worldIn.setBlockState(lowerPos, this.getDefaultState().withProperty(HALF, dp.LOWER).withProperty(VARIANT, variant), flags);
      worldIn.setBlockState(lowerPos.up(), this.getDefaultState().withProperty(HALF, dp.UPPER), flags);
   }

   public void onBlockPlacedBy(bij worldIn, BlockPos pos, in state, Iw placer, Qy stack) {
      worldIn.setBlockState(pos.up(), this.getDefaultState().withProperty(HALF, dp.UPPER), 2);
   }

   public void harvestBlock(bij worldIn, ME player, BlockPos pos, in state, @Nullable Yg te, Qy stack) {
      if (worldIn.isRemote || stack.getItem() != NK.SHEARS || state.getValue(HALF) != dp.LOWER || !this.onHarvest(worldIn, pos, state, player)) {
         super.harvestBlock(worldIn, player, pos, state, te, stack);
      }

   }

   public void onBlockHarvested(bij worldIn, BlockPos pos, in state, ME player) {
      if (state.getValue(HALF) == dp.UPPER) {
         if (worldIn.getBlockState(pos.down()).getBlock() == this) {
            if (player.capabilities.isCreativeMode) {
               worldIn.setBlockToAir(pos.down());
            } else {
               in iblockstate = worldIn.getBlockState(pos.down());
               dq blockdoubleplant$enumplanttype = (dq)iblockstate.getValue(VARIANT);
               if (blockdoubleplant$enumplanttype != dq.FERN && blockdoubleplant$enumplanttype != dq.GRASS) {
                  worldIn.destroyBlock(pos.down(), true);
               } else if (worldIn.isRemote) {
                  worldIn.setBlockToAir(pos.down());
               } else if (!player.getHeldItemMainhand().isEmpty() && player.getHeldItemMainhand().getItem() == NK.SHEARS) {
                  this.onHarvest(worldIn, pos, iblockstate, player);
                  worldIn.setBlockToAir(pos.down());
               } else {
                  worldIn.destroyBlock(pos.down(), true);
               }
            }
         }
      } else if (worldIn.getBlockState(pos.up()).getBlock() == this) {
         worldIn.setBlockState(pos.up(), Nk.AIR.getDefaultState(), 2);
      }

      super.onBlockHarvested(worldIn, pos, state, player);
   }

   private boolean onHarvest(bij worldIn, BlockPos pos, in state, ME player) {
      dq blockdoubleplant$enumplanttype = (dq)state.getValue(VARIANT);
      if (blockdoubleplant$enumplanttype != dq.FERN && blockdoubleplant$enumplanttype != dq.GRASS) {
         return false;
      } else {
         player.addStat(XV.getBlockStats(this));
         int i = (blockdoubleplant$enumplanttype == dq.GRASS ? hj.GRASS : hj.FERN).getMeta();
         spawnAsEntity(worldIn, pos, new Qy(Nk.TALLGRASS, 2, i));
         return true;
      }
   }

   public void getSubBlocks(EN itemIn, NonNullList<Qy> items) {
      dq[] var3 = dq.values();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         dq blockdoubleplant$enumplanttype = var3[var5];
         items.add(new Qy(this, 1, blockdoubleplant$enumplanttype.getMeta()));
      }

   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      return new Qy(this, 1, this.getType(worldIn, pos, state).getMeta());
   }

   public boolean canGrow(bij worldIn, BlockPos pos, in state, boolean isClient) {
      dq blockdoubleplant$enumplanttype = this.getType(worldIn, pos, state);
      return blockdoubleplant$enumplanttype != dq.GRASS && blockdoubleplant$enumplanttype != dq.FERN;
   }

   public boolean canUseBonemeal(bij worldIn, Random rand, BlockPos pos, in state) {
      return true;
   }

   public void grow(bij worldIn, Random rand, BlockPos pos, in state) {
      spawnAsEntity(worldIn, pos, new Qy(this, 1, this.getType(worldIn, pos, state).getMeta()));
   }

   public in getStateFromMeta(int meta) {
      return (meta & 8) > 0 ? this.getDefaultState().withProperty(HALF, dp.UPPER) : this.getDefaultState().withProperty(HALF, dp.LOWER).withProperty(VARIANT, dq.byMetadata(meta & 7));
   }

   public in getActualState(in state, bfZ worldIn, BlockPos pos) {
      if (state.getValue(HALF) == dp.UPPER) {
         in iblockstate = worldIn.getBlockState(pos.down());
         if (iblockstate.getBlock() == this) {
            state = state.withProperty(VARIANT, iblockstate.getValue(VARIANT));
         }
      }

      return state;
   }

   public int getMetaFromState(in state) {
      return state.getValue(HALF) == dp.UPPER ? 8 | ((EnumFacing)state.getValue(FACING)).getHorizontalIndex() : ((dq)state.getValue(VARIANT)).getMeta();
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{HALF, VARIANT, FACING});
   }

   public cn getOffsetType() {
      return cn.XZ;
   }

   static {
      FACING = en.FACING;
   }
}
