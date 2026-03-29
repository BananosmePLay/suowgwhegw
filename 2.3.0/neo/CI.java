package neo;

import com.google.common.collect.Maps;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.math.BlockPos;

public class CI extends Ch {
   private static final Map<String, Integer> SHORTCUTS = Maps.newHashMap();

   public CI() {
   }

   public String getName() {
      return "replaceitem";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getUsage(DB sender) {
      return "commands.replaceitem.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length < 1) {
         throw new Ej("commands.replaceitem.usage", new Object[0]);
      } else {
         boolean flag;
         if ("entity".equals(args[0])) {
            flag = false;
         } else {
            if (!"block".equals(args[0])) {
               throw new Ej("commands.replaceitem.usage", new Object[0]);
            }

            flag = true;
         }

         int i;
         if (flag) {
            if (args.length < 6) {
               throw new Ej("commands.replaceitem.block.usage", new Object[0]);
            }

            i = 4;
         } else {
            if (args.length < 4) {
               throw new Ej("commands.replaceitem.entity.usage", new Object[0]);
            }

            i = 2;
         }

         String s = args[i];
         int j = this.getSlotForShortcut(args[i++]);

         OL item;
         try {
            item = getItemByText(sender, args[i]);
         } catch (DD var17) {
            DD numberinvalidexception = var17;
            if (co.getBlockFromName(args[i]) != Nk.AIR) {
               throw numberinvalidexception;
            }

            item = null;
         }

         ++i;
         int k = args.length > i ? parseInt(args[i++], 1, item.getItemStackLimit()) : 1;
         int l = args.length > i ? parseInt(args[i++]) : 0;
         Qy itemstack = new Qy(item, k, l);
         if (args.length > i) {
            String s1 = buildString(args, i);

            try {
               itemstack.setTagCompound(QG.getTagFromJson(s1));
            } catch (QI var16) {
               QI nbtexception = var16;
               throw new Ct("commands.replaceitem.tagError", new Object[]{nbtexception.getMessage()});
            }
         }

         if (flag) {
            sender.setCommandStat(CK.AFFECTED_ITEMS, 0);
            BlockPos blockpos = parseBlockPos(sender, args, 1, false);
            bij world = sender.getEntityWorld();
            Yg tileentity = world.getTileEntity(blockpos);
            if (tileentity == null || !(tileentity instanceof IInventory)) {
               throw new Ct("commands.replaceitem.noContainer", new Object[]{blockpos.getX(), blockpos.getY(), blockpos.getZ()});
            }

            IInventory iinventory = (IInventory)tileentity;
            if (j >= 0 && j < iinventory.getSizeInventory()) {
               iinventory.setInventorySlotContents(j, itemstack);
            }
         } else {
            Ig entity = getEntity(server, sender, args[1]);
            sender.setCommandStat(CK.AFFECTED_ITEMS, 0);
            if (entity instanceof ME) {
               ((ME)entity).inventoryContainer.detectAndSendChanges();
            }

            if (!entity.replaceItemInInventory(j, itemstack)) {
               throw new Ct("commands.replaceitem.failed", new Object[]{s, k, itemstack.isEmpty() ? "Air" : itemstack.getTextComponent()});
            }

            if (entity instanceof ME) {
               ((ME)entity).inventoryContainer.detectAndSendChanges();
            }
         }

         sender.setCommandStat(CK.AFFECTED_ITEMS, k);
         notifyCommandListener(sender, this, "commands.replaceitem.success", new Object[]{s, k, itemstack.isEmpty() ? "Air" : itemstack.getTextComponent()});
      }
   }

   private int getSlotForShortcut(String shortcut) throws Ct {
      if (!SHORTCUTS.containsKey(shortcut)) {
         throw new Ct("commands.generic.parameter.invalid", new Object[]{shortcut});
      } else {
         return (Integer)SHORTCUTS.get(shortcut);
      }
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      if (args.length == 1) {
         return getListOfStringsMatchingLastWord(args, new String[]{"entity", "block"});
      } else if (args.length == 2 && "entity".equals(args[0])) {
         return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
      } else if (args.length >= 2 && args.length <= 4 && "block".equals(args[0])) {
         return getTabCompletionCoordinate(args, 1, targetPos);
      } else if ((args.length != 3 || !"entity".equals(args[0])) && (args.length != 5 || !"block".equals(args[0]))) {
         return args.length == 4 && "entity".equals(args[0]) || args.length == 6 && "block".equals(args[0]) ? getListOfStringsMatchingLastWord(args, OL.REGISTRY.getKeys()) : Collections.emptyList();
      } else {
         return getListOfStringsMatchingLastWord(args, SHORTCUTS.keySet());
      }
   }

   public boolean isUsernameIndex(String[] args, int index) {
      return args.length > 0 && "entity".equals(args[0]) && index == 1;
   }

   static {
      int j1;
      for(j1 = 0; j1 < 54; ++j1) {
         SHORTCUTS.put("slot.container." + j1, j1);
      }

      for(j1 = 0; j1 < 9; ++j1) {
         SHORTCUTS.put("slot.hotbar." + j1, j1);
      }

      for(j1 = 0; j1 < 27; ++j1) {
         SHORTCUTS.put("slot.inventory." + j1, 9 + j1);
      }

      for(j1 = 0; j1 < 27; ++j1) {
         SHORTCUTS.put("slot.enderchest." + j1, 200 + j1);
      }

      for(j1 = 0; j1 < 8; ++j1) {
         SHORTCUTS.put("slot.villager." + j1, 300 + j1);
      }

      for(j1 = 0; j1 < 15; ++j1) {
         SHORTCUTS.put("slot.horse." + j1, 500 + j1);
      }

      SHORTCUTS.put("slot.weapon", 98);
      SHORTCUTS.put("slot.weapon.mainhand", 98);
      SHORTCUTS.put("slot.weapon.offhand", 99);
      SHORTCUTS.put("slot.armor.head", 100 + EntityEquipmentSlot.HEAD.getIndex());
      SHORTCUTS.put("slot.armor.chest", 100 + EntityEquipmentSlot.CHEST.getIndex());
      SHORTCUTS.put("slot.armor.legs", 100 + EntityEquipmentSlot.LEGS.getIndex());
      SHORTCUTS.put("slot.armor.feet", 100 + EntityEquipmentSlot.FEET.getIndex());
      SHORTCUTS.put("slot.horse.saddle", 400);
      SHORTCUTS.put("slot.horse.armor", 401);
      SHORTCUTS.put("slot.horse.chest", 499);
   }
}
