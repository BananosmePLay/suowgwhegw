package neo;

import com.google.common.base.Joiner;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import com.google.common.collect.Sets;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.IRegistry;
import net.minecraft.util.registry.RegistrySimple;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class st {
   private static final Set<ResourceLocation> LOCATIONS_BUILTIN_TEXTURES = Sets.newHashSet(new ResourceLocation[]{new ResourceLocation("blocks/water_flow"), new ResourceLocation("blocks/water_still"), new ResourceLocation("blocks/lava_flow"), new ResourceLocation("blocks/lava_still"), new ResourceLocation("blocks/water_overlay"), new ResourceLocation("blocks/destroy_stage_0"), new ResourceLocation("blocks/destroy_stage_1"), new ResourceLocation("blocks/destroy_stage_2"), new ResourceLocation("blocks/destroy_stage_3"), new ResourceLocation("blocks/destroy_stage_4"), new ResourceLocation("blocks/destroy_stage_5"), new ResourceLocation("blocks/destroy_stage_6"), new ResourceLocation("blocks/destroy_stage_7"), new ResourceLocation("blocks/destroy_stage_8"), new ResourceLocation("blocks/destroy_stage_9"), new ResourceLocation("items/empty_armor_slot_helmet"), new ResourceLocation("items/empty_armor_slot_chestplate"), new ResourceLocation("items/empty_armor_slot_leggings"), new ResourceLocation("items/empty_armor_slot_boots"), new ResourceLocation("items/empty_armor_slot_shield"), new ResourceLocation("blocks/shulker_top_white"), new ResourceLocation("blocks/shulker_top_orange"), new ResourceLocation("blocks/shulker_top_magenta"), new ResourceLocation("blocks/shulker_top_light_blue"), new ResourceLocation("blocks/shulker_top_yellow"), new ResourceLocation("blocks/shulker_top_lime"), new ResourceLocation("blocks/shulker_top_pink"), new ResourceLocation("blocks/shulker_top_gray"), new ResourceLocation("blocks/shulker_top_silver"), new ResourceLocation("blocks/shulker_top_cyan"), new ResourceLocation("blocks/shulker_top_purple"), new ResourceLocation("blocks/shulker_top_blue"), new ResourceLocation("blocks/shulker_top_brown"), new ResourceLocation("blocks/shulker_top_green"), new ResourceLocation("blocks/shulker_top_red"), new ResourceLocation("blocks/shulker_top_black")});
   private static final Logger LOGGER = LogManager.getLogger();
   protected static final sD MODEL_MISSING = new sD("builtin/missing", "missing");
   private static final String MISSING_MODEL_MESH = "{    'textures': {       'particle': 'missingno',       'missingno': 'missingno'    },    'elements': [         {  'from': [ 0, 0, 0 ],            'to': [ 16, 16, 16 ],            'faces': {                'down':  { 'uv': [ 0, 0, 16, 16 ], 'cullface': 'down',  'texture': '#missingno' },                'up':    { 'uv': [ 0, 0, 16, 16 ], 'cullface': 'up',    'texture': '#missingno' },                'north': { 'uv': [ 0, 0, 16, 16 ], 'cullface': 'north', 'texture': '#missingno' },                'south': { 'uv': [ 0, 0, 16, 16 ], 'cullface': 'south', 'texture': '#missingno' },                'west':  { 'uv': [ 0, 0, 16, 16 ], 'cullface': 'west',  'texture': '#missingno' },                'east':  { 'uv': [ 0, 0, 16, 16 ], 'cullface': 'east',  'texture': '#missingno' }            }        }    ]}".replaceAll("'", "\"");
   private static final Map<String, String> BUILT_IN_MODELS = Maps.newHashMap();
   private static final Joiner JOINER = Joiner.on(" -> ");
   private final AA resourceManager;
   private final Map<ResourceLocation, zd> sprites = Maps.newHashMap();
   private final Map<ResourceLocation, sy> models = Maps.newLinkedHashMap();
   private final Map<sD, tg> variants = Maps.newLinkedHashMap();
   private final Map<sB, Collection<sD>> multipartVariantMap = Maps.newLinkedHashMap();
   private final zj textureMap;
   private final tH blockModelShapes;
   private final sb faceBakery = new sb();
   private final sk itemModelGenerator = new sk();
   private final RegistrySimple<sD, sc> bakedRegistry = new RegistrySimple();
   private static final String EMPTY_MODEL_RAW = "{    'elements': [        {   'from': [0, 0, 0],            'to': [16, 16, 16],            'faces': {                'down': {'uv': [0, 0, 16, 16], 'texture': '' }            }        }    ]}".replaceAll("'", "\"");
   private static final sy MODEL_GENERATED;
   private static final sy MODEL_ENTITY;
   private final Map<String, ResourceLocation> itemLocations = Maps.newLinkedHashMap();
   private final Map<ResourceLocation, sB> blockDefinitions = Maps.newHashMap();
   private final Map<OL, List<String>> variantNames = Maps.newIdentityHashMap();
   private static Map<biH<OL>, Set<String>> customVariantNames;

   public st(AA resourceManagerIn, zj textureMapIn, tH blockModelShapesIn) {
      this.resourceManager = resourceManagerIn;
      this.textureMap = textureMapIn;
      this.blockModelShapes = blockModelShapesIn;
   }

   public IRegistry<sD, sc> setupModelRegistry() {
      this.loadBlocks();
      this.loadVariantItemModels();
      this.loadModelsCheck();
      this.loadSprites();
      this.makeItemModels();
      this.bakeBlockModels();
      this.bakeItemModels();
      return this.bakedRegistry;
   }

   private void loadBlocks() {
      tk blockstatemapper = this.blockModelShapes.getBlockStateMapper();
      Iterator var2 = co.REGISTRY.iterator();

      while(var2.hasNext()) {
         co block = (co)var2.next();
         Iterator var4 = blockstatemapper.getBlockstateLocations(block).iterator();

         while(var4.hasNext()) {
            ResourceLocation resourcelocation = (ResourceLocation)var4.next();

            try {
               this.loadBlock(blockstatemapper, block, resourcelocation);
            } catch (Exception var7) {
               Exception exception = var7;
               LOGGER.warn("Unable to load definition " + resourcelocation, exception);
            }
         }
      }

   }

   protected void loadBlock(tk p_loadBlock_1_, co p_loadBlock_2_, final ResourceLocation p_loadBlock_3_) {
      sB modelblockdefinition = this.getModelBlockDefinition(p_loadBlock_3_);
      Map<in, sD> map = p_loadBlock_1_.getVariants(p_loadBlock_2_);
      if (modelblockdefinition.hasMultipartData()) {
         Collection<sD> collection = Sets.newHashSet(map.values());
         modelblockdefinition.getMultipartData().setStateContainer(p_loadBlock_2_.getBlockState());
         Collection<sD> collection1 = (Collection)this.multipartVariantMap.get(modelblockdefinition);
         if (collection1 == null) {
            collection1 = Lists.newArrayList();
         }

         ((Collection)collection1).addAll(Lists.newArrayList(Iterables.filter(collection, new Predicate<sD>() {
            public boolean apply(@Nullable sD p_apply_1_) {
               return p_loadBlock_3_.equals(p_apply_1_);
            }

            // $FF: synthetic method
            // $FF: bridge method
            public boolean apply(@Nullable Object var1) {
               return this.apply((sD)var1);
            }
         })));
         this.registerMultipartVariant(modelblockdefinition, (Collection)collection1);
      }

      Iterator var11 = map.entrySet().iterator();

      while(true) {
         sD modelresourcelocation;
         do {
            if (!var11.hasNext()) {
               return;
            }

            Map.Entry<in, sD> entry = (Map.Entry)var11.next();
            modelresourcelocation = (sD)entry.getValue();
         } while(!p_loadBlock_3_.equals(modelresourcelocation));

         try {
            if (bnK.ForgeItem_delegate.exists()) {
               this.registerVariant(modelblockdefinition, modelresourcelocation);
            } else {
               this.variants.put(modelresourcelocation, modelblockdefinition.getVariant(modelresourcelocation.getVariant()));
            }
         } catch (RuntimeException var10) {
            RuntimeException runtimeexception = var10;
            if (!modelblockdefinition.hasMultipartData()) {
               LOGGER.warn("Unable to load variant: " + modelresourcelocation.getVariant() + " from " + modelresourcelocation, runtimeexception);
            }
         }
      }
   }

   private void loadVariantItemModels() {
      this.variants.put(MODEL_MISSING, new tg(Lists.newArrayList(new te[]{new te(new ResourceLocation(MODEL_MISSING.getPath()), sE.X0_Y0, false, 1)})));
      this.loadStaticModels();
      this.loadVariantModels();
      this.loadMultipartVariantModels();
      this.loadItemModels();
      bjG.update();
      bjG.loadModels(this);
   }

   private void loadStaticModels() {
      ResourceLocation resourcelocation = new ResourceLocation("item_frame");
      sB modelblockdefinition = this.getModelBlockDefinition(resourcelocation);
      this.registerVariant(modelblockdefinition, new sD(resourcelocation, "normal"));
      this.registerVariant(modelblockdefinition, new sD(resourcelocation, "map"));
   }

   private void registerVariant(sB blockstateDefinition, sD location) {
      try {
         this.variants.put(location, blockstateDefinition.getVariant(location.getVariant()));
      } catch (RuntimeException var4) {
         if (!blockstateDefinition.hasMultipartData()) {
            LOGGER.warn("Unable to load variant: {} from {}", location.getVariant(), location);
         }
      }

   }

   private sB getModelBlockDefinition(ResourceLocation location) {
      ResourceLocation resourcelocation = this.getBlockstateLocation(location);
      sB modelblockdefinition = (sB)this.blockDefinitions.get(resourcelocation);
      if (modelblockdefinition == null) {
         modelblockdefinition = this.loadMultipartMBD(location, resourcelocation);
         this.blockDefinitions.put(resourcelocation, modelblockdefinition);
      }

      return modelblockdefinition;
   }

   private sB loadMultipartMBD(ResourceLocation location, ResourceLocation fileIn) {
      List<sB> list = Lists.newArrayList();

      try {
         Iterator var7 = this.resourceManager.getAllResources(fileIn).iterator();

         while(var7.hasNext()) {
            Az iresource = (Az)var7.next();
            list.add(this.loadModelBlockDefinition(location, iresource));
         }
      } catch (IOException var6) {
         IOException ioexception = var6;
         throw new RuntimeException("Encountered an exception when loading model definition of model " + fileIn, ioexception);
      }

      return new sB(list);
   }

   private sB loadModelBlockDefinition(ResourceLocation location, Az resource) {
      InputStream inputstream = null;

      sB modelblockdefinition;
      try {
         inputstream = resource.getInputStream();
         if (bnK.ForgeModelBlockDefinition_parseFromReader2.exists()) {
            modelblockdefinition = (sB)bnK.call(bnK.ForgeModelBlockDefinition_parseFromReader2, new InputStreamReader(inputstream, StandardCharsets.UTF_8), location);
         } else {
            modelblockdefinition = sB.parseFromReader(new InputStreamReader(inputstream, StandardCharsets.UTF_8));
         }
      } catch (Exception var9) {
         Exception exception = var9;
         throw new RuntimeException("Encountered an exception when loading model definition of '" + location + "' from: '" + resource.getResourceLocation() + "' in resourcepack: '" + resource.getResourcePackName() + "'", exception);
      } finally {
         IOUtils.closeQuietly(inputstream);
      }

      return modelblockdefinition;
   }

   private ResourceLocation getBlockstateLocation(ResourceLocation location) {
      return new ResourceLocation(location.getNamespace(), "blockstates/" + location.getPath() + ".json");
   }

   private void loadVariantModels() {
      Iterator var1 = this.variants.entrySet().iterator();

      while(var1.hasNext()) {
         Map.Entry<sD, tg> entry = (Map.Entry)var1.next();
         this.loadVariantList((sD)entry.getKey(), (tg)entry.getValue());
      }

   }

   private void loadMultipartVariantModels() {
      Iterator var1 = this.multipartVariantMap.entrySet().iterator();

      while(var1.hasNext()) {
         Map.Entry<sB, Collection<sD>> entry = (Map.Entry)var1.next();
         sD modelresourcelocation = (sD)((Collection)entry.getValue()).iterator().next();
         Iterator var4 = ((sB)entry.getKey()).getMultipartVariants().iterator();

         while(var4.hasNext()) {
            tg variantlist = (tg)var4.next();
            this.loadVariantList(modelresourcelocation, variantlist);
         }
      }

   }

   private void loadVariantList(sD p_188638_1_, tg p_188638_2_) {
      Iterator var3 = p_188638_2_.getVariantList().iterator();

      while(var3.hasNext()) {
         te variant = (te)var3.next();
         ResourceLocation resourcelocation = variant.getModelLocation();
         if (this.models.get(resourcelocation) == null) {
            try {
               this.models.put(resourcelocation, this.loadModel(resourcelocation));
            } catch (Exception var7) {
               Exception exception = var7;
               LOGGER.warn("Unable to load block model: '{}' for variant: '{}': {} ", resourcelocation, p_188638_1_, exception);
            }
         }
      }

   }

   private sy loadModel(ResourceLocation location) throws IOException {
      Reader reader = null;
      Az iresource = null;

      sy modelblock1;
      try {
         String s = location.getPath();
         sy modelblock3;
         if ("builtin/generated".equals(s)) {
            modelblock3 = MODEL_GENERATED;
            sy var14 = modelblock3;
            return var14;
         }

         if (!"builtin/entity".equals(s)) {
            String s1;
            if (s.startsWith("builtin/")) {
               String s2 = s.substring("builtin/".length());
               s1 = (String)BUILT_IN_MODELS.get(s2);
               if (s1 == null) {
                  throw new FileNotFoundException(location.toString());
               }

               reader = new StringReader(s1);
            } else {
               location = this.getModelLocation(location);
               iresource = this.resourceManager.getResource(location);
               reader = new InputStreamReader(iresource.getInputStream(), StandardCharsets.UTF_8);
            }

            modelblock3 = sy.deserialize((Reader)reader);
            modelblock3.name = location.toString();
            s1 = bqS.getBasePath(location.getPath());
            fixModelLocations(modelblock3, s1);
            sy modelblock2 = modelblock3;
            sy var9 = modelblock2;
            return var9;
         }

         modelblock3 = MODEL_ENTITY;
         modelblock1 = modelblock3;
      } finally {
         IOUtils.closeQuietly((Reader)reader);
         IOUtils.closeQuietly(iresource);
      }

      return modelblock1;
   }

   private ResourceLocation getModelLocation(ResourceLocation location) {
      String s = location.getPath();
      if (!s.startsWith("mcpatcher") && !s.startsWith("optifine")) {
         return new ResourceLocation(location.getNamespace(), "models/" + location.getPath() + ".json");
      } else {
         if (!s.endsWith(".json")) {
            location = new ResourceLocation(location.getNamespace(), s + ".json");
         }

         return location;
      }
   }

   private void loadItemModels() {
      this.registerVariantNames();
      Iterator var1 = OL.REGISTRY.iterator();

      label36:
      while(var1.hasNext()) {
         OL item = (OL)var1.next();
         Iterator var3 = this.getVariantNames(item).iterator();

         while(true) {
            ResourceLocation resourcelocation1;
            sy modelblock;
            do {
               ResourceLocation resourcelocation;
               do {
                  if (!var3.hasNext()) {
                     continue label36;
                  }

                  String s = (String)var3.next();
                  resourcelocation = this.getItemLocation(s);
                  resourcelocation1 = (ResourceLocation)OL.REGISTRY.getNameForObject(item);
                  this.loadItemModel(s, resourcelocation, resourcelocation1);
               } while(!item.hasCustomProperties());

               modelblock = (sy)this.models.get(resourcelocation);
            } while(modelblock == null);

            Iterator var8 = modelblock.getOverrideLocations().iterator();

            while(var8.hasNext()) {
               ResourceLocation resourcelocation2 = (ResourceLocation)var8.next();
               this.loadItemModel(resourcelocation2.toString(), resourcelocation2, resourcelocation1);
            }
         }
      }

   }

   public void loadItemModel(String variantName, ResourceLocation location, ResourceLocation itemName) {
      this.itemLocations.put(variantName, location);
      if (this.models.get(location) == null) {
         try {
            sy modelblock = this.loadModel(location);
            this.models.put(location, modelblock);
         } catch (Exception var5) {
            Exception exception1 = var5;
            LOGGER.warn("Unable to load item model: '{}' for item: '{}'", location, itemName);
            LOGGER.warn(exception1.getClass().getName() + ": " + exception1.getMessage());
         }
      }

   }

   private void registerVariantNames() {
      this.variantNames.clear();
      this.variantNames.put(OL.getItemFromBlock(Nk.STONE), Lists.newArrayList(new String[]{"stone", "granite", "granite_smooth", "diorite", "diorite_smooth", "andesite", "andesite_smooth"}));
      this.variantNames.put(OL.getItemFromBlock(Nk.DIRT), Lists.newArrayList(new String[]{"dirt", "coarse_dirt", "podzol"}));
      this.variantNames.put(OL.getItemFromBlock(Nk.PLANKS), Lists.newArrayList(new String[]{"oak_planks", "spruce_planks", "birch_planks", "jungle_planks", "acacia_planks", "dark_oak_planks"}));
      this.variantNames.put(OL.getItemFromBlock(Nk.SAPLING), Lists.newArrayList(new String[]{"oak_sapling", "spruce_sapling", "birch_sapling", "jungle_sapling", "acacia_sapling", "dark_oak_sapling"}));
      this.variantNames.put(OL.getItemFromBlock(Nk.SAND), Lists.newArrayList(new String[]{"sand", "red_sand"}));
      this.variantNames.put(OL.getItemFromBlock(Nk.LOG), Lists.newArrayList(new String[]{"oak_log", "spruce_log", "birch_log", "jungle_log"}));
      this.variantNames.put(OL.getItemFromBlock(Nk.LEAVES), Lists.newArrayList(new String[]{"oak_leaves", "spruce_leaves", "birch_leaves", "jungle_leaves"}));
      this.variantNames.put(OL.getItemFromBlock(Nk.SPONGE), Lists.newArrayList(new String[]{"sponge", "sponge_wet"}));
      this.variantNames.put(OL.getItemFromBlock(Nk.SANDSTONE), Lists.newArrayList(new String[]{"sandstone", "chiseled_sandstone", "smooth_sandstone"}));
      this.variantNames.put(OL.getItemFromBlock(Nk.RED_SANDSTONE), Lists.newArrayList(new String[]{"red_sandstone", "chiseled_red_sandstone", "smooth_red_sandstone"}));
      this.variantNames.put(OL.getItemFromBlock(Nk.TALLGRASS), Lists.newArrayList(new String[]{"dead_bush", "tall_grass", "fern"}));
      this.variantNames.put(OL.getItemFromBlock(Nk.DEADBUSH), Lists.newArrayList(new String[]{"dead_bush"}));
      this.variantNames.put(OL.getItemFromBlock(Nk.WOOL), Lists.newArrayList(new String[]{"black_wool", "red_wool", "green_wool", "brown_wool", "blue_wool", "purple_wool", "cyan_wool", "silver_wool", "gray_wool", "pink_wool", "lime_wool", "yellow_wool", "light_blue_wool", "magenta_wool", "orange_wool", "white_wool"}));
      this.variantNames.put(OL.getItemFromBlock(Nk.YELLOW_FLOWER), Lists.newArrayList(new String[]{"dandelion"}));
      this.variantNames.put(OL.getItemFromBlock(Nk.RED_FLOWER), Lists.newArrayList(new String[]{"poppy", "blue_orchid", "allium", "houstonia", "red_tulip", "orange_tulip", "white_tulip", "pink_tulip", "oxeye_daisy"}));
      this.variantNames.put(OL.getItemFromBlock(Nk.STONE_SLAB), Lists.newArrayList(new String[]{"stone_slab", "sandstone_slab", "cobblestone_slab", "brick_slab", "stone_brick_slab", "nether_brick_slab", "quartz_slab"}));
      this.variantNames.put(OL.getItemFromBlock(Nk.STONE_SLAB2), Lists.newArrayList(new String[]{"red_sandstone_slab"}));
      this.variantNames.put(OL.getItemFromBlock(Nk.STAINED_GLASS), Lists.newArrayList(new String[]{"black_stained_glass", "red_stained_glass", "green_stained_glass", "brown_stained_glass", "blue_stained_glass", "purple_stained_glass", "cyan_stained_glass", "silver_stained_glass", "gray_stained_glass", "pink_stained_glass", "lime_stained_glass", "yellow_stained_glass", "light_blue_stained_glass", "magenta_stained_glass", "orange_stained_glass", "white_stained_glass"}));
      this.variantNames.put(OL.getItemFromBlock(Nk.MONSTER_EGG), Lists.newArrayList(new String[]{"stone_monster_egg", "cobblestone_monster_egg", "stone_brick_monster_egg", "mossy_brick_monster_egg", "cracked_brick_monster_egg", "chiseled_brick_monster_egg"}));
      this.variantNames.put(OL.getItemFromBlock(Nk.STONEBRICK), Lists.newArrayList(new String[]{"stonebrick", "mossy_stonebrick", "cracked_stonebrick", "chiseled_stonebrick"}));
      this.variantNames.put(OL.getItemFromBlock(Nk.WOODEN_SLAB), Lists.newArrayList(new String[]{"oak_slab", "spruce_slab", "birch_slab", "jungle_slab", "acacia_slab", "dark_oak_slab"}));
      this.variantNames.put(OL.getItemFromBlock(Nk.COBBLESTONE_WALL), Lists.newArrayList(new String[]{"cobblestone_wall", "mossy_cobblestone_wall"}));
      this.variantNames.put(OL.getItemFromBlock(Nk.ANVIL), Lists.newArrayList(new String[]{"anvil_intact", "anvil_slightly_damaged", "anvil_very_damaged"}));
      this.variantNames.put(OL.getItemFromBlock(Nk.QUARTZ_BLOCK), Lists.newArrayList(new String[]{"quartz_block", "chiseled_quartz_block", "quartz_column"}));
      this.variantNames.put(OL.getItemFromBlock(Nk.STAINED_HARDENED_CLAY), Lists.newArrayList(new String[]{"black_stained_hardened_clay", "red_stained_hardened_clay", "green_stained_hardened_clay", "brown_stained_hardened_clay", "blue_stained_hardened_clay", "purple_stained_hardened_clay", "cyan_stained_hardened_clay", "silver_stained_hardened_clay", "gray_stained_hardened_clay", "pink_stained_hardened_clay", "lime_stained_hardened_clay", "yellow_stained_hardened_clay", "light_blue_stained_hardened_clay", "magenta_stained_hardened_clay", "orange_stained_hardened_clay", "white_stained_hardened_clay"}));
      this.variantNames.put(OL.getItemFromBlock(Nk.STAINED_GLASS_PANE), Lists.newArrayList(new String[]{"black_stained_glass_pane", "red_stained_glass_pane", "green_stained_glass_pane", "brown_stained_glass_pane", "blue_stained_glass_pane", "purple_stained_glass_pane", "cyan_stained_glass_pane", "silver_stained_glass_pane", "gray_stained_glass_pane", "pink_stained_glass_pane", "lime_stained_glass_pane", "yellow_stained_glass_pane", "light_blue_stained_glass_pane", "magenta_stained_glass_pane", "orange_stained_glass_pane", "white_stained_glass_pane"}));
      this.variantNames.put(OL.getItemFromBlock(Nk.LEAVES2), Lists.newArrayList(new String[]{"acacia_leaves", "dark_oak_leaves"}));
      this.variantNames.put(OL.getItemFromBlock(Nk.LOG2), Lists.newArrayList(new String[]{"acacia_log", "dark_oak_log"}));
      this.variantNames.put(OL.getItemFromBlock(Nk.PRISMARINE), Lists.newArrayList(new String[]{"prismarine", "prismarine_bricks", "dark_prismarine"}));
      this.variantNames.put(OL.getItemFromBlock(Nk.CARPET), Lists.newArrayList(new String[]{"black_carpet", "red_carpet", "green_carpet", "brown_carpet", "blue_carpet", "purple_carpet", "cyan_carpet", "silver_carpet", "gray_carpet", "pink_carpet", "lime_carpet", "yellow_carpet", "light_blue_carpet", "magenta_carpet", "orange_carpet", "white_carpet"}));
      this.variantNames.put(OL.getItemFromBlock(Nk.DOUBLE_PLANT), Lists.newArrayList(new String[]{"sunflower", "syringa", "double_grass", "double_fern", "double_rose", "paeonia"}));
      this.variantNames.put(NK.COAL, Lists.newArrayList(new String[]{"coal", "charcoal"}));
      this.variantNames.put(NK.FISH, Lists.newArrayList(new String[]{"cod", "salmon", "clownfish", "pufferfish"}));
      this.variantNames.put(NK.COOKED_FISH, Lists.newArrayList(new String[]{"cooked_cod", "cooked_salmon"}));
      this.variantNames.put(NK.DYE, Lists.newArrayList(new String[]{"dye_black", "dye_red", "dye_green", "dye_brown", "dye_blue", "dye_purple", "dye_cyan", "dye_silver", "dye_gray", "dye_pink", "dye_lime", "dye_yellow", "dye_light_blue", "dye_magenta", "dye_orange", "dye_white"}));
      this.variantNames.put(NK.POTIONITEM, Lists.newArrayList(new String[]{"bottle_drinkable"}));
      this.variantNames.put(NK.SKULL, Lists.newArrayList(new String[]{"skull_skeleton", "skull_wither", "skull_zombie", "skull_char", "skull_creeper", "skull_dragon"}));
      this.variantNames.put(NK.SPLASH_POTION, Lists.newArrayList(new String[]{"bottle_splash"}));
      this.variantNames.put(NK.LINGERING_POTION, Lists.newArrayList(new String[]{"bottle_lingering"}));
      this.variantNames.put(OL.getItemFromBlock(Nk.CONCRETE), Lists.newArrayList(new String[]{"black_concrete", "red_concrete", "green_concrete", "brown_concrete", "blue_concrete", "purple_concrete", "cyan_concrete", "silver_concrete", "gray_concrete", "pink_concrete", "lime_concrete", "yellow_concrete", "light_blue_concrete", "magenta_concrete", "orange_concrete", "white_concrete"}));
      this.variantNames.put(OL.getItemFromBlock(Nk.CONCRETE_POWDER), Lists.newArrayList(new String[]{"black_concrete_powder", "red_concrete_powder", "green_concrete_powder", "brown_concrete_powder", "blue_concrete_powder", "purple_concrete_powder", "cyan_concrete_powder", "silver_concrete_powder", "gray_concrete_powder", "pink_concrete_powder", "lime_concrete_powder", "yellow_concrete_powder", "light_blue_concrete_powder", "magenta_concrete_powder", "orange_concrete_powder", "white_concrete_powder"}));
      this.variantNames.put(OL.getItemFromBlock(Nk.AIR), Collections.emptyList());
      this.variantNames.put(OL.getItemFromBlock(Nk.OAK_FENCE_GATE), Lists.newArrayList(new String[]{"oak_fence_gate"}));
      this.variantNames.put(OL.getItemFromBlock(Nk.OAK_FENCE), Lists.newArrayList(new String[]{"oak_fence"}));
      this.variantNames.put(NK.OAK_DOOR, Lists.newArrayList(new String[]{"oak_door"}));
      this.variantNames.put(NK.BOAT, Lists.newArrayList(new String[]{"oak_boat"}));
      this.variantNames.put(NK.TOTEM_OF_UNDYING, Lists.newArrayList(new String[]{"totem"}));
      Iterator var1 = customVariantNames.entrySet().iterator();

      while(var1.hasNext()) {
         Map.Entry<biH<OL>, Set<String>> entry = (Map.Entry)var1.next();
         this.variantNames.put(((biH)entry.getKey()).get(), Lists.newArrayList(((Set)entry.getValue()).iterator()));
      }

   }

   private List<String> getVariantNames(OL stack) {
      List<String> list = (List)this.variantNames.get(stack);
      if (list == null) {
         list = Collections.singletonList(((ResourceLocation)OL.REGISTRY.getNameForObject(stack)).toString());
      }

      return list;
   }

   private ResourceLocation getItemLocation(String location) {
      ResourceLocation resourcelocation = new ResourceLocation(location);
      if (bnK.ForgeHooksClient.exists()) {
         resourcelocation = new ResourceLocation(location.replaceAll("#.*", ""));
      }

      return new ResourceLocation(resourcelocation.getNamespace(), "item/" + resourcelocation.getPath());
   }

   private void bakeBlockModels() {
      Iterator var1 = this.variants.keySet().iterator();

      while(var1.hasNext()) {
         sD modelresourcelocation = (sD)var1.next();
         sc ibakedmodel = this.createRandomModelForVariantList((tg)this.variants.get(modelresourcelocation), modelresourcelocation.toString());
         if (ibakedmodel != null) {
            this.bakedRegistry.putObject(modelresourcelocation, ibakedmodel);
         }
      }

      var1 = this.multipartVariantMap.entrySet().iterator();

      while(var1.hasNext()) {
         Map.Entry<sB, Collection<sD>> entry = (Map.Entry)var1.next();
         sB modelblockdefinition = (sB)entry.getKey();
         sS multipart = modelblockdefinition.getMultipartData();
         String s = ((ResourceLocation)co.REGISTRY.getNameForObject(multipart.getStateContainer().getBlock())).toString();
         sY multipartbakedmodel$builder = new sY();
         Iterator var7 = multipart.getSelectors().iterator();

         while(var7.hasNext()) {
            sX selector = (sX)var7.next();
            sc ibakedmodel1 = this.createRandomModelForVariantList(selector.getVariantList(), "selector of " + s);
            if (ibakedmodel1 != null) {
               multipartbakedmodel$builder.putModel(selector.getPredicate(multipart.getStateContainer()), ibakedmodel1);
            }
         }

         sc ibakedmodel2 = multipartbakedmodel$builder.makeMultipartModel();
         Iterator var13 = ((Collection)entry.getValue()).iterator();

         while(var13.hasNext()) {
            sD modelresourcelocation1 = (sD)var13.next();
            if (!modelblockdefinition.hasVariant(modelresourcelocation1.getVariant())) {
               this.bakedRegistry.putObject(modelresourcelocation1, ibakedmodel2);
            }
         }
      }

   }

   @Nullable
   private sc createRandomModelForVariantList(tg variantsIn, String modelLocation) {
      if (variantsIn.getVariantList().isEmpty()) {
         return null;
      } else {
         th weightedbakedmodel$builder = new th();
         int i = 0;
         Iterator var5 = variantsIn.getVariantList().iterator();

         while(true) {
            while(var5.hasNext()) {
               te variant = (te)var5.next();
               sy modelblock = (sy)this.models.get(variant.getModelLocation());
               if (modelblock != null && modelblock.isResolved()) {
                  if (modelblock.getElements().isEmpty()) {
                     LOGGER.warn("Missing elements for: {}", modelLocation);
                  } else {
                     sc ibakedmodel = this.bakeModel(modelblock, variant.getRotation(), variant.isUvLock());
                     if (ibakedmodel != null) {
                        ++i;
                        weightedbakedmodel$builder.add(ibakedmodel, variant.getWeight());
                     }
                  }
               } else {
                  LOGGER.warn("Missing model for: {}", modelLocation);
               }
            }

            sc ibakedmodel1 = null;
            if (i == 0) {
               LOGGER.warn("No weighted models for: {}", modelLocation);
            } else if (i == 1) {
               ibakedmodel1 = weightedbakedmodel$builder.first();
            } else {
               ibakedmodel1 = weightedbakedmodel$builder.build();
            }

            return (sc)ibakedmodel1;
         }
      }
   }

   private void bakeItemModels() {
      Iterator var1 = this.itemLocations.entrySet().iterator();

      while(true) {
         while(var1.hasNext()) {
            Map.Entry<String, ResourceLocation> entry = (Map.Entry)var1.next();
            ResourceLocation resourcelocation = (ResourceLocation)entry.getValue();
            sD modelresourcelocation = new sD((String)entry.getKey(), "inventory");
            if (bnK.ForgeHooksClient.exists()) {
               modelresourcelocation = (sD)bnK.call(bnK.ModelLoader_getInventoryVariant, entry.getKey());
            }

            sy modelblock = (sy)this.models.get(resourcelocation);
            if (modelblock != null && modelblock.isResolved()) {
               if (modelblock.getElements().isEmpty()) {
                  LOGGER.warn("Missing elements for: {}", resourcelocation);
               } else if (this.isCustomRenderer(modelblock)) {
                  this.bakedRegistry.putObject(modelresourcelocation, new rU(modelblock.getAllTransforms(), modelblock.createOverrides()));
               } else {
                  sc ibakedmodel = this.bakeModel(modelblock, sE.X0_Y0, false);
                  if (ibakedmodel != null) {
                     this.bakedRegistry.putObject(modelresourcelocation, ibakedmodel);
                  }
               }
            } else {
               LOGGER.warn("Missing model for: {}", resourcelocation);
            }
         }

         return;
      }
   }

   private Set<ResourceLocation> getVariantsTextureLocations() {
      Set<ResourceLocation> set = Sets.newHashSet();
      List<sD> list = Lists.newArrayList(this.variants.keySet());
      Collections.sort(list, new Comparator<sD>() {
         public int compare(sD p_compare_1_, sD p_compare_2_) {
            return p_compare_1_.toString().compareTo(p_compare_2_.toString());
         }

         // $FF: synthetic method
         // $FF: bridge method
         public int compare(Object var1, Object var2) {
            return this.compare((sD)var1, (sD)var2);
         }
      });
      Iterator var3 = list.iterator();

      while(var3.hasNext()) {
         sD modelresourcelocation = (sD)var3.next();
         tg variantlist = (tg)this.variants.get(modelresourcelocation);
         Iterator var6 = variantlist.getVariantList().iterator();

         while(var6.hasNext()) {
            te variant = (te)var6.next();
            sy modelblock = (sy)this.models.get(variant.getModelLocation());
            if (modelblock == null) {
               LOGGER.warn("Missing model for: {}", modelresourcelocation);
            } else {
               set.addAll(this.getTextureLocations(modelblock));
            }
         }
      }

      var3 = this.multipartVariantMap.keySet().iterator();

      while(var3.hasNext()) {
         sB modelblockdefinition = (sB)var3.next();
         Iterator var11 = modelblockdefinition.getMultipartData().getVariants().iterator();

         while(var11.hasNext()) {
            tg variantlist1 = (tg)var11.next();
            Iterator var13 = variantlist1.getVariantList().iterator();

            while(var13.hasNext()) {
               te variant1 = (te)var13.next();
               sy modelblock1 = (sy)this.models.get(variant1.getModelLocation());
               if (modelblock1 == null) {
                  LOGGER.warn("Missing model for: {}", co.REGISTRY.getNameForObject(modelblockdefinition.getMultipartData().getStateContainer().getBlock()));
               } else {
                  set.addAll(this.getTextureLocations(modelblock1));
               }
            }
         }
      }

      set.addAll(LOCATIONS_BUILTIN_TEXTURES);
      return set;
   }

   @Nullable
   public sc bakeModel(sy modelBlockIn, sE modelRotationIn, boolean uvLocked) {
      zd textureatlassprite = (zd)this.sprites.get(new ResourceLocation(modelBlockIn.resolveTextureName("particle")));
      tb simplebakedmodel$builder = (new tb(modelBlockIn, modelBlockIn.createOverrides())).setTexture(textureatlassprite);
      if (modelBlockIn.getElements().isEmpty()) {
         return null;
      } else {
         Iterator var6 = modelBlockIn.getElements().iterator();

         while(var6.hasNext()) {
            rQ blockpart = (rQ)var6.next();
            Iterator var8 = blockpart.mapFaces.keySet().iterator();

            while(var8.hasNext()) {
               EnumFacing enumfacing = (EnumFacing)var8.next();
               rS blockpartface = (rS)blockpart.mapFaces.get(enumfacing);
               zd textureatlassprite1 = (zd)this.sprites.get(new ResourceLocation(modelBlockIn.resolveTextureName(blockpartface.texture)));
               if (blockpartface.cullFace == null) {
                  simplebakedmodel$builder.addGeneralQuad(this.makeBakedQuad(blockpart, blockpartface, textureatlassprite1, enumfacing, modelRotationIn, uvLocked));
               } else {
                  simplebakedmodel$builder.addFaceQuad(modelRotationIn.rotateFace(blockpartface.cullFace), this.makeBakedQuad(blockpart, blockpartface, textureatlassprite1, enumfacing, modelRotationIn, uvLocked));
               }
            }
         }

         return simplebakedmodel$builder.makeBakedModel();
      }
   }

   protected sc bakeModel(sy p_bakeModel_1_, biD p_bakeModel_2_, boolean p_bakeModel_3_) {
      zd textureatlassprite = (zd)this.sprites.get(new ResourceLocation(p_bakeModel_1_.resolveTextureName("particle")));
      tb simplebakedmodel$builder = (new tb(p_bakeModel_1_, p_bakeModel_1_.createOverrides())).setTexture(textureatlassprite);
      if (p_bakeModel_1_.getElements().isEmpty()) {
         return null;
      } else {
         Iterator var6 = p_bakeModel_1_.getElements().iterator();

         label36:
         while(var6.hasNext()) {
            rQ blockpart = (rQ)var6.next();
            Iterator var8 = blockpart.mapFaces.keySet().iterator();

            while(true) {
               while(true) {
                  if (!var8.hasNext()) {
                     continue label36;
                  }

                  EnumFacing enumfacing = (EnumFacing)var8.next();
                  rS blockpartface = (rS)blockpart.mapFaces.get(enumfacing);
                  zd textureatlassprite1 = (zd)this.sprites.get(new ResourceLocation(p_bakeModel_1_.resolveTextureName(blockpartface.texture)));
                  boolean flag = true;
                  if (bnK.ForgeHooksClient.exists()) {
                     flag = biE.isInteger(p_bakeModel_2_.getMatrix());
                  }

                  if (blockpartface.cullFace != null && flag) {
                     simplebakedmodel$builder.addFaceQuad(p_bakeModel_2_.rotate(blockpartface.cullFace), this.makeBakedQuad(blockpart, blockpartface, textureatlassprite1, enumfacing, p_bakeModel_2_, p_bakeModel_3_));
                  } else {
                     simplebakedmodel$builder.addGeneralQuad(this.makeBakedQuad(blockpart, blockpartface, textureatlassprite1, enumfacing, p_bakeModel_2_, p_bakeModel_3_));
                  }
               }
            }
         }

         return simplebakedmodel$builder.makeBakedModel();
      }
   }

   private rK makeBakedQuad(rQ blockPartt, rS blockPartFaceIn, zd sprite, EnumFacing face, sE transform, boolean uvLocked) {
      return bnK.ForgeHooksClient.exists() ? this.makeBakedQuad(blockPartt, blockPartFaceIn, sprite, face, transform, uvLocked) : this.faceBakery.makeBakedQuad(blockPartt.positionFrom, blockPartt.positionTo, blockPartFaceIn, sprite, face, transform, blockPartt.partRotation, uvLocked, blockPartt.shade);
   }

   protected rK makeBakedQuad(rQ p_makeBakedQuad_1_, rS p_makeBakedQuad_2_, zd p_makeBakedQuad_3_, EnumFacing p_makeBakedQuad_4_, biD p_makeBakedQuad_5_, boolean p_makeBakedQuad_6_) {
      return this.faceBakery.makeBakedQuad(p_makeBakedQuad_1_.positionFrom, p_makeBakedQuad_1_.positionTo, p_makeBakedQuad_2_, p_makeBakedQuad_3_, p_makeBakedQuad_4_, p_makeBakedQuad_5_, p_makeBakedQuad_1_.partRotation, p_makeBakedQuad_6_, p_makeBakedQuad_1_.shade);
   }

   private void loadModelsCheck() {
      this.loadModels();
      Iterator var1 = this.models.values().iterator();

      while(var1.hasNext()) {
         sy modelblock = (sy)var1.next();
         modelblock.getParentFromMap(this.models);
      }

      sy.checkModelHierarchy(this.models);
   }

   private void loadModels() {
      Deque<ResourceLocation> deque = Queues.newArrayDeque();
      Set<ResourceLocation> set = Sets.newHashSet();
      Iterator var3 = this.models.keySet().iterator();

      while(var3.hasNext()) {
         ResourceLocation resourcelocation = (ResourceLocation)var3.next();
         set.add(resourcelocation);
         this.addModelParentLocation(deque, set, (sy)this.models.get(resourcelocation));
      }

      while(!deque.isEmpty()) {
         ResourceLocation resourcelocation1 = (ResourceLocation)deque.pop();

         try {
            if (this.models.get(resourcelocation1) != null) {
               continue;
            }

            sy modelblock = this.loadModel(resourcelocation1);
            this.models.put(resourcelocation1, modelblock);
            this.addModelParentLocation(deque, set, modelblock);
         } catch (Exception var5) {
            LOGGER.warn("In parent chain: {}; unable to load model: '{}'", JOINER.join(this.getParentPath(resourcelocation1)), resourcelocation1);
         }

         set.add(resourcelocation1);
      }

   }

   private void addModelParentLocation(Deque<ResourceLocation> p_188633_1_, Set<ResourceLocation> p_188633_2_, sy p_188633_3_) {
      ResourceLocation resourcelocation = p_188633_3_.getParentLocation();
      if (resourcelocation != null && !p_188633_2_.contains(resourcelocation)) {
         p_188633_1_.add(resourcelocation);
      }

   }

   private List<ResourceLocation> getParentPath(ResourceLocation p_177573_1_) {
      List<ResourceLocation> list = Lists.newArrayList(new ResourceLocation[]{p_177573_1_});
      ResourceLocation resourcelocation = p_177573_1_;

      while((resourcelocation = this.getParentLocation(resourcelocation)) != null) {
         list.add(0, resourcelocation);
      }

      return list;
   }

   @Nullable
   private ResourceLocation getParentLocation(ResourceLocation p_177576_1_) {
      Iterator var2 = this.models.entrySet().iterator();

      Map.Entry entry;
      sy modelblock;
      do {
         if (!var2.hasNext()) {
            return null;
         }

         entry = (Map.Entry)var2.next();
         modelblock = (sy)entry.getValue();
      } while(modelblock == null || !p_177576_1_.equals(modelblock.getParentLocation()));

      return (ResourceLocation)entry.getKey();
   }

   private Set<ResourceLocation> getTextureLocations(sy p_177585_1_) {
      Set<ResourceLocation> set = Sets.newHashSet();
      Iterator var3 = p_177585_1_.getElements().iterator();

      while(var3.hasNext()) {
         rQ blockpart = (rQ)var3.next();
         Iterator var5 = blockpart.mapFaces.values().iterator();

         while(var5.hasNext()) {
            rS blockpartface = (rS)var5.next();
            ResourceLocation resourcelocation = new ResourceLocation(p_177585_1_.resolveTextureName(blockpartface.texture));
            set.add(resourcelocation);
         }
      }

      set.add(new ResourceLocation(p_177585_1_.resolveTextureName("particle")));
      return set;
   }

   private void loadSprites() {
      final Set<ResourceLocation> set = this.getVariantsTextureLocations();
      set.addAll(this.getItemsTextureLocations());
      set.remove(zj.LOCATION_MISSING_TEXTURE);
      yQ itexturemappopulator = new yQ() {
         public void registerSprites(zj textureMapIn) {
            Iterator var2 = set.iterator();

            while(var2.hasNext()) {
               ResourceLocation resourcelocation = (ResourceLocation)var2.next();
               zd textureatlassprite = textureMapIn.registerSprite(resourcelocation);
               st.this.sprites.put(resourcelocation, textureatlassprite);
            }

         }
      };
      this.textureMap.loadSprites(this.resourceManager, itexturemappopulator);
      this.sprites.put(new ResourceLocation("missingno"), this.textureMap.getMissingSprite());
   }

   private Set<ResourceLocation> getItemsTextureLocations() {
      Set<ResourceLocation> set = Sets.newHashSet();
      Iterator var2 = this.itemLocations.values().iterator();

      while(true) {
         while(true) {
            sy modelblock;
            do {
               if (!var2.hasNext()) {
                  return set;
               }

               ResourceLocation resourcelocation = (ResourceLocation)var2.next();
               modelblock = (sy)this.models.get(resourcelocation);
            } while(modelblock == null);

            set.add(new ResourceLocation(modelblock.resolveTextureName("particle")));
            Iterator var5;
            if (this.hasItemModel(modelblock)) {
               var5 = sk.LAYERS.iterator();

               while(var5.hasNext()) {
                  String s = (String)var5.next();
                  set.add(new ResourceLocation(modelblock.resolveTextureName(s)));
               }
            } else if (!this.isCustomRenderer(modelblock)) {
               var5 = modelblock.getElements().iterator();

               while(var5.hasNext()) {
                  rQ blockpart = (rQ)var5.next();
                  Iterator var7 = blockpart.mapFaces.values().iterator();

                  while(var7.hasNext()) {
                     rS blockpartface = (rS)var7.next();
                     ResourceLocation resourcelocation1 = new ResourceLocation(modelblock.resolveTextureName(blockpartface.texture));
                     set.add(resourcelocation1);
                  }
               }
            }
         }
      }
   }

   private boolean hasItemModel(@Nullable sy p_177581_1_) {
      if (p_177581_1_ == null) {
         return false;
      } else {
         return p_177581_1_.getRootModel() == MODEL_GENERATED;
      }
   }

   private boolean isCustomRenderer(@Nullable sy p_177587_1_) {
      if (p_177587_1_ == null) {
         return false;
      } else {
         sy modelblock = p_177587_1_.getRootModel();
         return modelblock == MODEL_ENTITY;
      }
   }

   private void makeItemModels() {
      Iterator var1 = this.itemLocations.values().iterator();

      while(var1.hasNext()) {
         ResourceLocation resourcelocation = (ResourceLocation)var1.next();
         sy modelblock = (sy)this.models.get(resourcelocation);
         if (this.hasItemModel(modelblock)) {
            sy modelblock1 = this.makeItemModel(modelblock);
            if (modelblock1 != null) {
               modelblock1.name = resourcelocation.toString();
            }

            this.models.put(resourcelocation, modelblock1);
         } else if (this.isCustomRenderer(modelblock)) {
            this.models.put(resourcelocation, modelblock);
         }
      }

      var1 = this.sprites.values().iterator();

      while(var1.hasNext()) {
         zd textureatlassprite = (zd)var1.next();
         if (!textureatlassprite.hasAnimationMetadata()) {
            textureatlassprite.clearFramesTextureData();
         }
      }

   }

   private sy makeItemModel(sy p_177582_1_) {
      return this.itemModelGenerator.makeItemModel(this.textureMap, p_177582_1_);
   }

   public sy getModelBlock(ResourceLocation p_getModelBlock_1_) {
      sy modelblock = (sy)this.models.get(p_getModelBlock_1_);
      return modelblock;
   }

   public static void fixModelLocations(sy p_fixModelLocations_0_, String p_fixModelLocations_1_) {
      ResourceLocation resourcelocation = fixModelLocation(p_fixModelLocations_0_.parentLocation, p_fixModelLocations_1_);
      if (resourcelocation != p_fixModelLocations_0_.parentLocation) {
         p_fixModelLocations_0_.parentLocation = resourcelocation;
      }

      if (p_fixModelLocations_0_.textures != null) {
         Iterator var3 = p_fixModelLocations_0_.textures.entrySet().iterator();

         while(var3.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry)var3.next();
            String s = (String)entry.getValue();
            String s1 = fixResourcePath(s, p_fixModelLocations_1_);
            if (s1 != s) {
               entry.setValue(s1);
            }
         }
      }

   }

   public static ResourceLocation fixModelLocation(ResourceLocation p_fixModelLocation_0_, String p_fixModelLocation_1_) {
      if (p_fixModelLocation_0_ != null && p_fixModelLocation_1_ != null) {
         if (!p_fixModelLocation_0_.getNamespace().equals("minecraft")) {
            return p_fixModelLocation_0_;
         } else {
            String s = p_fixModelLocation_0_.getPath();
            String s1 = fixResourcePath(s, p_fixModelLocation_1_);
            if (s1 != s) {
               p_fixModelLocation_0_ = new ResourceLocation(p_fixModelLocation_0_.getNamespace(), s1);
            }

            return p_fixModelLocation_0_;
         }
      } else {
         return p_fixModelLocation_0_;
      }
   }

   private static String fixResourcePath(String p_fixResourcePath_0_, String p_fixResourcePath_1_) {
      p_fixResourcePath_0_ = bqS.fixResourcePath(p_fixResourcePath_0_, p_fixResourcePath_1_);
      p_fixResourcePath_0_ = bqP.removeSuffix(p_fixResourcePath_0_, ".json");
      p_fixResourcePath_0_ = bqP.removeSuffix(p_fixResourcePath_0_, ".png");
      return p_fixResourcePath_0_;
   }

   protected void registerMultipartVariant(sB p_registerMultipartVariant_1_, Collection<sD> p_registerMultipartVariant_2_) {
      this.multipartVariantMap.put(p_registerMultipartVariant_1_, p_registerMultipartVariant_2_);
   }

   public static void registerItemVariants(OL p_registerItemVariants_0_, ResourceLocation... p_registerItemVariants_1_) {
      biH iregistrydelegate = (biH)bnK.getFieldValue(p_registerItemVariants_0_, bnK.ForgeItem_delegate);
      if (!customVariantNames.containsKey(iregistrydelegate)) {
         customVariantNames.put(iregistrydelegate, Sets.newHashSet());
      }

      ResourceLocation[] var3 = p_registerItemVariants_1_;
      int var4 = p_registerItemVariants_1_.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         ResourceLocation resourcelocation = var3[var5];
         ((Set)customVariantNames.get(iregistrydelegate)).add(resourcelocation.toString());
      }

   }

   static {
      MODEL_GENERATED = sy.deserialize(EMPTY_MODEL_RAW);
      MODEL_ENTITY = sy.deserialize(EMPTY_MODEL_RAW);
      customVariantNames = Maps.newHashMap();
      BUILT_IN_MODELS.put("missing", MISSING_MODEL_MESH);
      MODEL_GENERATED.name = "generation marker";
      MODEL_ENTITY.name = "block entity marker";
   }
}
