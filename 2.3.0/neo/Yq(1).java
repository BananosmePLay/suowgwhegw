package neo;

import io.netty.buffer.ByteBuf;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class Yq extends Yg {
   private boolean powered;
   private boolean auto;
   private boolean conditionMet;
   private boolean sendToClient;
   private final XZ commandBlockLogic = new XZ() {
      public BlockPos getPosition() {
         return Yq.this.pos;
      }

      public Vec3d getPositionVector() {
         return new Vec3d((double)Yq.this.pos.getX() + 0.5, (double)Yq.this.pos.getY() + 0.5, (double)Yq.this.pos.getZ() + 0.5);
      }

      public bij getEntityWorld() {
         return Yq.this.getWorld();
      }

      public void setCommand(String command) {
         super.setCommand(command);
         Yq.this.markDirty();
      }

      public void updateCommand() {
         in iblockstate = Yq.this.world.getBlockState(Yq.this.pos);
         Yq.this.getWorld().notifyBlockUpdate(Yq.this.pos, iblockstate, iblockstate, 3);
      }

      public int getCommandBlockType() {
         return 0;
      }

      public void fillInInfo(ByteBuf buf) {
         buf.writeInt(Yq.this.pos.getX());
         buf.writeInt(Yq.this.pos.getY());
         buf.writeInt(Yq.this.pos.getZ());
      }

      public Xx getServer() {
         return Yq.this.world.getMinecraftServer();
      }
   };

   public Yq() {
   }

   public QQ writeToNBT(QQ compound) {
      super.writeToNBT(compound);
      this.commandBlockLogic.writeToNBT(compound);
      compound.setBoolean("powered", this.isPowered());
      compound.setBoolean("conditionMet", this.isConditionMet());
      compound.setBoolean("auto", this.isAuto());
      return compound;
   }

   public void readFromNBT(QQ compound) {
      super.readFromNBT(compound);
      this.commandBlockLogic.readDataFromNBT(compound);
      this.powered = compound.getBoolean("powered");
      this.conditionMet = compound.getBoolean("conditionMet");
      this.setAuto(compound.getBoolean("auto"));
   }

   @Nullable
   public Vg getUpdatePacket() {
      if (this.isSendToClient()) {
         this.setSendToClient(false);
         QQ nbttagcompound = this.writeToNBT(new QQ());
         return new Vg(this.pos, 2, nbttagcompound);
      } else {
         return null;
      }
   }

   public boolean onlyOpsCanSetNbt() {
      return true;
   }

   public XZ getCommandBlockLogic() {
      return this.commandBlockLogic;
   }

   public CL getCommandResultStats() {
      return this.commandBlockLogic.getCommandResultStats();
   }

   public void setPowered(boolean poweredIn) {
      this.powered = poweredIn;
   }

   public boolean isPowered() {
      return this.powered;
   }

   public boolean isAuto() {
      return this.auto;
   }

   public void setAuto(boolean autoIn) {
      boolean flag = this.auto;
      this.auto = autoIn;
      if (!flag && autoIn && !this.powered && this.world != null && this.getMode() != Yp.SEQUENCE) {
         co block = this.getBlockType();
         if (block instanceof da) {
            this.setConditionMet();
            this.world.scheduleUpdate(this.pos, block, block.tickRate(this.world));
         }
      }

   }

   public boolean isConditionMet() {
      return this.conditionMet;
   }

   public boolean setConditionMet() {
      this.conditionMet = true;
      if (this.isConditional()) {
         BlockPos blockpos = this.pos.offset(((EnumFacing)this.world.getBlockState(this.pos).getValue(da.FACING)).getOpposite());
         if (this.world.getBlockState(blockpos).getBlock() instanceof da) {
            Yg tileentity = this.world.getTileEntity(blockpos);
            this.conditionMet = tileentity instanceof Yq && ((Yq)tileentity).getCommandBlockLogic().getSuccessCount() > 0;
         } else {
            this.conditionMet = false;
         }
      }

      return this.conditionMet;
   }

   public boolean isSendToClient() {
      return this.sendToClient;
   }

   public void setSendToClient(boolean p_184252_1_) {
      this.sendToClient = p_184252_1_;
   }

   public Yp getMode() {
      co block = this.getBlockType();
      if (block == Nk.COMMAND_BLOCK) {
         return Yp.REDSTONE;
      } else if (block == Nk.REPEATING_COMMAND_BLOCK) {
         return Yp.AUTO;
      } else {
         return block == Nk.CHAIN_COMMAND_BLOCK ? Yp.SEQUENCE : Yp.REDSTONE;
      }
   }

   public boolean isConditional() {
      in iblockstate = this.world.getBlockState(this.getPos());
      return iblockstate.getBlock() instanceof da ? (Boolean)iblockstate.getValue(da.CONDITIONAL) : false;
   }

   public void validate() {
      this.blockType = null;
      super.validate();
   }
}
