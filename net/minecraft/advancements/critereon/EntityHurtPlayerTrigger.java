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
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;

public class EntityHurtPlayerTrigger implements ICriterionTrigger<Instance> {
   private static final ResourceLocation ID = new ResourceLocation("entity_hurt_player");
   private final Map<PlayerAdvancements, Listeners> listeners = Maps.newHashMap();

   public EntityHurtPlayerTrigger() {
   }

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<Instance> listener) {
      Listeners entityhurtplayertrigger$listeners = (Listeners)this.listeners.get(playerAdvancementsIn);
      if (entityhurtplayertrigger$listeners == null) {
         entityhurtplayertrigger$listeners = new Listeners(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, entityhurtplayertrigger$listeners);
      }

      entityhurtplayertrigger$listeners.add(listener);
   }

   public void removeListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<Instance> listener) {
      Listeners entityhurtplayertrigger$listeners = (Listeners)this.listeners.get(playerAdvancementsIn);
      if (entityhurtplayertrigger$listeners != null) {
         entityhurtplayertrigger$listeners.remove(listener);
         if (entityhurtplayertrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(PlayerAdvancements playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      DamagePredicate damagepredicate = DamagePredicate.deserialize(json.get("damage"));
      return new Instance(damagepredicate);
   }

   public void trigger(EntityPlayerMP player, DamageSource source, float amountDealt, float amountTaken, boolean wasBlocked) {
      Listeners entityhurtplayertrigger$listeners = (Listeners)this.listeners.get(player.getAdvancements());
      if (entityhurtplayertrigger$listeners != null) {
         entityhurtplayertrigger$listeners.trigger(player, source, amountDealt, amountTaken, wasBlocked);
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

      public Listeners(PlayerAdvancements p_i47439_1_) {
         this.playerAdvancements = p_i47439_1_;
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

      public void trigger(EntityPlayerMP player, DamageSource source, float amountDealt, float amountTaken, boolean wasBlocked) {
         List<ICriterionTrigger.Listener<Instance>> list = null;
         Iterator var7 = this.listeners.iterator();

         ICriterionTrigger.Listener listener1;
         while(var7.hasNext()) {
            listener1 = (ICriterionTrigger.Listener)var7.next();
            if (((Instance)listener1.getCriterionInstance()).test(player, source, amountDealt, amountTaken, wasBlocked)) {
               if (list == null) {
                  list = Lists.newArrayList();
               }

               list.add(listener1);
            }
         }

         if (list != null) {
            var7 = list.iterator();

            while(var7.hasNext()) {
               listener1 = (ICriterionTrigger.Listener)var7.next();
               listener1.grantCriterion(this.playerAdvancements);
            }
         }

      }
   }

   public static class Instance extends AbstractCriterionInstance {
      private final DamagePredicate damage;

      public Instance(DamagePredicate damage) {
         super(EntityHurtPlayerTrigger.ID);
         this.damage = damage;
      }

      public boolean test(EntityPlayerMP player, DamageSource source, float amountDealt, float amountTaken, boolean wasBlocked) {
         return this.damage.test(player, source, amountDealt, amountTaken, wasBlocked);
      }
   }
}
