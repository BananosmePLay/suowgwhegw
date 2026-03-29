package neo;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Iterator;
import org.lwjgl.Sys;

public class boU extends bmw {
   protected lg parentGui;
   protected String screenTitle = "Shaders";
   private bmy tooltipManager = new bmy(this, new bmA());
   private int updateTimer = -1;
   private boX shaderList;
   private boolean saved = false;
   private static float[] QUALITY_MULTIPLIERS = new float[]{0.5F, 0.6F, 0.6666667F, 0.75F, 0.8333333F, 0.9F, 1.0F, 1.1666666F, 1.3333334F, 1.5F, 1.6666666F, 1.8F, 2.0F};
   private static String[] QUALITY_MULTIPLIER_NAMES = new String[]{"0.5x", "0.6x", "0.66x", "0.75x", "0.83x", "0.9x", "1x", "1.16x", "1.33x", "1.5x", "1.66x", "1.8x", "2x"};
   private static float QUALITY_MULTIPLIER_DEFAULT = 1.0F;
   private static float[] HAND_DEPTH_VALUES = new float[]{0.0625F, 0.125F, 0.25F};
   private static String[] HAND_DEPTH_NAMES = new String[]{"0.5x", "1x", "2x"};
   private static float HAND_DEPTH_DEFAULT = 0.125F;
   public static final int EnumOS_UNKNOWN = 0;
   public static final int EnumOS_WINDOWS = 1;
   public static final int EnumOS_OSX = 2;
   public static final int EnumOS_SOLARIS = 3;
   public static final int EnumOS_LINUX = 4;

   public boU(lg par1GuiScreen, Bj par2GameSettings) {
      this.parentGui = par1GuiScreen;
   }

   public void initGui() {
      this.screenTitle = Ax.format("of.options.shadersTitle");
      if (bpq.shadersConfig == null) {
         bpq.loadConfig();
      }

      int i = 120;
      int j = 20;
      int k = this.width - i - 10;
      int l = 30;
      int i1 = 20;
      int j1 = this.width - i - 20;
      this.shaderList = new boX(this, j1, this.height, l, this.height - 50, 16);
      this.shaderList.registerScrollButtons(7, 8);
      this.buttonList.add(new boQ(bog.ANTIALIASING, k, 0 * i1 + l, i, j));
      this.buttonList.add(new boQ(bog.NORMAL_MAP, k, 1 * i1 + l, i, j));
      this.buttonList.add(new boQ(bog.SPECULAR_MAP, k, 2 * i1 + l, i, j));
      this.buttonList.add(new boQ(bog.RENDER_RES_MUL, k, 3 * i1 + l, i, j));
      this.buttonList.add(new boQ(bog.SHADOW_RES_MUL, k, 4 * i1 + l, i, j));
      this.buttonList.add(new boQ(bog.HAND_DEPTH_MUL, k, 5 * i1 + l, i, j));
      this.buttonList.add(new boQ(bog.OLD_HAND_LIGHT, k, 6 * i1 + l, i, j));
      this.buttonList.add(new boQ(bog.OLD_LIGHTING, k, 7 * i1 + l, i, j));
      int k1 = Math.min(150, j1 / 2 - 10);
      int l1 = j1 / 4 - k1 / 2;
      int i2 = this.height - 25;
      this.buttonList.add(new jK(201, l1, i2, k1 - 22 + 1, j, bmW.get("of.options.shaders.shadersFolder")));
      this.buttonList.add(new boO(210, l1 + k1 - 22 - 1, i2));
      this.buttonList.add(new jK(202, j1 / 4 * 3 - k1 / 2, this.height - 25, k1, j, Ax.format("gui.done")));
      this.buttonList.add(new jK(203, k, this.height - 25, i, j, bmW.get("of.options.shaders.shaderOptions")));
      this.updateButtons();
   }

