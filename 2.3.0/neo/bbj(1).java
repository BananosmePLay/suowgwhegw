package neo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import net.minecraft.util.JsonUtils;

public class bbj implements JsonDeserializer<bbi>, JsonSerializer<bbi> {
   public bbj() {
   }

   public bbi deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
      JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
      bbi chunkgeneratorsettings$factory = new bbi();

      try {
         chunkgeneratorsettings$factory.coordinateScale = JsonUtils.getFloat(jsonobject, "coordinateScale", chunkgeneratorsettings$factory.coordinateScale);
         chunkgeneratorsettings$factory.heightScale = JsonUtils.getFloat(jsonobject, "heightScale", chunkgeneratorsettings$factory.heightScale);
         chunkgeneratorsettings$factory.lowerLimitScale = JsonUtils.getFloat(jsonobject, "lowerLimitScale", chunkgeneratorsettings$factory.lowerLimitScale);
         chunkgeneratorsettings$factory.upperLimitScale = JsonUtils.getFloat(jsonobject, "upperLimitScale", chunkgeneratorsettings$factory.upperLimitScale);
         chunkgeneratorsettings$factory.depthNoiseScaleX = JsonUtils.getFloat(jsonobject, "depthNoiseScaleX", chunkgeneratorsettings$factory.depthNoiseScaleX);
         chunkgeneratorsettings$factory.depthNoiseScaleZ = JsonUtils.getFloat(jsonobject, "depthNoiseScaleZ", chunkgeneratorsettings$factory.depthNoiseScaleZ);
         chunkgeneratorsettings$factory.depthNoiseScaleExponent = JsonUtils.getFloat(jsonobject, "depthNoiseScaleExponent", chunkgeneratorsettings$factory.depthNoiseScaleExponent);
         chunkgeneratorsettings$factory.mainNoiseScaleX = JsonUtils.getFloat(jsonobject, "mainNoiseScaleX", chunkgeneratorsettings$factory.mainNoiseScaleX);
         chunkgeneratorsettings$factory.mainNoiseScaleY = JsonUtils.getFloat(jsonobject, "mainNoiseScaleY", chunkgeneratorsettings$factory.mainNoiseScaleY);
         chunkgeneratorsettings$factory.mainNoiseScaleZ = JsonUtils.getFloat(jsonobject, "mainNoiseScaleZ", chunkgeneratorsettings$factory.mainNoiseScaleZ);
         chunkgeneratorsettings$factory.baseSize = JsonUtils.getFloat(jsonobject, "baseSize", chunkgeneratorsettings$factory.baseSize);
         chunkgeneratorsettings$factory.stretchY = JsonUtils.getFloat(jsonobject, "stretchY", chunkgeneratorsettings$factory.stretchY);
         chunkgeneratorsettings$factory.biomeDepthWeight = JsonUtils.getFloat(jsonobject, "biomeDepthWeight", chunkgeneratorsettings$factory.biomeDepthWeight);
         chunkgeneratorsettings$factory.biomeDepthOffset = JsonUtils.getFloat(jsonobject, "biomeDepthOffset", chunkgeneratorsettings$factory.biomeDepthOffset);
         chunkgeneratorsettings$factory.biomeScaleWeight = JsonUtils.getFloat(jsonobject, "biomeScaleWeight", chunkgeneratorsettings$factory.biomeScaleWeight);
         chunkgeneratorsettings$factory.biomeScaleOffset = JsonUtils.getFloat(jsonobject, "biomeScaleOffset", chunkgeneratorsettings$factory.biomeScaleOffset);
         chunkgeneratorsettings$factory.seaLevel = JsonUtils.getInt(jsonobject, "seaLevel", chunkgeneratorsettings$factory.seaLevel);
         chunkgeneratorsettings$factory.useCaves = JsonUtils.getBoolean(jsonobject, "useCaves", chunkgeneratorsettings$factory.useCaves);
         chunkgeneratorsettings$factory.useDungeons = JsonUtils.getBoolean(jsonobject, "useDungeons", chunkgeneratorsettings$factory.useDungeons);
         chunkgeneratorsettings$factory.dungeonChance = JsonUtils.getInt(jsonobject, "dungeonChance", chunkgeneratorsettings$factory.dungeonChance);
         chunkgeneratorsettings$factory.useStrongholds = JsonUtils.getBoolean(jsonobject, "useStrongholds", chunkgeneratorsettings$factory.useStrongholds);
         chunkgeneratorsettings$factory.useVillages = JsonUtils.getBoolean(jsonobject, "useVillages", chunkgeneratorsettings$factory.useVillages);
         chunkgeneratorsettings$factory.useMineShafts = JsonUtils.getBoolean(jsonobject, "useMineShafts", chunkgeneratorsettings$factory.useMineShafts);
         chunkgeneratorsettings$factory.useTemples = JsonUtils.getBoolean(jsonobject, "useTemples", chunkgeneratorsettings$factory.useTemples);
         chunkgeneratorsettings$factory.useMonuments = JsonUtils.getBoolean(jsonobject, "useMonuments", chunkgeneratorsettings$factory.useMonuments);
         chunkgeneratorsettings$factory.useMansions = JsonUtils.getBoolean(jsonobject, "useMansions", chunkgeneratorsettings$factory.useMansions);
         chunkgeneratorsettings$factory.useRavines = JsonUtils.getBoolean(jsonobject, "useRavines", chunkgeneratorsettings$factory.useRavines);
         chunkgeneratorsettings$factory.useWaterLakes = JsonUtils.getBoolean(jsonobject, "useWaterLakes", chunkgeneratorsettings$factory.useWaterLakes);
         chunkgeneratorsettings$factory.waterLakeChance = JsonUtils.getInt(jsonobject, "waterLakeChance", chunkgeneratorsettings$factory.waterLakeChance);
         chunkgeneratorsettings$factory.useLavaLakes = JsonUtils.getBoolean(jsonobject, "useLavaLakes", chunkgeneratorsettings$factory.useLavaLakes);
         chunkgeneratorsettings$factory.lavaLakeChance = JsonUtils.getInt(jsonobject, "lavaLakeChance", chunkgeneratorsettings$factory.lavaLakeChance);
         chunkgeneratorsettings$factory.useLavaOceans = JsonUtils.getBoolean(jsonobject, "useLavaOceans", chunkgeneratorsettings$factory.useLavaOceans);
         chunkgeneratorsettings$factory.fixedBiome = JsonUtils.getInt(jsonobject, "fixedBiome", chunkgeneratorsettings$factory.fixedBiome);
         if (chunkgeneratorsettings$factory.fixedBiome < 38 && chunkgeneratorsettings$factory.fixedBiome >= -1) {
            if (chunkgeneratorsettings$factory.fixedBiome >= Zi.getIdForBiome(Nj.HELL)) {
               chunkgeneratorsettings$factory.fixedBiome += 2;
            }
         } else {
            chunkgeneratorsettings$factory.fixedBiome = -1;
         }

         chunkgeneratorsettings$factory.biomeSize = JsonUtils.getInt(jsonobject, "biomeSize", chunkgeneratorsettings$factory.biomeSize);
         chunkgeneratorsettings$factory.riverSize = JsonUtils.getInt(jsonobject, "riverSize", chunkgeneratorsettings$factory.riverSize);
         chunkgeneratorsettings$factory.dirtSize = JsonUtils.getInt(jsonobject, "dirtSize", chunkgeneratorsettings$factory.dirtSize);
         chunkgeneratorsettings$factory.dirtCount = JsonUtils.getInt(jsonobject, "dirtCount", chunkgeneratorsettings$factory.dirtCount);
         chunkgeneratorsettings$factory.dirtMinHeight = JsonUtils.getInt(jsonobject, "dirtMinHeight", chunkgeneratorsettings$factory.dirtMinHeight);
         chunkgeneratorsettings$factory.dirtMaxHeight = JsonUtils.getInt(jsonobject, "dirtMaxHeight", chunkgeneratorsettings$factory.dirtMaxHeight);
         chunkgeneratorsettings$factory.gravelSize = JsonUtils.getInt(jsonobject, "gravelSize", chunkgeneratorsettings$factory.gravelSize);
         chunkgeneratorsettings$factory.gravelCount = JsonUtils.getInt(jsonobject, "gravelCount", chunkgeneratorsettings$factory.gravelCount);
         chunkgeneratorsettings$factory.gravelMinHeight = JsonUtils.getInt(jsonobject, "gravelMinHeight", chunkgeneratorsettings$factory.gravelMinHeight);
         chunkgeneratorsettings$factory.gravelMaxHeight = JsonUtils.getInt(jsonobject, "gravelMaxHeight", chunkgeneratorsettings$factory.gravelMaxHeight);
         chunkgeneratorsettings$factory.graniteSize = JsonUtils.getInt(jsonobject, "graniteSize", chunkgeneratorsettings$factory.graniteSize);
         chunkgeneratorsettings$factory.graniteCount = JsonUtils.getInt(jsonobject, "graniteCount", chunkgeneratorsettings$factory.graniteCount);
         chunkgeneratorsettings$factory.graniteMinHeight = JsonUtils.getInt(jsonobject, "graniteMinHeight", chunkgeneratorsettings$factory.graniteMinHeight);
         chunkgeneratorsettings$factory.graniteMaxHeight = JsonUtils.getInt(jsonobject, "graniteMaxHeight", chunkgeneratorsettings$factory.graniteMaxHeight);
         chunkgeneratorsettings$factory.dioriteSize = JsonUtils.getInt(jsonobject, "dioriteSize", chunkgeneratorsettings$factory.dioriteSize);
         chunkgeneratorsettings$factory.dioriteCount = JsonUtils.getInt(jsonobject, "dioriteCount", chunkgeneratorsettings$factory.dioriteCount);
         chunkgeneratorsettings$factory.dioriteMinHeight = JsonUtils.getInt(jsonobject, "dioriteMinHeight", chunkgeneratorsettings$factory.dioriteMinHeight);
         chunkgeneratorsettings$factory.dioriteMaxHeight = JsonUtils.getInt(jsonobject, "dioriteMaxHeight", chunkgeneratorsettings$factory.dioriteMaxHeight);
         chunkgeneratorsettings$factory.andesiteSize = JsonUtils.getInt(jsonobject, "andesiteSize", chunkgeneratorsettings$factory.andesiteSize);
         chunkgeneratorsettings$factory.andesiteCount = JsonUtils.getInt(jsonobject, "andesiteCount", chunkgeneratorsettings$factory.andesiteCount);
         chunkgeneratorsettings$factory.andesiteMinHeight = JsonUtils.getInt(jsonobject, "andesiteMinHeight", chunkgeneratorsettings$factory.andesiteMinHeight);
         chunkgeneratorsettings$factory.andesiteMaxHeight = JsonUtils.getInt(jsonobject, "andesiteMaxHeight", chunkgeneratorsettings$factory.andesiteMaxHeight);
         chunkgeneratorsettings$factory.coalSize = JsonUtils.getInt(jsonobject, "coalSize", chunkgeneratorsettings$factory.coalSize);
         chunkgeneratorsettings$factory.coalCount = JsonUtils.getInt(jsonobject, "coalCount", chunkgeneratorsettings$factory.coalCount);
         chunkgeneratorsettings$factory.coalMinHeight = JsonUtils.getInt(jsonobject, "coalMinHeight", chunkgeneratorsettings$factory.coalMinHeight);
         chunkgeneratorsettings$factory.coalMaxHeight = JsonUtils.getInt(jsonobject, "coalMaxHeight", chunkgeneratorsettings$factory.coalMaxHeight);
         chunkgeneratorsettings$factory.ironSize = JsonUtils.getInt(jsonobject, "ironSize", chunkgeneratorsettings$factory.ironSize);
         chunkgeneratorsettings$factory.ironCount = JsonUtils.getInt(jsonobject, "ironCount", chunkgeneratorsettings$factory.ironCount);
         chunkgeneratorsettings$factory.ironMinHeight = JsonUtils.getInt(jsonobject, "ironMinHeight", chunkgeneratorsettings$factory.ironMinHeight);
         chunkgeneratorsettings$factory.ironMaxHeight = JsonUtils.getInt(jsonobject, "ironMaxHeight", chunkgeneratorsettings$factory.ironMaxHeight);
         chunkgeneratorsettings$factory.goldSize = JsonUtils.getInt(jsonobject, "goldSize", chunkgeneratorsettings$factory.goldSize);
         chunkgeneratorsettings$factory.goldCount = JsonUtils.getInt(jsonobject, "goldCount", chunkgeneratorsettings$factory.goldCount);
         chunkgeneratorsettings$factory.goldMinHeight = JsonUtils.getInt(jsonobject, "goldMinHeight", chunkgeneratorsettings$factory.goldMinHeight);
         chunkgeneratorsettings$factory.goldMaxHeight = JsonUtils.getInt(jsonobject, "goldMaxHeight", chunkgeneratorsettings$factory.goldMaxHeight);
         chunkgeneratorsettings$factory.redstoneSize = JsonUtils.getInt(jsonobject, "redstoneSize", chunkgeneratorsettings$factory.redstoneSize);
         chunkgeneratorsettings$factory.redstoneCount = JsonUtils.getInt(jsonobject, "redstoneCount", chunkgeneratorsettings$factory.redstoneCount);
         chunkgeneratorsettings$factory.redstoneMinHeight = JsonUtils.getInt(jsonobject, "redstoneMinHeight", chunkgeneratorsettings$factory.redstoneMinHeight);
         chunkgeneratorsettings$factory.redstoneMaxHeight = JsonUtils.getInt(jsonobject, "redstoneMaxHeight", chunkgeneratorsettings$factory.redstoneMaxHeight);
         chunkgeneratorsettings$factory.diamondSize = JsonUtils.getInt(jsonobject, "diamondSize", chunkgeneratorsettings$factory.diamondSize);
         chunkgeneratorsettings$factory.diamondCount = JsonUtils.getInt(jsonobject, "diamondCount", chunkgeneratorsettings$factory.diamondCount);
         chunkgeneratorsettings$factory.diamondMinHeight = JsonUtils.getInt(jsonobject, "diamondMinHeight", chunkgeneratorsettings$factory.diamondMinHeight);
         chunkgeneratorsettings$factory.diamondMaxHeight = JsonUtils.getInt(jsonobject, "diamondMaxHeight", chunkgeneratorsettings$factory.diamondMaxHeight);
         chunkgeneratorsettings$factory.lapisSize = JsonUtils.getInt(jsonobject, "lapisSize", chunkgeneratorsettings$factory.lapisSize);
         chunkgeneratorsettings$factory.lapisCount = JsonUtils.getInt(jsonobject, "lapisCount", chunkgeneratorsettings$factory.lapisCount);
         chunkgeneratorsettings$factory.lapisCenterHeight = JsonUtils.getInt(jsonobject, "lapisCenterHeight", chunkgeneratorsettings$factory.lapisCenterHeight);
         chunkgeneratorsettings$factory.lapisSpread = JsonUtils.getInt(jsonobject, "lapisSpread", chunkgeneratorsettings$factory.lapisSpread);
      } catch (Exception var7) {
      }

