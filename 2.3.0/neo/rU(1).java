package neo;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;

public class rU implements sc {
   private final sg cameraTransforms;
   private final sn overrideList;

   public rU(sg p_i46537_1_, sn p_i46537_2_) {
      this.cameraTransforms = p_i46537_1_;
      this.overrideList = p_i46537_2_;
   }

   public List<rK> getQuads(@Nullable in state, @Nullable EnumFacing side, long rand) {
      return Collections.emptyList();
   }

   public boolean isAmbientOcclusion() {
      return false;
   }

   public boolean isGui3d() {
      return true;
   }

   public boolean isBuiltInRenderer() {
      return true;
   }

   public zd getParticleTexture() {
      return null;
   }

   public sg getItemCameraTransforms() {
      return this.cameraTransforms;
   }

   public sn getOverrides() {
      return this.overrideList;
   }
}
