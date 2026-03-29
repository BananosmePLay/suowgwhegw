package neo;

import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;

public abstract class KS extends Jv {
   private static final Rd<Byte> SPELL;
   protected int spellTicks;
   private KR activeSpell;

   public KS(bij p_i47506_1_) {
      super(p_i47506_1_);
      this.activeSpell = KR.NONE;
   }

   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(SPELL, (byte)0);
   }

   public void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      this.spellTicks = compound.getInteger("SpellTicks");
   }

   public void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      compound.setInteger("SpellTicks", this.spellTicks);
   }

   public Ju getArmPose() {
      return this.isSpellcasting() ? Ju.SPELLCASTING : Ju.CROSSED;
   }

   public boolean isSpellcasting() {
      if (this.world.isRemote) {
         return (Byte)this.dataManager.get(SPELL) > 0;
      } else {
         return this.spellTicks > 0;
      }
   }

   public void setSpellType(KR spellType) {
      this.activeSpell = spellType;
      this.dataManager.set(SPELL, (byte)KR.access$000(spellType));
   }

   protected KR getSpellType() {
      return !this.world.isRemote ? this.activeSpell : KR.getFromId((Byte)this.dataManager.get(SPELL));
   }

   protected void updateAITasks() {
      super.updateAITasks();
      if (this.spellTicks > 0) {
         --this.spellTicks;
      }

   }

   public void onUpdate() {
      super.onUpdate();
      if (this.world.isRemote && this.isSpellcasting()) {
         KR entityspellcasterillager$spelltype = this.getSpellType();
         double d0 = KR.access$100(entityspellcasterillager$spelltype)[0];
         double d1 = KR.access$100(entityspellcasterillager$spelltype)[1];
         double d2 = KR.access$100(entityspellcasterillager$spelltype)[2];
         float f = this.renderYawOffset * 0.017453292F + MathHelper.cos((float)this.ticksExisted * 0.6662F) * 0.25F;
         float f1 = MathHelper.cos(f);
         float f2 = MathHelper.sin(f);
         this.world.spawnParticle(EnumParticleTypes.SPELL_MOB, this.posX + (double)f1 * 0.6, this.posY + 1.8, this.posZ + (double)f2 * 0.6, d0, d1, d2);
         this.world.spawnParticle(EnumParticleTypes.SPELL_MOB, this.posX - (double)f1 * 0.6, this.posY + 1.8, this.posZ - (double)f2 * 0.6, d0, d1, d2);
      }

   }

   protected int getSpellTicks() {
      return this.spellTicks;
   }

   protected abstract SoundEvent getSpellSound();

   // $FF: synthetic method
   static VL access$200(KS x0) {
      return x0.navigator;
   }

   static {
      SPELL = Rv.createKey(KS.class, Rt.BYTE);
   }
}
