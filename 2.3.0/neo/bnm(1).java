package neo;

import java.awt.image.BufferedImage;
import net.minecraft.util.ResourceLocation;

public class bnm extends yj {
   private jf player;
   private ResourceLocation resourceLocation;
   private boolean elytraOfCape;

   public bnm(jf player, ResourceLocation resourceLocation) {
      this.player = player;
      this.resourceLocation = resourceLocation;
   }

   public BufferedImage parseUserSkin(BufferedImage imageRaw) {
      BufferedImage bufferedimage = bnn.parseCape(imageRaw);
      this.elytraOfCape = bnn.isElytraCape(imageRaw, bufferedimage);
      return bufferedimage;
   }

   public void skinAvailable() {
      if (this.player != null) {
         this.player.setLocationOfCape(this.resourceLocation);
         this.player.setElytraOfCape(this.elytraOfCape);
      }

      this.cleanup();
   }

   public void cleanup() {
      this.player = null;
   }

   public boolean isElytraOfCape() {
      return this.elytraOfCape;
   }
}
