package neo;

import net.minecraft.util.math.MathHelper;

public class VR {
   public final int x;
   public final int y;
   public final int z;
   private final int hash;
   public int index = -1;
   public float totalPathDistance;
   public float distanceToNext;
   public float distanceToTarget;
   public VR previous;
   public boolean visited;
   public float distanceFromOrigin;
   public float cost;
   public float costMalus;
   public VQ nodeType;

   public VR(int x, int y, int z) {
      this.nodeType = VQ.BLOCKED;
      this.x = x;
      this.y = y;
      this.z = z;
      this.hash = makeHash(x, y, z);
   }

   public VR cloneMove(int x, int y, int z) {
      VR pathpoint = new VR(x, y, z);
      pathpoint.index = this.index;
      pathpoint.totalPathDistance = this.totalPathDistance;
      pathpoint.distanceToNext = this.distanceToNext;
      pathpoint.distanceToTarget = this.distanceToTarget;
      pathpoint.previous = this.previous;
      pathpoint.visited = this.visited;
      pathpoint.distanceFromOrigin = this.distanceFromOrigin;
      pathpoint.cost = this.cost;
      pathpoint.costMalus = this.costMalus;
      pathpoint.nodeType = this.nodeType;
      return pathpoint;
   }

   public static int makeHash(int x, int y, int z) {
      return y & 255 | (x & 32767) << 8 | (z & 32767) << 24 | (x < 0 ? Integer.MIN_VALUE : 0) | (z < 0 ? '耀' : 0);
   }

   public float distanceTo(VR pathpointIn) {
      float f = (float)(pathpointIn.x - this.x);
      float f1 = (float)(pathpointIn.y - this.y);
      float f2 = (float)(pathpointIn.z - this.z);
      return MathHelper.sqrt(f * f + f1 * f1 + f2 * f2);
   }

   public float distanceToSquared(VR pathpointIn) {
      float f = (float)(pathpointIn.x - this.x);
      float f1 = (float)(pathpointIn.y - this.y);
      float f2 = (float)(pathpointIn.z - this.z);
      return f * f + f1 * f1 + f2 * f2;
   }

   public float distanceManhattan(VR p_186281_1_) {
      float f = (float)Math.abs(p_186281_1_.x - this.x);
      float f1 = (float)Math.abs(p_186281_1_.y - this.y);
      float f2 = (float)Math.abs(p_186281_1_.z - this.z);
      return f + f1 + f2;
   }

   public boolean equals(Object p_equals_1_) {
      if (!(p_equals_1_ instanceof VR)) {
         return false;
      } else {
         VR pathpoint = (VR)p_equals_1_;
         return this.hash == pathpoint.hash && this.x == pathpoint.x && this.y == pathpoint.y && this.z == pathpoint.z;
      }
   }

   public int hashCode() {
      return this.hash;
   }

   public boolean isAssigned() {
      return this.index >= 0;
   }

   public String toString() {
      return this.x + ", " + this.y + ", " + this.z;
   }

   public static VR createFromBuffer(SA buf) {
      VR pathpoint = new VR(buf.readInt(), buf.readInt(), buf.readInt());
      pathpoint.distanceFromOrigin = buf.readFloat();
      pathpoint.cost = buf.readFloat();
      pathpoint.costMalus = buf.readFloat();
      pathpoint.visited = buf.readBoolean();
      pathpoint.nodeType = VQ.values()[buf.readInt()];
      pathpoint.distanceToTarget = buf.readFloat();
      return pathpoint;
   }
}
