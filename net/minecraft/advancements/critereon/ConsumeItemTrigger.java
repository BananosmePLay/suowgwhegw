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

public class ConsumeItemTrigger implements ICriterionTrigger<Instance> {
   private static final ResourceLocation ID = new ResourceLocation("consume_item");
   private final Map<PlayerAdvancements, Listeners> listeners = Maps.newHashMap();

   public ConsumeItemTrigger() {
   }

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<Instance> listener) {
      Listeners consumeitemtrigger$listeners = (Listeners)this.listeners.get(playerAdvancementsIn);
      if (consumeitemtrigger$listeners == null) {
         consumeitemtrigger$listeners = new Listeners(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, consumeitemtrigger$listeners);
      }

      consumeitemtrigger$listeners.add(listener);
   }

   public void removeListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<Instance> listener) {
      Listeners consumeitemtrigger$listeners = (Listeners)this.listeners.get(playerAdvancementsIn);
      if (consumeitemtrigger$listeners != null) {
         consumeitemtrigger$listeners.remove(listener);
         if (consumeitemtrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(PlayerAdvancements playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      ItemPredicate itempredicate = ItemPredicate.deserialize(json.get("item"));
      return new Instance(itempredicate);
   }

   public void trigger(EntityPlayerMP player, ItemStack item) {
      Listeners consumeitemtrigger$listeners = (Listeners)this.listeners.get(player.getAdvancements());
      if (consumeitemtrigger$listeners != null) {
         consumeitemtrigger$listeners.trigger(item);
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

      public void trigger(ItemStack item) {
         List<ICriterionTrigger.Listener<Instance>> list = null;
         Iterator var3 = this.listeners.iterator();

         ICriterionTrigger.Listener listener1;
         while(var3.hasNext()) {
            listener1 = (ICriterionTrigger.Listener)var3.next();
            if (((Instance)listener1.getCriterionInstance()).test(item)) {
               if (list == null) {
                  list = Lists.newArrayList();
               }

               list.add(listener1);
            }
         }

         if (list != null) {
            var3 = list.iterator();

            while(var3.hasNext()) {
               listener1 = (ICriterionTrigger.Listener)var3.next();
               listener1.grantCriterion(this.playerAdvancements);
            }
         }

      }
   }

   public static class Instance extends AbstractCriterionInstance {
      private final ItemPredicate item;

      public Instance(ItemPredicate item) {
         super(ConsumeItemTrigger.ID);
         this.item = item;
      }

      public boolean test(ItemStack item) {
         return this.item.test(item);
      }
   }
}
