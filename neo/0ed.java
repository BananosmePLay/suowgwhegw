package neo;

import java.io.Serializable;
import org.jetbrains.annotations.NotNull;

public class 0ed implements Serializable {
   public final String label;
   public final String url;

   private static String RjO68CDLGh(0ed var0) {
      return var0.url;
   }

   protected _ed/* $FF was: 0ed*/(String label, String url) {
      this.label = label;
      this.url = url;
   }

   public static @NotNull 0ed create(String substring, String s) {
      substring = substring.substring(30304 ^ -20995 ^ 18173 ^ -25248, Math.min(substring.length(), 14821 ^ -3343 ^ 15648 ^ -2517));
      return new 0ed(substring, s);
   }

   public String getLabel() {
      return DSdoIJxTFI(this);
   }

   private static String DSdoIJxTFI(0ed var0) {
      return var0.label;
   }

   public String getUrl() {
      return RjO68CDLGh(this);
   }
}
