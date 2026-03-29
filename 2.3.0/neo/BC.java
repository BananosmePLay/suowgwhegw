package neo;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class BC implements BA {
   private static final ITextComponent TITLE = new TextComponentTranslation("tutorial.open_inventory.title", new Object[0]);
   private static final ITextComponent DESCRIPTION = new TextComponentTranslation("tutorial.open_inventory.description", new Object[]{BF.createKeybindComponent("inventory")});
   private final BF tutorial;
   private nk toast;
   private int timeWaiting;

   public BC(BF tutorial) {
      this.tutorial = tutorial;
   }

   public void update() {
      ++this.timeWaiting;
      if (this.tutorial.getGameType() != bbb.SURVIVAL) {
         this.tutorial.setStep(BG.NONE);
      } else if (this.timeWaiting >= 600 && this.toast == null) {
         this.toast = new nk(nj.RECIPE_BOOK, TITLE, DESCRIPTION, false);
         this.tutorial.getMinecraft().getToastGui().add(this.toast);
      }

   }

   public void onStop() {
      if (this.toast != null) {
         this.toast.hide();
         this.toast = null;
      }

   }

   public void openInventory() {
      this.tutorial.setStep(BG.CRAFT_PLANKS);
   }
}
