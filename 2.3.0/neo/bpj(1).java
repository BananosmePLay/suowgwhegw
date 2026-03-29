package neo;

public enum bpj {
   NONE(""),
   SHADOW("shadow"),
   GBUFFERS("gbuffers"),
   DEFERRED("deferred"),
   COMPOSITE("composite");

   private String name;

   private bpj(String name) {
      this.name = name;
   }

   public String getName() {
      return this.name;
   }
}
