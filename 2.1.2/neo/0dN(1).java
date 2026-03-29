package neo;

import com.google.gson.JsonParser;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Objects;
import net.minecraft.client.Minecraft;
import org.apache.commons.io.FileUtils;

public final class 0dN {
   public _dN/* $FF was: 0dN*/() {
      this.loadConfig(Kz8lYoB42O("ȩȨȫȬȸȡȹ"));
   }

   private static File PgFgvIeyQu(Minecraft var0) {
      return var0.gameDir;
   }

   public static String readUsingFiles(File file) {
      try {
         return new String(Files.readAllBytes(file.toPath()));
      } catch (IOException var2) {
         return null;
      }
   }

   private static Charset zSGQtB1E11() {
      return StandardCharsets.UTF_8;
   }

   public void saveConfig(String name) {
      try {
         FileUtils.writeByteArrayToFile(new File(PgFgvIeyQu(Minecraft.getMinecraft()), Kz8lYoB42O("ɢȃȨȢȚȬȿȨɢȮȢȣȫȤȪȾɢ") + name + Kz8lYoB42O("ɣȮȫȪ")), 0dM.save().toString().getBytes(zSGQtB1E11()));
      } catch (Exception var3) {
         Exception e = var3;
         e.printStackTrace();
      }

   }

   public void loadConfig(String name) {
      try {
         0dM.load((new JsonParser()).parse((String)Objects.requireNonNull(readUsingFiles(new File(6aSYLGV0bv(Minecraft.getMinecraft()), Kz8lYoB42O("ɢȃȨȢȚȬȿȨɢȮȢȣȫȤȪȾɢ") + name + Kz8lYoB42O("ɣȮȫȪ"))))).getAsJsonObject());
      } catch (Exception var3) {
         Exception e = var3;
         e.printStackTrace();
      }

   }

   private static File _aSYLGV0bv/* $FF was: 6aSYLGV0bv*/(Minecraft var0) {
      return var0.gameDir;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String Kz8lYoB42O(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 31270 ^ -27502 ^ 795 ^ -4689; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 9627 ^ -20189 ^ 16174 ^ -22053));
      }

      return var1.toString();
   }
}
