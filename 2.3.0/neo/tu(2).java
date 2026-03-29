package neo;

import java.util.BitSet;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

public class tu {
   private final float[] vertexColorMultiplier;
   private final int[] vertexBrightness;
   private BlockPos.MutableBlockPos[] blockPosArr;

   public tu() {
      this((ty)null);
   }

   public tu(ty p_i46235_1_) {
      this.vertexColorMultiplier = new float[4];
      this.vertexBrightness = new int[4];
      this.blockPosArr = new BlockPos.MutableBlockPos[5];

      for(int i = 0; i < this.blockPosArr.length; ++i) {
         this.blockPosArr[i] = new BlockPos.MutableBlockPos();
      }

   }

   public void setMaxBlockLight() {
      int i = 240;
      int[] var10000 = this.vertexBrightness;
      var10000[0] |= i;
      var10000 = this.vertexBrightness;
      var10000[1] |= i;
      var10000 = this.vertexBrightness;
      var10000[2] |= i;
      var10000 = this.vertexBrightness;
      var10000[3] |= i;
      this.vertexColorMultiplier[0] = 1.0F;
      this.vertexColorMultiplier[1] = 1.0F;
      this.vertexColorMultiplier[2] = 1.0F;
      this.vertexColorMultiplier[3] = 1.0F;
   }

