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
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;

public class KilledTrigger implements ICriterionTrigger<Instance> {
   private final Map<PlayerAdvancements, Listeners> listeners = Maps.newHashMap();
   private final ResourceLocation id;

   public KilledTrigger(ResourceLocation id) {
      this.id = id;
   }

   public ResourceLocation getId() {
      return this.id;
   }

   public void addListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<Instance> listener) {
      Listeners killedtrigger$listeners = (Listeners)this.listeners.get(playerAdvancementsIn);
      if (killedtrigger$listeners == null) {
         killedtrigger$listeners = new Listeners(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, killedtrigger$listeners);
      }

      killedtrigger$listeners.add(listener);
   }

   public void removeListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<Instance> listener) {
      Listeners killedtrigger$listeners = (Listeners)this.listeners.get(playerAdvancementsIn);
      if (killedtrigger$listeners != null) {
         killedtrigger$listeners.remove(listener);
         if (killedtrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(PlayerAdvancements playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      return new Instance(this.id, EntityPredicate.deserialize(json.get("entity")), DamageSourcePredicate.deserialize(json.get("killing_blow")));
   }

   public void trigger(EntityPlayerMP player, Entity entity, DamageSource source) {
      Listeners killedtrigger$listeners = (Listeners)this.listeners.get(player.getAdvancements());
      if (killedtrigger$listeners != null) {
         killedtrigger$listeners.trigger(player, entity, source);
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

      public void trigger(EntityPlayerMP player, Entity entity, DamageSource source) {
         List<ICriterionTrigger.Listener<Instance>> list = null;
         Iterator var5 = this.listeners.iterator();

         ICriterionTrigger.Listener listener1;
         while(var5.hasNext()) {
            listener1 = (ICriterionTrigger.Listener)var5.next();
            if (((Instance)listener1.getCriterionInstance()).test(player, entity, source)) {
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
      private final EntityPredicate entity;
      private final DamageSourcePredicate killingBlow;

      public Instance(ResourceLocation criterionIn, EntityPredicate entity, DamageSourcePredicate killingBlow) {
         super(criterionIn);
         this.entity = entity;
         this.killingBlow = killingBlow;
      }

      public boolean test(EntityPlayerMP player, Entity entity, DamageSource source) {
         return !this.killingBlow.test(player, source) ? false : this.entity.test(player, entity);
      }
   }
}
