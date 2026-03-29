package neo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;
import net.minecraft.util.ResourceLocation;

public class bnx {
   private static Map<String, bnz> mapProperties = new HashMap();
   private static boolean active = false;
   private static yy renderGlobal;
   private static bny randomEntity = new bny();
   private static zz tileEntityRendererDispatcher;
   private static bnB randomTileEntity = new bnB();
   private static boolean working = false;
   public static final String SUFFIX_PNG = ".png";
   public static final String SUFFIX_PROPERTIES = ".properties";
   public static final String PREFIX_TEXTURES_ENTITY = "textures/entity/";
   public static final String PREFIX_TEXTURES_PAINTING = "textures/painting/";
   public static final String PREFIX_TEXTURES = "textures/";
   public static final String PREFIX_OPTIFINE_RANDOM = "optifine/random/";
   public static final String PREFIX_MCPATCHER_MOB = "mcpatcher/mob/";
   private static final String[] DEPENDANT_SUFFIXES = new String[]{"_armor", "_eyes", "_exploding", "_shooting", "_fur", "_eyes", "_invulnerable", "_angry", "_tame", "_collar"};
   private static final String PREFIX_DYNAMIC_TEXTURE_HORSE = "horse/";
   private static final String[] HORSE_TEXTURES = (String[])((String[])bnS.getFieldValue((Object)null, LF.class, String[].class, 0));
   private static final String[] HORSE_TEXTURES_ABBR = (String[])((String[])bnS.getFieldValue((Object)null, LF.class, String[].class, 1));

   public bnx() {
   }

   public static void entityLoaded(Ig entity, bij world) {
      if (world != null) {
         Rv entitydatamanager = entity.getDataManager();
         entitydatamanager.spawnPosition = entity.getPosition();
         entitydatamanager.spawnBiome = world.getBiome(entitydatamanager.spawnPosition);
         if (entity instanceof Mc) {
            Mc entityshoulderriding = (Mc)entity;
            checkEntityShoulder(entityshoulderriding, false);
         }

         UUID uuid = entity.getUniqueID();
         if (entity instanceof Mq) {
            updateEntityVillager(uuid, (Mq)entity);
         }
      }

   }

   public static void entityUnloaded(Ig entity, bij world) {
      if (entity instanceof Mc) {
         Mc entityshoulderriding = (Mc)entity;
         checkEntityShoulder(entityshoulderriding, true);
      }

   }

   private static void checkEntityShoulder(Mc entity, boolean attach) {
      Iw entitylivingbase = entity.getOwner();
      if (entitylivingbase == null) {
         XH.getMinecraft();
         entitylivingbase = nC.player;
      }

      if (entitylivingbase instanceof jf) {
         jf abstractclientplayer = (jf)entitylivingbase;
         UUID uuid = entity.getUniqueID();
         if (attach) {
            QQ nbttagcompound = abstractclientplayer.getLeftShoulderEntity();
            if (nbttagcompound != null && nbttagcompound.hasKey("UUID") && XH.equals(nbttagcompound.getUniqueId("UUID"), uuid)) {
               abstractclientplayer.entityShoulderLeft = entity;
            }

            QQ nbttagcompound1 = abstractclientplayer.getRightShoulderEntity();
            if (nbttagcompound1 != null && nbttagcompound1.hasKey("UUID") && XH.equals(nbttagcompound1.getUniqueId("UUID"), uuid)) {
               abstractclientplayer.entityShoulderRight = entity;
            }
         } else {
            Rv entitydatamanager = entity.getDataManager();
            Rv entitydatamanager2;
            if (abstractclientplayer.entityShoulderLeft != null && XH.equals(abstractclientplayer.entityShoulderLeft.getUniqueID(), uuid)) {
               entitydatamanager2 = abstractclientplayer.entityShoulderLeft.getDataManager();
               entitydatamanager.spawnPosition = entitydatamanager2.spawnPosition;
               entitydatamanager.spawnBiome = entitydatamanager2.spawnBiome;
               abstractclientplayer.entityShoulderLeft = null;
            }

            if (abstractclientplayer.entityShoulderRight != null && XH.equals(abstractclientplayer.entityShoulderRight.getUniqueID(), uuid)) {
               entitydatamanager2 = abstractclientplayer.entityShoulderRight.getDataManager();
               entitydatamanager.spawnPosition = entitydatamanager2.spawnPosition;
               entitydatamanager.spawnBiome = entitydatamanager2.spawnBiome;
               abstractclientplayer.entityShoulderRight = null;
            }
         }
      }

   }

