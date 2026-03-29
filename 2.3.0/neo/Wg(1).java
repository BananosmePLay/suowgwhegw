package neo;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Tuple;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;

public class Wg {
   public Wg() {
   }

   public static List<VZ> getEffectsFromStack(Qy stack) {
      return getEffectsFromTag(stack.getTagCompound());
   }

   public static List<VZ> mergeEffects(Wf potionIn, Collection<VZ> effects) {
      List<VZ> list = Lists.newArrayList();
      list.addAll(potionIn.getEffects());
      list.addAll(effects);
      return list;
   }

   public static List<VZ> getEffectsFromTag(@Nullable QQ tag) {
      List<VZ> list = Lists.newArrayList();
      list.addAll(getPotionTypeFromNBT(tag).getEffects());
      addCustomPotionEffectToList(tag, list);
      return list;
   }

   public static List<VZ> getFullEffectsFromItem(Qy itemIn) {
      return getFullEffectsFromTag(itemIn.getTagCompound());
   }

   public static List<VZ> getFullEffectsFromTag(@Nullable QQ tag) {
      List<VZ> list = Lists.newArrayList();
      addCustomPotionEffectToList(tag, list);
      return list;
   }

   public static void addCustomPotionEffectToList(@Nullable QQ tag, List<VZ> effectList) {
      if (tag != null && tag.hasKey("CustomPotionEffects", 9)) {
         QW nbttaglist = tag.getTagList("CustomPotionEffects", 10);

         for(int i = 0; i < nbttaglist.tagCount(); ++i) {
            QQ nbttagcompound = nbttaglist.getCompoundTagAt(i);
            VZ potioneffect = VZ.readCustomPotionEffectFromNBT(nbttagcompound);
            if (potioneffect != null) {
               effectList.add(potioneffect);
            }
         }
      }

   }

   public static int getColor(Qy p_190932_0_) {
      QQ nbttagcompound = p_190932_0_.getTagCompound();
      if (nbttagcompound != null && nbttagcompound.hasKey("CustomPotionColor", 99)) {
         return nbttagcompound.getInteger("CustomPotionColor");
      } else {
         return getPotionFromItem(p_190932_0_) == NN.EMPTY ? 16253176 : getPotionColorFromEffectList(getEffectsFromStack(p_190932_0_));
      }
   }

   public static int getPotionColor(Wf potionIn) {
      return potionIn == NN.EMPTY ? 16253176 : getPotionColorFromEffectList(potionIn.getEffects());
   }

   public static int getPotionColorFromEffectList(Collection<VZ> effects) {
      int i = 3694022;
      if (effects.isEmpty()) {
         return XH.isCustomColors() ? bjy.getPotionColor((VW)null, i) : 3694022;
      } else {
         float f = 0.0F;
         float f1 = 0.0F;
         float f2 = 0.0F;
         int j = 0;
         Iterator var6 = effects.iterator();

         while(var6.hasNext()) {
            VZ potioneffect = (VZ)var6.next();
            if (potioneffect.doesShowParticles()) {
               int k = potioneffect.getPotion().getLiquidColor();
               if (XH.isCustomColors()) {
                  k = bjy.getPotionColor(potioneffect.getPotion(), k);
               }

               int l = potioneffect.getAmplifier() + 1;
               f += (float)(l * (k >> 16 & 255)) / 255.0F;
               f1 += (float)(l * (k >> 8 & 255)) / 255.0F;
               f2 += (float)(l * (k >> 0 & 255)) / 255.0F;
               j += l;
            }
         }

         if (j == 0) {
            return 0;
         } else {
            f = f / (float)j * 255.0F;
            f1 = f1 / (float)j * 255.0F;
            f2 = f2 / (float)j * 255.0F;
            return (int)f << 16 | (int)f1 << 8 | (int)f2;
         }
      }
   }

   public static Wf getPotionFromItem(Qy itemIn) {
      return getPotionTypeFromNBT(itemIn.getTagCompound());
   }

   public static Wf getPotionTypeFromNBT(@Nullable QQ tag) {
      return tag == null ? NN.EMPTY : Wf.getPotionTypeForName(tag.getString("Potion"));
   }

