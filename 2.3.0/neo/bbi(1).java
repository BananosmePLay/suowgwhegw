package neo;

import com.google.common.annotations.VisibleForTesting;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.util.JsonUtils;

public class bbi {
   @VisibleForTesting
   static final Gson JSON_ADAPTER = (new GsonBuilder()).registerTypeAdapter(bbi.class, new bbj()).create();
   public float coordinateScale = 684.412F;
   public float heightScale = 684.412F;
   public float upperLimitScale = 512.0F;
   public float lowerLimitScale = 512.0F;
   public float depthNoiseScaleX = 200.0F;
   public float depthNoiseScaleZ = 200.0F;
   public float depthNoiseScaleExponent = 0.5F;
   public float mainNoiseScaleX = 80.0F;
   public float mainNoiseScaleY = 160.0F;
   public float mainNoiseScaleZ = 80.0F;
   public float baseSize = 8.5F;
   public float stretchY = 12.0F;
   public float biomeDepthWeight = 1.0F;
   public float biomeDepthOffset;
   public float biomeScaleWeight = 1.0F;
   public float biomeScaleOffset;
   public int seaLevel = 63;
   public boolean useCaves = true;
   public boolean useDungeons = true;
   public int dungeonChance = 8;
   public boolean useStrongholds = true;
   public boolean useVillages = true;
   public boolean useMineShafts = true;
   public boolean useTemples = true;
   public boolean useMonuments = true;
   public boolean useMansions = true;
   public boolean useRavines = true;
   public boolean useWaterLakes = true;
   public int waterLakeChance = 4;
   public boolean useLavaLakes = true;
   public int lavaLakeChance = 80;
   public boolean useLavaOceans;
   public int fixedBiome = -1;
   public int biomeSize = 4;
   public int riverSize = 4;
   public int dirtSize = 33;
   public int dirtCount = 10;
   public int dirtMinHeight;
   public int dirtMaxHeight = 256;
   public int gravelSize = 33;
   public int gravelCount = 8;
   public int gravelMinHeight;
   public int gravelMaxHeight = 256;
   public int graniteSize = 33;
   public int graniteCount = 10;
   public int graniteMinHeight;
   public int graniteMaxHeight = 80;
   public int dioriteSize = 33;
   public int dioriteCount = 10;
   public int dioriteMinHeight;
   public int dioriteMaxHeight = 80;
   public int andesiteSize = 33;
   public int andesiteCount = 10;
   public int andesiteMinHeight;
   public int andesiteMaxHeight = 80;
   public int coalSize = 17;
   public int coalCount = 20;
   public int coalMinHeight;
   public int coalMaxHeight = 128;
   public int ironSize = 9;
   public int ironCount = 20;
   public int ironMinHeight;
   public int ironMaxHeight = 64;
   public int goldSize = 9;
   public int goldCount = 2;
   public int goldMinHeight;
   public int goldMaxHeight = 32;
   public int redstoneSize = 8;
   public int redstoneCount = 8;
   public int redstoneMinHeight;
   public int redstoneMaxHeight = 16;
   public int diamondSize = 8;
   public int diamondCount = 1;
   public int diamondMinHeight;
   public int diamondMaxHeight = 16;
   public int lapisSize = 7;
   public int lapisCount = 1;
   public int lapisCenterHeight = 16;
   public int lapisSpread = 16;

   public static bbi jsonToFactory(String p_177865_0_) {
      if (p_177865_0_.isEmpty()) {
         return new bbi();
      } else {
         try {
            return (bbi)JsonUtils.gsonDeserialize(JSON_ADAPTER, p_177865_0_, bbi.class);
         } catch (Exception var2) {
            return new bbi();
         }
      }
   }

   public String toString() {
      return JSON_ADAPTER.toJson(this);
   }

   public bbi() {
      this.setDefaults();
   }

