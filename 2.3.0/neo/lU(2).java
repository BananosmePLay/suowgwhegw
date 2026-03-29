package neo;

import com.google.common.collect.Sets;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.input.Keyboard;

public abstract class lU extends lg {
   public static final ResourceLocation INVENTORY_BACKGROUND = new ResourceLocation("textures/gui/container/inventory.png");
   protected int xSize = 176;
   protected int ySize = 166;
   public Container inventorySlots;
   protected int guiLeft;
   protected int guiTop;
   private Slot hoveredSlot;
   private Slot clickedSlot;
   private boolean isRightMouseClick;
   private Qy draggedStack;
   private int touchUpX;
   private int touchUpY;
   private Slot returningStackDestSlot;
   private long returningStackTime;
   private Qy returningStack;
   private Slot currentDragTargetSlot;
   private long dragItemDropDelay;
   protected final Set<Slot> dragSplittingSlots;
   protected boolean dragSplitting;
   private int dragSplittingLimit;
   private int dragSplittingButton;
   private boolean ignoreMouseUp;
   private int dragSplittingRemnant;
   private long lastClickTime;
   private Slot lastClickSlot;
   private int lastClickButton;
   private boolean doubleClick;
   private Qy shiftClickedSlot;

   public lU(Container inventorySlotsIn) {
      this.draggedStack = Qy.EMPTY;
      this.returningStack = Qy.EMPTY;
      this.dragSplittingSlots = Sets.newHashSet();
      this.shiftClickedSlot = Qy.EMPTY;
      this.inventorySlots = inventorySlotsIn;
      this.ignoreMouseUp = true;
   }

   public void initGui() {
      super.initGui();
      nC var10000 = this.mc;
      nC.player.openContainer = this.inventorySlots;
      this.guiLeft = (this.width - this.xSize) / 2;
      this.guiTop = (this.height - this.ySize) / 2;
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      int i = this.guiLeft;
      int j = this.guiTop;
      this.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
      yh.disableRescaleNormal();
      yz.disableStandardItemLighting();
      yh.disableLighting();
      yh.disableDepth();
      super.drawScreen(mouseX, mouseY, partialTicks);
      yz.enableGUIStandardItemLighting();
      yh.pushMatrix();
      yh.translate((float)i, (float)j, 0.0F);
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      yh.enableRescaleNormal();
      this.hoveredSlot = null;
      int k = true;
      int l = true;
      ys.setLightmapTextureCoords(ys.lightmapTexUnit, 240.0F, 240.0F);
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);

      int l2;
      for(int i1 = 0; i1 < this.inventorySlots.inventorySlots.size(); ++i1) {
         Slot slot = (Slot)this.inventorySlots.inventorySlots.get(i1);
         if (slot.isEnabled()) {
            this.drawSlot(slot);
         }

         if (this.isMouseOverSlot(slot, mouseX, mouseY) && slot.isEnabled()) {
            this.hoveredSlot = slot;
            yh.disableLighting();
            yh.disableDepth();
            int j1 = slot.xPos;
            l2 = slot.yPos;
            yh.colorMask(true, true, true, false);
            this.drawGradientRect(j1, l2, j1 + 16, l2 + 16, -2130706433, -2130706433);
            yh.colorMask(true, true, true, true);
            yh.enableLighting();
            yh.enableDepth();
         }
      }

      yz.disableStandardItemLighting();
      this.drawGuiContainerForegroundLayer(mouseX, mouseY);
      yz.enableGUIStandardItemLighting();
      nC var10000 = this.mc;
      MJ inventoryplayer = nC.player.inventory;
      Qy itemstack = this.draggedStack.isEmpty() ? inventoryplayer.getItemStack() : this.draggedStack;
      if (!itemstack.isEmpty()) {
         int j2 = true;
         l2 = this.draggedStack.isEmpty() ? 8 : 16;
         String s = null;
         if (!this.draggedStack.isEmpty() && this.isRightMouseClick) {
            itemstack = itemstack.copy();
            itemstack.setCount(MathHelper.ceil((float)itemstack.getCount() / 2.0F));
         } else if (this.dragSplitting && this.dragSplittingSlots.size() > 1) {
            itemstack = itemstack.copy();
            itemstack.setCount(this.dragSplittingRemnant);
            if (itemstack.isEmpty()) {
               s = "" + TextFormatting.YELLOW + "0";
            }
         }

         this.drawItemStack(itemstack, mouseX - i - 8, mouseY - j - l2, s);
      }

