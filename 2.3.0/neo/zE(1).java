package neo;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class zE extends zF<YR> {
   private static final ResourceLocation SKELETON_TEXTURES = new ResourceLocation("textures/entity/skeleton/skeleton.png");
   private static final ResourceLocation WITHER_SKELETON_TEXTURES = new ResourceLocation("textures/entity/skeleton/wither_skeleton.png");
   private static final ResourceLocation ZOMBIE_TEXTURES = new ResourceLocation("textures/entity/zombie/zombie.png");
   private static final ResourceLocation CREEPER_TEXTURES = new ResourceLocation("textures/entity/creeper/creeper.png");
   private static final ResourceLocation DRAGON_TEXTURES = new ResourceLocation("textures/entity/enderdragon/dragon.png");
   private final nW dragonHead = new nW(0.0F);
   public static zE instance;
   private final oF skeletonHead = new oF(0, 0, 64, 32);
   private final oF humanoidHead = new of();

   public zE() {
   }

   public void render(YR te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
      EnumFacing enumfacing = EnumFacing.byIndex(te.getBlockMetadata() & 7);
      float f = te.getAnimationProgress(partialTicks);
      this.renderSkull((float)x, (float)y, (float)z, enumfacing, (float)(te.getSkullRotation() * 360) / 16.0F, te.getSkullType(), te.getPlayerProfile(), destroyStage, f);
   }

   public void setRendererDispatcher(zz rendererDispatcherIn) {
      super.setRendererDispatcher(rendererDispatcherIn);
      instance = this;
   }

   public void renderSkull(float x, float y, float z, EnumFacing facing, float rotationIn, int skullType, @Nullable GameProfile profile, int destroyStage, float animateTicks) {
      nH modelbase = this.skeletonHead;
      if (destroyStage >= 0) {
         this.bindTexture(DESTROY_STAGES[destroyStage]);
         yh.matrixMode(5890);
         yh.pushMatrix();
         yh.scale(4.0F, 2.0F, 1.0F);
         yh.translate(0.0625F, 0.0625F, 0.0625F);
         yh.matrixMode(5888);
      } else {
         switch (skullType) {
            case 0:
            default:
               this.bindTexture(SKELETON_TEXTURES);
               break;
            case 1:
               this.bindTexture(WITHER_SKELETON_TEXTURES);
               break;
            case 2:
               this.bindTexture(ZOMBIE_TEXTURES);
               modelbase = this.humanoidHead;
               break;
            case 3:
               modelbase = this.humanoidHead;
               ResourceLocation resourcelocation = Ap.getDefaultSkinLegacy();
               if (profile != null) {
                  nC minecraft = nC.getMinecraft();
                  Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> map = minecraft.getSkinManager().loadSkinFromCache(profile);
                  if (map.containsKey(Type.SKIN)) {
                     resourcelocation = minecraft.getSkinManager().loadSkin((MinecraftProfileTexture)map.get(Type.SKIN), Type.SKIN);
                  } else {
                     UUID uuid = ME.getUUID(profile);
                     resourcelocation = Ap.getDefaultSkin(uuid);
                  }
               }

               this.bindTexture(resourcelocation);
               break;
            case 4:
               this.bindTexture(CREEPER_TEXTURES);
               break;
            case 5:
               this.bindTexture(DRAGON_TEXTURES);
               modelbase = this.dragonHead;
         }
      }

      yh.pushMatrix();
      yh.disableCull();
      if (facing == EnumFacing.UP) {
         yh.translate(x + 0.5F, y, z + 0.5F);
      } else {
         switch (facing) {
            case NORTH:
               yh.translate(x + 0.5F, y + 0.25F, z + 0.74F);
               break;
            case SOUTH:
               yh.translate(x + 0.5F, y + 0.25F, z + 0.26F);
               rotationIn = 180.0F;
               break;
            case WEST:
               yh.translate(x + 0.74F, y + 0.25F, z + 0.5F);
               rotationIn = 270.0F;
               break;
            case EAST:
            default:
               yh.translate(x + 0.26F, y + 0.25F, z + 0.5F);
               rotationIn = 90.0F;
         }
      }

      float f = 0.0625F;
      yh.enableRescaleNormal();
      yh.scale(-1.0F, -1.0F, 1.0F);
      yh.enableAlpha();
      if (skullType == 3) {
         yh.enableBlendProfile(xZ.PLAYER_SKIN);
      }

      ((nH)modelbase).render((Ig)null, animateTicks, 0.0F, 0.0F, rotationIn, 0.0F, 0.0625F);
      yh.popMatrix();
      if (destroyStage >= 0) {
         yh.matrixMode(5890);
         yh.popMatrix();
         yh.matrixMode(5888);
      }

   }

   // $FF: synthetic method
   // $FF: bridge method
   public void render(Yg var1, double var2, double var4, double var6, float var8, int var9, float var10) {
      this.render((YR)var1, var2, var4, var6, var8, var9, var10);
   }
}
