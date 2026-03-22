package neo;

public class 0cw extends 0cB {
   public static 0bz swingSpeed;
   public static 0bz spinSpeed;
   public static 0by anim;
   public static 0bz speed;
   public static 0bz wrapSpeed;

   static {
      String var10002 = 2d6r9Dbvs9("r_Z^R$Z-");
      String var10003 = 2d6r9Dbvs9("ЦЇЄЃЗЎЖ");
      String[] var10004 = new String[14818 ^ -6568 ^ 27544 ^ -19414];
      var10004[31622 ^ -6449 ^ 17932 ^ -9403] = 2d6r9Dbvs9("РЎЍЁЉ");
      var10004[31353 ^ -20258 ^ 23509 ^ -28301] = 2d6r9Dbvs9("бЕЋЌЅ");
      var10004[18955 ^ -30050 ^ 21857 ^ -27146] = 2d6r9Dbvs9("бЕЋВЇ");
      var10004[19217 ^ -23818 ^ 6555 ^ -3969] = 2d6r9Dbvs9("ФЃВ");
      var10004[15629 ^ -22724 ^ 15707 ^ -22674] = 2d6r9Dbvs9("еАЃВ");
      var10004[24791 ^ -28462 ^ 26708 ^ -26540] = 2d6r9Dbvs9("бВЋЌ");
      var10004[1126 ^ -22243 ^ 28466 ^ -15793] = 2d6r9Dbvs9("Л");
      var10004[28172 ^ -10946 ^ 27177 ^ -12004] = 2d6r9Dbvs9("ЬЍЌЇ");
      anim = new 0by(var10002, var10003, var10004);
      speed = new 0bz(2d6r9Dbvs9("бЕЋЌЅт#X\\\"\\# ."), Float.intBitsToFloat(27802 ^ 114309 ^ 24724 ^ 1033807745 ^ 2659 ^ 85548 ^ '饻' ^ 2090780734), Float.intBitsToFloat(28430 ^ '꽁' ^ 20663 ^ -1720836922 ^ 7788 ^ '蒂' ^ 28716 ^ -1494325508), Float.intBitsToFloat(233710 ^ 244985 ^ 8033 ^ 874089985 ^ 232940 ^ '뫩' ^ 262096 ^ 1976661410), Float.intBitsToFloat(121955 ^ 115930 ^ 26633 ^ 1303022743 ^ 124465 ^ 3239 ^ 123961 ^ 1915417224));
      swingSpeed = new 0bz(2d6r9Dbvs9("ФЃВт#X\\\"\\# ."), Float.intBitsToFloat(8167 ^ 475906 ^ 514171 ^ 809725439 ^ 517984 ^ 481529 ^ 11664 ^ 1910733672), Float.intBitsToFloat(25020 ^ 505671 ^ 50 ^ -1331434281 ^ 30448 ^ 30862 ^ 4571 ^ -242997829), Float.intBitsToFloat(22178 ^ 111351 ^ 15285 ^ 124181576 ^ 24792 ^ 115487 ^ 4802 ^ 1162261165), Float.intBitsToFloat(11892 ^ 'ꅗ' ^ '쯾' ^ -1725429632 ^ '\udce1' ^ 996604 ^ 21518 ^ -1498943410));
      wrapSpeed = new 0bz(2d6r9Dbvs9("еАЃВт#X\\\"\\# ."), Float.intBitsToFloat(127800 ^ 24283 ^ 121354 ^ -244538064 ^ 8292 ^ 100987 ^ 130097 ^ -1337162505), Float.intBitsToFloat('\uf45b' ^ '칚' ^ 20897 ^ 1082793809 ^ '\ueefc' ^ '\ue5a0' ^ 10768 ^ 9065917), Float.intBitsToFloat(108909 ^ 102363 ^ 26436 ^ -780883456 ^ 129444 ^ 123293 ^ 30650 ^ -1865120655), Float.intBitsToFloat('ﳑ' ^ 30632 ^ '쳦' ^ 218563456 ^ 3402 ^ 105847 ^ 12489 ^ 847701227));
      spinSpeed = new 0bz(2d6r9Dbvs9("бВЋЌт#X\\\"\\# ."), Float.intBitsToFloat(25172 ^ 105573 ^ 119101 ^ 1048087845 ^ 28093 ^ '멯' ^ 7467 ^ 2128111824), Float.intBitsToFloat(519522 ^ 499982 ^ 23461 ^ 1482943197 ^ 495480 ^ 507581 ^ 944 ^ 409194337), Float.intBitsToFloat(13292 ^ 231631 ^ 247773 ^ 1339667936 ^ 235 ^ 20919 ^ 7877 ^ 251233927), Float.intBitsToFloat(257408 ^ 251293 ^ 5856 ^ -61224664 ^ 241654 ^ 233101 ^ 16788 ^ -1009154246));
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String _d6r9Dbvs9/* $FF was: 2d6r9Dbvs9*/(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 2976 ^ -20480 ^ 16263 ^ -31705; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 30461 ^ -23324 ^ 18112 ^ -28485));
      }

      return var1.toString();
   }

   public _cw/* $FF was: 0cw*/() {
      super(2d6r9Dbvs9("ЫЖЇЏУЌЋЏ"), 0bV.Render);
      0bC[] var10001 = new 0bC[20760 ^ -19007 ^ 3187 ^ -5969];
      var10001[10014 ^ -12297 ^ 30207 ^ -25322] = anim;
      var10001[30154 ^ -19131 ^ 25123 ^ -23891] = speed;
      var10001[21551 ^ -23553 ^ 27327 ^ -25235] = swingSpeed;
      var10001[11557 ^ -18538 ^ 12359 ^ -21769] = wrapSpeed;
      var10001[18779 ^ -2050 ^ 19061 ^ -2860] = spinSpeed;
      this.addSetting(var10001);
   }

   @0X
   public void onSidePerson(0M event) {
   }

   @0X
   public void onUpdate(0K event) {
   }
}
