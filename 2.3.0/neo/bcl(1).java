package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import net.minecraft.util.math.MathHelper;

public class bcl {
   private final List<bcm> flatLayers = Lists.newArrayList();
   private final Map<String, Map<String, String>> worldFeatures = Maps.newHashMap();
   private int biomeToUse;

   public bcl() {
   }

   public int getBiome() {
      return this.biomeToUse;
   }

   public void setBiome(int biome) {
      this.biomeToUse = biome;
   }

   public Map<String, Map<String, String>> getWorldFeatures() {
      return this.worldFeatures;
   }

   public List<bcm> getFlatLayers() {
      return this.flatLayers;
   }

   public void updateLayers() {
      int i = 0;

      bcm flatlayerinfo;
      for(Iterator var2 = this.flatLayers.iterator(); var2.hasNext(); i += flatlayerinfo.getLayerCount()) {
         flatlayerinfo = (bcm)var2.next();
         flatlayerinfo.setMinY(i);
      }

   }

   public String toString() {
      StringBuilder stringbuilder = new StringBuilder();
      stringbuilder.append(3);
      stringbuilder.append(";");

      int k;
      for(k = 0; k < this.flatLayers.size(); ++k) {
         if (k > 0) {
            stringbuilder.append(",");
         }

         stringbuilder.append(this.flatLayers.get(k));
      }

      stringbuilder.append(";");
      stringbuilder.append(this.biomeToUse);
      if (this.worldFeatures.isEmpty()) {
         stringbuilder.append(";");
         return stringbuilder.toString();
      } else {
         stringbuilder.append(";");
         k = 0;
         Iterator var3 = this.worldFeatures.entrySet().iterator();

         while(true) {
            Map map;
            do {
               if (!var3.hasNext()) {
                  return stringbuilder.toString();
               }

               Map.Entry<String, Map<String, String>> entry = (Map.Entry)var3.next();
               if (k++ > 0) {
                  stringbuilder.append(",");
               }

               stringbuilder.append(((String)entry.getKey()).toLowerCase(Locale.ROOT));
               map = (Map)entry.getValue();
            } while(map.isEmpty());

            stringbuilder.append("(");
            int j = 0;
            Iterator var7 = map.entrySet().iterator();

            while(var7.hasNext()) {
               Map.Entry<String, String> entry1 = (Map.Entry)var7.next();
               if (j++ > 0) {
                  stringbuilder.append(" ");
               }

               stringbuilder.append((String)entry1.getKey());
               stringbuilder.append("=");
               stringbuilder.append((String)entry1.getValue());
            }

            stringbuilder.append(")");
         }
      }
   }

   private static bcm getLayerFromString(int p_180715_0_, String p_180715_1_, int p_180715_2_) {
      String[] astring = p_180715_0_ >= 3 ? p_180715_1_.split("\\*", 2) : p_180715_1_.split("x", 2);
      int i = 1;
      int j = 0;
      if (astring.length == 2) {
         try {
            i = Integer.parseInt(astring[0]);
            if (p_180715_2_ + i >= 256) {
               i = 256 - p_180715_2_;
            }

            if (i < 0) {
               i = 0;
            }
         } catch (Throwable var8) {
            return null;
         }
      }

      co block;
      try {
         String s = astring[astring.length - 1];
         if (p_180715_0_ < 3) {
            astring = s.split(":", 2);
            if (astring.length > 1) {
               j = Integer.parseInt(astring[1]);
            }

            block = co.getBlockById(Integer.parseInt(astring[0]));
         } else {
            astring = s.split(":", 3);
            block = astring.length > 1 ? co.getBlockFromName(astring[0] + ":" + astring[1]) : null;
            if (block != null) {
               j = astring.length > 2 ? Integer.parseInt(astring[2]) : 0;
            } else {
               block = co.getBlockFromName(astring[0]);
               if (block != null) {
                  j = astring.length > 1 ? Integer.parseInt(astring[1]) : 0;
               }
            }

            if (block == null) {
               return null;
            }
         }

         if (block == Nk.AIR) {
            j = 0;
         }

         if (j < 0 || j > 15) {
            j = 0;
         }
      } catch (Throwable var9) {
         return null;
      }

      bcm flatlayerinfo = new bcm(p_180715_0_, i, block, j);
      flatlayerinfo.setMinY(p_180715_2_);
      return flatlayerinfo;
   }

