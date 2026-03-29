package neo;

public class bnv {
   private int attachTo = 0;
   private ow modelRenderer = null;

   public bnv(int attachTo, ow modelRenderer) {
      this.attachTo = attachTo;
      this.modelRenderer = modelRenderer;
   }

   public ow getModelRenderer() {
      return this.modelRenderer;
   }

   public void render(nM modelBiped, float scale) {
      ow modelrenderer = bnt.getAttachModel(modelBiped, this.attachTo);
      if (modelrenderer != null) {
         modelrenderer.postRender(scale);
      }

      this.modelRenderer.render(scale);
   }
}
