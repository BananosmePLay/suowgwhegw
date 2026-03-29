package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class tb {
   private final List<rK> builderGeneralQuads;
   private final Map<EnumFacing, List<rK>> builderFaceQuads;
   private final sn builderItemOverrideList;
   private final boolean builderAmbientOcclusion;
   private zd builderTexture;
   private final boolean builderGui3d;
   private final sg builderCameraTransforms;

   public tb(sy model, sn overrides) {
      this(model.isAmbientOcclusion(), model.isGui3d(), model.getAllTransforms(), overrides);
   }

   public tb(in state, sc model, zd texture, BlockPos pos) {
      this(model.isAmbientOcclusion(), model.isGui3d(), model.getItemCameraTransforms(), model.getOverrides());
      this.builderTexture = model.getParticleTexture();
      long i = MathHelper.getPositionRandom(pos);
      EnumFacing[] var7 = EnumFacing.values();
      int var8 = var7.length;

      for(int var9 = 0; var9 < var8; ++var9) {
         EnumFacing enumfacing = var7[var9];
         this.addFaceQuads(state, model, texture, enumfacing, i);
      }

      this.addGeneralQuads(state, model, texture, i);
   }

   private tb(boolean ambientOcclusion, boolean gui3d, sg transforms, sn overrides) {
      this.builderGeneralQuads = Lists.newArrayList();
      this.builderFaceQuads = Maps.newEnumMap(EnumFacing.class);
      EnumFacing[] var5 = EnumFacing.values();
      int var6 = var5.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         EnumFacing enumfacing = var5[var7];
         this.builderFaceQuads.put(enumfacing, Lists.newArrayList());
      }

      this.builderItemOverrideList = overrides;
      this.builderAmbientOcclusion = ambientOcclusion;
      this.builderGui3d = gui3d;
      this.builderCameraTransforms = transforms;
   }

   private void addFaceQuads(in p_188644_1_, sc p_188644_2_, zd p_188644_3_, EnumFacing p_188644_4_, long p_188644_5_) {
      Iterator var7 = p_188644_2_.getQuads(p_188644_1_, p_188644_4_, p_188644_5_).iterator();

      while(var7.hasNext()) {
         rK bakedquad = (rK)var7.next();
         this.addFaceQuad(p_188644_4_, new rL(bakedquad, p_188644_3_));
      }

   }

   private void addGeneralQuads(in p_188645_1_, sc p_188645_2_, zd p_188645_3_, long p_188645_4_) {
      Iterator var6 = p_188645_2_.getQuads(p_188645_1_, (EnumFacing)null, p_188645_4_).iterator();

      while(var6.hasNext()) {
         rK bakedquad = (rK)var6.next();
         this.addGeneralQuad(new rL(bakedquad, p_188645_3_));
      }

   }

   public tb addFaceQuad(EnumFacing facing, rK quad) {
      ((List)this.builderFaceQuads.get(facing)).add(quad);
      return this;
   }

   public tb addGeneralQuad(rK quad) {
      this.builderGeneralQuads.add(quad);
      return this;
   }

   public tb setTexture(zd texture) {
      this.builderTexture = texture;
      return this;
   }

   public sc makeBakedModel() {
      if (this.builderTexture == null) {
         throw new RuntimeException("Missing particle!");
      } else {
         return new tc(this.builderGeneralQuads, this.builderFaceQuads, this.builderAmbientOcclusion, this.builderGui3d, this.builderTexture, this.builderCameraTransforms, this.builderItemOverrideList);
      }
   }
}
