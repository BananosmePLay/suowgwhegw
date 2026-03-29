package neo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class bjC {
   private String fileName = null;
   private String basePath = null;
   private bjA container = null;
   private Map<ResourceLocation, ResourceLocation> textureLocations = null;
   private bjb nbtName = null;
   private Zi[] biomes = null;
   private bje heights = null;
   private Boolean large = null;
   private Boolean trapped = null;
   private Boolean christmas = null;
   private Boolean ender = null;
   private bje levels = null;
   private bjf[] professions = null;
   private bjB[] variants = null;
   private Om[] colors = null;
   private static final bjB[] VARIANTS_HORSE;
   private static final bjB[] VARIANTS_DISPENSER;
   private static final bjB[] VARIANTS_INVALID;
   private static final Om[] COLORS_INVALID;
   private static final ResourceLocation ANVIL_GUI_TEXTURE;
   private static final ResourceLocation BEACON_GUI_TEXTURE;
   private static final ResourceLocation BREWING_STAND_GUI_TEXTURE;
   private static final ResourceLocation CHEST_GUI_TEXTURE;
   private static final ResourceLocation CRAFTING_TABLE_GUI_TEXTURE;
   private static final ResourceLocation HORSE_GUI_TEXTURE;
   private static final ResourceLocation DISPENSER_GUI_TEXTURE;
   private static final ResourceLocation ENCHANTMENT_TABLE_GUI_TEXTURE;
   private static final ResourceLocation FURNACE_GUI_TEXTURE;
   private static final ResourceLocation HOPPER_GUI_TEXTURE;
   private static final ResourceLocation INVENTORY_GUI_TEXTURE;
   private static final ResourceLocation SHULKER_BOX_GUI_TEXTURE;
   private static final ResourceLocation VILLAGER_GUI_TEXTURE;

   public bjC(Properties props, String path) {
      biS connectedparser = new biS("CustomGuis");
      this.fileName = connectedparser.parseName(path);
      this.basePath = connectedparser.parseBasePath(path);
      this.container = (bjA)connectedparser.parseEnum(props.getProperty("container"), bjA.values(), "container");
      this.textureLocations = parseTextureLocations(props, "texture", this.container, "textures/gui/", this.basePath);
      this.nbtName = connectedparser.parseNbtTagValue("name", props.getProperty("name"));
      this.biomes = connectedparser.parseBiomes(props.getProperty("biomes"));
      this.heights = connectedparser.parseRangeListInt(props.getProperty("heights"));
      this.large = connectedparser.parseBooleanObject(props.getProperty("large"));
      this.trapped = connectedparser.parseBooleanObject(props.getProperty("trapped"));
      this.christmas = connectedparser.parseBooleanObject(props.getProperty("christmas"));
      this.ender = connectedparser.parseBooleanObject(props.getProperty("ender"));
      this.levels = connectedparser.parseRangeListInt(props.getProperty("levels"));
      this.professions = connectedparser.parseProfessions(props.getProperty("professions"));
      bjB[] acustomguiproperties$enumvariant = getContainerVariants(this.container);
      this.variants = (bjB[])((bjB[])connectedparser.parseEnums(props.getProperty("variants"), acustomguiproperties$enumvariant, "variants", VARIANTS_INVALID));
      this.colors = parseEnumDyeColors(props.getProperty("colors"));
   }

   private static bjB[] getContainerVariants(bjA cont) {
      if (cont == bjA.HORSE) {
         return VARIANTS_HORSE;
      } else {
         return cont == bjA.DISPENSER ? VARIANTS_DISPENSER : new bjB[0];
      }
   }

   private static Om[] parseEnumDyeColors(String str) {
      if (str == null) {
         return null;
      } else {
         str = str.toLowerCase();
         String[] astring = XH.tokenize(str, " ");
         Om[] aenumdyecolor = new Om[astring.length];

         for(int i = 0; i < astring.length; ++i) {
            String s = astring[i];
            Om enumdyecolor = parseEnumDyeColor(s);
            if (enumdyecolor == null) {
               warn("Invalid color: " + s);
               return COLORS_INVALID;
            }

            aenumdyecolor[i] = enumdyecolor;
         }

         return aenumdyecolor;
      }
   }

   private static Om parseEnumDyeColor(String str) {
      if (str == null) {
         return null;
      } else {
         Om[] aenumdyecolor = Om.values();

         for(int i = 0; i < aenumdyecolor.length; ++i) {
            Om enumdyecolor = aenumdyecolor[i];
            if (enumdyecolor.getName().equals(str)) {
               return enumdyecolor;
            }

            if (enumdyecolor.getTranslationKey().equals(str)) {
               return enumdyecolor;
            }
         }

         return null;
      }
   }

   private static ResourceLocation parseTextureLocation(String str, String basePath) {
      if (str == null) {
         return null;
      } else {
         str = str.trim();
         String s = bqS.fixResourcePath(str, basePath);
         if (!s.endsWith(".png")) {
            s = s + ".png";
         }

         return new ResourceLocation(basePath + "/" + s);
      }
   }

   private static Map<ResourceLocation, ResourceLocation> parseTextureLocations(Properties props, String property, bjA container, String pathPrefix, String basePath) {
      Map<ResourceLocation, ResourceLocation> map = new HashMap();
      String s = props.getProperty(property);
      if (s != null) {
         ResourceLocation resourcelocation = getGuiTextureLocation(container);
         ResourceLocation resourcelocation1 = parseTextureLocation(s, basePath);
         if (resourcelocation != null && resourcelocation1 != null) {
            map.put(resourcelocation, resourcelocation1);
         }
      }

      String s5 = property + ".";
      Iterator var17 = props.keySet().iterator();

      while(var17.hasNext()) {
         Object s11 = var17.next();
         String s1 = (String)s11;
         if (s1.startsWith(s5)) {
            String s2 = s1.substring(s5.length());
            s2 = s2.replace('\\', '/');
            s2 = bqP.removePrefixSuffix(s2, "/", ".png");
            String s3 = pathPrefix + s2 + ".png";
            String s4 = props.getProperty(s1);
            ResourceLocation resourcelocation2 = new ResourceLocation(s3);
            ResourceLocation resourcelocation3 = parseTextureLocation(s4, basePath);
            map.put(resourcelocation2, resourcelocation3);
         }
      }

      return map;
   }

   private static ResourceLocation getGuiTextureLocation(bjA container) {
      if (container == null) {
         return null;
      } else {
         switch (container) {
            case ANVIL:
               return ANVIL_GUI_TEXTURE;
            case BEACON:
               return BEACON_GUI_TEXTURE;
            case BREWING_STAND:
               return BREWING_STAND_GUI_TEXTURE;
            case CHEST:
               return CHEST_GUI_TEXTURE;
            case CRAFTING:
               return CRAFTING_TABLE_GUI_TEXTURE;
            case CREATIVE:
               return null;
            case DISPENSER:
               return DISPENSER_GUI_TEXTURE;
            case ENCHANTMENT:
               return ENCHANTMENT_TABLE_GUI_TEXTURE;
            case FURNACE:
               return FURNACE_GUI_TEXTURE;
            case HOPPER:
               return HOPPER_GUI_TEXTURE;
            case HORSE:
               return HORSE_GUI_TEXTURE;
            case INVENTORY:
               return INVENTORY_GUI_TEXTURE;
            case SHULKER_BOX:
               return SHULKER_BOX_GUI_TEXTURE;
            case VILLAGER:
               return VILLAGER_GUI_TEXTURE;
            default:
               return null;
         }
      }
   }

   public boolean isValid(String path) {
      if (this.fileName != null && this.fileName.length() > 0) {
         if (this.basePath == null) {
            warn("No base path found: " + path);
            return false;
         } else if (this.container == null) {
            warn("No container found: " + path);
            return false;
         } else if (this.textureLocations.isEmpty()) {
            warn("No texture found: " + path);
            return false;
         } else if (this.professions == biS.PROFESSIONS_INVALID) {
            warn("Invalid professions or careers: " + path);
            return false;
         } else if (this.variants == VARIANTS_INVALID) {
            warn("Invalid variants: " + path);
            return false;
         } else if (this.colors == COLORS_INVALID) {
            warn("Invalid colors: " + path);
            return false;
         } else {
            return true;
         }
      } else {
         warn("No name found: " + path);
         return false;
      }
   }

   private static void warn(String str) {
      XH.warn("[CustomGuis] " + str);
   }

   private boolean matchesGeneral(bjA ec, BlockPos pos, bfZ blockAccess) {
      if (this.container != ec) {
         return false;
      } else {
         if (this.biomes != null) {
            Zi biome = blockAccess.getBiome(pos);
            if (!bja.biome(biome, this.biomes)) {
               return false;
            }
         }

         return this.heights == null || this.heights.isInRange(pos.getY());
      }
   }

   public boolean matchesPos(bjA ec, BlockPos pos, bfZ blockAccess, lg screen) {
      if (!this.matchesGeneral(ec, pos, blockAccess)) {
         return false;
      } else {
         if (this.nbtName != null) {
            String s = getName(screen);
            if (!this.nbtName.matchesValue(s)) {
               return false;
            }
         }

         switch (ec) {
            case BEACON:
               return this.matchesBeacon(pos, blockAccess);
            case CHEST:
               return this.matchesChest(pos, blockAccess);
            case DISPENSER:
               return this.matchesDispenser(pos, blockAccess);
            case SHULKER_BOX:
               return this.matchesShulker(pos, blockAccess);
            default:
               return true;
         }
      }
   }

   public static String getName(lg screen) {
      bgd iworldnameable = getWorldNameable(screen);
      return iworldnameable == null ? null : iworldnameable.getDisplayName().getUnformattedText();
   }

   private static bgd getWorldNameable(lg screen) {
      if (screen instanceof lR) {
         return getWorldNameable(screen, bnK.GuiBeacon_tileBeacon);
      } else if (screen instanceof lS) {
         return getWorldNameable(screen, bnK.GuiBrewingStand_tileBrewingStand);
      } else if (screen instanceof lT) {
         return getWorldNameable(screen, bnK.GuiChest_lowerChestInventory);
      } else if (screen instanceof ma) {
         return ((ma)screen).dispenserInventory;
      } else if (screen instanceof kf) {
         return getWorldNameable(screen, bnK.GuiEnchantment_nameable);
      } else if (screen instanceof mg) {
         return getWorldNameable(screen, bnK.GuiFurnace_tileFurnace);
      } else if (screen instanceof kl) {
         return getWorldNameable(screen, bnK.GuiHopper_hopperInventory);
      } else {
         return screen instanceof mj ? getWorldNameable(screen, bnK.GuiShulkerBox_inventory) : null;
      }
   }

   private static bgd getWorldNameable(lg screen, bnN fieldInventory) {
      Object object = bnK.getFieldValue(screen, fieldInventory);
      return !(object instanceof bgd) ? null : (bgd)object;
   }

   private boolean matchesBeacon(BlockPos pos, bfZ blockAccess) {
      Yg tileentity = blockAccess.getTileEntity(pos);
      if (!(tileentity instanceof Yj)) {
         return false;
      } else {
         Yj tileentitybeacon = (Yj)tileentity;
         if (this.levels != null) {
            int i = tileentitybeacon.getLevels();
            if (!this.levels.isInRange(i)) {
               return false;
            }
         }

         return true;
      }
   }

   private boolean matchesChest(BlockPos pos, bfZ blockAccess) {
      Yg tileentity = blockAccess.getTileEntity(pos);
      if (tileentity instanceof Yn) {
         Yn tileentitychest = (Yn)tileentity;
         return this.matchesChest(tileentitychest, pos, blockAccess);
      } else if (tileentity instanceof Yw) {
         Yw tileentityenderchest = (Yw)tileentity;
         return this.matchesEnderChest(tileentityenderchest, pos, blockAccess);
      } else {
         return false;
      }
   }

   private boolean matchesChest(Yn tec, BlockPos pos, bfZ blockAccess) {
      boolean flag = tec.adjacentChestXNeg != null || tec.adjacentChestXPos != null || tec.adjacentChestZNeg != null || tec.adjacentChestZPos != null;
      boolean flag1 = tec.getChestType() == cS.TRAP;
      boolean flag2 = bjD.isChristmas;
      boolean flag3 = false;
      return this.matchesChest(flag, flag1, flag2, flag3);
   }

   private boolean matchesEnderChest(Yw teec, BlockPos pos, bfZ blockAccess) {
      return this.matchesChest(false, false, false, true);
   }

   private boolean matchesChest(boolean isLarge, boolean isTrapped, boolean isChristmas, boolean isEnder) {
      if (this.large != null && this.large != isLarge) {
         return false;
      } else if (this.trapped != null && this.trapped != isTrapped) {
         return false;
      } else if (this.christmas != null && this.christmas != isChristmas) {
         return false;
      } else {
         return this.ender == null || this.ender == isEnder;
      }
   }

   private boolean matchesDispenser(BlockPos pos, bfZ blockAccess) {
      Yg tileentity = blockAccess.getTileEntity(pos);
      if (!(tileentity instanceof Yt)) {
         return false;
      } else {
         Yt tileentitydispenser = (Yt)tileentity;
         if (this.variants != null) {
            bjB customguiproperties$enumvariant = this.getDispenserVariant(tileentitydispenser);
            if (!XH.equalsOne(customguiproperties$enumvariant, this.variants)) {
               return false;
            }
         }

         return true;
      }
   }

   private bjB getDispenserVariant(Yt ted) {
      return ted instanceof Yu ? bjB.DROPPER : bjB.DISPENSER;
   }

   private boolean matchesShulker(BlockPos pos, bfZ blockAccess) {
      Yg tileentity = blockAccess.getTileEntity(pos);
      if (!(tileentity instanceof YN)) {
         return false;
      } else {
         YN tileentityshulkerbox = (YN)tileentity;
         if (this.colors != null) {
            Om enumdyecolor = tileentityshulkerbox.getColor();
            if (!XH.equalsOne(enumdyecolor, this.colors)) {
               return false;
            }
         }

         return true;
      }
   }

   public boolean matchesEntity(bjA ec, Ig entity, bfZ blockAccess) {
      if (!this.matchesGeneral(ec, entity.getPosition(), blockAccess)) {
         return false;
      } else {
         if (this.nbtName != null) {
            String s = entity.getName();
            if (!this.nbtName.matchesValue(s)) {
               return false;
            }
         }

         switch (ec) {
            case HORSE:
               return this.matchesHorse(entity, blockAccess);
            case VILLAGER:
               return this.matchesVillager(entity, blockAccess);
            default:
               return true;
         }
      }
   }

   private boolean matchesVillager(Ig entity, bfZ blockAccess) {
      if (!(entity instanceof Mq)) {
         return false;
      } else {
         Mq entityvillager = (Mq)entity;
         if (this.professions != null) {
            int i = entityvillager.getProfession();
            int j = bnK.getFieldValueInt(entityvillager, bnK.EntityVillager_careerId, -1);
            if (j < 0) {
               return false;
            }

            boolean flag = false;

            for(int k = 0; k < this.professions.length; ++k) {
               bjf villagerprofession = this.professions[k];
               if (villagerprofession.matches(i, j)) {
                  flag = true;
                  break;
               }
            }

            if (!flag) {
               return false;
            }
         }

         return true;
      }
   }

   private boolean matchesHorse(Ig entity, bfZ blockAccess) {
      if (!(entity instanceof Lw)) {
         return false;
      } else {
         Lw abstracthorse = (Lw)entity;
         if (this.variants != null) {
            bjB customguiproperties$enumvariant = this.getHorseVariant(abstracthorse);
            if (!XH.equalsOne(customguiproperties$enumvariant, this.variants)) {
               return false;
            }
         }

         if (this.colors != null && abstracthorse instanceof LK) {
            LK entityllama = (LK)abstracthorse;
            Om enumdyecolor = entityllama.getColor();
            if (!XH.equalsOne(enumdyecolor, this.colors)) {
               return false;
            }
         }

         return true;
      }
   }

   private bjB getHorseVariant(Lw entity) {
      if (entity instanceof LF) {
         return bjB.HORSE;
      } else if (entity instanceof LC) {
         return bjB.DONKEY;
      } else if (entity instanceof LM) {
         return bjB.MULE;
      } else {
         return entity instanceof LK ? bjB.LLAMA : null;
      }
   }

   public bjA getContainer() {
      return this.container;
   }

   public ResourceLocation getTextureLocation(ResourceLocation loc) {
      ResourceLocation resourcelocation = (ResourceLocation)this.textureLocations.get(loc);
      return resourcelocation == null ? loc : resourcelocation;
   }

   public String toString() {
      return "name: " + this.fileName + ", container: " + this.container + ", textures: " + this.textureLocations;
   }

   static {
      VARIANTS_HORSE = new bjB[]{bjB.HORSE, bjB.DONKEY, bjB.MULE, bjB.LLAMA};
      VARIANTS_DISPENSER = new bjB[]{bjB.DISPENSER, bjB.DROPPER};
      VARIANTS_INVALID = new bjB[0];
      COLORS_INVALID = new Om[0];
      ANVIL_GUI_TEXTURE = new ResourceLocation("textures/gui/container/anvil.png");
      BEACON_GUI_TEXTURE = new ResourceLocation("textures/gui/container/beacon.png");
      BREWING_STAND_GUI_TEXTURE = new ResourceLocation("textures/gui/container/brewing_stand.png");
      CHEST_GUI_TEXTURE = new ResourceLocation("textures/gui/container/generic_54.png");
      CRAFTING_TABLE_GUI_TEXTURE = new ResourceLocation("textures/gui/container/crafting_table.png");
      HORSE_GUI_TEXTURE = new ResourceLocation("textures/gui/container/horse.png");
      DISPENSER_GUI_TEXTURE = new ResourceLocation("textures/gui/container/dispenser.png");
      ENCHANTMENT_TABLE_GUI_TEXTURE = new ResourceLocation("textures/gui/container/enchanting_table.png");
      FURNACE_GUI_TEXTURE = new ResourceLocation("textures/gui/container/furnace.png");
      HOPPER_GUI_TEXTURE = new ResourceLocation("textures/gui/container/hopper.png");
      INVENTORY_GUI_TEXTURE = new ResourceLocation("textures/gui/container/inventory.png");
      SHULKER_BOX_GUI_TEXTURE = new ResourceLocation("textures/gui/container/shulker_box.png");
      VILLAGER_GUI_TEXTURE = new ResourceLocation("textures/gui/container/villager.png");
   }
}
