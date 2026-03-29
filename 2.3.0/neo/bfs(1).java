package neo;

public class bfs {
   public Class<? extends bfw> villagePieceClass;
   public final int villagePieceWeight;
   public int villagePiecesSpawned;
   public int villagePiecesLimit;

   public bfs(Class<? extends bfw> p_i2098_1_, int p_i2098_2_, int p_i2098_3_) {
      this.villagePieceClass = p_i2098_1_;
      this.villagePieceWeight = p_i2098_2_;
      this.villagePiecesLimit = p_i2098_3_;
   }

   public boolean canSpawnMoreVillagePiecesOfType(int componentType) {
      return this.villagePiecesLimit == 0 || this.villagePiecesSpawned < this.villagePiecesLimit;
   }

   public boolean canSpawnMoreVillagePieces() {
      return this.villagePiecesLimit == 0 || this.villagePiecesSpawned < this.villagePiecesLimit;
   }
}
