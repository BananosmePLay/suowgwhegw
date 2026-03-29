package neo;

import net.minecraft.util.text.ITextComponent;

public class Vu {
   private ITextComponent description;
   private Vq players;
   private Vt version;
   private String favicon;

   public Vu() {
   }

   public ITextComponent getServerDescription() {
      return this.description;
   }

   public void setServerDescription(ITextComponent descriptionIn) {
      this.description = descriptionIn;
   }

   public Vq getPlayers() {
      return this.players;
   }

   public void setPlayers(Vq playersIn) {
      this.players = playersIn;
   }

   public Vt getVersion() {
      return this.version;
   }

   public void setVersion(Vt versionIn) {
      this.version = versionIn;
   }

   public void setFavicon(String faviconBlob) {
      this.favicon = faviconBlob;
   }

   public String getFavicon() {
      return this.favicon;
   }
}
