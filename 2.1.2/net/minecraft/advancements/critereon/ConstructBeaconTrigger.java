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
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.ResourceLocation;

public class ConstructBeaconTrigger implements ICriterionTrigger<Instance> {
   private static final ResourceLocation ID = new ResourceLocation("construct_beacon");
   private final Map<PlayerAdvancements, Listeners> listeners = Maps.newHashMap();

   public ConstructBeaconTrigger() {
   }

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<Instance> listener) {
      Listeners constructbeacontrigger$listeners = (Listeners)this.listeners.get(playerAdvancementsIn);
      if (constructbeacontrigger$listeners == null) {
         constructbeacontrigger$listeners = new Listeners(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, constructbeacontrigger$listeners);
      }

      constructbeacontrigger$listeners.add(listener);
   }

   public void removeListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<Instance> listener) {
      Listeners constructbeacontrigger$listeners = (Listeners)this.listeners.get(playerAdvancementsIn);
      if (constructbeacontrigger$listeners != null) {
         constructbeacontrigger$listeners.remove(listener);
         if (constructbeacontrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(PlayerAdvancements playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      MinMaxBounds minmaxbounds = MinMaxBounds.deserialize(json.get("level"));
      return new Instance(minmaxbounds);
   }

   public void trigger(EntityPlayerMP player, TileEntityBeacon beacon) {
      Listeners constructbeacontrigger$listeners = (Listeners)this.listeners.get(player.getAdvancements());
      if (constructbeacontrigger$listeners != null) {
         constructbeacontrigger$listeners.trigger(beacon);
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

      public void trigger(TileEntityBeacon beacon) {
         List<ICriterionTrigger.Listener<Instance>> list = null;
         Iterator var3 = this.listeners.iterator();

         ICriterionTrigger.Listener listener1;
         while(var3.hasNext()) {
            listener1 = (ICriterionTrigger.Listener)var3.next();
            if (((Instance)listener1.getCriterionInstance()).test(beacon)) {
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
      private final MinMaxBounds level;

      public Instance(MinMaxBounds level) {
         super(ConstructBeaconTrigger.ID);
         this.level = level;
      }

      public boolean test(TileEntityBeacon beacon) {
         return this.level.test((float)beacon.getLevels());
      }
   }
}
