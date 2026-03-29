package neo;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;

public abstract class bdB {
   protected bdy boundingBox;
   @Nullable
   private EnumFacing coordBaseMode;
   private Mirror mirror;
   private Rotation rotation;
   protected int componentType;

   public bdB() {
   }

   protected bdB(int type) {
      this.componentType = type;
   }

   public final QQ createStructureBaseNBT() {
      QQ nbttagcompound = new QQ();
      nbttagcompound.setString("id", bdt.getStructureComponentName(this));
      nbttagcompound.setTag("BB", this.boundingBox.toNBTTagIntArray());
      EnumFacing enumfacing = this.getCoordBaseMode();
      nbttagcompound.setInteger("O", enumfacing == null ? -1 : enumfacing.getHorizontalIndex());
      nbttagcompound.setInteger("GD", this.componentType);
      this.writeStructureToNBT(nbttagcompound);
      return nbttagcompound;
   }

   protected abstract void writeStructureToNBT(QQ var1);

   public void readStructureBaseNBT(bij worldIn, QQ tagCompound) {
      if (tagCompound.hasKey("BB")) {
         this.boundingBox = new bdy(tagCompound.getIntArray("BB"));
      }

      int i = tagCompound.getInteger("O");
      this.setCoordBaseMode(i == -1 ? null : EnumFacing.byHorizontalIndex(i));
      this.componentType = tagCompound.getInteger("GD");
      this.readStructureFromNBT(tagCompound, worldIn.getSaveHandler().getStructureTemplateManager());
   }

   protected abstract void readStructureFromNBT(QQ var1, bfL var2);

   public void buildComponent(bdB componentIn, List<bdB> listIn, Random rand) {
   }

   public abstract boolean addComponentParts(bij var1, Random var2, bdy var3);

   public bdy getBoundingBox() {
      return this.boundingBox;
   }

   public int getComponentType() {
      return this.componentType;
   }

   public static bdB findIntersecting(List<bdB> listIn, bdy boundingboxIn) {
      Iterator var2 = listIn.iterator();

      bdB structurecomponent;
      do {
         if (!var2.hasNext()) {
            return null;
         }

         structurecomponent = (bdB)var2.next();
      } while(structurecomponent.getBoundingBox() == null || !structurecomponent.getBoundingBox().intersectsWith(boundingboxIn));

      return structurecomponent;
   }

   protected boolean isLiquidInStructureBoundingBox(bij worldIn, bdy boundingboxIn) {
      int i = Math.max(this.boundingBox.minX - 1, boundingboxIn.minX);
      int j = Math.max(this.boundingBox.minY - 1, boundingboxIn.minY);
      int k = Math.max(this.boundingBox.minZ - 1, boundingboxIn.minZ);
      int l = Math.min(this.boundingBox.maxX + 1, boundingboxIn.maxX);
      int i1 = Math.min(this.boundingBox.maxY + 1, boundingboxIn.maxY);
      int j1 = Math.min(this.boundingBox.maxZ + 1, boundingboxIn.maxZ);
      BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

      int j2;
      int l2;
      for(j2 = i; j2 <= l; ++j2) {
         for(l2 = k; l2 <= j1; ++l2) {
            if (worldIn.getBlockState(blockpos$mutableblockpos.setPos(j2, j, l2)).getMaterial().isLiquid()) {
               return true;
            }

            if (worldIn.getBlockState(blockpos$mutableblockpos.setPos(j2, i1, l2)).getMaterial().isLiquid()) {
               return true;
            }
         }
      }

      for(j2 = i; j2 <= l; ++j2) {
         for(l2 = j; l2 <= i1; ++l2) {
            if (worldIn.getBlockState(blockpos$mutableblockpos.setPos(j2, l2, k)).getMaterial().isLiquid()) {
               return true;
            }

            if (worldIn.getBlockState(blockpos$mutableblockpos.setPos(j2, l2, j1)).getMaterial().isLiquid()) {
               return true;
            }
         }
      }

      for(j2 = k; j2 <= j1; ++j2) {
         for(l2 = j; l2 <= i1; ++l2) {
            if (worldIn.getBlockState(blockpos$mutableblockpos.setPos(i, l2, j2)).getMaterial().isLiquid()) {
               return true;
            }

            if (worldIn.getBlockState(blockpos$mutableblockpos.setPos(l, l2, j2)).getMaterial().isLiquid()) {
               return true;
            }
         }
      }

      return false;
   }

