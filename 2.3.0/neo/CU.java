package neo;

import io.netty.buffer.Unpooled;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;

public class CU extends Ch {
   public CU() {
   }

   public String getName() {
      return "stopsound";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getUsage(DB sender) {
      return "commands.stopsound.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length >= 1 && args.length <= 3) {
         int i = 0;
         MG entityplayermp = getPlayer(server, sender, args[i++]);
         String s = "";
         String s1 = "";
         if (args.length >= 2) {
            String s2 = args[i++];
            SoundCategory soundcategory = SoundCategory.getByName(s2);
            if (soundcategory == null) {
               throw new Ct("commands.stopsound.unknownSoundSource", new Object[]{s2});
            }

            s = soundcategory.getName();
         }

         if (args.length == 3) {
            s1 = args[i++];
         }

         SA packetbuffer = new SA(Unpooled.buffer());
         packetbuffer.writeString(s);
         packetbuffer.writeString(s1);
         entityplayermp.connection.sendPacket(new TM("MC|StopSound", packetbuffer));
         if (s.isEmpty() && s1.isEmpty()) {
            notifyCommandListener(sender, this, "commands.stopsound.success.all", new Object[]{entityplayermp.getName()});
         } else if (s1.isEmpty()) {
            notifyCommandListener(sender, this, "commands.stopsound.success.soundSource", new Object[]{s, entityplayermp.getName()});
         } else {
            notifyCommandListener(sender, this, "commands.stopsound.success.individualSound", new Object[]{s1, s, entityplayermp.getName()});
         }

      } else {
         throw new Ej(this.getUsage(sender), new Object[0]);
      }
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      if (args.length == 1) {
         return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
      } else if (args.length == 2) {
         return getListOfStringsMatchingLastWord(args, SoundCategory.getSoundCategoryNames());
      } else {
         return args.length == 3 ? getListOfStringsMatchingLastWord(args, SoundEvent.REGISTRY.getKeys()) : Collections.emptyList();
      }
   }

   public boolean isUsernameIndex(String[] args, int index) {
      return index == 0;
   }
}
