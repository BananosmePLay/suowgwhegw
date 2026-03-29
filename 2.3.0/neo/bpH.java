package neo;

import net.minecraft.util.math.BlockPos;

public enum bpH {
   BIOME("biome"),
   TEMPERATURE("temperature"),
   RAINFALL("rainfall"),
   HELD_ITEM_ID(bpq.uniform_heldItemId),
   HELD_BLOCK_LIGHT_VALUE(bpq.uniform_heldBlockLightValue),
   HELD_ITEM_ID2(bpq.uniform_heldItemId2),
   HELD_BLOCK_LIGHT_VALUE2(bpq.uniform_heldBlockLightValue2),
   WORLD_TIME(bpq.uniform_worldTime),
   WORLD_DAY(bpq.uniform_worldDay),
   MOON_PHASE(bpq.uniform_moonPhase),
   FRAME_COUNTER(bpq.uniform_frameCounter),
   FRAME_TIME(bpq.uniform_frameTime),
   FRAME_TIME_COUNTER(bpq.uniform_frameTimeCounter),
   SUN_ANGLE(bpq.uniform_sunAngle),
   SHADOW_ANGLE(bpq.uniform_shadowAngle),
   RAIN_STRENGTH(bpq.uniform_rainStrength),
   ASPECT_RATIO(bpq.uniform_aspectRatio),
   VIEW_WIDTH(bpq.uniform_viewWidth),
   VIEW_HEIGHT(bpq.uniform_viewHeight),
   NEAR(bpq.uniform_near),
   FAR(bpq.uniform_far),
   WETNESS(bpq.uniform_wetness),
   EYE_ALTITUDE(bpq.uniform_eyeAltitude),
   EYE_BRIGHTNESS(bpq.uniform_eyeBrightness, new String[]{"x", "y"}),
   TERRAIN_TEXTURE_SIZE(bpq.uniform_terrainTextureSize, new String[]{"x", "y"}),
   TERRRAIN_ICON_SIZE(bpq.uniform_terrainIconSize),
   IS_EYE_IN_WATER(bpq.uniform_isEyeInWater),
   NIGHT_VISION(bpq.uniform_nightVision),
   BLINDNESS(bpq.uniform_blindness),
   SCREEN_BRIGHTNESS(bpq.uniform_screenBrightness),
   HIDE_GUI(bpq.uniform_hideGUI),
   CENTER_DEPT_SMOOTH(bpq.uniform_centerDepthSmooth),
   ATLAS_SIZE(bpq.uniform_atlasSize, new String[]{"x", "y"}),
   CAMERA_POSITION(bpq.uniform_cameraPosition, new String[]{"x", "y", "z"}),
   PREVIOUS_CAMERA_POSITION(bpq.uniform_previousCameraPosition, new String[]{"x", "y", "z"}),
   SUN_POSITION(bpq.uniform_sunPosition, new String[]{"x", "y", "z"}),
   MOON_POSITION(bpq.uniform_moonPosition, new String[]{"x", "y", "z"}),
   SHADOW_LIGHT_POSITION(bpq.uniform_shadowLightPosition, new String[]{"x", "y", "z"}),
   UP_POSITION(bpq.uniform_upPosition, new String[]{"x", "y", "z"}),
   SKY_COLOR(bpq.uniform_skyColor, new String[]{"r", "g", "b"}),
   GBUFFER_PROJECTION(bpq.uniform_gbufferProjection, new String[]{"0", "1", "2", "3"}, new String[]{"0", "1", "2", "3"}),
   GBUFFER_PROJECTION_INVERSE(bpq.uniform_gbufferProjectionInverse, new String[]{"0", "1", "2", "3"}, new String[]{"0", "1", "2", "3"}),
   GBUFFER_PREVIOUS_PROJECTION(bpq.uniform_gbufferPreviousProjection, new String[]{"0", "1", "2", "3"}, new String[]{"0", "1", "2", "3"}),
   GBUFFER_MODEL_VIEW(bpq.uniform_gbufferModelView, new String[]{"0", "1", "2", "3"}, new String[]{"0", "1", "2", "3"}),
   GBUFFER_MODEL_VIEW_INVERSE(bpq.uniform_gbufferModelViewInverse, new String[]{"0", "1", "2", "3"}, new String[]{"0", "1", "2", "3"}),
   GBUFFER_PREVIOUS_MODEL_VIEW(bpq.uniform_gbufferPreviousModelView, new String[]{"0", "1", "2", "3"}, new String[]{"0", "1", "2", "3"}),
   SHADOW_PROJECTION(bpq.uniform_shadowProjection, new String[]{"0", "1", "2", "3"}, new String[]{"0", "1", "2", "3"}),
   SHADOW_PROJECTION_INVERSE(bpq.uniform_shadowProjectionInverse, new String[]{"0", "1", "2", "3"}, new String[]{"0", "1", "2", "3"}),
   SHADOW_MODEL_VIEW(bpq.uniform_shadowModelView, new String[]{"0", "1", "2", "3"}, new String[]{"0", "1", "2", "3"}),
   SHADOW_MODEL_VIEW_INVERSE(bpq.uniform_shadowModelViewInverse, new String[]{"0", "1", "2", "3"}, new String[]{"0", "1", "2", "3"});

