package neo;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class Eb extends Ch {
   public Eb() {
   }

   public String getName() {
      return "summon";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getUsage(DB sender) {
      return "commands.summon.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length < 1) {
         throw new Ej("commands.summon.usage", new Object[0]);
      } else {
         String s = args[0];
         BlockPos blockpos = sender.getPosition();
         Vec3d vec3d = sender.getPositionVector();
         double d0 = vec3d.x;
         double d1 = vec3d.y;
         double d2 = vec3d.z;
         if (args.length >= 4) {
            d0 = parseDouble(d0, args[1], true);
            d1 = parseDouble(d1, args[2], false);
            d2 = parseDouble(d2, args[3], true);
            blockpos = new BlockPos(d0, d1, d2);
         }

         bij world = sender.getEntityWorld();
         if (!world.isBlockLoaded(blockpos)) {
            throw new Ct("commands.summon.outOfWorld", new Object[0]);
         } else {
            if (Ir.LIGHTNING_BOLT.equals(new ResourceLocation(s))) {
               world.addWeatherEffect(new HX(world, d0, d1, d2, false));
               notifyCommandListener(sender, this, "commands.summon.success", new Object[0]);
            } else {
               QQ nbttagcompound = new QQ();
               boolean flag = false;
               if (args.length >= 5) {
                  String s1 = buildString(args, 4);

                  try {
                     nbttagcompound = QG.getTagFromJson(s1);
                     flag = true;
                  } catch (QI var18) {
                     QI nbtexception = var18;
                     throw new Ct("commands.summon.tagError", new Object[]{nbtexception.getMessage()});
                  }
               }

               nbttagcompound.setString("id", s);
               Ig entity = bav.readWorldEntityPos(nbttagcompound, world, d0, d1, d2, true);
               if (entity == null) {
                  throw new Ct("commands.summon.failed", new Object[0]);
               }

               entity.setLocationAndAngles(d0, d1, d2, entity.rotationYaw, entity.rotationPitch);
               if (!flag && entity instanceof Iu) {
                  ((Iu)entity).onInitialSpawn(world.getDifficultyForLocation(new BlockPos(entity)), (ID)null);
               }

               notifyCommandListener(sender, this, "commands.summon.success", new Object[0]);
            }

         }
      }
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      if (args.length == 1) {
         return getListOfStringsMatchingLastWord(args, Ir.getEntityNameList());
      } else {
         return args.length > 1 && args.length <= 4 ? getTabCompletionCoordinate(args, 1, targetPos) : Collections.emptyList();
      }
   }
}
