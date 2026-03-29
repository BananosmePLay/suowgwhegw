package neo;

import net.minecraft.util.ITickable;

public class Ys extends Yg implements ITickable {
   public Ys() {
   }

   public void update() {
      if (this.world != null && !this.world.isRemote && this.world.getTotalWorldTime() % 20L == 0L) {
         this.blockType = this.getBlockType();
         if (this.blockType instanceof df) {
            ((df)this.blockType).updatePower(this.world, this.pos);
         }
      }

   }
}
