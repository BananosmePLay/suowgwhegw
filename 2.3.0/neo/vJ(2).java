package neo;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.util.ResourceLocation;

public class vJ extends ww<Lw> {
   private static final Map<Class<?>, ResourceLocation> MAP = Maps.newHashMap();
   private final float scale;

   public vJ(wC manager) {
      this(manager, 1.0F);
   }

   public vJ(wC renderManagerIn, float scaleIn) {
      super(renderManagerIn, new oe(), 0.75F);
      this.scale = scaleIn;
   }

   protected void preRenderCallback(Lw entitylivingbaseIn, float partialTickTime) {
      yh.scale(this.scale, this.scale, this.scale);
      super.preRenderCallback(entitylivingbaseIn, partialTickTime);
   }

   protected ResourceLocation getEntityTexture(Lw entity) {
      return (ResourceLocation)MAP.get(entity.getClass());
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void preRenderCallback(Iw var1, float var2) {
      this.preRenderCallback((Lw)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((Lw)var1);
   }

   static {
      MAP.put(LC.class, new ResourceLocation("textures/entity/horse/donkey.png"));
      MAP.put(LM.class, new ResourceLocation("textures/entity/horse/mule.png"));
      MAP.put(Mv.class, new ResourceLocation("textures/entity/horse/horse_zombie.png"));
      MAP.put(Md.class, new ResourceLocation("textures/entity/horse/horse_skeleton.png"));
   }
}
