package neo;

import com.google.gson.JsonParseException;
import java.io.IOException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AQ extends AN {
   private static final Logger LOGGER = LogManager.getLogger();
   private final AC resourcePack;
   private final ResourceLocation resourcePackIcon;

   public AQ(lr resourcePacksGUIIn, AC resourcePackIn) {
      super(resourcePacksGUIIn);
      this.resourcePack = resourcePackIn;

      yP dynamictexture;
      try {
         dynamictexture = new yP(resourcePackIn.getPackImage());
      } catch (IOException var5) {
         dynamictexture = zk.MISSING_TEXTURE;
      }

      this.resourcePackIcon = this.mc.getTextureManager().getDynamicTextureLocation("texturepackicon", dynamictexture);
   }

   protected int getResourcePackFormat() {
      return 3;
   }

   protected String getResourcePackDescription() {
      try {
         Al packmetadatasection = (Al)this.resourcePack.getPackMetadata(this.mc.getResourcePackRepository().rprMetadataSerializer, "pack");
         if (packmetadatasection != null) {
            return packmetadatasection.getPackDescription().getFormattedText();
         }
      } catch (JsonParseException var2) {
         JsonParseException jsonparseexception = var2;
         LOGGER.error("Couldn't load metadata info", jsonparseexception);
      } catch (IOException var3) {
         IOException ioexception = var3;
         LOGGER.error("Couldn't load metadata info", ioexception);
      }

      return TextFormatting.RED + "Missing pack.mcmeta :(";
   }

   protected boolean canMoveRight() {
      return false;
   }

   protected boolean canMoveLeft() {
      return false;
   }

   protected boolean canMoveUp() {
      return false;
   }

   protected boolean canMoveDown() {
      return false;
   }

   protected String getResourcePackName() {
      return "Server";
   }

   protected void bindResourcePackIcon() {
      this.mc.getTextureManager().bindTexture(this.resourcePackIcon);
   }

   protected boolean showHoverOverlay() {
      return false;
   }

   public boolean isServerPack() {
      return true;
   }
}
