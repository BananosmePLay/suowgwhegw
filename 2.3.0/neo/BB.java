package neo;

import net.minecraft.util.MouseHelper;
import net.minecraft.util.MovementInput;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class BB implements BA {
   private static final ITextComponent MOVE_TITLE = new TextComponentTranslation("tutorial.move.title", new Object[]{BF.createKeybindComponent("forward"), BF.createKeybindComponent("left"), BF.createKeybindComponent("back"), BF.createKeybindComponent("right")});
   private static final ITextComponent MOVE_DESCRIPTION = new TextComponentTranslation("tutorial.move.description", new Object[]{BF.createKeybindComponent("jump")});
   private static final ITextComponent LOOK_TITLE = new TextComponentTranslation("tutorial.look.title", new Object[0]);
   private static final ITextComponent LOOK_DESCRIPTION = new TextComponentTranslation("tutorial.look.description", new Object[0]);
   private final BF tutorial;
   private nk moveToast;
   private nk lookToast;
   private int timeWaiting;
   private int timeMoved;
   private int timeLooked;
   private boolean moved;
   private boolean turned;
   private int moveCompleted = -1;
   private int lookCompleted = -1;

   public BB(BF tutorial) {
      this.tutorial = tutorial;
   }

   public void update() {
      ++this.timeWaiting;
      if (this.moved) {
         ++this.timeMoved;
         this.moved = false;
      }

      if (this.turned) {
         ++this.timeLooked;
         this.turned = false;
      }

      if (this.moveCompleted == -1 && this.timeMoved > 40) {
         if (this.moveToast != null) {
            this.moveToast.hide();
            this.moveToast = null;
         }

         this.moveCompleted = this.timeWaiting;
      }

      if (this.lookCompleted == -1 && this.timeLooked > 40) {
         if (this.lookToast != null) {
            this.lookToast.hide();
            this.lookToast = null;
         }

         this.lookCompleted = this.timeWaiting;
      }

      if (this.moveCompleted != -1 && this.lookCompleted != -1) {
         if (this.tutorial.getGameType() == bbb.SURVIVAL) {
            this.tutorial.setStep(BG.FIND_TREE);
         } else {
            this.tutorial.setStep(BG.NONE);
         }
      }

      if (this.moveToast != null) {
         this.moveToast.setProgress((float)this.timeMoved / 40.0F);
      }

      if (this.lookToast != null) {
         this.lookToast.setProgress((float)this.timeLooked / 40.0F);
      }

      if (this.timeWaiting >= 100) {
         if (this.moveCompleted == -1 && this.moveToast == null) {
            this.moveToast = new nk(nj.MOVEMENT_KEYS, MOVE_TITLE, MOVE_DESCRIPTION, true);
            this.tutorial.getMinecraft().getToastGui().add(this.moveToast);
         } else if (this.moveCompleted != -1 && this.timeWaiting - this.moveCompleted >= 20 && this.lookCompleted == -1 && this.lookToast == null) {
            this.lookToast = new nk(nj.MOUSE, LOOK_TITLE, LOOK_DESCRIPTION, true);
            this.tutorial.getMinecraft().getToastGui().add(this.lookToast);
         }
      }

   }

   public void onStop() {
      if (this.moveToast != null) {
         this.moveToast.hide();
         this.moveToast = null;
      }

      if (this.lookToast != null) {
         this.lookToast.hide();
         this.lookToast = null;
      }

   }

   public void handleMovement(MovementInput input) {
      if (input.forwardKeyDown || input.backKeyDown || input.leftKeyDown || input.rightKeyDown || input.jump) {
         this.moved = true;
      }

   }

   public void handleMouse(MouseHelper mouseHelperIn) {
      if ((double)MathHelper.abs(mouseHelperIn.deltaX) > 0.01 || (double)MathHelper.abs(mouseHelperIn.deltaY) > 0.01) {
         this.turned = true;
      }

   }
}
