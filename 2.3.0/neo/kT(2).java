package neo;

import javax.annotation.Nullable;

public class kT implements kx {
   private final nC client = nC.getMinecraft();
   private final jI component1;
   private final jI component2;
   private jI focusedControl;

   public kT(@Nullable jI p_i45533_1_, @Nullable jI p_i45533_2_) {
      this.component1 = p_i45533_1_;
      this.component2 = p_i45533_2_;
   }

   public jI getComponent1() {
      return this.component1;
   }

   public jI getComponent2() {
      return this.component2;
   }

   public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected, float partialTicks) {
      this.renderComponent(this.component1, y, mouseX, mouseY, false, partialTicks);
      this.renderComponent(this.component2, y, mouseX, mouseY, false, partialTicks);
   }

   private void renderComponent(jI p_192636_1_, int p_192636_2_, int p_192636_3_, int p_192636_4_, boolean p_192636_5_, float p_192636_6_) {
      if (p_192636_1_ != null) {
         if (p_192636_1_ instanceof jK) {
            this.renderButton((jK)p_192636_1_, p_192636_2_, p_192636_3_, p_192636_4_, p_192636_5_, p_192636_6_);
         } else if (p_192636_1_ instanceof lE) {
            this.renderTextField((lE)p_192636_1_, p_192636_2_, p_192636_5_);
         } else if (p_192636_1_ instanceof kt) {
            this.renderLabel((kt)p_192636_1_, p_192636_2_, p_192636_3_, p_192636_4_, p_192636_5_);
         }
      }

   }

   private void renderButton(jK p_192635_1_, int p_192635_2_, int p_192635_3_, int p_192635_4_, boolean p_192635_5_, float p_192635_6_) {
      p_192635_1_.y = p_192635_2_;
      if (!p_192635_5_) {
         p_192635_1_.drawButton(this.client, p_192635_3_, p_192635_4_, p_192635_6_);
      }

   }

   private void renderTextField(lE p_178027_1_, int p_178027_2_, boolean p_178027_3_) {
      p_178027_1_.y = p_178027_2_;
      if (!p_178027_3_) {
         p_178027_1_.drawTextBox();
      }

   }

   private void renderLabel(kt p_178025_1_, int p_178025_2_, int p_178025_3_, int p_178025_4_, boolean p_178025_5_) {
      p_178025_1_.y = p_178025_2_;
      if (!p_178025_5_) {
         p_178025_1_.drawLabel(this.client, p_178025_3_, p_178025_4_);
      }

   }

   public void updatePosition(int slotIndex, int x, int y, float partialTicks) {
      this.renderComponent(this.component1, y, 0, 0, true, partialTicks);
      this.renderComponent(this.component2, y, 0, 0, true, partialTicks);
   }

   public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY) {
      boolean flag = this.clickComponent(this.component1, mouseX, mouseY, mouseEvent);
      boolean flag1 = this.clickComponent(this.component2, mouseX, mouseY, mouseEvent);
      return flag || flag1;
   }

   private boolean clickComponent(jI p_178026_1_, int p_178026_2_, int p_178026_3_, int p_178026_4_) {
      if (p_178026_1_ == null) {
         return false;
      } else if (p_178026_1_ instanceof jK) {
         return this.clickButton((jK)p_178026_1_, p_178026_2_, p_178026_3_, p_178026_4_);
      } else {
         if (p_178026_1_ instanceof lE) {
            this.clickTextField((lE)p_178026_1_, p_178026_2_, p_178026_3_, p_178026_4_);
         }

         return false;
      }
   }

   private boolean clickButton(jK p_178023_1_, int p_178023_2_, int p_178023_3_, int p_178023_4_) {
      boolean flag = p_178023_1_.mousePressed(this.client, p_178023_2_, p_178023_3_);
      if (flag) {
         this.focusedControl = p_178023_1_;
      }

      return flag;
   }

   private void clickTextField(lE p_178018_1_, int p_178018_2_, int p_178018_3_, int p_178018_4_) {
      p_178018_1_.mouseClicked(p_178018_2_, p_178018_3_, p_178018_4_);
      if (p_178018_1_.isFocused()) {
         this.focusedControl = p_178018_1_;
      }

   }

   public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
      this.releaseComponent(this.component1, x, y, mouseEvent);
      this.releaseComponent(this.component2, x, y, mouseEvent);
   }

   private void releaseComponent(jI p_178016_1_, int p_178016_2_, int p_178016_3_, int p_178016_4_) {
      if (p_178016_1_ != null && p_178016_1_ instanceof jK) {
         this.releaseButton((jK)p_178016_1_, p_178016_2_, p_178016_3_, p_178016_4_);
      }

   }

   private void releaseButton(jK p_178019_1_, int p_178019_2_, int p_178019_3_, int p_178019_4_) {
      p_178019_1_.mouseReleased(p_178019_2_, p_178019_3_);
   }

   // $FF: synthetic method
   static jI access$000(kT x0) {
      return x0.component1;
   }

   // $FF: synthetic method
   static jI access$100(kT x0) {
      return x0.component2;
   }

   // $FF: synthetic method
   static jI access$200(kT x0) {
      return x0.focusedControl;
   }
}
