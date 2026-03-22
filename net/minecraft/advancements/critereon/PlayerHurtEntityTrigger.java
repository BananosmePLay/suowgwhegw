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

public class PlayerHurtEntityTrigger implements ICriterionTrigger<Instance> {
   private static final ResourceLocation ID = new ResourceLocation("player_hurt_entity");
   private final Map<PlayerAdvancements, Listeners> listeners = Maps.newHashMap();

   public PlayerHurtEntityTrigger() {
   }

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<Instance> listener) {
      Listeners playerhurtentitytrigger$listeners = (Listeners)this.listeners.get(playerAdvancementsIn);
      if (playerhurtentitytrigger$listeners == null) {
         playerhurtentitytrigger$listeners = new Listeners(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, playerhurtentitytrigger$listeners);
      }

      playerhurtentitytrigger$listeners.add(listener);
   }

   public void removeListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<Instance> listener) {
      Listeners playerhurtentitytrigger$listeners = (Listeners)this.listeners.get(playerAdvancementsIn);
      if (playerhurtentitytrigger$listeners != null) {
         playerhurtentitytrigger$listeners.remove(listener);
         if (playerhurtentitytrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(PlayerAdvancements playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      DamagePredicate damagepredicate = DamagePredicate.deserialize(json.get("damage"));
      EntityPredicate entitypredicate = EntityPredicate.deserialize(json.get("entity"));
      return new Instance(damagepredicate, entitypredicate);
   }

   public void trigger(EntityPlayerMP player, Entity entityIn, DamageSource source, float amountDealt, float amountTaken, boolean blocked) {
      Listeners playerhurtentitytrigger$listeners = (Listeners)this.listeners.get(player.getAdvancements());
      if (playerhurtentitytrigger$listeners != null) {
         playerhurtentitytrigger$listeners.trigger(player, entityIn, source, amountDealt, amountTaken, blocked);
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

      public void trigger(EntityPlayerMP player, Entity entity, DamageSource source, float dealt, float taken, boolean blocked) {
         List<ICriterionTrigger.Listener<Instance>> list = null;
         Iterator var8 = this.listeners.iterator();

         ICriterionTrigger.Listener listener1;
         while(var8.hasNext()) {
            listener1 = (ICriterionTrigger.Listener)var8.next();
            if (((Instance)listener1.getCriterionInstance()).test(player, entity, source, dealt, taken, blocked)) {
               if (list == null) {
                  list = Lists.newArrayList();
               }

               list.add(listener1);
            }
         }

         if (list != null) {
            var8 = list.iterator();

            while(var8.hasNext()) {
               listener1 = (ICriterionTrigger.Listener)var8.next();
               listener1.grantCriterion(this.playerAdvancements);
            }
         }

      }
   }

   public static class Instance extends AbstractCriterionInstance {
      private final DamagePredicate damage;
      private final EntityPredicate entity;

      public Instance(DamagePredicate damage, EntityPredicate entity) {
         super(PlayerHurtEntityTrigger.ID);
         this.damage = damage;
         this.entity = entity;
      }

      public boolean test(EntityPlayerMP player, Entity entity, DamageSource source, float dealt, float taken, boolean blocked) {
         return !this.damage.test(player, source, dealt, taken, blocked) ? false : this.entity.test(player, entity);
      }
   }
}
