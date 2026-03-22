package neo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.imageio.ImageIO;

public class 0dt {
   public static void saveImage(BufferedImage image, File file) {
      try {
         ImageIO.write(image, AgrVIPHpY1("Ɇɘɑ"), file);
      } catch (IOException var3) {
      }

   }

   public static String md5(String md5) {
      try {
         MessageDigest md = MessageDigest.getInstance(AgrVIPHpY1("ɛɒȣ"));
         byte[] array = md.digest(md5.getBytes());
         StringBuilder sb = new StringBuilder();
         byte[] var4 = array;
         int var5 = array.length;

         for(int var6 = 20239 ^ -12408 ^ 13625 ^ -19010; var6 < var5; ++var6) {
            byte b = var4[var6];
            sb.append(Integer.toHexString(b & (21061 ^ -9667 ^ 12269 ^ -22678) | 29710 ^ -29259 ^ 2653 ^ -3354).substring(15856 ^ -30729 ^ 23012 ^ -7198, 27733 ^ -12999 ^ 13648 ^ -27585));
         }

         return sb.toString();
      } catch (NoSuchAlgorithmException var8) {
         return null;
      }
   }

   public static double calculateDifference(BufferedImage img1, BufferedImage img2) {
      int width = img1.getWidth();
      int height = img1.getHeight();
      int[] pixels1 = new int[width * height];
      int[] pixels2 = new int[width * height];
      img1.getRGB(28041 ^ -25449 ^ 13766 ^ -15144, 30955 ^ -13004 ^ 17575 ^ -3720, width, height, pixels1, 12252 ^ -12404 ^ 31220 ^ -26204, width);
      img2.getRGB(774 ^ -23110 ^ 8804 ^ -31528, 11497 ^ -10919 ^ 30684 ^ -29076, width, height, pixels2, 12341 ^ -3266 ^ 2341 ^ -13778, width);
      double totalDiff = Double.longBitsToDouble(-4668555346835154911L ^ -4668555346835154911L);

      for(int i = 8868 ^ -30608 ^ 29199 ^ -10021; i < pixels1.length; ++i) {
         int pixel1 = pixels1[i];
         int pixel2 = pixels2[i];
         int r1 = pixel1 >> (16861 ^ -23811 ^ 4109 ^ -3267) & (29948 ^ -27729 ^ 8236 ^ -14464);
         int g1 = pixel1 >> (12444 ^ -26233 ^ 27181 ^ -15554) & (22938 ^ -4467 ^ 13612 ^ -32060);
         int b1 = pixel1 & (30531 ^ -28346 ^ 19283 ^ -21079);
         int r2 = pixel2 >> (11722 ^ -6037 ^ 26017 ^ -24560) & (19669 ^ -15206 ^ 8656 ^ -22176);
         int g2 = pixel2 >> (24480 ^ -28040 ^ 233 ^ -12999) & (16538 ^ -13198 ^ 3371 ^ -32452);
         int b2 = pixel2 & (4186 ^ -15157 ^ 8297 ^ -3065);
         totalDiff += (double)(Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1 - b2));
      }

