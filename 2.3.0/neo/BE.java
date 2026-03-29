package neo;

import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.Set;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class BE implements BA {
   private static final Set<co> LOG_BLOCKS;
   private static final ITextComponent TITLE;
   private static final ITextComponent DESCRIPTION;
   private final BF tutorial;
   private nk toast;
   private int timeWaiting;
   private int resetCount;

   public BE(BF tutorial) {
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
               Iterator var2 = LOG_BLOCKS.iterator();

               while(var2.hasNext()) {
                  co block = (co)var2.next();
                  if (entityplayersp.inventory.hasItemStack(new Qy(block))) {
                     this.tutorial.setStep(BG.CRAFT_PLANKS);
                     return;
                  }
               }

               if (Bz.hasPunchedTreesPreviously(entityplayersp)) {
                  this.tutorial.setStep(BG.CRAFT_PLANKS);
                  return;
               }
            }
         }

         if ((this.timeWaiting >= 600 || this.resetCount > 3) && this.toast == null) {
            this.toast = new nk(nj.TREE, TITLE, DESCRIPTION, true);
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

   public void onHitBlock(pm worldIn, BlockPos pos, in state, float diggingStage) {
      boolean flag = LOG_BLOCKS.contains(state.getBlock());
      if (flag && diggingStage > 0.0F) {
         if (this.toast != null) {
            this.toast.setProgress(diggingStage);
         }

         if (diggingStage >= 1.0F) {
            this.tutorial.setStep(BG.OPEN_INVENTORY);
         }
      } else if (this.toast != null) {
         this.toast.setProgress(0.0F);
      } else if (flag) {
         ++this.resetCount;
      }

   }

   public void handleSetSlot(Qy stack) {
      Iterator var2 = LOG_BLOCKS.iterator();

      co block;
      do {
         if (!var2.hasNext()) {
            return;
         }

         block = (co)var2.next();
      } while(stack.getItem() != OL.getItemFromBlock(block));

      this.tutorial.setStep(BG.CRAFT_PLANKS);
   }

   static {
      LOG_BLOCKS = Sets.newHashSet(new co[]{Nk.LOG, Nk.LOG2});
      TITLE = new TextComponentTranslation("tutorial.punch_tree.title", new Object[0]);
      DESCRIPTION = new TextComponentTranslation("tutorial.punch_tree.description", new Object[]{BF.createKeybindComponent("attack")});
   }
}
