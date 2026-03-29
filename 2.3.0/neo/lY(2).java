package neo;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class lY extends yk {
   private static final ResourceLocation CREATIVE_INVENTORY_TABS = new ResourceLocation("textures/gui/container/creative_inventory/tabs.png");
   private static final InventoryBasic basicInventory = new InventoryBasic("tmp", true, 45);
   private static int selectedTabIndex;
   private float currentScroll;
   private boolean isScrolling;
   private boolean wasClicking;
   private lE searchField;
   private List<Slot> originalSlots;
   private Slot destroyItemSlot;
   private boolean clearSearch;
   private lM listener;

   public lY(ME player) {
      super(new lV(player));
      player.openContainer = this.inventorySlots;
      this.allowUserInput = true;
      this.ySize = 136;
      this.xSize = 195;
   }

   public void updateScreen() {
      if (!this.mc.playerController.isInCreativeMode()) {
         nC var10003 = this.mc;
         this.mc.displayGuiScreen(new mh(nC.player));
      }

   }

   protected void handleMouseClick(@Nullable Slot slotIn, int slotId, int mouseButton, ClickType type) {
      this.clearSearch = true;
      boolean flag = type == ClickType.QUICK_MOVE;
      type = slotId == -999 && type == ClickType.PICKUP ? ClickType.THROW : type;
      Qy itemstack5;
      MJ inventoryplayer;
      nC var10000;
      if (slotIn == null && selectedTabIndex != EN.INVENTORY.getIndex() && type != ClickType.QUICK_CRAFT) {
         var10000 = this.mc;
         inventoryplayer = nC.player.inventory;
         if (!inventoryplayer.getItemStack().isEmpty()) {
            if (mouseButton == 0) {
               var10000 = this.mc;
               nC.player.dropItem(inventoryplayer.getItemStack(), true);
               this.mc.playerController.sendPacketDropItem(inventoryplayer.getItemStack());
               inventoryplayer.setItemStack(Qy.EMPTY);
            }

            if (mouseButton == 1) {
               itemstack5 = inventoryplayer.getItemStack().splitStack(1);
               var10000 = this.mc;
               nC.player.dropItem(itemstack5, true);
               this.mc.playerController.sendPacketDropItem(itemstack5);
            }
         }
      } else {
         nC var10001;
         if (slotIn != null) {
            var10001 = this.mc;
            if (!slotIn.canTakeStack(nC.player)) {
               return;
            }
         }

         if (slotIn == this.destroyItemSlot && flag) {
            int j = 0;

            while(true) {
               var10001 = this.mc;
               if (j >= nC.player.inventoryContainer.getInventory().size()) {
                  break;
               }

               this.mc.playerController.sendSlotPacket(Qy.EMPTY, j);
               ++j;
            }
         } else {
            Qy itemstack3;
            int var14;
            nC var10004;
            if (selectedTabIndex == EN.INVENTORY.getIndex()) {
               if (slotIn == this.destroyItemSlot) {
                  var10000 = this.mc;
                  nC.player.inventory.setItemStack(Qy.EMPTY);
               } else if (type == ClickType.THROW && slotIn != null && slotIn.getHasStack()) {
                  itemstack3 = slotIn.decrStackSize(mouseButton == 0 ? 1 : slotIn.getStack().getMaxStackSize());
                  itemstack5 = slotIn.getStack();
                  var10000 = this.mc;
                  nC.player.dropItem(itemstack3, true);
                  this.mc.playerController.sendPacketDropItem(itemstack3);
                  this.mc.playerController.sendSlotPacket(itemstack5, lW.access$000((lW)slotIn).slotNumber);
               } else {
                  if (type == ClickType.THROW) {
                     var10000 = this.mc;
                     if (!nC.player.inventory.getItemStack().isEmpty()) {
                        var10000 = this.mc;
                        var10001 = this.mc;
                        nC.player.dropItem(nC.player.inventory.getItemStack(), true);
                        var10001 = this.mc;
                        this.mc.playerController.sendPacketDropItem(nC.player.inventory.getItemStack());
                        var10000 = this.mc;
                        nC.player.inventory.setItemStack(Qy.EMPTY);
                        return;
                     }
                  }

                  var10000 = this.mc;
                  var14 = slotIn == null ? slotId : lW.access$000((lW)slotIn).slotNumber;
                  var10004 = this.mc;
                  nC.player.inventoryContainer.slotClick(var14, mouseButton, type, nC.player);
                  var10000 = this.mc;
                  nC.player.inventoryContainer.detectAndSendChanges();
               }
            } else {
               Qy itemstack2;
               if (type != ClickType.QUICK_CRAFT && slotIn.inventory == basicInventory) {
                  var10000 = this.mc;
                  inventoryplayer = nC.player.inventory;
                  itemstack5 = inventoryplayer.getItemStack();
                  Qy itemstack7 = slotIn.getStack();
                  if (type == ClickType.SWAP) {
                     if (!itemstack7.isEmpty() && mouseButton >= 0 && mouseButton < 9) {
                        itemstack2 = itemstack7.copy();
                        itemstack2.setCount(itemstack2.getMaxStackSize());
                        var10000 = this.mc;
                        nC.player.inventory.setInventorySlotContents(mouseButton, itemstack2);
                        var10000 = this.mc;
                        nC.player.inventoryContainer.detectAndSendChanges();
                     }

                     return;
                  }

                  if (type == ClickType.CLONE) {
                     if (inventoryplayer.getItemStack().isEmpty() && slotIn.getHasStack()) {
                        itemstack2 = slotIn.getStack().copy();
                        itemstack2.setCount(itemstack2.getMaxStackSize());
                        inventoryplayer.setItemStack(itemstack2);
                     }

                     return;
                  }

                  if (type == ClickType.THROW) {
                     if (!itemstack7.isEmpty()) {
                        itemstack2 = itemstack7.copy();
                        itemstack2.setCount(mouseButton == 0 ? 1 : itemstack2.getMaxStackSize());
                        var10000 = this.mc;
                        nC.player.dropItem(itemstack2, true);
                        this.mc.playerController.sendPacketDropItem(itemstack2);
                     }

                     return;
                  }

                  if (!itemstack5.isEmpty() && !itemstack7.isEmpty() && itemstack5.isItemEqual(itemstack7) && Qy.areItemStackTagsEqual(itemstack5, itemstack7)) {
                     if (mouseButton == 0) {
                        if (flag) {
                           itemstack5.setCount(itemstack5.getMaxStackSize());
                        } else if (itemstack5.getCount() < itemstack5.getMaxStackSize()) {
                           itemstack5.grow(1);
                        }
                     } else {
                        itemstack5.shrink(1);
                     }
                  } else if (!itemstack7.isEmpty() && itemstack5.isEmpty()) {
                     inventoryplayer.setItemStack(itemstack7.copy());
                     itemstack5 = inventoryplayer.getItemStack();
                     if (flag) {
                        itemstack5.setCount(itemstack5.getMaxStackSize());
                     }
                  } else if (mouseButton == 0) {
                     inventoryplayer.setItemStack(Qy.EMPTY);
                  } else {
                     inventoryplayer.getItemStack().shrink(1);
                  }
               } else if (this.inventorySlots != null) {
                  itemstack3 = slotIn == null ? Qy.EMPTY : this.inventorySlots.getSlot(slotIn.slotNumber).getStack();
                  var14 = slotIn == null ? slotId : slotIn.slotNumber;
                  var10004 = this.mc;
                  this.inventorySlots.slotClick(var14, mouseButton, type, nC.player);
                  if (Container.getDragEvent(mouseButton) == 2) {
                     for(int k = 0; k < 9; ++k) {
                        this.mc.playerController.sendSlotPacket(this.inventorySlots.getSlot(45 + k).getStack(), 36 + k);
                     }
                  } else if (slotIn != null) {
                     itemstack5 = this.inventorySlots.getSlot(slotIn.slotNumber).getStack();
                     this.mc.playerController.sendSlotPacket(itemstack5, slotIn.slotNumber - this.inventorySlots.inventorySlots.size() + 9 + 36);
                     int i = 45 + mouseButton;
                     if (type == ClickType.SWAP) {
                        this.mc.playerController.sendSlotPacket(itemstack3, i - this.inventorySlots.inventorySlots.size() + 9 + 36);
                     } else if (type == ClickType.THROW && !itemstack3.isEmpty()) {
                        itemstack2 = itemstack3.copy();
                        itemstack2.setCount(mouseButton == 0 ? 1 : itemstack2.getMaxStackSize());
                        var10000 = this.mc;
                        nC.player.dropItem(itemstack2, true);
                        this.mc.playerController.sendPacketDropItem(itemstack2);
                     }

                     var10000 = this.mc;
                     nC.player.inventoryContainer.detectAndSendChanges();
                  }
               }
            }
         }
      }

   }

   protected void updateActivePotionEffects() {
      int i = this.guiLeft;
      super.updateActivePotionEffects();
      if (this.searchField != null && this.guiLeft != i) {
         this.searchField.x = this.guiLeft + 82;
      }

   }

   public void initGui() {
      if (this.mc.playerController.isInCreativeMode()) {
         super.initGui();
         this.buttonList.clear();
         Keyboard.enableRepeatEvents(true);
         this.searchField = new lE(0, this.fontRenderer, this.guiLeft + 82, this.guiTop + 6, 80, this.fontRenderer.FONT_HEIGHT);
         this.searchField.setMaxStringLength(50);
         this.searchField.setEnableBackgroundDrawing(false);
         this.searchField.setVisible(false);
         this.searchField.setTextColor(16777215);
         int i = selectedTabIndex;
         selectedTabIndex = -1;
         this.setCurrentCreativeTab(EN.CREATIVE_TAB_ARRAY[i]);
         this.listener = new lM(this.mc);
         nC var10000 = this.mc;
         nC.player.inventoryContainer.addListener(this.listener);
      } else {
         nC var10003 = this.mc;
         this.mc.displayGuiScreen(new mh(nC.player));
      }

   }

   public void onGuiClosed() {
      super.onGuiClosed();
      nC var10000 = this.mc;
      if (nC.player != null) {
         var10000 = this.mc;
         if (nC.player.inventory != null) {
            var10000 = this.mc;
            nC.player.inventoryContainer.removeListener(this.listener);
         }
      }

      Keyboard.enableRepeatEvents(false);
   }

   protected void keyTyped(char typedChar, int keyCode) throws IOException {
      if (selectedTabIndex != EN.SEARCH.getIndex()) {
         nC var10000 = this.mc;
         if (Bj.isKeyDown(nC.gameSettings.keyBindChat)) {
            this.setCurrentCreativeTab(EN.SEARCH);
         } else {
            super.keyTyped(typedChar, keyCode);
         }
      } else {
         if (this.clearSearch) {
            this.clearSearch = false;
            this.searchField.setText("");
         }

         if (!this.checkHotbarKeys(keyCode)) {
            if (this.searchField.textboxKeyTyped(typedChar, keyCode)) {
               this.updateCreativeSearch();
            } else {
               super.keyTyped(typedChar, keyCode);
            }
         }
      }

   }

   private void updateCreativeSearch() {
      lV guicontainercreative$containercreative = (lV)this.inventorySlots;
      guicontainercreative$containercreative.itemList.clear();
      if (this.searchField.getText().isEmpty()) {
         Iterator var2 = OL.REGISTRY.iterator();

         while(var2.hasNext()) {
            OL item = (OL)var2.next();
            item.getSubItems(EN.SEARCH, guicontainercreative$containercreative.itemList);
         }
      } else {
         guicontainercreative$containercreative.itemList.addAll(this.mc.getSearchTree(BV.ITEMS).search(this.searchField.getText().toLowerCase(Locale.ROOT)));
      }

      this.currentScroll = 0.0F;
      guicontainercreative$containercreative.scrollTo(0.0F);
   }

   protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
      EN creativetabs = EN.CREATIVE_TAB_ARRAY[selectedTabIndex];
      if (creativetabs.drawInForegroundOfTab()) {
         yh.disableBlend();
         this.fontRenderer.drawString(Ax.format(creativetabs.getTranslationKey()), 8, 6, 4210752);
      }

   }

   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      if (mouseButton == 0) {
         int i = mouseX - this.guiLeft;
         int j = mouseY - this.guiTop;
         EN[] var6 = EN.CREATIVE_TAB_ARRAY;
         int var7 = var6.length;

         for(int var8 = 0; var8 < var7; ++var8) {
            EN creativetabs = var6[var8];
            if (this.isMouseOverTab(creativetabs, i, j)) {
               return;
            }
         }
      }

      super.mouseClicked(mouseX, mouseY, mouseButton);
   }

   protected void mouseReleased(int mouseX, int mouseY, int state) {
      if (state == 0) {
         int i = mouseX - this.guiLeft;
         int j = mouseY - this.guiTop;
         EN[] var6 = EN.CREATIVE_TAB_ARRAY;
         int var7 = var6.length;

         for(int var8 = 0; var8 < var7; ++var8) {
            EN creativetabs = var6[var8];
            if (this.isMouseOverTab(creativetabs, i, j)) {
               this.setCurrentCreativeTab(creativetabs);
               return;
            }
         }
      }

      super.mouseReleased(mouseX, mouseY, state);
   }

   private boolean needsScrollBars() {
      return selectedTabIndex != EN.INVENTORY.getIndex() && EN.CREATIVE_TAB_ARRAY[selectedTabIndex].hasScrollbar() && ((lV)this.inventorySlots).canScroll();
   }

   private void setCurrentCreativeTab(EN tab) {
      int i = selectedTabIndex;
      selectedTabIndex = tab.getIndex();
      lV guicontainercreative$containercreative = (lV)this.inventorySlots;
      this.dragSplittingSlots.clear();
      guicontainercreative$containercreative.itemList.clear();
      nC var10000;
      if (tab == EN.HOTBAR) {
         for(int j = 0; j < 9; ++j) {
            Bk hotbarsnapshot = this.mc.creativeSettings.getHotbarSnapshot(j);
            if (hotbarsnapshot.isEmpty()) {
               for(int k = 0; k < 9; ++k) {
                  if (k == j) {
                     Qy itemstack = new Qy(NK.PAPER);
                     itemstack.getOrCreateSubCompound("CustomCreativeLock");
                     var10000 = this.mc;
                     String s = Bj.getKeyDisplayString(nC.gameSettings.keyBindsHotbar[j].getKeyCode());
                     var10000 = this.mc;
                     String s1 = Bj.getKeyDisplayString(nC.gameSettings.keyBindSaveToolbar.getKeyCode());
                     itemstack.setStackDisplayName((new TextComponentTranslation("inventory.hotbarInfo", new Object[]{s1, s})).getUnformattedText());
                     guicontainercreative$containercreative.itemList.add(itemstack);
                  } else {
                     guicontainercreative$containercreative.itemList.add(Qy.EMPTY);
                  }
               }
            } else {
               guicontainercreative$containercreative.itemList.addAll(hotbarsnapshot);
            }
         }
      } else if (tab != EN.SEARCH) {
         tab.displayAllRelevantItems(guicontainercreative$containercreative.itemList);
      }

      if (tab == EN.INVENTORY) {
         var10000 = this.mc;
         Container container = nC.player.inventoryContainer;
         if (this.originalSlots == null) {
            this.originalSlots = guicontainercreative$containercreative.inventorySlots;
         }

         guicontainercreative$containercreative.inventorySlots = Lists.newArrayList();

         for(int l = 0; l < container.inventorySlots.size(); ++l) {
            Slot slot = new lW(this, (Slot)container.inventorySlots.get(l), l);
            guicontainercreative$containercreative.inventorySlots.add(slot);
            int i1;
            int k1;
            int i2;
            if (l >= 5 && l < 9) {
               i1 = l - 5;
               k1 = i1 / 2;
               i2 = i1 % 2;
               slot.xPos = 54 + k1 * 54;
               slot.yPos = 6 + i2 * 27;
            } else if (l >= 0 && l < 5) {
               slot.xPos = -2000;
               slot.yPos = -2000;
            } else if (l == 45) {
               slot.xPos = 35;
               slot.yPos = 20;
            } else if (l < container.inventorySlots.size()) {
               i1 = l - 9;
               k1 = i1 % 9;
               i2 = i1 / 9;
               slot.xPos = 9 + k1 * 18;
               if (l >= 36) {
                  slot.yPos = 112;
               } else {
                  slot.yPos = 54 + i2 * 18;
               }
            }
         }

         this.destroyItemSlot = new Slot(basicInventory, 0, 173, 112);
         guicontainercreative$containercreative.inventorySlots.add(this.destroyItemSlot);
      } else if (i == EN.INVENTORY.getIndex()) {
         guicontainercreative$containercreative.inventorySlots = this.originalSlots;
         this.originalSlots = null;
      }

      if (this.searchField != null) {
         if (tab == EN.SEARCH) {
            this.searchField.setVisible(true);
            this.searchField.setCanLoseFocus(false);
            this.searchField.setFocused(true);
            this.searchField.setText("");
            this.updateCreativeSearch();
         } else {
            this.searchField.setVisible(false);
            this.searchField.setCanLoseFocus(true);
            this.searchField.setFocused(false);
         }
      }

      this.currentScroll = 0.0F;
      guicontainercreative$containercreative.scrollTo(0.0F);
   }

   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      int i = Mouse.getEventDWheel();
      if (i != 0 && this.needsScrollBars()) {
         int j = (((lV)this.inventorySlots).itemList.size() + 9 - 1) / 9 - 5;
         if (i > 0) {
            i = 1;
         }

         if (i < 0) {
            i = -1;
         }

         this.currentScroll = (float)((double)this.currentScroll - (double)i / (double)j);
         this.currentScroll = MathHelper.clamp(this.currentScroll, 0.0F, 1.0F);
         ((lV)this.inventorySlots).scrollTo(this.currentScroll);
      }

   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      boolean flag = Mouse.isButtonDown(0);
      int i = this.guiLeft;
      int j = this.guiTop;
      int k = i + 175;
      int l = j + 18;
      int i1 = k + 14;
      int j1 = l + 112;
      if (!this.wasClicking && flag && mouseX >= k && mouseY >= l && mouseX < i1 && mouseY < j1) {
         this.isScrolling = this.needsScrollBars();
      }

      if (!flag) {
         this.isScrolling = false;
      }

      this.wasClicking = flag;
      if (this.isScrolling) {
         this.currentScroll = ((float)(mouseY - l) - 7.5F) / ((float)(j1 - l) - 15.0F);
         this.currentScroll = MathHelper.clamp(this.currentScroll, 0.0F, 1.0F);
         ((lV)this.inventorySlots).scrollTo(this.currentScroll);
      }

      super.drawScreen(mouseX, mouseY, partialTicks);
      EN[] var11 = EN.CREATIVE_TAB_ARRAY;
      int var12 = var11.length;

      for(int var13 = 0; var13 < var12; ++var13) {
         EN creativetabs = var11[var13];
         if (this.renderCreativeInventoryHoveringText(creativetabs, mouseX, mouseY)) {
            break;
         }
      }

      if (this.destroyItemSlot != null && selectedTabIndex == EN.INVENTORY.getIndex() && this.isPointInRegion(this.destroyItemSlot.xPos, this.destroyItemSlot.yPos, 16, 16, mouseX, mouseY)) {
         this.drawHoveringText(Ax.format("inventory.binSlot"), mouseX, mouseY);
      }

      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      yh.disableLighting();
      this.renderHoveredToolTip(mouseX, mouseY);
   }

   protected void renderToolTip(Qy stack, int x, int y) {
      if (selectedTabIndex == EN.SEARCH.getIndex()) {
         nC var10001 = this.mc;
         nC var10002 = this.mc;
         List<String> list = stack.getTooltip(nC.player, nC.gameSettings.advancedItemTooltips ? BI.ADVANCED : BI.NORMAL);
         EN creativetabs = stack.getItem().getCreativeTab();
         if (creativetabs == null && stack.getItem() == NK.ENCHANTED_BOOK) {
            Map<Fa, Integer> map = Ft.getEnchantments(stack);
            if (map.size() == 1) {
               Fa enchantment = (Fa)map.keySet().iterator().next();
               EN[] var8 = EN.CREATIVE_TAB_ARRAY;
               int var9 = var8.length;

               for(int var10 = 0; var10 < var9; ++var10) {
                  EN creativetabs1 = var8[var10];
                  if (creativetabs1.hasRelevantEnchantmentType(enchantment.type)) {
                     creativetabs = creativetabs1;
                     break;
                  }
               }
            }
         }

         if (creativetabs != null) {
            list.add(1, "" + TextFormatting.BOLD + TextFormatting.BLUE + Ax.format(creativetabs.getTranslationKey()));
         }

         for(int i = 0; i < list.size(); ++i) {
            if (i == 0) {
               list.set(i, stack.getRarity().color + (String)list.get(i));
            } else {
               list.set(i, TextFormatting.GRAY + (String)list.get(i));
            }
         }

         this.drawHoveringText(list, x, y);
      } else {
         super.renderToolTip(stack, x, y);
      }

   }

   protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      yz.enableGUIStandardItemLighting();
      EN creativetabs = EN.CREATIVE_TAB_ARRAY[selectedTabIndex];
      EN[] var5 = EN.CREATIVE_TAB_ARRAY;
      int j = var5.length;

      int k;
      for(k = 0; k < j; ++k) {
         EN creativetabs1 = var5[k];
         this.mc.getTextureManager().bindTexture(CREATIVE_INVENTORY_TABS);
         if (creativetabs1.getIndex() != selectedTabIndex) {
            this.drawTab(creativetabs1);
         }
      }

      this.mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/creative_inventory/tab_" + creativetabs.getBackgroundImageName()));
      this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
      this.searchField.drawTextBox();
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      int i = this.guiLeft + 175;
      j = this.guiTop + 18;
      k = j + 112;
      this.mc.getTextureManager().bindTexture(CREATIVE_INVENTORY_TABS);
      if (creativetabs.hasScrollbar()) {
         this.drawTexturedModalRect(i, j + (int)((float)(k - j - 17) * this.currentScroll), 232 + (this.needsScrollBars() ? 0 : 12), 0, 12, 15);
      }

      this.drawTab(creativetabs);
      if (creativetabs == EN.INVENTORY) {
         int var10000 = this.guiLeft + 88;
         int var10001 = this.guiTop + 45;
         float var10003 = (float)(this.guiLeft + 88 - mouseX);
         float var10004 = (float)(this.guiTop + 45 - 30 - mouseY);
         nC var10005 = this.mc;
         mh.drawEntityOnScreen(var10000, var10001, 20, var10003, var10004, nC.player);
      }

   }

   protected boolean isMouseOverTab(EN tab, int mouseX, int mouseY) {
      int i = tab.getColumn();
      int j = 28 * i;
      int k = 0;
      if (tab.isAlignedRight()) {
         j = this.xSize - 28 * (6 - i) + 2;
      } else if (i > 0) {
         j += i;
      }

      if (tab.isOnTopRow()) {
         k -= 32;
      } else {
         k += this.ySize;
      }

      return mouseX >= j && mouseX <= j + 28 && mouseY >= k && mouseY <= k + 32;
   }

   protected boolean renderCreativeInventoryHoveringText(EN tab, int mouseX, int mouseY) {
      int i = tab.getColumn();
      int j = 28 * i;
      int k = 0;
      if (tab.isAlignedRight()) {
         j = this.xSize - 28 * (6 - i) + 2;
      } else if (i > 0) {
         j += i;
      }

      if (tab.isOnTopRow()) {
         k -= 32;
      } else {
         k += this.ySize;
      }

      if (this.isPointInRegion(j + 3, k + 3, 23, 27, mouseX, mouseY)) {
         this.drawHoveringText(Ax.format(tab.getTranslationKey()), mouseX, mouseY);
         return true;
      } else {
         return false;
      }
   }

   protected void drawTab(EN tab) {
      boolean flag = tab.getIndex() == selectedTabIndex;
      boolean flag1 = tab.isOnTopRow();
      int i = tab.getColumn();
      int j = i * 28;
      int k = 0;
      int l = this.guiLeft + 28 * i;
      int i1 = this.guiTop;
      int j1 = true;
      if (flag) {
         k += 32;
      }

      if (tab.isAlignedRight()) {
         l = this.guiLeft + this.xSize - 28 * (6 - i);
      } else if (i > 0) {
         l += i;
      }

      if (flag1) {
         i1 -= 28;
      } else {
         k += 64;
         i1 += this.ySize - 4;
      }

      yh.disableLighting();
      this.drawTexturedModalRect(l, i1, j, k, 28, 32);
      this.zLevel = 100.0F;
      this.itemRender.zLevel = 100.0F;
      l += 6;
      i1 = i1 + 8 + (flag1 ? 1 : -1);
      yh.enableLighting();
      yh.enableRescaleNormal();
      Qy itemstack = tab.getIcon();
      this.itemRender.renderItemAndEffectIntoGUI(itemstack, l, i1);
      this.itemRender.renderItemOverlays(this.fontRenderer, itemstack, l, i1);
      yh.disableLighting();
      this.itemRender.zLevel = 0.0F;
      this.zLevel = 0.0F;
   }

   protected void actionPerformed(jK button) throws IOException {
      if (button.id == 1) {
         nC var10004 = this.mc;
         this.mc.displayGuiScreen(new jq(this, nC.player.getStatFileWriter()));
      }

   }

   public int getSelectedTabIndex() {
      return selectedTabIndex;
   }

   public static void handleHotbarSnapshots(nC client, int index, boolean load, boolean save) {
      jh entityplayersp = nC.player;
      Bf creativesettings = client.creativeSettings;
      Bk hotbarsnapshot = creativesettings.getHotbarSnapshot(index);
      int j;
      if (load) {
         for(j = 0; j < MJ.getHotbarSize(); ++j) {
            Qy itemstack = ((Qy)hotbarsnapshot.get(j)).copy();
            entityplayersp.inventory.setInventorySlotContents(j, itemstack);
            client.playerController.sendSlotPacket(itemstack, 36 + j);
         }

         entityplayersp.inventoryContainer.detectAndSendChanges();
      } else if (save) {
         for(j = 0; j < MJ.getHotbarSize(); ++j) {
            hotbarsnapshot.set(j, entityplayersp.inventory.getStackInSlot(j).copy());
         }

         String s = Bj.getKeyDisplayString(nC.gameSettings.keyBindsHotbar[index].getKeyCode());
         String s1 = Bj.getKeyDisplayString(nC.gameSettings.keyBindLoadToolbar.getKeyCode());
         client.ingameGUI.setOverlayMessage((ITextComponent)(new TextComponentTranslation("inventory.hotbarSaved", new Object[]{s1, s})), false);
         creativesettings.write();
      }

   }

   // $FF: synthetic method
   static InventoryBasic access$100() {
      return basicInventory;
   }

   static {
      selectedTabIndex = EN.BUILDING_BLOCKS.getIndex();
   }
}
