package neo;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class kJ extends jI {
   private static final Logger LOGGER = LogManager.getLogger();
   private final nC mc;
   private final List<String> sentMessages = Lists.newArrayList();
   private final List<jG> chatLines = Lists.newArrayList();
   private final List<jG> drawnChatLines = Lists.newArrayList();
   private int scrollPos;
   private boolean isScrolled;

   public kJ(nC mcIn) {
      this.mc = mcIn;
   }

   public void drawChat(int updateCounter) {
      nC var10000 = this.mc;
      if (nC.gameSettings.chatVisibility != MB.HIDDEN) {
         int i = this.getLineCount();
         int j = this.drawnChatLines.size();
         var10000 = this.mc;
         float f = nC.gameSettings.chatOpacity * 0.9F + 0.1F;
         if (j > 0) {
            boolean flag = false;
            if (this.getChatOpen()) {
               flag = true;
            }

            float f1 = this.getChatScale();
            int k = MathHelper.ceil((float)this.getChatWidth() / f1);
            yh.pushMatrix();
            yh.translate(2.0F, 8.0F, 0.0F);
            yh.scale(f1, f1, 1.0F);
            int l = 0;
            boolean nameprotect = 0bz.method_Qm().method_Qs().method_bxY(0bS.class).method_bBh();

            int i1;
            int j1;
            int l1;
            for(i1 = 0; i1 + this.scrollPos < this.drawnChatLines.size() && i1 < i; ++i1) {
               jG chatline = (jG)this.drawnChatLines.get(i1 + this.scrollPos);
               if (chatline != null) {
                  j1 = updateCounter - chatline.getUpdatedCounter();
                  if (j1 < 200 || flag) {
                     double d0 = (double)j1 / 200.0;
                     d0 = 1.0 - d0;
                     d0 *= 10.0;
                     d0 = MathHelper.clamp(d0, 0.0, 1.0);
                     d0 *= d0;
                     l1 = (int)(255.0 * d0);
                     if (flag) {
                        l1 = 255;
                     }

                     l1 = (int)((float)l1 * f);
                     ++l;
                     if (l1 > 3) {
                        int i2 = false;
                        int j2 = -i1 * 9;
                        drawRect(-2, j2 - 9, k + 4, j2, l1 / 2 << 24);
                        String s = chatline.getChatComponent().getFormattedText();
                        if (nameprotect) {
                           s = s.replace(nC.player.getName(), "NeoWare");
                        }

                        yh.enableBlend();
                        this.mc.fontRenderer.drawStringWithShadow(s, 0.0F, (float)(j2 - 8), 16777215 + (l1 << 24));
                        yh.disableAlpha();
                        yh.disableBlend();
                     }
                  }
               }
            }

            if (flag) {
               i1 = this.mc.fontRenderer.FONT_HEIGHT;
               yh.translate(-3.0F, 0.0F, 0.0F);
               int l2 = j * i1 + j;
               j1 = l * i1 + l;
               int j3 = this.scrollPos * j1 / j;
               int k1 = j1 * j1 / l2;
               if (l2 != j1) {
                  l1 = j3 > 0 ? 170 : 96;
                  int l3 = this.isScrolled ? 13382451 : 3355562;
                  drawRect(0, -j3, 2, -j3 - k1, l3 + (l1 << 24));
                  drawRect(2, -j3, 1, -j3 - k1, 13421772 + (l1 << 24));
               }
            }

            yh.popMatrix();
         }
      }

   }

   public void clearChatMessages(boolean p_146231_1_) {
      this.drawnChatLines.clear();
      this.chatLines.clear();
      if (p_146231_1_) {
         this.sentMessages.clear();
      }

   }

   public void printChatMessage(ITextComponent chatComponent) {
      this.printChatMessageWithOptionalDeletion(chatComponent, 0);
   }

   public void printChatMessageWithOptionalDeletion(ITextComponent chatComponent, int chatLineId) {
      this.setChatLine(chatComponent, chatLineId, this.mc.ingameGUI.getUpdateCounter(), false);
      LOGGER.info("[CHAT] {}", chatComponent.getUnformattedText().replaceAll("\r", "\\\\r").replaceAll("\n", "\\\\n"));
   }

   private void setChatLine(ITextComponent chatComponent, int chatLineId, int updateCounter, boolean displayOnly) {
      if (chatLineId != 0) {
         this.deleteChatLine(chatLineId);
      }

      int i = MathHelper.floor((float)this.getChatWidth() / this.getChatScale());
      List<ITextComponent> list = lF.splitText(chatComponent, i, this.mc.fontRenderer, false, false);
      boolean flag = this.getChatOpen();

      ITextComponent itextcomponent;
      for(Iterator var8 = list.iterator(); var8.hasNext(); this.drawnChatLines.add(0, new jG(updateCounter, itextcomponent, chatLineId))) {
         itextcomponent = (ITextComponent)var8.next();
         if (flag && this.scrollPos > 0) {
            this.isScrolled = true;
            this.scroll(1);
         }
      }

      while(this.drawnChatLines.size() > 800) {
         this.drawnChatLines.remove(799);
      }

      if (!displayOnly) {
         this.chatLines.add(0, new jG(updateCounter, chatComponent, chatLineId));

         while(this.chatLines.size() > 800) {
            this.chatLines.remove(799);
         }
      }

   }

   public void refreshChat() {
      this.drawnChatLines.clear();
      this.resetScroll();

      for(int i = this.chatLines.size() - 1; i >= 0; --i) {
         jG chatline = (jG)this.chatLines.get(i);
         if (chatline != null) {
            this.setChatLine(chatline.getChatComponent(), chatline.getChatLineID(), chatline.getUpdatedCounter(), true);
         }
      }

   }

   public List<String> getSentMessages() {
      return this.sentMessages;
   }

   public void addToSentMessages(String message) {
      if (this.sentMessages.isEmpty() || !((String)this.sentMessages.get(this.sentMessages.size() - 1)).equals(message)) {
         this.sentMessages.add(message);
      }

   }

   public void resetScroll() {
      this.scrollPos = 0;
      this.isScrolled = false;
   }

   public void scroll(int amount) {
      this.scrollPos += amount;
      int i = this.drawnChatLines.size();
      if (this.scrollPos > i - this.getLineCount()) {
         this.scrollPos = i - this.getLineCount();
      }

      if (this.scrollPos <= 0) {
         this.scrollPos = 0;
         this.isScrolled = false;
      }

   }

   @Nullable
   public ITextComponent getChatComponent(int mouseX, int mouseY) {
      if (!this.getChatOpen()) {
         return null;
      } else {
         mC scaledresolution = new mC(this.mc);
         int i = scaledresolution.getScaleFactor();
         float f = this.getChatScale();
         int j = mouseX / i - 2;
         int k = mouseY / i - 40;
         j = MathHelper.floor((float)j / f);
         k = MathHelper.floor((float)k / f);
         if (j >= 0 && k >= 0) {
            int l = Math.min(this.getLineCount(), this.drawnChatLines.size());
            if (j <= MathHelper.floor((float)this.getChatWidth() / this.getChatScale()) && k < this.mc.fontRenderer.FONT_HEIGHT * l + l) {
               int i1 = k / this.mc.fontRenderer.FONT_HEIGHT + this.scrollPos;
               if (i1 >= 0 && i1 < this.drawnChatLines.size()) {
                  jG chatline = (jG)this.drawnChatLines.get(i1);
                  int j1 = 0;
                  if (chatline != null) {
                     Iterator var12 = chatline.getChatComponent().iterator();

                     while(var12.hasNext()) {
                        ITextComponent itextcomponent = (ITextComponent)var12.next();
                        if (itextcomponent instanceof TextComponentString) {
                           j1 += this.mc.fontRenderer.getStringWidth(lF.removeTextColorsIfConfigured(((TextComponentString)itextcomponent).getText(), false));
                           if (j1 > j) {
                              return itextcomponent;
                           }
                        }
                     }
                  }
               }

               return null;
            } else {
               return null;
            }
         } else {
            return null;
         }
      }
   }

   public boolean getChatOpen() {
      return this.mc.currentScreen instanceof jP;
   }

   public void deleteChatLine(int id) {
      Iterator<jG> iterator = this.drawnChatLines.iterator();

      jG chatline1;
      while(iterator.hasNext()) {
         chatline1 = (jG)iterator.next();
         if (chatline1 != null && chatline1.getChatLineID() == id) {
            iterator.remove();
         }
      }

      iterator = this.chatLines.iterator();

      while(iterator.hasNext()) {
         chatline1 = (jG)iterator.next();
         if (chatline1 != null && chatline1.getChatLineID() == id) {
            iterator.remove();
            break;
         }
      }

   }

   public int getChatWidth() {
      nC var10000 = this.mc;
      return calculateChatboxWidth(nC.gameSettings.chatWidth * 2.0F);
   }

   public int getChatHeight() {
      nC var10000;
      float var1;
      if (this.getChatOpen()) {
         var10000 = this.mc;
         var1 = nC.gameSettings.chatHeightFocused;
      } else {
         var10000 = this.mc;
         var1 = nC.gameSettings.chatHeightUnfocused;
      }

      return calculateChatboxHeight(var1);
   }

   public float getChatScale() {
      nC var10000 = this.mc;
      return nC.gameSettings.chatScale;
   }

   public static int calculateChatboxWidth(float scale) {
      int i = true;
      int j = true;
      return MathHelper.floor(scale * 280.0F + 40.0F);
   }

   public static int calculateChatboxHeight(float scale) {
      int i = true;
      int j = true;
      return MathHelper.floor(scale * 160.0F + 20.0F);
   }

   public int getLineCount() {
      return this.getChatHeight() / 9;
   }
}
