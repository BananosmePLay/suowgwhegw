package neo;

public enum bpF implements blV {
   IS_ALIVE("is_alive"),
   IS_BURNING("is_burning"),
   IS_CHILD("is_child"),
   IS_GLOWING("is_glowing"),
   IS_HURT("is_hurt"),
   IS_IN_LAVA("is_in_lava"),
   IS_IN_WATER("is_in_water"),
   IS_INVISIBLE("is_invisible"),
   IS_ON_GROUND("is_on_ground"),
   IS_RIDDEN("is_ridden"),
   IS_RIDING("is_riding"),
   IS_SNEAKING("is_sneaking"),
   IS_SPRINTING("is_sprinting"),
   IS_WET("is_wet");

   private String name;
   private wC renderManager;
   private static final bpF[] VALUES = values();

   private bpF(String name) {
      this.name = name;
      this.renderManager = nC.getMinecraft().getRenderManager();
   }

   public String getName() {
      return this.name;
   }

   public boolean eval() {
      Ig entity = nC.getMinecraft().getRenderViewEntity();
      if (entity instanceof Iw) {
         Iw entitylivingbase = (Iw)entity;
         switch (this) {
            case IS_ALIVE:
               return entitylivingbase.isEntityAlive();
            case IS_BURNING:
               return entitylivingbase.isBurning();
            case IS_CHILD:
               return entitylivingbase.isChild();
            case IS_GLOWING:
               return entitylivingbase.isGlowing();
            case IS_HURT:
               return entitylivingbase.hurtTime > 0;
            case IS_IN_LAVA:
               return entitylivingbase.isInLava();
            case IS_IN_WATER:
               return entitylivingbase.isInWater();
            case IS_INVISIBLE:
               return entitylivingbase.isInvisible();
            case IS_ON_GROUND:
               return entitylivingbase.onGround;
            case IS_RIDDEN:
               return entitylivingbase.isBeingRidden();
            case IS_RIDING:
               return entitylivingbase.isRiding();
            case IS_SNEAKING:
               return entitylivingbase.isSneaking();
            case IS_SPRINTING:
               return entitylivingbase.isSprinting();
            case IS_WET:
               return entitylivingbase.isWet();
         }
      }

      return false;
   }

   public static bpF parse(String str) {
      if (str == null) {
         return null;
      } else {
         for(int i = 0; i < VALUES.length; ++i) {
            bpF shaderparameterbool = VALUES[i];
            if (shaderparameterbool.getName().equals(str)) {
               return shaderparameterbool;
            }
         }

         return null;
      }
   }
}
