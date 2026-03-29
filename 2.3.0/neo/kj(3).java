package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.lwjgl.input.Keyboard;

public class kj extends lg {
   private static final List<kh> FLAT_WORLD_PRESETS = Lists.newArrayList();
   private final jW parentScreen;
   private String presetsTitle;
   private String presetsShare;
   private String listText;
   private ki list;
   private jK btnSelect;
   private lE export;

   public kj(jW p_i46318_1_) {
      this.parentScreen = p_i46318_1_;
   }

   public void initGui() {
      this.buttonList.clear();
      Keyboard.enableRepeatEvents(true);
      this.presetsTitle = Ax.format("createWorld.customize.presets.title");
      this.presetsShare = Ax.format("createWorld.customize.presets.share");
      this.listText = Ax.format("createWorld.customize.presets.list");
      this.export = new lE(2, this.fontRenderer, 50, 40, this.width - 100, 20);
      this.list = new ki(this);
      this.export.setMaxStringLength(1230);
      this.export.setText(this.parentScreen.getPreset());
      this.btnSelect = this.addButton(new jK(0, this.width / 2 - 155, this.height - 28, 150, 20, Ax.format("createWorld.customize.presets.select")));
      this.buttonList.add(new jK(1, this.width / 2 + 5, this.height - 28, 150, 20, Ax.format("gui.cancel")));
      this.updateButtonValidity();
   }

   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      this.list.handleMouseInput();
   }

   public void onGuiClosed() {
      Keyboard.enableRepeatEvents(false);
   }

   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      this.export.mouseClicked(mouseX, mouseY, mouseButton);
      super.mouseClicked(mouseX, mouseY, mouseButton);
   }

   protected void keyTyped(char typedChar, int keyCode) throws IOException {
      if (!this.export.textboxKeyTyped(typedChar, keyCode)) {
         super.keyTyped(typedChar, keyCode);
      }

   }

   protected void actionPerformed(jK button) throws IOException {
      if (button.id == 0 && this.hasValidSelection()) {
         this.parentScreen.setPreset(this.export.getText());
         this.mc.displayGuiScreen(this.parentScreen);
      } else if (button.id == 1) {
         this.mc.displayGuiScreen(this.parentScreen);
      }

   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      this.list.drawScreen(mouseX, mouseY, partialTicks);
      this.drawCenteredString(this.fontRenderer, this.presetsTitle, this.width / 2, 8, 16777215);
      this.drawString(this.fontRenderer, this.presetsShare, 50, 30, 10526880);
      this.drawString(this.fontRenderer, this.listText, 50, 70, 10526880);
      this.export.drawTextBox();
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   public void updateScreen() {
      this.export.updateCursorCounter();
      super.updateScreen();
   }

   public void updateButtonValidity() {
      this.btnSelect.enabled = this.hasValidSelection();
   }

   private boolean hasValidSelection() {
      return this.list.selected > -1 && this.list.selected < FLAT_WORLD_PRESETS.size() || this.export.getText().length() > 1;
   }

   private static void registerPreset(String name, OL icon, Zi biome, List<String> features, bcm... layers) {
      registerPreset(name, icon, 0, biome, features, layers);
   }

   private static void registerPreset(String name, OL icon, int iconMetadata, Zi biome, List<String> features, bcm... layers) {
      bcl flatgeneratorinfo = new bcl();

      for(int i = layers.length - 1; i >= 0; --i) {
         flatgeneratorinfo.getFlatLayers().add(layers[i]);
      }

      flatgeneratorinfo.setBiome(Zi.getIdForBiome(biome));
      flatgeneratorinfo.updateLayers();
      Iterator var9 = features.iterator();

      while(var9.hasNext()) {
         String s = (String)var9.next();
         flatgeneratorinfo.getWorldFeatures().put(s, Maps.newHashMap());
      }

      FLAT_WORLD_PRESETS.add(new kh(icon, iconMetadata, name, flatgeneratorinfo.toString()));
   }

   // $FF: synthetic method
   static List access$000() {
      return FLAT_WORLD_PRESETS;
   }

   // $FF: synthetic method
   static ki access$100(kj x0) {
      return x0.list;
   }

   // $FF: synthetic method
   static lE access$200(kj x0) {
      return x0.export;
   }

   static {
      registerPreset(Ax.format("createWorld.customize.preset.classic_flat"), OL.getItemFromBlock(Nk.GRASS), Nj.PLAINS, Arrays.asList("village"), new bcm(1, Nk.GRASS), new bcm(2, Nk.DIRT), new bcm(1, Nk.BEDROCK));
      registerPreset(Ax.format("createWorld.customize.preset.tunnelers_dream"), OL.getItemFromBlock(Nk.STONE), Nj.EXTREME_HILLS, Arrays.asList("biome_1", "dungeon", "decoration", "stronghold", "mineshaft"), new bcm(1, Nk.GRASS), new bcm(5, Nk.DIRT), new bcm(230, Nk.STONE), new bcm(1, Nk.BEDROCK));
      registerPreset(Ax.format("createWorld.customize.preset.water_world"), NK.WATER_BUCKET, Nj.DEEP_OCEAN, Arrays.asList("biome_1", "oceanmonument"), new bcm(90, Nk.WATER), new bcm(5, Nk.SAND), new bcm(5, Nk.DIRT), new bcm(5, Nk.STONE), new bcm(1, Nk.BEDROCK));
      registerPreset(Ax.format("createWorld.customize.preset.overworld"), OL.getItemFromBlock(Nk.TALLGRASS), hj.GRASS.getMeta(), Nj.PLAINS, Arrays.asList("village", "biome_1", "decoration", "stronghold", "mineshaft", "dungeon", "lake", "lava_lake"), new bcm(1, Nk.GRASS), new bcm(3, Nk.DIRT), new bcm(59, Nk.STONE), new bcm(1, Nk.BEDROCK));
      registerPreset(Ax.format("createWorld.customize.preset.snowy_kingdom"), OL.getItemFromBlock(Nk.SNOW_LAYER), Nj.ICE_PLAINS, Arrays.asList("village", "biome_1"), new bcm(1, Nk.SNOW_LAYER), new bcm(1, Nk.GRASS), new bcm(3, Nk.DIRT), new bcm(59, Nk.STONE), new bcm(1, Nk.BEDROCK));
      registerPreset(Ax.format("createWorld.customize.preset.bottomless_pit"), NK.FEATHER, Nj.PLAINS, Arrays.asList("village", "biome_1"), new bcm(1, Nk.GRASS), new bcm(3, Nk.DIRT), new bcm(2, Nk.COBBLESTONE));
      registerPreset(Ax.format("createWorld.customize.preset.desert"), OL.getItemFromBlock(Nk.SAND), Nj.DESERT, Arrays.asList("village", "biome_1", "decoration", "stronghold", "mineshaft", "dungeon"), new bcm(8, Nk.SAND), new bcm(52, Nk.SANDSTONE), new bcm(3, Nk.STONE), new bcm(1, Nk.BEDROCK));
      registerPreset(Ax.format("createWorld.customize.preset.redstone_ready"), NK.REDSTONE, Nj.DESERT, Collections.emptyList(), new bcm(52, Nk.SANDSTONE), new bcm(3, Nk.STONE), new bcm(1, Nk.BEDROCK));
      registerPreset(Ax.format("createWorld.customize.preset.the_void"), OL.getItemFromBlock(Nk.BARRIER), Nj.VOID, Arrays.asList("decoration"), new bcm(1, Nk.AIR));
   }
}
