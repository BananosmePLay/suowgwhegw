package neo;

import io.netty.buffer.Unpooled;
import java.io.IOException;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class kH extends lU {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final ResourceLocation MERCHANT_GUI_TEXTURE = new ResourceLocation("textures/gui/container/villager.png");
   private final IH merchant;
   private kG nextButton;
   private kG previousButton;
   private int selectedMerchantRecipe;
   private final ITextComponent chatComponent;

   public kH(MJ playerInventoryIn, IH merchantIn, bij worldIn) {
      super(new ContainerMerchant(playerInventoryIn, merchantIn, worldIn));
      this.merchant = merchantIn;
      this.chatComponent = merchantIn.getDisplayName();
   }

   public void initGui() {
      super.initGui();
      int i = (this.width - this.xSize) / 2;
      int j = (this.height - this.ySize) / 2;
      this.nextButton = (kG)this.addButton(new kG(1, i + 120 + 27, j + 24 - 1, true));
      this.previousButton = (kG)this.addButton(new kG(2, i + 36 - 19, j + 24 - 1, false));
      this.nextButton.enabled = false;
      this.previousButton.enabled = false;
   }

   protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
      String s = this.chatComponent.getUnformattedText();
      this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
      this.fontRenderer.drawString(Ax.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
   }

   public void updateScreen() {
      super.updateScreen();
      nC var10001 = this.mc;
      YX merchantrecipelist = this.merchant.getRecipes(nC.player);
      if (merchantrecipelist != null) {
         this.nextButton.enabled = this.selectedMerchantRecipe < merchantrecipelist.size() - 1;
         this.previousButton.enabled = this.selectedMerchantRecipe > 0;
      }

   }

   protected void actionPerformed(jK button) throws IOException {
      boolean flag = false;
      if (button == this.nextButton) {
         ++this.selectedMerchantRecipe;
         nC var10001 = this.mc;
         YX merchantrecipelist = this.merchant.getRecipes(nC.player);
         if (merchantrecipelist != null && this.selectedMerchantRecipe >= merchantrecipelist.size()) {
            this.selectedMerchantRecipe = merchantrecipelist.size() - 1;
         }

         flag = true;
      } else if (button == this.previousButton) {
         --this.selectedMerchantRecipe;
         if (this.selectedMerchantRecipe < 0) {
            this.selectedMerchantRecipe = 0;
         }

         flag = true;
      }

      if (flag) {
         ((ContainerMerchant)this.inventorySlots).setCurrentRecipeIndex(this.selectedMerchantRecipe);
         SA packetbuffer = new SA(Unpooled.buffer());
         packetbuffer.writeInt(this.selectedMerchantRecipe);
         this.mc.getConnection().sendPacket(new SN("MC|TrSel", packetbuffer));
      }

   }

   protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(MERCHANT_GUI_TEXTURE);
      int i = (this.width - this.xSize) / 2;
      int j = (this.height - this.ySize) / 2;
      this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
      nC var10001 = this.mc;
      YX merchantrecipelist = this.merchant.getRecipes(nC.player);
      if (merchantrecipelist != null && !merchantrecipelist.isEmpty()) {
         int k = this.selectedMerchantRecipe;
         if (k < 0 || k >= merchantrecipelist.size()) {
            return;
         }

         YW merchantrecipe = (YW)merchantrecipelist.get(k);
         if (merchantrecipe.isRecipeDisabled()) {
            this.mc.getTextureManager().bindTexture(MERCHANT_GUI_TEXTURE);
            yh.color(1.0F, 1.0F, 1.0F, 1.0F);
            yh.disableLighting();
            this.drawTexturedModalRect(this.guiLeft + 83, this.guiTop + 21, 212, 0, 28, 21);
            this.drawTexturedModalRect(this.guiLeft + 83, this.guiTop + 51, 212, 0, 28, 21);
         }
      }

   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      super.drawScreen(mouseX, mouseY, partialTicks);
      nC var10001 = this.mc;
      YX merchantrecipelist = this.merchant.getRecipes(nC.player);
      if (merchantrecipelist != null && !merchantrecipelist.isEmpty()) {
         int i = (this.width - this.xSize) / 2;
         int j = (this.height - this.ySize) / 2;
         int k = this.selectedMerchantRecipe;
         YW merchantrecipe = (YW)merchantrecipelist.get(k);
         Qy itemstack = merchantrecipe.getItemToBuy();
         Qy itemstack1 = merchantrecipe.getSecondItemToBuy();
         Qy itemstack2 = merchantrecipe.getItemToSell();
         yh.pushMatrix();
         yz.enableGUIStandardItemLighting();
         yh.disableLighting();
         yh.enableRescaleNormal();
         yh.enableColorMaterial();
         yh.enableLighting();
         this.itemRender.zLevel = 100.0F;
         this.itemRender.renderItemAndEffectIntoGUI(itemstack, i + 36, j + 24);
         this.itemRender.renderItemOverlays(this.fontRenderer, itemstack, i + 36, j + 24);
         if (!itemstack1.isEmpty()) {
            this.itemRender.renderItemAndEffectIntoGUI(itemstack1, i + 62, j + 24);
            this.itemRender.renderItemOverlays(this.fontRenderer, itemstack1, i + 62, j + 24);
         }

         this.itemRender.renderItemAndEffectIntoGUI(itemstack2, i + 120, j + 24);
         this.itemRender.renderItemOverlays(this.fontRenderer, itemstack2, i + 120, j + 24);
         this.itemRender.zLevel = 0.0F;
         yh.disableLighting();
         if (this.isPointInRegion(36, 24, 16, 16, mouseX, mouseY) && !itemstack.isEmpty()) {
            this.renderToolTip(itemstack, mouseX, mouseY);
         } else if (!itemstack1.isEmpty() && this.isPointInRegion(62, 24, 16, 16, mouseX, mouseY) && !itemstack1.isEmpty()) {
            this.renderToolTip(itemstack1, mouseX, mouseY);
         } else if (!itemstack2.isEmpty() && this.isPointInRegion(120, 24, 16, 16, mouseX, mouseY) && !itemstack2.isEmpty()) {
            this.renderToolTip(itemstack2, mouseX, mouseY);
         } else if (merchantrecipe.isRecipeDisabled() && (this.isPointInRegion(83, 21, 28, 21, mouseX, mouseY) || this.isPointInRegion(83, 51, 28, 21, mouseX, mouseY))) {
            this.drawHoveringText(Ax.format("merchant.deprecated"), mouseX, mouseY);
         }

         yh.popMatrix();
         yh.enableLighting();
         yh.enableDepth();
         yz.enableStandardItemLighting();
      }

      this.renderHoveredToolTip(mouseX, mouseY);
   }

   public IH getMerchant() {
      return this.merchant;
   }

   // $FF: synthetic method
   static ResourceLocation access$000() {
      return MERCHANT_GUI_TEXTURE;
   }
}
