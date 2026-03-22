package neo;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import net.minecraft.client.Minecraft;

public class 0dw {
   public final ArrayList<String> nickList = new ArrayList();
   public int fileIndex;

   private static ArrayList rtZbSvI2qj(0dw var0) {
      return var0.nickList;
   }

   private static ArrayList r4KVnJbods(0dw var0) {
      return var0.nickList;
   }

   private static void RVbjpShtDt(0dw var0, int var1) {
      var0.fileIndex = var1;
   }

   private static 0bA grwvYrGXgC() {
      return 0cd.customname;
   }

   private static ArrayList gFEy26Y9Wz(0dw var0) {
      return var0.nickList;
   }

   public ArrayList<String> getNickList() {
      return gFEy26Y9Wz(this);
   }

   private static File _1L5FJ212o/* $FF was: 21L5FJ212o*/(Minecraft var0) {
      return var0.gameDir;
   }

   private static ArrayList juLjB5IJgv(0dw var0) {
      return var0.nickList;
   }

   public String getBotName() {
      String var1 = AFJablWro7().get();
      int var2 = -1301 ^ -11137 ^ 2664 ^ -9469;
      switch (var1.hashCode()) {
         case -1180434330:
            if (var1.equals(TTvBtoPf6P("ǲǆǛǙǲǝǘǑ"))) {
               var2 = 24297 ^ -8711 ^ 5261 ^ -26721;
            }
            break;
         case -792413163:
            if (var1.equals(TTvBtoPf6P("ǺǑǛǣǕǆǑ"))) {
               var2 = 29943 ^ -30532 ^ 17998 ^ -17915;
            }
            break;
         case 2029746065:
            if (var1.equals(TTvBtoPf6P("ǷǁǇǀǛǙ"))) {
               var2 = 13456 ^ -4506 ^ 148 ^ -9629;
            }
      }

      switch (var2) {
         case 0:
            return TTvBtoPf6P("ǺǑǛǣǕǆǑǫ") + 0ej.randomNumber(25898 ^ -32349 ^ 5628 ^ -3728);
         case 1:
            return 0dy.format(grwvYrGXgC().get());
         case 2:
            if (6e4F7hKbno(this).size() == 0) {
               return TTvBtoPf6P("ǑǙǄǀǍ");
            }

            if (PF47A5rgXJ(this) >= r4KVnJbods(this).size()) {
               RVbjpShtDt(this, 2368 ^ -28727 ^ 3525 ^ -29876);
            }

            String fromFile = (String)IkSiQcHJWE(this).get(QIm416ZjML(this));
            NaQQnCLgHV(this, Wn7V6YwT6Q(this) + (22912 ^ -12990 ^ 9553 ^ -20078));
            return fromFile;
         default:
            return 0ej.randomString(9364 ^ -7998 ^ 22278 ^ -27810);
      }
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String TTvBtoPf6P(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 18756 ^ -13294 ^ 25079 ^ -7007; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 15575 ^ -297 ^ 22116 ^ -27184));
      }

      return var1.toString();
   }

   private static ArrayList IkSiQcHJWE(0dw var0) {
      return var0.nickList;
   }

   public _dw/* $FF was: 0dw*/() {
   }

   private static int Wn7V6YwT6Q(0dw var0) {
      return var0.fileIndex;
   }

   private static int PF47A5rgXJ(0dw var0) {
      return var0.fileIndex;
   }

   private static ArrayList anKYjI7Ft3(0dw var0) {
      return var0.nickList;
   }

   private static int QIm416ZjML(0dw var0) {
      return var0.fileIndex;
   }

   private static ArrayList _e4F7hKbno/* $FF was: 6e4F7hKbno*/(0dw var0) {
      return var0.nickList;
   }

   private static 0by AFJablWro7() {
      return 0cd.nickstype;
   }

   private static void NaQQnCLgHV(0dw var0, int var1) {
      var0.fileIndex = var1;
   }

   public void loadNicks() {
      anKYjI7Ft3(this).clear();
      File nickFile = new File(21L5FJ212o(Minecraft.getMinecraft()), TTvBtoPf6P("ƛǺǑǛǣǕǆǑƛǚǝǗǟǇƛǚǝǗǟǇƚǀǌǀ"));
      this.createFile(nickFile.toPath());

      try {
         Iterator var5 = Files.readAllLines(nickFile.toPath()).iterator();

         while(var5.hasNext()) {
            String nick = (String)var5.next();
            nick = nick.replaceAll(TTvBtoPf6P("ǯǪǕƙǎǵƙǮƄƙƍǫǩ"), TTvBtoPf6P(""));
            if (nick.length() > (1527 ^ -28154 ^ 1 ^ -26637) && nick.length() < (10912 ^ -29460 ^ 7653 ^ -17479)) {
               rtZbSvI2qj(this).add(nick);
            }
         }
      } catch (Exception var4) {
         Exception exception = var4;
         if (exception instanceof NoSuchFileException) {
            0dK.formatMsg(TTvBtoPf6P("\u0590ք֍֏Ɣ\u05f5Ɣ։քփֆք։\u058cցֈƔƒǐƒǘǚǝǗǟǇƚǀǌǀƔƒǒƒǘ։ցƔ։ք֍րց։ƕ"));
         }

         exception.printStackTrace();
         return;
      }

      0dK.formatMsg(TTvBtoPf6P("֣քև״\u05f7ւց։֊Ɣƒǐƒǘ") + juLjB5IJgv(this).size() + TTvBtoPf6P("Ɣƒǒƒǘ։\u058c֎։ց֍ֈ֊ֆ"));
   }

   private void createFile(Path path) {
      try {
         Files.createDirectories(path.getParent());
         if (!path.toFile().exists()) {
            Files.createFile(path);
         }
      } catch (Exception var3) {
         Exception exception = var3;
         exception.printStackTrace();
      }

   }
}
