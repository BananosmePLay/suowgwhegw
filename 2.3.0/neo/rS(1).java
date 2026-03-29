package neo;

import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;

public class rS {
   public static final EnumFacing FACING_DEFAULT = null;
   public final EnumFacing cullFace;
   public final int tintIndex;
   public final String texture;
   public final rN blockFaceUV;

   public rS(@Nullable EnumFacing cullFaceIn, int tintIndexIn, String textureIn, rN blockFaceUVIn) {
      this.cullFace = cullFaceIn;
      this.tintIndex = tintIndexIn;
      this.texture = textureIn;
      this.blockFaceUV = blockFaceUVIn;
   }
}
