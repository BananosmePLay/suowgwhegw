package neo;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class lD extends jI implements iE {
   private final nC client;
   private final List<lC> subtitles = Lists.newArrayList();
   private boolean enabled;

   public lD(nC clientIn) {
      this.client = clientIn;
   }

   public void renderSubtitles(mC resolution) {
      label58: {
         nC var10000;
         if (!this.enabled) {
            var10000 = this.client;
            if (nC.gameSettings.showSubtitles) {
               this.client.getSoundHandler().addListener(this);
               this.enabled = true;
               break label58;
            }
         }

         if (this.enabled) {
            var10000 = this.client;
            if (!nC.gameSettings.showSubtitles) {
               this.client.getSoundHandler().removeListener(this);
               this.enabled = false;
            }
         }
      }

      if (this.enabled && !this.subtitles.isEmpty()) {
         yh.pushMatrix();
         yh.enableBlend();
         yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA, ya.ONE, xR.ZERO);
         nC var10002 = this.client;
         double var28 = nC.player.posX;
         nC var10003 = this.client;
         nC var10004 = this.client;
         double var29 = nC.player.posY + (double)nC.player.getEyeHeight();
         var10004 = this.client;
         Vec3d vec3d = new Vec3d(var28, var29, nC.player.posZ);
         Vec3d var27 = new Vec3d(0.0, 0.0, -1.0);
         nC var10001 = this.client;
         var27 = var27.rotatePitch(-nC.player.rotationPitch * 0.017453292F);
         var10001 = this.client;
         Vec3d vec3d1 = var27.rotateYaw(-nC.player.rotationYaw * 0.017453292F);
         var27 = new Vec3d(0.0, 1.0, 0.0);
         var10001 = this.client;
         var27 = var27.rotatePitch(-nC.player.rotationPitch * 0.017453292F);
         var10001 = this.client;
         Vec3d vec3d2 = var27.rotateYaw(-nC.player.rotationYaw * 0.017453292F);
         Vec3d vec3d3 = vec3d1.crossProduct(vec3d2);
         int i = 0;
         int j = 0;
         Iterator<lC> iterator = this.subtitles.iterator();

         while(iterator.hasNext()) {
            lC guisubtitleoverlay$subtitle = (lC)iterator.next();
            if (guisubtitleoverlay$subtitle.getStartTime() + 3000L <= nC.getSystemTime()) {
               iterator.remove();
            } else {
               j = Math.max(j, this.client.fontRenderer.getStringWidth(guisubtitleoverlay$subtitle.getString()));
            }
         }

         j = j + this.client.fontRenderer.getStringWidth("<") + this.client.fontRenderer.getStringWidth(" ") + this.client.fontRenderer.getStringWidth(">") + this.client.fontRenderer.getStringWidth(" ");

         for(Iterator var26 = this.subtitles.iterator(); var26.hasNext(); ++i) {
            lC guisubtitleoverlay$subtitle1 = (lC)var26.next();
            int k = true;
            String s = guisubtitleoverlay$subtitle1.getString();
            Vec3d vec3d4 = guisubtitleoverlay$subtitle1.getLocation().subtract(vec3d).normalize();
            double d0 = -vec3d3.dotProduct(vec3d4);
            double d1 = -vec3d1.dotProduct(vec3d4);
            boolean flag = d1 > 0.5;
            int l = j / 2;
            int i1 = this.client.fontRenderer.FONT_HEIGHT;
            int j1 = i1 / 2;
            float f = 1.0F;
            int k1 = this.client.fontRenderer.getStringWidth(s);
            int l1 = MathHelper.floor(MathHelper.clampedLerp(255.0, 75.0, (double)((float)(nC.getSystemTime() - guisubtitleoverlay$subtitle1.getStartTime()) / 3000.0F)));
            int i2 = l1 << 16 | l1 << 8 | l1;
            yh.pushMatrix();
            yh.translate((float)resolution.getScaledWidth() - (float)l * 1.0F - 2.0F, (float)(resolution.getScaledHeight() - 30) - (float)(i * (i1 + 1)) * 1.0F, 0.0F);
            yh.scale(1.0F, 1.0F, 1.0F);
            drawRect(-l - 1, -j1 - 1, l + 1, j1 + 1, -872415232);
            yh.enableBlend();
            if (!flag) {
               if (d0 > 0.0) {
                  this.client.fontRenderer.drawString(">", l - this.client.fontRenderer.getStringWidth(">"), -j1, i2 + -16777216);
               } else if (d0 < 0.0) {
                  this.client.fontRenderer.drawString("<", -l, -j1, i2 + -16777216);
               }
            }

            this.client.fontRenderer.drawString(s, -k1 / 2, -j1, i2 + -16777216);
            yh.popMatrix();
         }

         yh.disableBlend();
         yh.popMatrix();
      }

   }

   public void soundPlay(iC soundIn, iQ accessor) {
      if (accessor.getSubtitle() != null) {
         String s = accessor.getSubtitle().getFormattedText();
         if (!this.subtitles.isEmpty()) {
            Iterator var4 = this.subtitles.iterator();

            while(var4.hasNext()) {
               lC guisubtitleoverlay$subtitle = (lC)var4.next();
               if (guisubtitleoverlay$subtitle.getString().equals(s)) {
                  guisubtitleoverlay$subtitle.refresh(new Vec3d((double)soundIn.getXPosF(), (double)soundIn.getYPosF(), (double)soundIn.getZPosF()));
                  return;
               }
            }
         }

         this.subtitles.add(new lC(this, s, new Vec3d((double)soundIn.getXPosF(), (double)soundIn.getYPosF(), (double)soundIn.getZPosF())));
      }

   }
}