   private static List<bcm> getLayersFromString(int p_180716_0_, String p_180716_1_) {
      if (p_180716_1_ != null && p_180716_1_.length() >= 1) {
         List<bcm> list = Lists.newArrayList();
         String[] astring = p_180716_1_.split(",");
         int i = 0;
         String[] var5 = astring;
         int var6 = astring.length;

         for(int var7 = 0; var7 < var6; ++var7) {
            String s = var5[var7];
            bcm flatlayerinfo = getLayerFromString(p_180716_0_, s, i);
            if (flatlayerinfo == null) {
               return null;
            }

            list.add(flatlayerinfo);
            i += flatlayerinfo.getLayerCount();
         }

         return list;
      } else {
         return null;
      }
   }

   public static bcl createFlatGeneratorFromString(String flatGeneratorSettings) {
      if (flatGeneratorSettings == null) {
         return getDefaultFlatGenerator();
      } else {
         String[] astring = flatGeneratorSettings.split(";", -1);
         int i = astring.length == 1 ? 0 : MathHelper.getInt(astring[0], 0);
         if (i >= 0 && i <= 3) {
            bcl flatgeneratorinfo = new bcl();
            int j = astring.length == 1 ? 0 : 1;
            List<bcm> list = getLayersFromString(i, astring[j++]);
            if (list != null && !list.isEmpty()) {
               flatgeneratorinfo.getFlatLayers().addAll(list);
               flatgeneratorinfo.updateLayers();
               int k = Zi.getIdForBiome(Nj.PLAINS);
               if (i > 0 && astring.length > j) {
                  k = MathHelper.getInt(astring[j++], k);
               }

               flatgeneratorinfo.setBiome(k);
               if (i > 0 && astring.length > j) {
                  String[] astring1 = astring[j++].toLowerCase(Locale.ROOT).split(",");
                  String[] var8 = astring1;
                  int var9 = astring1.length;

                  for(int var10 = 0; var10 < var9; ++var10) {
                     String s = var8[var10];
                     String[] astring2 = s.split("\\(", 2);
                     Map<String, String> map = Maps.newHashMap();
                     if (!astring2[0].isEmpty()) {
                        flatgeneratorinfo.getWorldFeatures().put(astring2[0], map);
                        if (astring2.length > 1 && astring2[1].endsWith(")") && astring2[1].length() > 1) {
                           String[] astring3 = astring2[1].substring(0, astring2[1].length() - 1).split(" ");
                           String[] var15 = astring3;
                           int var16 = astring3.length;

                           for(int var17 = 0; var17 < var16; ++var17) {
                              String s1 = var15[var17];
                              String[] astring4 = s1.split("=", 2);
                              if (astring4.length == 2) {
                                 map.put(astring4[0], astring4[1]);
                              }
                           }
                        }
                     }
                  }
               } else {
                  flatgeneratorinfo.getWorldFeatures().put("village", Maps.newHashMap());
               }

               return flatgeneratorinfo;
            } else {
               return getDefaultFlatGenerator();
            }
         } else {
            return getDefaultFlatGenerator();
         }
      }
   }

   public static bcl getDefaultFlatGenerator() {
      bcl flatgeneratorinfo = new bcl();
      flatgeneratorinfo.setBiome(Zi.getIdForBiome(Nj.PLAINS));
      flatgeneratorinfo.getFlatLayers().add(new bcm(1, Nk.BEDROCK));
      flatgeneratorinfo.getFlatLayers().add(new bcm(2, Nk.DIRT));
      flatgeneratorinfo.getFlatLayers().add(new bcm(1, Nk.GRASS));
      flatgeneratorinfo.updateLayers();
      flatgeneratorinfo.getWorldFeatures().put("village", Maps.newHashMap());
      return flatgeneratorinfo;
   }
}
