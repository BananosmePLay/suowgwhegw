package net.minecraft.command;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;

public class CommandGive extends CommandBase {
   public CommandGive() {
   }

   public String getName() {
      return "give";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getUsage(ICommandSender sender) {
      return "commands.give.usage";
   }

   public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
      if (args.length < 2) {
         throw new WrongUsageException("commands.give.usage", new Object[0]);
      } else {
         EntityPlayer entityplayer = getPlayer(server, sender, args[0]);
         Item item = getItemByText(sender, args[1]);
         int i = args.length >= 3 ? parseInt(args[2], 1, item.getItemStackLimit()) : 1;
         int j = args.length >= 4 ? parseInt(args[3]) : 0;
         ItemStack itemstack = new ItemStack(item, i, j);
         if (args.length >= 5) {
            String s = buildString(args, 4);

            try {
               itemstack.setTagCompound(JsonToNBT.getTagFromJson(s));
            } catch (NBTException var11) {
               NBTException nbtexception = var11;
               throw new CommandException("commands.give.tagError", new Object[]{nbtexception.getMessage()});
            }
         }

         boolean flag = entityplayer.inventory.addItemStackToInventory(itemstack);
         if (flag) {
            entityplayer.world.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, ((((EntityPlayer)entityplayer).getRNG().nextFloat() - ((EntityPlayer)entityplayer).getRNG().nextFloat()) * 0.7F + 1.0F) * 2.0F);
            entityplayer.inventoryContainer.detectAndSendChanges();
         }

         EntityItem entityitem;
         if (flag && itemstack.isEmpty()) {
            itemstack.setCount(1);
            sender.setCommandStat(CommandResultStats.Type.AFFECTED_ITEMS, i);
            entityitem = ((EntityPlayer)entityplayer).dropItem(itemstack, false);
            if (entityitem != null) {
               entityitem.makeFakeItem();
            }
         } else {
            sender.setCommandStat(CommandResultStats.Type.AFFECTED_ITEMS, i - itemstack.getCount());
            entityitem = ((EntityPlayer)entityplayer).dropItem(itemstack, false);
            if (entityitem != null) {
               entityitem.setNoPickupDelay();
               entityitem.setOwner(((EntityPlayer)entityplayer).getName());
            }
         }

         notifyCommandListener(sender, this, "commands.give.success", new Object[]{itemstack.getTextComponent(), i, ((EntityPlayer)entityplayer).getName()});
      }
   }

   public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
      if (args.length == 1) {
         return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
      } else {
         return args.length == 2 ? getListOfStringsMatchingLastWord(args, Item.REGISTRY.getKeys()) : Collections.emptyList();
      }
   }

   public boolean isUsernameIndex(String[] args, int index) {
      return index == 0;
   }
}
