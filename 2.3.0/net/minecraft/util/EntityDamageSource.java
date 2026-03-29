package net.minecraft.util;

import javax.annotation.Nullable;
import neo.Ig;
import neo.Iw;
import neo.ME;
import neo.Qy;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.translation.I18n;

public class EntityDamageSource extends DamageSource {
   @Nullable
   protected Ig damageSourceEntity;
   private boolean isThornsDamage;

   public EntityDamageSource(String damageTypeIn, @Nullable Ig damageSourceEntityIn) {
      super(damageTypeIn);
      this.damageSourceEntity = damageSourceEntityIn;
   }

   public EntityDamageSource setIsThornsDamage() {
      this.isThornsDamage = true;
      return this;
   }

   public boolean getIsThornsDamage() {
      return this.isThornsDamage;
   }

   @Nullable
   public Ig getTrueSource() {
      return this.damageSourceEntity;
   }

   public ITextComponent getDeathMessage(Iw entityLivingBaseIn) {
      Qy itemstack = this.damageSourceEntity instanceof Iw ? ((Iw)this.damageSourceEntity).getHeldItemMainhand() : Qy.EMPTY;
      String s = "death.attack." + this.damageType;
      String s1 = s + ".item";
      return !itemstack.isEmpty() && itemstack.hasDisplayName() && I18n.canTranslate(s1) ? new TextComponentTranslation(s1, new Object[]{entityLivingBaseIn.getDisplayName(), this.damageSourceEntity.getDisplayName(), itemstack.getTextComponent()}) : new TextComponentTranslation(s, new Object[]{entityLivingBaseIn.getDisplayName(), this.damageSourceEntity.getDisplayName()});
   }

   public boolean isDifficultyScaled() {
      return this.damageSourceEntity != null && this.damageSourceEntity instanceof Iw && !(this.damageSourceEntity instanceof ME);
   }

   @Nullable
   public Vec3d getDamageLocation() {
      return new Vec3d(this.damageSourceEntity.posX, this.damageSourceEntity.posY, this.damageSourceEntity.posZ);
   }
}
