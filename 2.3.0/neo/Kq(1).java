package neo;

import com.google.common.base.Predicate;
import java.util.Iterator;

class Kq extends GR<ME> {
   // $FF: synthetic field
   final Kv this$0;

   public Kq(Kv this$0) {
      super(this$0, ME.class, 20, true, true, (Predicate)null);
      this.this$0 = this$0;
   }

   public boolean shouldExecute() {
      if (this.this$0.isChild()) {
         return false;
      } else {
         if (super.shouldExecute()) {
            Iterator var1 = this.this$0.world.getEntitiesWithinAABB(Kv.class, this.this$0.getEntityBoundingBox().grow(8.0, 4.0, 8.0)).iterator();

            while(var1.hasNext()) {
               Kv entitypolarbear = (Kv)var1.next();
               if (entitypolarbear.isChild()) {
                  return true;
               }
            }
         }

         this.this$0.setAttackTarget((Iw)null);
         return false;
      }
   }

   protected double getTargetDistance() {
      return super.getTargetDistance() * 0.5;
   }
}
