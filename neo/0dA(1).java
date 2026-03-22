package neo;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.inventory.ClickType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class 0dA {
   private static CopyOnWriteArrayList woqPGaxtmD() {
      return 0cC.bots;
   }

   public String stripColor(String input) {
      return 0cC.stripColor(input);
   }

   public String limitStringLength(String str, int maxLength) {
      return str.length() > maxLength ? str.substring(24393 ^ -25457 ^ 23680 ^ -24762, maxLength) : str;
   }

   public int getSword(0cC bot) {
      return 0dm.getSword(bot);
   }

   public 0cC[] getBots() {
      0cC[] bots = new 0cC[fVuBrDUQ6g().size()];
      return (0cC[])woqPGaxtmD().toArray(bots);
   }

   public float normalizeYaw(float yaw) {
      return 0dm.normalizeYaw(yaw);
   }

   public boolean hasSword(0cC bot) {
      return 0dm.hasSword(bot);
   }

   public float normalizePitch(float pitch) {
      return 0dm.normalizePitch(pitch);
   }

   public void sendMessage(String message) {
      0dK.formatMsg(message);
   }

   public String translateItemKey(String input) {
      return 0dE.translate(input);
   }

   public float getDistance(0cC pbot, double targetX, double targetY, double targetZ) {
      return 0dm.getDistance(pbot, targetX, targetY, targetZ);
   }

   public double getDistance(double px1, double py1, double pz1, double px2, double py2, double pz2) {
      return 0dm.getDistance(px1, py1, pz1, px2, py2, pz2);
   }

   private static CopyOnWriteArrayList fVuBrDUQ6g() {
      return 0cC.bots;
   }

   public String parsePlaceholders(String message) {
      return 0dy.format(message);
   }

   public _dA/* $FF was: 0dA*/() {
   }

   public EnumFacing getEnumFacing(String name) {
      return EnumFacing.byName(name);
   }

   public Color getColor(BufferedImage img, int x, int y) {
      int clr = img.getRGB(x, y);
      int red = (clr & (1884 ^ 119914 ^ 31393 ^ 16689559)) >> (25775 ^ -15507 ^ 8451 ^ -31023);
      int green = (clr & (102418 ^ 122603 ^ 14162 ^ '蚫')) >> (14098 ^ -19675 ^ 24153 ^ -9626);
      int blue = clr & (12920 ^ -20578 ^ 14142 ^ -21977);
      return new Color(red, green, blue);
   }

   public BlockPos newBlockPos(int x, int y, int z) {
      return new BlockPos(x, y, z);
   }

   public ClickType getClickType(String type) {
      return ClickType.valueOf(type);
   }
}
