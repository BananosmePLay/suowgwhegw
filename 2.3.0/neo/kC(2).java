package neo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class kC implements kx {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final DateFormat DATE_FORMAT = new SimpleDateFormat();
   private static final ResourceLocation ICON_MISSING = new ResourceLocation("textures/misc/unknown_server.png");
   private static final ResourceLocation ICON_OVERLAY_LOCATION = new ResourceLocation("textures/gui/world_selection.png");
   private final nC client;
   private final lJ worldSelScreen;
   private final bib worldSummary;
   private final ResourceLocation iconLocation;
   private final kz containingListSel;
   private File iconFile;
   private yP icon;
   private long lastClickTime;

   public kC(kz listWorldSelIn, bib worldSummaryIn, bgl saveFormat) {
      this.containingListSel = listWorldSelIn;
      this.worldSelScreen = listWorldSelIn.getGuiWorldSelection();
      this.worldSummary = worldSummaryIn;
      this.client = nC.getMinecraft();
      this.iconLocation = new ResourceLocation("worlds/" + worldSummaryIn.getFileName() + "/icon");
      this.iconFile = saveFormat.getFile(worldSummaryIn.getFileName(), "icon.png");
      if (!this.iconFile.isFile()) {
         this.iconFile = null;
      }

      this.loadServerIcon();
   }

   public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected, float partialTicks) {
      String s = this.worldSummary.getDisplayName();
      String s1 = this.worldSummary.getFileName() + " (" + DATE_FORMAT.format(new Date(this.worldSummary.getLastTimePlayed())) + ")";
      String s2 = "";
      if (StringUtils.isEmpty(s)) {
         s = Ax.format("selectWorld.world") + " " + (slotIndex + 1);
      }

      if (this.worldSummary.requiresConversion()) {
         s2 = Ax.format("selectWorld.conversion") + " " + s2;
      } else {
         s2 = Ax.format("gameMode." + this.worldSummary.getEnumGameType().getName());
         if (this.worldSummary.isHardcoreModeEnabled()) {
            s2 = TextFormatting.DARK_RED + Ax.format("gameMode.hardcore") + TextFormatting.RESET;
         }

         if (this.worldSummary.getCheatsEnabled()) {
            s2 = s2 + ", " + Ax.format("selectWorld.cheats");
         }

         String s3 = this.worldSummary.getVersionName();
         if (this.worldSummary.markVersionInList()) {
            if (this.worldSummary.askToOpenWorld()) {
               s2 = s2 + ", " + Ax.format("selectWorld.version") + " " + TextFormatting.RED + s3 + TextFormatting.RESET;
            } else {
               s2 = s2 + ", " + Ax.format("selectWorld.version") + " " + TextFormatting.ITALIC + s3 + TextFormatting.RESET;
            }
         } else {
            s2 = s2 + ", " + Ax.format("selectWorld.version") + " " + s3;
         }
      }

      this.client.fontRenderer.drawString(s, x + 32 + 3, y + 1, 16777215);
      this.client.fontRenderer.drawString(s1, x + 32 + 3, y + this.client.fontRenderer.FONT_HEIGHT + 3, 8421504);
      this.client.fontRenderer.drawString(s2, x + 32 + 3, y + this.client.fontRenderer.FONT_HEIGHT + this.client.fontRenderer.FONT_HEIGHT + 3, 8421504);
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.client.getTextureManager().bindTexture(this.icon != null ? this.iconLocation : ICON_MISSING);
      yh.enableBlend();
      jI.drawModalRectWithCustomSizedTexture(x, y, 0.0F, 0.0F, 32, 32, 32.0F, 32.0F);
      yh.disableBlend();
      nC var10000 = this.client;
      if (nC.gameSettings.touchscreen || isSelected) {
         this.client.getTextureManager().bindTexture(ICON_OVERLAY_LOCATION);
         jI.drawRect(x, y, x + 32, y + 32, -1601138544);
         yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         int j = mouseX - x;
         int i = j < 32 ? 32 : 0;
         if (this.worldSummary.markVersionInList()) {
            jI.drawModalRectWithCustomSizedTexture(x, y, 32.0F, (float)i, 32, 32, 256.0F, 256.0F);
            if (this.worldSummary.askToOpenWorld()) {
               jI.drawModalRectWithCustomSizedTexture(x, y, 96.0F, (float)i, 32, 32, 256.0F, 256.0F);
               if (j < 32) {
                  this.worldSelScreen.setVersionTooltip(TextFormatting.RED + Ax.format("selectWorld.tooltip.fromNewerVersion1") + "\n" + TextFormatting.RED + Ax.format("selectWorld.tooltip.fromNewerVersion2"));
               }
            } else {
               jI.drawModalRectWithCustomSizedTexture(x, y, 64.0F, (float)i, 32, 32, 256.0F, 256.0F);
               if (j < 32) {
                  this.worldSelScreen.setVersionTooltip(TextFormatting.GOLD + Ax.format("selectWorld.tooltip.snapshot1") + "\n" + TextFormatting.GOLD + Ax.format("selectWorld.tooltip.snapshot2"));
               }
            }
         } else {
            jI.drawModalRectWithCustomSizedTexture(x, y, 0.0F, (float)i, 32, 32, 256.0F, 256.0F);
         }
      }

   }

   public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY) {
      this.containingListSel.selectWorld(slotIndex);
      if (relativeX <= 32 && relativeX < 32) {
         this.joinWorld();
         return true;
      } else if (nC.getSystemTime() - this.lastClickTime < 250L) {
         this.joinWorld();
         return true;
      } else {
         this.lastClickTime = nC.getSystemTime();
         return false;
      }
   }

   public void joinWorld() {
      if (this.worldSummary.askToOpenWorld()) {
         this.client.displayGuiScreen(new lK(new lL() {
            public void confirmClicked(boolean result, int id) {
               if (result) {
                  kC.this.loadWorld();
               } else {
                  kC.this.client.displayGuiScreen(kC.this.worldSelScreen);
               }

            }
         }, Ax.format("selectWorld.versionQuestion"), Ax.format("selectWorld.versionWarning", this.worldSummary.getVersionName()), Ax.format("selectWorld.versionJoinButton"), Ax.format("gui.cancel"), 0));
      } else {
         this.loadWorld();
      }

   }

   public void deleteWorld() {
      this.client.displayGuiScreen(new lK(new lL() {
         public void confirmClicked(boolean result, int id) {
            if (result) {
               kC.this.client.displayGuiScreen(new lt());
               bgl isaveformat = kC.this.client.getSaveLoader();
               isaveformat.flushCache();
               isaveformat.deleteWorldDirectory(kC.this.worldSummary.getFileName());
               kC.this.containingListSel.refreshList();
            }

            kC.this.client.displayGuiScreen(kC.this.worldSelScreen);
         }
      }, Ax.format("selectWorld.deleteQuestion"), "'" + this.worldSummary.getDisplayName() + "' " + Ax.format("selectWorld.deleteWarning"), Ax.format("selectWorld.deleteButton"), Ax.format("gui.cancel"), 0));
   }

   public void editWorld() {
      this.client.displayGuiScreen(new lI(this.worldSelScreen, this.worldSummary.getFileName()));
   }

   public void recreateWorld() {
      this.client.displayGuiScreen(new lt());
      jX guicreateworld = new jX(this.worldSelScreen);
      bgm isavehandler = this.client.getSaveLoader().getSaveLoader(this.worldSummary.getFileName(), false);
      bhY worldinfo = isavehandler.loadWorldInfo();
      isavehandler.flush();
      if (worldinfo != null) {
         guicreateworld.recreateFromExistingWorld(worldinfo);
         this.client.displayGuiScreen(guicreateworld);
      }

   }

   private void loadWorld() {
      this.client.getSoundHandler().playSound(iN.getMasterRecord(NO.UI_BUTTON_CLICK, 1.0F));
      if (this.client.getSaveLoader().canLoadWorld(this.worldSummary.getFileName())) {
         this.client.launchIntegratedServer(this.worldSummary.getFileName(), this.worldSummary.getDisplayName(), (biw)null);
      }

   }

   private void loadServerIcon() {
      boolean flag = this.iconFile != null && this.iconFile.isFile();
      if (flag) {
         BufferedImage bufferedimage;
         try {
            bufferedimage = ImageIO.read(this.iconFile);
            Validate.validState(bufferedimage.getWidth() == 64, "Must be 64 pixels wide", new Object[0]);
            Validate.validState(bufferedimage.getHeight() == 64, "Must be 64 pixels high", new Object[0]);
         } catch (Throwable var4) {
            Throwable throwable = var4;
            LOGGER.error("Invalid icon for world {}", this.worldSummary.getFileName(), throwable);
            this.iconFile = null;
            return;
         }

         if (this.icon == null) {
            this.icon = new yP(bufferedimage.getWidth(), bufferedimage.getHeight());
            this.client.getTextureManager().loadTexture(this.iconLocation, this.icon);
         }

         bufferedimage.getRGB(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(), this.icon.getTextureData(), 0, bufferedimage.getWidth());
         this.icon.updateDynamicTexture();
      } else if (!flag) {
         this.client.getTextureManager().deleteTexture(this.iconLocation);
         this.icon = null;
      }

   }

   public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
   }

   public void updatePosition(int slotIndex, int x, int y, float partialTicks) {
   }
}