   private static void updateEntityVillager(UUID uuid, Mq ev) {
      Ig entity = bqt.getEntity(uuid);
      if (entity instanceof Mq) {
         Mq entityvillager = (Mq)entity;
         int i = entityvillager.getProfession();
         ev.setProfession(i);
         int j = bnK.getFieldValueInt(entityvillager, bnK.EntityVillager_careerId, 0);
         bnK.setFieldValueInt(ev, bnK.EntityVillager_careerId, j);
         int k = bnK.getFieldValueInt(entityvillager, bnK.EntityVillager_careerLevel, 0);
         bnK.setFieldValueInt(ev, bnK.EntityVillager_careerLevel, k);
      }

   }

   public static void worldChanged(bij oldWorld, bij newWorld) {
      if (newWorld != null) {
         List list = newWorld.getLoadedEntityList();

         for(int i = 0; i < list.size(); ++i) {
            Ig entity = (Ig)list.get(i);
            entityLoaded(entity, newWorld);
         }
      }

      randomEntity.setEntity((Ig)null);
      randomTileEntity.setTileEntity((Yg)null);
   }

   public static ResourceLocation getTextureLocation(ResourceLocation loc) {
      if (!active) {
         return loc;
      } else if (working) {
         return loc;
      } else {
         ResourceLocation var6;
         try {
            working = true;
            bmR irandomentity = getRandomEntityRendered();
            if (irandomentity == null) {
               ResourceLocation name = loc;
               return name;
            }

            String s = loc.getPath();
            if (s.startsWith("horse/")) {
               s = getHorseTexturePath(s, "horse/".length());
            }

            ResourceLocation resourcelocation1;
            if (!s.startsWith("textures/entity/") && !s.startsWith("textures/painting/")) {
               ResourceLocation resourcelocation2 = loc;
               resourcelocation1 = resourcelocation2;
               return resourcelocation1;
            }

            bnz randomentityproperties = (bnz)mapProperties.get(s);
            if (randomentityproperties == null) {
               resourcelocation1 = loc;
               var6 = resourcelocation1;
               return var6;
            }

            resourcelocation1 = randomentityproperties.getTextureLocation(loc, irandomentity);
            var6 = resourcelocation1;
         } finally {
            working = false;
         }

         return var6;
      }
   }

   private static String getHorseTexturePath(String path, int pos) {
      if (HORSE_TEXTURES != null && HORSE_TEXTURES_ABBR != null) {
         for(int i = 0; i < HORSE_TEXTURES_ABBR.length; ++i) {
            String s = HORSE_TEXTURES_ABBR[i];
            if (path.startsWith(s, pos)) {
               return HORSE_TEXTURES[i];
            }
         }

         return path;
      } else {
         return path;
      }
   }

   private static bmR getRandomEntityRendered() {
      if (renderGlobal.renderedEntity != null) {
         randomEntity.setEntity(renderGlobal.renderedEntity);
         return randomEntity;
      } else {
         if (tileEntityRendererDispatcher.tileEntityRendered != null) {
            Yg tileentity = tileEntityRendererDispatcher.tileEntityRendered;
            if (tileentity.getWorld() != null) {
               randomTileEntity.setTileEntity(tileentity);
               return randomTileEntity;
            }
         }

         return null;
      }
   }

   private static bnz makeProperties(ResourceLocation loc, boolean mcpatcher) {
      String s = loc.getPath();
      ResourceLocation resourcelocation = getLocationProperties(loc, mcpatcher);
      if (resourcelocation != null) {
         bnz randomentityproperties = parseProperties(resourcelocation, loc);
         if (randomentityproperties != null) {
            return randomentityproperties;
         }
      }

      ResourceLocation[] aresourcelocation = getLocationsVariants(loc, mcpatcher);
      return aresourcelocation == null ? null : new bnz(s, aresourcelocation);
   }

   private static bnz parseProperties(ResourceLocation propLoc, ResourceLocation resLoc) {
      try {
         String s = propLoc.getPath();
         dbg(resLoc.getPath() + ", properties: " + s);
         InputStream inputstream = XH.getResourceStream(propLoc);
         if (inputstream == null) {
            warn("Properties not found: " + s);
            return null;
         } else {
            Properties properties = new bqL();
            ((Properties)properties).load(inputstream);
            inputstream.close();
            bnz randomentityproperties = new bnz(properties, s, resLoc);
            return !randomentityproperties.isValid(s) ? null : randomentityproperties;
         }
      } catch (FileNotFoundException var6) {
         warn("File not found: " + resLoc.getPath());
         return null;
      } catch (IOException var7) {
         IOException ioexception = var7;
         ioexception.printStackTrace();
         return null;
      }
   }

