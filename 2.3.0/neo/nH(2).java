package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.Random;

public abstract class nH {
   public float swingProgress;
   public boolean isRiding;
   public boolean isChild = true;
   public List<ow> boxList = Lists.newArrayList();
   private final Map<String, oU> modelTextureMap = Maps.newHashMap();
   public int textureWidth = 64;
   public int textureHeight = 32;

   public nH() {
   }

   public void render(Ig entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
   }

   public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Ig entityIn) {
   }

   public void setLivingAnimations(Iw entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
   }

   public ow getRandomModelBox(Random rand) {
      return (ow)this.boxList.get(rand.nextInt(this.boxList.size()));
   }

   protected void setTextureOffset(String partName, int x, int y) {
      this.modelTextureMap.put(partName, new oU(x, y));
   }

   public oU getTextureOffset(String partName) {
      return (oU)this.modelTextureMap.get(partName);
   }

   public static void copyModelAngles(ow source, ow dest) {
      dest.rotateAngleX = source.rotateAngleX;
      dest.rotateAngleY = source.rotateAngleY;
      dest.rotateAngleZ = source.rotateAngleZ;
      dest.rotationPointX = source.rotationPointX;
      dest.rotationPointY = source.rotationPointY;
      dest.rotationPointZ = source.rotationPointZ;
   }

   public void setModelAttributes(nH model) {
      this.swingProgress = model.swingProgress;
      this.isRiding = model.isRiding;
      this.isChild = model.isChild;
   }
}
