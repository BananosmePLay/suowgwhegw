package net.minecraft.util;

import neo.MG;
import neo.OL;
import neo.TL;

public class CooldownTrackerServer extends CooldownTracker {
   private final MG player;

   public CooldownTrackerServer(MG playerIn) {
      this.player = playerIn;
   }

   protected void notifyOnSet(OL itemIn, int ticksIn) {
      super.notifyOnSet(itemIn, ticksIn);
      this.player.connection.sendPacket(new TL(itemIn, ticksIn));
   }

   protected void notifyOnRemove(OL itemIn) {
      super.notifyOnRemove(itemIn);
      this.player.connection.sendPacket(new TL(itemIn, 0));
   }
}