   public void setDefaults() {
      this.coordinateScale = 684.412F;
      this.heightScale = 684.412F;
      this.upperLimitScale = 512.0F;
      this.lowerLimitScale = 512.0F;
      this.depthNoiseScaleX = 200.0F;
      this.depthNoiseScaleZ = 200.0F;
      this.depthNoiseScaleExponent = 0.5F;
      this.mainNoiseScaleX = 80.0F;
      this.mainNoiseScaleY = 160.0F;
      this.mainNoiseScaleZ = 80.0F;
      this.baseSize = 8.5F;
      this.stretchY = 12.0F;
      this.biomeDepthWeight = 1.0F;
      this.biomeDepthOffset = 0.0F;
      this.biomeScaleWeight = 1.0F;
      this.biomeScaleOffset = 0.0F;
      this.seaLevel = 63;
      this.useCaves = true;
      this.useDungeons = true;
      this.dungeonChance = 8;
      this.useStrongholds = true;
      this.useVillages = true;
      this.useMineShafts = true;
      this.useTemples = true;
      this.useMonuments = true;
      this.useMansions = true;
      this.useRavines = true;
      this.useWaterLakes = true;
      this.waterLakeChance = 4;
      this.useLavaLakes = true;
      this.lavaLakeChance = 80;
      this.useLavaOceans = false;
      this.fixedBiome = -1;
      this.biomeSize = 4;
      this.riverSize = 4;
      this.dirtSize = 33;
      this.dirtCount = 10;
      this.dirtMinHeight = 0;
      this.dirtMaxHeight = 256;
      this.gravelSize = 33;
      this.gravelCount = 8;
      this.gravelMinHeight = 0;
      this.gravelMaxHeight = 256;
      this.graniteSize = 33;
      this.graniteCount = 10;
      this.graniteMinHeight = 0;
      this.graniteMaxHeight = 80;
      this.dioriteSize = 33;
      this.dioriteCount = 10;
      this.dioriteMinHeight = 0;
      this.dioriteMaxHeight = 80;
      this.andesiteSize = 33;
      this.andesiteCount = 10;
      this.andesiteMinHeight = 0;
      this.andesiteMaxHeight = 80;
      this.coalSize = 17;
      this.coalCount = 20;
      this.coalMinHeight = 0;
      this.coalMaxHeight = 128;
      this.ironSize = 9;
      this.ironCount = 20;
      this.ironMinHeight = 0;
      this.ironMaxHeight = 64;
      this.goldSize = 9;
      this.goldCount = 2;
      this.goldMinHeight = 0;
      this.goldMaxHeight = 32;
      this.redstoneSize = 8;
      this.redstoneCount = 8;
      this.redstoneMinHeight = 0;
      this.redstoneMaxHeight = 16;
      this.diamondSize = 8;
      this.diamondCount = 1;
      this.diamondMinHeight = 0;
      this.diamondMaxHeight = 16;
      this.lapisSize = 7;
      this.lapisCount = 1;
      this.lapisCenterHeight = 16;
      this.lapisSpread = 16;
   }

