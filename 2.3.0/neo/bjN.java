package neo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import net.minecraft.util.math.BlockPos;

public class bjN {
   public String source = null;
   private int startFadeIn = -1;
   private int endFadeIn = -1;
   private int startFadeOut = -1;
   private int endFadeOut = -1;
   private int blend = 1;
   private boolean rotate = false;
   private float speed = 1.0F;
   private float[] axis;
   private bje days;
   private int daysLoop;
   private boolean weatherClear;
   private boolean weatherRain;
   private boolean weatherThunder;
   public Zi[] biomes;
   public bje heights;
   private float transition;
   private bqO smoothPositionBrightness;
   public int textureId;
   private bij lastWorld;
   public static final float[] DEFAULT_AXIS = new float[]{1.0F, 0.0F, 0.0F};
   private static final String WEATHER_CLEAR = "clear";
   private static final String WEATHER_RAIN = "rain";
   private static final String WEATHER_THUNDER = "thunder";

   public bjN(Properties props, String defSource) {
      this.axis = DEFAULT_AXIS;
      this.days = null;
      this.daysLoop = 8;
      this.weatherClear = true;
      this.weatherRain = false;
      this.weatherThunder = false;
      this.biomes = null;
      this.heights = null;
      this.transition = 1.0F;
      this.smoothPositionBrightness = null;
      this.textureId = -1;
      this.lastWorld = null;
      biS connectedparser = new biS("CustomSky");
      this.source = props.getProperty("source", defSource);
      this.startFadeIn = this.parseTime(props.getProperty("startFadeIn"));
      this.endFadeIn = this.parseTime(props.getProperty("endFadeIn"));
      this.startFadeOut = this.parseTime(props.getProperty("startFadeOut"));
      this.endFadeOut = this.parseTime(props.getProperty("endFadeOut"));
      this.blend = bnV.parseBlend(props.getProperty("blend"));
      this.rotate = this.parseBoolean(props.getProperty("rotate"), true);
      this.speed = this.parseFloat(props.getProperty("speed"), 1.0F);
      this.axis = this.parseAxis(props.getProperty("axis"), DEFAULT_AXIS);
      this.days = connectedparser.parseRangeListInt(props.getProperty("days"));
      this.daysLoop = connectedparser.parseInt(props.getProperty("daysLoop"), 8);
      List<String> list = this.parseWeatherList(props.getProperty("weather", "clear"));
      this.weatherClear = list.contains("clear");
      this.weatherRain = list.contains("rain");
      this.weatherThunder = list.contains("thunder");
      this.biomes = connectedparser.parseBiomes(props.getProperty("biomes"));
      this.heights = connectedparser.parseRangeListInt(props.getProperty("heights"));
      this.transition = this.parseFloat(props.getProperty("transition"), 1.0F);
   }

   private List<String> parseWeatherList(String str) {
      List<String> list = Arrays.asList("clear", "rain", "thunder");
      List<String> list1 = new ArrayList();
      String[] astring = XH.tokenize(str, " ");

      for(int i = 0; i < astring.length; ++i) {
         String s = astring[i];
         if (!list.contains(s)) {
            XH.warn("Unknown weather: " + s);
         } else {
            list1.add(s);
         }
      }

      return list1;
   }

   private int parseTime(String str) {
      if (str == null) {
         return -1;
      } else {
         String[] astring = XH.tokenize(str, ":");
         if (astring.length != 2) {
            XH.warn("Invalid time: " + str);
            return -1;
         } else {
            String s = astring[0];
            String s1 = astring[1];
            int i = XH.parseInt(s, -1);
            int j = XH.parseInt(s1, -1);
            if (i >= 0 && i <= 23 && j >= 0 && j <= 59) {
               i -= 6;
               if (i < 0) {
                  i += 24;
               }

               int k = i * 1000 + (int)((double)j / 60.0 * 1000.0);
               return k;
            } else {
               XH.warn("Invalid time: " + str);
               return -1;
            }
         }
      }
   }

   private boolean parseBoolean(String str, boolean defVal) {
      if (str == null) {
         return defVal;
      } else if (str.toLowerCase().equals("true")) {
         return true;
      } else if (str.toLowerCase().equals("false")) {
         return false;
      } else {
         XH.warn("Unknown boolean: " + str);
         return defVal;
      }
   }

   private float parseFloat(String str, float defVal) {
      if (str == null) {
         return defVal;
      } else {
         float f = XH.parseFloat(str, Float.MIN_VALUE);
         if (f == Float.MIN_VALUE) {
            XH.warn("Invalid value: " + str);
            return defVal;
         } else {
            return f;
         }
      }
   }

