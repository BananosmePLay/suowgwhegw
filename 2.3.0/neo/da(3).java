package neo;

import java.util.Random;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.StringUtils;
import net.minecraft.util.math.BlockPos;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class da extends dd {
   private static final Logger LOGGER = LogManager.getLogger();
   public static final hW FACING;
   public static final hV CONDITIONAL;

   public da(hK color) {
      super(hM.IRON, color);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(CONDITIONAL, false));
   }

   public Yg createNewTileEntity(bij worldIn, int meta) {
      Yq tileentitycommandblock = new Yq();
      tileentitycommandblock.setAuto(this == Nk.CHAIN_COMMAND_BLOCK);
      return tileentitycommandblock;
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      if (!worldIn.isRemote) {
         Yg tileentity = worldIn.getTileEntity(pos);
         if (tileentity instanceof Yq) {
            Yq tileentitycommandblock = (Yq)tileentity;
            boolean flag = worldIn.isBlockPowered(pos);
            boolean flag1 = tileentitycommandblock.isPowered();
            tileentitycommandblock.setPowered(flag);
            if (!flag1 && !tileentitycommandblock.isAuto() && tileentitycommandblock.getMode() != Yp.SEQUENCE && flag) {
               tileentitycommandblock.setConditionMet();
               worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
            }
         }
      }

   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      if (!worldIn.isRemote) {
         Yg tileentity = worldIn.getTileEntity(pos);
         if (tileentity instanceof Yq) {
            Yq tileentitycommandblock = (Yq)tileentity;
            XZ commandblockbaselogic = tileentitycommandblock.getCommandBlockLogic();
            boolean flag = !StringUtils.isNullOrEmpty(commandblockbaselogic.getCommand());
            Yp tileentitycommandblock$mode = tileentitycommandblock.getMode();
            boolean flag1 = tileentitycommandblock.isConditionMet();
            if (tileentitycommandblock$mode == Yp.AUTO) {
               tileentitycommandblock.setConditionMet();
               if (flag1) {
                  this.execute(state, worldIn, pos, commandblockbaselogic, flag);
               } else if (tileentitycommandblock.isConditional()) {
                  commandblockbaselogic.setSuccessCount(0);
               }

               if (tileentitycommandblock.isPowered() || tileentitycommandblock.isAuto()) {
                  worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
               }
            } else if (tileentitycommandblock$mode == Yp.REDSTONE) {
               if (flag1) {
                  this.execute(state, worldIn, pos, commandblockbaselogic, flag);
               } else if (tileentitycommandblock.isConditional()) {
                  commandblockbaselogic.setSuccessCount(0);
               }
            }

            worldIn.updateComparatorOutputLevel(pos, this);
         }
      }

   }

   private void execute(in p_193387_1_, bij p_193387_2_, BlockPos p_193387_3_, XZ p_193387_4_, boolean p_193387_5_) {
      if (p_193387_5_) {
         p_193387_4_.trigger(p_193387_2_);
      } else {
         p_193387_4_.setSuccessCount(0);
      }

      executeChain(p_193387_2_, p_193387_3_, (EnumFacing)p_193387_1_.getValue(FACING));
   }

   public int tickRate(bij worldIn) {
      return 1;
   }

   public boolean onBlockActivated(bij worldIn, BlockPos pos, in state, ME playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      Yg tileentity = worldIn.getTileEntity(pos);
      if (tileentity instanceof Yq && playerIn.canUseCommandBlock()) {
         playerIn.displayGuiCommandBlock((Yq)tileentity);
         return true;
      } else {
         return false;
      }
   }

   /** @deprecated */
   public boolean hasComparatorInputOverride(in state) {
      return true;
   }

   /** @deprecated */
   public int getComparatorInputOverride(in blockState, bij worldIn, BlockPos pos) {
      Yg tileentity = worldIn.getTileEntity(pos);
      return tileentity instanceof Yq ? ((Yq)tileentity).getCommandBlockLogic().getSuccessCount() : 0;
   }

   public void onBlockPlacedBy(bij worldIn, BlockPos pos, in state, Iw placer, Qy stack) {
      Yg tileentity = worldIn.getTileEntity(pos);
      if (tileentity instanceof Yq) {
         Yq tileentitycommandblock = (Yq)tileentity;
         XZ commandblockbaselogic = tileentitycommandblock.getCommandBlockLogic();
         if (stack.hasDisplayName()) {
            commandblockbaselogic.setName(stack.getDisplayName());
         }

         if (!worldIn.isRemote) {
            QQ nbttagcompound = stack.getTagCompound();
            if (nbttagcompound == null || !nbttagcompound.hasKey("BlockEntityTag", 10)) {
               commandblockbaselogic.setTrackOutput(worldIn.getGameRules().getBoolean("sendCommandFeedback"));
               tileentitycommandblock.setAuto(this == Nk.CHAIN_COMMAND_BLOCK);
            }

            if (tileentitycommandblock.getMode() == Yp.SEQUENCE) {
               boolean flag = worldIn.isBlockPowered(pos);
               tileentitycommandblock.setPowered(flag);
            }
         }
      }

   }

   public int quantityDropped(Random random) {
      return 0;
   }

   /** @deprecated */
   public EnumBlockRenderType getRenderType(in state) {
      return EnumBlockRenderType.MODEL;
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(FACING, EnumFacing.byIndex(meta & 7)).withProperty(CONDITIONAL, (meta & 8) != 0);
   }

   public int getMetaFromState(in state) {
      return ((EnumFacing)state.getValue(FACING)).getIndex() | ((Boolean)state.getValue(CONDITIONAL) ? 8 : 0);
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
      return new ii(this, new hT[]{FACING, CONDITIONAL});
   }

   public in getStateForPlacement(bij worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, Iw placer) {
      return this.getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer)).withProperty(CONDITIONAL, false);
   }

   private static void executeChain(bij p_193386_0_, BlockPos p_193386_1_, EnumFacing p_193386_2_) {
      BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos(p_193386_1_);
      bba gamerules = p_193386_0_.getGameRules();

      int i;
      in iblockstate;
      for(i = gamerules.getInt("maxCommandChainLength"); i-- > 0; p_193386_2_ = (EnumFacing)iblockstate.getValue(FACING)) {
         blockpos$mutableblockpos.move(p_193386_2_);
         iblockstate = p_193386_0_.getBlockState(blockpos$mutableblockpos);
         co block = iblockstate.getBlock();
         if (block != Nk.CHAIN_COMMAND_BLOCK) {
            break;
         }

         Yg tileentity = p_193386_0_.getTileEntity(blockpos$mutableblockpos);
         if (!(tileentity instanceof Yq)) {
            break;
         }

         Yq tileentitycommandblock = (Yq)tileentity;
         if (tileentitycommandblock.getMode() != Yp.SEQUENCE) {
            break;
         }

         if (tileentitycommandblock.isPowered() || tileentitycommandblock.isAuto()) {
            XZ commandblockbaselogic = tileentitycommandblock.getCommandBlockLogic();
            if (tileentitycommandblock.setConditionMet()) {
               if (!commandblockbaselogic.trigger(p_193386_0_)) {
                  break;
               }

               p_193386_0_.updateComparatorOutputLevel(blockpos$mutableblockpos, block);
            } else if (tileentitycommandblock.isConditional()) {
               commandblockbaselogic.setSuccessCount(0);
            }
         }
      }

      if (i <= 0) {
         int j = Math.max(gamerules.getInt("maxCommandChainLength"), 0);
         LOGGER.warn("Commandblock chain tried to execure more than " + j + " steps!");
      }

   }

   static {
      FACING = dh.FACING;
      CONDITIONAL = hV.create("conditional");
   }
}
