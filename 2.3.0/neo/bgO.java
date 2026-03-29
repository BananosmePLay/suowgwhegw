package neo;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSyntaxException;
import java.util.Iterator;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.JsonUtils;

class bgO {
   private final String modifierName;
   private final String attributeName;
   private final int operation;
   private final bhC amount;
   @Nullable
   private final UUID uuid;
   private final EntityEquipmentSlot[] slots;

   private bgO(String modifName, String attrName, int operationIn, bhC randomAmount, EntityEquipmentSlot[] slotsIn, @Nullable UUID uuidIn) {
      this.modifierName = modifName;
      this.attributeName = attrName;
      this.operation = operationIn;
      this.amount = randomAmount;
      this.uuid = uuidIn;
      this.slots = slotsIn;
   }

   public JsonObject serialize(JsonSerializationContext context) {
      JsonObject jsonobject = new JsonObject();
      jsonobject.addProperty("name", this.modifierName);
      jsonobject.addProperty("attribute", this.attributeName);
      jsonobject.addProperty("operation", getOperationFromStr(this.operation));
      jsonobject.add("amount", context.serialize(this.amount));
      if (this.uuid != null) {
         jsonobject.addProperty("id", this.uuid.toString());
      }

      if (this.slots.length == 1) {
         jsonobject.addProperty("slot", this.slots[0].getName());
      } else {
         JsonArray jsonarray = new JsonArray();
         EntityEquipmentSlot[] var4 = this.slots;
         int var5 = var4.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            EntityEquipmentSlot entityequipmentslot = var4[var6];
            jsonarray.add(new JsonPrimitive(entityequipmentslot.getName()));
         }

         jsonobject.add("slot", jsonarray);
      }

      return jsonobject;
   }

   public static bgO deserialize(JsonObject jsonObj, JsonDeserializationContext context) {
      String s = JsonUtils.getString(jsonObj, "name");
      String s1 = JsonUtils.getString(jsonObj, "attribute");
      int i = getOperationFromInt(JsonUtils.getString(jsonObj, "operation"));
      bhC randomvaluerange = (bhC)JsonUtils.deserializeClass(jsonObj, "amount", context, bhC.class);
      UUID uuid = null;
      EntityEquipmentSlot[] aentityequipmentslot;
      if (JsonUtils.isString(jsonObj, "slot")) {
         aentityequipmentslot = new EntityEquipmentSlot[]{EntityEquipmentSlot.fromString(JsonUtils.getString(jsonObj, "slot"))};
      } else {
         if (!JsonUtils.isJsonArray(jsonObj, "slot")) {
            throw new JsonSyntaxException("Invalid or missing attribute modifier slot; must be either string or array of strings.");
         }

         JsonArray jsonarray = JsonUtils.getJsonArray(jsonObj, "slot");
         aentityequipmentslot = new EntityEquipmentSlot[jsonarray.size()];
         int j = 0;

         JsonElement jsonelement;
         for(Iterator var10 = jsonarray.iterator(); var10.hasNext(); aentityequipmentslot[j++] = EntityEquipmentSlot.fromString(JsonUtils.getString(jsonelement, "slot"))) {
            jsonelement = (JsonElement)var10.next();
         }

         if (aentityequipmentslot.length == 0) {
            throw new JsonSyntaxException("Invalid attribute modifier slot; must contain at least one entry.");
         }
      }

      if (jsonObj.has("id")) {
         String s2 = JsonUtils.getString(jsonObj, "id");

         try {
            uuid = UUID.fromString(s2);
         } catch (IllegalArgumentException var12) {
            throw new JsonSyntaxException("Invalid attribute modifier id '" + s2 + "' (must be UUID format, with dashes)");
         }
      }

      return new bgO(s, s1, i, randomvaluerange, aentityequipmentslot, uuid);
   }

   private static String getOperationFromStr(int operationIn) {
      switch (operationIn) {
         case 0:
            return "addition";
         case 1:
            return "multiply_base";
         case 2:
            return "multiply_total";
         default:
            throw new IllegalArgumentException("Unknown operation " + operationIn);
      }
   }

   private static int getOperationFromInt(String operationIn) {
      if ("addition".equals(operationIn)) {
         return 0;
      } else if ("multiply_base".equals(operationIn)) {
         return 1;
      } else if ("multiply_total".equals(operationIn)) {
         return 2;
      } else {
         throw new JsonSyntaxException("Unknown attribute modifier operation " + operationIn);
      }
   }

   // $FF: synthetic method
   static UUID access$000(bgO x0) {
      return x0.uuid;
   }

   // $FF: synthetic method
   static EntityEquipmentSlot[] access$100(bgO x0) {
      return x0.slots;
   }

   // $FF: synthetic method
   static String access$200(bgO x0) {
      return x0.attributeName;
   }

   // $FF: synthetic method
   static String access$300(bgO x0) {
      return x0.modifierName;
   }

   // $FF: synthetic method
   static bhC access$400(bgO x0) {
      return x0.amount;
   }

   // $FF: synthetic method
   static int access$500(bgO x0) {
      return x0.operation;
   }
}
