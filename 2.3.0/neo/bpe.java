package neo;

import java.util.Iterator;
import net.minecraft.util.math.BlockPos;

public class bpe implements Iterator<ug> {
   private zT viewFrustum;
   private bpc Iterator3d;
   private biN posBlock = new biN(0, 0, 0);

   public bpe(zT viewFrustum, BlockPos posStart, BlockPos posEnd, int width, int height) {
      this.viewFrustum = viewFrustum;
      this.Iterator3d = new bpc(posStart, posEnd, width, height);
   }

   public boolean hasNext() {
      return this.Iterator3d.hasNext();
   }

   public ug next() {
      BlockPos blockpos = this.Iterator3d.next();
      this.posBlock.setXyz(blockpos.getX() << 4, blockpos.getY() << 4, blockpos.getZ() << 4);
      ug renderchunk = this.viewFrustum.getRenderChunk(this.posBlock);
      return renderchunk;
   }

   public void remove() {
      throw new RuntimeException("Not implemented");
   }

   // $FF: synthetic method
   // $FF: bridge method
   public Object next() {
      return this.next();
   }
}
