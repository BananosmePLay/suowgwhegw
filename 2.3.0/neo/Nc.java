package neo;

import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;

public class Nc extends MO {
   private int duration = 200;

   public Nc(bij worldIn) {
      super(worldIn);
   }

   public Nc(bij worldIn, Iw shooter) {
      super(worldIn, shooter);
   }

   public Nc(bij worldIn, double x, double y, double z) {
      super(worldIn, x, y, z);
   }

   public void onUpdate() {
      super.onUpdate();
      if (this.world.isRemote && !this.inGround) {
         this.world.spawnParticle(EnumParticleTypes.SPELL_INSTANT, this.posX, this.posY, this.posZ, 0.0, 0.0, 0.0);
      }

   }

   protected Qy getArrowStack() {
      return new Qy(NK.SPECTRAL_ARROW);
   }

   protected void arrowHit(Iw living) {
      super.arrowHit(living);
      VZ potioneffect = new VZ(NL.GLOWING, this.duration, 0);
      living.addPotionEffect(potioneffect);
   }

   public static void registerFixesSpectralArrow(DataFixer fixer) {
      MO.registerFixesArrow(fixer, "SpectralArrow");
   }

   public void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      if (compound.hasKey("Duration")) {
         this.duration = compound.getInteger("Duration");
      }

   }

   public void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      compound.setInteger("Duration", this.duration);
   }
}
