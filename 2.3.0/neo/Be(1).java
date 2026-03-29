package neo;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.InsecureTextureException;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;

public class Be {
   private static final ExecutorService THREAD_POOL;
   private final zf textureManager;
   private final File skinCacheDir;
   private final MinecraftSessionService sessionService;
   private final LoadingCache<GameProfile, Map<MinecraftProfileTexture.Type, MinecraftProfileTexture>> skinCacheLoader;

   public Be(zf textureManagerInstance, File skinCacheDirectory, MinecraftSessionService sessionService) {
      this.textureManager = textureManagerInstance;
      this.skinCacheDir = skinCacheDirectory;
      this.sessionService = sessionService;
      this.skinCacheLoader = CacheBuilder.newBuilder().expireAfterAccess(15L, TimeUnit.SECONDS).build(new CacheLoader<GameProfile, Map<MinecraftProfileTexture.Type, MinecraftProfileTexture>>() {
         public Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> load(GameProfile p_load_1_) throws Exception {
            try {
               return nC.getMinecraft().getSessionService().getTextures(p_load_1_, false);
            } catch (Throwable var3) {
               return Maps.newHashMap();
            }
         }

         // $FF: synthetic method
         // $FF: bridge method
         public Object load(Object var1) throws Exception {
            return this.load((GameProfile)var1);
         }
      });
   }

   public ResourceLocation loadSkin(MinecraftProfileTexture profileTexture, MinecraftProfileTexture.Type textureType) {
      return this.loadSkin(profileTexture, textureType, (Bd)null);
   }

   public ResourceLocation loadSkin(final MinecraftProfileTexture profileTexture, final MinecraftProfileTexture.Type textureType, @Nullable final Bd skinAvailableCallback) {
      final ResourceLocation resourcelocation = new ResourceLocation("skins/" + profileTexture.getHash());
      yR itextureobject = this.textureManager.getTexture(resourcelocation);
      if (itextureobject != null) {
         if (skinAvailableCallback != null) {
            skinAvailableCallback.skinAvailable(textureType, resourcelocation, profileTexture);
         }
      } else {
         File file1 = new File(this.skinCacheDir, profileTexture.getHash().length() > 2 ? profileTexture.getHash().substring(0, 2) : "xx");
         File file2 = new File(file1, profileTexture.getHash());
         final yi iimagebuffer = textureType == Type.SKIN ? new yj() : null;
         zm threaddownloadimagedata = new zm(file2, profileTexture.getUrl(), Ap.getDefaultSkinLegacy(), new yi() {
            public BufferedImage parseUserSkin(BufferedImage image) {
               if (iimagebuffer != null) {
                  image = iimagebuffer.parseUserSkin(image);
               }

               return image;
            }

            public void skinAvailable() {
               if (iimagebuffer != null) {
                  iimagebuffer.skinAvailable();
               }

               if (skinAvailableCallback != null) {
                  skinAvailableCallback.skinAvailable(textureType, resourcelocation, profileTexture);
               }

            }
         });
         this.textureManager.loadTexture(resourcelocation, threaddownloadimagedata);
      }

      return resourcelocation;
   }

   public void loadProfileTextures(final GameProfile profile, final Bd skinAvailableCallback, final boolean requireSecure) {
      THREAD_POOL.submit(new Runnable() {
         public void run() {
            final Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> map = Maps.newHashMap();

            try {
               map.putAll(Be.this.sessionService.getTextures(profile, requireSecure));
            } catch (InsecureTextureException var3) {
            }

            if (map.isEmpty() && profile.getId().equals(nC.getMinecraft().getSession().getProfile().getId())) {
               profile.getProperties().clear();
               profile.getProperties().putAll(nC.getMinecraft().getProfileProperties());
               map.putAll(Be.this.sessionService.getTextures(profile, false));
            }

            nC.getMinecraft().addScheduledTask(new Runnable() {
               public void run() {
                  if (map.containsKey(Type.SKIN)) {
                     Be.this.loadSkin((MinecraftProfileTexture)map.get(Type.SKIN), Type.SKIN, skinAvailableCallback);
                  }

                  if (map.containsKey(Type.CAPE)) {
                     Be.this.loadSkin((MinecraftProfileTexture)map.get(Type.CAPE), Type.CAPE, skinAvailableCallback);
                  }

               }
            });
         }
      });
   }

   public Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> loadSkinFromCache(GameProfile profile) {
      return (Map)this.skinCacheLoader.getUnchecked(profile);
   }

   static {
      THREAD_POOL = new ThreadPoolExecutor(0, 2, 1L, TimeUnit.MINUTES, new LinkedBlockingQueue());
   }
}
