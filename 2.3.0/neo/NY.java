package neo;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.util.NonNullList;

public class NY implements NT {
   public NY() {
   }

   public boolean matches(InventoryCrafting inv, bij worldIn) {
      Qy itemstack = Qy.EMPTY;
      List<Qy> list = Lists.newArrayList();

      for(int i = 0; i < inv.getSizeInventory(); ++i) {
         Qy itemstack1 = inv.getStackInSlot(i);
         if (!itemstack1.isEmpty()) {
            if (itemstack1.getItem() instanceof OR) {
               OR itemarmor = (OR)itemstack1.getItem();
               if (itemarmor.getArmorMaterial() != OQ.LEATHER || !itemstack.isEmpty()) {
                  return false;
               }

               itemstack = itemstack1;
            } else {
               if (itemstack1.getItem() != NK.DYE) {
                  return false;
               }

               list.add(itemstack1);
            }
         }
      }

      return !itemstack.isEmpty() && !list.isEmpty();
   }

   public Qy getCraftingResult(InventoryCrafting inv) {
      Qy itemstack = Qy.EMPTY;
      int[] aint = new int[3];
      int i = 0;
      int j = 0;
      OR itemarmor = null;

      int i1;
      int l;
      float f;
      float f1;
      int j2;
      for(i1 = 0; i1 < inv.getSizeInventory(); ++i1) {
         Qy itemstack1 = inv.getStackInSlot(i1);
         if (!itemstack1.isEmpty()) {
            if (itemstack1.getItem() instanceof OR) {
               itemarmor = (OR)itemstack1.getItem();
               if (itemarmor.getArmorMaterial() != OQ.LEATHER || !itemstack.isEmpty()) {
                  return Qy.EMPTY;
               }

               itemstack = itemstack1.copy();
               itemstack.setCount(1);
               if (itemarmor.hasColor(itemstack1)) {
                  l = itemarmor.getColor(itemstack);
                  f = (float)(l >> 16 & 255) / 255.0F;
                  f1 = (float)(l >> 8 & 255) / 255.0F;
                  float f2 = (float)(l & 255) / 255.0F;
                  i = (int)((float)i + Math.max(f, Math.max(f1, f2)) * 255.0F);
                  aint[0] = (int)((float)aint[0] + f * 255.0F);
                  aint[1] = (int)((float)aint[1] + f1 * 255.0F);
                  aint[2] = (int)((float)aint[2] + f2 * 255.0F);
                  ++j;
               }
            } else {
               if (itemstack1.getItem() != NK.DYE) {
                  return Qy.EMPTY;
               }

               float[] afloat = Om.byDyeDamage(itemstack1.getMetadata()).getColorComponentValues();
               int l1 = (int)(afloat[0] * 255.0F);
               int i2 = (int)(afloat[1] * 255.0F);
               j2 = (int)(afloat[2] * 255.0F);
               i += Math.max(l1, Math.max(i2, j2));
               aint[0] += l1;
               aint[1] += i2;
               aint[2] += j2;
               ++j;
            }
         }
      }

      if (itemarmor == null) {
         return Qy.EMPTY;
      } else {
         i1 = aint[0] / j;
         int j1 = aint[1] / j;
         l = aint[2] / j;
         f = (float)i / (float)j;
         f1 = (float)Math.max(i1, Math.max(j1, l));
         i1 = (int)((float)i1 * f / f1);
         j1 = (int)((float)j1 * f / f1);
         l = (int)((float)l * f / f1);
         j2 = (i1 << 8) + j1;
         j2 = (j2 << 8) + l;
         itemarmor.setColor(itemstack, j2);
         return itemstack;
      }
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
