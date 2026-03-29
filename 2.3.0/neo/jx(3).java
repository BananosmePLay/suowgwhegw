package neo;

import com.google.common.collect.Maps;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;

public class jx extends lg implements oX {
   private static final ResourceLocation WINDOW = new ResourceLocation("textures/gui/advancements/window.png");
   private static final ResourceLocation TABS = new ResourceLocation("textures/gui/advancements/tabs.png");
   private final oY clientAdvancementManager;
   private final Map<b, jw> tabs = Maps.newLinkedHashMap();
   private jw selectedTab;
   private int scrollMouseX;
   private int scrollMouseY;
   private boolean isScrolling;

   public jx(oY p_i47383_1_) {
      this.clientAdvancementManager = p_i47383_1_;
   }

   public void initGui() {
      this.tabs.clear();
      this.selectedTab = null;
      this.clientAdvancementManager.setListener(this);
      if (this.selectedTab == null && !this.tabs.isEmpty()) {
         this.clientAdvancementManager.setSelectedTab(((jw)this.tabs.values().iterator().next()).getAdvancement(), true);
      } else {
         this.clientAdvancementManager.setSelectedTab(this.selectedTab == null ? null : this.selectedTab.getAdvancement(), true);
      }

   }

   public void onGuiClosed() {
      this.clientAdvancementManager.setListener((oX)null);
      py nethandlerplayclient = this.mc.getConnection();
      if (nethandlerplayclient != null) {
         nethandlerplayclient.sendPacket(Tj.closedScreen());
      }

   }

   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      if (mouseButton == 0) {
         int i = (this.width - 252) / 2;
         int j = (this.height - 140) / 2;
         Iterator var6 = this.tabs.values().iterator();

         while(var6.hasNext()) {
            jw guiadvancementtab = (jw)var6.next();
            if (guiadvancementtab.isMouseOver(i, j, mouseX, mouseY)) {
               this.clientAdvancementManager.setSelectedTab(guiadvancementtab.getAdvancement(), true);
               break;
            }
         }
      }

