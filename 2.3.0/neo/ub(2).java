package neo;

import com.google.common.collect.Lists;
import java.util.BitSet;
import java.util.List;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;

public class ub {
   public static final ub DUMMY = new ub() {
      protected void setLayerUsed(BlockRenderLayer layer) {
         throw new UnsupportedOperationException();
      }

      public void setLayerStarted(BlockRenderLayer layer) {
         throw new UnsupportedOperationException();
      }

      public boolean isVisible(EnumFacing facing, EnumFacing facing2) {
         return false;
      }

      public void setAnimatedSprites(BlockRenderLayer p_setAnimatedSprites_1_, BitSet p_setAnimatedSprites_2_) {
         throw new UnsupportedOperationException();
      }
   };
   private final boolean[] layersUsed;
   private final boolean[] layersStarted;
   private boolean empty;
   private final List<Yg> tileEntities;
   private uh setVisibility;
   private tM state;
   private BitSet[] animatedSprites;

   public ub() {
      this.layersUsed = new boolean[ug.ENUM_WORLD_BLOCK_LAYERS.length];
      this.layersStarted = new boolean[ug.ENUM_WORLD_BLOCK_LAYERS.length];
      this.empty = true;
      this.tileEntities = Lists.newArrayList();
      this.setVisibility = new uh();
      this.animatedSprites = new BitSet[ug.ENUM_WORLD_BLOCK_LAYERS.length];
   }

   public boolean isEmpty() {
      return this.empty;
   }

   protected void setLayerUsed(BlockRenderLayer layer) {
      this.empty = false;
      this.layersUsed[layer.ordinal()] = true;
   }

   public boolean isLayerEmpty(BlockRenderLayer layer) {
      return !this.layersUsed[layer.ordinal()];
   }

   public void setLayerStarted(BlockRenderLayer layer) {
      this.layersStarted[layer.ordinal()] = true;
   }

   public boolean isLayerStarted(BlockRenderLayer layer) {
      return this.layersStarted[layer.ordinal()];
   }

   public List<Yg> getTileEntities() {
      return this.tileEntities;
   }

   public void addTileEntity(Yg tileEntityIn) {
      this.tileEntities.add(tileEntityIn);
   }

   public boolean isVisible(EnumFacing facing, EnumFacing facing2) {
      return this.setVisibility.isVisible(facing, facing2);
   }

   public void setVisibility(uh visibility) {
      this.setVisibility = visibility;
   }

   public tM getState() {
      return this.state;
   }

   public void setState(tM stateIn) {
      this.state = stateIn;
   }

   public BitSet getAnimatedSprites(BlockRenderLayer p_getAnimatedSprites_1_) {
      return this.animatedSprites[p_getAnimatedSprites_1_.ordinal()];
   }

   public void setAnimatedSprites(BlockRenderLayer p_setAnimatedSprites_1_, BitSet p_setAnimatedSprites_2_) {
      this.animatedSprites[p_setAnimatedSprites_1_.ordinal()] = p_setAnimatedSprites_2_;
   }
}
