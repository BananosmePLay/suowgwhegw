package neo;

import io.netty.buffer.Unpooled;
import java.io.IOException;
import java.util.Iterator;
import net.minecraft.inventory.ContainerBeacon;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class lR extends lU {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final ResourceLocation BEACON_GUI_TEXTURES = new ResourceLocation("textures/gui/container/beacon.png");
   private final IInventory tileBeacon;
   private lP beaconConfirmButton;
   private boolean buttonsNotDrawn;

   public lR(MJ playerInventory, IInventory tileBeaconIn) {
      super(new ContainerBeacon(playerInventory, tileBeaconIn));
      this.tileBeacon = tileBeaconIn;
      this.xSize = 230;
      this.ySize = 219;
   }

   public void initGui() {
      super.initGui();
      this.beaconConfirmButton = new lP(this, -1, this.guiLeft + 164, this.guiTop + 107);
      this.buttonList.add(this.beaconConfirmButton);
      this.buttonList.add(new lO(this, -2, this.guiLeft + 190, this.guiTop + 107));
      this.buttonsNotDrawn = true;
      this.beaconConfirmButton.enabled = false;
   }

   public void updateScreen() {
      super.updateScreen();
      int i = this.tileBeacon.getField(0);
      VW potion = VW.getPotionById(this.tileBeacon.getField(1));
      VW potion1 = VW.getPotionById(this.tileBeacon.getField(2));
      if (this.buttonsNotDrawn && i >= 0) {
         this.buttonsNotDrawn = false;
         int j = 100;

         int l1;
         int i2;
         int j2;
         VW potion3;
         lQ guibeacon$powerbutton2;
         for(int k = 0; k <= 2; ++k) {
            l1 = Yj.EFFECTS_LIST[k].length;
            i2 = l1 * 22 + (l1 - 1) * 2;

            for(j2 = 0; j2 < l1; ++j2) {
               potion3 = Yj.EFFECTS_LIST[k][j2];
               guibeacon$powerbutton2 = new lQ(this, j++, this.guiLeft + 76 + j2 * 24 - i2 / 2, this.guiTop + 22 + k * 25, potion3, k);
               this.buttonList.add(guibeacon$powerbutton2);
               if (k >= i) {
                  guibeacon$powerbutton2.enabled = false;
               } else if (potion3 == potion) {
                  guibeacon$powerbutton2.setSelected(true);
               }
            }
         }

         int k1 = true;
         l1 = Yj.EFFECTS_LIST[3].length + 1;
         i2 = l1 * 22 + (l1 - 1) * 2;

         for(j2 = 0; j2 < l1 - 1; ++j2) {
            potion3 = Yj.EFFECTS_LIST[3][j2];
            guibeacon$powerbutton2 = new lQ(this, j++, this.guiLeft + 167 + j2 * 24 - i2 / 2, this.guiTop + 47, potion3, 3);
            this.buttonList.add(guibeacon$powerbutton2);
            if (3 >= i) {
               guibeacon$powerbutton2.enabled = false;
            } else if (potion3 == potion1) {
               guibeacon$powerbutton2.setSelected(true);
            }
         }

         if (potion != null) {
            lQ guibeacon$powerbutton1 = new lQ(this, j++, this.guiLeft + 167 + (l1 - 1) * 24 - i2 / 2, this.guiTop + 47, potion, 3);
            this.buttonList.add(guibeacon$powerbutton1);
            if (3 >= i) {
               guibeacon$powerbutton1.enabled = false;
            } else if (potion == potion1) {
               guibeacon$powerbutton1.setSelected(true);
            }
         }
      }

      this.beaconConfirmButton.enabled = !this.tileBeacon.getStackInSlot(0).isEmpty() && potion != null;
   }

   protected void actionPerformed(jK button) throws IOException {
      nC var10000;
      nC var10003;
      if (button.id == -2) {
         var10000 = this.mc;
         var10003 = this.mc;
         nC.player.connection.sendPacket(new SJ(nC.player.openContainer.windowId));
         this.mc.displayGuiScreen((lg)null);
      } else if (button.id == -1) {
         String s = "MC|Beacon";
         SA packetbuffer = new SA(Unpooled.buffer());
         packetbuffer.writeInt(this.tileBeacon.getField(1));
         packetbuffer.writeInt(this.tileBeacon.getField(2));
         this.mc.getConnection().sendPacket(new SN("MC|Beacon", packetbuffer));
         var10000 = this.mc;
         var10003 = this.mc;
         nC.player.connection.sendPacket(new SJ(nC.player.openContainer.windowId));
         this.mc.displayGuiScreen((lg)null);
      } else if (button instanceof lQ) {
         lQ guibeacon$powerbutton = (lQ)button;
         if (guibeacon$powerbutton.isSelected()) {
            return;
         }

         int i = VW.getIdFromPotion(lQ.access$000(guibeacon$powerbutton));
         if (lQ.access$100(guibeacon$powerbutton) < 3) {
            this.tileBeacon.setField(1, i);
         } else {
            this.tileBeacon.setField(2, i);
         }

         this.buttonList.clear();
         this.initGui();
         this.updateScreen();
      }

   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      super.drawScreen(mouseX, mouseY, partialTicks);
      this.renderHoveredToolTip(mouseX, mouseY);
   }

   protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
      yz.disableStandardItemLighting();
      this.drawCenteredString(this.fontRenderer, Ax.format("tile.beacon.primary"), 62, 10, 14737632);
      this.drawCenteredString(this.fontRenderer, Ax.format("tile.beacon.secondary"), 169, 10, 14737632);
      Iterator var3 = this.buttonList.iterator();

      while(var3.hasNext()) {
         jK guibutton = (jK)var3.next();
         if (guibutton.isMouseOver()) {
            guibutton.drawButtonForegroundLayer(mouseX - this.guiLeft, mouseY - this.guiTop);
            break;
         }
      }

      yz.enableGUIStandardItemLighting();
   }

   protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(BEACON_GUI_TEXTURES);
      int i = (this.width - this.xSize) / 2;
      int j = (this.height - this.ySize) / 2;
      this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
      this.itemRender.zLevel = 100.0F;
      this.itemRender.renderItemAndEffectIntoGUI(new Qy(NK.EMERALD), i + 42, j + 109);
      this.itemRender.renderItemAndEffectIntoGUI(new Qy(NK.DIAMOND), i + 42 + 22, j + 109);
      this.itemRender.renderItemAndEffectIntoGUI(new Qy(NK.GOLD_INGOT), i + 42 + 44, j + 109);
      this.itemRender.renderItemAndEffectIntoGUI(new Qy(NK.IRON_INGOT), i + 42 + 66, j + 109);
      this.itemRender.zLevel = 0.0F;
   }

   // $FF: synthetic method
   static ResourceLocation access$200() {
      return BEACON_GUI_TEXTURES;
   }
}
