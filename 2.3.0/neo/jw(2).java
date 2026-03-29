package neo;

import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class jw extends jI {
   private final nC minecraft;
   private final jx screen;
   private final ju type;
   private final int index;
   private final b advancement;
   private final cb display;
   private final Qy icon;
   private final String title;
   private final jv root;
   private final Map<b, jv> guis = Maps.newLinkedHashMap();
   private int scrollX;
   private int scrollY;
   private int minX = Integer.MAX_VALUE;
   private int minY = Integer.MAX_VALUE;
   private int maxX = Integer.MIN_VALUE;
   private int maxY = Integer.MIN_VALUE;
   private float fade;
   private boolean centered;

   public jw(nC p_i47589_1_, jx p_i47589_2_, ju p_i47589_3_, int p_i47589_4_, b p_i47589_5_, cb p_i47589_6_) {
      this.minecraft = p_i47589_1_;
      this.screen = p_i47589_2_;
      this.type = p_i47589_3_;
      this.index = p_i47589_4_;
      this.advancement = p_i47589_5_;
      this.display = p_i47589_6_;
      this.icon = p_i47589_6_.getIcon();
      this.title = p_i47589_6_.getTitle().getFormattedText();
      this.root = new jv(this, p_i47589_1_, p_i47589_5_, p_i47589_6_);
      this.addGuiAdvancement(this.root, p_i47589_5_);
   }

   public b getAdvancement() {
      return this.advancement;
   }

   public String getTitle() {
      return this.title;
   }

   public void drawTab(int p_191798_1_, int p_191798_2_, boolean p_191798_3_) {
      this.type.draw(this, p_191798_1_, p_191798_2_, p_191798_3_, this.index);
   }

   public void drawIcon(int p_191796_1_, int p_191796_2_, yK p_191796_3_) {
      this.type.drawIcon(p_191796_1_, p_191796_2_, this.index, p_191796_3_, this.icon);
   }

   public void drawContents() {
      if (!this.centered) {
         this.scrollX = 117 - (this.maxX + this.minX) / 2;
         this.scrollY = 56 - (this.maxY + this.minY) / 2;
         this.centered = true;
      }

      yh.depthFunc(518);
      drawRect(0, 0, 234, 113, -16777216);
      yh.depthFunc(515);
      ResourceLocation resourcelocation = this.display.getBackground();
      if (resourcelocation != null) {
         this.minecraft.getTextureManager().bindTexture(resourcelocation);
      } else {
         this.minecraft.getTextureManager().bindTexture(zf.RESOURCE_LOCATION_EMPTY);
      }

      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      int i = this.scrollX % 16;
      int j = this.scrollY % 16;

      for(int k = -1; k <= 15; ++k) {
         for(int l = -1; l <= 8; ++l) {
            drawModalRectWithCustomSizedTexture(i + 16 * k, j + 16 * l, 0.0F, 0.0F, 16, 16, 16.0F, 16.0F);
         }
      }

      this.root.drawConnectivity(this.scrollX, this.scrollY, true);
      this.root.drawConnectivity(this.scrollX, this.scrollY, false);
      this.root.draw(this.scrollX, this.scrollY);
   }

   public void drawToolTips(int p_192991_1_, int p_192991_2_, int p_192991_3_, int p_192991_4_) {
      yh.pushMatrix();
      yh.translate(0.0F, 0.0F, 200.0F);
      drawRect(0, 0, 234, 113, MathHelper.floor(this.fade * 255.0F) << 24);
      boolean flag = false;
      if (p_192991_1_ > 0 && p_192991_1_ < 234 && p_192991_2_ > 0 && p_192991_2_ < 113) {
         Iterator var6 = this.guis.values().iterator();

         while(var6.hasNext()) {
            jv guiadvancement = (jv)var6.next();
            if (guiadvancement.isMouseOver(this.scrollX, this.scrollY, p_192991_1_, p_192991_2_)) {
               flag = true;
               guiadvancement.drawHover(this.scrollX, this.scrollY, this.fade, p_192991_3_, p_192991_4_);
               break;
            }
         }
      }

      yh.popMatrix();
      if (flag) {
         this.fade = MathHelper.clamp(this.fade + 0.02F, 0.0F, 0.3F);
      } else {
         this.fade = MathHelper.clamp(this.fade - 0.04F, 0.0F, 1.0F);
      }

   }

   public boolean isMouseOver(int p_191793_1_, int p_191793_2_, int p_191793_3_, int p_191793_4_) {
      return this.type.isMouseOver(p_191793_1_, p_191793_2_, this.index, p_191793_3_, p_191793_4_);
   }

   @Nullable
   public static jw create(nC p_193936_0_, jx p_193936_1_, int p_193936_2_, b p_193936_3_) {
      if (p_193936_3_.getDisplay() == null) {
         return null;
      } else {
         ju[] var4 = ju.values();
         int var5 = var4.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            ju advancementtabtype = var4[var6];
            if (p_193936_2_ < advancementtabtype.getMax()) {
               return new jw(p_193936_0_, p_193936_1_, advancementtabtype, p_193936_2_, p_193936_3_, p_193936_3_.getDisplay());
            }

            p_193936_2_ -= advancementtabtype.getMax();
         }

         return null;
      }
   }

   public void scroll(int p_191797_1_, int p_191797_2_) {
      if (this.maxX - this.minX > 234) {
         this.scrollX = MathHelper.clamp(this.scrollX + p_191797_1_, -(this.maxX - 234), 0);
      }

      if (this.maxY - this.minY > 113) {
         this.scrollY = MathHelper.clamp(this.scrollY + p_191797_2_, -(this.maxY - 113), 0);
      }

   }

   public void addAdvancement(b p_191800_1_) {
      if (p_191800_1_.getDisplay() != null) {
         jv guiadvancement = new jv(this, this.minecraft, p_191800_1_, p_191800_1_.getDisplay());
         this.addGuiAdvancement(guiadvancement, p_191800_1_);
      }

   }

   private void addGuiAdvancement(jv p_193937_1_, b p_193937_2_) {
      this.guis.put(p_193937_2_, p_193937_1_);
      int i = p_193937_1_.getX();
      int j = i + 28;
      int k = p_193937_1_.getY();
      int l = k + 27;
      this.minX = Math.min(this.minX, i);
      this.maxX = Math.max(this.maxX, j);
      this.minY = Math.min(this.minY, k);
      this.maxY = Math.max(this.maxY, l);
      Iterator var7 = this.guis.values().iterator();

      while(var7.hasNext()) {
         jv guiadvancement = (jv)var7.next();
         guiadvancement.attachToParent();
      }

   }

   @Nullable
   public jv getAdvancementGui(b p_191794_1_) {
      return (jv)this.guis.get(p_191794_1_);
   }

   public jx getScreen() {
      return this.screen;
   }
}
