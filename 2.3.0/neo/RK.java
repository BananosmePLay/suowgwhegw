package neo;

import com.mojang.authlib.GameProfile;
import java.io.IOException;
import java.util.UUID;

public class RK implements Sz<RN> {
   private GameProfile profile;

   public RK() {
   }

   public RK(GameProfile profileIn) {
      this.profile = profileIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.profile = new GameProfile((UUID)null, buf.readString(16));
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeString(this.profile.getName());
   }

   public void processPacket(RN handler) {
      handler.processLoginStart(this);
   }

   public GameProfile getProfile() {
      return this.profile;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((RN)var1);
   }
}
