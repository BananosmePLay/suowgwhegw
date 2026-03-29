package neo;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.FoodStats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

public class kn extends jI {
   private static final ResourceLocation VIGNETTE_TEX_PATH = new ResourceLocation("textures/misc/vignette.png");
   private static final ResourceLocation WIDGETS_TEX_PATH = new ResourceLocation("textures/gui/widgets.png");
   private static final ResourceLocation PUMPKIN_BLUR_TEX_PATH = new ResourceLocation("textures/misc/pumpkinblur.png");
   private final Random rand = new Random();
   private final nC mc;
   private final yK itemRenderer;
   private final kJ persistantChatGUI;
   private int updateCounter;
   private String overlayMessage = "";
   private int overlayMessageTime;
   private boolean animateOverlayMessageColor;
   public float prevVignetteBrightness = 1.0F;
   private int remainingHighlightTicks;
   private Qy highlightingItemStack;
   private final kQ overlayDebug;
   private final lD overlaySubtitle;
   private final lB spectatorGui;
   private final lb overlayPlayerList;
   private final jJ overlayBoss;
   private int titlesTimer;
   private String displayedTitle;
   private String displayedSubTitle;
   private int titleFadeIn;
   private int titleDisplayTime;
   private int titleFadeOut;
   private int playerHealth;
   private int lastPlayerHealth;
   private long lastSystemTime;
   private long healthUpdateCounter;
   private final Map<ChatType, List<jB>> chatListeners;

   public kn(nC mcIn) {
      this.highlightingItemStack = Qy.EMPTY;
      this.displayedTitle = "";
      this.displayedSubTitle = "";
      this.chatListeners = Maps.newHashMap();
      this.mc = mcIn;
      this.itemRenderer = mcIn.getRenderItem();
      this.overlayDebug = new kQ(mcIn);
      this.spectatorGui = new lB(mcIn);
      this.persistantChatGUI = new kJ(mcIn);
      this.overlayPlayerList = new lb(mcIn, this);
      this.overlayBoss = new jJ(mcIn);
      this.overlaySubtitle = new lD(mcIn);
      ChatType[] var2 = ChatType.values();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         ChatType chattype = var2[var4];
         this.chatListeners.put(chattype, Lists.newArrayList());
      }

