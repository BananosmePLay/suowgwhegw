package neo;

import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import de.florianmichael.viamcp.gui.GuiProtocolSelector;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.network.ServerPinger;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class 0bG extends GuiScreen {
   public final GuiScreen parentScreen;

   private static 0eg D1oXj94TDj() {
      return 0eh.mnstb_12;
   }

   public void onGuiClosed() {
      Keyboard.enableRepeatEvents((boolean)(31422 ^ -19149 ^ 368 ^ -12547));
      x1BY6JG7O3().clearPendingNetworks();
   }

   protected void actionPerformed(GuiButton button) throws IOException {
      if (EvPvQI6h49(button) == 0) {
         Ddy9Rzw01U(this).displayGuiScreen(Oq218MvP6j(this));
      } else if (IQx8y7SVi7(button) == (19705 ^ -21935 ^ 31779 ^ -25974)) {
         0bH.init();
      } else if (BrAlHptm4A(button) == (15800 ^ -4251 ^ 10586 ^ -1139)) {
         owgl6MBC2T(this).displayGuiScreen(new GuiProtocolSelector(this));
      }

   }

   private static 0eg My7NvXOWHY() {
      return 0eh.mnstb_12;
   }

   private static 0eg e21EgZ2sbX() {
      return 0eh.mnstb_12;
   }

   private static int BrAlHptm4A(GuiButton var0) {
      return var0.id;
   }

   private static BufferedImage QgXIz6BKwN(ServerData var0) {
      return var0.iconRender;
   }

   private static BufferedImage _1i9ZYObe4/* $FF was: 71i9ZYObe4*/(ServerData var0) {
      return var0.iconRender;
   }

   private static int EvPvQI6h49(GuiButton var0) {
      return var0.id;
   }

   private static 0eg _vgNllV5AT/* $FF was: 4vgNllV5AT*/() {
      return 0eh.mnstb_16;
   }

   private static int IQx8y7SVi7(GuiButton var0) {
      return var0.id;
   }

   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
   }

   private static Minecraft Ddy9Rzw01U(0bG var0) {
      return var0.mc;
   }

   private static String VrUet2Dih9(ServerData var0) {
      return var0.populationInfo;
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
      FtIOhGoWX6().useShader(sr.getScaledWidth(), sr.getScaledHeight(), (float)mouseX, (float)mouseY, (float)(System.currentTimeMillis() - D97UFOJOK2()) / Float.intBitsToFloat('롑' ^ '\ue322' ^ 9294 ^ 440601695 ^ 126593 ^ 102293 ^ 20838 ^ 1593361168));
      GL11.glBegin(22489 ^ -5918 ^ 13554 ^ -29746);
      GL11.glVertex2f(Float.intBitsToFloat('뮮' ^ 31379 ^ '릭' ^ -1183310346 ^ '꤇' ^ '꼍' ^ 25536 ^ 116916396), Float.intBitsToFloat('\uef19' ^ 12629 ^ '\udeee' ^ 480793208 ^ '\uf322' ^ 20767 ^ '햳' ^ -1557650092));
      GL11.glVertex2f(Float.intBitsToFloat(114487 ^ 110903 ^ 4004 ^ -1093093833 ^ 108216 ^ 98267 ^ 1291 ^ 22572027), Float.intBitsToFloat('龦' ^ '\ueeeb' ^ 9867 ^ -265149678 ^ '\uf1d7' ^ 221211 ^ 4192 ^ -810420872));
      GL11.glVertex2f(Float.intBitsToFloat('윦' ^ '\uedff' ^ 14844 ^ 905071499 ^ 16241 ^ 210075 ^ 'ꯌ' ^ 175241352), Float.intBitsToFloat(21119 ^ 232547 ^ 5610 ^ -1240083269 ^ 9700 ^ 245663 ^ 16506 ^ -1986673332));
      GL11.glVertex2f(Float.intBitsToFloat(300 ^ 92651 ^ 102251 ^ -1613866500 ^ 16560 ^ 21983 ^ 16017 ^ -1605462610), Float.intBitsToFloat('돢' ^ 110287 ^ '텅' ^ -183439679 ^ 23898 ^ 130825 ^ 19988 ^ 1251004142));
      GL11.glEnd();
      GL20.glUseProgram(17194 ^ -6516 ^ 15090 ^ -24748);
      GlStateManager.disableCull();
      GlStateManager.pushMatrix();
      float panelWidth = Float.intBitsToFloat(5104 ^ 2075011 ^ 26709 ^ 1508890933 ^ 14838 ^ 521065 ^ 25915 ^ 439067831);
      float panelHeight = Float.intBitsToFloat(9117 ^ 2095321 ^ 14809 ^ -24123628 ^ 3958 ^ 523929 ^ 32083 ^ -1122268875);
      0ex.drawGradientRound((float)(sr.getScaledWidth() / (7606 ^ -31212 ^ 23116 ^ -15892)) - panelWidth / Float.intBitsToFloat(126810 ^ 119493 ^ 22239 ^ 637963544 ^ 120837 ^ 129384 ^ 7586 ^ 1711725207), (float)(sr.getScaledHeight() / (30943 ^ -30628 ^ 4636 ^ -7523)) - panelHeight / Float.intBitsToFloat(255332 ^ 232798 ^ 227 ^ -219833993 ^ 251131 ^ 242011 ^ 31659 ^ -1293553755), panelWidth, panelHeight, Float.intBitsToFloat(31022 ^ 24157 ^ 7356 ^ 1177390935 ^ 20936 ^ '\ua7ce' ^ 2684 ^ 120408290), 0br.getC(9755 ^ -27119 ^ 12636 ^ -32426), 0br.getC(3736 ^ -13100 ^ 7061 ^ -9949), 0br.getC(21668 ^ -732 ^ 3016 ^ -24410), 0br.getC(15652 ^ -11238 ^ 20078 ^ -23368));
      0ex.drawRound((float)(sr.getScaledWidth() / (9002 ^ -21805 ^ 7495 ^ -27460)) - panelWidth / Float.intBitsToFloat(246858 ^ 216293 ^ 15713 ^ 1163224313 ^ 253957 ^ 221395 ^ 8356 ^ 89485637) + Float.intBitsToFloat(14912 ^ '릘' ^ 13498 ^ -422480193 ^ 252098 ^ 240522 ^ 8519 ^ -1496214574), (float)(sr.getScaledHeight() / (19049 ^ -25190 ^ 1809 ^ -12064)) - panelHeight / Float.intBitsToFloat(20485 ^ 'ꈯ' ^ 15993 ^ -907059989 ^ 27396 ^ '흹' ^ 5003 ^ -1980809394) + Float.intBitsToFloat(25200 ^ 102016 ^ 126117 ^ -141120463 ^ 18766 ^ 110998 ^ 118761 ^ -1214866603), panelWidth - Float.intBitsToFloat(21465 ^ 17651 ^ 11175 ^ 253566335 ^ 21425 ^ 121086 ^ 23217 ^ 1335685132), panelHeight - Float.intBitsToFloat(2140 ^ 100701 ^ 30609 ^ 801826301 ^ 2891 ^ 115914 ^ 9358 ^ 1867184226), Float.intBitsToFloat(1022687 ^ 1022605 ^ 25741 ^ -1446539106 ^ 1021428 ^ 29146 ^ 1022646 ^ -389575975), new Color(9679 ^ -16733 ^ 16830 ^ -9533, 3959 ^ -24441 ^ 12470 ^ -24745, 27046 ^ -27702 ^ 9764 ^ -9127, 17915 ^ -6344 ^ 18640 ^ -5405));
      4vgNllV5AT().drawCenteredGradientThemeString(VG2eQPrYG5("ǸǚǙǜƦǚƤǜǙǗׄƥǑƤǖǑƤǚǖ"), (float)(sr.getScaledWidth() / (8857 ^ -8259 ^ 4991 ^ -4519)), (float)(sr.getScaledHeight() / (4045 ^ -21867 ^ 6692 ^ -16514)) - panelHeight / Float.intBitsToFloat(130680 ^ 102122 ^ 1896 ^ 1083750719 ^ 102347 ^ '릭' ^ 105721 ^ 9987162) + Float.intBitsToFloat('Ꜿ' ^ '쟾' ^ 15362 ^ -1592054220 ^ 1476 ^ '船' ^ '랱' ^ -529841478));
      int offsetX = 9134 ^ -12611 ^ 5318 ^ -1579;
      int offsetY = 18530 ^ -26010 ^ 28053 ^ -16495;
      Iterator var9 = 0bH.getServers().iterator();

      while(var9.hasNext()) {
         ServerData serverData = (ServerData)var9.next();
         float elementX = (float)(sr.getScaledWidth() / (882 ^ -6181 ^ 25755 ^ -32720)) - panelWidth / Float.intBitsToFloat(8404 ^ 89900 ^ 121592 ^ -1879938921 ^ 860 ^ 124334 ^ 100176 ^ -806179787) + (float)offsetX + Float.intBitsToFloat(28647 ^ '룵' ^ 18563 ^ 1237719207 ^ 31117 ^ '\uf1dc' ^ 5820 ^ 157686235);
         float elementY = (float)(sr.getScaledHeight() / (12194 ^ -12018 ^ 1514 ^ -1212)) - panelHeight / Float.intBitsToFloat(25554 ^ '鄲' ^ 808 ^ -1883943233 ^ 26957 ^ 243843 ^ 258714 ^ -810181597) + (float)offsetY + Float.intBitsToFloat('쀡' ^ 'ﮒ' ^ 373 ^ -1191948080 ^ '\ue700' ^ '쐷' ^ 11172 ^ -89628027);
         0ex.drawRound(elementX, elementY, Float.intBitsToFloat(519992 ^ 482382 ^ 9714 ^ -1750368681 ^ 515053 ^ 524073 ^ 5954 ^ -714876075), Float.intBitsToFloat(12182 ^ 469665 ^ '뫯' ^ 1651747542 ^ 29308 ^ 514483 ^ 2669 ^ 542356652), Float.intBitsToFloat(26688 ^ '뿐' ^ 18034 ^ -501801532 ^ 5572 ^ '蟝' ^ 30296 ^ -1558747033), new Color(29681 ^ -1177 ^ 24378 ^ -10364, 19115 ^ -23221 ^ 5819 ^ -1677, 28130 ^ -12339 ^ 4414 ^ -19655));
         e21EgZ2sbX().drawCenteredGradientThemeString(JIB5ElITv7(serverData), elementX + Float.intBitsToFloat(244398 ^ '펇' ^ 261642 ^ 1263613648 ^ 29578 ^ '\ue42a' ^ 24947 ^ 152655648), elementY + Float.intBitsToFloat('鼱' ^ 22800 ^ '혂' ^ 1544037248 ^ '맶' ^ 1040779 ^ '벭' ^ 478664563));
         My7NvXOWHY().drawString(VG2eQPrYG5("ǺǙǟǔǝǙמׄ") + 0cC.stripColor(VrUet2Dih9(serverData)), elementX + Float.intBitsToFloat(16089 ^ 85057 ^ 122636 ^ 1562012810 ^ 8882 ^ 28846 ^ 8383 ^ 485143485), elementY + Float.intBitsToFloat('ꎙ' ^ '\uea07' ^ 23312 ^ -135110212 ^ '\uda10' ^ '辟' ^ 32620 ^ -1234019887), (new Color(9567 ^ -5297 ^ 9210 ^ -4830, 32391 ^ -10019 ^ 31385 ^ -9205, 10235 ^ -27473 ^ 29062 ^ -15846)).getRGB());
         String version = ProtocolVersion.getProtocol(kkmIgEjj5T(serverData)).getName();
         D1oXj94TDj().drawString(VG2eQPrYG5("ǶǑƤƥǜƫמׄ") + (version.contains(VG2eQPrYG5("ֱ֊֏֊\u058b֓֊")) ? VG2eQPrYG5("ֱ֊֏֊\u058b֓֊") : version), elementX + Float.intBitsToFloat('ꭵ' ^ '쭤' ^ 21105 ^ -2043129590 ^ '닟' ^ 1015513 ^ 22426 ^ -943195914), elementY + Float.intBitsToFloat(13069 ^ '\uebd9' ^ 27193 ^ 667824664 ^ 29594 ^ '칡' ^ 18470 ^ 1719039272), (new Color(24022 ^ -4087 ^ 15885 ^ -27878, 21928 ^ -8050 ^ 9494 ^ -28424, 16888 ^ -13271 ^ 6164 ^ -27379)).getRGB());
         u9ul6vjUWI().drawString(VG2eQPrYG5("ǻǜǙǗמׄ") + TrGaJ6Mug4(serverData), elementX + Float.intBitsToFloat(232267 ^ '落' ^ 260489 ^ -113415724 ^ 18687 ^ '싦' ^ 16204 ^ -1194513794), elementY + Float.intBitsToFloat(247861 ^ 255425 ^ 16107 ^ 1300611502 ^ 250747 ^ 216838 ^ 4935 ^ 209018251), (new Color(10822 ^ -9240 ^ 7568 ^ -4874, 11898 ^ -5421 ^ 1786 ^ -15717, 28763 ^ -22704 ^ 13654 ^ -7531)).getRGB());
         if (71i9ZYObe4(serverData) != null) {
            0ew.renderImage(QgXIz6BKwN(serverData), (int)(elementX + Float.intBitsToFloat(13908 ^ 130195 ^ 546 ^ -819672405 ^ 28513 ^ 103535 ^ 23520 ^ -1887133024)), (int)(elementY + Float.intBitsToFloat(130552 ^ 115299 ^ 30216 ^ 333091160 ^ 118570 ^ 93566 ^ 9243 ^ 1385848452)), 5460 ^ -10756 ^ 9687 ^ -6805, 1265 ^ -14803 ^ 13058 ^ -3638);
         } else {
            0ew.drawImage(new ResourceLocation(VG2eQPrYG5("\u0590ց֜\u0590֑֖ց֗\u05cb։֍֗և\u05cb֑֊֏֊\u058b֓֊ֻ֗ց֖֒ց֖\u05ca֔֊փ")), (float)((int)(elementX + Float.intBitsToFloat(14996 ^ 109085 ^ 116016 ^ -1521969927 ^ 130384 ^ 99258 ^ 8601 ^ -437739981))), (float)((int)(elementY + Float.intBitsToFloat(10469 ^ '쁉' ^ 32098 ^ -1444014473 ^ 25687 ^ '킈' ^ 22993 ^ -391221577))), Float.intBitsToFloat(115084 ^ 94147 ^ 9505 ^ -229221831 ^ 103387 ^ 91380 ^ 24347 ^ -1275692701), Float.intBitsToFloat(31338 ^ 'ﰞ' ^ 9296 ^ 2084449593 ^ 31286 ^ '\ude45' ^ 162 ^ 1033776076), new Color(4531 ^ -2068 ^ 19020 ^ -21268, 15342 ^ -15182 ^ 1016 ^ -933, 10403 ^ -24663 ^ 13995 ^ -32418));
         }

         offsetX += 110;
         if (offsetX > (31115 ^ -16814 ^ 16034 ^ -1999)) {
            offsetX = 22919 ^ -24721 ^ 11462 ^ -5586;
            offsetY += 50;
         }

         if (offsetY >= (11943 ^ -13364 ^ 19686 ^ -22203)) {
            break;
         }
      }

      GlStateManager.popMatrix();
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   private static GuiScreen WvghkIE3NE(0bG var0) {
      return var0.parentScreen;
   }

   public void confirmClicked(boolean result, int id) {
   }

   private static int kkmIgEjj5T(ServerData var0) {
      return var0.version;
   }

   private static List VoTfmqiO48(0bG var0) {
      return var0.buttonList;
   }

   private static long D97UFOJOK2() {
      return 0bE.time;
   }

   private static String JIB5ElITv7(ServerData var0) {
      return var0.serverIP;
   }

   public void updateScreen() {
      super.updateScreen();
      oReSPq1nWM().pingPendingNetworks();
   }

   private static Minecraft aKItTDrSKL(0bG var0) {
      return var0.mc;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String VG2eQPrYG5(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 11870 ^ -32152 ^ 27014 ^ -14928; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 12251 ^ -34893 ^ '깫' ^ -3097));
      }

      return var1.toString();
   }

   private static GuiScreen Oq218MvP6j(0bG var0) {
      return var0.parentScreen;
   }

   private static ServerPinger oReSPq1nWM() {
      return 0bH.serverPinger;
   }

   private static 0eg u9ul6vjUWI() {
      return 0eh.mnstb_12;
   }

   public _bG/* $FF was: 0bG*/(GuiScreen parentScreen) {
      this.parentScreen = parentScreen;
   }

   private static ServerPinger x1BY6JG7O3() {
      return 0bH.serverPinger;
   }

   public void initGui() {
      VoTfmqiO48(this).clear();
      0bH.init();
   }

   protected void keyTyped(char typedChar, int keyCode) throws IOException {
      if (keyCode == (18461 ^ -20088 ^ 28735 ^ -30293)) {
         aKItTDrSKL(this).displayGuiScreen(WvghkIE3NE(this));
      }

      super.keyTyped(typedChar, keyCode);
   }

   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
   }

   private static long TrGaJ6Mug4(ServerData var0) {
      return var0.pingToServer;
   }

   private static 0ev FtIOhGoWX6() {
      return 0bE.backgroundShader;
   }

   private static Minecraft owgl6MBC2T(0bG var0) {
      return var0.mc;
   }

   protected void mouseReleased(int mouseX, int mouseY, int state) {
      super.mouseReleased(mouseX, mouseY, state);
   }
}