      return totalDiff;
   }

   public _dt/* $FF was: 0dt*/() {
   }

   public static BufferedImage rotateImage(BufferedImage image, double angle) {
      int w = image.getWidth();
      int h = image.getHeight();
      BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());
      Graphics2D g2d = rotatedImage.createGraphics();
      g2d.rotate(Math.toRadians(angle), (double)w / Double.longBitsToDouble(-8451921679081576300L ^ -3840235660654188396L), (double)h / Double.longBitsToDouble(2717502775429898345L ^ 7329188793857286249L));
      g2d.drawImage(image, 22896 ^ -1905 ^ 17780 ^ -7029, 20978 ^ -21109 ^ 22863 ^ -23242, (ImageObserver)null);
      g2d.dispose();
      return rotatedImage;
   }

   public static BufferedImage decodeBase64Image(String base64String) {
      try {
         byte[] imageBytes = Base64.getDecoder().decode(base64String);
         return ImageIO.read(new ByteArrayInputStream(imageBytes));
      } catch (IOException var2) {
         IOException e = var2;
         e.printStackTrace();
         return null;
      }
   }

   public static String convertToBase64(BufferedImage image) throws IOException {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ImageIO.write(image, AgrVIPHpY1("ɦɸɱ"), baos);
      byte[] bytes = baos.toByteArray();
      String base64 = Base64.getEncoder().encodeToString(bytes);
      return AgrVIPHpY1("ɲɷɢɷȬɿɻɷɱɳȹɦɸɱȭɴɷɥɳȠȢȺ") + base64;
   }

   private static Color F2RJjaVGov() {
      return Color.WHITE;
   }

   public static BufferedImage toMonochrome(BufferedImage bufferedImage) {
      for(int width = 23804 ^ -2067 ^ 15536 ^ -26719; width < bufferedImage.getWidth(); ++width) {
         for(int height = 8633 ^ -18639 ^ 9581 ^ -19483; height < bufferedImage.getHeight(); ++height) {
            Graphics2D graphics = bufferedImage.createGraphics();
            Color color = new Color(bufferedImage.getRGB(width, height));
            if (color.getRed() > (7630 ^ -30279 ^ 6943 ^ -28780) && color.getGreen() > (23237 ^ -24233 ^ 6065 ^ -4897) && color.getBlue() > (26159 ^ -7630 ^ 10389 ^ -21388)) {
               graphics.setColor(tdlBOLPXFr());
               graphics.drawLine(width, height, width, height);
            } else {
               graphics.setColor(F2RJjaVGov());
               graphics.drawLine(width, height, width, height);
            }
         }
      }

      return bufferedImage;
   }

   public static BufferedImage resizeImage(BufferedImage originalImage, int newWidth) {
      if (originalImage.getWidth() < newWidth) {
         return originalImage;
      } else {
         int originalWidth = originalImage.getWidth();
         int originalHeight = originalImage.getHeight();
         int newHeight = newWidth * originalHeight / originalWidth;
         Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, 1777 ^ -7493 ^ 26131 ^ -32163);
         BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, 7367 ^ -28697 ^ 6508 ^ -30130);
         Graphics2D g = resizedImage.createGraphics();
         g.drawImage(scaledImage, 29719 ^ -16092 ^ 32601 ^ -13718, 14424 ^ -22525 ^ 2435 ^ -26152, (ImageObserver)null);
         g.dispose();
         return resizedImage;
      }
   }

   public static String imageToBase64(BufferedImage image) throws IOException {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      ImageIO.write(image, AgrVIPHpY1("ɦɸɱ"), byteArrayOutputStream);
      return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
   }

   public static String encodeToString(BufferedImage image, String type) {
      String imageString = null;
      ByteArrayOutputStream bos = new ByteArrayOutputStream();

      try {
         ImageIO.write(image, type, bos);
         byte[] imageBytes = bos.toByteArray();
         Base64.Encoder encoder = Base64.getEncoder();
         imageString = encoder.encodeToString(imageBytes);
         bos.close();
      } catch (IOException var6) {
         IOException e = var6;
         e.printStackTrace();
      }

      return imageString;
   }

   public static String imageToHash(BufferedImage image) {
      return md5(encodeToString(image, AgrVIPHpY1("ɦɸɱ")));
   }

   public static BufferedImage compareImages(List<BufferedImage> images) {
      BufferedImage first = (BufferedImage)images.remove(26633 ^ -28889 ^ 2148 ^ -4278);
      int width = first.getWidth();
      int height = first.getHeight();
      Set<Integer> colors = new HashSet();

      for(int y = 16678 ^ -25130 ^ 7574 ^ -16026; y < (31867 ^ -9692 ^ 5883 ^ -20313); ++y) {
         for(int x = 25423 ^ -24796 ^ 29476 ^ -28849; x < (17025 ^ -6070 ^ 29570 ^ -9910); ++x) {
            colors.add(first.getRGB(x, y));
         }
      }

      Iterator var11 = images.iterator();

      while(var11.hasNext()) {
         BufferedImage image = (BufferedImage)var11.next();

         for(int y = 16789 ^ -1913 ^ 19094 ^ -3196; y < height; ++y) {
            for(int x = 28815 ^ -3607 ^ 15348 ^ -17774; x < width; ++x) {
               int colorF = first.getRGB(x, y);
               if (colors.contains(colorF)) {
                  int colorL = image.getRGB(x, y);
                  if (colorF != colorL) {
                     first.setRGB(x, y, colorL);
                  }
               }
            }
         }
      }

      return first;
   }

   public static void rotateFrame(BufferedImage image, int frameX, int frameY) {
      frameX *= 21719 ^ -19227 ^ 30185 ^ -27301;
      frameY *= 27393 ^ -22781 ^ 26120 ^ -21878;
      Graphics2D g2d = image.createGraphics();
      g2d.drawImage(rotateImage(image.getSubimage(frameX, frameY, 29791 ^ -17333 ^ 23629 ^ -27431, 3492 ^ -17271 ^ 2856 ^ -17787), Double.longBitsToDouble(-3967171477089056047L ^ -8599827381281387823L)), frameX, frameY, (ImageObserver)null);
      g2d.dispose();
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String AgrVIPHpY1(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 10510 ^ -8208 ^ 23592 ^ -21802; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 3989 ^ -17882 ^ 22330 ^ -8033));
      }

      return var1.toString();
   }

   private static Color tdlBOLPXFr() {
      return Color.BLACK;
   }
}
