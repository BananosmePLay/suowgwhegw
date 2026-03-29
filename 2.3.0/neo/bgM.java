package neo;

import java.util.Random;

public class bgM extends bgI {
   private final bhC count;
   private final int limit;

   public bgM(bgv[] conditions, bhC countIn, int limitIn) {
      super(conditions);
      this.count = countIn;
      this.limit = limitIn;
   }

   public Qy apply(Qy stack, Random rand, bhg context) {
      Ig entity = context.getKiller();
      if (entity instanceof Iw) {
         int i = Ft.getLootingModifier((Iw)entity);
         if (i == 0) {
            return stack;
         }

         float f = (float)i * this.count.generateFloat(rand);
         stack.grow(Math.round(f));
         if (this.limit != 0 && stack.getCount() > this.limit) {
            stack.setCount(this.limit);
         }
      }

      return stack;
   }

   // $FF: synthetic method
   static bhC access$000(bgM x0) {
      return x0.count;
   }

   // $FF: synthetic method
   static int access$100(bgM x0) {
      return x0.limit;
   }
}
