package neo;

import javax.annotation.Nullable;
import net.minecraft.util.MouseHelper;
import net.minecraft.util.MovementInput;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentKeybind;

public class BF {
   private final nC minecraft;
   @Nullable
   private BA tutorialStep;

   public BF(nC minecraft) {
      this.minecraft = minecraft;
   }

   public void handleMovement(MovementInput p_193293_1_) {
      if (this.tutorialStep != null) {
         this.tutorialStep.handleMovement(p_193293_1_);
      }

   }

   public void handleMouse(MouseHelper p_193299_1_) {
      if (this.tutorialStep != null) {
         this.tutorialStep.handleMouse(p_193299_1_);
      }

   }

   public void onMouseHover(@Nullable pm worldIn, @Nullable RayTraceResult result) {
      if (this.tutorialStep != null && result != null && worldIn != null) {
         this.tutorialStep.onMouseHover(worldIn, result);
      }

   }

   public void onHitBlock(pm worldIn, BlockPos pos, in state, float diggingStage) {
      if (this.tutorialStep != null) {
         this.tutorialStep.onHitBlock(worldIn, pos, state, diggingStage);
      }

   }

   public void openInventory() {
      if (this.tutorialStep != null) {
         this.tutorialStep.openInventory();
      }

   }

   public void handleSetSlot(Qy stack) {
      if (this.tutorialStep != null) {
         this.tutorialStep.handleSetSlot(stack);
      }

   }

   public void stop() {
      if (this.tutorialStep != null) {
         this.tutorialStep.onStop();
         this.tutorialStep = null;
      }

   }

   public void reload() {
      if (this.tutorialStep != null) {
         this.stop();
      }

      nC var10001 = this.minecraft;
      this.tutorialStep = nC.gameSettings.tutorialStep.create(this);
   }

   public void update() {
      if (this.tutorialStep != null) {
         if (this.minecraft.world != null) {
            this.tutorialStep.update();
         } else {
            this.stop();
         }
      } else if (this.minecraft.world != null) {
         this.reload();
      }

   }

   public void setStep(BG step) {
      nC var10000 = this.minecraft;
      nC.gameSettings.tutorialStep = step;
      var10000 = this.minecraft;
      nC.gameSettings.saveOptions();
      if (this.tutorialStep != null) {
         this.tutorialStep.onStop();
         this.tutorialStep = step.create(this);
      }

   }

   public nC getMinecraft() {
      return this.minecraft;
   }

   public bbb getGameType() {
      return this.minecraft.playerController == null ? bbb.NOT_SET : this.minecraft.playerController.getCurrentGameType();
   }

   public static ITextComponent createKeybindComponent(String keybind) {
      TextComponentKeybind textcomponentkeybind = new TextComponentKeybind("key." + keybind);
      textcomponentkeybind.getStyle().setBold(true);
      return textcomponentkeybind;
   }
}
