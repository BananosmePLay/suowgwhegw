package neo;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.mojang.authlib.Agent;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.ProfileLookupCallback;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.util.JsonUtils;
import org.apache.commons.io.IOUtils;

public class Xd {
   public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
   private static boolean onlineMode;
   private final Map<String, Xb> usernameToProfileEntryMap = Maps.newHashMap();
   private final Map<UUID, Xb> uuidToProfileEntryMap = Maps.newHashMap();
   private final Deque<GameProfile> gameProfiles = Lists.newLinkedList();
   private final GameProfileRepository profileRepo;
   protected final Gson gson;
   private final File usercacheFile;
   private static final ParameterizedType TYPE = new ParameterizedType() {
      public Type[] getActualTypeArguments() {
         return new Type[]{Xb.class};
      }

      public Type getRawType() {
         return List.class;
      }

      public Type getOwnerType() {
         return null;
      }
   };

   public Xd(GameProfileRepository profileRepoIn, File usercacheFileIn) {
      this.profileRepo = profileRepoIn;
      this.usercacheFile = usercacheFileIn;
      GsonBuilder gsonbuilder = new GsonBuilder();
      gsonbuilder.registerTypeHierarchyAdapter(Xb.class, new Xc(this));
      this.gson = gsonbuilder.create();
      this.load();
   }

   private static GameProfile lookupProfile(GameProfileRepository profileRepoIn, String name) {
      final GameProfile[] agameprofile = new GameProfile[1];
      ProfileLookupCallback profilelookupcallback = new ProfileLookupCallback() {
         public void onProfileLookupSucceeded(GameProfile p_onProfileLookupSucceeded_1_) {
            agameprofile[0] = p_onProfileLookupSucceeded_1_;
         }

         public void onProfileLookupFailed(GameProfile p_onProfileLookupFailed_1_, Exception p_onProfileLookupFailed_2_) {
            agameprofile[0] = null;
         }
      };
      profileRepoIn.findProfilesByNames(new String[]{name}, Agent.MINECRAFT, profilelookupcallback);
      if (!isOnlineMode() && agameprofile[0] == null) {
         UUID uuid = ME.getUUID(new GameProfile((UUID)null, name));
         GameProfile gameprofile = new GameProfile(uuid, name);
         profilelookupcallback.onProfileLookupSucceeded(gameprofile);
      }

      return agameprofile[0];
   }

   public static void setOnlineMode(boolean onlineModeIn) {
      onlineMode = onlineModeIn;
   }

   private static boolean isOnlineMode() {
      return onlineMode;
   }

   public void addEntry(GameProfile gameProfile) {
      this.addEntry(gameProfile, (Date)null);
   }

   private void addEntry(GameProfile gameProfile, Date expirationDate) {
      UUID uuid = gameProfile.getId();
      if (expirationDate == null) {
         Calendar calendar = Calendar.getInstance();
         calendar.setTime(new Date());
         calendar.add(2, 1);
         expirationDate = calendar.getTime();
      }

      String s = gameProfile.getName().toLowerCase(Locale.ROOT);
      Xb playerprofilecache$profileentry = new Xb(this, gameProfile, expirationDate);
      if (this.uuidToProfileEntryMap.containsKey(uuid)) {
         Xb playerprofilecache$profileentry1 = (Xb)this.uuidToProfileEntryMap.get(uuid);
         this.usernameToProfileEntryMap.remove(playerprofilecache$profileentry1.getGameProfile().getName().toLowerCase(Locale.ROOT));
         this.gameProfiles.remove(gameProfile);
      }

      this.usernameToProfileEntryMap.put(gameProfile.getName().toLowerCase(Locale.ROOT), playerprofilecache$profileentry);
      this.uuidToProfileEntryMap.put(uuid, playerprofilecache$profileentry);
      this.gameProfiles.addFirst(gameProfile);
      this.save();
   }

