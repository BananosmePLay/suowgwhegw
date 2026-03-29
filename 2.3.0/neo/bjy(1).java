package neo;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import javax.imageio.ImageIO;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class bjy {
   private static String paletteFormatDefault = "vanilla";
   private static bjr waterColors = null;
   private static bjr foliagePineColors = null;
   private static bjr foliageBirchColors = null;
   private static bjr swampFoliageColors = null;
   private static bjr swampGrassColors = null;
   private static bjr[] colorsBlockColormaps = null;
   private static bjr[][] blockColormaps = (bjr[][])((bjr[][])null);
   private static bjr skyColors = null;
   private static bjq skyColorFader = new bjq();
   private static bjr fogColors = null;
   private static bjq fogColorFader = new bjq();
   private static bjr underwaterColors = null;
   private static bjq underwaterColorFader = new bjq();
   private static bjr underlavaColors = null;
   private static bjq underlavaColorFader = new bjq();
   private static bmY[] lightMapPacks = null;
   private static int lightmapMinDimensionId = 0;
   private static bjr redstoneColors = null;
   private static bjr xpOrbColors = null;
   private static int xpOrbTime = -1;
   private static bjr durabilityColors = null;
   private static bjr stemColors = null;
   private static bjr stemMelonColors = null;
   private static bjr stemPumpkinColors = null;
   private static bjr myceliumParticleColors = null;
   private static boolean useDefaultGrassFoliageColors = true;
   private static int particleWaterColor = -1;
   private static int particlePortalColor = -1;
   private static int lilyPadColor = -1;
   private static int expBarTextColor = -1;
   private static int bossTextColor = -1;
   private static int signTextColor = -1;
   private static Vec3d fogColorNether = null;
   private static Vec3d fogColorEnd = null;
   private static Vec3d skyColorEnd = null;
   private static int[] spawnEggPrimaryColors = null;
   private static int[] spawnEggSecondaryColors = null;
   private static float[][] wolfCollarColors = (float[][])((float[][])null);
   private static float[][] sheepColors = (float[][])((float[][])null);
   private static int[] textColors = null;
   private static int[] mapColorsOriginal = null;
   private static int[] potionColors = null;
   private static final in BLOCK_STATE_DIRT;
   private static final in BLOCK_STATE_WATER;
   public static Random random;
   private static final bjx COLORIZER_GRASS;
   private static final bjx COLORIZER_FOLIAGE;
   private static final bjx COLORIZER_FOLIAGE_PINE;
   private static final bjx COLORIZER_FOLIAGE_BIRCH;
   private static final bjx COLORIZER_WATER;

   public bjy() {
   }

   public static void update() {
      paletteFormatDefault = "vanilla";
      waterColors = null;
      foliageBirchColors = null;
      foliagePineColors = null;
      swampGrassColors = null;
      swampFoliageColors = null;
      skyColors = null;
      fogColors = null;
      underwaterColors = null;
      underlavaColors = null;
      redstoneColors = null;
      xpOrbColors = null;
      xpOrbTime = -1;
      durabilityColors = null;
      stemColors = null;
      myceliumParticleColors = null;
      lightMapPacks = null;
      particleWaterColor = -1;
      particlePortalColor = -1;
      lilyPadColor = -1;
      expBarTextColor = -1;
      bossTextColor = -1;
      signTextColor = -1;
      fogColorNether = null;
      fogColorEnd = null;
      skyColorEnd = null;
      colorsBlockColormaps = null;
      blockColormaps = (bjr[][])((bjr[][])null);
      useDefaultGrassFoliageColors = true;
      spawnEggPrimaryColors = null;
      spawnEggSecondaryColors = null;
      wolfCollarColors = (float[][])((float[][])null);
      sheepColors = (float[][])((float[][])null);
      textColors = null;
      setMapColors(mapColorsOriginal);
      potionColors = null;
      paletteFormatDefault = getValidProperty("mcpatcher/color.properties", "palette.format", bjr.FORMAT_STRINGS, "vanilla");
      String s = "mcpatcher/colormap/";
      String[] astring = new String[]{"water.png", "watercolorX.png"};
      waterColors = getCustomColors(s, astring, 256, 256);
      updateUseDefaultGrassFoliageColors();
      if (XH.isCustomColors()) {
         String[] astring1 = new String[]{"pine.png", "pinecolor.png"};
         foliagePineColors = getCustomColors(s, astring1, 256, 256);
         String[] astring2 = new String[]{"birch.png", "birchcolor.png"};
         foliageBirchColors = getCustomColors(s, astring2, 256, 256);
         String[] astring3 = new String[]{"swampgrass.png", "swampgrasscolor.png"};
         swampGrassColors = getCustomColors(s, astring3, 256, 256);
         String[] astring4 = new String[]{"swampfoliage.png", "swampfoliagecolor.png"};
         swampFoliageColors = getCustomColors(s, astring4, 256, 256);
         String[] astring5 = new String[]{"sky0.png", "skycolor0.png"};
         skyColors = getCustomColors(s, astring5, 256, 256);
         String[] astring6 = new String[]{"fog0.png", "fogcolor0.png"};
         fogColors = getCustomColors(s, astring6, 256, 256);
         String[] astring7 = new String[]{"underwater.png", "underwatercolor.png"};
         underwaterColors = getCustomColors(s, astring7, 256, 256);
         String[] astring8 = new String[]{"underlava.png", "underlavacolor.png"};
         underlavaColors = getCustomColors(s, astring8, 256, 256);
         String[] astring9 = new String[]{"redstone.png", "redstonecolor.png"};
         redstoneColors = getCustomColors(s, astring9, 16, 1);
         xpOrbColors = getCustomColors(s + "xporb.png", -1, -1);
         durabilityColors = getCustomColors(s + "durability.png", -1, -1);
         String[] astring10 = new String[]{"stem.png", "stemcolor.png"};
         stemColors = getCustomColors(s, astring10, 8, 1);
         stemPumpkinColors = getCustomColors(s + "pumpkinstem.png", 8, 1);
         stemMelonColors = getCustomColors(s + "melonstem.png", 8, 1);
         String[] astring11 = new String[]{"myceliumparticle.png", "myceliumparticlecolor.png"};
         myceliumParticleColors = getCustomColors(s, astring11, -1, -1);
         Pair<bmY[], Integer> pair = parseLightMapPacks();
         lightMapPacks = (bmY[])pair.getLeft();
         lightmapMinDimensionId = (Integer)pair.getRight();
         readColorProperties("mcpatcher/color.properties");
         blockColormaps = readBlockColormaps(new String[]{s + "custom/", s + "blocks/"}, colorsBlockColormaps, 256, 256);
         updateUseDefaultGrassFoliageColors();
      }

   }

   private static String getValidProperty(String fileName, String key, String[] validValues, String valDef) {
      try {
         ResourceLocation resourcelocation = new ResourceLocation(fileName);
         InputStream inputstream = XH.getResourceStream(resourcelocation);
         if (inputstream == null) {
            return valDef;
         } else {
            Properties properties = new bqL();
            ((Properties)properties).load(inputstream);
            inputstream.close();
            String s = ((Properties)properties).getProperty(key);
            if (s == null) {
               return valDef;
            } else {
               List<String> list = Arrays.asList(validValues);
               if (!list.contains(s)) {
                  warn("Invalid value: " + key + "=" + s);
                  warn("Expected values: " + XH.arrayToString((Object[])validValues));
                  return valDef;
               } else {
                  dbg("" + key + "=" + s);
                  return s;
               }
            }
         }
      } catch (FileNotFoundException var9) {
         return valDef;
      } catch (IOException var10) {
         IOException ioexception = var10;
         ioexception.printStackTrace();
         return valDef;
      }
   }

   private static Pair<bmY[], Integer> parseLightMapPacks() {
      String s = "mcpatcher/lightmap/world";
      String s1 = ".png";
      String[] astring = bqN.collectFiles(s, s1);
      Map<Integer, String> map = new HashMap();

      int k1;
      for(int i = 0; i < astring.length; ++i) {
         String s2 = astring[i];
         String s3 = bqP.removePrefixSuffix(s2, s, s1);
         k1 = XH.parseInt(s3, Integer.MIN_VALUE);
         if (k1 == Integer.MIN_VALUE) {
            warn("Invalid dimension ID: " + s3 + ", path: " + s2);
         } else {
            map.put(k1, s2);
         }
      }

      Set<Integer> set = map.keySet();
      Integer[] ainteger = (Integer[])((Integer[])set.toArray(new Integer[set.size()]));
      Arrays.sort((Object[])ainteger);
      if (ainteger.length <= 0) {
         return new ImmutablePair((Object)null, 0);
      } else {
         int j1 = ainteger[0];
         k1 = ainteger[ainteger.length - 1];
         int k = k1 - j1 + 1;
         bjr[] acustomcolormap = new bjr[k];

         for(int l = 0; l < ainteger.length; ++l) {
            Integer integer = ainteger[l];
            String s4 = (String)map.get(integer);
            bjr customcolormap = getCustomColors(s4, -1, -1);
            if (customcolormap != null) {
               if (customcolormap.getWidth() < 16) {
                  warn("Invalid lightmap width: " + customcolormap.getWidth() + ", path: " + s4);
               } else {
                  int i1 = integer - j1;
                  acustomcolormap[i1] = customcolormap;
               }
            }
         }

         bmY[] alightmappack = new bmY[acustomcolormap.length];

         for(int l1 = 0; l1 < acustomcolormap.length; ++l1) {
            bjr customcolormap3 = acustomcolormap[l1];
            if (customcolormap3 != null) {
               String s5 = customcolormap3.name;
               String s6 = customcolormap3.basePath;
               bjr customcolormap1 = getCustomColors(s6 + "/" + s5 + "_rain.png", -1, -1);
               bjr customcolormap2 = getCustomColors(s6 + "/" + s5 + "_thunder.png", -1, -1);
               bmX lightmap = new bmX(customcolormap3);
               bmX lightmap1 = customcolormap1 != null ? new bmX(customcolormap1) : null;
               bmX lightmap2 = customcolormap2 != null ? new bmX(customcolormap2) : null;
               bmY lightmappack = new bmY(lightmap, lightmap1, lightmap2);
               alightmappack[l1] = lightmappack;
            }
         }

         return new ImmutablePair(alightmappack, j1);
      }
   }

   private static int getTextureHeight(String path, int defHeight) {
      try {
         InputStream inputstream = XH.getResourceStream(new ResourceLocation(path));
         if (inputstream == null) {
            return defHeight;
         } else {
            BufferedImage bufferedimage = ImageIO.read(inputstream);
            inputstream.close();
            return bufferedimage == null ? defHeight : bufferedimage.getHeight();
         }
      } catch (IOException var4) {
         return defHeight;
      }
   }

   private static void readColorProperties(String fileName) {
      try {
         ResourceLocation resourcelocation = new ResourceLocation(fileName);
         InputStream inputstream = XH.getResourceStream(resourcelocation);
         if (inputstream == null) {
            return;
         }

         dbg("Loading " + fileName);
         Properties properties = new bqL();
         ((Properties)properties).load(inputstream);
         inputstream.close();
         particleWaterColor = readColor(properties, (String[])(new String[]{"particle.water", "drop.water"}));
         particlePortalColor = readColor(properties, (String)"particle.portal");
         lilyPadColor = readColor(properties, (String)"lilypad");
         expBarTextColor = readColor(properties, (String)"text.xpbar");
         bossTextColor = readColor(properties, (String)"text.boss");
         signTextColor = readColor(properties, (String)"text.sign");
         fogColorNether = readColorVec3(properties, "fog.nether");
         fogColorEnd = readColorVec3(properties, "fog.end");
         skyColorEnd = readColorVec3(properties, "sky.end");
         colorsBlockColormaps = readCustomColormaps(properties, fileName);
         spawnEggPrimaryColors = readSpawnEggColors(properties, fileName, "egg.shell.", "Spawn egg shell");
         spawnEggSecondaryColors = readSpawnEggColors(properties, fileName, "egg.spots.", "Spawn egg spot");
         wolfCollarColors = readDyeColors(properties, fileName, "collar.", "Wolf collar");
         sheepColors = readDyeColors(properties, fileName, "sheep.", "Sheep");
         textColors = readTextColors(properties, fileName, "text.code.", "Text");
         int[] aint = readMapColors(properties, fileName, "map.", "Map");
         if (aint != null) {
            if (mapColorsOriginal == null) {
               mapColorsOriginal = getMapColors();
            }

            setMapColors(aint);
         }

         potionColors = readPotionColors(properties, fileName, "potion.", "Potion");
         xpOrbTime = XH.parseInt(((Properties)properties).getProperty("xporb.time"), -1);
      } catch (FileNotFoundException var5) {
         return;
      } catch (IOException var6) {
         IOException ioexception = var6;
         ioexception.printStackTrace();
      }

   }

   private static bjr[] readCustomColormaps(Properties props, String fileName) {
      List list = new ArrayList();
      String s = "palette.block.";
      Map map = new HashMap();
      Iterator var5 = props.keySet().iterator();

      String s6;
      String s3;
      while(var5.hasNext()) {
         Object s11 = var5.next();
         s6 = (String)s11;
         s3 = props.getProperty(s6);
         if (s6.startsWith(s)) {
            map.put(s6, s3);
         }
      }

      String[] astring = (String[])((String[])map.keySet().toArray(new String[map.size()]));

      for(int j = 0; j < astring.length; ++j) {
         s6 = astring[j];
         s3 = props.getProperty(s6);
         dbg("Block palette: " + s6 + " = " + s3);
         String s4 = s6.substring(s.length());
         String s5 = bqS.getBasePath(fileName);
         s4 = bqS.fixResourcePath(s4, s5);
         bjr customcolormap = getCustomColors(s4, 256, 256);
         if (customcolormap == null) {
            warn("Colormap not found: " + s4);
         } else {
            biS connectedparser = new biS("CustomColors");
            biZ[] amatchblock = connectedparser.parseMatchBlocks(s3);
            if (amatchblock != null && amatchblock.length > 0) {
               for(int i = 0; i < amatchblock.length; ++i) {
                  biZ matchblock = amatchblock[i];
                  customcolormap.addMatchBlock(matchblock);
               }

               list.add(customcolormap);
            } else {
               warn("Invalid match blocks: " + s3);
            }
         }
      }

      if (list.size() <= 0) {
         return null;
      } else {
         bjr[] acustomcolormap = (bjr[])((bjr[])list.toArray(new bjr[list.size()]));
         return acustomcolormap;
      }
   }

   private static bjr[][] readBlockColormaps(String[] basePaths, bjr[] basePalettes, int width, int height) {
      String[] astring = bqN.collectFiles(basePaths, new String[]{".properties"});
      Arrays.sort((Object[])astring);
      List list = new ArrayList();

      int j;
      for(j = 0; j < astring.length; ++j) {
         String s = astring[j];
         dbg("Block colormap: " + s);

         try {
            ResourceLocation resourcelocation = new ResourceLocation("minecraft", s);
            InputStream inputstream = XH.getResourceStream(resourcelocation);
            if (inputstream == null) {
               warn("File not found: " + s);
            } else {
               Properties properties = new bqL();
               ((Properties)properties).load(inputstream);
               inputstream.close();
               bjr customcolormap = new bjr(properties, s, width, height, paletteFormatDefault);
               if (customcolormap.isValid(s) && customcolormap.isValidMatchBlocks(s)) {
                  addToBlockList(customcolormap, list);
               }
            }
         } catch (FileNotFoundException var12) {
            warn("File not found: " + s);
         } catch (Exception var13) {
            Exception exception = var13;
            exception.printStackTrace();
         }
      }

      if (basePalettes != null) {
         for(j = 0; j < basePalettes.length; ++j) {
            bjr customcolormap1 = basePalettes[j];
            addToBlockList(customcolormap1, list);
         }
      }

      if (list.size() <= 0) {
         return (bjr[][])((bjr[][])null);
      } else {
         bjr[][] acustomcolormap = blockListToArray(list);
         return acustomcolormap;
      }
   }

   private static void addToBlockList(bjr cm, List blockList) {
      int[] aint = cm.getMatchBlockIds();
      if (aint != null && aint.length > 0) {
         for(int i = 0; i < aint.length; ++i) {
            int j = aint[i];
            if (j < 0) {
               warn("Invalid block ID: " + j);
            } else {
               addToList(cm, blockList, j);
            }
         }
      } else {
         warn("No match blocks: " + XH.arrayToString(aint));
      }

   }

   private static void addToList(bjr cm, List list, int id) {
      while(id >= list.size()) {
         list.add((Object)null);
      }

      ArrayList<bjr> subList = (ArrayList)list.get(id);
      if (subList == null) {
         subList = new ArrayList();
         list.set(id, subList);
      }

      subList.add(cm);
   }

   private static bjr[][] blockListToArray(List list) {
      bjr[][] colArr = new bjr[list.size()][];

      for(int i2 = 0; i2 < list.size(); ++i2) {
         List<bjr> subList = (List)list.get(i2);
         if (subList != null) {
            bjr[] subArr = (bjr[])subList.toArray(new bjr[subList.size()]);
            colArr[i2] = subArr;
         }
      }

      return colArr;
   }

   private static int readColor(Properties props, String[] names) {
      for(int i = 0; i < names.length; ++i) {
         String s = names[i];
         int j = readColor(props, s);
         if (j >= 0) {
            return j;
         }
      }

      return -1;
   }

   private static int readColor(Properties props, String name) {
      String s = props.getProperty(name);
      if (s == null) {
         return -1;
      } else {
         s = s.trim();
         int i = parseColor(s);
         if (i < 0) {
            warn("Invalid color: " + name + " = " + s);
            return i;
         } else {
            dbg(name + " = " + s);
            return i;
         }
      }
   }

   private static int parseColor(String str) {
      if (str == null) {
         return -1;
      } else {
         str = str.trim();

         try {
            int i = Integer.parseInt(str, 16) & 16777215;
            return i;
         } catch (NumberFormatException var2) {
            return -1;
         }
      }
   }

   private static Vec3d readColorVec3(Properties props, String name) {
      int i = readColor(props, name);
      if (i < 0) {
         return null;
      } else {
         int j = i >> 16 & 255;
         int k = i >> 8 & 255;
         int l = i & 255;
         float f = (float)j / 255.0F;
         float f1 = (float)k / 255.0F;
         float f2 = (float)l / 255.0F;
         return new Vec3d((double)f, (double)f1, (double)f2);
      }
   }

   private static bjr getCustomColors(String basePath, String[] paths, int width, int height) {
      for(int i = 0; i < paths.length; ++i) {
         String s = paths[i];
         s = basePath + s;
         bjr customcolormap = getCustomColors(s, width, height);
         if (customcolormap != null) {
            return customcolormap;
         }
      }

      return null;
   }

   public static bjr getCustomColors(String pathImage, int width, int height) {
      try {
         ResourceLocation resourcelocation = new ResourceLocation(pathImage);
         if (!XH.hasResource(resourcelocation)) {
            return null;
         } else {
            dbg("Colormap " + pathImage);
            Properties properties = new bqL();
            String s = bqP.replaceSuffix(pathImage, ".png", ".properties");
            ResourceLocation resourcelocation1 = new ResourceLocation(s);
            if (XH.hasResource(resourcelocation1)) {
               InputStream inputstream = XH.getResourceStream(resourcelocation1);
               ((Properties)properties).load(inputstream);
               inputstream.close();
               dbg("Colormap properties: " + s);
            } else {
               ((Properties)properties).put("format", paletteFormatDefault);
               ((Properties)properties).put("source", pathImage);
               s = pathImage;
            }

            bjr customcolormap = new bjr(properties, s, width, height, paletteFormatDefault);
            return !customcolormap.isValid(s) ? null : customcolormap;
         }
      } catch (Exception var8) {
         Exception exception = var8;
         exception.printStackTrace();
         return null;
      }
   }

   public static void updateUseDefaultGrassFoliageColors() {
      useDefaultGrassFoliageColors = foliageBirchColors == null && foliagePineColors == null && swampGrassColors == null && swampFoliageColors == null && XH.isSwampColors() && XH.isSmoothBiomes();
   }

   public static int getColorMultiplier(rK quad, in blockState, bfZ blockAccess, BlockPos blockPos, boa renderEnv) {
      co block = blockState.getBlock();
      in iblockstate = renderEnv.getBlockState();
      if (blockColormaps != null) {
         if (!quad.hasTintIndex()) {
            if (block == Nk.GRASS) {
               iblockstate = BLOCK_STATE_DIRT;
            }

            if (block == Nk.REDSTONE_WIRE) {
               return -1;
            }
         }

         if (block == Nk.DOUBLE_PLANT && renderEnv.getMetadata() >= 8) {
            blockPos = blockPos.down();
            iblockstate = blockAccess.getBlockState(blockPos);
         }

         bjr customcolormap = getBlockColormap(iblockstate);
         if (customcolormap != null) {
            if (XH.isSmoothBiomes() && !customcolormap.isColorConstant()) {
               return getSmoothColorMultiplier(blockState, blockAccess, blockPos, customcolormap, renderEnv.getColorizerBlockPosM());
            }

            return customcolormap.getColor(blockAccess, blockPos);
         }
      }

      if (!quad.hasTintIndex()) {
         return -1;
      } else if (block == Nk.WATERLILY) {
         return getLilypadColorMultiplier(blockAccess, blockPos);
      } else if (block == Nk.REDSTONE_WIRE) {
         return getRedstoneColor(renderEnv.getBlockState());
      } else if (block instanceof gX) {
         return getStemColorMultiplier(block, blockAccess, blockPos, renderEnv);
      } else if (useDefaultGrassFoliageColors) {
         return -1;
      } else {
         int i = renderEnv.getMetadata();
         bjx customcolors$icolorizer;
         if (block != Nk.GRASS && block != Nk.TALLGRASS && block != Nk.DOUBLE_PLANT) {
            if (block == Nk.DOUBLE_PLANT) {
               customcolors$icolorizer = COLORIZER_GRASS;
               if (i >= 8) {
                  blockPos = blockPos.down();
               }
            } else if (block == Nk.LEAVES) {
               switch (i & 3) {
                  case 0:
                     customcolors$icolorizer = COLORIZER_FOLIAGE;
                     break;
                  case 1:
                     customcolors$icolorizer = COLORIZER_FOLIAGE_PINE;
                     break;
                  case 2:
                     customcolors$icolorizer = COLORIZER_FOLIAGE_BIRCH;
                     break;
                  default:
                     customcolors$icolorizer = COLORIZER_FOLIAGE;
               }
            } else if (block == Nk.LEAVES2) {
               customcolors$icolorizer = COLORIZER_FOLIAGE;
            } else {
               if (block != Nk.VINE) {
                  return -1;
               }

               customcolors$icolorizer = COLORIZER_FOLIAGE;
            }
         } else {
            customcolors$icolorizer = COLORIZER_GRASS;
         }

         return XH.isSmoothBiomes() && !customcolors$icolorizer.isColorConstant() ? getSmoothColorMultiplier(blockState, blockAccess, blockPos, customcolors$icolorizer, renderEnv.getColorizerBlockPosM()) : customcolors$icolorizer.getColor(iblockstate, blockAccess, blockPos);
      }
   }

   protected static Zi getColorBiome(bfZ blockAccess, BlockPos blockPos) {
      Zi biome = blockAccess.getBiome(blockPos);
      if (biome == Nj.SWAMPLAND && !XH.isSwampColors()) {
         biome = Nj.PLAINS;
      }

      return biome;
   }

   private static bjr getBlockColormap(in blockState) {
      if (blockColormaps == null) {
         return null;
      } else if (!(blockState instanceof ie)) {
         return null;
      } else {
         ie blockstatebase = (ie)blockState;
         int i = blockstatebase.getBlockId();
         if (i >= 0 && i < blockColormaps.length) {
            bjr[] acustomcolormap = blockColormaps[i];
            if (acustomcolormap == null) {
               return null;
            } else {
               for(int j = 0; j < acustomcolormap.length; ++j) {
                  bjr customcolormap = acustomcolormap[j];
                  if (customcolormap.matchesBlock(blockstatebase)) {
                     return customcolormap;
                  }
               }

               return null;
            }
         } else {
            return null;
         }
      }
   }

   private static int getSmoothColorMultiplier(in blockState, bfZ blockAccess, BlockPos blockPos, bjx colorizer, biN blockPosM) {
      int i = 0;
      int j = 0;
      int k = 0;
      int l = blockPos.getX();
      int i1 = blockPos.getY();
      int j1 = blockPos.getZ();
      biN blockposm = blockPosM;

      int k1;
      int l1;
      int i2;
      for(k1 = l - 1; k1 <= l + 1; ++k1) {
         for(l1 = j1 - 1; l1 <= j1 + 1; ++l1) {
            blockposm.setXyz(k1, i1, l1);
            i2 = colorizer.getColor(blockState, blockAccess, blockposm);
            i += i2 >> 16 & 255;
            j += i2 >> 8 & 255;
            k += i2 & 255;
         }
      }

      k1 = i / 9;
      l1 = j / 9;
      i2 = k / 9;
      return k1 << 16 | l1 << 8 | i2;
   }

   public static int getFluidColor(bfZ blockAccess, in blockState, BlockPos blockPos, boa renderEnv) {
      co block = blockState.getBlock();
      bjx customcolors$icolorizer = getBlockColormap(blockState);
      if (customcolors$icolorizer == null && blockState.getMaterial() == hM.WATER) {
         customcolors$icolorizer = COLORIZER_WATER;
      }

      if (customcolors$icolorizer == null) {
         return getBlockColors().colorMultiplier(blockState, blockAccess, blockPos, 0);
      } else {
         return XH.isSmoothBiomes() && !((bjx)customcolors$icolorizer).isColorConstant() ? getSmoothColorMultiplier(blockState, blockAccess, blockPos, (bjx)customcolors$icolorizer, renderEnv.getColorizerBlockPosM()) : ((bjx)customcolors$icolorizer).getColor(blockState, blockAccess, blockPos);
      }
   }

   public static uy getBlockColors() {
      return nC.getMinecraft().getBlockColors();
   }

   public static void updatePortalFX(pM fx) {
      if (particlePortalColor >= 0) {
         int i = particlePortalColor;
         int j = i >> 16 & 255;
         int k = i >> 8 & 255;
         int l = i & 255;
         float f = (float)j / 255.0F;
         float f1 = (float)k / 255.0F;
         float f2 = (float)l / 255.0F;
         fx.setRBGColorF(f, f1, f2);
      }

   }

   public static void updateMyceliumFX(pM fx) {
      if (myceliumParticleColors != null) {
         int i = myceliumParticleColors.getColorRandom();
         int j = i >> 16 & 255;
         int k = i >> 8 & 255;
         int l = i & 255;
         float f = (float)j / 255.0F;
         float f1 = (float)k / 255.0F;
         float f2 = (float)l / 255.0F;
         fx.setRBGColorF(f, f1, f2);
      }

   }

   private static int getRedstoneColor(in blockState) {
      if (redstoneColors == null) {
         return -1;
      } else {
         int i = getRedstoneLevel(blockState, 15);
         int j = redstoneColors.getColor(i);
         return j;
      }
   }

   public static void updateReddustFX(pM fx, bfZ blockAccess, double x, double y, double z) {
      if (redstoneColors != null) {
         in iblockstate = blockAccess.getBlockState(new BlockPos(x, y, z));
         int i = getRedstoneLevel(iblockstate, 15);
         int j = redstoneColors.getColor(i);
         int k = j >> 16 & 255;
         int l = j >> 8 & 255;
         int i1 = j & 255;
         float f = (float)k / 255.0F;
         float f1 = (float)l / 255.0F;
         float f2 = (float)i1 / 255.0F;
         fx.setRBGColorF(f, f1, f2);
      }

   }

   private static int getRedstoneLevel(in state, int def) {
      co block = state.getBlock();
      if (!(block instanceof gf)) {
         return def;
      } else {
         Object object = state.getValue(gf.POWER);
         if (!(object instanceof Integer)) {
            return def;
         } else {
            Integer integer = (Integer)object;
            return integer;
         }
      }
   }

   public static float getXpOrbTimer(float timer) {
      if (xpOrbTime <= 0) {
         return timer;
      } else {
         float f = 628.0F / (float)xpOrbTime;
         return timer * f;
      }
   }

   public static int getXpOrbColor(float timer) {
      if (xpOrbColors == null) {
         return -1;
      } else {
         int i = (int)Math.round((double)((MathHelper.sin(timer) + 1.0F) * (float)(xpOrbColors.getLength() - 1)) / 2.0);
         int j = xpOrbColors.getColor(i);
         return j;
      }
   }

   public static int getDurabilityColor(float dur, int color) {
      if (durabilityColors == null) {
         return color;
      } else {
         int i = (int)(dur * (float)durabilityColors.getLength());
         int j = durabilityColors.getColor(i);
         return j;
      }
   }

   public static void updateWaterFX(pM fx, bfZ blockAccess, double x, double y, double z, boa renderEnv) {
      if (waterColors != null || blockColormaps != null || particleWaterColor >= 0) {
         BlockPos blockpos = new BlockPos(x, y, z);
         renderEnv.reset(BLOCK_STATE_WATER, blockpos);
         int i = getFluidColor(blockAccess, BLOCK_STATE_WATER, blockpos, renderEnv);
         int j = i >> 16 & 255;
         int k = i >> 8 & 255;
         int l = i & 255;
         float f = (float)j / 255.0F;
         float f1 = (float)k / 255.0F;
         float f2 = (float)l / 255.0F;
         if (particleWaterColor >= 0) {
            int i1 = particleWaterColor >> 16 & 255;
            int j1 = particleWaterColor >> 8 & 255;
            int k1 = particleWaterColor & 255;
            f *= (float)i1 / 255.0F;
            f1 *= (float)j1 / 255.0F;
            f2 *= (float)k1 / 255.0F;
         }

         fx.setRBGColorF(f, f1, f2);
      }

   }

   private static int getLilypadColorMultiplier(bfZ blockAccess, BlockPos blockPos) {
      return lilyPadColor < 0 ? getBlockColors().colorMultiplier(Nk.WATERLILY.getDefaultState(), blockAccess, blockPos, 0) : lilyPadColor;
   }

   private static Vec3d getFogColorNether(Vec3d col) {
      return fogColorNether == null ? col : fogColorNether;
   }

   private static Vec3d getFogColorEnd(Vec3d col) {
      return fogColorEnd == null ? col : fogColorEnd;
   }

   private static Vec3d getSkyColorEnd(Vec3d col) {
      return skyColorEnd == null ? col : skyColorEnd;
   }

   public static Vec3d getSkyColor(Vec3d skyColor3d, bfZ blockAccess, double x, double y, double z) {
      if (skyColors == null) {
         return skyColor3d;
      } else {
         int i = skyColors.getColorSmooth(blockAccess, x, y, z, 3);
         int j = i >> 16 & 255;
         int k = i >> 8 & 255;
         int l = i & 255;
         float f = (float)j / 255.0F;
         float f1 = (float)k / 255.0F;
         float f2 = (float)l / 255.0F;
         float f3 = (float)skyColor3d.x / 0.5F;
         float f4 = (float)skyColor3d.y / 0.66275F;
         float f5 = (float)skyColor3d.z;
         f *= f3;
         f1 *= f4;
         f2 *= f5;
         Vec3d vec3d = skyColorFader.getColor((double)f, (double)f1, (double)f2);
         return vec3d;
      }
   }

   private static Vec3d getFogColor(Vec3d fogColor3d, bfZ blockAccess, double x, double y, double z) {
      if (fogColors == null) {
         return fogColor3d;
      } else {
         int i = fogColors.getColorSmooth(blockAccess, x, y, z, 3);
         int j = i >> 16 & 255;
         int k = i >> 8 & 255;
         int l = i & 255;
         float f = (float)j / 255.0F;
         float f1 = (float)k / 255.0F;
         float f2 = (float)l / 255.0F;
         float f3 = (float)fogColor3d.x / 0.753F;
         float f4 = (float)fogColor3d.y / 0.8471F;
         float f5 = (float)fogColor3d.z;
         f *= f3;
         f1 *= f4;
         f2 *= f5;
         Vec3d vec3d = fogColorFader.getColor((double)f, (double)f1, (double)f2);
         return vec3d;
      }
   }

   public static Vec3d getUnderwaterColor(bfZ blockAccess, double x, double y, double z) {
      return getUnderFluidColor(blockAccess, x, y, z, underwaterColors, underwaterColorFader);
   }

   public static Vec3d getUnderlavaColor(bfZ blockAccess, double x, double y, double z) {
      return getUnderFluidColor(blockAccess, x, y, z, underlavaColors, underlavaColorFader);
   }

   public static Vec3d getUnderFluidColor(bfZ blockAccess, double x, double y, double z, bjr underFluidColors, bjq underFluidColorFader) {
      if (underFluidColors == null) {
         return null;
      } else {
         int i = underFluidColors.getColorSmooth(blockAccess, x, y, z, 3);
         int j = i >> 16 & 255;
         int k = i >> 8 & 255;
         int l = i & 255;
         float f = (float)j / 255.0F;
         float f1 = (float)k / 255.0F;
         float f2 = (float)l / 255.0F;
         Vec3d vec3d = underFluidColorFader.getColor((double)f, (double)f1, (double)f2);
         return vec3d;
      }
   }

   private static int getStemColorMultiplier(co blockStem, bfZ blockAccess, BlockPos blockPos, boa renderEnv) {
      bjr customcolormap = stemColors;
      if (blockStem == Nk.PUMPKIN_STEM && stemPumpkinColors != null) {
         customcolormap = stemPumpkinColors;
      }

      if (blockStem == Nk.MELON_STEM && stemMelonColors != null) {
         customcolormap = stemMelonColors;
      }

      if (customcolormap == null) {
         return -1;
      } else {
         int i = renderEnv.getMetadata();
         return customcolormap.getColor(i);
      }
   }

   public static boolean updateLightmap(bij world, float torchFlickerX, int[] lmColors, boolean nightvision, float partialTicks) {
      if (world == null) {
         return false;
      } else if (lightMapPacks == null) {
         return false;
      } else {
         int i = world.provider.getDimensionType().getId();
         int j = i - lightmapMinDimensionId;
         if (j >= 0 && j < lightMapPacks.length) {
            bmY lightmappack = lightMapPacks[j];
            return lightmappack == null ? false : lightmappack.updateLightmap(world, torchFlickerX, lmColors, nightvision, partialTicks);
         } else {
            return false;
         }
      }
   }

   public static Vec3d getWorldFogColor(Vec3d fogVec, bij world, Ig renderViewEntity, float partialTicks) {
      baM dimensiontype = world.provider.getDimensionType();
      nC minecraft = nC.getMinecraft();
      if (dimensiontype == baM.NETHER) {
         return getFogColorNether(fogVec);
      } else if (dimensiontype == baM.OVERWORLD) {
         return getFogColor(fogVec, minecraft.world, renderViewEntity.posX, renderViewEntity.posY + 1.0, renderViewEntity.posZ);
      } else {
         return dimensiontype == baM.THE_END ? getFogColorEnd(fogVec) : fogVec;
      }
   }

   public static Vec3d getWorldSkyColor(Vec3d skyVec, bij world, Ig renderViewEntity, float partialTicks) {
      baM dimensiontype = world.provider.getDimensionType();
      nC minecraft = nC.getMinecraft();
      if (dimensiontype == baM.OVERWORLD) {
         return getSkyColor(skyVec, minecraft.world, renderViewEntity.posX, renderViewEntity.posY + 1.0, renderViewEntity.posZ);
      } else {
         return dimensiontype == baM.THE_END ? getSkyColorEnd(skyVec) : skyVec;
      }
   }

   private static int[] readSpawnEggColors(Properties props, String fileName, String prefix, String logName) {
      List<Integer> list = new ArrayList();
      Set set = props.keySet();
      int i = 0;
      Iterator var7 = set.iterator();

      while(true) {
         while(true) {
            String s;
            String s1;
            do {
               if (!var7.hasNext()) {
                  if (i <= 0) {
                     return null;
                  }

                  dbg(logName + " colors: " + i);
                  int[] aint = new int[list.size()];

                  for(int l = 0; l < aint.length; ++l) {
                     aint[l] = (Integer)list.get(l);
                  }

                  return aint;
               }

               Object ss = var7.next();
               s = (String)ss;
               s1 = props.getProperty(s);
            } while(!s.startsWith(prefix));

            String s2 = bqP.removePrefix(s, prefix);
            int j = bqp.getEntityIdByName(s2);
            if (j < 0) {
               j = bqp.getEntityIdByLocation((new ResourceLocation(s2)).toString());
            }

            if (j < 0) {
               warn("Invalid spawn egg name: " + s);
            } else {
               int k = parseColor(s1);
               if (k < 0) {
                  warn("Invalid spawn egg color: " + s + " = " + s1);
               } else {
                  while(list.size() <= j) {
                     list.add(-1);
                  }

                  list.set(j, k);
                  ++i;
               }
            }
         }
      }
   }

   private static int getSpawnEggColor(PX item, Qy itemStack, int layer, int color) {
      if (spawnEggPrimaryColors == null && spawnEggSecondaryColors == null) {
         return color;
      } else {
         QQ nbttagcompound = itemStack.getTagCompound();
         if (nbttagcompound == null) {
            return color;
         } else {
            QQ nbttagcompound1 = nbttagcompound.getCompoundTag("EntityTag");
            if (nbttagcompound1 == null) {
               return color;
            } else {
               String s = nbttagcompound1.getString("id");
               int i = bqp.getEntityIdByLocation(s);
               int[] aint = layer == 0 ? spawnEggPrimaryColors : spawnEggSecondaryColors;
               if (aint == null) {
                  return color;
               } else if (i >= 0 && i < aint.length) {
                  int j = aint[i];
                  return j < 0 ? color : j;
               } else {
                  return color;
               }
            }
         }
      }
   }

   public static int getColorFromItemStack(Qy itemStack, int layer, int color) {
      if (itemStack == null) {
         return color;
      } else {
         OL item = itemStack.getItem();
         if (item == null) {
            return color;
         } else {
            return item instanceof PX ? getSpawnEggColor((PX)item, itemStack, layer, color) : color;
         }
      }
   }

   private static float[][] readDyeColors(Properties props, String fileName, String prefix, String logName) {
      Om[] aenumdyecolor = Om.values();
      Map<String, Om> map = new HashMap();

      for(int i = 0; i < aenumdyecolor.length; ++i) {
         Om enumdyecolor = aenumdyecolor[i];
         map.put(enumdyecolor.getName(), enumdyecolor);
      }

      float[][] afloat1 = new float[aenumdyecolor.length][];
      int k = 0;
      Iterator var8 = props.keySet().iterator();

      while(true) {
         while(true) {
            String s;
            String s1;
            do {
               if (!var8.hasNext()) {
                  if (k <= 0) {
                     return (float[][])((float[][])null);
                  }

                  dbg(logName + " colors: " + k);
                  return afloat1;
               }

               Object ss = var8.next();
               s = (String)ss;
               s1 = props.getProperty(s);
            } while(!s.startsWith(prefix));

            String s2 = bqP.removePrefix(s, prefix);
            if (s2.equals("lightBlue")) {
               s2 = "light_blue";
            }

            Om enumdyecolor1 = (Om)map.get(s2);
            int j = parseColor(s1);
            if (enumdyecolor1 != null && j >= 0) {
               float[] afloat = new float[]{(float)(j >> 16 & 255) / 255.0F, (float)(j >> 8 & 255) / 255.0F, (float)(j & 255) / 255.0F};
               afloat1[enumdyecolor1.ordinal()] = afloat;
               ++k;
            } else {
               warn("Invalid color: " + s + " = " + s1);
            }
         }
      }
   }

   private static float[] getDyeColors(Om dye, float[][] dyeColors, float[] colors) {
      if (dyeColors == null) {
         return colors;
      } else if (dye == null) {
         return colors;
      } else {
         float[] afloat = dyeColors[dye.ordinal()];
         return afloat == null ? colors : afloat;
      }
   }

   public static float[] getWolfCollarColors(Om dye, float[] colors) {
      return getDyeColors(dye, wolfCollarColors, colors);
   }

   public static float[] getSheepColors(Om dye, float[] colors) {
      return getDyeColors(dye, sheepColors, colors);
   }

   private static int[] readTextColors(Properties props, String fileName, String prefix, String logName) {
      int[] aint = new int[32];
      Arrays.fill(aint, -1);
      int i = 0;
      Iterator var6 = props.keySet().iterator();

      while(true) {
         while(true) {
            String s;
            String s1;
            do {
               if (!var6.hasNext()) {
                  if (i <= 0) {
                     return null;
                  }

                  dbg(logName + " colors: " + i);
                  return aint;
               }

               Object ss = var6.next();
               s = (String)ss;
               s1 = props.getProperty(s);
            } while(!s.startsWith(prefix));

            String s2 = bqP.removePrefix(s, prefix);
            int j = XH.parseInt(s2, -1);
            int k = parseColor(s1);
            if (j >= 0 && j < aint.length && k >= 0) {
               aint[j] = k;
               ++i;
            } else {
               warn("Invalid color: " + s + " = " + s1);
            }
         }
      }
   }

   public static int getTextColor(int index, int color) {
      if (textColors == null) {
         return color;
      } else if (index >= 0 && index < textColors.length) {
         int i = textColors[index];
         return i < 0 ? color : i;
      } else {
         return color;
      }
   }

   private static int[] readMapColors(Properties props, String fileName, String prefix, String logName) {
      int[] aint = new int[hK.COLORS.length];
      Arrays.fill(aint, -1);
      int i = 0;
      Iterator var6 = props.keySet().iterator();

      while(true) {
         while(true) {
            String s;
            String s1;
            do {
               if (!var6.hasNext()) {
                  if (i <= 0) {
                     return null;
                  }

                  dbg(logName + " colors: " + i);
                  return aint;
               }

               Object ss = var6.next();
               s = (String)ss;
               s1 = props.getProperty(s);
            } while(!s.startsWith(prefix));

            String s2 = bqP.removePrefix(s, prefix);
            int j = getMapColorIndex(s2);
            int k = parseColor(s1);
            if (j >= 0 && j < aint.length && k >= 0) {
               aint[j] = k;
               ++i;
            } else {
               warn("Invalid color: " + s + " = " + s1);
            }
         }
      }
   }

   private static int[] readPotionColors(Properties props, String fileName, String prefix, String logName) {
      int[] aint = new int[getMaxPotionId()];
      Arrays.fill(aint, -1);
      int i = 0;
      Iterator var6 = props.keySet().iterator();

      while(true) {
         while(true) {
            String s;
            String s1;
            do {
               if (!var6.hasNext()) {
                  if (i <= 0) {
                     return null;
                  }

                  dbg(logName + " colors: " + i);
                  return aint;
               }

               Object ss = var6.next();
               s = (String)ss;
               s1 = props.getProperty(s);
            } while(!s.startsWith(prefix));

            int j = getPotionId(s);
            int k = parseColor(s1);
            if (j >= 0 && j < aint.length && k >= 0) {
               aint[j] = k;
               ++i;
            } else {
               warn("Invalid color: " + s + " = " + s1);
            }
         }
      }
   }

   private static int getMaxPotionId() {
      int i = 0;
      Iterator var1 = VW.REGISTRY.getKeys().iterator();

      while(var1.hasNext()) {
         ResourceLocation resourcelocation = (ResourceLocation)var1.next();
         VW potion = (VW)VW.REGISTRY.getObject(resourcelocation);
         int j = VW.getIdFromPotion(potion);
         if (j > i) {
            i = j;
         }
      }

      return i;
   }

   public static int getPotionId(String name) {
      if (name.equals("potion.water")) {
         return 0;
      } else {
         name = bqP.replacePrefix(name, "potion.", "effect.");
         Iterator var1 = VW.REGISTRY.getKeys().iterator();

         VW potion;
         do {
            if (!var1.hasNext()) {
               return -1;
            }

            ResourceLocation resourcelocation = (ResourceLocation)var1.next();
            potion = (VW)VW.REGISTRY.getObject(resourcelocation);
         } while(!potion.getName().equals(name));

         return VW.getIdFromPotion(potion);
      }
   }

   public static int getPotionColor(VW potion, int color) {
      int i = 0;
      if (potion != null) {
         i = VW.getIdFromPotion(potion);
      }

      return getPotionColor(i, color);
   }

   public static int getPotionColor(int potionId, int color) {
      if (potionColors == null) {
         return color;
      } else if (potionId >= 0 && potionId < potionColors.length) {
         int i = potionColors[potionId];
         return i < 0 ? color : i;
      } else {
         return color;
      }
   }

   private static int getMapColorIndex(String name) {
      if (name == null) {
         return -1;
      } else if (name.equals("air")) {
         return hK.AIR.colorIndex;
      } else if (name.equals("grass")) {
         return hK.GRASS.colorIndex;
      } else if (name.equals("sand")) {
         return hK.SAND.colorIndex;
      } else if (name.equals("cloth")) {
         return hK.CLOTH.colorIndex;
      } else if (name.equals("tnt")) {
         return hK.TNT.colorIndex;
      } else if (name.equals("ice")) {
         return hK.ICE.colorIndex;
      } else if (name.equals("iron")) {
         return hK.IRON.colorIndex;
      } else if (name.equals("foliage")) {
         return hK.FOLIAGE.colorIndex;
      } else if (name.equals("clay")) {
         return hK.CLAY.colorIndex;
      } else if (name.equals("dirt")) {
         return hK.DIRT.colorIndex;
      } else if (name.equals("stone")) {
         return hK.STONE.colorIndex;
      } else if (name.equals("water")) {
         return hK.WATER.colorIndex;
      } else if (name.equals("wood")) {
         return hK.WOOD.colorIndex;
      } else if (name.equals("quartz")) {
         return hK.QUARTZ.colorIndex;
      } else if (name.equals("gold")) {
         return hK.GOLD.colorIndex;
      } else if (name.equals("diamond")) {
         return hK.DIAMOND.colorIndex;
      } else if (name.equals("lapis")) {
         return hK.LAPIS.colorIndex;
      } else if (name.equals("emerald")) {
         return hK.EMERALD.colorIndex;
      } else if (name.equals("podzol")) {
         return hK.OBSIDIAN.colorIndex;
      } else if (name.equals("netherrack")) {
         return hK.NETHERRACK.colorIndex;
      } else if (!name.equals("snow") && !name.equals("white")) {
         if (!name.equals("adobe") && !name.equals("orange")) {
            if (name.equals("magenta")) {
               return hK.MAGENTA.colorIndex;
            } else if (!name.equals("light_blue") && !name.equals("lightBlue")) {
               if (name.equals("yellow")) {
                  return hK.YELLOW.colorIndex;
               } else if (name.equals("lime")) {
                  return hK.LIME.colorIndex;
               } else if (name.equals("pink")) {
                  return hK.PINK.colorIndex;
               } else if (name.equals("gray")) {
                  return hK.GRAY.colorIndex;
               } else if (name.equals("silver")) {
                  return hK.SILVER.colorIndex;
               } else if (name.equals("cyan")) {
                  return hK.CYAN.colorIndex;
               } else if (name.equals("purple")) {
                  return hK.PURPLE.colorIndex;
               } else if (name.equals("blue")) {
                  return hK.BLUE.colorIndex;
               } else if (name.equals("brown")) {
                  return hK.BROWN.colorIndex;
               } else if (name.equals("green")) {
                  return hK.GREEN.colorIndex;
               } else if (name.equals("red")) {
                  return hK.RED.colorIndex;
               } else {
                  return name.equals("black") ? hK.BLACK.colorIndex : -1;
               }
            } else {
               return hK.LIGHT_BLUE.colorIndex;
            }
         } else {
            return hK.ADOBE.colorIndex;
         }
      } else {
         return hK.SNOW.colorIndex;
      }
   }

   private static int[] getMapColors() {
      hK[] amapcolor = hK.COLORS;
      int[] aint = new int[amapcolor.length];
      Arrays.fill(aint, -1);

      for(int i = 0; i < amapcolor.length && i < aint.length; ++i) {
         hK mapcolor = amapcolor[i];
         if (mapcolor != null) {
            aint[i] = mapcolor.colorValue;
         }
      }

      return aint;
   }

   private static void setMapColors(int[] colors) {
      if (colors != null) {
         hK[] amapcolor = hK.COLORS;
         boolean flag = false;

         for(int i = 0; i < amapcolor.length && i < colors.length; ++i) {
            hK mapcolor = amapcolor[i];
            if (mapcolor != null) {
               int j = colors[i];
               if (j >= 0 && mapcolor.colorValue != j) {
                  mapcolor.colorValue = j;
                  flag = true;
               }
            }
         }

         if (flag) {
            nC.getMinecraft().getTextureManager().reloadBannerTextures();
         }
      }

   }

   private static void dbg(String str) {
      XH.dbg("CustomColors: " + str);
   }

   private static void warn(String str) {
      XH.warn("CustomColors: " + str);
   }

   public static int getExpBarTextColor(int color) {
      return expBarTextColor < 0 ? color : expBarTextColor;
   }

   public static int getBossTextColor(int color) {
      return bossTextColor < 0 ? color : bossTextColor;
   }

   public static int getSignTextColor(int color) {
      return signTextColor < 0 ? color : signTextColor;
   }

   static {
      BLOCK_STATE_DIRT = Nk.DIRT.getDefaultState();
      BLOCK_STATE_WATER = Nk.WATER.getDefaultState();
      random = new Random();
      COLORIZER_GRASS = new bjx() {
         public int getColor(in blockState, bfZ blockAccess, BlockPos blockPos) {
            Zi biome = bjy.getColorBiome(blockAccess, blockPos);
            return bjy.swampGrassColors != null && biome == Nj.SWAMPLAND ? bjy.swampGrassColors.getColor(biome, blockPos) : biome.getGrassColorAtPos(blockPos);
         }

         public boolean isColorConstant() {
            return false;
         }
      };
      COLORIZER_FOLIAGE = new bjx() {
         public int getColor(in blockState, bfZ blockAccess, BlockPos blockPos) {
            Zi biome = bjy.getColorBiome(blockAccess, blockPos);
            return bjy.swampFoliageColors != null && biome == Nj.SWAMPLAND ? bjy.swampFoliageColors.getColor(biome, blockPos) : biome.getFoliageColorAtPos(blockPos);
         }

         public boolean isColorConstant() {
            return false;
         }
      };
      COLORIZER_FOLIAGE_PINE = new bjx() {
         public int getColor(in blockState, bfZ blockAccess, BlockPos blockPos) {
            return bjy.foliagePineColors != null ? bjy.foliagePineColors.getColor(blockAccess, blockPos) : baJ.getFoliageColorPine();
         }

         public boolean isColorConstant() {
            return bjy.foliagePineColors == null;
         }
      };
      COLORIZER_FOLIAGE_BIRCH = new bjx() {
         public int getColor(in blockState, bfZ blockAccess, BlockPos blockPos) {
            return bjy.foliageBirchColors != null ? bjy.foliageBirchColors.getColor(blockAccess, blockPos) : baJ.getFoliageColorBirch();
         }

         public boolean isColorConstant() {
            return bjy.foliageBirchColors == null;
         }
      };
      COLORIZER_WATER = new bjx() {
         public int getColor(in blockState, bfZ blockAccess, BlockPos blockPos) {
            Zi biome = bjy.getColorBiome(blockAccess, blockPos);
            if (bjy.waterColors != null) {
               return bjy.waterColors.getColor(biome, blockPos);
            } else {
               return bnK.ForgeBiome_getWaterColorMultiplier.exists() ? bnK.callInt(biome, bnK.ForgeBiome_getWaterColorMultiplier) : biome.getWaterColor();
            }
         }

         public boolean isColorConstant() {
            return false;
         }
      };
   }
}
