package neo;

import com.google.common.collect.Iterables;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.properties.Property;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.StringUtils;

public class YR extends Yg implements ITickable {
   private int skullType;
   private int skullRotation;
   private GameProfile playerProfile;
   private int dragonAnimatedTicks;
   private boolean dragonAnimated;
   private static Xd profileCache;
   private static MinecraftSessionService sessionService;

   public YR() {
   }

   public static void setProfileCache(Xd profileCacheIn) {
      profileCache = profileCacheIn;
   }

   public static void setSessionService(MinecraftSessionService sessionServiceIn) {
      sessionService = sessionServiceIn;
   }

   public QQ writeToNBT(QQ compound) {
      super.writeToNBT(compound);
      compound.setByte("SkullType", (byte)(this.skullType & 255));
      compound.setByte("Rot", (byte)(this.skullRotation & 255));
      if (this.playerProfile != null) {
         QQ nbttagcompound = new QQ();
         Rb.writeGameProfile(nbttagcompound, this.playerProfile);
         compound.setTag("Owner", nbttagcompound);
      }

      return compound;
   }

   public void readFromNBT(QQ compound) {
      super.readFromNBT(compound);
      this.skullType = compound.getByte("SkullType");
      this.skullRotation = compound.getByte("Rot");
      if (this.skullType == 3) {
         if (compound.hasKey("Owner", 10)) {
            this.playerProfile = Rb.readGameProfileFromNBT(compound.getCompoundTag("Owner"));
         } else if (compound.hasKey("ExtraType", 8)) {
            String s = compound.getString("ExtraType");
            if (!StringUtils.isNullOrEmpty(s)) {
               this.playerProfile = new GameProfile((UUID)null, s);
               this.updatePlayerProfile();
            }
         }
      }

   }

   public void update() {
      if (this.skullType == 5) {
         if (this.world.isBlockPowered(this.pos)) {
            this.dragonAnimated = true;
            ++this.dragonAnimatedTicks;
         } else {
            this.dragonAnimated = false;
         }
      }

   }

   public float getAnimationProgress(float p_184295_1_) {
      return this.dragonAnimated ? (float)this.dragonAnimatedTicks + p_184295_1_ : (float)this.dragonAnimatedTicks;
   }

   @Nullable
   public GameProfile getPlayerProfile() {
      return this.playerProfile;
   }

   @Nullable
   public Vg getUpdatePacket() {
      return new Vg(this.pos, 4, this.getUpdateTag());
   }

   public QQ getUpdateTag() {
      return this.writeToNBT(new QQ());
   }

   public void setType(int type) {
      this.skullType = type;
      this.playerProfile = null;
   }

   public void setPlayerProfile(@Nullable GameProfile playerProfile) {
      this.skullType = 3;
      this.playerProfile = playerProfile;
      this.updatePlayerProfile();
   }

   private void updatePlayerProfile() {
      this.playerProfile = updateGameProfile(this.playerProfile);
      this.markDirty();
   }

   public static GameProfile updateGameProfile(GameProfile input) {
      if (input != null && !StringUtils.isNullOrEmpty(input.getName())) {
         if (input.isComplete() && input.getProperties().containsKey("textures")) {
            return input;
         } else if (profileCache != null && sessionService != null) {
            GameProfile gameprofile = profileCache.getGameProfileForUsername(input.getName());
            if (gameprofile == null) {
               return input;
            } else {
               Property property = (Property)Iterables.getFirst(gameprofile.getProperties().get("textures"), (Object)null);
               if (property == null) {
                  gameprofile = sessionService.fillProfileProperties(gameprofile, true);
               }

               return gameprofile;
            }
         } else {
            return input;
         }
      } else {
         return input;
      }
   }

   public int getSkullType() {
      return this.skullType;
   }

   public int getSkullRotation() {
      return this.skullRotation;
   }

   public void setSkullRotation(int rotation) {
      this.skullRotation = rotation;
   }

   public void mirror(Mirror mirrorIn) {
      if (this.world != null && this.world.getBlockState(this.getPos()).getValue(gE.FACING) == EnumFacing.UP) {
         this.skullRotation = mirrorIn.mirrorRotation(this.skullRotation, 16);
      }

   }

   public void rotate(Rotation rotationIn) {
      if (this.world != null && this.world.getBlockState(this.getPos()).getValue(gE.FACING) == EnumFacing.UP) {
         this.skullRotation = rotationIn.rotate(this.skullRotation, 16);
      }

   }
}
