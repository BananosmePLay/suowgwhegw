package neo;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import java.io.IOException;
import javax.annotation.Nullable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class lJ extends lg {
   private static final Logger LOGGER = LogManager.getLogger();
   protected lg prevScreen;
   protected String title = "Select world";
   private String worldVersTooltip;
   private jK deleteButton;
   private jK selectButton;
   private jK renameButton;
   private jK copyButton;
   private kz selectionList;

   public lJ(lg screenIn) {
      this.prevScreen = screenIn;
   }

   public void initGui() {
      this.title = Ax.format("selectWorld.title");
      this.selectionList = new kz(this, this.mc, this.width, this.height, 32, this.height - 64, 36);
      this.postInit();
   }

   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      this.selectionList.handleMouseInput();
   }

   public void postInit() {
      this.selectButton = this.addButton(new jK(1, this.width / 2 - 154, this.height - 52, 150, 20, Ax.format("selectWorld.select")));
      this.addButton(new jK(3, this.width / 2 + 4, this.height - 52, 150, 20, Ax.format("selectWorld.create")));
      this.renameButton = this.addButton(new jK(4, this.width / 2 - 154, this.height - 28, 72, 20, Ax.format("selectWorld.edit")));
      this.deleteButton = this.addButton(new jK(2, this.width / 2 - 76, this.height - 28, 72, 20, Ax.format("selectWorld.delete")));
      this.copyButton = this.addButton(new jK(5, this.width / 2 + 4, this.height - 28, 72, 20, Ax.format("selectWorld.recreate")));
      this.addButton(new jK(0, this.width / 2 + 82, this.height - 28, 72, 20, Ax.format("gui.cancel")));
      this.selectButton.enabled = false;
      this.deleteButton.enabled = false;
      this.renameButton.enabled = false;
      this.copyButton.enabled = false;
   }

   protected void actionPerformed(jK button) throws IOException {
      if (button.enabled) {
         kC guilistworldselectionentry = this.selectionList.getSelectedWorld();
         if (button.id == 2) {
            if (guilistworldselectionentry != null) {
               guilistworldselectionentry.deleteWorld();
            }
         } else if (button.id == 1) {
            if (guilistworldselectionentry != null) {
               guilistworldselectionentry.joinWorld();
            }
         } else if (button.id == 3) {
            this.mc.displayGuiScreen(new jX(this));
         } else if (button.id == 4) {
            if (guilistworldselectionentry != null) {
               guilistworldselectionentry.editWorld();
            }
         } else if (button.id == 0) {
            this.mc.displayGuiScreen(this.prevScreen);
         } else if (button.id == 5 && guilistworldselectionentry != null) {
            guilistworldselectionentry.recreateWorld();
         }
      }

   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.worldVersTooltip = null;
      this.selectionList.drawScreen(mouseX, mouseY, partialTicks);
      this.drawCenteredString(this.fontRenderer, this.title, this.width / 2, 20, 16777215);
      super.drawScreen(mouseX, mouseY, partialTicks);
      if (this.worldVersTooltip != null) {
         this.drawHoveringText(Lists.newArrayList(Splitter.on("\n").split(this.worldVersTooltip)), mouseX, mouseY);
      }

   }

   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      this.selectionList.mouseClicked(mouseX, mouseY, mouseButton);
   }

   protected void mouseReleased(int mouseX, int mouseY, int state) {
      super.mouseReleased(mouseX, mouseY, state);
      this.selectionList.mouseReleased(mouseX, mouseY, state);
   }

   public void setVersionTooltip(String p_184861_1_) {
      this.worldVersTooltip = p_184861_1_;
   }

   public void selectWorld(@Nullable kC entry) {
      boolean flag = entry != null;
      this.selectButton.enabled = flag;
      this.deleteButton.enabled = flag;
      this.renameButton.enabled = flag;
      this.copyButton.enabled = flag;
   }
}
