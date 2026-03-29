package neo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;

public class bjG {
   private static bjE[][] itemProperties = (bjE[][])((bjE[][])null);
   private static bjE[][] enchantmentProperties = (bjE[][])((bjE[][])null);
   private static Map mapPotionIds = null;
   private static sk itemModelGenerator = new sk();
   private static boolean useGlint = true;
   private static boolean renderOffHand = false;
   public static final int MASK_POTION_SPLASH = 16384;
   public static final int MASK_POTION_NAME = 63;
   public static final int MASK_POTION_EXTENDED = 64;
   public static final String KEY_TEXTURE_OVERLAY = "texture.potion_overlay";
   public static final String KEY_TEXTURE_SPLASH = "texture.potion_bottle_splash";
   public static final String KEY_TEXTURE_DRINKABLE = "texture.potion_bottle_drinkable";
   public static final String DEFAULT_TEXTURE_OVERLAY = "items/potion_overlay";
   public static final String DEFAULT_TEXTURE_SPLASH = "items/potion_bottle_splash";
   public static final String DEFAULT_TEXTURE_DRINKABLE = "items/potion_bottle_drinkable";
   private static final int[][] EMPTY_INT2_ARRAY = new int[0][];
   private static final Map<String, Integer> mapPotionDamages = makeMapPotionDamages();
   private static final String TYPE_POTION_NORMAL = "normal";
   private static final String TYPE_POTION_SPLASH = "splash";
   private static final String TYPE_POTION_LINGER = "linger";

   public bjG() {
   }

   public static void update() {
      itemProperties = (bjE[][])((bjE[][])null);
      enchantmentProperties = (bjE[][])((bjE[][])null);
      useGlint = true;
      if (XH.isCustomItems()) {
         readCitProperties("mcpatcher/cit.properties");
         AC[] airesourcepack = XH.getResourcePacks();

         for(int i = airesourcepack.length - 1; i >= 0; --i) {
            AC iresourcepack = airesourcepack[i];
            update(iresourcepack);
         }

         update(XH.getDefaultResourcePack());
         if (itemProperties.length <= 0) {
            itemProperties = (bjE[][])((bjE[][])null);
         }

         if (enchantmentProperties.length <= 0) {
            enchantmentProperties = (bjE[][])((bjE[][])null);
         }
      }

   }

   private static void readCitProperties(String fileName) {
      try {
         ResourceLocation resourcelocation = new ResourceLocation(fileName);
         InputStream inputstream = XH.getResourceStream(resourcelocation);
         if (inputstream == null) {
            return;
         }

         XH.dbg("CustomItems: Loading " + fileName);
         Properties properties = new bqL();
         ((Properties)properties).load(inputstream);
         inputstream.close();
         useGlint = XH.parseBoolean(((Properties)properties).getProperty("useGlint"), true);
      } catch (FileNotFoundException var4) {
         return;
      } catch (IOException var5) {
         IOException ioexception = var5;
         ioexception.printStackTrace();
      }

   }

   private static void update(AC rp) {
      String[] astring = bqN.collectFiles(rp, "mcpatcher/cit/", ".properties", (String[])null);
      Map map = makeAutoImageProperties(rp);
      if (map.size() > 0) {
         Set set = map.keySet();
         String[] astring1 = (String[])((String[])set.toArray(new String[set.size()]));
         astring = (String[])((String[])XH.addObjectsToArray(astring, astring1));
      }

      Arrays.sort((Object[])astring);
      List list = makePropertyList(itemProperties);
      List list1 = makePropertyList(enchantmentProperties);

      for(int i = 0; i < astring.length; ++i) {
         String s = astring[i];
         XH.dbg("CustomItems: " + s);

         try {
            bjE customitemproperties = null;
            if (map.containsKey(s)) {
               customitemproperties = (bjE)map.get(s);
            }

            if (customitemproperties == null) {
               ResourceLocation resourcelocation = new ResourceLocation(s);
               InputStream inputstream = rp.getInputStream(resourcelocation);
               if (inputstream == null) {
                  XH.warn("CustomItems file not found: " + s);
                  continue;
               }

               Properties properties = new bqL();
               ((Properties)properties).load(inputstream);
               inputstream.close();
               customitemproperties = new bjE(properties, s);
            }

            if (customitemproperties.isValid(s)) {
               addToItemList(customitemproperties, list);
               addToEnchantmentList(customitemproperties, list1);
            }
         } catch (FileNotFoundException var11) {
            XH.warn("CustomItems file not found: " + s);
         } catch (Exception var12) {
            Exception exception = var12;
            exception.printStackTrace();
         }
      }

      itemProperties = propertyListToArray(list);
      enchantmentProperties = propertyListToArray(list1);
      Comparator comparator = getPropertiesComparator();

      int k;
      bjE[] acustomitemproperties1;
      for(k = 0; k < itemProperties.length; ++k) {
         acustomitemproperties1 = itemProperties[k];
         if (acustomitemproperties1 != null) {
            Arrays.sort(acustomitemproperties1, comparator);
         }
      }

      for(k = 0; k < enchantmentProperties.length; ++k) {
         acustomitemproperties1 = enchantmentProperties[k];
         if (acustomitemproperties1 != null) {
            Arrays.sort(acustomitemproperties1, comparator);
         }
      }

   }

