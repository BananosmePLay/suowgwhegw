package neo;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class CY extends Ch {
   public CY() {
   }

   public String getName() {
      return "tp";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getUsage(DB sender) {
      return "commands.tp.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length < 1) {
         throw new Ej("commands.tp.usage", new Object[0]);
      } else {
         int i = 0;
         Object entity;
         if (args.length != 2 && args.length != 4 && args.length != 6) {
            entity = getCommandSenderAsPlayer(sender);
         } else {
            entity = getEntity(server, sender, args[0]);
            i = 1;
         }

         if (args.length != 1 && args.length != 2) {
            if (args.length < i + 3) {
               throw new Ej("commands.tp.usage", new Object[0]);
            }

            if (((Ig)entity).world != null) {
               int j = true;
               int k = i + 1;
               Cg commandbase$coordinatearg = parseCoordinate(((Ig)entity).posX, args[i], true);
               Cg commandbase$coordinatearg1 = parseCoordinate(((Ig)entity).posY, args[k++], -4096, 4096, false);
               Cg commandbase$coordinatearg2 = parseCoordinate(((Ig)entity).posZ, args[k++], true);
               Cg commandbase$coordinatearg3 = parseCoordinate((double)((Ig)entity).rotationYaw, args.length > k ? args[k++] : "~", false);
               Cg commandbase$coordinatearg4 = parseCoordinate((double)((Ig)entity).rotationPitch, args.length > k ? args[k] : "~", false);
               teleportEntityToCoordinates((Ig)entity, commandbase$coordinatearg, commandbase$coordinatearg1, commandbase$coordinatearg2, commandbase$coordinatearg3, commandbase$coordinatearg4);
               notifyCommandListener(sender, this, "commands.tp.success.coordinates", new Object[]{((Ig)entity).getName(), commandbase$coordinatearg.getResult(), commandbase$coordinatearg1.getResult(), commandbase$coordinatearg2.getResult()});
            }
         } else {
            Ig entity1 = getEntity(server, sender, args[args.length - 1]);
            if (entity1.world != ((Ig)entity).world) {
               throw new Ct("commands.tp.notSameDimension", new Object[0]);
            }

            ((Ig)entity).dismountRidingEntity();
            if (entity instanceof MG) {
               ((MG)entity).connection.setPlayerLocation(entity1.posX, entity1.posY, entity1.posZ, entity1.rotationYaw, entity1.rotationPitch);
            } else {
               ((Ig)entity).setLocationAndAngles(entity1.posX, entity1.posY, entity1.posZ, entity1.rotationYaw, entity1.rotationPitch);
            }

            notifyCommandListener(sender, this, "commands.tp.success", new Object[]{((Ig)entity).getName(), entity1.getName()});
         }

      }
   }

   private static void teleportEntityToCoordinates(Ig teleportingEntity, Cg argX, Cg argY, Cg argZ, Cg argYaw, Cg argPitch) {
      float f;
      if (teleportingEntity instanceof MG) {
         Set<Ux> set = EnumSet.noneOf(Ux.class);
         if (argX.isRelative()) {
            set.add(Ux.X);
         }

         if (argY.isRelative()) {
            set.add(Ux.Y);
         }

         if (argZ.isRelative()) {
            set.add(Ux.Z);
         }

         if (argPitch.isRelative()) {
            set.add(Ux.X_ROT);
         }

         if (argYaw.isRelative()) {
            set.add(Ux.Y_ROT);
         }

         f = (float)argYaw.getAmount();
         if (!argYaw.isRelative()) {
            f = MathHelper.wrapDegrees(f);
         }

         float f1 = (float)argPitch.getAmount();
         if (!argPitch.isRelative()) {
            f1 = MathHelper.wrapDegrees(f1);
         }

         teleportingEntity.dismountRidingEntity();
         ((MG)teleportingEntity).connection.setPlayerLocation(argX.getAmount(), argY.getAmount(), argZ.getAmount(), f, f1, set);
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
      return args.length != 1 && args.length != 2 ? Collections.emptyList() : getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
   }

   public boolean isUsernameIndex(String[] args, int index) {
      return index == 0;
   }
}
