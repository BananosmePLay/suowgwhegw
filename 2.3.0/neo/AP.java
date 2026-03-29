package neo;

public class AP extends AN {
   private final AU resourcePackEntry;

   public AP(lr resourcePacksGUIIn, AU entry) {
      super(resourcePacksGUIIn);
      this.resourcePackEntry = entry;
   }

   protected void bindResourcePackIcon() {
      this.resourcePackEntry.bindTexturePackIcon(this.mc.getTextureManager());
   }

   protected int getResourcePackFormat() {
      return this.resourcePackEntry.getPackFormat();
   }

   protected String getResourcePackDescription() {
      return this.resourcePackEntry.getTexturePackDescription();
   }

   protected String getResourcePackName() {
      return this.resourcePackEntry.getResourcePackName();
   }

   public AU getResourcePackEntry() {
      return this.resourcePackEntry;
   }
}