   private static Comparator getPropertiesComparator() {
      Comparator comparator = new Comparator() {
         public int compare(Object o1, Object o2) {
            bjE customitemproperties = (bjE)o1;
            bjE customitemproperties1 = (bjE)o2;
            if (customitemproperties.layer != customitemproperties1.layer) {
               return customitemproperties.layer - customitemproperties1.layer;
            } else if (customitemproperties.weight != customitemproperties1.weight) {
               return customitemproperties1.weight - customitemproperties.weight;
            } else {
               return !customitemproperties.basePath.equals(customitemproperties1.basePath) ? customitemproperties.basePath.compareTo(customitemproperties1.basePath) : customitemproperties.name.compareTo(customitemproperties1.name);
            }
         }
      };
      return comparator;
   }

   public static void updateIcons(zj textureMap) {
      Iterator var1 = getAllProperties().iterator();

      while(var1.hasNext()) {
         bjE customitemproperties = (bjE)var1.next();
         customitemproperties.updateIcons(textureMap);
      }

   }

   public static void loadModels(st modelBakery) {
      Iterator var1 = getAllProperties().iterator();

      while(var1.hasNext()) {
         bjE customitemproperties = (bjE)var1.next();
         customitemproperties.loadModels(modelBakery);
      }

   }

   public static void updateModels() {
      Iterator var0 = getAllProperties().iterator();

      while(var0.hasNext()) {
         bjE customitemproperties = (bjE)var0.next();
         if (customitemproperties.type == 1) {
            zj texturemap = nC.getMinecraft().getTextureMapBlocks();
            customitemproperties.updateModelTexture(texturemap, itemModelGenerator);
            customitemproperties.updateModelsFull();
         }
      }

   }

   private static List<bjE> getAllProperties() {
      List<bjE> list = new ArrayList();
      addAll(itemProperties, list);
      addAll(enchantmentProperties, list);
      return list;
   }

   private static void addAll(bjE[][] cipsArr, List<bjE> list) {
      if (cipsArr != null) {
         for(int i = 0; i < cipsArr.length; ++i) {
            bjE[] acustomitemproperties = cipsArr[i];
            if (acustomitemproperties != null) {
               for(int j = 0; j < acustomitemproperties.length; ++j) {
                  bjE customitemproperties = acustomitemproperties[j];
                  if (customitemproperties != null) {
                     list.add(customitemproperties);
                  }
               }
            }
         }
      }

   }

   private static Map makeAutoImageProperties(AC rp) {
      Map map = new HashMap();
      map.putAll(makePotionImageProperties(rp, "normal", OL.getIdFromItem(NK.POTIONITEM)));
      map.putAll(makePotionImageProperties(rp, "splash", OL.getIdFromItem(NK.SPLASH_POTION)));
      map.putAll(makePotionImageProperties(rp, "linger", OL.getIdFromItem(NK.LINGERING_POTION)));
      return map;
   }

