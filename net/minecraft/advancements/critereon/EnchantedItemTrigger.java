package net.minecraft.advancements.critereon;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class EnchantedItemTrigger implements ICriterionTrigger<Instance> {
   private static final ResourceLocation ID = new ResourceLocation("enchanted_item");
   private final Map<PlayerAdvancements, Listeners> listeners = Maps.newHashMap();

   public EnchantedItemTrigger() {
   }

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<Instance> listener) {
      Listeners enchanteditemtrigger$listeners = (Listeners)this.listeners.get(playerAdvancementsIn);
      if (enchanteditemtrigger$listeners == null) {
         enchanteditemtrigger$listeners = new Listeners(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, enchanteditemtrigger$listeners);
      }

      enchanteditemtrigger$listeners.add(listener);
   }

   public void removeListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<Instance> listener) {
      Listeners enchanteditemtrigger$listeners = (Listeners)this.listeners.get(playerAdvancementsIn);
      if (enchanteditemtrigger$listeners != null) {
         enchanteditemtrigger$listeners.remove(listener);
         if (enchanteditemtrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(PlayerAdvancements playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      ItemPredicate itempredicate = ItemPredicate.deserialize(json.get("item"));
      MinMaxBounds minmaxbounds = MinMaxBounds.deserialize(json.get("levels"));
      return new Instance(itempredicate, minmaxbounds);
   }

   public void trigger(EntityPlayerMP player, ItemStack item, int levelsSpent) {
      Listeners enchanteditemtrigger$listeners = (Listeners)this.listeners.get(player.getAdvancements());
      if (enchanteditemtrigger$listeners != null) {
         enchanteditemtrigger$listeners.trigger(item, levelsSpent);
      }

   }

   // $FF: synthetic method
   // $FF: bridge method
   public ICriterionInstance deserializeInstance(JsonObject var1, JsonDeserializationContext var2) {
      return this.deserializeInstance(var1, var2);
   }

   static class Listeners {
      private final PlayerAdvancements playerAdvancements;
      private final Set<ICriterionTrigger.Listener<Instance>> listeners = Sets.newHashSet();

      public Listeners(PlayerAdvancements playerAdvancementsIn) {
         this.playerAdvancements = playerAdvancementsIn;
      }

      public boolean isEmpty() {
         return this.listeners.isEmpty();
      }

      public void add(ICriterionTrigger.Listener<Instance> listener) {
         this.listeners.add(listener);
      }

      public void remove(ICriterionTrigger.Listener<Instance> listener) {
         this.listeners.remove(listener);
      }

      public void trigger(ItemStack item, int levelsIn) {
         List<ICriterionTrigger.Listener<Instance>> list = null;
         Iterator var4 = this.listeners.iterator();

         ICriterionTrigger.Listener listener1;
         while(var4.hasNext()) {
            listener1 = (ICriterionTrigger.Listener)var4.next();
            if (((Instance)listener1.getCriterionInstance()).test(item, levelsIn)) {
               if (list == null) {
                  list = Lists.newArrayList();
               }

               list.add(listener1);
            }
         }

         if (list != null) {
            var4 = list.iterator();

            while(var4.hasNext()) {
               listener1 = (ICriterionTrigger.Listener)var4.next();
               listener1.grantCriterion(this.playerAdvancements);
            }
         }

      }
   }

   public static class Instance extends AbstractCriterionInstance {
      private final ItemPredicate item;
      private final MinMaxBounds levels;

      public Instance(ItemPredicate item, MinMaxBounds levels) {
         super(EnchantedItemTrigger.ID);
         this.item = item;
         this.levels = levels;
      }

      public boolean test(ItemStack item, int levelsIn) {
         return !this.item.test(item) ? false : this.levels.test((float)levelsIn);
      }
   }
}
