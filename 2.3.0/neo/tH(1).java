package neo;

import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class tH {
   private final Map<in, sc> bakedModelStore = Maps.newIdentityHashMap();
   private final tk blockStateMapper = new tk();
   private final sC modelManager;

   public tH(sC manager) {
      this.modelManager = manager;
      this.registerAllBlocks();
   }

   public tk getBlockStateMapper() {
      return this.blockStateMapper;
   }

   public zd getTexture(in state) {
      co block = state.getBlock();
      sc ibakedmodel = this.getModelForState(state);
      if (ibakedmodel == null || ibakedmodel == this.modelManager.getMissingModel()) {
         label127: {
            if (block != Nk.WALL_SIGN && block != Nk.STANDING_SIGN && block != Nk.CHEST && block != Nk.TRAPPED_CHEST && block != Nk.STANDING_BANNER && block != Nk.WALL_BANNER && block != Nk.BED) {
               if (block == Nk.ENDER_CHEST) {
                  return this.modelManager.getTextureMap().getAtlasSprite("minecraft:blocks/obsidian");
               }

               if (block != Nk.FLOWING_LAVA && block != Nk.LAVA) {
                  if (block != Nk.FLOWING_WATER && block != Nk.WATER) {
                     if (block == Nk.SKULL) {
                        return this.modelManager.getTextureMap().getAtlasSprite("minecraft:blocks/soul_sand");
                     }

                     if (block == Nk.BARRIER) {
                        return this.modelManager.getTextureMap().getAtlasSprite("minecraft:items/barrier");
                     }

                     if (block == Nk.STRUCTURE_VOID) {
                        return this.modelManager.getTextureMap().getAtlasSprite("minecraft:items/structure_void");
                     }

                     if (block == Nk.WHITE_SHULKER_BOX) {
                        return this.modelManager.getTextureMap().getAtlasSprite("minecraft:blocks/shulker_top_white");
                     }

                     if (block == Nk.ORANGE_SHULKER_BOX) {
                        return this.modelManager.getTextureMap().getAtlasSprite("minecraft:blocks/shulker_top_orange");
                     }

                     if (block == Nk.MAGENTA_SHULKER_BOX) {
                        return this.modelManager.getTextureMap().getAtlasSprite("minecraft:blocks/shulker_top_magenta");
                     }

                     if (block == Nk.LIGHT_BLUE_SHULKER_BOX) {
                        return this.modelManager.getTextureMap().getAtlasSprite("minecraft:blocks/shulker_top_light_blue");
                     }

                     if (block == Nk.YELLOW_SHULKER_BOX) {
                        return this.modelManager.getTextureMap().getAtlasSprite("minecraft:blocks/shulker_top_yellow");
                     }

                     if (block == Nk.LIME_SHULKER_BOX) {
                        return this.modelManager.getTextureMap().getAtlasSprite("minecraft:blocks/shulker_top_lime");
                     }

                     if (block == Nk.PINK_SHULKER_BOX) {
                        return this.modelManager.getTextureMap().getAtlasSprite("minecraft:blocks/shulker_top_pink");
                     }

                     if (block == Nk.GRAY_SHULKER_BOX) {
                        return this.modelManager.getTextureMap().getAtlasSprite("minecraft:blocks/shulker_top_gray");
                     }

                     if (block == Nk.SILVER_SHULKER_BOX) {
                        return this.modelManager.getTextureMap().getAtlasSprite("minecraft:blocks/shulker_top_silver");
                     }

                     if (block == Nk.CYAN_SHULKER_BOX) {
                        return this.modelManager.getTextureMap().getAtlasSprite("minecraft:blocks/shulker_top_cyan");
                     }

                     if (block == Nk.PURPLE_SHULKER_BOX) {
                        return this.modelManager.getTextureMap().getAtlasSprite("minecraft:blocks/shulker_top_purple");
                     }

                     if (block == Nk.BLUE_SHULKER_BOX) {
                        return this.modelManager.getTextureMap().getAtlasSprite("minecraft:blocks/shulker_top_blue");
                     }

                     if (block == Nk.BROWN_SHULKER_BOX) {
                        return this.modelManager.getTextureMap().getAtlasSprite("minecraft:blocks/shulker_top_brown");
                     }

                     if (block == Nk.GREEN_SHULKER_BOX) {
                        return this.modelManager.getTextureMap().getAtlasSprite("minecraft:blocks/shulker_top_green");
                     }

                     if (block == Nk.RED_SHULKER_BOX) {
                        return this.modelManager.getTextureMap().getAtlasSprite("minecraft:blocks/shulker_top_red");
                     }

                     if (block == Nk.BLACK_SHULKER_BOX) {
                        return this.modelManager.getTextureMap().getAtlasSprite("minecraft:blocks/shulker_top_black");
                     }
                     break label127;
                  }

                  return this.modelManager.getTextureMap().getAtlasSprite("minecraft:blocks/water_still");
               }

               return this.modelManager.getTextureMap().getAtlasSprite("minecraft:blocks/lava_still");
            }

            return this.modelManager.getTextureMap().getAtlasSprite("minecraft:blocks/planks_oak");
         }
      }

      if (ibakedmodel == null) {
         ibakedmodel = this.modelManager.getMissingModel();
      }

      return ibakedmodel.getParticleTexture();
   }

   public sc getModelForState(in state) {
      sc ibakedmodel = (sc)this.bakedModelStore.get(state);
      if (ibakedmodel == null) {
         ibakedmodel = this.modelManager.getMissingModel();
      }

      return ibakedmodel;
   }

   public sC getModelManager() {
      return this.modelManager;
   }

   public void reloadModels() {
      this.bakedModelStore.clear();
      Iterator var1 = this.blockStateMapper.putAllStateModelLocations().entrySet().iterator();

      while(var1.hasNext()) {
         Map.Entry<in, sD> entry = (Map.Entry)var1.next();
         this.bakedModelStore.put(entry.getKey(), this.modelManager.getModel((sD)entry.getValue()));
      }

   }

   public void registerBlockWithStateMapper(co assoc, tm stateMapper) {
      this.blockStateMapper.registerBlockStateMapper(assoc, stateMapper);
   }

   public void registerBuiltInBlocks(co... builtIns) {
      this.blockStateMapper.registerBuiltInBlocks(builtIns);
   }

   private void registerAllBlocks() {
      this.registerBuiltInBlocks(Nk.AIR, Nk.FLOWING_WATER, Nk.WATER, Nk.FLOWING_LAVA, Nk.LAVA, Nk.PISTON_EXTENSION, Nk.CHEST, Nk.ENDER_CHEST, Nk.TRAPPED_CHEST, Nk.STANDING_SIGN, Nk.SKULL, Nk.END_PORTAL, Nk.BARRIER, Nk.WALL_SIGN, Nk.WALL_BANNER, Nk.STANDING_BANNER, Nk.END_GATEWAY, Nk.STRUCTURE_VOID, Nk.WHITE_SHULKER_BOX, Nk.ORANGE_SHULKER_BOX, Nk.MAGENTA_SHULKER_BOX, Nk.LIGHT_BLUE_SHULKER_BOX, Nk.YELLOW_SHULKER_BOX, Nk.LIME_SHULKER_BOX, Nk.PINK_SHULKER_BOX, Nk.GRAY_SHULKER_BOX, Nk.SILVER_SHULKER_BOX, Nk.CYAN_SHULKER_BOX, Nk.PURPLE_SHULKER_BOX, Nk.BLUE_SHULKER_BOX, Nk.BROWN_SHULKER_BOX, Nk.GREEN_SHULKER_BOX, Nk.RED_SHULKER_BOX, Nk.BLACK_SHULKER_BOX, Nk.BED);
      this.registerBlockWithStateMapper(Nk.STONE, (new tp()).withName(gZ.VARIANT).build());
      this.registerBlockWithStateMapper(Nk.PRISMARINE, (new tp()).withName(fv.VARIANT).build());
      this.registerBlockWithStateMapper(Nk.LEAVES, (new tp()).withName(eW.VARIANT).withSuffix("_leaves").ignore(ew.CHECK_DECAY, ew.DECAYABLE).build());
      this.registerBlockWithStateMapper(Nk.LEAVES2, (new tp()).withName(eO.VARIANT).withSuffix("_leaves").ignore(ew.CHECK_DECAY, ew.DECAYABLE).build());
      this.registerBlockWithStateMapper(Nk.CACTUS, (new tp()).ignore(cN.AGE).build());
      this.registerBlockWithStateMapper(Nk.REEDS, (new tp()).ignore(gg.AGE).build());
      this.registerBlockWithStateMapper(Nk.JUKEBOX, (new tp()).ignore(et.HAS_RECORD).build());
      this.registerBlockWithStateMapper(Nk.COBBLESTONE_WALL, (new tp()).withName(hz.VARIANT).withSuffix("_wall").build());
      this.registerBlockWithStateMapper(Nk.DOUBLE_PLANT, (new tp()).withName(dr.VARIANT).ignore(dr.FACING).build());
      this.registerBlockWithStateMapper(Nk.OAK_FENCE_GATE, (new tp()).ignore(dM.POWERED).build());
      this.registerBlockWithStateMapper(Nk.SPRUCE_FENCE_GATE, (new tp()).ignore(dM.POWERED).build());
      this.registerBlockWithStateMapper(Nk.BIRCH_FENCE_GATE, (new tp()).ignore(dM.POWERED).build());
      this.registerBlockWithStateMapper(Nk.JUNGLE_FENCE_GATE, (new tp()).ignore(dM.POWERED).build());
      this.registerBlockWithStateMapper(Nk.DARK_OAK_FENCE_GATE, (new tp()).ignore(dM.POWERED).build());
      this.registerBlockWithStateMapper(Nk.ACACIA_FENCE_GATE, (new tp()).ignore(dM.POWERED).build());
      this.registerBlockWithStateMapper(Nk.TRIPWIRE, (new tp()).ignore(ht.DISARMED, ht.POWERED).build());
      this.registerBlockWithStateMapper(Nk.DOUBLE_WOODEN_SLAB, (new tp()).withName(fl.VARIANT).withSuffix("_double_slab").build());
      this.registerBlockWithStateMapper(Nk.WOODEN_SLAB, (new tp()).withName(fl.VARIANT).withSuffix("_slab").build());
      this.registerBlockWithStateMapper(Nk.TNT, (new tp()).ignore(hl.EXPLODE).build());
      this.registerBlockWithStateMapper(Nk.FIRE, (new tp()).ignore(dN.AGE).build());
      this.registerBlockWithStateMapper(Nk.REDSTONE_WIRE, (new tp()).ignore(gf.POWER).build());
      this.registerBlockWithStateMapper(Nk.OAK_DOOR, (new tp()).ignore(do.POWERED).build());
      this.registerBlockWithStateMapper(Nk.SPRUCE_DOOR, (new tp()).ignore(do.POWERED).build());
      this.registerBlockWithStateMapper(Nk.BIRCH_DOOR, (new tp()).ignore(do.POWERED).build());
      this.registerBlockWithStateMapper(Nk.JUNGLE_DOOR, (new tp()).ignore(do.POWERED).build());
      this.registerBlockWithStateMapper(Nk.ACACIA_DOOR, (new tp()).ignore(do.POWERED).build());
      this.registerBlockWithStateMapper(Nk.DARK_OAK_DOOR, (new tp()).ignore(do.POWERED).build());
      this.registerBlockWithStateMapper(Nk.IRON_DOOR, (new tp()).ignore(do.POWERED).build());
      this.registerBlockWithStateMapper(Nk.WOOL, (new tp()).withName(cZ.COLOR).withSuffix("_wool").build());
      this.registerBlockWithStateMapper(Nk.CARPET, (new tp()).withName(cZ.COLOR).withSuffix("_carpet").build());
      this.registerBlockWithStateMapper(Nk.STAINED_HARDENED_CLAY, (new tp()).withName(cZ.COLOR).withSuffix("_stained_hardened_clay").build());
      this.registerBlockWithStateMapper(Nk.STAINED_GLASS_PANE, (new tp()).withName(cZ.COLOR).withSuffix("_stained_glass_pane").build());
      this.registerBlockWithStateMapper(Nk.STAINED_GLASS, (new tp()).withName(cZ.COLOR).withSuffix("_stained_glass").build());
      this.registerBlockWithStateMapper(Nk.SANDSTONE, (new tp()).withName(gm.TYPE).build());
      this.registerBlockWithStateMapper(Nk.RED_SANDSTONE, (new tp()).withName(fT.TYPE).build());
      this.registerBlockWithStateMapper(Nk.TALLGRASS, (new tp()).withName(hk.TYPE).build());
      this.registerBlockWithStateMapper(Nk.YELLOW_FLOWER, (new tp()).withName(Nk.YELLOW_FLOWER.getTypeProperty()).build());
      this.registerBlockWithStateMapper(Nk.RED_FLOWER, (new tp()).withName(Nk.RED_FLOWER.getTypeProperty()).build());
      this.registerBlockWithStateMapper(Nk.STONE_SLAB, (new tp()).withName(hd.VARIANT).withSuffix("_slab").build());
      this.registerBlockWithStateMapper(Nk.STONE_SLAB2, (new tp()).withName(hf.VARIANT).withSuffix("_slab").build());
      this.registerBlockWithStateMapper(Nk.MONSTER_EGG, (new tp()).withName(gB.VARIANT).withSuffix("_monster_egg").build());
      this.registerBlockWithStateMapper(Nk.STONEBRICK, (new tp()).withName(hb.VARIANT).build());
      this.registerBlockWithStateMapper(Nk.DISPENSER, (new tp()).ignore(dk.TRIGGERED).build());
      this.registerBlockWithStateMapper(Nk.DROPPER, (new tp()).ignore(dw.TRIGGERED).build());
      this.registerBlockWithStateMapper(Nk.LOG, (new tp()).withName(eZ.VARIANT).withSuffix("_log").build());
      this.registerBlockWithStateMapper(Nk.LOG2, (new tp()).withName(eR.VARIANT).withSuffix("_log").build());
      this.registerBlockWithStateMapper(Nk.PLANKS, (new tp()).withName(fl.VARIANT).withSuffix("_planks").build());
      this.registerBlockWithStateMapper(Nk.SAPLING, (new tp()).withName(go.TYPE).withSuffix("_sapling").build());
      this.registerBlockWithStateMapper(Nk.SAND, (new tp()).withName(gk.VARIANT).build());
      this.registerBlockWithStateMapper(Nk.HOPPER, (new tp()).ignore(em.ENABLED).build());
      this.registerBlockWithStateMapper(Nk.FLOWER_POT, (new tp()).ignore(dV.LEGACY_DATA).build());
      this.registerBlockWithStateMapper(Nk.CONCRETE, (new tp()).withName(cZ.COLOR).withSuffix("_concrete").build());
      this.registerBlockWithStateMapper(Nk.CONCRETE_POWDER, (new tp()).withName(cZ.COLOR).withSuffix("_concrete_powder").build());
      this.registerBlockWithStateMapper(Nk.QUARTZ_BLOCK, new tr() {
         protected sD getModelResourceLocation(in state) {
            fD blockquartz$enumtype = (fD)state.getValue(fE.VARIANT);
            switch (blockquartz$enumtype) {
               case DEFAULT:
               default:
                  return new sD("quartz_block", "normal");
               case CHISELED:
                  return new sD("chiseled_quartz_block", "normal");
               case LINES_Y:
                  return new sD("quartz_column", "axis=y");
               case LINES_X:
                  return new sD("quartz_column", "axis=x");
               case LINES_Z:
                  return new sD("quartz_column", "axis=z");
            }
         }
      });
      this.registerBlockWithStateMapper(Nk.DEADBUSH, new tr() {
         protected sD getModelResourceLocation(in state) {
            return new sD("dead_bush", "normal");
         }
      });
      this.registerBlockWithStateMapper(Nk.PUMPKIN_STEM, new tr() {
         protected sD getModelResourceLocation(in state) {
            Map<hT<?>, Comparable<?>> map = Maps.newLinkedHashMap(state.getProperties());
            if (state.getValue(gX.FACING) != EnumFacing.UP) {
               map.remove(gX.AGE);
            }

            return new sD((ResourceLocation)co.REGISTRY.getNameForObject(state.getBlock()), this.getPropertyString(map));
         }
      });
      this.registerBlockWithStateMapper(Nk.MELON_STEM, new tr() {
         protected sD getModelResourceLocation(in state) {
            Map<hT<?>, Comparable<?>> map = Maps.newLinkedHashMap(state.getProperties());
            if (state.getValue(gX.FACING) != EnumFacing.UP) {
               map.remove(gX.AGE);
            }

            return new sD((ResourceLocation)co.REGISTRY.getNameForObject(state.getBlock()), this.getPropertyString(map));
         }
      });
      this.registerBlockWithStateMapper(Nk.DIRT, new tr() {
         protected sD getModelResourceLocation(in state) {
            Map<hT<?>, Comparable<?>> map = Maps.newLinkedHashMap(state.getProperties());
            String s = dj.VARIANT.getName((Enum)((di)map.remove(dj.VARIANT)));
            if (di.PODZOL != state.getValue(dj.VARIANT)) {
               map.remove(dj.SNOWY);
            }

            return new sD(s, this.getPropertyString(map));
         }
      });
      this.registerBlockWithStateMapper(Nk.DOUBLE_STONE_SLAB, new tr() {
         protected sD getModelResourceLocation(in state) {
            Map<hT<?>, Comparable<?>> map = Maps.newLinkedHashMap(state.getProperties());
            String s = hd.VARIANT.getName((Enum)((hc)map.remove(hd.VARIANT)));
            map.remove(hd.SEAMLESS);
            String s1 = (Boolean)state.getValue(hd.SEAMLESS) ? "all" : "normal";
            return new sD(s + "_double_slab", s1);
         }
      });
      this.registerBlockWithStateMapper(Nk.DOUBLE_STONE_SLAB2, new tr() {
         protected sD getModelResourceLocation(in state) {
            Map<hT<?>, Comparable<?>> map = Maps.newLinkedHashMap(state.getProperties());
            String s = hf.VARIANT.getName((Enum)((he)map.remove(hf.VARIANT)));
            map.remove(hd.SEAMLESS);
            String s1 = (Boolean)state.getValue(hf.SEAMLESS) ? "all" : "normal";
            return new sD(s + "_double_slab", s1);
         }
      });
   }
}
