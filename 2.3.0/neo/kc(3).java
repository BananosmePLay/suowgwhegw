package neo;

import com.google.common.base.Predicate;
import com.google.common.primitives.Floats;
import java.io.IOException;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.math.MathHelper;

public class kc extends lg implements lw, kW {
   private final jX parent;
   protected String title = "Customize World Settings";
   protected String subtitle = "Page 1 of 3";
   protected String pageTitle = "Basic Settings";
   protected String[] pageNames = new String[4];
   private kY list;
   private jK done;
   private jK randomize;
   private jK defaults;
   private jK previousPage;
   private jK nextPage;
   private jK confirm;
   private jK cancel;
   private jK presets;
   private boolean settingsModified;
   private int confirmMode;
   private boolean confirmDismissed;
   private final Predicate<String> numberFilter = new Predicate<String>() {
      public boolean apply(@Nullable String p_apply_1_) {
         Float f = Floats.tryParse(p_apply_1_);
         return p_apply_1_.isEmpty() || f != null && Floats.isFinite(f) && f >= 0.0F;
      }

      // $FF: synthetic method
      // $FF: bridge method
      public boolean apply(@Nullable Object var1) {
         return this.apply((String)var1);
      }
   };
   private final bbi defaultSettings = new bbi();
   private bbi settings;
   private final Random random = new Random();

   public kc(lg parentIn, String p_i45521_2_) {
      this.parent = (jX)parentIn;
      this.loadValues(p_i45521_2_);
   }

