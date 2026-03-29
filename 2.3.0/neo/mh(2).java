package neo;

import java.io.IOException;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.Slot;

public class mh extends yk implements mx {
   private float oldMouseX;
   private float oldMouseY;
   private jL recipeButton;
   private final mu recipeBookGui = new mu();
   private boolean widthTooNarrow;
   private boolean buttonClicked;

   public mh(ME player) {
      super(player.inventoryContainer);
      this.allowUserInput = true;
   }

   public void updateScreen() {
      if (this.mc.playerController.isInCreativeMode()) {
         nC var10003 = this.mc;
         this.mc.displayGuiScreen(new lY(nC.player));
      }

      this.recipeBookGui.tick();
   }

   public void initGui() {
      this.buttonList.clear();
      if (this.mc.playerController.isInCreativeMode()) {
         nC var10003 = this.mc;
         this.mc.displayGuiScreen(new lY(nC.player));
      } else {
         super.initGui();
      }

      this.widthTooNarrow = this.width < 379;
      this.recipeBookGui.func_194303_a(this.width, this.height, this.mc, this.widthTooNarrow, ((ContainerPlayer)this.inventorySlots).craftMatrix);
      this.guiLeft = this.recipeBookGui.updateScreenPosition(this.widthTooNarrow, this.width, this.xSize);
      this.recipeButton = new jL(10, this.guiLeft + 104, this.height / 2 - 22, 20, 18, 178, 0, 19, INVENTORY_BACKGROUND);
      this.buttonList.add(this.recipeButton);
   }

   protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
      this.fontRenderer.drawString(Ax.format("container.crafting"), 97, 8, 4210752);
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      this.hasActivePotionEffects = !this.recipeBookGui.isVisible();
      if (this.recipeBookGui.isVisible() && this.widthTooNarrow) {
         this.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
         this.recipeBookGui.render(mouseX, mouseY, partialTicks);
      } else {
         this.recipeBookGui.render(mouseX, mouseY, partialTicks);
         super.drawScreen(mouseX, mouseY, partialTicks);
         this.recipeBookGui.renderGhostRecipe(this.guiLeft, this.guiTop, false, partialTicks);
      }

      this.renderHoveredToolTip(mouseX, mouseY);
      this.recipeBookGui.renderTooltip(this.guiLeft, this.guiTop, mouseX, mouseY);
      this.oldMouseX = (float)mouseX;
      this.oldMouseY = (float)mouseY;
   }

   protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(INVENTORY_BACKGROUND);
      int i = this.guiLeft;
      int j = this.guiTop;
      this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
      int var10000 = i + 51;
      int var10001 = j + 75;
      float var10003 = (float)(i + 51) - this.oldMouseX;
      float var10004 = (float)(j + 75 - 50) - this.oldMouseY;
      nC var10005 = this.mc;
      drawEntityOnScreen(var10000, var10001, 30, var10003, var10004, nC.player);
   }

   public static void drawEntityOnScreen(int posX, int posY, int scale, float mouseX, float mouseY, Iw ent) {
      yh.enableColorMaterial();
      yh.pushMatrix();
      yh.translate((float)posX, (float)posY, 50.0F);
      yh.scale((float)(-scale), (float)scale, (float)scale);
      yh.rotate(180.0F, 0.0F, 0.0F, 1.0F);
      float f = ent.renderYawOffset;
      float f1 = ent.rotationYaw;
      float f2 = ent.rotationPitch;
      float f3 = ent.prevRotationYawHead;
      float f4 = ent.rotationYawHead;
      yh.rotate(135.0F, 0.0F, 1.0F, 0.0F);
      yz.enableStandardItemLighting();
      yh.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
      yh.rotate(-((float)Math.atan((double)(mouseY / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
      ent.renderYawOffset = (float)Math.atan((double)(mouseX / 40.0F)) * 20.0F;
      ent.rotationYaw = (float)Math.atan((double)(mouseX / 40.0F)) * 40.0F;
      ent.rotationPitch = -((float)Math.atan((double)(mouseY / 40.0F))) * 20.0F;
      ent.rotationYawHead = ent.rotationYaw;
      ent.prevRotationYawHead = ent.rotationYaw;
      yh.translate(0.0F, 0.0F, 0.0F);
      wC rendermanager = nC.getMinecraft().getRenderManager();
      rendermanager.setPlayerViewY(180.0F);
      rendermanager.setRenderShadow(false);
      rendermanager.renderEntity(ent, 0.0, 0.0, 0.0, 0.0F, 1.0F, false);
      rendermanager.setRenderShadow(true);
      ent.renderYawOffset = f;
      ent.rotationYaw = f1;
      ent.rotationPitch = f2;
      ent.prevRotationYawHead = f3;
      ent.rotationYawHead = f4;
      yh.popMatrix();
      yz.disableStandardItemLighting();
      yh.disableRescaleNormal();
      yh.setActiveTexture(ys.lightmapTexUnit);
      yh.disableTexture2D();
      yh.setActiveTexture(ys.defaultTexUnit);
   }

   protected boolean isPointInRegion(int rectX, int rectY, int rectWidth, int rectHeight, int pointX, int pointY) {
      return (!this.widthTooNarrow || !this.recipeBookGui.isVisible()) && super.isPointInRegion(rectX, rectY, rectWidth, rectHeight, pointX, pointY);
   }

   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      if (!this.recipeBookGui.mouseClicked(mouseX, mouseY, mouseButton) && (!this.widthTooNarrow || !this.recipeBookGui.isVisible())) {
         super.mouseClicked(mouseX, mouseY, mouseButton);
      }

   }

   protected void mouseReleased(int mouseX, int mouseY, int state) {
      if (this.buttonClicked) {
         this.buttonClicked = false;
      } else {
         super.mouseReleased(mouseX, mouseY, state);
      }

   }

   protected boolean hasClickedOutside(int p_193983_1_, int p_193983_2_, int p_193983_3_, int p_193983_4_) {
      boolean flag = p_193983_1_ < p_193983_3_ || p_193983_2_ < p_193983_4_ || p_193983_1_ >= p_193983_3_ + this.xSize || p_193983_2_ >= p_193983_4_ + this.ySize;
      return this.recipeBookGui.hasClickedOutside(p_193983_1_, p_193983_2_, this.guiLeft, this.guiTop, this.xSize, this.ySize) && flag;
   }

   protected void actionPerformed(jK button) throws IOException {
      if (button.id == 10) {
         this.recipeBookGui.initVisuals(this.widthTooNarrow, ((ContainerPlayer)this.inventorySlots).craftMatrix);
         this.recipeBookGui.toggleVisibility();
         this.guiLeft = this.recipeBookGui.updateScreenPosition(this.widthTooNarrow, this.width, this.xSize);
         this.recipeButton.setPosition(this.guiLeft + 104, this.height / 2 - 22);
         this.buttonClicked = true;
      }

   }

   protected void keyTyped(char typedChar, int keyCode) throws IOException {
      if (!this.recipeBookGui.keyPressed(typedChar, keyCode)) {
         super.keyTyped(typedChar, keyCode);
      }

   }

   protected void handleMouseClick(Slot slotIn, int slotId, int mouseButton, ClickType type) {
      super.handleMouseClick(slotIn, slotId, mouseButton, type);
      this.recipeBookGui.slotClicked(slotIn);
   }

   public void recipesUpdated() {
      this.recipeBookGui.recipesUpdated();
   }

   public void onGuiClosed() {
      this.recipeBookGui.removed();
      super.onGuiClosed();
   }

   public mu func_194310_f() {
      return this.recipeBookGui;
   }
}
