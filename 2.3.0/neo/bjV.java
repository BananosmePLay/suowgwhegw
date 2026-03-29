package neo;

public class bjV {
   private bjZ[] modelVariableUpdaters;

   public bjV(bjZ[] modelVariableUpdaters) {
      this.modelVariableUpdaters = modelVariableUpdaters;
   }

   public void update() {
      for(int i = 0; i < this.modelVariableUpdaters.length; ++i) {
         bjZ modelvariableupdater = this.modelVariableUpdaters[i];
         modelvariableupdater.update();
      }

   }

   public boolean initialize(bjS mr) {
      for(int i = 0; i < this.modelVariableUpdaters.length; ++i) {
         bjZ modelvariableupdater = this.modelVariableUpdaters[i];
         if (!modelvariableupdater.initialize(mr)) {
            return false;
         }
      }

      return true;
   }
}
