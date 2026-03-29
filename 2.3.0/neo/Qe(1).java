package neo;

import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.translation.I18n;

public class Qe extends OL {
   public Qe() {
      this.setMaxStackSize(1);
      this.setCreativeTab(EN.BREWING);
   }

   public Qy getDefaultInstance() {
      return Wg.addPotionToItemStack(super.getDefaultInstance(), NN.WATER);
   }

   public Qy onItemUseFinish(Qy stack, bij worldIn, Iw entityLiving) {
      ME entityplayer = entityLiving instanceof ME ? (ME)entityLiving : null;
      if (entityplayer == null || !entityplayer.capabilities.isCreativeMode) {
         stack.shrink(1);
      }

      if (entityplayer instanceof MG) {
         bY.CONSUME_ITEM.trigger((MG)entityplayer, stack);
      }

      if (!worldIn.isRemote) {
         Iterator var5 = Wg.getEffectsFromStack(stack).iterator();

         while(var5.hasNext()) {
            VZ potioneffect = (VZ)var5.next();
            if (potioneffect.getPotion().isInstant()) {
               potioneffect.getPotion().affectEntity(entityplayer, entityplayer, entityLiving, potioneffect.getAmplifier(), 1.0);
            } else {
               entityLiving.addPotionEffect(new VZ(potioneffect));
            }
         }
      }

      if (entityplayer != null) {
         entityplayer.addStat(XV.getObjectUseStats(this));
      }

      if (entityplayer == null || !entityplayer.capabilities.isCreativeMode) {
         if (stack.isEmpty()) {
            return new Qy(NK.GLASS_BOTTLE);
         }

         if (entityplayer != null) {
            entityplayer.inventory.addItemStackToInventory(new Qy(NK.GLASS_BOTTLE));
         }
      }

      return stack;
   }

   public int getMaxItemUseDuration(Qy stack) {
      return 32;
   }

   public Ol getItemUseAction(Qy stack) {
      return Ol.DRINK;
   }

   public ActionResult<Qy> onItemRightClick(bij worldIn, ME playerIn, EnumHand handIn) {
      playerIn.setActiveHand(handIn);
      return new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
   }

   public String getItemStackDisplayName(Qy stack) {
      return I18n.translateToLocal(Wg.getPotionFromItem(stack).getNamePrefixed("potion.effect."));
   }

   public void addInformation(Qy stack, @Nullable bij worldIn, List<String> tooltip, BJ flagIn) {
      Wg.addPotionTooltip(stack, tooltip, 1.0F);
   }

   public boolean hasEffect(Qy stack) {
      return super.hasEffect(stack) || !Wg.getEffectsFromStack(stack).isEmpty();
   }

   public void getSubItems(EN tab, NonNullList<Qy> items) {
      if (this.isInCreativeTab(tab)) {
         Iterator var3 = Wf.REGISTRY.iterator();

         while(var3.hasNext()) {
            Wf potiontype = (Wf)var3.next();
            if (potiontype != NN.EMPTY) {
               items.add(Wg.addPotionToItemStack(new Qy(this), potiontype));
            }
         }
      }

   }
}
