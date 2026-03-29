package neo;

public class n extends m {
   private final V parent;
   private final V partner;
   private final V child;

   public n(V parent, V partner, V child) {
      super(p.access$000());
      this.parent = parent;
      this.partner = partner;
      this.child = child;
   }

   public boolean test(MG player, Ly parent1In, Ly parent2In, Ih childIn) {
      if (!this.child.test(player, childIn)) {
         return false;
      } else {
         return this.parent.test(player, parent1In) && this.partner.test(player, parent2In) || this.parent.test(player, parent2In) && this.partner.test(player, parent1In);
      }
   }
}
