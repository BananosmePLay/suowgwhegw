package neo;

import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.NonNullList;

public class Pv extends OL {
   public Pv() {
   }

   public boolean hasEffect(Qy stack) {
      return true;
   }

   public boolean isEnchantable(Qy stack) {
      return false;
   }

   public On getRarity(Qy stack) {
      return getEnchantments(stack).isEmpty() ? super.getRarity(stack) : On.UNCOMMON;
   }

   public static QW getEnchantments(Qy p_92110_0_) {
      QQ nbttagcompound = p_92110_0_.getTagCompound();
      return nbttagcompound != null ? nbttagcompound.getTagList("StoredEnchantments", 10) : new QW();
   }

   public void addInformation(Qy stack, @Nullable bij worldIn, List<String> tooltip, BJ flagIn) {
      super.addInformation(stack, worldIn, tooltip, flagIn);
      QW nbttaglist = getEnchantments(stack);

      for(int i = 0; i < nbttaglist.tagCount(); ++i) {
         QQ nbttagcompound = nbttaglist.getCompoundTagAt(i);
         int j = nbttagcompound.getShort("id");
         Fa enchantment = Fa.getEnchantmentByID(j);
         if (enchantment != null) {
            tooltip.add(enchantment.getTranslatedName(nbttagcompound.getShort("lvl")));
         }
      }

   }

   public static void addEnchantment(Qy p_92115_0_, Fh stack) {
      QW nbttaglist = getEnchantments(p_92115_0_);
      boolean flag = true;

      for(int i = 0; i < nbttaglist.tagCount(); ++i) {
         QQ nbttagcompound = nbttaglist.getCompoundTagAt(i);
         if (Fa.getEnchantmentByID(nbttagcompound.getShort("id")) == stack.enchantment) {
            if (nbttagcompound.getShort("lvl") < stack.enchantmentLevel) {
               nbttagcompound.setShort("lvl", (short)stack.enchantmentLevel);
            }

            flag = false;
            break;
         }
      }

      if (flag) {
         QQ nbttagcompound1 = new QQ();
         nbttagcompound1.setShort("id", (short)Fa.getEnchantmentID(stack.enchantment));
         nbttagcompound1.setShort("lvl", (short)stack.enchantmentLevel);
         nbttaglist.appendTag(nbttagcompound1);
      }

      if (!p_92115_0_.hasTagCompound()) {
         p_92115_0_.setTagCompound(new QQ());
      }

      p_92115_0_.getTagCompound().setTag("StoredEnchantments", nbttaglist);
   }

   public static Qy getEnchantedItemStack(Fh p_92111_0_) {
      Qy itemstack = new Qy(NK.ENCHANTED_BOOK);
      addEnchantment(itemstack, p_92111_0_);
      return itemstack;
   }

   public void getSubItems(EN tab, NonNullList<Qy> items) {
      Iterator var3;
      Fa enchantment;
      if (tab == EN.SEARCH) {
         var3 = Fa.REGISTRY.iterator();

         while(true) {
            do {
               if (!var3.hasNext()) {
                  return;
               }

               enchantment = (Fa)var3.next();
            } while(enchantment.type == null);

            for(int i = enchantment.getMinLevel(); i <= enchantment.getMaxLevel(); ++i) {
               items.add(getEnchantedItemStack(new Fh(enchantment, i)));
            }
         }
      } else if (tab.getRelevantEnchantmentTypes().length != 0) {
         var3 = Fa.REGISTRY.iterator();

         while(var3.hasNext()) {
            enchantment = (Fa)var3.next();
            if (tab.hasRelevantEnchantmentType(enchantment.type)) {
               items.add(getEnchantedItemStack(new Fh(enchantment, enchantment.getMaxLevel())));
            }
         }
      }

   }
}
