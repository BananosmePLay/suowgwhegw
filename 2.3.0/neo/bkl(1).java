package neo;

public class bkl {
   private String modelPart;
   private boolean attach;
   private ow modelRenderer;
   private bjV modelUpdater;

   public bkl(String modelPart, boolean attach, ow modelRenderer, bjV modelUpdater) {
      this.modelPart = modelPart;
      this.attach = attach;
      this.modelRenderer = modelRenderer;
      this.modelUpdater = modelUpdater;
   }

   public ow getModelRenderer() {
      return this.modelRenderer;
   }

   public String getModelPart() {
      return this.modelPart;
   }

   public boolean isAttach() {
      return this.attach;
   }

   public bjV getModelUpdater() {
      return this.modelUpdater;
   }
}
