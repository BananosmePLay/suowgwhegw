package neo;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;

public class Cq extends Ch {
   public Cq() {
   }

   public String getName() {
      return "effect";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getUsage(DB sender) {
      return "commands.effect.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length < 2) {
         throw new Ej("commands.effect.usage", new Object[0]);
      } else {
         Iw entitylivingbase = (Iw)getEntity(server, sender, args[0], Iw.class);
         if ("clear".equals(args[1])) {
            if (entitylivingbase.getActivePotionEffects().isEmpty()) {
               throw new Ct("commands.effect.failure.notActive.all", new Object[]{entitylivingbase.getName()});
            }

            entitylivingbase.clearActivePotions();
            notifyCommandListener(sender, this, "commands.effect.success.removed.all", new Object[]{entitylivingbase.getName()});
         } else {
            VW potion;
            try {
               potion = VW.getPotionById(parseInt(args[1], 1));
            } catch (DD var11) {
               potion = VW.getPotionFromResourceLocation(args[1]);
            }

            if (potion == null) {
               throw new DD("commands.effect.notFound", new Object[]{args[1]});
            }

            int i = 600;
            int j = 30;
            int k = 0;
            if (args.length >= 3) {
               j = parseInt(args[2], 0, 1000000);
               if (potion.isInstant()) {
                  i = j;
               } else {
                  i = j * 20;
               }
            } else if (potion.isInstant()) {
               i = 1;
            }

            if (args.length >= 4) {
               k = parseInt(args[3], 0, 255);
            }

            boolean flag = true;
            if (args.length >= 5 && "true".equalsIgnoreCase(args[4])) {
               flag = false;
            }

            if (j > 0) {
               VZ potioneffect = new VZ(potion, i, k, false, flag);
               entitylivingbase.addPotionEffect(potioneffect);
               notifyCommandListener(sender, this, "commands.effect.success", new Object[]{new TextComponentTranslation(potioneffect.getEffectName(), new Object[0]), VW.getIdFromPotion(potion), k, entitylivingbase.getName(), j});
            } else {
               if (!entitylivingbase.isPotionActive(potion)) {
                  throw new Ct("commands.effect.failure.notActive", new Object[]{new TextComponentTranslation(potion.getName(), new Object[0]), entitylivingbase.getName()});
               }

               entitylivingbase.removePotionEffect(potion);
               notifyCommandListener(sender, this, "commands.effect.success.removed", new Object[]{new TextComponentTranslation(potion.getName(), new Object[0]), entitylivingbase.getName()});
            }
         }

      }
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      if (args.length == 1) {
         return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
      } else if (args.length == 2) {
         return getListOfStringsMatchingLastWord(args, VW.REGISTRY.getKeys());
      } else {
         return args.length == 5 ? getListOfStringsMatchingLastWord(args, new String[]{"true", "false"}) : Collections.emptyList();
      }
   }

   public boolean isUsernameIndex(String[] args, int index) {
      return index == 0;
   }
}
