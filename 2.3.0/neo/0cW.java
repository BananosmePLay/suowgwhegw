package neo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Optional;
import net.minecraft.util.EnumHand;

public class 0cW {
   public final 0a bot;
   public final List<0cX> frames;
   public String hash;
   public BufferedImage captcha;
   private static String _DSC GG NEOWARECLIENT _;

   private static BufferedImage VOoViJATzp(0cW var0) {
      return var0.captcha;
   }

   private static 0a Hxr2qer7vN(0cW var0) {
      return var0.bot;
   }

   private static 0a AQW0mL7a6r(0cW var0) {
      return var0.bot;
   }

   private static 0cp TG9oktBI2g() {
      return 0bI.field_m;
   }

   public 0a getBot() {
      return 9A9dNOUIPv(this);
   }

   private static List QeSvdrQ3st(0cW var0) {
      return var0.frames;
   }

   private static BufferedImage OBLpgBTcuD(0cW var0) {
      return var0.captcha;
   }

   private static 0bl _oHbqw4A1b/* $FF was: 6oHbqw4A1b*/(0f var0) {
      return var0.connection;
   }

   public String getHash() {
      return 8198rn4Ba2(this);
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String _5GI65tWCf/* $FF was: 25GI65tWCf*/(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 24405 ^ -6678 ^ 26667 ^ -11628; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 17843 ^ -21525 ^ 12714 ^ -8855));
      }

      return var1.toString();
   }

   private static 0cp HJSSWo7Sca() {
      return 0bF.field_d;
   }

   public BufferedImage getCaptcha() {
      return VOoViJATzp(this);
   }

   private static 0a TweDj1rq6I(0cW var0) {
      return var0.bot;
   }

   public void rotateFrame(int a, int b) {
      this.rotateFrame(a, b, 31589 ^ -20098 ^ 22749 ^ -27964);
   }

   private static 0f clqct9t6JI(0a var0) {
      return var0.player;
   }

   private static 0a wilzqNWgJb(0cW var0) {
      return var0.bot;
   }

   private static BufferedImage IFlYjrJiue(0cW var0) {
      return var0.captcha;
   }

   private static void qnLQWl6ij1(0cW var0, String var1) {
      var0.hash = var1;
   }

   private void updateHash() {
      qnLQWl6ij1(this, 0o.method_be(OBLpgBTcuD(this)));
   }

   private static EnumHand Ha6FG73uS1() {
      return EnumHand.MAIN_HAND;
   }

   public List<0cX> getFrames() {
      return stYHQUOty6(this);
   }

   public void rotateFrame(int c, int d, int e) {
      0o.method_bc(IFlYjrJiue(this), c, d);
      this.updateHash();
      if (!this.isMap() && wilzqNWgJb(this).isOnline() && this.getFrame(c, d) != null) {
         Tp b;
         if (e == (31690 ^ -29173 ^ 1804 ^ -3380)) {
            b = new Tp(this.getFrame(c, d).getId());
         } else {
            b = new Tp(this.getFrame(c, d).getId(), Ha6FG73uS1());
         }

         6oHbqw4A1b(Z24vTubnR6(TweDj1rq6I(this))).sendPacket(b);
      }

   }

   private static String _198rn4Ba2/* $FF was: 8198rn4Ba2*/(0cW var0) {
      return var0.hash;
   }

   private static 0a NZMFIN2AVW(0cW var0) {
      return var0.bot;
   }

   public boolean isMap() {
      return (boolean)(ea1aa287SQ(this).size() == 0 ? 1007 ^ -12641 ^ 1073 ^ -14016 : 21265 ^ -16978 ^ 7333 ^ -3558);
   }

   public _cW/* $FF was: 0cW*/(BufferedImage a, List<0cX> b, 0a c) {
      this.captcha = a;
      this.frames = b;
      this.bot = c;
      this.updateHash();
   }

   private static List ea1aa287SQ(0cW var0) {
      return var0.frames;
   }

   private static 0f Z24vTubnR6(0a var0) {
      return var0.player;
   }

   private static 0a _A9dNOUIPv/* $FF was: 9A9dNOUIPv*/(0cW var0) {
      return var0.bot;
   }

   private static File _cwtwflVny/* $FF was: 0cwtwflVny*/(nC var0) {
      return var0.gameDir;
   }

   public 0cX getFrame(int a, int b) {
      Optional<0cX> c = QeSvdrQ3st(this).stream().filter((cx) -> {
         return (boolean)(cx.getX() == a && cx.getY() == b ? 467 ^ -21769 ^ 30405 ^ -8736 : 11389 ^ -4537 ^ 31293 ^ -18425);
      }).findFirst();
      return (0cX)c.orElse((Object)null);
   }

   private static List stYHQUOty6(0cW var0) {
      return var0.frames;
   }

   public void sendAnswer(String a) {
      if (TG9oktBI2g().method_bna()) {
         0o.method_bb(this.getCaptcha(), new File(0cwtwflVny(nC.getMinecraft()), 25GI65tWCf("ʴ˕˾˴ˌ˺˩˾ʴ˖˺˵ˮ˺˷˓˾˷˫˾˩ʴ˨˺˭˾˿ʴ") + a + 25GI65tWCf("ʵ˫˵˼")));
      }

      if (AQW0mL7a6r(this).isOnline()) {
         clqct9t6JI(Hxr2qer7vN(this)).sendChatMessage(a);
         if (HJSSWo7Sca().method_bna()) {
            String var10000 = 25GI65tWCf("˹˴˯ʵ˸˺˫˯˸˳˺ʵ˨˴˷˭˾˿");
            Object[] var10001 = new Object[16678 ^ -31851 ^ 19212 ^ -30275];
            var10001[28862 ^ -11696 ^ 15978 ^ -25468] = NZMFIN2AVW(this).getNickname();
            var10001[24640 ^ -17819 ^ 12548 ^ -5344] = a;
            0ek.addMessage(0cT.method_byW(var10000, var10001));
         }
      }

   }
}
