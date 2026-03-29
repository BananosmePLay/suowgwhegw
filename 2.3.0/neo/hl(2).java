package neo;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;

public class hl extends co {
   public static final hV EXPLODE = hV.create("explode");

   public hl() {
      super(hM.TNT);
      this.setDefaultState(this.blockState.getBaseState().withProperty(EXPLODE, false));
      this.setCreativeTab(EN.REDSTONE);
   }

   public void onBlockAdded(bij worldIn, BlockPos pos, in state) {
      super.onBlockAdded(worldIn, pos, state);
      if (worldIn.isBlockPowered(pos)) {
         this.onPlayerDestroy(worldIn, pos, state.withProperty(EXPLODE, true));
         worldIn.setBlockToAir(pos);
      }

   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      if (worldIn.isBlockPowered(pos)) {
         this.onPlayerDestroy(worldIn, pos, state.withProperty(EXPLODE, true));
         worldIn.setBlockToAir(pos);
      }

   }

   public void onExplosionDestroy(bij worldIn, BlockPos pos, baX explosionIn) {
      if (!worldIn.isRemote) {
         Jr entitytntprimed = new Jr(worldIn, (double)((float)pos.getX() + 0.5F), (double)pos.getY(), (double)((float)pos.getZ() + 0.5F), explosionIn.getExplosivePlacedBy());
         entitytntprimed.setFuse((short)(worldIn.rand.nextInt(entitytntprimed.getFuse() / 4) + entitytntprimed.getFuse() / 8));
         worldIn.spawnEntity(entitytntprimed);
      }

   }

   public void onPlayerDestroy(bij worldIn, BlockPos pos, in state) {
      this.explode(worldIn, pos, state, (Iw)null);
   }

   public void explode(bij worldIn, BlockPos pos, in state, Iw igniter) {
      if (!worldIn.isRemote && (Boolean)state.getValue(EXPLODE)) {
         Jr entitytntprimed = new Jr(worldIn, (double)((float)pos.getX() + 0.5F), (double)pos.getY(), (double)((float)pos.getZ() + 0.5F), igniter);
         worldIn.spawnEntity(entitytntprimed);
         worldIn.playSound((ME)null, entitytntprimed.posX, entitytntprimed.posY, entitytntprimed.posZ, NO.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
      }

   }

   public boolean onBlockActivated(bij worldIn, BlockPos pos, in state, ME playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      Qy itemstack = playerIn.getHeldItem(hand);
      if (!itemstack.isEmpty() && (itemstack.getItem() == NK.FLINT_AND_STEEL || itemstack.getItem() == NK.FIRE_CHARGE)) {
         this.explode(worldIn, pos, state.withProperty(EXPLODE, true), playerIn);
         worldIn.setBlockState(pos, Nk.AIR.getDefaultState(), 11);
         if (itemstack.getItem() == NK.FLINT_AND_STEEL) {
            itemstack.damageItem(1, playerIn);
         } else if (!playerIn.capabilities.isCreativeMode) {
            itemstack.shrink(1);
         }

         return true;
      } else {
         return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
      }
   }

   public void onEntityCollision(bij worldIn, BlockPos pos, in state, Ig entityIn) {
      if (!worldIn.isRemote && entityIn instanceof MO) {
         MO entityarrow = (MO)entityIn;
         if (entityarrow.isBurning()) {
            this.explode(worldIn, pos, worldIn.getBlockState(pos).withProperty(EXPLODE, true), entityarrow.shootingEntity instanceof Iw ? (Iw)entityarrow.shootingEntity : null);
            worldIn.setBlockToAir(pos);
         }
      }

   }

   public boolean canDropFromExplosion(baX explosionIn) {
      return false;
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(EXPLODE, (meta & 1) > 0);
   }

   public int getMetaFromState(in state) {
      return (Boolean)state.getValue(EXPLODE) ? 1 : 0;
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{EXPLODE});
   }
}
