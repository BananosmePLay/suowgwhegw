package neo;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import java.util.Map;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class bF implements ci<bD> {
   private static final ResourceLocation ID = new ResourceLocation("recipe_unlocked");
   private final Map<cl, bE> listeners = Maps.newHashMap();

   public bF() {
   }

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(cl playerAdvancementsIn, ch<bD> listener) {
      bE recipeunlockedtrigger$listeners = (bE)this.listeners.get(playerAdvancementsIn);
      if (recipeunlockedtrigger$listeners == null) {
         recipeunlockedtrigger$listeners = new bE(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, recipeunlockedtrigger$listeners);
      }

      recipeunlockedtrigger$listeners.add(listener);
   }

   public void removeListener(cl playerAdvancementsIn, ch<bD> listener) {
      bE recipeunlockedtrigger$listeners = (bE)this.listeners.get(playerAdvancementsIn);
      if (recipeunlockedtrigger$listeners != null) {
         recipeunlockedtrigger$listeners.remove(listener);
         if (recipeunlockedtrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(cl playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public bD deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      ResourceLocation resourcelocation = new ResourceLocation(JsonUtils.getString(json, "recipe"));
      NT irecipe = NP.getRecipe(resourcelocation);
      if (irecipe == null) {
         throw new JsonSyntaxException("Unknown recipe '" + resourcelocation + "'");
      } else {
         return new bD(irecipe);
      }
   }

   public void trigger(MG player, NT recipe) {
      bE recipeunlockedtrigger$listeners = (bE)this.listeners.get(player.getAdvancements());
      if (recipeunlockedtrigger$listeners != null) {
         recipeunlockedtrigger$listeners.trigger(recipe);
      }

   }

   // $FF: synthetic method
   // $FF: bridge method
   public cg deserializeInstance(JsonObject var1, JsonDeserializationContext var2) {
      return this.deserializeInstance(var1, var2);
   }

   // $FF: synthetic method
   static ResourceLocation access$000() {
      return ID;
   }
}