   protected int getXWithOffset(int x, int z) {
      EnumFacing enumfacing = this.getCoordBaseMode();
      if (enumfacing == null) {
         return x;
      } else {
         switch (enumfacing) {
            case NORTH:
            case SOUTH:
               return this.boundingBox.minX + x;
            case WEST:
               return this.boundingBox.maxX - z;
            case EAST:
               return this.boundingBox.minX + z;
            default:
               return x;
         }
      }
   }

   protected int getYWithOffset(int y) {
      return this.getCoordBaseMode() == null ? y : y + this.boundingBox.minY;
   }

   protected int getZWithOffset(int x, int z) {
      EnumFacing enumfacing = this.getCoordBaseMode();
      if (enumfacing == null) {
         return z;
      } else {
         switch (enumfacing) {
            case NORTH:
               return this.boundingBox.maxZ - z;
            case SOUTH:
               return this.boundingBox.minZ + z;
            case WEST:
            case EAST:
               return this.boundingBox.minZ + x;
            default:
               return z;
         }
      }
   }

   protected void setBlockState(bij worldIn, in blockstateIn, int x, int y, int z, bdy boundingboxIn) {
      BlockPos blockpos = new BlockPos(this.getXWithOffset(x, z), this.getYWithOffset(y), this.getZWithOffset(x, z));
      if (boundingboxIn.isVecInside(blockpos)) {
         if (this.mirror != Mirror.NONE) {
            blockstateIn = blockstateIn.withMirror(this.mirror);
         }

         if (this.rotation != Rotation.NONE) {
            blockstateIn = blockstateIn.withRotation(this.rotation);
         }

         worldIn.setBlockState(blockpos, blockstateIn, 2);
      }

   }

   protected in getBlockStateFromPos(bij worldIn, int x, int y, int z, bdy boundingboxIn) {
      int i = this.getXWithOffset(x, z);
      int j = this.getYWithOffset(y);
      int k = this.getZWithOffset(x, z);
      BlockPos blockpos = new BlockPos(i, j, k);
      return !boundingboxIn.isVecInside(blockpos) ? Nk.AIR.getDefaultState() : worldIn.getBlockState(blockpos);
   }

   protected int getSkyBrightness(bij worldIn, int x, int y, int z, bdy boundingboxIn) {
      int i = this.getXWithOffset(x, z);
      int j = this.getYWithOffset(y + 1);
      int k = this.getZWithOffset(x, z);
      BlockPos blockpos = new BlockPos(i, j, k);
      return !boundingboxIn.isVecInside(blockpos) ? baW.SKY.defaultLightValue : worldIn.getLightFor(baW.SKY, blockpos);
   }

   protected void fillWithAir(bij worldIn, bdy structurebb, int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
      for(int i = minY; i <= maxY; ++i) {
         for(int j = minX; j <= maxX; ++j) {
            for(int k = minZ; k <= maxZ; ++k) {
               this.setBlockState(worldIn, Nk.AIR.getDefaultState(), j, i, k, structurebb);
            }
         }
      }

   }

   protected void fillWithBlocks(bij worldIn, bdy boundingboxIn, int xMin, int yMin, int zMin, int xMax, int yMax, int zMax, in boundaryBlockState, in insideBlockState, boolean existingOnly) {
      for(int i = yMin; i <= yMax; ++i) {
         for(int j = xMin; j <= xMax; ++j) {
            for(int k = zMin; k <= zMax; ++k) {
               if (!existingOnly || this.getBlockStateFromPos(worldIn, j, i, k, boundingboxIn).getMaterial() != hM.AIR) {
                  if (i != yMin && i != yMax && j != xMin && j != xMax && k != zMin && k != zMax) {
                     this.setBlockState(worldIn, insideBlockState, j, i, k, boundingboxIn);
                  } else {
                     this.setBlockState(worldIn, boundaryBlockState, j, i, k, boundingboxIn);
                  }
               }
            }
         }
      }

   }

