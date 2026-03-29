package neo;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class zW implements AC {
   private static final Logger LOGGER = LogManager.getLogger();
   public final File resourcePackFile;

   public zW(File resourcePackFileIn) {
      this.resourcePackFile = resourcePackFileIn;
   }

   private static String locationToName(ResourceLocation location) {
      return String.format("%s/%s/%s", "assets", location.getNamespace(), location.getPath());
   }

   protected static String getRelativeName(File p_110595_0_, File p_110595_1_) {
      return p_110595_0_.toURI().relativize(p_110595_1_.toURI()).getPath();
   }

   public InputStream getInputStream(ResourceLocation location) throws IOException {
      return this.getInputStreamByName(locationToName(location));
   }

   public boolean resourceExists(ResourceLocation location) {
      return this.hasResourceName(locationToName(location));
   }

   protected abstract InputStream getInputStreamByName(String var1) throws IOException;

   protected abstract boolean hasResourceName(String var1);

   protected void logNameNotLowercase(String name) {
      LOGGER.warn("ResourcePack: ignored non-lowercase namespace: {} in {}", name, this.resourcePackFile);
   }

   public <T extends Ad> T getPackMetadata(Aj metadataSerializer, String metadataSectionName) throws IOException {
      return readMetadata(metadataSerializer, this.getInputStreamByName("pack.mcmeta"), metadataSectionName);
   }

   static <T extends Ad> T readMetadata(Aj metadataSerializer, InputStream p_110596_1_, String sectionName) {
      JsonObject jsonobject = null;
      BufferedReader bufferedreader = null;

      try {
         bufferedreader = new BufferedReader(new InputStreamReader(p_110596_1_, StandardCharsets.UTF_8));
         jsonobject = (new JsonParser()).parse(bufferedreader).getAsJsonObject();
      } catch (RuntimeException var9) {
         RuntimeException runtimeexception = var9;
         throw new JsonParseException(runtimeexception);
      } finally {
         IOUtils.closeQuietly(bufferedreader);
      }

      return metadataSerializer.parseMetadataSection(sectionName, jsonobject);
   }

   public BufferedImage getPackImage() throws IOException {
      return zk.readBufferedImage(this.getInputStreamByName("pack.png"));
   }

   public String getPackName() {
      return this.resourcePackFile.getName();
   }
}
