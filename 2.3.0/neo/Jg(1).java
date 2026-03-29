package neo;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.IDataFixer;
import net.minecraft.util.datafix.IDataWalker;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class Jg extends Jc {
   private static final Rd<String> COMMAND;
   private static final Rd<ITextComponent> LAST_OUTPUT;
   private final XZ commandBlockLogic = new XZ() {
      public void updateCommand() {
         Jg.this.getDataManager().set(Jg.COMMAND, this.getCommand());
         Jg.this.getDataManager().set(Jg.LAST_OUTPUT, this.getLastOutput());
      }

      public int getCommandBlockType() {
         return 1;
      }

      public void fillInInfo(ByteBuf buf) {
         buf.writeInt(Jg.this.getEntityId());
      }

      public BlockPos getPosition() {
         return new BlockPos(Jg.this.posX, Jg.this.posY + 0.5, Jg.this.posZ);
      }

      public Vec3d getPositionVector() {
         return new Vec3d(Jg.this.posX, Jg.this.posY, Jg.this.posZ);
      }

      public bij getEntityWorld() {
         return Jg.this.world;
      }

      public Ig getCommandSenderEntity() {
         return Jg.this;
      }

      public Xx getServer() {
         return Jg.this.world.getMinecraftServer();
      }
   };
   private int activatorRailCooldown;

   public Jg(bij worldIn) {
      super(worldIn);
   }

   public Jg(bij worldIn, double x, double y, double z) {
      super(worldIn, x, y, z);
   }

   public static void registerFixesMinecartCommand(DataFixer fixer) {
      Jc.registerFixesMinecart(fixer, Jg.class);
      fixer.registerWalker(FixTypes.ENTITY, new IDataWalker() {
         public QQ process(IDataFixer fixer, QQ compound, int versionIn) {
            if (Yg.getKey(Yq.class).equals(new ResourceLocation(compound.getString("id")))) {
               compound.setString("id", "Control");
               fixer.process(FixTypes.BLOCK_ENTITY, compound, versionIn);
               compound.setString("id", "MinecartCommandBlock");
            }

            return compound;
         }
      });
   }

   protected void entityInit() {
      super.entityInit();
      this.getDataManager().register(COMMAND, "");
      this.getDataManager().register(LAST_OUTPUT, new TextComponentString(""));
   }

   protected void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      this.commandBlockLogic.readDataFromNBT(compound);
      this.getDataManager().set(COMMAND, this.getCommandBlockLogic().getCommand());
      this.getDataManager().set(LAST_OUTPUT, this.getCommandBlockLogic().getLastOutput());
   }

   protected void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      this.commandBlockLogic.writeToNBT(compound);
   }

   public Jb getType() {
      return Jb.COMMAND_BLOCK;
   }

   public in getDefaultDisplayTile() {
      return Nk.COMMAND_BLOCK.getDefaultState();
   }

   public XZ getCommandBlockLogic() {
      return this.commandBlockLogic;
   }

   public void onActivatorRailPass(int x, int y, int z, boolean receivingPower) {
      if (receivingPower && this.ticksExisted - this.activatorRailCooldown >= 4) {
         this.getCommandBlockLogic().trigger(this.world);
         this.activatorRailCooldown = this.ticksExisted;
      }

   }

   public boolean processInitialInteract(ME player, EnumHand hand) {
      this.commandBlockLogic.tryOpenEditCommandBlock(player);
      return false;
   }

   public void notifyDataManagerChange(Rd<?> key) {
      super.notifyDataManagerChange(key);
      if (LAST_OUTPUT.equals(key)) {
         try {
            this.commandBlockLogic.setLastOutput((ITextComponent)this.getDataManager().get(LAST_OUTPUT));
         } catch (Throwable var3) {
         }
      } else if (COMMAND.equals(key)) {
         this.commandBlockLogic.setCommand((String)this.getDataManager().get(COMMAND));
      }

   }

   public boolean ignoreItemEntityData() {
      return true;
   }

   static {
      COMMAND = Rv.createKey(Jg.class, Rt.STRING);
      LAST_OUTPUT = Rv.createKey(Jg.class, Rt.TEXT_COMPONENT);
   }
}
