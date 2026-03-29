package neo;

import java.util.UUID;

public class Cs extends Ch {
   public Cs() {
   }

   public String getName() {
      return "entitydata";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getUsage(DB sender) {
      return "commands.entitydata.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length < 2) {
         throw new Ej("commands.entitydata.usage", new Object[0]);
      } else {
         Ig entity = getEntity(server, sender, args[0]);
         if (entity instanceof ME) {
            throw new Ct("commands.entitydata.noPlayers", new Object[]{entity.getDisplayName()});
         } else {
            QQ nbttagcompound = entityToNBT(entity);
            QQ nbttagcompound1 = nbttagcompound.copy();

            QQ nbttagcompound2;
            try {
               nbttagcompound2 = QG.getTagFromJson(buildString(args, 1));
            } catch (QI var9) {
               QI nbtexception = var9;
               throw new Ct("commands.entitydata.tagError", new Object[]{nbtexception.getMessage()});
            }

            UUID uuid = entity.getUniqueID();
            nbttagcompound.merge(nbttagcompound2);
            entity.setUniqueId(uuid);
            if (nbttagcompound.equals(nbttagcompound1)) {
               throw new Ct("commands.entitydata.failed", new Object[]{nbttagcompound.toString()});
            } else {
               entity.readFromNBT(nbttagcompound);
               notifyCommandListener(sender, this, "commands.entitydata.success", new Object[]{nbttagcompound.toString()});
            }
         }
      }
   }

   public boolean isUsernameIndex(String[] args, int index) {
      return index == 0;
   }
}
