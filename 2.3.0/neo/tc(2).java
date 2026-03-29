package neo;

import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;

public class tc implements sc {
   protected final List<rK> generalQuads;
   protected final Map<EnumFacing, List<rK>> faceQuads;
   protected final boolean ambientOcclusion;
   protected final boolean gui3d;
   protected final zd texture;
   protected final sg cameraTransforms;
   protected final sn itemOverrideList;

   public tc(List<rK> generalQuadsIn, Map<EnumFacing, List<rK>> faceQuadsIn, boolean ambientOcclusionIn, boolean gui3dIn, zd textureIn, sg cameraTransformsIn, sn itemOverrideListIn) {
      this.generalQuads = generalQuadsIn;
      this.faceQuads = faceQuadsIn;
      this.ambientOcclusion = ambientOcclusionIn;
      this.gui3d = gui3dIn;
      this.texture = textureIn;
      this.cameraTransforms = cameraTransformsIn;
      this.itemOverrideList = itemOverrideListIn;
   }

   public List<rK> getQuads(@Nullable in state, @Nullable EnumFacing side, long rand) {
      return side == null ? this.generalQuads : (List)this.faceQuads.get(side);
   }

   public boolean isAmbientOcclusion() {
      return this.ambientOcclusion;
   }

   public boolean isGui3d() {
      return this.gui3d;
   }

   public boolean isBuiltInRenderer() {
      return false;
   }

   public zd getParticleTexture() {
      return this.texture;
   }

   public sg getItemCameraTransforms() {
      return this.cameraTransforms;
   }

   public sn getOverrides() {
      return this.itemOverrideList;
   }
}
