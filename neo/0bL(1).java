package neo;

public class 0bL {
   public final 0do captchaManager;
   public static 0bL instance;
   public final 0dw nickManager;
   public final 0dx proxyManager;

   public static 0bL getInstance() {
      return cSEwdqWGmW();
   }

   private static 0bL cSEwdqWGmW() {
      return instance;
   }

   private static 0dw EbRLWovRPZ(0bL var0) {
      return var0.nickManager;
   }

   public _bL/* $FF was: 0bL*/() {
      instance = this;
      this.proxyManager = new 0dx();
      this.nickManager = new 0dw();
      this.captchaManager = new 0do();
      0du.create();
   }

   private static 0do _dDVbHtIjr/* $FF was: 4dDVbHtIjr*/(0bL var0) {
      return var0.captchaManager;
   }

   private static 0dx pe8twIqRy5(0bL var0) {
      return var0.proxyManager;
   }

   public 0do getCaptchaManager() {
      return 4dDVbHtIjr(this);
   }

   public 0dx getProxyManager() {
      return pe8twIqRy5(this);
   }

   public 0dw getNickManager() {
      return EbRLWovRPZ(this);
   }
}
