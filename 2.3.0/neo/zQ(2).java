package neo;

public enum zQ {
   POSITION("Position"),
   NORMAL("Normal"),
   COLOR("Vertex Color"),
   UV("UV"),
   MATRIX("Bone Matrix"),
   BLEND_WEIGHT("Blend Weight"),
   PADDING("Padding");

   private final String displayName;

   private zQ(String displayNameIn) {
      this.displayName = displayNameIn;
   }

   public String getDisplayName() {
      return this.displayName;
   }
}
