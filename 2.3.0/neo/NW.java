package neo;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.util.NonNullList;

public class NW implements NT {
   private Qy resultItem;

   public NW() {
      this.resultItem = Qy.EMPTY;
   }

   public boolean matches(InventoryCrafting inv, bij worldIn) {
      this.resultItem = Qy.EMPTY;
      int i = 0;
      int j = 0;
      int k = 0;
      int l = 0;
      int i1 = 0;
      int j1 = 0;

      for(int k1 = 0; k1 < inv.getSizeInventory(); ++k1) {
         Qy itemstack = inv.getStackInSlot(k1);
         if (!itemstack.isEmpty()) {
            if (itemstack.getItem() == NK.GUNPOWDER) {
               ++j;
            } else if (itemstack.getItem() == NK.FIREWORK_CHARGE) {
               ++l;
            } else if (itemstack.getItem() == NK.DYE) {
               ++k;
            } else if (itemstack.getItem() == NK.PAPER) {
               ++i;
            } else if (itemstack.getItem() == NK.GLOWSTONE_DUST) {
               ++i1;
            } else if (itemstack.getItem() == NK.DIAMOND) {
               ++i1;
            } else if (itemstack.getItem() == NK.FIRE_CHARGE) {
               ++j1;
            } else if (itemstack.getItem() == NK.FEATHER) {
               ++j1;
            } else if (itemstack.getItem() == NK.GOLD_NUGGET) {
               ++j1;
            } else {
               if (itemstack.getItem() != NK.SKULL) {
                  return false;
               }

               ++j1;
            }
         }
      }

      i1 = i1 + k + j1;
      if (j <= 3 && i <= 1) {
         QQ nbttagcompound;
         QQ nbttagcompound2;
         int j2;
         if (j >= 1 && i == 1 && i1 == 0) {
            this.resultItem = new Qy(NK.FIREWORKS, 3);
            nbttagcompound = new QQ();
            if (l > 0) {
               QW nbttaglist = new QW();

               for(j2 = 0; j2 < inv.getSizeInventory(); ++j2) {
                  Qy itemstack3 = inv.getStackInSlot(j2);
                  if (itemstack3.getItem() == NK.FIREWORK_CHARGE && itemstack3.hasTagCompound() && itemstack3.getTagCompound().hasKey("Explosion", 10)) {
                     nbttaglist.appendTag(itemstack3.getTagCompound().getCompoundTag("Explosion"));
                  }
               }

               nbttagcompound.setTag("Explosions", nbttaglist);
            }

            nbttagcompound.setByte("Flight", (byte)j);
            nbttagcompound2 = new QQ();
            nbttagcompound2.setTag("Fireworks", nbttagcompound);
            this.resultItem.setTagCompound(nbttagcompound2);
            return true;
         } else if (j == 1 && i == 0 && l == 0 && k > 0 && j1 <= 1) {
            this.resultItem = new Qy(NK.FIREWORK_CHARGE);
            nbttagcompound = new QQ();
            nbttagcompound2 = new QQ();
            byte b0 = 0;
            List<Integer> list = Lists.newArrayList();

            for(int l1 = 0; l1 < inv.getSizeInventory(); ++l1) {
               Qy itemstack2 = inv.getStackInSlot(l1);
               if (!itemstack2.isEmpty()) {
                  if (itemstack2.getItem() == NK.DYE) {
                     list.add(Pq.DYE_COLORS[itemstack2.getMetadata() & 15]);
                  } else if (itemstack2.getItem() == NK.GLOWSTONE_DUST) {
                     nbttagcompound2.setBoolean("Flicker", true);
                  } else if (itemstack2.getItem() == NK.DIAMOND) {
                     nbttagcompound2.setBoolean("Trail", true);
                  } else if (itemstack2.getItem() == NK.FIRE_CHARGE) {
                     b0 = 1;
                  } else if (itemstack2.getItem() == NK.FEATHER) {
                     b0 = 4;
                  } else if (itemstack2.getItem() == NK.GOLD_NUGGET) {
                     b0 = 2;
                  } else if (itemstack2.getItem() == NK.SKULL) {
                     b0 = 3;
                  }
               }
            }

            int[] aint1 = new int[list.size()];

            for(int l2 = 0; l2 < aint1.length; ++l2) {
               aint1[l2] = (Integer)list.get(l2);
            }

            nbttagcompound2.setIntArray("Colors", aint1);
            nbttagcompound2.setByte("Type", b0);
            nbttagcompound.setTag("Explosion", nbttagcompound2);
            this.resultItem.setTagCompound(nbttagcompound);
            return true;
         } else if (j == 0 && i == 0 && l == 1 && k > 0 && k == i1) {
            List<Integer> list1 = Lists.newArrayList();

            for(int i2 = 0; i2 < inv.getSizeInventory(); ++i2) {
               Qy itemstack1 = inv.getStackInSlot(i2);
               if (!itemstack1.isEmpty()) {
                  if (itemstack1.getItem() == NK.DYE) {
                     list1.add(Pq.DYE_COLORS[itemstack1.getMetadata() & 15]);
                  } else if (itemstack1.getItem() == NK.FIREWORK_CHARGE) {
                     this.resultItem = itemstack1.copy();
                     this.resultItem.setCount(1);
                  }
               }
            }

            int[] aint = new int[list1.size()];

            for(j2 = 0; j2 < aint.length; ++j2) {
               aint[j2] = (Integer)list1.get(j2);
            }

            if (!this.resultItem.isEmpty() && this.resultItem.hasTagCompound()) {
               QQ nbttagcompound4 = this.resultItem.getTagCompound().getCompoundTag("Explosion");
               if (nbttagcompound4 == null) {
                  return false;
               } else {
                  nbttagcompound4.setIntArray("FadeColors", aint);
                  return true;
               }
            } else {
               return false;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public Qy getCraftingResult(InventoryCrafting inv) {
      return this.resultItem.copy();
   }

   public Qy getRecipeOutput() {
      return this.resultItem;
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
      return width * height >= 1;
   }
}