   private static Map makePotionImageProperties(AC rp, String type, int itemId) {
      Map map = new HashMap();
      String s = type + "/";
      String[] astring = new String[]{"mcpatcher/cit/potion/" + s, "mcpatcher/cit/Potion/" + s};
      String[] astring1 = new String[]{".png"};
      String[] astring2 = bqN.collectFiles(rp, astring, astring1);

      for(int i = 0; i < astring2.length; ++i) {
         String s1 = astring2[i];
         String name = bqP.removePrefixSuffix(s1, astring, astring1);
         Properties properties = makePotionProperties(name, type, itemId, s1);
         if (properties != null) {
            String s3 = bqP.removeSuffix(s1, astring1) + ".properties";
            bjE customitemproperties = new bjE(properties, s3);
            map.put(s3, customitemproperties);
         }
      }

      return map;
   }

   private static Properties makePotionProperties(String name, String type, int itemId, String path) {
      if (bqP.endsWith(name, new String[]{"_n", "_s"})) {
         return null;
      } else if (name.equals("empty") && type.equals("normal")) {
         itemId = OL.getIdFromItem(NK.GLASS_BOTTLE);
         Properties properties = new bqL();
         ((Properties)properties).put("type", "item");
         ((Properties)properties).put("items", "" + itemId);
         return properties;
      } else {
         int[] aint = (int[])((int[])getMapPotionIds().get(name));
         if (aint == null) {
            XH.warn("Potion not found for image: " + path);
            return null;
         } else {
            StringBuffer stringbuffer = new StringBuffer();

            int i;
            for(i = 0; i < aint.length; ++i) {
               int j = aint[i];
               if (type.equals("splash")) {
                  j |= 16384;
               }

               if (i > 0) {
                  stringbuffer.append(" ");
               }

               stringbuffer.append(j);
            }

            i = 16447;
            if (name.equals("water") || name.equals("mundane")) {
               i |= 64;
            }

            Properties properties1 = new bqL();
            ((Properties)properties1).put("type", "item");
            ((Properties)properties1).put("items", "" + itemId);
            ((Properties)properties1).put("damage", "" + stringbuffer.toString());
            ((Properties)properties1).put("damageMask", "" + i);
            if (type.equals("splash")) {
               ((Properties)properties1).put("texture.potion_bottle_splash", name);
            } else {
               ((Properties)properties1).put("texture.potion_bottle_drinkable", name);
            }

            return properties1;
         }
      }
   }

   private static Map getMapPotionIds() {
      if (mapPotionIds == null) {
         mapPotionIds = new LinkedHashMap();
         mapPotionIds.put("water", getPotionId(0, 0));
         mapPotionIds.put("awkward", getPotionId(0, 1));
         mapPotionIds.put("thick", getPotionId(0, 2));
         mapPotionIds.put("potent", getPotionId(0, 3));
         mapPotionIds.put("regeneration", getPotionIds(1));
         mapPotionIds.put("movespeed", getPotionIds(2));
         mapPotionIds.put("fireresistance", getPotionIds(3));
         mapPotionIds.put("poison", getPotionIds(4));
         mapPotionIds.put("heal", getPotionIds(5));
         mapPotionIds.put("nightvision", getPotionIds(6));
         mapPotionIds.put("clear", getPotionId(7, 0));
         mapPotionIds.put("bungling", getPotionId(7, 1));
         mapPotionIds.put("charming", getPotionId(7, 2));
         mapPotionIds.put("rank", getPotionId(7, 3));
         mapPotionIds.put("weakness", getPotionIds(8));
         mapPotionIds.put("damageboost", getPotionIds(9));
         mapPotionIds.put("moveslowdown", getPotionIds(10));
         mapPotionIds.put("leaping", getPotionIds(11));
         mapPotionIds.put("harm", getPotionIds(12));
         mapPotionIds.put("waterbreathing", getPotionIds(13));
         mapPotionIds.put("invisibility", getPotionIds(14));
         mapPotionIds.put("thin", getPotionId(15, 0));
         mapPotionIds.put("debonair", getPotionId(15, 1));
         mapPotionIds.put("sparkling", getPotionId(15, 2));
         mapPotionIds.put("stinky", getPotionId(15, 3));
         mapPotionIds.put("mundane", getPotionId(0, 4));
         mapPotionIds.put("speed", mapPotionIds.get("movespeed"));
         mapPotionIds.put("fire_resistance", mapPotionIds.get("fireresistance"));
         mapPotionIds.put("instant_health", mapPotionIds.get("heal"));
         mapPotionIds.put("night_vision", mapPotionIds.get("nightvision"));
         mapPotionIds.put("strength", mapPotionIds.get("damageboost"));
         mapPotionIds.put("slowness", mapPotionIds.get("moveslowdown"));
         mapPotionIds.put("instant_damage", mapPotionIds.get("harm"));
         mapPotionIds.put("water_breathing", mapPotionIds.get("waterbreathing"));
      }

      return mapPotionIds;
   }

