package neo;

import javax.annotation.concurrent.Immutable;

@Immutable
public class bge {
   public static final bge EMPTY_CODE = new bge("");
   private final String lock;

   public bge(String code) {
      this.lock = code;
   }

   public boolean isEmpty() {
      return this.lock == null || this.lock.isEmpty();
   }

   public String getLock() {
      return this.lock;
   }

   public void toNBT(QQ nbt) {
      nbt.setString("Lock", this.lock);
   }

   public static bge fromNBT(QQ nbt) {
      if (nbt.hasKey("Lock", 8)) {
         String s = nbt.getString("Lock");
         return new bge(s);
      } else {
         return EMPTY_CODE;
      }
   }
}
