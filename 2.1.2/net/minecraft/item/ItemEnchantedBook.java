package net.minecraft.item;

import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class ItemEnchantedBook extends Item {
   public ItemEnchantedBook() {
   }

   public boolean hasEffect(ItemStack stack) {
      return true;
   }

   public boolean isEnchantable(ItemStack stack) {
      return false;
   }

   public EnumRarity getRarity(ItemStack stack) {
      return getEnchantments(stack).isEmpty() ? super.getRarity(stack) : EnumRarity.UNCOMMON;
   }

   public static NBTTagList getEnchantments(ItemStack p_92110_0_) {
      NBTTagCompound nbttagcompound = p_92110_0_.getTagCompound();
      return nbttagcompound != null ? nbttagcompound.getTagList("StoredEnchantments", 10) : new NBTTagList();
   }

   public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
      super.addInformation(stack, worldIn, tooltip, flagIn);
      NBTTagList nbttaglist = getEnchantments(stack);

      for(int i = 0; i < nbttaglist.tagCount(); ++i) {
         NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
         int j = nbttagcompound.getShort("id");
         Enchantment enchantment = Enchantment.getEnchantmentByID(j);
         if (enchantment != null) {
            tooltip.add(enchantment.getTranslatedName(nbttagcompound.getShort("lvl")));
         }
      }

   }

   public static void addEnchantment(ItemStack p_92115_0_, EnchantmentData stack) {
      NBTTagList nbttaglist = getEnchantments(p_92115_0_);
      boolean flag = true;

      for(int i = 0; i < nbttaglist.tagCount(); ++i) {
         NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
         if (Enchantment.getEnchantmentByID(nbttagcompound.getShort("id")) == stack.enchantment) {
            if (nbttagcompound.getShort("lvl") < stack.enchantmentLevel) {
               nbttagcompound.setShort("lvl", (short)stack.enchantmentLevel);
            }

            flag = false;
            break;
         }
      }

      if (flag) {
         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
         nbttagcompound1.setShort("id", (short)Enchantment.getEnchantmentID(stack.enchantment));
         nbttagcompound1.setShort("lvl", (short)stack.enchantmentLevel);
         nbttaglist.appendTag(nbttagcompound1);
      }

      if (!p_92115_0_.hasTagCompound()) {
         p_92115_0_.setTagCompound(new NBTTagCompound());
      }

      p_92115_0_.getTagCompound().setTag("StoredEnchantments", nbttaglist);
   }

   public static ItemStack getEnchantedItemStack(EnchantmentData p_92111_0_) {
      ItemStack itemstack = new ItemStack(Items.ENCHANTED_BOOK);
      addEnchantment(itemstack, p_92111_0_);
      return itemstack;
   }

   public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
      Iterator var3;
      Enchantment enchantment;
      if (tab == CreativeTabs.SEARCH) {
         var3 = Enchantment.REGISTRY.iterator();

         while(true) {
            do {
               if (!var3.hasNext()) {
                  return;
               }

               enchantment = (Enchantment)var3.next();
            } while(enchantment.type == null);

            for(int i = enchantment.getMinLevel(); i <= enchantment.getMaxLevel(); ++i) {
               items.add(getEnchantedItemStack(new EnchantmentData(enchantment, i)));
            }
         }
      } else if (tab.getRelevantEnchantmentTypes().length != 0) {
         var3 = Enchantment.REGISTRY.iterator();

         while(var3.hasNext()) {
            enchantment = (Enchantment)var3.next();
            if (tab.hasRelevantEnchantmentType(enchantment.type)) {
               items.add(getEnchantedItemStack(new EnchantmentData(enchantment, enchantment.getMaxLevel())));
            }
         }
      }

   }
}
