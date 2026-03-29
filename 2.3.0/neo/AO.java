package neo;

public class AO extends AQ {
   public AO(lr resourcePacksGUIIn) {
      super(resourcePacksGUIIn, nC.getMinecraft().getResourcePackRepository().rprDefaultResourcePack);
   }

   protected String getResourcePackName() {
      return "Default";
   }

   public boolean isServerPack() {
      return false;
   }
}
