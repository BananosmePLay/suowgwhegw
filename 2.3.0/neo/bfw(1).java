package neo;

import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

abstract class bfw extends bdB {
   protected int averageGroundLvl = -1;
   private int villagersSpawned;
   protected int structureType;
   protected boolean isZombieInfested;

   public bfw() {
   }

   protected bfw(bfu start, int type) {
      super(type);
      if (start != null) {
         this.structureType = start.structureType;
         this.isZombieInfested = start.isZombieInfested;
      }

   }

   protected void writeStructureToNBT(QQ tagCompound) {
      tagCompound.setInteger("HPos", this.averageGroundLvl);
      tagCompound.setInteger("VCount", this.villagersSpawned);
      tagCompound.setByte("Type", (byte)this.structureType);
      tagCompound.setBoolean("Zombie", this.isZombieInfested);
   }

   protected void readStructureFromNBT(QQ tagCompound, bfL p_143011_2_) {
      this.averageGroundLvl = tagCompound.getInteger("HPos");
      this.villagersSpawned = tagCompound.getInteger("VCount");
      this.structureType = tagCompound.getByte("Type");
      if (tagCompound.getBoolean("Desert")) {
         this.structureType = 1;
      }

      this.isZombieInfested = tagCompound.getBoolean("Zombie");
   }

