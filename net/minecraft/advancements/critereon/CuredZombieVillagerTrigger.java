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
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;

public class CuredZombieVillagerTrigger implements ICriterionTrigger<Instance> {
   private static final ResourceLocation ID = new ResourceLocation("cured_zombie_villager");
   private final Map<PlayerAdvancements, Listeners> listeners = Maps.newHashMap();

   public CuredZombieVillagerTrigger() {
   }

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<Instance> listener) {
      Listeners curedzombievillagertrigger$listeners = (Listeners)this.listeners.get(playerAdvancementsIn);
      if (curedzombievillagertrigger$listeners == null) {
         curedzombievillagertrigger$listeners = new Listeners(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, curedzombievillagertrigger$listeners);
      }

      curedzombievillagertrigger$listeners.add(listener);
   }

   public void removeListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<Instance> listener) {
      Listeners curedzombievillagertrigger$listeners = (Listeners)this.listeners.get(playerAdvancementsIn);
      if (curedzombievillagertrigger$listeners != null) {
         curedzombievillagertrigger$listeners.remove(listener);
         if (curedzombievillagertrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(PlayerAdvancements playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      EntityPredicate entitypredicate = EntityPredicate.deserialize(json.get("zombie"));
      EntityPredicate entitypredicate1 = EntityPredicate.deserialize(json.get("villager"));
      return new Instance(entitypredicate, entitypredicate1);
   }

   public void trigger(EntityPlayerMP player, EntityZombie zombie, EntityVillager villager) {
      Listeners curedzombievillagertrigger$listeners = (Listeners)this.listeners.get(player.getAdvancements());
      if (curedzombievillagertrigger$listeners != null) {
         curedzombievillagertrigger$listeners.trigger(player, zombie, villager);
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

      public void trigger(EntityPlayerMP player, EntityZombie zombie, EntityVillager villager) {
         List<ICriterionTrigger.Listener<Instance>> list = null;
         Iterator var5 = this.listeners.iterator();

         ICriterionTrigger.Listener listener1;
         while(var5.hasNext()) {
            listener1 = (ICriterionTrigger.Listener)var5.next();
            if (((Instance)listener1.getCriterionInstance()).test(player, zombie, villager)) {
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
      private final EntityPredicate zombie;
      private final EntityPredicate villager;

      public Instance(EntityPredicate zombie, EntityPredicate villager) {
         super(CuredZombieVillagerTrigger.ID);
         this.zombie = zombie;
         this.villager = villager;
      }

      public boolean test(EntityPlayerMP player, EntityZombie zombie, EntityVillager villager) {
         return !this.zombie.test(player, zombie) ? false : this.villager.test(player, villager);
      }
   }
}
