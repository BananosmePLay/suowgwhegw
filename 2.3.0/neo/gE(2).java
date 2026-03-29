package neo;

import com.google.common.base.Predicate;
import java.util.Iterator;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;

public class gE extends dd {
   public static final hW FACING;
   public static final hV NODROP;
   private static final Predicate<ik> IS_WITHER_SKELETON;
   protected static final AxisAlignedBB DEFAULT_AABB;
   protected static final AxisAlignedBB NORTH_AABB;
   protected static final AxisAlignedBB SOUTH_AABB;
   protected static final AxisAlignedBB WEST_AABB;
   protected static final AxisAlignedBB EAST_AABB;
   private it witherBasePattern;
   private it witherPattern;

   protected gE() {
      super(hM.CIRCUITS);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(NODROP, false));
   }

   public String getLocalizedName() {
      return I18n.translateToLocal("tile.skull.skeleton.name");
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   /** @deprecated */
   public boolean hasCustomBreakingProgress(in state) {
      return true;
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      switch ((EnumFacing)state.getValue(FACING)) {
         case UP:
         default:
            return DEFAULT_AABB;
         case NORTH:
            return NORTH_AABB;
         case SOUTH:
            return SOUTH_AABB;
         case WEST:
            return WEST_AABB;
         case EAST:
            return EAST_AABB;
      }
   }

   public in getStateForPlacement(bij worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, Iw placer) {
      return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing()).withProperty(NODROP, false);
   }

   public Yg createNewTileEntity(bij worldIn, int meta) {
      return new YR();
   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      int i = 0;
      Yg tileentity = worldIn.getTileEntity(pos);
      if (tileentity instanceof YR) {
         i = ((YR)tileentity).getSkullType();
      }

      return new Qy(NK.SKULL, 1, i);
   }

   public void dropBlockAsItemWithChance(bij worldIn, BlockPos pos, in state, float chance, int fortune) {
   }

   public void onBlockHarvested(bij worldIn, BlockPos pos, in state, ME player) {
      if (player.capabilities.isCreativeMode) {
         state = state.withProperty(NODROP, true);
         worldIn.setBlockState(pos, state, 4);
      }

      super.onBlockHarvested(worldIn, pos, state, player);
   }

   public void breakBlock(bij worldIn, BlockPos pos, in state) {
      if (!worldIn.isRemote) {
         if (!(Boolean)state.getValue(NODROP)) {
            Yg tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof YR) {
               YR tileentityskull = (YR)tileentity;
               Qy itemstack = this.getItem(worldIn, pos, state);
               if (tileentityskull.getSkullType() == 3 && tileentityskull.getPlayerProfile() != null) {
                  itemstack.setTagCompound(new QQ());
                  QQ nbttagcompound = new QQ();
                  Rb.writeGameProfile(nbttagcompound, tileentityskull.getPlayerProfile());
                  itemstack.getTagCompound().setTag("SkullOwner", nbttagcompound);
               }

               spawnAsEntity(worldIn, pos, itemstack);
            }
         }

         super.breakBlock(worldIn, pos, state);
      }

   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return NK.SKULL;
   }

   public boolean canDispenserPlace(bij worldIn, BlockPos pos, Qy stack) {
      if (stack.getMetadata() == 1 && pos.getY() >= 2 && worldIn.getDifficulty() != baV.PEACEFUL && !worldIn.isRemote) {
         return this.getWitherBasePattern().match(worldIn, pos) != null;
      } else {
         return false;
      }
   }

   public void checkWitherSpawn(bij worldIn, BlockPos pos, YR te) {
      if (te.getSkullType() == 1 && pos.getY() >= 2 && worldIn.getDifficulty() != baV.PEACEFUL && !worldIn.isRemote) {
         it blockpattern = this.getWitherPattern();
         is blockpattern$patternhelper = blockpattern.match(worldIn, pos);
         if (blockpattern$patternhelper != null) {
            int j;
            for(j = 0; j < 3; ++j) {
               ik blockworldstate = blockpattern$patternhelper.translateOffset(j, 0, 0);
               worldIn.setBlockState(blockworldstate.getPos(), blockworldstate.getBlockState().withProperty(NODROP, true), 2);
            }

            for(j = 0; j < blockpattern.getPalmLength(); ++j) {
               for(int k = 0; k < blockpattern.getThumbLength(); ++k) {
                  ik blockworldstate1 = blockpattern$patternhelper.translateOffset(j, k, 0);
                  worldIn.setBlockState(blockworldstate1.getPos(), Nk.AIR.getDefaultState(), 2);
               }
            }

            BlockPos blockpos = blockpattern$patternhelper.translateOffset(1, 0, 0).getPos();
            HV entitywither = new HV(worldIn);
            BlockPos blockpos1 = blockpattern$patternhelper.translateOffset(1, 2, 0).getPos();
            entitywither.setLocationAndAngles((double)blockpos1.getX() + 0.5, (double)blockpos1.getY() + 0.55, (double)blockpos1.getZ() + 0.5, blockpattern$patternhelper.getForwards().getAxis() == EnumFacing.Axis.X ? 0.0F : 90.0F, 0.0F);
            entitywither.renderYawOffset = blockpattern$patternhelper.getForwards().getAxis() == EnumFacing.Axis.X ? 0.0F : 90.0F;
            entitywither.ignite();
            Iterator var9 = worldIn.getEntitiesWithinAABB(MG.class, entitywither.getEntityBoundingBox().grow(50.0)).iterator();

            while(var9.hasNext()) {
               MG entityplayermp = (MG)var9.next();
               bY.SUMMONED_ENTITY.trigger(entityplayermp, entitywither);
            }

            worldIn.spawnEntity(entitywither);

            int i1;
            for(i1 = 0; i1 < 120; ++i1) {
               worldIn.spawnParticle(EnumParticleTypes.SNOWBALL, (double)blockpos.getX() + worldIn.rand.nextDouble(), (double)(blockpos.getY() - 2) + worldIn.rand.nextDouble() * 3.9, (double)blockpos.getZ() + worldIn.rand.nextDouble(), 0.0, 0.0, 0.0);
            }

            for(i1 = 0; i1 < blockpattern.getPalmLength(); ++i1) {
               for(int j1 = 0; j1 < blockpattern.getThumbLength(); ++j1) {
                  ik blockworldstate2 = blockpattern$patternhelper.translateOffset(i1, j1, 0);
                  worldIn.notifyNeighborsRespectDebug(blockworldstate2.getPos(), Nk.AIR, false);
               }
            }
         }
      }

   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(FACING, EnumFacing.byIndex(meta & 7)).withProperty(NODROP, (meta & 8) > 0);
   }

   public int getMetaFromState(in state) {
      int i = 0;
      i |= ((EnumFacing)state.getValue(FACING)).getIndex();
      if ((Boolean)state.getValue(NODROP)) {
         i |= 8;
      }

      return i;
   }

   /** @deprecated */
   public in withRotation(in state, Rotation rot) {
      return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
   }

   /** @deprecated */
   public in withMirror(in state, Mirror mirrorIn) {
      return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{FACING, NODROP});
   }

   protected it getWitherBasePattern() {
      if (this.witherBasePattern == null) {
         this.witherBasePattern = iw.start().aisle("   ", "###", "~#~").where('#', ik.hasState(iv.forBlock(Nk.SOUL_SAND))).where('~', ik.hasState(iq.forMaterial(hM.AIR))).build();
      }

      return this.witherBasePattern;
   }

   protected it getWitherPattern() {
      if (this.witherPattern == null) {
         this.witherPattern = iw.start().aisle("^^^", "###", "~#~").where('#', ik.hasState(iv.forBlock(Nk.SOUL_SAND))).where('^', IS_WITHER_SKELETON).where('~', ik.hasState(iq.forMaterial(hM.AIR))).build();
      }

      return this.witherPattern;
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return ib.UNDEFINED;
   }

   static {
      FACING = dh.FACING;
      NODROP = hV.create("nodrop");
      IS_WITHER_SKELETON = new Predicate<ik>() {
         public boolean apply(@Nullable ik p_apply_1_) {
            return p_apply_1_.getBlockState() != null && p_apply_1_.getBlockState().getBlock() == Nk.SKULL && p_apply_1_.getTileEntity() instanceof YR && ((YR)p_apply_1_.getTileEntity()).getSkullType() == 1;
         }

         // $FF: synthetic method
         // $FF: bridge method
         public boolean apply(@Nullable Object var1) {
            return this.apply((ik)var1);
         }
      };
      DEFAULT_AABB = new AxisAlignedBB(0.25, 0.0, 0.25, 0.75, 0.5, 0.75);
      NORTH_AABB = new AxisAlignedBB(0.25, 0.25, 0.5, 0.75, 0.75, 1.0);
      SOUTH_AABB = new AxisAlignedBB(0.25, 0.25, 0.0, 0.75, 0.75, 0.5);
      WEST_AABB = new AxisAlignedBB(0.5, 0.25, 0.25, 1.0, 0.75, 0.75);
      EAST_AABB = new AxisAlignedBB(0.0, 0.25, 0.25, 0.5, 0.75, 0.75);
   }
}
