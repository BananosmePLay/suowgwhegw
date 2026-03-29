package neo;

import com.google.gson.JsonObject;
import com.mojang.authlib.GameProfile;
import java.io.File;
import java.util.Iterator;

public class Xs extends Xj<GameProfile, Xt> {
   public Xs(File p_i1132_1_) {
      super(p_i1132_1_);
   }

   protected Xm<GameProfile> createEntry(JsonObject entryData) {
      return new Xt(entryData);
   }

   public String[] getKeys() {
      String[] astring = new String[this.getValues().size()];
      int i = 0;

      Xt userlistwhitelistentry;
      for(Iterator var3 = this.getValues().values().iterator(); var3.hasNext(); astring[i++] = ((GameProfile)userlistwhitelistentry.getValue()).getName()) {
         userlistwhitelistentry = (Xt)var3.next();
      }

      return astring;
   }

   protected String getObjectKey(GameProfile obj) {
      return obj.getId().toString();
   }

   public GameProfile getByName(String profileName) {
      Iterator var2 = this.getValues().values().iterator();

      Xt userlistwhitelistentry;
      do {
         if (!var2.hasNext()) {
            return null;
         }

         userlistwhitelistentry = (Xt)var2.next();
      } while(!profileName.equalsIgnoreCase(((GameProfile)userlistwhitelistentry.getValue()).getName()));

      return (GameProfile)userlistwhitelistentry.getValue();
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected String getObjectKey(Object var1) {
      return this.getObjectKey((GameProfile)var1);
   }
}
