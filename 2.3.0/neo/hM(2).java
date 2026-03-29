package neo;

public class hM {
   public static final hM AIR;
   public static final hM GRASS;
   public static final hM GROUND;
   public static final hM WOOD;
   public static final hM ROCK;
   public static final hM IRON;
   public static final hM ANVIL;
   public static final hM WATER;
   public static final hM LAVA;
   public static final hM LEAVES;
   public static final hM PLANTS;
   public static final hM VINE;
   public static final hM SPONGE;
   public static final hM CLOTH;
   public static final hM FIRE;
   public static final hM SAND;
   public static final hM CIRCUITS;
   public static final hM CARPET;
   public static final hM GLASS;
   public static final hM REDSTONE_LIGHT;
   public static final hM TNT;
   public static final hM CORAL;
   public static final hM ICE;
   public static final hM PACKED_ICE;
   public static final hM SNOW;
   public static final hM CRAFTED_SNOW;
   public static final hM CACTUS;
   public static final hM CLAY;
   public static final hM GOURD;
   public static final hM DRAGON_EGG;
   public static final hM PORTAL;
   public static final hM CAKE;
   public static final hM WEB;
   public static final hM PISTON;
   public static final hM BARRIER;
   public static final hM STRUCTURE_VOID;
   private boolean canBurn;
   private boolean replaceable;
   private boolean isTranslucent;
   private final hK materialMapColor;
   private boolean requiresNoTool = true;
   private hJ pushReaction;
   private boolean isAdventureModeExempt;

   public hM(hK color) {
      this.pushReaction = hJ.NORMAL;
      this.materialMapColor = color;
   }

   public boolean isLiquid() {
      return false;
   }

   public boolean isSolid() {
      return true;
   }

   public boolean blocksLight() {
      return true;
   }

   public boolean blocksMovement() {
      return true;
   }

   private hM setTranslucent() {
      this.isTranslucent = true;
      return this;
   }

   protected hM setRequiresTool() {
      this.requiresNoTool = false;
      return this;
   }

   protected hM setBurning() {
      this.canBurn = true;
      return this;
   }

   public boolean getCanBurn() {
      return this.canBurn;
   }

   public hM setReplaceable() {
      this.replaceable = true;
      return this;
   }

   public boolean isReplaceable() {
      return this.replaceable;
   }

   public boolean isOpaque() {
      return this.isTranslucent ? false : this.blocksMovement();
   }

   public boolean isToolNotRequired() {
      return this.requiresNoTool;
   }

   public hJ getPushReaction() {
      return this.pushReaction;
   }

   protected hM setNoPushMobility() {
      this.pushReaction = hJ.DESTROY;
      return this;
   }

   protected hM setImmovableMobility() {
      this.pushReaction = hJ.BLOCK;
      return this;
   }

   protected hM setAdventureModeExempt() {
      this.isAdventureModeExempt = true;
      return this;
   }

   public hK getMaterialMapColor() {
      return this.materialMapColor;
   }

   static {
      AIR = new hQ(hK.AIR);
      GRASS = new hM(hK.GRASS);
      GROUND = new hM(hK.DIRT);
      WOOD = (new hM(hK.WOOD)).setBurning();
      ROCK = (new hM(hK.STONE)).setRequiresTool();
      IRON = (new hM(hK.IRON)).setRequiresTool();
      ANVIL = (new hM(hK.IRON)).setRequiresTool().setImmovableMobility();
      WATER = (new hN(hK.WATER)).setNoPushMobility();
      LAVA = (new hN(hK.TNT)).setNoPushMobility();
      LEAVES = (new hM(hK.FOLIAGE)).setBurning().setTranslucent().setNoPushMobility();
      PLANTS = (new hO(hK.FOLIAGE)).setNoPushMobility();
      VINE = (new hO(hK.FOLIAGE)).setBurning().setNoPushMobility().setReplaceable();
      SPONGE = new hM(hK.YELLOW);
      CLOTH = (new hM(hK.CLOTH)).setBurning();
      FIRE = (new hQ(hK.AIR)).setNoPushMobility();
      SAND = new hM(hK.SAND);
      CIRCUITS = (new hO(hK.AIR)).setNoPushMobility();
      CARPET = (new hO(hK.CLOTH)).setBurning();
      GLASS = (new hM(hK.AIR)).setTranslucent().setAdventureModeExempt();
      REDSTONE_LIGHT = (new hM(hK.AIR)).setAdventureModeExempt();
      TNT = (new hM(hK.TNT)).setBurning().setTranslucent();
      CORAL = (new hM(hK.FOLIAGE)).setNoPushMobility();
      ICE = (new hM(hK.ICE)).setTranslucent().setAdventureModeExempt();
      PACKED_ICE = (new hM(hK.ICE)).setAdventureModeExempt();
      SNOW = (new hO(hK.SNOW)).setReplaceable().setTranslucent().setRequiresTool().setNoPushMobility();
      CRAFTED_SNOW = (new hM(hK.SNOW)).setRequiresTool();
      CACTUS = (new hM(hK.FOLIAGE)).setTranslucent().setNoPushMobility();
      CLAY = new hM(hK.CLAY);
      GOURD = (new hM(hK.FOLIAGE)).setNoPushMobility();
      DRAGON_EGG = (new hM(hK.FOLIAGE)).setNoPushMobility();
      PORTAL = (new hP(hK.AIR)).setImmovableMobility();
      CAKE = (new hM(hK.AIR)).setNoPushMobility();
      WEB = (new hM(hK.CLOTH) {
         public boolean blocksMovement() {
            return false;
         }
      }).setRequiresTool().setNoPushMobility();
      PISTON = (new hM(hK.STONE)).setImmovableMobility();
      BARRIER = (new hM(hK.AIR)).setRequiresTool().setImmovableMobility();
      STRUCTURE_VOID = new hQ(hK.AIR);
   }
}
