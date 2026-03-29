package neo;

import com.google.common.base.Predicate;
import javax.annotation.Nullable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;

public class eW extends ew {
   public static final hX<fk> VARIANT = hX.create("variant", fk.class, new Predicate<fk>() {
      public boolean apply(@Nullable fk p_apply_1_) {
         return p_apply_1_.getMetadata() < 4;
      }

      // $FF: synthetic method
      // $FF: bridge method
      public boolean apply(@Nullable Object var1) {
         return this.apply((fk)var1);
      }
   });

   public eW() {
      this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, fk.OAK).withProperty(CHECK_DECAY, true).withProperty(DECAYABLE, true));
   }

   protected void dropApple(bij worldIn, BlockPos pos, in state, int chance) {
      if (state.getValue(VARIANT) == fk.OAK && worldIn.rand.nextInt(chance) == 0) {
         spawnAsEntity(worldIn, pos, new Qy(NK.APPLE));
      }

   }

   protected int getSaplingDropChance(in state) {
      return state.getValue(VARIANT) == fk.JUNGLE ? 40 : super.getSaplingDropChance(state);
   }

   public void getSubBlocks(EN itemIn, NonNullList<Qy> items) {
      items.add(new Qy(this, 1, fk.OAK.getMetadata()));
      items.add(new Qy(this, 1, fk.SPRUCE.getMetadata()));
      items.add(new Qy(this, 1, fk.BIRCH.getMetadata()));
      items.add(new Qy(this, 1, fk.JUNGLE.getMetadata()));
   }

   protected Qy getSilkTouchDrop(in state) {
      return new Qy(OL.getItemFromBlock(this), 1, ((fk)state.getValue(VARIANT)).getMetadata());
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(VARIANT, this.getWoodType(meta)).withProperty(DECAYABLE, (meta & 4) == 0).withProperty(CHECK_DECAY, (meta & 8) > 0);
   }

   public int getMetaFromState(in state) {
      int i = 0;
      i |= ((fk)state.getValue(VARIANT)).getMetadata();
      if (!(Boolean)state.getValue(DECAYABLE)) {
         i |= 4;
      }

      if ((Boolean)state.getValue(CHECK_DECAY)) {
         i |= 8;
      }

      return i;
   }

   public fk getWoodType(int meta) {
      return fk.byMetadata((meta & 3) % 4);
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{VARIANT, CHECK_DECAY, DECAYABLE});
   }

   public int damageDropped(in state) {
      return ((fk)state.getValue(VARIANT)).getMetadata();
   }

   public void harvestBlock(bij worldIn, ME player, BlockPos pos, in state, @Nullable Yg te, Qy stack) {
      if (!worldIn.isRemote && stack.getItem() == NK.SHEARS) {
         player.addStat(XV.getBlockStats(this));
         spawnAsEntity(worldIn, pos, new Qy(OL.getItemFromBlock(this), 1, ((fk)state.getValue(VARIANT)).getMetadata()));
      } else {
         super.harvestBlock(worldIn, player, pos, state, te, stack);
      }

   }
}
