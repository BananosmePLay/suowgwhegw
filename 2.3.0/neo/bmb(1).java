package neo;

public class bmb implements bma {
   private blN[] parameterTypes;

   public bmb(blN[] parameterTypes) {
      this.parameterTypes = parameterTypes;
   }

   public blN[] getParameterTypes(blU[] params) {
      return this.parameterTypes;
   }
}
