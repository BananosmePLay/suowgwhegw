package neo;

import net.minecraft.util.EnumFacing;
import org.lwjgl.util.vector.Vector3f;

public class rT {
   public final Vector3f origin;
   public final EnumFacing.Axis axis;
   public final float angle;
   public final boolean rescale;

   public rT(Vector3f originIn, EnumFacing.Axis axisIn, float angleIn, boolean rescaleIn) {
      this.origin = originIn;
      this.axis = axisIn;
      this.angle = angleIn;
      this.rescale = rescaleIn;
   }
}