   private static ResourceLocation getLocationProperties(ResourceLocation loc, boolean mcpatcher) {
      ResourceLocation resourcelocation = getLocationRandom(loc, mcpatcher);
      if (resourcelocation == null) {
         return null;
      } else {
         String s = resourcelocation.getNamespace();
         String s1 = resourcelocation.getPath();
         String s2 = bqP.removeSuffix(s1, ".png");
         String s3 = s2 + ".properties";
         ResourceLocation resourcelocation1 = new ResourceLocation(s, s3);
         if (XH.hasResource(resourcelocation1)) {
            return resourcelocation1;
         } else {
            String s4 = getParentTexturePath(s2);
            if (s4 == null) {
               return null;
            } else {
               ResourceLocation resourcelocation2 = new ResourceLocation(s, s4 + ".properties");
               return XH.hasResource(resourcelocation2) ? resourcelocation2 : null;
            }
         }
      }
   }

   protected static ResourceLocation getLocationRandom(ResourceLocation loc, boolean mcpatcher) {
      String s = loc.getNamespace();
      String s1 = loc.getPath();
      String s2 = "textures/";
      String s3 = "optifine/random/";
      if (mcpatcher) {
         s2 = "textures/entity/";
         s3 = "mcpatcher/mob/";
      }

      if (!s1.startsWith(s2)) {
         return null;
      } else {
         String s4 = bqP.replacePrefix(s1, s2, s3);
         return new ResourceLocation(s, s4);
      }
   }

   private static String getPathBase(String pathRandom) {
      if (pathRandom.startsWith("optifine/random/")) {
         return bqP.replacePrefix(pathRandom, "optifine/random/", "textures/");
      } else {
         return pathRandom.startsWith("mcpatcher/mob/") ? bqP.replacePrefix(pathRandom, "mcpatcher/mob/", "textures/entity/") : null;
      }
   }

   protected static ResourceLocation getLocationIndexed(ResourceLocation loc, int index) {
      if (loc == null) {
         return null;
      } else {
         String s = loc.getPath();
         int i = s.lastIndexOf(46);
         if (i < 0) {
            return null;
         } else {
            String s1 = s.substring(0, i);
            String s2 = s.substring(i);
            String s3 = s1 + index + s2;
            ResourceLocation resourcelocation = new ResourceLocation(loc.getNamespace(), s3);
            return resourcelocation;
         }
      }
   }

   private static String getParentTexturePath(String path) {
      for(int i = 0; i < DEPENDANT_SUFFIXES.length; ++i) {
         String s = DEPENDANT_SUFFIXES[i];
         if (path.endsWith(s)) {
            String s1 = bqP.removeSuffix(path, s);
            return s1;
         }
      }

      return null;
   }

   private static ResourceLocation[] getLocationsVariants(ResourceLocation loc, boolean mcpatcher) {
      List list = new ArrayList();
      list.add(loc);
      ResourceLocation resourcelocation = getLocationRandom(loc, mcpatcher);
      if (resourcelocation == null) {
         return null;
      } else {
         for(int i = 1; i < list.size() + 10; ++i) {
            int j = i + 1;
            ResourceLocation resourcelocation1 = getLocationIndexed(resourcelocation, j);
            if (XH.hasResource(resourcelocation1)) {
               list.add(resourcelocation1);
            }
         }

         if (list.size() <= 1) {
            return null;
         } else {
            ResourceLocation[] aresourcelocation = (ResourceLocation[])((ResourceLocation[])list.toArray(new ResourceLocation[list.size()]));
            dbg(loc.getPath() + ", variants: " + aresourcelocation.length);
            return aresourcelocation;
         }
      }
   }

   public static void update() {
      mapProperties.clear();
      active = false;
      if (XH.isRandomEntities()) {
         initialize();
      }

   }

   private static void initialize() {
      renderGlobal = XH.getRenderGlobal();
      tileEntityRendererDispatcher = zz.instance;
      String[] astring = new String[]{"optifine/random/", "mcpatcher/mob/"};
      String[] astring1 = new String[]{".png", ".properties"};
      String[] astring2 = bqN.collectFiles(astring, astring1);
      Set set = new HashSet();

      for(int i = 0; i < astring2.length; ++i) {
         String s = astring2[i];
         s = bqP.removeSuffix(s, astring1);
         s = bqP.trimTrailing(s, "0123456789");
         s = s + ".png";
         String s1 = getPathBase(s);
         if (!set.contains(s1)) {
            set.add(s1);
            ResourceLocation resourcelocation = new ResourceLocation(s1);
            if (XH.hasResource(resourcelocation)) {
               bnz randomentityproperties = (bnz)mapProperties.get(s1);
               if (randomentityproperties == null) {
                  randomentityproperties = makeProperties(resourcelocation, false);
                  if (randomentityproperties == null) {
                     randomentityproperties = makeProperties(resourcelocation, true);
                  }

                  if (randomentityproperties != null) {
                     mapProperties.put(s1, randomentityproperties);
                  }
               }
            }
         }
      }

      active = !mapProperties.isEmpty();
   }

   public static void dbg(String str) {
      XH.dbg("RandomEntities: " + str);
   }

   public static void warn(String str) {
      XH.warn("RandomEntities: " + str);
   }
}
