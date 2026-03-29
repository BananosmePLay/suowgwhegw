package neo;

import com.mojang.authlib.GameProfile;
import java.util.UUID;
import net.minecraft.util.EnumFacing;
import org.apache.commons.lang3.StringUtils;

public class zw {
   private static final YN[] SHULKER_BOXES = new YN[16];
   public static zw instance;
   private final Yn chestBasic;
   private final Yn chestTrap;
   private final Yw enderChest;
   private final Yh banner;
   private final Yk bed;
   private final YR skull;
   private final oz modelShield;

   public zw() {
      this.chestBasic = new Yn(cS.BASIC);
      this.chestTrap = new Yn(cS.TRAP);
      this.enderChest = new Yw();
      this.banner = new Yh();
      this.bed = new Yk();
      this.skull = new YR();
      this.modelShield = new oz();
   }

   public void renderByItem(Qy itemStackIn) {
      this.renderByItem(itemStackIn, 1.0F);
   }

   public void renderByItem(Qy itemStackIn, float partialTicks) {
      OL item = itemStackIn.getItem();
      if (item == NK.BANNER) {
         this.banner.setItemValues(itemStackIn, false);
         zz.instance.render(this.banner, 0.0, 0.0, 0.0, 0.0F, partialTicks);
      } else if (item == NK.BED) {
         this.bed.setItemValues(itemStackIn);
         zz.instance.render(this.bed, 0.0, 0.0, 0.0, 0.0F);
      } else if (item == NK.SHIELD) {
         if (itemStackIn.getSubCompound("BlockEntityTag") != null) {
            this.banner.setItemValues(itemStackIn, true);
            nC.getMinecraft().getTextureManager().bindTexture(rJ.SHIELD_DESIGNS.getResourceLocation(this.banner.getPatternResourceLocation(), this.banner.getPatternList(), this.banner.getColorList()));
         } else {
            nC.getMinecraft().getTextureManager().bindTexture(rJ.SHIELD_BASE_TEXTURE);
         }

         yh.pushMatrix();
         yh.scale(1.0F, -1.0F, -1.0F);
         this.modelShield.render();
         yh.popMatrix();
      } else if (item == NK.SKULL) {
         GameProfile gameprofile = null;
         if (itemStackIn.hasTagCompound()) {
            QQ nbttagcompound = itemStackIn.getTagCompound();
            if (nbttagcompound.hasKey("SkullOwner", 10)) {
               gameprofile = Rb.readGameProfileFromNBT(nbttagcompound.getCompoundTag("SkullOwner"));
            } else if (nbttagcompound.hasKey("SkullOwner", 8) && !StringUtils.isBlank(nbttagcompound.getString("SkullOwner"))) {
               GameProfile gameprofile1 = new GameProfile((UUID)null, nbttagcompound.getString("SkullOwner"));
               gameprofile = YR.updateGameProfile(gameprofile1);
               nbttagcompound.removeTag("SkullOwner");
               nbttagcompound.setTag("SkullOwner", Rb.writeGameProfile(new QQ(), gameprofile));
            }
         }

         if (zE.instance != null) {
            yh.pushMatrix();
            yh.disableCull();
            zE.instance.renderSkull(0.0F, 0.0F, 0.0F, EnumFacing.UP, 180.0F, itemStackIn.getMetadata(), gameprofile, -1, 0.0F);
            yh.enableCull();
            yh.popMatrix();
         }
      } else if (item == OL.getItemFromBlock(Nk.ENDER_CHEST)) {
         zz.instance.render(this.enderChest, 0.0, 0.0, 0.0, 0.0F, partialTicks);
      } else if (item == OL.getItemFromBlock(Nk.TRAPPED_CHEST)) {
         zz.instance.render(this.chestTrap, 0.0, 0.0, 0.0, 0.0F, partialTicks);
      } else if (co.getBlockFromItem(item) instanceof gr) {
         zz.instance.render(SHULKER_BOXES[gr.getColorFromItem(item).getMetadata()], 0.0, 0.0, 0.0, 0.0F, partialTicks);
      } else {
         zz.instance.render(this.chestBasic, 0.0, 0.0, 0.0, 0.0F, partialTicks);
      }

   }

   static {
      Om[] var0 = Om.values();
      int var1 = var0.length;

      for(int var2 = 0; var2 < var1; ++var2) {
         Om enumdyecolor = var0[var2];
         SHULKER_BOXES[enumdyecolor.getMetadata()] = new YN(enumdyecolor);
      }

      instance = new zw();
   }
}
