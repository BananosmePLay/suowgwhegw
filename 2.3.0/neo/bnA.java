package neo;

import java.util.Properties;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class bnA {
   private String pathProps = null;
   private ResourceLocation baseResLoc = null;
   private int index;
   private int[] textures = null;
   private ResourceLocation[] resourceLocations = null;
   private int[] weights = null;
   private Zi[] biomes = null;
   private bje heights = null;
   private bje healthRange = null;
   private boolean healthPercent = false;
   private bjb nbtName = null;
   public int[] sumWeights = null;
   public int sumAllWeights = 1;
   private bjf[] professions = null;
   private Om[] collarColors = null;
   private Boolean baby = null;
   private bje moonPhases = null;
   private bje dayTimes = null;
   private bjg[] weatherList = null;

   public bnA(Properties props, String pathProps, ResourceLocation baseResLoc, int index, String valTextures, biS cp) {
      this.pathProps = pathProps;
      this.baseResLoc = baseResLoc;
      this.index = index;
      this.textures = cp.parseIntList(valTextures);
      this.weights = cp.parseIntList(props.getProperty("weights." + index));
      this.biomes = cp.parseBiomes(props.getProperty("biomes." + index));
      this.heights = cp.parseRangeListInt(props.getProperty("heights." + index));
      if (this.heights == null) {
         this.heights = this.parseMinMaxHeight(props, index);
      }

      String s = props.getProperty("health." + index);
      if (s != null) {
         this.healthPercent = s.contains("%");
         s = s.replace("%", "");
         this.healthRange = cp.parseRangeListInt(s);
      }

      this.nbtName = cp.parseNbtTagValue("name", props.getProperty("name." + index));
      this.professions = cp.parseProfessions(props.getProperty("professions." + index));
      this.collarColors = cp.parseDyeColors(props.getProperty("collarColors." + index), "collar color", biS.DYE_COLORS_INVALID);
      this.baby = cp.parseBooleanObject(props.getProperty("baby." + index));
      this.moonPhases = cp.parseRangeListInt(props.getProperty("moonPhase." + index));
      this.dayTimes = cp.parseRangeListInt(props.getProperty("dayTime." + index));
      this.weatherList = cp.parseWeather(props.getProperty("weather." + index), "weather." + index, (bjg[])null);
   }

   private bje parseMinMaxHeight(Properties props, int index) {
      String s = props.getProperty("minHeight." + index);
      String s1 = props.getProperty("maxHeight." + index);
      if (s == null && s1 == null) {
         return null;
      } else {
         int i = 0;
         if (s != null) {
            i = XH.parseInt(s, -1);
            if (i < 0) {
               XH.warn("Invalid minHeight: " + s);
               return null;
            }
         }

         int j = 256;
         if (s1 != null) {
            j = XH.parseInt(s1, -1);
            if (j < 0) {
               XH.warn("Invalid maxHeight: " + s1);
               return null;
            }
         }

         if (j < 0) {
            XH.warn("Invalid minHeight, maxHeight: " + s + ", " + s1);
            return null;
         } else {
            bje rangelistint = new bje();
            rangelistint.addRange(new bjd(i, j));
            return rangelistint;
         }
      }
   }

   public boolean isValid(String path) {
      if (this.textures != null && this.textures.length != 0) {
         if (this.resourceLocations != null) {
            return true;
         } else {
            this.resourceLocations = new ResourceLocation[this.textures.length];
            boolean flag = this.pathProps.startsWith("mcpatcher/mob/");
            ResourceLocation resourcelocation = bnx.getLocationRandom(this.baseResLoc, flag);
            if (resourcelocation == null) {
               XH.warn("Invalid path: " + this.baseResLoc.getPath());
               return false;
            } else {
               int k;
               int i1;
               for(k = 0; k < this.resourceLocations.length; ++k) {
                  i1 = this.textures[k];
                  if (i1 <= 1) {
                     this.resourceLocations[k] = this.baseResLoc;
                  } else {
                     ResourceLocation resourcelocation1 = bnx.getLocationIndexed(resourcelocation, i1);
                     if (resourcelocation1 == null) {
                        XH.warn("Invalid path: " + this.baseResLoc.getPath());
                        return false;
                     }

                     if (!XH.hasResource(resourcelocation1)) {
                        XH.warn("Texture not found: " + resourcelocation1.getPath());
                        return false;
                     }

                     this.resourceLocations[k] = resourcelocation1;
                  }
               }

               if (this.weights != null) {
                  int[] aint1;
                  if (this.weights.length > this.resourceLocations.length) {
                     XH.warn("More weights defined than skins, trimming weights: " + path);
                     aint1 = new int[this.resourceLocations.length];
                     System.arraycopy(this.weights, 0, aint1, 0, aint1.length);
                     this.weights = aint1;
                  }

                  if (this.weights.length < this.resourceLocations.length) {
                     XH.warn("Less weights defined than skins, expanding weights: " + path);
                     aint1 = new int[this.resourceLocations.length];
                     System.arraycopy(this.weights, 0, aint1, 0, this.weights.length);
                     i1 = bqD.getAverage(this.weights);

                     for(int j1 = this.weights.length; j1 < aint1.length; ++j1) {
                        aint1[j1] = i1;
                     }

                     this.weights = aint1;
                  }

                  this.sumWeights = new int[this.weights.length];
                  k = 0;

                  for(i1 = 0; i1 < this.weights.length; ++i1) {
                     if (this.weights[i1] < 0) {
                        XH.warn("Invalid weight: " + this.weights[i1]);
                        return false;
                     }

                     k += this.weights[i1];
                     this.sumWeights[i1] = k;
                  }

                  this.sumAllWeights = k;
                  if (this.sumAllWeights <= 0) {
                     XH.warn("Invalid sum of all weights: " + k);
                     this.sumAllWeights = 1;
                  }
               }

               if (this.professions == biS.PROFESSIONS_INVALID) {
                  XH.warn("Invalid professions or careers: " + path);
                  return false;
               } else if (this.collarColors == biS.DYE_COLORS_INVALID) {
                  XH.warn("Invalid collar colors: " + path);
                  return false;
               } else {
                  return true;
               }
            }
         }
      } else {
         XH.warn("Invalid skins for rule: " + this.index);
         return false;
      }
   }

   public boolean matches(bmR randomEntity) {
      if (this.biomes != null && !bja.biome(randomEntity.getSpawnBiome(), this.biomes)) {
         return false;
      } else {
         if (this.heights != null) {
            BlockPos blockpos = randomEntity.getSpawnPosition();
            if (blockpos != null && !this.heights.isInRange(blockpos.getY())) {
               return false;
            }
         }

         int k1;
         if (this.healthRange != null) {
            int i1 = randomEntity.getHealth();
            if (this.healthPercent) {
               k1 = randomEntity.getMaxHealth();
               if (k1 > 0) {
                  i1 = (int)((double)(i1 * 100) / (double)k1);
               }
            }

            if (!this.healthRange.isInRange(i1)) {
               return false;
            }
         }

         if (this.nbtName != null) {
            String s = randomEntity.getName();
            if (!this.nbtName.matchesValue(s)) {
               return false;
            }
         }

         Ig entity2;
         bny randomentity2;
         if (this.professions != null && randomEntity instanceof bny) {
            randomentity2 = (bny)randomEntity;
            entity2 = randomentity2.getEntity();
            if (entity2 instanceof Mq) {
               Mq entityvillager = (Mq)entity2;
               int j = entityvillager.getProfession();
               int k = bnK.getFieldValueInt(entityvillager, bnK.EntityVillager_careerId, -1);
               if (j < 0 || k < 0) {
                  return false;
               }

               boolean flag = false;

               for(int l = 0; l < this.professions.length; ++l) {
                  bjf villagerprofession = this.professions[l];
                  if (villagerprofession.matches(j, k)) {
                     flag = true;
                     break;
                  }
               }

               if (!flag) {
                  return false;
               }
            }
         }

         if (this.collarColors != null && randomEntity instanceof bny) {
            randomentity2 = (bny)randomEntity;
            entity2 = randomentity2.getEntity();
            if (entity2 instanceof Mu) {
               Mu entitywolf = (Mu)entity2;
               if (!entitywolf.isTamed()) {
                  return false;
               }

               Om enumdyecolor = entitywolf.getCollarColor();
               if (!XH.equalsOne(enumdyecolor, this.collarColors)) {
                  return false;
               }
            }
         }

         if (this.baby != null && randomEntity instanceof bny) {
            randomentity2 = (bny)randomEntity;
            entity2 = randomentity2.getEntity();
            if (entity2 instanceof Iu) {
               Iu entityliving = (Iu)entity2;
               if (entityliving.isChild() != this.baby) {
                  return false;
               }
            }
         }

         pm world2;
         if (this.moonPhases != null) {
            world2 = XH.getMinecraft().world;
            if (world2 != null) {
               k1 = ((bij)world2).getMoonPhase();
               if (!this.moonPhases.isInRange(k1)) {
                  return false;
               }
            }
         }

         if (this.dayTimes != null) {
            world2 = XH.getMinecraft().world;
            if (world2 != null) {
               k1 = (int)((bij)world2).getWorldInfo().getWorldTime();
               if (!this.dayTimes.isInRange(k1)) {
                  return false;
               }
            }
         }

         if (this.weatherList != null) {
            world2 = XH.getMinecraft().world;
            if (world2 != null) {
               bjg weather = bjg.getWeather(world2, 0.0F);
               if (!bqh.contains(this.weatherList, weather)) {
                  return false;
               }
            }
         }

         return true;
      }
   }

   public ResourceLocation getTextureLocation(ResourceLocation loc, int randomId) {
      if (this.resourceLocations != null && this.resourceLocations.length != 0) {
         int i = 0;
         if (this.weights == null) {
            i = randomId % this.resourceLocations.length;
         } else {
            int j = randomId % this.sumAllWeights;

            for(int k = 0; k < this.sumWeights.length; ++k) {
               if (this.sumWeights[k] > j) {
                  i = k;
                  break;
               }
            }
         }

         return this.resourceLocations[i];
      } else {
         return loc;
      }
   }
}
