package neo;

import com.google.common.base.Optional;
import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class R implements ci<P> {
   private static final ResourceLocation ID = new ResourceLocation("enter_block");
   private final Map<cl, Q> listeners = Maps.newHashMap();

   public R() {
   }

   public ResourceLocation getId() {
      return ID;
   }

   public void addListener(cl playerAdvancementsIn, ch<P> listener) {
      Q enterblocktrigger$listeners = (Q)this.listeners.get(playerAdvancementsIn);
      if (enterblocktrigger$listeners == null) {
         enterblocktrigger$listeners = new Q(playerAdvancementsIn);
         this.listeners.put(playerAdvancementsIn, enterblocktrigger$listeners);
      }

      enterblocktrigger$listeners.add(listener);
   }

   public void removeListener(cl playerAdvancementsIn, ch<P> listener) {
      Q enterblocktrigger$listeners = (Q)this.listeners.get(playerAdvancementsIn);
      if (enterblocktrigger$listeners != null) {
         enterblocktrigger$listeners.remove(listener);
         if (enterblocktrigger$listeners.isEmpty()) {
            this.listeners.remove(playerAdvancementsIn);
         }
      }

   }

   public void removeAllListeners(cl playerAdvancementsIn) {
      this.listeners.remove(playerAdvancementsIn);
   }

   public P deserializeInstance(JsonObject json, JsonDeserializationContext context) {
      co block = null;
      if (json.has("block")) {
         ResourceLocation resourcelocation = new ResourceLocation(JsonUtils.getString(json, "block"));
         if (!co.REGISTRY.containsKey(resourcelocation)) {
            throw new JsonSyntaxException("Unknown block type '" + resourcelocation + "'");
         }

         block = (co)co.REGISTRY.getObject(resourcelocation);
      }

      Map<hT<?>, Object> map = null;
      if (json.has("state")) {
         if (block == null) {
            throw new JsonSyntaxException("Can't define block state without a specific block type");
         }

         ii blockstatecontainer = block.getBlockState();

         hT iproperty;
         Optional optional;
         for(Iterator var6 = JsonUtils.getJsonObject(json, "state").entrySet().iterator(); var6.hasNext(); map.put(iproperty, optional.get())) {
            Map.Entry<String, JsonElement> entry = (Map.Entry)var6.next();
            iproperty = blockstatecontainer.getProperty((String)entry.getKey());
            if (iproperty == null) {
               throw new JsonSyntaxException("Unknown block state property '" + (String)entry.getKey() + "' for block '" + co.REGISTRY.getNameForObject(block) + "'");
            }

            String s = JsonUtils.getString((JsonElement)entry.getValue(), (String)entry.getKey());
            optional = iproperty.parseValue(s);
            if (!optional.isPresent()) {
               throw new JsonSyntaxException("Invalid block state value '" + s + "' for property '" + (String)entry.getKey() + "' on block '" + co.REGISTRY.getNameForObject(block) + "'");
            }

            if (map == null) {
               map = Maps.newHashMap();
            }
         }
      }

      return new P(block, map);
   }

   public void trigger(MG player, in state) {
      Q enterblocktrigger$listeners = (Q)this.listeners.get(player.getAdvancements());
      if (enterblocktrigger$listeners != null) {
         enterblocktrigger$listeners.trigger(state);
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
