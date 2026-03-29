package neo;

import com.google.common.collect.Lists;
import java.util.List;

public class Hy {
   Iu entity;
   List<Ig> seenEntities = Lists.newArrayList();
   List<Ig> unseenEntities = Lists.newArrayList();

   public Hy(Iu entityIn) {
      this.entity = entityIn;
   }

   public void clearSensingCache() {
      this.seenEntities.clear();
      this.unseenEntities.clear();
   }

   public boolean canSee(Ig entityIn) {
      if (this.seenEntities.contains(entityIn)) {
         return true;
      } else if (this.unseenEntities.contains(entityIn)) {
         return false;
      } else {
         this.entity.world.profiler.startSection("canSee");
         boolean flag = this.entity.canEntityBeSeen(entityIn);
         this.entity.world.profiler.endSection();
         if (flag) {
            this.seenEntities.add(entityIn);
         } else {
            this.unseenEntities.add(entityIn);
         }

         return flag;
      }
   }
}
