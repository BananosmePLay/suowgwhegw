package neo;

import com.google.common.collect.ImmutableList;
import java.util.List;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class bnb {
   private static final List<rK> NO_QUADS = ImmutableList.of();

   public bnb() {
   }

   public static sc getRenderModel(sc modelIn, in stateIn, boa renderEnv) {
      if (renderEnv.isSmartLeaves()) {
         modelIn = bpX.getLeavesModel(modelIn, stateIn);
      }

      return modelIn;
   }

   public static List<rK> getRenderQuads(List<rK> quads, bfZ worldIn, in stateIn, BlockPos posIn, EnumFacing enumfacing, BlockRenderLayer layer, long rand, boa renderEnv) {
      if (enumfacing != null) {
         if (renderEnv.isSmartLeaves() && bpX.isSameLeaves(worldIn.getBlockState(posIn.offset(enumfacing)), stateIn)) {
            return NO_QUADS;
         }

         if (!renderEnv.isBreakingAnimation(quads) && XH.isBetterGrass()) {
            quads = biI.getFaceQuads(worldIn, stateIn, posIn, enumfacing, quads);
         }
      }

      List<rK> list = renderEnv.getListQuadsCustomizer();
      list.clear();

      for(int i = 0; i < quads.size(); ++i) {
         rK bakedquad = (rK)quads.get(i);
         rK[] abakedquad = getRenderQuads(bakedquad, worldIn, stateIn, posIn, enumfacing, rand, renderEnv);
         if (i == 0 && quads.size() == 1 && abakedquad.length == 1 && abakedquad[0] == bakedquad && bakedquad.getQuadEmissive() == null) {
            return quads;
         }

         for(int j = 0; j < abakedquad.length; ++j) {
            rK bakedquad1 = abakedquad[j];
            list.add(bakedquad1);
            if (bakedquad1.getQuadEmissive() != null) {
               renderEnv.getListQuadsOverlay(getEmissiveLayer(layer)).addQuad(bakedquad1.getQuadEmissive(), stateIn);
               renderEnv.setOverlaysRendered(true);
            }
         }
      }

      return list;
   }

   private static BlockRenderLayer getEmissiveLayer(BlockRenderLayer layer) {
      return layer != null && layer != BlockRenderLayer.SOLID ? layer : BlockRenderLayer.CUTOUT_MIPPED;
   }

   private static rK[] getRenderQuads(rK quad, bfZ worldIn, in stateIn, BlockPos posIn, EnumFacing enumfacing, long rand, boa renderEnv) {
      if (renderEnv.isBreakingAnimation(quad)) {
         return renderEnv.getArrayQuadsCtm(quad);
      } else {
         rK bakedquad = quad;
         if (XH.isConnectedTextures()) {
            rK[] abakedquad = bjj.getConnectedTexture(worldIn, stateIn, posIn, quad, renderEnv);
            if (abakedquad.length != 1 || abakedquad[0] != quad) {
               return abakedquad;
            }
         }

         if (XH.isNaturalTextures()) {
            quad = bnj.getNaturalTexture(posIn, quad);
            if (quad != bakedquad) {
               return renderEnv.getArrayQuadsCtm(quad);
            }
         }

         return renderEnv.getArrayQuadsCtm(quad);
      }
   }
}
