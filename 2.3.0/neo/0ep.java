package neo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.lwjgl.opengl.GL20;

public class 0ep {
   public boolean field_b;
   public int field_a;
   public int field_d;
   public int field_e;
   public int field_c;
   private static int _DSC GG NEOWARECLIENT _;

   public boolean method_bJx() {
      return method_bJG(this);
   }

   private int method_bJv(InputStream a, int b) throws IOException {
      int c = GL20.glCreateShader(b);
      GL20.glShaderSource(c, this.method_bJw(a));
      GL20.glCompileShader(c);
      int d = GL20.glGetShaderi(c, 7925 ^ '쀮' ^ 7919 ^ 19381);
      if (d == 0) {
         method_bJE().println(GL20.glGetShaderInfoLog(c, GL20.glGetShaderi(c, 11387 ^ 'ꯤ' ^ 27200 ^ 26203)));
         throw new IllegalStateException(method_bJy("ҜһҳҶҿҾӺҮҵӺҹҵҷҪҳҶҿӺҩҲһҾҿҨ"));
      } else {
         return c;
      }
   }

   private static int method_bJC(0ep var0) {
      return var0.field_c;
   }

   public void method_bJu(int a, int b, float c, float d, float e) {
      if (!method_bJz(this)) {
         GL20.glUseProgram(method_bJA(this));
         GL20.glUniform2f(method_bJB(this), (float)a, (float)b);
         GL20.glUniform2f(method_bJC(this), c / (float)a, Float.intBitsToFloat(1890 ^ 6329 ^ 366 ^ 363433040 ^ 24347 ^ 28658 ^ 8962 ^ 707363086) - d / (float)b);
         GL20.glUniform1f(method_bJD(this), e);
      }
   }

   private static boolean method_bJG(0ep var0) {
      return var0.field_b;
   }

   private static Charset method_bJF() {
      return StandardCharsets.UTF_8;
   }

   private static int method_bJB(0ep var0) {
      return var0.field_e;
   }

   private static int method_bJD(0ep var0) {
      return var0.field_a;
   }

   private String method_bJw(InputStream a) throws IOException {
      ByteArrayOutputStream c = new ByteArrayOutputStream();
      byte[] d = new byte[28409 ^ -3885 ^ 4366 ^ -29404];

      int b;
      while((b = a.read(d, 19573 ^ -13043 ^ 9036 ^ -24012, d.length)) != (-21412 ^ -30720 ^ 11404 ^ -2257)) {
         c.write(d, 21739 ^ -7913 ^ 914 ^ -18834, b);
      }

      return new String(c.toByteArray(), method_bJF());
   }

   private static PrintStream method_bJE() {
      return System.err;
   }

   public _ep/* $FF was: 0ep*/(String d) {
      try {
         int a = GL20.glCreateProgram();
         GL20.glAttachShader(a, this.method_bJv(0ep.class.getResourceAsStream(method_bJy("ӵһҩҩҿҮҩӵҷҳҴҿҹҨһҼҮӵҴҿҵҭһҨҿӵҩҲһҾҿҨҩӵҪһҩҩҮҲҨҵүҽҲӴҬҩҲ")), 19835 ^ '鶊' ^ 26190 ^ 15758));
         GL20.glAttachShader(a, this.method_bJv(0ep.class.getResourceAsStream(d), 6668 ^ 70891 ^ 102404 ^ 5587));
         GL20.glLinkProgram(a);
         int b = GL20.glGetProgrami(a, 4325 ^ '胶' ^ 17433 ^ 24456);
         if (b == 0) {
            System.err.println(GL20.glGetProgramInfoLog(a, GL20.glGetProgrami(a, 23605 ^ '긵' ^ 7194 ^ 26014)));
            throw new IllegalStateException(method_bJy("҉ҲһҾҿҨӺҼһҳҶҿҾӺҮҵӺҶҳҴұ"));
         }

         this.field_d = a;
         GL20.glUseProgram(a);
         this.field_a = GL20.glGetUniformLocation(a, method_bJy("Үҳҷҿ"));
         this.field_c = GL20.glGetUniformLocation(a, method_bJy("ҷҵүҩҿ"));
         this.field_e = GL20.glGetUniformLocation(a, method_bJy("ҨҿҩҵҶүҮҳҵҴ"));
         GL20.glUseProgram(19518 ^ -21677 ^ 20703 ^ -18510);
      } catch (IOException var4) {
         IOException c = var4;
         c.printStackTrace();
         this.field_b = (boolean)(9375 ^ -6040 ^ 16986 ^ -29012);
      }

   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_bJy(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 10174 ^ -28157 ^ 18395 ^ -3482; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 6932 ^ -27840 ^ 6158 ^ -27520));
      }

      return var1.toString();
   }

   private static int method_bJA(0ep var0) {
      return var0.field_d;
   }

   private static boolean method_bJz(0ep var0) {
      return var0.field_b;
   }
}