   private String name;
   private bpQ uniform;
   private String[] indexNames1;
   private String[] indexNames2;

   private bpH(String name) {
      this.name = name;
   }

   private bpH(bpQ uniform) {
      this.name = uniform.getName();
      this.uniform = uniform;
      if (!instanceOf(uniform, bpJ.class, bpK.class)) {
         throw new IllegalArgumentException("Invalid uniform type for enum: " + this + ", uniform: " + uniform.getClass().getName());
      }
   }

   private bpH(bpQ uniform, String[] indexNames1) {
      this.name = uniform.getName();
      this.uniform = uniform;
      this.indexNames1 = indexNames1;
      if (!instanceOf(uniform, bpM.class, bpL.class, bpN.class, bpO.class)) {
         throw new IllegalArgumentException("Invalid uniform type for enum: " + this + ", uniform: " + uniform.getClass().getName());
      }
   }

   private bpH(bpQ uniform, String[] indexNames1, String[] indexNames2) {
      this.name = uniform.getName();
      this.uniform = uniform;
      this.indexNames1 = indexNames1;
      this.indexNames2 = indexNames2;
      if (!instanceOf(uniform, bpR.class)) {
         throw new IllegalArgumentException("Invalid uniform type for enum: " + this + ", uniform: " + uniform.getClass().getName());
      }
   }

   public String getName() {
      return this.name;
   }

   public bpQ getUniform() {
      return this.uniform;
   }

   public String[] getIndexNames1() {
      return this.indexNames1;
   }

   public String[] getIndexNames2() {
      return this.indexNames2;
   }

   public float eval(int index1, int index2) {
      if (this.indexNames1 == null || index1 >= 0 && index1 <= this.indexNames1.length) {
         if (this.indexNames2 == null || index2 >= 0 && index2 <= this.indexNames2.length) {
            switch (this) {
               case BIOME:
                  BlockPos blockpos2 = bpq.getCameraPosition();
                  Zi biome2 = bpq.getCurrentWorld().getBiome(blockpos2);
                  return (float)Zi.getIdForBiome(biome2);
               case TEMPERATURE:
                  BlockPos blockpos1 = bpq.getCameraPosition();
                  Zi biome1 = bpq.getCurrentWorld().getBiome(blockpos1);
                  return biome1 != null ? biome1.getTemperature(blockpos1) : 0.0F;
               case RAINFALL:
                  BlockPos blockpos = bpq.getCameraPosition();
                  Zi biome = bpq.getCurrentWorld().getBiome(blockpos);
                  return biome != null ? biome.getRainfall() : 0.0F;
               default:
                  if (this.uniform instanceof bpJ) {
                     return ((bpJ)this.uniform).getValue();
                  } else if (this.uniform instanceof bpK) {
                     return (float)((bpK)this.uniform).getValue();
                  } else if (this.uniform instanceof bpM) {
                     return (float)((bpM)this.uniform).getValue()[index1];
                  } else if (this.uniform instanceof bpL) {
                     return ((bpL)this.uniform).getValue()[index1];
                  } else if (this.uniform instanceof bpN) {
                     return ((bpN)this.uniform).getValue()[index1];
                  } else if (this.uniform instanceof bpO) {
                     return ((bpO)this.uniform).getValue()[index1];
                  } else if (this.uniform instanceof bpR) {
                     return ((bpR)this.uniform).getValue(index1, index2);
                  } else {
                     throw new IllegalArgumentException("Unknown uniform type: " + this);
                  }
            }
         } else {
            XH.warn("Invalid index2, parameter: " + this + ", index: " + index2);
            return 0.0F;
         }
      } else {
         XH.warn("Invalid index1, parameter: " + this + ", index: " + index1);
         return 0.0F;
      }
   }

   private static boolean instanceOf(Object obj, Class... classes) {
      if (obj == null) {
         return false;
      } else {
         Class oclass = obj.getClass();

         for(int i = 0; i < classes.length; ++i) {
            Class oclass1 = classes[i];
            if (oclass1.isAssignableFrom(oclass)) {
               return true;
            }
         }

         return false;
      }
   }
}
