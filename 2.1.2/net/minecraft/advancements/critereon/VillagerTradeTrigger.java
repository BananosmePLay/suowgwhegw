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
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class VillagerTradeTrigger implements ICriterionTrigger<Instance> {
   private static final ResourceLocation ID = new ResourceLocation("villager_trade");
   private final Map<PlayerAdvancements, Listeners> listeners = Maps.newHashMap();

   public VillagerTradeTrigger() {
   }

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<Instance> listener) {
      Listeners villagertradetrigger$listeners = (Listeners)this.listeners.get(playerAdvancementsIn);
      if (villagertradetrigger$listeners == null) {
         villagertradetrigger$listeners = new Listeners(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, villagertradetrigger$listeners);
      }

      villagertradetrigger$listeners.add(listener);
   }

   public void removeListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<Instance> listener) {
      Listeners villagertradetrigger$listeners = (Listeners)this.listeners.get(playerAdvancementsIn);
      if (villagertradetrigger$listeners != null) {
         villagertradetrigger$listeners.remove(listener);
         if (villagertradetrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(PlayerAdvancements playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      EntityPredicate entitypredicate = EntityPredicate.deserialize(json.get("villager"));
      ItemPredicate itempredicate = ItemPredicate.deserialize(json.get("item"));
      return new Instance(entitypredicate, itempredicate);
   }

   public void trigger(EntityPlayerMP player, EntityVillager villager, ItemStack item) {
      Listeners villagertradetrigger$listeners = (Listeners)this.listeners.get(player.getAdvancements());
      if (villagertradetrigger$listeners != null) {
         villagertradetrigger$listeners.trigger(player, villager, item);
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

      public void trigger(EntityPlayerMP player, EntityVillager villager, ItemStack item) {
         List<ICriterionTrigger.Listener<Instance>> list = null;
         Iterator var5 = this.listeners.iterator();

         ICriterionTrigger.Listener listener1;
         while(var5.hasNext()) {
            listener1 = (ICriterionTrigger.Listener)var5.next();
            if (((Instance)listener1.getCriterionInstance()).test(player, villager, item)) {
               if (list == null) {
                  list = Lists.newArrayList();
               }

               list.add(listener1);
            }
         }

         if (list != null) {
            var5 = list.iterator();

            while(var5.hasNext()) {
               listener1 = (ICriterionTrigger.Listener)var5.next();
               listener1.grantCriterion(this.playerAdvancements);
            }
         }

      }
   }

   public static class Instance extends AbstractCriterionInstance {
      private final EntityPredicate villager;
      private final ItemPredicate item;

      public Instance(EntityPredicate villager, ItemPredicate item) {
         super(VillagerTradeTrigger.ID);
         this.villager = villager;
         this.item = item;
      }

      public boolean test(EntityPlayerMP player, EntityVillager villager, ItemStack item) {
         return !this.villager.test(player, villager) ? false : this.item.test(item);
      }
   }
}
