package neo;

import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;

public class Dt {
   public static final Dt EMPTY = new Dt((ResourceLocation)null);
   @Nullable
   private final ResourceLocation id;
   private boolean isValid;
   private Dx function;

   public Dt(@Nullable ResourceLocation idIn) {
      this.id = idIn;
   }

   public Dt(Dx functionIn) {
      this.id = null;
      this.function = functionIn;
   }

   @Nullable
   public Dx get(cf functionManagerIn) {
      if (!this.isValid) {
         if (this.id != null) {
            this.function = functionManagerIn.getFunction(this.id);
         }

         this.isValid = true;
      }

      return this.function;
   }

   public String toString() {
      return String.valueOf(this.id);
   }
}
