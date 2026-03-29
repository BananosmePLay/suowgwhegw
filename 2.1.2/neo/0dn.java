package neo;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Optional;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;

public class 0dn {
   public BufferedImage captcha;
   public final List<0dp> frames;
   public final String hash;
   public final 0cC pbot;

   private static List _yWjNqmjlP/* $FF was: 5yWjNqmjlP*/(0dn var0) {
      return var0.frames;
   }

   private static boolean YORglFMYnw(0bv var0) {
      return var0.value;
   }

   public String getHash() {
      return ocJAjb6XSx(this);
   }

   public void sendAnswer(String text) {
      if (Gw0qNjWvjx(Sgw7sMFrlQ())) {
         0dt.saveImage(this.getCaptcha(), new File(1MNSxIu2aB(Minecraft.getMinecraft()), oqVq293cFn("ܕݴݟݕݭݛ݈ݟܕݷݛݔݏݛݖݲݟݖ݊ݟ݈ܕ݉ݛ\u074cݟݞܕ") + text + oqVq293cFn("ܔ݊ݔݝ")));
      }

      if (1mYfwDv0xS(this).isOnline()) {
         zA6S6TBgYL(this).sendMessage(text);
         if (YORglFMYnw(fMiwUS7uOf())) {
            0co.notify(yR89CQqVcd() + oqVq293cFn("ݹݛ݊ݎݙݒݛ"), oqVq293cFn("̫̄\u0378ܚ") + e7oRhFqoOY(this).getNickname() + oqVq293cFn("ܚ̤\u0378̈̏\u0378ܚ") + text, NsB0WtYfyF(), 929 ^ -21660 ^ 22059 ^ -278);
         }

         if (YTr87wI0SY(oyqAWW6bry())) {
            0dK.formatMsg(oqVq293cFn("̠̊̅ͽ̊ܚ̎́͵ܚ") + TJdZroXw8q(this).getNickname() + oqVq293cFn("ܚͺ̏Ͳ̏̇̊ܛܚ̤\u0378̈̏\u0378ܚ") + text);
         }
      }

   }

   public _dn/* $FF was: 0dn*/(String hash, BufferedImage captcha, List<0dp> frames, 0cC pbot) {
      this.hash = hash;
      this.captcha = captcha;
      this.frames = frames;
      this.pbot = pbot;
   }

   private static File _MNSxIu2aB/* $FF was: 1MNSxIu2aB*/(Minecraft var0) {
      return var0.gameDir;
   }

   private static List Qw36MiNFLO(0dn var0) {
      return var0.frames;
   }

   private static 0cC zA6S6TBgYL(0dn var0) {
      return var0.pbot;
   }

   private static 0bv oyqAWW6bry() {
      return 0cc.captcha;
   }

   private static 0cm NsB0WtYfyF() {
      return 0cm.SUCCESS;
   }

   public BufferedImage getCaptcha() {
      return qWjrBajjNA(this);
   }

   private static TextFormatting yR89CQqVcd() {
      return TextFormatting.GREEN;
   }

   private static EnumHand yfJrz5TObG() {
      return EnumHand.MAIN_HAND;
   }

   private static boolean YTr87wI0SY(0bv var0) {
      return var0.value;
   }

   private static 0cC bNGAPKnLMn(0dn var0) {
      return var0.pbot;
   }

   private static 0cL Av746K1lWp(0cD var0) {
      return var0.connection;
   }

   private static 0bv fMiwUS7uOf() {
      return 0cc.notifications;
   }

   private static 0cC TJdZroXw8q(0dn var0) {
      return var0.pbot;
   }

   private static boolean Gw0qNjWvjx(0bv var0) {
      return var0.value;
   }

   public 0dp getFrame(int x, int y) {
      Optional<0dp> frameOptional = 5yWjNqmjlP(this).stream().filter((frame) -> {
         return (boolean)(frame.getX() == x && frame.getY() == y ? 949 ^ -17798 ^ 29593 ^ -13737 : 9660 ^ -29361 ^ 12886 ^ -25947);
      }).findFirst();
      return (0dp)frameOptional.orElse((Object)null);
   }

   public 0cC getPBot() {
      return q7sF1dWgWl(this);
   }

   public boolean isMap() {
      return (boolean)(Qw36MiNFLO(this).size() == 0 ? 9721 ^ -23758 ^ 17521 ^ -15685 : 15917 ^ -2501 ^ 2391 ^ -16063);
   }

   private static List _9NId1TZIx/* $FF was: 79NId1TZIx*/(0dn var0) {
      return var0.frames;
   }

   public void rotateFrame(int x, int y) {
      0dt.rotateFrame(this.getCaptcha(), x, y);
      if (!this.isMap() && iq2TxtMHSS(this).isOnline()) {
         Av746K1lWp(M6aJs1W9oG(bNGAPKnLMn(this))).sendPacket(new CPacketUseEntity(this.getFrame(x, y).getId(), yfJrz5TObG()));
      }

   }

   private static String ocJAjb6XSx(0dn var0) {
      return var0.hash;
   }

   private static 0cC iq2TxtMHSS(0dn var0) {
      return var0.pbot;
   }

   private static BufferedImage qWjrBajjNA(0dn var0) {
      return var0.captcha;
   }

   public void setCaptcha(BufferedImage captcha) {
      uwoTA3BRIl(this, captcha);
   }

   private static void uwoTA3BRIl(0dn var0, BufferedImage var1) {
      var0.captcha = var1;
   }

   private static 0bv Sgw7sMFrlQ() {
      return 0ce.saveCaptcha;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String oqVq293cFn(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 8381 ^ -456 ^ 10930 ^ -3017; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 31257 ^ -22386 ^ 3141 ^ -9752));
      }

      return var1.toString();
   }

   private static 0cC _mYfwDv0xS/* $FF was: 1mYfwDv0xS*/(0dn var0) {
      return var0.pbot;
   }

   public List<0dp> getFrames() {
      return 79NId1TZIx(this);
   }

   private static 0cD M6aJs1W9oG(0cC var0) {
      return var0.player;
   }

   private static 0cC e7oRhFqoOY(0dn var0) {
      return var0.pbot;
   }

   private static 0cC q7sF1dWgWl(0dn var0) {
      return var0.pbot;
   }
}
