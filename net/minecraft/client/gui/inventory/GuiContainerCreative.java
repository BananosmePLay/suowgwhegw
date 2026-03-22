package net.minecraft.client.gui.inventory;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.CreativeSettings;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.HotbarSnapshot;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.SearchTreeManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class GuiContainerCreative extends InventoryEffectRenderer {
   private static final ResourceLocation CREATIVE_INVENTORY_TABS = new ResourceLocation("textures/gui/container/creative_inventory/tabs.png");
   private static final InventoryBasic basicInventory = new InventoryBasic("tmp", true, 45);
   private static int selectedTabIndex;
   private float currentScroll;
   private boolean isScrolling;
   private boolean wasClicking;
   private GuiTextField searchField;
   private List<Slot> originalSlots;
   private Slot destroyItemSlot;
   private boolean clearSearch;
   private CreativeCrafting listener;

   public GuiContainerCreative(EntityPlayer player) {
      super(new ContainerCreative(player));
      player.openContainer = this.inventorySlots;
      this.allowUserInput = true;
      this.ySize = 136;
      this.xSize = 195;
   }

   public void updateScreen() {
      if (!this.mc.playerController.isInCreativeMode()) {
         Minecraft var10003 = this.mc;
         this.mc.displayGuiScreen(new GuiInventory(Minecraft.player));
      }

   }

   protected void handleMouseClick(@Nullable Slot slotIn, int slotId, int mouseButton, ClickType type) {
      this.clearSearch = true;
      boolean flag = type == ClickType.QUICK_MOVE;
      type = slotId == -999 && type == ClickType.PICKUP ? ClickType.THROW : type;
      ItemStack itemstack5;
      InventoryPlayer inventoryplayer;
      Minecraft var10000;
      if (slotIn == null && selectedTabIndex != CreativeTabs.INVENTORY.getIndex() && type != ClickType.QUICK_CRAFT) {
         var10000 = this.mc;
         inventoryplayer = Minecraft.player.inventory;
         if (!inventoryplayer.getItemStack().isEmpty()) {
            if (mouseButton == 0) {
               var10000 = this.mc;
               Minecraft.player.dropItem(inventoryplayer.getItemStack(), true);
               this.mc.playerController.sendPacketDropItem(inventoryplayer.getItemStack());
               inventoryplayer.setItemStack(ItemStack.EMPTY);
            }

            if (mouseButton == 1) {
               itemstack5 = inventoryplayer.getItemStack().splitStack(1);
               var10000 = this.mc;
               Minecraft.player.dropItem(itemstack5, true);
               this.mc.playerController.sendPacketDropItem(itemstack5);
            }
         }
      } else {
         Minecraft var10001;
         if (slotIn != null) {
            var10001 = this.mc;
            if (!slotIn.canTakeStack(Minecraft.player)) {
               return;
            }
         }

         if (slotIn == this.destroyItemSlot && flag) {
            int j = 0;

            while(true) {
               var10001 = this.mc;
               if (j >= Minecraft.player.inventoryContainer.getInventory().size()) {
                  break;
               }

               this.mc.playerController.sendSlotPacket(ItemStack.EMPTY, j);
               ++j;
            }
         } else {
            ItemStack itemstack3;
            int var14;
            Minecraft var10004;
            if (selectedTabIndex == CreativeTabs.INVENTORY.getIndex()) {
               if (slotIn == this.destroyItemSlot) {
                  var10000 = this.mc;
                  Minecraft.player.inventory.setItemStack(ItemStack.EMPTY);
               } else if (type == ClickType.THROW && slotIn != null && slotIn.getHasStack()) {
                  itemstack3 = slotIn.decrStackSize(mouseButton == 0 ? 1 : slotIn.getStack().getMaxStackSize());
                  itemstack5 = slotIn.getStack();
                  var10000 = this.mc;
                  Minecraft.player.dropItem(itemstack3, true);
                  this.mc.playerController.sendPacketDropItem(itemstack3);
                  this.mc.playerController.sendSlotPacket(itemstack5, ((CreativeSlot)slotIn).slot.slotNumber);
               } else {
                  if (type == ClickType.THROW) {
                     var10000 = this.mc;
                     if (!Minecraft.player.inventory.getItemStack().isEmpty()) {
                        var10000 = this.mc;
                        var10001 = this.mc;
                        Minecraft.player.dropItem(Minecraft.player.inventory.getItemStack(), true);
                        var10001 = this.mc;
                        this.mc.playerController.sendPacketDropItem(Minecraft.player.inventory.getItemStack());
                        var10000 = this.mc;
                        Minecraft.player.inventory.setItemStack(ItemStack.EMPTY);
                        return;
                     }
                  }

                  var10000 = this.mc;
                  var14 = slotIn == null ? slotId : ((CreativeSlot)slotIn).slot.slotNumber;
                  var10004 = this.mc;
                  Minecraft.player.inventoryContainer.slotClick(var14, mouseButton, type, Minecraft.player);
                  var10000 = this.mc;
                  Minecraft.player.inventoryContainer.detectAndSendChanges();
               }
            } else {
               ItemStack itemstack2;
               if (type != ClickType.QUICK_CRAFT && slotIn.inventory == basicInventory) {
                  var10000 = this.mc;
                  inventoryplayer = Minecraft.player.inventory;
                  itemstack5 = inventoryplayer.getItemStack();
                  ItemStack itemstack7 = slotIn.getStack();
                  if (type == ClickType.SWAP) {
                     if (!itemstack7.isEmpty() && mouseButton >= 0 && mouseButton < 9) {
                        itemstack2 = itemstack7.copy();
                        itemstack2.setCount(itemstack2.getMaxStackSize());
                        var10000 = this.mc;
                        Minecraft.player.inventory.setInventorySlotContents(mouseButton, itemstack2);
                        var10000 = this.mc;
                        Minecraft.player.inventoryContainer.detectAndSendChanges();
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
                        Minecraft.player.dropItem(itemstack2, true);
                        this.mc.playerController.sendPacketDropItem(itemstack2);
                     }

                     return;
                  }

                  if (!itemstack5.isEmpty() && !itemstack7.isEmpty() && itemstack5.isItemEqual(itemstack7) && ItemStack.areItemStackTagsEqual(itemstack5, itemstack7)) {
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
                     inventoryplayer.setItemStack(ItemStack.EMPTY);
                  } else {
                     inventoryplayer.getItemStack().shrink(1);
                  }
               } else if (this.inventorySlots != null) {
                  itemstack3 = slotIn == null ? ItemStack.EMPTY : this.inventorySlots.getSlot(slotIn.slotNumber).getStack();
                  var14 = slotIn == null ? slotId : slotIn.slotNumber;
                  var10004 = this.mc;
                  this.inventorySlots.slotClick(var14, mouseButton, type, Minecraft.player);
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
                        Minecraft.player.dropItem(itemstack2, true);
                        this.mc.playerController.sendPacketDropItem(itemstack2);
                     }

                     var10000 = this.mc;
                     Minecraft.player.inventoryContainer.detectAndSendChanges();
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
         this.searchField = new GuiTextField(0, this.fontRenderer, this.guiLeft + 82, this.guiTop + 6, 80, this.fontRenderer.FONT_HEIGHT);
         this.searchField.setMaxStringLength(50);
         this.searchField.setEnableBackgroundDrawing(false);
         this.searchField.setVisible(false);
         this.searchField.setTextColor(16777215);
         int i = selectedTabIndex;
         selectedTabIndex = -1;
         this.setCurrentCreativeTab(CreativeTabs.CREATIVE_TAB_ARRAY[i]);
         this.listener = new CreativeCrafting(this.mc);
         Minecraft var10000 = this.mc;
         Minecraft.player.inventoryContainer.addListener(this.listener);
      } else {
         Minecraft var10003 = this.mc;
         this.mc.displayGuiScreen(new GuiInventory(Minecraft.player));
      }

   }

   public void onGuiClosed() {
      super.onGuiClosed();
      Minecraft var10000 = this.mc;
      if (Minecraft.player != null) {
         var10000 = this.mc;
         if (Minecraft.player.inventory != null) {
            var10000 = this.mc;
            Minecraft.player.inventoryContainer.removeListener(this.listener);
         }
      }

      Keyboard.enableRepeatEvents(false);
   }

   protected void keyTyped(char typedChar, int keyCode) throws IOException {
      if (selectedTabIndex != CreativeTabs.SEARCH.getIndex()) {
         Minecraft var10000 = this.mc;
         if (GameSettings.isKeyDown(Minecraft.gameSettings.keyBindChat)) {
            this.setCurrentCreativeTab(CreativeTabs.SEARCH);
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
      ContainerCreative guicontainercreative$containercreative = (ContainerCreative)this.inventorySlots;
      guicontainercreative$containercreative.itemList.clear();
      if (this.searchField.getText().isEmpty()) {
         Iterator var2 = Item.REGISTRY.iterator();

         while(var2.hasNext()) {
            Item item = (Item)var2.next();
            item.getSubItems(CreativeTabs.SEARCH, guicontainercreative$containercreative.itemList);
         }
      } else {
         guicontainercreative$containercreative.itemList.addAll(this.mc.getSearchTree(SearchTreeManager.ITEMS).search(this.searchField.getText().toLowerCase(Locale.ROOT)));
      }

      this.currentScroll = 0.0F;
      guicontainercreative$containercreative.scrollTo(0.0F);
   }

   protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
      CreativeTabs creativetabs = CreativeTabs.CREATIVE_TAB_ARRAY[selectedTabIndex];
      if (creativetabs.drawInForegroundOfTab()) {
         GlStateManager.disableBlend();
         this.fontRenderer.drawString(I18n.format(creativetabs.getTranslationKey()), 8, 6, 4210752);
      }

   }

   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      if (mouseButton == 0) {
         int i = mouseX - this.guiLeft;
         int j = mouseY - this.guiTop;
         CreativeTabs[] var6 = CreativeTabs.CREATIVE_TAB_ARRAY;
         int var7 = var6.length;

         for(int var8 = 0; var8 < var7; ++var8) {
            CreativeTabs creativetabs = var6[var8];
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
         CreativeTabs[] var6 = CreativeTabs.CREATIVE_TAB_ARRAY;
         int var7 = var6.length;

         for(int var8 = 0; var8 < var7; ++var8) {
            CreativeTabs creativetabs = var6[var8];
            if (this.isMouseOverTab(creativetabs, i, j)) {
               this.setCurrentCreativeTab(creativetabs);
               return;
            }
         }
      }

      super.mouseReleased(mouseX, mouseY, state);
   }

   private boolean needsScrollBars() {
      return selectedTabIndex != CreativeTabs.INVENTORY.getIndex() && CreativeTabs.CREATIVE_TAB_ARRAY[selectedTabIndex].hasScrollbar() && ((ContainerCreative)this.inventorySlots).canScroll();
   }

   private void setCurrentCreativeTab(CreativeTabs tab) {
      int i = selectedTabIndex;
      selectedTabIndex = tab.getIndex();
      ContainerCreative guicontainercreative$containercreative = (ContainerCreative)this.inventorySlots;
      this.dragSplittingSlots.clear();
      guicontainercreative$containercreative.itemList.clear();
      Minecraft var10000;
      if (tab == CreativeTabs.HOTBAR) {
         for(int j = 0; j < 9; ++j) {
            HotbarSnapshot hotbarsnapshot = this.mc.creativeSettings.getHotbarSnapshot(j);
            if (hotbarsnapshot.isEmpty()) {
               for(int k = 0; k < 9; ++k) {
                  if (k == j) {
                     ItemStack itemstack = new ItemStack(Items.PAPER);
                     itemstack.getOrCreateSubCompound("CustomCreativeLock");
                     var10000 = this.mc;
                     String s = GameSettings.getKeyDisplayString(Minecraft.gameSettings.keyBindsHotbar[j].getKeyCode());
                     var10000 = this.mc;
                     String s1 = GameSettings.getKeyDisplayString(Minecraft.gameSettings.keyBindSaveToolbar.getKeyCode());
                     itemstack.setStackDisplayName((new TextComponentTranslation("inventory.hotbarInfo", new Object[]{s1, s})).getUnformattedText());
                     guicontainercreative$containercreative.itemList.add(itemstack);
                  } else {
                     guicontainercreative$containercreative.itemList.add(ItemStack.EMPTY);
                  }
               }
            } else {
               guicontainercreative$containercreative.itemList.addAll(hotbarsnapshot);
            }
         }
      } else if (tab != CreativeTabs.SEARCH) {
         tab.displayAllRelevantItems(guicontainercreative$containercreative.itemList);
      }

      if (tab == CreativeTabs.INVENTORY) {
         var10000 = this.mc;
         Container container = Minecraft.player.inventoryContainer;
         if (this.originalSlots == null) {
            this.originalSlots = guicontainercreative$containercreative.inventorySlots;
         }

         guicontainercreative$containercreative.inventorySlots = Lists.newArrayList();

         for(int l = 0; l < container.inventorySlots.size(); ++l) {
            Slot slot = new CreativeSlot((Slot)container.inventorySlots.get(l), l);
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
      } else if (i == CreativeTabs.INVENTORY.getIndex()) {
         guicontainercreative$containercreative.inventorySlots = this.originalSlots;
         this.originalSlots = null;
      }

      if (this.searchField != null) {
         if (tab == CreativeTabs.SEARCH) {
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
         int j = (((ContainerCreative)this.inventorySlots).itemList.size() + 9 - 1) / 9 - 5;
         if (i > 0) {
            i = 1;
         }

         if (i < 0) {
            i = -1;
         }

         this.currentScroll = (float)((double)this.currentScroll - (double)i / (double)j);
         this.currentScroll = MathHelper.clamp(this.currentScroll, 0.0F, 1.0F);
         ((ContainerCreative)this.inventorySlots).scrollTo(this.currentScroll);
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
         ((ContainerCreative)this.inventorySlots).scrollTo(this.currentScroll);
      }

      super.drawScreen(mouseX, mouseY, partialTicks);
      CreativeTabs[] var11 = CreativeTabs.CREATIVE_TAB_ARRAY;
      int var12 = var11.length;

      for(int var13 = 0; var13 < var12; ++var13) {
         CreativeTabs creativetabs = var11[var13];
         if (this.renderCreativeInventoryHoveringText(creativetabs, mouseX, mouseY)) {
            break;
         }
      }

      if (this.destroyItemSlot != null && selectedTabIndex == CreativeTabs.INVENTORY.getIndex() && this.isPointInRegion(this.destroyItemSlot.xPos, this.destroyItemSlot.yPos, 16, 16, mouseX, mouseY)) {
         this.drawHoveringText(I18n.format("inventory.binSlot"), mouseX, mouseY);
      }

      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.disableLighting();
      this.renderHoveredToolTip(mouseX, mouseY);
   }

   protected void renderToolTip(ItemStack stack, int x, int y) {
      if (selectedTabIndex == CreativeTabs.SEARCH.getIndex()) {
         Minecraft var10001 = this.mc;
         Minecraft var10002 = this.mc;
         List<String> list = stack.getTooltip(Minecraft.player, Minecraft.gameSettings.advancedItemTooltips ? ITooltipFlag.TooltipFlags.ADVANCED : ITooltipFlag.TooltipFlags.NORMAL);
         CreativeTabs creativetabs = stack.getItem().getCreativeTab();
         if (creativetabs == null && stack.getItem() == Items.ENCHANTED_BOOK) {
            Map<Enchantment, Integer> map = EnchantmentHelper.getEnchantments(stack);
            if (map.size() == 1) {
               Enchantment enchantment = (Enchantment)map.keySet().iterator().next();
               CreativeTabs[] var8 = CreativeTabs.CREATIVE_TAB_ARRAY;
               int var9 = var8.length;

               for(int var10 = 0; var10 < var9; ++var10) {
                  CreativeTabs creativetabs1 = var8[var10];
                  if (creativetabs1.hasRelevantEnchantmentType(enchantment.type)) {
                     creativetabs = creativetabs1;
                     break;
                  }
               }
            }
         }

         if (creativetabs != null) {
            list.add(1, "" + TextFormatting.BOLD + TextFormatting.BLUE + I18n.format(creativetabs.getTranslationKey()));
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
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      RenderHelper.enableGUIStandardItemLighting();
      CreativeTabs creativetabs = CreativeTabs.CREATIVE_TAB_ARRAY[selectedTabIndex];
      CreativeTabs[] var5 = CreativeTabs.CREATIVE_TAB_ARRAY;
      int j = var5.length;

      int k;
      for(k = 0; k < j; ++k) {
         CreativeTabs creativetabs1 = var5[k];
         this.mc.getTextureManager().bindTexture(CREATIVE_INVENTORY_TABS);
         if (creativetabs1.getIndex() != selectedTabIndex) {
            this.drawTab(creativetabs1);
         }
      }

      this.mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/creative_inventory/tab_" + creativetabs.getBackgroundImageName()));
      this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
      this.searchField.drawTextBox();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      int i = this.guiLeft + 175;
      j = this.guiTop + 18;
      k = j + 112;
      this.mc.getTextureManager().bindTexture(CREATIVE_INVENTORY_TABS);
      if (creativetabs.hasScrollbar()) {
         this.drawTexturedModalRect(i, j + (int)((float)(k - j - 17) * this.currentScroll), 232 + (this.needsScrollBars() ? 0 : 12), 0, 12, 15);
      }

      this.drawTab(creativetabs);
      if (creativetabs == CreativeTabs.INVENTORY) {
         int var10000 = this.guiLeft + 88;
         int var10001 = this.guiTop + 45;
         float var10003 = (float)(this.guiLeft + 88 - mouseX);
         float var10004 = (float)(this.guiTop + 45 - 30 - mouseY);
         Minecraft var10005 = this.mc;
         GuiInventory.drawEntityOnScreen(var10000, var10001, 20, var10003, var10004, Minecraft.player);
      }

   }

   protected boolean isMouseOverTab(CreativeTabs tab, int mouseX, int mouseY) {
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

   protected boolean renderCreativeInventoryHoveringText(CreativeTabs tab, int mouseX, int mouseY) {
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
         this.drawHoveringText(I18n.format(tab.getTranslationKey()), mouseX, mouseY);
         return true;
      } else {
         return false;
      }
   }

   protected void drawTab(CreativeTabs tab) {
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

      GlStateManager.disableLighting();
      this.drawTexturedModalRect(l, i1, j, k, 28, 32);
      this.zLevel = 100.0F;
      this.itemRender.zLevel = 100.0F;
      l += 6;
      i1 = i1 + 8 + (flag1 ? 1 : -1);
      GlStateManager.enableLighting();
      GlStateManager.enableRescaleNormal();
      ItemStack itemstack = tab.getIcon();
      this.itemRender.renderItemAndEffectIntoGUI(itemstack, l, i1);
      this.itemRender.renderItemOverlays(this.fontRenderer, itemstack, l, i1);
      GlStateManager.disableLighting();
      this.itemRender.zLevel = 0.0F;
      this.zLevel = 0.0F;
   }

   protected void actionPerformed(GuiButton button) throws IOException {
      if (button.id == 1) {
         Minecraft var10004 = this.mc;
         this.mc.displayGuiScreen(new GuiStats(this, Minecraft.player.getStatFileWriter()));
      }

   }

   public int getSelectedTabIndex() {
      return selectedTabIndex;
   }

   public static void handleHotbarSnapshots(Minecraft client, int index, boolean load, boolean save) {
      EntityPlayerSP entityplayersp = Minecraft.player;
      CreativeSettings creativesettings = client.creativeSettings;
      HotbarSnapshot hotbarsnapshot = creativesettings.getHotbarSnapshot(index);
      int j;
      if (load) {
         for(j = 0; j < InventoryPlayer.getHotbarSize(); ++j) {
            ItemStack itemstack = ((ItemStack)hotbarsnapshot.get(j)).copy();
            entityplayersp.inventory.setInventorySlotContents(j, itemstack);
            client.playerController.sendSlotPacket(itemstack, 36 + j);
         }

         entityplayersp.inventoryContainer.detectAndSendChanges();
      } else if (save) {
         for(j = 0; j < InventoryPlayer.getHotbarSize(); ++j) {
            hotbarsnapshot.set(j, entityplayersp.inventory.getStackInSlot(j).copy());
         }

         String s = GameSettings.getKeyDisplayString(Minecraft.gameSettings.keyBindsHotbar[index].getKeyCode());
         String s1 = GameSettings.getKeyDisplayString(Minecraft.gameSettings.keyBindLoadToolbar.getKeyCode());
         client.ingameGUI.setOverlayMessage((ITextComponent)(new TextComponentTranslation("inventory.hotbarSaved", new Object[]{s1, s})), false);
         creativesettings.write();
      }

   }

   static {
      selectedTabIndex = CreativeTabs.BUILDING_BLOCKS.getIndex();
   }

   static class LockedSlot extends Slot {
      public LockedSlot(IInventory p_i47453_1_, int p_i47453_2_, int p_i47453_3_, int p_i47453_4_) {
         super(p_i47453_1_, p_i47453_2_, p_i47453_3_, p_i47453_4_);
      }

      public boolean canTakeStack(EntityPlayer playerIn) {
         if (super.canTakeStack(playerIn) && this.getHasStack()) {
            return this.getStack().getSubCompound("CustomCreativeLock") == null;
         } else {
            return !this.getHasStack();
         }
      }
   }

   class CreativeSlot extends Slot {
      private final Slot slot;

      public CreativeSlot(Slot p_i46313_2_, int index) {
         super(p_i46313_2_.inventory, index, 0, 0);
         this.slot = p_i46313_2_;
      }

      public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack) {
         this.slot.onTake(thePlayer, stack);
         return stack;
      }

      public boolean isItemValid(ItemStack stack) {
         return this.slot.isItemValid(stack);
      }

      public ItemStack getStack() {
         return this.slot.getStack();
      }

      public boolean getHasStack() {
         return this.slot.getHasStack();
      }

      public void putStack(ItemStack stack) {
         this.slot.putStack(stack);
      }

      public void onSlotChanged() {
         this.slot.onSlotChanged();
      }

      public int getSlotStackLimit() {
         return this.slot.getSlotStackLimit();
      }

      public int getItemStackLimit(ItemStack stack) {
         return this.slot.getItemStackLimit(stack);
      }

      @Nullable
      public String getSlotTexture() {
         return this.slot.getSlotTexture();
      }

      public ItemStack decrStackSize(int amount) {
         return this.slot.decrStackSize(amount);
      }

      public boolean isHere(IInventory inv, int slotIn) {
         return this.slot.isHere(inv, slotIn);
      }

      public boolean isEnabled() {
         return this.slot.isEnabled();
      }

      public boolean canTakeStack(EntityPlayer playerIn) {
         return this.slot.canTakeStack(playerIn);
      }
   }

   public static class ContainerCreative extends Container {
      public NonNullList<ItemStack> itemList = NonNullList.create();

      public ContainerCreative(EntityPlayer player) {
         InventoryPlayer inventoryplayer = player.inventory;

         int k;
         for(k = 0; k < 5; ++k) {
            for(int j = 0; j < 9; ++j) {
               this.addSlotToContainer(new LockedSlot(GuiContainerCreative.basicInventory, k * 9 + j, 9 + j * 18, 18 + k * 18));
            }
         }

         for(k = 0; k < 9; ++k) {
            this.addSlotToContainer(new Slot(inventoryplayer, k, 9 + k * 18, 112));
         }

         this.scrollTo(0.0F);
      }

      public boolean canInteractWith(EntityPlayer playerIn) {
         return true;
      }

      public void scrollTo(float pos) {
         int i = (this.itemList.size() + 9 - 1) / 9 - 5;
         int j = (int)((double)(pos * (float)i) + 0.5);
         if (j < 0) {
            j = 0;
         }

         for(int k = 0; k < 5; ++k) {
            for(int l = 0; l < 9; ++l) {
               int i1 = l + (k + j) * 9;
               if (i1 >= 0 && i1 < this.itemList.size()) {
                  GuiContainerCreative.basicInventory.setInventorySlotContents(l + k * 9, (ItemStack)this.itemList.get(i1));
               } else {
                  GuiContainerCreative.basicInventory.setInventorySlotContents(l + k * 9, ItemStack.EMPTY);
               }
            }
         }

      }

      public boolean canScroll() {
         return this.itemList.size() > 45;
      }

      public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
         if (index >= this.inventorySlots.size() - 9 && index < this.inventorySlots.size()) {
            Slot slot = (Slot)this.inventorySlots.get(index);
            if (slot != null && slot.getHasStack()) {
               slot.putStack(ItemStack.EMPTY);
            }
         }

         return ItemStack.EMPTY;
      }

      public boolean canMergeSlot(ItemStack stack, Slot slotIn) {
         return slotIn.yPos > 90;
      }

      public boolean canDragIntoSlot(Slot slotIn) {
         return slotIn.inventory instanceof InventoryPlayer || slotIn.yPos > 90 && slotIn.xPos <= 162;
      }
   }
}
