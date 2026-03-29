package neo;

import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import org.lwjgl.util.vector.Vector3f;

public class rQ {
   public final Vector3f positionFrom;
   public final Vector3f positionTo;
   public final Map<EnumFacing, rS> mapFaces;
   public final rT partRotation;
   public final boolean shade;

   public rQ(Vector3f positionFromIn, Vector3f positionToIn, Map<EnumFacing, rS> mapFacesIn, @Nullable rT partRotationIn, boolean shadeIn) {
      this.positionFrom = positionFromIn;
      this.positionTo = positionToIn;
      this.mapFaces = mapFacesIn;
      this.partRotation = partRotationIn;
      this.shade = shadeIn;
      this.setDefaultUvs();
   }

   private void setDefaultUvs() {
      Iterator var1 = this.mapFaces.entrySet().iterator();

      while(var1.hasNext()) {
         Map.Entry<EnumFacing, rS> entry = (Map.Entry)var1.next();
         float[] afloat = this.getFaceUvs((EnumFacing)entry.getKey());
         ((rS)entry.getValue()).blockFaceUV.setUvs(afloat);
      }

   }

   private float[] getFaceUvs(EnumFacing facing) {
      switch (facing) {
         case DOWN:
            return new float[]{this.positionFrom.x, 16.0F - this.positionTo.z, this.positionTo.x, 16.0F - this.positionFrom.z};
         case UP:
            return new float[]{this.positionFrom.x, this.positionFrom.z, this.positionTo.x, this.positionTo.z};
         case NORTH:
         default:
            return new float[]{16.0F - this.positionTo.x, 16.0F - this.positionTo.y, 16.0F - this.positionFrom.x, 16.0F - this.positionFrom.y};
         case SOUTH:
            return new float[]{this.positionFrom.x, 16.0F - this.positionTo.y, this.positionTo.x, 16.0F - this.positionFrom.y};
         case WEST:
            return new float[]{this.positionFrom.z, 16.0F - this.positionTo.y, this.positionTo.z, 16.0F - this.positionFrom.y};
         case EAST:
            return new float[]{16.0F - this.positionTo.z, 16.0F - this.positionTo.y, 16.0F - this.positionFrom.z, 16.0F - this.positionFrom.y};
      }
   }
}
