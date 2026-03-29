package neo;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.io.File;
import java.util.Iterator;

public class Xq extends Xj<GameProfile, Xr> {
   public Xq(File saveFile) {
      super(saveFile);
   }

   protected Xm<GameProfile> createEntry(JsonObject entryData) {
      return new Xr(entryData);
   }

   public String[] getKeys() {
      String[] astring = new String[this.getValues().size()];
      int i = 0;

      Xr userlistopsentry;
      for(Iterator var3 = this.getValues().values().iterator(); var3.hasNext(); astring[i++] = ((GameProfile)userlistopsentry.getValue()).getName()) {
         userlistopsentry = (Xr)var3.next();
      }

      return astring;
   }

   public int getPermissionLevel(GameProfile profile) {
      Xr userlistopsentry = (Xr)this.getEntry(profile);
      return userlistopsentry != null ? userlistopsentry.getPermissionLevel() : 0;
   }

   public boolean bypassesPlayerLimit(GameProfile profile) {
      Xr userlistopsentry = (Xr)this.getEntry(profile);
      return userlistopsentry != null ? userlistopsentry.bypassesPlayerLimit() : false;
   }

   protected String getObjectKey(GameProfile obj) {
      return obj.getId().toString();
   }

   public GameProfile getGameProfileFromName(String username) {
      Iterator var2 = this.getValues().values().iterator();

      Xr userlistopsentry;
      do {
         if (!var2.hasNext()) {
            return null;
         }

         userlistopsentry = (Xr)var2.next();
      } while(!username.equalsIgnoreCase(((GameProfile)userlistopsentry.getValue()).getName()));

      return (GameProfile)userlistopsentry.getValue();
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected String getObjectKey(Object var1) {
      return this.getObjectKey((GameProfile)var1);
   }
}