   public void updateVertexBrightness(bfZ worldIn, in state, BlockPos centerPos, EnumFacing direction, float[] faceShape, BitSet shapeState) {
      BlockPos blockpos = shapeState.get(0) ? centerPos.offset(direction) : centerPos;
      BlockPos.MutableBlockPos blockpos$mutableblockpos = this.blockPosArr[0].setPos(0, 0, 0);
      tv blockmodelrenderer$enumneighborinfo = tv.getNeighbourInfo(direction);
      BlockPos.MutableBlockPos blockpos$mutableblockpos1 = this.blockPosArr[1].setPos((Vec3i)blockpos).move(tv.access$200(blockmodelrenderer$enumneighborinfo)[0]);
      BlockPos.MutableBlockPos blockpos$mutableblockpos2 = this.blockPosArr[2].setPos((Vec3i)blockpos).move(tv.access$200(blockmodelrenderer$enumneighborinfo)[1]);
      BlockPos.MutableBlockPos blockpos$mutableblockpos3 = this.blockPosArr[3].setPos((Vec3i)blockpos).move(tv.access$200(blockmodelrenderer$enumneighborinfo)[2]);
      BlockPos.MutableBlockPos blockpos$mutableblockpos4 = this.blockPosArr[4].setPos((Vec3i)blockpos).move(tv.access$200(blockmodelrenderer$enumneighborinfo)[3]);
      int i = state.getPackedLightmapCoords(worldIn, blockpos$mutableblockpos1);
      int j = state.getPackedLightmapCoords(worldIn, blockpos$mutableblockpos2);
      int k = state.getPackedLightmapCoords(worldIn, blockpos$mutableblockpos3);
      int l = state.getPackedLightmapCoords(worldIn, blockpos$mutableblockpos4);
      float f = worldIn.getBlockState(blockpos$mutableblockpos1).getAmbientOcclusionLightValue();
      float f1 = worldIn.getBlockState(blockpos$mutableblockpos2).getAmbientOcclusionLightValue();
      float f2 = worldIn.getBlockState(blockpos$mutableblockpos3).getAmbientOcclusionLightValue();
      float f3 = worldIn.getBlockState(blockpos$mutableblockpos4).getAmbientOcclusionLightValue();
      f = ty.fixAoLightValue(f);
      f1 = ty.fixAoLightValue(f1);
      f2 = ty.fixAoLightValue(f2);
      f3 = ty.fixAoLightValue(f3);
      boolean flag = worldIn.getBlockState(blockpos$mutableblockpos.setPos((Vec3i)blockpos$mutableblockpos1).move(direction)).isTranslucent();
      boolean flag1 = worldIn.getBlockState(blockpos$mutableblockpos.setPos((Vec3i)blockpos$mutableblockpos2).move(direction)).isTranslucent();
      boolean flag2 = worldIn.getBlockState(blockpos$mutableblockpos.setPos((Vec3i)blockpos$mutableblockpos3).move(direction)).isTranslucent();
      boolean flag3 = worldIn.getBlockState(blockpos$mutableblockpos.setPos((Vec3i)blockpos$mutableblockpos4).move(direction)).isTranslucent();
      int i1;
      float f25;
      if (!flag2 && !flag) {
         f25 = f;
         i1 = i;
      } else {
         BlockPos blockpos1 = blockpos$mutableblockpos.setPos((Vec3i)blockpos$mutableblockpos1).move(tv.access$200(blockmodelrenderer$enumneighborinfo)[2]);
         f25 = worldIn.getBlockState(blockpos1).getAmbientOcclusionLightValue();
         f25 = ty.fixAoLightValue(f25);
         i1 = state.getPackedLightmapCoords(worldIn, blockpos1);
      }

      float f26;
      int j1;
      if (!flag3 && !flag) {
         f26 = f;
         j1 = i;
      } else {
         BlockPos blockpos2 = blockpos$mutableblockpos.setPos((Vec3i)blockpos$mutableblockpos1).move(tv.access$200(blockmodelrenderer$enumneighborinfo)[3]);
         f26 = worldIn.getBlockState(blockpos2).getAmbientOcclusionLightValue();
         f26 = ty.fixAoLightValue(f26);
         j1 = state.getPackedLightmapCoords(worldIn, blockpos2);
      }

      float f27;
      int k1;
      if (!flag2 && !flag1) {
         f27 = f1;
         k1 = j;
      } else {
         BlockPos blockpos3 = blockpos$mutableblockpos.setPos((Vec3i)blockpos$mutableblockpos2).move(tv.access$200(blockmodelrenderer$enumneighborinfo)[2]);
         f27 = worldIn.getBlockState(blockpos3).getAmbientOcclusionLightValue();
         f27 = ty.fixAoLightValue(f27);
         k1 = state.getPackedLightmapCoords(worldIn, blockpos3);
      }

      float f28;
      int l1;
      if (!flag3 && !flag1) {
         f28 = f1;
         l1 = j;
      } else {
         BlockPos blockpos4 = blockpos$mutableblockpos.setPos((Vec3i)blockpos$mutableblockpos2).move(tv.access$200(blockmodelrenderer$enumneighborinfo)[3]);
         f28 = worldIn.getBlockState(blockpos4).getAmbientOcclusionLightValue();
         f28 = ty.fixAoLightValue(f28);
         l1 = state.getPackedLightmapCoords(worldIn, blockpos4);
      }

      int i3 = state.getPackedLightmapCoords(worldIn, centerPos);
      if (shapeState.get(0) || !worldIn.getBlockState(centerPos.offset(direction)).isOpaqueCube()) {
         i3 = state.getPackedLightmapCoords(worldIn, centerPos.offset(direction));
      }

      float f4 = shapeState.get(0) ? worldIn.getBlockState(blockpos).getAmbientOcclusionLightValue() : worldIn.getBlockState(centerPos).getAmbientOcclusionLightValue();
      f4 = ty.fixAoLightValue(f4);
      tx blockmodelrenderer$vertextranslations = tx.getVertexTranslations(direction);
      float f5;
      float f6;
      float f7;
      float f8;
      if (shapeState.get(1) && tv.access$300(blockmodelrenderer$enumneighborinfo)) {
         f5 = (f3 + f + f26 + f4) * 0.25F;
         f6 = (f2 + f + f25 + f4) * 0.25F;
         f7 = (f2 + f1 + f27 + f4) * 0.25F;
         f8 = (f3 + f1 + f28 + f4) * 0.25F;
         float f9 = faceShape[tw.access$500(tv.access$400(blockmodelrenderer$enumneighborinfo)[0])] * faceShape[tw.access$500(tv.access$400(blockmodelrenderer$enumneighborinfo)[1])];
         float f10 = faceShape[tw.access$500(tv.access$400(blockmodelrenderer$enumneighborinfo)[2])] * faceShape[tw.access$500(tv.access$400(blockmodelrenderer$enumneighborinfo)[3])];
         float f11 = faceShape[tw.access$500(tv.access$400(blockmodelrenderer$enumneighborinfo)[4])] * faceShape[tw.access$500(tv.access$400(blockmodelrenderer$enumneighborinfo)[5])];
         float f12 = faceShape[tw.access$500(tv.access$400(blockmodelrenderer$enumneighborinfo)[6])] * faceShape[tw.access$500(tv.access$400(blockmodelrenderer$enumneighborinfo)[7])];
         float f13 = faceShape[tw.access$500(tv.access$600(blockmodelrenderer$enumneighborinfo)[0])] * faceShape[tw.access$500(tv.access$600(blockmodelrenderer$enumneighborinfo)[1])];
         float f14 = faceShape[tw.access$500(tv.access$600(blockmodelrenderer$enumneighborinfo)[2])] * faceShape[tw.access$500(tv.access$600(blockmodelrenderer$enumneighborinfo)[3])];
         float f15 = faceShape[tw.access$500(tv.access$600(blockmodelrenderer$enumneighborinfo)[4])] * faceShape[tw.access$500(tv.access$600(blockmodelrenderer$enumneighborinfo)[5])];
         float f16 = faceShape[tw.access$500(tv.access$600(blockmodelrenderer$enumneighborinfo)[6])] * faceShape[tw.access$500(tv.access$600(blockmodelrenderer$enumneighborinfo)[7])];
         float f17 = faceShape[tw.access$500(tv.access$700(blockmodelrenderer$enumneighborinfo)[0])] * faceShape[tw.access$500(tv.access$700(blockmodelrenderer$enumneighborinfo)[1])];
         float f18 = faceShape[tw.access$500(tv.access$700(blockmodelrenderer$enumneighborinfo)[2])] * faceShape[tw.access$500(tv.access$700(blockmodelrenderer$enumneighborinfo)[3])];
         float f19 = faceShape[tw.access$500(tv.access$700(blockmodelrenderer$enumneighborinfo)[4])] * faceShape[tw.access$500(tv.access$700(blockmodelrenderer$enumneighborinfo)[5])];
         float f20 = faceShape[tw.access$500(tv.access$700(blockmodelrenderer$enumneighborinfo)[6])] * faceShape[tw.access$500(tv.access$700(blockmodelrenderer$enumneighborinfo)[7])];
         float f21 = faceShape[tw.access$500(tv.access$800(blockmodelrenderer$enumneighborinfo)[0])] * faceShape[tw.access$500(tv.access$800(blockmodelrenderer$enumneighborinfo)[1])];
         float f22 = faceShape[tw.access$500(tv.access$800(blockmodelrenderer$enumneighborinfo)[2])] * faceShape[tw.access$500(tv.access$800(blockmodelrenderer$enumneighborinfo)[3])];
         float f23 = faceShape[tw.access$500(tv.access$800(blockmodelrenderer$enumneighborinfo)[4])] * faceShape[tw.access$500(tv.access$800(blockmodelrenderer$enumneighborinfo)[5])];
         float f24 = faceShape[tw.access$500(tv.access$800(blockmodelrenderer$enumneighborinfo)[6])] * faceShape[tw.access$500(tv.access$800(blockmodelrenderer$enumneighborinfo)[7])];
         this.vertexColorMultiplier[tx.access$900(blockmodelrenderer$vertextranslations)] = f5 * f9 + f6 * f10 + f7 * f11 + f8 * f12;
         this.vertexColorMultiplier[tx.access$1000(blockmodelrenderer$vertextranslations)] = f5 * f13 + f6 * f14 + f7 * f15 + f8 * f16;
         this.vertexColorMultiplier[tx.access$1100(blockmodelrenderer$vertextranslations)] = f5 * f17 + f6 * f18 + f7 * f19 + f8 * f20;
         this.vertexColorMultiplier[tx.access$1200(blockmodelrenderer$vertextranslations)] = f5 * f21 + f6 * f22 + f7 * f23 + f8 * f24;
         int i2 = this.getAoBrightness(l, i, j1, i3);
         int j2 = this.getAoBrightness(k, i, i1, i3);
         int k2 = this.getAoBrightness(k, j, k1, i3);
         int l2 = this.getAoBrightness(l, j, l1, i3);
         this.vertexBrightness[tx.access$900(blockmodelrenderer$vertextranslations)] = this.getVertexBrightness(i2, j2, k2, l2, f9, f10, f11, f12);
         this.vertexBrightness[tx.access$1000(blockmodelrenderer$vertextranslations)] = this.getVertexBrightness(i2, j2, k2, l2, f13, f14, f15, f16);
         this.vertexBrightness[tx.access$1100(blockmodelrenderer$vertextranslations)] = this.getVertexBrightness(i2, j2, k2, l2, f17, f18, f19, f20);
         this.vertexBrightness[tx.access$1200(blockmodelrenderer$vertextranslations)] = this.getVertexBrightness(i2, j2, k2, l2, f21, f22, f23, f24);
      } else {
         f5 = (f3 + f + f26 + f4) * 0.25F;
         f6 = (f2 + f + f25 + f4) * 0.25F;
         f7 = (f2 + f1 + f27 + f4) * 0.25F;
         f8 = (f3 + f1 + f28 + f4) * 0.25F;
         this.vertexBrightness[tx.access$900(blockmodelrenderer$vertextranslations)] = this.getAoBrightness(l, i, j1, i3);
         this.vertexBrightness[tx.access$1000(blockmodelrenderer$vertextranslations)] = this.getAoBrightness(k, i, i1, i3);
         this.vertexBrightness[tx.access$1100(blockmodelrenderer$vertextranslations)] = this.getAoBrightness(k, j, k1, i3);
         this.vertexBrightness[tx.access$1200(blockmodelrenderer$vertextranslations)] = this.getAoBrightness(l, j, l1, i3);
         this.vertexColorMultiplier[tx.access$900(blockmodelrenderer$vertextranslations)] = f5;
         this.vertexColorMultiplier[tx.access$1000(blockmodelrenderer$vertextranslations)] = f6;
         this.vertexColorMultiplier[tx.access$1100(blockmodelrenderer$vertextranslations)] = f7;
         this.vertexColorMultiplier[tx.access$1200(blockmodelrenderer$vertextranslations)] = f8;
      }

   }

