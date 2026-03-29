package neo;

import com.mojang.authlib.GameProfile;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class mU implements mQ {
   private final GameProfile profile;
   private final ResourceLocation resourceLocation;

   public mU(GameProfile profileIn) {
      this.profile = profileIn;
      this.resourceLocation = jf.getLocationSkin(profileIn.getName());
      jf.getDownloadImageSkin(this.resourceLocation, profileIn.getName());
   }

   public void selectItem(mY menu) {
      nC.getMinecraft().getConnection().sendPacket(new Tk(this.profile.getId()));
   }

   public ITextComponent getSpectatorName() {
      return new TextComponentString(this.profile.getName());
   }

   public void renderIcon(float brightness, int alpha) {
      nC.getMinecraft().getTextureManager().bindTexture(this.resourceLocation);
      yh.color(1.0F, 1.0F, 1.0F, (float)alpha / 255.0F);
      jI.drawScaledCustomSizeModalRect(2, 2, 8.0F, 8.0F, 8, 8, 12, 12, 64.0F, 64.0F);
      jI.drawScaledCustomSizeModalRect(2, 2, 40.0F, 8.0F, 8, 8, 12, 12, 64.0F, 64.0F);
   }

   public boolean isEnabled() {
      return true;
   }
}
