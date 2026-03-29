package neo;

import com.google.common.collect.Maps;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import org.apache.commons.io.IOUtils;

public class bfL {
   private final Map<String, bfK> templates = Maps.newHashMap();
   private final String baseFolder;
   private final DataFixer fixer;

   public bfL(String p_i47239_1_, DataFixer p_i47239_2_) {
      this.baseFolder = p_i47239_1_;
      this.fixer = p_i47239_2_;
   }

   public bfK getTemplate(@Nullable Xx server, ResourceLocation id) {
      bfK template = this.get(server, id);
      if (template == null) {
         template = new bfK();
         this.templates.put(id.getPath(), template);
      }

      return template;
   }

   @Nullable
   public bfK get(@Nullable Xx server, ResourceLocation templatePath) {
      String s = templatePath.getPath();
      if (this.templates.containsKey(s)) {
         return (bfK)this.templates.get(s);
      } else {
         if (server == null) {
            this.readTemplateFromJar(templatePath);
         } else {
            this.readTemplate(templatePath);
         }

         return this.templates.containsKey(s) ? (bfK)this.templates.get(s) : null;
      }
   }

   public boolean readTemplate(ResourceLocation server) {
      String s = server.getPath();
      File file1 = new File(this.baseFolder, s + ".nbt");
      if (!file1.exists()) {
         return this.readTemplateFromJar(server);
      } else {
         InputStream inputstream = null;

         boolean flag;
         try {
            inputstream = new FileInputStream(file1);
            this.readTemplateFromStream(s, inputstream);
            boolean var6 = true;
            return var6;
         } catch (Throwable var10) {
            flag = false;
         } finally {
            IOUtils.closeQuietly(inputstream);
         }

         return flag;
      }
   }

   private boolean readTemplateFromJar(ResourceLocation id) {
      String s = id.getNamespace();
      String s1 = id.getPath();
      InputStream inputstream = null;

      boolean flag;
      try {
         inputstream = Xx.class.getResourceAsStream("/assets/" + s + "/structures/" + s1 + ".nbt");
         this.readTemplateFromStream(s1, inputstream);
         boolean var6 = true;
         return var6;
      } catch (Throwable var10) {
         flag = false;
      } finally {
         IOUtils.closeQuietly(inputstream);
      }

      return flag;
   }

   private void readTemplateFromStream(String id, InputStream stream) throws IOException {
      QQ nbttagcompound = QF.readCompressed(stream);
      if (!nbttagcompound.hasKey("DataVersion", 99)) {
         nbttagcompound.setInteger("DataVersion", 500);
      }

      bfK template = new bfK();
      template.read(this.fixer.process(FixTypes.STRUCTURE, nbttagcompound));
      this.templates.put(id, template);
   }

   public boolean writeTemplate(@Nullable Xx server, ResourceLocation id) {
      String s = id.getPath();
      if (server != null && this.templates.containsKey(s)) {
         File file1 = new File(this.baseFolder);
         if (!file1.exists()) {
            if (!file1.mkdirs()) {
               return false;
            }
         } else if (!file1.isDirectory()) {
            return false;
         }

         File file2 = new File(file1, s + ".nbt");
         bfK template = (bfK)this.templates.get(s);
         OutputStream outputstream = null;

         boolean flag;
         try {
            QQ nbttagcompound = template.writeToNBT(new QQ());
            outputstream = new FileOutputStream(file2);
            QF.writeCompressed(nbttagcompound, outputstream);
            boolean var10 = true;
            return var10;
         } catch (Throwable var14) {
            flag = false;
         } finally {
            IOUtils.closeQuietly(outputstream);
         }

         return flag;
      } else {
         return false;
      }
   }

   public void remove(ResourceLocation templatePath) {
      this.templates.remove(templatePath.getPath());
   }
}