   private float[] parseAxis(String str, float[] defVal) {
      if (str == null) {
         return defVal;
      } else {
         String[] astring = XH.tokenize(str, " ");
         if (astring.length != 3) {
            XH.warn("Invalid axis: " + str);
            return defVal;
         } else {
            float[] afloat = new float[3];

            for(int i = 0; i < astring.length; ++i) {
               afloat[i] = XH.parseFloat(astring[i], Float.MIN_VALUE);
               if (afloat[i] == Float.MIN_VALUE) {
                  XH.warn("Invalid axis: " + str);
                  return defVal;
               }

               if (afloat[i] < -1.0F || afloat[i] > 1.0F) {
                  XH.warn("Invalid axis values: " + str);
                  return defVal;
               }
            }

            float f2 = afloat[0];
            float f = afloat[1];
            float f1 = afloat[2];
            if (f2 * f2 + f * f + f1 * f1 < 1.0E-5F) {
               XH.warn("Invalid axis values: " + str);
               return defVal;
            } else {
               float[] afloat1 = new float[]{f1, f, -f2};
               return afloat1;
            }
         }
      }
   }

   public boolean isValid(String path) {
      if (this.source == null) {
         XH.warn("No source texture: " + path);
         return false;
      } else {
         this.source = bqS.fixResourcePath(this.source, bqS.getBasePath(path));
         if (this.startFadeIn >= 0 && this.endFadeIn >= 0 && this.endFadeOut >= 0) {
            int i = this.normalizeTime(this.endFadeIn - this.startFadeIn);
            if (this.startFadeOut < 0) {
               this.startFadeOut = this.normalizeTime(this.endFadeOut - i);
               if (this.timeBetween(this.startFadeOut, this.startFadeIn, this.endFadeIn)) {
                  this.startFadeOut = this.endFadeIn;
               }
            }

            int j = this.normalizeTime(this.startFadeOut - this.endFadeIn);
            int k = this.normalizeTime(this.endFadeOut - this.startFadeOut);
            int l = this.normalizeTime(this.startFadeIn - this.endFadeOut);
            int i1 = i + j + k + l;
            if (i1 != 24000) {
               XH.warn("Invalid fadeIn/fadeOut times, sum is not 24h: " + i1);
               return false;
            } else if (this.speed < 0.0F) {
               XH.warn("Invalid speed: " + this.speed);
               return false;
            } else if (this.daysLoop <= 0) {
               XH.warn("Invalid daysLoop: " + this.daysLoop);
               return false;
            } else {
               return true;
            }
         } else {
            XH.warn("Invalid times, required are: startFadeIn, endFadeIn and endFadeOut.");
            return false;
         }
      }
   }

   private int normalizeTime(int timeMc) {
      while(timeMc >= 24000) {
         timeMc -= 24000;
      }

      while(timeMc < 0) {
         timeMc += 24000;
      }

      return timeMc;
   }

   public void render(bij world, int timeOfDay, float celestialAngle, float rainStrength, float thunderStrength) {
      float f = this.getPositionBrightness(world);
      float f1 = this.getWeatherBrightness(rainStrength, thunderStrength);
      float f2 = this.getFadeBrightness(timeOfDay);
      float f3 = f * f1 * f2;
      f3 = XH.limit(f3, 0.0F, 1.0F);
      if (f3 >= 1.0E-4F) {
         yh.bindTexture(this.textureId);
         bnV.setupBlend(this.blend, f3);
         yh.pushMatrix();
         if (this.rotate) {
            float f4 = 0.0F;
            if (this.speed != (float)Math.round(this.speed)) {
               long i = (world.getWorldTime() + 18000L) / 24000L;
               double d0 = (double)(this.speed % 1.0F);
               double d1 = (double)i * d0;
               f4 = (float)(d1 % 1.0);
            }

            yh.rotate(360.0F * (f4 + celestialAngle * this.speed), this.axis[0], this.axis[1], this.axis[2]);
         }

         yN tessellator = yN.getInstance();
         yh.rotate(90.0F, 1.0F, 0.0F, 0.0F);
         yh.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
         this.renderSide(tessellator, 4);
         yh.pushMatrix();
         yh.rotate(90.0F, 1.0F, 0.0F, 0.0F);
         this.renderSide(tessellator, 1);
         yh.popMatrix();
         yh.pushMatrix();
         yh.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
         this.renderSide(tessellator, 0);
         yh.popMatrix();
         yh.rotate(90.0F, 0.0F, 0.0F, 1.0F);
         this.renderSide(tessellator, 5);
         yh.rotate(90.0F, 0.0F, 0.0F, 1.0F);
         this.renderSide(tessellator, 2);
         yh.rotate(90.0F, 0.0F, 0.0F, 1.0F);
         this.renderSide(tessellator, 3);
         yh.popMatrix();
      }

   }

