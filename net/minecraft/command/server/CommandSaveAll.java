package net.minecraft.command.server;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.WorldServer;

public class CommandSaveAll extends CommandBase {
   public CommandSaveAll() {
   }

   public String getName() {
      return "save-all";
   }

   public String getUsage(ICommandSender sender) {
      return "commands.save.usage";
   }

   public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
      sender.sendMessage(new TextComponentTranslation("commands.save.start", new Object[0]));
      if (server.getPlayerList() != null) {
         server.getPlayerList().saveAllPlayerData();
      }

      try {
         WorldServer worldserver1;
         boolean flag1;
         int j;
         for(j = 0; j < server.worlds.length; ++j) {
            if (server.worlds[j] != null) {
               worldserver1 = server.worlds[j];
               flag1 = worldserver1.disableLevelSaving;
               worldserver1.disableLevelSaving = false;
               worldserver1.saveAllChunks(true, (IProgressUpdate)null);
               worldserver1.disableLevelSaving = flag1;
            }
         }

         if (args.length > 0 && "flush".equals(args[0])) {
            sender.sendMessage(new TextComponentTranslation("commands.save.flushStart", new Object[0]));

            for(j = 0; j < server.worlds.length; ++j) {
               if (server.worlds[j] != null) {
                  worldserver1 = server.worlds[j];
                  flag1 = worldserver1.disableLevelSaving;
                  worldserver1.disableLevelSaving = false;
                  worldserver1.flushToDisk();
                  worldserver1.disableLevelSaving = flag1;
               }
            }

            sender.sendMessage(new TextComponentTranslation("commands.save.flushEnd", new Object[0]));
         }
      } catch (MinecraftException var7) {
         MinecraftException minecraftexception = var7;
         notifyCommandListener(sender, this, "commands.save.failed", new Object[]{minecraftexception.getMessage()});
         return;
      }

      notifyCommandListener(sender, this, "commands.save.success", new Object[0]);
   }

   public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
      return args.length == 1 ? getListOfStringsMatchingLastWord(args, new String[]{"flush"}) : Collections.emptyList();
   }
}
