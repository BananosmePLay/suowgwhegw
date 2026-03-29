package neo;

import java.util.Iterator;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.util.NonNullList;

public class Od extends Of {
   public Od() {
      super("", 3, 3, NonNullList.from(NS.EMPTY, NS.fromItems(NK.PAPER), NS.fromItems(NK.PAPER), NS.fromItems(NK.PAPER), NS.fromItems(NK.PAPER), NS.fromItem(NK.FILLED_MAP), NS.fromItems(NK.PAPER), NS.fromItems(NK.PAPER), NS.fromItems(NK.PAPER), NS.fromItems(NK.PAPER)), new Qy(NK.MAP));
   }

   public boolean matches(InventoryCrafting inv, bij worldIn) {
      if (!super.matches(inv, worldIn)) {
         return false;
      } else {
         Qy itemstack = Qy.EMPTY;

         for(int i = 0; i < inv.getSizeInventory() && itemstack.isEmpty(); ++i) {
            Qy itemstack1 = inv.getStackInSlot(i);
            if (itemstack1.getItem() == NK.FILLED_MAP) {
               itemstack = itemstack1;
            }
         }

         if (itemstack.isEmpty()) {
            return false;
         } else {
            bhE mapdata = NK.FILLED_MAP.getMapData(itemstack, worldIn);
            if (mapdata == null) {
               return false;
            } else if (this.isExplorationMap(mapdata)) {
               return false;
            } else {
               return mapdata.scale < 4;
            }
         }
      }
   }

   private boolean isExplorationMap(bhE p_190934_1_) {
      if (p_190934_1_.mapDecorations != null) {
         Iterator var2 = p_190934_1_.mapDecorations.values().iterator();

         while(var2.hasNext()) {
            bhG mapdecoration = (bhG)var2.next();
            if (mapdecoration.getType() == bhF.MANSION || mapdecoration.getType() == bhF.MONUMENT) {
               return true;
            }
         }
      }

      return false;
   }

   public Qy getCraftingResult(InventoryCrafting inv) {
      Qy itemstack = Qy.EMPTY;

      for(int i = 0; i < inv.getSizeInventory() && itemstack.isEmpty(); ++i) {
         Qy itemstack1 = inv.getStackInSlot(i);
         if (itemstack1.getItem() == NK.FILLED_MAP) {
            itemstack = itemstack1;
         }
      }

      itemstack = itemstack.copy();
      itemstack.setCount(1);
      if (itemstack.getTagCompound() == null) {
         itemstack.setTagCompound(new QQ());
      }

      itemstack.getTagCompound().setInteger("map_scale_direction", 1);
      return itemstack;
   }

   public boolean isDynamic() {
      return true;
   }
}