      jB ichatlistener = jC.INSTANCE;
      ((List)this.chatListeners.get(ChatType.CHAT)).add(new jD(mcIn));
      ((List)this.chatListeners.get(ChatType.CHAT)).add(ichatlistener);
      ((List)this.chatListeners.get(ChatType.SYSTEM)).add(new jD(mcIn));
      ((List)this.chatListeners.get(ChatType.SYSTEM)).add(ichatlistener);
      ((List)this.chatListeners.get(ChatType.GAME_INFO)).add(new jE(mcIn));
      this.setDefaultTitlesTimes();
   }

   public void setDefaultTitlesTimes() {
      this.titleFadeIn = 10;
      this.titleDisplayTime = 70;
      this.titleFadeOut = 20;
   }

   public void renderGameOverlay(float partialTicks) {
      mC scaledresolution = new mC(this.mc);
      int i = scaledresolution.getScaledWidth();
      int j = scaledresolution.getScaledHeight();
      jH fontrenderer = this.getFontRenderer();
      yh.enableBlend();
      nC var10001;
      if (XH.isVignetteEnabled()) {
         var10001 = this.mc;
         this.renderVignette(nC.player.getBrightness(), scaledresolution);
      } else {
         yh.enableDepth();
         yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA, ya.ONE, xR.ZERO);
      }

      nC var10000 = this.mc;
      Qy itemstack = nC.player.inventory.armorItemInSlot(3);
      var10000 = this.mc;
      if (nC.gameSettings.thirdPersonView == 0 && itemstack.getItem() == OL.getItemFromBlock(Nk.PUMPKIN)) {
         this.renderPumpkinOverlay(scaledresolution);
      }

      var10000 = this.mc;
      if (!nC.player.isPotionActive(NL.NAUSEA)) {
         var10000 = this.mc;
         var10001 = this.mc;
         nC var10002 = this.mc;
         float f = nC.player.prevTimeInPortal + (nC.player.timeInPortal - nC.player.prevTimeInPortal) * partialTicks;
         if (f > 0.0F) {
            this.renderPortal(f, scaledresolution);
         }
      }

      if (this.mc.playerController.isSpectator()) {
         this.spectatorGui.renderTooltip(scaledresolution, partialTicks);
      } else {
         this.renderHotbar(scaledresolution, partialTicks);
      }

      0bz.method_Qm().method_Qn().post(new 0dn(scaledresolution));
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(ICONS);
      yh.enableBlend();
      this.renderAttackIndicator(partialTicks, scaledresolution);
      yh.enableAlpha();
      yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA, ya.ONE, xR.ZERO);
      this.mc.profiler.startSection("bossHealth");
      this.overlayBoss.renderBossHealth();
      this.mc.profiler.endSection();
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(ICONS);
      if (this.mc.playerController.shouldDrawHUD()) {
         this.renderPlayerStats(scaledresolution);
      }

      this.renderMountHealth(scaledresolution);
      yh.disableBlend();
      var10000 = this.mc;
      float f3;
      int i2;
      int k1;
      if (nC.player.getSleepTimer() > 0) {
         this.mc.profiler.startSection("sleep");
         yh.disableDepth();
         yh.disableAlpha();
         var10000 = this.mc;
         k1 = nC.player.getSleepTimer();
         f3 = (float)k1 / 100.0F;
         if (f3 > 1.0F) {
            f3 = 1.0F - (float)(k1 - 100) / 10.0F;
         }

         i2 = (int)(220.0F * f3) << 24 | 1052704;
         drawRect(0, 0, i, j, i2);
         yh.enableAlpha();
         yh.enableDepth();
         this.mc.profiler.endSection();
      }

      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      k1 = i / 2 - 91;
      var10000 = this.mc;
      if (nC.player.isRidingHorse()) {
         this.renderHorseJumpBar(scaledresolution, k1);
      } else if (this.mc.playerController.gameIsSurvivalOrAdventure()) {
         this.renderExpBar(scaledresolution, k1);
      }

      var10000 = this.mc;
      if (nC.gameSettings.heldItemTooltips && !this.mc.playerController.isSpectator()) {
         this.renderSelectedItem(scaledresolution);
      } else {
         var10000 = this.mc;
         if (nC.player.isSpectator()) {
            this.spectatorGui.renderSelectedItem(scaledresolution);
         }
      }

      if (this.mc.isDemo()) {
         this.renderDemo(scaledresolution);
      }

      this.renderPotionEffects(scaledresolution);
      var10000 = this.mc;
      if (nC.gameSettings.showDebugInfo) {
         this.overlayDebug.renderDebugInfo(scaledresolution);
      }

      int j2;
      if (this.overlayMessageTime > 0) {
         this.mc.profiler.startSection("overlayMessage");
         f3 = (float)this.overlayMessageTime - partialTicks;
         i2 = (int)(f3 * 255.0F / 20.0F);
         if (i2 > 255) {
            i2 = 255;
         }

         if (i2 > 8) {
            yh.pushMatrix();
            yh.translate((float)(i / 2), (float)(j - 68), 0.0F);
            yh.enableBlend();
            yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA, ya.ONE, xR.ZERO);
            j2 = 16777215;
            if (this.animateOverlayMessageColor) {
               j2 = MathHelper.hsvToRGB(f3 / 50.0F, 0.7F, 0.6F) & 16777215;
            }

            fontrenderer.drawString(this.overlayMessage, -fontrenderer.getStringWidth(this.overlayMessage) / 2, -4, j2 + (i2 << 24 & -16777216));
            yh.disableBlend();
            yh.popMatrix();
         }

         this.mc.profiler.endSection();
      }

      this.overlaySubtitle.renderSubtitles(scaledresolution);
      if (this.titlesTimer > 0) {
         this.mc.profiler.startSection("titleAndSubtitle");
         f3 = (float)this.titlesTimer - partialTicks;
         i2 = 255;
         if (this.titlesTimer > this.titleFadeOut + this.titleDisplayTime) {
            float f4 = (float)(this.titleFadeIn + this.titleDisplayTime + this.titleFadeOut) - f3;
            i2 = (int)(f4 * 255.0F / (float)this.titleFadeIn);
         }

         if (this.titlesTimer <= this.titleFadeOut) {
            i2 = (int)(f3 * 255.0F / (float)this.titleFadeOut);
         }

         i2 = MathHelper.clamp(i2, 0, 255);
         if (i2 > 8) {
            yh.pushMatrix();
            yh.translate((float)(i / 2), (float)(j / 2), 0.0F);
            yh.enableBlend();
            yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA, ya.ONE, xR.ZERO);
            yh.pushMatrix();
            yh.scale(4.0F, 4.0F, 4.0F);
            j2 = i2 << 24 & -16777216;
            fontrenderer.drawString(this.displayedTitle, (float)(-fontrenderer.getStringWidth(this.displayedTitle) / 2), -10.0F, 16777215 | j2, true);
            yh.popMatrix();
            yh.pushMatrix();
            yh.scale(2.0F, 2.0F, 2.0F);
            fontrenderer.drawString(this.displayedSubTitle, (float)(-fontrenderer.getStringWidth(this.displayedSubTitle) / 2), 5.0F, 16777215 | j2, true);
            yh.popMatrix();
            yh.disableBlend();
            yh.popMatrix();
         }

         this.mc.profiler.endSection();
      }

      Ws scoreboard = this.mc.world.getScoreboard();
      Wz scoreobjective = null;
      var10001 = this.mc;
      WA scoreplayerteam = scoreboard.getPlayersTeam(nC.player.getName());
      if (scoreplayerteam != null && scoreplayerteam.getColor() != null) {
         int i1 = scoreplayerteam.getColor().getColorIndex();
         if (i1 >= 0) {
            scoreobjective = scoreboard.getObjectiveInDisplaySlot(3 + i1);
         }
      }

      Wz scoreobjective1 = scoreobjective != null ? scoreobjective : scoreboard.getObjectiveInDisplaySlot(1);
      if (scoreobjective1 != null) {
         this.renderScoreboard(scoreobjective1, scaledresolution);
      }

      label108: {
         label107: {
            yh.enableBlend();
            yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA, ya.ONE, xR.ZERO);
            yh.disableAlpha();
            yh.pushMatrix();
            yh.translate(0.0F, (float)(j - 48), 0.0F);
            this.mc.profiler.startSection("chat");
            this.persistantChatGUI.drawChat(this.updateCounter);
            this.mc.profiler.endSection();
            yh.popMatrix();
            scoreobjective1 = scoreboard.getObjectiveInDisplaySlot(0);
            var10000 = this.mc;
            if (nC.gameSettings.keyBindPlayerList.isKeyDown()) {
               if (!this.mc.isIntegratedServerRunning()) {
                  break label107;
               }

               var10000 = this.mc;
               if (nC.player.connection.getPlayerInfoMap().size() > 1 || scoreobjective1 != null) {
                  break label107;
               }
            }

            this.overlayPlayerList.updatePlayerList(false);
            break label108;
         }

         this.overlayPlayerList.updatePlayerList(true);
         this.overlayPlayerList.renderPlayerlist(i, scoreboard, scoreobjective1);
      }

      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      yh.disableLighting();
      yh.enableAlpha();
   }

   private void renderAttackIndicator(float partialTicks, mC p_184045_2_) {
      nC var10000 = this.mc;
      Bj gamesettings = nC.gameSettings;
      if (gamesettings.thirdPersonView == 0) {
         if (this.mc.playerController.isSpectator() && this.mc.pointedEntity == null) {
            RayTraceResult raytraceresult = this.mc.objectMouseOver;
            if (raytraceresult == null || raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK) {
               return;
            }

            BlockPos blockpos = raytraceresult.getBlockPos();
            in iblockstate = this.mc.world.getBlockState(blockpos);
            if (!bnQ.blockHasTileEntity(iblockstate) || !(this.mc.world.getTileEntity(blockpos) instanceof IInventory)) {
               return;
            }
         }

         int l = p_184045_2_.getScaledWidth();
         int i1 = p_184045_2_.getScaledHeight();
         if (gamesettings.showDebugInfo && !gamesettings.hideGUI) {
            var10000 = this.mc;
            if (!nC.player.hasReducedDebug() && !gamesettings.reducedDebugInfo) {
               yh.pushMatrix();
               yh.translate((float)(l / 2), (float)(i1 / 2), this.zLevel);
               Ig entity = this.mc.getRenderViewEntity();
               yh.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, -1.0F, 0.0F, 0.0F);
               yh.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks, 0.0F, 1.0F, 0.0F);
               yh.scale(-1.0F, -1.0F, -1.0F);
               ys.renderDirections(10);
               yh.popMatrix();
               return;
            }
         }

         yh.tryBlendFuncSeparate(ya.ONE_MINUS_DST_COLOR, xR.ONE_MINUS_SRC_COLOR, ya.ONE, xR.ZERO);
         yh.enableAlpha();
         this.drawTexturedModalRect(l / 2 - 7, i1 / 2 - 7, 0, 0, 16, 16);
         var10000 = this.mc;
         if (nC.gameSettings.attackIndicator == 1) {
            var10000 = this.mc;
            float f = nC.player.getCooledAttackStrength(0.0F);
            boolean flag = false;
            if (this.mc.pointedEntity != null && this.mc.pointedEntity instanceof Iw && f >= 1.0F) {
               var10000 = this.mc;
               flag = nC.player.getCooldownPeriod() > 5.0F;
               flag &= ((Iw)this.mc.pointedEntity).isEntityAlive();
            }

            int i = i1 / 2 - 7 + 16;
            int j = l / 2 - 8;
            if (flag) {
               this.drawTexturedModalRect(j, i, 68, 94, 16, 16);
            } else if (f < 1.0F) {
               int k = (int)(f * 17.0F);
               this.drawTexturedModalRect(j, i, 36, 94, 16, 4);
               this.drawTexturedModalRect(j, i, 52, 94, k, 4);
            }
         }
      }

   }

   protected void renderPotionEffects(mC resolution) {
      nC var10000 = this.mc;
      Collection<VZ> collection = nC.player.getActivePotionEffects();
      if (!collection.isEmpty()) {
         this.mc.getTextureManager().bindTexture(lU.INVENTORY_BACKGROUND);
         yh.enableBlend();
         int i = 0;
         int j = 0;
         Iterator iterator = Ordering.natural().reverse().sortedCopy(collection).iterator();

         while(true) {
            VZ potioneffect;
            VW potion;
            boolean flag;
            while(true) {
               if (!iterator.hasNext()) {
                  return;
               }

               potioneffect = (VZ)iterator.next();
               potion = potioneffect.getPotion();
               flag = potion.hasStatusIcon();
               if (!bnK.ForgePotion_shouldRenderHUD.exists()) {
                  break;
               }

               if (bnK.callBoolean(potion, bnK.ForgePotion_shouldRenderHUD, potioneffect)) {
                  this.mc.getTextureManager().bindTexture(lU.INVENTORY_BACKGROUND);
                  flag = true;
                  break;
               }
            }

            if (flag && potioneffect.doesShowParticles()) {
               int k = resolution.getScaledWidth();
               int l = 1;
               if (this.mc.isDemo()) {
                  l += 15;
               }

               int i1 = potion.getStatusIconIndex();
               if (potion.isBeneficial()) {
                  ++i;
                  k -= 25 * i;
               } else {
                  ++j;
                  k -= 25 * j;
                  l += 26;
               }

               yh.color(1.0F, 1.0F, 1.0F, 1.0F);
               float f = 1.0F;
               if (potioneffect.getIsAmbient()) {
                  this.drawTexturedModalRect(k, l, 165, 166, 24, 24);
               } else {
                  this.drawTexturedModalRect(k, l, 141, 166, 24, 24);
                  if (potioneffect.getDuration() <= 200) {
                     int j1 = 10 - potioneffect.getDuration() / 20;
                     f = MathHelper.clamp((float)potioneffect.getDuration() / 10.0F / 5.0F * 0.5F, 0.0F, 0.5F) + MathHelper.cos((float)potioneffect.getDuration() * 3.1415927F / 5.0F) * MathHelper.clamp((float)j1 / 10.0F * 0.25F, 0.0F, 0.25F);
                  }
               }

               yh.color(1.0F, 1.0F, 1.0F, f);
               if (bnK.ForgePotion_renderHUDEffect.exists()) {
                  if (potion.hasStatusIcon()) {
                     this.drawTexturedModalRect(k + 3, l + 3, i1 % 8 * 18, 198 + i1 / 8 * 18, 18, 18);
                  }

                  bnK.call(potion, bnK.ForgePotion_renderHUDEffect, potioneffect, this, k, l, this.zLevel, f);
               } else {
                  this.drawTexturedModalRect(k + 3, l + 3, i1 % 8 * 18, 198 + i1 / 8 * 18, 18, 18);
               }
            }
         }
      }
   }

   protected void renderHotbar(mC sr, float partialTicks) {
      if (this.mc.getRenderViewEntity() instanceof ME) {
         yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.mc.getTextureManager().bindTexture(WIDGETS_TEX_PATH);
         ME entityplayer = (ME)this.mc.getRenderViewEntity();
         Qy itemstack = entityplayer.getHeldItemOffhand();
         EnumHandSide enumhandside = entityplayer.getPrimaryHand().opposite();
         int i = sr.getScaledWidth() / 2;
         float f = this.zLevel;
         int j = true;
         int k = true;
         this.zLevel = -90.0F;
         this.drawTexturedModalRect(i - 91, sr.getScaledHeight() - 22, 0, 0, 182, 22);
         this.drawTexturedModalRect(i - 91 - 1 + entityplayer.inventory.currentItem * 20, sr.getScaledHeight() - 22 - 1, 0, 22, 24, 22);
         if (!itemstack.isEmpty()) {
            if (enumhandside == EnumHandSide.LEFT) {
               this.drawTexturedModalRect(i - 91 - 29, sr.getScaledHeight() - 23, 24, 22, 29, 24);
            } else {
               this.drawTexturedModalRect(i + 91, sr.getScaledHeight() - 23, 53, 22, 29, 24);
            }
         }

         this.zLevel = f;
         yh.enableRescaleNormal();
         yh.enableBlend();
         yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA, ya.ONE, xR.ZERO);
         yz.enableGUIStandardItemLighting();
         bjG.setRenderOffHand(false);

         int l1;
         int i2;
         int j2;
         for(l1 = 0; l1 < 9; ++l1) {
            i2 = i - 90 + l1 * 20 + 2;
            j2 = sr.getScaledHeight() - 16 - 3;
            this.renderHotbarItem(i2, j2, partialTicks, entityplayer, (Qy)entityplayer.inventory.mainInventory.get(l1));
         }

         if (!itemstack.isEmpty()) {
            bjG.setRenderOffHand(true);
            l1 = sr.getScaledHeight() - 16 - 3;
            if (enumhandside == EnumHandSide.LEFT) {
               this.renderHotbarItem(i - 91 - 26, l1, partialTicks, entityplayer, itemstack);
            } else {
               this.renderHotbarItem(i + 91 + 10, l1, partialTicks, entityplayer, itemstack);
            }

            bjG.setRenderOffHand(false);
         }

         nC var10000 = this.mc;
         if (nC.gameSettings.attackIndicator == 2) {
            var10000 = this.mc;
            float f1 = nC.player.getCooledAttackStrength(0.0F);
            if (f1 < 1.0F) {
               i2 = sr.getScaledHeight() - 20;
               j2 = i + 91 + 6;
               if (enumhandside == EnumHandSide.RIGHT) {
                  j2 = i - 91 - 22;
               }

               this.mc.getTextureManager().bindTexture(jI.ICONS);
               int k1 = (int)(f1 * 19.0F);
               yh.color(1.0F, 1.0F, 1.0F, 1.0F);
               this.drawTexturedModalRect(j2, i2, 0, 94, 18, 18);
               this.drawTexturedModalRect(j2, i2 + 18 - k1, 18, 112 - k1, 18, k1);
            }
         }

         yz.disableStandardItemLighting();
         yh.disableRescaleNormal();
         yh.disableBlend();
      }

   }

   public void renderHorseJumpBar(mC scaledRes, int x) {
      this.mc.profiler.startSection("jumpBar");
      this.mc.getTextureManager().bindTexture(jI.ICONS);
      nC var10000 = this.mc;
      float f = nC.player.getHorseJumpPower();
      int i = true;
      int j = (int)(f * 183.0F);
      int k = scaledRes.getScaledHeight() - 32 + 3;
      this.drawTexturedModalRect(x, k, 0, 84, 182, 5);
      if (j > 0) {
         this.drawTexturedModalRect(x, k, 0, 89, j, 5);
      }

      this.mc.profiler.endSection();
   }

   public void renderExpBar(mC scaledRes, int x) {
      this.mc.profiler.startSection("expBar");
      this.mc.getTextureManager().bindTexture(jI.ICONS);
      nC var10000 = this.mc;
      int i = nC.player.xpBarCap();
      int k1;
      if (i > 0) {
         int j = true;
         var10000 = this.mc;
         int k = (int)(nC.player.experience * 183.0F);
         k1 = scaledRes.getScaledHeight() - 32 + 3;
         this.drawTexturedModalRect(x, k1, 0, 64, 182, 5);
         if (k > 0) {
            this.drawTexturedModalRect(x, k1, 0, 69, k, 5);
         }
      }

      this.mc.profiler.endSection();
      var10000 = this.mc;
      if (nC.player.experienceLevel > 0) {
         this.mc.profiler.startSection("expLevel");
         int j1 = 8453920;
         if (XH.isCustomColors()) {
            j1 = bjy.getExpBarTextColor(j1);
         }

         StringBuilder var10 = (new StringBuilder()).append("");
         nC var10001 = this.mc;
         String s = var10.append(nC.player.experienceLevel).toString();
         k1 = (scaledRes.getScaledWidth() - this.getFontRenderer().getStringWidth(s)) / 2;
         int i1 = scaledRes.getScaledHeight() - 31 - 4;
         this.getFontRenderer().drawString(s, k1 + 1, i1, 0);
         this.getFontRenderer().drawString(s, k1 - 1, i1, 0);
         this.getFontRenderer().drawString(s, k1, i1 + 1, 0);
         this.getFontRenderer().drawString(s, k1, i1 - 1, 0);
         this.getFontRenderer().drawString(s, k1, i1, j1);
         this.mc.profiler.endSection();
      }

   }

   public void renderSelectedItem(mC scaledRes) {
      this.mc.profiler.startSection("selectedItemName");
      if (this.remainingHighlightTicks > 0 && !this.highlightingItemStack.isEmpty()) {
         String s = this.highlightingItemStack.getDisplayName();
         if (this.highlightingItemStack.hasDisplayName()) {
            s = TextFormatting.ITALIC + s;
         }

         int i = (scaledRes.getScaledWidth() - this.getFontRenderer().getStringWidth(s)) / 2;
         int j = scaledRes.getScaledHeight() - 59;
         if (!this.mc.playerController.shouldDrawHUD()) {
            j += 14;
         }

         int k = (int)((float)this.remainingHighlightTicks * 256.0F / 10.0F);
         if (k > 255) {
            k = 255;
         }

         if (k > 0) {
            yh.pushMatrix();
            yh.enableBlend();
            yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA, ya.ONE, xR.ZERO);
            this.getFontRenderer().drawStringWithShadow(s, (float)i, (float)j, 16777215 + (k << 24));
            yh.disableBlend();
            yh.popMatrix();
         }
      }

      this.mc.profiler.endSection();
   }

   public void renderDemo(mC scaledRes) {
      this.mc.profiler.startSection("demo");
      String s;
      if (this.mc.world.getTotalWorldTime() >= 120500L) {
         s = Ax.format("demo.demoExpired");
      } else {
         s = Ax.format("demo.remainingTime", StringUtils.ticksToElapsedTime((int)(120500L - this.mc.world.getTotalWorldTime())));
      }

      int i = this.getFontRenderer().getStringWidth(s);
      this.getFontRenderer().drawStringWithShadow(s, (float)(scaledRes.getScaledWidth() - i - 10), 5.0F, 16777215);
      this.mc.profiler.endSection();
   }

   private void renderScoreboard(Wz objective, mC scaledRes) {
      Ws scoreboard = objective.getScoreboard();
      Collection<Wr> collection = scoreboard.getSortedScores(objective);
      List<Wr> list = Lists.newArrayList(Iterables.filter(collection, new Predicate<Wr>() {
         public boolean apply(@Nullable Wr p_apply_1_) {
            return p_apply_1_.getPlayerName() != null && !p_apply_1_.getPlayerName().startsWith("#");
         }

         // $FF: synthetic method
         // $FF: bridge method
         public boolean apply(@Nullable Object var1) {
            return this.apply((Wr)var1);
         }
      }));
      ArrayList collection;
      if (list.size() > 15) {
         collection = Lists.newArrayList(Iterables.skip(list, collection.size() - 15));
      } else {
         collection = list;
      }

      int i = this.getFontRenderer().getStringWidth(objective.getDisplayName());

      String s;
      for(Iterator var7 = collection.iterator(); var7.hasNext(); i = Math.max(i, this.getFontRenderer().getStringWidth(s))) {
         Wr score = (Wr)var7.next();
         WA scoreplayerteam = scoreboard.getPlayersTeam(score.getPlayerName());
         s = WA.formatPlayerName(scoreplayerteam, score.getPlayerName()) + ": " + TextFormatting.RED + score.getScorePoints();
      }

      int i1 = collection.size() * this.getFontRenderer().FONT_HEIGHT;
      int j1 = scaledRes.getScaledHeight() / 2 + i1 / 3;
      int k1 = true;
      int l1 = scaledRes.getScaledWidth() - i - 3;
      int j = 0;
      boolean nameprotect = 0bz.method_Qm().method_Qs().method_bxY(0bS.class).method_bBh();
      Iterator var13 = collection.iterator();

      while(var13.hasNext()) {
         Wr score1 = (Wr)var13.next();
         ++j;
         WA scoreplayerteam1 = scoreboard.getPlayersTeam(score1.getPlayerName());
         String s1 = WA.formatPlayerName(scoreplayerteam1, score1.getPlayerName());
         if (nameprotect) {
            s1 = s1.replace(nC.player.getName(), "NeoWare");
         }

         String s2 = TextFormatting.RED + "" + score1.getScorePoints();
         int k = j1 - j * this.getFontRenderer().FONT_HEIGHT;
         int l = scaledRes.getScaledWidth() - 3 + 2;
         drawRect(l1 - 2, k, l, k + this.getFontRenderer().FONT_HEIGHT, 1342177280);
         this.getFontRenderer().drawString(s1, l1, k, 553648127);
         this.getFontRenderer().drawString(s2, l - this.getFontRenderer().getStringWidth(s2), k, 553648127);
         if (j == collection.size()) {
            String s3 = objective.getDisplayName();
            drawRect(l1 - 2, k - this.getFontRenderer().FONT_HEIGHT - 1, l, k - 1, 1610612736);
            drawRect(l1 - 2, k - 1, l, k, 1342177280);
            this.getFontRenderer().drawString(s3, l1 + i / 2 - this.getFontRenderer().getStringWidth(s3) / 2, k - this.getFontRenderer().FONT_HEIGHT, 553648127);
         }
      }

   }

   private void renderPlayerStats(mC scaledRes) {
      if (this.mc.getRenderViewEntity() instanceof ME) {
         ME entityplayer = (ME)this.mc.getRenderViewEntity();
         int i = MathHelper.ceil(entityplayer.getHealth());
         boolean flag = this.healthUpdateCounter > (long)this.updateCounter && (this.healthUpdateCounter - (long)this.updateCounter) / 3L % 2L == 1L;
         if (i < this.playerHealth && entityplayer.hurtResistantTime > 0) {
            this.lastSystemTime = nC.getSystemTime();
            this.healthUpdateCounter = (long)(this.updateCounter + 20);
         } else if (i > this.playerHealth && entityplayer.hurtResistantTime > 0) {
            this.lastSystemTime = nC.getSystemTime();
            this.healthUpdateCounter = (long)(this.updateCounter + 10);
         }

         if (nC.getSystemTime() - this.lastSystemTime > 1000L) {
            this.playerHealth = i;
            this.lastPlayerHealth = i;
            this.lastSystemTime = nC.getSystemTime();
         }

         this.playerHealth = i;
         int j = this.lastPlayerHealth;
         this.rand.setSeed((long)(this.updateCounter * 312871));
         FoodStats foodstats = entityplayer.getFoodStats();
         int k = foodstats.getFoodLevel();
         FZ iattributeinstance = entityplayer.getEntityAttribute(Ni.MAX_HEALTH);
         int l = scaledRes.getScaledWidth() / 2 - 91;
         int i1 = scaledRes.getScaledWidth() / 2 + 91;
         int j1 = scaledRes.getScaledHeight() - 39;
         float f = (float)iattributeinstance.getAttributeValue();
         int k1 = MathHelper.ceil(entityplayer.getAbsorptionAmount());
         int l1 = MathHelper.ceil((f + (float)k1) / 2.0F / 10.0F);
         int i2 = Math.max(10 - (l1 - 2), 3);
         int j2 = j1 - (l1 - 1) * i2 - 10;
         int k2 = j1 - 10;
         int l2 = k1;
         int i3 = entityplayer.getTotalArmorValue();
         int j3 = -1;
         if (entityplayer.isPotionActive(NL.REGENERATION)) {
            j3 = this.updateCounter % MathHelper.ceil(f + 5.0F);
         }

         this.mc.profiler.startSection("armor");

         int j5;
         int k5;
         for(j5 = 0; j5 < 10; ++j5) {
            if (i3 > 0) {
               k5 = l + j5 * 8;
               if (j5 * 2 + 1 < i3) {
                  this.drawTexturedModalRect(k5, j2, 34, 9, 9, 9);
               }

               if (j5 * 2 + 1 == i3) {
                  this.drawTexturedModalRect(k5, j2, 25, 9, 9, 9);
               }

               if (j5 * 2 + 1 > i3) {
                  this.drawTexturedModalRect(k5, j2, 16, 9, 9, 9);
               }
            }
         }

         this.mc.profiler.endStartSection("health");

         int j6;
         int l6;
         int k4;
         int l4;
         for(j5 = MathHelper.ceil((f + (float)k1) / 2.0F) - 1; j5 >= 0; --j5) {
            k5 = 16;
            if (entityplayer.isPotionActive(NL.POISON)) {
               k5 += 36;
            } else if (entityplayer.isPotionActive(NL.WITHER)) {
               k5 += 72;
            }

            j6 = 0;
            if (flag) {
               j6 = 1;
            }

            l6 = MathHelper.ceil((float)(j5 + 1) / 10.0F) - 1;
            k4 = l + j5 % 10 * 8;
            l4 = j1 - l6 * i2;
            if (i <= 4) {
               l4 += this.rand.nextInt(2);
            }

            if (l2 <= 0 && j5 == j3) {
               l4 -= 2;
            }

            int i5 = 0;
            if (entityplayer.world.getWorldInfo().isHardcoreModeEnabled()) {
               i5 = 5;
            }

            this.drawTexturedModalRect(k4, l4, 16 + j6 * 9, 9 * i5, 9, 9);
            if (flag) {
               if (j5 * 2 + 1 < j) {
                  this.drawTexturedModalRect(k4, l4, k5 + 54, 9 * i5, 9, 9);
               }

               if (j5 * 2 + 1 == j) {
                  this.drawTexturedModalRect(k4, l4, k5 + 63, 9 * i5, 9, 9);
               }
            }

            if (l2 > 0) {
               if (l2 == k1 && k1 % 2 == 1) {
                  this.drawTexturedModalRect(k4, l4, k5 + 153, 9 * i5, 9, 9);
                  --l2;
               } else {
                  this.drawTexturedModalRect(k4, l4, k5 + 144, 9 * i5, 9, 9);
                  l2 -= 2;
               }
            } else {
               if (j5 * 2 + 1 < i) {
                  this.drawTexturedModalRect(k4, l4, k5 + 36, 9 * i5, 9, 9);
               }

               if (j5 * 2 + 1 == i) {
                  this.drawTexturedModalRect(k4, l4, k5 + 45, 9 * i5, 9, 9);
               }
            }
         }

         Ig entity = entityplayer.getRidingEntity();
         if (entity == null || !(entity instanceof Iw)) {
            this.mc.profiler.endStartSection("food");

            for(k5 = 0; k5 < 10; ++k5) {
               j6 = j1;
               l6 = 16;
               int j7 = 0;
               if (entityplayer.isPotionActive(NL.HUNGER)) {
                  l6 += 36;
                  j7 = 13;
               }

               if (entityplayer.getFoodStats().getSaturationLevel() <= 0.0F && this.updateCounter % (k * 3 + 1) == 0) {
                  j6 = j1 + (this.rand.nextInt(3) - 1);
               }

               l4 = i1 - k5 * 8 - 9;
               this.drawTexturedModalRect(l4, j6, 16 + j7 * 9, 27, 9, 9);
               if (k5 * 2 + 1 < k) {
                  this.drawTexturedModalRect(l4, j6, l6 + 36, 27, 9, 9);
               }

               if (k5 * 2 + 1 == k) {
                  this.drawTexturedModalRect(l4, j6, l6 + 45, 27, 9, 9);
               }
            }
         }

         this.mc.profiler.endStartSection("air");
         if (entityplayer.isInsideOfMaterial(hM.WATER)) {
            nC var10000 = this.mc;
            k5 = nC.player.getAir();
            j6 = MathHelper.ceil((double)(k5 - 2) * 10.0 / 300.0);
            l6 = MathHelper.ceil((double)k5 * 10.0 / 300.0) - j6;

            for(k4 = 0; k4 < j6 + l6; ++k4) {
               if (k4 < j6) {
                  this.drawTexturedModalRect(i1 - k4 * 8 - 9, k2, 16, 18, 9, 9);
               } else {
                  this.drawTexturedModalRect(i1 - k4 * 8 - 9, k2, 25, 18, 9, 9);
               }
            }
         }

         this.mc.profiler.endSection();
      }

   }

   private void renderMountHealth(mC p_184047_1_) {
      if (this.mc.getRenderViewEntity() instanceof ME) {
         ME entityplayer = (ME)this.mc.getRenderViewEntity();
         Ig entity = entityplayer.getRidingEntity();
         if (entity instanceof Iw) {
            this.mc.profiler.endStartSection("mountHealth");
            Iw entitylivingbase = (Iw)entity;
            int i = (int)Math.ceil((double)entitylivingbase.getHealth());
            float f = entitylivingbase.getMaxHealth();
            int j = (int)(f + 0.5F) / 2;
            if (j > 30) {
               j = 30;
            }

            int k = p_184047_1_.getScaledHeight() - 39;
            int l = p_184047_1_.getScaledWidth() / 2 + 91;
            int i1 = k;
            int j1 = 0;

            for(boolean flag = false; j > 0; j1 += 20) {
               int k1 = Math.min(j, 10);
               j -= k1;

               for(int l1 = 0; l1 < k1; ++l1) {
                  int i2 = true;
                  int j2 = 0;
                  int k2 = l - l1 * 8 - 9;
                  this.drawTexturedModalRect(k2, i1, 52 + j2 * 9, 9, 9, 9);
                  if (l1 * 2 + 1 + j1 < i) {
                     this.drawTexturedModalRect(k2, i1, 88, 9, 9, 9);
                  }

                  if (l1 * 2 + 1 + j1 == i) {
                     this.drawTexturedModalRect(k2, i1, 97, 9, 9, 9);
                  }
               }

               i1 -= 10;
            }
         }
      }

   }

   private void renderPumpkinOverlay(mC scaledRes) {
      yh.disableDepth();
      yh.depthMask(false);
      yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA, ya.ONE, xR.ZERO);
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      yh.disableAlpha();
      this.mc.getTextureManager().bindTexture(PUMPKIN_BLUR_TEX_PATH);
      yN tessellator = yN.getInstance();
      tN bufferbuilder = tessellator.getBuffer();
      bufferbuilder.begin(7, zK.POSITION_TEX);
      bufferbuilder.pos(0.0, (double)scaledRes.getScaledHeight(), -90.0).tex(0.0, 1.0).endVertex();
      bufferbuilder.pos((double)scaledRes.getScaledWidth(), (double)scaledRes.getScaledHeight(), -90.0).tex(1.0, 1.0).endVertex();
      bufferbuilder.pos((double)scaledRes.getScaledWidth(), 0.0, -90.0).tex(1.0, 0.0).endVertex();
      bufferbuilder.pos(0.0, 0.0, -90.0).tex(0.0, 0.0).endVertex();
      tessellator.draw();
      yh.depthMask(true);
      yh.enableDepth();
      yh.enableAlpha();
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
   }

   private void renderVignette(float lightLevel, mC scaledRes) {
      if (!XH.isVignetteEnabled()) {
         yh.enableDepth();
         yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA, ya.ONE, xR.ZERO);
      } else {
         lightLevel = 1.0F - lightLevel;
         lightLevel = MathHelper.clamp(lightLevel, 0.0F, 1.0F);
         bab worldborder = this.mc.world.getWorldBorder();
         nC var10001 = this.mc;
         float f = (float)worldborder.getClosestDistance(nC.player);
         double d0 = Math.min(worldborder.getResizeSpeed() * (double)worldborder.getWarningTime() * 1000.0, Math.abs(worldborder.getTargetSize() - worldborder.getDiameter()));
         double d1 = Math.max((double)worldborder.getWarningDistance(), d0);
         if ((double)f < d1) {
            f = 1.0F - (float)((double)f / d1);
         } else {
            f = 0.0F;
         }

         this.prevVignetteBrightness = (float)((double)this.prevVignetteBrightness + (double)(lightLevel - this.prevVignetteBrightness) * 0.01);
         yh.disableDepth();
         yh.depthMask(false);
         yh.tryBlendFuncSeparate(ya.ZERO, xR.ONE_MINUS_SRC_COLOR, ya.ONE, xR.ZERO);
         if (f > 0.0F) {
            yh.color(0.0F, f, f, 1.0F);
         } else {
            yh.color(this.prevVignetteBrightness, this.prevVignetteBrightness, this.prevVignetteBrightness, 1.0F);
         }

         this.mc.getTextureManager().bindTexture(VIGNETTE_TEX_PATH);
         yN tessellator = yN.getInstance();
         tN bufferbuilder = tessellator.getBuffer();
         bufferbuilder.begin(7, zK.POSITION_TEX);
         bufferbuilder.pos(0.0, (double)scaledRes.getScaledHeight(), -90.0).tex(0.0, 1.0).endVertex();
         bufferbuilder.pos((double)scaledRes.getScaledWidth(), (double)scaledRes.getScaledHeight(), -90.0).tex(1.0, 1.0).endVertex();
         bufferbuilder.pos((double)scaledRes.getScaledWidth(), 0.0, -90.0).tex(1.0, 0.0).endVertex();
         bufferbuilder.pos(0.0, 0.0, -90.0).tex(0.0, 0.0).endVertex();
         tessellator.draw();
         yh.depthMask(true);
         yh.enableDepth();
         yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA, ya.ONE, xR.ZERO);
      }

   }

   private void renderPortal(float timeInPortal, mC scaledRes) {
      if (timeInPortal < 1.0F) {
         timeInPortal *= timeInPortal;
         timeInPortal *= timeInPortal;
         timeInPortal = timeInPortal * 0.8F + 0.2F;
      }

      yh.disableAlpha();
      yh.disableDepth();
      yh.depthMask(false);
      yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA, ya.ONE, xR.ZERO);
      yh.color(1.0F, 1.0F, 1.0F, timeInPortal);
      this.mc.getTextureManager().bindTexture(zj.LOCATION_BLOCKS_TEXTURE);
      zd textureatlassprite = this.mc.getBlockRendererDispatcher().getBlockModelShapes().getTexture(Nk.PORTAL.getDefaultState());
      float f = textureatlassprite.getMinU();
      float f1 = textureatlassprite.getMinV();
      float f2 = textureatlassprite.getMaxU();
      float f3 = textureatlassprite.getMaxV();
      yN tessellator = yN.getInstance();
      tN bufferbuilder = tessellator.getBuffer();
      bufferbuilder.begin(7, zK.POSITION_TEX);
      bufferbuilder.pos(0.0, (double)scaledRes.getScaledHeight(), -90.0).tex((double)f, (double)f3).endVertex();
      bufferbuilder.pos((double)scaledRes.getScaledWidth(), (double)scaledRes.getScaledHeight(), -90.0).tex((double)f2, (double)f3).endVertex();
      bufferbuilder.pos((double)scaledRes.getScaledWidth(), 0.0, -90.0).tex((double)f2, (double)f1).endVertex();
      bufferbuilder.pos(0.0, 0.0, -90.0).tex((double)f, (double)f1).endVertex();
      tessellator.draw();
      yh.depthMask(true);
      yh.enableDepth();
      yh.enableAlpha();
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
   }

   private void renderHotbarItem(int x, int y, float partialTicks, ME player, Qy stack) {
      if (!stack.isEmpty()) {
         float f = (float)stack.getAnimationsToGo() - partialTicks;
         if (f > 0.0F) {
            yh.pushMatrix();
            float f1 = 1.0F + f / 5.0F;
            yh.translate((float)(x + 8), (float)(y + 12), 0.0F);
            yh.scale(1.0F / f1, (f1 + 1.0F) / 2.0F, 1.0F);
            yh.translate((float)(-(x + 8)), (float)(-(y + 12)), 0.0F);
         }

         this.itemRenderer.renderItemAndEffectIntoGUI(player, stack, x, y);
         if (f > 0.0F) {
            yh.popMatrix();
         }

         this.itemRenderer.renderItemOverlays(this.mc.fontRenderer, stack, x, y);
      }

   }

   public void updateTick() {
      if (this.mc.world == null) {
         bqf.updateAnimations();
      }

      if (this.overlayMessageTime > 0) {
         --this.overlayMessageTime;
      }

      if (this.titlesTimer > 0) {
         --this.titlesTimer;
         if (this.titlesTimer <= 0) {
            this.displayedTitle = "";
            this.displayedSubTitle = "";
         }
      }

      ++this.updateCounter;
      nC var10000 = this.mc;
      if (nC.player != null) {
         var10000 = this.mc;
         Qy itemstack = nC.player.inventory.getCurrentItem();
         if (itemstack.isEmpty()) {
            this.remainingHighlightTicks = 0;
         } else if (!this.highlightingItemStack.isEmpty() && itemstack.getItem() == this.highlightingItemStack.getItem() && Qy.areItemStackTagsEqual(itemstack, this.highlightingItemStack) && (itemstack.isItemStackDamageable() || itemstack.getMetadata() == this.highlightingItemStack.getMetadata())) {
            if (this.remainingHighlightTicks > 0) {
               --this.remainingHighlightTicks;
            }
         } else {
            this.remainingHighlightTicks = 40;
         }

         this.highlightingItemStack = itemstack;
      }

   }

   public void setRecordPlayingMessage(String recordName) {
      this.setOverlayMessage(Ax.format("record.nowPlaying", recordName), true);
   }

   public void setOverlayMessage(String message, boolean animateColor) {
      this.overlayMessage = message;
      this.overlayMessageTime = 60;
      this.animateOverlayMessageColor = animateColor;
   }

   public void displayTitle(String title, String subTitle, int timeFadeIn, int displayTime, int timeFadeOut) {
      if (title == null && subTitle == null && timeFadeIn < 0 && displayTime < 0 && timeFadeOut < 0) {
         this.displayedTitle = "";
         this.displayedSubTitle = "";
         this.titlesTimer = 0;
      } else if (title != null) {
         this.displayedTitle = title;
         this.titlesTimer = this.titleFadeIn + this.titleDisplayTime + this.titleFadeOut;
      } else if (subTitle != null) {
         this.displayedSubTitle = subTitle;
      } else {
         if (timeFadeIn >= 0) {
            this.titleFadeIn = timeFadeIn;
         }

         if (displayTime >= 0) {
            this.titleDisplayTime = displayTime;
         }

         if (timeFadeOut >= 0) {
            this.titleFadeOut = timeFadeOut;
         }

         if (this.titlesTimer > 0) {
            this.titlesTimer = this.titleFadeIn + this.titleDisplayTime + this.titleFadeOut;
         }
      }

   }

   public void setOverlayMessage(ITextComponent component, boolean animateColor) {
      this.setOverlayMessage(component.getUnformattedText(), animateColor);
   }

   public void addChatMessage(ChatType chatTypeIn, ITextComponent message) {
      Iterator var3 = ((List)this.chatListeners.get(chatTypeIn)).iterator();

      while(var3.hasNext()) {
         jB ichatlistener = (jB)var3.next();
         ichatlistener.say(chatTypeIn, message);
      }

   }

   public kJ getChatGUI() {
      return this.persistantChatGUI;
   }

   public int getUpdateCounter() {
      return this.updateCounter;
   }

   public jH getFontRenderer() {
      return this.mc.fontRenderer;
   }

   public lB getSpectatorGui() {
      return this.spectatorGui;
   }

   public lb getTabList() {
      return this.overlayPlayerList;
   }

   public void resetPlayersOverlayFooterHeader() {
      this.overlayPlayerList.resetFooterHeader();
      this.overlayBoss.clearBossInfos();
      this.mc.getToastGui().clear();
   }

   public jJ getBossOverlay() {
      return this.overlayBoss;
   }
}
