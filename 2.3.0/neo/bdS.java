package neo;

import java.util.Iterator;
import java.util.Random;

public class bdS extends beM {
   private bdg mineShaftType;

   public bdS() {
   }

   public bdS(bij p_i47149_1_, Random p_i47149_2_, int p_i47149_3_, int p_i47149_4_, bdg p_i47149_5_) {
      super(p_i47149_3_, p_i47149_4_);
      this.mineShaftType = p_i47149_5_;
      bdP structuremineshaftpieces$room = new bdP(0, p_i47149_2_, (p_i47149_3_ << 4) + 2, (p_i47149_4_ << 4) + 2, this.mineShaftType);
      this.components.add(structuremineshaftpieces$room);
      structuremineshaftpieces$room.buildComponent(structuremineshaftpieces$room, this.components, p_i47149_2_);
      this.updateBoundingBox();
      if (p_i47149_5_ == bdg.MESA) {
         int i = true;
         int j = p_i47149_1_.getSeaLevel() - this.boundingBox.maxY + this.boundingBox.getYSize() / 2 - -5;
         this.boundingBox.offset(0, j, 0);
         Iterator var9 = this.components.iterator();

         while(var9.hasNext()) {
            bdB structurecomponent = (bdB)var9.next();
            structurecomponent.offset(0, j, 0);
         }
      } else {
         this.markAvailableHeight(p_i47149_1_, p_i47149_2_, 10);
      }

   }
}
