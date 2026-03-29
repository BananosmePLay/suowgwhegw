package neo;

import com.google.gson.JsonObject;

public class Xm<T> {
   private final T value;

   public Xm(T valueIn) {
      this.value = valueIn;
   }

   protected Xm(T valueIn, JsonObject json) {
      this.value = valueIn;
   }

   T getValue() {
      return this.value;
   }

   boolean hasBanExpired() {
      return false;
   }

   protected void onSerialization(JsonObject data) {
   }
}
