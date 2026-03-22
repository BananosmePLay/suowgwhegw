package neo;

public class 0bZ extends 0cB {
   public static 0bA customPassword;
   public static 0bA authCommand;
   private static final 0bx register_info;
   public static 0bv needLogin;
   public static 0by passwordGenerator;
   public static 0bA registerCommand = new 0bA(LxWbQVbapE("ߔߣߡ߅ߩ߫߫ߧߨߢ"), LxWbQVbapE("ީߴߣߡ߯ߵ߲ߣߴަޣ߶ߧߵߵަޣ߶ߧߵߵ"));

   private static boolean tjIGd9o2tl(0bv var0) {
      return var0.value;
   }

   private static 0bv _Yw7TO9XB4/* $FF was: 0Yw7TO9XB4*/() {
      return needLogin;
   }

   public _bZ/* $FF was: 0bZ*/() {
      super(LxWbQVbapE("߇߲߳ߩަߔߣߡ߯ߵ߲ߣߴ"), 0bV.Bots);
      0bC[] var10001 = new 0bC[24409 ^ -28008 ^ 27443 ^ -22796];
      var10001[22572 ^ -20039 ^ 4763 ^ -1266] = registerCommand;
      var10001[27306 ^ -16348 ^ 18336 ^ -4817] = register_info;
      var10001[4179 ^ -16827 ^ 14617 ^ -26867] = needLogin;
      var10001[5271 ^ -3474 ^ 30033 ^ -27733] = authCommand;
      var10001[21592 ^ -11105 ^ 16145 ^ -16430] = passwordGenerator;
      var10001[12595 ^ -4503 ^ 3555 ^ -11588] = customPassword;
      this.addSetting(var10001);
   }

   private static 0by FGoijMqeyf() {
      return passwordGenerator;
   }

   static {
      String[] var10002 = new String[20474 ^ -12323 ^ 31049 ^ -1681];
      var10002[8983 ^ -405 ^ 25530 ^ -16698] = LxWbQVbapE("ޣ߶ߧߵߵަαζκγλωγτχωަλζަιζφθνϊ");
      register_info = new 0bx(var10002);
      needLogin = new 0bv(LxWbQVbapE("߈ߣߣߢߊߩߡ߯ߨ"), (boolean)(29645 ^ -27075 ^ 7096 ^ -440));
      authCommand = new 0bA(LxWbQVbapE("߇߲߳߮߅ߩ߫߫ߧߨߢ"), LxWbQVbapE("ީߪߩߡ߯ߨަޣ߶ߧߵߵ"), () -> {
         return tjIGd9o2tl(0Yw7TO9XB4());
      });
      String var0 = LxWbQVbapE("ߖߧߵߵ߱ߩߴߢ");
      String var10003 = LxWbQVbapE("ߴߧߨߢߩ߫");
      String[] var10004 = new String[29115 ^ -19383 ^ 12276 ^ -5628];
      var10004[26076 ^ -3549 ^ 23737 ^ -13498] = LxWbQVbapE("ߥ߳ߵ߲ߩ߫");
      var10004[22225 ^ -6049 ^ 3145 ^ -19770] = LxWbQVbapE("ߨ߯ߥ߭ߒߩߖߧߵߵ");
      passwordGenerator = new 0by(var0, var10003, var10004);
      customPassword = new 0bA(LxWbQVbapE("߅߳ߵ߲ߩ߫ߖߧߵߵ"), LxWbQVbapE("߷߱ߣߴ߲߿\u07b4\u07b7\u07b5"), () -> {
         return FGoijMqeyf().is(LxWbQVbapE("ߥ߳ߵ߲ߩ߫"));
      });
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String LxWbQVbapE(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 25903 ^ -16417 ^ 24707 ^ -17805; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 23933 ^ -26095 ^ 15181 ^ -1113));
      }

      return var1.toString();
   }
}
