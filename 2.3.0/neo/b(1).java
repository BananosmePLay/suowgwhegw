package neo;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.event.HoverEvent;

public class b {
   private final b parent;
   private final cb display;
   private final k rewards;
   private final ResourceLocation id;
   private final Map<String, bZ> criteria;
   private final String[][] requirements;
   private final Set<b> children = Sets.newLinkedHashSet();
   private final ITextComponent displayText;

   public b(ResourceLocation id, @Nullable b parentIn, @Nullable cb displayIn, k rewardsIn, Map<String, bZ> criteriaIn, String[][] requirementsIn) {
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

   public a copy() {
      return new a(this.parent == null ? null : this.parent.getId(), this.display, this.rewards, this.criteria, this.requirements);
   }

   @Nullable
   public b getParent() {
      return this.parent;
   }

   @Nullable
   public cb getDisplay() {
      return this.display;
   }

   public k getRewards() {
      return this.rewards;
   }

   public String toString() {
      return "SimpleAdvancement{id=" + this.getId() + ", parent=" + (this.parent == null ? "null" : this.parent.getId()) + ", display=" + this.display + ", rewards=" + this.rewards + ", criteria=" + this.criteria + ", requirements=" + Arrays.deepToString(this.requirements) + '}';
   }

   public Iterable<b> getChildren() {
      return this.children;
   }

   public Map<String, bZ> getCriteria() {
      return this.criteria;
   }

   public int getRequirementCount() {
      return this.requirements.length;
   }

   public void addChild(b advancementIn) {
      this.children.add(advancementIn);
   }

   public ResourceLocation getId() {
      return this.id;
   }

   public boolean equals(Object p_equals_1_) {
      if (this == p_equals_1_) {
         return true;
      } else if (!(p_equals_1_ instanceof b)) {
         return false;
      } else {
         b advancement = (b)p_equals_1_;
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
}
