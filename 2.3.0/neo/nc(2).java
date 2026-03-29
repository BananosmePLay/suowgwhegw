package neo;

import com.google.common.collect.Queues;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import javax.annotation.Nullable;

public class nc extends jI {
   private final nC mc;
   private final nb<?>[] visible = new nb[5];
   private final Deque<ne> toastsQueue = Queues.newArrayDeque();

   public nc(nC mcIn) {
      this.mc = mcIn;
   }

   public void drawToast(mC resolution) {
      nC var10000 = this.mc;
      if (!nC.gameSettings.hideGUI) {
         yz.disableStandardItemLighting();

         for(int i = 0; i < this.visible.length; ++i) {
            nb<?> toastinstance = this.visible[i];
            if (toastinstance != null && toastinstance.render(resolution.getScaledWidth(), i)) {
               this.visible[i] = null;
            }

            if (this.visible[i] == null && !this.toastsQueue.isEmpty()) {
               this.visible[i] = new nb(this, (ne)this.toastsQueue.removeFirst());
            }
         }
      }

   }

   @Nullable
   public <T extends ne> T getToast(Class<? extends T> p_192990_1_, Object p_192990_2_) {
      nb[] var3 = this.visible;
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         nb<?> toastinstance = var3[var5];
         if (toastinstance != null && p_192990_1_.isAssignableFrom(toastinstance.getToast().getClass()) && toastinstance.getToast().getType().equals(p_192990_2_)) {
            return toastinstance.getToast();
         }
      }

      Iterator var7 = this.toastsQueue.iterator();

      ne itoast;
      do {
         if (!var7.hasNext()) {
            return (ne)null;
         }

         itoast = (ne)var7.next();
      } while(!p_192990_1_.isAssignableFrom(itoast.getClass()) || !itoast.getType().equals(p_192990_2_));

      return itoast;
   }

   public void clear() {
      Arrays.fill(this.visible, (Object)null);
      this.toastsQueue.clear();
   }

   public void add(ne toastIn) {
      this.toastsQueue.add(toastIn);
   }

   public nC getMinecraft() {
      return this.mc;
   }

   // $FF: synthetic method
   static nC access$100(nc x0) {
      return x0.mc;
   }
}
