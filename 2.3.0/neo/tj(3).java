package neo;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.WeightedRandom;

public class tj implements sc {
   private final int totalWeight;
   private final List<ti> models;
   private final sc baseModel;

   public tj(List<ti> modelsIn) {
      this.models = modelsIn;
      this.totalWeight = WeightedRandom.getTotalWeight(modelsIn);
      this.baseModel = ((ti)modelsIn.get(0)).model;
   }

   private sc getRandomModel(long p_188627_1_) {
      return ((ti)WeightedRandom.getRandomItem(this.models, Math.abs((int)p_188627_1_ >> 16) % this.totalWeight)).model;
   }

   public List<rK> getQuads(@Nullable in state, @Nullable EnumFacing side, long rand) {
      return this.getRandomModel(rand).getQuads(state, side, rand);
   }

   public boolean isAmbientOcclusion() {
      return this.baseModel.isAmbientOcclusion();
   }

   public boolean isGui3d() {
      return this.baseModel.isGui3d();
   }

   public boolean isBuiltInRenderer() {
      return this.baseModel.isBuiltInRenderer();
   }

   public zd getParticleTexture() {
      return this.baseModel.getParticleTexture();
   }

   public sg getItemCameraTransforms() {
      return this.baseModel.getItemCameraTransforms();
   }

   public sn getOverrides() {
      return this.baseModel.getOverrides();
   }
}
