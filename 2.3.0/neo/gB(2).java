package neo;

import java.util.Random;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;

public class gB extends co {
   public static final hX<gA> VARIANT = hX.create("variant", gA.class);

   public gB() {
      super(hM.CLAY);
      this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, gA.STONE));
      this.setHardness(0.0F);
      this.setCreativeTab(EN.DECORATIONS);
   }

   public int quantityDropped(Random random) {
      return 0;
   }

   public static boolean canContainSilverfish(in blockState) {
      co block = blockState.getBlock();
      return blockState == Nk.STONE.getDefaultState().withProperty(gZ.VARIANT, gY.STONE) || block == Nk.COBBLESTONE || block == Nk.STONEBRICK;
   }

   protected Qy getSilkTouchDrop(in state) {
      switch ((gA)state.getValue(VARIANT)) {
         case COBBLESTONE:
            return new Qy(Nk.COBBLESTONE);
         case STONEBRICK:
            return new Qy(Nk.STONEBRICK);
         case MOSSY_STONEBRICK:
            return new Qy(Nk.STONEBRICK, 1, ha.MOSSY.getMetadata());
         case CRACKED_STONEBRICK:
            return new Qy(Nk.STONEBRICK, 1, ha.CRACKED.getMetadata());
         case CHISELED_STONEBRICK:
            return new Qy(Nk.STONEBRICK, 1, ha.CHISELED.getMetadata());
         default:
            return new Qy(Nk.STONE);
      }
   }

   public void dropBlockAsItemWithChance(bij worldIn, BlockPos pos, in state, float chance, int fortune) {
      if (!worldIn.isRemote && worldIn.getGameRules().getBoolean("doTileDrops")) {
         KG entitysilverfish = new KG(worldIn);
         entitysilverfish.setLocationAndAngles((double)pos.getX() + 0.5, (double)pos.getY(), (double)pos.getZ() + 0.5, 0.0F, 0.0F);
         worldIn.spawnEntity(entitysilverfish);
         entitysilverfish.spawnExplosionParticle();
      }

   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      return new Qy(this, 1, state.getBlock().getMetaFromState(state));
   }

   public void getSubBlocks(EN itemIn, NonNullList<Qy> items) {
      gA[] var3 = gA.values();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         gA blocksilverfish$enumtype = var3[var5];
         items.add(new Qy(this, 1, blocksilverfish$enumtype.getMetadata()));
      }

   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(VARIANT, gA.byMetadata(meta));
   }

   public int getMetaFromState(in state) {
      return ((gA)state.getValue(VARIANT)).getMetadata();
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{VARIANT});
   }
}
