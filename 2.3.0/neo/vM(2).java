package neo;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class vM extends wy<IN> {
   public static final ResourceLocation TEXTURE_ARMOR_STAND = new ResourceLocation("textures/entity/armorstand/wood.png");

   public vM(wC manager) {
      super(manager, new nE(), 0.0F);
      vf layerbipedarmor = new vf(this) {
         protected void initArmor() {
            this.modelLeggings = new nF(0.5F);
            this.modelArmor = new nF(1.0F);
         }
      };
      this.addLayer(layerbipedarmor);
      this.addLayer(new vr(this));
      this.addLayer(new vk(this));
      this.addLayer(new vi(this.getMainModel().bipedHead));
   }

   protected ResourceLocation getEntityTexture(IN entity) {
      return TEXTURE_ARMOR_STAND;
   }

   public nE getMainModel() {
      return (nE)super.getMainModel();
   }

   protected void applyRotations(IN entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      yh.rotate(180.0F - rotationYaw, 0.0F, 1.0F, 0.0F);
      float f = (float)(entityLiving.world.getTotalWorldTime() - entityLiving.punchCooldown) + partialTicks;
      if (f < 5.0F) {
         yh.rotate(MathHelper.sin(f / 1.5F * 3.1415927F) * 3.0F, 0.0F, 1.0F, 0.0F);
      }

   }

   protected boolean canRenderName(IN entity) {
      return entity.getAlwaysRenderNameTag();
   }

   public void doRender(IN entity, double x, double y, double z, float entityYaw, float partialTicks) {
      if (entity.hasMarker()) {
         this.renderMarker = true;
      }

      super.doRender((Iw)entity, x, y, z, entityYaw, partialTicks);
      if (entity.hasMarker()) {
         this.renderMarker = false;
      }

   }

   // $FF: synthetic method
   // $FF: bridge method
   protected boolean canRenderName(Iw var1) {
      return this.canRenderName((IN)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void applyRotations(Iw var1, float var2, float var3, float var4) {
      this.applyRotations((IN)var1, var2, var3, var4);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Iw var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((IN)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public nH getMainModel() {
      return this.getMainModel();
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((IN)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected boolean canRenderName(Ig var1) {
      return this.canRenderName((IN)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Ig var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((IN)var1, var2, var4, var6, var8, var9);
   }
}
