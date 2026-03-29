package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class CA implements DA {
   private static final Logger LOGGER = LogManager.getLogger();
   private final Map<String, Dy> commandMap = Maps.newHashMap();
   private final Set<Dy> commandSet = Sets.newHashSet();

   public CA() {
   }

   public int executeCommand(DB sender, String rawCommand) {
      rawCommand = rawCommand.trim();
      if (rawCommand.startsWith("/")) {
         rawCommand = rawCommand.substring(1);
      }

      String[] astring = rawCommand.split(" ");
      String s = astring[0];
      astring = dropFirstString(astring);
      Dy icommand = (Dy)this.commandMap.get(s);
      int i = 0;

      TextComponentTranslation textcomponenttranslation2;
      try {
         int j = this.getUsernameIndex(icommand, astring);
         if (icommand == null) {
            textcomponenttranslation2 = new TextComponentTranslation("commands.generic.notFound", new Object[0]);
            textcomponenttranslation2.getStyle().setColor(TextFormatting.RED);
            sender.sendMessage(textcomponenttranslation2);
         } else if (icommand.checkPermission(this.getServer(), sender)) {
            if (j > -1) {
               List<Ig> list = Ds.matchEntities(sender, astring[j], Ig.class);
               String s1 = astring[j];
               sender.setCommandStat(CK.AFFECTED_ENTITIES, list.size());
               if (list.isEmpty()) {
                  throw new DF("commands.generic.selector.notFound", new Object[]{astring[j]});
               }

               Iterator var10 = list.iterator();

               while(var10.hasNext()) {
                  Ig entity = (Ig)var10.next();
                  astring[j] = entity.getCachedUniqueIdString();
                  if (this.tryExecute(sender, astring, icommand, rawCommand)) {
                     ++i;
                  }
               }

               astring[j] = s1;
            } else {
               sender.setCommandStat(CK.AFFECTED_ENTITIES, 1);
               if (this.tryExecute(sender, astring, icommand, rawCommand)) {
                  ++i;
               }
            }
         } else {
            textcomponenttranslation2 = new TextComponentTranslation("commands.generic.permission", new Object[0]);
            textcomponenttranslation2.getStyle().setColor(TextFormatting.RED);
            sender.sendMessage(textcomponenttranslation2);
         }
      } catch (Ct var12) {
         Ct commandexception = var12;
         textcomponenttranslation2 = new TextComponentTranslation(commandexception.getMessage(), commandexception.getErrorObjects());
         textcomponenttranslation2.getStyle().setColor(TextFormatting.RED);
         sender.sendMessage(textcomponenttranslation2);
      }

      sender.setCommandStat(CK.SUCCESS_COUNT, i);
      return i;
   }

   protected boolean tryExecute(DB sender, String[] args, Dy command, String input) {
      TextComponentTranslation textcomponenttranslation;
      try {
         command.execute(this.getServer(), sender, args);
         return true;
      } catch (Ej var7) {
         Ej wrongusageexception = var7;
         textcomponenttranslation = new TextComponentTranslation("commands.generic.usage", new Object[]{new TextComponentTranslation(wrongusageexception.getMessage(), wrongusageexception.getErrorObjects())});
         textcomponenttranslation.getStyle().setColor(TextFormatting.RED);
         sender.sendMessage(textcomponenttranslation);
      } catch (Ct var8) {
         Ct commandexception = var8;
         textcomponenttranslation = new TextComponentTranslation(commandexception.getMessage(), commandexception.getErrorObjects());
         textcomponenttranslation.getStyle().setColor(TextFormatting.RED);
         sender.sendMessage(textcomponenttranslation);
      } catch (Throwable var9) {
         Throwable throwable = var9;
         textcomponenttranslation = new TextComponentTranslation("commands.generic.exception", new Object[0]);
         textcomponenttranslation.getStyle().setColor(TextFormatting.RED);
         sender.sendMessage(textcomponenttranslation);
         LOGGER.warn("Couldn't process command: " + input, throwable);
      }

      return false;
   }

   protected abstract Xx getServer();

   public Dy registerCommand(Dy command) {
      this.commandMap.put(command.getName(), command);
      this.commandSet.add(command);
      Iterator var2 = command.getAliases().iterator();

      while(true) {
         String s;
         Dy icommand;
         do {
            if (!var2.hasNext()) {
               return command;
            }

            s = (String)var2.next();
            icommand = (Dy)this.commandMap.get(s);
         } while(icommand != null && icommand.getName().equals(s));

         this.commandMap.put(s, command);
      }
   }

   private static String[] dropFirstString(String[] input) {
      String[] astring = new String[input.length - 1];
      System.arraycopy(input, 1, astring, 0, input.length - 1);
      return astring;
   }

   public List<String> getTabCompletions(DB sender, String input, @Nullable BlockPos pos) {
      String[] astring = input.split(" ", -1);
      String s = astring[0];
      if (astring.length == 1) {
         List<String> list = Lists.newArrayList();
         Iterator var7 = this.commandMap.entrySet().iterator();

         while(var7.hasNext()) {
            Map.Entry<String, Dy> entry = (Map.Entry)var7.next();
            if (Ch.doesStringStartWith(s, (String)entry.getKey()) && ((Dy)entry.getValue()).checkPermission(this.getServer(), sender)) {
               list.add(entry.getKey());
            }
         }

         return list;
      } else {
         if (astring.length > 1) {
            Dy icommand = (Dy)this.commandMap.get(s);
            if (icommand != null && icommand.checkPermission(this.getServer(), sender)) {
               return icommand.getTabCompletions(this.getServer(), sender, dropFirstString(astring), pos);
            }
         }

         return Collections.emptyList();
      }
   }

   public List<Dy> getPossibleCommands(DB sender) {
      List<Dy> list = Lists.newArrayList();
      Iterator var3 = this.commandSet.iterator();

      while(var3.hasNext()) {
         Dy icommand = (Dy)var3.next();
         if (icommand.checkPermission(this.getServer(), sender)) {
            list.add(icommand);
         }
      }

      return list;
   }

   public Map<String, Dy> getCommands() {
      return this.commandMap;
   }

   private int getUsernameIndex(Dy command, String[] args) throws Ct {
      if (command == null) {
         return -1;
      } else {
         for(int i = 0; i < args.length; ++i) {
            if (command.isUsernameIndex(args, i) && Ds.matchesMultiplePlayers(args[i])) {
               return i;
            }
         }

         return -1;
      }
   }
}
