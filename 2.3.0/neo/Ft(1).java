package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Util;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.math.MathHelper;

public class Ft {
   private static final Fr ENCHANTMENT_MODIFIER_DAMAGE = new Fr();
   private static final Fs ENCHANTMENT_MODIFIER_LIVING = new Fs();
   private static final Fp ENCHANTMENT_ITERATOR_HURT = new Fp();
   private static final Fo ENCHANTMENT_ITERATOR_DAMAGE = new Fo();

   public Ft() {
   }

   public static int getEnchantmentLevel(Fa enchID, Qy stack) {
      if (stack.isEmpty()) {
         return 0;
      } else {
         QW nbttaglist = stack.getEnchantmentTagList();

         for(int i = 0; i < nbttaglist.tagCount(); ++i) {
            QQ nbttagcompound = nbttaglist.getCompoundTagAt(i);
            Fa enchantment = Fa.getEnchantmentByID(nbttagcompound.getShort("id"));
            int j = nbttagcompound.getShort("lvl");
            if (enchantment == enchID) {
               return j;
            }
         }

         return 0;
      }
   }

   public static Map<Fa, Integer> getEnchantments(Qy stack) {
      Map<Fa, Integer> map = Maps.newLinkedHashMap();
      QW nbttaglist = stack.getItem() == NK.ENCHANTED_BOOK ? Pv.getEnchantments(stack) : stack.getEnchantmentTagList();

      for(int i = 0; i < nbttaglist.tagCount(); ++i) {
         QQ nbttagcompound = nbttaglist.getCompoundTagAt(i);
         Fa enchantment = Fa.getEnchantmentByID(nbttagcompound.getShort("id"));
         int j = nbttagcompound.getShort("lvl");
         map.put(enchantment, Integer.valueOf(j));
      }

      return map;
   }

   public static void setEnchantments(Map<Fa, Integer> enchMap, Qy stack) {
      QW nbttaglist = new QW();
      Iterator var3 = enchMap.entrySet().iterator();

      while(var3.hasNext()) {
         Map.Entry<Fa, Integer> entry = (Map.Entry)var3.next();
         Fa enchantment = (Fa)entry.getKey();
         if (enchantment != null) {
            int i = (Integer)entry.getValue();
            QQ nbttagcompound = new QQ();
            nbttagcompound.setShort("id", (short)Fa.getEnchantmentID(enchantment));
            nbttagcompound.setShort("lvl", (short)i);
            nbttaglist.appendTag(nbttagcompound);
            if (stack.getItem() == NK.ENCHANTED_BOOK) {
               Pv.addEnchantment(stack, new Fh(enchantment, i));
            }
         }
      }

      if (nbttaglist.isEmpty()) {
         if (stack.hasTagCompound()) {
            stack.getTagCompound().removeTag("ench");
         }
      } else if (stack.getItem() != NK.ENCHANTED_BOOK) {
         stack.setTagInfo("ench", nbttaglist);
      }

   }

   private static void applyEnchantmentModifier(Fq modifier, Qy stack) {
      if (!stack.isEmpty()) {
         QW nbttaglist = stack.getEnchantmentTagList();

         for(int i = 0; i < nbttaglist.tagCount(); ++i) {
            int j = nbttaglist.getCompoundTagAt(i).getShort("id");
            int k = nbttaglist.getCompoundTagAt(i).getShort("lvl");
            if (Fa.getEnchantmentByID(j) != null) {
               modifier.calculateModifier(Fa.getEnchantmentByID(j), k);
            }
         }
      }

   }

   private static void applyEnchantmentModifierArray(Fq modifier, Iterable<Qy> stacks) {
      Iterator var2 = stacks.iterator();

      while(var2.hasNext()) {
         Qy itemstack = (Qy)var2.next();
         applyEnchantmentModifier(modifier, itemstack);
      }

   }

   public static int getEnchantmentModifierDamage(Iterable<Qy> stacks, DamageSource source) {
      ENCHANTMENT_MODIFIER_DAMAGE.damageModifier = 0;
      ENCHANTMENT_MODIFIER_DAMAGE.source = source;
      applyEnchantmentModifierArray(ENCHANTMENT_MODIFIER_DAMAGE, stacks);
      return ENCHANTMENT_MODIFIER_DAMAGE.damageModifier;
   }

   public static float getModifierForCreature(Qy stack, IB creatureAttribute) {
      ENCHANTMENT_MODIFIER_LIVING.livingModifier = 0.0F;
      ENCHANTMENT_MODIFIER_LIVING.entityLiving = creatureAttribute;
      applyEnchantmentModifier(ENCHANTMENT_MODIFIER_LIVING, stack);
      return ENCHANTMENT_MODIFIER_LIVING.livingModifier;
   }

