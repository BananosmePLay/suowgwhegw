package neo;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public abstract class YC extends Yg implements bgb {
   private bge code;

   public YC() {
      this.code = bge.EMPTY_CODE;
   }

   public void readFromNBT(QQ compound) {
      super.readFromNBT(compound);
      this.code = bge.fromNBT(compound);
   }

   public QQ writeToNBT(QQ compound) {
      super.writeToNBT(compound);
      if (this.code != null) {
         this.code.toNBT(compound);
      }

      return compound;
   }

   public boolean isLocked() {
      return this.code != null && !this.code.isEmpty();
   }

   public bge getLockCode() {
      return this.code;
   }

   public void setLockCode(bge code) {
      this.code = code;
   }

   public ITextComponent getDisplayName() {
      return (ITextComponent)(this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName(), new Object[0]));
   }
}
