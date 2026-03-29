package neo;

import java.awt.image.BufferedImage;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import org.apache.commons.io.IOUtils;

public class AU {
   private final AC reResourcePack;
   private Al rePackMetadataSection;
   private ResourceLocation locationTexturePackIcon;
   // $FF: synthetic field
   final AV this$0;

   private AU(AV this$0, File resourcePackFileIn) {
      this(this$0, AV.access$400(this$0, resourcePackFileIn));
   }

   private AU(AV this$0, AC reResourcePackIn) {
      this.this$0 = this$0;
      this.reResourcePack = reResourcePackIn;
   }

   public void updateResourcePack() throws IOException {
      this.rePackMetadataSection = (Al)this.reResourcePack.getPackMetadata(this.this$0.rprMetadataSerializer, "pack");
      this.closeResourcePack();
   }

   public void bindTexturePackIcon(zf textureManagerIn) {
      BufferedImage bufferedimage = null;
      if (this.locationTexturePackIcon == null) {
         try {
            bufferedimage = this.reResourcePack.getPackImage();
         } catch (IOException var5) {
         }

         if (bufferedimage == null) {
            try {
               bufferedimage = zk.readBufferedImage(nC.getMinecraft().getResourceManager().getResource(AV.access$500()).getInputStream());
            } catch (IOException var4) {
               IOException ioexception = var4;
               throw new Error("Couldn't bind resource pack icon", ioexception);
            }
         }
      }

      if (this.locationTexturePackIcon == null) {
         this.locationTexturePackIcon = textureManagerIn.getDynamicTextureLocation("texturepackicon", new yP(bufferedimage));
      }

      textureManagerIn.bindTexture(this.locationTexturePackIcon);
   }

   public void closeResourcePack() {
      if (this.reResourcePack instanceof Closeable) {
         IOUtils.closeQuietly((Closeable)this.reResourcePack);
      }

   }

   public AC getResourcePack() {
      return this.reResourcePack;
   }

   public String getResourcePackName() {
      return this.reResourcePack.getPackName();
   }

   public String getTexturePackDescription() {
      return this.rePackMetadataSection == null ? TextFormatting.RED + "Invalid pack.mcmeta (or missing 'pack' section)" : this.rePackMetadataSection.getPackDescription().getFormattedText();
   }

   public int getPackFormat() {
      return this.rePackMetadataSection == null ? 0 : this.rePackMetadataSection.getPackFormat();
   }

   public boolean equals(Object p_equals_1_) {
      if (this == p_equals_1_) {
         return true;
      } else {
         return p_equals_1_ instanceof AU ? this.toString().equals(p_equals_1_.toString()) : false;
      }
   }

   public int hashCode() {
      return this.toString().hashCode();
   }

   public String toString() {
      return String.format("%s:%s", this.reResourcePack.getPackName(), this.reResourcePack instanceof Au ? "folder" : "zip");
   }

   // $FF: synthetic method
   AU(AV x0, File x1, Object x2) {
      this(x0, x1);
   }

   // $FF: synthetic method
   AU(AV x0, AC x1, Object x2) {
      this(x0, x1);
   }
}
