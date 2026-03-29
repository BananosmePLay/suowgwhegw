package neo;

import com.google.common.collect.Lists;
import com.google.gson.JsonParseException;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

public class lk extends lg {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final ResourceLocation BOOK_GUI_TEXTURES = new ResourceLocation("textures/gui/book.png");
   private final ME editingPlayer;
   private final Qy book;
   private final boolean bookIsUnsigned;
   private boolean bookIsModified;
   private boolean bookGettingSigned;
   private int updateCount;
   private final int bookImageWidth = 192;
   private final int bookImageHeight = 192;
   private int bookTotalPages = 1;
   private int currPage;
   private QW bookPages;
   private String bookTitle = "";
   private List<ITextComponent> cachedComponents;
   private int cachedPage = -1;
   private lj buttonNextPage;
   private lj buttonPreviousPage;
   private jK buttonDone;
   private jK buttonSign;
   private jK buttonFinalize;
   private jK buttonCancel;

   public lk(ME player, Qy book, boolean isUnsigned) {
      this.editingPlayer = player;
      this.book = book;
      this.bookIsUnsigned = isUnsigned;
      if (book.hasTagCompound()) {
         QQ nbttagcompound = book.getTagCompound();
         this.bookPages = nbttagcompound.getTagList("pages", 8).copy();
         this.bookTotalPages = this.bookPages.tagCount();
         if (this.bookTotalPages < 1) {
            this.bookTotalPages = 1;
         }
      }

      if (this.bookPages == null && isUnsigned) {
         this.bookPages = new QW();
         this.bookPages.appendTag(new Ra(""));
         this.bookTotalPages = 1;
      }

   }

   public void updateScreen() {
      super.updateScreen();
      ++this.updateCount;
   }

   public void initGui() {
      this.buttonList.clear();
      Keyboard.enableRepeatEvents(true);
      if (this.bookIsUnsigned) {
         this.buttonSign = this.addButton(new jK(3, this.width / 2 - 100, 196, 98, 20, Ax.format("book.signButton")));
         this.buttonDone = this.addButton(new jK(0, this.width / 2 + 2, 196, 98, 20, Ax.format("gui.done")));
         this.buttonFinalize = this.addButton(new jK(5, this.width / 2 - 100, 196, 98, 20, Ax.format("book.finalizeButton")));
         this.buttonCancel = this.addButton(new jK(4, this.width / 2 + 2, 196, 98, 20, Ax.format("gui.cancel")));
      } else {
         this.buttonDone = this.addButton(new jK(0, this.width / 2 - 100, 196, 200, 20, Ax.format("gui.done")));
      }

      int i = (this.width - 192) / 2;
      int j = true;
      this.buttonNextPage = (lj)this.addButton(new lj(1, i + 120, 156, true));
      this.buttonPreviousPage = (lj)this.addButton(new lj(2, i + 38, 156, false));
      this.updateButtons();
   }

   public void onGuiClosed() {
      Keyboard.enableRepeatEvents(false);
   }

   private void updateButtons() {
      this.buttonNextPage.visible = !this.bookGettingSigned && (this.currPage < this.bookTotalPages - 1 || this.bookIsUnsigned);
      this.buttonPreviousPage.visible = !this.bookGettingSigned && this.currPage > 0;
      this.buttonDone.visible = !this.bookIsUnsigned || !this.bookGettingSigned;
      if (this.bookIsUnsigned) {
         this.buttonSign.visible = !this.bookGettingSigned;
         this.buttonCancel.visible = this.bookGettingSigned;
         this.buttonFinalize.visible = this.bookGettingSigned;
         this.buttonFinalize.enabled = !this.bookTitle.trim().isEmpty();
      }

   }

   private void sendBookToServer(boolean publish) throws IOException {
      if (this.bookIsUnsigned && this.bookIsModified && this.bookPages != null) {
         String s1;
         while(this.bookPages.tagCount() > 1) {
            s1 = this.bookPages.getStringTagAt(this.bookPages.tagCount() - 1);
            if (!s1.isEmpty()) {
               break;
            }

            this.bookPages.removeTag(this.bookPages.tagCount() - 1);
         }

         if (this.book.hasTagCompound()) {
            QQ nbttagcompound = this.book.getTagCompound();
            nbttagcompound.setTag("pages", this.bookPages);
         } else {
            this.book.setTagInfo("pages", this.bookPages);
         }

         s1 = "MC|BEdit";
         if (publish) {
            s1 = "MC|BSign";
            this.book.setTagInfo("author", new Ra(this.editingPlayer.getName()));
            this.book.setTagInfo("title", new Ra(this.bookTitle.trim()));
         }

         SA packetbuffer = new SA(Unpooled.buffer());
         packetbuffer.writeItemStack(this.book);
         this.mc.getConnection().sendPacket(new SN(s1, packetbuffer));
      }

   }

