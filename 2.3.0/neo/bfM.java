package neo;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;

public class bfM extends beM {
   private boolean isValid;

   public bfM() {
   }

   public bfM(bij p_i47235_1_, bbg p_i47235_2_, Random p_i47235_3_, int p_i47235_4_, int p_i47235_5_) {
      super(p_i47235_4_, p_i47235_5_);
      this.create(p_i47235_1_, p_i47235_2_, p_i47235_3_, p_i47235_4_, p_i47235_5_);
   }

   private void create(bij p_191092_1_, bbg p_191092_2_, Random p_191092_3_, int p_191092_4_, int p_191092_5_) {
      Rotation rotation = Rotation.values()[p_191092_3_.nextInt(Rotation.values().length)];
      ban chunkprimer = new ban();
      p_191092_2_.setBlocksInChunk(p_191092_4_, p_191092_5_, chunkprimer);
      int i = 5;
      int j = 5;
      if (rotation == Rotation.CLOCKWISE_90) {
         i = -5;
      } else if (rotation == Rotation.CLOCKWISE_180) {
         i = -5;
         j = -5;
      } else if (rotation == Rotation.COUNTERCLOCKWISE_90) {
         j = -5;
      }

      int k = chunkprimer.findGroundBlockIdx(7, 7);
      int l = chunkprimer.findGroundBlockIdx(7, 7 + j);
      int i1 = chunkprimer.findGroundBlockIdx(7 + i, 7);
      int j1 = chunkprimer.findGroundBlockIdx(7 + i, 7 + j);
      int k1 = Math.min(Math.min(k, l), Math.min(i1, j1));
      if (k1 < 60) {
         this.isValid = false;
      } else {
         BlockPos blockpos = new BlockPos(p_191092_4_ * 16 + 8, k1 + 1, p_191092_5_ * 16 + 8);
         List<bfR> list = Lists.newLinkedList();
         bfY.generateMansion(p_191092_1_.getSaveHandler().getStructureTemplateManager(), blockpos, rotation, list, p_191092_3_);
         this.components.addAll(list);
         this.updateBoundingBox();
         this.isValid = true;
      }

   }

   public void generateStructure(bij worldIn, Random rand, bdy structurebb) {
      super.generateStructure(worldIn, rand, structurebb);
      int i = this.boundingBox.minY;

      for(int j = structurebb.minX; j <= structurebb.maxX; ++j) {
         for(int k = structurebb.minZ; k <= structurebb.maxZ; ++k) {
            BlockPos blockpos = new BlockPos(j, i, k);
            if (!worldIn.isAirBlock(blockpos) && this.boundingBox.isVecInside(blockpos)) {
               boolean flag = false;
               Iterator var9 = this.components.iterator();

               while(var9.hasNext()) {
                  bdB structurecomponent = (bdB)var9.next();
                  if (structurecomponent.boundingBox.isVecInside(blockpos)) {
                     flag = true;
                     break;
                  }
               }

               if (flag) {
                  for(int l = i - 1; l > 1; --l) {
                     BlockPos blockpos1 = new BlockPos(j, l, k);
                     if (!worldIn.isAirBlock(blockpos1) && !worldIn.getBlockState(blockpos1).getMaterial().isLiquid()) {
                        break;
                     }

                     worldIn.setBlockState(blockpos1, Nk.COBBLESTONE.getDefaultState(), 2);
                  }
               }
            }
         }
      }

   }

   public boolean isSizeableStructure() {
      return this.isValid;
   }
}
