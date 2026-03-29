package neo;

import io.netty.buffer.ByteBuf;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Nullable;
import net.minecraft.util.ReportedException;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public abstract class XZ implements DB {
   private static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("HH:mm:ss");
   private long lastExecution = -1L;
   private boolean updateLastExecution = true;
   private int successCount;
   private boolean trackOutput = true;
   private ITextComponent lastOutput;
   private String commandStored = "";
   private String customName = "@";
   private final CL resultStats = new CL();

   public XZ() {
   }

   public int getSuccessCount() {
      return this.successCount;
   }

   public void setSuccessCount(int successCountIn) {
      this.successCount = successCountIn;
   }

   public ITextComponent getLastOutput() {
      return (ITextComponent)(this.lastOutput == null ? new TextComponentString("") : this.lastOutput);
   }

   public QQ writeToNBT(QQ p_189510_1_) {
      p_189510_1_.setString("Command", this.commandStored);
      p_189510_1_.setInteger("SuccessCount", this.successCount);
      p_189510_1_.setString("CustomName", this.customName);
      p_189510_1_.setBoolean("TrackOutput", this.trackOutput);
      if (this.lastOutput != null && this.trackOutput) {
         p_189510_1_.setString("LastOutput", ITextComponent.Serializer.componentToJson(this.lastOutput));
      }

      p_189510_1_.setBoolean("UpdateLastExecution", this.updateLastExecution);
      if (this.updateLastExecution && this.lastExecution > 0L) {
         p_189510_1_.setLong("LastExecution", this.lastExecution);
      }

      this.resultStats.writeStatsToNBT(p_189510_1_);
      return p_189510_1_;
   }

   public void readDataFromNBT(QQ nbt) {
      this.commandStored = nbt.getString("Command");
      this.successCount = nbt.getInteger("SuccessCount");
      if (nbt.hasKey("CustomName", 8)) {
         this.customName = nbt.getString("CustomName");
      }

      if (nbt.hasKey("TrackOutput", 1)) {
         this.trackOutput = nbt.getBoolean("TrackOutput");
      }

      if (nbt.hasKey("LastOutput", 8) && this.trackOutput) {
         try {
            this.lastOutput = ITextComponent.Serializer.jsonToComponent(nbt.getString("LastOutput"));
         } catch (Throwable var3) {
            Throwable throwable = var3;
            this.lastOutput = new TextComponentString(throwable.getMessage());
         }
      } else {
         this.lastOutput = null;
      }

      if (nbt.hasKey("UpdateLastExecution")) {
         this.updateLastExecution = nbt.getBoolean("UpdateLastExecution");
      }

      if (this.updateLastExecution && nbt.hasKey("LastExecution")) {
         this.lastExecution = nbt.getLong("LastExecution");
      } else {
         this.lastExecution = -1L;
      }

      this.resultStats.readStatsFromNBT(nbt);
   }

   public boolean canUseCommand(int permLevel, String commandName) {
      return permLevel <= 2;
   }

   public void setCommand(String command) {
      this.commandStored = command;
      this.successCount = 0;
   }

   public String getCommand() {
      return this.commandStored;
   }

   public boolean trigger(bij worldIn) {
      if (!worldIn.isRemote && worldIn.getTotalWorldTime() != this.lastExecution) {
         if ("Searge".equalsIgnoreCase(this.commandStored)) {
            this.lastOutput = new TextComponentString("#itzlipofutzli");
            this.successCount = 1;
            return true;
         } else {
            Xx minecraftserver = this.getServer();
            if (minecraftserver != null && minecraftserver.isAnvilFileSet() && minecraftserver.isCommandBlockEnabled()) {
               try {
                  this.lastOutput = null;
                  this.successCount = minecraftserver.getCommandManager().executeCommand(this, this.commandStored);
               } catch (Throwable var6) {
                  Throwable throwable = var6;
                  Er crashreport = Er.makeCrashReport(throwable, "Executing command block");
                  Ey crashreportcategory = crashreport.makeCategory("Command to be executed");
                  crashreportcategory.addDetail("Command", new Ez<String>() {
                     public String call() throws Exception {
                        return XZ.this.getCommand();
                     }

                     // $FF: synthetic method
                     // $FF: bridge method
                     public Object call() throws Exception {
                        return this.call();
                     }
                  });
                  crashreportcategory.addDetail("Name", new Ez<String>() {
                     public String call() throws Exception {
                        return XZ.this.getName();
                     }

                     // $FF: synthetic method
                     // $FF: bridge method
                     public Object call() throws Exception {
                        return this.call();
                     }
                  });
                  throw new ReportedException(crashreport);
               }
            } else {
               this.successCount = 0;
            }

            if (this.updateLastExecution) {
               this.lastExecution = worldIn.getTotalWorldTime();
            } else {
               this.lastExecution = -1L;
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public String getName() {
      return this.customName;
   }

   public void setName(String name) {
      this.customName = name;
   }

   public void sendMessage(ITextComponent component) {
      if (this.trackOutput && this.getEntityWorld() != null && !this.getEntityWorld().isRemote) {
         this.lastOutput = (new TextComponentString("[" + TIMESTAMP_FORMAT.format(new Date()) + "] ")).appendSibling(component);
         this.updateCommand();
      }

   }

   public boolean sendCommandFeedback() {
      Xx minecraftserver = this.getServer();
      return minecraftserver == null || !minecraftserver.isAnvilFileSet() || minecraftserver.worlds[0].getGameRules().getBoolean("commandBlockOutput");
   }

   public void setCommandStat(CK type, int amount) {
      this.resultStats.setCommandStatForSender(this.getServer(), this, type, amount);
   }

   public abstract void updateCommand();

   public abstract int getCommandBlockType();

   public abstract void fillInInfo(ByteBuf var1);

   public void setLastOutput(@Nullable ITextComponent lastOutputMessage) {
      this.lastOutput = lastOutputMessage;
   }

   public void setTrackOutput(boolean shouldTrackOutput) {
      this.trackOutput = shouldTrackOutput;
   }

   public boolean shouldTrackOutput() {
      return this.trackOutput;
   }

   public boolean tryOpenEditCommandBlock(ME playerIn) {
      if (!playerIn.canUseCommandBlock()) {
         return false;
      } else {
         if (playerIn.getEntityWorld().isRemote) {
            playerIn.displayGuiEditCommandCart(this);
         }

         return true;
      }
   }

   public CL getCommandResultStats() {
      return this.resultStats;
   }
}
