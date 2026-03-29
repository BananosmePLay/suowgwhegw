package neo;

import net.minecraft.util.math.AxisAlignedBB;

public class bnU extends AxisAlignedBB {
   private int frameCount = -1;
   private boolean inFrustumFully = false;

   public bnU(double x1, double y1, double z1, double x2, double y2, double z2) {
      super(x1, y1, z1, x2, y2, z2);
   }

   public boolean isBoundingBoxInFrustumFully(uO camera, int frameCount) {
      if (this.frameCount != frameCount) {
         this.inFrustumFully = camera instanceof uN ? ((uN)camera).isBoxInFrustumFully(this.minX, this.minY, this.minZ, this.maxX, this.maxY, this.maxZ) : false;
         this.frameCount = frameCount;
      }

      return this.inFrustumFully;
   }
}
