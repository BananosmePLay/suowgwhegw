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
import net.minecraft.util.math.BlockPos;

public class UsedEnderEyeTrigger implements ICriterionTrigger<Instance> {
   private static final ResourceLocation ID = new ResourceLocation("used_ender_eye");
   private final Map<PlayerAdvancements, Listeners> listeners = Maps.newHashMap();

   public UsedEnderEyeTrigger() {
   }

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<Instance> listener) {
      Listeners usedendereyetrigger$listeners = (Listeners)this.listeners.get(playerAdvancementsIn);
      if (usedendereyetrigger$listeners == null) {
         usedendereyetrigger$listeners = new Listeners(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, usedendereyetrigger$listeners);
      }

      usedendereyetrigger$listeners.add(listener);
   }

   public void removeListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<Instance> listener) {
      Listeners usedendereyetrigger$listeners = (Listeners)this.listeners.get(playerAdvancementsIn);
      if (usedendereyetrigger$listeners != null) {
         usedendereyetrigger$listeners.remove(listener);
         if (usedendereyetrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(PlayerAdvancements playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      MinMaxBounds minmaxbounds = MinMaxBounds.deserialize(json.get("distance"));
      return new Instance(minmaxbounds);
   }

   public void trigger(EntityPlayerMP player, BlockPos pos) {
      Listeners usedendereyetrigger$listeners = (Listeners)this.listeners.get(player.getAdvancements());
      if (usedendereyetrigger$listeners != null) {
         double d0 = player.posX - (double)pos.getX();
         double d1 = player.posZ - (double)pos.getZ();
         usedendereyetrigger$listeners.trigger(d0 * d0 + d1 * d1);
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

      public void trigger(double distanceSq) {
         List<ICriterionTrigger.Listener<Instance>> list = null;
         Iterator var4 = this.listeners.iterator();

         ICriterionTrigger.Listener listener1;
         while(var4.hasNext()) {
            listener1 = (ICriterionTrigger.Listener)var4.next();
            if (((Instance)listener1.getCriterionInstance()).test(distanceSq)) {
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
      private final MinMaxBounds distance;

      public Instance(MinMaxBounds distance) {
         super(UsedEnderEyeTrigger.ID);
         this.distance = distance;
      }

      public boolean test(double distanceSq) {
         return this.distance.testSquare(distanceSq);
      }
   }
}
