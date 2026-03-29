package neo;

import net.minecraft.client.Minecraft;
import net.minecraft.client.shader.Framebuffer;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;

public class 0ez {
   public static Minecraft mc = Minecraft.getMinecraft();

   private static void BVGOSVq65r(Framebuffer var0, int var1) {
      var0.depthBuffer = var1;
   }

   private static int DEe6u9IQ1L(Minecraft var0) {
      return var0.displayWidth;
   }

   private static Minecraft NgdQ0d2cHJ() {
      return mc;
   }

   public _ez/* $FF was: 0ez*/() {
   }

   private static int _vtDlOKaBT/* $FF was: 4vtDlOKaBT*/(Minecraft var0) {
      return var0.displayHeight;
   }

   public static void setupFBO(Framebuffer framebuffer) {
      EXTFramebufferObject.glDeleteRenderbuffersEXT(BawYVSnY5j(framebuffer));
      int stencilDepthBufferID = EXTFramebufferObject.glGenRenderbuffersEXT();
      EXTFramebufferObject.glBindRenderbufferEXT(31448 ^ '쉺' ^ 31054 ^ 19629, stencilDepthBufferID);
      EXTFramebufferObject.glRenderbufferStorageEXT(8439 ^ '\uf0c2' ^ 30216 ^ 11132, 17004 ^ '驎' ^ 2054 ^ 21725, DEe6u9IQ1L(ToAaASaM14()), 4vtDlOKaBT(qObA9oKknx()));
      EXTFramebufferObject.glFramebufferRenderbufferEXT(14403 ^ 92248 ^ 111162 ^ 28513, 22737 ^ 'ꦃ' ^ 8756 ^ 24134, 24361 ^ '\uf3b8' ^ 32343 ^ 24455, stencilDepthBufferID);
      EXTFramebufferObject.glFramebufferRenderbufferEXT(14967 ^ '쾳' ^ 8864 ^ 23076, 16675 ^ '蝚' ^ 32113 ^ 13832, 9893 ^ '뜐' ^ 13829 ^ 10993, stencilDepthBufferID);
   }

   public static void initStencilToWrite() {
      NgdQ0d2cHJ().getFramebuffer().bindFramebuffer((boolean)(18398 ^ -11024 ^ 2916 ^ -26550));
      checkSetupFBO(oq9II1jh7j().getFramebuffer());
      GL11.glClear('蟞' ^ -7452 ^ '鷮' ^ -812);
      GL11.glEnable(13356 ^ -21142 ^ 12862 ^ -24344);
      GL11.glStencilFunc(6837 ^ -24677 ^ 3126 ^ -29921, 15677 ^ -13398 ^ 6036 ^ -7934, 18973 ^ -761 ^ 2962 ^ -17271);
      GL11.glStencilOp('蘼' ^ 18241 ^ '쾷' ^ 4299, 30974 ^ -30634 ^ 471 ^ -4226, '鼇' ^ -11917 ^ '꺗' ^ -286);
      GL11.glColorMask((boolean)(22666 ^ -24900 ^ 25427 ^ -23195), (boolean)(2831 ^ -30615 ^ 2635 ^ -30419), (boolean)(21042 ^ -29043 ^ 5118 ^ -12479), (boolean)(25173 ^ -858 ^ 20389 ^ -11946));
   }

   private static int rlSyib1EJJ(Framebuffer var0) {
      return var0.depthBuffer;
   }

   private static Minecraft ToAaASaM14() {
      return mc;
   }

   public static void checkSetupFBO(Framebuffer framebuffer) {
      if (framebuffer != null && rlSyib1EJJ(framebuffer) > (-25285 ^ -31879 ^ 22357 ^ -18712)) {
         setupFBO(framebuffer);
         BVGOSVq65r(framebuffer, -28228 ^ -13934 ^ 15914 ^ -26117);
      }

   }

   private static Minecraft qObA9oKknx() {
      return mc;
   }

   private static int BawYVSnY5j(Framebuffer var0) {
      return var0.depthBuffer;
   }

   public static void uninitStencilBuffer() {
      GL11.glDisable('诠' ^ -8913 ^ '\uf880' ^ -23073);
   }

   private static Minecraft oq9II1jh7j() {
      return mc;
   }

   public static void readStencilBuffer(int ref) {
      GL11.glColorMask((boolean)(31898 ^ -12309 ^ 19292 ^ -2004), (boolean)(16853 ^ -10624 ^ 22663 ^ -12333), (boolean)(11569 ^ -20540 ^ 7882 ^ -25538), (boolean)(30854 ^ -12616 ^ 21871 ^ -7344));
      GL11.glStencilFunc(9378 ^ -10873 ^ 7118 ^ -5911, ref, 12463 ^ -9265 ^ 10751 ^ -15714);
      GL11.glStencilOp(23819 ^ -40776 ^ '쟾' ^ -7091, 21879 ^ 13237 ^ 28702 ^ 2268, '蠪' ^ -26439 ^ '숞' ^ -13171);
   }
}
