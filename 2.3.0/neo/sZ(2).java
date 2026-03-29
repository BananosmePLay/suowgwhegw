package neo;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;

public class sZ implements sc {
   private final Map<Predicate<in>, sc> selectors;
   protected final boolean ambientOcclusion;
   protected final boolean gui3D;
   protected final zd particleTexture;
   protected final sg cameraTransforms;
   protected final sn overrides;

   public sZ(Map<Predicate<in>, sc> selectorsIn) {
      this.selectors = selectorsIn;
      sc ibakedmodel = (sc)selectorsIn.values().iterator().next();
      this.ambientOcclusion = ibakedmodel.isAmbientOcclusion();
      this.gui3D = ibakedmodel.isGui3d();
      this.particleTexture = ibakedmodel.getParticleTexture();
      this.cameraTransforms = ibakedmodel.getItemCameraTransforms();
      this.overrides = ibakedmodel.getOverrides();
   }

   public List<rK> getQuads(@Nullable in state, @Nullable EnumFacing side, long rand) {
      List<rK> list = Lists.newArrayList();
      if (state != null) {
         Iterator var6 = this.selectors.entrySet().iterator();

         while(var6.hasNext()) {
            Map.Entry<Predicate<in>, sc> entry = (Map.Entry)var6.next();
            if (((Predicate)entry.getKey()).apply(state)) {
               list.addAll(((sc)entry.getValue()).getQuads(state, side, rand++));
            }
         }
      }

      return list;
   }

   public boolean isAmbientOcclusion() {
      return this.ambientOcclusion;
   }

   public boolean isGui3d() {
      return this.gui3D;
   }

   public boolean isBuiltInRenderer() {
      return false;
   }

   public zd getParticleTexture() {
      return this.particleTexture;
   }

   public sg getItemCameraTransforms() {
      return this.cameraTransforms;
   }

   public sn getOverrides() {
      return this.overrides;
   }
}
