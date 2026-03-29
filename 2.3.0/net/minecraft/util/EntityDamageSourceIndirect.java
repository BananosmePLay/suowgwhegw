package net.minecraft.util;

import javax.annotation.Nullable;
import neo.Ig;
import neo.Iw;
import neo.Qy;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.translation.I18n;

public class EntityDamageSourceIndirect extends EntityDamageSource {
   private final Ig indirectEntity;

   public EntityDamageSourceIndirect(String damageTypeIn, Ig source, @Nullable Ig indirectEntityIn) {
      super(damageTypeIn, source);
      this.indirectEntity = indirectEntityIn;
   }

   @Nullable
   public Ig getImmediateSource() {
      return this.damageSourceEntity;
   }

   @Nullable
   public Ig getTrueSource() {
      return this.indirectEntity;
   }

   public ITextComponent getDeathMessage(Iw entityLivingBaseIn) {
      ITextComponent itextcomponent = this.indirectEntity == null ? this.damageSourceEntity.getDisplayName() : this.indirectEntity.getDisplayName();
      Qy itemstack = this.indirectEntity instanceof Iw ? ((Iw)this.indirectEntity).getHeldItemMainhand() : Qy.EMPTY;
      String s = "death.attack." + this.damageType;
      String s1 = s + ".item";
      return !itemstack.isEmpty() && itemstack.hasDisplayName() && I18n.canTranslate(s1) ? new TextComponentTranslation(s1, new Object[]{entityLivingBaseIn.getDisplayName(), itextcomponent, itemstack.getTextComponent()}) : new TextComponentTranslation(s, new Object[]{entityLivingBaseIn.getDisplayName(), itextcomponent});
   }
}
