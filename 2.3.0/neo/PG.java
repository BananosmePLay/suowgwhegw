package neo;

import javax.annotation.Nullable;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;

public class PG extends OL {
   public PG() {
      this.setMaxDamage(64);
      this.setMaxStackSize(1);
      this.setCreativeTab(EN.TOOLS);
      this.addPropertyOverride(new ResourceLocation("cast"), new Oo() {
         public float apply(Qy stack, @Nullable bij worldIn, @Nullable Iw entityIn) {
            if (entityIn == null) {
               return 0.0F;
            } else {
               boolean flag = entityIn.getHeldItemMainhand() == stack;
               boolean flag1 = entityIn.getHeldItemOffhand() == stack;
               if (entityIn.getHeldItemMainhand().getItem() instanceof PG) {
                  flag1 = false;
               }

               return (flag || flag1) && entityIn instanceof ME && ((ME)entityIn).fishEntity != null ? 1.0F : 0.0F;
            }
         }
      });
   }

   public boolean isFull3D() {
      return true;
   }

   public boolean shouldRotateAroundWhenRendering() {
      return true;
   }

   public ActionResult<Qy> onItemRightClick(bij worldIn, ME playerIn, EnumHand handIn) {
      Qy itemstack = playerIn.getHeldItem(handIn);
      if (playerIn.fishEntity != null) {
         int i = playerIn.fishEntity.handleHookRetraction();
         itemstack.damageItem(i, playerIn);
         playerIn.swingArm(handIn);
         worldIn.playSound((ME)null, playerIn.posX, playerIn.posY, playerIn.posZ, NO.ENTITY_BOBBER_RETRIEVE, SoundCategory.NEUTRAL, 1.0F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
      } else {
         worldIn.playSound((ME)null, playerIn.posX, playerIn.posY, playerIn.posZ, NO.ENTITY_BOBBER_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
         if (!worldIn.isRemote) {
            MU entityfishhook = new MU(worldIn, playerIn);
            int j = Ft.getFishingSpeedBonus(itemstack);
            if (j > 0) {
               entityfishhook.setLureSpeed(j);
            }

            int k = Ft.getFishingLuckBonus(itemstack);
            if (k > 0) {
               entityfishhook.setLuck(k);
            }

            worldIn.spawnEntity(entityfishhook);
         }

         playerIn.swingArm(handIn);
         playerIn.addStat(XV.getObjectUseStats(this));
      }

      return new ActionResult(EnumActionResult.SUCCESS, itemstack);
   }

   public int getItemEnchantability() {
      return 1;
   }
}