   @Nullable
   protected bdB getNextComponentNN(bfu start, List<bdB> structureComponents, Random rand, int p_74891_4_, int p_74891_5_) {
      EnumFacing enumfacing = this.getCoordBaseMode();
      if (enumfacing != null) {
         switch (enumfacing) {
            case NORTH:
            default:
               return bfz.access$100(start, structureComponents, rand, this.boundingBox.minX - 1, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ + p_74891_5_, EnumFacing.WEST, this.getComponentType());
            case SOUTH:
               return bfz.access$100(start, structureComponents, rand, this.boundingBox.minX - 1, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ + p_74891_5_, EnumFacing.WEST, this.getComponentType());
            case WEST:
               return bfz.access$100(start, structureComponents, rand, this.boundingBox.minX + p_74891_5_, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
            case EAST:
               return bfz.access$100(start, structureComponents, rand, this.boundingBox.minX + p_74891_5_, this.boundingBox.minY + p_74891_4_, this.boundingBox.minZ - 1, EnumFacing.NORTH, this.getComponentType());
         }
      } else {
         return null;
      }
   }

   @Nullable
   protected bdB getNextComponentPP(bfu start, List<bdB> structureComponents, Random rand, int p_74894_4_, int p_74894_5_) {
      EnumFacing enumfacing = this.getCoordBaseMode();
      if (enumfacing != null) {
         switch (enumfacing) {
            case NORTH:
            default:
               return bfz.access$100(start, structureComponents, rand, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74894_4_, this.boundingBox.minZ + p_74894_5_, EnumFacing.EAST, this.getComponentType());
            case SOUTH:
               return bfz.access$100(start, structureComponents, rand, this.boundingBox.maxX + 1, this.boundingBox.minY + p_74894_4_, this.boundingBox.minZ + p_74894_5_, EnumFacing.EAST, this.getComponentType());
            case WEST:
               return bfz.access$100(start, structureComponents, rand, this.boundingBox.minX + p_74894_5_, this.boundingBox.minY + p_74894_4_, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
            case EAST:
               return bfz.access$100(start, structureComponents, rand, this.boundingBox.minX + p_74894_5_, this.boundingBox.minY + p_74894_4_, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, this.getComponentType());
         }
      } else {
         return null;
      }
   }

   protected int getAverageGroundLevel(bij worldIn, bdy structurebb) {
      int i = 0;
      int j = 0;
      BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

      for(int k = this.boundingBox.minZ; k <= this.boundingBox.maxZ; ++k) {
         for(int l = this.boundingBox.minX; l <= this.boundingBox.maxX; ++l) {
            blockpos$mutableblockpos.setPos(l, 64, k);
            if (structurebb.isVecInside(blockpos$mutableblockpos)) {
               i += Math.max(worldIn.getTopSolidOrLiquidBlock(blockpos$mutableblockpos).getY(), worldIn.provider.getAverageGroundLevel() - 1);
               ++j;
            }
         }
      }

      if (j == 0) {
         return -1;
      } else {
         return i / j;
      }
   }

   protected static boolean canVillageGoDeeper(bdy structurebb) {
      return structurebb != null && structurebb.minY > 10;
   }

   protected void spawnVillagers(bij worldIn, bdy structurebb, int x, int y, int z, int count) {
      if (this.villagersSpawned < count) {
         for(int i = this.villagersSpawned; i < count; ++i) {
            int j = this.getXWithOffset(x + i, z);
            int k = this.getYWithOffset(y);
            int l = this.getZWithOffset(x + i, z);
            if (!structurebb.isVecInside(new BlockPos(j, k, l))) {
               break;
            }

            ++this.villagersSpawned;
            if (this.isZombieInfested) {
               Ll entityzombievillager = new Ll(worldIn);
               entityzombievillager.setLocationAndAngles((double)j + 0.5, (double)k, (double)l + 0.5, 0.0F, 0.0F);
               entityzombievillager.onInitialSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityzombievillager)), (ID)null);
               entityzombievillager.setProfession(this.chooseProfession(i, 0));
               entityzombievillager.enablePersistence();
               worldIn.spawnEntity(entityzombievillager);
            } else {
               Mq entityvillager = new Mq(worldIn);
               entityvillager.setLocationAndAngles((double)j + 0.5, (double)k, (double)l + 0.5, 0.0F, 0.0F);
               entityvillager.setProfession(this.chooseProfession(i, worldIn.rand.nextInt(6)));
               entityvillager.finalizeMobSpawn(worldIn.getDifficultyForLocation(new BlockPos(entityvillager)), (ID)null, false);
               worldIn.spawnEntity(entityvillager);
            }
         }
      }

   }

   protected int chooseProfession(int villagersSpawnedIn, int currentVillagerProfession) {
      return currentVillagerProfession;
   }

   protected in getBiomeSpecificBlockState(in blockstateIn) {
      if (this.structureType == 1) {
         if (blockstateIn.getBlock() == Nk.LOG || blockstateIn.getBlock() == Nk.LOG2) {
            return Nk.SANDSTONE.getDefaultState();
         }

         if (blockstateIn.getBlock() == Nk.COBBLESTONE) {
            return Nk.SANDSTONE.getStateFromMeta(gl.DEFAULT.getMetadata());
         }

         if (blockstateIn.getBlock() == Nk.PLANKS) {
            return Nk.SANDSTONE.getStateFromMeta(gl.SMOOTH.getMetadata());
         }

         if (blockstateIn.getBlock() == Nk.OAK_STAIRS) {
            return Nk.SANDSTONE_STAIRS.getDefaultState().withProperty(gU.FACING, blockstateIn.getValue(gU.FACING));
         }

         if (blockstateIn.getBlock() == Nk.STONE_STAIRS) {
            return Nk.SANDSTONE_STAIRS.getDefaultState().withProperty(gU.FACING, blockstateIn.getValue(gU.FACING));
         }

         if (blockstateIn.getBlock() == Nk.GRAVEL) {
            return Nk.SANDSTONE.getDefaultState();
         }
      } else if (this.structureType == 3) {
         if (blockstateIn.getBlock() == Nk.LOG || blockstateIn.getBlock() == Nk.LOG2) {
            return Nk.LOG.getDefaultState().withProperty(eZ.VARIANT, fk.SPRUCE).withProperty(eE.LOG_AXIS, blockstateIn.getValue(eE.LOG_AXIS));
         }

         if (blockstateIn.getBlock() == Nk.PLANKS) {
            return Nk.PLANKS.getDefaultState().withProperty(fl.VARIANT, fk.SPRUCE);
         }

         if (blockstateIn.getBlock() == Nk.OAK_STAIRS) {
            return Nk.SPRUCE_STAIRS.getDefaultState().withProperty(gU.FACING, blockstateIn.getValue(gU.FACING));
         }

         if (blockstateIn.getBlock() == Nk.OAK_FENCE) {
            return Nk.SPRUCE_FENCE.getDefaultState();
         }
      } else if (this.structureType == 2) {
         if (blockstateIn.getBlock() == Nk.LOG || blockstateIn.getBlock() == Nk.LOG2) {
            return Nk.LOG2.getDefaultState().withProperty(eR.VARIANT, fk.ACACIA).withProperty(eE.LOG_AXIS, blockstateIn.getValue(eE.LOG_AXIS));
         }

         if (blockstateIn.getBlock() == Nk.PLANKS) {
            return Nk.PLANKS.getDefaultState().withProperty(fl.VARIANT, fk.ACACIA);
         }

         if (blockstateIn.getBlock() == Nk.OAK_STAIRS) {
            return Nk.ACACIA_STAIRS.getDefaultState().withProperty(gU.FACING, blockstateIn.getValue(gU.FACING));
         }

         if (blockstateIn.getBlock() == Nk.COBBLESTONE) {
            return Nk.LOG2.getDefaultState().withProperty(eR.VARIANT, fk.ACACIA).withProperty(eE.LOG_AXIS, eD.Y);
         }

         if (blockstateIn.getBlock() == Nk.OAK_FENCE) {
            return Nk.ACACIA_FENCE.getDefaultState();
         }
      }

      return blockstateIn;
   }

   protected do biomeDoor() {
      switch (this.structureType) {
         case 2:
            return Nk.ACACIA_DOOR;
         case 3:
            return Nk.SPRUCE_DOOR;
         default:
            return Nk.OAK_DOOR;
      }
   }

   protected void createVillageDoor(bij p_189927_1_, bdy p_189927_2_, Random p_189927_3_, int p_189927_4_, int p_189927_5_, int p_189927_6_, EnumFacing p_189927_7_) {
      if (!this.isZombieInfested) {
         this.generateDoor(p_189927_1_, p_189927_2_, p_189927_3_, p_189927_4_, p_189927_5_, p_189927_6_, EnumFacing.NORTH, this.biomeDoor());
      }

   }

   protected void placeTorch(bij p_189926_1_, EnumFacing p_189926_2_, int p_189926_3_, int p_189926_4_, int p_189926_5_, bdy p_189926_6_) {
      if (!this.isZombieInfested) {
         this.setBlockState(p_189926_1_, Nk.TORCH.getDefaultState().withProperty(ho.FACING, p_189926_2_), p_189926_3_, p_189926_4_, p_189926_5_, p_189926_6_);
      }

   }

   protected void replaceAirAndLiquidDownwards(bij worldIn, in blockstateIn, int x, int y, int z, bdy boundingboxIn) {
      in iblockstate = this.getBiomeSpecificBlockState(blockstateIn);
      super.replaceAirAndLiquidDownwards(worldIn, iblockstate, x, y, z, boundingboxIn);
   }

   protected void setStructureType(int p_189924_1_) {
      this.structureType = p_189924_1_;
   }
}
