package neo;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class wP extends ww<LY> {
   private static final ResourceLocation BROWN = new ResourceLocation("textures/entity/rabbit/brown.png");
   private static final ResourceLocation WHITE = new ResourceLocation("textures/entity/rabbit/white.png");
   private static final ResourceLocation BLACK = new ResourceLocation("textures/entity/rabbit/black.png");
   private static final ResourceLocation GOLD = new ResourceLocation("textures/entity/rabbit/gold.png");
   private static final ResourceLocation SALT = new ResourceLocation("textures/entity/rabbit/salt.png");
   private static final ResourceLocation WHITE_SPLOTCHED = new ResourceLocation("textures/entity/rabbit/white_splotched.png");
   private static final ResourceLocation TOAST = new ResourceLocation("textures/entity/rabbit/toast.png");
   private static final ResourceLocation CAERBANNOG = new ResourceLocation("textures/entity/rabbit/caerbannog.png");

   public wP(wC p_i47196_1_) {
      super(p_i47196_1_, new ov(), 0.3F);
   }

   protected ResourceLocation getEntityTexture(LY entity) {
      String s = TextFormatting.getTextWithoutFormattingCodes(entity.getName());
      if (s != null && "Toast".equals(s)) {
         return TOAST;
      } else {
         switch (entity.getRabbitType()) {
            case 0:
            default:
               return BROWN;
            case 1:
               return WHITE;
            case 2:
               return BLACK;
            case 3:
               return WHITE_SPLOTCHED;
            case 4:
               return GOLD;
            case 5:
               return SALT;
            case 99:
               return CAERBANNOG;
         }
      }
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((LY)var1);
   }
}