      super.mouseClicked(mouseX, mouseY, mouseButton);
   }

   protected void keyTyped(char typedChar, int keyCode) throws IOException {
      nC var10001 = this.mc;
      if (keyCode == nC.gameSettings.keyBindAdvancements.getKeyCode()) {
         this.mc.displayGuiScreen((lg)null);
         this.mc.setIngameFocus();
      } else {
         super.keyTyped(typedChar, keyCode);
      }

   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      int i = (this.width - 252) / 2;
      int j = (this.height - 140) / 2;
      if (Mouse.isButtonDown(0)) {
         if (!this.isScrolling) {
            this.isScrolling = true;
         } else if (this.selectedTab != null) {
            this.selectedTab.scroll(mouseX - this.scrollMouseX, mouseY - this.scrollMouseY);
         }

         this.scrollMouseX = mouseX;
         this.scrollMouseY = mouseY;
      } else {
         this.isScrolling = false;
      }

      this.drawDefaultBackground();
      this.renderInside(mouseX, mouseY, i, j);
      this.renderWindow(i, j);
      this.renderToolTips(mouseX, mouseY, i, j);
   }

   private void renderInside(int p_191936_1_, int p_191936_2_, int p_191936_3_, int p_191936_4_) {
      jw guiadvancementtab = this.selectedTab;
      if (guiadvancementtab == null) {
         drawRect(p_191936_3_ + 9, p_191936_4_ + 18, p_191936_3_ + 9 + 234, p_191936_4_ + 18 + 113, -16777216);
         String s = Ax.format("advancements.empty");
         int i = this.fontRenderer.getStringWidth(s);
         this.fontRenderer.drawString(s, p_191936_3_ + 9 + 117 - i / 2, p_191936_4_ + 18 + 56 - this.fontRenderer.FONT_HEIGHT / 2, -1);
         this.fontRenderer.drawString(":(", p_191936_3_ + 9 + 117 - this.fontRenderer.getStringWidth(":(") / 2, p_191936_4_ + 18 + 113 - this.fontRenderer.FONT_HEIGHT, -1);
      } else {
         yh.pushMatrix();
         yh.translate((float)(p_191936_3_ + 9), (float)(p_191936_4_ + 18), -400.0F);
         yh.enableDepth();
         guiadvancementtab.drawContents();
         yh.popMatrix();
         yh.depthFunc(515);
         yh.disableDepth();
      }

   }

   public void renderWindow(int p_191934_1_, int p_191934_2_) {
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      yh.enableBlend();
      yz.disableStandardItemLighting();
      this.mc.getTextureManager().bindTexture(WINDOW);
      this.drawTexturedModalRect(p_191934_1_, p_191934_2_, 0, 0, 252, 140);
      if (this.tabs.size() > 1) {
         this.mc.getTextureManager().bindTexture(TABS);
         Iterator var3 = this.tabs.values().iterator();

         jw guiadvancementtab1;
         while(var3.hasNext()) {
            guiadvancementtab1 = (jw)var3.next();
            guiadvancementtab1.drawTab(p_191934_1_, p_191934_2_, guiadvancementtab1 == this.selectedTab);
         }

         yh.enableRescaleNormal();
         yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA, ya.ONE, xR.ZERO);
         yz.enableGUIStandardItemLighting();
         var3 = this.tabs.values().iterator();

         while(var3.hasNext()) {
            guiadvancementtab1 = (jw)var3.next();
            guiadvancementtab1.drawIcon(p_191934_1_, p_191934_2_, this.itemRender);
         }

         yh.disableBlend();
      }

      this.fontRenderer.drawString(Ax.format("gui.advancements"), p_191934_1_ + 8, p_191934_2_ + 6, 4210752);
   }

   private void renderToolTips(int p_191937_1_, int p_191937_2_, int p_191937_3_, int p_191937_4_) {
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      if (this.selectedTab != null) {
         yh.pushMatrix();
         yh.enableDepth();
         yh.translate((float)(p_191937_3_ + 9), (float)(p_191937_4_ + 18), 400.0F);
         this.selectedTab.drawToolTips(p_191937_1_ - p_191937_3_ - 9, p_191937_2_ - p_191937_4_ - 18, p_191937_3_, p_191937_4_);
         yh.disableDepth();
         yh.popMatrix();
      }

      if (this.tabs.size() > 1) {
         Iterator var5 = this.tabs.values().iterator();

         while(var5.hasNext()) {
            jw guiadvancementtab = (jw)var5.next();
            if (guiadvancementtab.isMouseOver(p_191937_3_, p_191937_4_, p_191937_1_, p_191937_2_)) {
               this.drawHoveringText(guiadvancementtab.getTitle(), p_191937_1_, p_191937_2_);
            }
         }
      }

   }

   public void rootAdvancementAdded(b advancementIn) {
      jw guiadvancementtab = jw.create(this.mc, this, this.tabs.size(), advancementIn);
      if (guiadvancementtab != null) {
         this.tabs.put(advancementIn, guiadvancementtab);
      }

   }

   public void rootAdvancementRemoved(b advancementIn) {
   }

   public void nonRootAdvancementAdded(b advancementIn) {
      jw guiadvancementtab = this.getTab(advancementIn);
      if (guiadvancementtab != null) {
         guiadvancementtab.addAdvancement(advancementIn);
      }

   }

   public void nonRootAdvancementRemoved(b advancementIn) {
   }

   public void onUpdateAdvancementProgress(b advancementIn, h progress) {
      jv guiadvancement = this.getAdvancementGui(advancementIn);
      if (guiadvancement != null) {
         guiadvancement.setAdvancementProgress(progress);
      }

   }

   public void setSelectedTab(@Nullable b advancementIn) {
      this.selectedTab = (jw)this.tabs.get(advancementIn);
   }

   public void advancementsCleared() {
      this.tabs.clear();
      this.selectedTab = null;
   }

   @Nullable
   public jv getAdvancementGui(b p_191938_1_) {
      jw guiadvancementtab = this.getTab(p_191938_1_);
      return guiadvancementtab == null ? null : guiadvancementtab.getAdvancementGui(p_191938_1_);
   }

   @Nullable
   private jw getTab(b p_191935_1_) {
      while(p_191935_1_.getParent() != null) {
         p_191935_1_ = p_191935_1_.getParent();
      }

      return (jw)this.tabs.get(p_191935_1_);
   }
}
