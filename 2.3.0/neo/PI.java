package neo;

import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;

public class PI extends OL {
   public final int itemUseDuration;
   private final int healAmount;
   private final float saturationModifier;
   private final boolean isWolfsFavoriteMeat;
   private boolean alwaysEdible;
   private VZ potionId;
   private float potionEffectProbability;

   public PI(int amount, float saturation, boolean isWolfFood) {
      this.itemUseDuration = 32;
      this.healAmount = amount;
      this.isWolfsFavoriteMeat = isWolfFood;
      this.saturationModifier = saturation;
      this.setCreativeTab(EN.FOOD);
   }

   public PI(int amount, boolean isWolfFood) {
      this(amount, 0.6F, isWolfFood);
   }

   public Qy onItemUseFinish(Qy stack, bij worldIn, Iw entityLiving) {
      if (entityLiving instanceof ME) {
         ME entityplayer = (ME)entityLiving;
         entityplayer.getFoodStats().addStats(this, stack);
         worldIn.playSound((ME)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, NO.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
         this.onFoodEaten(stack, worldIn, entityplayer);
         entityplayer.addStat(XV.getObjectUseStats(this));
         if (entityplayer instanceof MG) {
            bY.CONSUME_ITEM.trigger((MG)entityplayer, stack);
         }
      }

      stack.shrink(1);
      return stack;
   }

   protected void onFoodEaten(Qy stack, bij worldIn, ME player) {
      if (!worldIn.isRemote && this.potionId != null && worldIn.rand.nextFloat() < this.potionEffectProbability) {
         player.addPotionEffect(new VZ(this.potionId));
      }

   }

   public int getMaxItemUseDuration(Qy stack) {
      return 32;
   }

   public Ol getItemUseAction(Qy stack) {
      return Ol.EAT;
   }

   public ActionResult<Qy> onItemRightClick(bij worldIn, ME playerIn, EnumHand handIn) {
      Qy itemstack = playerIn.getHeldItem(handIn);
      if (playerIn.canEat(this.alwaysEdible)) {
         playerIn.setActiveHand(handIn);
         return new ActionResult(EnumActionResult.SUCCESS, itemstack);
      } else {
         return new ActionResult(EnumActionResult.FAIL, itemstack);
      }
   }

   public int getHealAmount(Qy stack) {
      return this.healAmount;
   }

   public float getSaturationModifier(Qy stack) {
      return this.saturationModifier;
   }

   public boolean isWolfsFavoriteMeat() {
      return this.isWolfsFavoriteMeat;
   }

   public PI setPotionEffect(VZ effect, float probability) {
      this.potionId = effect;
      this.potionEffectProbability = probability;
      return this;
   }

   public PI setAlwaysEdible() {
      this.alwaysEdible = true;
      return this;
   }
}
