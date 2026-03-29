package neo;

import com.google.common.base.Predicate;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackData;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MY extends Nd {
   private static final Rd<Qy> ITEM;
   private static final Logger LOGGER;
   public static final Predicate<Iw> WATER_SENSITIVE;

   public MY(bij worldIn) {
      super(worldIn);
   }

   public MY(bij worldIn, Iw throwerIn, Qy potionDamageIn) {
      super(worldIn, throwerIn);
      this.setItem(potionDamageIn);
   }

   public MY(bij worldIn, double x, double y, double z, Qy potionDamageIn) {
      super(worldIn, x, y, z);
      if (!potionDamageIn.isEmpty()) {
         this.setItem(potionDamageIn);
      }

   }

   protected void entityInit() {
      this.getDataManager().register(ITEM, Qy.EMPTY);
   }

   public Qy getPotion() {
      Qy itemstack = (Qy)this.getDataManager().get(ITEM);
      if (itemstack.getItem() != NK.SPLASH_POTION && itemstack.getItem() != NK.LINGERING_POTION) {
         if (this.world != null) {
            LOGGER.error("ThrownPotion entity {} has no item?!", this.getEntityId());
         }

         return new Qy(NK.SPLASH_POTION);
      } else {
         return itemstack;
      }
   }

   public void setItem(Qy stack) {
      this.getDataManager().set(ITEM, stack);
      this.getDataManager().setDirty(ITEM);
   }

   protected float getGravityVelocity() {
      return 0.05F;
   }

   protected void onImpact(RayTraceResult result) {
      if (!this.world.isRemote) {
         Qy itemstack = this.getPotion();
         Wf potiontype = Wg.getPotionFromItem(itemstack);
         List<VZ> list = Wg.getEffectsFromStack(itemstack);
         boolean flag = potiontype == NN.WATER && list.isEmpty();
         if (result.typeOfHit == RayTraceResult.Type.BLOCK && flag) {
            BlockPos blockpos = result.getBlockPos().offset(result.sideHit);
            this.extinguishFires(blockpos, result.sideHit);
            Iterator var7 = EnumFacing.Plane.HORIZONTAL.iterator();

            while(var7.hasNext()) {
               EnumFacing enumfacing = (EnumFacing)var7.next();
               this.extinguishFires(blockpos.offset(enumfacing), enumfacing);
            }
         }

         if (flag) {
            this.applyWater();
         } else if (!list.isEmpty()) {
            if (this.isLingering()) {
               this.makeAreaOfEffectCloud(itemstack, potiontype);
            } else {
               this.applySplash(result, list);
            }
         }

         int i = potiontype.hasInstantEffect() ? 2007 : 2002;
         this.world.playEvent(i, new BlockPos(this), Wg.getColor(itemstack));
         this.setDead();
      }

   }

   private void applyWater() {
      AxisAlignedBB axisalignedbb = this.getEntityBoundingBox().grow(4.0, 2.0, 4.0);
      List<Iw> list = this.world.getEntitiesWithinAABB(Iw.class, axisalignedbb, WATER_SENSITIVE);
      if (!list.isEmpty()) {
         Iterator var3 = list.iterator();

         while(var3.hasNext()) {
            Iw entitylivingbase = (Iw)var3.next();
            double d0 = this.getDistanceSq(entitylivingbase);
            if (d0 < 16.0 && isWaterSensitiveEntity(entitylivingbase)) {
               entitylivingbase.attackEntityFrom(DamageSource.DROWN, 1.0F);
            }
         }
      }

   }

   private void applySplash(RayTraceResult p_190543_1_, List<VZ> p_190543_2_) {
      AxisAlignedBB axisalignedbb = this.getEntityBoundingBox().grow(4.0, 2.0, 4.0);
      List<Iw> list = this.world.getEntitiesWithinAABB(Iw.class, axisalignedbb);
      if (!list.isEmpty()) {
         Iterator var5 = list.iterator();

         while(true) {
            Iw entitylivingbase;
            double d0;
            do {
               do {
                  if (!var5.hasNext()) {
                     return;
                  }

                  entitylivingbase = (Iw)var5.next();
               } while(!entitylivingbase.canBeHitWithPotion());

               d0 = this.getDistanceSq(entitylivingbase);
            } while(!(d0 < 16.0));

            double d1 = 1.0 - Math.sqrt(d0) / 4.0;
            if (entitylivingbase == p_190543_1_.entityHit) {
               d1 = 1.0;
            }

            Iterator var11 = p_190543_2_.iterator();

            while(var11.hasNext()) {
               VZ potioneffect = (VZ)var11.next();
               VW potion = potioneffect.getPotion();
               if (potion.isInstant()) {
                  potion.affectEntity(this, this.getThrower(), entitylivingbase, potioneffect.getAmplifier(), d1);
               } else {
                  int i = (int)(d1 * (double)potioneffect.getDuration() + 0.5);
                  if (i > 20) {
                     entitylivingbase.addPotionEffect(new VZ(potion, i, potioneffect.getAmplifier(), potioneffect.getIsAmbient(), potioneffect.doesShowParticles()));
                  }
               }
            }
         }
      }
   }

   private void makeAreaOfEffectCloud(Qy p_190542_1_, Wf p_190542_2_) {
      Ii entityareaeffectcloud = new Ii(this.world, this.posX, this.posY, this.posZ);
      entityareaeffectcloud.setOwner(this.getThrower());
      entityareaeffectcloud.setRadius(3.0F);
      entityareaeffectcloud.setRadiusOnUse(-0.5F);
      entityareaeffectcloud.setWaitTime(10);
      entityareaeffectcloud.setRadiusPerTick(-entityareaeffectcloud.getRadius() / (float)entityareaeffectcloud.getDuration());
      entityareaeffectcloud.setPotion(p_190542_2_);
      Iterator var4 = Wg.getFullEffectsFromItem(p_190542_1_).iterator();

      while(var4.hasNext()) {
         VZ potioneffect = (VZ)var4.next();
         entityareaeffectcloud.addEffect(new VZ(potioneffect));
      }

      QQ nbttagcompound = p_190542_1_.getTagCompound();
      if (nbttagcompound != null && nbttagcompound.hasKey("CustomPotionColor", 99)) {
         entityareaeffectcloud.setColor(nbttagcompound.getInteger("CustomPotionColor"));
      }

      this.world.spawnEntity(entityareaeffectcloud);
   }

   private boolean isLingering() {
      return this.getPotion().getItem() == NK.LINGERING_POTION;
   }

   private void extinguishFires(BlockPos pos, EnumFacing p_184542_2_) {
      if (this.world.getBlockState(pos).getBlock() == Nk.FIRE) {
         this.world.extinguishFire((ME)null, pos.offset(p_184542_2_), p_184542_2_.getOpposite());
      }

   }

   public static void registerFixesPotion(DataFixer fixer) {
      Nd.registerFixesThrowable(fixer, "ThrownPotion");
      fixer.registerWalker(FixTypes.ENTITY, new ItemStackData(MY.class, new String[]{"Potion"}));
   }

   public void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      Qy itemstack = new Qy(compound.getCompoundTag("Potion"));
      if (itemstack.isEmpty()) {
         this.setDead();
      } else {
         this.setItem(itemstack);
      }

   }

   public void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      Qy itemstack = this.getPotion();
      if (!itemstack.isEmpty()) {
         compound.setTag("Potion", itemstack.writeToNBT(new QQ()));
      }

   }

   private static boolean isWaterSensitiveEntity(Iw p_190544_0_) {
      return p_190544_0_ instanceof JJ || p_190544_0_ instanceof Jz;
   }

   static {
      ITEM = Rv.createKey(MY.class, Rt.ITEM_STACK);
      LOGGER = LogManager.getLogger();
      WATER_SENSITIVE = new Predicate<Iw>() {
         public boolean apply(@Nullable Iw p_apply_1_) {
            return MY.isWaterSensitiveEntity(p_apply_1_);
         }

         // $FF: synthetic method
         // $FF: bridge method
         public boolean apply(@Nullable Object var1) {
            return this.apply((Iw)var1);
         }
      };
   }
}
