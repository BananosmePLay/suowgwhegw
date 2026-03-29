package neo;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class bjP {
   private static bjQ mapDynamicLights = new bjQ();
   private static Map<Class, Integer> mapEntityLightLevels = new HashMap();
   private static Map<OL, Integer> mapItemLightLevels = new HashMap();
   private static long timeUpdateMs = 0L;
   private static final double MAX_DIST = 7.5;
   private static final double MAX_DIST_SQ = 56.25;
   private static final int LIGHT_LEVEL_MAX = 15;
   private static final int LIGHT_LEVEL_FIRE = 15;
   private static final int LIGHT_LEVEL_BLAZE = 10;
   private static final int LIGHT_LEVEL_MAGMA_CUBE = 8;
   private static final int LIGHT_LEVEL_MAGMA_CUBE_CORE = 13;
   private static final int LIGHT_LEVEL_GLOWSTONE_DUST = 8;
   private static final int LIGHT_LEVEL_PRISMARINE_CRYSTALS = 8;
   private static final Rd<Qy> PARAMETER_ITEM_STACK;
   private static boolean initialized;

   public bjP() {
   }

   public static void entityAdded(Ig entityIn, yy renderGlobal) {
   }

   public static void entityRemoved(Ig entityIn, yy renderGlobal) {
      synchronized(mapDynamicLights) {
         bjO dynamiclight = mapDynamicLights.remove(entityIn.getEntityId());
         if (dynamiclight != null) {
            dynamiclight.updateLitChunks(renderGlobal);
         }

      }
   }

   public static void update(yy renderGlobal) {
      long i = System.currentTimeMillis();
      if (i >= timeUpdateMs + 50L) {
         timeUpdateMs = i;
         if (!initialized) {
            initialize();
         }

         synchronized(mapDynamicLights) {
            updateMapDynamicLights(renderGlobal);
            if (mapDynamicLights.size() > 0) {
               List<bjO> list = mapDynamicLights.valueList();

               for(int j = 0; j < list.size(); ++j) {
                  bjO dynamiclight = (bjO)list.get(j);
                  dynamiclight.update(renderGlobal);
               }
            }
         }
      }

   }

   private static void initialize() {
      initialized = true;
      mapEntityLightLevels.clear();
      mapItemLightLevels.clear();
      String[] astring = bnQ.getForgeModIds();

      for(int i = 0; i < astring.length; ++i) {
         String s = astring[i];

         try {
            ResourceLocation resourcelocation = new ResourceLocation(s, "optifine/dynamic_lights.properties");
            InputStream inputstream = XH.getResourceStream(resourcelocation);
            loadModConfiguration(inputstream, resourcelocation.toString(), s);
         } catch (IOException var5) {
         }
      }

      if (mapEntityLightLevels.size() > 0) {
         XH.dbg("DynamicLights entities: " + mapEntityLightLevels.size());
      }

      if (mapItemLightLevels.size() > 0) {
         XH.dbg("DynamicLights items: " + mapItemLightLevels.size());
      }

   }

   private static void loadModConfiguration(InputStream in, String path, String modId) {
      if (in != null) {
         try {
            Properties properties = new bqL();
            ((Properties)properties).load(in);
            in.close();
            XH.dbg("DynamicLights: Parsing " + path);
            biS connectedparser = new biS("DynamicLights");
            loadModLightLevels(((Properties)properties).getProperty("entities"), mapEntityLightLevels, new biT(), connectedparser, path, modId);
            loadModLightLevels(((Properties)properties).getProperty("items"), mapItemLightLevels, new biY(), connectedparser, path, modId);
         } catch (IOException var5) {
            XH.warn("DynamicLights: Error reading " + path);
         }
      }

   }

   private static void loadModLightLevels(String prop, Map mapLightLevels, biW ol, biS cp, String path, String modId) {
      if (prop != null) {
         String[] astring = XH.tokenize(prop, " ");

         for(int i = 0; i < astring.length; ++i) {
            String s = astring[i];
            String[] astring1 = XH.tokenize(s, ":");
            if (astring1.length != 2) {
               cp.warn("Invalid entry: " + s + ", in:" + path);
            } else {
               String s1 = astring1[0];
               String s2 = astring1[1];
               String s3 = modId + ":" + s1;
               ResourceLocation resourcelocation = new ResourceLocation(s3);
               Object object = ol.getObject(resourcelocation);
               if (object == null) {
                  cp.warn("Object not found: " + s3);
               } else {
                  int j = cp.parseInt(s2, -1);
                  if (j >= 0 && j <= 15) {
                     mapLightLevels.put(object, new Integer(j));
                  } else {
                     cp.warn("Invalid light level: " + s);
                  }
               }
            }
         }
      }

   }

   private static void updateMapDynamicLights(yy renderGlobal) {
      bij world = renderGlobal.getWorld();
      if (world != null) {
         Iterator var2 = ((bij)world).getLoadedEntityList().iterator();

         while(var2.hasNext()) {
            Ig entity = (Ig)var2.next();
            int i = getLightLevel(entity);
            int j;
            bjO dynamiclight;
            if (i > 0) {
               j = entity.getEntityId();
               dynamiclight = mapDynamicLights.get(j);
               if (dynamiclight == null) {
                  dynamiclight = new bjO(entity);
                  mapDynamicLights.put(j, dynamiclight);
               }
            } else {
               j = entity.getEntityId();
               dynamiclight = mapDynamicLights.remove(j);
               if (dynamiclight != null) {
                  dynamiclight.updateLitChunks(renderGlobal);
               }
            }
         }
      }

   }

   public static int getCombinedLight(BlockPos pos, int combinedLight) {
      double d0 = getLightLevel(pos);
      combinedLight = getCombinedLight(d0, combinedLight);
      return combinedLight;
   }

   public static int getCombinedLight(Ig entity, int combinedLight) {
      double d0 = (double)getLightLevel(entity);
      combinedLight = getCombinedLight(d0, combinedLight);
      return combinedLight;
   }

   public static int getCombinedLight(double lightPlayer, int combinedLight) {
      if (lightPlayer > 0.0) {
         int i = (int)(lightPlayer * 16.0);
         int j = combinedLight & 255;
         if (i > j) {
            combinedLight &= -256;
            combinedLight |= i;
         }
      }

      return combinedLight;
   }

   public static double getLightLevel(BlockPos pos) {
      double d0 = 0.0;
      synchronized(mapDynamicLights) {
         List<bjO> list = mapDynamicLights.valueList();
         int i = list.size();

         for(int j = 0; j < i; ++j) {
            bjO dynamiclight = (bjO)list.get(j);
            int k = dynamiclight.getLastLightLevel();
            if (k > 0) {
               double d1 = dynamiclight.getLastPosX();
               double d2 = dynamiclight.getLastPosY();
               double d3 = dynamiclight.getLastPosZ();
               double d4 = (double)pos.getX() - d1;
               double d5 = (double)pos.getY() - d2;
               double d6 = (double)pos.getZ() - d3;
               double d7 = d4 * d4 + d5 * d5 + d6 * d6;
               if (dynamiclight.isUnderwater() && !XH.isClearWater()) {
                  k = XH.limit(k - 2, 0, 15);
                  d7 *= 2.0;
               }

               if (d7 <= 56.25) {
                  double d8 = Math.sqrt(d7);
                  double d9 = 1.0 - d8 / 7.5;
                  double d10 = d9 * (double)k;
                  if (d10 > d0) {
                     d0 = d10;
                  }
               }
            }
         }
      }

      double d11 = XH.limit(d0, 0.0, 15.0);
      return d11;
   }

   public static int getLightLevel(Qy itemStack) {
      if (itemStack == null) {
         return 0;
      } else {
         OL item = itemStack.getItem();
         if (item instanceof OX) {
            OX itemblock = (OX)item;
            co block = itemblock.getBlock();
            if (block != null) {
               return block.getLightValue(block.getDefaultState());
            }
         }

         if (item == NK.LAVA_BUCKET) {
            return Nk.LAVA.getLightValue(Nk.LAVA.getDefaultState());
         } else if (item != NK.BLAZE_ROD && item != NK.BLAZE_POWDER) {
            if (item == NK.GLOWSTONE_DUST) {
               return 8;
            } else if (item == NK.PRISMARINE_CRYSTALS) {
               return 8;
            } else if (item == NK.MAGMA_CREAM) {
               return 8;
            } else if (item == NK.NETHER_STAR) {
               return Nk.BEACON.getLightValue(Nk.BEACON.getDefaultState()) / 2;
            } else {
               if (!mapItemLightLevels.isEmpty()) {
                  Integer integer = (Integer)mapItemLightLevels.get(item);
                  if (integer != null) {
                     return integer;
                  }
               }

               return 0;
            }
         } else {
            return 10;
         }
      }
   }

   public static int getLightLevel(Ig entity) {
      if (entity == XH.getMinecraft().getRenderViewEntity() && !XH.isDynamicHandLight()) {
         return 0;
      } else {
         if (entity instanceof ME) {
            ME entityplayer = (ME)entity;
            if (entityplayer.isSpectator()) {
               return 0;
            }
         }

         if (entity.isBurning()) {
            return 15;
         } else {
            if (!mapEntityLightLevels.isEmpty()) {
               Integer integer = (Integer)mapEntityLightLevels.get(entity.getClass());
               if (integer != null) {
                  return integer;
               }
            }

            if (entity instanceof MS) {
               return 15;
            } else if (entity instanceof Jr) {
               return 15;
            } else if (entity instanceof Jz) {
               Jz entityblaze = (Jz)entity;
               return entityblaze.isCharged() ? 15 : 10;
            } else if (entity instanceof Kk) {
               Kk entitymagmacube = (Kk)entity;
               return (double)entitymagmacube.squishFactor > 0.6 ? 13 : 8;
            } else {
               if (entity instanceof JB) {
                  JB entitycreeper = (JB)entity;
                  if ((double)entitycreeper.getCreeperFlashIntensity(0.0F) > 0.001) {
                     return 15;
                  }
               }

               Qy itemstack;
               if (entity instanceof Iw) {
                  Iw entitylivingbase = (Iw)entity;
                  itemstack = entitylivingbase.getHeldItemMainhand();
                  int i = getLightLevel(itemstack);
                  Qy itemstack1 = entitylivingbase.getHeldItemOffhand();
                  int j = getLightLevel(itemstack1);
                  Qy itemstack2 = entitylivingbase.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
                  int k = getLightLevel(itemstack2);
                  int l = Math.max(i, j);
                  return Math.max(l, k);
               } else if (entity instanceof IY) {
                  IY entityitem = (IY)entity;
                  itemstack = getItemStack(entityitem);
                  return getLightLevel(itemstack);
               } else {
                  return 0;
               }
            }
         }
      }
   }

   public static void removeLights(yy renderGlobal) {
      synchronized(mapDynamicLights) {
         List<bjO> list = mapDynamicLights.valueList();

         for(int i = 0; i < list.size(); ++i) {
            bjO dynamiclight = (bjO)list.get(i);
            dynamiclight.updateLitChunks(renderGlobal);
         }

         mapDynamicLights.clear();
      }
   }

   public static void clear() {
      synchronized(mapDynamicLights) {
         mapDynamicLights.clear();
      }
   }

   public static int getCount() {
      synchronized(mapDynamicLights) {
         return mapDynamicLights.size();
      }
   }

   public static Qy getItemStack(IY entityItem) {
      Qy itemstack = (Qy)entityItem.getDataManager().get(PARAMETER_ITEM_STACK);
      return itemstack;
   }

   static {
      PARAMETER_ITEM_STACK = new Rd(6, Rt.ITEM_STACK);
   }
}
