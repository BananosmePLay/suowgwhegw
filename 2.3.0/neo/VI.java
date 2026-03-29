package neo;

import javax.annotation.Nullable;
import net.minecraft.util.math.Vec3d;

public class VI {
   private final VR[] points;
   private VR[] openSet = new VR[0];
   private VR[] closedSet = new VR[0];
   private VR target;
   private int currentPathIndex;
   private int pathLength;

   public VI(VR[] pathpoints) {
      this.points = pathpoints;
      this.pathLength = pathpoints.length;
   }

   public void incrementPathIndex() {
      ++this.currentPathIndex;
   }

   public boolean isFinished() {
      return this.currentPathIndex >= this.pathLength;
   }

   @Nullable
   public VR getFinalPathPoint() {
      return this.pathLength > 0 ? this.points[this.pathLength - 1] : null;
   }

   public VR getPathPointFromIndex(int index) {
      return this.points[index];
   }

   public void setPoint(int index, VR point) {
      this.points[index] = point;
   }

   public int getCurrentPathLength() {
      return this.pathLength;
   }

   public void setCurrentPathLength(int length) {
      this.pathLength = length;
   }

   public int getCurrentPathIndex() {
      return this.currentPathIndex;
   }

   public void setCurrentPathIndex(int currentPathIndexIn) {
      this.currentPathIndex = currentPathIndexIn;
   }

   public Vec3d getVectorFromIndex(Ig entityIn, int index) {
      double d0 = (double)this.points[index].x + (double)((int)(entityIn.width + 1.0F)) * 0.5;
      double d1 = (double)this.points[index].y;
      double d2 = (double)this.points[index].z + (double)((int)(entityIn.width + 1.0F)) * 0.5;
      return new Vec3d(d0, d1, d2);
   }

   public Vec3d getPosition(Ig entityIn) {
      return this.getVectorFromIndex(entityIn, this.currentPathIndex);
   }

   public Vec3d getCurrentPos() {
      VR pathpoint = this.points[this.currentPathIndex];
      return new Vec3d((double)pathpoint.x, (double)pathpoint.y, (double)pathpoint.z);
   }

   public boolean isSamePath(VI pathentityIn) {
      if (pathentityIn == null) {
         return false;
      } else if (pathentityIn.points.length != this.points.length) {
         return false;
      } else {
         for(int i = 0; i < this.points.length; ++i) {
            if (this.points[i].x != pathentityIn.points[i].x || this.points[i].y != pathentityIn.points[i].y || this.points[i].z != pathentityIn.points[i].z) {
               return false;
            }
         }

         return true;
      }
   }

   public VR[] getOpenSet() {
      return this.openSet;
   }

   public VR[] getClosedSet() {
      return this.closedSet;
   }

   public VR getTarget() {
      return this.target;
   }

   public static VI read(SA buf) {
      int i = buf.readInt();
      VR pathpoint = VR.createFromBuffer(buf);
      VR[] apathpoint = new VR[buf.readInt()];

      for(int j = 0; j < apathpoint.length; ++j) {
         apathpoint[j] = VR.createFromBuffer(buf);
      }

      VR[] apathpoint1 = new VR[buf.readInt()];

      for(int k = 0; k < apathpoint1.length; ++k) {
         apathpoint1[k] = VR.createFromBuffer(buf);
      }

      VR[] apathpoint2 = new VR[buf.readInt()];

      for(int l = 0; l < apathpoint2.length; ++l) {
         apathpoint2[l] = VR.createFromBuffer(buf);
      }

      VI path = new VI(apathpoint);
      path.openSet = apathpoint1;
      path.closedSet = apathpoint2;
      path.target = pathpoint;
      path.currentPathIndex = i;
      return path;
   }
}
