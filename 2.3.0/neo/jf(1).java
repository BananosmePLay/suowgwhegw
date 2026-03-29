package neo;

import com.mojang.authlib.GameProfile;
import java.io.File;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;

public abstract class jf extends ME {
   private pB playerInfo;
   public float rotateElytraX;
   public float rotateElytraY;
   public float rotateElytraZ;
   private ResourceLocation locationOfCape = null;
   private long reloadCapeTimeMs = 0L;
   private boolean elytraOfCape = false;
   private String nameClear = null;
   public Mc entityShoulderLeft;
   public Mc entityShoulderRight;

   public jf(bij worldIn, GameProfile playerProfile) {
      super(worldIn, playerProfile);
      this.nameClear = playerProfile.getName();
      if (this.nameClear != null && !this.nameClear.isEmpty()) {
         this.nameClear = StringUtils.stripControlCodes(this.nameClear);
      }

      bnn.downloadCape(this);
      bns.getPlayerConfiguration(this);
   }

   public boolean isSpectator() {
      pB networkplayerinfo = nC.getMinecraft().getConnection().getPlayerInfo(this.getGameProfile().getId());
      return networkplayerinfo != null && networkplayerinfo.getGameType() == bbb.SPECTATOR;
   }

   public boolean isCreative() {
      pB networkplayerinfo = nC.getMinecraft().getConnection().getPlayerInfo(this.getGameProfile().getId());
      return networkplayerinfo != null && networkplayerinfo.getGameType() == bbb.CREATIVE;
   }

   public boolean hasPlayerInfo() {
      return this.getPlayerInfo() != null;
   }

   @Nullable
   protected pB getPlayerInfo() {
      if (this.playerInfo == null) {
         this.playerInfo = nC.getMinecraft().getConnection().getPlayerInfo(this.getUniqueID());
      }

      return this.playerInfo;
   }

   public boolean hasSkin() {
      pB networkplayerinfo = this.getPlayerInfo();
      return networkplayerinfo != null && networkplayerinfo.hasLocationSkin();
   }

   public ResourceLocation getLocationSkin() {
      pB networkplayerinfo = this.getPlayerInfo();
      return networkplayerinfo == null ? Ap.getDefaultSkin(this.getUniqueID()) : networkplayerinfo.getLocationSkin();
   }

   @Nullable
   public ResourceLocation getLocationCape() {
      if (!XH.isShowCapes()) {
         return null;
      } else {
         if (this.reloadCapeTimeMs != 0L && System.currentTimeMillis() > this.reloadCapeTimeMs) {
            bnn.reloadCape(this);
            this.reloadCapeTimeMs = 0L;
         }

         if (this.locationOfCape != null) {
            return this.locationOfCape;
         } else {
            pB networkplayerinfo = this.getPlayerInfo();
            return networkplayerinfo == null ? null : networkplayerinfo.getLocationCape();
         }
      }
   }

   public boolean isPlayerInfoSet() {
      return this.getPlayerInfo() != null;
   }

   @Nullable
   public ResourceLocation getLocationElytra() {
      pB networkplayerinfo = this.getPlayerInfo();
      return networkplayerinfo == null ? null : networkplayerinfo.getLocationElytra();
   }

   public static zm getDownloadImageSkin(ResourceLocation resourceLocationIn, String username) {
      zf texturemanager = nC.getMinecraft().getTextureManager();
      yR itextureobject = texturemanager.getTexture(resourceLocationIn);
      if (itextureobject == null) {
         itextureobject = new zm((File)null, String.format("http://skins.minecraft.net/MinecraftSkins/%s.png", StringUtils.stripControlCodes(username)), Ap.getDefaultSkin(getOfflineUUID(username)), new yj());
         texturemanager.loadTexture(resourceLocationIn, (yR)itextureobject);
      }

      return (zm)itextureobject;
   }

   public static ResourceLocation getLocationSkin(String username) {
      return new ResourceLocation("skins/" + StringUtils.stripControlCodes(username));
   }

   public String getSkinType() {
      pB networkplayerinfo = this.getPlayerInfo();
      return networkplayerinfo == null ? Ap.getSkinType(this.getUniqueID()) : networkplayerinfo.getSkinType();
   }

   public float getFovModifier() {
      float f = 1.0F;
      if (this.capabilities.isFlying) {
         f *= 1.1F;
      }

      FZ iattributeinstance = this.getEntityAttribute(Ni.MOVEMENT_SPEED);
      f = (float)((double)f * ((iattributeinstance.getAttributeValue() / (double)this.capabilities.getWalkSpeed() + 1.0) / 2.0));
      if (this.capabilities.getWalkSpeed() == 0.0F || Float.isNaN(f) || Float.isInfinite(f)) {
         f = 1.0F;
      }

      if (this.isHandActive() && this.getActiveItemStack().getItem() instanceof Pd) {
         int i = this.getItemInUseMaxCount();
         float f1 = (float)i / 20.0F;
         if (f1 > 1.0F) {
            f1 = 1.0F;
         } else {
            f1 *= f1;
         }

         f *= 1.0F - f1 * 0.15F;
      }

      return bnK.ForgeHooksClient_getOffsetFOV.exists() ? bnK.callFloat(bnK.ForgeHooksClient_getOffsetFOV, this, f) : f;
   }

   public String getNameClear() {
      return this.nameClear;
   }

   public ResourceLocation getLocationOfCape() {
      return this.locationOfCape;
   }

   public void setLocationOfCape(ResourceLocation p_setLocationOfCape_1_) {
      this.locationOfCape = p_setLocationOfCape_1_;
   }

   public boolean hasElytraCape() {
      ResourceLocation resourcelocation = this.getLocationCape();
      if (resourcelocation == null) {
         return false;
      } else {
         return resourcelocation == this.locationOfCape ? this.elytraOfCape : true;
      }
   }

   public void setElytraOfCape(boolean p_setElytraOfCape_1_) {
      this.elytraOfCape = p_setElytraOfCape_1_;
   }

   public boolean isElytraOfCape() {
      return this.elytraOfCape;
   }

   public long getReloadCapeTimeMs() {
      return this.reloadCapeTimeMs;
   }

   public void setReloadCapeTimeMs(long p_setReloadCapeTimeMs_1_) {
      this.reloadCapeTimeMs = p_setReloadCapeTimeMs_1_;
   }
}