   public static float getSweepingDamageRatio(Iw p_191527_0_) {
      int i = getMaxEnchantmentLevel(NJ.SWEEPING, p_191527_0_);
      return i > 0 ? FA.getSweepingDamageRatio(i) : 0.0F;
   }

   public static void applyThornEnchantments(Iw p_151384_0_, Ig p_151384_1_) {
      ENCHANTMENT_ITERATOR_HURT.attacker = p_151384_1_;
      ENCHANTMENT_ITERATOR_HURT.user = p_151384_0_;
      if (p_151384_0_ != null) {
         applyEnchantmentModifierArray(ENCHANTMENT_ITERATOR_HURT, p_151384_0_.getEquipmentAndArmor());
      }

      if (p_151384_1_ instanceof ME) {
         applyEnchantmentModifier(ENCHANTMENT_ITERATOR_HURT, p_151384_0_.getHeldItemMainhand());
      }

   }

   public static void applyArthropodEnchantments(Iw p_151385_0_, Ig p_151385_1_) {
      ENCHANTMENT_ITERATOR_DAMAGE.user = p_151385_0_;
      ENCHANTMENT_ITERATOR_DAMAGE.target = p_151385_1_;
      if (p_151385_0_ != null) {
         applyEnchantmentModifierArray(ENCHANTMENT_ITERATOR_DAMAGE, p_151385_0_.getEquipmentAndArmor());
      }

      if (p_151385_0_ instanceof ME) {
         applyEnchantmentModifier(ENCHANTMENT_ITERATOR_DAMAGE, p_151385_0_.getHeldItemMainhand());
      }

   }

   public static int getMaxEnchantmentLevel(Fa p_185284_0_, Iw p_185284_1_) {
      Iterable<Qy> iterable = p_185284_0_.getEntityEquipment(p_185284_1_);
      if (iterable == null) {
         return 0;
      } else {
         int i = 0;
         Iterator var4 = iterable.iterator();

         while(var4.hasNext()) {
            Qy itemstack = (Qy)var4.next();
            int j = getEnchantmentLevel(p_185284_0_, itemstack);
            if (j > i) {
               i = j;
            }
         }

         return i;
      }
   }

   public static int getKnockbackModifier(Iw player) {
      return getMaxEnchantmentLevel(NJ.KNOCKBACK, player);
   }

   public static int getFireAspectModifier(Iw player) {
      return getMaxEnchantmentLevel(NJ.FIRE_ASPECT, player);
   }

   public static int getRespirationModifier(Iw p_185292_0_) {
      return getMaxEnchantmentLevel(NJ.RESPIRATION, p_185292_0_);
   }

   public static int getDepthStriderModifier(Iw p_185294_0_) {
      return getMaxEnchantmentLevel(NJ.DEPTH_STRIDER, p_185294_0_);
   }

   public static int getEfficiencyModifier(Iw p_185293_0_) {
      return getMaxEnchantmentLevel(NJ.EFFICIENCY, p_185293_0_);
   }

   public static int getFishingLuckBonus(Qy p_191529_0_) {
      return getEnchantmentLevel(NJ.LUCK_OF_THE_SEA, p_191529_0_);
   }

   public static int getFishingSpeedBonus(Qy p_191528_0_) {
      return getEnchantmentLevel(NJ.LURE, p_191528_0_);
   }

   public static int getLootingModifier(Iw p_185283_0_) {
      return getMaxEnchantmentLevel(NJ.LOOTING, p_185283_0_);
   }

   public static boolean getAquaAffinityModifier(Iw p_185287_0_) {
      return getMaxEnchantmentLevel(NJ.AQUA_AFFINITY, p_185287_0_) > 0;
   }

   public static boolean hasFrostWalkerEnchantment(Iw player) {
      return getMaxEnchantmentLevel(NJ.FROST_WALKER, player) > 0;
   }

   public static boolean hasBindingCurse(Qy p_190938_0_) {
      return getEnchantmentLevel(NJ.BINDING_CURSE, p_190938_0_) > 0;
   }

   public static boolean hasVanishingCurse(Qy p_190939_0_) {
      return getEnchantmentLevel(NJ.VANISHING_CURSE, p_190939_0_) > 0;
   }

