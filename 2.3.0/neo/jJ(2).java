package neo;

import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import net.minecraft.util.ResourceLocation;

public class jJ extends jI {
   private static final ResourceLocation GUI_BARS_TEXTURES = new ResourceLocation("textures/gui/bars.png");
   private final nC client;
   private final Map<UUID, jA> mapBossInfos = Maps.newLinkedHashMap();

   public jJ(nC clientIn) {
      this.client = clientIn;
   }

   public void renderBossHealth() {
      if (!this.mapBossInfos.isEmpty()) {
         mC scaledresolution = new mC(this.client);
         int i = scaledresolution.getScaledWidth();
         int j = 12;
         Iterator var4 = this.mapBossInfos.values().iterator();

         while(var4.hasNext()) {
            jA bossinfoclient = (jA)var4.next();
            int k = i / 2 - 91;
            yh.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.client.getTextureManager().bindTexture(GUI_BARS_TEXTURES);
            this.render(k, j, bossinfoclient);
            String s = bossinfoclient.getName().getFormattedText();
            this.client.fontRenderer.drawStringWithShadow(s, (float)(i / 2 - this.client.fontRenderer.getStringWidth(s) / 2), (float)(j - 9), 16777215);
            j += 10 + this.client.fontRenderer.FONT_HEIGHT;
            if (j >= scaledresolution.getScaledHeight() / 3) {
               break;
            }
         }
      }

   }

   private void render(int x, int y, bae info) {
      this.drawTexturedModalRect(x, y, 0, info.getColor().ordinal() * 5 * 2, 182, 5);
      if (info.getOverlay() != bad.PROGRESS) {
         this.drawTexturedModalRect(x, y, 0, 80 + (info.getOverlay().ordinal() - 1) * 5 * 2, 182, 5);
      }

      int i = (int)(info.getPercent() * 183.0F);
      if (i > 0) {
         this.drawTexturedModalRect(x, y, 0, info.getColor().ordinal() * 5 * 2 + 5, i, 5);
         if (info.getOverlay() != bad.PROGRESS) {
            this.drawTexturedModalRect(x, y, 0, 80 + (info.getOverlay().ordinal() - 1) * 5 * 2 + 5, i, 5);
         }
      }

   }

   public void read(Vc packetIn) {
      if (packetIn.getOperation() == Vb.ADD) {
         this.mapBossInfos.put(packetIn.getUniqueId(), new jA(packetIn));
      } else if (packetIn.getOperation() == Vb.REMOVE) {
         this.mapBossInfos.remove(packetIn.getUniqueId());
      } else {
         ((jA)this.mapBossInfos.get(packetIn.getUniqueId())).updateFromPacket(packetIn);
      }

   }

   public void clearBossInfos() {
      this.mapBossInfos.clear();
   }

   public boolean shouldPlayEndBossMusic() {
      if (!this.mapBossInfos.isEmpty()) {
         Iterator var1 = this.mapBossInfos.values().iterator();

         while(var1.hasNext()) {
            bae bossinfo = (bae)var1.next();
            if (bossinfo.shouldPlayEndBossMusic()) {
               return true;
            }
         }
      }

      return false;
   }

   public boolean shouldDarkenSky() {
      if (!this.mapBossInfos.isEmpty()) {
         Iterator var1 = this.mapBossInfos.values().iterator();

         while(var1.hasNext()) {
            bae bossinfo = (bae)var1.next();
            if (bossinfo.shouldDarkenSky()) {
               return true;
            }
         }
      }

      return false;
   }

   public boolean shouldCreateFog() {
      if (!this.mapBossInfos.isEmpty()) {
         Iterator var1 = this.mapBossInfos.values().iterator();

         while(var1.hasNext()) {
            bae bossinfo = (bae)var1.next();
            if (bossinfo.shouldCreateFog()) {
               return true;
            }
         }
      }

      return false;
   }
}
