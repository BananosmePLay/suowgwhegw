package neo;

import com.google.common.base.MoreObjects;
import com.mojang.authlib.GameProfile;
import javax.annotation.Nullable;
import net.minecraft.util.text.ITextComponent;

public class Uv {
   private final int ping;
   private final bbb gamemode;
   private final GameProfile profile;
   private ITextComponent displayName;
   // $FF: synthetic field
   final Uw this$0;

   public Uv(Uw this$0, GameProfile profileIn, int latencyIn, @Nullable bbb gameModeIn, ITextComponent displayNameIn) {
      this.this$0 = this$0;
      this.profile = profileIn;
      this.ping = latencyIn;
      this.gamemode = gameModeIn;
      this.displayName = displayNameIn;
   }

   public GameProfile getProfile() {
      return this.profile;
   }

   public int getPing() {
      return this.ping;
   }

   public bbb getGameMode() {
      return this.gamemode;
   }

   @Nullable
   public ITextComponent getDisplayName() {
      return this.displayName;
   }

   public void setDisplayName(ITextComponent displayName) {
      this.displayName = displayName;
   }

   public String toString() {
      return MoreObjects.toStringHelper(this).add("latency", this.ping).add("gameMode", this.gamemode).add("profile", this.profile).add("displayName", this.displayName == null ? null : ITextComponent.Serializer.componentToJson(this.displayName)).toString();
   }
}
