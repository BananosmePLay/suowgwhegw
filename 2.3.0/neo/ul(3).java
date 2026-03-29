package neo;

import com.google.common.collect.Lists;
import java.util.BitSet;
import java.util.List;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;

public abstract class ul {
   private double viewEntityX;
   private double viewEntityY;
   private double viewEntityZ;
   protected List<ug> renderChunks = Lists.newArrayListWithCapacity(17424);
   protected boolean initialized;
   private BitSet animatedSpritesRendered;
   private final BitSet animatedSpritesCached = new BitSet();

   public ul() {
   }

   public void initialize(double viewEntityXIn, double viewEntityYIn, double viewEntityZIn) {
      this.initialized = true;
      this.renderChunks.clear();
      this.viewEntityX = viewEntityXIn;
      this.viewEntityY = viewEntityYIn;
      this.viewEntityZ = viewEntityZIn;
      if (bpW.isActive()) {
         if (this.animatedSpritesRendered != null) {
            bpW.spritesRendered(this.animatedSpritesRendered);
         } else {
            this.animatedSpritesRendered = this.animatedSpritesCached;
         }

         this.animatedSpritesRendered.clear();
      } else if (this.animatedSpritesRendered != null) {
         bpW.spritesRendered(this.animatedSpritesRendered);
         this.animatedSpritesRendered = null;
      }

   }

   public void preRenderChunk(ug renderChunkIn) {
      BlockPos blockpos = renderChunkIn.getPosition();
      yh.translate((float)((double)blockpos.getX() - this.viewEntityX), (float)((double)blockpos.getY() - this.viewEntityY), (float)((double)blockpos.getZ() - this.viewEntityZ));
   }

   public void addRenderChunk(ug renderChunkIn, BlockRenderLayer layer) {
      this.renderChunks.add(renderChunkIn);
      if (this.animatedSpritesRendered != null) {
         BitSet bitset = renderChunkIn.compiledChunk.getAnimatedSprites(layer);
         if (bitset != null) {
            this.animatedSpritesRendered.or(bitset);
         }
      }

   }

   public abstract void renderChunkLayer(BlockRenderLayer var1);
}
