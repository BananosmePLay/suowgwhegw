package neo;

public class 0bb {
   public String password;
   public String mask;
   public 0ba status;
   public final String username;

   public void setStatus(0ba status) {
      nW1LGKeoPN(this, status);
   }

   private static String XpdSl4cZeS(0bb var0) {
      return var0.mask;
   }

   public String getMask() {
      return XpdSl4cZeS(this);
   }

   public _bb/* $FF was: 0bb*/(String username, String password, 0ba status) {
      this(username, password, kowtsYQMbI(""), status);
   }

   public String getPassword() {
      return BV6pGBIYNa(this);
   }

   public void setMask(String mask) {
      SdXowYcQuq(this, mask);
   }

   public _bb/* $FF was: 0bb*/(String username, String password) {
      this(username, password, 0ba.Unchecked);
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String kowtsYQMbI(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 834 ^ -14322 ^ 17359 ^ -30589; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 28459 ^ -26997 ^ 29941 ^ -29721));
      }

      return var1.toString();
   }

   public String getUsername() {
      return tJWt7kQthq(this);
   }

   private static String tJWt7kQthq(0bb var0) {
      return var0.username;
   }

   private static 0ba iWL9vDzz5B(0bb var0) {
      return var0.status;
   }

   private static void qIYWWcfSbe(0bb var0, String var1) {
      var0.password = var1;
   }

   public 0ba getStatus() {
      return iWL9vDzz5B(this);
   }

   public void setPassword(String password) {
      qIYWWcfSbe(this, password);
   }

   private static void SdXowYcQuq(0bb var0, String var1) {
      var0.mask = var1;
   }

   private static String BV6pGBIYNa(0bb var0) {
      return var0.password;
   }

   public _bb/* $FF was: 0bb*/(String username, String password, String mask, 0ba status) {
      this.username = username;
      this.password = password;
      this.mask = mask;
      this.status = status;
   }

   private static void nW1LGKeoPN(0bb var0, 0ba var1) {
      var0.status = var1;
   }
}
