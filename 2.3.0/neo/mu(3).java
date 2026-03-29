package neo;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nullable;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;

public class mu extends jI implements my {
   protected static final ResourceLocation RECIPE_BOOK = new ResourceLocation("textures/gui/recipe_book.png");
   private int xOffset;
   private int width;
   private int height;
   private final mr ghostRecipe = new mr();
   private final List<mt> recipeTabs;
   private mt currentTab;
   private jN toggleRecipesBtn;
   private InventoryCrafting craftingSlots;
   private nC mc;
   private lE searchBar;
   private String lastSearch;
   private XK recipeBook;
   private final mA recipeBookPage;
   private BR stackedContents;
   private int timesInventoryChanged;

   public mu() {
      this.recipeTabs = Lists.newArrayList(new mt[]{new mt(0, EN.SEARCH), new mt(0, EN.TOOLS), new mt(0, EN.BUILDING_BLOCKS), new mt(0, EN.MISC), new mt(0, EN.REDSTONE)});
      this.lastSearch = "";
      this.recipeBookPage = new mA();
      this.stackedContents = new BR();
   }

   public void func_194303_a(int p_194303_1_, int p_194303_2_, nC p_194303_3_, boolean p_194303_4_, InventoryCrafting p_194303_5_) {
      this.mc = p_194303_3_;
      this.width = p_194303_1_;
      this.height = p_194303_2_;
      this.craftingSlots = p_194303_5_;
      this.recipeBook = nC.player.getRecipeBook();
      this.timesInventoryChanged = nC.player.inventory.getTimesChanged();
      this.currentTab = (mt)this.recipeTabs.get(0);
      this.currentTab.setStateTriggered(true);
      if (this.isVisible()) {
         this.initVisuals(p_194303_4_, p_194303_5_);
      }

      Keyboard.enableRepeatEvents(true);
   }

   public void initVisuals(boolean p_193014_1_, InventoryCrafting p_193014_2_) {
      this.xOffset = p_193014_1_ ? 0 : 86;
      int i = (this.width - 147) / 2 - this.xOffset;
      int j = (this.height - 166) / 2;
      this.stackedContents.clear();
      nC var10000 = this.mc;
      nC.player.inventory.fillStackedContents(this.stackedContents, false);
      p_193014_2_.fillStackedContents(this.stackedContents);
      this.searchBar = new lE(0, this.mc.fontRenderer, i + 25, j + 14, 80, this.mc.fontRenderer.FONT_HEIGHT + 5);
      this.searchBar.setMaxStringLength(50);
      this.searchBar.setEnableBackgroundDrawing(false);
      this.searchBar.setVisible(true);
      this.searchBar.setTextColor(16777215);
      this.recipeBookPage.init(this.mc, i, j);
      this.recipeBookPage.addListener(this);
      this.toggleRecipesBtn = new jN(0, i + 110, j + 12, 26, 16, this.recipeBook.isFilteringCraftable());
      this.toggleRecipesBtn.initTextureValues(152, 41, 28, 18, RECIPE_BOOK);
      this.updateCollections(false);
      this.updateTabs();
   }

   public void removed() {
      Keyboard.enableRepeatEvents(false);
   }

   public int updateScreenPosition(boolean p_193011_1_, int p_193011_2_, int p_193011_3_) {
      int i;
      if (this.isVisible() && !p_193011_1_) {
         i = 177 + (p_193011_2_ - p_193011_3_ - 200) / 2;
      } else {
         i = (p_193011_2_ - p_193011_3_) / 2;
      }

      return i;
   }

   public void toggleVisibility() {
      this.setVisible(!this.isVisible());
   }

   public boolean isVisible() {
      return this.recipeBook.isGuiOpen();
   }

   private void setVisible(boolean p_193006_1_) {
      this.recipeBook.setGuiOpen(p_193006_1_);
      if (!p_193006_1_) {
         this.recipeBookPage.setInvisible();
      }

      this.sendUpdateSettings();
   }

   public void slotClicked(@Nullable Slot slotIn) {
      if (slotIn != null && slotIn.slotNumber <= 9) {
         this.ghostRecipe.clear();
         if (this.isVisible()) {
            this.updateStackedContents();
         }
      }

   }

