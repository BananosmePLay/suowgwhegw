package neo;

import com.mojang.authlib.GameProfile;
import java.io.IOException;
import java.util.UUID;

public class RT implements Sz<RM> {
   private GameProfile profile;

   public RT() {
   }

   public RT(GameProfile profileIn) {
      this.profile = profileIn;
   }

   public void readPacketData(SA buf) throws IOException {
      String s = buf.readString(36);
      String s1 = buf.readString(16);
      UUID uuid = UUID.fromString(s);
      this.profile = new GameProfile(uuid, s1);
   }

   public void writePacketData(SA buf) throws IOException {
      UUID uuid = this.profile.getId();
      buf.writeString(uuid == null ? "" : uuid.toString());
      buf.writeString(this.profile.getName());
   }

   public void processPacket(RM handler) {
      handler.handleLoginSuccess(this);
   }

   public GameProfile getProfile() {
      return this.profile;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((RM)var1);
   }
}