   protected void fillWithRandomizedBlocks(bij worldIn, bdy boundingboxIn, int minX, int minY, int minZ, int maxX, int maxY, int maxZ, boolean alwaysReplace, Random rand, bdA blockselector) {
      for(int i = minY; i <= maxY; ++i) {
         for(int j = minX; j <= maxX; ++j) {
            for(int k = minZ; k <= maxZ; ++k) {
               if (!alwaysReplace || this.getBlockStateFromPos(worldIn, j, i, k, boundingboxIn).getMaterial() != hM.AIR) {
                  blockselector.selectBlocks(rand, j, i, k, i == minY || i == maxY || j == minX || j == maxX || k == minZ || k == maxZ);
                  this.setBlockState(worldIn, blockselector.getBlockState(), j, i, k, boundingboxIn);
               }
            }
         }
      }

   }

   protected void generateMaybeBox(bij worldIn, bdy sbb, Random rand, float chance, int x1, int y1, int z1, int x2, int y2, int z2, in edgeState, in state, boolean requireNonAir, int requiredSkylight) {
      for(int i = y1; i <= y2; ++i) {
         for(int j = x1; j <= x2; ++j) {
            for(int k = z1; k <= z2; ++k) {
               if (rand.nextFloat() <= chance && (!requireNonAir || this.getBlockStateFromPos(worldIn, j, i, k, sbb).getMaterial() != hM.AIR) && (requiredSkylight <= 0 || this.getSkyBrightness(worldIn, j, i, k, sbb) < requiredSkylight)) {
                  if (i != y1 && i != y2 && j != x1 && j != x2 && k != z1 && k != z2) {
                     this.setBlockState(worldIn, state, j, i, k, sbb);
                  } else {
                     this.setBlockState(worldIn, edgeState, j, i, k, sbb);
                  }
               }
            }
         }
      }

   }

   protected void randomlyPlaceBlock(bij worldIn, bdy boundingboxIn, Random rand, float chance, int x, int y, int z, in blockstateIn) {
      if (rand.nextFloat() < chance) {
         this.setBlockState(worldIn, blockstateIn, x, y, z, boundingboxIn);
      }

   }

   protected void randomlyRareFillWithBlocks(bij worldIn, bdy boundingboxIn, int minX, int minY, int minZ, int maxX, int maxY, int maxZ, in blockstateIn, boolean excludeAir) {
      float f = (float)(maxX - minX + 1);
      float f1 = (float)(maxY - minY + 1);
      float f2 = (float)(maxZ - minZ + 1);
      float f3 = (float)minX + f / 2.0F;
      float f4 = (float)minZ + f2 / 2.0F;

      for(int i = minY; i <= maxY; ++i) {
         float f5 = (float)(i - minY) / f1;

         for(int j = minX; j <= maxX; ++j) {
            float f6 = ((float)j - f3) / (f * 0.5F);

            for(int k = minZ; k <= maxZ; ++k) {
               float f7 = ((float)k - f4) / (f2 * 0.5F);
               if (!excludeAir || this.getBlockStateFromPos(worldIn, j, i, k, boundingboxIn).getMaterial() != hM.AIR) {
                  float f8 = f6 * f6 + f5 * f5 + f7 * f7;
                  if (f8 <= 1.05F) {
                     this.setBlockState(worldIn, blockstateIn, j, i, k, boundingboxIn);
                  }
               }
            }
         }
      }

   }

   protected void clearCurrentPositionBlocksUpwards(bij worldIn, int x, int y, int z, bdy structurebb) {
      BlockPos blockpos = new BlockPos(this.getXWithOffset(x, z), this.getYWithOffset(y), this.getZWithOffset(x, z));
      if (structurebb.isVecInside(blockpos)) {
         while(!worldIn.isAirBlock(blockpos) && blockpos.getY() < 255) {
            worldIn.setBlockState(blockpos, Nk.AIR.getDefaultState(), 2);
            blockpos = blockpos.up();
         }
      }

   }

