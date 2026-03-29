package neo;

import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;

public class Ne extends MO {
   private static final Rd<Integer> COLOR;
   private Wf potion;
   private final Set<VZ> customPotionEffects;
   private boolean fixedColor;

   public Ne(bij worldIn) {
      super(worldIn);
      this.potion = NN.EMPTY;
      this.customPotionEffects = Sets.newHashSet();
   }

   public Ne(bij worldIn, double x, double y, double z) {
      super(worldIn, x, y, z);
      this.potion = NN.EMPTY;
      this.customPotionEffects = Sets.newHashSet();
   }

   public Ne(bij worldIn, Iw shooter) {
      super(worldIn, shooter);
      this.potion = NN.EMPTY;
      this.customPotionEffects = Sets.newHashSet();
   }

   public void setPotionEffect(Qy stack) {
      if (stack.getItem() == NK.TIPPED_ARROW) {
         this.potion = Wg.getPotionFromItem(stack);
         Collection<VZ> collection = Wg.getFullEffectsFromItem(stack);
         if (!collection.isEmpty()) {
            Iterator var3 = collection.iterator();

            while(var3.hasNext()) {
               VZ potioneffect = (VZ)var3.next();
               this.customPotionEffects.add(new VZ(potioneffect));
            }
         }

         int i = getCustomColor(stack);
         if (i == -1) {
            this.refreshColor();
         } else {
            this.setFixedColor(i);
         }
      } else if (stack.getItem() == NK.ARROW) {
         this.potion = NN.EMPTY;
         this.customPotionEffects.clear();
         this.dataManager.set(COLOR, -1);
      }

   }

   public static int getCustomColor(Qy p_191508_0_) {
      QQ nbttagcompound = p_191508_0_.getTagCompound();
      return nbttagcompound != null && nbttagcompound.hasKey("CustomPotionColor", 99) ? nbttagcompound.getInteger("CustomPotionColor") : -1;
   }

   private void refreshColor() {
      this.fixedColor = false;
      this.dataManager.set(COLOR, Wg.getPotionColorFromEffectList(Wg.mergeEffects(this.potion, this.customPotionEffects)));
   }

   public void addEffect(VZ effect) {
      this.customPotionEffects.add(effect);
      this.getDataManager().set(COLOR, Wg.getPotionColorFromEffectList(Wg.mergeEffects(this.potion, this.customPotionEffects)));
   }

   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(COLOR, -1);
   }

   public void onUpdate() {
      super.onUpdate();
      if (this.world.isRemote) {
         if (this.inGround) {
            if (this.timeInGround % 5 == 0) {
               this.spawnPotionParticles(1);
            }
         } else {
            this.spawnPotionParticles(2);
         }
      } else if (this.inGround && this.timeInGround != 0 && !this.customPotionEffects.isEmpty() && this.timeInGround >= 600) {
         this.world.setEntityState(this, (byte)0);
         this.potion = NN.EMPTY;
         this.customPotionEffects.clear();
         this.dataManager.set(COLOR, -1);
      }

   }

   private void spawnPotionParticles(int particleCount) {
      int i = this.getColor();
      if (i != -1 && particleCount > 0) {
         double d0 = (double)(i >> 16 & 255) / 255.0;
         double d1 = (double)(i >> 8 & 255) / 255.0;
         double d2 = (double)(i >> 0 & 255) / 255.0;

         for(int j = 0; j < particleCount; ++j) {
            this.world.spawnParticle(EnumParticleTypes.SPELL_MOB, this.posX + (this.rand.nextDouble() - 0.5) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5) * (double)this.width, d0, d1, d2);
         }
      }

   }

   public int getColor() {
      return (Integer)this.dataManager.get(COLOR);
   }

   private void setFixedColor(int p_191507_1_) {
      this.fixedColor = true;
      this.dataManager.set(COLOR, p_191507_1_);
   }

   public static void registerFixesTippedArrow(DataFixer fixer) {
      MO.registerFixesArrow(fixer, "TippedArrow");
   }

   public void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      if (this.potion != NN.EMPTY && this.potion != null) {
         compound.setString("Potion", ((ResourceLocation)Wf.REGISTRY.getNameForObject(this.potion)).toString());
      }

      if (this.fixedColor) {
         compound.setInteger("Color", this.getColor());
      }

      if (!this.customPotionEffects.isEmpty()) {
         QW nbttaglist = new QW();
         Iterator var3 = this.customPotionEffects.iterator();

         while(var3.hasNext()) {
            VZ potioneffect = (VZ)var3.next();
            nbttaglist.appendTag(potioneffect.writeCustomPotionEffectToNBT(new QQ()));
         }

         compound.setTag("CustomPotionEffects", nbttaglist);
      }

   }

   public void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      if (compound.hasKey("Potion", 8)) {
         this.potion = Wg.getPotionTypeFromNBT(compound);
      }

      Iterator var2 = Wg.getFullEffectsFromTag(compound).iterator();

      while(var2.hasNext()) {
         VZ potioneffect = (VZ)var2.next();
         this.addEffect(potioneffect);
      }

      if (compound.hasKey("Color", 99)) {
         this.setFixedColor(compound.getInteger("Color"));
      } else {
         this.refreshColor();
      }

   }

   protected void arrowHit(Iw living) {
      super.arrowHit(living);
      Iterator var2 = this.potion.getEffects().iterator();

      VZ potioneffect1;
      while(var2.hasNext()) {
         potioneffect1 = (VZ)var2.next();
         living.addPotionEffect(new VZ(potioneffect1.getPotion(), Math.max(potioneffect1.getDuration() / 8, 1), potioneffect1.getAmplifier(), potioneffect1.getIsAmbient(), potioneffect1.doesShowParticles()));
      }

      if (!this.customPotionEffects.isEmpty()) {
         var2 = this.customPotionEffects.iterator();

         while(var2.hasNext()) {
            potioneffect1 = (VZ)var2.next();
            living.addPotionEffect(potioneffect1);
         }
      }

   }

   protected Qy getArrowStack() {
      if (this.customPotionEffects.isEmpty() && this.potion == NN.EMPTY) {
         return new Qy(NK.ARROW);
      } else {
         Qy itemstack = new Qy(NK.TIPPED_ARROW);
         Wg.addPotionToItemStack(itemstack, this.potion);
         Wg.appendEffects(itemstack, this.customPotionEffects);
         if (this.fixedColor) {
            QQ nbttagcompound = itemstack.getTagCompound();
            if (nbttagcompound == null) {
               nbttagcompound = new QQ();
               itemstack.setTagCompound(nbttagcompound);
            }

            nbttagcompound.setInteger("CustomPotionColor", this.getColor());
         }

         return itemstack;
      }
   }

   public void handleStatusUpdate(byte id) {
      if (id == 0) {
         int i = this.getColor();
         if (i != -1) {
            double d0 = (double)(i >> 16 & 255) / 255.0;
            double d1 = (double)(i >> 8 & 255) / 255.0;
            double d2 = (double)(i >> 0 & 255) / 255.0;

            for(int j = 0; j < 20; ++j) {
               this.world.spawnParticle(EnumParticleTypes.SPELL_MOB, this.posX + (this.rand.nextDouble() - 0.5) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5) * (double)this.width, d0, d1, d2);
            }
         }
      } else {
         super.handleStatusUpdate(id);
      }

   }

   static {
      COLOR = Rv.createKey(Ne.class, Rt.VARINT);
   }
}
