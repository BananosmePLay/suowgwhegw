package neo;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class bnQ {
   public static Object EVENT_RESULT_ALLOW;
   public static Object EVENT_RESULT_DENY;
   public static Object EVENT_RESULT_DEFAULT;

   public bnQ() {
   }

   public static void FMLClientHandler_trackBrokenTexture(ResourceLocation loc, String message) {
      if (!bnK.FMLClientHandler_trackBrokenTexture.exists()) {
         Object object = bnK.call(bnK.FMLClientHandler_instance);
         bnK.call(object, bnK.FMLClientHandler_trackBrokenTexture, loc, message);
      }

   }

   public static void FMLClientHandler_trackMissingTexture(ResourceLocation loc) {
      if (!bnK.FMLClientHandler_trackMissingTexture.exists()) {
         Object object = bnK.call(bnK.FMLClientHandler_instance);
         bnK.call(object, bnK.FMLClientHandler_trackMissingTexture, loc);
      }

   }

   public static void putLaunchBlackboard(String key, Object value) {
      Map map = (Map)bnK.getFieldValue(bnK.Launch_blackboard);
      if (map != null) {
         map.put(key, value);
      }

   }

   public static boolean renderFirstPersonHand(yy renderGlobal, float partialTicks, int pass) {
      return !bnK.ForgeHooksClient_renderFirstPersonHand.exists() ? false : bnK.callBoolean(bnK.ForgeHooksClient_renderFirstPersonHand, renderGlobal, partialTicks, pass);
   }

   public static InputStream getOptiFineResourceStream(String path) {
      if (!bnK.OptiFineClassTransformer_instance.exists()) {
         return null;
      } else {
         Object object = bnK.getFieldValue(bnK.OptiFineClassTransformer_instance);
         if (object == null) {
            return null;
         } else {
            if (path.startsWith("/")) {
               path = path.substring(1);
            }

            byte[] abyte = (byte[])((byte[])bnK.call(object, bnK.OptiFineClassTransformer_getOptiFineResource, path));
            if (abyte == null) {
               return null;
            } else {
               InputStream inputstream = new ByteArrayInputStream(abyte);
               return inputstream;
            }
         }
      }
   }

   public static boolean blockHasTileEntity(in state) {
      co block = state.getBlock();
      return !bnK.ForgeBlock_hasTileEntity.exists() ? block.hasTileEntity() : bnK.callBoolean(block, bnK.ForgeBlock_hasTileEntity, state);
   }

   public static boolean isItemDamaged(Qy stack) {
      return !bnK.ForgeItem_showDurabilityBar.exists() ? stack.isItemDamaged() : bnK.callBoolean(stack.getItem(), bnK.ForgeItem_showDurabilityBar, stack);
   }

   public static boolean armorHasOverlay(OR itemArmor, Qy itemStack) {
      if (bnK.ForgeItemArmor_hasOverlay.exists()) {
         return bnK.callBoolean(itemArmor, bnK.ForgeItemArmor_hasOverlay, itemStack);
      } else {
         int i = itemArmor.getColor(itemStack);
         return i != 16777215;
      }
   }

   public static int getLightValue(in stateIn, bfZ worldIn, BlockPos posIn) {
      return bnK.ForgeIBlockProperties_getLightValue2.exists() ? bnK.callInt(stateIn, bnK.ForgeIBlockProperties_getLightValue2, worldIn, posIn) : stateIn.getLightValue();
   }

   public static bhE getMapData(PT itemMap, Qy stack, bij world) {
      return bnK.ForgeHooksClient.exists() ? ((PT)stack.getItem()).getMapData(stack, world) : itemMap.getMapData(stack, world);
   }

   public static String[] getForgeModIds() {
      if (!bnK.Loader.exists()) {
         return new String[0];
      } else {
         Object object = bnK.call(bnK.Loader_instance);
         List list = (List)bnK.call(object, bnK.Loader_getActiveModList);
         if (list == null) {
            return new String[0];
         } else {
            List<String> list1 = new ArrayList();
            Iterator var3 = list.iterator();

            while(var3.hasNext()) {
               Object object1 = var3.next();
               if (bnK.ModContainer.isInstance(object1)) {
                  String s = bnK.callString(object1, bnK.ModContainer_getModId);
                  if (s != null) {
                     list1.add(s);
                  }
               }
            }

            String[] astring = (String[])((String[])list1.toArray(new String[list1.size()]));
            return astring;
         }
      }
   }

   public static boolean canEntitySpawn(Iu entityliving, bij world, float x, float y, float z) {
      Object object = bnK.call(bnK.ForgeEventFactory_canEntitySpawn, entityliving, world, x, y, z, false);
      return object == EVENT_RESULT_ALLOW || object == EVENT_RESULT_DEFAULT && entityliving.getCanSpawnHere() && entityliving.isNotColliding();
   }

   public static boolean doSpecialSpawn(Iu entityliving, bij world, float x, int y, float z) {
      return bnK.ForgeEventFactory_doSpecialSpawn.exists() ? bnK.callBoolean(bnK.ForgeEventFactory_doSpecialSpawn, entityliving, world, x, y, z) : false;
   }

   public static boolean isAmbientOcclusion(sc model, in state) {
      return bnK.ForgeIBakedModel_isAmbientOcclusion2.exists() ? bnK.callBoolean(model, bnK.ForgeIBakedModel_isAmbientOcclusion2, state) : model.isAmbientOcclusion();
   }

   public static Ez<String> getDetailItemRegistryName(final OL item) {
      return new Ez<String>() {
         public String call() throws Exception {
            Object object = bnK.call(item, bnK.IForgeRegistryEntry_Impl_getRegistryName);
            return String.valueOf(object);
         }

         // $FF: synthetic method
         // $FF: bridge method
         public Object call() throws Exception {
            return this.call();
         }
      };
   }

   static {
      EVENT_RESULT_ALLOW = bnK.getFieldValue(bnK.Event_Result_ALLOW);
      EVENT_RESULT_DENY = bnK.getFieldValue(bnK.Event_Result_DENY);
      EVENT_RESULT_DEFAULT = bnK.getFieldValue(bnK.Event_Result_DEFAULT);
   }
}
