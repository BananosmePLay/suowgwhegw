package neo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.util.ITickable;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class iU implements AB, ITickable {
   public static final iP MISSING_SOUND;
   private static final Logger LOGGER;
   private static final Gson GSON;
   private static final ParameterizedType TYPE;
   private final jd soundRegistry = new jd();
   private final jc sndManager;
   private final AA resourceManager;

   public iU(AA manager, Bj gameSettingsIn) {
      this.resourceManager = manager;
      this.sndManager = new jc(this, gameSettingsIn);
   }

   public void onResourceManagerReload(AA resourceManager) {
      this.soundRegistry.clearMap();
      Iterator var2 = resourceManager.getResourceDomains().iterator();

      while(var2.hasNext()) {
         String s = (String)var2.next();

         try {
            Iterator var4 = resourceManager.getAllResources(new ResourceLocation(s, "sounds.json")).iterator();

            while(var4.hasNext()) {
               Az iresource = (Az)var4.next();

               try {
                  Map<String, iV> map = this.getSoundMap(iresource.getInputStream());
                  Iterator var7 = map.entrySet().iterator();

                  while(var7.hasNext()) {
                     Map.Entry<String, iV> entry = (Map.Entry)var7.next();
                     this.loadSoundResource(new ResourceLocation(s, (String)entry.getKey()), (iV)entry.getValue());
                  }
               } catch (RuntimeException var9) {
                  RuntimeException runtimeexception = var9;
                  LOGGER.warn("Invalid sounds.json", runtimeexception);
               }
            }
         } catch (IOException var10) {
         }
      }

      var2 = this.soundRegistry.getKeys().iterator();

      ResourceLocation resourcelocation1;
      while(var2.hasNext()) {
         resourcelocation1 = (ResourceLocation)var2.next();
         iQ soundeventaccessor = (iQ)this.soundRegistry.getObject(resourcelocation1);
         if (soundeventaccessor.getSubtitle() instanceof TextComponentTranslation) {
            String s1 = ((TextComponentTranslation)soundeventaccessor.getSubtitle()).getKey();
            if (!Ax.hasKey(s1)) {
               LOGGER.debug("Missing subtitle {} for event: {}", s1, resourcelocation1);
            }
         }
      }

      var2 = this.soundRegistry.getKeys().iterator();

      while(var2.hasNext()) {
         resourcelocation1 = (ResourceLocation)var2.next();
         if (SoundEvent.REGISTRY.getObject(resourcelocation1) == null) {
            LOGGER.debug("Not having sound event for: {}", resourcelocation1);
         }
      }

      this.sndManager.reloadSoundSystem();
   }

   @Nullable
   protected Map<String, iV> getSoundMap(InputStream stream) {
      Map map;
      try {
         map = (Map)JsonUtils.fromJson(GSON, new InputStreamReader(stream, StandardCharsets.UTF_8), (Type)TYPE);
      } finally {
         IOUtils.closeQuietly(stream);
      }

      return map;
   }

   private void loadSoundResource(ResourceLocation location, iV sounds) {
      iQ soundeventaccessor = (iQ)this.soundRegistry.getObject(location);
      boolean flag = soundeventaccessor == null;
      if (flag || sounds.canReplaceExisting()) {
         if (!flag) {
            LOGGER.debug("Replaced sound event location {}", location);
         }

         soundeventaccessor = new iQ(location, sounds.getSubtitle());
         this.soundRegistry.add(soundeventaccessor);
      }

      Iterator var5 = sounds.getSounds().iterator();

      while(var5.hasNext()) {
         final iP sound = (iP)var5.next();
         final ResourceLocation resourcelocation = sound.getSoundLocation();
         Object isoundeventaccessor;
         switch (sound.getType()) {
            case FILE:
               if (!this.validateSoundResource(sound, location)) {
                  continue;
               }

               isoundeventaccessor = sound;
               break;
            case SOUND_EVENT:
               isoundeventaccessor = new iD<iP>() {
                  public int getWeight() {
                     iQ soundeventaccessor1 = (iQ)iU.this.soundRegistry.getObject(resourcelocation);
                     return soundeventaccessor1 == null ? 0 : soundeventaccessor1.getWeight();
                  }

                  public iP cloneEntry() {
                     iQ soundeventaccessor1 = (iQ)iU.this.soundRegistry.getObject(resourcelocation);
                     if (soundeventaccessor1 == null) {
                        return iU.MISSING_SOUND;
                     } else {
                        iP sound1 = soundeventaccessor1.cloneEntry();
                        return new iP(sound1.getSoundLocation().toString(), sound1.getVolume() * sound.getVolume(), sound1.getPitch() * sound.getPitch(), sound.getWeight(), iO.FILE, sound1.isStreaming() || sound.isStreaming());
                     }
                  }

                  // $FF: synthetic method
                  // $FF: bridge method
                  public Object cloneEntry() {
                     return this.cloneEntry();
                  }
               };
               break;
            default:
               throw new IllegalStateException("Unknown SoundEventRegistration type: " + sound.getType());
         }

         soundeventaccessor.addSound((iD)isoundeventaccessor);
      }

   }

   private boolean validateSoundResource(iP p_184401_1_, ResourceLocation p_184401_2_) {
      ResourceLocation resourcelocation = p_184401_1_.getSoundAsOggLocation();
      Az iresource = null;

      boolean var7;
      try {
         boolean flag;
         try {
            iresource = this.resourceManager.getResource(resourcelocation);
            iresource.getInputStream();
            boolean var15 = true;
            return var15;
         } catch (FileNotFoundException var12) {
            LOGGER.warn("File {} does not exist, cannot add it to event {}", resourcelocation, p_184401_2_);
            flag = false;
            return flag;
         } catch (IOException var13) {
            IOException ioexception = var13;
            LOGGER.warn("Could not load sound file {}, cannot add it to event {}", resourcelocation, p_184401_2_, ioexception);
            flag = false;
            var7 = flag;
         }
      } finally {
         IOUtils.closeQuietly(iresource);
      }

      return var7;
   }

   @Nullable
   public iQ getAccessor(ResourceLocation location) {
      return (iQ)this.soundRegistry.getObject(location);
   }

   public void playSound(iC sound) {
      this.sndManager.playSound(sound);
   }

   public void playDelayedSound(iC sound, int delay) {
      this.sndManager.playDelayedSound(sound, delay);
   }

   public void setListener(ME player, float p_147691_2_) {
      this.sndManager.setListener(player, p_147691_2_);
   }

   public void pauseSounds() {
      this.sndManager.pauseAllSounds();
   }

   public void stopSounds() {
      this.sndManager.stopAllSounds();
   }

   public void unloadSounds() {
      this.sndManager.unloadSoundSystem();
   }

   public void update() {
      this.sndManager.updateAllSounds();
   }

   public void resumeSounds() {
      this.sndManager.resumeAllSounds();
   }

   public void setSoundLevel(SoundCategory category, float volume) {
      if (category == SoundCategory.MASTER && volume <= 0.0F) {
         this.stopSounds();
      }

      this.sndManager.setVolume(category, volume);
   }

   public void stopSound(iC soundIn) {
      this.sndManager.stopSound(soundIn);
   }

   public boolean isSoundPlaying(iC sound) {
      return this.sndManager.isSoundPlaying(sound);
   }

   public void addListener(iE listener) {
      this.sndManager.addListener(listener);
   }

   public void removeListener(iE listener) {
      this.sndManager.removeListener(listener);
   }

   public void stop(String p_189520_1_, SoundCategory p_189520_2_) {
      this.sndManager.stop(p_189520_1_, p_189520_2_);
   }

   static {
      MISSING_SOUND = new iP("meta:missing_sound", 1.0F, 1.0F, 1, iO.FILE, false);
      LOGGER = LogManager.getLogger();
      GSON = (new GsonBuilder()).registerTypeHierarchyAdapter(ITextComponent.class, new ITextComponent.Serializer()).registerTypeAdapter(iV.class, new iW()).create();
      TYPE = new ParameterizedType() {
         public Type[] getActualTypeArguments() {
            return new Type[]{String.class, iV.class};
         }

         public Type getRawType() {
            return Map.class;
         }

         public Type getOwnerType() {
            return null;
         }
      };
   }
}
