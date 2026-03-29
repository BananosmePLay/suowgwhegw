package neo;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class By implements BA {
   private static final ITextComponent TITLE = new TextComponentTranslation("tutorial.craft_planks.title", new Object[0]);
   private static final ITextComponent DESCRIPTION = new TextComponentTranslation("tutorial.craft_planks.description", new Object[0]);
   private final BF tutorial;
   private nk toast;
   private int timeWaiting;

   public By(BF tutorial) {
      this.tutorial = tutorial;
   }

   public void update() {
      ++this.timeWaiting;
      if (this.tutorial.getGameType() != bbb.SURVIVAL) {
         this.tutorial.setStep(BG.NONE);
      } else {
         if (this.timeWaiting == 1) {
            this.tutorial.getMinecraft();
            jh entityplayersp = nC.player;
            if (entityplayersp != null) {
               if (entityplayersp.inventory.hasItemStack(new Qy(Nk.PLANKS))) {
                  this.tutorial.setStep(BG.NONE);
                  return;
               }

               if (didPlayerCraftedPlanks(entityplayersp)) {
                  this.tutorial.setStep(BG.NONE);
                  return;
               }
            }
         }

         if (this.timeWaiting >= 1200 && this.toast == null) {
            this.toast = new nk(nj.WOODEN_PLANKS, TITLE, DESCRIPTION, false);
            this.tutorial.getMinecraft().getToastGui().add(this.toast);
         }
      }

   }

   public void onStop() {
      if (this.toast != null) {
         this.toast.hide();
         this.toast = null;
      }

   }

   public void handleSetSlot(Qy stack) {
      if (stack.getItem() == OL.getItemFromBlock(Nk.PLANKS)) {
         this.tutorial.setStep(BG.NONE);
      }

   }

   public static boolean didPlayerCraftedPlanks(jh player) {
      XQ statbase = XV.getCraftStats(OL.getItemFromBlock(Nk.PLANKS));
      return statbase != null && player.getStatFileWriter().readStat(statbase) > 0;
   }
}
