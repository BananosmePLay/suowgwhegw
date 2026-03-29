package neo;

import net.minecraft.util.NonNullList;

public class Pl extends OL {
   public Pl() {
      this.setHasSubtypes(true);
      this.setMaxDamage(0);
      this.setCreativeTab(EN.MATERIALS);
   }

   public String getTranslationKey(Qy stack) {
      return stack.getMetadata() == 1 ? "item.charcoal" : "item.coal";
   }

   public void getSubItems(EN tab, NonNullList<Qy> items) {
      if (this.isInCreativeTab(tab)) {
         items.add(new Qy(this, 1, 0));
         items.add(new Qy(this, 1, 1));
      }

   }
}