   private void updateCollections(boolean p_193003_1_) {
      List<mB> list = (List)BP.RECIPES_BY_TAB.get(this.currentTab.getCategory());
      list.forEach((p_193944_1_) -> {
         p_193944_1_.canCraft(this.stackedContents, this.craftingSlots.getWidth(), this.craftingSlots.getHeight(), this.recipeBook);
      });
      List<mB> list1 = Lists.newArrayList(list);
      list1.removeIf((p_193952_0_) -> {
         return !p_193952_0_.isNotEmpty();
      });
      list1.removeIf((p_193953_0_) -> {
         return !p_193953_0_.containsValidRecipes();
      });
      String s = this.searchBar.getText();
      if (!s.isEmpty()) {
         ObjectSet<mB> objectset = new ObjectLinkedOpenHashSet(this.mc.getSearchTree(BV.RECIPES).search(s.toLowerCase(Locale.ROOT)));
         list1.removeIf((p_193947_1_) -> {
            return !objectset.contains(p_193947_1_);
         });
      }

      if (this.recipeBook.isFilteringCraftable()) {
         list1.removeIf((p_193958_0_) -> {
            return !p_193958_0_.containsCraftableRecipes();
         });
      }

      this.recipeBookPage.updateLists(list1, p_193003_1_);
   }

   private void updateTabs() {
      int i = (this.width - 147) / 2 - this.xOffset - 30;
      int j = (this.height - 166) / 2 + 3;
      int k = true;
      int l = 0;
      Iterator var5 = this.recipeTabs.iterator();

      while(var5.hasNext()) {
         mt guibuttonrecipetab = (mt)var5.next();
         EN creativetabs = guibuttonrecipetab.getCategory();
         if (creativetabs == EN.SEARCH) {
            guibuttonrecipetab.visible = true;
            guibuttonrecipetab.setPosition(i, j + 27 * l++);
         } else if (guibuttonrecipetab.updateVisibility()) {
            guibuttonrecipetab.setPosition(i, j + 27 * l++);
            guibuttonrecipetab.startAnimation(this.mc);
         }
      }

   }

   public void tick() {
      if (this.isVisible()) {
         nC var10001 = this.mc;
         if (this.timesInventoryChanged != nC.player.inventory.getTimesChanged()) {
            this.updateStackedContents();
            var10001 = this.mc;
            this.timesInventoryChanged = nC.player.inventory.getTimesChanged();
         }
      }

   }

   private void updateStackedContents() {
      this.stackedContents.clear();
      nC var10000 = this.mc;
      nC.player.inventory.fillStackedContents(this.stackedContents, false);
      this.craftingSlots.fillStackedContents(this.stackedContents);
      this.updateCollections(false);
   }

   public void render(int mouseX, int mouseY, float partialTicks) {
      if (this.isVisible()) {
         yz.enableGUIStandardItemLighting();
         yh.disableLighting();
         yh.pushMatrix();
         yh.translate(0.0F, 0.0F, 100.0F);
         this.mc.getTextureManager().bindTexture(RECIPE_BOOK);
         yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         int i = (this.width - 147) / 2 - this.xOffset;
         int j = (this.height - 166) / 2;
         this.drawTexturedModalRect(i, j, 1, 1, 147, 166);
         this.searchBar.drawTextBox();
         yz.disableStandardItemLighting();
         Iterator var6 = this.recipeTabs.iterator();

         while(var6.hasNext()) {
            mt guibuttonrecipetab = (mt)var6.next();
            guibuttonrecipetab.drawButton(this.mc, mouseX, mouseY, partialTicks);
         }

         this.toggleRecipesBtn.drawButton(this.mc, mouseX, mouseY, partialTicks);
         this.recipeBookPage.render(i, j, mouseX, mouseY, partialTicks);
         yh.popMatrix();
      }

   }

   public void renderTooltip(int p_191876_1_, int p_191876_2_, int p_191876_3_, int p_191876_4_) {
      if (this.isVisible()) {
         this.recipeBookPage.renderTooltip(p_191876_3_, p_191876_4_);
         if (this.toggleRecipesBtn.isMouseOver()) {
            String s1 = Ax.format(this.toggleRecipesBtn.isStateTriggered() ? "gui.recipebook.toggleRecipes.craftable" : "gui.recipebook.toggleRecipes.all");
            if (this.mc.currentScreen != null) {
               this.mc.currentScreen.drawHoveringText(s1, p_191876_3_, p_191876_4_);
            }
         }

         this.renderGhostRecipeTooltip(p_191876_1_, p_191876_2_, p_191876_3_, p_191876_4_);
      }

   }

