package neo;

import com.google.common.collect.Ordering;
import com.mojang.authlib.GameProfile;
import java.awt.Color;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

public class lb extends jI {
   public static final Ordering<pB> ENTRY_ORDERING = Ordering.from(new la());
   private final nC mc;
   private final kn guiIngame;
   private ITextComponent footer;
   private ITextComponent header;
   private long lastTimeOpened;
   public static float animTicks;
   public static float addition;
   public static float lastTime;
   private boolean isBeingRendered;

   public lb(nC mcIn, kn guiIngameIn) {
      this.mc = mcIn;
      this.guiIngame = guiIngameIn;
   }

   public String getPlayerName(pB networkPlayerInfoIn) {
      String playerName = networkPlayerInfoIn.getDisplayName() != null ? networkPlayerInfoIn.getDisplayName().getFormattedText() : WA.formatPlayerName(networkPlayerInfoIn.getPlayerTeam(), networkPlayerInfoIn.getGameProfile().getName());
      if (networkPlayerInfoIn.isBot()) {
         playerName = "§7§l[§c§lBOT§7§l] §f" + playerName;
      }

      return playerName;
   }

   public void updatePlayerList(boolean willBeRendered) {
      if (willBeRendered && !this.isBeingRendered) {
         animTicks = 0.0F;
         addition = 0.0F;
         lastTime = 0.0F;
         this.lastTimeOpened = nC.getSystemTime();
      }

      this.isBeingRendered = willBeRendered;
   }

   public void renderPlayerlist(int width, Ws scoreboardIn, @Nullable Wz scoreObjectiveIn) {
      py nethandlerplayclient = nC.player.connection;
      List<pB> list = ENTRY_ORDERING.sortedCopy(nethandlerplayclient.getPlayerInfoMap());
      int i = 0;
      int j = 0;
      Iterator var8 = list.iterator();

      int j4;
      while(var8.hasNext()) {
         pB networkplayerinfo = (pB)var8.next();
         j4 = this.mc.fontRenderer.getStringWidth(this.getPlayerName(networkplayerinfo));
         i = Math.max(i, j4);
         if (scoreObjectiveIn != null && scoreObjectiveIn.getRenderType() != Wn.HEARTS) {
            j4 = this.mc.fontRenderer.getStringWidth(" " + scoreboardIn.getOrCreateScore(networkplayerinfo.getGameProfile().getName(), scoreObjectiveIn).getScorePoints());
            j = Math.max(j, j4);
         }
      }

      list = list.subList(0, Math.min(list.size(), 0bz.method_Qm().method_Qs().method_bxY(0bO.class).method_bBh() ? Integer.MAX_VALUE : 80));
      int l3 = list.size();
      int i4 = l3;

      for(j4 = 1; i4 > 30; i4 = (l3 + j4 - 1) / j4) {
         ++j4;
      }

      boolean flag = this.mc.isIntegratedServerRunning() || this.mc.getConnection().getNetworkManager().isEncrypted();
      int l;
      if (scoreObjectiveIn != null) {
         if (scoreObjectiveIn.getRenderType() == Wn.HEARTS) {
            l = 90;
         } else {
            l = j;
         }
      } else {
         l = 0;
      }

      int i1 = Math.min(j4 * ((flag ? 9 : 0) + i + l + 13), width - 50) / j4;
      int j1 = width / 2 - (i1 * j4 + (j4 - 1) * 5) / 2;
      int k1 = 10;
      int l1 = i1 * j4 + (j4 - 1) * 5;
      List<String> list1 = null;
      if (this.header != null) {
         list1 = this.mc.fontRenderer.listFormattedStringToWidth(this.header.getFormattedText(), width - 50);

         String s;
         for(Iterator var18 = list1.iterator(); var18.hasNext(); l1 = Math.max(l1, this.mc.fontRenderer.getStringWidth(s))) {
            s = (String)var18.next();
         }
      }

      List<String> list2 = null;
      String s2;
      Iterator var36;
      if (this.footer != null) {
         list2 = this.mc.fontRenderer.listFormattedStringToWidth(this.footer.getFormattedText(), width - 50);

         for(var36 = list2.iterator(); var36.hasNext(); l1 = Math.max(l1, this.mc.fontRenderer.getStringWidth(s2))) {
            s2 = (String)var36.next();
         }
      }

      int l4;
      if (list1 != null) {
         drawRect(width / 2 - l1 / 2 - 1, k1 - 1, width / 2 + l1 / 2 + 1, k1 + list1.size() * this.mc.fontRenderer.FONT_HEIGHT, Integer.MIN_VALUE);

         for(var36 = list1.iterator(); var36.hasNext(); k1 += this.mc.fontRenderer.FONT_HEIGHT) {
            s2 = (String)var36.next();
            l4 = this.mc.fontRenderer.getStringWidth(s2);
            this.mc.fontRenderer.drawStringWithShadow(s2, (float)(width / 2 - l4 / 2), (float)k1, -1);
         }

         ++k1;
      }

      drawRect(width / 2 - l1 / 2 - 1, k1 - 1, width / 2 + l1 / 2 + 1, k1 + i4 * 9, Integer.MIN_VALUE);
      boolean nameprotect = 0bz.method_Qm().method_Qs().method_bxY(0bS.class).method_bBh();

      int j5;
      for(int k4 = 0; k4 < l3; ++k4) {
         l4 = k4 / i4;
         j5 = k4 % i4;
         int j2 = j1 + l4 * i1 + l4 * 5;
         int k2 = k1 + j5 * 9;
         yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         yh.enableAlpha();
         yh.enableBlend();
         yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA, ya.ONE, xR.ZERO);
         if (k4 < list.size()) {
            pB networkplayerinfo1 = (pB)list.get(k4);
            GameProfile gameprofile = networkplayerinfo1.getGameProfile();
            int l5;
            if (flag) {
               ME entityplayer = this.mc.world.getPlayerEntityByUUID(gameprofile.getId());
               boolean flag1 = entityplayer != null && entityplayer.isWearing(MH.CAPE) && ("Dinnerbone".equals(gameprofile.getName()) || "Grumm".equals(gameprofile.getName()));
               this.mc.getTextureManager().bindTexture(networkplayerinfo1.getLocationSkin());
               l5 = 8 + (flag1 ? 8 : 0);
               int i3 = 8 * (flag1 ? -1 : 1);
               jI.drawScaledCustomSizeModalRect(j2, k2, 8.0F, (float)l5, 8, i3, 8, 8, 64.0F, 64.0F);
               if (entityplayer != null && entityplayer.isWearing(MH.HAT)) {
                  int j3 = 8 + (flag1 ? 8 : 0);
                  int k3 = 8 * (flag1 ? -1 : 1);
                  jI.drawScaledCustomSizeModalRect(j2, k2, 40.0F, (float)j3, 8, k3, 8, 8, 64.0F, 64.0F);
               }

               j2 += 9;
            }

            String s4 = this.getPlayerName(networkplayerinfo1);
            if (nameprotect && gameprofile.getName().equals(nC.player.getGameProfile().getName())) {
               s4 = s4.replace(nC.player.getName(), "NeoWare");
            }

            if (networkplayerinfo1.getGameType() == bbb.SPECTATOR) {
               this.mc.fontRenderer.drawString(TextFormatting.ITALIC + s4, j2 + 1, k2, -1862270977);
            } else {
               this.mc.fontRenderer.drawString(s4, j2 + 1, k2, -1);
            }

            if (scoreObjectiveIn != null && networkplayerinfo1.getGameType() != bbb.SPECTATOR) {
               int k5 = j2 + i + 1;
               l5 = k5 + l;
               if (l5 - k5 > 5) {
                  this.drawScoreboardValues(scoreObjectiveIn, k2, gameprofile.getName(), k5, l5, networkplayerinfo1);
               }
            }

            this.drawPing(i1, j2 - (flag ? 9 : 0), k2, networkplayerinfo1);
         }
      }

