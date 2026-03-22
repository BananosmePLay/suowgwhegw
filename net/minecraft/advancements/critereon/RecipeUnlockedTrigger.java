package net.minecraft.advancements.critereon;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class RecipeUnlockedTrigger implements ICriterionTrigger<Instance> {
   private static final ResourceLocation ID = new ResourceLocation("recipe_unlocked");
   private final Map<PlayerAdvancements, Listeners> listeners = Maps.newHashMap();

   public RecipeUnlockedTrigger() {
   }

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<Instance> listener) {
      Listeners recipeunlockedtrigger$listeners = (Listeners)this.listeners.get(playerAdvancementsIn);
      if (recipeunlockedtrigger$listeners == null) {
         recipeunlockedtrigger$listeners = new Listeners(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, recipeunlockedtrigger$listeners);
      }

      recipeunlockedtrigger$listeners.add(listener);
   }

   public void removeListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<Instance> listener) {
      Listeners recipeunlockedtrigger$listeners = (Listeners)this.listeners.get(playerAdvancementsIn);
      if (recipeunlockedtrigger$listeners != null) {
         recipeunlockedtrigger$listeners.remove(listener);
         if (recipeunlockedtrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(PlayerAdvancements playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      ResourceLocation resourcelocation = new ResourceLocation(JsonUtils.getString(json, "recipe"));
      IRecipe irecipe = CraftingManager.getRecipe(resourcelocation);
      if (irecipe == null) {
         throw new JsonSyntaxException("Unknown recipe '" + resourcelocation + "'");
      } else {
         return new Instance(irecipe);
      }
   }

   public void trigger(EntityPlayerMP player, IRecipe recipe) {
      Listeners recipeunlockedtrigger$listeners = (Listeners)this.listeners.get(player.getAdvancements());
      if (recipeunlockedtrigger$listeners != null) {
         recipeunlockedtrigger$listeners.trigger(recipe);
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

      public void trigger(IRecipe recipe) {
         List<ICriterionTrigger.Listener<Instance>> list = null;
         Iterator var3 = this.listeners.iterator();

         ICriterionTrigger.Listener listener1;
         while(var3.hasNext()) {
            listener1 = (ICriterionTrigger.Listener)var3.next();
            if (((Instance)listener1.getCriterionInstance()).test(recipe)) {
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
      private final IRecipe recipe;

      public Instance(IRecipe recipe) {
         super(RecipeUnlockedTrigger.ID);
         this.recipe = recipe;
      }

      public boolean test(IRecipe recipe) {
         return this.recipe == recipe;
      }
   }
}