   public static Qy addPotionToItemStack(Qy itemIn, Wf potionIn) {
      ResourceLocation resourcelocation = (ResourceLocation)Wf.REGISTRY.getNameForObject(potionIn);
      QQ nbttagcompound;
      if (potionIn == NN.EMPTY) {
         if (itemIn.hasTagCompound()) {
            nbttagcompound = itemIn.getTagCompound();
            nbttagcompound.removeTag("Potion");
            if (nbttagcompound.isEmpty()) {
               itemIn.setTagCompound((QQ)null);
            }
         }
      } else {
         nbttagcompound = itemIn.hasTagCompound() ? itemIn.getTagCompound() : new QQ();
         nbttagcompound.setString("Potion", resourcelocation.toString());
         itemIn.setTagCompound(nbttagcompound);
      }

      return itemIn;
   }

   public static Qy appendEffects(Qy itemIn, Collection<VZ> effects) {
      if (effects.isEmpty()) {
         return itemIn;
      } else {
         QQ nbttagcompound = (QQ)MoreObjects.firstNonNull(itemIn.getTagCompound(), new QQ());
         QW nbttaglist = nbttagcompound.getTagList("CustomPotionEffects", 9);
         Iterator var4 = effects.iterator();

         while(var4.hasNext()) {
            VZ potioneffect = (VZ)var4.next();
            nbttaglist.appendTag(potioneffect.writeCustomPotionEffectToNBT(new QQ()));
         }

         nbttagcompound.setTag("CustomPotionEffects", nbttaglist);
         itemIn.setTagCompound(nbttagcompound);
         return itemIn;
      }
   }

   public static void addPotionTooltip(Qy itemIn, List<String> lores, float durationFactor) {
      List<VZ> list = getEffectsFromStack(itemIn);
      List<Tuple<String, FW>> list1 = Lists.newArrayList();
      Iterator var14;
      if (list.isEmpty()) {
         String s = I18n.translateToLocal("effect.none").trim();
         lores.add(TextFormatting.GRAY + s);
      } else {
         var14 = list.iterator();

         while(var14.hasNext()) {
            VZ potioneffect = (VZ)var14.next();
            String s1 = I18n.translateToLocal(potioneffect.getEffectName()).trim();
            VW potion = potioneffect.getPotion();
            Map<FY, FW> map = potion.getAttributeModifierMap();
            if (!map.isEmpty()) {
               Iterator var10 = map.entrySet().iterator();

               while(var10.hasNext()) {
                  Map.Entry<FY, FW> entry = (Map.Entry)var10.next();
                  FW attributemodifier = (FW)entry.getValue();
                  FW attributemodifier1 = new FW(attributemodifier.getName(), potion.getAttributeModifierAmount(potioneffect.getAmplifier(), attributemodifier), attributemodifier.getOperation());
                  list1.add(new Tuple(((FY)entry.getKey()).getName(), attributemodifier1));
               }
            }

            if (potioneffect.getAmplifier() > 0) {
               s1 = s1 + " " + I18n.translateToLocal("potion.potency." + potioneffect.getAmplifier()).trim();
            }

            if (potioneffect.getDuration() > 20) {
               s1 = s1 + " (" + VW.getPotionDurationString(potioneffect, durationFactor) + ")";
            }

            if (potion.isBadEffect()) {
               lores.add(TextFormatting.RED + s1);
            } else {
               lores.add(TextFormatting.BLUE + s1);
            }
         }
      }

      if (!list1.isEmpty()) {
         lores.add("");
         lores.add(TextFormatting.DARK_PURPLE + I18n.translateToLocal("potion.whenDrank"));
         var14 = list1.iterator();

         while(var14.hasNext()) {
            Tuple<String, FW> tuple = (Tuple)var14.next();
            FW attributemodifier2 = (FW)tuple.getSecond();
            double d0 = attributemodifier2.getAmount();
            double d1;
            if (attributemodifier2.getOperation() != 1 && attributemodifier2.getOperation() != 2) {
               d1 = attributemodifier2.getAmount();
            } else {
               d1 = attributemodifier2.getAmount() * 100.0;
            }

            if (d0 > 0.0) {
               lores.add(TextFormatting.BLUE + I18n.translateToLocalFormatted("attribute.modifier.plus." + attributemodifier2.getOperation(), Qy.DECIMALFORMAT.format(d1), I18n.translateToLocal("attribute.name." + (String)tuple.getFirst())));
            } else if (d0 < 0.0) {
               d1 *= -1.0;
               lores.add(TextFormatting.RED + I18n.translateToLocalFormatted("attribute.modifier.take." + attributemodifier2.getOperation(), Qy.DECIMALFORMAT.format(d1), I18n.translateToLocal("attribute.name." + (String)tuple.getFirst())));
            }
         }
      }

   }
}