   private static int[] getPotionIds(int baseId) {
      return new int[]{baseId, baseId + 16, baseId + 32, baseId + 48};
   }

   private static int[] getPotionId(int baseId, int subId) {
      return new int[]{baseId + subId * 16};
   }

   private static int getPotionNameDamage(String name) {
      String s = "effect." + name;
      Iterator var2 = VW.REGISTRY.getKeys().iterator();

      VW potion;
      String s1;
      do {
         if (!var2.hasNext()) {
            return -1;
         }

         ResourceLocation resourcelocation = (ResourceLocation)var2.next();
         potion = (VW)VW.REGISTRY.getObject(resourcelocation);
         s1 = potion.getName();
      } while(!s.equals(s1));

      return VW.getIdFromPotion(potion);
   }

   private static List makePropertyList(bjE[][] propsArr) {
      List list = new ArrayList();
      if (propsArr != null) {
         for(int i = 0; i < propsArr.length; ++i) {
            bjE[] acustomitemproperties = propsArr[i];
            List list1 = null;
            if (acustomitemproperties != null) {
               list1 = new ArrayList(Arrays.asList(acustomitemproperties));
            }

            list.add(list1);
         }
      }

      return list;
   }

   private static bjE[][] propertyListToArray(List list) {
      bjE[][] acustomitemproperties = new bjE[list.size()][];

      for(int i = 0; i < list.size(); ++i) {
         List subList = (List)list.get(i);
         if (subList != null) {
            bjE[] acustomitemproperties1 = (bjE[])((bjE[])subList.toArray(new bjE[subList.size()]));
            Arrays.sort(acustomitemproperties1, new bjH());
            acustomitemproperties[i] = acustomitemproperties1;
         }
      }

      return acustomitemproperties;
   }

   private static void addToItemList(bjE cp, List itemList) {
      if (cp.items != null) {
         for(int i = 0; i < cp.items.length; ++i) {
            int j = cp.items[i];
            if (j <= 0) {
               XH.warn("Invalid item ID: " + j);
            } else {
               addToList(cp, itemList, j);
            }
         }
      }

   }

   private static void addToEnchantmentList(bjE cp, List enchantmentList) {
      if (cp.type == 2 && cp.enchantmentIds != null) {
         for(int i = 0; i < 256; ++i) {
            if (cp.enchantmentIds.isInRange(i)) {
               addToList(cp, enchantmentList, i);
            }
         }
      }

   }

   private static void addToList(bjE cp, List list, int id) {
      while(id >= list.size()) {
         list.add((Object)null);
      }

      ArrayList<bjE> subList = (ArrayList)list.get(id);
      if (subList == null) {
         subList = new ArrayList();
         list.set(id, subList);
      }

      subList.add(cp);
   }

   public static sc getCustomItemModel(Qy itemStack, sc model, ResourceLocation modelLocation, boolean fullModel) {
      if (!fullModel && model.isGui3d()) {
         return model;
      } else if (itemProperties == null) {
         return model;
      } else {
         bjE customitemproperties = getCustomItemProperties(itemStack, 1);
         if (customitemproperties == null) {
            return model;
         } else {
            sc ibakedmodel = customitemproperties.getBakedModel(modelLocation, fullModel);
            return ibakedmodel != null ? ibakedmodel : model;
         }
      }
   }

   public static boolean bindCustomArmorTexture(Qy itemStack, EntityEquipmentSlot slot, String overlay) {
      if (itemProperties == null) {
         return false;
      } else {
         ResourceLocation resourcelocation = getCustomArmorLocation(itemStack, slot, overlay);
         if (resourcelocation == null) {
            return false;
         } else {
            XH.getTextureManager().bindTexture(resourcelocation);
            return true;
         }
      }
   }