   public static Qy getEnchantedItem(Fa p_92099_0_, Iw p_92099_1_) {
      List<Qy> list = p_92099_0_.getEntityEquipment(p_92099_1_);
      if (list.isEmpty()) {
         return Qy.EMPTY;
      } else {
         List<Qy> list1 = Lists.newArrayList();
         Iterator var4 = list.iterator();

         while(var4.hasNext()) {
            Qy itemstack = (Qy)var4.next();
            if (!itemstack.isEmpty() && getEnchantmentLevel(p_92099_0_, itemstack) > 0) {
               list1.add(itemstack);
            }
         }

         return list1.isEmpty() ? Qy.EMPTY : (Qy)list1.get(p_92099_1_.getRNG().nextInt(list1.size()));
      }
   }

   public static int calcItemStackEnchantability(Random rand, int enchantNum, int power, Qy stack) {
      OL item = stack.getItem();
      int i = item.getItemEnchantability();
      if (i <= 0) {
         return 0;
      } else {
         if (power > 15) {
            power = 15;
         }

         int j = rand.nextInt(8) + 1 + (power >> 1) + rand.nextInt(power + 1);
         if (enchantNum == 0) {
            return Math.max(j / 3, 1);
         } else {
            return enchantNum == 1 ? j * 2 / 3 + 1 : Math.max(j, power * 2);
         }
      }
   }

   public static Qy addRandomEnchantment(Random random, Qy stack, int level, boolean allowTreasure) {
      List<Fh> list = buildEnchantmentList(random, stack, level, allowTreasure);
      boolean flag = stack.getItem() == NK.BOOK;
      if (flag) {
         stack = new Qy(NK.ENCHANTED_BOOK);
      }

      Iterator var6 = list.iterator();

      while(var6.hasNext()) {
         Fh enchantmentdata = (Fh)var6.next();
         if (flag) {
            Pv.addEnchantment(stack, enchantmentdata);
         } else {
            stack.addEnchantment(enchantmentdata.enchantment, enchantmentdata.enchantmentLevel);
         }
      }

      return stack;
   }

   public static List<Fh> buildEnchantmentList(Random randomIn, Qy itemStackIn, int level, boolean allowTreasure) {
      List<Fh> list = Lists.newArrayList();
      OL item = itemStackIn.getItem();
      int i = item.getItemEnchantability();
      if (i <= 0) {
         return list;
      } else {
         level = level + 1 + randomIn.nextInt(i / 4 + 1) + randomIn.nextInt(i / 4 + 1);
         float f = (randomIn.nextFloat() + randomIn.nextFloat() - 1.0F) * 0.15F;
         level = MathHelper.clamp(Math.round((float)level + (float)level * f), 1, Integer.MAX_VALUE);
         List<Fh> list1 = getEnchantmentDatas(level, itemStackIn, allowTreasure);
         if (!list1.isEmpty()) {
            list.add(WeightedRandom.getRandomItem(randomIn, list1));

            while(randomIn.nextInt(50) <= level) {
               removeIncompatible(list1, (Fh)Util.getLastElement(list));
               if (list1.isEmpty()) {
                  break;
               }

               list.add(WeightedRandom.getRandomItem(randomIn, list1));
               level /= 2;
            }
         }

         return list;
      }
   }

   public static void removeIncompatible(List<Fh> p_185282_0_, Fh p_185282_1_) {
      Iterator<Fh> iterator = p_185282_0_.iterator();

      while(iterator.hasNext()) {
         if (!p_185282_1_.enchantment.isCompatibleWith(((Fh)iterator.next()).enchantment)) {
            iterator.remove();
         }
      }

   }

   public static List<Fh> getEnchantmentDatas(int p_185291_0_, Qy p_185291_1_, boolean allowTreasure) {
      List<Fh> list = Lists.newArrayList();
      OL item = p_185291_1_.getItem();
      boolean flag = p_185291_1_.getItem() == NK.BOOK;
      Iterator var6 = Fa.REGISTRY.iterator();

      while(true) {
         while(true) {
            Fa enchantment;
            do {
               do {
                  if (!var6.hasNext()) {
                     return list;
                  }

                  enchantment = (Fa)var6.next();
               } while(enchantment.isTreasureEnchantment() && !allowTreasure);
            } while(!enchantment.type.canEnchantItem(item) && !flag);

            for(int i = enchantment.getMaxLevel(); i > enchantment.getMinLevel() - 1; --i) {
               if (p_185291_0_ >= enchantment.getMinEnchantability(i) && p_185291_0_ <= enchantment.getMaxEnchantability(i)) {
                  list.add(new Fh(enchantment, i));
                  break;
               }
            }
         }
      }
   }
}