   public void initGui() {
      int i = 0;
      int j = 0;
      if (this.list != null) {
         i = this.list.getPage();
         j = this.list.getAmountScrolled();
      }

      this.title = Ax.format("options.customizeTitle");
      this.buttonList.clear();
      this.previousPage = this.addButton(new jK(302, 20, 5, 80, 20, Ax.format("createWorld.customize.custom.prev")));
      this.nextPage = this.addButton(new jK(303, this.width - 100, 5, 80, 20, Ax.format("createWorld.customize.custom.next")));
      this.defaults = this.addButton(new jK(304, this.width / 2 - 187, this.height - 27, 90, 20, Ax.format("createWorld.customize.custom.defaults")));
      this.randomize = this.addButton(new jK(301, this.width / 2 - 92, this.height - 27, 90, 20, Ax.format("createWorld.customize.custom.randomize")));
      this.presets = this.addButton(new jK(305, this.width / 2 + 3, this.height - 27, 90, 20, Ax.format("createWorld.customize.custom.presets")));
      this.done = this.addButton(new jK(300, this.width / 2 + 98, this.height - 27, 90, 20, Ax.format("gui.done")));
      this.defaults.enabled = this.settingsModified;
      this.confirm = new jK(306, this.width / 2 - 55, 160, 50, 20, Ax.format("gui.yes"));
      this.confirm.visible = false;
      this.buttonList.add(this.confirm);
      this.cancel = new jK(307, this.width / 2 + 5, 160, 50, 20, Ax.format("gui.no"));
      this.cancel.visible = false;
      this.buttonList.add(this.cancel);
      if (this.confirmMode != 0) {
         this.confirm.visible = true;
         this.cancel.visible = true;
      }

      this.createPagedList();
      if (i != 0) {
         this.list.setPage(i);
         this.list.scrollBy(j);
         this.updatePageControls();
      }

   }

   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      this.list.handleMouseInput();
   }

   private void createPagedList() {
      kV[] aguipagebuttonlist$guilistentry = new kV[]{new kX(160, Ax.format("createWorld.customize.custom.seaLevel"), true, this, 1.0F, 255.0F, (float)this.settings.seaLevel), new kS(148, Ax.format("createWorld.customize.custom.useCaves"), true, this.settings.useCaves), new kS(150, Ax.format("createWorld.customize.custom.useStrongholds"), true, this.settings.useStrongholds), new kS(151, Ax.format("createWorld.customize.custom.useVillages"), true, this.settings.useVillages), new kS(152, Ax.format("createWorld.customize.custom.useMineShafts"), true, this.settings.useMineShafts), new kS(153, Ax.format("createWorld.customize.custom.useTemples"), true, this.settings.useTemples), new kS(210, Ax.format("createWorld.customize.custom.useMonuments"), true, this.settings.useMonuments), new kS(211, Ax.format("createWorld.customize.custom.useMansions"), true, this.settings.useMansions), new kS(154, Ax.format("createWorld.customize.custom.useRavines"), true, this.settings.useRavines), new kS(149, Ax.format("createWorld.customize.custom.useDungeons"), true, this.settings.useDungeons), new kX(157, Ax.format("createWorld.customize.custom.dungeonChance"), true, this, 1.0F, 100.0F, (float)this.settings.dungeonChance), new kS(155, Ax.format("createWorld.customize.custom.useWaterLakes"), true, this.settings.useWaterLakes), new kX(158, Ax.format("createWorld.customize.custom.waterLakeChance"), true, this, 1.0F, 100.0F, (float)this.settings.waterLakeChance), new kS(156, Ax.format("createWorld.customize.custom.useLavaLakes"), true, this.settings.useLavaLakes), new kX(159, Ax.format("createWorld.customize.custom.lavaLakeChance"), true, this, 10.0F, 100.0F, (float)this.settings.lavaLakeChance), new kS(161, Ax.format("createWorld.customize.custom.useLavaOceans"), true, this.settings.useLavaOceans), new kX(162, Ax.format("createWorld.customize.custom.fixedBiome"), true, this, -1.0F, 37.0F, (float)this.settings.fixedBiome), new kX(163, Ax.format("createWorld.customize.custom.biomeSize"), true, this, 1.0F, 8.0F, (float)this.settings.biomeSize), new kX(164, Ax.format("createWorld.customize.custom.riverSize"), true, this, 1.0F, 5.0F, (float)this.settings.riverSize)};
      kV[] aguipagebuttonlist$guilistentry1 = new kV[]{new kU(416, Ax.format("tile.dirt.name"), false), null, new kX(165, Ax.format("createWorld.customize.custom.size"), false, this, 1.0F, 50.0F, (float)this.settings.dirtSize), new kX(166, Ax.format("createWorld.customize.custom.count"), false, this, 0.0F, 40.0F, (float)this.settings.dirtCount), new kX(167, Ax.format("createWorld.customize.custom.minHeight"), false, this, 0.0F, 255.0F, (float)this.settings.dirtMinHeight), new kX(168, Ax.format("createWorld.customize.custom.maxHeight"), false, this, 0.0F, 255.0F, (float)this.settings.dirtMaxHeight), new kU(417, Ax.format("tile.gravel.name"), false), null, new kX(169, Ax.format("createWorld.customize.custom.size"), false, this, 1.0F, 50.0F, (float)this.settings.gravelSize), new kX(170, Ax.format("createWorld.customize.custom.count"), false, this, 0.0F, 40.0F, (float)this.settings.gravelCount), new kX(171, Ax.format("createWorld.customize.custom.minHeight"), false, this, 0.0F, 255.0F, (float)this.settings.gravelMinHeight), new kX(172, Ax.format("createWorld.customize.custom.maxHeight"), false, this, 0.0F, 255.0F, (float)this.settings.gravelMaxHeight), new kU(418, Ax.format("tile.stone.granite.name"), false), null, new kX(173, Ax.format("createWorld.customize.custom.size"), false, this, 1.0F, 50.0F, (float)this.settings.graniteSize), new kX(174, Ax.format("createWorld.customize.custom.count"), false, this, 0.0F, 40.0F, (float)this.settings.graniteCount), new kX(175, Ax.format("createWorld.customize.custom.minHeight"), false, this, 0.0F, 255.0F, (float)this.settings.graniteMinHeight), new kX(176, Ax.format("createWorld.customize.custom.maxHeight"), false, this, 0.0F, 255.0F, (float)this.settings.graniteMaxHeight), new kU(419, Ax.format("tile.stone.diorite.name"), false), null, new kX(177, Ax.format("createWorld.customize.custom.size"), false, this, 1.0F, 50.0F, (float)this.settings.dioriteSize), new kX(178, Ax.format("createWorld.customize.custom.count"), false, this, 0.0F, 40.0F, (float)this.settings.dioriteCount), new kX(179, Ax.format("createWorld.customize.custom.minHeight"), false, this, 0.0F, 255.0F, (float)this.settings.dioriteMinHeight), new kX(180, Ax.format("createWorld.customize.custom.maxHeight"), false, this, 0.0F, 255.0F, (float)this.settings.dioriteMaxHeight), new kU(420, Ax.format("tile.stone.andesite.name"), false), null, new kX(181, Ax.format("createWorld.customize.custom.size"), false, this, 1.0F, 50.0F, (float)this.settings.andesiteSize), new kX(182, Ax.format("createWorld.customize.custom.count"), false, this, 0.0F, 40.0F, (float)this.settings.andesiteCount), new kX(183, Ax.format("createWorld.customize.custom.minHeight"), false, this, 0.0F, 255.0F, (float)this.settings.andesiteMinHeight), new kX(184, Ax.format("createWorld.customize.custom.maxHeight"), false, this, 0.0F, 255.0F, (float)this.settings.andesiteMaxHeight), new kU(421, Ax.format("tile.oreCoal.name"), false), null, new kX(185, Ax.format("createWorld.customize.custom.size"), false, this, 1.0F, 50.0F, (float)this.settings.coalSize), new kX(186, Ax.format("createWorld.customize.custom.count"), false, this, 0.0F, 40.0F, (float)this.settings.coalCount), new kX(187, Ax.format("createWorld.customize.custom.minHeight"), false, this, 0.0F, 255.0F, (float)this.settings.coalMinHeight), new kX(189, Ax.format("createWorld.customize.custom.maxHeight"), false, this, 0.0F, 255.0F, (float)this.settings.coalMaxHeight), new kU(422, Ax.format("tile.oreIron.name"), false), null, new kX(190, Ax.format("createWorld.customize.custom.size"), false, this, 1.0F, 50.0F, (float)this.settings.ironSize), new kX(191, Ax.format("createWorld.customize.custom.count"), false, this, 0.0F, 40.0F, (float)this.settings.ironCount), new kX(192, Ax.format("createWorld.customize.custom.minHeight"), false, this, 0.0F, 255.0F, (float)this.settings.ironMinHeight), new kX(193, Ax.format("createWorld.customize.custom.maxHeight"), false, this, 0.0F, 255.0F, (float)this.settings.ironMaxHeight), new kU(423, Ax.format("tile.oreGold.name"), false), null, new kX(194, Ax.format("createWorld.customize.custom.size"), false, this, 1.0F, 50.0F, (float)this.settings.goldSize), new kX(195, Ax.format("createWorld.customize.custom.count"), false, this, 0.0F, 40.0F, (float)this.settings.goldCount), new kX(196, Ax.format("createWorld.customize.custom.minHeight"), false, this, 0.0F, 255.0F, (float)this.settings.goldMinHeight), new kX(197, Ax.format("createWorld.customize.custom.maxHeight"), false, this, 0.0F, 255.0F, (float)this.settings.goldMaxHeight), new kU(424, Ax.format("tile.oreRedstone.name"), false), null, new kX(198, Ax.format("createWorld.customize.custom.size"), false, this, 1.0F, 50.0F, (float)this.settings.redstoneSize), new kX(199, Ax.format("createWorld.customize.custom.count"), false, this, 0.0F, 40.0F, (float)this.settings.redstoneCount), new kX(200, Ax.format("createWorld.customize.custom.minHeight"), false, this, 0.0F, 255.0F, (float)this.settings.redstoneMinHeight), new kX(201, Ax.format("createWorld.customize.custom.maxHeight"), false, this, 0.0F, 255.0F, (float)this.settings.redstoneMaxHeight), new kU(425, Ax.format("tile.oreDiamond.name"), false), null, new kX(202, Ax.format("createWorld.customize.custom.size"), false, this, 1.0F, 50.0F, (float)this.settings.diamondSize), new kX(203, Ax.format("createWorld.customize.custom.count"), false, this, 0.0F, 40.0F, (float)this.settings.diamondCount), new kX(204, Ax.format("createWorld.customize.custom.minHeight"), false, this, 0.0F, 255.0F, (float)this.settings.diamondMinHeight), new kX(205, Ax.format("createWorld.customize.custom.maxHeight"), false, this, 0.0F, 255.0F, (float)this.settings.diamondMaxHeight), new kU(426, Ax.format("tile.oreLapis.name"), false), null, new kX(206, Ax.format("createWorld.customize.custom.size"), false, this, 1.0F, 50.0F, (float)this.settings.lapisSize), new kX(207, Ax.format("createWorld.customize.custom.count"), false, this, 0.0F, 40.0F, (float)this.settings.lapisCount), new kX(208, Ax.format("createWorld.customize.custom.center"), false, this, 0.0F, 255.0F, (float)this.settings.lapisCenterHeight), new kX(209, Ax.format("createWorld.customize.custom.spread"), false, this, 0.0F, 255.0F, (float)this.settings.lapisSpread)};
      kV[] aguipagebuttonlist$guilistentry2 = new kV[]{new kX(100, Ax.format("createWorld.customize.custom.mainNoiseScaleX"), false, this, 1.0F, 5000.0F, this.settings.mainNoiseScaleX), new kX(101, Ax.format("createWorld.customize.custom.mainNoiseScaleY"), false, this, 1.0F, 5000.0F, this.settings.mainNoiseScaleY), new kX(102, Ax.format("createWorld.customize.custom.mainNoiseScaleZ"), false, this, 1.0F, 5000.0F, this.settings.mainNoiseScaleZ), new kX(103, Ax.format("createWorld.customize.custom.depthNoiseScaleX"), false, this, 1.0F, 2000.0F, this.settings.depthNoiseScaleX), new kX(104, Ax.format("createWorld.customize.custom.depthNoiseScaleZ"), false, this, 1.0F, 2000.0F, this.settings.depthNoiseScaleZ), new kX(105, Ax.format("createWorld.customize.custom.depthNoiseScaleExponent"), false, this, 0.01F, 20.0F, this.settings.depthNoiseScaleExponent), new kX(106, Ax.format("createWorld.customize.custom.baseSize"), false, this, 1.0F, 25.0F, this.settings.baseSize), new kX(107, Ax.format("createWorld.customize.custom.coordinateScale"), false, this, 1.0F, 6000.0F, this.settings.coordinateScale), new kX(108, Ax.format("createWorld.customize.custom.heightScale"), false, this, 1.0F, 6000.0F, this.settings.heightScale), new kX(109, Ax.format("createWorld.customize.custom.stretchY"), false, this, 0.01F, 50.0F, this.settings.stretchY), new kX(110, Ax.format("createWorld.customize.custom.upperLimitScale"), false, this, 1.0F, 5000.0F, this.settings.upperLimitScale), new kX(111, Ax.format("createWorld.customize.custom.lowerLimitScale"), false, this, 1.0F, 5000.0F, this.settings.lowerLimitScale), new kX(112, Ax.format("createWorld.customize.custom.biomeDepthWeight"), false, this, 1.0F, 20.0F, this.settings.biomeDepthWeight), new kX(113, Ax.format("createWorld.customize.custom.biomeDepthOffset"), false, this, 0.0F, 20.0F, this.settings.biomeDepthOffset), new kX(114, Ax.format("createWorld.customize.custom.biomeScaleWeight"), false, this, 1.0F, 20.0F, this.settings.biomeScaleWeight), new kX(115, Ax.format("createWorld.customize.custom.biomeScaleOffset"), false, this, 0.0F, 20.0F, this.settings.biomeScaleOffset)};
      kV[] aguipagebuttonlist$guilistentry3 = new kV[]{new kU(400, Ax.format("createWorld.customize.custom.mainNoiseScaleX") + ":", false), new kR(132, String.format("%5.3f", this.settings.mainNoiseScaleX), false, this.numberFilter), new kU(401, Ax.format("createWorld.customize.custom.mainNoiseScaleY") + ":", false), new kR(133, String.format("%5.3f", this.settings.mainNoiseScaleY), false, this.numberFilter), new kU(402, Ax.format("createWorld.customize.custom.mainNoiseScaleZ") + ":", false), new kR(134, String.format("%5.3f", this.settings.mainNoiseScaleZ), false, this.numberFilter), new kU(403, Ax.format("createWorld.customize.custom.depthNoiseScaleX") + ":", false), new kR(135, String.format("%5.3f", this.settings.depthNoiseScaleX), false, this.numberFilter), new kU(404, Ax.format("createWorld.customize.custom.depthNoiseScaleZ") + ":", false), new kR(136, String.format("%5.3f", this.settings.depthNoiseScaleZ), false, this.numberFilter), new kU(405, Ax.format("createWorld.customize.custom.depthNoiseScaleExponent") + ":", false), new kR(137, String.format("%2.3f", this.settings.depthNoiseScaleExponent), false, this.numberFilter), new kU(406, Ax.format("createWorld.customize.custom.baseSize") + ":", false), new kR(138, String.format("%2.3f", this.settings.baseSize), false, this.numberFilter), new kU(407, Ax.format("createWorld.customize.custom.coordinateScale") + ":", false), new kR(139, String.format("%5.3f", this.settings.coordinateScale), false, this.numberFilter), new kU(408, Ax.format("createWorld.customize.custom.heightScale") + ":", false), new kR(140, String.format("%5.3f", this.settings.heightScale), false, this.numberFilter), new kU(409, Ax.format("createWorld.customize.custom.stretchY") + ":", false), new kR(141, String.format("%2.3f", this.settings.stretchY), false, this.numberFilter), new kU(410, Ax.format("createWorld.customize.custom.upperLimitScale") + ":", false), new kR(142, String.format("%5.3f", this.settings.upperLimitScale), false, this.numberFilter), new kU(411, Ax.format("createWorld.customize.custom.lowerLimitScale") + ":", false), new kR(143, String.format("%5.3f", this.settings.lowerLimitScale), false, this.numberFilter), new kU(412, Ax.format("createWorld.customize.custom.biomeDepthWeight") + ":", false), new kR(144, String.format("%2.3f", this.settings.biomeDepthWeight), false, this.numberFilter), new kU(413, Ax.format("createWorld.customize.custom.biomeDepthOffset") + ":", false), new kR(145, String.format("%2.3f", this.settings.biomeDepthOffset), false, this.numberFilter), new kU(414, Ax.format("createWorld.customize.custom.biomeScaleWeight") + ":", false), new kR(146, String.format("%2.3f", this.settings.biomeScaleWeight), false, this.numberFilter), new kU(415, Ax.format("createWorld.customize.custom.biomeScaleOffset") + ":", false), new kR(147, String.format("%2.3f", this.settings.biomeScaleOffset), false, this.numberFilter)};
      this.list = new kY(this.mc, this.width, this.height, 32, this.height - 32, 25, this, new kV[][]{aguipagebuttonlist$guilistentry, aguipagebuttonlist$guilistentry1, aguipagebuttonlist$guilistentry2, aguipagebuttonlist$guilistentry3});

      for(int i = 0; i < 4; ++i) {
         this.pageNames[i] = Ax.format("createWorld.customize.custom.page" + i);
      }

      this.updatePageControls();
   }

   public String saveValues() {
      return this.settings.toString().replace("\n", "");
   }

   public void loadValues(String p_175324_1_) {
      if (p_175324_1_ != null && !p_175324_1_.isEmpty()) {
         this.settings = bbi.jsonToFactory(p_175324_1_);
      } else {
         this.settings = new bbi();
      }

   }

   public void setEntryValue(int id, String value) {
      float f = 0.0F;

      try {
         f = Float.parseFloat(value);
      } catch (NumberFormatException var5) {
      }

      float f1 = 0.0F;
      switch (id) {
         case 132:
            this.settings.mainNoiseScaleX = MathHelper.clamp(f, 1.0F, 5000.0F);
            f1 = this.settings.mainNoiseScaleX;
            break;
         case 133:
            this.settings.mainNoiseScaleY = MathHelper.clamp(f, 1.0F, 5000.0F);
            f1 = this.settings.mainNoiseScaleY;
            break;
         case 134:
            this.settings.mainNoiseScaleZ = MathHelper.clamp(f, 1.0F, 5000.0F);
            f1 = this.settings.mainNoiseScaleZ;
            break;
         case 135:
            this.settings.depthNoiseScaleX = MathHelper.clamp(f, 1.0F, 2000.0F);
            f1 = this.settings.depthNoiseScaleX;
            break;
         case 136:
            this.settings.depthNoiseScaleZ = MathHelper.clamp(f, 1.0F, 2000.0F);
            f1 = this.settings.depthNoiseScaleZ;
            break;
         case 137:
            this.settings.depthNoiseScaleExponent = MathHelper.clamp(f, 0.01F, 20.0F);
            f1 = this.settings.depthNoiseScaleExponent;
            break;
         case 138:
            this.settings.baseSize = MathHelper.clamp(f, 1.0F, 25.0F);
            f1 = this.settings.baseSize;
            break;
         case 139:
            this.settings.coordinateScale = MathHelper.clamp(f, 1.0F, 6000.0F);
            f1 = this.settings.coordinateScale;
            break;
         case 140:
            this.settings.heightScale = MathHelper.clamp(f, 1.0F, 6000.0F);
            f1 = this.settings.heightScale;
            break;
         case 141:
            this.settings.stretchY = MathHelper.clamp(f, 0.01F, 50.0F);
            f1 = this.settings.stretchY;
            break;
         case 142:
            this.settings.upperLimitScale = MathHelper.clamp(f, 1.0F, 5000.0F);
            f1 = this.settings.upperLimitScale;
            break;
         case 143:
            this.settings.lowerLimitScale = MathHelper.clamp(f, 1.0F, 5000.0F);
            f1 = this.settings.lowerLimitScale;
            break;
         case 144:
            this.settings.biomeDepthWeight = MathHelper.clamp(f, 1.0F, 20.0F);
            f1 = this.settings.biomeDepthWeight;
            break;
         case 145:
            this.settings.biomeDepthOffset = MathHelper.clamp(f, 0.0F, 20.0F);
            f1 = this.settings.biomeDepthOffset;
            break;
         case 146:
            this.settings.biomeScaleWeight = MathHelper.clamp(f, 1.0F, 20.0F);
            f1 = this.settings.biomeScaleWeight;
            break;
         case 147:
            this.settings.biomeScaleOffset = MathHelper.clamp(f, 0.0F, 20.0F);
            f1 = this.settings.biomeScaleOffset;
      }

      if (f1 != f && f != 0.0F) {
         ((lE)this.list.getComponent(id)).setText(this.getFormattedValue(id, f1));
      }

      ((lx)this.list.getComponent(id - 132 + 100)).setSliderValue(f1, false);
      if (!this.settings.equals(this.defaultSettings)) {
         this.setSettingsModified(true);
      }

   }

   private void setSettingsModified(boolean modified) {
      this.settingsModified = modified;
      this.defaults.enabled = modified;
   }

   public String getText(int id, String name, float value) {
      return name + ": " + this.getFormattedValue(id, value);
   }

   private String getFormattedValue(int p_175330_1_, float p_175330_2_) {
      switch (p_175330_1_) {
         case 100:
         case 101:
         case 102:
         case 103:
         case 104:
         case 107:
         case 108:
         case 110:
         case 111:
         case 132:
         case 133:
         case 134:
         case 135:
         case 136:
         case 139:
         case 140:
         case 142:
         case 143:
            return String.format("%5.3f", p_175330_2_);
         case 105:
         case 106:
         case 109:
         case 112:
         case 113:
         case 114:
         case 115:
         case 137:
         case 138:
         case 141:
         case 144:
         case 145:
         case 146:
         case 147:
            return String.format("%2.3f", p_175330_2_);
         case 116:
         case 117:
         case 118:
         case 119:
         case 120:
         case 121:
         case 122:
         case 123:
         case 124:
         case 125:
         case 126:
         case 127:
         case 128:
         case 129:
         case 130:
         case 131:
         case 148:
         case 149:
         case 150:
         case 151:
         case 152:
         case 153:
         case 154:
         case 155:
         case 156:
         case 157:
         case 158:
         case 159:
         case 160:
         case 161:
         default:
            return String.format("%d", (int)p_175330_2_);
         case 162:
            if (p_175330_2_ < 0.0F) {
               return Ax.format("gui.all");
            } else {
               Zi biome;
               if ((int)p_175330_2_ >= Zi.getIdForBiome(Nj.HELL)) {
                  biome = Zi.getBiomeForId((int)p_175330_2_ + 2);
                  return biome != null ? biome.getBiomeName() : "?";
               } else {
                  biome = Zi.getBiomeForId((int)p_175330_2_);
                  return biome != null ? biome.getBiomeName() : "?";
               }
            }
      }
   }

   public void setEntryValue(int id, boolean value) {
      switch (id) {
         case 148:
            this.settings.useCaves = value;
            break;
         case 149:
            this.settings.useDungeons = value;
            break;
         case 150:
            this.settings.useStrongholds = value;
            break;
         case 151:
            this.settings.useVillages = value;
            break;
         case 152:
            this.settings.useMineShafts = value;
            break;
         case 153:
            this.settings.useTemples = value;
            break;
         case 154:
            this.settings.useRavines = value;
            break;
         case 155:
            this.settings.useWaterLakes = value;
            break;
         case 156:
            this.settings.useLavaLakes = value;
            break;
         case 161:
            this.settings.useLavaOceans = value;
            break;
         case 210:
            this.settings.useMonuments = value;
            break;
         case 211:
            this.settings.useMansions = value;
      }

      if (!this.settings.equals(this.defaultSettings)) {
         this.setSettingsModified(true);
      }

   }

   public void setEntryValue(int id, float value) {
      switch (id) {
         case 100:
            this.settings.mainNoiseScaleX = value;
            break;
         case 101:
            this.settings.mainNoiseScaleY = value;
            break;
         case 102:
            this.settings.mainNoiseScaleZ = value;
            break;
         case 103:
            this.settings.depthNoiseScaleX = value;
            break;
         case 104:
            this.settings.depthNoiseScaleZ = value;
            break;
         case 105:
            this.settings.depthNoiseScaleExponent = value;
            break;
         case 106:
            this.settings.baseSize = value;
            break;
         case 107:
            this.settings.coordinateScale = value;
            break;
         case 108:
            this.settings.heightScale = value;
            break;
         case 109:
            this.settings.stretchY = value;
            break;
         case 110:
            this.settings.upperLimitScale = value;
            break;
         case 111:
            this.settings.lowerLimitScale = value;
            break;
         case 112:
            this.settings.biomeDepthWeight = value;
            break;
         case 113:
            this.settings.biomeDepthOffset = value;
            break;
         case 114:
            this.settings.biomeScaleWeight = value;
            break;
         case 115:
            this.settings.biomeScaleOffset = value;
         case 116:
         case 117:
         case 118:
         case 119:
         case 120:
         case 121:
         case 122:
         case 123:
         case 124:
         case 125:
         case 126:
         case 127:
         case 128:
         case 129:
         case 130:
         case 131:
         case 132:
         case 133:
         case 134:
         case 135:
         case 136:
         case 137:
         case 138:
         case 139:
         case 140:
         case 141:
         case 142:
         case 143:
         case 144:
         case 145:
         case 146:
         case 147:
         case 148:
         case 149:
         case 150:
         case 151:
         case 152:
         case 153:
         case 154:
         case 155:
         case 156:
         case 161:
         case 188:
         default:
            break;
         case 157:
            this.settings.dungeonChance = (int)value;
            break;
         case 158:
            this.settings.waterLakeChance = (int)value;
            break;
         case 159:
            this.settings.lavaLakeChance = (int)value;
            break;
         case 160:
            this.settings.seaLevel = (int)value;
            break;
         case 162:
            this.settings.fixedBiome = (int)value;
            break;
         case 163:
            this.settings.biomeSize = (int)value;
            break;
         case 164:
            this.settings.riverSize = (int)value;
            break;
         case 165:
            this.settings.dirtSize = (int)value;
            break;
         case 166:
            this.settings.dirtCount = (int)value;
            break;
         case 167:
            this.settings.dirtMinHeight = (int)value;
            break;
         case 168:
            this.settings.dirtMaxHeight = (int)value;
            break;
         case 169:
            this.settings.gravelSize = (int)value;
            break;
         case 170:
            this.settings.gravelCount = (int)value;
            break;
         case 171:
            this.settings.gravelMinHeight = (int)value;
            break;
         case 172:
            this.settings.gravelMaxHeight = (int)value;
            break;
         case 173:
            this.settings.graniteSize = (int)value;
            break;
         case 174:
            this.settings.graniteCount = (int)value;
            break;
         case 175:
            this.settings.graniteMinHeight = (int)value;
            break;
         case 176:
            this.settings.graniteMaxHeight = (int)value;
            break;
         case 177:
            this.settings.dioriteSize = (int)value;
            break;
         case 178:
            this.settings.dioriteCount = (int)value;
            break;
         case 179:
            this.settings.dioriteMinHeight = (int)value;
            break;
         case 180:
            this.settings.dioriteMaxHeight = (int)value;
            break;
         case 181:
            this.settings.andesiteSize = (int)value;
            break;
         case 182:
            this.settings.andesiteCount = (int)value;
            break;
         case 183:
            this.settings.andesiteMinHeight = (int)value;
            break;
         case 184:
            this.settings.andesiteMaxHeight = (int)value;
            break;
         case 185:
            this.settings.coalSize = (int)value;
            break;
         case 186:
            this.settings.coalCount = (int)value;
            break;
         case 187:
            this.settings.coalMinHeight = (int)value;
            break;
         case 189:
            this.settings.coalMaxHeight = (int)value;
            break;
         case 190:
            this.settings.ironSize = (int)value;
            break;
         case 191:
            this.settings.ironCount = (int)value;
            break;
         case 192:
            this.settings.ironMinHeight = (int)value;
            break;
         case 193:
            this.settings.ironMaxHeight = (int)value;
            break;
         case 194:
            this.settings.goldSize = (int)value;
            break;
         case 195:
            this.settings.goldCount = (int)value;
            break;
         case 196:
            this.settings.goldMinHeight = (int)value;
            break;
         case 197:
            this.settings.goldMaxHeight = (int)value;
            break;
         case 198:
            this.settings.redstoneSize = (int)value;
            break;
         case 199:
            this.settings.redstoneCount = (int)value;
            break;
         case 200:
            this.settings.redstoneMinHeight = (int)value;
            break;
         case 201:
            this.settings.redstoneMaxHeight = (int)value;
            break;
         case 202:
            this.settings.diamondSize = (int)value;
            break;
         case 203:
            this.settings.diamondCount = (int)value;
            break;
         case 204:
            this.settings.diamondMinHeight = (int)value;
            break;
         case 205:
            this.settings.diamondMaxHeight = (int)value;
            break;
         case 206:
            this.settings.lapisSize = (int)value;
            break;
         case 207:
            this.settings.lapisCount = (int)value;
            break;
         case 208:
            this.settings.lapisCenterHeight = (int)value;
            break;
         case 209:
            this.settings.lapisSpread = (int)value;
      }

      if (id >= 100 && id < 116) {
         jI gui = this.list.getComponent(id - 100 + 132);
         if (gui != null) {
            ((lE)gui).setText(this.getFormattedValue(id, value));
         }
      }

      if (!this.settings.equals(this.defaultSettings)) {
         this.setSettingsModified(true);
      }

   }

   protected void actionPerformed(jK button) throws IOException {
      if (button.enabled) {
         switch (button.id) {
            case 300:
               this.parent.chunkProviderSettingsJson = this.settings.toString();
               this.mc.displayGuiScreen(this.parent);
               break;
            case 301:
               for(int i = 0; i < this.list.getSize(); ++i) {
                  kT guipagebuttonlist$guientry = this.list.getListEntry(i);
                  jI gui = guipagebuttonlist$guientry.getComponent1();
                  if (gui instanceof jK) {
                     jK guibutton = (jK)gui;
                     if (guibutton instanceof lx) {
                        float f = ((lx)guibutton).getSliderPosition() * (0.75F + this.random.nextFloat() * 0.5F) + (this.random.nextFloat() * 0.1F - 0.05F);
                        ((lx)guibutton).setSliderPosition(MathHelper.clamp(f, 0.0F, 1.0F));
                     } else if (guibutton instanceof kw) {
                        ((kw)guibutton).setValue(this.random.nextBoolean());
                     }
                  }

                  jI gui1 = guipagebuttonlist$guientry.getComponent2();
                  if (gui1 instanceof jK) {
                     jK guibutton1 = (jK)gui1;
                     if (guibutton1 instanceof lx) {
                        float f1 = ((lx)guibutton1).getSliderPosition() * (0.75F + this.random.nextFloat() * 0.5F) + (this.random.nextFloat() * 0.1F - 0.05F);
                        ((lx)guibutton1).setSliderPosition(MathHelper.clamp(f1, 0.0F, 1.0F));
                     } else if (guibutton1 instanceof kw) {
                        ((kw)guibutton1).setValue(this.random.nextBoolean());
                     }
                  }
               }

               return;
            case 302:
               this.list.previousPage();
               this.updatePageControls();
               break;
            case 303:
               this.list.nextPage();
               this.updatePageControls();
               break;
            case 304:
               if (this.settingsModified) {
                  this.enterConfirmation(304);
               }
               break;
            case 305:
               this.mc.displayGuiScreen(new ln(this));
               break;
            case 306:
               this.exitConfirmation();
               break;
            case 307:
               this.confirmMode = 0;
               this.exitConfirmation();
         }
      }

   }

   private void restoreDefaults() {
      this.settings.setDefaults();
      this.createPagedList();
      this.setSettingsModified(false);
   }

   private void enterConfirmation(int confirmModeIn) {
      this.confirmMode = confirmModeIn;
      this.setConfirmationControls(true);
   }

   private void exitConfirmation() throws IOException {
      switch (this.confirmMode) {
         case 300:
            this.actionPerformed((kw)this.list.getComponent(300));
            break;
         case 304:
            this.restoreDefaults();
      }

      this.confirmMode = 0;
      this.confirmDismissed = true;
      this.setConfirmationControls(false);
   }

   private void setConfirmationControls(boolean visible) {
      this.confirm.visible = visible;
      this.cancel.visible = visible;
      this.randomize.enabled = !visible;
      this.done.enabled = !visible;
      this.previousPage.enabled = !visible;
      this.nextPage.enabled = !visible;
      this.defaults.enabled = this.settingsModified && !visible;
      this.presets.enabled = !visible;
      this.list.setActive(!visible);
   }

   private void updatePageControls() {
      this.previousPage.enabled = this.list.getPage() != 0;
      this.nextPage.enabled = this.list.getPage() != this.list.getPageCount() - 1;
      this.subtitle = Ax.format("book.pageIndicator", this.list.getPage() + 1, this.list.getPageCount());
      this.pageTitle = this.pageNames[this.list.getPage()];
      this.randomize.enabled = this.list.getPage() != this.list.getPageCount() - 1;
   }

   protected void keyTyped(char typedChar, int keyCode) throws IOException {
      super.keyTyped(typedChar, keyCode);
      if (this.confirmMode == 0) {
         switch (keyCode) {
            case 200:
               this.modifyFocusValue(1.0F);
               break;
            case 208:
               this.modifyFocusValue(-1.0F);
               break;
            default:
               this.list.onKeyPressed(typedChar, keyCode);
         }
      }

   }

   private void modifyFocusValue(float p_175327_1_) {
      jI gui = this.list.getFocusedControl();
      if (gui instanceof lE) {
         float f = p_175327_1_;
         if (lg.isShiftKeyDown()) {
            f = p_175327_1_ * 0.1F;
            if (lg.isCtrlKeyDown()) {
               f *= 0.1F;
            }
         } else if (lg.isCtrlKeyDown()) {
            f = p_175327_1_ * 10.0F;
            if (lg.isAltKeyDown()) {
               f *= 10.0F;
            }
         }

         lE guitextfield = (lE)gui;
         Float f1 = Floats.tryParse(guitextfield.getText());
         if (f1 != null) {
            f1 = f1 + f;
            int i = guitextfield.getId();
            String s = this.getFormattedValue(guitextfield.getId(), f1);
            guitextfield.setText(s);
            this.setEntryValue(i, s);
         }
      }

   }

   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      if (this.confirmMode == 0 && !this.confirmDismissed) {
         this.list.mouseClicked(mouseX, mouseY, mouseButton);
      }

   }

   protected void mouseReleased(int mouseX, int mouseY, int state) {
      super.mouseReleased(mouseX, mouseY, state);
      if (this.confirmDismissed) {
         this.confirmDismissed = false;
      } else if (this.confirmMode == 0) {
         this.list.mouseReleased(mouseX, mouseY, state);
      }

   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      this.list.drawScreen(mouseX, mouseY, partialTicks);
      this.drawCenteredString(this.fontRenderer, this.title, this.width / 2, 2, 16777215);
      this.drawCenteredString(this.fontRenderer, this.subtitle, this.width / 2, 12, 16777215);
      this.drawCenteredString(this.fontRenderer, this.pageTitle, this.width / 2, 22, 16777215);
      super.drawScreen(mouseX, mouseY, partialTicks);
      if (this.confirmMode != 0) {
         drawRect(0, 0, this.width, this.height, Integer.MIN_VALUE);
         this.drawHorizontalLine(this.width / 2 - 91, this.width / 2 + 90, 99, -2039584);
         this.drawHorizontalLine(this.width / 2 - 91, this.width / 2 + 90, 185, -6250336);
         this.drawVerticalLine(this.width / 2 - 91, 99, 185, -2039584);
         this.drawVerticalLine(this.width / 2 + 90, 99, 185, -6250336);
         float f = 85.0F;
         float f1 = 180.0F;
         yh.disableLighting();
         yh.disableFog();
         yN tessellator = yN.getInstance();
         tN bufferbuilder = tessellator.getBuffer();
         this.mc.getTextureManager().bindTexture(OPTIONS_BACKGROUND);
         yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         float f2 = 32.0F;
         bufferbuilder.begin(7, zK.POSITION_TEX_COLOR);
         bufferbuilder.pos((double)(this.width / 2 - 90), 185.0, 0.0).tex(0.0, 2.65625).color(64, 64, 64, 64).endVertex();
         bufferbuilder.pos((double)(this.width / 2 + 90), 185.0, 0.0).tex(5.625, 2.65625).color(64, 64, 64, 64).endVertex();
         bufferbuilder.pos((double)(this.width / 2 + 90), 100.0, 0.0).tex(5.625, 0.0).color(64, 64, 64, 64).endVertex();
         bufferbuilder.pos((double)(this.width / 2 - 90), 100.0, 0.0).tex(0.0, 0.0).color(64, 64, 64, 64).endVertex();
         tessellator.draw();
         this.drawCenteredString(this.fontRenderer, Ax.format("createWorld.customize.custom.confirmTitle"), this.width / 2, 105, 16777215);
         this.drawCenteredString(this.fontRenderer, Ax.format("createWorld.customize.custom.confirm1"), this.width / 2, 125, 16777215);
         this.drawCenteredString(this.fontRenderer, Ax.format("createWorld.customize.custom.confirm2"), this.width / 2, 135, 16777215);
         this.confirm.drawButton(this.mc, mouseX, mouseY, partialTicks);
         this.cancel.drawButton(this.mc, mouseX, mouseY, partialTicks);
      }

   }
}
