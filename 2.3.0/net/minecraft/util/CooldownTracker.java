package net.minecraft.util;

import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import neo.OL;
import net.minecraft.util.math.MathHelper;

public class CooldownTracker {
   private final Map<OL, Cooldown> cooldowns = Maps.newHashMap();
   private int ticks;

   public CooldownTracker() {
   }

   public boolean hasCooldown(OL itemIn) {
      return this.getCooldown(itemIn, 0.0F) > 0.0F;
   }

   public float getCooldown(OL itemIn, float partialTicks) {
      Cooldown cooldowntracker$cooldown = (Cooldown)this.cooldowns.get(itemIn);
      if (cooldowntracker$cooldown != null) {
         float f = (float)(cooldowntracker$cooldown.expireTicks - cooldowntracker$cooldown.createTicks);
         float f1 = (float)cooldowntracker$cooldown.expireTicks - ((float)this.ticks + partialTicks);
         return MathHelper.clamp(f1 / f, 0.0F, 1.0F);
      } else {
         return 0.0F;
      }
   }

   public void tick() {
      ++this.ticks;
      if (!this.cooldowns.isEmpty()) {
         Iterator<Map.Entry<OL, Cooldown>> iterator = this.cooldowns.entrySet().iterator();

         while(iterator.hasNext()) {
            Map.Entry<OL, Cooldown> entry = (Map.Entry)iterator.next();
            if (((Cooldown)entry.getValue()).expireTicks <= this.ticks) {
               iterator.remove();
               this.notifyOnRemove((OL)entry.getKey());
            }
         }
      }

   }

   public void setCooldown(OL itemIn, int ticksIn) {
      this.cooldowns.put(itemIn, new Cooldown(this.ticks, this.ticks + ticksIn));
      this.notifyOnSet(itemIn, ticksIn);
   }

   public void removeCooldown(OL itemIn) {
      this.cooldowns.remove(itemIn);
      this.notifyOnRemove(itemIn);
   }

   protected void notifyOnSet(OL itemIn, int ticksIn) {
   }

   protected void notifyOnRemove(OL itemIn) {
   }

   class Cooldown {
      final int createTicks;
      final int expireTicks;

      private Cooldown(int createTicksIn, int expireTicksIn) {
         this.createTicks = createTicksIn;
         this.expireTicks = expireTicksIn;
      }

      // $FF: synthetic method
      Cooldown(int x1, int x2, Object x3) {
         this(x1, x2);
      }
   }
}
