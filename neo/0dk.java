package neo;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;

public class 0dk {
   public static BlockPos[] getFigureByName(String name) {
      int botsCount = 0cC.getOnline().size();
      String var2 = name.toLowerCase();
      int var3 = -4531 ^ -26314 ^ 12490 ^ -18354;
      switch (var2.hashCode()) {
         case 3321844:
            if (var2.equals(DdwSLBrZW2("ަޣޤޯ"))) {
               var3 = 19767 ^ -25932 ^ 25354 ^ -19319;
            }
            break;
         case 44880289:
            if (var2.equals(DdwSLBrZW2("ߧަޣޤޯ"))) {
               var3 = 25534 ^ -21974 ^ 2575 ^ -15462;
            }
      }

      switch (var3) {
         case 0:
            BlockPos[] positions1 = new BlockPos[botsCount];

            for(int index = 12030 ^ -15624 ^ 26239 ^ -30087; index < botsCount; ++index) {
               positions1[index] = new BlockPos(index, 1939 ^ -17283 ^ 23873 ^ -6481, 10760 ^ -4198 ^ 30415 ^ -19619);
            }

            return positions1;
         case 1:
            BlockPos[] positions2 = new BlockPos[botsCount];

            for(int index = 11791 ^ -13084 ^ 28822 ^ -28035; index < botsCount; ++index) {
               positions2[index] = new BlockPos(29241 ^ -18536 ^ 18440 ^ -29271, 16034 ^ -20546 ^ 5447 ^ -31653, index);
            }

            return positions2;
         default:
            try {
               String content;
               if (!name.startsWith(DdwSLBrZW2("ޢ\u07be\u07be\u07ba\u07b9߰ߥߥ")) && !name.startsWith(DdwSLBrZW2("ޢ\u07be\u07be\u07ba߰ߥߥ"))) {
                  content = 0en.readUsingFiles(new File(o2FgDObhRl(Minecraft.getMinecraft()), DdwSLBrZW2("ߥބޯޥޝޫ\u07b8ޯߥެޣޭ\u07bf\u07b8ޯ\u07b9ߥ") + name + DdwSLBrZW2("ߤޠ\u07b9ޥޤ")));
               } else {
                  content = Jsoup.connect(name).ignoreContentType((boolean)(20456 ^ -5643 ^ 7461 ^ -17607)).get().text();
               }

               if (content != null) {
                  JSONObject figureData = new JSONObject(content);
                  int centerX = figureData.getInt(DdwSLBrZW2("ީ\u07b2"));
                  int centerY = figureData.getInt(DdwSLBrZW2("ީ\u07b3"));
                  JSONArray matrix = figureData.getJSONArray(DdwSLBrZW2("ާޫ\u07be\u07b8ޣ\u07b2"));
                  ArrayList<BlockPos> positions3 = new ArrayList();

                  for(int row = 1836 ^ -29672 ^ 30087 ^ -333; row < matrix.length(); ++row) {
                     String data = matrix.getString(row);
                     Ee947dJnCv().println(data);
                     char[] types = data.toCharArray();

                     for(int col = 15342 ^ -1840 ^ 6471 ^ -9607; col < types.length; ++col) {
                        if (types[col] == (556 ^ -16769 ^ 1069 ^ -18339)) {
                           positions3.add(new BlockPos(col - centerX, 30438 ^ -4540 ^ 10834 ^ -19728, row - centerY));
                        }
                     }
                  }

                  return (BlockPos[])positions3.toArray(new BlockPos[30713 ^ -14183 ^ 32376 ^ -16104]);
               }
            } catch (Exception var16) {
               var16.printStackTrace();
            }

            return null;
      }
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String DdwSLBrZW2(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 22773 ^ -16972 ^ 29136 ^ -27503; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 16186 ^ -31783 ^ 8230 ^ -25841));
      }

      return var1.toString();
   }

   private static PrintStream Ee947dJnCv() {
      return System.out;
   }

   private static File o2FgDObhRl(Minecraft var0) {
      return var0.gameDir;
   }

   public _dk/* $FF was: 0dk*/() {
   }
}
