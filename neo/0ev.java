package neo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.lwjgl.opengl.GL20;

public class 0ev {
   public final int timeUniform;
   public final int resolutionUniform;
   public final int mouseUniform;
   public final int programId;

   public _ev/* $FF was: 0ev*/(String shaderLocation) {
      try {
         int program = GL20.glCreateProgram();
         GL20.glAttachShader(program, this.createShader(0ev.class.getResourceAsStream(juZvY0xUbn("ݳܽܯܯܹܨܯݳܱܹܵܲܿܮܽܺܨݳܹܲܳܫܽܮܹݳܯܴܸܹܽܮܯݳܬܽܯܯܨܴܮܳܩܻܴݲܪܯܴ")), 17348 ^ 'ꂮ' ^ 31994 ^ 5281));
         GL20.glAttachShader(program, this.createShader(0ev.class.getResourceAsStream(shaderLocation), 19001 ^ 96021 ^ 115555 ^ 30079));
         GL20.glLinkProgram(program);
         int linked = GL20.glGetProgrami(program, 8959 ^ '멊' ^ 892 ^ 4171);
         if (linked == 0) {
            System.err.println(GL20.glGetProgramInfoLog(program, GL20.glGetProgrami(program, 14844 ^ '댘' ^ 16096 ^ 16256)));
            throw new IllegalStateException(juZvY0xUbn("\u070fܴܸܹܽܮݼܹܸܺܽܵܰݼܨܳݼܷܰܵܲ"));
         } else {
            this.programId = program;
            GL20.glUseProgram(program);
            this.timeUniform = GL20.glGetUniformLocation(program, juZvY0xUbn("ܨܱܹܵ"));
            this.mouseUniform = GL20.glGetUniformLocation(program, juZvY0xUbn("ܱܳܩܯܹ"));
            this.resolutionUniform = GL20.glGetUniformLocation(program, juZvY0xUbn("ܮܹܯܳܰܩܨܵܳܲ"));
            GL20.glUseProgram(19219 ^ -10485 ^ 11373 ^ -20363);
         }
      } catch (IOException var4) {
         IOException exception = var4;
         throw new IllegalStateException(juZvY0xUbn("ܚܹܸܽܵܰݼܨܳݼܸܰܳܽݼܯܴܸܹܽܮ"), exception);
      }
   }

   public void useShader(int width, int height, float mouseX, float mouseY, float time) {
      GL20.glUseProgram(ZAGCCGTwwF(this));
      GL20.glUniform2f(hEGNy9B2bT(this), (float)width, (float)height);
      GL20.glUniform2f(FqJA24sLS1(this), mouseX / (float)width, Float.intBitsToFloat('\uf4ed' ^ '錤' ^ 7028 ^ 2117600851 ^ '鑩' ^ '\uec7d' ^ 17734 ^ 1102596028) - mouseY / (float)height);
      GL20.glUniform1f(geTWK9dx7c(this), time);
   }

   private String readStreamToString(InputStream inputStream) throws IOException {
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      byte[] buffer = new byte[31500 ^ -12586 ^ 19219 ^ -823];

      int read;
      while((read = inputStream.read(buffer, 4547 ^ -21551 ^ 11101 ^ -28337, buffer.length)) != (-5656 ^ -22450 ^ 5531 ^ -21566)) {
         out.write(buffer, 14932 ^ -9153 ^ 24673 ^ -31222, read);
      }

      return new String(out.toByteArray(), jrreaCwn22());
   }

   private static Charset jrreaCwn22() {
      return StandardCharsets.UTF_8;
   }

   private static PrintStream Jw1AnS7v76() {
      return System.err;
   }

   private static int geTWK9dx7c(0ev var0) {
      return var0.timeUniform;
   }

   private static int hEGNy9B2bT(0ev var0) {
      return var0.resolutionUniform;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String juZvY0xUbn(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 23782 ^ -30966 ^ 11047 ^ -3893; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 31617 ^ -49208 ^ 'ﯶ' ^ -18205));
      }

      return var1.toString();
   }

   private int createShader(InputStream inputStream, int shaderType) throws IOException {
      int shader = GL20.glCreateShader(shaderType);
      GL20.glShaderSource(shader, this.readStreamToString(inputStream));
      GL20.glCompileShader(shader);
      int compiled = GL20.glGetShaderi(shader, 7862 ^ '설' ^ 14798 ^ 28125);
      if (compiled == 0) {
         Jw1AnS7v76().println(GL20.glGetShaderInfoLog(shader, GL20.glGetShaderi(shader, 99164 ^ '駲' ^ 126412 ^ 31974)));
         throw new IllegalStateException(juZvY0xUbn("ܚܹܸܽܵܰݼܨܳݼܱܿܳܬܹܵܰݼܯܴܸܹܽܮ"));
      } else {
         return shader;
      }
   }

   private static int FqJA24sLS1(0ev var0) {
      return var0.mouseUniform;
   }

   private static int ZAGCCGTwwF(0ev var0) {
      return var0.programId;
   }
}
