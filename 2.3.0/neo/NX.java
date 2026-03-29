package neo;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.util.NonNullList;

public class NX implements NT {
   public NX() {
   }

   public boolean matches(InventoryCrafting inv, bij worldIn) {
      List<Qy> list = Lists.newArrayList();

      for(int i = 0; i < inv.getSizeInventory(); ++i) {
         Qy itemstack = inv.getStackInSlot(i);
         if (!itemstack.isEmpty()) {
            list.add(itemstack);
            if (list.size() > 1) {
               Qy itemstack1 = (Qy)list.get(0);
               if (itemstack.getItem() != itemstack1.getItem() || itemstack1.getCount() != 1 || itemstack.getCount() != 1 || !itemstack1.getItem().isDamageable()) {
                  return false;
               }
            }
         }
      }

      return list.size() == 2;
   }

   public Qy getCraftingResult(InventoryCrafting inv) {
      List<Qy> list = Lists.newArrayList();

      Qy itemstack;
      for(int i = 0; i < inv.getSizeInventory(); ++i) {
         itemstack = inv.getStackInSlot(i);
         if (!itemstack.isEmpty()) {
            list.add(itemstack);
            if (list.size() > 1) {
               Qy itemstack1 = (Qy)list.get(0);
               if (itemstack.getItem() != itemstack1.getItem() || itemstack1.getCount() != 1 || itemstack.getCount() != 1 || !itemstack1.getItem().isDamageable()) {
                  return Qy.EMPTY;
               }
            }
         }
      }

      if (list.size() == 2) {
         Qy itemstack2 = (Qy)list.get(0);
         itemstack = (Qy)list.get(1);
         if (itemstack2.getItem() == itemstack.getItem() && itemstack2.getCount() == 1 && itemstack.getCount() == 1 && itemstack2.getItem().isDamageable()) {
            OL item = itemstack2.getItem();
            int j = item.getMaxDamage() - itemstack2.getItemDamage();
            int k = item.getMaxDamage() - itemstack.getItemDamage();
            int l = j + k + item.getMaxDamage() * 5 / 100;
            int i1 = item.getMaxDamage() - l;
            if (i1 < 0) {
               i1 = 0;
            }

            return new Qy(itemstack2.getItem(), 1, i1);
         }
      }

      return Qy.EMPTY;
   }

   public Qy getRecipeOutput() {
      return Qy.EMPTY;
   }

   public NonNullList<Qy> getRemainingItems(InventoryCrafting inv) {
      NonNullList<Qy> nonnulllist = NonNullList.withSize(inv.getSizeInventory(), Qy.EMPTY);

      for(int i = 0; i < nonnulllist.size(); ++i) {
         Qy itemstack = inv.getStackInSlot(i);
         if (itemstack.getItem().hasContainerItem()) {
            nonnulllist.set(i, new Qy(itemstack.getItem().getContainerItem()));
         }
      }

      return nonnulllist;
   }

   public boolean isDynamic() {
      return true;
   }

   public boolean canFit(int width, int height) {
      return width * height >= 2;
   }
}
