package neo;

public class biv extends bis {
   private final bis delegate;

   public biv(Xx server, bgm saveHandlerIn, int dimensionId, bis delegate, Wk profilerIn) {
      super(server, saveHandlerIn, new bgj(delegate.getWorldInfo()), dimensionId, profilerIn);
      this.delegate = delegate;
      delegate.getWorldBorder().addListener(new ZZ() {
         public void onSizeChanged(bab border, double newSize) {
            biv.this.getWorldBorder().setTransition(newSize);
         }

         public void onTransitionStarted(bab border, double oldSize, double newSize, long time) {
            biv.this.getWorldBorder().setTransition(oldSize, newSize, time);
         }

         public void onCenterChanged(bab border, double x, double z) {
            biv.this.getWorldBorder().setCenter(x, z);
         }

         public void onWarningTimeChanged(bab border, int newTime) {
            biv.this.getWorldBorder().setWarningTime(newTime);
         }

         public void onWarningDistanceChanged(bab border, int newDistance) {
            biv.this.getWorldBorder().setWarningDistance(newDistance);
         }

         public void onDamageAmountChanged(bab border, double newAmount) {
            biv.this.getWorldBorder().setDamageAmount(newAmount);
         }

         public void onDamageBufferChanged(bab border, double newSize) {
            biv.this.getWorldBorder().setDamageBuffer(newSize);
         }
      });
   }

   protected void saveLevel() throws bgf {
   }

   public bij init() {
      this.mapStorage = this.delegate.getMapStorage();
      this.worldScoreboard = this.delegate.getScoreboard();
      this.lootTable = this.delegate.getLootTableManager();
      this.advancementManager = this.delegate.getAdvancementManager();
      String s = Zb.fileNameForProvider(this.provider);
      Zb villagecollection = (Zb)this.mapStorage.getOrLoadData(Zb.class, s);
      if (villagecollection == null) {
         this.villageCollection = new Zb(this);
         this.mapStorage.setData(s, this.villageCollection);
      } else {
         this.villageCollection = villagecollection;
         this.villageCollection.setWorldsForAll(this);
      }

      return this;
   }

   public void saveAdditionalData() {
      this.provider.onWorldSave();
   }
}
