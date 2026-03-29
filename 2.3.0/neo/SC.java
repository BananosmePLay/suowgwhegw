package neo;

import net.minecraft.util.IThreadListener;

public class SC {
   public static int lastDimensionId = Integer.MIN_VALUE;

   public SC() {
   }

   public static <T extends RH> void checkThreadAndEnqueue(final Sz<T> packetIn, final T processor, IThreadListener scheduler) throws VE {
      if (!scheduler.isCallingFromMinecraftThread()) {
         scheduler.addScheduledTask(new Runnable() {
            public void run() {
               SC.clientPreProcessPacket(packetIn);
               packetIn.processPacket(processor);
            }
         });
         throw VE.INSTANCE;
      } else {
         clientPreProcessPacket(packetIn);
      }
   }

   protected static void clientPreProcessPacket(Sz p_clientPreProcessPacket_0_) {
      if (p_clientPreProcessPacket_0_ instanceof Uy) {
         XH.getRenderGlobal().onPlayerPositionSet();
      }

      if (p_clientPreProcessPacket_0_ instanceof UD) {
         UD spacketrespawn = (UD)p_clientPreProcessPacket_0_;
         lastDimensionId = spacketrespawn.getDimensionID();
      } else if (p_clientPreProcessPacket_0_ instanceof Ui) {
         Ui spacketjoingame = (Ui)p_clientPreProcessPacket_0_;
         lastDimensionId = spacketjoingame.getDimension();
      } else {
         lastDimensionId = Integer.MIN_VALUE;
      }

   }
}
