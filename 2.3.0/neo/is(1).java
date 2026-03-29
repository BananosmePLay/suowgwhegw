package neo;

import com.google.common.base.MoreObjects;
import com.google.common.cache.LoadingCache;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class is {
   private final BlockPos frontTopLeft;
   private final EnumFacing forwards;
   private final EnumFacing up;
   private final LoadingCache<BlockPos, ik> lcache;
   private final int width;
   private final int height;
   private final int depth;

   public is(BlockPos posIn, EnumFacing fingerIn, EnumFacing thumbIn, LoadingCache<BlockPos, ik> lcacheIn, int widthIn, int heightIn, int depthIn) {
      this.frontTopLeft = posIn;
      this.forwards = fingerIn;
      this.up = thumbIn;
      this.lcache = lcacheIn;
      this.width = widthIn;
      this.height = heightIn;
      this.depth = depthIn;
   }

   public BlockPos getFrontTopLeft() {
      return this.frontTopLeft;
   }

   public EnumFacing getForwards() {
      return this.forwards;
   }

   public EnumFacing getUp() {
      return this.up;
   }

   public int getWidth() {
      return this.width;
   }

   public int getHeight() {
      return this.height;
   }

   public ik translateOffset(int palmOffset, int thumbOffset, int fingerOffset) {
      return (ik)this.lcache.getUnchecked(it.translateOffset(this.frontTopLeft, this.getForwards(), this.getUp(), palmOffset, thumbOffset, fingerOffset));
   }

   public String toString() {
      return MoreObjects.toStringHelper(this).add("up", this.up).add("forwards", this.forwards).add("frontTopLeft", this.frontTopLeft).toString();
   }
}
