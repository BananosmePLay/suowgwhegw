package neo;

import java.io.File;

public class 0cf extends 0cB {
   public static 0bz openDelay;
   private static final 0bx solverType_info;
   public static 0bA webApi;
   private static final 0bx openDelay_info;
   public static 0bA chromePath;
   private static final 0bx useProxy_info;
   public static 0by solverType;
   public static 0bv useProxy;

   // $FF: synthetic method
   // $FF: bridge method
   private static String FwCblq9LWm(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 10531 ^ -28666 ^ 15914 ^ -30961; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 17703 ^ -10893 ^ 9900 ^ -18674));
      }

      return var1.toString();
   }

   public _cf/* $FF was: 0cf*/() {
      super(FwCblq9LWm("ơƓƔƥƙƚƀƓƄ"), 0bV.Bots);
      0bC[] var10001 = new 0bC[17010 ^ -18285 ^ 12645 ^ -13428];
      var10001[30119 ^ -23054 ^ 28639 ^ -16502] = solverType;
      var10001[16038 ^ -20146 ^ 18333 ^ -14220] = solverType_info;
      var10001[19295 ^ -17984 ^ 18252 ^ -18991] = webApi;
      var10001[6529 ^ -23136 ^ 3593 ^ -19925] = chromePath;
      var10001[8631 ^ -294 ^ 26468 ^ -18419] = useProxy;
      var10001[32744 ^ -18726 ^ 20735 ^ -26168] = useProxy_info;
      var10001[4484 ^ -10468 ^ 7159 ^ -8855] = openDelay;
      var10001[26639 ^ -20825 ^ 27566 ^ -21247] = openDelay_info;
      this.addSetting(var10001);
   }

   public void onDisable() {
      super.onDisable();
   }

   private static 0by P4UNGf1Lwj() {
      return solverType;
   }

   public void onEnable() {
      if (vDc2lgll8Z().is(FwCblq9LWm("ƵƞƄƙƛƓ")) && !(new File(0cyNTjJ876().get())).exists()) {
         this.setState((boolean)(21375 ^ -22479 ^ 7273 ^ -6361));
         0dK.formatMsg(FwCblq9LWm("ǐƕשִֵֺǖׂ\u05c8ǖֶׇ׆ֵׁ׃ֶ׆ǖֵ\u05cc׆ׁ׆\u05cbǖ\u05cb׃ׄ׃ֶ\u05cb\u05c8ǚǖ\u05cd\u05ceׇ\u05c8ǖ\u05c8\u05cbǖ\u05cb׃ǖֵַֿ׃ִֵַׄ׃ִǗ"));
      } else {
         super.onEnable();
      }
   }

   static {
      String var10002 = FwCblq9LWm("ƻƙƒƓ");
      String var10003 = FwCblq9LWm("ƵƞƄƙƛƓ");
      String[] var10004 = new String[12661 ^ -8159 ^ 24410 ^ -29169];
      var10004[28815 ^ -32615 ^ 15427 ^ -13227] = FwCblq9LWm("ơƓƔƷƦƿ");
      solverType = new 0by(var10002, var10003, var10004);
      String[] var0 = new String[30484 ^ -14210 ^ 16697 ^ -426];
      var0[435 ^ -3153 ^ 16684 ^ -19664] = FwCblq9LWm("ơƓƔƷƦƿǖǛǖז׆ׇ\u05c8ִ׆ǖƱƳƢǖׁ׆\u05c9ֶ\u05c8ַ׆\u05ca\u05ce");
      var0[4289 ^ -669 ^ 21043 ^ -16496] = FwCblq9LWm("ׂ\u05cdֹǖׄ\u05cb׃־\u05cb\u05ceֳǖׄ׆־\u05ceֳǖ\u05c9ֶ\u05c8ֶׅ׆\u05ca\u05ca");
      var0[5390 ^ -15823 ^ 7631 ^ -13582] = FwCblq9LWm("ַǘ\u05caǘǖƸƓƙơƗƄƓơƟƝƟ");
      var0[25709 ^ -12635 ^ 6992 ^ -20069] = FwCblq9LWm("ƵƞƄƙƛƓǖǛǖקֵׂ׃ִǖ\u05c8ִ\u05ccֶֽׄ׆ִֺǖ\u05cc׆\u05c9ֱֵ");
      var0[4840 ^ -779 ^ 5460 ^ -1203] = FwCblq9LWm("\u05ceַ\u05c9\u05c8\u05cdֵֺֹׁǖֶׇ׆ֵׁ׃ֶǖƵƞƄƙƛƓǖ\u05cb׆ǖ\u05c9\u05cc");
      solverType_info = new 0bx(var0);
      webApi = new 0bA(FwCblq9LWm("ơƓƔƷƦƿǖƞƙƅƂ"), FwCblq9LWm("ƞƂƂƆǌǙǙǇǄǁǘǆǘǆǘǇǌǎǆǎǆ"), () -> {
         return i2fYPkVqgp().is(FwCblq9LWm("ơƓƔƷƦƿ"));
      });
      chromePath = new 0bA(FwCblq9LWm("ƵƞƄƙƛƓǖƦƗƂƞ"), FwCblq9LWm("ƵǌǙƦƄƙƑƄƗƛǖưƟƚƓƅǙƱƙƙƑƚƓǙƵƞƄƙƛƓǙƷƆƆƚƟƕƗƂƟƙƘǙƕƞƄƙƛƓǘƓƎƓ"), () -> {
         return P4UNGf1Lwj().is(FwCblq9LWm("ƵƞƄƙƛƓ"));
      });
      useProxy = new 0bv(FwCblq9LWm("ƣƅƓǖƆƄƙƎƏ"), (boolean)(2681 ^ -19843 ^ 27351 ^ -11566));
      var0 = new String[10620 ^ -12649 ^ 21474 ^ -19446];
      var0[22338 ^ -3467 ^ 19086 ^ -4167] = FwCblq9LWm("ש׆ֶ׆\u05ca׃ִֶǖ\u05c9\u05c8\u05cc׆ֽׁׄ׆׃ִǚǖִֵֵׇׂǖ\u05cd\u05ce");
      var0[23062 ^ -3198 ^ 11352 ^ -31283] = FwCblq9LWm("\u05ceַ\u05c9\u05c8\u05cdֺׁ\u05c8ׄ׆ִַֺֹǖ\u05c9ֶ\u05c8\u05ccַ\u05ceǖ\u05c8ִǖׇ\u05c8ִ׆");
      var0[7638 ^ -13174 ^ 23439 ^ -29999] = FwCblq9LWm("ׂ\u05cdֹǖ\u05c8ִ\u05ccִֶֽ\u05ceֹǖִֶַ׆\u05cb\u05ceְֽ");
      useProxy_info = new 0bx(var0);
      openDelay = new 0bz(FwCblq9LWm("ƹƆƓƘǖƒƓƚƗƏ"), Float.intBitsToFloat(2296 ^ 88962 ^ '꾻' ^ -840900838 ^ 27412 ^ 16828 ^ 1293 ^ -1986327426), Float.intBitsToFloat(26116 ^ '褄' ^ 8909 ^ 2006143682 ^ 497046 ^ '볳' ^ 521943 ^ 895172797), Float.intBitsToFloat(23778 ^ 124875 ^ 12741 ^ -1909684005 ^ 16563 ^ 108531 ^ 32182 ^ -875128639), Float.intBitsToFloat(116377 ^ 105477 ^ 16758 ^ -1731801114 ^ 126894 ^ 94736 ^ 4851 ^ -639214783));
      var0 = new String[14719 ^ -3897 ^ 24253 ^ -26874];
      var0[1876 ^ -29529 ^ 18159 ^ -13028] = FwCblq9LWm("וִַ׆\u05cb\u05c8ׄ\u05cc׆ǖׁ׆ׂ׃ֶ׀\u05cc\u05ceǖ\u05cb׆ǖ\u05c8ִ\u05ccִֶֽ\u05ce׃");
      var0[90 ^ -21229 ^ 31585 ^ -10711] = FwCblq9LWm("ִֶַ׆\u05cb\u05ceְǘǖ\u05eb׃ǖׂ׆ִַǖ\u05c8ִ\u05ccִֶֺֽǖַׄ׃ǖֶַ׆ֵׁ");
      var0[20379 ^ -9900 ^ 6918 ^ -29237] = FwCblq9LWm("׃ַ\u05cd\u05ceǖׁ׆\u05cfׂ׃ִǖ\u05ca\u05cb\u05c8ׅ\u05c8ǖׇ\u05c8ִ\u05c8ׄ");
      openDelay_info = new 0bx(var0);
   }

   private static 0by i2fYPkVqgp() {
      return solverType;
   }

   private static 0by vDc2lgll8Z() {
      return solverType;
   }

   private static 0bA _cyNTjJ876/* $FF was: 0cyNTjJ876*/() {
      return chromePath;
   }
}
