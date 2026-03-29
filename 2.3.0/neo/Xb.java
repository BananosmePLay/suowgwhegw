package neo;

import com.mojang.authlib.GameProfile;
import java.util.Date;

class Xb {
   private final GameProfile gameProfile;
   private final Date expirationDate;
   // $FF: synthetic field
   final Xd this$0;

   private Xb(Xd this$0, GameProfile gameProfileIn, Date expirationDateIn) {
      this.this$0 = this$0;
      this.gameProfile = gameProfileIn;
      this.expirationDate = expirationDateIn;
   }

   public GameProfile getGameProfile() {
      return this.gameProfile;
   }

   public Date getExpirationDate() {
      return this.expirationDate;
   }

   // $FF: synthetic method
   Xb(Xd x0, GameProfile x1, Date x2, Object x3) {
      this(x0, x1, x2);
   }

   // $FF: synthetic method
   static Date access$200(Xb x0) {
      return x0.expirationDate;
   }
}
