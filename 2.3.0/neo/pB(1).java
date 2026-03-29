package neo;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class pB {
   private final GameProfile gameProfile;
   Map<MinecraftProfileTexture.Type, ResourceLocation> playerTextures = Maps.newEnumMap(MinecraftProfileTexture.Type.class);
   private bbb gameType;
   private int responseTime;
   private boolean playerTexturesLoaded;
   private String skinType;
   private ITextComponent displayName;
   private int lastHealth;
   private int displayHealth;
   private long lastHealthTime;
   private long healthBlinkTime;
   private long renderVisibilityId;
   private boolean bot;

   public pB(Uv entry) {
      this.gameProfile = entry.getProfile();
      this.gameType = entry.getGameMode();
      this.responseTime = entry.getPing();
      this.displayName = entry.getDisplayName();
      this.bot = 0e.get(this.gameProfile.getName()) != null;
   }

   public boolean isBot() {
      return this.bot;
   }

   public GameProfile getGameProfile() {
      return this.gameProfile;
   }

   public bbb getGameType() {
      return this.gameType;
   }

   public void setGameType(bbb gameMode) {
      this.gameType = gameMode;
   }

   public int getResponseTime() {
      return this.responseTime;
   }

   public void setResponseTime(int latency) {
      this.responseTime = latency;
   }

   public boolean hasLocationSkin() {
      return this.getLocationSkin() != null;
   }

   public String getSkinType() {
      return this.skinType == null ? Ap.getSkinType(this.gameProfile.getId()) : this.skinType;
   }

   public ResourceLocation getLocationSkin() {
      this.loadPlayerTextures();
      return (ResourceLocation)MoreObjects.firstNonNull(this.playerTextures.get(Type.SKIN), Ap.getDefaultSkin(this.gameProfile.getId()));
   }

   @Nullable
   public ResourceLocation getLocationCape() {
      this.loadPlayerTextures();
      return (ResourceLocation)this.playerTextures.get(Type.CAPE);
   }

   @Nullable
   public ResourceLocation getLocationElytra() {
      this.loadPlayerTextures();
      return (ResourceLocation)this.playerTextures.get(Type.ELYTRA);
   }

   @Nullable
   public WA getPlayerTeam() {
      return nC.getMinecraft().world.getScoreboard().getPlayersTeam(this.getGameProfile().getName());
   }

   protected void loadPlayerTextures() {
      synchronized(this) {
         if (!this.playerTexturesLoaded) {
            this.playerTexturesLoaded = true;
            nC.getMinecraft().getSkinManager().loadProfileTextures(this.gameProfile, new Bd() {
               public void skinAvailable(MinecraftProfileTexture.Type typeIn, ResourceLocation location, MinecraftProfileTexture profileTexture) {
                  switch (typeIn) {
                     case SKIN:
                        pB.this.playerTextures.put(Type.SKIN, location);
                        pB.this.skinType = profileTexture.getMetadata("model");
                        if (pB.this.skinType == null) {
                           pB.this.skinType = "default";
                        }
                        break;
                     case CAPE:
                        pB.this.playerTextures.put(Type.CAPE, location);
                        break;
                     case ELYTRA:
                        pB.this.playerTextures.put(Type.ELYTRA, location);
                  }

               }
            }, true);
         }

      }
   }

   public void setDisplayName(@Nullable ITextComponent displayNameIn) {
      this.displayName = displayNameIn;
   }

   @Nullable
   public ITextComponent getDisplayName() {
      return this.displayName;
   }

   public int getLastHealth() {
      return this.lastHealth;
   }

   public void setLastHealth(int p_178836_1_) {
      this.lastHealth = p_178836_1_;
   }

   public int getDisplayHealth() {
      return this.displayHealth;
   }

   public void setDisplayHealth(int p_178857_1_) {
      this.displayHealth = p_178857_1_;
   }

   public long getLastHealthTime() {
      return this.lastHealthTime;
   }

   public void setLastHealthTime(long p_178846_1_) {
      this.lastHealthTime = p_178846_1_;
   }

   public long getHealthBlinkTime() {
      return this.healthBlinkTime;
   }

   public void setHealthBlinkTime(long p_178844_1_) {
      this.healthBlinkTime = p_178844_1_;
   }

   public long getRenderVisibilityId() {
      return this.renderVisibilityId;
   }

   public void setRenderVisibilityId(long p_178843_1_) {
      this.renderVisibilityId = p_178843_1_;
   }
}
