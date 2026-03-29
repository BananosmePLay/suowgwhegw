package neo;

import java.util.Random;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;

public class go extends cI implements hH {
   public static final hX<fk> TYPE = hX.create("type", fk.class);
   public static final hZ STAGE = hZ.create("stage", 0, 1);
   protected static final AxisAlignedBB SAPLING_AABB = new AxisAlignedBB(0.09999999403953552, 0.0, 0.09999999403953552, 0.8999999761581421, 0.800000011920929, 0.8999999761581421);

   protected go() {
      this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, fk.OAK).withProperty(STAGE, 0));
      this.setCreativeTab(EN.DECORATIONS);
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      return SAPLING_AABB;
   }

   public String getLocalizedName() {
      return I18n.translateToLocal(this.getTranslationKey() + "." + fk.OAK.getTranslationKey() + ".name");
   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      if (!worldIn.isRemote) {
         super.updateTick(worldIn, pos, state, rand);
         if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0) {
            this.grow(worldIn, pos, state, rand);
         }
      }

   }

   public void grow(bij worldIn, BlockPos pos, in state, Random rand) {
      if ((Integer)state.getValue(STAGE) == 0) {
         worldIn.setBlockState(pos, state.cycleProperty(STAGE), 4);
      } else {
         this.generateTree(worldIn, pos, state, rand);
      }

   }

   public void generateTree(bij worldIn, BlockPos pos, in state, Random rand) {
      bbE worldgenerator = rand.nextInt(10) == 0 ? new bbq(true) : new bci(true);
      int i = 0;
      int j = 0;
      boolean flag = false;
      in iblockstate;
      switch ((fk)state.getValue(TYPE)) {
         case SPRUCE:
            label68:
            for(i = 0; i >= -1; --i) {
               for(j = 0; j >= -1; --j) {
                  if (this.isTwoByTwoOfType(worldIn, pos, i, j, fk.SPRUCE)) {
                     worldgenerator = new bbS(false, rand.nextBoolean());
                     flag = true;
                     break label68;
                  }
               }
            }

            if (!flag) {
               i = 0;
               j = 0;
               worldgenerator = new bcg(true);
            }
            break;
         case BIRCH:
            worldgenerator = new bbr(true, false);
            break;
         case JUNGLE:
            iblockstate = Nk.LOG.getDefaultState().withProperty(eZ.VARIANT, fk.JUNGLE);
            in iblockstate1 = Nk.LEAVES.getDefaultState().withProperty(eW.VARIANT, fk.JUNGLE).withProperty(ew.CHECK_DECAY, false);

            label82:
            for(i = 0; i >= -1; --i) {
               for(j = 0; j >= -1; --j) {
                  if (this.isTwoByTwoOfType(worldIn, pos, i, j, fk.JUNGLE)) {
                     worldgenerator = new bbR(true, 10, 20, iblockstate, iblockstate1);
                     flag = true;
                     break label82;
                  }
               }
            }

            if (!flag) {
               i = 0;
               j = 0;
               worldgenerator = new bci(true, 4 + rand.nextInt(7), iblockstate, iblockstate1, false);
            }
            break;
         case ACACIA:
            worldgenerator = new bca(true);
            break;
         case DARK_OAK:
            label96:
            for(i = 0; i >= -1; --i) {
               for(j = 0; j >= -1; --j) {
                  if (this.isTwoByTwoOfType(worldIn, pos, i, j, fk.DARK_OAK)) {
                     worldgenerator = new bbv(true);
                     flag = true;
                     break label96;
                  }
               }
            }

            if (!flag) {
               return;
            }
         case OAK:
      }

      iblockstate = Nk.AIR.getDefaultState();
      if (flag) {
         worldIn.setBlockState(pos.add(i, 0, j), iblockstate, 4);
         worldIn.setBlockState(pos.add(i + 1, 0, j), iblockstate, 4);
         worldIn.setBlockState(pos.add(i, 0, j + 1), iblockstate, 4);
         worldIn.setBlockState(pos.add(i + 1, 0, j + 1), iblockstate, 4);
      } else {
         worldIn.setBlockState(pos, iblockstate, 4);
      }

      if (!((bbE)worldgenerator).generate(worldIn, rand, pos.add(i, 0, j))) {
         if (flag) {
            worldIn.setBlockState(pos.add(i, 0, j), state, 4);
            worldIn.setBlockState(pos.add(i + 1, 0, j), state, 4);
            worldIn.setBlockState(pos.add(i, 0, j + 1), state, 4);
            worldIn.setBlockState(pos.add(i + 1, 0, j + 1), state, 4);
         } else {
            worldIn.setBlockState(pos, state, 4);
         }
      }

   }

   private boolean isTwoByTwoOfType(bij worldIn, BlockPos pos, int p_181624_3_, int p_181624_4_, fk type) {
      return this.isTypeAt(worldIn, pos.add(p_181624_3_, 0, p_181624_4_), type) && this.isTypeAt(worldIn, pos.add(p_181624_3_ + 1, 0, p_181624_4_), type) && this.isTypeAt(worldIn, pos.add(p_181624_3_, 0, p_181624_4_ + 1), type) && this.isTypeAt(worldIn, pos.add(p_181624_3_ + 1, 0, p_181624_4_ + 1), type);
   }

   public boolean isTypeAt(bij worldIn, BlockPos pos, fk type) {
      in iblockstate = worldIn.getBlockState(pos);
      return iblockstate.getBlock() == this && iblockstate.getValue(TYPE) == type;
   }

   public int damageDropped(in state) {
      return ((fk)state.getValue(TYPE)).getMetadata();
   }

   public void getSubBlocks(EN itemIn, NonNullList<Qy> items) {
      fk[] var3 = fk.values();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         fk blockplanks$enumtype = var3[var5];
         items.add(new Qy(this, 1, blockplanks$enumtype.getMetadata()));
      }

   }

   public boolean canGrow(bij worldIn, BlockPos pos, in state, boolean isClient) {
      return true;
   }

   public boolean canUseBonemeal(bij worldIn, Random rand, BlockPos pos, in state) {
      return (double)worldIn.rand.nextFloat() < 0.45;
   }

   public void grow(bij worldIn, Random rand, BlockPos pos, in state) {
      this.grow(worldIn, pos, state, rand);
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(TYPE, fk.byMetadata(meta & 7)).withProperty(STAGE, (meta & 8) >> 3);
   }

   public int getMetaFromState(in state) {
      int i = 0;
      i |= ((fk)state.getValue(TYPE)).getMetadata();
      i |= (Integer)state.getValue(STAGE) << 3;
      return i;
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{TYPE, STAGE});
   }
}
