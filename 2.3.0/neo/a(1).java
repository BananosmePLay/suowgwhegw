package neo;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.function.Function;
import javax.annotation.Nullable;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.ArrayUtils;

public class a {
   private final ResourceLocation parentId;
   private b parent;
   private final cb display;
   private final k rewards;
   private final Map<String, bZ> criteria;
   private final String[][] requirements;

   a(@Nullable ResourceLocation parentIdIn, @Nullable cb displayIn, k rewardsIn, Map<String, bZ> criteriaIn, String[][] requirementsIn) {
      this.parentId = parentIdIn;
      this.display = displayIn;
      this.rewards = rewardsIn;
      this.criteria = criteriaIn;
      this.requirements = requirementsIn;
   }

   public boolean resolveParent(Function<ResourceLocation, b> lookup) {
      if (this.parentId == null) {
         return true;
      } else {
         this.parent = (b)lookup.apply(this.parentId);
         return this.parent != null;
      }
   }

   public b build(ResourceLocation id) {
      return new b(id, this.parent, this.display, this.rewards, this.criteria, this.requirements);
   }

   public void writeTo(SA buf) {
      if (this.parentId == null) {
         buf.writeBoolean(false);
      } else {
         buf.writeBoolean(true);
         buf.writeResourceLocation(this.parentId);
      }

      if (this.display == null) {
         buf.writeBoolean(false);
      } else {
         buf.writeBoolean(true);
         this.display.write(buf);
      }

      bZ.serializeToNetwork(this.criteria, buf);
      buf.writeVarInt(this.requirements.length);
      String[][] var2 = this.requirements;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         String[] astring = var2[var4];
         buf.writeVarInt(astring.length);
         String[] var6 = astring;
         int var7 = astring.length;

         for(int var8 = 0; var8 < var7; ++var8) {
            String s = var6[var8];
            buf.writeString(s);
         }
      }

   }

   public String toString() {
      return "Task Advancement{parentId=" + this.parentId + ", display=" + this.display + ", rewards=" + this.rewards + ", criteria=" + this.criteria + ", requirements=" + Arrays.deepToString(this.requirements) + '}';
   }

   public static a deserialize(JsonObject json, JsonDeserializationContext context) {
      ResourceLocation resourcelocation = json.has("parent") ? new ResourceLocation(JsonUtils.getString(json, "parent")) : null;
      cb displayinfo = json.has("display") ? cb.deserialize(JsonUtils.getJsonObject(json, "display"), context) : null;
      k advancementrewards = (k)JsonUtils.deserializeClass(json, "rewards", k.EMPTY, context, k.class);
      Map<String, bZ> map = bZ.criteriaFromJson(JsonUtils.getJsonObject(json, "criteria"), context);
      if (map.isEmpty()) {
         throw new JsonSyntaxException("Advancement criteria cannot be empty");
      } else {
         JsonArray jsonarray = JsonUtils.getJsonArray(json, "requirements", new JsonArray());
         String[][] astring = new String[jsonarray.size()][];

         int i;
         int j;
         for(i = 0; i < jsonarray.size(); ++i) {
            JsonArray jsonarray1 = JsonUtils.getJsonArray(jsonarray.get(i), "requirements[" + i + "]");
            astring[i] = new String[jsonarray1.size()];

            for(j = 0; j < jsonarray1.size(); ++j) {
               astring[i][j] = JsonUtils.getString(jsonarray1.get(j), "requirements[" + i + "][" + j + "]");
            }
         }

         if (astring.length == 0) {
            astring = new String[map.size()][];
            i = 0;

            String s2;
            for(Iterator var16 = map.keySet().iterator(); var16.hasNext(); astring[i++] = new String[]{s2}) {
               s2 = (String)var16.next();
            }
         }

         String[][] var17 = astring;
         int var18 = astring.length;

         int var13;
         for(j = 0; j < var18; ++j) {
            String[] astring1 = var17[j];
            if (astring1.length == 0 && map.isEmpty()) {
               throw new JsonSyntaxException("Requirement entry cannot be empty");
            }

            String[] var12 = astring1;
            var13 = astring1.length;

            for(int var14 = 0; var14 < var13; ++var14) {
               String s = var12[var14];
               if (!map.containsKey(s)) {
                  throw new JsonSyntaxException("Unknown required criterion '" + s + "'");
               }
            }
         }

         Iterator var19 = map.keySet().iterator();

         String s1;
         boolean flag;
         do {
            if (!var19.hasNext()) {
               return new a(resourcelocation, displayinfo, advancementrewards, map, astring);
            }

            s1 = (String)var19.next();
            flag = false;
            String[][] var22 = astring;
            int var24 = astring.length;

            for(var13 = 0; var13 < var24; ++var13) {
               String[] astring2 = var22[var13];
               if (ArrayUtils.contains(astring2, s1)) {
                  flag = true;
                  break;
               }
            }
         } while(flag);

         throw new JsonSyntaxException("Criterion '" + s1 + "' isn't a requirement for completion. This isn't supported behaviour, all criteria must be required.");
      }
   }

   public static a readFrom(SA buf) throws IOException {
      ResourceLocation resourcelocation = buf.readBoolean() ? buf.readResourceLocation() : null;
      cb displayinfo = buf.readBoolean() ? cb.read(buf) : null;
      Map<String, bZ> map = bZ.criteriaFromNetwork(buf);
      String[][] astring = new String[buf.readVarInt()][];

      for(int i = 0; i < astring.length; ++i) {
         astring[i] = new String[buf.readVarInt()];

         for(int j = 0; j < astring[i].length; ++j) {
            astring[i][j] = buf.readString(32767);
         }
      }

      return new a(resourcelocation, displayinfo, k.EMPTY, map, astring);
   }
}
