package neo;

import java.io.IOException;

public class jW extends lg {
   private final jX createWorldGui;
   private bcl generatorInfo = bcl.getDefaultFlatGenerator();
   private String flatWorldTitle;
   private String materialText;
   private String heightText;
   private jV createFlatWorldListSlotGui;
   private jK addLayerButton;
   private jK editLayerButton;
   private jK removeLayerButton;

   public jW(jX createWorldGuiIn, String preset) {
      this.createWorldGui = createWorldGuiIn;
      this.setPreset(preset);
   }

   public String getPreset() {
      return this.generatorInfo.toString();
   }

   public void setPreset(String preset) {
      this.generatorInfo = bcl.createFlatGeneratorFromString(preset);
   }

   public void initGui() {
      this.buttonList.clear();
      this.flatWorldTitle = Ax.format("createWorld.customize.flat.title");
      this.materialText = Ax.format("createWorld.customize.flat.tile");
      this.heightText = Ax.format("createWorld.customize.flat.height");
      this.createFlatWorldListSlotGui = new jV(this);
      this.addLayerButton = this.addButton(new jK(2, this.width / 2 - 154, this.height - 52, 100, 20, Ax.format("createWorld.customize.flat.addLayer") + " (NYI)"));
      this.editLayerButton = this.addButton(new jK(3, this.width / 2 - 50, this.height - 52, 100, 20, Ax.format("createWorld.customize.flat.editLayer") + " (NYI)"));
      this.removeLayerButton = this.addButton(new jK(4, this.width / 2 - 155, this.height - 52, 150, 20, Ax.format("createWorld.customize.flat.removeLayer")));
      this.buttonList.add(new jK(0, this.width / 2 - 155, this.height - 28, 150, 20, Ax.format("gui.done")));
      this.buttonList.add(new jK(5, this.width / 2 + 5, this.height - 52, 150, 20, Ax.format("createWorld.customize.presets")));
      this.buttonList.add(new jK(1, this.width / 2 + 5, this.height - 28, 150, 20, Ax.format("gui.cancel")));
      this.addLayerButton.visible = false;
      this.editLayerButton.visible = false;
      this.generatorInfo.updateLayers();
      this.onLayersChanged();
   }

   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      this.createFlatWorldListSlotGui.handleMouseInput();
   }

   protected void actionPerformed(jK button) throws IOException {
      int i = this.generatorInfo.getFlatLayers().size() - this.createFlatWorldListSlotGui.selectedLayer - 1;
      if (button.id == 1) {
         this.mc.displayGuiScreen(this.createWorldGui);
      } else if (button.id == 0) {
         this.createWorldGui.chunkProviderSettingsJson = this.getPreset();
         this.mc.displayGuiScreen(this.createWorldGui);
      } else if (button.id == 5) {
         this.mc.displayGuiScreen(new kj(this));
      } else if (button.id == 4 && this.hasSelectedLayer()) {
         this.generatorInfo.getFlatLayers().remove(i);
         this.createFlatWorldListSlotGui.selectedLayer = Math.min(this.createFlatWorldListSlotGui.selectedLayer, this.generatorInfo.getFlatLayers().size() - 1);
      }

      this.generatorInfo.updateLayers();
      this.onLayersChanged();
   }

   public void onLayersChanged() {
      boolean flag = this.hasSelectedLayer();
      this.removeLayerButton.enabled = flag;
      this.editLayerButton.enabled = flag;
      this.editLayerButton.enabled = false;
      this.addLayerButton.enabled = false;
   }

   private boolean hasSelectedLayer() {
      return this.createFlatWorldListSlotGui.selectedLayer > -1 && this.createFlatWorldListSlotGui.selectedLayer < this.generatorInfo.getFlatLayers().size();
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      this.createFlatWorldListSlotGui.drawScreen(mouseX, mouseY, partialTicks);
      this.drawCenteredString(this.fontRenderer, this.flatWorldTitle, this.width / 2, 8, 16777215);
      int i = this.width / 2 - 92 - 16;
      this.drawString(this.fontRenderer, this.materialText, i, 32, 16777215);
      this.drawString(this.fontRenderer, this.heightText, i + 2 + 213 - this.fontRenderer.getStringWidth(this.heightText), 32, 16777215);
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   // $FF: synthetic method
   static bcl access$000(jW x0) {
      return x0.generatorInfo;
   }
}
