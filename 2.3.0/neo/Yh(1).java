package neo;

import com.google.common.collect.Lists;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class Yh extends Yg implements bgd {
   private String name;
   private Om baseColor;
   private QW patterns;
   private boolean patternDataSet;
   private List<XW> patternList;
   private List<Om> colorList;
   private String patternResourceLocation;

   public Yh() {
      this.baseColor = Om.BLACK;
   }

   public void setItemValues(Qy stack, boolean p_175112_2_) {
      this.patterns = null;
      QQ nbttagcompound = stack.getSubCompound("BlockEntityTag");
      if (nbttagcompound != null && nbttagcompound.hasKey("Patterns", 9)) {
         this.patterns = nbttagcompound.getTagList("Patterns", 10).copy();
      }

      this.baseColor = p_175112_2_ ? getColor(stack) : OV.getBaseColor(stack);
      this.patternList = null;
      this.colorList = null;
      this.patternResourceLocation = "";
      this.patternDataSet = true;
      this.name = stack.hasDisplayName() ? stack.getDisplayName() : null;
   }

   public String getName() {
      return this.hasCustomName() ? this.name : "banner";
   }

   public boolean hasCustomName() {
      return this.name != null && !this.name.isEmpty();
   }

   public ITextComponent getDisplayName() {
      return (ITextComponent)(this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName(), new Object[0]));
   }

   public QQ writeToNBT(QQ compound) {
      super.writeToNBT(compound);
      compound.setInteger("Base", this.baseColor.getDyeDamage());
      if (this.patterns != null) {
         compound.setTag("Patterns", this.patterns);
      }

      if (this.hasCustomName()) {
         compound.setString("CustomName", this.name);
      }

      return compound;
   }

   public void readFromNBT(QQ compound) {
      super.readFromNBT(compound);
      if (compound.hasKey("CustomName", 8)) {
         this.name = compound.getString("CustomName");
      }

      this.baseColor = Om.byDyeDamage(compound.getInteger("Base"));
      this.patterns = compound.getTagList("Patterns", 10);
      this.patternList = null;
      this.colorList = null;
      this.patternResourceLocation = null;
      this.patternDataSet = true;
   }

   @Nullable
   public Vg getUpdatePacket() {
      return new Vg(this.pos, 6, this.getUpdateTag());
   }

   public QQ getUpdateTag() {
      return this.writeToNBT(new QQ());
   }

   public static int getPatterns(Qy stack) {
      QQ nbttagcompound = stack.getSubCompound("BlockEntityTag");
      return nbttagcompound != null && nbttagcompound.hasKey("Patterns") ? nbttagcompound.getTagList("Patterns", 10).tagCount() : 0;
   }

   public List<XW> getPatternList() {
      this.initializeBannerData();
      return this.patternList;
   }

   public List<Om> getColorList() {
      this.initializeBannerData();
      return this.colorList;
   }

   public String getPatternResourceLocation() {
      this.initializeBannerData();
      return this.patternResourceLocation;
   }

   private void initializeBannerData() {
      if (this.patternList == null || this.colorList == null || this.patternResourceLocation == null) {
         if (!this.patternDataSet) {
            this.patternResourceLocation = "";
         } else {
            this.patternList = Lists.newArrayList();
            this.colorList = Lists.newArrayList();
            this.patternList.add(XW.BASE);
            this.colorList.add(this.baseColor);
            this.patternResourceLocation = "b" + this.baseColor.getDyeDamage();
            if (this.patterns != null) {
               for(int i = 0; i < this.patterns.tagCount(); ++i) {
                  QQ nbttagcompound = this.patterns.getCompoundTagAt(i);
                  XW bannerpattern = XW.byHash(nbttagcompound.getString("Pattern"));
                  if (bannerpattern != null) {
                     this.patternList.add(bannerpattern);
                     int j = nbttagcompound.getInteger("Color");
                     this.colorList.add(Om.byDyeDamage(j));
                     this.patternResourceLocation = this.patternResourceLocation + bannerpattern.getHashname() + j;
                  }
               }
            }
         }
      }

   }

   public static void removeBannerData(Qy stack) {
      QQ nbttagcompound = stack.getSubCompound("BlockEntityTag");
      if (nbttagcompound != null && nbttagcompound.hasKey("Patterns", 9)) {
         QW nbttaglist = nbttagcompound.getTagList("Patterns", 10);
         if (!nbttaglist.isEmpty()) {
            nbttaglist.removeTag(nbttaglist.tagCount() - 1);
            if (nbttaglist.isEmpty()) {
               stack.getTagCompound().removeTag("BlockEntityTag");
               if (stack.getTagCompound().isEmpty()) {
                  stack.setTagCompound((QQ)null);
               }
            }
         }
      }

   }

   public Qy getItem() {
      Qy itemstack = OV.makeBanner(this.baseColor, this.patterns);
      if (this.hasCustomName()) {
         itemstack.setStackDisplayName(this.getName());
      }

      return itemstack;
   }

   public static Om getColor(Qy p_190616_0_) {
      QQ nbttagcompound = p_190616_0_.getSubCompound("BlockEntityTag");
      return nbttagcompound != null && nbttagcompound.hasKey("Base") ? Om.byDyeDamage(nbttagcompound.getInteger("Base")) : Om.BLACK;
   }
}
