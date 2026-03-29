package neo;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

class mO implements mQ {
   private final WA team;
   private final ResourceLocation location;
   private final List<pB> players;
   // $FF: synthetic field
   final mP this$0;

   public mO(mP this$0, WA teamIn) {
      this.this$0 = this$0;
      this.team = teamIn;
      this.players = Lists.newArrayList();
      Iterator var3 = teamIn.getMembershipCollection().iterator();

      while(var3.hasNext()) {
         String s = (String)var3.next();
         pB networkplayerinfo = nC.getMinecraft().getConnection().getPlayerInfo(s);
         if (networkplayerinfo != null) {
            this.players.add(networkplayerinfo);
         }
      }

      if (this.players.isEmpty()) {
         this.location = Ap.getDefaultSkinLegacy();
      } else {
         String s1 = ((pB)this.players.get((new Random()).nextInt(this.players.size()))).getGameProfile().getName();
         this.location = jf.getLocationSkin(s1);
         jf.getDownloadImageSkin(this.location, s1);
      }

   }

   public void selectItem(mY menu) {
      menu.selectCategory(new mN(this.players));
   }

   public ITextComponent getSpectatorName() {
      return new TextComponentString(this.team.getDisplayName());
   }

   public void renderIcon(float brightness, int alpha) {
      int i = -1;
      String s = jH.getFormatFromString(this.team.getPrefix());
      if (s.length() >= 2) {
         i = nC.getMinecraft().fontRenderer.getColorCode(s.charAt(1));
      }

      if (i >= 0) {
         float f = (float)(i >> 16 & 255) / 255.0F;
         float f1 = (float)(i >> 8 & 255) / 255.0F;
         float f2 = (float)(i & 255) / 255.0F;
         jI.drawRect(1, 1, 15, 15, MathHelper.rgb(f * brightness, f1 * brightness, f2 * brightness) | alpha << 24);
      }

      nC.getMinecraft().getTextureManager().bindTexture(this.location);
      yh.color(brightness, brightness, brightness, (float)alpha / 255.0F);
      jI.drawScaledCustomSizeModalRect(2, 2, 8.0F, 8.0F, 8, 8, 12, 12, 64.0F, 64.0F);
      jI.drawScaledCustomSizeModalRect(2, 2, 40.0F, 8.0F, 8, 8, 12, 12, 64.0F, 64.0F);
   }

   public boolean isEnabled() {
      return !this.players.isEmpty();
   }
}
