package neo;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.commons.io.IOUtils;

public class bpv extends yO {
   private String texturePath;
   private static final Aj METADATA_SERIALIZER = makeMetadataSerializer();

   public bpv(String texturePath) {
      this.texturePath = texturePath;
   }

   public void loadTexture(AA resourceManager) throws IOException {
      this.deleteGlTexture();
      InputStream inputstream = bpq.getShaderPackResourceStream(this.texturePath);
      if (inputstream == null) {
         throw new FileNotFoundException("Shader texture not found: " + this.texturePath);
      } else {
         try {
            BufferedImage bufferedimage = zk.readBufferedImage(inputstream);
            An texturemetadatasection = loadTextureMetadataSection(this.texturePath, new An(false, false));
            zk.uploadTextureImageAllocate(this.getGlTextureId(), bufferedimage, texturemetadatasection.getTextureBlur(), texturemetadatasection.getTextureClamp());
         } finally {
            IOUtils.closeQuietly(inputstream);
         }

      }
   }

   public static An loadTextureMetadataSection(String texturePath, An def) {
      String s = texturePath + ".mcmeta";
      String s1 = "texture";
      InputStream inputstream = bpq.getShaderPackResourceStream(s);
      if (inputstream != null) {
         Aj metadataserializer = METADATA_SERIALIZER;
         BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream));

         An texturemetadatasection1;
         try {
            An texturemetadatasection;
            try {
               JsonObject jsonobject = (new JsonParser()).parse(bufferedreader).getAsJsonObject();
               texturemetadatasection = (An)metadataserializer.parseMetadataSection(s1, jsonobject);
               if (texturemetadatasection == null) {
                  An var10 = def;
                  return var10;
               }

               texturemetadatasection1 = texturemetadatasection;
            } catch (RuntimeException var14) {
               RuntimeException runtimeexception = var14;
               bpx.warning("Error reading metadata: " + s);
               bpx.warning("" + runtimeexception.getClass().getName() + ": " + runtimeexception.getMessage());
               texturemetadatasection = def;
               return texturemetadatasection;
            }
         } finally {
            IOUtils.closeQuietly(bufferedreader);
            IOUtils.closeQuietly(inputstream);
         }

         return texturemetadatasection1;
      } else {
         return def;
      }
   }

   private static Aj makeMetadataSerializer() {
      Aj metadataserializer = new Aj();
      metadataserializer.registerMetadataSectionType(new Ao(), An.class);
      metadataserializer.registerMetadataSectionType(new Ac(), Ab.class);
      metadataserializer.registerMetadataSectionType(new zZ(), zY.class);
      metadataserializer.registerMetadataSectionType(new Am(), Al.class);
      metadataserializer.registerMetadataSectionType(new Ag(), Af.class);
      return metadataserializer;
   }
}