   private int getAoBrightness(int br1, int br2, int br3, int br4) {
      if (br1 == 0) {
         br1 = br4;
      }

      if (br2 == 0) {
         br2 = br4;
      }

      if (br3 == 0) {
         br3 = br4;
      }

      return br1 + br2 + br3 + br4 >> 2 & 16711935;
   }

   private int getVertexBrightness(int p_178203_1_, int p_178203_2_, int p_178203_3_, int p_178203_4_, float p_178203_5_, float p_178203_6_, float p_178203_7_, float p_178203_8_) {
      int i = (int)((float)(p_178203_1_ >> 16 & 255) * p_178203_5_ + (float)(p_178203_2_ >> 16 & 255) * p_178203_6_ + (float)(p_178203_3_ >> 16 & 255) * p_178203_7_ + (float)(p_178203_4_ >> 16 & 255) * p_178203_8_) & 255;
      int j = (int)((float)(p_178203_1_ & 255) * p_178203_5_ + (float)(p_178203_2_ & 255) * p_178203_6_ + (float)(p_178203_3_ & 255) * p_178203_7_ + (float)(p_178203_4_ & 255) * p_178203_8_) & 255;
      return i << 16 | j;
   }

   // $FF: synthetic method
   static int[] access$000(tu x0) {
      return x0.vertexBrightness;
   }

   // $FF: synthetic method
   static float[] access$100(tu x0) {
      return x0.vertexColorMultiplier;
   }
}
