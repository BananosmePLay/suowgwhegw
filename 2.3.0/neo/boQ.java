package neo;

public class boQ extends jK {
   private bog enumShaderOption = null;

   public boQ(bog enumShaderOption, int x, int y, int widthIn, int heightIn) {
      super(enumShaderOption.ordinal(), x, y, widthIn, heightIn, getButtonText(enumShaderOption));
      this.enumShaderOption = enumShaderOption;
   }

   public bog getEnumShaderOption() {
      return this.enumShaderOption;
   }

   private static String getButtonText(bog eso) {
      String s = Ax.format(eso.getResourceKey()) + ": ";
      switch (eso) {
         case ANTIALIASING:
            return s + boU.toStringAa(bpq.configAntialiasingLevel);
         case NORMAL_MAP:
            return s + boU.toStringOnOff(bpq.configNormalMap);
         case SPECULAR_MAP:
            return s + boU.toStringOnOff(bpq.configSpecularMap);
         case RENDER_RES_MUL:
            return s + boU.toStringQuality(bpq.configRenderResMul);
         case SHADOW_RES_MUL:
            return s + boU.toStringQuality(bpq.configShadowResMul);
         case HAND_DEPTH_MUL:
            return s + boU.toStringHandDepth(bpq.configHandDepthMul);
         case CLOUD_SHADOW:
            return s + boU.toStringOnOff(bpq.configCloudShadow);
         case OLD_HAND_LIGHT:
            return s + bpq.configOldHandLight.getUserValue();
         case OLD_LIGHTING:
            return s + bpq.configOldLighting.getUserValue();
         case SHADOW_CLIP_FRUSTRUM:
            return s + boU.toStringOnOff(bpq.configShadowClipFrustrum);
         case TWEAK_BLOCK_DAMAGE:
            return s + boU.toStringOnOff(bpq.configTweakBlockDamage);
         default:
            return s + bpq.getEnumShaderOption(eso);
      }
   }

   public void updateButtonText() {
      this.displayString = getButtonText(this.enumShaderOption);
   }
}
