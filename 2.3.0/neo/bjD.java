package neo;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class bjD {
   private static nC mc = XH.getMinecraft();
   private static bnl playerControllerOF = null;
   private static bjC[][] guiProperties = (bjC[][])((bjC[][])null);
   public static boolean isChristmas = isChristmas();

   public bjD() {
   }

   public static ResourceLocation getTextureLocation(ResourceLocation loc) {
      if (guiProperties == null) {
         return loc;
      } else {
         lg guiscreen = mc.currentScreen;
         if (!(guiscreen instanceof lU)) {
            return loc;
         } else if (loc.getNamespace().equals("minecraft") && loc.getPath().startsWith("textures/gui/")) {
            if (playerControllerOF == null) {
               return loc;
            } else {
               bfZ iblockaccess = mc.world;
               if (iblockaccess == null) {
                  return loc;
               } else {
                  nC var10001;
                  if (guiscreen instanceof lY) {
                     var10001 = mc;
                     return getTexturePos(bjA.CREATIVE, nC.player.getPosition(), iblockaccess, loc, guiscreen);
                  } else if (guiscreen instanceof mh) {
                     var10001 = mc;
                     return getTexturePos(bjA.INVENTORY, nC.player.getPosition(), iblockaccess, loc, guiscreen);
                  } else {
                     BlockPos blockpos = playerControllerOF.getLastClickBlockPos();
                     if (blockpos != null) {
                        if (guiscreen instanceof lc) {
                           return getTexturePos(bjA.ANVIL, blockpos, iblockaccess, loc, guiscreen);
                        }

                        if (guiscreen instanceof lR) {
                           return getTexturePos(bjA.BEACON, blockpos, iblockaccess, loc, guiscreen);
                        }

                        if (guiscreen instanceof lS) {
                           return getTexturePos(bjA.BREWING_STAND, blockpos, iblockaccess, loc, guiscreen);
                        }

                        if (guiscreen instanceof lT) {
                           return getTexturePos(bjA.CHEST, blockpos, iblockaccess, loc, guiscreen);
                        }

                        if (guiscreen instanceof lZ) {
                           return getTexturePos(bjA.CRAFTING, blockpos, iblockaccess, loc, guiscreen);
                        }

                        if (guiscreen instanceof ma) {
                           return getTexturePos(bjA.DISPENSER, blockpos, iblockaccess, loc, guiscreen);
                        }

                        if (guiscreen instanceof kf) {
                           return getTexturePos(bjA.ENCHANTMENT, blockpos, iblockaccess, loc, guiscreen);
                        }

                        if (guiscreen instanceof mg) {
                           return getTexturePos(bjA.FURNACE, blockpos, iblockaccess, loc, guiscreen);
                        }

                        if (guiscreen instanceof kl) {
                           return getTexturePos(bjA.HOPPER, blockpos, iblockaccess, loc, guiscreen);
                        }

                        if (guiscreen instanceof mj) {
                           return getTexturePos(bjA.SHULKER_BOX, blockpos, iblockaccess, loc, guiscreen);
                        }
                     }

                     Ig entity = playerControllerOF.getLastClickEntity();
                     if (entity != null) {
                        if (guiscreen instanceof mi) {
                           return getTextureEntity(bjA.HORSE, entity, iblockaccess, loc);
                        }

                        if (guiscreen instanceof kH) {
                           return getTextureEntity(bjA.VILLAGER, entity, iblockaccess, loc);
                        }
                     }

                     return loc;
                  }
               }
            }
         } else {
            return loc;
         }
      }
   }

   private static ResourceLocation getTexturePos(bjA container, BlockPos pos, bfZ blockAccess, ResourceLocation loc, lg screen) {
      bjC[] acustomguiproperties = guiProperties[container.ordinal()];
      if (acustomguiproperties == null) {
         return loc;
      } else {
         for(int i = 0; i < acustomguiproperties.length; ++i) {
            bjC customguiproperties = acustomguiproperties[i];
            if (customguiproperties.matchesPos(container, pos, blockAccess, screen)) {
               return customguiproperties.getTextureLocation(loc);
            }
         }

         return loc;
      }
   }

   private static ResourceLocation getTextureEntity(bjA container, Ig entity, bfZ blockAccess, ResourceLocation loc) {
      bjC[] acustomguiproperties = guiProperties[container.ordinal()];
      if (acustomguiproperties == null) {
         return loc;
      } else {
         for(int i = 0; i < acustomguiproperties.length; ++i) {
            bjC customguiproperties = acustomguiproperties[i];
            if (customguiproperties.matchesEntity(container, entity, blockAccess)) {
               return customguiproperties.getTextureLocation(loc);
            }
         }

         return loc;
      }
   }

   public static void update() {
      guiProperties = (bjC[][])((bjC[][])null);
      if (XH.isCustomGuis()) {
         List<List<bjC>> list = new ArrayList();
         AC[] airesourcepack = XH.getResourcePacks();

         for(int i = airesourcepack.length - 1; i >= 0; --i) {
            AC iresourcepack = airesourcepack[i];
            update(iresourcepack, list);
         }

         guiProperties = propertyListToArray(list);
      }

   }

   private static bjC[][] propertyListToArray(List<List<bjC>> listProps) {
      if (listProps.isEmpty()) {
         return (bjC[][])((bjC[][])null);
      } else {
         bjC[][] acustomguiproperties = new bjC[bjA.values().length][];

         for(int i = 0; i < acustomguiproperties.length; ++i) {
            if (listProps.size() > i) {
               List<bjC> list = (List)listProps.get(i);
               if (list != null) {
                  bjC[] acustomguiproperties1 = (bjC[])((bjC[])list.toArray(new bjC[list.size()]));
                  acustomguiproperties[i] = acustomguiproperties1;
               }
            }
         }

         return acustomguiproperties;
      }
   }

   private static void update(AC rp, List<List<bjC>> listProps) {
      String[] astring = bqN.collectFiles(rp, "optifine/gui/container/", ".properties", (String[])null);
      Arrays.sort((Object[])astring);

      for(int i = 0; i < astring.length; ++i) {
         String s = astring[i];
         XH.dbg("CustomGuis: " + s);

         try {
            ResourceLocation resourcelocation = new ResourceLocation(s);
            InputStream inputstream = rp.getInputStream(resourcelocation);
            if (inputstream == null) {
               XH.warn("CustomGuis file not found: " + s);
            } else {
               Properties properties = new bqL();
               ((Properties)properties).load(inputstream);
               inputstream.close();
               bjC customguiproperties = new bjC(properties, s);
               if (customguiproperties.isValid(s)) {
                  addToList(customguiproperties, listProps);
               }
            }
         } catch (FileNotFoundException var9) {
            XH.warn("CustomGuis file not found: " + s);
         } catch (Exception var10) {
            Exception exception = var10;
            exception.printStackTrace();
         }
      }

   }

   private static void addToList(bjC cgp, List<List<bjC>> listProps) {
      if (cgp.getContainer() == null) {
         warn("Invalid container: " + cgp.getContainer());
      } else {
         int i = cgp.getContainer().ordinal();

         while(listProps.size() <= i) {
            listProps.add((Object)null);
         }

         List<bjC> list = (List)listProps.get(i);
         if (list == null) {
            list = new ArrayList();
            listProps.set(i, list);
         }

         ((List)list).add(cgp);
      }

   }

   public static bnl getPlayerControllerOF() {
      return playerControllerOF;
   }

   public static void setPlayerControllerOF(bnl playerControllerOF) {
   }

   private static boolean isChristmas() {
      Calendar calendar = Calendar.getInstance();
      return calendar.get(2) + 1 == 12 && calendar.get(5) >= 24 && calendar.get(5) <= 26;
   }

   private static void warn(String str) {
      XH.warn("[CustomGuis] " + str);
   }
}
