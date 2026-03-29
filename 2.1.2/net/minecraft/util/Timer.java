package net.minecraft.util;

import net.minecraft.client.Minecraft;

public class Timer {
   public int elapsedTicks;
   public float renderPartialTicks;
   public float elapsedPartialTicks;
   private long lastSyncSysClock;
   private float tickLength;
   public float timerSpeed = 1.0F;

   public Timer(float tps) {
      this.tickLength = 1000.0F / tps;
      this.lastSyncSysClock = Minecraft.getSystemTime();
   }

   public void updateTimer() {
      long i = Minecraft.getSystemTime();
      this.elapsedPartialTicks = (float)((double)((float)(i - this.lastSyncSysClock) / this.tickLength) * (double)this.timerSpeed);
      this.lastSyncSysClock = i;
      this.renderPartialTicks += this.elapsedPartialTicks;
      this.elapsedTicks = (int)this.renderPartialTicks;
      this.renderPartialTicks -= (float)this.elapsedTicks;
   }
}
