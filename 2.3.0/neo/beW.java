package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class beW extends bfg {
   private boolean hasSpawner;

   public beW() {
   }

   public beW(int p_i45577_1_, Random p_i45577_2_, bdy p_i45577_3_, EnumFacing p_i45577_4_) {
      super(p_i45577_1_);
      this.setCoordBaseMode(p_i45577_4_);
      this.boundingBox = p_i45577_3_;
   }

   protected void writeStructureToNBT(QQ tagCompound) {
      super.writeStructureToNBT(tagCompound);
      tagCompound.setBoolean("Mob", this.hasSpawner);
   }

   protected void readStructureFromNBT(QQ tagCompound, bfL p_143011_2_) {
      super.readStructureFromNBT(tagCompound, p_143011_2_);
      this.hasSpawner = tagCompound.getBoolean("Mob");
   }

   public void buildComponent(bdB componentIn, List<bdB> listIn, Random rand) {
      if (componentIn != null) {
         ((bfb)componentIn).strongholdPortalRoom = this;
      }

   }

   public static beW createPiece(List<bdB> p_175865_0_, Random p_175865_1_, int p_175865_2_, int p_175865_3_, int p_175865_4_, EnumFacing p_175865_5_, int p_175865_6_) {
      bdy structureboundingbox = bdy.getComponentToAddBoundingBox(p_175865_2_, p_175865_3_, p_175865_4_, -4, -1, 0, 11, 8, 16, p_175865_5_);
      return canStrongholdGoDeeper(structureboundingbox) && bdB.findIntersecting(p_175865_0_, structureboundingbox) == null ? new beW(p_175865_6_, p_175865_1_, structureboundingbox, p_175865_5_) : null;
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 10, 7, 15, false, randomIn, bfh.access$100());
      this.placeDoor(worldIn, randomIn, structureBoundingBoxIn, bff.GRATES, 4, 1, 0);
      int i = 6;
      this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 1, i, 1, 1, i, 14, false, randomIn, bfh.access$100());
      this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 9, i, 1, 9, i, 14, false, randomIn, bfh.access$100());
      this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 2, i, 1, 8, i, 2, false, randomIn, bfh.access$100());
      this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 2, i, 14, 8, i, 14, false, randomIn, bfh.access$100());
      this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 1, 1, 1, 2, 1, 4, false, randomIn, bfh.access$100());
      this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 8, 1, 1, 9, 1, 4, false, randomIn, bfh.access$100());
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 1, 1, 1, 3, Nk.FLOWING_LAVA.getDefaultState(), Nk.FLOWING_LAVA.getDefaultState(), false);
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 9, 1, 1, 9, 1, 3, Nk.FLOWING_LAVA.getDefaultState(), Nk.FLOWING_LAVA.getDefaultState(), false);
      this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 3, 1, 8, 7, 1, 12, false, randomIn, bfh.access$100());
      this.fillWithBlocks(worldIn, structureBoundingBoxIn, 4, 1, 9, 6, 1, 11, Nk.FLOWING_LAVA.getDefaultState(), Nk.FLOWING_LAVA.getDefaultState(), false);

      int i1;
      for(i1 = 3; i1 < 14; i1 += 2) {
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 3, i1, 0, 4, i1, Nk.IRON_BARS.getDefaultState(), Nk.IRON_BARS.getDefaultState(), false);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 10, 3, i1, 10, 4, i1, Nk.IRON_BARS.getDefaultState(), Nk.IRON_BARS.getDefaultState(), false);
      }

      for(i1 = 2; i1 < 9; i1 += 2) {
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, i1, 3, 15, i1, 4, 15, Nk.IRON_BARS.getDefaultState(), Nk.IRON_BARS.getDefaultState(), false);
      }

      in iblockstate3 = Nk.STONE_BRICK_STAIRS.getDefaultState().withProperty(gU.FACING, EnumFacing.NORTH);
      this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 4, 1, 5, 6, 1, 7, false, randomIn, bfh.access$100());
      this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 4, 2, 6, 6, 2, 7, false, randomIn, bfh.access$100());
      this.fillWithRandomizedBlocks(worldIn, structureBoundingBoxIn, 4, 3, 7, 6, 3, 7, false, randomIn, bfh.access$100());

      for(int k = 4; k <= 6; ++k) {
         this.setBlockState(worldIn, iblockstate3, k, 1, 4, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate3, k, 2, 5, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate3, k, 3, 6, structureBoundingBoxIn);
      }

      in iblockstate4 = Nk.END_PORTAL_FRAME.getDefaultState().withProperty(dD.FACING, EnumFacing.NORTH);
      in iblockstate = Nk.END_PORTAL_FRAME.getDefaultState().withProperty(dD.FACING, EnumFacing.SOUTH);
      in iblockstate1 = Nk.END_PORTAL_FRAME.getDefaultState().withProperty(dD.FACING, EnumFacing.EAST);
      in iblockstate2 = Nk.END_PORTAL_FRAME.getDefaultState().withProperty(dD.FACING, EnumFacing.WEST);
      boolean flag = true;
      boolean[] aboolean = new boolean[12];

      for(int l = 0; l < aboolean.length; ++l) {
         aboolean[l] = randomIn.nextFloat() > 0.9F;
         flag &= aboolean[l];
      }

      this.setBlockState(worldIn, iblockstate4.withProperty(dD.EYE, aboolean[0]), 4, 3, 8, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate4.withProperty(dD.EYE, aboolean[1]), 5, 3, 8, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate4.withProperty(dD.EYE, aboolean[2]), 6, 3, 8, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate.withProperty(dD.EYE, aboolean[3]), 4, 3, 12, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate.withProperty(dD.EYE, aboolean[4]), 5, 3, 12, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate.withProperty(dD.EYE, aboolean[5]), 6, 3, 12, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate1.withProperty(dD.EYE, aboolean[6]), 3, 3, 9, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate1.withProperty(dD.EYE, aboolean[7]), 3, 3, 10, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate1.withProperty(dD.EYE, aboolean[8]), 3, 3, 11, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate2.withProperty(dD.EYE, aboolean[9]), 7, 3, 9, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate2.withProperty(dD.EYE, aboolean[10]), 7, 3, 10, structureBoundingBoxIn);
      this.setBlockState(worldIn, iblockstate2.withProperty(dD.EYE, aboolean[11]), 7, 3, 11, structureBoundingBoxIn);
      if (flag) {
         in iblockstate5 = Nk.END_PORTAL.getDefaultState();
         this.setBlockState(worldIn, iblockstate5, 4, 3, 9, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate5, 5, 3, 9, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate5, 6, 3, 9, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate5, 4, 3, 10, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate5, 5, 3, 10, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate5, 6, 3, 10, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate5, 4, 3, 11, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate5, 5, 3, 11, structureBoundingBoxIn);
         this.setBlockState(worldIn, iblockstate5, 6, 3, 11, structureBoundingBoxIn);
      }

      if (!this.hasSpawner) {
         i = this.getYWithOffset(3);
         BlockPos blockpos = new BlockPos(this.getXWithOffset(5, 6), i, this.getZWithOffset(5, 6));
         if (structureBoundingBoxIn.isVecInside(blockpos)) {
            this.hasSpawner = true;
            worldIn.setBlockState(blockpos, Nk.MOB_SPAWNER.getDefaultState(), 2);
            Yg tileentity = worldIn.getTileEntity(blockpos);
            if (tileentity instanceof YG) {
               ((YG)tileentity).getSpawnerBaseLogic().setEntityId(Ir.getKey(KG.class));
            }
         }
      }

      return true;
   }
}
