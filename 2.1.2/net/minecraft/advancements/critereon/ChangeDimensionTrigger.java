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
import javax.annotation.Nullable;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;

public class ChangeDimensionTrigger implements ICriterionTrigger<Instance> {
   private static final ResourceLocation ID = new ResourceLocation("changed_dimension");
   private final Map<PlayerAdvancements, Listeners> listeners = Maps.newHashMap();

   public ChangeDimensionTrigger() {
   }

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<Instance> listener) {
      Listeners changedimensiontrigger$listeners = (Listeners)this.listeners.get(playerAdvancementsIn);
      if (changedimensiontrigger$listeners == null) {
         changedimensiontrigger$listeners = new Listeners(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, changedimensiontrigger$listeners);
      }

      changedimensiontrigger$listeners.add(listener);
   }

   public void removeListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<Instance> listener) {
      Listeners changedimensiontrigger$listeners = (Listeners)this.listeners.get(playerAdvancementsIn);
      if (changedimensiontrigger$listeners != null) {
         changedimensiontrigger$listeners.remove(listener);
         if (changedimensiontrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(PlayerAdvancements playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      DimensionType dimensiontype = json.has("from") ? DimensionType.byName(JsonUtils.getString(json, "from")) : null;
      DimensionType dimensiontype1 = json.has("to") ? DimensionType.byName(JsonUtils.getString(json, "to")) : null;
      return new Instance(dimensiontype, dimensiontype1);
   }

   public void trigger(EntityPlayerMP player, DimensionType from, DimensionType to) {
      Listeners changedimensiontrigger$listeners = (Listeners)this.listeners.get(player.getAdvancements());
      if (changedimensiontrigger$listeners != null) {
         changedimensiontrigger$listeners.trigger(from, to);
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

      public void trigger(DimensionType from, DimensionType to) {
         List<ICriterionTrigger.Listener<Instance>> list = null;
         Iterator var4 = this.listeners.iterator();

         ICriterionTrigger.Listener listener1;
         while(var4.hasNext()) {
            listener1 = (ICriterionTrigger.Listener)var4.next();
            if (((Instance)listener1.getCriterionInstance()).test(from, to)) {
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
      @Nullable
      private final DimensionType from;
      @Nullable
      private final DimensionType to;

      public Instance(@Nullable DimensionType from, @Nullable DimensionType to) {
         super(ChangeDimensionTrigger.ID);
         this.from = from;
         this.to = to;
      }

      public boolean test(DimensionType from, DimensionType to) {
         if (this.from != null && this.from != from) {
            return false;
         } else {
            return this.to == null || this.to == to;
         }
      }
   }
}