   private void renderGhostRecipeTooltip(int p_193015_1_, int p_193015_2_, int p_193015_3_, int p_193015_4_) {
      Qy itemstack = null;

      for(int i = 0; i < this.ghostRecipe.size(); ++i) {
         mq ghostrecipe$ghostingredient = this.ghostRecipe.get(i);
         int j = ghostrecipe$ghostingredient.getX() + p_193015_1_;
         int k = ghostrecipe$ghostingredient.getY() + p_193015_2_;
         if (p_193015_3_ >= j && p_193015_4_ >= k && p_193015_3_ < j + 16 && p_193015_4_ < k + 16) {
            itemstack = ghostrecipe$ghostingredient.getItem();
         }
      }

      if (itemstack != null && this.mc.currentScreen != null) {
         this.mc.currentScreen.drawHoveringText(this.mc.currentScreen.getItemToolTip(itemstack), p_193015_3_, p_193015_4_);
      }

   }

   public void renderGhostRecipe(int p_191864_1_, int p_191864_2_, boolean p_191864_3_, float p_191864_4_) {
      this.ghostRecipe.render(this.mc, p_191864_1_, p_191864_2_, p_191864_3_, p_191864_4_);
   }

   public boolean mouseClicked(int p_191862_1_, int p_191862_2_, int p_191862_3_) {
      if (this.isVisible()) {
         nC var10000 = this.mc;
         if (!nC.player.isSpectator()) {
            if (this.recipeBookPage.mouseClicked(p_191862_1_, p_191862_2_, p_191862_3_, (this.width - 147) / 2 - this.xOffset, (this.height - 166) / 2, 147, 166)) {
               NT irecipe = this.recipeBookPage.getLastClickedRecipe();
               mB recipelist = this.recipeBookPage.getLastClickedRecipeList();
               if (irecipe != null && recipelist != null) {
                  if (!recipelist.isCraftable(irecipe) && this.ghostRecipe.getRecipe() == irecipe) {
                     return false;
                  }

                  this.ghostRecipe.clear();
                  pc var9 = this.mc.playerController;
                  nC var10001 = this.mc;
                  int var10 = nC.player.openContainer.windowId;
                  boolean var10003 = lg.isShiftKeyDown();
                  nC var10004 = this.mc;
                  var9.func_194338_a(var10, irecipe, var10003, nC.player);
                  if (!this.isOffsetNextToMainGUI() && p_191862_3_ == 0) {
                     this.setVisible(false);
                  }
               }

               return true;
            }

            if (p_191862_3_ != 0) {
               return false;
            }

            if (this.searchBar.mouseClicked(p_191862_1_, p_191862_2_, p_191862_3_)) {
               return true;
            }

            if (this.toggleRecipesBtn.mousePressed(this.mc, p_191862_1_, p_191862_2_)) {
               boolean flag = !this.recipeBook.isFilteringCraftable();
               this.recipeBook.setFilteringCraftable(flag);
               this.toggleRecipesBtn.setStateTriggered(flag);
               this.toggleRecipesBtn.playPressSound(this.mc.getSoundHandler());
               this.sendUpdateSettings();
               this.updateCollections(false);
               return true;
            }

            Iterator var4 = this.recipeTabs.iterator();

            mt guibuttonrecipetab;
            do {
               if (!var4.hasNext()) {
                  return false;
               }

               guibuttonrecipetab = (mt)var4.next();
            } while(!guibuttonrecipetab.mousePressed(this.mc, p_191862_1_, p_191862_2_));

            if (this.currentTab != guibuttonrecipetab) {
               guibuttonrecipetab.playPressSound(this.mc.getSoundHandler());
               this.currentTab.setStateTriggered(false);
               this.currentTab = guibuttonrecipetab;
               this.currentTab.setStateTriggered(true);
               this.updateCollections(true);
            }

            return true;
         }
      }

      return false;
   }

   public boolean hasClickedOutside(int p_193955_1_, int p_193955_2_, int p_193955_3_, int p_193955_4_, int p_193955_5_, int p_193955_6_) {
      if (!this.isVisible()) {
         return true;
      } else {
         boolean flag = p_193955_1_ < p_193955_3_ || p_193955_2_ < p_193955_4_ || p_193955_1_ >= p_193955_3_ + p_193955_5_ || p_193955_2_ >= p_193955_4_ + p_193955_6_;
         boolean flag1 = p_193955_3_ - 147 < p_193955_1_ && p_193955_1_ < p_193955_3_ && p_193955_4_ < p_193955_2_ && p_193955_2_ < p_193955_4_ + p_193955_6_;
         return flag && !flag1 && !this.currentTab.mousePressed(this.mc, p_193955_1_, p_193955_2_);
      }
   }

