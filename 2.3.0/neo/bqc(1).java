package neo;

public enum bqc {
   TEXTURE_1D(3552),
   TEXTURE_2D(3553),
   TEXTURE_3D(32879),
   TEXTURE_RECTANGLE(34037);

   private int id;

   private bqc(int id) {
      this.id = id;
   }

   public int getId() {
      return this.id;
   }
}