   private float getPositionBrightness(bij world) {
      if (this.biomes == null && this.heights == null) {
         return 1.0F;
      } else {
         float f = this.getPositionBrightnessRaw(world);
         if (this.smoothPositionBrightness == null) {
            this.smoothPositionBrightness = new bqO(f, this.transition);
         }

         f = this.smoothPositionBrightness.getSmoothValue(f);
         return f;
      }
   }

   private float getPositionBrightnessRaw(bij world) {
      Ig entity = nC.getMinecraft().getRenderViewEntity();
      if (entity == null) {
         return 0.0F;
      } else {
         BlockPos blockpos = entity.getPosition();
         if (this.biomes != null) {
            Zi biome = world.getBiome(blockpos);
            if (biome == null) {
               return 0.0F;
            }

            if (!bja.biome(biome, this.biomes)) {
               return 0.0F;
            }
         }

         return this.heights != null && !this.heights.isInRange(blockpos.getY()) ? 0.0F : 1.0F;
      }
   }

   private float getWeatherBrightness(float rainStrength, float thunderStrength) {
      float f = 1.0F - rainStrength;
      float f1 = rainStrength - thunderStrength;
      float f2 = 0.0F;
      if (this.weatherClear) {
         f2 += f;
      }

      if (this.weatherRain) {
         f2 += f1;
      }

      if (this.weatherThunder) {
         f2 += thunderStrength;
      }

      f2 = bqK.limit(f2, 0.0F, 1.0F);
      return f2;
   }

   private float getFadeBrightness(int timeOfDay) {
      int i;
      int j;
      if (this.timeBetween(timeOfDay, this.startFadeIn, this.endFadeIn)) {
         i = this.normalizeTime(this.endFadeIn - this.startFadeIn);
         j = this.normalizeTime(timeOfDay - this.startFadeIn);
         return (float)j / (float)i;
      } else if (this.timeBetween(timeOfDay, this.endFadeIn, this.startFadeOut)) {
         return 1.0F;
      } else if (this.timeBetween(timeOfDay, this.startFadeOut, this.endFadeOut)) {
         i = this.normalizeTime(this.endFadeOut - this.startFadeOut);
         j = this.normalizeTime(timeOfDay - this.startFadeOut);
         return 1.0F - (float)j / (float)i;
      } else {
         return 0.0F;
      }
   }

   private void renderSide(yN tess, int side) {
      tN bufferbuilder = tess.getBuffer();
      double d0 = (double)(side % 3) / 3.0;
      double d1 = (double)(side / 3) / 2.0;
      bufferbuilder.begin(7, zK.POSITION_TEX);
      bufferbuilder.pos(-100.0, -100.0, -100.0).tex(d0, d1).endVertex();
      bufferbuilder.pos(-100.0, -100.0, 100.0).tex(d0, d1 + 0.5).endVertex();
      bufferbuilder.pos(100.0, -100.0, 100.0).tex(d0 + 0.3333333333333333, d1 + 0.5).endVertex();
      bufferbuilder.pos(100.0, -100.0, -100.0).tex(d0 + 0.3333333333333333, d1).endVertex();
      tess.draw();
   }

   public boolean isActive(bij world, int timeOfDay) {
      if (world != this.lastWorld) {
         this.lastWorld = world;
         this.smoothPositionBrightness = null;
      }

      if (this.timeBetween(timeOfDay, this.endFadeOut, this.startFadeIn)) {
         return false;
      } else {
         if (this.days != null) {
            long i = world.getWorldTime();

            long j;
            for(j = i - (long)this.startFadeIn; j < 0L; j += (long)(24000 * this.daysLoop)) {
            }

            int k = (int)(j / 24000L);
            int l = k % this.daysLoop;
            if (!this.days.isInRange(l)) {
               return false;
            }
         }

         return true;
      }
   }

   private boolean timeBetween(int timeOfDay, int timeStart, int timeEnd) {
      if (timeStart <= timeEnd) {
         return timeOfDay >= timeStart && timeOfDay <= timeEnd;
      } else {
         return timeOfDay >= timeStart || timeOfDay <= timeEnd;
      }
   }

   public String toString() {
      return "" + this.source + ", " + this.startFadeIn + "-" + this.endFadeIn + " " + this.startFadeOut + "-" + this.endFadeOut;
   }
}
