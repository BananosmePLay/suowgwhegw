package neo;

import java.util.UUID;
import net.minecraft.util.ResourceLocation;

class vo {
   public UUID entityId;
   public wy<? extends Iw> renderer;
   public nH model;
   public ResourceLocation textureLocation;
   public Class<?> clazz;
   // $FF: synthetic field
   final vp this$0;

   public vo(vp this$0, UUID p_i47463_2_, wy p_i47463_3_, nH p_i47463_4_, ResourceLocation p_i47463_5_, Class p_i47463_6_) {
      this.this$0 = this$0;
      this.entityId = p_i47463_2_;
      this.renderer = p_i47463_3_;
      this.model = p_i47463_4_;
      this.textureLocation = p_i47463_5_;
      this.clazz = p_i47463_6_;
   }
}