   protected void actionPerformed(jK button) throws IOException {
      if (button.enabled) {
         if (button.id == 0) {
            this.mc.displayGuiScreen((lg)null);
            this.sendBookToServer(false);
         } else if (button.id == 3 && this.bookIsUnsigned) {
            this.bookGettingSigned = true;
         } else if (button.id == 1) {
            if (this.currPage < this.bookTotalPages - 1) {
               ++this.currPage;
            } else if (this.bookIsUnsigned) {
               this.addNewPage();
               if (this.currPage < this.bookTotalPages - 1) {
                  ++this.currPage;
               }
            }
         } else if (button.id == 2) {
            if (this.currPage > 0) {
               --this.currPage;
            }
         } else if (button.id == 5 && this.bookGettingSigned) {
            this.sendBookToServer(true);
            this.mc.displayGuiScreen((lg)null);
         } else if (button.id == 4 && this.bookGettingSigned) {
            this.bookGettingSigned = false;
         }

         this.updateButtons();
      }

   }

   private void addNewPage() {
      if (this.bookPages != null && this.bookPages.tagCount() < 50) {
         this.bookPages.appendTag(new Ra(""));
         ++this.bookTotalPages;
         this.bookIsModified = true;
      }

   }

   protected void keyTyped(char typedChar, int keyCode) throws IOException {
      super.keyTyped(typedChar, keyCode);
      if (this.bookIsUnsigned) {
         if (this.bookGettingSigned) {
            this.keyTypedInTitle(typedChar, keyCode);
         } else {
            this.keyTypedInBook(typedChar, keyCode);
         }
      }

   }

   private void keyTypedInBook(char typedChar, int keyCode) {
      if (lg.isKeyComboCtrlV(keyCode)) {
         this.pageInsertIntoCurrent(lg.getClipboardString());
      } else {
         switch (keyCode) {
            case 14:
               String s = this.pageGetCurrent();
               if (!s.isEmpty()) {
                  this.pageSetCurrent(s.substring(0, s.length() - 1));
               }

               return;
            case 28:
            case 156:
               this.pageInsertIntoCurrent("\n");
               return;
            default:
               if (ChatAllowedCharacters.isAllowedCharacter(typedChar)) {
                  this.pageInsertIntoCurrent(Character.toString(typedChar));
               }
         }
      }

   }

   private void keyTypedInTitle(char typedChar, int keyCode) throws IOException {
      switch (keyCode) {
         case 14:
            if (!this.bookTitle.isEmpty()) {
               this.bookTitle = this.bookTitle.substring(0, this.bookTitle.length() - 1);
               this.updateButtons();
            }

            return;
         case 28:
         case 156:
            if (!this.bookTitle.isEmpty()) {
               this.sendBookToServer(true);
               this.mc.displayGuiScreen((lg)null);
            }

            return;
         default:
            if (this.bookTitle.length() < 16 && ChatAllowedCharacters.isAllowedCharacter(typedChar)) {
               this.bookTitle = this.bookTitle + Character.toString(typedChar);
               this.updateButtons();
               this.bookIsModified = true;
            }

      }
   }

   private String pageGetCurrent() {
      return this.bookPages != null && this.currPage >= 0 && this.currPage < this.bookPages.tagCount() ? this.bookPages.getStringTagAt(this.currPage) : "";
   }

   private void pageSetCurrent(String p_146457_1_) {
      if (this.bookPages != null && this.currPage >= 0 && this.currPage < this.bookPages.tagCount()) {
         this.bookPages.set(this.currPage, new Ra(p_146457_1_));
         this.bookIsModified = true;
      }

   }

