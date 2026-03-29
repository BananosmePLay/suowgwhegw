package neo;

import com.google.common.base.Predicate;
import java.util.Iterator;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;

public class fx extends en {
   private it snowmanBasePattern;
   private it snowmanPattern;
   private it golemBasePattern;
   private it golemPattern;
   private static final Predicate<in> IS_PUMPKIN = new Predicate<in>() {
      public boolean apply(@Nullable in p_apply_1_) {
         return p_apply_1_ != null && (p_apply_1_.getBlock() == Nk.PUMPKIN || p_apply_1_.getBlock() == Nk.LIT_PUMPKIN);
      }

      // $FF: synthetic method
      // $FF: bridge method
      public boolean apply(@Nullable Object var1) {
         return this.apply((in)var1);
      }
   };

   protected fx() {
      super(hM.GOURD, hK.ADOBE);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
      this.setTickRandomly(true);
      this.setCreativeTab(EN.BUILDING_BLOCKS);
   }

   public void onBlockAdded(bij worldIn, BlockPos pos, in state) {
      super.onBlockAdded(worldIn, pos, state);
      this.trySpawnGolem(worldIn, pos);
   }

   public boolean canDispenserPlace(bij worldIn, BlockPos pos) {
      return this.getSnowmanBasePattern().match(worldIn, pos) != null || this.getGolemBasePattern().match(worldIn, pos) != null;
   }

   private void trySpawnGolem(bij worldIn, BlockPos pos) {
      is blockpattern$patternhelper = this.getSnowmanPattern().match(worldIn, pos);
      int j;
      Iterator var6;
      MG entityplayermp1;
      int k1;
      if (blockpattern$patternhelper != null) {
         for(j = 0; j < this.getSnowmanPattern().getThumbLength(); ++j) {
            ik blockworldstate = blockpattern$patternhelper.translateOffset(0, j, 0);
            worldIn.setBlockState(blockworldstate.getPos(), Nk.AIR.getDefaultState(), 2);
         }

         KO entitysnowman = new KO(worldIn);
         BlockPos blockpos1 = blockpattern$patternhelper.translateOffset(0, 2, 0).getPos();
         entitysnowman.setLocationAndAngles((double)blockpos1.getX() + 0.5, (double)blockpos1.getY() + 0.05, (double)blockpos1.getZ() + 0.5, 0.0F, 0.0F);
         worldIn.spawnEntity(entitysnowman);
         var6 = worldIn.getEntitiesWithinAABB(MG.class, entitysnowman.getEntityBoundingBox().grow(5.0)).iterator();

         while(var6.hasNext()) {
            entityplayermp1 = (MG)var6.next();
            bY.SUMMONED_ENTITY.trigger(entityplayermp1, entitysnowman);
         }

         for(k1 = 0; k1 < 120; ++k1) {
            worldIn.spawnParticle(EnumParticleTypes.SNOW_SHOVEL, (double)blockpos1.getX() + worldIn.rand.nextDouble(), (double)blockpos1.getY() + worldIn.rand.nextDouble() * 2.5, (double)blockpos1.getZ() + worldIn.rand.nextDouble(), 0.0, 0.0, 0.0);
         }

         for(k1 = 0; k1 < this.getSnowmanPattern().getThumbLength(); ++k1) {
            ik blockworldstate2 = blockpattern$patternhelper.translateOffset(0, k1, 0);
            worldIn.notifyNeighborsRespectDebug(blockworldstate2.getPos(), Nk.AIR, false);
         }
      } else {
         blockpattern$patternhelper = this.getGolemPattern().match(worldIn, pos);
         if (blockpattern$patternhelper != null) {
            for(j = 0; j < this.getGolemPattern().getPalmLength(); ++j) {
               for(int k = 0; k < this.getGolemPattern().getThumbLength(); ++k) {
                  worldIn.setBlockState(blockpattern$patternhelper.translateOffset(j, k, 0).getPos(), Nk.AIR.getDefaultState(), 2);
               }
            }

            BlockPos blockpos = blockpattern$patternhelper.translateOffset(1, 2, 0).getPos();
            Kj entityirongolem = new Kj(worldIn);
            entityirongolem.setPlayerCreated(true);
            entityirongolem.setLocationAndAngles((double)blockpos.getX() + 0.5, (double)blockpos.getY() + 0.05, (double)blockpos.getZ() + 0.5, 0.0F, 0.0F);
            worldIn.spawnEntity(entityirongolem);
            var6 = worldIn.getEntitiesWithinAABB(MG.class, entityirongolem.getEntityBoundingBox().grow(5.0)).iterator();

            while(var6.hasNext()) {
               entityplayermp1 = (MG)var6.next();
               bY.SUMMONED_ENTITY.trigger(entityplayermp1, entityirongolem);
            }

            for(k1 = 0; k1 < 120; ++k1) {
               worldIn.spawnParticle(EnumParticleTypes.SNOWBALL, (double)blockpos.getX() + worldIn.rand.nextDouble(), (double)blockpos.getY() + worldIn.rand.nextDouble() * 3.9, (double)blockpos.getZ() + worldIn.rand.nextDouble(), 0.0, 0.0, 0.0);
            }

            for(k1 = 0; k1 < this.getGolemPattern().getPalmLength(); ++k1) {
               for(int l1 = 0; l1 < this.getGolemPattern().getThumbLength(); ++l1) {
                  ik blockworldstate1 = blockpattern$patternhelper.translateOffset(k1, l1, 0);
                  worldIn.notifyNeighborsRespectDebug(blockworldstate1.getPos(), Nk.AIR, false);
               }
            }
         }
      }

   }

   public boolean canPlaceBlockAt(bij worldIn, BlockPos pos) {
      return worldIn.getBlockState(pos).getBlock().material.isReplaceable() && worldIn.getBlockState(pos.down()).isTopSolid();
   }

   /** @deprecated */
   public in withRotation(in state, Rotation rot) {
      return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
   }

   /** @deprecated */
   public in withMirror(in state, Mirror mirrorIn) {
      return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
   }

   public in getStateForPlacement(bij worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, Iw placer) {
      return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta));
   }

   public int getMetaFromState(in state) {
      return ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{FACING});
   }

   protected it getSnowmanBasePattern() {
      if (this.snowmanBasePattern == null) {
         this.snowmanBasePattern = iw.start().aisle(" ", "#", "#").where('#', ik.hasState(iv.forBlock(Nk.SNOW))).build();
      }

      return this.snowmanBasePattern;
   }

   protected it getSnowmanPattern() {
      if (this.snowmanPattern == null) {
         this.snowmanPattern = iw.start().aisle("^", "#", "#").where('^', ik.hasState(IS_PUMPKIN)).where('#', ik.hasState(iv.forBlock(Nk.SNOW))).build();
      }

      return this.snowmanPattern;
   }

   protected it getGolemBasePattern() {
      if (this.golemBasePattern == null) {
         this.golemBasePattern = iw.start().aisle("~ ~", "###", "~#~").where('#', ik.hasState(iv.forBlock(Nk.IRON_BLOCK))).where('~', ik.hasState(iq.forMaterial(hM.AIR))).build();
      }

      return this.golemBasePattern;
   }

   protected it getGolemPattern() {
      if (this.golemPattern == null) {
         this.golemPattern = iw.start().aisle("~^~", "###", "~#~").where('^', ik.hasState(IS_PUMPKIN)).where('#', ik.hasState(iv.forBlock(Nk.IRON_BLOCK))).where('~', ik.hasState(iq.forMaterial(hM.AIR))).build();
      }

      return this.golemPattern;
   }
}
