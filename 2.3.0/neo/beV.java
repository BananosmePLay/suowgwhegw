package neo;

class beV {
   public Class<? extends bfg> pieceClass;
   public final int pieceWeight;
   public int instancesSpawned;
   public int instancesLimit;

   public beV(Class<? extends bfg> p_i2076_1_, int p_i2076_2_, int p_i2076_3_) {
      this.pieceClass = p_i2076_1_;
      this.pieceWeight = p_i2076_2_;
      this.instancesLimit = p_i2076_3_;
   }

   public boolean canSpawnMoreStructuresOfType(int p_75189_1_) {
      return this.instancesLimit == 0 || this.instancesSpawned < this.instancesLimit;
   }

   public boolean canSpawnMoreStructures() {
      return this.instancesLimit == 0 || this.instancesSpawned < this.instancesLimit;
   }
}
