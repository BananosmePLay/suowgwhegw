package neo;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.mojang.authlib.Agent;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.ProfileLookupCallback;
import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Xg {
   private static final Logger LOGGER = LogManager.getLogger();
   public static final File OLD_IPBAN_FILE = new File("banned-ips.txt");
   public static final File OLD_PLAYERBAN_FILE = new File("banned-players.txt");
   public static final File OLD_OPS_FILE = new File("ops.txt");
   public static final File OLD_WHITELIST_FILE = new File("white-list.txt");

   public Xg() {
   }

   private static void lookupNames(Xx server, Collection<String> names, ProfileLookupCallback callback) {
      String[] astring = (String[])((String[])Iterators.toArray(Iterators.filter(names.iterator(), new Predicate<String>() {
         public boolean apply(@Nullable String p_apply_1_) {
            return !StringUtils.isNullOrEmpty(p_apply_1_);
         }

         // $FF: synthetic method
         // $FF: bridge method
         public boolean apply(@Nullable Object var1) {
            return this.apply((String)var1);
         }
      }), String.class));
      if (server.isServerInOnlineMode()) {
         server.getGameProfileRepository().findProfilesByNames(astring, Agent.MINECRAFT, callback);
      } else {
         String[] var4 = astring;
         int var5 = astring.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            String s = var4[var6];
            UUID uuid = ME.getUUID(new GameProfile((UUID)null, s));
            GameProfile gameprofile = new GameProfile(uuid, s);
            callback.onProfileLookupSucceeded(gameprofile);
         }
      }

   }

   public static String convertMobOwnerIfNeeded(final Xx server, String username) {
      if (!StringUtils.isNullOrEmpty(username) && username.length() <= 16) {
         GameProfile gameprofile = server.getPlayerProfileCache().getGameProfileForUsername(username);
         if (gameprofile != null && gameprofile.getId() != null) {
            return gameprofile.getId().toString();
         } else if (!server.isSinglePlayer() && server.isServerInOnlineMode()) {
            final List<GameProfile> list = Lists.newArrayList();
            ProfileLookupCallback profilelookupcallback = new ProfileLookupCallback() {
               public void onProfileLookupSucceeded(GameProfile p_onProfileLookupSucceeded_1_) {
                  server.getPlayerProfileCache().addEntry(p_onProfileLookupSucceeded_1_);
                  list.add(p_onProfileLookupSucceeded_1_);
               }

               public void onProfileLookupFailed(GameProfile p_onProfileLookupFailed_1_, Exception p_onProfileLookupFailed_2_) {
                  Xg.LOGGER.warn("Could not lookup user whitelist entry for {}", p_onProfileLookupFailed_1_.getName(), p_onProfileLookupFailed_2_);
               }
            };
            lookupNames(server, Lists.newArrayList(new String[]{username}), profilelookupcallback);
            return !list.isEmpty() && ((GameProfile)list.get(0)).getId() != null ? ((GameProfile)list.get(0)).getId().toString() : "";
         } else {
            return ME.getUUID(new GameProfile((UUID)null, username)).toString();
         }
      } else {
         return username;
      }
   }
}
