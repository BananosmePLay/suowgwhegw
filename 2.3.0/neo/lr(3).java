package neo;

import com.google.common.collect.Lists;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class lr extends lg {
   private final lg parentScreen;
   private List<AN> availableResourcePacks;
   private List<AN> selectedResourcePacks;
   private ld availableResourcePacksList;
   private lf selectedResourcePacksList;
   private boolean changed;

   public lr(lg parentScreenIn) {
      this.parentScreen = parentScreenIn;
   }

   public void initGui() {
      this.buttonList.add(new kK(2, this.width / 2 - 154, this.height - 48, Ax.format("resourcePack.openFolder")));
      this.buttonList.add(new kK(1, this.width / 2 + 4, this.height - 48, Ax.format("gui.done")));
      if (!this.changed) {
         this.availableResourcePacks = Lists.newArrayList();
         this.selectedResourcePacks = Lists.newArrayList();
         AV resourcepackrepository = this.mc.getResourcePackRepository();
         resourcepackrepository.updateRepositoryEntriesAll();
         List<AU> list = Lists.newArrayList(resourcepackrepository.getRepositoryEntriesAll());
         list.removeAll(resourcepackrepository.getRepositoryEntries());
         Iterator var3 = list.iterator();

         while(var3.hasNext()) {
            AU resourcepackrepository$entry = (AU)var3.next();
            this.availableResourcePacks.add(new AP(this, resourcepackrepository$entry));
         }

         AU resourcepackrepository$entry2 = resourcepackrepository.getResourcePackEntry();
         if (resourcepackrepository$entry2 != null) {
            this.selectedResourcePacks.add(new AQ(this, resourcepackrepository.getServerResourcePack()));
         }

         Iterator var7 = Lists.reverse(resourcepackrepository.getRepositoryEntries()).iterator();

         while(var7.hasNext()) {
            AU resourcepackrepository$entry1 = (AU)var7.next();
            this.selectedResourcePacks.add(new AP(this, resourcepackrepository$entry1));
         }

         this.selectedResourcePacks.add(new AO(this));
      }

      this.availableResourcePacksList = new ld(this.mc, 200, this.height, this.availableResourcePacks);
      this.availableResourcePacksList.setSlotXBoundsFromLeft(this.width / 2 - 4 - 200);
      this.availableResourcePacksList.registerScrollButtons(7, 8);
      this.selectedResourcePacksList = new lf(this.mc, 200, this.height, this.selectedResourcePacks);
      this.selectedResourcePacksList.setSlotXBoundsFromLeft(this.width / 2 + 4);
      this.selectedResourcePacksList.registerScrollButtons(7, 8);
   }

   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      this.selectedResourcePacksList.handleMouseInput();
      this.availableResourcePacksList.handleMouseInput();
   }

   public boolean hasResourcePackEntry(AN resourcePackEntry) {
      return this.selectedResourcePacks.contains(resourcePackEntry);
   }

   public List<AN> getListContaining(AN resourcePackEntry) {
      return this.hasResourcePackEntry(resourcePackEntry) ? this.selectedResourcePacks : this.availableResourcePacks;
   }

   public List<AN> getAvailableResourcePacks() {
      return this.availableResourcePacks;
   }

   public List<AN> getSelectedResourcePacks() {
      return this.selectedResourcePacks;
   }

   protected void actionPerformed(jK button) throws IOException {
      if (button.enabled) {
         if (button.id == 2) {
            File file1 = this.mc.getResourcePackRepository().getDirResourcepacks();
            ys.openFile(file1);
         } else if (button.id == 1) {
            if (this.changed) {
               List<AU> list = Lists.newArrayList();
               Iterator var3 = this.selectedResourcePacks.iterator();

               while(var3.hasNext()) {
                  AN resourcepacklistentry = (AN)var3.next();
                  if (resourcepacklistentry instanceof AP) {
                     list.add(((AP)resourcepacklistentry).getResourcePackEntry());
                  }
               }

               Collections.reverse(list);
               this.mc.getResourcePackRepository().setRepositories(list);
               nC var10000 = this.mc;
               nC.gameSettings.resourcePacks.clear();
               var10000 = this.mc;
               nC.gameSettings.incompatibleResourcePacks.clear();
               var3 = list.iterator();

               while(var3.hasNext()) {
                  AU resourcepackrepository$entry = (AU)var3.next();
                  var10000 = this.mc;
                  nC.gameSettings.resourcePacks.add(resourcepackrepository$entry.getResourcePackName());
                  if (resourcepackrepository$entry.getPackFormat() != 3) {
                     var10000 = this.mc;
                     nC.gameSettings.incompatibleResourcePacks.add(resourcepackrepository$entry.getResourcePackName());
                  }
               }

               var10000 = this.mc;
               nC.gameSettings.saveOptions();
               this.mc.refreshResources();
            }

            this.mc.displayGuiScreen(this.parentScreen);
         }
      }

   }

   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      this.availableResourcePacksList.mouseClicked(mouseX, mouseY, mouseButton);
      this.selectedResourcePacksList.mouseClicked(mouseX, mouseY, mouseButton);
   }

   protected void mouseReleased(int mouseX, int mouseY, int state) {
      super.mouseReleased(mouseX, mouseY, state);
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawBackground(0);
      this.availableResourcePacksList.drawScreen(mouseX, mouseY, partialTicks);
      this.selectedResourcePacksList.drawScreen(mouseX, mouseY, partialTicks);
      this.drawCenteredString(this.fontRenderer, Ax.format("resourcePack.title"), this.width / 2, 16, 16777215);
      this.drawCenteredString(this.fontRenderer, Ax.format("resourcePack.folderInfo"), this.width / 2 - 77, this.height - 26, 8421504);
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   public void markChanged() {
      this.changed = true;
   }
}
