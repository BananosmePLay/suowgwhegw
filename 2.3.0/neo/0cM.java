package neo;

import com.google.common.eventbus.Subscribe;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class 0cM extends ArrayList<0dr> implements 0cE<0dr> {
   private static int _DSC GG NEOWARECLIENT _;

   // $FF: synthetic method
   // $FF: bridge method
   public void register(Object var1) {
      this.method_bxX((0dr)var1);
   }

   public List<0dr> method_bya(0dz a) {
      return (List)this.stream().filter((b) -> {
         return (boolean)(b.method_bBa() == a ? 30039 ^ -32367 ^ 25091 ^ -26940 : 26077 ^ -26148 ^ 25987 ^ -26238);
      }).collect(Collectors.toList());
   }

   public void init() {
      this.method_bxX(new 0bC());
      this.method_bxX(new 0bD());
      this.method_bxX(new 0bE());
      this.method_bxX(new 0bF());
      this.method_bxX(new 0bH());
      this.method_bxX(new 0bI());
      this.method_bxX(new 0bJ());
      this.method_bxX(new 0bG());
      this.method_bxX(new 0bL());
      this.method_bxX(new 0bN());
      this.method_bxX(new 0bO());
      this.method_bxX(new 0bR());
      this.method_bxX(new 0cb());
      this.method_bxX(new 0bQ());
      this.method_bxX(new 0bT());
      this.method_bxX(new 0bZ());
      this.method_bxX(new 0ca());
      this.method_bxX(new 0bS());
      this.method_bxX(new 0bU());
      this.method_bxX(new 0bV());
      this.method_bxX(new 0bW());
      this.method_bxX(new 0bX());
      this.method_bxX(new 0bY());
      this.method_bxX(new 0bK());
      this.method_bxX(new 0bP());
      this.method_bxY(0bN.class).method_bBj((boolean)(12244 ^ -17494 ^ 4181 ^ -31702));
      this.method_bxY(0ca.class).method_bBj((boolean)(30453 ^ -30845 ^ 240 ^ -3705));
      0bz.method_Qm().method_Qn().register(this);
   }

   public _cM/* $FF was: 0cM*/() {
      this.init();
   }

   @Subscribe
   private void method_byb(0dd b) {
      Iterator var2 = this.iterator();

      while(var2.hasNext()) {
         0dr a = (0dr)var2.next();
         if (a.method_bBb() == b.method_bzX()) {
            a.method_bBi();
         }
      }

   }

   public void method_bxX(0dr a) {
      this.add(a);
   }

   public 0dr method_bxZ(String a) {
      return (0dr)this.stream().filter((b) -> {
         return b.method_bAZ().equalsIgnoreCase(a);
      }).findAny().orElse((Object)null);
   }

   public 0dr method_bxY(Class<?> a) {
      return (0dr)this.stream().filter((b) -> {
         return (boolean)(b.getClass() == a ? 5051 ^ -9272 ^ 1913 ^ -12533 : 20283 ^ -20361 ^ 12590 ^ -12702);
      }).findAny().orElse((Object)null);
   }
}
