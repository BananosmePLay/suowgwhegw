package net.minecraft.advancements.critereon;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.util.ResourceLocation;

public class ImpossibleTrigger implements ICriterionTrigger<Instance> {
   private static final ResourceLocation ID = new ResourceLocation("impossible");

   public ImpossibleTrigger() {
   }

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<Instance> listener) {
   }

   public void removeListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<Instance> listener) {
   }

   public void removeAllListeners(PlayerAdvancements playerAdvancementsIn) {
   }

   public Instance deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      return new Instance();
   }

   // $FF: synthetic method
   // $FF: bridge method
   public ICriterionInstance deserializeInstance(JsonObject var1, JsonDeserializationContext var2) {
      return this.deserializeInstance(var1, var2);
   }

   public static class Instance extends AbstractCriterionInstance {
      public Instance() {
         super(ImpossibleTrigger.ID);
      }
   }
}