   public boolean keyPressed(char typedChar, int keycode) {
      if (this.isVisible()) {
         nC var10000 = this.mc;
         if (!nC.player.isSpectator()) {
            if (keycode == 1 && !this.isOffsetNextToMainGUI()) {
               this.setVisible(false);
               return true;
            }

            var10000 = this.mc;
            if (Bj.isKeyDown(nC.gameSettings.keyBindChat) && !this.searchBar.isFocused()) {
               this.searchBar.setFocused(true);
            } else if (this.searchBar.textboxKeyTyped(typedChar, keycode)) {
               String s1 = this.searchBar.getText().toLowerCase(Locale.ROOT);
               this.pirateRecipe(s1);
               if (!s1.equals(this.lastSearch)) {
                  this.updateCollections(false);
                  this.lastSearch = s1;
               }

               return true;
            }

            return false;
         }
      }

      return false;
   }

   private void pirateRecipe(String text) {
      if ("excitedze".equals(text)) {
         AE languagemanager = this.mc.getLanguageManager();
         AD language = languagemanager.getLanguage("en_pt");
         if (languagemanager.getCurrentLanguage().compareTo(language) == 0) {
            return;
         }

         nC var10000;
         jH var4;
         boolean var5;
         label17: {
            languagemanager.setCurrentLanguage(language);
            var10000 = this.mc;
            nC.gameSettings.language = language.getLanguageCode();
            this.mc.refreshResources();
            var4 = this.mc.fontRenderer;
            if (!this.mc.getLanguageManager().isCurrentLocaleUnicode()) {
               nC var10001 = this.mc;
               if (!nC.gameSettings.forceUnicodeFont) {
                  var5 = false;
                  break label17;
               }
            }

            var5 = true;
         }

         var4.setUnicodeFlag(var5);
         this.mc.fontRenderer.setBidiFlag(languagemanager.isCurrentLanguageBidirectional());
         var10000 = this.mc;
         nC.gameSettings.saveOptions();
      }

   }

   private boolean isOffsetNextToMainGUI() {
      return this.xOffset == 86;
   }

   public void recipesUpdated() {
      this.updateTabs();
      if (this.isVisible()) {
         this.updateCollections(false);
      }

   }

   public void recipesShown(List<NT> recipes) {
      Iterator var2 = recipes.iterator();

      while(var2.hasNext()) {
         NT irecipe = (NT)var2.next();
         nC var10000 = this.mc;
         nC.player.removeRecipeHighlight(irecipe);
      }

   }

   public void setupGhostRecipe(NT p_193951_1_, List<Slot> p_193951_2_) {
      Qy itemstack = p_193951_1_.getRecipeOutput();
      this.ghostRecipe.setRecipe(p_193951_1_);
      this.ghostRecipe.addIngredient(NS.fromStacks(itemstack), ((Slot)p_193951_2_.get(0)).xPos, ((Slot)p_193951_2_.get(0)).yPos);
      int i = this.craftingSlots.getWidth();
      int j = this.craftingSlots.getHeight();
      int k = p_193951_1_ instanceof Of ? ((Of)p_193951_1_).getWidth() : i;
      int l = 1;
      Iterator<NS> iterator = p_193951_1_.getIngredients().iterator();

      for(int i1 = 0; i1 < j; ++i1) {
         for(int j1 = 0; j1 < k; ++j1) {
            if (!iterator.hasNext()) {
               return;
            }

            NS ingredient = (NS)iterator.next();
            if (ingredient != NS.EMPTY) {
               Slot slot = (Slot)p_193951_2_.get(l);
               this.ghostRecipe.addIngredient(ingredient, slot.xPos, slot.yPos);
            }

            ++l;
         }

         if (k < i) {
            l += i - k;
         }
      }

   }

   private void sendUpdateSettings() {
      if (this.mc.getConnection() != null) {
         this.mc.getConnection().sendPacket(new Tf(this.isVisible(), this.recipeBook.isFilteringCraftable()));
      }

   }
}