      if (list2 != null) {
         k1 = k1 + i4 * 9 + 1;
         drawRect(width / 2 - l1 / 2 - 1, k1 - 1, width / 2 + l1 / 2 + 1, k1 + list2.size() * this.mc.fontRenderer.FONT_HEIGHT, Integer.MIN_VALUE);

         for(Iterator var39 = list2.iterator(); var39.hasNext(); k1 += this.mc.fontRenderer.FONT_HEIGHT) {
            String s3 = (String)var39.next();
            j5 = this.mc.fontRenderer.getStringWidth(s3);
            this.mc.fontRenderer.drawStringWithShadow(s3, (float)(width / 2 - j5 / 2), (float)k1, -1);
         }
      }

   }

   protected void drawPing(int p_175245_1_, int p_175245_2_, int p_175245_3_, pB networkPlayerInfoIn) {
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      int color = -1;
      if (networkPlayerInfoIn.getResponseTime() >= 0) {
         color = (new Color(59, 234, 146)).getRGB();
      }

      if (networkPlayerInfoIn.getResponseTime() > 200) {
         color = (new Color(248, 227, 76)).getRGB();
      }

      if (networkPlayerInfoIn.getResponseTime() > 500) {
         color = (new Color(239, 158, 67)).getRGB();
      }

      if (networkPlayerInfoIn.getResponseTime() > 800) {
         color = (new Color(255, 75, 75)).getRGB();
      }

      this.zLevel += 100.0F;
      String p = String.valueOf(MathHelper.clamp(networkPlayerInfoIn.getResponseTime(), 0, 999));
      0dZ.field_h.method_bED(p, (float)(p_175245_2_ + p_175245_1_ - 2 - 0dZ.field_h.method_bDO(p)), (float)p_175245_3_ + 5.5F, color);
      this.zLevel -= 100.0F;
   }

   private void drawScoreboardValues(Wz objective, int p_175247_2_, String name, int p_175247_4_, int p_175247_5_, pB info) {
      int i = objective.getScoreboard().getOrCreateScore(name, objective).getScorePoints();
      if (objective.getRenderType() == Wn.HEARTS) {
         this.mc.getTextureManager().bindTexture(ICONS);
         if (this.lastTimeOpened == info.getRenderVisibilityId()) {
            if (i < info.getLastHealth()) {
               info.setLastHealthTime(nC.getSystemTime());
               info.setHealthBlinkTime((long)(this.guiIngame.getUpdateCounter() + 20));
            } else if (i > info.getLastHealth()) {
               info.setLastHealthTime(nC.getSystemTime());
               info.setHealthBlinkTime((long)(this.guiIngame.getUpdateCounter() + 10));
            }
         }

         if (nC.getSystemTime() - info.getLastHealthTime() > 1000L || this.lastTimeOpened != info.getRenderVisibilityId()) {
            info.setLastHealth(i);
            info.setDisplayHealth(i);
            info.setLastHealthTime(nC.getSystemTime());
         }

         info.setRenderVisibilityId(this.lastTimeOpened);
         info.setLastHealth(i);
         int j = MathHelper.ceil((float)Math.max(i, info.getDisplayHealth()) / 2.0F);
         int k = Math.max(MathHelper.ceil((float)(i / 2)), Math.max(MathHelper.ceil((float)(info.getDisplayHealth() / 2)), 10));
         boolean flag = info.getHealthBlinkTime() > (long)this.guiIngame.getUpdateCounter() && (info.getHealthBlinkTime() - (long)this.guiIngame.getUpdateCounter()) / 3L % 2L == 1L;
         if (j > 0) {
            float f = Math.min((float)(p_175247_5_ - p_175247_4_ - 4) / (float)k, 9.0F);
            if (f > 3.0F) {
               int j1;
               for(j1 = j; j1 < k; ++j1) {
                  this.drawTexturedModalRect((float)p_175247_4_ + (float)j1 * f, (float)p_175247_2_, flag ? 25 : 16, 0, 9, 9);
               }

               for(j1 = 0; j1 < j; ++j1) {
                  this.drawTexturedModalRect((float)p_175247_4_ + (float)j1 * f, (float)p_175247_2_, flag ? 25 : 16, 0, 9, 9);
                  if (flag) {
                     if (j1 * 2 + 1 < info.getDisplayHealth()) {
                        this.drawTexturedModalRect((float)p_175247_4_ + (float)j1 * f, (float)p_175247_2_, 70, 0, 9, 9);
                     }

                     if (j1 * 2 + 1 == info.getDisplayHealth()) {
                        this.drawTexturedModalRect((float)p_175247_4_ + (float)j1 * f, (float)p_175247_2_, 79, 0, 9, 9);
                     }
                  }

                  if (j1 * 2 + 1 < i) {
                     this.drawTexturedModalRect((float)p_175247_4_ + (float)j1 * f, (float)p_175247_2_, j1 >= 10 ? 160 : 52, 0, 9, 9);
                  }

                  if (j1 * 2 + 1 == i) {
                     this.drawTexturedModalRect((float)p_175247_4_ + (float)j1 * f, (float)p_175247_2_, j1 >= 10 ? 169 : 61, 0, 9, 9);
                  }
               }
            } else {
               float f1 = MathHelper.clamp((float)i / 20.0F, 0.0F, 1.0F);
               int i1 = (int)((1.0F - f1) * 255.0F) << 16 | (int)(f1 * 255.0F) << 8;
               String s = "" + (float)i / 2.0F;
               if (p_175247_5_ - this.mc.fontRenderer.getStringWidth(s + "hp") >= p_175247_4_) {
                  s = s + "hp";
               }

               this.mc.fontRenderer.drawStringWithShadow(s, (float)((p_175247_5_ + p_175247_4_) / 2 - this.mc.fontRenderer.getStringWidth(s) / 2), (float)p_175247_2_, i1);
            }
         }
      } else {
         String s1 = TextFormatting.YELLOW + "" + i;
         this.mc.fontRenderer.drawStringWithShadow(s1, (float)(p_175247_5_ - this.mc.fontRenderer.getStringWidth(s1)), (float)p_175247_2_, 16777215);
      }

   }

   public void setFooter(@Nullable ITextComponent footerIn) {
      this.footer = footerIn;
   }

   public void setHeader(@Nullable ITextComponent headerIn) {
      this.header = headerIn;
   }

   public void resetFooterHeader() {
      this.header = null;
      this.footer = null;
   }
}
