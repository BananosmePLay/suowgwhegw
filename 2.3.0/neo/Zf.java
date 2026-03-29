package neo;

import javax.annotation.Nullable;

public class Zf {
   private final String biomeName;
   private float baseHeight = 0.1F;
   private float heightVariation = 0.2F;
   private float temperature = 0.5F;
   private float rainfall = 0.5F;
   private int waterColor = 16777215;
   private boolean enableSnow;
   private boolean enableRain = true;
   @Nullable
   private String baseBiomeRegName;

   public Zf(String nameIn) {
      this.biomeName = nameIn;
   }

   protected Zf setTemperature(float temperatureIn) {
      if (temperatureIn > 0.1F && temperatureIn < 0.2F) {
         throw new IllegalArgumentException("Please avoid temperatures in the range 0.1 - 0.2 because of snow");
      } else {
         this.temperature = temperatureIn;
         return this;
      }
   }

   protected Zf setRainfall(float rainfallIn) {
      this.rainfall = rainfallIn;
      return this;
   }

   protected Zf setBaseHeight(float baseHeightIn) {
      this.baseHeight = baseHeightIn;
      return this;
   }

   protected Zf setHeightVariation(float heightVariationIn) {
      this.heightVariation = heightVariationIn;
      return this;
   }

   protected Zf setRainDisabled() {
      this.enableRain = false;
      return this;
   }

   protected Zf setSnowEnabled() {
      this.enableSnow = true;
      return this;
   }

   protected Zf setWaterColor(int waterColorIn) {
      this.waterColor = waterColorIn;
      return this;
   }

   protected Zf setBaseBiome(String nameIn) {
      this.baseBiomeRegName = nameIn;
      return this;
   }

   // $FF: synthetic method
   static String access$000(Zf x0) {
      return x0.biomeName;
   }

   // $FF: synthetic method
   static float access$100(Zf x0) {
      return x0.baseHeight;
   }

   // $FF: synthetic method
   static float access$200(Zf x0) {
      return x0.heightVariation;
   }

   // $FF: synthetic method
   static float access$300(Zf x0) {
      return x0.temperature;
   }

   // $FF: synthetic method
   static float access$400(Zf x0) {
      return x0.rainfall;
   }

   // $FF: synthetic method
   static int access$500(Zf x0) {
      return x0.waterColor;
   }

   // $FF: synthetic method
   static boolean access$600(Zf x0) {
      return x0.enableSnow;
   }

   // $FF: synthetic method
   static boolean access$700(Zf x0) {
      return x0.enableRain;
   }

   // $FF: synthetic method
   static String access$800(Zf x0) {
      return x0.baseBiomeRegName;
   }
}
