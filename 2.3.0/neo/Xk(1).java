package neo;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.io.File;
import java.util.Iterator;

public class Xk extends Xj<GameProfile, Xl> {
   public Xk(File bansFile) {
      super(bansFile);
   }

   protected Xm<GameProfile> createEntry(JsonObject entryData) {
      return new Xl(entryData);
   }

   public boolean isBanned(GameProfile profile) {
      return this.hasEntry(profile);
   }

   public String[] getKeys() {
      String[] astring = new String[this.getValues().size()];
      int i = 0;

      Xl userlistbansentry;
      for(Iterator var3 = this.getValues().values().iterator(); var3.hasNext(); astring[i++] = ((GameProfile)userlistbansentry.getValue()).getName()) {
         userlistbansentry = (Xl)var3.next();
      }

      return astring;
   }

   protected String getObjectKey(GameProfile obj) {
      return obj.getId().toString();
   }

   public GameProfile getBannedProfile(String username) {
      Iterator var2 = this.getValues().values().iterator();

      Xl userlistbansentry;
      do {
         if (!var2.hasNext()) {
            return null;
         }

         userlistbansentry = (Xl)var2.next();
      } while(!username.equalsIgnoreCase(((GameProfile)userlistbansentry.getValue()).getName()));

      return (GameProfile)userlistbansentry.getValue();
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected String getObjectKey(Object var1) {
      return this.getObjectKey((GameProfile)var1);
   }
}
