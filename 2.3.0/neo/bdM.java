package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class bdM extends bdO {
   private boolean hasRails;
   private boolean hasSpiders;
   private boolean spawnerPlaced;
   private int sectionCount;

   public bdM() {
   }

   protected void writeStructureToNBT(QQ tagCompound) {
      super.writeStructureToNBT(tagCompound);
      tagCompound.setBoolean("hr", this.hasRails);
      tagCompound.setBoolean("sc", this.hasSpiders);
      tagCompound.setBoolean("hps", this.spawnerPlaced);
      tagCompound.setInteger("Num", this.sectionCount);
   }

   protected void readStructureFromNBT(QQ tagCompound, bfL p_143011_2_) {
      super.readStructureFromNBT(tagCompound, p_143011_2_);
      this.hasRails = tagCompound.getBoolean("hr");
      this.hasSpiders = tagCompound.getBoolean("sc");
      this.spawnerPlaced = tagCompound.getBoolean("hps");
      this.sectionCount = tagCompound.getInteger("Num");
   }

   public bdM(int p_i47140_1_, Random p_i47140_2_, bdy p_i47140_3_, EnumFacing p_i47140_4_, bdg p_i47140_5_) {
      super(p_i47140_1_, p_i47140_5_);
      this.setCoordBaseMode(p_i47140_4_);
      this.boundingBox = p_i47140_3_;
      this.hasRails = p_i47140_2_.nextInt(3) == 0;
      this.hasSpiders = !this.hasRails && p_i47140_2_.nextInt(23) == 0;
      if (this.getCoordBaseMode().getAxis() == EnumFacing.Axis.Z) {
         this.sectionCount = p_i47140_3_.getZSize() / 5;
      } else {
         this.sectionCount = p_i47140_3_.getXSize() / 5;
      }

   }

   public static bdy findCorridorSize(List<bdB> p_175814_0_, Random rand, int x, int y, int z, EnumFacing facing) {
      bdy structureboundingbox = new bdy(x, y, z, x, y + 2, z);

      int i;
      for(i = rand.nextInt(3) + 2; i > 0; --i) {
         int j = i * 5;
         switch (facing) {
            case NORTH:
            default:
               structureboundingbox.maxX = x + 2;
               structureboundingbox.minZ = z - (j - 1);
               break;
            case SOUTH:
               structureboundingbox.maxX = x + 2;
               structureboundingbox.maxZ = z + (j - 1);
               break;
            case WEST:
               structureboundingbox.minX = x - (j - 1);
               structureboundingbox.maxZ = z + 2;
               break;
            case EAST:
               structureboundingbox.maxX = x + (j - 1);
               structureboundingbox.maxZ = z + 2;
         }

         if (bdB.findIntersecting(p_175814_0_, structureboundingbox) == null) {
            break;
         }
      }

      return i > 0 ? structureboundingbox : null;
   }

   public void buildComponent(bdB componentIn, List<bdB> listIn, Random rand) {
      int i = this.getComponentType();
      int j = rand.nextInt(4);
      EnumFacing enumfacing = this.getCoordBaseMode();
      if (enumfacing != null) {
         switch (enumfacing) {
            case NORTH:
            default:
               if (j <= 1) {
                  bdR.access$000(componentIn, listIn, rand, this.boundingBox.minX, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ - 1, enumfacing, i);
               } else if (j == 2) {
                  bdR.access$000(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ, EnumFacing.WEST, i);
               } else {
                  bdR.access$000(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ, EnumFacing.EAST, i);
               }
               break;
            case SOUTH:
               if (j <= 1) {
                  bdR.access$000(componentIn, listIn, rand, this.boundingBox.minX, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.maxZ + 1, enumfacing, i);
               } else if (j == 2) {
                  bdR.access$000(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.maxZ - 3, EnumFacing.WEST, i);
               } else {
                  bdR.access$000(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.maxZ - 3, EnumFacing.EAST, i);
               }
               break;
            case WEST:
               if (j <= 1) {
                  bdR.access$000(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ, enumfacing, i);
               } else if (j == 2) {
                  bdR.access$000(componentIn, listIn, rand, this.boundingBox.minX, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ - 1, EnumFacing.NORTH, i);
               } else {
                  bdR.access$000(componentIn, listIn, rand, this.boundingBox.minX, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.maxZ + 1, EnumFacing.SOUTH, i);
               }
               break;
            case EAST:
               if (j <= 1) {
                  bdR.access$000(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ, enumfacing, i);
               } else if (j == 2) {
                  bdR.access$000(componentIn, listIn, rand, this.boundingBox.maxX - 3, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.minZ - 1, EnumFacing.NORTH, i);
               } else {
                  bdR.access$000(componentIn, listIn, rand, this.boundingBox.maxX - 3, this.boundingBox.minY - 1 + rand.nextInt(3), this.boundingBox.maxZ + 1, EnumFacing.SOUTH, i);
               }
         }
      }

      if (i < 8) {
         int k;
         int l;
         if (enumfacing != EnumFacing.NORTH && enumfacing != EnumFacing.SOUTH) {
            for(k = this.boundingBox.minX + 3; k + 3 <= this.boundingBox.maxX; k += 5) {
               l = rand.nextInt(5);
               if (l == 0) {
                  bdR.access$000(componentIn, listIn, rand, k, this.boundingBox.minY, this.boundingBox.minZ - 1, EnumFacing.NORTH, i + 1);
               } else if (l == 1) {
                  bdR.access$000(componentIn, listIn, rand, k, this.boundingBox.minY, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, i + 1);
               }
            }
         } else {
            for(k = this.boundingBox.minZ + 3; k + 3 <= this.boundingBox.maxZ; k += 5) {
               l = rand.nextInt(5);
               if (l == 0) {
                  bdR.access$000(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY, k, EnumFacing.WEST, i + 1);
               } else if (l == 1) {
                  bdR.access$000(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY, k, EnumFacing.EAST, i + 1);
               }
            }
         }
      }

   }

   protected boolean generateChest(bij worldIn, bdy structurebb, Random randomIn, int x, int y, int z, ResourceLocation loot) {
      BlockPos blockpos = new BlockPos(this.getXWithOffset(x, z), this.getYWithOffset(y), this.getZWithOffset(x, z));
      if (structurebb.isVecInside(blockpos) && worldIn.getBlockState(blockpos).getMaterial() == hM.AIR && worldIn.getBlockState(blockpos.down()).getMaterial() != hM.AIR) {
         in iblockstate = Nk.RAIL.getDefaultState().withProperty(fG.SHAPE, randomIn.nextBoolean() ? fI.NORTH_SOUTH : fI.EAST_WEST);
         this.setBlockState(worldIn, iblockstate, x, y, z, structurebb);
         Jd entityminecartchest = new Jd(worldIn, (double)((float)blockpos.getX() + 0.5F), (double)((float)blockpos.getY() + 0.5F), (double)((float)blockpos.getZ() + 0.5F));
         entityminecartchest.setLootTable(loot, randomIn.nextLong());
         worldIn.spawnEntity(entityminecartchest);
         return true;
      } else {
         return false;
      }
   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      if (this.isLiquidInStructureBoundingBox(worldIn, structureBoundingBoxIn)) {
         return false;
      } else {
         int i = false;
         int j = true;
         int k = false;
         int l = true;
         int i1 = this.sectionCount * 5 - 1;
         in iblockstate = this.getPlanksBlock();
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 2, 1, i1, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
         this.generateMaybeBox(worldIn, structureBoundingBoxIn, randomIn, 0.8F, 0, 2, 0, 2, 2, i1, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false, 0);
         if (this.hasSpiders) {
            this.generateMaybeBox(worldIn, structureBoundingBoxIn, randomIn, 0.6F, 0, 0, 0, 2, 1, i1, Nk.WEB.getDefaultState(), Nk.AIR.getDefaultState(), false, 8);
         }

         int l2;
         int j3;
         for(l2 = 0; l2 < this.sectionCount; ++l2) {
            j3 = 2 + l2 * 5;
            this.placeSupport(worldIn, structureBoundingBoxIn, 0, 0, j3, 2, 2, randomIn);
            this.placeCobWeb(worldIn, structureBoundingBoxIn, randomIn, 0.1F, 0, 2, j3 - 1);
            this.placeCobWeb(worldIn, structureBoundingBoxIn, randomIn, 0.1F, 2, 2, j3 - 1);
            this.placeCobWeb(worldIn, structureBoundingBoxIn, randomIn, 0.1F, 0, 2, j3 + 1);
            this.placeCobWeb(worldIn, structureBoundingBoxIn, randomIn, 0.1F, 2, 2, j3 + 1);
            this.placeCobWeb(worldIn, structureBoundingBoxIn, randomIn, 0.05F, 0, 2, j3 - 2);
            this.placeCobWeb(worldIn, structureBoundingBoxIn, randomIn, 0.05F, 2, 2, j3 - 2);
            this.placeCobWeb(worldIn, structureBoundingBoxIn, randomIn, 0.05F, 0, 2, j3 + 2);
            this.placeCobWeb(worldIn, structureBoundingBoxIn, randomIn, 0.05F, 2, 2, j3 + 2);
            if (randomIn.nextInt(100) == 0) {
               this.generateChest(worldIn, structureBoundingBoxIn, randomIn, 2, 0, j3 - 1, bhq.CHESTS_ABANDONED_MINESHAFT);
            }

            if (randomIn.nextInt(100) == 0) {
               this.generateChest(worldIn, structureBoundingBoxIn, randomIn, 0, 0, j3 + 1, bhq.CHESTS_ABANDONED_MINESHAFT);
            }

            if (this.hasSpiders && !this.spawnerPlaced) {
               int l1 = this.getYWithOffset(0);
               int i2 = j3 - 1 + randomIn.nextInt(3);
               int j2 = this.getXWithOffset(1, i2);
               int k2 = this.getZWithOffset(1, i2);
               BlockPos blockpos = new BlockPos(j2, l1, k2);
               if (structureBoundingBoxIn.isVecInside(blockpos) && this.getSkyBrightness(worldIn, 1, 0, i2, structureBoundingBoxIn) < 8) {
                  this.spawnerPlaced = true;
                  worldIn.setBlockState(blockpos, Nk.MOB_SPAWNER.getDefaultState(), 2);
                  Yg tileentity = worldIn.getTileEntity(blockpos);
                  if (tileentity instanceof YG) {
                     ((YG)tileentity).getSpawnerBaseLogic().setEntityId(Ir.getKey(JA.class));
                  }
               }
            }
         }

         for(l2 = 0; l2 <= 2; ++l2) {
            for(j3 = 0; j3 <= i1; ++j3) {
               int k3 = true;
               in iblockstate3 = this.getBlockStateFromPos(worldIn, l2, -1, j3, structureBoundingBoxIn);
               if (iblockstate3.getMaterial() == hM.AIR && this.getSkyBrightness(worldIn, l2, -1, j3, structureBoundingBoxIn) < 8) {
                  int l3 = true;
                  this.setBlockState(worldIn, iblockstate, l2, -1, j3, structureBoundingBoxIn);
               }
            }
         }

         if (this.hasRails) {
            in iblockstate1 = Nk.RAIL.getDefaultState().withProperty(fG.SHAPE, fI.NORTH_SOUTH);

            for(j3 = 0; j3 <= i1; ++j3) {
               in iblockstate2 = this.getBlockStateFromPos(worldIn, 1, -1, j3, structureBoundingBoxIn);
               if (iblockstate2.getMaterial() != hM.AIR && iblockstate2.isFullBlock()) {
                  float f = this.getSkyBrightness(worldIn, 1, 0, j3, structureBoundingBoxIn) > 8 ? 0.9F : 0.7F;
                  this.randomlyPlaceBlock(worldIn, structureBoundingBoxIn, randomIn, f, 1, 0, j3, iblockstate1);
               }
            }
         }

         return true;
      }
   }

   private void placeSupport(bij p_189921_1_, bdy p_189921_2_, int p_189921_3_, int p_189921_4_, int p_189921_5_, int p_189921_6_, int p_189921_7_, Random p_189921_8_) {
      if (this.isSupportingBox(p_189921_1_, p_189921_2_, p_189921_3_, p_189921_7_, p_189921_6_, p_189921_5_)) {
         in iblockstate = this.getPlanksBlock();
         in iblockstate1 = this.getFenceBlock();
         in iblockstate2 = Nk.AIR.getDefaultState();
         this.fillWithBlocks(p_189921_1_, p_189921_2_, p_189921_3_, p_189921_4_, p_189921_5_, p_189921_3_, p_189921_6_ - 1, p_189921_5_, iblockstate1, iblockstate2, false);
         this.fillWithBlocks(p_189921_1_, p_189921_2_, p_189921_7_, p_189921_4_, p_189921_5_, p_189921_7_, p_189921_6_ - 1, p_189921_5_, iblockstate1, iblockstate2, false);
         if (p_189921_8_.nextInt(4) == 0) {
            this.fillWithBlocks(p_189921_1_, p_189921_2_, p_189921_3_, p_189921_6_, p_189921_5_, p_189921_3_, p_189921_6_, p_189921_5_, iblockstate, iblockstate2, false);
            this.fillWithBlocks(p_189921_1_, p_189921_2_, p_189921_7_, p_189921_6_, p_189921_5_, p_189921_7_, p_189921_6_, p_189921_5_, iblockstate, iblockstate2, false);
         } else {
            this.fillWithBlocks(p_189921_1_, p_189921_2_, p_189921_3_, p_189921_6_, p_189921_5_, p_189921_7_, p_189921_6_, p_189921_5_, iblockstate, iblockstate2, false);
            this.randomlyPlaceBlock(p_189921_1_, p_189921_2_, p_189921_8_, 0.05F, p_189921_3_ + 1, p_189921_6_, p_189921_5_ - 1, Nk.TORCH.getDefaultState().withProperty(ho.FACING, EnumFacing.NORTH));
            this.randomlyPlaceBlock(p_189921_1_, p_189921_2_, p_189921_8_, 0.05F, p_189921_3_ + 1, p_189921_6_, p_189921_5_ + 1, Nk.TORCH.getDefaultState().withProperty(ho.FACING, EnumFacing.SOUTH));
         }
      }

   }

   private void placeCobWeb(bij p_189922_1_, bdy p_189922_2_, Random p_189922_3_, float p_189922_4_, int p_189922_5_, int p_189922_6_, int p_189922_7_) {
      if (this.getSkyBrightness(p_189922_1_, p_189922_5_, p_189922_6_, p_189922_7_, p_189922_2_) < 8) {
         this.randomlyPlaceBlock(p_189922_1_, p_189922_2_, p_189922_3_, p_189922_4_, p_189922_5_, p_189922_6_, p_189922_7_, Nk.WEB.getDefaultState());
      }

   }
}
