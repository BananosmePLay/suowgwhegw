package neo;

import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class ft extends cx {
   public static final hZ POWER = hZ.create("power", 0, 15);
   private final int maxWeight;

   protected ft(hM materialIn, int p_i46379_2_) {
      this(materialIn, p_i46379_2_, materialIn.getMaterialMapColor());
   }

   protected ft(hM materialIn, int p_i46380_2_, hK color) {
      super(materialIn, color);
      this.setDefaultState(this.blockState.getBaseState().withProperty(POWER, 0));
      this.maxWeight = p_i46380_2_;
   }

   protected int computeRedstoneStrength(bij worldIn, BlockPos pos) {
      int i = Math.min(worldIn.getEntitiesWithinAABB(Ig.class, PRESSURE_AABB.offset(pos)).size(), this.maxWeight);
      if (i > 0) {
         float f = (float)Math.min(this.maxWeight, i) / (float)this.maxWeight;
         return MathHelper.ceil(f * 15.0F);
      } else {
         return 0;
      }
   }

   protected void playClickOnSound(bij worldIn, BlockPos color) {
      worldIn.playSound((ME)null, color, NO.BLOCK_METAL_PRESSPLATE_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.90000004F);
   }

   protected void playClickOffSound(bij worldIn, BlockPos pos) {
      worldIn.playSound((ME)null, pos, NO.BLOCK_METAL_PRESSPLATE_CLICK_OFF, SoundCategory.BLOCKS, 0.3F, 0.75F);
   }

   protected int getRedstoneStrength(in state) {
      return (Integer)state.getValue(POWER);
   }

   protected in setRedstoneStrength(in state, int strength) {
      return state.withProperty(POWER, strength);
   }

   public int tickRate(bij worldIn) {
      return 10;
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(POWER, meta);
   }

   public int getMetaFromState(in state) {
      return (Integer)state.getValue(POWER);
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{POWER});
   }
}
