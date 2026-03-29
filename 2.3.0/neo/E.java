package neo;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import java.util.Map;
import net.minecraft.util.ResourceLocation;

public class E implements ci<C> {
   private static final ResourceLocation ID = new ResourceLocation("cured_zombie_villager");
   private final Map<cl, D> listeners = Maps.newHashMap();

   public E() {
   }

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(cl playerAdvancementsIn, ch<C> listener) {
      D curedzombievillagertrigger$listeners = (D)this.listeners.get(playerAdvancementsIn);
      if (curedzombievillagertrigger$listeners == null) {
         curedzombievillagertrigger$listeners = new D(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, curedzombievillagertrigger$listeners);
      }

      curedzombievillagertrigger$listeners.add(listener);
   }

   public void removeListener(cl playerAdvancementsIn, ch<C> listener) {
      D curedzombievillagertrigger$listeners = (D)this.listeners.get(playerAdvancementsIn);
      if (curedzombievillagertrigger$listeners != null) {
         curedzombievillagertrigger$listeners.remove(listener);
         if (curedzombievillagertrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(cl playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public C deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      V entitypredicate = V.deserialize(json.get("zombie"));
      V entitypredicate1 = V.deserialize(json.get("villager"));
      return new C(entitypredicate, entitypredicate1);
   }

   public void trigger(MG player, Lk zombie, Mq villager) {
      D curedzombievillagertrigger$listeners = (D)this.listeners.get(player.getAdvancements());
      if (curedzombievillagertrigger$listeners != null) {
         curedzombievillagertrigger$listeners.trigger(player, zombie, villager);
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
