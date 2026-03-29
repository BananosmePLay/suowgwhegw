package neo;

import javax.annotation.Nullable;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.util.NonNullList;

public class NZ implements NT {
   public NZ() {
   }

   public boolean matches(InventoryCrafting inv, bij worldIn) {
      boolean flag = false;

      for(int i = 0; i < inv.getSizeInventory(); ++i) {
         Qy itemstack = inv.getStackInSlot(i);
         if (itemstack.getItem() == NK.BANNER) {
            if (flag) {
               return false;
            }

            if (Yh.getPatterns(itemstack) >= 6) {
               return false;
            }

            flag = true;
         }
      }

      if (!flag) {
         return false;
      } else {
         return this.matchPatterns(inv) != null;
      }
   }

   public Qy getCraftingResult(InventoryCrafting inv) {
      Qy itemstack = Qy.EMPTY;

      for(int i = 0; i < inv.getSizeInventory(); ++i) {
         Qy itemstack1 = inv.getStackInSlot(i);
         if (!itemstack1.isEmpty() && itemstack1.getItem() == NK.BANNER) {
            itemstack = itemstack1.copy();
            itemstack.setCount(1);
            break;
         }
      }

      XW bannerpattern = this.matchPatterns(inv);
      if (bannerpattern != null) {
         int k = 0;

         for(int j = 0; j < inv.getSizeInventory(); ++j) {
            Qy itemstack2 = inv.getStackInSlot(j);
            if (itemstack2.getItem() == NK.DYE) {
               k = itemstack2.getMetadata();
               break;
            }
         }

         QQ nbttagcompound1 = itemstack.getOrCreateSubCompound("BlockEntityTag");
         QW nbttaglist;
         if (nbttagcompound1.hasKey("Patterns", 9)) {
            nbttaglist = nbttagcompound1.getTagList("Patterns", 10);
         } else {
            nbttaglist = new QW();
            nbttagcompound1.setTag("Patterns", nbttaglist);
         }

         QQ nbttagcompound = new QQ();
         nbttagcompound.setString("Pattern", bannerpattern.getHashname());
         nbttagcompound.setInteger("Color", k);
         nbttaglist.appendTag(nbttagcompound);
      }

      return itemstack;
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

   @Nullable
   private XW matchPatterns(InventoryCrafting p_190933_1_) {
      XW[] var2 = XW.values();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         XW bannerpattern = var2[var4];
         if (bannerpattern.hasPattern()) {
            boolean flag = true;
            int l;
            if (bannerpattern.hasPatternItem()) {
               boolean flag1 = false;
               boolean flag2 = false;

               for(l = 0; l < p_190933_1_.getSizeInventory() && flag; ++l) {
                  Qy itemstack = p_190933_1_.getStackInSlot(l);
                  if (!itemstack.isEmpty() && itemstack.getItem() != NK.BANNER) {
                     if (itemstack.getItem() == NK.DYE) {
                        if (flag2) {
                           flag = false;
                           break;
                        }

                        flag2 = true;
                     } else {
                        if (flag1 || !itemstack.isItemEqual(bannerpattern.getPatternItem())) {
                           flag = false;
                           break;
                        }

                        flag1 = true;
                     }
                  }
               }

               if (!flag1 || !flag2) {
                  flag = false;
               }
            } else if (p_190933_1_.getSizeInventory() == bannerpattern.getPatterns().length * bannerpattern.getPatterns()[0].length()) {
               int j = -1;

               for(int k = 0; k < p_190933_1_.getSizeInventory() && flag; ++k) {
                  l = k / 3;
                  int i1 = k % 3;
                  Qy itemstack1 = p_190933_1_.getStackInSlot(k);
                  if (!itemstack1.isEmpty() && itemstack1.getItem() != NK.BANNER) {
                     if (itemstack1.getItem() != NK.DYE) {
                        flag = false;
                        break;
                     }

                     if (j != -1 && j != itemstack1.getMetadata()) {
                        flag = false;
                        break;
                     }

                     if (bannerpattern.getPatterns()[l].charAt(i1) == ' ') {
                        flag = false;
                        break;
                     }

                     j = itemstack1.getMetadata();
                  } else if (bannerpattern.getPatterns()[l].charAt(i1) != ' ') {
                     flag = false;
                     break;
                  }
               }
            } else {
               flag = false;
            }

            if (flag) {
               return bannerpattern;
            }
         }
      }

      return null;
   }

   public boolean isDynamic() {
      return true;
   }

   public boolean canFit(int width, int height) {
      return width >= 3 && height >= 3;
   }
}
