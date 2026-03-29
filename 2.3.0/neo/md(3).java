package neo;

import java.io.IOException;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.text.TextComponentString;
import org.lwjgl.input.Keyboard;

public class md extends lg {
   private final YQ tileSign;
   private int updateCounter;
   private int editLine;
   private jK doneBtn;

   public md(YQ teSign) {
      this.tileSign = teSign;
   }

   public void initGui() {
      this.buttonList.clear();
      Keyboard.enableRepeatEvents(true);
      this.doneBtn = this.addButton(new jK(0, this.width / 2 - 100, this.height / 4 + 120, Ax.format("gui.done")));
      this.tileSign.setEditable(false);
   }

   public void onGuiClosed() {
      Keyboard.enableRepeatEvents(false);
      py nethandlerplayclient = this.mc.getConnection();
      if (nethandlerplayclient != null) {
         nethandlerplayclient.sendPacket(new Tn(this.tileSign.getPos(), this.tileSign.signText));
      }

      this.tileSign.setEditable(true);
   }

   public void updateScreen() {
      ++this.updateCounter;
   }

   protected void actionPerformed(jK button) throws IOException {
      if (button.enabled && button.id == 0) {
         this.tileSign.markDirty();
         this.mc.displayGuiScreen((lg)null);
      }

   }

   protected void keyTyped(char typedChar, int keyCode) throws IOException {
      if (keyCode == 200) {
         this.editLine = this.editLine - 1 & 3;
      }

      if (keyCode == 208 || keyCode == 28 || keyCode == 156) {
         this.editLine = this.editLine + 1 & 3;
      }

      String s = this.tileSign.signText[this.editLine].getUnformattedText();
      if (keyCode == 14 && !s.isEmpty()) {
         s = s.substring(0, s.length() - 1);
      }

      if (ChatAllowedCharacters.isAllowedCharacter(typedChar) && this.fontRenderer.getStringWidth(s + typedChar) <= 90) {
         s = s + typedChar;
      }

      this.tileSign.signText[this.editLine] = new TextComponentString(s);
      if (keyCode == 1) {
         this.actionPerformed(this.doneBtn);
      }

   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.fontRenderer, Ax.format("sign.edit"), this.width / 2, 40, 16777215);
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      yh.pushMatrix();
      yh.translate((float)(this.width / 2), 0.0F, 50.0F);
      float f = 93.75F;
      yh.scale(-93.75F, -93.75F, -93.75F);
      yh.rotate(180.0F, 0.0F, 1.0F, 0.0F);
      co block = this.tileSign.getBlockType();
      if (block == Nk.STANDING_SIGN) {
         float f1 = (float)(this.tileSign.getBlockMetadata() * 360) / 16.0F;
         yh.rotate(f1, 0.0F, 1.0F, 0.0F);
         yh.translate(0.0F, -1.0625F, 0.0F);
      } else {
         int i = this.tileSign.getBlockMetadata();
         float f2 = 0.0F;
         if (i == 2) {
            f2 = 180.0F;
         }

         if (i == 4) {
            f2 = 90.0F;
         }

         if (i == 5) {
            f2 = -90.0F;
         }

         yh.rotate(f2, 0.0F, 1.0F, 0.0F);
         yh.translate(0.0F, -1.0625F, 0.0F);
      }

      if (this.updateCounter / 6 % 2 == 0) {
         this.tileSign.lineBeingEdited = this.editLine;
      }

      zz.instance.render(this.tileSign, -0.5, -0.75, -0.5, 0.0F);
      this.tileSign.lineBeingEdited = -1;
      yh.popMatrix();
      super.drawScreen(mouseX, mouseY, partialTicks);
   }
}