   private static ResourceLocation getCustomArmorLocation(Qy itemStack, EntityEquipmentSlot slot, String overlay) {
      bjE customitemproperties = getCustomItemProperties(itemStack, 3);
      if (customitemproperties == null) {
         return null;
      } else if (customitemproperties.mapTextureLocations == null) {
         return customitemproperties.textureLocation;
      } else {
         OL item = itemStack.getItem();
         if (!(item instanceof OR)) {
            return null;
         } else {
            OR itemarmor = (OR)item;
            String s = itemarmor.getArmorMaterial().getName();
            int i = slot == EntityEquipmentSlot.LEGS ? 2 : 1;
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("texture.");
            stringbuffer.append(s);
            stringbuffer.append("_layer_");
            stringbuffer.append(i);
            if (overlay != null) {
               stringbuffer.append("_");
               stringbuffer.append(overlay);
            }

            String s1 = stringbuffer.toString();
            ResourceLocation resourcelocation = (ResourceLocation)customitemproperties.mapTextureLocations.get(s1);
            return resourcelocation == null ? customitemproperties.textureLocation : resourcelocation;
         }
      }
   }

   public static ResourceLocation getCustomElytraTexture(Qy itemStack, ResourceLocation locElytra) {
      if (itemProperties == null) {
         return locElytra;
      } else {
         bjE customitemproperties = getCustomItemProperties(itemStack, 4);
         if (customitemproperties == null) {
            return locElytra;
         } else {
            return customitemproperties.textureLocation == null ? locElytra : customitemproperties.textureLocation;
         }
      }
   }

   private static bjE getCustomItemProperties(Qy itemStack, int type) {
      if (itemProperties == null) {
         return null;
      } else if (itemStack == null) {
         return null;
      } else {
         OL item = itemStack.getItem();
         int i = OL.getIdFromItem(item);
         if (i >= 0 && i < itemProperties.length) {
            bjE[] acustomitemproperties = itemProperties[i];
            if (acustomitemproperties != null) {
               for(int j = 0; j < acustomitemproperties.length; ++j) {
                  bjE customitemproperties = acustomitemproperties[j];
                  if (customitemproperties.type == type && matchesProperties(customitemproperties, itemStack, (int[][])((int[][])null))) {
                     return customitemproperties;
                  }
               }
            }
         }

         return null;
      }
   }

   private static boolean matchesProperties(bjE cip, Qy itemStack, int[][] enchantmentIdLevels) {
      OL item = itemStack.getItem();
      if (cip.damage != null) {
         int i = getItemStackDamage(itemStack);
         if (i < 0) {
            return false;
         }

         if (cip.damageMask != 0) {
            i &= cip.damageMask;
         }

         if (cip.damagePercent) {
            int j = item.getMaxDamage();
            i = (int)((double)(i * 100) / (double)j);
         }

         if (!cip.damage.isInRange(i)) {
            return false;
         }
      }

      if (cip.stackSize != null && !cip.stackSize.isInRange(itemStack.getCount())) {
         return false;
      } else {
         int[][] aint = enchantmentIdLevels;
         int j1;
         int k1;
         boolean flag1;
         if (cip.enchantmentIds != null) {
            if (enchantmentIdLevels == null) {
               aint = getEnchantmentIdLevels(itemStack);
            }

            flag1 = false;

            for(j1 = 0; j1 < aint.length; ++j1) {
               k1 = aint[j1][0];
               if (cip.enchantmentIds.isInRange(k1)) {
                  flag1 = true;
                  break;
               }
            }

            if (!flag1) {
               return false;
            }
         }

         if (cip.enchantmentLevels != null) {
            if (aint == null) {
               aint = getEnchantmentIdLevels(itemStack);
            }

            flag1 = false;

            for(j1 = 0; j1 < aint.length; ++j1) {
               k1 = aint[j1][1];
               if (cip.enchantmentLevels.isInRange(k1)) {
                  flag1 = true;
                  break;
               }
            }

            if (!flag1) {
               return false;
            }
         }

         if (cip.nbtTagValues != null) {
            QQ nbttagcompound = itemStack.getTagCompound();

            for(j1 = 0; j1 < cip.nbtTagValues.length; ++j1) {
               bjb nbttagvalue = cip.nbtTagValues[j1];
               if (!nbttagvalue.matches(nbttagcompound)) {
                  return false;
               }
            }
         }

         if (cip.hand != 0) {
            if (cip.hand == 1 && renderOffHand) {
               return false;
            }

            if (cip.hand == 2 && !renderOffHand) {
               return false;
            }
         }

         return true;
      }
   }

