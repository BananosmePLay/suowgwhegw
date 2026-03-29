package neo;

import org.lwjgl.opengl.GL11;

class xI {
   private final int capability;
   private boolean currentState;

   public xI(int capabilityIn) {
      this.capability = capabilityIn;
   }

   public void setDisabled() {
      this.setState(false);
   }

   public void setEnabled() {
      this.setState(true);
   }

   public void setState(boolean state) {
      if (state != this.currentState) {
         this.currentState = state;
         if (state) {
            GL11.glEnable(this.capability);
         } else {
            GL11.glDisable(this.capability);
         }
      }

   }

   // $FF: synthetic method
   static boolean access$100(xI x0) {
      return x0.currentState;
   }
}
