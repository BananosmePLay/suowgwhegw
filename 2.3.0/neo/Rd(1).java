package neo;

public class Rd<T> {
   private final int id;
   private final Re<T> serializer;

   public Rd(int idIn, Re<T> serializerIn) {
      this.id = idIn;
      this.serializer = serializerIn;
   }

   public int getId() {
      return this.id;
   }

   public Re<T> getSerializer() {
      return this.serializer;
   }

   public boolean equals(Object p_equals_1_) {
      if (this == p_equals_1_) {
         return true;
      } else if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
         Rd<?> dataparameter = (Rd)p_equals_1_;
         return this.id == dataparameter.id;
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.id;
   }
}
