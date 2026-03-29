package neo;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class Ec extends Ch {
   public Ec() {
   }

   public String getName() {
      return "teleport";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getUsage(DB sender) {
      return "commands.teleport.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length < 4) {
         throw new Ej("commands.teleport.usage", new Object[0]);
      } else {
         Ig entity = getEntity(server, sender, args[0]);
         if (entity.world != null) {
            int i = true;
            Vec3d vec3d = sender.getPositionVector();
            int j = 1;
            Cg commandbase$coordinatearg = parseCoordinate(vec3d.x, args[j++], true);
            Cg commandbase$coordinatearg1 = parseCoordinate(vec3d.y, args[j++], -4096, 4096, false);
            Cg commandbase$coordinatearg2 = parseCoordinate(vec3d.z, args[j++], true);
            Ig entity1 = sender.getCommandSenderEntity() == null ? entity : sender.getCommandSenderEntity();
            Cg commandbase$coordinatearg3 = parseCoordinate(args.length > j ? (double)entity1.rotationYaw : (double)entity.rotationYaw, args.length > j ? args[j] : "~", false);
            ++j;
            Cg commandbase$coordinatearg4 = parseCoordinate(args.length > j ? (double)entity1.rotationPitch : (double)entity.rotationPitch, args.length > j ? args[j] : "~", false);
            doTeleport(entity, commandbase$coordinatearg, commandbase$coordinatearg1, commandbase$coordinatearg2, commandbase$coordinatearg3, commandbase$coordinatearg4);
            notifyCommandListener(sender, this, "commands.teleport.success.coordinates", new Object[]{entity.getName(), commandbase$coordinatearg.getResult(), commandbase$coordinatearg1.getResult(), commandbase$coordinatearg2.getResult()});
         }

      }
   }

   private static void doTeleport(Ig teleportingEntity, Cg argX, Cg argY, Cg argZ, Cg argYaw, Cg argPitch) {
      float f;
      if (teleportingEntity instanceof MG) {
         Set<Ux> set = EnumSet.noneOf(Ux.class);
         f = (float)argYaw.getAmount();
         if (argYaw.isRelative()) {
            set.add(Ux.Y_ROT);
         } else {
            f = MathHelper.wrapDegrees(f);
         }

         float f1 = (float)argPitch.getAmount();
         if (argPitch.isRelative()) {
            set.add(Ux.X_ROT);
         } else {
            f1 = MathHelper.wrapDegrees(f1);
         }

         teleportingEntity.dismountRidingEntity();
         ((MG)teleportingEntity).connection.setPlayerLocation(argX.getResult(), argY.getResult(), argZ.getResult(), f, f1, set);
         teleportingEntity.setRotationYawHead(f);
      } else {
         float f2 = (float)MathHelper.wrapDegrees(argYaw.getResult());
         f = (float)MathHelper.wrapDegrees(argPitch.getResult());
         f = MathHelper.clamp(f, -90.0F, 90.0F);
         teleportingEntity.setLocationAndAngles(argX.getResult(), argY.getResult(), argZ.getResult(), f2, f);
         teleportingEntity.setRotationYawHead(f2);
      }

      if (!(teleportingEntity instanceof Iw) || !((Iw)teleportingEntity).isElytraFlying()) {
         teleportingEntity.motionY = 0.0;
         teleportingEntity.onGround = true;
      }

   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      if (args.length == 1) {
         return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
      } else {
         return args.length > 1 && args.length <= 4 ? getTabCompletionCoordinate(args, 1, targetPos) : Collections.emptyList();
      }
   }

   public boolean isUsernameIndex(String[] args, int index) {
      return index == 0;
   }
}
