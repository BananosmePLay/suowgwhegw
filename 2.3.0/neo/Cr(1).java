package neo;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;

public class Cr extends Ch {
   public Cr() {
   }

   public String getName() {
      return "enchant";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getUsage(DB sender) {
      return "commands.enchant.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length < 2) {
         throw new Ej("commands.enchant.usage", new Object[0]);
      } else {
         Iw entitylivingbase = (Iw)getEntity(server, sender, args[0], Iw.class);
         sender.setCommandStat(CK.AFFECTED_ITEMS, 0);

         Fa enchantment;
         try {
            enchantment = Fa.getEnchantmentByID(parseInt(args[1], 0));
         } catch (DD var12) {
            enchantment = Fa.getEnchantmentByLocation(args[1]);
         }

         if (enchantment == null) {
            throw new DD("commands.enchant.notFound", new Object[]{args[1]});
         } else {
            int i = 1;
            Qy itemstack = entitylivingbase.getHeldItemMainhand();
            if (itemstack.isEmpty()) {
               throw new Ct("commands.enchant.noItem", new Object[0]);
            } else if (!enchantment.canApply(itemstack)) {
               throw new Ct("commands.enchant.cantEnchant", new Object[0]);
            } else {
               if (args.length >= 3) {
                  i = parseInt(args[2], enchantment.getMinLevel(), enchantment.getMaxLevel());
               }

               if (itemstack.hasTagCompound()) {
                  QW nbttaglist = itemstack.getEnchantmentTagList();

                  for(int j = 0; j < nbttaglist.tagCount(); ++j) {
                     int k = nbttaglist.getCompoundTagAt(j).getShort("id");
                     if (Fa.getEnchantmentByID(k) != null) {
                        Fa enchantment1 = Fa.getEnchantmentByID(k);
                        if (!enchantment.isCompatibleWith(enchantment1)) {
                           throw new Ct("commands.enchant.cantCombine", new Object[]{enchantment.getTranslatedName(i), enchantment1.getTranslatedName(nbttaglist.getCompoundTagAt(j).getShort("lvl"))});
                        }
                     }
                  }
               }

               itemstack.addEnchantment(enchantment, i);
               notifyCommandListener(sender, this, "commands.enchant.success", new Object[0]);
               sender.setCommandStat(CK.AFFECTED_ITEMS, 1);
            }
         }
      }
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      if (args.length == 1) {
         return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
      } else {
         return args.length == 2 ? getListOfStringsMatchingLastWord(args, Fa.REGISTRY.getKeys()) : Collections.emptyList();
      }
   }

   public boolean isUsernameIndex(String[] args, int index) {
      return index == 0;
   }
}
