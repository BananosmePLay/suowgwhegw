package neo;

import java.util.List;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public abstract class AN implements kx {
   private static final ResourceLocation RESOURCE_PACKS_TEXTURE = new ResourceLocation("textures/gui/resource_packs.png");
   private static final ITextComponent INCOMPATIBLE = new TextComponentTranslation("resourcePack.incompatible", new Object[0]);
   private static final ITextComponent INCOMPATIBLE_OLD = new TextComponentTranslation("resourcePack.incompatible.old", new Object[0]);
   private static final ITextComponent INCOMPATIBLE_NEW = new TextComponentTranslation("resourcePack.incompatible.new", new Object[0]);
   protected final nC mc;
   protected final lr resourcePacksGUI;

   public AN(lr resourcePacksGUIIn) {
      this.resourcePacksGUI = resourcePacksGUIIn;
      this.mc = nC.getMinecraft();
   }

   public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected, float partialTicks) {
      int i = this.getResourcePackFormat();
      if (i != 3) {
         yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         jI.drawRect(x - 1, y - 1, x + listWidth - 9, y + slotHeight + 1, -8978432);
      }

      this.bindResourcePackIcon();
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      jI.drawModalRectWithCustomSizedTexture(x, y, 0.0F, 0.0F, 32, 32, 32.0F, 32.0F);
      String s = this.getResourcePackName();
      String s1 = this.getResourcePackDescription();
      int j;
      if (this.showHoverOverlay()) {
         nC var10000 = this.mc;
         if (nC.gameSettings.touchscreen || isSelected) {
            this.mc.getTextureManager().bindTexture(RESOURCE_PACKS_TEXTURE);
            jI.drawRect(x, y, x + 32, y + 32, -1601138544);
            yh.color(1.0F, 1.0F, 1.0F, 1.0F);
            j = mouseX - x;
            int k = mouseY - y;
            if (i < 3) {
               s = INCOMPATIBLE.getFormattedText();
               s1 = INCOMPATIBLE_OLD.getFormattedText();
            } else if (i > 3) {
               s = INCOMPATIBLE.getFormattedText();
               s1 = INCOMPATIBLE_NEW.getFormattedText();
            }

            if (this.canMoveRight()) {
               if (j < 32) {
                  jI.drawModalRectWithCustomSizedTexture(x, y, 0.0F, 32.0F, 32, 32, 256.0F, 256.0F);
               } else {
                  jI.drawModalRectWithCustomSizedTexture(x, y, 0.0F, 0.0F, 32, 32, 256.0F, 256.0F);
               }
            } else {
               if (this.canMoveLeft()) {
                  if (j < 16) {
                     jI.drawModalRectWithCustomSizedTexture(x, y, 32.0F, 32.0F, 32, 32, 256.0F, 256.0F);
                  } else {
                     jI.drawModalRectWithCustomSizedTexture(x, y, 32.0F, 0.0F, 32, 32, 256.0F, 256.0F);
                  }
               }

               if (this.canMoveUp()) {
                  if (j < 32 && j > 16 && k < 16) {
                     jI.drawModalRectWithCustomSizedTexture(x, y, 96.0F, 32.0F, 32, 32, 256.0F, 256.0F);
                  } else {
                     jI.drawModalRectWithCustomSizedTexture(x, y, 96.0F, 0.0F, 32, 32, 256.0F, 256.0F);
                  }
               }

               if (this.canMoveDown()) {
                  if (j < 32 && j > 16 && k > 16) {
                     jI.drawModalRectWithCustomSizedTexture(x, y, 64.0F, 32.0F, 32, 32, 256.0F, 256.0F);
                  } else {
                     jI.drawModalRectWithCustomSizedTexture(x, y, 64.0F, 0.0F, 32, 32, 256.0F, 256.0F);
                  }
               }
            }
         }
      }

      j = this.mc.fontRenderer.getStringWidth(s);
      if (j > 157) {
         s = this.mc.fontRenderer.trimStringToWidth(s, 157 - this.mc.fontRenderer.getStringWidth("...")) + "...";
      }

      this.mc.fontRenderer.drawStringWithShadow(s, (float)(x + 32 + 2), (float)(y + 1), 16777215);
      List<String> list = this.mc.fontRenderer.listFormattedStringToWidth(s1, 157);

      for(int l = 0; l < 2 && l < list.size(); ++l) {
         this.mc.fontRenderer.drawStringWithShadow((String)list.get(l), (float)(x + 32 + 2), (float)(y + 12 + 10 * l), 8421504);
      }

   }

   protected abstract int getResourcePackFormat();

   protected abstract String getResourcePackDescription();

   protected abstract String getResourcePackName();

   protected abstract void bindResourcePackIcon();

   protected boolean showHoverOverlay() {
      return true;
   }

   protected boolean canMoveRight() {
      return !this.resourcePacksGUI.hasResourcePackEntry(this);
   }

   protected boolean canMoveLeft() {
      return this.resourcePacksGUI.hasResourcePackEntry(this);
   }

   protected boolean canMoveUp() {
      List<AN> list = this.resourcePacksGUI.getListContaining(this);
      int i = list.indexOf(this);
      return i > 0 && ((AN)list.get(i - 1)).showHoverOverlay();
   }

   protected boolean canMoveDown() {
      List<AN> list = this.resourcePacksGUI.getListContaining(this);
      int i = list.indexOf(this);
      return i >= 0 && i < list.size() - 1 && ((AN)list.get(i + 1)).showHoverOverlay();
   }

   public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY) {
      if (this.showHoverOverlay() && relativeX <= 32) {
         int i;
         if (this.canMoveRight()) {
            this.resourcePacksGUI.markChanged();
            final int j = ((AN)this.resourcePacksGUI.getSelectedResourcePacks().get(0)).isServerPack() ? 1 : 0;
            i = this.getResourcePackFormat();
            if (i == 3) {
               this.resourcePacksGUI.getListContaining(this).remove(this);
               this.resourcePacksGUI.getSelectedResourcePacks().add(j, this);
            } else {
               String s = Ax.format("resourcePack.incompatible.confirm.title");
               String s1 = Ax.format("resourcePack.incompatible.confirm." + (i > 3 ? "new" : "old"));
               this.mc.displayGuiScreen(new lK(new lL() {
                  public void confirmClicked(boolean result, int id) {
                     List<AN> list2 = AN.this.resourcePacksGUI.getListContaining(AN.this);
                     AN.this.mc.displayGuiScreen(AN.this.resourcePacksGUI);
                     if (result) {
                        list2.remove(AN.this);
                        AN.this.resourcePacksGUI.getSelectedResourcePacks().add(j, AN.this);
                     }

                  }
               }, s, s1, 0));
            }

            return true;
         }

         if (relativeX < 16 && this.canMoveLeft()) {
            this.resourcePacksGUI.getListContaining(this).remove(this);
            this.resourcePacksGUI.getAvailableResourcePacks().add(0, this);
            this.resourcePacksGUI.markChanged();
            return true;
         }

         List list;
         if (relativeX > 16 && relativeY < 16 && this.canMoveUp()) {
            list = this.resourcePacksGUI.getListContaining(this);
            i = list.indexOf(this);
            list.remove(this);
            list.add(i - 1, this);
            this.resourcePacksGUI.markChanged();
            return true;
         }

         if (relativeX > 16 && relativeY > 16 && this.canMoveDown()) {
            list = this.resourcePacksGUI.getListContaining(this);
            i = list.indexOf(this);
            list.remove(this);
            list.add(i + 1, this);
            this.resourcePacksGUI.markChanged();
            return true;
         }
      }

      return false;
   }

   public void updatePosition(int slotIndex, int x, int y, float partialTicks) {
   }

   public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
   }

   public boolean isServerPack() {
      return false;
   }
}
