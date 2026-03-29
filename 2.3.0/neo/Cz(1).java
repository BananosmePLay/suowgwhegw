package neo;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;

public class Cz extends Ch {
   public Cz() {
   }

   public String getName() {
      return "give";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getUsage(DB sender) {
      return "commands.give.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length < 2) {
         throw new Ej("commands.give.usage", new Object[0]);
      } else {
         ME entityplayer = getPlayer(server, sender, args[0]);
         OL item = getItemByText(sender, args[1]);
         int i = args.length >= 3 ? parseInt(args[2], 1, item.getItemStackLimit()) : 1;
         int j = args.length >= 4 ? parseInt(args[3]) : 0;
         Qy itemstack = new Qy(item, i, j);
         if (args.length >= 5) {
            String s = buildString(args, 4);

            try {
               itemstack.setTagCompound(QG.getTagFromJson(s));
            } catch (QI var11) {
               QI nbtexception = var11;
               throw new Ct("commands.give.tagError", new Object[]{nbtexception.getMessage()});
            }
         }

         boolean flag = entityplayer.inventory.addItemStackToInventory(itemstack);
         if (flag) {
            entityplayer.world.playSound((ME)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, NO.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, ((((ME)entityplayer).getRNG().nextFloat() - ((ME)entityplayer).getRNG().nextFloat()) * 0.7F + 1.0F) * 2.0F);
            entityplayer.inventoryContainer.detectAndSendChanges();
         }

         IY entityitem;
         if (flag && itemstack.isEmpty()) {
            itemstack.setCount(1);
            sender.setCommandStat(CK.AFFECTED_ITEMS, i);
            entityitem = ((ME)entityplayer).dropItem(itemstack, false);
            if (entityitem != null) {
               entityitem.makeFakeItem();
            }
         } else {
            sender.setCommandStat(CK.AFFECTED_ITEMS, i - itemstack.getCount());
            entityitem = ((ME)entityplayer).dropItem(itemstack, false);
            if (entityitem != null) {
               entityitem.setNoPickupDelay();
               entityitem.setOwner(((ME)entityplayer).getName());
            }
         }

         notifyCommandListener(sender, this, "commands.give.success", new Object[]{itemstack.getTextComponent(), i, ((ME)entityplayer).getName()});
      }
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      if (args.length == 1) {
         return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
      } else {
         return args.length == 2 ? getListOfStringsMatchingLastWord(args, OL.REGISTRY.getKeys()) : Collections.emptyList();
      }
   }

   public boolean isUsernameIndex(String[] args, int index) {
      return index == 0;
   }
}