      return chunkgeneratorsettings$factory;
   }

   public JsonElement serialize(bbi p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
      JsonObject jsonobject = new JsonObject();
      jsonobject.addProperty("coordinateScale", p_serialize_1_.coordinateScale);
      jsonobject.addProperty("heightScale", p_serialize_1_.heightScale);
      jsonobject.addProperty("lowerLimitScale", p_serialize_1_.lowerLimitScale);
      jsonobject.addProperty("upperLimitScale", p_serialize_1_.upperLimitScale);
      jsonobject.addProperty("depthNoiseScaleX", p_serialize_1_.depthNoiseScaleX);
      jsonobject.addProperty("depthNoiseScaleZ", p_serialize_1_.depthNoiseScaleZ);
      jsonobject.addProperty("depthNoiseScaleExponent", p_serialize_1_.depthNoiseScaleExponent);
      jsonobject.addProperty("mainNoiseScaleX", p_serialize_1_.mainNoiseScaleX);
      jsonobject.addProperty("mainNoiseScaleY", p_serialize_1_.mainNoiseScaleY);
      jsonobject.addProperty("mainNoiseScaleZ", p_serialize_1_.mainNoiseScaleZ);
      jsonobject.addProperty("baseSize", p_serialize_1_.baseSize);
      jsonobject.addProperty("stretchY", p_serialize_1_.stretchY);
      jsonobject.addProperty("biomeDepthWeight", p_serialize_1_.biomeDepthWeight);
      jsonobject.addProperty("biomeDepthOffset", p_serialize_1_.biomeDepthOffset);
      jsonobject.addProperty("biomeScaleWeight", p_serialize_1_.biomeScaleWeight);
      jsonobject.addProperty("biomeScaleOffset", p_serialize_1_.biomeScaleOffset);
      jsonobject.addProperty("seaLevel", p_serialize_1_.seaLevel);
      jsonobject.addProperty("useCaves", p_serialize_1_.useCaves);
      jsonobject.addProperty("useDungeons", p_serialize_1_.useDungeons);
      jsonobject.addProperty("dungeonChance", p_serialize_1_.dungeonChance);
      jsonobject.addProperty("useStrongholds", p_serialize_1_.useStrongholds);
      jsonobject.addProperty("useVillages", p_serialize_1_.useVillages);
      jsonobject.addProperty("useMineShafts", p_serialize_1_.useMineShafts);
      jsonobject.addProperty("useTemples", p_serialize_1_.useTemples);
      jsonobject.addProperty("useMonuments", p_serialize_1_.useMonuments);
      jsonobject.addProperty("useMansions", p_serialize_1_.useMansions);
      jsonobject.addProperty("useRavines", p_serialize_1_.useRavines);
      jsonobject.addProperty("useWaterLakes", p_serialize_1_.useWaterLakes);
      jsonobject.addProperty("waterLakeChance", p_serialize_1_.waterLakeChance);
      jsonobject.addProperty("useLavaLakes", p_serialize_1_.useLavaLakes);
      jsonobject.addProperty("lavaLakeChance", p_serialize_1_.lavaLakeChance);
      jsonobject.addProperty("useLavaOceans", p_serialize_1_.useLavaOceans);
      jsonobject.addProperty("fixedBiome", p_serialize_1_.fixedBiome);
      jsonobject.addProperty("biomeSize", p_serialize_1_.biomeSize);
      jsonobject.addProperty("riverSize", p_serialize_1_.riverSize);
      jsonobject.addProperty("dirtSize", p_serialize_1_.dirtSize);
      jsonobject.addProperty("dirtCount", p_serialize_1_.dirtCount);
      jsonobject.addProperty("dirtMinHeight", p_serialize_1_.dirtMinHeight);
      jsonobject.addProperty("dirtMaxHeight", p_serialize_1_.dirtMaxHeight);
      jsonobject.addProperty("gravelSize", p_serialize_1_.gravelSize);
      jsonobject.addProperty("gravelCount", p_serialize_1_.gravelCount);
      jsonobject.addProperty("gravelMinHeight", p_serialize_1_.gravelMinHeight);
      jsonobject.addProperty("gravelMaxHeight", p_serialize_1_.gravelMaxHeight);
      jsonobject.addProperty("graniteSize", p_serialize_1_.graniteSize);
      jsonobject.addProperty("graniteCount", p_serialize_1_.graniteCount);
      jsonobject.addProperty("graniteMinHeight", p_serialize_1_.graniteMinHeight);
      jsonobject.addProperty("graniteMaxHeight", p_serialize_1_.graniteMaxHeight);
      jsonobject.addProperty("dioriteSize", p_serialize_1_.dioriteSize);
      jsonobject.addProperty("dioriteCount", p_serialize_1_.dioriteCount);
      jsonobject.addProperty("dioriteMinHeight", p_serialize_1_.dioriteMinHeight);
      jsonobject.addProperty("dioriteMaxHeight", p_serialize_1_.dioriteMaxHeight);
      jsonobject.addProperty("andesiteSize", p_serialize_1_.andesiteSize);
      jsonobject.addProperty("andesiteCount", p_serialize_1_.andesiteCount);
      jsonobject.addProperty("andesiteMinHeight", p_serialize_1_.andesiteMinHeight);
      jsonobject.addProperty("andesiteMaxHeight", p_serialize_1_.andesiteMaxHeight);
      jsonobject.addProperty("coalSize", p_serialize_1_.coalSize);
      jsonobject.addProperty("coalCount", p_serialize_1_.coalCount);
      jsonobject.addProperty("coalMinHeight", p_serialize_1_.coalMinHeight);
      jsonobject.addProperty("coalMaxHeight", p_serialize_1_.coalMaxHeight);
      jsonobject.addProperty("ironSize", p_serialize_1_.ironSize);
      jsonobject.addProperty("ironCount", p_serialize_1_.ironCount);
      jsonobject.addProperty("ironMinHeight", p_serialize_1_.ironMinHeight);
      jsonobject.addProperty("ironMaxHeight", p_serialize_1_.ironMaxHeight);
      jsonobject.addProperty("goldSize", p_serialize_1_.goldSize);
      jsonobject.addProperty("goldCount", p_serialize_1_.goldCount);
      jsonobject.addProperty("goldMinHeight", p_serialize_1_.goldMinHeight);
      jsonobject.addProperty("goldMaxHeight", p_serialize_1_.goldMaxHeight);
      jsonobject.addProperty("redstoneSize", p_serialize_1_.redstoneSize);
      jsonobject.addProperty("redstoneCount", p_serialize_1_.redstoneCount);
      jsonobject.addProperty("redstoneMinHeight", p_serialize_1_.redstoneMinHeight);
      jsonobject.addProperty("redstoneMaxHeight", p_serialize_1_.redstoneMaxHeight);
      jsonobject.addProperty("diamondSize", p_serialize_1_.diamondSize);
      jsonobject.addProperty("diamondCount", p_serialize_1_.diamondCount);
      jsonobject.addProperty("diamondMinHeight", p_serialize_1_.diamondMinHeight);
      jsonobject.addProperty("diamondMaxHeight", p_serialize_1_.diamondMaxHeight);
      jsonobject.addProperty("lapisSize", p_serialize_1_.lapisSize);
      jsonobject.addProperty("lapisCount", p_serialize_1_.lapisCount);
      jsonobject.addProperty("lapisCenterHeight", p_serialize_1_.lapisCenterHeight);
      jsonobject.addProperty("lapisSpread", p_serialize_1_.lapisSpread);
      return jsonobject;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public Object deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) throws JsonParseException {
      return this.deserialize(var1, var2, var3);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public JsonElement serialize(Object var1, Type var2, JsonSerializationContext var3) {
      return this.serialize((bbi)var1, var2, var3);
   }
}
