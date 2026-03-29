package neo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class biI {
   private static boolean betterGrass = true;
   private static boolean betterGrassPath = true;
   private static boolean betterMycelium = true;
   private static boolean betterPodzol = true;
   private static boolean betterGrassSnow = true;
   private static boolean betterMyceliumSnow = true;
   private static boolean betterPodzolSnow = true;
   private static boolean grassMultilayer = false;
   private static zd spriteGrass = null;
   private static zd spriteGrassSide = null;
   private static zd spriteGrassPath = null;
   private static zd spriteGrassPathSide = null;
   private static zd spriteMycelium = null;
   private static zd spritePodzol = null;
   private static zd spriteSnow = null;
   private static boolean spritesLoaded = false;
   private static sc modelCubeGrass = null;
   private static sc modelGrassPath = null;
   private static sc modelCubeGrassPath = null;
   private static sc modelCubeMycelium = null;
   private static sc modelCubePodzol = null;
   private static sc modelCubeSnow = null;
   private static boolean modelsLoaded = false;
   private static final String TEXTURE_GRASS_DEFAULT = "blocks/grass_top";
   private static final String TEXTURE_GRASS_SIDE_DEFAULT = "blocks/grass_side";
   private static final String TEXTURE_GRASS_PATH_DEFAULT = "blocks/grass_path_top";
   private static final String TEXTURE_GRASS_PATH_SIDE_DEFAULT = "blocks/grass_path_side";
   private static final String TEXTURE_MYCELIUM_DEFAULT = "blocks/mycelium_top";
   private static final String TEXTURE_PODZOL_DEFAULT = "blocks/dirt_podzol_top";
   private static final String TEXTURE_SNOW_DEFAULT = "blocks/snow";

   public biI() {
   }

   public static void updateIcons(zj textureMap) {
      spritesLoaded = false;
      modelsLoaded = false;
      loadProperties(textureMap);
   }

   public static void update() {
      if (spritesLoaded) {
         modelCubeGrass = bnc.makeModelCube((zd)spriteGrass, 0);
         if (grassMultilayer) {
            sc ibakedmodel = bnc.makeModelCube((zd)spriteGrassSide, -1);
            modelCubeGrass = bnc.joinModelsCube(ibakedmodel, modelCubeGrass);
         }

         modelGrassPath = bnc.makeModel("grass_path", spriteGrassPathSide, spriteGrassPath);
         modelCubeGrassPath = bnc.makeModelCube((zd)spriteGrassPath, -1);
         modelCubeMycelium = bnc.makeModelCube((zd)spriteMycelium, -1);
         modelCubePodzol = bnc.makeModelCube((zd)spritePodzol, 0);
         modelCubeSnow = bnc.makeModelCube((zd)spriteSnow, -1);
         modelsLoaded = true;
      }

   }

   private static void loadProperties(zj textureMap) {
      betterGrass = true;
      betterGrassPath = true;
      betterMycelium = true;
      betterPodzol = true;
      betterGrassSnow = true;
      betterMyceliumSnow = true;
      betterPodzolSnow = true;
      spriteGrass = textureMap.registerSprite(new ResourceLocation("blocks/grass_top"));
      spriteGrassSide = textureMap.registerSprite(new ResourceLocation("blocks/grass_side"));
      spriteGrassPath = textureMap.registerSprite(new ResourceLocation("blocks/grass_path_top"));
      spriteGrassPathSide = textureMap.registerSprite(new ResourceLocation("blocks/grass_path_side"));
      spriteMycelium = textureMap.registerSprite(new ResourceLocation("blocks/mycelium_top"));
      spritePodzol = textureMap.registerSprite(new ResourceLocation("blocks/dirt_podzol_top"));
      spriteSnow = textureMap.registerSprite(new ResourceLocation("blocks/snow"));
      spritesLoaded = true;
      String s = "optifine/bettergrass.properties";

      try {
         ResourceLocation resourcelocation = new ResourceLocation(s);
         if (!XH.hasResource(resourcelocation)) {
            return;
         }

         InputStream inputstream = XH.getResourceStream(resourcelocation);
         if (inputstream == null) {
            return;
         }

         boolean flag = XH.isFromDefaultResourcePack(resourcelocation);
         if (flag) {
            XH.dbg("BetterGrass: Parsing default configuration " + s);
         } else {
            XH.dbg("BetterGrass: Parsing configuration " + s);
         }

         Properties properties = new bqL();
         ((Properties)properties).load(inputstream);
         inputstream.close();
         betterGrass = getBoolean(properties, "grass", true);
         betterGrassPath = getBoolean(properties, "grass_path", true);
         betterMycelium = getBoolean(properties, "mycelium", true);
         betterPodzol = getBoolean(properties, "podzol", true);
         betterGrassSnow = getBoolean(properties, "grass.snow", true);
         betterMyceliumSnow = getBoolean(properties, "mycelium.snow", true);
         betterPodzolSnow = getBoolean(properties, "podzol.snow", true);
         grassMultilayer = getBoolean(properties, "grass.multilayer", false);
         spriteGrass = registerSprite(properties, "texture.grass", "blocks/grass_top", textureMap);
         spriteGrassSide = registerSprite(properties, "texture.grass_side", "blocks/grass_side", textureMap);
         spriteGrassPath = registerSprite(properties, "texture.grass_path", "blocks/grass_path_top", textureMap);
         spriteGrassPathSide = registerSprite(properties, "texture.grass_path_side", "blocks/grass_path_side", textureMap);
         spriteMycelium = registerSprite(properties, "texture.mycelium", "blocks/mycelium_top", textureMap);
         spritePodzol = registerSprite(properties, "texture.podzol", "blocks/dirt_podzol_top", textureMap);
         spriteSnow = registerSprite(properties, "texture.snow", "blocks/snow", textureMap);
      } catch (IOException var6) {
         IOException ioexception = var6;
         XH.warn("Error reading: " + s + ", " + ioexception.getClass().getName() + ": " + ioexception.getMessage());
      }

   }

   private static zd registerSprite(Properties props, String key, String textureDefault, zj textureMap) {
      String s = props.getProperty(key);
      if (s == null) {
         s = textureDefault;
      }

      ResourceLocation resourcelocation = new ResourceLocation("textures/" + s + ".png");
      if (!XH.hasResource(resourcelocation)) {
         XH.warn("BetterGrass texture not found: " + resourcelocation);
         s = textureDefault;
      }

      ResourceLocation resourcelocation1 = new ResourceLocation(s);
      zd textureatlassprite = textureMap.registerSprite(resourcelocation1);
      return textureatlassprite;
   }

   public static List getFaceQuads(bfZ blockAccess, in blockState, BlockPos blockPos, EnumFacing facing, List quads) {
      if (facing != EnumFacing.UP && facing != EnumFacing.DOWN) {
         if (!modelsLoaded) {
            return quads;
         } else {
            co block = blockState.getBlock();
            if (block instanceof eJ) {
               return getFaceQuadsMycelium(blockAccess, blockState, blockPos, facing, quads);
            } else if (block instanceof ee) {
               return getFaceQuadsGrassPath(blockAccess, blockState, blockPos, facing, quads);
            } else if (block instanceof dj) {
               return getFaceQuadsDirt(blockAccess, blockState, blockPos, facing, quads);
            } else {
               return block instanceof ec ? getFaceQuadsGrass(blockAccess, blockState, blockPos, facing, quads) : quads;
            }
         }
      } else {
         return quads;
      }
   }

   private static List getFaceQuadsMycelium(bfZ blockAccess, in blockState, BlockPos blockPos, EnumFacing facing, List quads) {
      co block = blockAccess.getBlockState(blockPos.up()).getBlock();
      boolean flag = block == Nk.SNOW || block == Nk.SNOW_LAYER;
      if (XH.isBetterGrassFancy()) {
         if (flag) {
            if (betterMyceliumSnow && getBlockAt(blockPos, facing, blockAccess) == Nk.SNOW_LAYER) {
               return modelCubeSnow.getQuads(blockState, facing, 0L);
            }
         } else if (betterMycelium && getBlockAt(blockPos.down(), facing, blockAccess) == Nk.MYCELIUM) {
            return modelCubeMycelium.getQuads(blockState, facing, 0L);
         }
      } else if (flag) {
         if (betterMyceliumSnow) {
            return modelCubeSnow.getQuads(blockState, facing, 0L);
         }
      } else if (betterMycelium) {
         return modelCubeMycelium.getQuads(blockState, facing, 0L);
      }

      return quads;
   }

   private static List getFaceQuadsGrassPath(bfZ blockAccess, in blockState, BlockPos blockPos, EnumFacing facing, List quads) {
      if (!betterGrassPath) {
         return quads;
      } else if (XH.isBetterGrassFancy()) {
         return getBlockAt(blockPos.down(), facing, blockAccess) == Nk.GRASS_PATH ? modelGrassPath.getQuads(blockState, facing, 0L) : quads;
      } else {
         return modelGrassPath.getQuads(blockState, facing, 0L);
      }
   }

   private static List getFaceQuadsDirt(bfZ blockAccess, in blockState, BlockPos blockPos, EnumFacing facing, List quads) {
      co block = getBlockAt(blockPos, EnumFacing.UP, blockAccess);
      if (blockState.getValue(dj.VARIANT) != di.PODZOL) {
         if (block != Nk.GRASS_PATH) {
            return quads;
         } else {
            return betterGrassPath && getBlockAt(blockPos, facing, blockAccess) == Nk.GRASS_PATH ? modelCubeGrassPath.getQuads(blockState, facing, 0L) : quads;
         }
      } else {
         boolean flag = block == Nk.SNOW || block == Nk.SNOW_LAYER;
         if (XH.isBetterGrassFancy()) {
            if (flag) {
               if (betterPodzolSnow && getBlockAt(blockPos, facing, blockAccess) == Nk.SNOW_LAYER) {
                  return modelCubeSnow.getQuads(blockState, facing, 0L);
               }
            } else if (betterPodzol) {
               BlockPos blockpos = blockPos.down().offset(facing);
               in iblockstate = blockAccess.getBlockState(blockpos);
               if (iblockstate.getBlock() == Nk.DIRT && iblockstate.getValue(dj.VARIANT) == di.PODZOL) {
                  return modelCubePodzol.getQuads(blockState, facing, 0L);
               }
            }
         } else if (flag) {
            if (betterPodzolSnow) {
               return modelCubeSnow.getQuads(blockState, facing, 0L);
            }
         } else if (betterPodzol) {
            return modelCubePodzol.getQuads(blockState, facing, 0L);
         }

         return quads;
      }
   }

   private static List getFaceQuadsGrass(bfZ blockAccess, in blockState, BlockPos blockPos, EnumFacing facing, List quads) {
      co block = blockAccess.getBlockState(blockPos.up()).getBlock();
      boolean flag = block == Nk.SNOW || block == Nk.SNOW_LAYER;
      if (XH.isBetterGrassFancy()) {
         if (flag) {
            if (betterGrassSnow && getBlockAt(blockPos, facing, blockAccess) == Nk.SNOW_LAYER) {
               return modelCubeSnow.getQuads(blockState, facing, 0L);
            }
         } else if (betterGrass && getBlockAt(blockPos.down(), facing, blockAccess) == Nk.GRASS) {
            return modelCubeGrass.getQuads(blockState, facing, 0L);
         }
      } else if (flag) {
         if (betterGrassSnow) {
            return modelCubeSnow.getQuads(blockState, facing, 0L);
         }
      } else if (betterGrass) {
         return modelCubeGrass.getQuads(blockState, facing, 0L);
      }

      return quads;
   }

   private static co getBlockAt(BlockPos blockPos, EnumFacing facing, bfZ blockAccess) {
      BlockPos blockpos = blockPos.offset(facing);
      co block = blockAccess.getBlockState(blockpos).getBlock();
      return block;
   }

   private static boolean getBoolean(Properties props, String key, boolean def) {
      String s = props.getProperty(key);
      return s == null ? def : Boolean.parseBoolean(s);
   }
}
