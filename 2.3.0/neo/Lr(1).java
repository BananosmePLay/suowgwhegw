package neo;

import net.minecraft.util.DamageSource;

public class Lr extends Ig {
   public final IE parent;
   public final String partName;

   public Lr(IE parent, String partName, float width, float height) {
      super(parent.getWorld());
      this.setSize(width, height);
      this.parent = parent;
      this.partName = partName;
   }

   protected void entityInit() {
   }

   protected void readEntityFromNBT(QQ compound) {
   }

   protected void writeEntityToNBT(QQ compound) {
   }

   public boolean canBeCollidedWith() {
      return true;
   }

   public boolean attackEntityFrom(DamageSource source, float amount) {
      return this.isEntityInvulnerable(source) ? false : this.parent.attackEntityFromPart(this, source, amount);
   }

   public boolean isEntityEqual(Ig entityIn) {
      return this == entityIn || this.parent == entityIn;
   }
}
