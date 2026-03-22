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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;

public class LevitationTrigger implements ICriterionTrigger<Instance> {
   private static final ResourceLocation ID = new ResourceLocation("levitation");
   private final Map<PlayerAdvancements, Listeners> listeners = Maps.newHashMap();

   public LevitationTrigger() {
   }

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<Instance> listener) {
      Listeners levitationtrigger$listeners = (Listeners)this.listeners.get(playerAdvancementsIn);
      if (levitationtrigger$listeners == null) {
         levitationtrigger$listeners = new Listeners(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, levitationtrigger$listeners);
      }

      levitationtrigger$listeners.add(listener);
   }

   public void removeListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<Instance> listener) {
      Listeners levitationtrigger$listeners = (Listeners)this.listeners.get(playerAdvancementsIn);
      if (levitationtrigger$listeners != null) {
         levitationtrigger$listeners.remove(listener);
         if (levitationtrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(PlayerAdvancements playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      DistancePredicate distancepredicate = DistancePredicate.deserialize(json.get("distance"));
      MinMaxBounds minmaxbounds = MinMaxBounds.deserialize(json.get("duration"));
      return new Instance(distancepredicate, minmaxbounds);
   }

   public void trigger(EntityPlayerMP player, Vec3d startPos, int duration) {
      Listeners levitationtrigger$listeners = (Listeners)this.listeners.get(player.getAdvancements());
      if (levitationtrigger$listeners != null) {
         levitationtrigger$listeners.trigger(player, startPos, duration);
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

      public void trigger(EntityPlayerMP player, Vec3d startPos, int durationIn) {
         List<ICriterionTrigger.Listener<Instance>> list = null;
         Iterator var5 = this.listeners.iterator();

         ICriterionTrigger.Listener listener1;
         while(var5.hasNext()) {
            listener1 = (ICriterionTrigger.Listener)var5.next();
            if (((Instance)listener1.getCriterionInstance()).test(player, startPos, durationIn)) {
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
      private final DistancePredicate distance;
      private final MinMaxBounds duration;

      public Instance(DistancePredicate distance, MinMaxBounds duration) {
         super(LevitationTrigger.ID);
         this.distance = distance;
         this.duration = duration;
      }

      public boolean test(EntityPlayerMP player, Vec3d startPos, int durationIn) {
         return !this.distance.test(startPos.x, startPos.y, startPos.z, player.posX, player.posY, player.posZ) ? false : this.duration.test((float)durationIn);
      }
   }
}