   private static int getItemStackDamage(Qy itemStack) {
      OL item = itemStack.getItem();
      return item instanceof Qe ? getPotionDamage(itemStack) : itemStack.getItemDamage();
   }

   private static int getPotionDamage(Qy itemStack) {
      QQ nbttagcompound = itemStack.getTagCompound();
      if (nbttagcompound == null) {
         return 0;
      } else {
         String s = nbttagcompound.getString("Potion");
         if (s != null && !s.equals("")) {
            Integer integer = (Integer)mapPotionDamages.get(s);
            if (integer == null) {
               return -1;
            } else {
               int i = integer;
               if (itemStack.getItem() == NK.SPLASH_POTION) {
                  i |= 16384;
               }

               return i;
            }
         } else {
            return 0;
         }
      }
   }

   private static Map<String, Integer> makeMapPotionDamages() {
      Map<String, Integer> map = new HashMap();
      addPotion("water", 0, false, map);
      addPotion("awkward", 16, false, map);
      addPotion("thick", 32, false, map);
      addPotion("mundane", 64, false, map);
      addPotion("regeneration", 1, true, map);
      addPotion("swiftness", 2, true, map);
      addPotion("fire_resistance", 3, true, map);
      addPotion("poison", 4, true, map);
      addPotion("healing", 5, true, map);
      addPotion("night_vision", 6, true, map);
      addPotion("weakness", 8, true, map);
      addPotion("strength", 9, true, map);
      addPotion("slowness", 10, true, map);
      addPotion("leaping", 11, true, map);
      addPotion("harming", 12, true, map);
      addPotion("water_breathing", 13, true, map);
      addPotion("invisibility", 14, true, map);
      return map;
   }

   private static void addPotion(String name, int value, boolean extended, Map<String, Integer> map) {
      if (extended) {
         value |= 8192;
      }

      map.put("minecraft:" + name, value);
      if (extended) {
         int i = value | 32;
         map.put("minecraft:strong_" + name, i);
         int j = value | 64;
         map.put("minecraft:long_" + name, j);
      }

   }

   private static int[][] getEnchantmentIdLevels(Qy itemStack) {
      OL item = itemStack.getItem();
      QW nbttaglist1;
      if (item == NK.ENCHANTED_BOOK) {
         Pv itemenchantedbook = (Pv)NK.ENCHANTED_BOOK;
         nbttaglist1 = Pv.getEnchantments(itemStack);
      } else {
         nbttaglist1 = itemStack.getEnchantmentTagList();
      }

      QW nbttaglist = nbttaglist1;
      if (nbttaglist1 != null && nbttaglist1.tagCount() > 0) {
         int[][] aint = new int[nbttaglist1.tagCount()][2];

         for(int i = 0; i < nbttaglist.tagCount(); ++i) {
            QQ nbttagcompound = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound.getShort("id");
            int k = nbttagcompound.getShort("lvl");
            aint[i][0] = j;
            aint[i][1] = k;
         }

         return aint;
      } else {
         return EMPTY_INT2_ARRAY;
      }
   }

