package neo;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import javax.annotation.Nullable;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class bl {
   public static bl ANY;
   private final bm x;
   private final bm y;
   private final bm z;
   @Nullable
   final Zi biome;
   @Nullable
   private final String feature;
   @Nullable
   private final baM dimension;

   public bl(bm x, bm y, bm z, @Nullable Zi biome, @Nullable String feature, @Nullable baM dimension) {
      this.x = x;
      this.y = y;
      this.z = z;
      this.biome = biome;
      this.feature = feature;
      this.dimension = dimension;
   }

   public boolean test(bis world, double x, double y, double z) {
      return this.test(world, (float)x, (float)y, (float)z);
   }

   public boolean test(bis world, float x, float y, float z) {
      if (!this.x.test(x)) {
         return false;
      } else if (!this.y.test(y)) {
         return false;
      } else if (!this.z.test(z)) {
         return false;
      } else if (this.dimension != null && this.dimension != world.provider.getDimensionType()) {
         return false;
      } else {
         BlockPos blockpos = new BlockPos((double)x, (double)y, (double)z);
         if (this.biome != null && this.biome != world.getBiome(blockpos)) {
            return false;
         } else {
            return this.feature == null || world.getChunkProvider().isInsideStructure(world, this.feature, blockpos);
         }
      }
   }

   public static bl deserialize(@Nullable JsonElement element) {
      if (element != null && !element.isJsonNull()) {
         JsonObject jsonobject = JsonUtils.getJsonObject(element, "location");
         JsonObject jsonobject1 = JsonUtils.getJsonObject(jsonobject, "position", new JsonObject());
         bm minmaxbounds = bm.deserialize(jsonobject1.get("x"));
         bm minmaxbounds1 = bm.deserialize(jsonobject1.get("y"));
         bm minmaxbounds2 = bm.deserialize(jsonobject1.get("z"));
         baM dimensiontype = jsonobject.has("dimension") ? baM.byName(JsonUtils.getString(jsonobject, "dimension")) : null;
         String s = jsonobject.has("feature") ? JsonUtils.getString(jsonobject, "feature") : null;
         Zi biome = null;
         if (jsonobject.has("biome")) {
            ResourceLocation resourcelocation = new ResourceLocation(JsonUtils.getString(jsonobject, "biome"));
            biome = (Zi)Zi.REGISTRY.getObject(resourcelocation);
            if (biome == null) {
               throw new JsonSyntaxException("Unknown biome '" + resourcelocation + "'");
            }
         }

         return new bl(minmaxbounds, minmaxbounds1, minmaxbounds2, biome, s, dimensiontype);
      } else {
         return ANY;
      }
   }

   static {
      ANY = new bl(bm.UNBOUNDED, bm.UNBOUNDED, bm.UNBOUNDED, (Zi)null, (String)null, (baM)null);
   }
}
