package neo;

import com.google.gson.JsonObject;
import javax.annotation.Nullable;
import net.minecraft.util.JsonUtils;

public class bn {
   private final bm amplifier;
   private final bm duration;
   @Nullable
   private final Boolean ambient;
   @Nullable
   private final Boolean visible;

   public bn(bm amplifier, bm duration, @Nullable Boolean ambient, @Nullable Boolean visible) {
      this.amplifier = amplifier;
      this.duration = duration;
      this.ambient = ambient;
      this.visible = visible;
   }

   public boolean test(@Nullable VZ effect) {
      if (effect == null) {
         return false;
      } else if (!this.amplifier.test((float)effect.getAmplifier())) {
         return false;
      } else if (!this.duration.test((float)effect.getDuration())) {
         return false;
      } else if (this.ambient != null && this.ambient != effect.getIsAmbient()) {
         return false;
      } else {
         return this.visible == null || this.visible == effect.doesShowParticles();
      }
   }

   public static bn deserialize(JsonObject object) {
      bm minmaxbounds = bm.deserialize(object.get("amplifier"));
      bm minmaxbounds1 = bm.deserialize(object.get("duration"));
      Boolean obool = object.has("ambient") ? JsonUtils.getBoolean(object, "ambient") : null;
      Boolean obool1 = object.has("visible") ? JsonUtils.getBoolean(object, "visible") : null;
      return new bn(minmaxbounds, minmaxbounds1, obool, obool1);
   }
}
