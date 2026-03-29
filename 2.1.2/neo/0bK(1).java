package neo;

import de.florianmichael.viamcp.ViaMCP;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;

public class 0bK {
   public 0bL botManager;
   public 0bM commandManager;
   public 0bO themeManager;
   public static String VERSION_TYPE = bVEL1BWcBj("ǓǏǐǏǓ");
   public 0dG seleniumManager;
   public 0dN configManager;
   public 0bj fileManager;
   public 0dB pBotsScriptManager;
   public static 0bK instance;
   public 0bN moduleManager;

   private static void IzQVWgvXNd(0bK var0, 0dN var1) {
      var0.configManager = var1;
   }

   public void startClient() {
      long mls = System.currentTimeMillis();
      vN4aKde6Wl(this);
      0ec.init();
      0el.init();
      0dE.register();
      0bI.loadConfig();
      ayXU1RSImx(this, new 0bM());
      0bJ.setProgress(28597 ^ -22042 ^ 8379 ^ -6422);
      nJwfJ9mF1Y(this, new 0bO());
      I7NN9Irxpo(this, new 0bN());
      IzQVWgvXNd(this, new 0dN());
      wij4G8HCeY(this).getModule(0cf.class).setState((boolean)(3005 ^ -12857 ^ 22173 ^ -28441));
      pdoN7NflvI(this).getModule(0cv.class).setState((boolean)(30407 ^ -12611 ^ 9290 ^ -25551));
      mbZWDf4I7c(this, new 0bj());
      0bJ.setProgress(18383 ^ -28728 ^ 28957 ^ -18151);
      aoc7rS5ryb(this, new 0dB());
      FvIj59bU1B(this, new 0dG());
      DSITwAUXN3(this).init();
      0bJ.setProgress(1731 ^ -8510 ^ 22034 ^ -29161);

      try {
         QrD6TkPQQ1(this).init();
      } catch (IOException var4) {
         IOException e = var4;
         e.printStackTrace();
      }

      ViaMCP.create();
      hL8H4gUg4Q().initAsyncSlider();
      0bJ.setProgress(1951 ^ -10945 ^ 30040 ^ -22531);
      OwD9my6bnl(this, new 0bL());
      if (OlWI7LNvKr() == null) {
         ATSzBUCFAw(MobbWduVcy(getInstance()).getDefaultTheme());
      }

      cIbFkqe1ld().println(bVEL1BWcBj("ƯƄƎƶƀƓƄǁƨƏƒƕƀƏƂƄǁƒƕƀƓƕƄƅǀǁǌǁ") + (System.currentTimeMillis() - mls) + bVEL1BWcBj("ƌƒ"));
   }

   private static void FvIj59bU1B(0bK var0, 0dG var1) {
      var0.seleniumManager = var1;
   }

   private static void nJwfJ9mF1Y(0bK var0, 0bO var1) {
      var0.themeManager = var1;
   }

   public static 0bK getInstance() {
      return 9THF1oKd6I();
   }

   private static void ATSzBUCFAw(0eA var0) {
      0br.currentTheme = var0;
   }

   private static void I7NN9Irxpo(0bK var0, 0bN var1) {
      var0.moduleManager = var1;
   }

   private static 0bK _THF1oKd6I/* $FF was: 9THF1oKd6I*/() {
      return instance;
   }

   private static 0bO MobbWduVcy(0bK var0) {
      return var0.themeManager;
   }

   private static void ayXU1RSImx(0bK var0, 0bM var1) {
      var0.commandManager = var1;
   }

   public _bK/* $FF was: 0bK*/() {
   }

   private static 0dG DSITwAUXN3(0bK var0) {
      return var0.seleniumManager;
   }

   private static 0bN pdoN7NflvI(0bK var0) {
      return var0.moduleManager;
   }

   public void keyPress(int key) {
      Iterator var2 = hoDROBt49L(this).getModules().iterator();

      while(var2.hasNext()) {
         0cB module = (0cB)var2.next();
         if (Q9YySITriJ(module) == key) {
            module.toggle();
         }
      }

   }

   private static void aoc7rS5ryb(0bK var0, 0dB var1) {
      var0.pBotsScriptManager = var1;
   }

   private static void mbZWDf4I7c(0bK var0, 0bj var1) {
      var0.fileManager = var1;
   }

   private static 0bj QrD6TkPQQ1(0bK var0) {
      return var0.fileManager;
   }

   private static void OwD9my6bnl(0bK var0, 0bL var1) {
      var0.botManager = var1;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String bVEL1BWcBj(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 15139 ^ -18793 ^ 15296 ^ -18828; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 12239 ^ -29816 ^ 20897 ^ -3065));
      }

      return var1.toString();
   }

   private static int Q9YySITriJ(0cB var0) {
      return var0.moduleKey;
   }

   private static 0bN hoDROBt49L(0bK var0) {
      return var0.moduleManager;
   }

   private static 0bN wij4G8HCeY(0bK var0) {
      return var0.moduleManager;
   }

   private static void vN4aKde6Wl(0bK var0) {
      instance = var0;
   }

   private static ViaMCP hL8H4gUg4Q() {
      return ViaMCP.INSTANCE;
   }

   private static PrintStream cIbFkqe1ld() {
      return System.out;
   }

   private static 0eA OlWI7LNvKr() {
      return 0br.currentTheme;
   }
}