   @Nullable
   public GameProfile getGameProfileForUsername(String username) {
      String s = username.toLowerCase(Locale.ROOT);
      Xb playerprofilecache$profileentry = (Xb)this.usernameToProfileEntryMap.get(s);
      if (playerprofilecache$profileentry != null && (new Date()).getTime() >= Xb.access$200(playerprofilecache$profileentry).getTime()) {
         this.uuidToProfileEntryMap.remove(playerprofilecache$profileentry.getGameProfile().getId());
         this.usernameToProfileEntryMap.remove(playerprofilecache$profileentry.getGameProfile().getName().toLowerCase(Locale.ROOT));
         this.gameProfiles.remove(playerprofilecache$profileentry.getGameProfile());
         playerprofilecache$profileentry = null;
      }

      GameProfile gameprofile1;
      if (playerprofilecache$profileentry != null) {
         gameprofile1 = playerprofilecache$profileentry.getGameProfile();
         this.gameProfiles.remove(gameprofile1);
         this.gameProfiles.addFirst(gameprofile1);
      } else {
         gameprofile1 = lookupProfile(this.profileRepo, s);
         if (gameprofile1 != null) {
            this.addEntry(gameprofile1);
            playerprofilecache$profileentry = (Xb)this.usernameToProfileEntryMap.get(s);
         }
      }

      this.save();
      return playerprofilecache$profileentry == null ? null : playerprofilecache$profileentry.getGameProfile();
   }

   public String[] getUsernames() {
      List<String> list = Lists.newArrayList(this.usernameToProfileEntryMap.keySet());
      return (String[])((String[])list.toArray(new String[list.size()]));
   }

   @Nullable
   public GameProfile getProfileByUUID(UUID uuid) {
      Xb playerprofilecache$profileentry = (Xb)this.uuidToProfileEntryMap.get(uuid);
      return playerprofilecache$profileentry == null ? null : playerprofilecache$profileentry.getGameProfile();
   }

   private Xb getByUUID(UUID uuid) {
      Xb playerprofilecache$profileentry = (Xb)this.uuidToProfileEntryMap.get(uuid);
      if (playerprofilecache$profileentry != null) {
         GameProfile gameprofile = playerprofilecache$profileentry.getGameProfile();
         this.gameProfiles.remove(gameprofile);
         this.gameProfiles.addFirst(gameprofile);
      }

      return playerprofilecache$profileentry;
   }

   public void load() {
      BufferedReader bufferedreader = null;

      try {
         bufferedreader = Files.newReader(this.usercacheFile, StandardCharsets.UTF_8);
         List<Xb> list = (List)JsonUtils.fromJson(this.gson, bufferedreader, (Type)TYPE);
         this.usernameToProfileEntryMap.clear();
         this.uuidToProfileEntryMap.clear();
         this.gameProfiles.clear();
         if (list != null) {
            Iterator var3 = Lists.reverse(list).iterator();

            while(var3.hasNext()) {
               Xb playerprofilecache$profileentry = (Xb)var3.next();
               if (playerprofilecache$profileentry != null) {
                  this.addEntry(playerprofilecache$profileentry.getGameProfile(), playerprofilecache$profileentry.getExpirationDate());
               }
            }
         }
      } catch (FileNotFoundException var9) {
      } catch (JsonParseException var10) {
      } finally {
         IOUtils.closeQuietly(bufferedreader);
      }

   }

   public void save() {
      String s = this.gson.toJson(this.getEntriesWithLimit(1000));
      BufferedWriter bufferedwriter = null;

      try {
         bufferedwriter = Files.newWriter(this.usercacheFile, StandardCharsets.UTF_8);
         bufferedwriter.write(s);
         return;
      } catch (FileNotFoundException var8) {
         return;
      } catch (IOException var9) {
      } finally {
         IOUtils.closeQuietly(bufferedwriter);
      }

   }

   private List<Xb> getEntriesWithLimit(int limitSize) {
      List<Xb> list = Lists.newArrayList();
      Iterator var3 = Lists.newArrayList(Iterators.limit(this.gameProfiles.iterator(), limitSize)).iterator();

      while(var3.hasNext()) {
         GameProfile gameprofile = (GameProfile)var3.next();
         Xb playerprofilecache$profileentry = this.getByUUID(gameprofile.getId());
         if (playerprofilecache$profileentry != null) {
            list.add(playerprofilecache$profileentry);
         }
      }

      return list;
   }
}