   private void pageInsertIntoCurrent(String p_146459_1_) {
      String s = this.pageGetCurrent();
      String s1 = s + p_146459_1_;
      int i = this.fontRenderer.getWordWrappedHeight(s1 + "" + TextFormatting.BLACK + "_", 118);
      if (i <= 128 && s1.length() < 256) {
         this.pageSetCurrent(s1);
      }

   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(BOOK_GUI_TEXTURES);
      int i = (this.width - 192) / 2;
      int j = true;
      this.drawTexturedModalRect(i, 2, 0, 0, 192, 192);
      String s4;
      String s5;
      int j1;
      int k1;
      if (this.bookGettingSigned) {
         s4 = this.bookTitle;
         if (this.bookIsUnsigned) {
            if (this.updateCount / 6 % 2 == 0) {
               s4 = s4 + "" + TextFormatting.BLACK + "_";
            } else {
               s4 = s4 + "" + TextFormatting.GRAY + "_";
            }
         }

         s5 = Ax.format("book.editTitle");
         j1 = this.fontRenderer.getStringWidth(s5);
         this.fontRenderer.drawString(s5, i + 36 + (116 - j1) / 2, 34, 0);
         k1 = this.fontRenderer.getStringWidth(s4);
         this.fontRenderer.drawString(s4, i + 36 + (116 - k1) / 2, 50, 0);
         String s2 = Ax.format("book.byAuthor", this.editingPlayer.getName());
         int i1 = this.fontRenderer.getStringWidth(s2);
         this.fontRenderer.drawString(TextFormatting.DARK_GRAY + s2, i + 36 + (116 - i1) / 2, 60, 0);
         String s3 = Ax.format("book.finalizeWarning");
         this.fontRenderer.drawSplitString(s3, i + 36, 82, 116, 0);
      } else {
         s4 = Ax.format("book.pageIndicator", this.currPage + 1, this.bookTotalPages);
         s5 = "";
         if (this.bookPages != null && this.currPage >= 0 && this.currPage < this.bookPages.tagCount()) {
            s5 = this.bookPages.getStringTagAt(this.currPage);
         }

         if (this.bookIsUnsigned) {
            if (this.fontRenderer.getBidiFlag()) {
               s5 = s5 + "_";
            } else if (this.updateCount / 6 % 2 == 0) {
               s5 = s5 + "" + TextFormatting.BLACK + "_";
            } else {
               s5 = s5 + "" + TextFormatting.GRAY + "_";
            }
         } else if (this.cachedPage != this.currPage) {
            if (QD.validBookTagContents(this.book.getTagCompound())) {
               try {
                  ITextComponent itextcomponent = ITextComponent.Serializer.jsonToComponent(s5);
                  this.cachedComponents = itextcomponent != null ? lF.splitText(itextcomponent, 116, this.fontRenderer, true, true) : null;
               } catch (JsonParseException var13) {
                  this.cachedComponents = null;
               }
            } else {
               TextComponentString textcomponentstring = new TextComponentString(TextFormatting.DARK_RED + "* Invalid book tag *");
               this.cachedComponents = Lists.newArrayList(textcomponentstring);
            }

            this.cachedPage = this.currPage;
         }

         j1 = this.fontRenderer.getStringWidth(s4);
         this.fontRenderer.drawString(s4, i - j1 + 192 - 44, 18, 0);
         if (this.cachedComponents == null) {
            this.fontRenderer.drawSplitString(s5, i + 36, 34, 116, 0);
         } else {
            k1 = Math.min(128 / this.fontRenderer.FONT_HEIGHT, this.cachedComponents.size());

            for(int l1 = 0; l1 < k1; ++l1) {
               ITextComponent itextcomponent2 = (ITextComponent)this.cachedComponents.get(l1);
               this.fontRenderer.drawString(itextcomponent2.getUnformattedText(), i + 36, 34 + l1 * this.fontRenderer.FONT_HEIGHT, 0);
            }

            ITextComponent itextcomponent1 = this.getClickedComponentAt(mouseX, mouseY);
            if (itextcomponent1 != null) {
               this.handleComponentHover(itextcomponent1, mouseX, mouseY);
            }
         }
      }

      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      if (mouseButton == 0) {
         ITextComponent itextcomponent = this.getClickedComponentAt(mouseX, mouseY);
         if (itextcomponent != null && this.handleComponentClick(itextcomponent)) {
            return;
         }
      }

      super.mouseClicked(mouseX, mouseY, mouseButton);
   }

   public boolean handleComponentClick(ITextComponent component) {
      ClickEvent clickevent = component.getStyle().getClickEvent();
      if (clickevent == null) {
         return false;
      } else if (clickevent.getAction() == ClickEvent.Action.CHANGE_PAGE) {
         String s = clickevent.getValue();

         try {
            int i = Integer.parseInt(s) - 1;
            if (i >= 0 && i < this.bookTotalPages && i != this.currPage) {
               this.currPage = i;
               this.updateButtons();
               return true;
            }
         } catch (Throwable var5) {
         }

         return false;
      } else {
         boolean flag = super.handleComponentClick(component);
         if (flag && clickevent.getAction() == ClickEvent.Action.RUN_COMMAND) {
            this.mc.displayGuiScreen((lg)null);
         }

         return flag;
      }
   }

   @Nullable
   public ITextComponent getClickedComponentAt(int p_175385_1_, int p_175385_2_) {
      if (this.cachedComponents == null) {
         return null;
      } else {
         int i = p_175385_1_ - (this.width - 192) / 2 - 36;
         int j = p_175385_2_ - 2 - 16 - 16;
         if (i >= 0 && j >= 0) {
            int k = Math.min(128 / this.fontRenderer.FONT_HEIGHT, this.cachedComponents.size());
            if (i <= 116 && j < this.mc.fontRenderer.FONT_HEIGHT * k + k) {
               int l = j / this.mc.fontRenderer.FONT_HEIGHT;
               if (l >= 0 && l < this.cachedComponents.size()) {
                  ITextComponent itextcomponent = (ITextComponent)this.cachedComponents.get(l);
                  int i1 = 0;
                  Iterator var9 = itextcomponent.iterator();

                  while(var9.hasNext()) {
                     ITextComponent itextcomponent1 = (ITextComponent)var9.next();
                     if (itextcomponent1 instanceof TextComponentString) {
                        i1 += this.mc.fontRenderer.getStringWidth(((TextComponentString)itextcomponent1).getText());
                        if (i1 > i) {
                           return itextcomponent1;
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

   // $FF: synthetic method
   static ResourceLocation access$000() {
      return BOOK_GUI_TEXTURES;
   }
}