   public void updateButtons() {
      boolean flag = XH.isShaders();
      Iterator var2 = this.buttonList.iterator();

      while(var2.hasNext()) {
         jK guibutton = (jK)var2.next();
         if (guibutton.id != 201 && guibutton.id != 202 && guibutton.id != 210 && guibutton.id != bog.ANTIALIASING.ordinal()) {
            guibutton.enabled = flag;
         }
      }

   }

   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      this.shaderList.handleMouseInput();
   }

   protected void actionPerformed(jK button) {
      this.actionPerformed(button, false);
   }

   protected void actionPerformedRightClick(jK button) {
      this.actionPerformed(button, true);
   }

   private void actionPerformed(jK button, boolean rightClick) {
      if (button.enabled) {
         if (!(button instanceof boQ)) {
            if (!rightClick) {
               switch (button.id) {
                  case 201:
                     IOException ioexception;
                     switch (getOSType()) {
                        case 1:
                           String s = String.format("cmd.exe /C start \"Open file\" \"%s\"", bpq.shaderPacksDir.getAbsolutePath());

                           try {
                              Runtime.getRuntime().exec(s);
                              return;
                           } catch (IOException var10) {
                              ioexception = var10;
                              ioexception.printStackTrace();
                              break;
                           }
                        case 2:
                           try {
                              Runtime.getRuntime().exec(new String[]{"/usr/bin/open", bpq.shaderPacksDir.getAbsolutePath()});
                              return;
                           } catch (IOException var9) {
                              ioexception = var9;
                              ioexception.printStackTrace();
                           }
                     }

                     boolean flag = false;

                     try {
                        Class oclass1 = Class.forName("java.awt.Desktop");
                        Object object1 = oclass1.getMethod("getDesktop").invoke((Object)null);
                        oclass1.getMethod("browse", URI.class).invoke(object1, (new File(this.mc.gameDir, "shaderpacks")).toURI());
                     } catch (Throwable var8) {
                        Throwable throwable1 = var8;
                        throwable1.printStackTrace();
                        flag = true;
                     }

                     if (flag) {
                        XH.dbg("Opening via system class!");
                        Sys.openURL("file://" + bpq.shaderPacksDir.getAbsolutePath());
                     }
                     break;
                  case 202:
                     bpq.storeConfig();
                     this.saved = true;
                     this.mc.displayGuiScreen(this.parentGui);
                     break;
                  case 203:
                     boS guishaderoptions = new boS(this, XH.getGameSettings());
                     XH.getMinecraft().displayGuiScreen(guishaderoptions);
                     break;
                  case 210:
                     try {
                        Class<?> oclass = Class.forName("java.awt.Desktop");
                        Object object = oclass.getMethod("getDesktop").invoke((Object)null);
                        oclass.getMethod("browse", URI.class).invoke(object, new URI("http://optifine.net/shaderPacks"));
                     } catch (Throwable var7) {
                        Throwable throwable1 = var7;
                        throwable1.printStackTrace();
                     }
                  case 204:
                  case 205:
                  case 206:
                  case 207:
                  case 208:
                  case 209:
                  default:
                     this.shaderList.actionPerformed(button);
               }
            }
         } else {
            boQ guibuttonenumshaderoption = (boQ)button;
            switch (guibuttonenumshaderoption.getEnumShaderOption()) {
               case ANTIALIASING:
                  bpq.nextAntialiasingLevel(!rightClick);
                  if (this.hasShiftDown()) {
                     bpq.configAntialiasingLevel = 0;
                  }

                  bpq.uninit();
                  break;
               case NORMAL_MAP:
                  bpq.configNormalMap = !bpq.configNormalMap;
                  if (this.hasShiftDown()) {
                     bpq.configNormalMap = true;
                  }

                  bpq.uninit();
                  this.mc.scheduleResourcesRefresh();
                  break;
               case SPECULAR_MAP:
                  bpq.configSpecularMap = !bpq.configSpecularMap;
                  if (this.hasShiftDown()) {
                     bpq.configSpecularMap = true;
                  }

                  bpq.uninit();
                  this.mc.scheduleResourcesRefresh();
                  break;
               case RENDER_RES_MUL:
                  bpq.configRenderResMul = this.getNextValue(bpq.configRenderResMul, QUALITY_MULTIPLIERS, QUALITY_MULTIPLIER_DEFAULT, !rightClick, this.hasShiftDown());
                  bpq.uninit();
                  bpq.scheduleResize();
                  break;
               case SHADOW_RES_MUL:
                  bpq.configShadowResMul = this.getNextValue(bpq.configShadowResMul, QUALITY_MULTIPLIERS, QUALITY_MULTIPLIER_DEFAULT, !rightClick, this.hasShiftDown());
                  bpq.uninit();
                  bpq.scheduleResizeShadow();
                  break;
               case HAND_DEPTH_MUL:
                  bpq.configHandDepthMul = this.getNextValue(bpq.configHandDepthMul, HAND_DEPTH_VALUES, HAND_DEPTH_DEFAULT, !rightClick, this.hasShiftDown());
                  bpq.uninit();
                  break;
               case OLD_HAND_LIGHT:
                  bpq.configOldHandLight.nextValue(!rightClick);
                  if (this.hasShiftDown()) {
                     bpq.configOldHandLight.resetValue();
                  }

                  bpq.uninit();
                  break;
               case OLD_LIGHTING:
                  bpq.configOldLighting.nextValue(!rightClick);
                  if (this.hasShiftDown()) {
                     bpq.configOldLighting.resetValue();
                  }

                  bpq.updateBlockLightLevel();
                  bpq.uninit();
                  this.mc.scheduleResourcesRefresh();
                  break;
               case TWEAK_BLOCK_DAMAGE:
                  bpq.configTweakBlockDamage = !bpq.configTweakBlockDamage;
                  break;
               case CLOUD_SHADOW:
                  bpq.configCloudShadow = !bpq.configCloudShadow;
                  break;
               case TEX_MIN_FIL_B:
                  bpq.configTexMinFilB = (bpq.configTexMinFilB + 1) % 3;
                  bpq.configTexMinFilN = bpq.configTexMinFilS = bpq.configTexMinFilB;
                  button.displayString = "Tex Min: " + bpq.texMinFilDesc[bpq.configTexMinFilB];
                  bps.updateTextureMinMagFilter();
                  break;
               case TEX_MAG_FIL_N:
                  bpq.configTexMagFilN = (bpq.configTexMagFilN + 1) % 2;
                  button.displayString = "Tex_n Mag: " + bpq.texMagFilDesc[bpq.configTexMagFilN];
                  bps.updateTextureMinMagFilter();
                  break;
               case TEX_MAG_FIL_S:
                  bpq.configTexMagFilS = (bpq.configTexMagFilS + 1) % 2;
                  button.displayString = "Tex_s Mag: " + bpq.texMagFilDesc[bpq.configTexMagFilS];
                  bps.updateTextureMinMagFilter();
                  break;
               case SHADOW_CLIP_FRUSTRUM:
                  bpq.configShadowClipFrustrum = !bpq.configShadowClipFrustrum;
                  button.displayString = "ShadowClipFrustrum: " + toStringOnOff(bpq.configShadowClipFrustrum);
                  bps.updateTextureMinMagFilter();
            }

            guibuttonenumshaderoption.updateButtonText();
         }
      }

   }

   public void onGuiClosed() {
      super.onGuiClosed();
      if (!this.saved) {
         bpq.storeConfig();
      }

   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      this.shaderList.drawScreen(mouseX, mouseY, partialTicks);
      if (this.updateTimer <= 0) {
         this.shaderList.updateList();
         this.updateTimer += 20;
      }

      this.drawCenteredString(this.fontRenderer, this.screenTitle + " ", this.width / 2, 15, 16777215);
      String s = "OpenGL: " + bpq.glVersionString + ", " + bpq.glVendorString + ", " + bpq.glRendererString;
      int i = this.fontRenderer.getStringWidth(s);
      if (i < this.width - 5) {
         this.drawCenteredString(this.fontRenderer, s, this.width / 2, this.height - 40, 8421504);
      } else {
         this.drawString(this.fontRenderer, s, 5, this.height - 40, 8421504);
      }

      super.drawScreen(mouseX, mouseY, partialTicks);
      this.tooltipManager.drawTooltips(mouseX, mouseY, this.buttonList);
   }

   public void updateScreen() {
      super.updateScreen();
      --this.updateTimer;
   }

   public nC getMc() {
      return this.mc;
   }

   public void drawCenteredString(String text, int x, int y, int color) {
      this.drawCenteredString(this.fontRenderer, text, x, y, color);
   }

   public static String toStringOnOff(boolean value) {
      String s = bmW.getOn();
      String s1 = bmW.getOff();
      return value ? s : s1;
   }

   public static String toStringAa(int value) {
      if (value == 2) {
         return "FXAA 2x";
      } else {
         return value == 4 ? "FXAA 4x" : bmW.getOff();
      }
   }

   public static String toStringValue(float val, float[] values, String[] names) {
      int i = getValueIndex(val, values);
      return names[i];
   }

   private float getNextValue(float val, float[] values, float valDef, boolean forward, boolean reset) {
      if (reset) {
         return valDef;
      } else {
         int i = getValueIndex(val, values);
         if (forward) {
            ++i;
            if (i >= values.length) {
               i = 0;
            }
         } else {
            --i;
            if (i < 0) {
               i = values.length - 1;
            }
         }

         return values[i];
      }
   }

   public static int getValueIndex(float val, float[] values) {
      for(int i = 0; i < values.length; ++i) {
         float f = values[i];
         if (f >= val) {
            return i;
         }
      }

      return values.length - 1;
   }

   public static String toStringQuality(float val) {
      return toStringValue(val, QUALITY_MULTIPLIERS, QUALITY_MULTIPLIER_NAMES);
   }

   public static String toStringHandDepth(float val) {
      return toStringValue(val, HAND_DEPTH_VALUES, HAND_DEPTH_NAMES);
   }

   public static int getOSType() {
      String s = System.getProperty("os.name").toLowerCase();
      if (s.contains("win")) {
         return 1;
      } else if (s.contains("mac")) {
         return 2;
      } else if (s.contains("solaris")) {
         return 3;
      } else if (s.contains("sunos")) {
         return 3;
      } else if (s.contains("linux")) {
         return 4;
      } else {
         return s.contains("unix") ? 4 : 0;
      }
   }

   public boolean hasShiftDown() {
      return isShiftKeyDown();
   }
}