   public static boolean renderCustomEffect(yK renderItem, Qy itemStack, sc model) {
      if (enchantmentProperties == null) {
         return false;
      } else if (itemStack == null) {
         return false;
      } else {
         int[][] aint = getEnchantmentIdLevels(itemStack);
         if (aint.length <= 0) {
            return false;
         } else {
            Set set = null;
            boolean flag = false;
            zf texturemanager = XH.getTextureManager();

            for(int i = 0; i < aint.length; ++i) {
               int j = aint[i][0];
               if (j >= 0 && j < enchantmentProperties.length) {
                  bjE[] acustomitemproperties = enchantmentProperties[j];
                  if (acustomitemproperties != null) {
                     for(int k = 0; k < acustomitemproperties.length; ++k) {
                        bjE customitemproperties = acustomitemproperties[k];
                        if (set == null) {
                           set = new HashSet();
                        }

                        if (set.add(j) && matchesProperties(customitemproperties, itemStack, aint) && customitemproperties.textureLocation != null) {
                           texturemanager.bindTexture(customitemproperties.textureLocation);
                           float f = customitemproperties.getTextureWidth(texturemanager);
                           if (!flag) {
                              flag = true;
                              yh.depthMask(false);
                              yh.depthFunc(514);
                              yh.disableLighting();
                              yh.matrixMode(5890);
                           }

                           bnV.setupBlend(customitemproperties.blend, 1.0F);
                           yh.pushMatrix();
                           yh.scale(f / 2.0F, f / 2.0F, f / 2.0F);
                           float f1 = customitemproperties.speed * (float)(nC.getSystemTime() % 3000L) / 3000.0F / 8.0F;
                           yh.translate(f1, 0.0F, 0.0F);
                           yh.rotate(customitemproperties.rotation, 0.0F, 0.0F, 1.0F);
                           renderItem.renderModel(model, -1);
                           yh.popMatrix();
                        }
                     }
                  }
               }
            }

            if (flag) {
               yh.enableAlpha();
               yh.enableBlend();
               yh.blendFunc(770, 771);
               yh.color(1.0F, 1.0F, 1.0F, 1.0F);
               yh.matrixMode(5888);
               yh.enableLighting();
               yh.depthFunc(515);
               yh.depthMask(true);
               texturemanager.bindTexture(zj.LOCATION_BLOCKS_TEXTURE);
            }

            return flag;
         }
      }
   }

   public static boolean renderCustomArmorEffect(Iw entity, Qy itemStack, nH model, float limbSwing, float prevLimbSwing, float partialTicks, float timeLimbSwing, float yaw, float pitch, float scale) {
      if (enchantmentProperties == null) {
         return false;
      } else if (XH.isShaders() && bpq.isShadowPass) {
         return false;
      } else if (itemStack == null) {
         return false;
      } else {
         int[][] aint = getEnchantmentIdLevels(itemStack);
         if (aint.length <= 0) {
            return false;
         } else {
            Set set = null;
            boolean flag = false;
            zf texturemanager = XH.getTextureManager();

            for(int i = 0; i < aint.length; ++i) {
               int j = aint[i][0];
               if (j >= 0 && j < enchantmentProperties.length) {
                  bjE[] acustomitemproperties = enchantmentProperties[j];
                  if (acustomitemproperties != null) {
                     for(int k = 0; k < acustomitemproperties.length; ++k) {
                        bjE customitemproperties = acustomitemproperties[k];
                        if (set == null) {
                           set = new HashSet();
                        }

                        if (set.add(j) && matchesProperties(customitemproperties, itemStack, aint) && customitemproperties.textureLocation != null) {
                           texturemanager.bindTexture(customitemproperties.textureLocation);
                           float f = customitemproperties.getTextureWidth(texturemanager);
                           if (!flag) {
                              flag = true;
                              if (XH.isShaders()) {
                                 bpr.renderEnchantedGlintBegin();
                              }

                              yh.enableBlend();
                              yh.depthFunc(514);
                              yh.depthMask(false);
                           }

                           bnV.setupBlend(customitemproperties.blend, 1.0F);
                           yh.disableLighting();
                           yh.matrixMode(5890);
                           yh.loadIdentity();
                           yh.rotate(customitemproperties.rotation, 0.0F, 0.0F, 1.0F);
                           float f1 = f / 8.0F;
                           yh.scale(f1, f1 / 2.0F, f1);
                           float f2 = customitemproperties.speed * (float)(nC.getSystemTime() % 3000L) / 3000.0F / 8.0F;
                           yh.translate(0.0F, f2, 0.0F);
                           yh.matrixMode(5888);
                           model.render(entity, limbSwing, prevLimbSwing, timeLimbSwing, yaw, pitch, scale);
                        }
                     }
                  }
               }
            }

            if (flag) {
               yh.enableAlpha();
               yh.enableBlend();
               yh.blendFunc(770, 771);
               yh.color(1.0F, 1.0F, 1.0F, 1.0F);
               yh.matrixMode(5890);
               yh.loadIdentity();
               yh.matrixMode(5888);
               yh.enableLighting();
               yh.depthMask(true);
               yh.depthFunc(515);
               yh.disableBlend();
               if (XH.isShaders()) {
                  bpr.renderEnchantedGlintEnd();
               }
            }

            return flag;
         }
      }
   }

   public static boolean isUseGlint() {
      return useGlint;
   }

   public static void setRenderOffHand(boolean renderOffHand) {
   }
}
