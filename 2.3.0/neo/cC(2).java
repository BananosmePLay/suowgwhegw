package neo;

import java.util.Iterator;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;

public class cC extends en implements hI {
   public static final hX<cB> PART = hX.create("part", cB.class);
   public static final hV OCCUPIED = hV.create("occupied");
   protected static final AxisAlignedBB BED_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.5625, 1.0);

   public cC() {
      super(hM.CLOTH);
      this.setDefaultState(this.blockState.getBaseState().withProperty(PART, cB.FOOT).withProperty(OCCUPIED, false));
      this.hasTileEntity = true;
   }

   /** @deprecated */
   public hK getMapColor(in state, bfZ worldIn, BlockPos pos) {
      if (state.getValue(PART) == cB.FOOT) {
         Yg tileentity = worldIn.getTileEntity(pos);
         if (tileentity instanceof Yk) {
            Om enumdyecolor = ((Yk)tileentity).getColor();
            return hK.getBlockColor(enumdyecolor);
         }
      }

      return hK.CLOTH;
   }

   public boolean onBlockActivated(bij worldIn, BlockPos pos, in state, ME playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (worldIn.isRemote) {
         return true;
      } else {
         if (state.getValue(PART) != cB.HEAD) {
            pos = pos.offset((EnumFacing)state.getValue(FACING));
            state = worldIn.getBlockState(pos);
            if (state.getBlock() != this) {
               return true;
            }
         }

         if (worldIn.provider.canRespawnHere() && worldIn.getBiome(pos) != Nj.HELL) {
            if ((Boolean)state.getValue(OCCUPIED)) {
               ME entityplayer = this.getPlayerInBed(worldIn, pos);
               if (entityplayer != null) {
                  playerIn.sendStatusMessage(new TextComponentTranslation("tile.bed.occupied", new Object[0]), true);
                  return true;
               }

               state = state.withProperty(OCCUPIED, false);
               worldIn.setBlockState(pos, state, 4);
            }

            MD entityplayer$sleepresult = playerIn.trySleep(pos);
            if (entityplayer$sleepresult == MD.OK) {
               state = state.withProperty(OCCUPIED, true);
               worldIn.setBlockState(pos, state, 4);
               return true;
            } else {
               if (entityplayer$sleepresult == MD.NOT_POSSIBLE_NOW) {
                  playerIn.sendStatusMessage(new TextComponentTranslation("tile.bed.noSleep", new Object[0]), true);
               } else if (entityplayer$sleepresult == MD.NOT_SAFE) {
                  playerIn.sendStatusMessage(new TextComponentTranslation("tile.bed.notSafe", new Object[0]), true);
               } else if (entityplayer$sleepresult == MD.TOO_FAR_AWAY) {
                  playerIn.sendStatusMessage(new TextComponentTranslation("tile.bed.tooFarAway", new Object[0]), true);
               }

               return true;
            }
         } else {
            worldIn.setBlockToAir(pos);
            BlockPos blockpos = pos.offset(((EnumFacing)state.getValue(FACING)).getOpposite());
            if (worldIn.getBlockState(blockpos).getBlock() == this) {
               worldIn.setBlockToAir(blockpos);
            }

            worldIn.newExplosion((Ig)null, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, 5.0F, true, true);
            return true;
         }
      }
   }

   @Nullable
   private ME getPlayerInBed(bij worldIn, BlockPos pos) {
      Iterator var3 = worldIn.playerEntities.iterator();

      ME entityplayer;
      do {
         if (!var3.hasNext()) {
            return null;
         }

         entityplayer = (ME)var3.next();
      } while(!entityplayer.isPlayerSleeping() || !entityplayer.bedLocation.equals(pos));

      return entityplayer;
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   public void onFallenUpon(bij worldIn, BlockPos pos, Ig entityIn, float fallDistance) {
      super.onFallenUpon(worldIn, pos, entityIn, fallDistance * 0.5F);
   }

   public void onLanded(bij worldIn, Ig entityIn) {
      if (entityIn.isSneaking()) {
         super.onLanded(worldIn, entityIn);
      } else if (entityIn.motionY < 0.0) {
         entityIn.motionY = -entityIn.motionY * 0.6600000262260437;
         if (!(entityIn instanceof Iw)) {
            entityIn.motionY *= 0.8;
         }
      }

   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
      if (state.getValue(PART) == cB.FOOT) {
         if (worldIn.getBlockState(pos.offset(enumfacing)).getBlock() != this) {
            worldIn.setBlockToAir(pos);
         }
      } else if (worldIn.getBlockState(pos.offset(enumfacing.getOpposite())).getBlock() != this) {
         if (!worldIn.isRemote) {
            this.dropBlockAsItem(worldIn, pos, state, 0);
         }

         worldIn.setBlockToAir(pos);
      }

   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return state.getValue(PART) == cB.FOOT ? NK.AIR : NK.BED;
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      return BED_AABB;
   }

   /** @deprecated */
   public boolean hasCustomBreakingProgress(in state) {
      return true;
   }

   @Nullable
   public static BlockPos getSafeExitLocation(bij worldIn, BlockPos pos, int tries) {
      EnumFacing enumfacing = (EnumFacing)worldIn.getBlockState(pos).getValue(FACING);
      int i = pos.getX();
      int j = pos.getY();
      int k = pos.getZ();

      for(int l = 0; l <= 1; ++l) {
         int i1 = i - enumfacing.getXOffset() * l - 1;
         int j1 = k - enumfacing.getZOffset() * l - 1;
         int k1 = i1 + 2;
         int l1 = j1 + 2;

         for(int i2 = i1; i2 <= k1; ++i2) {
            for(int j2 = j1; j2 <= l1; ++j2) {
               BlockPos blockpos = new BlockPos(i2, j, j2);
               if (hasRoomForPlayer(worldIn, blockpos)) {
                  if (tries <= 0) {
                     return blockpos;
                  }

                  --tries;
               }
            }
         }
      }

      return null;
   }

   protected static boolean hasRoomForPlayer(bij worldIn, BlockPos pos) {
      return worldIn.getBlockState(pos.down()).isTopSolid() && !worldIn.getBlockState(pos).getMaterial().isSolid() && !worldIn.getBlockState(pos.up()).getMaterial().isSolid();
   }

   public void dropBlockAsItemWithChance(bij worldIn, BlockPos pos, in state, float chance, int fortune) {
      if (state.getValue(PART) == cB.HEAD) {
         Yg tileentity = worldIn.getTileEntity(pos);
         Om enumdyecolor = tileentity instanceof Yk ? ((Yk)tileentity).getColor() : Om.RED;
         spawnAsEntity(worldIn, pos, new Qy(NK.BED, 1, enumdyecolor.getMetadata()));
      }

   }

   /** @deprecated */
   public hJ getPushReaction(in state) {
      return hJ.DESTROY;
   }

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.CUTOUT;
   }

   /** @deprecated */
   public EnumBlockRenderType getRenderType(in state) {
      return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      BlockPos blockpos = pos;
      if (state.getValue(PART) == cB.FOOT) {
         blockpos = pos.offset((EnumFacing)state.getValue(FACING));
      }

      Yg tileentity = worldIn.getTileEntity(blockpos);
      Om enumdyecolor = tileentity instanceof Yk ? ((Yk)tileentity).getColor() : Om.RED;
      return new Qy(NK.BED, 1, enumdyecolor.getMetadata());
   }

   public void onBlockHarvested(bij worldIn, BlockPos pos, in state, ME player) {
      if (player.capabilities.isCreativeMode && state.getValue(PART) == cB.FOOT) {
         BlockPos blockpos = pos.offset((EnumFacing)state.getValue(FACING));
         if (worldIn.getBlockState(blockpos).getBlock() == this) {
            worldIn.setBlockToAir(blockpos);
         }
      }

   }

   public void harvestBlock(bij worldIn, ME player, BlockPos pos, in state, Yg te, Qy stack) {
      if (state.getValue(PART) == cB.HEAD && te instanceof Yk) {
         Yk tileentitybed = (Yk)te;
         Qy itemstack = tileentitybed.getItemStack();
         spawnAsEntity(worldIn, pos, itemstack);
      } else {
         super.harvestBlock(worldIn, player, pos, state, (Yg)null, stack);
      }

   }

   public void breakBlock(bij worldIn, BlockPos pos, in state) {
      super.breakBlock(worldIn, pos, state);
      worldIn.removeTileEntity(pos);
   }

   public in getStateFromMeta(int meta) {
      EnumFacing enumfacing = EnumFacing.byHorizontalIndex(meta);
      return (meta & 8) > 0 ? this.getDefaultState().withProperty(PART, cB.HEAD).withProperty(FACING, enumfacing).withProperty(OCCUPIED, (meta & 4) > 0) : this.getDefaultState().withProperty(PART, cB.FOOT).withProperty(FACING, enumfacing);
   }

   public in getActualState(in state, bfZ worldIn, BlockPos pos) {
      if (state.getValue(PART) == cB.FOOT) {
         in iblockstate = worldIn.getBlockState(pos.offset((EnumFacing)state.getValue(FACING)));
         if (iblockstate.getBlock() == this) {
            state = state.withProperty(OCCUPIED, iblockstate.getValue(OCCUPIED));
         }
      }

      return state;
   }

   /** @deprecated */
   public in withRotation(in state, Rotation rot) {
      return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
   }

   /** @deprecated */
   public in withMirror(in state, Mirror mirrorIn) {
      return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
   }

   public int getMetaFromState(in state) {
      int i = 0;
      i |= ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
      if (state.getValue(PART) == cB.HEAD) {
         i |= 8;
         if ((Boolean)state.getValue(OCCUPIED)) {
            i |= 4;
         }
      }

      return i;
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return ib.UNDEFINED;
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{FACING, PART, OCCUPIED});
   }

   public Yg createNewTileEntity(bij worldIn, int meta) {
      return new Yk();
   }

   public static boolean isHeadPiece(int metadata) {
      return (metadata & 8) != 0;
   }
}
