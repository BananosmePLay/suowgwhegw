package neo;

import java.util.List;
import net.minecraft.util.EnumFacing;

public interface sc {
   List<rK> getQuads(in var1, EnumFacing var2, long var3);

   boolean isAmbientOcclusion();

   boolean isGui3d();

   boolean isBuiltInRenderer();

   zd getParticleTexture();

   sg getItemCameraTransforms();

   sn getOverrides();
}
