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
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;

public class SummonedEntityTrigger implements ICriterionTrigger<Instance> {
   private static final ResourceLocation ID = new ResourceLocation("summoned_entity");
   private final Map<PlayerAdvancements, Listeners> listeners = Maps.newHashMap();

   public SummonedEntityTrigger() {
   }

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<Instance> listener) {
      Listeners summonedentitytrigger$listeners = (Listeners)this.listeners.get(playerAdvancementsIn);
      if (summonedentitytrigger$listeners == null) {
         summonedentitytrigger$listeners = new Listeners(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, summonedentitytrigger$listeners);
      }

      summonedentitytrigger$listeners.add(listener);
   }

   public void removeListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<Instance> listener) {
      Listeners summonedentitytrigger$listeners = (Listeners)this.listeners.get(playerAdvancementsIn);
      if (summonedentitytrigger$listeners != null) {
         summonedentitytrigger$listeners.remove(listener);
         if (summonedentitytrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(PlayerAdvancements playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      EntityPredicate entitypredicate = EntityPredicate.deserialize(json.get("entity"));
      return new Instance(entitypredicate);
   }

   public void trigger(EntityPlayerMP player, Entity entity) {
      Listeners summonedentitytrigger$listeners = (Listeners)this.listeners.get(player.getAdvancements());
      if (summonedentitytrigger$listeners != null) {
         summonedentitytrigger$listeners.trigger(player, entity);
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

      public void trigger(EntityPlayerMP player, Entity entity) {
         List<ICriterionTrigger.Listener<Instance>> list = null;
         Iterator var4 = this.listeners.iterator();

         ICriterionTrigger.Listener listener1;
         while(var4.hasNext()) {
            listener1 = (ICriterionTrigger.Listener)var4.next();
            if (((Instance)listener1.getCriterionInstance()).test(player, entity)) {
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
      private final EntityPredicate entity;

      public Instance(EntityPredicate entity) {
         super(SummonedEntityTrigger.ID);
         this.entity = entity;
      }

      public boolean test(EntityPlayerMP player, Entity entity) {
         return this.entity.test(player, entity);
      }
   }
}
