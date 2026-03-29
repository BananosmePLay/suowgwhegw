package neo;

import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;

public class vp implements vw<ME> {
   private final wC renderManager;
   protected wy<? extends Iw> leftRenderer;
   private nH leftModel;
   private ResourceLocation leftResource;
   private UUID leftUniqueId;
   private Class<?> leftEntityClass;
   protected wy<? extends Iw> rightRenderer;
   private nH rightModel;
   private ResourceLocation rightResource;
   private UUID rightUniqueId;
   private Class<?> rightEntityClass;

   public vp(wC p_i47370_1_) {
      this.renderManager = p_i47370_1_;
   }

   public void doRenderLayer(ME entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      if (entitylivingbaseIn.getLeftShoulderEntity() != null || entitylivingbaseIn.getRightShoulderEntity() != null) {
         yh.enableRescaleNormal();
         yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         QQ nbttagcompound = entitylivingbaseIn.getLeftShoulderEntity();
         if (!nbttagcompound.isEmpty()) {
            vo layerentityonshoulder$dataholder = this.renderEntityOnShoulder(entitylivingbaseIn, this.leftUniqueId, nbttagcompound, this.leftRenderer, this.leftModel, this.leftResource, this.leftEntityClass, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale, true);
            this.leftUniqueId = layerentityonshoulder$dataholder.entityId;
            this.leftRenderer = layerentityonshoulder$dataholder.renderer;
            this.leftResource = layerentityonshoulder$dataholder.textureLocation;
            this.leftModel = layerentityonshoulder$dataholder.model;
            this.leftEntityClass = layerentityonshoulder$dataholder.clazz;
         }

         QQ nbttagcompound1 = entitylivingbaseIn.getRightShoulderEntity();
         if (!nbttagcompound1.isEmpty()) {
            vo layerentityonshoulder$dataholder1 = this.renderEntityOnShoulder(entitylivingbaseIn, this.rightUniqueId, nbttagcompound1, this.rightRenderer, this.rightModel, this.rightResource, this.rightEntityClass, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale, false);
            this.rightUniqueId = layerentityonshoulder$dataholder1.entityId;
            this.rightRenderer = layerentityonshoulder$dataholder1.renderer;
            this.rightResource = layerentityonshoulder$dataholder1.textureLocation;
            this.rightModel = layerentityonshoulder$dataholder1.model;
            this.rightEntityClass = layerentityonshoulder$dataholder1.clazz;
         }

         yh.disableRescaleNormal();
      }

   }

   private vo renderEntityOnShoulder(ME p_192864_1_, @Nullable UUID p_192864_2_, QQ p_192864_3_, wy<? extends Iw> p_192864_4_, nH p_192864_5_, ResourceLocation p_192864_6_, Class<?> p_192864_7_, float p_192864_8_, float p_192864_9_, float p_192864_10_, float p_192864_11_, float p_192864_12_, float p_192864_13_, float p_192864_14_, boolean p_192864_15_) {
      if (p_192864_2_ == null || !p_192864_2_.equals(p_192864_3_.getUniqueId("UUID"))) {
         p_192864_2_ = p_192864_3_.getUniqueId("UUID");
         p_192864_7_ = Ir.getClassFromName(p_192864_3_.getString("id"));
         if (p_192864_7_ == LP.class) {
            p_192864_4_ = new wI(this.renderManager);
            p_192864_5_ = new oq();
            p_192864_6_ = wI.PARROT_TEXTURES[p_192864_3_.getInteger("Variant")];
         }
      }

      Ig entity = XH.getRenderGlobal().renderedEntity;
      if (p_192864_1_ instanceof jf) {
         jf abstractclientplayer = (jf)p_192864_1_;
         Ig entity1 = p_192864_2_ == this.leftUniqueId ? abstractclientplayer.entityShoulderLeft : abstractclientplayer.entityShoulderRight;
         if (entity1 != null) {
            XH.getRenderGlobal().renderedEntity = entity1;
            if (XH.isShaders()) {
               bpq.nextEntity(entity1);
            }
         }
      }

      ((wy)p_192864_4_).bindTexture(p_192864_6_);
      yh.pushMatrix();
      float f = p_192864_1_.isSneaking() ? -1.3F : -1.5F;
      float f1 = p_192864_15_ ? 0.4F : -0.4F;
      yh.translate(f1, f, 0.0F);
      if (p_192864_7_ == LP.class) {
         p_192864_11_ = 0.0F;
      }

      ((nH)p_192864_5_).setLivingAnimations(p_192864_1_, p_192864_8_, p_192864_9_, p_192864_10_);
      ((nH)p_192864_5_).setRotationAngles(p_192864_8_, p_192864_9_, p_192864_11_, p_192864_12_, p_192864_13_, p_192864_14_, p_192864_1_);
      ((nH)p_192864_5_).render(p_192864_1_, p_192864_8_, p_192864_9_, p_192864_11_, p_192864_12_, p_192864_13_, p_192864_14_);
      yh.popMatrix();
      XH.getRenderGlobal().renderedEntity = entity;
      if (XH.isShaders()) {
         bpq.nextEntity(entity);
      }

      return new vo(this, p_192864_2_, (wy)p_192864_4_, (nH)p_192864_5_, p_192864_6_, p_192864_7_);
   }

   public boolean shouldCombineTextures() {
      return false;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRenderLayer(Iw var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      this.doRenderLayer((ME)var1, var2, var3, var4, var5, var6, var7, var8);
   }
}
