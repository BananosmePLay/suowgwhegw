package neo;

import net.minecraft.util.ResourceLocation;

public class boO extends jK {
   public boO(int buttonID, int xPos, int yPos) {
      super(buttonID, xPos, yPos, 22, 20, "");
   }

   public void drawButton(nC mc, int mouseX, int mouseY, float partialTicks) {
      if (this.visible) {
         super.drawButton(mc, mouseX, mouseY, partialTicks);
         ResourceLocation resourcelocation = new ResourceLocation("optifine/textures/icons.png");
         mc.getTextureManager().bindTexture(resourcelocation);
         yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.drawTexturedModalRect(this.x + 3, this.y + 2, 0, 0, 16, 16);
      }

   }
}
