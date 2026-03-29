package net.minecraft.advancements.critereon;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;

public class TickTrigger implements ICriterionTrigger<Instance> {
   public static final ResourceLocation ID = new ResourceLocation("tick");
   private final Map<PlayerAdvancements, Listeners> listeners = Maps.newHashMap();

   public TickTrigger() {
   }

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<Instance> listener) {
      Listeners ticktrigger$listeners = (Listeners)this.listeners.get(playerAdvancementsIn);
      if (ticktrigger$listeners == null) {
         ticktrigger$listeners = new Listeners(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, ticktrigger$listeners);
      }

      ticktrigger$listeners.add(listener);
   }

   public void removeListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<Instance> listener) {
      Listeners ticktrigger$listeners = (Listeners)this.listeners.get(playerAdvancementsIn);
      if (ticktrigger$listeners != null) {
         ticktrigger$listeners.remove(listener);
         if (ticktrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(PlayerAdvancements playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      return new Instance();
   }

   public void trigger(EntityPlayerMP player) {
      Listeners ticktrigger$listeners = (Listeners)this.listeners.get(player.getAdvancements());
      if (ticktrigger$listeners != null) {
         ticktrigger$listeners.trigger();
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

      public void trigger() {
         Iterator var1 = Lists.newArrayList((Iterable)this.listeners).iterator();

         while(var1.hasNext()) {
            ICriterionTrigger.Listener<Instance> listener = (ICriterionTrigger.Listener)var1.next();
            listener.grantCriterion(this.playerAdvancements);
         }

      }
   }

   public static class Instance extends AbstractCriterionInstance {
      public Instance() {
         super(TickTrigger.ID);
      }
   }
}