   public boolean equals(Object p_equals_1_) {
      if (this == p_equals_1_) {
         return true;
      } else if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
         bbi chunkgeneratorsettings$factory = (bbi)p_equals_1_;
         if (this.andesiteCount != chunkgeneratorsettings$factory.andesiteCount) {
            return false;
         } else if (this.andesiteMaxHeight != chunkgeneratorsettings$factory.andesiteMaxHeight) {
            return false;
         } else if (this.andesiteMinHeight != chunkgeneratorsettings$factory.andesiteMinHeight) {
            return false;
         } else if (this.andesiteSize != chunkgeneratorsettings$factory.andesiteSize) {
            return false;
         } else if (Float.compare(chunkgeneratorsettings$factory.baseSize, this.baseSize) != 0) {
            return false;
         } else if (Float.compare(chunkgeneratorsettings$factory.biomeDepthOffset, this.biomeDepthOffset) != 0) {
            return false;
         } else if (Float.compare(chunkgeneratorsettings$factory.biomeDepthWeight, this.biomeDepthWeight) != 0) {
            return false;
         } else if (Float.compare(chunkgeneratorsettings$factory.biomeScaleOffset, this.biomeScaleOffset) != 0) {
            return false;
         } else if (Float.compare(chunkgeneratorsettings$factory.biomeScaleWeight, this.biomeScaleWeight) != 0) {
            return false;
         } else if (this.biomeSize != chunkgeneratorsettings$factory.biomeSize) {
            return false;
         } else if (this.coalCount != chunkgeneratorsettings$factory.coalCount) {
            return false;
         } else if (this.coalMaxHeight != chunkgeneratorsettings$factory.coalMaxHeight) {
            return false;
         } else if (this.coalMinHeight != chunkgeneratorsettings$factory.coalMinHeight) {
            return false;
         } else if (this.coalSize != chunkgeneratorsettings$factory.coalSize) {
            return false;
         } else if (Float.compare(chunkgeneratorsettings$factory.coordinateScale, this.coordinateScale) != 0) {
            return false;
         } else if (Float.compare(chunkgeneratorsettings$factory.depthNoiseScaleExponent, this.depthNoiseScaleExponent) != 0) {
            return false;
         } else if (Float.compare(chunkgeneratorsettings$factory.depthNoiseScaleX, this.depthNoiseScaleX) != 0) {
            return false;
         } else if (Float.compare(chunkgeneratorsettings$factory.depthNoiseScaleZ, this.depthNoiseScaleZ) != 0) {
            return false;
         } else if (this.diamondCount != chunkgeneratorsettings$factory.diamondCount) {
            return false;
         } else if (this.diamondMaxHeight != chunkgeneratorsettings$factory.diamondMaxHeight) {
            return false;
         } else if (this.diamondMinHeight != chunkgeneratorsettings$factory.diamondMinHeight) {
            return false;
         } else if (this.diamondSize != chunkgeneratorsettings$factory.diamondSize) {
            return false;
         } else if (this.dioriteCount != chunkgeneratorsettings$factory.dioriteCount) {
            return false;
         } else if (this.dioriteMaxHeight != chunkgeneratorsettings$factory.dioriteMaxHeight) {
            return false;
         } else if (this.dioriteMinHeight != chunkgeneratorsettings$factory.dioriteMinHeight) {
            return false;
         } else if (this.dioriteSize != chunkgeneratorsettings$factory.dioriteSize) {
            return false;
         } else if (this.dirtCount != chunkgeneratorsettings$factory.dirtCount) {
            return false;
         } else if (this.dirtMaxHeight != chunkgeneratorsettings$factory.dirtMaxHeight) {
            return false;
         } else if (this.dirtMinHeight != chunkgeneratorsettings$factory.dirtMinHeight) {
            return false;
         } else if (this.dirtSize != chunkgeneratorsettings$factory.dirtSize) {
            return false;
         } else if (this.dungeonChance != chunkgeneratorsettings$factory.dungeonChance) {
            return false;
         } else if (this.fixedBiome != chunkgeneratorsettings$factory.fixedBiome) {
            return false;
         } else if (this.goldCount != chunkgeneratorsettings$factory.goldCount) {
            return false;
         } else if (this.goldMaxHeight != chunkgeneratorsettings$factory.goldMaxHeight) {
            return false;
         } else if (this.goldMinHeight != chunkgeneratorsettings$factory.goldMinHeight) {
            return false;
         } else if (this.goldSize != chunkgeneratorsettings$factory.goldSize) {
            return false;
         } else if (this.graniteCount != chunkgeneratorsettings$factory.graniteCount) {
            return false;
         } else if (this.graniteMaxHeight != chunkgeneratorsettings$factory.graniteMaxHeight) {
            return false;
         } else if (this.graniteMinHeight != chunkgeneratorsettings$factory.graniteMinHeight) {
            return false;
         } else if (this.graniteSize != chunkgeneratorsettings$factory.graniteSize) {
            return false;
         } else if (this.gravelCount != chunkgeneratorsettings$factory.gravelCount) {
            return false;
         } else if (this.gravelMaxHeight != chunkgeneratorsettings$factory.gravelMaxHeight) {
            return false;
         } else if (this.gravelMinHeight != chunkgeneratorsettings$factory.gravelMinHeight) {
            return false;
         } else if (this.gravelSize != chunkgeneratorsettings$factory.gravelSize) {
            return false;
         } else if (Float.compare(chunkgeneratorsettings$factory.heightScale, this.heightScale) != 0) {
            return false;
         } else if (this.ironCount != chunkgeneratorsettings$factory.ironCount) {
            return false;
         } else if (this.ironMaxHeight != chunkgeneratorsettings$factory.ironMaxHeight) {
            return false;
         } else if (this.ironMinHeight != chunkgeneratorsettings$factory.ironMinHeight) {
            return false;
         } else if (this.ironSize != chunkgeneratorsettings$factory.ironSize) {
            return false;
         } else if (this.lapisCenterHeight != chunkgeneratorsettings$factory.lapisCenterHeight) {
            return false;
         } else if (this.lapisCount != chunkgeneratorsettings$factory.lapisCount) {
            return false;
         } else if (this.lapisSize != chunkgeneratorsettings$factory.lapisSize) {
            return false;
         } else if (this.lapisSpread != chunkgeneratorsettings$factory.lapisSpread) {
            return false;
         } else if (this.lavaLakeChance != chunkgeneratorsettings$factory.lavaLakeChance) {
            return false;
         } else if (Float.compare(chunkgeneratorsettings$factory.lowerLimitScale, this.lowerLimitScale) != 0) {
            return false;
         } else if (Float.compare(chunkgeneratorsettings$factory.mainNoiseScaleX, this.mainNoiseScaleX) != 0) {
            return false;
         } else if (Float.compare(chunkgeneratorsettings$factory.mainNoiseScaleY, this.mainNoiseScaleY) != 0) {
            return false;
         } else if (Float.compare(chunkgeneratorsettings$factory.mainNoiseScaleZ, this.mainNoiseScaleZ) != 0) {
            return false;
         } else if (this.redstoneCount != chunkgeneratorsettings$factory.redstoneCount) {
            return false;
         } else if (this.redstoneMaxHeight != chunkgeneratorsettings$factory.redstoneMaxHeight) {
            return false;
         } else if (this.redstoneMinHeight != chunkgeneratorsettings$factory.redstoneMinHeight) {
            return false;
         } else if (this.redstoneSize != chunkgeneratorsettings$factory.redstoneSize) {
            return false;
         } else if (this.riverSize != chunkgeneratorsettings$factory.riverSize) {
            return false;
         } else if (this.seaLevel != chunkgeneratorsettings$factory.seaLevel) {
            return false;
         } else if (Float.compare(chunkgeneratorsettings$factory.stretchY, this.stretchY) != 0) {
            return false;
         } else if (Float.compare(chunkgeneratorsettings$factory.upperLimitScale, this.upperLimitScale) != 0) {
            return false;
         } else if (this.useCaves != chunkgeneratorsettings$factory.useCaves) {
            return false;
         } else if (this.useDungeons != chunkgeneratorsettings$factory.useDungeons) {
            return false;
         } else if (this.useLavaLakes != chunkgeneratorsettings$factory.useLavaLakes) {
            return false;
         } else if (this.useLavaOceans != chunkgeneratorsettings$factory.useLavaOceans) {
            return false;
         } else if (this.useMineShafts != chunkgeneratorsettings$factory.useMineShafts) {
            return false;
         } else if (this.useRavines != chunkgeneratorsettings$factory.useRavines) {
            return false;
         } else if (this.useStrongholds != chunkgeneratorsettings$factory.useStrongholds) {
            return false;
         } else if (this.useTemples != chunkgeneratorsettings$factory.useTemples) {
            return false;
         } else if (this.useMonuments != chunkgeneratorsettings$factory.useMonuments) {
            return false;
         } else if (this.useMansions != chunkgeneratorsettings$factory.useMansions) {
            return false;
         } else if (this.useVillages != chunkgeneratorsettings$factory.useVillages) {
            return false;
         } else if (this.useWaterLakes != chunkgeneratorsettings$factory.useWaterLakes) {
            return false;
         } else {
            return this.waterLakeChance == chunkgeneratorsettings$factory.waterLakeChance;
         }
      } else {
         return false;
      }
   }

   public int hashCode() {
      int i = this.coordinateScale == 0.0F ? 0 : Float.floatToIntBits(this.coordinateScale);
      i = 31 * i + (this.heightScale == 0.0F ? 0 : Float.floatToIntBits(this.heightScale));
      i = 31 * i + (this.upperLimitScale == 0.0F ? 0 : Float.floatToIntBits(this.upperLimitScale));
      i = 31 * i + (this.lowerLimitScale == 0.0F ? 0 : Float.floatToIntBits(this.lowerLimitScale));
      i = 31 * i + (this.depthNoiseScaleX == 0.0F ? 0 : Float.floatToIntBits(this.depthNoiseScaleX));
      i = 31 * i + (this.depthNoiseScaleZ == 0.0F ? 0 : Float.floatToIntBits(this.depthNoiseScaleZ));
      i = 31 * i + (this.depthNoiseScaleExponent == 0.0F ? 0 : Float.floatToIntBits(this.depthNoiseScaleExponent));
      i = 31 * i + (this.mainNoiseScaleX == 0.0F ? 0 : Float.floatToIntBits(this.mainNoiseScaleX));
      i = 31 * i + (this.mainNoiseScaleY == 0.0F ? 0 : Float.floatToIntBits(this.mainNoiseScaleY));
      i = 31 * i + (this.mainNoiseScaleZ == 0.0F ? 0 : Float.floatToIntBits(this.mainNoiseScaleZ));
      i = 31 * i + (this.baseSize == 0.0F ? 0 : Float.floatToIntBits(this.baseSize));
      i = 31 * i + (this.stretchY == 0.0F ? 0 : Float.floatToIntBits(this.stretchY));
      i = 31 * i + (this.biomeDepthWeight == 0.0F ? 0 : Float.floatToIntBits(this.biomeDepthWeight));
      i = 31 * i + (this.biomeDepthOffset == 0.0F ? 0 : Float.floatToIntBits(this.biomeDepthOffset));
      i = 31 * i + (this.biomeScaleWeight == 0.0F ? 0 : Float.floatToIntBits(this.biomeScaleWeight));
      i = 31 * i + (this.biomeScaleOffset == 0.0F ? 0 : Float.floatToIntBits(this.biomeScaleOffset));
      i = 31 * i + this.seaLevel;
      i = 31 * i + (this.useCaves ? 1 : 0);
      i = 31 * i + (this.useDungeons ? 1 : 0);
      i = 31 * i + this.dungeonChance;
      i = 31 * i + (this.useStrongholds ? 1 : 0);
      i = 31 * i + (this.useVillages ? 1 : 0);
      i = 31 * i + (this.useMineShafts ? 1 : 0);
      i = 31 * i + (this.useTemples ? 1 : 0);
      i = 31 * i + (this.useMonuments ? 1 : 0);
      i = 31 * i + (this.useMansions ? 1 : 0);
      i = 31 * i + (this.useRavines ? 1 : 0);
      i = 31 * i + (this.useWaterLakes ? 1 : 0);
      i = 31 * i + this.waterLakeChance;
      i = 31 * i + (this.useLavaLakes ? 1 : 0);
      i = 31 * i + this.lavaLakeChance;
      i = 31 * i + (this.useLavaOceans ? 1 : 0);
      i = 31 * i + this.fixedBiome;
      i = 31 * i + this.biomeSize;
      i = 31 * i + this.riverSize;
      i = 31 * i + this.dirtSize;
      i = 31 * i + this.dirtCount;
      i = 31 * i + this.dirtMinHeight;
      i = 31 * i + this.dirtMaxHeight;
      i = 31 * i + this.gravelSize;
      i = 31 * i + this.gravelCount;
      i = 31 * i + this.gravelMinHeight;
      i = 31 * i + this.gravelMaxHeight;
      i = 31 * i + this.graniteSize;
      i = 31 * i + this.graniteCount;
      i = 31 * i + this.graniteMinHeight;
      i = 31 * i + this.graniteMaxHeight;
      i = 31 * i + this.dioriteSize;
      i = 31 * i + this.dioriteCount;
      i = 31 * i + this.dioriteMinHeight;
      i = 31 * i + this.dioriteMaxHeight;
      i = 31 * i + this.andesiteSize;
      i = 31 * i + this.andesiteCount;
      i = 31 * i + this.andesiteMinHeight;
      i = 31 * i + this.andesiteMaxHeight;
      i = 31 * i + this.coalSize;
      i = 31 * i + this.coalCount;
      i = 31 * i + this.coalMinHeight;
      i = 31 * i + this.coalMaxHeight;
      i = 31 * i + this.ironSize;
      i = 31 * i + this.ironCount;
      i = 31 * i + this.ironMinHeight;
      i = 31 * i + this.ironMaxHeight;
      i = 31 * i + this.goldSize;
      i = 31 * i + this.goldCount;
      i = 31 * i + this.goldMinHeight;
      i = 31 * i + this.goldMaxHeight;
      i = 31 * i + this.redstoneSize;
      i = 31 * i + this.redstoneCount;
      i = 31 * i + this.redstoneMinHeight;
      i = 31 * i + this.redstoneMaxHeight;
      i = 31 * i + this.diamondSize;
      i = 31 * i + this.diamondCount;
      i = 31 * i + this.diamondMinHeight;
      i = 31 * i + this.diamondMaxHeight;
      i = 31 * i + this.lapisSize;
      i = 31 * i + this.lapisCount;
      i = 31 * i + this.lapisCenterHeight;
      i = 31 * i + this.lapisSpread;
      return i;
   }

   public bbk build() {
      return new bbk(this);
   }
}
