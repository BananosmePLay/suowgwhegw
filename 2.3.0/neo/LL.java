package neo;

import javax.annotation.Nullable;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;

public class LL extends LB {
   public LL(bij worldIn) {
      super(worldIn);
      this.setSize(0.9F, 1.4F);
      this.spawnableBlock = Nk.MYCELIUM;
   }

   public static void registerFixesMooshroom(DataFixer fixer) {
      Iu.registerFixesMob(fixer, LL.class);
   }

   public boolean processInteract(ME player, EnumHand hand) {
      Qy itemstack = player.getHeldItem(hand);
      if (itemstack.getItem() == NK.BOWL && this.getGrowingAge() >= 0 && !player.capabilities.isCreativeMode) {
         itemstack.shrink(1);
         if (itemstack.isEmpty()) {
            player.setHeldItem(hand, new Qy(NK.MUSHROOM_STEW));
         } else if (!player.inventory.addItemStackToInventory(new Qy(NK.MUSHROOM_STEW))) {
            player.dropItem(new Qy(NK.MUSHROOM_STEW), false);
         }

         return true;
      } else if (itemstack.getItem() == NK.SHEARS && this.getGrowingAge() >= 0) {
         this.setDead();
         this.world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, this.posX, this.posY + (double)(this.height / 2.0F), this.posZ, 0.0, 0.0, 0.0);
         if (!this.world.isRemote) {
            LB entitycow = new LB(this.world);
            entitycow.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
            entitycow.setHealth(this.getHealth());
            entitycow.renderYawOffset = this.renderYawOffset;
            if (this.hasCustomName()) {
               entitycow.setCustomNameTag(this.getCustomNameTag());
            }

            this.world.spawnEntity(entitycow);

            for(int i = 0; i < 5; ++i) {
               this.world.spawnEntity(new IY(this.world, this.posX, this.posY + (double)this.height, this.posZ, new Qy(Nk.RED_MUSHROOM)));
            }

            itemstack.damageItem(1, player);
            this.playSound(NO.ENTITY_MOOSHROOM_SHEAR, 1.0F, 1.0F);
         }

         return true;
      } else {
         return super.processInteract(player, hand);
      }
   }

   public LL createChild(Ih ageable) {
      return new LL(this.world);
   }

   @Nullable
   protected ResourceLocation getLootTable() {
      return bhq.ENTITIES_MUSHROOM_COW;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public LB createChild(Ih var1) {
      return this.createChild(var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public Ih createChild(Ih var1) {
      return this.createChild(var1);
   }
}
