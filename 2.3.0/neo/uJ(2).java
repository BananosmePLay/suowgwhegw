package neo;

import net.minecraft.util.ObjectIntIdentityMap;
import net.minecraft.util.math.BlockPos;

public class uJ {
   private final ObjectIntIdentityMap<uA> mapItemColors = new ObjectIntIdentityMap(32);

   public uJ() {
   }

   public static uJ init(final uy colors) {
      uJ itemcolors = new uJ();
      itemcolors.registerItemColorHandler(new uA() {
         public int colorMultiplier(Qy stack, int tintIndex) {
            return tintIndex > 0 ? -1 : ((OR)stack.getItem()).getColor(stack);
         }
      }, NK.LEATHER_HELMET, NK.LEATHER_CHESTPLATE, NK.LEATHER_LEGGINGS, NK.LEATHER_BOOTS);
      itemcolors.registerItemColorHandler(new uA() {
         public int colorMultiplier(Qy stack, int tintIndex) {
            dq blockdoubleplant$enumplanttype = dq.byMetadata(stack.getMetadata());
            return blockdoubleplant$enumplanttype != dq.GRASS && blockdoubleplant$enumplanttype != dq.FERN ? -1 : baK.getGrassColor(0.5, 1.0);
         }
      }, Nk.DOUBLE_PLANT);
      itemcolors.registerItemColorHandler(new uA() {
         public int colorMultiplier(Qy stack, int tintIndex) {
            if (tintIndex != 1) {
               return -1;
            } else {
               QH nbtbase = PC.getExplosionTag(stack, "Colors");
               if (!(nbtbase instanceof QV)) {
                  return 9079434;
               } else {
                  int[] aint = ((QV)nbtbase).getIntArray();
                  if (aint.length == 1) {
                     return aint[0];
                  } else {
                     int i = 0;
                     int j = 0;
                     int k = 0;
                     int[] var8 = aint;
                     int var9 = aint.length;

                     for(int var10 = 0; var10 < var9; ++var10) {
                        int l = var8[var10];
                        i += (l & 16711680) >> 16;
                        j += (l & '\uff00') >> 8;
                        k += (l & 255) >> 0;
                     }

                     i /= aint.length;
                     j /= aint.length;
                     k /= aint.length;
                     return i << 16 | j << 8 | k;
                  }
               }
            }
         }
      }, NK.FIREWORK_CHARGE);
      itemcolors.registerItemColorHandler(new uA() {
         public int colorMultiplier(Qy stack, int tintIndex) {
            return tintIndex > 0 ? -1 : Wg.getColor(stack);
         }
      }, NK.POTIONITEM, NK.SPLASH_POTION, NK.LINGERING_POTION);
      itemcolors.registerItemColorHandler(new uA() {
         public int colorMultiplier(Qy stack, int tintIndex) {
            Iq entitylist$entityegginfo = (Iq)Ir.ENTITY_EGGS.get(PX.getNamedIdFrom(stack));
            if (entitylist$entityegginfo == null) {
               return -1;
            } else {
               return tintIndex == 0 ? entitylist$entityegginfo.primaryColor : entitylist$entityegginfo.secondaryColor;
            }
         }
      }, NK.SPAWN_EGG);
      itemcolors.registerItemColorHandler(new uA() {
         public int colorMultiplier(Qy stack, int tintIndex) {
            in iblockstate = ((OX)stack.getItem()).getBlock().getStateFromMeta(stack.getMetadata());
            return colors.colorMultiplier(iblockstate, (bfZ)null, (BlockPos)null, tintIndex);
         }
      }, Nk.GRASS, Nk.TALLGRASS, Nk.VINE, Nk.LEAVES, Nk.LEAVES2, Nk.WATERLILY);
      itemcolors.registerItemColorHandler(new uA() {
         public int colorMultiplier(Qy stack, int tintIndex) {
            return tintIndex == 0 ? Wg.getColor(stack) : -1;
         }
      }, NK.TIPPED_ARROW);
      itemcolors.registerItemColorHandler(new uA() {
         public int colorMultiplier(Qy stack, int tintIndex) {
            return tintIndex == 0 ? -1 : PT.getColor(stack);
         }
      }, NK.FILLED_MAP);
      return itemcolors;
   }

   public int colorMultiplier(Qy stack, int tintIndex) {
      uA iitemcolor = (uA)this.mapItemColors.getByValue(OL.REGISTRY.getIDForObject(stack.getItem()));
      return iitemcolor == null ? -1 : iitemcolor.colorMultiplier(stack, tintIndex);
   }

   public void registerItemColorHandler(uA itemColor, co... blocksIn) {
      co[] var3 = blocksIn;
      int var4 = blocksIn.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         co block = var3[var5];
         this.mapItemColors.put(itemColor, OL.getIdFromItem(OL.getItemFromBlock(block)));
      }

   }

   public void registerItemColorHandler(uA itemColor, OL... itemsIn) {
      OL[] var3 = itemsIn;
      int var4 = itemsIn.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         OL item = var3[var5];
         this.mapItemColors.put(itemColor, OL.getIdFromItem(item));
      }

   }
}
