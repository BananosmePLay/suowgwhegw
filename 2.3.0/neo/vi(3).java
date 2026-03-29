package neo;

import com.mojang.authlib.GameProfile;
import java.util.UUID;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.EnumFacing;
import org.apache.commons.lang3.StringUtils;

public class vi implements vw<Iw> {
   private final ow modelRenderer;

   public vi(ow p_i46120_1_) {
      this.modelRenderer = p_i46120_1_;
   }

   public void doRenderLayer(Iw entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
      Qy itemstack = entitylivingbaseIn.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
      if (!itemstack.isEmpty()) {
         OL item = itemstack.getItem();
         nC minecraft = nC.getMinecraft();
         yh.pushMatrix();
         if (entitylivingbaseIn.isSneaking()) {
            yh.translate(0.0F, 0.2F, 0.0F);
         }

         boolean flag = entitylivingbaseIn instanceof Mq || entitylivingbaseIn instanceof Ll;
         float f3;
         if (entitylivingbaseIn.isChild() && !(entitylivingbaseIn instanceof Mq)) {
            f3 = 2.0F;
            float f1 = 1.4F;
            yh.translate(0.0F, 0.5F * scale, 0.0F);
            yh.scale(0.7F, 0.7F, 0.7F);
            yh.translate(0.0F, 16.0F * scale, 0.0F);
         }

         this.modelRenderer.postRender(0.0625F);
         yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         if (item == NK.SKULL) {
            f3 = 1.1875F;
            yh.scale(1.1875F, -1.1875F, -1.1875F);
            if (flag) {
               yh.translate(0.0F, 0.0625F, 0.0F);
            }

            GameProfile gameprofile = null;
            if (itemstack.hasTagCompound()) {
               QQ nbttagcompound = itemstack.getTagCompound();
               if (nbttagcompound.hasKey("SkullOwner", 10)) {
                  gameprofile = Rb.readGameProfileFromNBT(nbttagcompound.getCompoundTag("SkullOwner"));
               } else if (nbttagcompound.hasKey("SkullOwner", 8)) {
                  String s = nbttagcompound.getString("SkullOwner");
                  if (!StringUtils.isBlank(s)) {
                     gameprofile = YR.updateGameProfile(new GameProfile((UUID)null, s));
                     nbttagcompound.setTag("SkullOwner", Rb.writeGameProfile(new QQ(), gameprofile));
                  }
               }
            }

            zE.instance.renderSkull(-0.5F, 0.0F, -0.5F, EnumFacing.UP, 180.0F, itemstack.getMetadata(), gameprofile, -1, limbSwing);
         } else if (!(item instanceof OR) || ((OR)item).getEquipmentSlot() != EntityEquipmentSlot.HEAD) {
            f3 = 0.625F;
            yh.translate(0.0F, -0.25F, 0.0F);
            yh.rotate(180.0F, 0.0F, 1.0F, 0.0F);
            yh.scale(0.625F, -0.625F, -0.625F);
            if (flag) {
               yh.translate(0.0F, 0.1875F, 0.0F);
            }

            minecraft.getItemRenderer().renderItem(entitylivingbaseIn, itemstack, sf.HEAD);
         }

         yh.popMatrix();
      }

   }

   public boolean shouldCombineTextures() {
      return false;
   }
}
