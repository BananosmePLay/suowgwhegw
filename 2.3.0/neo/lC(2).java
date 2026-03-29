package neo;

import net.minecraft.util.math.Vec3d;

public class lC {
   private final String subtitle;
   private long startTime;
   private Vec3d location;
   // $FF: synthetic field
   final lD this$0;

   public lC(lD this$0, String subtitleIn, Vec3d locationIn) {
      this.this$0 = this$0;
      this.subtitle = subtitleIn;
      this.location = locationIn;
      this.startTime = nC.getSystemTime();
   }

   public String getString() {
      return this.subtitle;
   }

   public long getStartTime() {
      return this.startTime;
   }

   public Vec3d getLocation() {
      return this.location;
   }

   public void refresh(Vec3d locationIn) {
      this.location = locationIn;
      this.startTime = nC.getSystemTime();
   }
}