   protected void replaceAirAndLiquidDownwards(bij worldIn, in blockstateIn, int x, int y, int z, bdy boundingboxIn) {
      int i = this.getXWithOffset(x, z);
      int j = this.getYWithOffset(y);
      int k = this.getZWithOffset(x, z);
      if (boundingboxIn.isVecInside(new BlockPos(i, j, k))) {
         while((worldIn.isAirBlock(new BlockPos(i, j, k)) || worldIn.getBlockState(new BlockPos(i, j, k)).getMaterial().isLiquid()) && j > 1) {
            worldIn.setBlockState(new BlockPos(i, j, k), blockstateIn, 2);
            --j;
         }
      }

   }

   protected boolean generateChest(bij worldIn, bdy structurebb, Random randomIn, int x, int y, int z, ResourceLocation loot) {
      BlockPos blockpos = new BlockPos(this.getXWithOffset(x, z), this.getYWithOffset(y), this.getZWithOffset(x, z));
      return this.generateChest(worldIn, structurebb, randomIn, blockpos, loot, (in)null);
   }

   protected boolean generateChest(bij p_191080_1_, bdy p_191080_2_, Random p_191080_3_, BlockPos p_191080_4_, ResourceLocation p_191080_5_, @Nullable in p_191080_6_) {
      if (p_191080_2_.isVecInside(p_191080_4_) && p_191080_1_.getBlockState(p_191080_4_).getBlock() != Nk.CHEST) {
         if (p_191080_6_ == null) {
            p_191080_6_ = Nk.CHEST.correctFacing(p_191080_1_, p_191080_4_, Nk.CHEST.getDefaultState());
         }

         p_191080_1_.setBlockState(p_191080_4_, p_191080_6_, 2);
         Yg tileentity = p_191080_1_.getTileEntity(p_191080_4_);
         if (tileentity instanceof Yn) {
            ((Yn)tileentity).setLootTable(p_191080_5_, p_191080_3_.nextLong());
         }

         return true;
      } else {
         return false;
      }
   }

   protected boolean createDispenser(bij worldIn, bdy sbb, Random rand, int x, int y, int z, EnumFacing facing, ResourceLocation lootTableIn) {
      BlockPos blockpos = new BlockPos(this.getXWithOffset(x, z), this.getYWithOffset(y), this.getZWithOffset(x, z));
      if (sbb.isVecInside(blockpos) && worldIn.getBlockState(blockpos).getBlock() != Nk.DISPENSER) {
         this.setBlockState(worldIn, Nk.DISPENSER.getDefaultState().withProperty(dk.FACING, facing), x, y, z, sbb);
         Yg tileentity = worldIn.getTileEntity(blockpos);
         if (tileentity instanceof Yt) {
            ((Yt)tileentity).setLootTable(lootTableIn, rand.nextLong());
         }

         return true;
      } else {
         return false;
      }
   }

   protected void generateDoor(bij worldIn, bdy sbb, Random rand, int x, int y, int z, EnumFacing facing, do door) {
      this.setBlockState(worldIn, door.getDefaultState().withProperty(do.FACING, facing), x, y, z, sbb);
      this.setBlockState(worldIn, door.getDefaultState().withProperty(do.FACING, facing).withProperty(do.HALF, dm.UPPER), x, y + 1, z, sbb);
   }

   public void offset(int x, int y, int z) {
      this.boundingBox.offset(x, y, z);
   }

   @Nullable
   public EnumFacing getCoordBaseMode() {
      return this.coordBaseMode;
   }

   public void setCoordBaseMode(@Nullable EnumFacing facing) {
      this.coordBaseMode = facing;
      if (facing == null) {
         this.rotation = Rotation.NONE;
         this.mirror = Mirror.NONE;
      } else {
         switch (facing) {
            case SOUTH:
               this.mirror = Mirror.LEFT_RIGHT;
               this.rotation = Rotation.NONE;
               break;
            case WEST:
               this.mirror = Mirror.LEFT_RIGHT;
               this.rotation = Rotation.CLOCKWISE_90;
               break;
            case EAST:
               this.mirror = Mirror.NONE;
               this.rotation = Rotation.CLOCKWISE_90;
               break;
            default:
               this.mirror = Mirror.NONE;
               this.rotation = Rotation.NONE;
         }
      }

   }
}
