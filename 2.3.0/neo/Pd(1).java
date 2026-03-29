package neo;

import javax.annotation.Nullable;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;

public class Pd extends OL {
   public Pd() {
      this.maxStackSize = 1;
      this.setMaxDamage(384);
      this.setCreativeTab(EN.COMBAT);
      this.addPropertyOverride(new ResourceLocation("pull"), new Oo() {
         public float apply(Qy stack, @Nullable bij worldIn, @Nullable Iw entityIn) {
            if (entityIn == null) {
               return 0.0F;
            } else {
               return entityIn.getActiveItemStack().getItem() != NK.BOW ? 0.0F : (float)(stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 20.0F;
            }
         }
      });
      this.addPropertyOverride(new ResourceLocation("pulling"), new Oo() {
         public float apply(Qy stack, @Nullable bij worldIn, @Nullable Iw entityIn) {
            return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
         }
      });
   }

   private Qy findAmmo(ME player) {
      if (this.isArrow(player.getHeldItem(EnumHand.OFF_HAND))) {
         return player.getHeldItem(EnumHand.OFF_HAND);
      } else if (this.isArrow(player.getHeldItem(EnumHand.MAIN_HAND))) {
         return player.getHeldItem(EnumHand.MAIN_HAND);
      } else {
         for(int i = 0; i < player.inventory.getSizeInventory(); ++i) {
            Qy itemstack = player.inventory.getStackInSlot(i);
            if (this.isArrow(itemstack)) {
               return itemstack;
            }
         }

         return Qy.EMPTY;
      }
   }

   protected boolean isArrow(Qy stack) {
      return stack.getItem() instanceof OT;
   }

   public void onPlayerStoppedUsing(Qy stack, bij worldIn, Iw entityLiving, int timeLeft) {
      if (entityLiving instanceof ME) {
         ME entityplayer = (ME)entityLiving;
         boolean flag = entityplayer.capabilities.isCreativeMode || Ft.getEnchantmentLevel(NJ.INFINITY, stack) > 0;
         Qy itemstack = this.findAmmo(entityplayer);
         if (!itemstack.isEmpty() || flag) {
            if (itemstack.isEmpty()) {
               itemstack = new Qy(NK.ARROW);
            }

            int i = this.getMaxItemUseDuration(stack) - timeLeft;
            float f = getArrowVelocity(i);
            if ((double)f >= 0.1) {
               boolean flag1 = flag && itemstack.getItem() == NK.ARROW;
               if (!worldIn.isRemote) {
                  OT itemarrow = (OT)((OT)(itemstack.getItem() instanceof OT ? itemstack.getItem() : NK.ARROW));
                  MO entityarrow = itemarrow.createArrow(worldIn, itemstack, entityplayer);
                  entityarrow.shoot(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F, f * 3.0F, 1.0F);
                  if (f == 1.0F) {
                     entityarrow.setIsCritical(true);
                  }

                  int j = Ft.getEnchantmentLevel(NJ.POWER, stack);
                  if (j > 0) {
                     entityarrow.setDamage(entityarrow.getDamage() + (double)j * 0.5 + 0.5);
                  }

                  int k = Ft.getEnchantmentLevel(NJ.PUNCH, stack);
                  if (k > 0) {
                     entityarrow.setKnockbackStrength(k);
                  }

                  if (Ft.getEnchantmentLevel(NJ.FLAME, stack) > 0) {
                     entityarrow.setFire(100);
                  }

                  stack.damageItem(1, entityplayer);
                  if (flag1 || entityplayer.capabilities.isCreativeMode && (itemstack.getItem() == NK.SPECTRAL_ARROW || itemstack.getItem() == NK.TIPPED_ARROW)) {
                     entityarrow.pickupStatus = MN.CREATIVE_ONLY;
                  }

                  worldIn.spawnEntity(entityarrow);
               }

               worldIn.playSound((ME)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, NO.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
               if (!flag1 && !entityplayer.capabilities.isCreativeMode) {
                  itemstack.shrink(1);
                  if (itemstack.isEmpty()) {
                     entityplayer.inventory.deleteStack(itemstack);
                  }
               }

               entityplayer.addStat(XV.getObjectUseStats(this));
            }
         }
      }

   }

   public static float getArrowVelocity(int charge) {
      float f = (float)charge / 20.0F;
      f = (f * f + f * 2.0F) / 3.0F;
      if (f > 1.0F) {
         f = 1.0F;
      }

      return f;
   }

   public int getMaxItemUseDuration(Qy stack) {
      return 72000;
   }

   public Ol getItemUseAction(Qy stack) {
      return Ol.BOW;
   }

   public ActionResult<Qy> onItemRightClick(bij worldIn, ME playerIn, EnumHand handIn) {
      Qy itemstack = playerIn.getHeldItem(handIn);
      boolean flag = !this.findAmmo(playerIn).isEmpty();
      if (!playerIn.capabilities.isCreativeMode && !flag) {
         return flag ? new ActionResult(EnumActionResult.PASS, itemstack) : new ActionResult(EnumActionResult.FAIL, itemstack);
      } else {
         playerIn.setActiveHand(handIn);
         return new ActionResult(EnumActionResult.SUCCESS, itemstack);
      }
   }

   public int getItemEnchantability() {
      return 1;
   }
}
