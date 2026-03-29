package neo;

import net.minecraft.client.Minecraft;

public class 0cg extends 0cB {
   public static 0bv wiki = new 0bv(77QPs9iXC5("ӧӆӌҹӂӈӏӂӀ"), (boolean)(18310 ^ -12059 ^ 16769 ^ -10525));
   public static 0bv snow = new 0bv(77QPs9iXC5("әӅӍӋ"), (boolean)(7195 ^ -9758 ^ 29122 ^ -19397));

   // $FF: synthetic method
   // $FF: bridge method
   private static String _7QPs9iXC5/* $FF was: 77QPs9iXC5*/(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 6286 ^ -16606 ^ 6161 ^ -16451; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 16430 ^ -22632 ^ 23519 ^ -17263));
      }

      return var1.toString();
   }

   public void onEnable() {
      super.onEnable();
      2JOJbTTmTt().displayGuiScreen(new 0br());
      this.toggle();
   }

   private static Minecraft _JOJbTTmTt/* $FF was: 2JOJbTTmTt*/() {
      return mc;
   }

   public _cg/* $FF was: 0cg*/() {
      super(77QPs9iXC5("»\u0094\u0091\u009b\u0093¿\u008d\u0091"), 0bV.Other);
      this.moduleKey = 17116 ^ -17221 ^ 19126 ^ -19225;
      0bC[] var10001 = new 0bC[20873 ^ -20624 ^ 1597 ^ -1850];
      var10001[28355 ^ -8819 ^ 6134 ^ -23368] = snow;
      var10001[28338 ^ -21572 ^ 15733 ^ -1926] = wiki;
      this.addSetting(var10001);
   }
}
