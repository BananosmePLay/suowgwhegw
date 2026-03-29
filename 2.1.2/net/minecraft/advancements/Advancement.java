package net.minecraft.advancements;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import javax.annotation.Nullable;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.event.HoverEvent;
import org.apache.commons.lang3.ArrayUtils;

public class Advancement {
   private final Advancement parent;
   private final DisplayInfo display;
   private final AdvancementRewards rewards;
   private final ResourceLocation id;
   private final Map<String, Criterion> criteria;
   private final String[][] requirements;
   private final Set<Advancement> children = Sets.newLinkedHashSet();
   private final ITextComponent displayText;

   public Advancement(ResourceLocation id, @Nullable Advancement parentIn, @Nullable DisplayInfo displayIn, AdvancementRewards rewardsIn, Map<String, Criterion> criteriaIn, String[][] requirementsIn) {
      this.id = id;
      this.display = displayIn;
      this.criteria = ImmutableMap.copyOf(criteriaIn);
      this.parent = parentIn;
      this.rewards = rewardsIn;
      this.requirements = requirementsIn;
      if (parentIn != null) {
         parentIn.addChild(this);
      }

      if (displayIn == null) {
         this.displayText = new TextComponentString(id.toString());
      } else {
         this.displayText = new TextComponentString("[");
         this.displayText.getStyle().setColor(displayIn.getFrame().getFormat());
         ITextComponent itextcomponent = displayIn.getTitle().createCopy();
         ITextComponent itextcomponent1 = new TextComponentString("");
         ITextComponent itextcomponent2 = itextcomponent.createCopy();
         itextcomponent2.getStyle().setColor(displayIn.getFrame().getFormat());
         itextcomponent1.appendSibling(itextcomponent2);
         itextcomponent1.appendText("\n");
         itextcomponent1.appendSibling(displayIn.getDescription());
         itextcomponent.getStyle().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, itextcomponent1));
         this.displayText.appendSibling(itextcomponent);
         this.displayText.appendText("]");
      }

   }

   public Builder copy() {
      return new Builder(this.parent == null ? null : this.parent.getId(), this.display, this.rewards, this.criteria, this.requirements);
   }

   @Nullable
   public Advancement getParent() {
      return this.parent;
   }

   @Nullable
   public DisplayInfo getDisplay() {
      return this.display;
   }

   public AdvancementRewards getRewards() {
      return this.rewards;
   }

   public String toString() {
      return "SimpleAdvancement{id=" + this.getId() + ", parent=" + (this.parent == null ? "null" : this.parent.getId()) + ", display=" + this.display + ", rewards=" + this.rewards + ", criteria=" + this.criteria + ", requirements=" + Arrays.deepToString(this.requirements) + '}';
   }

   public Iterable<Advancement> getChildren() {
      return this.children;
   }

   public Map<String, Criterion> getCriteria() {
      return this.criteria;
   }

   public int getRequirementCount() {
      return this.requirements.length;
   }

   public void addChild(Advancement advancementIn) {
      this.children.add(advancementIn);
   }

   public ResourceLocation getId() {
      return this.id;
   }

   public boolean equals(Object p_equals_1_) {
      if (this == p_equals_1_) {
         return true;
      } else if (!(p_equals_1_ instanceof Advancement)) {
         return false;
      } else {
         Advancement advancement = (Advancement)p_equals_1_;
         return this.id.equals(advancement.id);
      }
   }

   public int hashCode() {
      return this.id.hashCode();
   }

   public String[][] getRequirements() {
      return this.requirements;
   }

   public ITextComponent getDisplayText() {
      return this.displayText;
   }

   public static class Builder {
      private final ResourceLocation parentId;
      private Advancement parent;
      private final DisplayInfo display;
      private final AdvancementRewards rewards;
      private final Map<String, Criterion> criteria;
      private final String[][] requirements;

      Builder(@Nullable ResourceLocation parentIdIn, @Nullable DisplayInfo displayIn, AdvancementRewards rewardsIn, Map<String, Criterion> criteriaIn, String[][] requirementsIn) {
         this.parentId = parentIdIn;
         this.display = displayIn;
         this.rewards = rewardsIn;
         this.criteria = criteriaIn;
         this.requirements = requirementsIn;
      }

      public boolean resolveParent(Function<ResourceLocation, Advancement> lookup) {
         if (this.parentId == null) {
            return true;
         } else {
            this.parent = (Advancement)lookup.apply(this.parentId);
            return this.parent != null;
         }
      }

      public Advancement build(ResourceLocation id) {
         return new Advancement(id, this.parent, this.display, this.rewards, this.criteria, this.requirements);
      }

      public void writeTo(PacketBuffer buf) {
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

         Criterion.serializeToNetwork(this.criteria, buf);
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

      public static Builder deserialize(JsonObject json, JsonDeserializationContext context) {
         ResourceLocation resourcelocation = json.has("parent") ? new ResourceLocation(JsonUtils.getString(json, "parent")) : null;
         DisplayInfo displayinfo = json.has("display") ? DisplayInfo.deserialize(JsonUtils.getJsonObject(json, "display"), context) : null;
         AdvancementRewards advancementrewards = (AdvancementRewards)JsonUtils.deserializeClass(json, "rewards", AdvancementRewards.EMPTY, context, AdvancementRewards.class);
         Map<String, Criterion> map = Criterion.criteriaFromJson(JsonUtils.getJsonObject(json, "criteria"), context);
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
                  return new Builder(resourcelocation, displayinfo, advancementrewards, map, astring);
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

      public static Builder readFrom(PacketBuffer buf) throws IOException {
         ResourceLocation resourcelocation = buf.readBoolean() ? buf.readResourceLocation() : null;
         DisplayInfo displayinfo = buf.readBoolean() ? DisplayInfo.read(buf) : null;
         Map<String, Criterion> map = Criterion.criteriaFromNetwork(buf);
         String[][] astring = new String[buf.readVarInt()][];

         for(int i = 0; i < astring.length; ++i) {
            astring[i] = new String[buf.readVarInt()];

            for(int j = 0; j < astring[i].length; ++j) {
               astring[i][j] = buf.readString(32767);
            }
         }

         return new Builder(resourcelocation, displayinfo, AdvancementRewards.EMPTY, map, astring);
      }
   }
}
