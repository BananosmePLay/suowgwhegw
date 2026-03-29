package neo;

import org.lwjgl.util.vector.Vector3f;

public class sp {
   public static final sp DEFAULT = new sp(new Vector3f(), new Vector3f(), new Vector3f(1.0F, 1.0F, 1.0F));
   public final Vector3f rotation;
   public final Vector3f translation;
   public final Vector3f scale;

   public sp(Vector3f rotation, Vector3f translation, Vector3f scale) {
      this.rotation = new Vector3f(rotation);
      this.translation = new Vector3f(translation);
      this.scale = new Vector3f(scale);
   }

   public boolean equals(Object p_equals_1_) {
      if (this == p_equals_1_) {
         return true;
      } else if (this.getClass() != p_equals_1_.getClass()) {
         return false;
      } else {
         sp itemtransformvec3f = (sp)p_equals_1_;
         return this.rotation.equals(itemtransformvec3f.rotation) && this.scale.equals(itemtransformvec3f.scale) && this.translation.equals(itemtransformvec3f.translation);
      }
   }

   public int hashCode() {
      int i = this.rotation.hashCode();
      i = 31 * i + this.translation.hashCode();
      i = 31 * i + this.scale.hashCode();
      return i;
   }
}