      if (!this.returningStack.isEmpty()) {
         float f = (float)(nC.getSystemTime() - this.returningStackTime) / 100.0F;
         if (f >= 1.0F) {
            f = 1.0F;
            this.returningStack = Qy.EMPTY;
         }

         l2 = this.returningStackDestSlot.xPos - this.touchUpX;
         int i3 = this.returningStackDestSlot.yPos - this.touchUpY;
         int l1 = this.touchUpX + (int)((float)l2 * f);
         int i2 = this.touchUpY + (int)((float)i3 * f);
         this.drawItemStack(this.returningStack, l1, i2, (String)null);
      }

      yh.popMatrix();
      yh.enableLighting();
      yh.enableDepth();
      yz.enableStandardItemLighting();
   }

   protected void renderHoveredToolTip(int p_191948_1_, int p_191948_2_) {
      nC var10000 = this.mc;
      if (nC.player.inventory.getItemStack().isEmpty() && this.hoveredSlot != null && this.hoveredSlot.getHasStack()) {
         this.renderToolTip(this.hoveredSlot.getStack(), p_191948_1_, p_191948_2_);
      }

   }

   private void drawItemStack(Qy stack, int x, int y, String altText) {
      yh.translate(0.0F, 0.0F, 32.0F);
      this.zLevel = 200.0F;
      this.itemRender.zLevel = 200.0F;
      this.itemRender.renderItemAndEffectIntoGUI(stack, x, y);
      this.itemRender.renderItemOverlayIntoGUI(this.fontRenderer, stack, x, y - (this.draggedStack.isEmpty() ? 0 : 8), altText);
      this.zLevel = 0.0F;
      this.itemRender.zLevel = 0.0F;
   }

   protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
   }

   protected abstract void drawGuiContainerBackgroundLayer(float var1, int var2, int var3);

   private void drawSlot(Slot slotIn) {
      int i = slotIn.xPos;
      int j = slotIn.yPos;
      Qy itemstack = slotIn.getStack();
      boolean flag = false;
      boolean flag1 = slotIn == this.clickedSlot && !this.draggedStack.isEmpty() && !this.isRightMouseClick;
      nC var10000 = this.mc;
      Qy itemstack1 = nC.player.inventory.getItemStack();
      String s = null;
      if (slotIn == this.clickedSlot && !this.draggedStack.isEmpty() && this.isRightMouseClick && !itemstack.isEmpty()) {
         itemstack = itemstack.copy();
         itemstack.setCount(itemstack.getCount() / 2);
      } else if (this.dragSplitting && this.dragSplittingSlots.contains(slotIn) && !itemstack1.isEmpty()) {
         if (this.dragSplittingSlots.size() == 1) {
            return;
         }

         if (Container.canAddItemToSlot(slotIn, itemstack1, true) && this.inventorySlots.canDragIntoSlot(slotIn)) {
            itemstack = itemstack1.copy();
            flag = true;
            Container.computeStackSize(this.dragSplittingSlots, this.dragSplittingLimit, itemstack, slotIn.getStack().isEmpty() ? 0 : slotIn.getStack().getCount());
            int k = Math.min(itemstack.getMaxStackSize(), slotIn.getItemStackLimit(itemstack));
            if (itemstack.getCount() > k) {
               s = TextFormatting.YELLOW.toString() + k;
               itemstack.setCount(k);
            }
         } else {
            this.dragSplittingSlots.remove(slotIn);
            this.updateDragSplitting();
         }
      }

      this.zLevel = 100.0F;
      this.itemRender.zLevel = 100.0F;
      if (itemstack.isEmpty() && slotIn.isEnabled()) {
         String s1 = slotIn.getSlotTexture();
         if (s1 != null) {
            zd textureatlassprite = this.mc.getTextureMapBlocks().getAtlasSprite(s1);
            yh.disableLighting();
            this.mc.getTextureManager().bindTexture(zj.LOCATION_BLOCKS_TEXTURE);
            this.drawTexturedModalRect(i, j, textureatlassprite, 16, 16);
            yh.enableLighting();
            flag1 = true;
         }
      }

      if (!flag1) {
         if (flag) {
            drawRect(i, j, i + 16, j + 16, -2130706433);
         }

         yh.enableDepth();
         nC var10001 = this.mc;
         this.itemRender.renderItemAndEffectIntoGUI(nC.player, itemstack, i, j);
         this.itemRender.renderItemOverlayIntoGUI(this.fontRenderer, itemstack, i, j, s);
      }

      this.itemRender.zLevel = 0.0F;
      this.zLevel = 0.0F;
   }

   private void updateDragSplitting() {
      nC var10000 = this.mc;
      Qy itemstack = nC.player.inventory.getItemStack();
      if (!itemstack.isEmpty() && this.dragSplitting) {
         if (this.dragSplittingLimit == 2) {
            this.dragSplittingRemnant = itemstack.getMaxStackSize();
         } else {
            this.dragSplittingRemnant = itemstack.getCount();

            Qy itemstack1;
            int i;
            for(Iterator var2 = this.dragSplittingSlots.iterator(); var2.hasNext(); this.dragSplittingRemnant -= itemstack1.getCount() - i) {
               Slot slot = (Slot)var2.next();
               itemstack1 = itemstack.copy();
               Qy itemstack2 = slot.getStack();
               i = itemstack2.isEmpty() ? 0 : itemstack2.getCount();
               Container.computeStackSize(this.dragSplittingSlots, this.dragSplittingLimit, itemstack1, i);
               int j = Math.min(itemstack1.getMaxStackSize(), slot.getItemStackLimit(itemstack1));
               if (itemstack1.getCount() > j) {
                  itemstack1.setCount(j);
               }
            }
         }
      }

   }

   private Slot getSlotAtPosition(int x, int y) {
      for(int i = 0; i < this.inventorySlots.inventorySlots.size(); ++i) {
         Slot slot = (Slot)this.inventorySlots.inventorySlots.get(i);
         if (this.isMouseOverSlot(slot, x, y) && slot.isEnabled()) {
            return slot;
         }
      }

      return null;
   }

   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      nC var10001 = this.mc;
      boolean flag = mouseButton == nC.gameSettings.keyBindPickBlock.getKeyCode() + 100;
      Slot slot = this.getSlotAtPosition(mouseX, mouseY);
      long i = nC.getSystemTime();
      this.doubleClick = this.lastClickSlot == slot && i - this.lastClickTime < 250L && this.lastClickButton == mouseButton;
      this.ignoreMouseUp = false;
      if (mouseButton == 0 || mouseButton == 1 || flag) {
         int j = this.guiLeft;
         int k = this.guiTop;
         boolean flag1 = this.hasClickedOutside(mouseX, mouseY, j, k);
         int l = -1;
         if (slot != null) {
            l = slot.slotNumber;
         }

         if (flag1) {
            l = -999;
         }

         nC var10000 = this.mc;
         if (nC.gameSettings.touchscreen && flag1) {
            var10000 = this.mc;
            if (nC.player.inventory.getItemStack().isEmpty()) {
               this.mc.displayGuiScreen((lg)null);
               return;
            }
         }

         if (l != -1) {
            var10000 = this.mc;
            if (nC.gameSettings.touchscreen) {
               if (slot != null && slot.getHasStack()) {
                  this.clickedSlot = slot;
                  this.draggedStack = Qy.EMPTY;
                  this.isRightMouseClick = mouseButton == 1;
               } else {
                  this.clickedSlot = null;
               }
            } else if (!this.dragSplitting) {
               var10000 = this.mc;
               if (nC.player.inventory.getItemStack().isEmpty()) {
                  var10001 = this.mc;
                  if (mouseButton == nC.gameSettings.keyBindPickBlock.getKeyCode() + 100) {
                     this.handleMouseClick(slot, l, mouseButton, ClickType.CLONE);
                  } else {
                     boolean flag2 = l != -999 && (Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54));
                     ClickType clicktype = ClickType.PICKUP;
                     if (flag2) {
                        this.shiftClickedSlot = slot != null && slot.getHasStack() ? slot.getStack().copy() : Qy.EMPTY;
                        clicktype = ClickType.QUICK_MOVE;
                     } else if (l == -999) {
                        clicktype = ClickType.THROW;
                     }

                     0bz.method_Qm().method_Qn().post(new 0dj(l, clicktype));
                     this.handleMouseClick(slot, l, mouseButton, clicktype);
                  }

                  this.ignoreMouseUp = true;
               } else {
                  this.dragSplitting = true;
                  this.dragSplittingButton = mouseButton;
                  this.dragSplittingSlots.clear();
                  if (mouseButton == 0) {
                     this.dragSplittingLimit = 0;
                  } else if (mouseButton == 1) {
                     this.dragSplittingLimit = 1;
                  } else {
                     var10001 = this.mc;
                     if (mouseButton == nC.gameSettings.keyBindPickBlock.getKeyCode() + 100) {
                        this.dragSplittingLimit = 2;
                     }
                  }
               }
            }
         }
      }

      this.lastClickSlot = slot;
      this.lastClickTime = i;
      this.lastClickButton = mouseButton;
   }

   protected boolean hasClickedOutside(int p_193983_1_, int p_193983_2_, int p_193983_3_, int p_193983_4_) {
      return p_193983_1_ < p_193983_3_ || p_193983_2_ < p_193983_4_ || p_193983_1_ >= p_193983_3_ + this.xSize || p_193983_2_ >= p_193983_4_ + this.ySize;
   }

   protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
      Slot slot = this.getSlotAtPosition(mouseX, mouseY);
      nC var10000 = this.mc;
      Qy itemstack = nC.player.inventory.getItemStack();
      if (this.clickedSlot != null) {
         var10000 = this.mc;
         if (nC.gameSettings.touchscreen) {
            if (clickedMouseButton == 0 || clickedMouseButton == 1) {
               if (this.draggedStack.isEmpty()) {
                  if (slot != this.clickedSlot && !this.clickedSlot.getStack().isEmpty()) {
                     this.draggedStack = this.clickedSlot.getStack().copy();
                     return;
                  }
               } else if (this.draggedStack.getCount() > 1 && slot != null && Container.canAddItemToSlot(slot, this.draggedStack, false)) {
                  long i = nC.getSystemTime();
                  if (this.currentDragTargetSlot == slot) {
                     if (i - this.dragItemDropDelay > 500L) {
                        this.handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, 0, ClickType.PICKUP);
                        this.handleMouseClick(slot, slot.slotNumber, 1, ClickType.PICKUP);
                        this.handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, 0, ClickType.PICKUP);
                        this.dragItemDropDelay = i + 750L;
                        this.draggedStack.shrink(1);
                        return;
                     }
                  } else {
                     this.currentDragTargetSlot = slot;
                     this.dragItemDropDelay = i;
                  }

                  return;
               }

               return;
            }

            return;
         }
      }

      if (this.dragSplitting && slot != null && !itemstack.isEmpty() && (itemstack.getCount() > this.dragSplittingSlots.size() || this.dragSplittingLimit == 2) && Container.canAddItemToSlot(slot, itemstack, true) && slot.isItemValid(itemstack) && this.inventorySlots.canDragIntoSlot(slot)) {
         this.dragSplittingSlots.add(slot);
         this.updateDragSplitting();
      }

   }

   protected void mouseReleased(int mouseX, int mouseY, int state) {
      Slot slot = this.getSlotAtPosition(mouseX, mouseY);
      int i = this.guiLeft;
      int j = this.guiTop;
      boolean flag = this.hasClickedOutside(mouseX, mouseY, i, j);
      int k = -1;
      if (slot != null) {
         k = slot.slotNumber;
      }

      if (flag) {
         k = -999;
      }

      Slot slot1;
      Iterator var11;
      nC var10000;
      nC var10001;
      if (this.doubleClick && slot != null && state == 0 && this.inventorySlots.canMergeSlot(Qy.EMPTY, slot)) {
         if (isShiftKeyDown()) {
            if (!this.shiftClickedSlot.isEmpty()) {
               var11 = this.inventorySlots.inventorySlots.iterator();

               while(var11.hasNext()) {
                  slot1 = (Slot)var11.next();
                  if (slot1 != null) {
                     var10001 = this.mc;
                     if (slot1.canTakeStack(nC.player) && slot1.getHasStack() && slot1.inventory == slot.inventory && Container.canAddItemToSlot(slot1, this.shiftClickedSlot, true)) {
                        this.handleMouseClick(slot1, slot1.slotNumber, state, ClickType.QUICK_MOVE);
                     }
                  }
               }
            }
         } else {
            this.handleMouseClick(slot, k, state, ClickType.PICKUP_ALL);
         }

         this.doubleClick = false;
         this.lastClickTime = 0L;
      } else {
         label176: {
            if (this.dragSplitting && this.dragSplittingButton != state) {
               this.dragSplitting = false;
               this.dragSplittingSlots.clear();
               this.ignoreMouseUp = true;
               return;
            }

            if (this.ignoreMouseUp) {
               this.ignoreMouseUp = false;
               return;
            }

            boolean flag1;
            if (this.clickedSlot != null) {
               var10000 = this.mc;
               if (nC.gameSettings.touchscreen) {
                  if (state == 0 || state == 1) {
                     if (this.draggedStack.isEmpty() && slot != this.clickedSlot) {
                        this.draggedStack = this.clickedSlot.getStack();
                     }

                     flag1 = Container.canAddItemToSlot(slot, this.draggedStack, false);
                     if (k != -1 && !this.draggedStack.isEmpty() && flag1) {
                        this.handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, state, ClickType.PICKUP);
                        this.handleMouseClick(slot, k, 0, ClickType.PICKUP);
                        var10000 = this.mc;
                        if (nC.player.inventory.getItemStack().isEmpty()) {
                           this.returningStack = Qy.EMPTY;
                        } else {
                           this.handleMouseClick(this.clickedSlot, this.clickedSlot.slotNumber, state, ClickType.PICKUP);
                           this.touchUpX = mouseX - i;
                           this.touchUpY = mouseY - j;
                           this.returningStackDestSlot = this.clickedSlot;
                           this.returningStack = this.draggedStack;
                           this.returningStackTime = nC.getSystemTime();
                        }
                     } else if (!this.draggedStack.isEmpty()) {
                        this.touchUpX = mouseX - i;
                        this.touchUpY = mouseY - j;
                        this.returningStackDestSlot = this.clickedSlot;
                        this.returningStack = this.draggedStack;
                        this.returningStackTime = nC.getSystemTime();
                     }

                     this.draggedStack = Qy.EMPTY;
                     this.clickedSlot = null;
                  }
                  break label176;
               }
            }

            if (this.dragSplitting && !this.dragSplittingSlots.isEmpty()) {
               this.handleMouseClick((Slot)null, -999, Container.getQuickcraftMask(0, this.dragSplittingLimit), ClickType.QUICK_CRAFT);
               var11 = this.dragSplittingSlots.iterator();

               while(var11.hasNext()) {
                  slot1 = (Slot)var11.next();
                  this.handleMouseClick(slot1, slot1.slotNumber, Container.getQuickcraftMask(1, this.dragSplittingLimit), ClickType.QUICK_CRAFT);
               }

               this.handleMouseClick((Slot)null, -999, Container.getQuickcraftMask(2, this.dragSplittingLimit), ClickType.QUICK_CRAFT);
            } else {
               var10000 = this.mc;
               if (!nC.player.inventory.getItemStack().isEmpty()) {
                  var10001 = this.mc;
                  if (state == nC.gameSettings.keyBindPickBlock.getKeyCode() + 100) {
                     this.handleMouseClick(slot, k, state, ClickType.CLONE);
                  } else {
                     flag1 = k != -999 && (Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54));
                     if (flag1) {
                        this.shiftClickedSlot = slot != null && slot.getHasStack() ? slot.getStack().copy() : Qy.EMPTY;
                     }

                     this.handleMouseClick(slot, k, state, flag1 ? ClickType.QUICK_MOVE : ClickType.PICKUP);
                  }
               }
            }
         }
      }

      var10000 = this.mc;
      if (nC.player.inventory.getItemStack().isEmpty()) {
         this.lastClickTime = 0L;
      }

      this.dragSplitting = false;
   }

   private boolean isMouseOverSlot(Slot slotIn, int mouseX, int mouseY) {
      return this.isPointInRegion(slotIn.xPos, slotIn.yPos, 16, 16, mouseX, mouseY);
   }

   protected boolean isPointInRegion(int rectX, int rectY, int rectWidth, int rectHeight, int pointX, int pointY) {
      int i = this.guiLeft;
      int j = this.guiTop;
      pointX -= i;
      pointY -= j;
      return pointX >= rectX - 1 && pointX < rectX + rectWidth + 1 && pointY >= rectY - 1 && pointY < rectY + rectHeight + 1;
   }

   protected void handleMouseClick(Slot slotIn, int slotId, int mouseButton, ClickType type) {
      if (slotIn != null) {
         slotId = slotIn.slotNumber;
      }

      nC var10005 = this.mc;
      this.mc.playerController.windowClick(this.inventorySlots.windowId, slotId, mouseButton, type, nC.player);
   }

   protected void keyTyped(char typedChar, int keyCode) throws IOException {
      nC var10001;
      label27: {
         if (keyCode != 1) {
            var10001 = this.mc;
            if (keyCode != nC.gameSettings.keyBindInventory.getKeyCode()) {
               break label27;
            }
         }

         nC var10000 = this.mc;
         nC.player.closeScreen();
      }

      this.checkHotbarKeys(keyCode);
      if (this.hoveredSlot != null && this.hoveredSlot.getHasStack()) {
         var10001 = this.mc;
         if (keyCode == nC.gameSettings.keyBindPickBlock.getKeyCode()) {
            this.handleMouseClick(this.hoveredSlot, this.hoveredSlot.slotNumber, 0, ClickType.CLONE);
         } else {
            var10001 = this.mc;
            if (keyCode == nC.gameSettings.keyBindDrop.getKeyCode()) {
               this.handleMouseClick(this.hoveredSlot, this.hoveredSlot.slotNumber, isCtrlKeyDown() ? 1 : 0, ClickType.THROW);
            }
         }
      }

   }

   protected boolean checkHotbarKeys(int keyCode) {
      nC var10000 = this.mc;
      if (nC.player.inventory.getItemStack().isEmpty() && this.hoveredSlot != null) {
         for(int i = 0; i < 9; ++i) {
            nC var10001 = this.mc;
            if (keyCode == nC.gameSettings.keyBindsHotbar[i].getKeyCode()) {
               this.handleMouseClick(this.hoveredSlot, this.hoveredSlot.slotNumber, i, ClickType.SWAP);
               return true;
            }
         }
      }

      return false;
   }

   public void onGuiClosed() {
      nC var10000 = this.mc;
      if (nC.player != null) {
         nC var10001 = this.mc;
         this.inventorySlots.onContainerClosed(nC.player);
      }

   }

   public boolean doesGuiPauseGame() {
      return false;
   }

   public void updateScreen() {
      super.updateScreen();
      nC var10000 = this.mc;
      if (nC.player.isEntityAlive()) {
         var10000 = this.mc;
         if (!nC.player.isDead) {
            return;
         }
      }

      var10000 = this.mc;
      nC.player.closeScreen();
   }
}
