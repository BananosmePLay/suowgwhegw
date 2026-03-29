package neo;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class boa {
   private in blockState;
   private BlockPos blockPos;
   private int blockId = -1;
   private int metadata = -1;
   private int breakingAnimation = -1;
   private int smartLeaves = -1;
   private float[] quadBounds;
   private BitSet boundsFlags;
   private tu aoFace;
   private biN colorizerBlockPosM;
   private boolean[] borderFlags;
   private boolean[] borderFlags2;
   private boolean[] borderFlags3;
   private EnumFacing[] borderDirections;
   private List<rK> listQuadsCustomizer;
   private List<rK> listQuadsCtmMultipass;
   private rK[] arrayQuadsCtm1;
   private rK[] arrayQuadsCtm2;
   private rK[] arrayQuadsCtm3;
   private rK[] arrayQuadsCtm4;
   private yu regionRenderCacheBuilder;
   private bnd[] listsQuadsOverlay;
   private boolean overlaysRendered;
   private static final int UNKNOWN = -1;
   private static final int FALSE = 0;
   private static final int TRUE = 1;

   public boa(in blockState, BlockPos blockPos) {
      this.quadBounds = new float[EnumFacing.VALUES.length * 2];
      this.boundsFlags = new BitSet(3);
      this.aoFace = new tu();
      this.colorizerBlockPosM = null;
      this.borderFlags = null;
      this.borderFlags2 = null;
      this.borderFlags3 = null;
      this.borderDirections = null;
      this.listQuadsCustomizer = new ArrayList();
      this.listQuadsCtmMultipass = new ArrayList();
      this.arrayQuadsCtm1 = new rK[1];
      this.arrayQuadsCtm2 = new rK[2];
      this.arrayQuadsCtm3 = new rK[3];
      this.arrayQuadsCtm4 = new rK[4];
      this.regionRenderCacheBuilder = null;
      this.listsQuadsOverlay = new bnd[BlockRenderLayer.values().length];
      this.overlaysRendered = false;
      this.blockState = blockState;
      this.blockPos = blockPos;
   }

   public void reset(in blockStateIn, BlockPos blockPosIn) {
      if (this.blockState != blockStateIn || this.blockPos != blockPosIn) {
         this.blockState = blockStateIn;
         this.blockPos = blockPosIn;
         this.blockId = -1;
         this.metadata = -1;
         this.breakingAnimation = -1;
         this.smartLeaves = -1;
         this.boundsFlags.clear();
      }

   }

   public int getBlockId() {
      if (this.blockId < 0) {
         if (this.blockState instanceof ie) {
            ie blockstatebase = (ie)this.blockState;
            this.blockId = blockstatebase.getBlockId();
         } else {
            this.blockId = co.getIdFromBlock(this.blockState.getBlock());
         }
      }

      return this.blockId;
   }

   public int getMetadata() {
      if (this.metadata < 0) {
         if (this.blockState instanceof ie) {
            ie blockstatebase = (ie)this.blockState;
            this.metadata = blockstatebase.getMetadata();
         } else {
            this.metadata = this.blockState.getBlock().getMetaFromState(this.blockState);
         }
      }

      return this.metadata;
   }

   public float[] getQuadBounds() {
      return this.quadBounds;
   }

   public BitSet getBoundsFlags() {
      return this.boundsFlags;
   }

   public tu getAoFace() {
      return this.aoFace;
   }

   public boolean isBreakingAnimation(List listQuads) {
      if (this.breakingAnimation == -1 && listQuads.size() > 0) {
         if (listQuads.get(0) instanceof rL) {
            this.breakingAnimation = 1;
         } else {
            this.breakingAnimation = 0;
         }
      }

      return this.breakingAnimation == 1;
   }

   public boolean isBreakingAnimation(rK quad) {
      if (this.breakingAnimation < 0) {
         if (quad instanceof rL) {
            this.breakingAnimation = 1;
         } else {
            this.breakingAnimation = 0;
         }
      }

      return this.breakingAnimation == 1;
   }

   public boolean isBreakingAnimation() {
      return this.breakingAnimation == 1;
   }

   public in getBlockState() {
      return this.blockState;
   }

   public biN getColorizerBlockPosM() {
      if (this.colorizerBlockPosM == null) {
         this.colorizerBlockPosM = new biN(0, 0, 0);
      }

      return this.colorizerBlockPosM;
   }

   public boolean[] getBorderFlags() {
      if (this.borderFlags == null) {
         this.borderFlags = new boolean[4];
      }

      return this.borderFlags;
   }

   public boolean[] getBorderFlags2() {
      if (this.borderFlags2 == null) {
         this.borderFlags2 = new boolean[4];
      }

      return this.borderFlags2;
   }

   public boolean[] getBorderFlags3() {
      if (this.borderFlags3 == null) {
         this.borderFlags3 = new boolean[4];
      }

      return this.borderFlags3;
   }

   public EnumFacing[] getBorderDirections() {
      if (this.borderDirections == null) {
         this.borderDirections = new EnumFacing[4];
      }

      return this.borderDirections;
   }

   public EnumFacing[] getBorderDirections(EnumFacing dir0, EnumFacing dir1, EnumFacing dir2, EnumFacing dir3) {
      EnumFacing[] aenumfacing = this.getBorderDirections();
      aenumfacing[0] = dir0;
      aenumfacing[1] = dir1;
      aenumfacing[2] = dir2;
      aenumfacing[3] = dir3;
      return aenumfacing;
   }

   public boolean isSmartLeaves() {
      if (this.smartLeaves == -1) {
         if (XH.isTreesSmart() && this.blockState.getBlock() instanceof ew) {
            this.smartLeaves = 1;
         } else {
            this.smartLeaves = 0;
         }
      }

      return this.smartLeaves == 1;
   }

   public List<rK> getListQuadsCustomizer() {
      return this.listQuadsCustomizer;
   }

   public rK[] getArrayQuadsCtm(rK quad) {
      this.arrayQuadsCtm1[0] = quad;
      return this.arrayQuadsCtm1;
   }

   public rK[] getArrayQuadsCtm(rK quad0, rK quad1) {
      this.arrayQuadsCtm2[0] = quad0;
      this.arrayQuadsCtm2[1] = quad1;
      return this.arrayQuadsCtm2;
   }

   public rK[] getArrayQuadsCtm(rK quad0, rK quad1, rK quad2) {
      this.arrayQuadsCtm3[0] = quad0;
      this.arrayQuadsCtm3[1] = quad1;
      this.arrayQuadsCtm3[2] = quad2;
      return this.arrayQuadsCtm3;
   }

   public rK[] getArrayQuadsCtm(rK quad0, rK quad1, rK quad2, rK quad3) {
      this.arrayQuadsCtm4[0] = quad0;
      this.arrayQuadsCtm4[1] = quad1;
      this.arrayQuadsCtm4[2] = quad2;
      this.arrayQuadsCtm4[3] = quad3;
      return this.arrayQuadsCtm4;
   }

   public List<rK> getListQuadsCtmMultipass(rK[] quads) {
      this.listQuadsCtmMultipass.clear();
      if (quads != null) {
         for(int i = 0; i < quads.length; ++i) {
            rK bakedquad = quads[i];
            this.listQuadsCtmMultipass.add(bakedquad);
         }
      }

      return this.listQuadsCtmMultipass;
   }

   public yu getRegionRenderCacheBuilder() {
      return this.regionRenderCacheBuilder;
   }

   public void setRegionRenderCacheBuilder(yu regionRenderCacheBuilder) {
      this.regionRenderCacheBuilder = regionRenderCacheBuilder;
   }

   public bnd getListQuadsOverlay(BlockRenderLayer layer) {
      bnd listquadsoverlay = this.listsQuadsOverlay[layer.ordinal()];
      if (listquadsoverlay == null) {
         listquadsoverlay = new bnd();
         this.listsQuadsOverlay[layer.ordinal()] = listquadsoverlay;
      }

      return listquadsoverlay;
   }

   public boolean isOverlaysRendered() {
      return this.overlaysRendered;
   }

   public void setOverlaysRendered(boolean overlaysRendered) {
      this.overlaysRendered = overlaysRendered;
   }
}
